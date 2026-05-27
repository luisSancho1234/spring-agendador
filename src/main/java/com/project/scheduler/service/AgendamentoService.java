package com.project.scheduler.service;

import com.project.scheduler.models.dto.AgendamentoDto;
import com.project.scheduler.models.entidades.AgendamentoEntidade;
import com.project.scheduler.models.entidades.UsuarioEntidade;
import com.project.scheduler.models.enums.AgendamentoStatusEnum;
import com.project.scheduler.models.forms.AgendamentoForm;
import com.project.scheduler.repository.AgendamentoRepository;
import com.project.scheduler.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoService {

    private static final Duration TEMPO_LOCK = Duration.ofMinutes(5);

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public AgendamentoDto criarAgendamento(AgendamentoForm form) {

        log.info(
            "Criando agendamento. dataForm={}, agoraServidor={}, diferencaMinutos={}",
            form.getAgeDtAgenda(),
            new Date(),
            Duration.between(new Date().toInstant(), form.getAgeDtAgenda().toInstant()).toMinutes()
        );

        UsuarioEntidade usuario = buscarUsuarioAutenticado();

        AgendamentoEntidade entidade = new AgendamentoEntidade();
        entidade.setAgeDtAgendamento(form.getAgeDtAgenda());
        entidade.setAgeTxNome(form.getAgeTxNome());
        entidade.setAgeObjUsuario(usuario);
        entidade.setAgeTxStatus(AgendamentoStatusEnum.PENDENTE);
        entidade.setAgeNrTentativas(0);

        AgendamentoEntidade salvo = agendamentoRepository.save(entidade);

        log.info(
                "Agendamento criado. id={}, nome={}, data={}",
                salvo.getAgeNrId(),
                salvo.getAgeTxNome(),
                salvo.getAgeDtAgendamento()
        );

        return AgendamentoDto.of(salvo);
    }

    @Transactional
    public List<Long> reservarPendentesParaProcessamento(int limit) {
        Date agora = new Date();

        List<AgendamentoEntidade> agendamentos =
                agendamentoRepository.buscarElegiveisParaProcessamentoComLock(limit, agora);

        if (agendamentos.isEmpty()) {
            return List.of();
        }

        Date lockAte = Date.from(agora.toInstant().plus(TEMPO_LOCK));

        agendamentos.forEach(agendamento -> {
            agendamento.setAgeTxStatus(AgendamentoStatusEnum.PROCESSANDO);
            agendamento.setAgeDtProcessamento(agora);
            agendamento.setAgeDtLockAte(lockAte);

            Integer tentativas = agendamento.getAgeNrTentativas();
            agendamento.setAgeNrTentativas(tentativas == null ? 1 : tentativas + 1);
        });

        return agendamentos.stream()
                .map(AgendamentoEntidade::getAgeNrId)
                .toList();
    }

    @Transactional
    public void processarAgendamento(Long id) {
        AgendamentoEntidade agendamento = agendamentoRepository.findById(id)
                .orElseThrow();

        if (agendamento.getAgeTxStatus() != AgendamentoStatusEnum.PROCESSANDO) {
            log.info(
                    "Agendamento ignorado. id={}, statusAtual={}",
                    agendamento.getAgeNrId(),
                    agendamento.getAgeTxStatus()
            );
            return;
        }

        try {
            log.info(
                    "Simulando envio de e-mail. id={}, nome={}, dataAgendada={}, thread={}",
                    agendamento.getAgeNrId(),
                    agendamento.getAgeTxNome(),
                    agendamento.getAgeDtAgendamento(),
                    Thread.currentThread().getName()
            );

            agendamento.setAgeTxStatus(AgendamentoStatusEnum.CONCLUIDO);
            agendamento.setAgeDtConclusao(new Date());
            agendamento.setAgeDtLockAte(null);
            agendamento.setAgeTxUltimoErro(null);

            log.info("Agendamento concluído. id={}", agendamento.getAgeNrId());

        } catch (Exception e) {
            log.error("Erro ao processar agendamento. id={}", id, e);

            agendamento.setAgeTxStatus(AgendamentoStatusEnum.ERRO);
            agendamento.setAgeTxUltimoErro(e.getMessage());
            agendamento.setAgeDtLockAte(null);
        }
    }

    @Transactional
    public void cancelarAgendamento(Long id) {
        AgendamentoEntidade agendamento = agendamentoRepository.findById(id)
                .orElseThrow();

        if (agendamento.getAgeTxStatus() == AgendamentoStatusEnum.CONCLUIDO) {
            throw new IllegalStateException("Não é possível cancelar um agendamento já concluído.");
        }

        if (agendamento.getAgeTxStatus() == AgendamentoStatusEnum.PROCESSANDO) {
            throw new IllegalStateException("Não é possível cancelar um agendamento em processamento.");
        }

        agendamento.setAgeTxStatus(AgendamentoStatusEnum.CANCELADO);
        agendamento.setAgeDtLockAte(null);

        log.info("Agendamento cancelado. id={}", id);
    }

    @Transactional
    public AgendamentoDto reagendarAgendamento(Long id, AgendamentoForm form) {
        AgendamentoEntidade agendamento = agendamentoRepository.findById(id)
                .orElseThrow();

        if (agendamento.getAgeTxStatus() == AgendamentoStatusEnum.CONCLUIDO) {
            throw new IllegalStateException("Não é possível reagendar um agendamento já concluído.");
        }

        if (agendamento.getAgeTxStatus() == AgendamentoStatusEnum.PROCESSANDO) {
            throw new IllegalStateException("Não é possível reagendar um agendamento em processamento.");
        }

        agendamento.setAgeTxNome(form.getAgeTxNome());
        agendamento.setAgeDtAgendamento(form.getAgeDtAgenda());
        agendamento.setAgeTxStatus(AgendamentoStatusEnum.PENDENTE);
        agendamento.setAgeDtLockAte(null);
        agendamento.setAgeTxUltimoErro(null);

        log.info("Agendamento reagendado. id={}, novaData={}", id, form.getAgeDtAgenda());

        return AgendamentoDto.of(agendamento);
    }

    @Transactional(readOnly = true)
    public Page<AgendamentoDto> buscarAgendamentosPorUsuario(AgendamentoStatusEnum status, Pageable pageable) {
        long usuNrId = buscarUsuarioAutenticado().getUsuNrId();

        return this.agendamentoRepository
                .findByUsuarioOrdenadoPorStatus(usuNrId, pageable, status)
                .map(AgendamentoDto::of);
    }

    @Transactional(readOnly = true)
    public AgendamentoDto buscarAgendamento(long ageNrId) {
        return  this.agendamentoRepository.findById(ageNrId).map(AgendamentoDto::of).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));


    }

    private UsuarioEntidade buscarUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || !(auth.getPrincipal() instanceof UserDetails user)) {
            throw new IllegalStateException("Usuário não autenticado.");
        }

        String email = user.getUsername();

        return usuarioRepository
                .findByUsuTxEmail(email)
                .orElseThrow();
    }
}
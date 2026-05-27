package com.project.scheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoWorker {

    private static final int TAMANHO_LOTE = 50;

    private final AgendamentoService agendamentoService;

    @Scheduled(fixedDelayString = "${scheduler.agendamento.fixed-delay-ms:5000}")
    public void processarAgendamentosPendentes() {
        List<Long> ids = agendamentoService.reservarPendentesParaProcessamento(TAMANHO_LOTE);

        if (ids.isEmpty()) {
            return;
        }

        log.info("Worker recebeu {} agendamentos para processar", ids.size());

        ids.forEach(agendamentoService::processarAgendamento);
    }
}
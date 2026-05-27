package com.project.scheduler.controller;

import com.project.scheduler.models.dto.AgendamentoDto;
import com.project.scheduler.models.enums.AgendamentoStatusEnum;
import com.project.scheduler.models.forms.AgendamentoForm;
import com.project.scheduler.models.response.ResponseDto;
import com.project.scheduler.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<ResponseDto<AgendamentoDto>> postAgendamento(@RequestBody AgendamentoForm form) {
        AgendamentoDto agendamento = this.agendamentoService.criarAgendamento(form);
        return ResponseDto.created(agendamento.ageNrId(), agendamento);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto<AgendamentoDto>> reagendar(
            @PathVariable Long id,
            @RequestBody AgendamentoForm form
    ) {
        AgendamentoDto agendamento = this.agendamentoService.reagendarAgendamento(id, form);
        return ResponseDto.ok(agendamento);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        this.agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoDto>> getAgendamentos(AgendamentoStatusEnum status, Pageable pageable) {
        Page<AgendamentoDto> agendamentos = this.agendamentoService.buscarAgendamentosPorUsuario(status, pageable);
        Page<AgendamentoDto> a = agendamentos;
        return ResponseEntity.ok(a);
    }

    @GetMapping("{id}")
    public ResponseEntity<AgendamentoDto> getAgendamento(@PathVariable Long id) {
        AgendamentoDto agendamentos = this.agendamentoService.buscarAgendamento(id);
        return ResponseEntity.ok(agendamentos);
    }
}

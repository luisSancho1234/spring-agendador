package com.project.scheduler.models.dto;

import com.project.scheduler.models.entidades.AgendamentoEntidade;
import com.project.scheduler.models.enums.AgendamentoStatusEnum;

import java.util.Date;

public record AgendamentoDto (
    Long ageNrId,
    String ageTxNome,
    Date ageDtAgendamento,
    AgendamentoStatusEnum ageTxStatus
){
    public static AgendamentoDto of(AgendamentoEntidade entidade){
        return new AgendamentoDto(
            entidade.getAgeNrId(),
            entidade.getAgeTxNome(),
            entidade.getAgeDtAgendamento(),
            entidade.getAgeTxStatus()
        );
    }
}

package com.project.scheduler.models.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AgendamentoForm {
    @NonNull
    private String ageTxNome;
    @NonNull
    private Date ageDtAgenda;
}

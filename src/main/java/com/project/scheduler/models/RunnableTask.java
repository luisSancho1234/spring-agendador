package com.project.scheduler.models;

import com.project.scheduler.models.entidades.AgendamentoEntidade;
import com.project.scheduler.models.enums.TaskType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class RunnableTask implements Runnable{
    private final TaskType taskType;
    private final List<AgendamentoEntidade> agendamentos;
    //EmailService emailService = new EmailService()

    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public RunnableTask(List<AgendamentoEntidade> agendamentos, TaskType taskType) {
        this.taskType = taskType;
        this.agendamentos = agendamentos;
    }

    @Override
    public void run() {
        //email service method
        switch (taskType){
            case TESTE: {
                agendamentos.forEach(agendamento -> {
                    log.info(
                            "{} - Runnable Task with {} on thread {}",
                            LocalDateTime.now().format(this.formater),
                            agendamento.getAgeTxNome(),
                            Thread.currentThread().getName()
                    );
                });
            }
            case ENVIAR_EMAIL: {
                agendamentos.forEach(agendamento -> {});
            }
        }

    }
}

package com.project.scheduler.models.entidades;

import com.project.scheduler.models.enums.AgendamentoStatusEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="age_agendamento", schema = "agendamento")
public class AgendamentoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_nr_id")
    @Nullable
    Long ageNrId;

    @Column(name = "age_tx_nome")
    String ageTxNome;

    @Column(name = "age_dt_agendamento")
    Date ageDtAgendamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_tx_status")
    AgendamentoStatusEnum ageTxStatus;

    @Column(name = "age_dt_lock_ate")
    private Date ageDtLockAte;

    @Column(name = "age_dt_processamento")
    private Date ageDtProcessamento;

    @Column(name = "age_dt_conclusao")
    private Date ageDtConclusao;

    @Column(name = "age_tx_ultimo_erro")
    private String ageTxUltimoErro;

    @Column(name = "age_nr_tentativas")
    private Integer ageNrTentativas;


    @ManyToOne
    @JoinColumn(name = "usu_nr_id")
    private UsuarioEntidade ageObjUsuario;

}

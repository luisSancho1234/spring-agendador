package com.project.scheduler.models.entidades;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UsuarioEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Long usuNrId;
    @NonNull
    @Column(name = "usu_tx_nome")
    private String usuTxNome;
    @NonNull
    @Column(name = "usu_tx_senha")
    private String usuTxSenha;
    @NonNull
    @Column(name = "usu_tx_email")
    private String usuTxEmail;
    @Column(name = "usu_tx_roles")
    private String usuTxRoles = "ROLE_USER";
}

package com.project.scheduler.models.dto;

import com.project.scheduler.models.entidades.UsuarioEntidade;

public record UsuarioDto(
        Long usuNrId,
        String usuTxNome,
        String usuTxEmail,
        String usuTxSenha,
        String usuTxRoles
){
    public static UsuarioDto of(UsuarioEntidade entidade){
        return new UsuarioDto(
                entidade.getUsuNrId(),
                entidade.getUsuTxNome(),
                entidade.getUsuTxEmail(),
                entidade.getUsuTxSenha(),
                entidade.getUsuTxRoles()
        );
    }
}

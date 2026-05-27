package com.project.scheduler.repository;

import com.project.scheduler.models.entidades.UsuarioEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidade,Long> {
    Optional<UsuarioEntidade> findByUsuTxEmail(String email);
}

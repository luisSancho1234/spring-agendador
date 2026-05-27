package com.project.scheduler.repository;

import com.project.scheduler.models.entidades.AgendamentoEntidade;
import com.project.scheduler.models.enums.AgendamentoStatusEnum;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntidade, Long> {

    @Query(value = """
        SELECT *
        FROM agendamento.age_agendamento a
        WHERE a.age_dt_agendamento <= :agora
          AND (
                a.age_tx_status = 'PENDENTE'
                OR (
                    a.age_tx_status = 'PROCESSANDO'
                    AND a.age_dt_lock_ate IS NOT NULL
                    AND a.age_dt_lock_ate < :agora
                )
          )
        ORDER BY a.age_dt_agendamento ASC, a.age_nr_id ASC
        LIMIT :limit
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    List<AgendamentoEntidade> buscarElegiveisParaProcessamentoComLock(
        @Param("limit") int limit,
        @Param("agora") Date agora
    );

    @Query(value = """
        SELECT a.*
        FROM agendamento.age_agendamento a
        WHERE a.usu_nr_id = :usuNrId
        AND (
            :#{#status == null} = true
            OR a.age_tx_status = :#{#status?.name()}
        )
        ORDER BY
            CASE a.age_tx_status
                WHEN 'PENDENTE' THEN 1
                WHEN 'PROCESSANDO' THEN 2
                WHEN 'ERRO' THEN 3
                WHEN 'CONCLUIDO' THEN 4
                ELSE 5
            END,
            a.age_dt_agendamento DESC
        """, nativeQuery = true)
    Page<AgendamentoEntidade> findByUsuarioOrdenadoPorStatus(
            @Param("usuNrId") Long usuNrId,
            Pageable pageable,
            @Nullable @Param("status") AgendamentoStatusEnum status
    );
}
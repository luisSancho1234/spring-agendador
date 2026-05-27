DROP TABLE IF EXISTS agendamento.age_agendamento;

CREATE TABLE agendamento.age_agendamento (
     age_nr_id BIGSERIAL PRIMARY KEY,
     age_tx_nome VARCHAR(100),
     age_dt_agendamento TIMESTAMP,
     usu_nr_id BIGINT,
     CONSTRAINT fk_agendamento_usuario
         FOREIGN KEY (usu_nr_id)
             REFERENCES public.usuario_entidade (usu_nr_id)
);
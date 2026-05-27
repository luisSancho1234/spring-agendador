ALTER TABLE public.usuario_entidade
    DROP COLUMN IF EXISTS usutxemail,
    DROP COLUMN IF EXISTS usutxsenha,
    DROP COLUMN IF EXISTS usutxroles;

DROP TABLE IF EXISTS agendamento.age_agendamento;

CREATE TABLE agendamento.age_agendamento (
     age_nr_id BIGSERIAL PRIMARY KEY,
     age_tx_nome VARCHAR(100),
     age_dt_agendamento TIMESTAMP,
     usu_nr_id BIGINT
);
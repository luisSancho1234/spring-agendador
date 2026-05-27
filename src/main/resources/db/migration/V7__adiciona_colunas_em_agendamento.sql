ALTER TABLE agendamento.age_agendamento
    ADD COLUMN age_dt_lock_ate TIMESTAMP NULL,
ADD COLUMN age_dt_processamento TIMESTAMP NULL,
ADD COLUMN age_dt_conclusao TIMESTAMP NULL,
ADD COLUMN age_tx_ultimo_erro TEXT NULL,
ADD COLUMN age_nr_tentativas INTEGER DEFAULT 0;
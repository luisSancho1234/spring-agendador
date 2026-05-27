ALTER TABLE agendamento.age_agendamento
    ADD age_tx_status VARCHAR(20) NOT NULL,
    DROP COLUMN age_nr_status;
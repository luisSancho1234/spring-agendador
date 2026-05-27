TRUNCATE TABLE agendamento.age_agendamento
    RESTART IDENTITY CASCADE;

INSERT INTO agendamento.age_agendamento
(age_tx_nome, age_dt_agendamento, age_tx_status, usu_nr_id)
VALUES

    ('Envio relatório diário', (NOW() AT TIME ZONE 'America/Sao_Paulo') - INTERVAL '30 minutes', 'CONCLUIDO', 1),
    ('Sincronização ERP', (NOW() AT TIME ZONE 'America/Sao_Paulo') - INTERVAL '25 minutes', 'CONCLUIDO', 1),
    ('Atualização estoque', (NOW() AT TIME ZONE 'America/Sao_Paulo') - INTERVAL '20 minutes', 'CONCLUIDO', 1),
    ('Backup servidor', (NOW() AT TIME ZONE 'America/Sao_Paulo') - INTERVAL '15 minutes', 'CONCLUIDO', 1),

    ('Processar pagamentos', (NOW() AT TIME ZONE 'America/Sao_Paulo'), 'PENDENTE', 1),
    ('Enviar e-mails lote A', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '1 minute', 'PENDENTE', 1),
    ('Gerar PDF contratos', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '2 minutes', 'PENDENTE', 1),
    ('Importar clientes', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '3 minutes', 'PENDENTE', 1),
    ('Atualizar dashboard', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '4 minutes', 'PENDENTE', 1),
    ('Processar pedidos', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '5 minutes', 'PENDENTE', 1),

    ('Webhook integração', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '6 minutes', 'PENDENTE', 1),
    ('Conferir notas fiscais', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '7 minutes', 'PROCESSANDO', 1),
    ('Importar XML', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '8 minutes', 'PENDENTE', 1),
    ('Sincronizar marketplace', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '9 minutes', 'PENDENTE', 1),
    ('Gerar etiquetas', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '10 minutes', 'ERRO', 1),

    ('Atualizar cache', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '11 minutes', 'PENDENTE', 1),
    ('Enviar SMS', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '12 minutes', 'PENDENTE', 1),
    ('Recalcular métricas', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '13 minutes', 'PROCESSANDO', 1),
    ('Integrar pagamentos', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '14 minutes', 'PENDENTE', 1),
    ('Ler fila Rabbit', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '15 minutes', 'PENDENTE', 1),

    ('Verificar APIs', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '16 minutes', 'ERRO', 1),
    ('Atualizar sessão', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '17 minutes', 'PENDENTE', 1),
    ('Criar logs analíticos', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '18 minutes', 'PENDENTE', 1),
    ('Enviar push', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '19 minutes', 'PENDENTE', 1),
    ('Fechar lote financeiro', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '20 minutes', 'PROCESSANDO', 1),

    ('Gerar ranking', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '21 minutes', 'PENDENTE', 1),
    ('Atualizar BI', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '22 minutes', 'PENDENTE', 1),
    ('Verificar consistência', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '23 minutes', 'ERRO', 1),
    ('Exportar CSV', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '24 minutes', 'PENDENTE', 1),
    ('Enviar resumo final', (NOW() AT TIME ZONE 'America/Sao_Paulo') + INTERVAL '25 minutes', 'PENDENTE', 1);
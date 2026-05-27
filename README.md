# 🚀 Agendador - Back-end

Back-end do sistema **Agendador**, desenvolvido em **Java com Spring Boot**, com foco no gerenciamento de agendamentos, usuários e execução automática de tarefas programadas.

Este projeto faz parte do meu portfólio e tem como objetivo demonstrar minha experiência prática no desenvolvimento de APIs REST, organização de arquitetura em camadas, integração com banco de dados relacional e automação de processos no back-end.

## 🧰 Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- API REST
- Scheduler
- Programação Orientada a Objetos

## 📌 Sobre o projeto

O **Agendador** é uma API back-end responsável por controlar agendamentos e executar tarefas automaticamente conforme a data e horário definidos.

A aplicação permite cadastrar, consultar, listar e atualizar agendamentos, além de acompanhar o status de cada tarefa durante seu ciclo de vida.

O projeto foi estruturado seguindo boas práticas de desenvolvimento, com separação clara entre controllers, services, repositories, models, DTOs e enums, facilitando a manutenção, a escalabilidade e a evolução da aplicação.

## ⚙️ Principais funcionalidades

- Cadastro de agendamentos
- Listagem paginada de registros
- Consulta de agendamentos por usuário
- Filtro por status
- Ordenação personalizada por regra de negócio
- Controle de status dos agendamentos
- Execução automática de tarefas programadas
- Simulação de envio de notificações
- Integração com banco de dados PostgreSQL
- Exposição de endpoints REST para integração com o front-end Angular

## 🔄 Status dos agendamentos

O sistema utiliza status para acompanhar o ciclo de vida dos agendamentos:

- **PENDENTE**: agendamento criado e aguardando execução
- **PROCESSANDO**: tarefa em execução
- **CONCLUIDO**: tarefa finalizada com sucesso
- **ERRO**: falha durante o processamento

Esse controle permite acompanhar a execução das tarefas e abre espaço para futuras evoluções, como integração com mensageria, filas, envio real de e-mails ou workers externos.

## 🏗️ Estrutura do projeto

src/main/java/com/project/scheduler
├── controller
├── service
├── repository
├── models
│   ├── entidades
│   └── enums
├── dto
└── config

## ▶️ Como executar o projeto

Clone o repositório:

git clone <URL_DO_REPOSITORIO>

Acesse a pasta do projeto:

cd scheduler

Configure o banco de dados no arquivo:

src/main/resources/application.properties

Exemplo de configuração:

spring.datasource.url=jdbc:postgresql://localhost:5432/agendador
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Execute a aplicação:

mvn spring-boot:run

A API será iniciada em:

http://localhost:8080

## 🌐 Exemplos de endpoints

GET    /agendamentos
POST   /agendamentos
GET    /agendamentos/{id}
PUT    /agendamentos/{id}
DELETE /agendamentos/{id}

## 💡 Diferenciais técnicos

- Arquitetura organizada em camadas
- Criação de API REST com Spring Boot
- Persistência de dados com Spring Data JPA
- Integração com banco de dados PostgreSQL
- Paginação com Pageable
- Queries customizadas
- Uso de enums para padronização de status
- Separação clara entre regra de negócio e acesso a dados
- Processamento automático de tarefas com Scheduler
- Código preparado para futuras evoluções com RabbitMQ, Kafka ou workers externos

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido com o objetivo de demonstrar conhecimentos em desenvolvimento back-end com **Java**, **Spring Boot**, **API REST**, **banco de dados relacional**, **arquitetura em camadas** e **automação de processos agendados**.

Além de atender a uma necessidade funcional, o projeto representa minha evolução prática na construção de aplicações back-end mais organizadas, escaláveis e próximas de cenários reais do mercado.

## 👨‍💻 Autor

Desenvolvido por mim **Luis Fernando Sancho**.

## 📍 Status do projeto

🚧 Em desenvolvimento.

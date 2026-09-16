# Gerenciador de Tarefas

API REST desenvolvida para a atividade final de Programação Web Back-end I.

## Tecnologias

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

## Banco de dados

Crie o banco PostgreSQL:

```sql
CREATE DATABASE gerenciador_tarefas;
```

Depois confira o usuário e senha em:

`src/main/resources/application.properties`

## Como executar no VS Code

1. Instale Java 25.
2. Instale o Maven.
3. Instale o PostgreSQL.
4. Crie o banco `gerenciador_tarefas`.
5. Abra esta pasta no VS Code.
6. Execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

`http://localhost:8080`

## Endpoints

### Projetos

- POST `/projetos`
- GET `/projetos`

### Responsáveis

- POST `/responsaveis`
- GET `/responsaveis`

### Tarefas

- POST `/tarefas`
- GET `/tarefas`
- GET `/tarefas/{id}`
- PUT `/tarefas/{id}`
- DELETE `/tarefas/{id}`

## Filtros

Filtrar por status:

`GET /tarefas?status=NOVA`

Filtrar por projeto:

`GET /tarefas?projeto=1`

Filtrar pelos dois:

`GET /tarefas?status=NOVA&projeto=1`

## Exemplo de projeto

```json
{
  "nome": "Projeto Sistema",
  "descricao": "Projeto do gerenciador de tarefas"
}
```

## Exemplo de responsável

```json
{
  "nome": "José",
  "email": "jose@email.com"
}
```

## Exemplo de tarefa

```json
{
  "titulo": "Criar API",
  "descricao": "Desenvolver API do gerenciador",
  "prioridade": "ALTA",
  "prazo": "2026-09-30",
  "projeto": {
    "id": 1
  },
  "responsavel": {
    "id": 1
  }
}
```

## Status

- NOVA
- EM_ANDAMENTO
- CONCLUIDA
- CANCELADA

## Prioridade

- BAIXA
- MEDIA
- ALTA

As tarefas são criadas automaticamente com status `NOVA` e data de criação preenchida pelo sistema.
Quando uma tarefa passa para `CONCLUIDA`, a data de conclusão é preenchida automaticamente.

## Observação

As funcionalidades de etiquetas, comentários, endpoint de conclusão, recurso aninhado, paginação e PostgreSQL em container são desafios opcionais da atividade.

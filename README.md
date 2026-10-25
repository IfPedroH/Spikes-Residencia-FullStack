# Spikes Spring Boot

Projetos de estudo da Residência Fullstack. Cada pasta é um projeto Maven independente, com o seu próprio entregável.

| Pasta | Tema |
|---|---|
| spike-mvc | Arquitetura MVC: camadas Controller, Service e Repository |
| spike-rest | API REST: GET, POST, PUT e DELETE com códigos HTTP adequados |
| spike-jpa | Spring Data JPA: CRUD com PostgreSQL |
| spike-hibernate | Hibernate e mapeamento objeto-relacional: Categoria e Tarefa com relacionamento 1:N |
| spike-poo | Programação Orientada a Objetos em Java: gerenciador de produtos |
| spike-camadas | Integração das camadas: cadastro e consulta de Produto com Controller, Service, Repository e Entity no PostgreSQL |

## Como rodar

- Java 21 e Maven (o projeto já traz o `mvnw`)
- PostgreSQL, para os projetos que usam banco (spike-jpa, spike-hibernate e spike-camadas)
- Defina a variável de ambiente `DB_PASSWORD` com a senha do usuário `postgres`
- Abra a pasta do spike no IntelliJ e rode a classe `...Application`
- O spike-poo é um projeto Java simples, sem Maven: abra a pasta no IntelliJ e rode a classe `Main`
# Projeto Api Contas

Este projeto é uma implementação simplicada de uma API REST utilizando as seguintes ferramentas: Spring Boot, JPA, Flyway, Swagger/OpenAPI, Apache Maven, Docker Compose e PostgreSQL, e outras bibliotecas: Lombok, ModelMapper, Jackson, Junit e Mockito, utilizando plugins nativos da IDE VSCode.

Ele segue, mesmo que simplificadamente, os conceitos do Domain Driven Design e da Clean Architecture, onde o temos o pacote domain isolando tanto as regras de negócio quanto os modelos do restante da aplicação.

## Testando a aplicação

Conforme solicitado, o arquivo "https://github.com/estevaofreitas/api-contas/blob/master/src/main/docker/docker-compose.yml" pode ser utilizado para subir a aplicação utilizando o comando abaixo:

Comando:

```
docker-compose -f docker-compose.yml up

```

OBS: o arquivo compose.yml é utilizado pelo plugin spring-boot-docker-compose e que monta um ambiente para desenvolvimento durante o debug da aplicação.

## Testando a aplicação

Todas as rotas solicitadas foram desenvolvidas e bem documentadas no Swagger-UI: "http://localhost:8080/swagger-ui/index.html" .

Na rota de carga de contas pode-se utilizar o arquivo "https://raw.githubusercontent.com/estevaofreitas/api-contas/main/docs/carga.csv" como teste inicial da funcionalidade.

## Observações
- Por simplificação algumas validações foram colocadas na API mas também deveriam estar na camada domain para reforçar a regra de negócio,
- Não foi implementada a solução de autenticação, mas uma solução simples utilizando tokens JWT vai ser adicionada.



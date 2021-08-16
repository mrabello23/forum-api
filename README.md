# Forum API
Forum Rest API with Spring boot

**Api docs (Swagger): /swagger-ui/index.html**

## Autenticar
- Method: POST
- Uri: /auth
- Headers:
  - Content-Type: application/json
- Body Param:
  - email: String
  - senha: String

## Buscar Topico
- Method: GET
- Uri: /topicos 
- Query String Params:
  - Filtro:
    - nomeCurso: String
  - Paginação: 
    - page=0
    - size=10
  - Ordenação: 
    - sort=dataCriacao,desc
    - sort=id,asc

## Buscar Topico Por Id
- Method: GET
- Uri: /topicos/{id}
- Path Param:
  - id: Number

## Salvar Tópico
- Method: POST
- Uri: /topicos
- Headers:
  - Content-Type: application/json
  - Authorization: Bearer token_JWT
- Body Param:
  - titulo: String
  - mensagem: String
  - nomeCurso: String

## Alterar Tópico
- Method: PUT
- Uri: /topicos/{id} 
- Path Param: 
  - id: Number
- Headers:
  - Content-Type: application/json
  - Authorization: Bearer token_JWT
- Body Param: 
  - titulo: String
  - mensagem: String

## Remover Tópico
- Method: DELETE
- Uri: /topicos/{id}
- Path Param:
    - id: Number
- Headers:
  - Authorization: Bearer token_JWT

## Autenticação Teste
email: aluno@email.com
senha: 123456
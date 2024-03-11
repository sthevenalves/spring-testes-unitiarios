<h1>Testes Unitários</h1>

 ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
 ![Spring](https://img.shields.io/badge/Spring-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white)
 [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
 
 <P>Para desenvolver esse projeto, foi usado Java, Spring Boot e H2 como database</P>
 <p>O objetivo do projeto é a prática de testes unitários, para isso foi criado uma API básica para fazer registros e pesquisas de Mangás</p>
 
   <h2>Sumário</h2>
<ul>
  <li><a href="#func">Funcionalidades</li>
  <li><a href="#pratic">Práticas adotadas</li>
   <li><a href="#tests">Testes Unitários</li>
    <li><a href="#instalacao">Instalação</li>
      <li><a href="#endpoints">API Endpoints</li>
</ul>
   
   <h2 id="func">Funcionalidades</h2>
   <ul>
      <li>Listar todos os mangás</li>
      <li>Retornar uma página de mangás com paginação</li>
      <li>Encontrar um mangá por ID</li>
      <li>Encontrar um mangá por nome</li>
      <li>Deletar um mangá por ID</li>
      <li>Salvar um novo mangá</li>
      <li>Substituir os dados de um mangá existente</li>
  </ul>

   <h2 id="pratic">Metodologias e Práticas Implementadas</h2>
<ul>
<li>Design Patterns, SOLID
<li>API REST
<li>Consultas com Spring Data JPA
<li>Injeção de Dependências
<li>Tratamento de respostas de erro
 <li>Testes Unitários</li>
</ul>

<h2 id="tests">Testes Unitários</h2>
<p>Todos os testes são executados de forma automatizada utilizando frameworks apropriados para testes unitários, como JUnit, Mockito.</p>
<p>Os testes são executados regularmente como parte do processo de integração contínua (CI) para garantir a qualidade contínua do código.</p>

<ul>
 <h3>Controller</h3>
 <li>Teste de Requisições HTTP: Verificação de que as requisições HTTP são tratadas corretamente pelo controller, incluindo testes para os métodos GET, POST, PUT e DELETE, conforme aplicável.</li>
 <li>Teste de Validação de Entrada: Garantia de que a entrada do usuário seja validada adequadamente antes de ser processada pelo controller.</li>
 <li>Teste de Respostas: Verificação de que as respostas retornadas pelo controller estejam corretas, incluindo códigos de status HTTP, corpo da resposta e headers.</li>
</ul>
<h3>Service</h3>
 <li>Teste de Lógica de Negócios: Verificação de que as funções e métodos do service executam corretamente a lógica de negócios da aplicação.
 <li>Teste de Integração com Repositório: Mocking ou simulação de dependências do repositório para testar a interação correta entre o service e o repository.
 <li>Teste de Exceções e Erros: Verificação de que o service lida adequadamente com exceções e erros, garantindo um comportamento resiliente.

<h2 id="instalacao">Instalação</h2>
<ol>
  <li>Clonar o repositório Git</li>
  
      https://github.com/sthevenalves/spring-testes-unitiarios.git

  <li>Navegar para o diretório do projeto:</li>
  
      cd projetos-spring

  <li>Compilar o projeto com Maven</li>

    mvn clean install

  <li>Executar o projeto:</li>

    java -jar target/spring-testes-unitiarios.jar
</ol>

<h2 id="endpoints">API Endpoints</h2>
<p>Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta <a href="https://www.postman.com/">Postman</a></p>

http POST :8080/mangas

    {
    "name": "Bleach",
    "chapter": 581
    }

http GET :8080/mangas

    [
    {
      "id": 1,
      "name": "Bleach",
      "chapter": 581
    }
    ]
  http PUT :8080/mangas

    {
    "id": 1,
    "name": "Bleach",
    "chapter": 686 
    }

  http GET :8080/mangas/{id}
  
    {
    "id": 0,
    "name": "String",
    "chapter": 0
    }

http GET :8080/mangas/page

      {
        "content": [
            {
                "id": 1,
                "name": "Bleach",
                "chapter": 566
            },
            {
                "id": 2,
                "name": "Naruto",
                "chapter": 700
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 20,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "unpaged": false,
            "paged": true
        },
        "last": true,
        "totalElements": 2,
        "totalPages": 1,
        "size": 20,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 2,
        "empty": false
    }

http GET :8080/mangas/find-name?name=Naruto

    {
      "id": 2,
      "name": "Naruto",
      "chapter": 700
    }

  http DELETE :8080/mangas/delete/{id}

     CODE 200 - OK














  


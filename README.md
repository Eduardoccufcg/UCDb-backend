# UCDb - Back-end
Projeto final da disciplina Projeto de Software na Universidade Federal de Campina Grande no período 2019.1

## Sobre o desenvolvimento

O projeto foi desenvolvido em Java, utilizando o framework Spring Boot.
Está disponível em [https://ucdbapplication.herokuapp.com/](https://ucdbapplication.herokuapp.com/).

A documentação swagger está dispónivel em [https://ucdbapplication.herokuapp.com/api/swagger-ui.html](https://ucdbapplication.herokuapp.com/api/swagger-ui.html)

A estrutura do projeto consiste:

```
|---
---| config
---| controllers
---| exception
---| components
---| model
---| services
---| repositoriesDAO
---| security

```

## Como executar?

Para rodar seu projeto abra um terminal, entre no diretório raiz do projeto e execute o comando abaixo:
```
mvn spring-boot:run
```

Se o maven não estiver instalado de forma apropriada você também pode executar com o seguinte comando (que vem no próprio projeto criado no starter do Spring quando você escolhe maven como seu gerenciador de dependências):
```
./mvnw spring-boot:run
```

## Arquitetura

O projeto utiliza a [arquitetura MVC](https://github.com/daltonserey/projsw-20191/blob/master/06.web_apps/1-padrao_mvc/text.md).

## Token
O tempo escolhido para o token foi de um dia, visto que a aplicacao não é critica, por isso não é necessário a troca de sessão em um curto período de tempo.

## Desenvolvedores

- [Eduardo Pereira](https://github.com/Eduardoccufcg)
- [Júlia Fernandes Alves](https://github.com/juliafealves)

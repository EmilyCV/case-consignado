# case-consignado

## Descrição

Este projeto é uma plataforma de empréstimo consignado. Ele permite que os usuários solicitem simulações de empréstimoos e realizem sua contratação.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Web
- Spring Kafka
- MySQL
- Lombok
- JUnit e Mockito para testes
- SpringDoc OpenAPI para documentação da API
- Docker
- JaCoCo para cobertura de código

## Instalação

### Pré-requisitos
- JDK 21 ou superior
- Maven 3.6.3 ou superior
- MySQL Server
- Docker e Docker Compose

### Passos para instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/EmilyCV/case-consignado.git
    cd case-consignado
    ```

2. Inicie os contêineres Docker:
    ```bash
    docker-compose up --build
    ```

## Documentação da API
A documentação da API para todos os serviços pode ser acessada através do Swagger, configurado no API Gateway. Acesse:
http://localhost:8081/swagger-ui/index.html#/

http://localhost:8082/swagger-ui/index.html#/

http://localhost:8083/swagger-ui/index.html#/

## Utilização

### API Gateway
O API Gateway centraliza os endpoints dos diferentes serviços. A configuração dos endpoints pode ser encontrada no arquivo `CaseConsignadEndpoints.yaml`.

#### Endpoints Principais
http://localhost:8080/client

http://localhost:8080/simulation

http://localhost:8080/contract

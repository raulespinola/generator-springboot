# generator-microservices
 Branch with a few changes 
	- Entity, Service and Controller mapping in a HashMap to simulate a Rest Controller
	- Jaeger Tracing for the Endpoints.

## How to use?

```
> npm install -g yo
> npm install -g generator-microservices
> yo microservices
```

## Jaeger Tracing

-Use the Docker installer
> docker run --rm -it --network=host jaegertracing/all-in-one


## Local Development Setup

```
> cd generator-microservices
> npm install 
> npm link
> yo microservices
```

## Note

```
If you have some errors to run the commands above try:
1- Remove:
  - node_modules
  - .yo-repository
  - package-lock.json
2- run:
   > npm install -g generator-microservices
   > cd /generator-microservices
   > npm link
   > yo microservices
```

## Features

1. Microservice

    * SpringBoot REST API with jar type packaging
    * CORS configuration
    * Swagger UI Integration
    * Spring Data JPA integration with option to select databases like MySQL, Postgresql
    * SpringBoot Actuator configuration
    * TestContainers integration
    * JUnit 5 
    * Jenkinsfile

### Generate REST API with CRUD operations
Run the following command from within the generated project folder. 

`myservice> yo microservices:controller Cuenta --base-path /api/cuenta`

This will generate:
* Spring Data JPA Repository
* Service
* Spring MVC REST Controller with CRUD operations
* Unit and Integration Tests for REST Controller
* Flyway or Liquibase migration to create table

### Generate Entity
Run the following command from within the generated project folder. 

`myservice> yo microservices:controllerMap Empleado --base-path /api/empleado`

This will generate:
* JPA entity
* Spring Data JPA Repository
* Service

## Use it

- Install the generator and complete with your date
- Dont use JPA, is not complete
- Run Jaeger Tracing
- Create the ControllerMap
- Use it with SwaggerUI and JaegerUI



# Demo Museum application

---

## Description

Java spring-boot application, written to try Redis OM library. Spoiler... It is fantastic!!!
It is a back-end part of the abstract Museum Website and provides REST endpoints with basic CRUD functionality and, most importantly, a global search endpoint with auto-suggestion.

---

## Features and technology stack

### Main properties

- Language: Java-17
- Build tool: Apache Maven
- Main framework: Spring Boot 3.1.2
- Superpower of searching capabilities: Redis OM Spring and Redis Stack (Redis Search, Redis Json, Redis Bloom)
- CI: GitHub Actions
- Deployment: Docker with Docker Compose plugin

#### WEB

- [Spring Web](https://spring.io/projects/spring-framework).
  - REST Controllers for endpoints
  - RestControllerAdvice for exception handling
- Swagger ([Springdoc OpenAPI](https://springdoc.org/))
  - Generating openAPI documentation
  - Swagger Web UI by link: [swagger-ui](http://localhost:8080/swagger-ui/index.html)
- [Jackson databind](https://github.com/FasterXML/jackson-databind)

#### Persistence

- [Redis OM Spring](https://github.com/redis/redis-om-spring)
  - Redis Documents for storing objects as JSON documents
  - Redis Search for creating indexes and searching by documents fields 
  - AuthoComletion library from com.redis.om.spring

- Redis as a database. Yes... As a database) 

#### Tests

- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org/)
- [Spring Boot Test](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html)
  - MockMVC for testing API cals
  - SpringBootTest for integration testing
- [Testcontainers](https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/) for integration testing
- [Instancio](https://www.instancio.org/getting-started/) for generating fake test data

#### Other

- [Lombok](https://projectlombok.org/features/)
- Java Bean Validation ([Hibernate Validation](https://hibernate.org/validator/))

### How you can try it

> Now the project is in development, and not everything should work perfectly fine 😊.  
> But I fix all issues as soon as possible 😅.  

__What do you need:__

1. You should have Docker with Docker compose plugin (or Docker desktop) installed on your machine. Link with instructions: [Get Docker](https://docs.docker.com/get-docker/)

2. And that\`s it 😎.

__Installation steps:__

1. Clone this repository (or just `docker-compose.yml` file).

2. Run this command in your terminal (on Linux and Mac), Power-Shell or Git-bash (on Windows), but first of all move to the root of the project: `docker compose up -d`

3. Test endpoints with the Swagger UI 😀: [swagger-ui](http://localhost:8080/swagger-ui/index.html)

4. Test auto suggestions by link: [web-app](http://localhost:8080)

5. Managing Redis instance from browser: [RedisInsight](http://localhost:8001)
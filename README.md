BOOK STORE API

Overview:

    Book Store is a RESTful API built with Spring Boot for managing an online bookstore. It provides 
    endpoints for managing books and categories, searching for books, and controlling access to
    protected operations.

Technologies:

    Java 17
    Spring Boot
    Spring Data JPA and Hibernate
    Spring Security and JWT
    MySQL
    Liquibase
    MapStruct
    Swagger / OpenAPI
    JUnit and Spring Boot Test
    Maven

Features:

    Retrieve books with pagination.
    Search books by specified parameters.
    Create, update, and delete books.
    Manage book categories.
    Associate books with multiple categories.
    Authenticate users and authorize requests based on roles.
    Restrict administrative operations to users with the ADMIN role.
    Document and test API endpoints using Swagger and automated tests.

GETTING STARTED

Prerequisites:

    Java 17 or later
    Maven
    MySQL

Installation:
    
    1. Clone the repository.
    2. Create a MySQL database.
    3. Configure database credentials and other required settings in application.properties or the corresponding environment variables.
    4. Run the application using Maven:

    mvn spring-boot:run

    Liquibase manages database schema migrations.

API Documentation:

    After starting the application, open the Swagger UI at:

    http://localhost:8080/swagger-ui/index.html

    Check the application's configuration for the correct port and context path.

TESTING

    Run the automated tests with:
    mvn test

FUTURE IMPROVEMENTS:

    Expand automated test coverage.
    Improve API documentation.
    Add additional bookstore management features.

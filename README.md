# Product Management Application

This is a full-stack application to manage products, built with a Spring Boot backend and an Angular frontend.
The project includes Docker Compose to run both the frontend and backend together, with the ability to use PostgreSQL as the database or run with H2 in a local development profile.

## Technologies Used

- ### Backend: 
  - Java
  - Maven
  - Spring Boot
  - Spring Security
  - Spring Data JPA
  - PostgreSQL / H2 (for local development)
  - JWT Authentication
- ### Frontend:
  - Angular
  - TypeScript
  - HTML
  - CSS
- ### Docker:
  - Docker Compose for multi-container setup
  - PostgreSQL Docker container for database

## Setting up the Project
   
  ### 1 - Using Docker Compose (Recommended)
To run both frontend and backend together with PostgreSQL, follow these steps:

   #### Clone the repository:
    git clone <repository-url>
    cd <repository-directory>

#### Build and start the containers with Docker Compose:
    docker-compose up --build

#### The application will be available at:
- Backend (API): http://localhost:8080
- Frontend (UI): http://localhost:4200

This setup runs the backend and frontend in separate containers, while PostgreSQL is used as the production database.

### 2 - Running Backend and Frontend Separately

#### Backend
2. Running Backend and Frontend Separately
   Backend
   To run the backend separately, follow these steps:

In the backend directory, configure your application properties to use H2 for local development, or set up PostgreSQL credentials in the application.properties or application.yml file for PostgreSQL.

To run the backend with H2 (local database), use:


    ./mvnw spring-boot:run -Dspring.profiles.active=local

To run the backend with PostgreSQL, ensure PostgreSQL is running locally or via Docker and configure the datasource in the application.yml:

      datasource:
        url: jdbc:postgresql://<db_host>/your_db
        driver-class-name: org.postgresql.Driver
        username: <user>
        password: <password>


### Frontend
To run the frontend separately, follow these steps:

In the frontend directory, install dependencies:

    npm install

Build and serve the Angular application:

    ng serve

The application will be available at http://localhost:4200.

3. Using PostgreSQL Database
   In production, PostgreSQL is used for storing product data. You can either set up a local PostgreSQL instance or use the Dockerized PostgreSQL container provided by Docker Compose.
In the Docker Compose setup, the PostgreSQL database container is automatically configured and started when running docker-compose up.


4. Running with H2 Database (Local Profile)
   If you want to use the H2 database for local development (without PostgreSQL), you just need to run the application with local profile

## Technologies Referenced
[Java - OpenJdk 21](https://www.java.com/)

[Maven 3+](https://maven.apache.org/)

[SpringBoot 3+](https://spring.io/projects/spring-boot)

[SpringData](https://spring.io/projects/spring-data)

[SpringSecurity](https://spring.io/projects/spring-security)

[Lombok](https://projectlombok.org/)

[Postgres](https://www.postgresql.org/)

[H2](https://www.h2database.com/html/main.html)

[Docker](https://www.docker.com/)

[Angular](https://angular.dev/)

[TypeScript](https://www.typescriptlang.org/)

[HTML](https://html5.org/)

[CSS](https://www.w3.org/Style/CSS/Overview.en.html)






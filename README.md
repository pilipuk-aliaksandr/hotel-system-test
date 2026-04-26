# Hotel Management API

_RESTful API application for managing hotels, built with Spring Boot._

## 🛠 Tech Stack
* **Java 24**
* **Spring Boot 4.0.0**
* **Database:** H2 (with the ability to quickly migrate to PostgreSQL)
* **Migration Tool:** Liquibase
* **Documentation:** Swagger (OpenAPI 3.0)
* **Build System:** Maven

## 🚀 Getting Started

### Run the Application
From the root directory, run:
```bash
mvn spring-boot:run
```
The application will start **on port 8092** with context path `/property-view`.

### **API Documentation**
Once the app is running, access Swagger UI at:
http://localhost:8092/property-view/swagger-ui/index.html

## 📋 Features

* Developed a full-featured **RESTful** architecture (Controllers, DTOs, Services, Mappers, Domain Models, and Repositories) using **SOLID** principles and **Clean Architecture**.

* Designed relational database schemas with defined relationships and constraints. Configured **Liquibase migrations** for versioned, managed data model evolution and consistency.

* Implemented **OpenAPI Generator** and **Swagger documentation** with detailed operation summaries, realistic request/response examples, and clear definitions for optional fields.

* Developed "out-of-the-box" support for **H2 Database** (in-memory) for rapid automated evaluation. Also offering a production-ready **PostgreSQL** integration via **Docker-Compose** infrastructure.

* Implemented comprehensive Integration Tests for REST endpoints to validate business logic, status codes, and JSON response structures in an automated environment.

## 📊 Endpoints

**POST** `/property-view/hotels` - Create a new hotel

**GET** `/property-view/hotels` - Get all hotels

**GET** `/property-view/search` - Search hotels by parameters

**GET**  `/property-view/hotels/{id}` - Get one hotel by ID

**POST** `/property-view/hotels/{id}/amenities` - Add amenities to a hotel

**GET** `/property-view/histogram/{param}` - Get hotel statistics (histogram)
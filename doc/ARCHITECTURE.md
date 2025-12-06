<style>
@import url('https://fonts.googleapis.com/css2?family=Anton&family=DM+Mono:ital,wght@0,300;0,400;0,500;1,300;1,400;1,500&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap');

body {
  font-family: "DM Mono";
}
</style>

# Architecture & Technical Documentation

This document provides a deep dive into the technical aspects of the Project UPE Document Server.

## 1. Clean Architecture

The project is built upon the principles of **Clean Architecture**, which separates the software into layers:

-   **Domain:** Contains the core business logic and entities.
-   **Application:** Orchestrates the flow of data and triggers business logic.
-   **Infrastructure:** Handles external concerns like databases, frameworks, and UI.

This separation ensures that the business logic is independent of any external agency, making the system more maintainable, testable, and scalable.

![Hexagonal Architecture](doc/hexagonal-diagram.drawio.png)

*A visual representation of the hexagonal (clean) architecture.*

## 2. Database Schema

The database uses PostgreSQL. The schema is designed to support the core features of the application, including user management, document generation, and auditing.

*(You can add more details about the tables and relationships here, or even include an ER diagram.)*

## 3. CI/CD Pipeline

The project uses GitHub Actions for Continuous Integration and Continuous Deployment. The pipeline is defined in `.github/workflows/maven.yml` and includes the following stages:

1.  **Lint:** Checks the code for style issues.
2.  **Test:** Runs the unit and integration tests.
3.  **Build:** Compiles the code and creates a JAR file.
4.  **Security Scan:** Scans the code for vulnerabilities.

This ensures that every change is automatically tested and verified before being merged.

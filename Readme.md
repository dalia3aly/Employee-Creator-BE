# Employee Creator

This is an Employee Creator API built with Java Spring Boot. The API allows users to get, create, update, and delete employees with various details.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation and Running the App](#installation-and-running-the-app)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Contributing](#contributing)

## Features

- **Create Employees**: Add new employees with personal, contact, and employment details.
- **Update Employees**: Edit employee details.
- **Delete Employees**: Remove employees from the list.
- **Get Employee List**: Retrieve the list of all employees.

## Prerequisites

Before you begin, ensure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (v11 or higher)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)

## Installation and Running the App

1. **Clone the Repository**:

```sh
git clone https://github.com/dalia3aly/Employee-Creator-BE.git
cd cd Employee-Creator-BE
```

2. **Create MySQL Database**:
   Create a MySQL database named employee_db:

```sql
CREATE DATABASE employee_db;
USE employee_db
```

3. **Update Application Properties**:
   Update the src/main/resources/application.properties file with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

4. **Build and Run the Backend**:
   Run the following command to build and run the backend:

```sh
mvn spring-boot:run
```

5. **Start the Frontend**:
   Please refer to the frontend github repo:

```
https://github.com/dalia3aly/Employee-Creator-FE
```

## Usage

Once the Employee Creator App is up and running, you can perform the following actions:

- Create a new employee by sending a POST request to /api/employees with the - required details.
- Update an employee by sending a PATCH request to /api/employees/{id} with the updated details.
- Delete an employee by sending a DELETE request to /api/employees/{id}.
- Get the list of all employees by sending a GET request to /api/employees.

## Folder Structure

The folder structure of the Employee Creator API is as follows:

```
Employee-Creator-BE/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── employees/
│   │   │           ├── empcreator/
│   │   │           │   ├── ContractType.java
│   │   │           │   ├── Employee.java
│   │   │           │   ├── EmployeeController.java
│   │   │           │   ├── EmployeeRepository.java
│   │   │           │   ├── EmployeeService.java
│   │   │           │   ├── EmploymentType.java
│   │   │           ├── config/
│   │   │           │   ├── CorsConfig.java
│   │   │           │   └── ModelMapperConfig.java
│   │   │           ├── common/
│   │   │           ├── dto/
│   │   │           │   ├── CreateEmployeeDTO.java
│   │   │           │   └── UpdateEmployeeDTO.java
│   │   │           └── exceptions/
│   │   │               ├── EmployeeNotFoundException.java
│   │   │               └── GlobalExceptionHandler.java
│   └── resources/
│       ├── application.properties
│       └── static/
└── pom.xml
```

## Contributing

Contributions are welcome! If you would like to contribute to the Employee Creator App, please follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Commit your changes.
5. Push to the branch.
6. Open a pull request.

Please ensure that your code follows the project's coding style and conventions.

**Thank you for your interest in contributing to the Employee Creator App!**

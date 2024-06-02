# Backend Payment System for Programming Championship Hackathon

## Technical Stack

- Java
- Spring (Boot, Security, Hibernate)
- Postgres

## How to Build and Run the Project

### Dependencies
- `java` >= 17.0.0
- `spring-boot` >= 3.0
- `postgres` >= 16.2.0
- `maven` >= 3.9.0

### Before Creating the Project

To set up the database, you need to create a database in **PostgreSQL**.

**Enter the psql shell**
```sh
psql -U postgres
```

**Create the Database in the psql shell**
```sql
CREATE DATABASE your_db_name;
```

**Configure settings** in `PaymentSystem/src/main/resources/application.properties`

### Building the Project

**Install all necessary packages using Maven**
```sh
mvn clean install
```

**Run the project**
```sh
mvn spring-boot:run
```

**Package the project**
```sh
mvn clean package
```

To run it as a jar file
```sh
java -jar target/PaymentSystem-0.0.1-SNAPSHOT.jar
```

## API Documentation for the Payment System

This document provides an overview of the endpoints available in the Payment System API, including their parameters, request bodies, and responses.

---

#### Transaction Controller

##### POST /transactions/create
Creates a new transaction.

**Request Body:**
```json
{
  "buyerName": "string",
  "buyerBankAccount": "string",
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "price": 0
}
```

**Responses:**
- **200 OK:** Transaction created successfully. Returns a string message.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### GET /transactions/getCompanies
Retrieves a list of companies with their transactions.

**Parameters:**
- **sort (query, string):** Optional sorting parameter.

**Responses:**
- **200 OK:** Returns a `TransactionResponseWrapper` object.
- **400 Bad Request:** Invalid request. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

#### Auth Controller

##### POST /role
Creates or updates a user role.

**Request Body:**
```json
{
  "id": 0,
  "role": "string"
}
```

**Responses:**
- **200 OK:** Role created/updated successfully. Returns a `UserRole` object.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### POST /reset/{resetToken}
Resets the password using a reset token.

**Parameters:**
- **resetToken (path, string):** The reset token.
- **password (query, string):** The new password.

**Responses:**
- **200 OK:** Password reset successfully. Returns a string message.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### POST /register
Registers a new user.

**Request Body:**
```json
{
  "email": "string",
  "password": "string",
  "title": "string",
  "bankAccount": "string",
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

**Responses:**
- **200 OK:** User registered successfully. Returns a `UserResponseDto` object.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### POST /auth
Authenticates a user.

**Request Body:**
```json
{
  "email": "string",
  "password": "string",
  "title": "string",
  "bankAccount": "string",
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

**Responses:**
- **200 OK:** Authentication successful. Returns a `UserResponseDto` object.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### GET /reset
Requests a password reset.

**Parameters:**
- **email (query, string):** The email address for password reset.

**Responses:**
- **200 OK:** Password reset request successful. Returns a string message.
- **400 Bad Request:** Invalid input. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

#### Chart Controller

##### GET /transactions/earningsChart
Retrieves data for the earnings chart.

**Parameters:**
- **sort (query, string):** Optional sorting parameter.

**Responses:**
- **200 OK:** Returns an array of strings.
- **400 Bad Request:** Invalid request. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

#### Admin Controller

##### GET /admin/getAllUsers
Retrieves a list of all users.

**Responses:**
- **200 OK:** Returns an array of `UserResponseDto` objects.
- **400 Bad Request:** Invalid request. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

##### GET /admin/getAllTransactions
Retrieves a list of all transactions.

**Parameters:**
- **sort (query, string):** Optional sorting parameter.

**Responses:**
- **200 OK:** Returns a `TransactionResponseWrapper` object.
- **400 Bad Request:** Invalid request. Returns an `ExceptionResponse` object.
- **403 Forbidden:** Access denied. Returns an `ExceptionResponse` object.
- **404 Not Found:** Resource not found. Returns an `ExceptionResponse` object.

---

#### Schemas

##### ExceptionResponse
- **httpStatus (string):** HTTP status code.
- **message (string):** Error message.

##### TransactionRequest
- **buyerName (string):** Name of the buyer.
- **buyerBankAccount (string):** Bank account of the buyer.
- **appId (string, $uuid):** Application ID.
- **price (number):** Price of the transaction.

##### UserRole
- **id (integer, $int32):** Role ID.
- **role (string):** Role name.

##### UserDto
- **email (string):** User's email.
- **password (string):** User's password.
- **title (string):** User's title.
- **bankAccount (string):** User's bank account.
- **appId (string, $uuid):** Application ID.

##### TransactionResponseDto
- **id (integer, $int32):** Transaction ID.
- **buyerName (string):** Name of the buyer.
- **buyerBankAccount (string):** Bank account of the buyer.
- **transactionTime (string, $date-time):** Time of the transaction.
- **price (number):** Price of the transaction.

##### TransactionResponseWrapper
- **totalCount (integer, $int64):** Total count of transactions.
- **totalSum (number):** Total sum of transactions.
- **transactions (array of TransactionResponseDto):** List of transactions.

##### UserResponseDto
- **email (string):** User's email.
- **title (string):** User's title.
- **bankAccount (string):** User's bank account.
- **appId (string, $uuid):** Application ID.

# Backend Payment system for Programming championship Hackaton

## Технический стек

Java, Spring(Boot, Security, Hibernate) Postgres



## Как собрать и запустить проект

### Зависимости
- `java`>=17.0.0
- `postgres`>=16.2.0
- `maven`>=3.9.0


### Перед созданием проекта

Чтобы настроить базу данных, вам необходимо создать базу данных в **postgresql**.

**Войдите в оболочку psql**
```
psql -U postgres
```

**В оболочке psql создайте Базу Данных**
```
CREATE DATABASE you_db_name;
```

**Настройте конфигурации** в `PaymentSystem/src/main/resources/application.properties` 



### Строительный проект

**Установите все необходимые пакеты с помощью maven**

```
mvn clean install
```

**Запуск проекта**
```
mvn spring-boot:run
```


**Сборка проекта**

```
mvn clean package
```

чтобы запустить в виде jar файла

```
java -jar target/PaymentSystem-0.0.1-SNAPSHOT.jar
```


## Документация API платежной системы

Эта документация предоставляет подробную информацию о доступных конечных точках API платежной системы. Каждая конечная точка объясняется с необходимыми параметрами, телами запросов и возможными ответами.

### Контроллер транзакций

#### Создать транзакцию

**Конечная точка:** `POST /transactions/create`

**Описание:** Создает новую транзакцию с указанными деталями.

**Тело запроса:**

```json
{
  "buyerName": "string",
  "buyerBankAccount": 0,
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "price": 0
}
```

**Ответы:**

- `200 OK`: Транзакция успешно создана.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Получить компании

**Конечная точка:** `GET /transactions/getCompanies`

**Описание:** Получает список транзакций, с возможностью сортировки.

**Параметры:**

- `sort` (query, string): Критерии сортировки для транзакций.

**Ответы:**

- `200 OK`: Транзакции успешно получены.

  ```json
  {
    "totalCount": 0,
    "totalSum": 0,
    "transactions": [
      {
        "id": 0,
        "buyerName": "string",
        "buyerBankAccount": 0,
        "transactionTime": "2024-06-01T02:46:50.960Z",
        "price": 0
      }
    ]
  }
  ```

- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

### Контроллер аутентификации

#### Создать роль

**Конечная точка:** `POST /role`

**Описание:** Создает новую роль для пользователя.

**Тело запроса:**

```json
{
  "id": 0,
  "role": "string"
}
```

**Ответы:**

- `200 OK`: Роль успешно создана.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Сброс пароля

**Конечная точка:** `POST /reset/{resetToken}`

**Описание:** Сбрасывает пароль с использованием предоставленного токена и нового пароля.

**Параметры:**

- `resetToken` (path, string): Токен для сброса.
- `password` (query, string): Новый пароль.

**Ответы:**

- `200 OK`: Пароль успешно сброшен.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Регистрация пользователя

**Конечная точка:** `POST /register`

**Описание:** Регистрирует нового пользователя.

**Тело запроса:**

```json
{
  "email": "string",
  "password": "string",
  "title": "string",
  "bankAccount": "string",
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

**Ответы:**

- `200 OK`: Пользователь успешно зарегистрирован.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Аутентификация пользователя

**Конечная точка:** `POST /auth`

**Описание:** Аутентифицирует пользователя с использованием предоставленных учетных данных.

**Тело запроса:**

```json
{
  "email": "string",
  "password": "string",
  "title": "string",
  "bankAccount": "string",
  "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

**Ответы:**

- `200 OK`: Пользователь успешно аутентифицирован.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Запрос на сброс пароля

**Конечная точка:** `GET /reset`

**Описание:** Запрашивает сброс пароля для указанного email.

**Параметры:**

- `email` (query, string): Email адрес для сброса пароля.

**Ответы:**

- `200 OK`: Запрос на сброс пароля успешно отправлен.
- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

### Контроллер администратора

#### Получить всех пользователей

**Конечная точка:** `GET /admin/getAllUsers`

**Описание:** Получает список всех пользователей.

**Ответы:**

- `200 OK`: Пользователи успешно получены.

  ```json
  [
    {
      "email": "string",
      "password": "string",
      "title": "string",
      "bankAccount": "string",
      "appId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    }
  ]
  ```

- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

#### Получить все транзакции

**Конечная точка:** `GET /admin/getAllTransactions`

**Описание:** Получает список всех транзакций, с возможностью сортировки.

**Параметры:**

- `sort` (query, string): Критерии сортировки для транзакций.

**Ответы:**

- `200 OK`: Транзакции успешно получены.

  ```json
  {
    "totalCount": 0,
    "totalSum": 0,
    "transactions": [
      {
        "id": 0,
        "buyerName": "string",
        "buyerBankAccount": 0,
        "transactionTime": "2024-06-01T02:46:50.986Z",
        "price": 0
      }
    ]
  }
  ```

- `400 Bad Request`: Неверные данные ввода.
- `403 Forbidden`: Доступ запрещен.
- `404 Not Found`: Ресурс не найден.

### Схемы

#### ExceptionResponse

```json
{
  "httpStatus": "string",
  "message": "string"
}
```

#### TransactionRequest

```json
{
  "buyerName": "string",
  "buyerBankAccount": 0,
  "appId": "string",
  "price": 0
}
```

#### UserRole

```json
{
  "id": 0,
  "role": "string"
}
```

#### UserDto

```json
{
  "email": "string",
  "password": "string",
  "title": "string",
  "bankAccount": "string",
  "appId": "string"
}
```

#### TransactionResponseDto

```json
{
  "id": 0,
  "buyerName": "string",
  "buyerBankAccount": 0,
  "transactionTime": "2024-06-01T02:46:50.986Z",
  "price": 0
}
```

#### TransactionResponseWrapper

```json
{
  "totalCount": 0,
  "totalSum": 0,
  "transactions": [
    {
      "id": 0,
      "buyerName": "string",
      "buyerBankAccount": 0,
      "transactionTime": "2024-06-01T02:46:50.986Z",
      "price": 0
    }
  ]
}
```

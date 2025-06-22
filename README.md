
# Pricing API

## 📌 Description

This is a Spring Boot application that exposes a REST API to retrieve the applicable price for a product at a specific date and time, considering the brand. The solution is built using clean architecture principles, specifically the hexagonal architecture model (Ports and Adapters).

---

## 🧱 Architecture

The project implements **Hexagonal Architecture (Ports and Adapters)** to separate domain logic from technical concerns like controllers and repositories.

### 🔹 Domain (Core)
- Business entities (e.g., `Price`)
- Domain services (`PriceDomainService`)
- Domain-specific exceptions (`PriceNotFoundException`)

### 🔹 Application (Use Cases)
- Coordinates business logic via application services.
- Bridges between external interfaces and the core domain.

### 🔹 Infrastructure (Adapters)
- REST controllers
- JPA repositories
- Configuration files (Swagger, H2 database)
- Test data loading

This structure improves maintainability, scalability, and testability.

---

## 🚀 Features

- Retrieve the valid price based on date, product ID, and brand ID.
- Error handling when no price is applicable.
- In-memory H2 database preloaded with sample data.
- API documentation using Swagger (OpenAPI).
- Business and integration tests with JUnit 5.

---

## 🛠️ Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Swagger (OpenAPI)
- JUnit 5
- Spring MockMvc

---

## ▶️ Running the Application

### Clone the repository

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

### Run the application

```bash
./mvnw spring-boot:run
```

The application will start at:  
`http://localhost:8080`

### Access Swagger UI

`http://localhost:8080/swagger-ui.html`

---

## 📡 API Usage

**Main Endpoint:**

```
GET /api/prices?date={date}&productId={productId}&brandId={brandId}
```

**Parameters:**
- `date`: ISO date/time format (e.g., `2020-06-14T16:00:00`)
- `productId`: Product ID
- `brandId`: Brand ID

**Example Request:**

```
GET /api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1
```

**Example Response:**

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45
}
```

---

## ⚠️ Error Handling

If no applicable price is found, a `PriceNotFoundException` is thrown with the following message:

```
No applicable price found for productId [35455], brandId [1] on date [2020-06-14T16:00:00]
```

---

## 🧪 Testing

Integration and business tests validate key scenarios such as:

- Multiple applicable prices for the same date
- Dates out of range
- Non-existent product or brand IDs

Test classes are located under `com.example.pricing.integration`.

---

## 📂 Project Structure

```
src
├── domain
│   ├── model
│   ├── service
│   └── exception
├── application
│   └── service
├── infrastructure
│   ├── controller
│   ├── repository
│   └── config
└── resources
    └── data.sql
```

---

## 📃 Author

- [Carmen Sirvent Garrigues](https://github.com/carsirga/pricing)

---


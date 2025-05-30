# Pricing Application

## Overview

This Spring Boot application provides a REST API to consult applicable prices for products based on date, product ID, and brand ID.

## Features

- Retrieve the applicable price according to date, product, and brand.
- Uses an in-memory H2 database with predefined sample data.
- Implements a clean architecture approach with separation of concerns.
- Includes integration tests validating key use cases.

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- JUnit 5 and Spring MockMvc for testing
- Maven

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
   ```

2. Build and run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

3. The application will start on `http://localhost:8080`.

## API Usage

**Endpoint:**

```
GET /api/prices?date={date}&productId={productId}&brandId={brandId}
```

**Parameters:**

- `date` — Date and time in ISO format, e.g., `2020-06-14T16:00:00`.
- `productId` — Product identifier (integer).
- `brandId` — Brand identifier (integer).

**Example request:**

```
GET /api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1
```

**Response:**

JSON with price details including productId, brandId, priceList, startDate, endDate, and price.

## Running Tests

Execute the integration tests with:

```bash
./mvnw test
```

## Notes

- Dates and times are managed with `LocalDateTime` and the timezone is UTC.
- The application uses H2 in-memory database for demo purposes.
- The repository query orders by price priority descending to select the most relevant price.



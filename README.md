# First-Rest-Api-Spring

A small REST API for managing products, built with Spring Boot for Exercise 16 at Vistula University. You can create, read, update and delete products, and everything is saved in an in-memory H2 database.

## What you can do with it

All endpoints live under `/api/v1/products`.

| Method | Path | What it does |
|--------|------|--------------|
| POST | `/api/v1/products` | Create a product (returns 201) |
| GET | `/api/v1/products/{id}` | Get one product |
| GET | `/api/v1/products` | Get all products |
| PUT | `/api/v1/products/{id}` | Update a product |
| DELETE | `/api/v1/products/{id}` | Delete a product (returns 204) |

If a product doesn't exist, the API answers with a 404 and a short message like `Product with 99 not found`.

Example body for POST:

```json
{ "name": "Laptop" }
```

## Built with

- Java 21
- Spring Boot 4.1 (Spring Web, Spring Data JPA, DevTools)
- H2 in-memory database
- springdoc-openapi (Swagger UI)
- Maven

## How to run it

1. Clone the repo and open it in IntelliJ.
2. Let Maven download the dependencies.
3. Run `FirstRestApiSpringApplication`.
4. Try the API in Swagger UI: http://localhost:8080/swagger-ui/index.html

Or from a terminal:

```
./mvnw spring-boot:run
```

To look at the data directly, open the H2 console at http://localhost:8080/console and connect with:

- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: (leave empty)

Then run `SELECT * FROM PRODUCTS;`. The database lives in memory, so the data disappears when the app stops.

## How the code is organised

```
product
├── api          ProductController plus request/response classes
├── domain       Product (the JPA entity)
├── repository   ProductRepository (extends JpaRepository)
├── service      ProductService (the logic)
└── support      ProductMapper and the exception handling
shared
└── api/response ErrorMessageResponse
```

A request goes: controller → service → mapper → repository → database, and then back out as a response object. The controller never touches the database directly, and the entity never leaves the service, so each part has one job.

## What I learned

- `ProductRepository` is an empty interface, but `save`, `findById`, `findAll` and `deleteById` still work. That's because it extends `JpaRepository`. Spring Data JPA writes the implementation at startup and injects it where it's needed, so I never write it myself.
- Separate request, response and entity classes feel like extra work at first, but they keep the API shape independent from the database.
- `@ControllerAdvice` is a clean way to turn exceptions into proper HTTP responses, instead of getting an ugly 500 error.
- Swagger UI is much faster than writing requests by hand.

Made by Adwaidh.

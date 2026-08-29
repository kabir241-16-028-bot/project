# ☕ Coffee Shop Management System (Spring Boot)

A simple, ready-to-run Spring Boot project for managing **Products, Categories, Customers, and Orders** in a coffee shop. It uses an **H2 in-memory database**, so no external database setup is required.

## Features

* **Product (Menu Item) CRUD** — Manage name, price, stock, and category
* **Category CRUD**
* **Customer CRUD**
* **Order Creation** — Automatically calculates the total amount and reduces product stock
* **Sample Data Auto-Load** — Sample data is automatically loaded when the application starts
* **Simple Built-in Web Dashboard** at `/` to view products and categories
* **H2 Database Console** at `/h2-console`

## Tech Stack

* **Java 17**
* **Spring Boot 3.3.4**
* **Spring Data JPA**
* **H2 Database (In-memory)**
* **Maven**
* **Lombok**

# VSCode Setup Guide

## 1. Prerequisites

You need the following:

* **Java JDK 17 or higher** installed
  Check it using:

  ```bash
  java -version
  ```

* **VSCode**

* VSCode Extension: **Extension Pack for Java**
  Extension ID: `vscjava.vscode-java-pack`

  Installing this extension provides Java support, Maven support, Java debugging, and other required tools.

* **Optional:** Spring Boot Extension Pack
  Extension ID: `vmware.vscode-spring-boot`

> You do not necessarily need to install Maven separately. You can use the Maven Wrapper included with the project, or use a system-wide Maven installation if you already have one.

## 2. Open the Project

1. Extract the ZIP file.
2. Open **VSCode**.
3. Go to **File → Open Folder**.
4. Select the `coffee-shop-management` folder.
5. VSCode should automatically detect it as a Java project.
6. The required dependencies will start downloading automatically. The first time may take a few minutes.

## 3. Run the Project

There are three easy ways to run the project.

### Option A: Run from VSCode UI

1. Open:
   `CoffeeShopManagementApplication.java`
2. Find the `main` method.
3. Click the **Run** button above the `main` method.

### Option B: Run from Terminal

If Maven is available, run:

```bash
mvn spring-boot:run
```

### Option C: Build and Run the JAR

First build the project:

```bash
mvn clean package
```

Then run the generated JAR:

```bash
java -jar target/coffee-shop-management.jar
```

## 4. Check the Application

After the application starts successfully, open your browser.

### Dashboard

```text
http://localhost:8080/
```

### H2 Database Console

```text
http://localhost:8080/h2-console
```

Use the following login information:

* **JDBC URL:** `jdbc:h2:mem:coffeeshopdb`
* **Username:** `sa`
* **Password:** Leave it empty

# API Endpoints

| Method | Endpoint                                   | Description             |
| ------ | ------------------------------------------ | ----------------------- |
| GET    | `/api/products`                            | Get all products        |
| GET    | `/api/products/{id}`                       | Get a specific product  |
| GET    | `/api/products/search?name=xxx`            | Search products by name |
| POST   | `/api/products`                            | Add a new product       |
| PUT    | `/api/products/{id}`                       | Update a product        |
| DELETE | `/api/products/{id}`                       | Delete a product        |
| GET    | `/api/categories`                          | Get all categories      |
| POST   | `/api/categories`                          | Add a new category      |
| GET    | `/api/customers`                           | Get all customers       |
| POST   | `/api/customers`                           | Add a new customer      |
| GET    | `/api/orders`                              | Get all orders          |
| POST   | `/api/orders`                              | Create a new order      |
| PUT    | `/api/orders/{id}/status?status=COMPLETED` | Update order status     |

# Sample: Create a New Order

Use the following JSON request body:

```json
{
  "customer": { "id": 1 },
  "items": [
    { "product": { "id": 1 }, "quantity": 2 },
    { "product": { "id": 3 }, "quantity": 1 }
  ]
}
```

> First, create a customer using `/api/customers`. Then use that customer's ID to create the order.

# Project Structure

```text
coffee-shop-management/
├── pom.xml
├── src/main/java/com/coffeeshop/management/
│   ├── CoffeeShopManagementApplication.java
│   ├── model/
│   │   ├── Product.java
│   │   ├── Category.java
│   │   ├── Customer.java
│   │   ├── Order.java
│   │   └── OrderItem.java
│   ├── repository/
│   │   └── JPA repositories
│   ├── service/
│   │   └── OrderService.java
│   └── controller/
│       └── REST controllers
└── src/main/resources/
    ├── application.properties
    └── static/
        └── index.html
```

### Project Purpose

The main purpose of this project is to provide a simple **Coffee Shop Management System** where the shop can manage its menu products, categories, customers, and orders using **Spring Boot, Spring Data JPA, and an H2 database**.

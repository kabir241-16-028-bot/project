# ☕ Coffee Shop Management System (Spring Boot)

একটি সহজ, রেডি-টু-রান Spring Boot প্রজেক্ট — Coffee Shop এর Product, Category, Customer এবং Order ম্যানেজমেন্টের জন্য। H2 in-memory database ব্যবহার করা হয়েছে, তাই কোনো external database সেটআপ লাগবে না।

## Features
- Product (menu item) CRUD — name, price, stock, category
- Category CRUD
- Customer CRUD
- Order creation — automatically calculates total amount & reduces stock
- Sample data auto-loads on startup
- Simple built-in web dashboard at `/` to view products & categories
- H2 database console at `/h2-console`

## Tech Stack
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Lombok

## VSCode এ চালানোর সহজ উপায় (Easy Setup in VSCode)

### 1. প্রয়োজনীয় জিনিস (Prerequisites)
- **Java JDK 17** বা তার উপরে ইন্সটল থাকতে হবে (`java -version` দিয়ে চেক করুন)
- **VSCode**
- VSCode Extension: **"Extension Pack for Java"** (`vscjava.vscode-java-pack`) — এটা ইন্সটল করলেই Maven, Java Debugger সব চলে আসবে
- (Optional) **"Spring Boot Extension Pack"** (`vmware.vscode-spring-boot`)

> Maven আলাদা করে ইন্সটল করা লাগবে না — VSCode Java Extension Pack এর সাথে built-in Maven wrapper ব্যবহার করা যায়, অথবা সিস্টেমে Maven ইন্সটল থাকলে সেটাও কাজ করবে।

### 2. প্রজেক্ট ওপেন করুন
1. এই zip ফাইলটা extract করুন
2. VSCode ওপেন করে **File → Open Folder** থেকে `coffee-shop-management` ফোল্ডারটা সিলেক্ট করুন
3. VSCode অটোমেটিক্যালি Java প্রজেক্ট হিসেবে ডিটেক্ট করবে এবং dependencies download শুরু করবে (প্রথমবার একটু সময় লাগতে পারে)

### 3. প্রজেক্ট রান করুন
তিনটা সহজ উপায় আছে:

**Option A: VSCode UI দিয়ে**
- `CoffeeShopManagementApplication.java` ফাইলটা ওপেন করুন
- `main` মেথডের উপরে **"Run"** বাটনে ক্লিক করুন

**Option B: Terminal দিয়ে (Maven লাগবে)**
```bash
mvn spring-boot:run
```

**Option C: Build করে Jar রান করুন**
```bash
mvn clean package
java -jar target/coffee-shop-management.jar
```

### 4. অ্যাপ চেক করুন
প্রজেক্ট রান হলে ব্রাউজারে যান:
- **Dashboard:** http://localhost:8080/
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:coffeeshopdb`
  - Username: `sa`
  - Password: *(খালি রাখুন)*

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | সব product দেখুন |
| GET | `/api/products/{id}` | নির্দিষ্ট product দেখুন |
| GET | `/api/products/search?name=xxx` | নাম দিয়ে খুঁজুন |
| POST | `/api/products` | নতুন product যোগ করুন |
| PUT | `/api/products/{id}` | product আপডেট করুন |
| DELETE | `/api/products/{id}` | product ডিলিট করুন |
| GET | `/api/categories` | সব category দেখুন |
| POST | `/api/categories` | নতুন category যোগ করুন |
| GET | `/api/customers` | সব customer দেখুন |
| POST | `/api/customers` | নতুন customer যোগ করুন |
| GET | `/api/orders` | সব order দেখুন |
| POST | `/api/orders` | নতুন order তৈরি করুন |
| PUT | `/api/orders/{id}/status?status=COMPLETED` | order এর status আপডেট করুন |

### Sample: নতুন Order তৈরি করার Request Body
```json
{
  "customer": { "id": 1 },
  "items": [
    { "product": { "id": 1 }, "quantity": 2 },
    { "product": { "id": 3 }, "quantity": 1 }
  ]
}
```
(প্রথমে `/api/customers` দিয়ে একটা customer বানিয়ে নিন, তারপর এই order request পাঠান।)

## Project Structure
```
coffee-shop-management/
├── pom.xml
├── src/main/java/com/coffeeshop/management/
│   ├── CoffeeShopManagementApplication.java
│   ├── model/         (Product, Category, Customer, Order, OrderItem)
│   ├── repository/    (JPA repositories)
│   ├── service/        (OrderService - business logic)
│   └── controller/     (REST controllers)
└── src/main/resources/
    ├── application.properties
    └── static/index.html   (simple dashboard)
```

## Extend করার আইডিয়া
- JWT Authentication যোগ করা
- MySQL/PostgreSQL এ migrate করা (শুধু `application.properties` পরিবর্তন করলেই হবে)
- Bill/Receipt generation (PDF)
- Admin login panel
- Daily sales report

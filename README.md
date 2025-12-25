# 🚆 RAILFLOW– Console-Based Railway Ticket Booking System

RAILFLOW is a **Java-based console application** that simulates a real-world railway ticket booking system. This project was built step-by-step with a **clean layered architecture**, proper **exception handling**, and a strong focus on **backend fundamentals**.

This is not just a "CRUD demo" — it demonstrates **how real backend systems are structured**, even without using frameworks like Spring Boot.

---

## 📌 Project Highlights

- Layered Architecture (Controller → Service → Repository → Util)
- Custom Domain-Specific Exceptions
- JSON-based persistent storage
- Secure password handling (SHA-256 hashing)
- Role-based access (User & Admin)
- Realistic ticket booking logic (seat allocation, cancellation)
- Clean separation of business logic and UI

---

## 🧱 Architecture Overview

```
controller/   → Handles user interaction (menus, input/output)
service/      → Core business logic
repository/   → Data access (JSON read/write)
model/        → Domain entities (User, Train, Ticket, Station, Admin)
exception/    → Custom application exceptions
util/         → Utility helpers (JSON, Date, Password)
```

### Why this architecture?
- Easy to maintain
- Easy to scale
- Matches real-world backend design
- No tight coupling between layers

---

## 🧑‍💻 Roles in the System

### 👤 User
- Sign up with username & password
- Login securely
- View all trains
- Search trains by source & destination
- Book tickets
- View booked tickets
- Cancel tickets

### 👮 Admin
- Login using admin credentials
- View all trains
- Search trains
- View all users
- View all tickets

---

## 🎟️ Ticket Booking Flow

1. User selects a train
2. System validates:
   - User existence
   - Train existence
   - Valid journey date
   - Source & destination stations
   - Seat availability
3. Seat number is allocated dynamically
4. Ticket is created & stored
5. Train seat count is updated

✔️ Any failure throws a meaningful exception

---

## ❌ Ticket Cancellation Flow

1. User provides ticket ID
2. System checks:
   - Ticket exists
   - Ticket belongs to user
   - Ticket is not already cancelled
3. Seat count is restored
4. Ticket status updated to CANCELLED

---

## ⚠️ Exception Handling

This project uses **custom runtime exceptions** instead of boolean flags:

- UserNotFoundException
- AuthenticationException
- TrainNotFoundException
- TicketNotFoundException
- SeatCapacityFullException
- InvalidDateException
- InvalidSourceToDestinationException
- StationsNotFoundException
- TicketCancelledException

### Why this approach?
- Cleaner service methods
- No confusing true/false returns
- Business logic stays expressive
- Controller decides how to show messages

---

## 🔐 Security

- Passwords are never stored in plain text
- SHA-256 hashing via `PasswordUtil`
- Authentication failures handled safely

---

## 💾 Data Persistence

- All data stored in JSON files
- Uses Gson for serialization/deserialization
- Repositories abstract file operations

Example files:
- users.json
- trains.json
- tickets.json
- admins.json

---

## 🧪 Design Decisions Worth Noting

### ✔ Why services return objects instead of booleans
- If operation fails → exception
- If operation succeeds → return entity (User, Ticket, etc.)
- Cleaner API, industry-standard pattern

### ✔ Why controllers handle printing
- Services never print
- Keeps business logic reusable

---

## 🚀 Future Enhancements

Planned improvements for scaling:

- CRUD operations for Trains & Stations
- Day-wise booking
- REST API version (Spring Boot)
- Database integration (MySQL/Postgres)

---

### 📁 Project Setup

1. **Clone the repository**
```
git clone <your-repo-url>
cd RailFlow
```

2. **Configure environment variables**

Create a `.env` file at the project root:
```
FILE_PATH=src/main/java/org/ticket/booking/system/data
```

This path is used to store and read JSON data files.

3. **Verify JSON data files**

Ensure the following files exist inside the data folder:

- `users.json`
- `trains.json`
- `tickets.json`
- `admins.json`

Each file should initially contain:
```
[]
```
Only for trains.json add some dummy data as we need Trains

4. **Run the application**

Using Gradle Wrapper (recommended):
```
./gradlew run
```

Or using local Gradle:
```
gradle run
```

---

## 🛠️ Tech Stack & Tools

### Language
- **Java (Core Java)**

### Build Tool
- **Gradle**

### Libraries
- **Gson** → JSON serialization/deserialization
- **Dotenv-java** → Environment variable management

### Concepts Used
- OOP (Encapsulation, Inheritance, Abstraction)
- Layered Architecture
- Exception-driven flow control
- SHA-256 Password Hashing
- File-based persistence (JSON)

### Storage
- Local JSON files (acts as a lightweight database)

---
## ⭐ Final Note

This project intentionally focuses on **core backend principles** before frameworks.

⭐ If you found this project helpful or insightful, feel free to star it on GitHub!


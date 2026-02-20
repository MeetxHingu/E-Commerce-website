# 🛒 E-Commerce Web Application

A full-stack E-Commerce web application developed using Spring Boot for the backend and HTML/CSS/JavaScript for the frontend.  
The system allows users to browse products, register/login, place orders, and enables administrators to manage products, users, and orders.

---

## 🚀 Features

### 👤 User Side
- User Registration & Login (Role-Based Authentication)
- Browse Products
- View Product Details
- Add to Cart
- Place Orders
- Order History
- Secure Authentication using Spring Security

### 🛠 Admin Side
- Admin Login
- Add / Update / Delete Products
- Manage Users
- View & Manage Orders
- Role-Based Access Control

---

## 🏗 Architecture

The project follows a layered architecture:

- Controller Layer (REST APIs)
- Service Layer (Business Logic)
- Repository Layer (Database Access)
- Model Layer (Entities)
- Frontend (HTML, CSS, JavaScript)

---

## 🧰 Technologies Used

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven
- REST APIs

### Frontend
- HTML5
- CSS3
- JavaScript (Vanilla JS)

### Database
- MySQL

---

## 🗄 Database Design

Main Entities:
- User (Admin / Customer)
- Product
- Order
- OrderItem
- Role

Relationships:
- One User → Many Orders
- One Order → Many OrderItems
- One Product → Many OrderItems

---

## 🔐 Security Features

- Role-Based Authentication (Admin / User)
- Password Encryption
- Secure API Endpoints
- Input Validation

---

## 📸 Screenshots

(Add screenshots here)

- Login Page
- Product Listing Page
- Admin Dashboard
- Cart Page
- Order Management Page

---


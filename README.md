
# 📝 Blog REST API (Spring Boot + JWT + PostgreSQL)

A **secure and modular Blog REST API** built with **Spring Boot 3**, featuring JWT authentication, role-based access, and CRUD operations for posts and comments.

---

## 🚀 Overview

This project demonstrates a production-grade backend API with:

- 🔐 **JWT-based Authentication & Authorization**
- 👥 **Role-based Access Control (USER, ADMIN)**
- 📝 **Post CRUD Endpoints**
- 💬 **Comment Endpoints** (add/update/delete by owner or admin)
- 🧩 **Layered Architecture (Controller → Service → Repository → Entity)**

---

## ⚙️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.6 |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| ORM | Spring Data JPA |
| Build Tool | Maven |
| IDE | IntelliJ / VS Code / Eclipse |

---

## 🔐 Authentication & Authorization

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/register-admin` | Register an admin |
| `POST` | `/api/auth/login` | Login and get JWT token |

Tokens are validated on every request using a custom `JwtAuthenticationFilter`.  
Passwords are encrypted using `BCryptPasswordEncoder`.

---

## 📝 Post Endpoints

| Method | Endpoint | Access | Description |
|--------|-----------|--------|-------------|
| `POST` | `/api/posts` | Authenticated | Create post |
| `GET` | `/api/posts` | Public | List all posts |
| `GET` | `/api/posts/user/{username}` | Public | Get posts by user |
| `PUT` | `/api/posts/{postId}` | Owner/Admin | Update post |
| `DELETE` | `/api/posts/{postId}` | Owner/Admin | Delete post |

---

## 💬 Comment Endpoints

| Method | Endpoint | Access | Description |
|--------|-----------|--------|-------------|
| `POST` | `/api/comments/{postId}` | Authenticated | Add comment to post |
| `GET` | `/api/comments/{postId}` | Public | View comments of post |
| `PUT` | `/api/comments/{commentId}` | Owner/Admin | Update comment |
| `DELETE` | `/api/comments/{commentId}` | Owner/Admin | Delete comment |

---

## 👥 User & Admin Endpoints

| Method | Endpoint | Access | Description |
|--------|-----------|--------|-------------|
| `GET` | `/api/user/profile` | Authenticated | View logged-in user info |
| `GET` | `/api/admin/dashboard` | Admin only | View admin dashboard |

---

## 🧱 Project Structure

```
com.learning.blog
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── repository
 ├── security
 ├── service
 └── BlogApplication.java
```

---

## ✅ Key Features

- JWT Authentication
- BCrypt password encryption
- Stateless Security Filter Chain
- Role-based Access Control
- Clean Layered Architecture
- Tested Post & Comment endpoints

---

## ⚡ How to Run

1. Update PostgreSQL configuration in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/blog
   spring.datasource.username=postgres
   spring.datasource.password=0000
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Run the project:
   ```bash
   mvn spring-boot:run
   ```

3. Test endpoints using Postman with JWT token in the header:
   ```http
   Authorization: Bearer <your_jwt_token>
   ```

---

## 🧑‍💻 Author

**Mohan Lal**  
Full Stack Java Developer | Spring Boot • React • PostgreSQL  
🔗 [GitHub Profile](https://github.com/mhnuk2007)

---

⭐ If you like this project, consider giving it a **star** on GitHub!

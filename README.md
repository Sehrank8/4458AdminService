# 🛠️ Admin Service

This microservice is responsible for managing job postings in the **SE4458 Job Search Web Application**. Admins and company users use this service to add, update, or retrieve job listings. It uses **MongoDB** as its data source and publishes new job events via **RabbitMQ** to notify other services.

[THE IMPORTANT DETAILS ARE HERE](https://github.com/Sehrank8/4458ApiGateway)

---

## 🚀 Overview

- REST endpoints for managing job postings.
- MongoDB is used as the primary database.
- Publishes job posting events to RabbitMQ for consumption by Notification Service.
- Supports Docker-based deployment.

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data MongoDB**
- **RabbitMQ**
- **Docker**

---

## 🗂 Project Structure

```
src/main/java/org/example/
├── controller/
│   └── JobController.java       # REST endpoints for managing jobs
├── service/
│   └── JobService.java          # Business logic
├── config/
│   └── RabbitConfig.java        # RabbitMQ setup
├── model/
│   └── Job.java                 # MongoDB entity
├── repository/
│   └── JobRepository.java       # MongoDB repository
└── AdminServiceApplication.java
```

---

## ⚙️ Configuration

Defined in `application.properties`:

```
spring.data.mongodb.uri=mongodb://localhost:27017/adminservice
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

---

## 📡 API Endpoints

### Create a Job
```
POST /api/v1/admin/jobs
```
**Request Body Example:**
```json
{
  "title": "Full Stack Developer",
  "company": "Tech Inc",
  "city": "Izmir",
  "description": "Looking for experienced Java + React developer"
}
```

### Get All Jobs
```
GET /api/v1/admin/jobs
```

### Update a Job
```
PUT /api/v1/admin/jobs/{id}
```

---

## 🐇 RabbitMQ Integration

- Publishes new job postings to `job-posting-queue`.
- This allows the Notification Service to notify users with matching job alerts.

---

## 🐳 Docker

Sample Dockerfile:
```dockerfile
FROM openjdk:17-jdk-alpine
COPY target/admin-service.jar admin-service.jar
ENTRYPOINT ["java", "-jar", "/admin-service.jar"]
```

Build and run:
```bash
docker build -t admin-service .
docker run -p 8082:8082 admin-service
```

---

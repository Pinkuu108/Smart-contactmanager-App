🚀 Smart Contact Manager – Spring Boot Web Application

Smart Contact Manager is a secure web application built using Java, Spring Boot, Spring Security, Spring Data JPA, MySQL, and Thymeleaf.
The application provides role-based authentication, secure password handling with BCrypt encryption, and complete contact management features with a clean layered architecture.

🔗 Repository URL  
https://github.com/Pinkuu108/Smart-contactmanager-App.git

✨ Features

✔ User Registration & Login  
✔ Role-Based Authentication & Authorization  
✔ BCrypt Password Encryption  
✔ Contact Management (Full CRUD)  
✔ DTO-Based Architecture  
✔ Input Validations  
✔ Global Exception Handling  
✔ Responsive UI (Bootstrap)  

🛠️ Tech Stack

Backend  
- Java / Java 8  
- Spring Boot  
- Spring MVC  
- Spring Security  
- Spring Data JPA  
- Hibernate  

Database  
- MySQL  

View Layer  
- Thymeleaf  
- HTML / CSS  
- Bootstrap  

📂 Project Structure

Smart_Contact_Manager/
├── controller/      → Request Handling Layer  
├── service/         → Business Logic  
├── repository/      → Data Access Layer (JPA)  
├── entity/          → Database Models  
├── dto/             → Data Transfer Objects  
├── exception/       → Global Exception Handling  
└── config/          → Security & App Configuration  

⚙️ Setup Instructions

1️⃣ Clone Repository  
git clone https://github.com/Pinkuu108/Smart-contactmanager-App.git

2️⃣ Open Project  
Import into IntelliJ IDEA / Eclipse / Spring Tool Suite

3️⃣ Configure Database (application.properties)

Update only these values:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database  
spring.datasource.username=your_username  
spring.datasource.password=your_password  

✔ No other changes required

4️⃣ Run Application  
Run the Spring Boot main class

5️⃣ Access Application  
http://localhost:8080/

🌐 Sample Endpoints (Illustrative)

Authentication  
POST /register        → Register new user  
POST /login           → User login  

Contacts  
GET    /contacts      → Fetch all contacts  
POST   /contacts      → Create new contact  
GET    /contacts/{id} → Fetch contact by ID  
PUT    /contacts/{id} → Update contact  
DELETE /contacts/{id} → Delete contact  

🎯 Project Objective

This project demonstrates secure authentication using Spring Security, clean DTO-driven design, validation mechanisms, exception handling strategies, and database-driven CRUD operations using Spring Data JPA.

🏷 Topics

mysql • css • java • html • spring • spring-boot • thymeleaf • java8 • springsecurity • springmvc • springdata-jpa

👨‍💻 Author

Pinku Prusty  
Java Developer  

🔗 LinkedIn: https://www.linkedin.com/in/pinkuna-prusty-55b487273/  
📧 Email: pinkunaprusty108@gmail.com

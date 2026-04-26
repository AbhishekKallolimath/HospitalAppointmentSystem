# 🏥 Hospital Appointment System

A web-based Hospital Appointment Management System built with **Java Servlets**, **JDBC**, **MySQL**, and **HTML/CSS**.  
This project demonstrates all basic database operations: **INSERT**, **UPDATE**, **DELETE**, **CREATE** and **SELECT** – each implemented in a simple, self-contained servlet.

![GitHub last commit](https://img.shields.io/github/last-commit/AbhishekKallolimath/HospitalAppointmentSystem)
![GitHub repo size](https://img.shields.io/github/repo-size/AbhishekKallolimath/HospitalAppointmentSystem)

---

## ✨ Features

- 📅 **Book an appointment** – adds a new patient and appointment (INSERT)
- ✏️ **Cancel an appointment** – updates appointment status to "CANCELLED" (UPDATE)
- 🗑️ **Delete a patient** – removes patient and all linked appointments (DELETE)
- 🆕 **Create a feedback table** – dynamically adds a new table to the database (CREATE)
- 👁️ **View all appointments** – displays a list with patient and doctor names (SELECT)

- 🎨 **Modern, responsive UI** – gradient backgrounds, card layout, hover effects
- 🧩 **Clean servlet‑only architecture** – no DAO layer, perfect for learning JDBC

---

## 🛠️ Tech Stack

| Layer       | Technology                      |
|-------------|---------------------------------|
| Backend     | Java Servlets, JDBC             |
| Frontend    | HTML5, CSS3, JavaScript         |
| Database    | MySQL                           |
| Server      | Apache Tomcat 9                 |
| IDE         | Eclipse EE (or any Java IDE)    |

---

## 📁 Project Structure (as on GitHub)
HospitalAppointmentSystem/
└── src/main/
├── java/com/hospital/servlets/
│ ├── InsertAppointmentServlet.java
│ ├── UpdateAppointmentServlet.java
│ ├── DeleteAppointmentServlet.java
│ ├── CreateTableServlet.java
│ └── DisplayAppointmentsServlet.java
└── webapp/
├── index.html
├── book-appointment.html
├── cancel-appointment.html
├── delete-patient.html
├── success.html
└── WEB-INF/web.xml


---

## 🚀 How to Run Locally

### 1. Prerequisites
- Java JDK 8 or 11
- Apache Tomcat 9
- MySQL Server
- Eclipse IDE for Enterprise Java (or VS Code with Tomcat extension)

### 2. Database Setup
Open MySQL and run:

```sql
CREATE DATABASE hospital_db;
USE hospital_db;

CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    specialization VARCHAR(100)
);

CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    address VARCHAR(255)
);

CREATE TABLE appointments (
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    app_date DATE,
    app_time TIME,
    status VARCHAR(20) DEFAULT 'SCHEDULED'
);

INSERT INTO doctors (name, specialization) VALUES
('Dr. Rajesh Kumar', 'Cardiologist'),
('Dr. Priya Sharma', 'Dermatologist'),
('Dr. Amit Patel', 'Orthopedic'),
('Dr. Neha Gupta', 'Pediatrician');

3. Update Database Credentials
In every servlet, locate this line:

java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

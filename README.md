# Hospital Management System (HMS) API

## Introduction
The **Hospital Management System (HMS) API** is a comprehensive backend solution built using **Java** and the **Spring Boot** framework.  
This project simulates real-world functionalities required to efficiently manage **patients**, **doctors**, **appointments**, and **insurance details** within a hospital ecosystem.  

The primary goal of this project is to create a **robust**, **scalable**, and **well-documented** RESTful service that can serve as a backbone for any hospital management application.

## Features Implemented

### 1. Patient Management
- Full CRUD operations for patient records, including creation, retrieval, updates, and deletion.

### 2. Doctor Management
- Full CRUD operations for managing doctor profiles and specialties.

### 3. Insurance Management
- CRUD operations for standalone insurance policies.

### 4. Appointment Scheduling
- Logic to book, cancel, and reschedule appointments, linking patients and doctors.

### 5. Relationship Management
- Assigning or removing an insurance policy to/from a patient.  
- Discharging a patient (state change).  
- Fetching all appointments for a specific patient or doctor.

### 6. Advanced Search & Filtering
- API endpoints to search for patients or doctors based on various criteria (e.g., name, gender, blood group, specialty).

### 7. Bulk Operations
- Endpoints to create multiple patients, doctors, or insurances in a single API call.


## Tech Stack & Tools

- **Language:** Java 17  
- **Framework:** Spring Boot 3.x  
- **Data Persistence:** Spring Data JPA / Hibernate  
- **Database:** MySQL  
- **Build Tool:** Apache Maven  
- **API Testing:** Postman  
- **Database GUI:** DBeaver  
- **Libraries:** Lombok (for reducing boilerplate code)






# Local Setup & Run 

## Clone the repository

git clone <TERA_GITHUB_REPO_URL>


## Database Configuration

-- **Make sure you have a MySQL database server running.**

-- **Create a new database (schema) named hospital_db.**

-- **Open src/main/resources/application.properties and update the following properties with your MySQL credentials:**

- spring.datasource.username=<YOUR_USERNAME>
- spring.datasource.password=<YOUR_PASSWORD>


## Run the application

-- **Open the project in your IDE (like IntelliJ IDEA).**

-- **Navigate to the main application class HospitalManagementSystemApiApplication.java and run it.**

-- **The application will start on http://localhost:8080**




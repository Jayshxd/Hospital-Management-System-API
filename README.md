# Hospital Management System (HMS) API

# Status
- working on security...
- stored jwt secret key in env variables and not in project files
- 

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

# API Endpoints 📖

Here is a list of the primary API endpoints available.

## Patient API (`/patients`)

| HTTP Method | URL Endpoint | Description |
|-------------|--------------|-------------|
| POST        | `/patients` | Create a new patient. |
| POST        | `/patients/bulk` | Create multiple new patients at once. |
| GET         | `/patients` | Get a list of all patients. |
| GET         | `/patients?name=...&gender=...` | Filter patients by name, gender, or blood group. |
| GET         | `/patients/{id}` | Get details of a specific patient. |
| PUT         | `/patients/{id}` | Fully update a patient's details. |
| PATCH       | `/patients/{id}` | Partially update a patient's details. |
| DELETE      | `/patients/{id}` | Delete a patient. |
| POST        | `/patients/{id}/discharge` | Mark a patient as discharged. |
| GET         | `/patients/{id}/appointments` | Get all appointments for a specific patient. |
| GET         | `/patients/{id}/insurance` | Get the insurance details for a specific patient. |
| PUT         | `/patients/{patientId}/insurances/{insuranceId}` | Assign an existing insurance policy to a patient. |
| DELETE      | `/patients/{patientId}/insurance` | Remove the insurance link from a patient. |
| GET         | `/patients/{patientId}/insurance/validity` | Check if a patient's insurance is currently valid. |



## Doctor API (`/doctors`)

| HTTP Method | URL Endpoint | Description |
|-------------|--------------|-------------|
| POST        | `/doctors` | Add a new doctor. |
| POST        | `/doctors/bulk` | Add multiple new doctors at once. |
| GET         | `/doctors` | Get a list of all doctors. |
| GET         | `/doctors?specialty=Cardiology` | Filter doctors by specialty. |
| GET         | `/doctors/{id}` | Get details of a specific doctor. |
| PUT         | `/doctors/{id}` | Fully update a doctor's details. |
| PATCH       | `/doctors/{id}` | Partially update a doctor's details. |
| DELETE      | `/doctors/{id}` | Remove a doctor from the system. |
| GET         | `/doctors/{id}/appointments?date=...` | Get all appointments for a doctor on a specific date. |



## Insurance API (`/insurances`)

| HTTP Method | URL Endpoint | Description |
|-------------|--------------|-------------|
| POST        | `/insurances` | Create a new, unassigned insurance policy. |
| POST        | `/insurances/bulk` | Create multiple new insurance policies. |
| GET         | `/insurances` | Get a list of all insurance policies. |
| GET         | `/insurances/{id}` | Get details of a specific insurance policy. |
| PUT         | `/insurances/{id}` | Fully update an insurance policy's details. |
| PATCH       | `/insurances/{id}` | Partially update an insurance policy's details. |
| DELETE      | `/insurances/{id}` | Delete an insurance policy from the system. |



## Appointment API (`/appointments`)

| HTTP Method | URL Endpoint | Description |
|-------------|--------------|-------------|
| POST        | `/appointments` | Book a new appointment for a patient with a doctor. |
| GET         | `/appointments` | Get a list of all appointments in the system. |
| PATCH       | `/appointments/{id}` | Reschedule an appointment (update time/date). |
| DELETE      | `/appointments/{id}` | Cancel an appointment. |







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




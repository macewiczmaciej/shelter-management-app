# Shelter Management Application

## Overview
This is a Spring Boot application designed for managing animals in a shelter. The application allows users to perform CRUD operations on animal data, track their status, and maintain a history of status changes.

---

## Features
- Add, edit, view, and delete animal records.
- Track status changes (e.g., "For Adoption", "Adopted", "Under Treatment").
- Maintain a history of all changes for each animal.
- Display formatted date and time.
- Responsive frontend built using Thymeleaf and Bootstrap.

---

## Technologies Used
- **Java 17**
- **Spring Boot** (Web, Data JPA, Thymeleaf)
- **MySQL** for database
- **Thymeleaf** for templating
- **Bootstrap** for frontend styling
- **Lombok** to reduce boilerplate code

---

## Setup

### Prerequisites
- **Java 17** or higher installed.
- **Maven** installed.
- **MySQL** running locally or on a server.

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/macewiczmaciej/shelter-management-app.git
   cd shelter-management-app
   ```

2. Configure the database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/schronisko_db
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application in your browser at:
   ```bash
   http://localhost:8080/animals
   ```

---

## Project Structure

### Backend
- `Animal`: Represents an animal entity with fields like name, species, age, status, and a date of addition.
- `AnimalHistory`: Tracks status changes for each animal.
- `AnimalController`: Handles all HTTP requests and maps them to appropriate services.
- `AnimalRepository`: Interface for database operations using Spring Data JPA.

### Frontend
- Thymeleaf templates:
  - `index.html`: Displays a list of animals.
  - `create.html`: Form for adding a new animal.
  - `update.html`: Form for updating an existing animal.
  - `view.html`: Detailed view of an animal with its history.

---

## Usage
- **Adding an Animal**: Navigate to "Add Animal" and fill in the form.
- **Editing an Animal**: Click "Edit" next to an animal in the list.
- **Viewing Details**: Click "Details" to see an animal's information and history.
- **Deleting an Animal**: Click "Delete" to remove an animal from the system.

---

## Future Improvements
- Add user authentication and roles (e.g., Admin, Staff).
- Implement file upload for animal photos.
- Add advanced search and filtering functionality.
- Export animal data to CSV or Excel.

---

## License
This project is licensed under the MIT License.

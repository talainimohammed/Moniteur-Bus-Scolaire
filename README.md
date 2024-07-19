
# 🚌 School Bus Tracking System

A modern solution designed to enhance the safety, efficiency, and transparency of school transportation by providing real-time tracking and notifications for school buses.

## 🚀 Features

- **Real-Time Tracking**: Monitor the exact location of school buses in real-time on an interactive map.
- **Automatic Notifications**: Receive instant updates about bus departures, arrivals, and schedule changes.
- **User-Friendly Mobile App**: Access tracking, notifications, and route information through an intuitive mobile application.
- **Multiple User Interfaces**: Tailored interfaces for school administrators, parents, and drivers.
- **Secure & Robust**: Developed using Java, Spring Boot, Angular, and PostgreSQL to ensure security and reliability.

## 🗺️ System Architecture

- **Frontend**: Angular for dynamic, responsive web applications.
- **Backend**: Java and Spring Boot for robust server-side logic.
- **Database**: PostgreSQL for efficient, secure data storage.

![Architecture Diagram](https://github.com/talainimohammed/Moniteur-Bus-Scolaire/blob/master/MonitorBus%20%7BTALAINI_Mohammed%7D.pdf)

## 📋 Table of Contents

- [Features](#-features)
- [System Architecture](#-system-architecture)
- [Installation](#-installation)
- [Usage](#-usage)
- [Contributing](#-contributing)
- [License](#-license)

## 🛠️ Installation

### Prerequisites

- **Java** (version 11 or higher)
- **Node.js** (version 14 or higher)
- **Angular CLI**
- **PostgreSQL**

### Clone the Repository

```bash
git clone https://github.com/talainimohammed/Moniteur-Bus-Scolaire
cd Moniteur-Bus-Scolaire
```

### Backend Setup

1. Navigate to the `backend` directory:

   ```bash
   cd backend
   ```

2. Build the Spring Boot application:

   ```bash
   ./mvnw clean install
   ```

3. Run the Spring Boot application:

   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup

1. Navigate to the `frontend` directory:

   ```bash
   cd ../frontend
   ```

2. Install the dependencies:

   ```bash
   npm install
   ```

3. Run the Angular application:

   ```bash
   ng serve
   ```

### Database Setup

1. Create a PostgreSQL database:

   ```sql
   CREATE DATABASE school_bus_tracking;
   ```

2. Update the `application.properties` file in the `backend` directory with your PostgreSQL credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/school_bus_tracking
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 🎮 Usage

- **Access the Web Application**: Visit `http://localhost:4200` in your browser.
- **Login**: Use the provided demo credentials or create a new account.
- **Real-Time Tracking**: Monitor bus locations, receive notifications, and explore the features through the mobile app.



## 📞 Contact

- **Author**: Mohammed Talaini
- **Email**: [talainimohammed@gmail.com](mailto:talainimohammed@gmail.com)
- **LinkedIn**: [mohammed-talaini](https://www.linkedin.com/in/mohammed-talaini)
- **Project Repository**: [School Bus Tracking System](https://github.com/talainimohammed/Moniteur-Bus-Scolaire)

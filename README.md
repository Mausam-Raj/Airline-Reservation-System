# Airline Reservation System

This project is an airline reservation system built using Spring Boot. It provides functionalities for user authentication, flight management, and ticket booking. The system includes various RESTful endpoints to manage users, flights, and reservations.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
  - [User Controller](#user-controller)
  - [Flight Controller](#flight-controller)
  - [Auth Controller](#auth-controller)
- [Security Configuration](#security-configuration)

## Features

- User registration and authentication with JWT.
- Password update for users.
- Flight management including adding, updating, and deleting flights (Admin only).
- Booking and viewing booked tickets.
- Cancelling tickets.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT for authentication
- Hibernate/JPA
- Lombok
- Maven
- MySQL


## Setup and Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/Mausam-Raj/Airline-Reservation-System.git
    cd airline-reservation-system
    ```

2. **Update the database configuration:**
   Update the `application.properties` file with your database configuration.

3. **Build the project:**
    ```sh
    mvn clean install
    ```

4. **Run the project:**
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### User Controller

- **Update Password**
    ```
    PUT /user/update-password
    ```
    Request Params: `userId`, `password`
    
    Response: `GeneralDto`
    
- **Get Flights**
    ```
    GET /user/get-flights
    ```
    Request Params: `from`, `to`
    
    Response: `List<FlightResponseDto>`
    
- **Book Flight**
    ```
    POST /user/book-flight
    ```
    Request Params: `userId`, `flightId`
    
    Response: `TicketResponseDto`
    
- **View Booked Tickets**
    ```
    GET /user/booked-tickets
    ```
    Request Params: `userId`
    
    Response: `List<TicketResponseDto>`
    
- **Cancel Ticket**
    ```
    DELETE /user/cancel-ticket
    ```
    Request Params: `ticketId`
    
    Response: `GeneralDto`

### Flight Controller

- **Add Flight**
    ```
    POST /flight/add-flight
    ```
    Request Body: `FlightRequestDto`
    
    Response: `FlightResponseDto`
    
- **Update Flight Details**
    ```
    PUT /flight/update-flight
    ```
    Request Body: `FlightUpdateRequestDto`
    
    Response: `FlightResponseDto`
    
- **Delete Flight**
    ```
    DELETE /flight/delete-flight
    ```
    Request Params: `flightId`
    
    Response: `FlightResponseDto`

### Auth Controller

- **Login**
    ```
    POST /auth/login
    ```
    Request Body: `JwtRequestDto`
    
    Response: `JwtResponseDto`
    
- **Register User**
    ```
    POST /auth/register
    ```
    Request Body: `UserRequestDto`
    
    Response: `UserResponseDto`

## Security Configuration

The security configuration is handled by `SecurityConfig.java`. Key points include:

- JWT authentication for securing endpoints.
- Specific endpoints (`/auth/login`, `/auth/register`, `/swagger-ui/**`, `/v3/api-docs/**`) are publicly accessible.
- Other endpoints are secured and require authentication.
- Password encoding using BCrypt.


# Spring Security with JWT Authentication

This repository demonstrates the implementation of JWT (JSON Web Token) Authentication in a Spring Boot application.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Security Configuration](#security-configuration)
- [JWT Implementation](#jwt-implementation)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Overview

This project showcases how to secure a Spring Boot application using JWT. It covers user authentication, role-based authorization, and integrates with a frontend application.

## Features

- User registration and login
- JWT-based authentication and authorization
- Role-based access control
- Integration with frontend clients (e.g., React)

## Getting Started

### Prerequisites

Ensure you have the following installed:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Postman](https://www.postman.com/downloads/) or any API testing tool

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/sumitsdubey/spring-security.git
Navigate to the project directory:

bash
Copy
Edit
cd spring-security
Build the project:

bash
Copy
Edit
mvn clean install
Run the application:

bash
Copy
Edit
mvn spring-boot:run
The application will start on http://localhost:8080.

Usage
Register a new user:

Send a POST request to /api/v1/public/register with the following JSON payload:

json
Copy
Edit
{
  "username": "newuser",
  "password": "password123",
  "roles": ["USER"]
}
Authenticate the user:

Send a POST request to /api/v1/public/login with the user's credentials to receive a JWT token.

Access secured endpoints:

Include the received JWT token in the Authorization header as a Bearer token to access secured endpoints.

Project Structure
swift
Copy
Edit
spring-security/
├── src/main/java/com/sumit/tableserve/
│   ├── config/            // Security configurations
│   ├── controller/        // REST controllers
│   ├── dto/               // Data Transfer Objects
│   ├── filter/            // JWT filters
│   ├── util/              // Utility classes (e.g., JwtUtils)
│   └── Application.java   // Main application class
└── src/main/resources/
    └── application.properties // Application properties
Security Configuration
The security configuration is managed in the SecurityConfig class:

java
Copy
Edit
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFilter(jwtUtils, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
JWT Implementation
The JwtUtils class handles the creation and validation of JWT tokens:

java
Copy
Edit
@Component
public class JwtUtils {

    private final String jwtSecret = "your_secret_key";
    private final int jwtExpirationMs = 86400000;

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // Handle exceptions
        }
        return false;
    }
}
API Endpoints
POST /api/v1/public/register: Register a new user

POST /api/v1/public/login: Authenticate user and return JWT

GET /api/v1/secure/user: Access secured endpoint (requires authentication)

Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments
Special thanks to all contributors and the open-source community for their invaluable resources and support.

ruby
Copy
Edit

**Tips for an Effective README:**

- **Clarity and Conciseness:** Ensure each section is clear and to the point. Avoid unnecessary jargon.&#8203;:contentReference[oaicite:0]{index=0}

- **Visual Aids:** :contentReference[oaicite:1]{index=1}&#8203;:contentReference[oaicite:2]{index=2}

- **Consistent Formatting:** :contentReference[oaicite:3]{index=3}&#8203;:contentReference[oaicite:4]{index=4}

- **Regular Updates:** :contentReference[oaicite:5]{index=5}&#8203;:contentReference[oaicite:6]{index=6}

:contentReference[oaicite:7]{index=7}&#8203;:contentReference[oaicite:8]{index=8}
::contentReference[oaicite:9]{index=9}
 

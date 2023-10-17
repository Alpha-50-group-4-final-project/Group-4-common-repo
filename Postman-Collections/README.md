# Postman Collections for WEare Social Network API Testing

## Table of Contents
1. [Overview](#overview)
2. [Test Scenarios](#test-scenarios)
3. [Environment and Preconditions](#environment-and-preconditions)
4. [Newman Reports](#newman-reports)
5. [API Documentation](#api-documentation)

---

## Overview
This folder contains the Postman collection and environment files for API testing of the WEare Social Network. These tests validate the backend functionality and are crucial for ensuring the robustness of the application.

---

## Test Scenarios

### Happy Path
- POST - User registration
- POST - Get Authorized
- PUT - Edit a post
- POST - Create a comment
- PUT - Edit a comment
- GET - Get all comments by post
- DEL - Delete a post

### User Controllers
- GET - Show User Post by ID
- GET - Get User by ID
- POST - Upgrade Expertise Profile
- POST - Upgrade Personal Profile
- POST - Admin registration

### Post Controllers
- POST - Create a post
- GET - Find All posts
- PUT - Edit a post
- POST - Like a post
- POST - Unlike a post
- GET - Show comments on post
- DEL - Delete a post

### Comment Controllers
- POST - Create a post
- POST - Create a comment
- GET - Find all comments
- PUT - Edit a comment
- POST - Like a comment
- POST - Unlike a comment
- GET - Get all comments by post
- GET - Get one comment by post
- DEL - Delete a comment

### Skill Controllers
- GET - Find all skills
- POST - Create skill
- PUT - Edit skill
- GET - Get one skill
- PUT - Delete skill

### Connection Controllers
- POST - User One sends connect request to user Two
- POST - User Two accepts the request

---

## Environment and Preconditions
The application is Dockerized and runs through IntelliJ with a shared database. Further environment setup and preconditions are documented in the [Test Plan](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/blob/main/Documentation/Test-Plan.md).

---

## Newman Reports
For a detailed guide on how to run Postman collections using Newman and generate reports, please refer to [Newman Reports](./Newman%20Reports/README.md).

---

## API Documentation

- **Swagger**: The API documentation for the WEare Social Network is available [here](http://localhost:8081/swagger-ui.html#/).

---

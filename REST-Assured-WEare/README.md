# REST-Assured-WEare for API Testing of WEare Social Network

## Table of Contents
1. [Overview](#overview)
2. [Dependencies](#dependencies)
3. [Project Structure](#project-structure)
4. [REST Assured WEare Reports](#rest-assured-weare-reports)
5. [API Documentation](#api-documentation)

---

## Overview
This folder contains the REST-Assured framework used for API testing of the WEare Social Network. The tests are written in Java and make use of REST-Assured for sending HTTP requests and receiving responses.

---

## Dependencies

- **REST-Assured**: Version 5.3.2
- **JSON.org**: Version 20230618
- **Google Gson**: Version 2.10.1
- **TestNG**: Version 7.8.0
- **JavaFaker**: Version 1.0.2
- **MySQL Connector**: Version 5.1.6

---

## Project Structure

- **src/main/java/com/api/utils**
  - `Constants.java`: Constants used across tests.
  - `Endpoints.java`: Endpoints for API requests.
  - `Helper.java`: Helper methods, including a method for JSON validation.
  - `RequestJSON.java`: Request bodies for API calls.
  
- **src/test/java/weare**
  - `BaseTest.java`: Base test class with setup and teardown methods.
  - `CommentsTests.java`: Tests related to comments.
  - `ConnectionTest.java`: Tests related to user connections.
  - `LoginUserTest.java`: Tests for user login.
  - `PostTest.java`: Tests related to posts.
  - `RegisterUserTest.java`: Tests for user registration.
  - `SkillsTest.java`: Tests related to skills.

- **src/test/resources**
  - `commentcontrollers-suite.xml`
  - `connectioncontroller-suite.xml`
  - `happy-path-suite.xml`
  - `main-suite.xml`
  - `postcontroller-suite.xml`
  - `skillscontroller-suite.xml`
  - `unhappy-suite.xml`
  - `usercontrollers-suite.xml`

These XML suite files allow you to configure which specific tests to run.

---

## REST Assured WEare Reports
For detailed reports on the API tests, please refer to the [REST Assured WEare Reports](./REST%20Assured%20WEare%20Reports/README.md).

---

## API Documentation

- **Swagger**: The API documentation for the WEare Social Network is available [here](http://localhost:8081/swagger-ui.html#/).

---

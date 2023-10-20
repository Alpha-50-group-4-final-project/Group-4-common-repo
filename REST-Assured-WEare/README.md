# REST-Assured-WEare for API Testing of WEare Social Network

## Table of Contents
1. [Overview](#overview)
2. [Database Configuration](#database-configuration)
3. [Dependencies](#dependencies)
4. [Project Structure](#project-structure)
5. [REST Assured WEare Reports](#rest-assured-weare-reports)
6. [API Documentation](#api-documentation)
7. [Repository Structure](#repository-structure)

---

## Overview
This folder contains the REST-Assured framework used for API testing of the WEare Social Network. The tests are written in Java and make use of REST-Assured for sending HTTP requests and receiving responses.

---

## Database Configuration
Before running tests, navigate to the following path in the project directory to configure your database credentials:

```plaintext
src
|-- test
|   |-- resources
|       |-- configuration.properties
```

In the `configuration.properties` file, provide the details for your database connection:

```plaintext
# Database connection
database.url= [Your Database URL]
database.username= [Your Database Username]
database.password= [Your Database Password]
```

This step is essential to enable the framework to interact with the database for test setup and verification.

---

## Dependencies

- **REST-Assured**: Version 5.3.2
- **JSON.org**: Version 20230618
- **Google Gson**: Version 2.10.1
- **TestNG**: Version 7.8.0
- **JavaFaker**: Version 1.0.2
- **MySQL Connector**: Version 5.1.6
- **Allure TestNG**: Version 2.24.0
- **Spring Security Core**: Version 5.5.3

---

## Project Structure

- **src/main/java/com/api/utils**
  - `Constants.java`
  - `Endpoints.java`
  - `Helper.java`
  - `RequestJSON.java`

These classes provide various utilities to assist in API testing.


- **src/test/java/weare**
  - `commentcontrollers`
  - `connectioncontrollers`
  - `login`
  - `postcontrollers`
  - `skillcontrollers`
  - `usercotrollers`

These packages are organized by functionality, allowing you to find and run tests specific to different features of the application.


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
For detailed reports on the API tests, please refer to the [REST Assured WEare Reports](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare/rest-assured-weare-reports).

---

## API Documentation

**Swagger**: The API documentation for the WEare Social Network is accessible via Swagger, but only when the application is actively running. You can view it [here](http://localhost:8081/swagger-ui.html#/).

---

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
- [Performance Testing](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Performance-Testing) - Houses the JMeter test plan.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/WEare-SeleniumWebDriver-tests) - Holds our Java-based test automation framework for UI testing.
- [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
  - [REST Assured WEare](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.
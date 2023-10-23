# Postman Collections for WEare Social Network API Testing

## Table of Contents
1. [Overview](#overview)
2. [Test Scenarios](#test-scenarios)
3. [Pre-Request Scripts](#pre-request-scripts)
4. [Environment and Preconditions](#environment-and-preconditions)
5. [Newman Reports](#newman-reports)
6. [API Documentation](#api-documentation)
7. [Repository Structure](#repository-structure)

---

## Overview
This folder contains the Postman collection and environment files for API testing of the WEare Social Network. These tests validate the backend functionality and are crucial for ensuring the robustness of the application.

### Note on Request Independence
All API requests in this collection are designed to work independently. However, to ensure 100% individual operability, it's recommended to run the 'User registration (FPW-233)' request at least once. If this condition is met, each request in the collection can be executed individually without any dependencies.

---

## Test Scenarios

### Happy Path
Covers basic user flows for authorization, post creation, and commenting.

### User Controllers
Focuses on user management, including registration and profile upgrades.

### Post Controllers
Handles all post-related activities, such as creation, editing, and deletion.

### Comment Controllers
Tests functionalities around comments, including creation, editing, and retrieval.

### Skill Controllers
Involves operations for skills management, like creation and editing.

### Connection Controllers - Happy Path
Addresses the sending and accepting of connection requests between users.

### Unhappy Paths
Tests edge cases and expected failures, such as invalid inputs and unauthorized actions.

---

## Pre-Request Scripts

### Main Folder

The main folder contains a pre-request script that sets up several variables used across tests:

- `CurrentDate`: Sets the current date using Moment.js.
- `random_memberDate`: Generates a random past date in "DD/MM/YYYY" format.
- `random_birthDate`: Generates a random past date in "YYYY-MM-DD" format.
- `random_city`: Generates a random number between 1 and 25.
- `random_number`: Generates a random number between 1 and 10.

These variables are used to generate dynamic data for tests and to simulate different scenarios.

---

## Environment and Preconditions
To execute the Postman tests successfully, make sure you are connected to the database and that the WEare Social Network application is actively running. If the application is not running, the Postman tests will not be able to interact with the API endpoints.

---

## Newman Reports
For a detailed guide on how to run Postman collections using Newman and generate reports, please refer to [Newman Reports](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections/newman-reports).

---

## API Documentation

**Swagger**: The API documentation for the WEare Social Network is accessible via Swagger, but only when the application is actively running. You can view it [here](http://localhost:8081/swagger-ui.html#/).

---

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
- [Performance Testing](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Performance-Testing) - Houses the JMeter test plan.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/SeleniumWebDriver-automation-framework) - Holds our Java-based test automation framework for UI testing.
  - [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
- [REST Assured WEare](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.

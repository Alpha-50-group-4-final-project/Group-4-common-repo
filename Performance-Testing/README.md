# WEare JMeter Performance Testing

## Table of Contents
1. [Overview](#overview)
2. [Dependencies](#dependencies)
3. [Test Plan Structure](#test-plan-structure)
4. [Prerequisites](#prerequisites)
5. [Running Tests](#running-tests)
6. [Reporting](#reporting)
7. [Repository Structure](#repository-structure)

---

## Overview
This directory contains JMeter performance tests specifically designed for the WEare Social Network application. The tests cover a range of functionalities including user registration, posting, and commenting. 

The test setup is structured to simulate a user journey that starts from registration and progresses to login, creating posts, commenting on those posts, and finally, browsing the feed. This journey is tested both as a registered user and an unregistered user, with each test being performed by different users to mimic real-world usage patterns.


---

## Dependencies
- **JMeter**: Version 5.6.2
- **Ultimate Thread Group Plugin**

---

### User Actions (Test Fragment)
The "User Actions" Test Fragment is designed to modularize the simulation of core user activities within the test plan. This organization makes it easier to manage and reuse these components across different thread groups in the test plan. It consists of two main Simple Controllers:

- **Registered User Actions (Simple Controller)**
 Houses logic for activities related to registered users, such as:

    - Registration
    - Login
    - Browsing posts
    - Creating posts
    - Commenting

- **Unregistered User Actions (Simple Controller)**

Contains logic for activities that can be performed by unregistered users, specifically:

    - Browsing public posts


### Listeners
- Summary Report

---

## Prerequisites

### JMeter Setup
Make sure you have JMeter and the Ultimate Thread Group plugin installed on your system. Load the `.jmx` test plan file into JMeter.

### Application and Database Setup
Ensure that you have a running instance of the WEare Social Network application and are connected to its database. This is crucial for the successful execution of the tests.

### Verify Setup
To verify your setup, open JMeter and ensure that the `.jmx` file loads successfully. Additionally, confirm that the application is running and the database is accessible.


---

## Running Tests

1. Open JMeter and load the `.jmx` test plan.

2. Configure the thread groups as needed.


---

## Reporting
The test plan includes listeners like 'Response Times Over Time' for real-time reporting. Additional reporting can be configured as needed.

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
  - [Performance Testing](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Performance-Testing) - Houses the JMeter test plan.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/WEare-SeleniumWebDriver-tests) - Holds our Java-based test automation framework for UI testing.
- [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
- [REST Assured](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.
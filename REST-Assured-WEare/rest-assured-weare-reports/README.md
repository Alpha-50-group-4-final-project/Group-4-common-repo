# REST Assured WEare Reports

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Running Tests](#running-tests)
4. [Repository Structure](#repository-structure)

## Overview
This folder contains detailed reports generated using JUnit for the REST Assured tests. The reports provide in-depth insights into the API test results, including pass/fail status, error messages, and more.

## Prerequisites

### Maven Setup
Before running tests, make sure you have Maven installed on your system. If it is not installed, you can download it from the [official Maven website](https://maven.apache.org/download.cgi) and follow the installation instructions. Verify the installation by running the following command in the terminal:

```bash
mvn -v
```

## Running Tests

1. Execute the following command in the project root directory:
    ```bash
    mvn test
    ```
   Or you can use the provided BAT files:
    - Main Suite: `RunAndReport_REST_Assured_API-Tests_main-suite.bat`
    - Happy Path Suite: `RunAndReport_REST_Assured_API-Tests_happy-path-suite.bat`
    - Unhappy Path Suite:`RunAndReport_REST_Assured_API-Tests_unhappy.bat`

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
- [Performance Testing](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Performance-Testing) - Houses the JMeter test plan.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/WEare-SeleniumWebDriver-tests) - Holds our Java-based test automation framework for UI testing.
- [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
  - [REST Assured WEare](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.

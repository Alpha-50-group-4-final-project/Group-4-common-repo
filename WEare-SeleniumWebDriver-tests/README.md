# WEare Selenium WebDriver UI Testing

## Table of Contents
1. [Overview](#overview)
2. [Dependencies](#dependencies)
3. [Browser Support](#browser-support)
4. [Directory Structure](#directory-structure)
5. [Prerequisites](#prerequisites)
6. [Running Tests](#running-tests)
7. [Reporting](#reporting)
8. [Repository Structure](#repository-structure)

---

## Overview
This directory contains Selenium WebDriver UI tests for the WEare Social Network application. It includes tests for different functionalities like user registration, login, posting, commenting, and admin actions.

---

## Dependencies
- **Selenium**: Version 3.141.59
- **TestNG**: Version 7.4.0
- **Log4j**: Version 2.14.1
- **JavaFaker**: Version 1.0.2

---

## Browser Support
The framework supports multiple browsers:
- Google Chrome
- Mozilla Firefox
- Microsoft Edge

---

## Directory Structure

```bash
src
|-- main
|   |-- java
|       |-- com
|           |-- api
|               |-- CommentModel.java
|               |-- Constants.java
|               |-- EndPoints.java
|               |-- JsonBodies.java
|               |-- PostModel.java
|               |-- WEareApi.java
|           |-- enums
|               |-- BrowserTypes.java
|           |-- pages
|               |-- BasePage.java
|       |-- CustomWebDriverManager.java
|       |-- Driver.java
|       |-- PropertiesManager.java
|       |-- UserActions.java
|       |-- Utils.java
|
|-- test
    |-- java
        |-- WEare
            |-- adminpart
                |-- AdminCommentTest.java
                |-- AdminEnableDisableUserTest.java
                |-- AdminPostTest.java
            |-- privatepart
                |-- CommentsTests.java
                |-- ConnectToUserTests.java
                |-- EditProfileTests.java
                |-- PostsTests.java
            |-- publicpart
                |-- AnonymousUserTests.java
                |-- LoginTests.java
                |-- RegistrationTests.java
            |-- BaseTest.java
            |-- MyTestWatcher.java
            |-- TestSuites.java

```
### Utility Files for UI Testing with API Support

In this Selenium WebDriver framework, we incorporate API calls to make UI testing more efficient and maintainable. Although the primary focus is UI testing, the API utility files help in setting up the test environment, creating necessary entities, and more. Below is a brief description of each:

- **`Constants.java`**: Houses all constant values that are used across different UI tests and API calls. Centralizing these constants makes the framework easier to manage.

- **`EndPoints.java`**: Lists all the API endpoints that are called during the UI tests. Keeping them in one place makes it convenient to update them if they change.

- **`JsonBodies.java`**: Defines the JSON request bodies for API calls. This central location for managing payloads ensures consistency and reusability.

- **`PostModel.java` and `CommentModel.java`**: Include the data models for Posts and Comments, aiding in the serialization and deserialization of JSON data during API calls within UI tests.

- **`WEareApi.java`**: Acts as an API client within the framework, providing methods for making API calls. This abstraction simplifies the process of incorporating API calls into UI tests.



---

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
    - `RunAndReport_Selenium_UI-Tests_HappyPath.bat`
    - `RunAndReport_Selenium_UI-Tests_UnhappyPath.bat`

---

## Reporting
The framework uses Allure for reporting. Reports are generated automatically when you run tests using the provided BAT files. Screenshots are captured automatically in case of test failures and are embedded in the report.

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/WEare-SeleniumWebDriver-tests) - Holds our Java-based test automation framework for UI testing.
- [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
- [REST-Assured-WEare](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.
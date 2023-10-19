## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Running Tests](#running-tests)
4. [Repository Structure](#repository-structure)

## Overview
This folder contains reports generated using Newman for the Postman collections. Newman is a command-line runner for Postman that allows us to run and test a Postman collection directly from the command line.

## Prerequisites

1. **Node.js and npm**: Before installing Newman, make sure to install Node.js and npm (Node Package Manager) on your computer. If you haven't installed them yet, you can download and install them from the [official Node.js website](https://nodejs.org/en/download/).

2. **Newman**:
    ```bash
    npm install -g newman
    ```
   Verify the installation:
    ```bash
    newman -v
    ```

3. **Newman Reporter**:
    ```bash
    npm install -g newman-reporter-htmlextra
    ```

## Running Tests

To run the Postman collection and generate reports, execute the provided BAT file `RunAndReport_API_Tests.bat`

### Preconditions

- Newman is installed
- Newman reporter is installed

### Steps

1. Ensure that the preconditions are met.
2. Navigate to the directory containing `RunAndReport_API_Tests.bat`.
3. Double-click `RunAndReport_API_Tests.bat` to execute it.

#### The BAT File Will:

- Automatically locate the Postman collection and environment files.
- Run the tests using Newman.
- Generate an HTML report.
- Display the HTML report using the system's default program for viewing HTML files once the tests are completed.

---

## Repository Structure

- [Main Repository](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main) - The main part of the repository.
- [Documentation](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Documentation) - Contains test plans, test cases, and other QA-related documents.
- [Performance Testing](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Performance-Testing) - Houses the JMeter test plan.
- [WEare Selenium WebDriver Tests](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/WEare-SeleniumWebDriver-tests) - Holds our Java-based test automation framework for UI testing.
   - [Postman Collections](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/Postman-Collections) - Includes Postman collections for API testing.
- [REST Assured WEare](https://github.com/Alpha-50-group-4-final-project/Group-4-common-repo/tree/main/REST-Assured-WEare) - Contains REST Assured tests for API validation.
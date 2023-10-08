# REST Assured WEare Reports

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Running Tests](#running-tests)
4. [Generating Reports](#generating-reports)

## Overview
This folder contains detailed reports generated using JUnit for the REST Assured tests. The reports provide in-depth insights into the API test results, including pass/fail status, error messages, and more.

## Prerequisites
- Make sure Java is installed on your system. If not, you can download it from the [official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- Ensure that Maven is installed. If not, download and install it from the [official website](https://maven.apache.org/download.cgi).

## Running Tests
1. Open Command Prompt.
2. Navigate to the project directory containing the `pom.xml` file.
3. Run the following command to execute the tests:

```bash
mvn clean test
```

## Generating Reports
After running the tests, you can generate HTML reports using the following command:

```bash
mvn surefire-report:report
```

This will generate an HTML report in the `target/site` folder. Open the `surefire-report.html` file in a web browser to view the detailed test report.

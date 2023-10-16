## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Running Tests](#running-tests)

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
- Open the HTML report in your default web browser after the tests are completed.
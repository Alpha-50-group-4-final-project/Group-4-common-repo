# Newman Reports for WEare Social Network API Testing

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Verify Installation](#verify-installation)
5. [Running Tests](#running-tests)
6. [Generating Reports](#generating-reports)

---

## Overview
This folder contains reports generated using Newman for the Postman collections. Newman is a command-line runner for Postman that allows us to run and test a Postman collection directly from the command line.

---

## Prerequisites
Before installing Newman, make sure to install Node.js and npm (Node Package Manager) on your computer. If you haven't installed them yet, you can download and install them from the [official Node.js website](https://nodejs.org/en/download/).

---

## Installation
To install Newman, open your command prompt and run the following command:

```bash
npm install -g newman
```

---

## Verify Installation
After the installation is complete, you can verify it by running:

```bash
newman -v
```

This should display the installed version of Newman, confirming that it was successfully installed.

---

## Running Tests
1. Open command prompt.
2. Navigate to the folder containing your Postman collection and environment files.
3. Run the following command:

```bash
newman run "Path/To/Your/Postman/Collection.json" -e "Path/To/Your/Postman/Environment.json"
```

Replace `Path/To/Your/Postman/Collection.json` and `Path/To/Your/Postman/Environment.json` with the actual paths to your Postman collection and environment files.

---

## Generating Reports
To generate a report after running your tests, you can extend the command like so:

```bash
newman run "Path/To/Your/Postman/Collection.json" -e "Path/To/Your/Postman/Environment.json" --reporters=cli,htmlextra
```

This will generate an HTML report alongside the CLI output.

---

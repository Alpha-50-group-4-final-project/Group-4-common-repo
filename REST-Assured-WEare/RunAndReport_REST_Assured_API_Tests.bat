@echo off

:: Run Maven to execute tests
call mvn clean test

:: Run Allure to generate and serve the report
call npx allure-commandline serve
@echo off

echo "Running HappyPathTestSuite..."

:: Clean the project and remove old test results
call mvn clean

:: Remove old Allure results and create a new allure-results folder
rmdir /s /q allure-results
mkdir allure-results

:: Run tests with the HappyPath tag
call mvn test -Dtest=WEare.TestSuites$HappyPathTestSuite

:: Serve the Allure report
call npx allure-commandline serve allure-results

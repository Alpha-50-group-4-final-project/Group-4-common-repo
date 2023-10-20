@echo off

echo "Running UnhappyPathTestSuite..."

:: Clean the project and remove old test results
call mvn clean

:: Remove old Allure results and create a new allure-results folder
rmdir /s /q allure-results
mkdir allure-results

:: Run tests with the UnhappyPath tag
call mvn test -Dtest=WEare.TestSuites$UnhappyPathTestSuite

:: Serve the Allure report
call npx allure-commandline serve allure-results
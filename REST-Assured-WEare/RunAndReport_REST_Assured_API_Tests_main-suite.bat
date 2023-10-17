@echo off
echo Current directory: %cd%

set SUITE_PATH=src\test\resources\main-suite.xml
set RESULTS_PATH=.\allure-results

:: Clear old Allure results
if exist %RESULTS_PATH%\* (
    echo Deleting old Allure results...
    del /Q /F %RESULTS_PATH%\*
)

:: Run main-suite.xml using Maven with surefire.suiteXmlFiles
call mvn clean test -Dsurefire.suiteXmlFiles=%SUITE_PATH%

:: Serve the Allure report from the allure-results directory
call npx allure-commandline serve %RESULTS_PATH%

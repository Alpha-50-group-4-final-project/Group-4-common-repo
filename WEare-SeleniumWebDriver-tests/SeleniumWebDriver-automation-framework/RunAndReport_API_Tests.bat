@echo off

:: Run Newman to execute Postman collection synchronously
call mvn test -Dtest=TestSuites$UnhappyPathTestSuite
call mvn test -Dtest=TestSuites$HappyPathTestSuite
call npx allure-commandline serve K:\Group-4-common-repo\WEare-SeleniumWebDriver-tests\SeleniumWebDriver-automation-framework\allure-results



:: Debug line if no HTML file is found
echo No HTML report found.
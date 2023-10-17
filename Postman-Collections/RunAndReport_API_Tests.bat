@echo off

:: Run Newman to execute Postman collection synchronously
call newman run "WEare - Postman API Tests.postman_collectionv2.json" -e "WEare_env NEW.postman_environmentv2.json" --reporters cli,htmlextra

:: Find the latest generated HTML report in the newman folder and try to open it
for /f "delims=" %%i in ('dir /b /o-d /a-d newman\*.html') do (
    echo Attempting to open: newman\%%i
    start "Report" "newman\%%i"
    goto :eof
)

:: Debug line if no HTML file is found
echo No HTML report found.
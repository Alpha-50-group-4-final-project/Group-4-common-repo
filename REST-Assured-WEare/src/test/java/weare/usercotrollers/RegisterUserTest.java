package weare.usercotrollers;

import base.BaseTest;


import io.restassured.response.Response;

import jdk.jfr.Label;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class RegisterUserTest extends BaseTest {

    public static final String STATUS_CODE_ERROR_MSG = "Incorrect status code. Expected %s.";

    @Test(priority = 1)
    @Label("Jira - FPW-233")
    public void newUserRegistered_When_ValidDataProvided() {
        USERNAME = letsTryIt();
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_USER_BODY, ROLE_USER,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        String responseBodyText = response.asString();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertTrue(responseBodyText.matches("User with name .* and id \\d+ was created"));
        String[] responseBody = response.asString().split(" ");

        regularUserId = responseBody[6];
        registeredUsername = responseBody[3];
        usernames.add(registeredUsername);
        System.out.printf("User named %s with id %s created.", registeredUsername, regularUserId);

    }

    @Test(priority = 2)
    @Label("Jira - FPW-233")
    public void newAdminRegistered_When_ValidDataProvided() {
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_USER_BODY, ROLE_ADMIN,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                ADMIN_USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertTrue(response.asString().contains(ADMIN_USERNAME), "Admin is registered with different username");
        assertTrue(response.asString().contains("id"), "Newly registered admin doesn't have ID");

        String[] responseBody = response.asString().split(" ");
        adminUserId = responseBody[6];
        System.out.printf("Admin user %s was registered successfully.%n", ADMIN_USERNAME);
    }

    @Test(priority = 1)
    @Label("Jira - FPW-268")
    public void newUserRegistered_When_UsernameNumeric() {
        USERNAME = "12345678";
        System.out.println(USERNAME.length());
        System.out.println(USERNAME);
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_USER_BODY, ROLE_USER,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();
        System.out.println(response.getBody().asPrettyString());


        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 400, format("Incorrect status code. Expected: %s.", 400));

    }


}

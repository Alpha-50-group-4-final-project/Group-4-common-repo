package weare.usercotrollers;

import base.BaseTest;


import io.restassured.response.Response;

import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static dataBaseManipulations.BaseSetup.freshUsernames;
import static dataBaseManipulations.BaseSetup.freshUsersIds;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class RegisterUserTest extends BaseTest {

    @Test(priority = 1)
    @Label("Jira - FPW-233")
    public void newUserRegistered_When_ValidDataProvided() {
        USERNAME = generateUsername();

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

        userId = responseBody[6];
        registeredUsername = responseBody[3];

        System.out.printf("User named %s with id %s created.", registeredUsername, userId);
        freshUsernames.add(registeredUsername);
        freshUsersIds.add(Integer.parseInt(userId));
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

        userReceivingRequestId = responseBody[6];
        String adminName = responseBody[3];

        freshUsernames.add(ADMIN_USERNAME);
        freshUsersIds.add(Integer.parseInt(adminUserId));

    }

    @Test(priority = 1)
    @Label("Jira - FPW-268")
    public void newUserRegistered_When_UsernameNumeric() {
        String name = "12345678";
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_USER_BODY, ROLE_USER,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                name));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();
        System.out.println(response.getBody().asPrettyString());


        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 400, format("Incorrect status code. Expected: %s.", 400));

    }

    @Test(priority = 1)
    @Label("Jira - FPW-263")
    public void newUserRegistered_When_UserWithThatUserNameAlreadyExist() {
        USERNAME = generateUsername();
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
        String[] responseBody = response.asString().split(" ");
        userId = responseBody[6];
        registeredUsername = responseBody[3];
        freshUsernames.add(registeredUsername);
        freshUsersIds.add(Integer.parseInt(userId));


        response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 409, format("Incorrect status code. Expected: %s.", 409));

        assertEquals(response.getBody().jsonPath().get("error"), "Conflict",
                format("Incorrect response message. Expected: %s.", "Conflict"));
        assertEquals(response.getBody().jsonPath().get("message"), "User with this username already exist",
                format("Incorrect response error. Expected: %s.", "User with this username already exist"));
        assertNotNull(response.getBody().jsonPath().get("timestamp"), "Empty timestamp field.");

    }


}

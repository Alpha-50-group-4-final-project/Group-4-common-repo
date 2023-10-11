package weare.usercotrollers;

import base.BaseTest;


import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.*;


public class RegisterUserTest extends BaseTest {

    public static final String STATUS_CODE_ERROR_MSG = "Incorrect status code. Expected %s.";

    @Test(priority = 1)

    public void registerNewUserTest() {
        USERNAME = letsTryIt();

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_USER_BODY,
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
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");

        assertTrue(responseBodyText.matches("User with name .* and id \\d+ was created"));

        String[] responseBody = response.asString().split(" ");

        regularUserId = responseBody[6];
        registeredUsername = responseBody[3];
        usernames.add(registeredUsername);

    }

    @Test(priority = 2)
    public void registerAdminUser() {
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_ADMIN_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                ADMIN_USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        assertTrue(response.asString().contains(ADMIN_USERNAME), "Admin is registered with different username");
        assertTrue(response.asString().contains("id"), "Newly registered admin doesn't have ID");

        String[] responseBody = response.asString().split(" ");
        adminUserId = responseBody[6];

        System.out.printf("Admin user %s was registered successfully.%n", ADMIN_USERNAME);
    }

    @Test(priority = 1)

    public void registerNewUserTest_when_invalidUserIsProvided() {
        USERNAME = letsTryIt();

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_USER_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                "a"));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();


        System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_INTERNAL_SERVER_ERROR,
                format(STATUS_CODE_ERROR_MSG, HttpStatus.SC_INTERNAL_SERVER_ERROR));

        assertTrue(response.getBody().jsonPath().getString("message").contains("The username must have at least 2 symbols!"),
                "Error message is not correct");
        assertNotNull(response.getBody().jsonPath().getString("timestamp"),"There is no timestamp  when error occurs");
    }
}

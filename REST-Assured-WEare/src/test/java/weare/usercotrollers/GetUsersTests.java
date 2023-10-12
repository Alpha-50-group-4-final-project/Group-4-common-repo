package weare.usercotrollers;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Constants.EMAIL;
import static com.api.utils.Endpoints.GET_REGISTER_USERS;
import static com.api.utils.Endpoints.GET_USER_BY_ID;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.GET_ALL_REGISTER_USERS_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;

public class GetUsersTests extends BaseTest {
    @Test
    public  void getAllRegisterUsersTest() {
        baseURI = format("%s%s", BASE_URL, GET_REGISTER_USERS);

        String requestBody = (format(GET_ALL_REGISTER_USERS_BODY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("[0].userId"), "User id is empty.");
        assertNotNull(response.getBody().jsonPath().get("[0].username"), "Username is empty.");
        assertNotNull(response.getBody().jsonPath().get("[0].expertiseProfile"), "Expertise Profile is empty.");
        System.out.printf("List of all existing users below: %s", response.getBody().asPrettyString());

        expertiseProfileId = response.getBody().jsonPath().get("expertiseProfile[0].id").toString();
        expertiseUpdateUsername = response.getBody().jsonPath().getString("username[0]");
    }
    @Test
    public void getUserByIdTest() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = (format("%s%s", BASE_URL, format(GET_USER_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .queryParam("principal", registeredUsername)
                .get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("id").toString(), regularUserId,
                "User id is not correct.");
        assertEquals(response.getBody().jsonPath().get("username"), registeredUsername,
                "Username is not correct.");
        assertEquals(response.getBody().jsonPath().get("email"), EMAIL,
                "Email is not correct.");
        System.out.printf("User with id: %s below: %s", regularUserId, response.getBody().asPrettyString());
    }
}

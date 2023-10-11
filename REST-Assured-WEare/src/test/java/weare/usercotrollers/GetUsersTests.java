package weare.usercotrollers;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.GET_REGISTER_USERS;
import static com.api.utils.Endpoints.GET_USER_BY_ID;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.GET_ALL_REGISTER_USERS_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        System.out.println(response.getBody().asPrettyString());

        expertiseProfileId = response.getBody().jsonPath().get("expertiseProfile[0].id").toString();
        expertiseUpdateUsername = response.getBody().jsonPath().getString("username[0]");
    }
    @Test
    public void getUserById() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = (format("%s%s", BASE_URL, format(GET_USER_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .queryParam("principal", registeredUsername)
                .get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("id").toString(), regularUserId,
                "User id is not correct.");
        assertEquals(response.getBody().jsonPath().get("username"), registeredUsername,
                "Username is not correct.");
    }
}

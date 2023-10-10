package weare;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ConnectionTest extends BaseTest {
    @BeforeClass
    public void userSetUp() {
        registerNewUser();
//        System.out.println("New user with Registered username: " + newUserName);
//        System.out.println("New user with Registered password: " + newUserPass);
//        System.out.println("New user with Registered id: " + regularUserId);
    }

    @Test(priority = 1)
    public void sendConnectionRequestTest() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, regularUserId);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD).body(requestBody)
                .post();

        System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK,
                format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* send friend request to .*"));
        System.out.printf("%s send friend request to %s.\n", EXISTING_USER, newUserName);
    }

    @Test(priority = 2)
    public void getConnectionRequestTest() {
        //sendConnectionRequest();
        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, regularUserId));
        response = postRequestSpecificationWithAuthentication(newUserName, newUserPass)
                .get(baseURI);

        //System.out.println(response.getBody().asPrettyString());
        connectionId = response.getBody().jsonPath().getString("[0].id");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK,
                format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getString("[0].approved"), "false", "This connection request have been already approved.");
        System.out.printf("%s received connection request with id %s.\n", newUserName, connectionId);
    }

    @Test(priority = 3)
    public void approveConnectionRequestTest() {
        if (connectionId == null) {
            getConnectionRequestTest();
        }
        baseURI = format("%s%s", BASE_URL, format(APPROVE_REQUEST, regularUserId, connectionId));

        response = postRequestSpecificationWithAuthentication(newUserName, newUserPass)
                .queryParam("requestId ", connectionId)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK,
                format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* approved request of .*"));
        System.out.printf("%s approved request with id %s from %s.\n", newUserName, connectionId, EXISTING_USER);
    }
}

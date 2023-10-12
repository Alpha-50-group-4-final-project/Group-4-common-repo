package weare.connectioncontrollers;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SendConnectionRequestTest extends BaseTest {

    @BeforeClass
    public void userSetUp() {
        registerNewUser();
        registerAnotherUser();
    }


    @Test
    public void sendConnectionRequestTest() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, userReceivingRequestId, userReceivingRequestName);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = given()
                .cookies(getAuthCookie(registeredUsername, PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* send friend request to .*"),
                format("Incorrect response. Expected: %s send friend request to %s.", registeredUsername, userReceivingRequestName));
        String resp = response.body().asPrettyString();
        int i = resp.indexOf(" ");
        assertEquals(resp.substring(0, i), registeredUsername,
                format("The name of the person sending friend request was wrong. Expected: %s, received: %s", registeredUsername, resp.substring(0, i)));
        System.out.printf("%s send friend request to %s.", registeredUsername, userReceivingRequestName);

        isConnectionSend = true;
    }

}

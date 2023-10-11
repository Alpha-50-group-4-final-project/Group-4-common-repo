package weare.connectioncontrollers;

import static org.apache.http.HttpStatus.SC_OK;

import base.BaseTest;
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
    }


    @Test(priority = 1)
    public void sendConnectionRequestTest() {

        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, regularUserId);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* send friend request to .*"));

        isConnectionSend=true;
    }

    @Test(priority = 2)
    public void getConnectionRequestTest() {
        if(isConnectionSend==false){
            sendConnectionRequestTest();
        }
        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, regularUserId));
        response = requestSpecificationWithAuthentication()
                .get(baseURI);

        connectionId = response.getBody().jsonPath().getString("[0].id");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getString("[0].approved"), "false",
                "This connection request have been already approved.");
    }

    @Test(priority = 3)
    public void approveConnectionRequestTest() {
        if (connectionId == null) {
            getConnectionRequestTest();
        }
        baseURI = format("%s%s", BASE_URL, format(APPROVE_REQUEST, regularUserId, connectionId));

        response = requestSpecificationWithAuthentication()
                .queryParam("requestId ", connectionId)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* approved request of .*"));
    }
}

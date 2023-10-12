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
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SendConnectionRequestTest extends BaseTest {
    @BeforeClass
    public void userSetUp() {
        registerNewUser();
    }


    @Test
    public void sendConnectionRequestTest() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, regularUserId, USERNAME);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* send friend request to .*"),
                format("Incorrect response. Expected: %s send friend request to %s.", USERNAME, registeredUsername));
        String resp = response.body().asPrettyString();
        int i = resp.indexOf(" ");
        assertEquals(resp.substring(0,i), USERNAME,
                format("The name of the person sending friend request was wrong. Expected: %s, received: %s", USERNAME, resp.substring(0,i)));
        System.out.printf("%s send friend request to %s.", USERNAME, registeredUsername );

        isConnectionSend=true;
    }

//    @Test(priority = 2)
//    public void getConnectionRequestTest() {
//        if(isConnectionSend==false){
//            sendConnectionRequestTest();
//        }
//        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, regularUserId));
//        response = requestSpecificationWithAuthentication()
//                .get(baseURI);
//
//        connectionId = response.getBody().jsonPath().getString("[0].id");
//
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
//        assertEquals(response.getBody().jsonPath().getString("[0].approved"), "false",
//                "This connection request have been already approved.");
//    }
//
//    @Test(priority = 3)
//    public void approveConnectionRequestTest() {
//        if (connectionId == null) {
//            getConnectionRequestTest();
//        }
//        baseURI = format("%s%s", BASE_URL, format(APPROVE_REQUEST, regularUserId, connectionId));
//
//        response = requestSpecificationWithAuthentication()
//                .queryParam("requestId ", connectionId)
//                .post(baseURI);
//
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
//        assertTrue(response.asString().matches(".* approved request of .*"));
//    }
}

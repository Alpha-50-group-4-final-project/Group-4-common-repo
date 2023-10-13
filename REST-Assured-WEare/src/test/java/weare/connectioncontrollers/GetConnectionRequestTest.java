package weare.connectioncontrollers;

import base.BaseTest;
import io.restassured.http.ContentType;
import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;


public class GetConnectionRequestTest extends BaseTest {
    @Test
    @Label("Jira - FPW-244")
    public void getConnectionRequestTest_When_RequestSent() {
        if (isNull(isConnectionSend )) {
            sendConnectionRequest();
        }
        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, userReceivingRequestId));
        response = given()
                .cookies(getAuthCookie(userReceivingRequestName, PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI);

        connectionId = response.getBody().jsonPath().getString("[0].id");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getString("[0].id"), connectionId, format("Connection Id not as expected. Expected: %s.", connectionId));
        assertEquals(response.getBody().jsonPath().getString("[0].approved"), "false",
                "This connection request have been already approved.");
        assertEquals(response.getBody().jsonPath().getString("[0].seen"), "false",
                "This connection request have been already seen.");
        System.out.printf("Connection request with id: %s received.",connectionId);
    }


}

package weare;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ConnectionTest extends BaseTest {


    @Test(priority = 1)
    public void sendConnectionRequest() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, annUserID);

        Response response = given()
                .cookies(getAuthCookie(EXISTING_USER,EXISTING_USER_PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .post(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
        assertTrue(response.asString().matches(".* send friend request to .*"));
    }

    @Test(priority = 2)
    public void getConnectionRequest() {

        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, annUserID));
        Response response = given()
                .cookies(getAuthCookie(EXISTING_USER_two,EXISTING_USER_PASSWORD))
                .contentType("application/json")
                .when()
                .get(baseURI);

        //System.out.println(response.getBody().asPrettyString());
        //System.out.println(response.getBody().jsonPath().getInt("[0].id"));
        setConnectionId(response.getBody().jsonPath().getInt("[0].id"));

        int statusCode = response.getStatusCode();
        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
//        Assert.assertNotNull(response.path("skill"), "Incorrect information returned");
    }

    @Test(priority = 3)
    public void approveConnectionRequest() {
       if(connectionId == 0) {
           getConnectionRequest();
       }
        baseURI = format("%s%s", BASE_URL, format(APPROVE_REQUEST, annUserID, getConnectionId()));
        System.out.println(baseURI);
        //System.out.println(getConnectionId());



        Response response = given()
                .cookies(getAuthCookie(EXISTING_USER_two,EXISTING_USER_PASSWORD))
                .contentType(ContentType.JSON)
                .queryParam("requestId ", getConnectionId())
                .when()
                .post(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
//        Assert.assertNotNull(response.path("skill"), "Incorrect information returned");
    }
}

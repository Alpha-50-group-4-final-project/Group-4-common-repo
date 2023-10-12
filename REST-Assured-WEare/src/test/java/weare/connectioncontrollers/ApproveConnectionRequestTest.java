package weare.connectioncontrollers;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApproveConnectionRequestTest extends BaseTest {
    @Test
    public void approveConnectionRequestTest() {
        if (connectionId == null) {
            getConnectionRequest();
        }
        baseURI = format("%s%s", BASE_URL, format(APPROVE_REQUEST, regularUserId, connectionId));

        response = requestSpecificationWithAuthentication()
                .queryParam("requestId ", connectionId)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(response.asString().matches(".* approved request of .*"),
                format("Incorrect response. Expected: %s approved request of %s.", regularUserId, USERNAME));
        String resp = response.body().asPrettyString();
        int i = resp.indexOf(" ");
        assertEquals(resp.substring(0, i), registeredUsername,
                format("The name of the person sending friend request was wrong. Expected: %s, received: %s", registeredUsername, resp.substring(0, i)));
        System.out.printf("%s approved request of %s.", registeredUsername, USERNAME);
    }


}

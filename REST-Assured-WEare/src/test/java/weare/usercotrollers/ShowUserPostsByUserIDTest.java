package weare.usercotrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SHOW_USER_POSTS_BY_ID;
import static com.api.utils.RequestJSON.SHOW_USER_BY_ID_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class ShowUserPostsByUserIDTest extends BaseTest {
    @Test
    public void showUserPostsByUserIdTest() {
        if (regularUserId == null || postId == null) {
            registerNewUser();
            createPost();
        }
        baseURI = (format("%s%s", BASE_URL, format(SHOW_USER_POSTS_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .body(SHOW_USER_BY_ID_BODY)
                .get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("[0].postId"), "Post id is empty.");
        assertNotNull(response.getBody().jsonPath().get("[0].content"), "Post content is empty.");
        assertNotNull(response.getBody().jsonPath().get("[0].date"), "Post Date is empty.");
        assertNotNull(response.getBody().jsonPath().get("[0].rank"), "Rank is empty.");
        System.out.printf("List of user %s posts below: %s ", regularUserId, response.getBody().asPrettyString());
    }
}

package weare.usercotrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SHOW_USER_POSTS_BY_ID;
import static com.api.utils.RequestJSON.SHOW_USER_BY_ID_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class ShowUserPostsByUserIDTest extends BaseTest {
    @Test
    public void showUserPostsByUserID() {
        if (regularUserId == null || postId == null) {
            registerNewUser();
            createPost();
        }
        baseURI = (format("%s%s", BASE_URL, format(SHOW_USER_POSTS_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .body(SHOW_USER_BY_ID_BODY)
                .get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }
}

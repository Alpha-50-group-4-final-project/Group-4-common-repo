package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SHOW_COMMENTS;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class ShowCommentsOnPostTest extends BaseTest {
    @Test
    public void showCommentsOnPostTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(SHOW_COMMENTS, postId));

        response = getRequest(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }
}

package weare.postcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SHOW_COMMENTS;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ShowCommentsOnPostTest extends BaseTest {
    @Test
    @Label("Jira - FPW-252")
    public void commentsShownOnPost_When_ValidRequestSent() {
        if (isNull(postId)) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(SHOW_COMMENTS, postId));

        response = getRequest(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        //System.out.println(response.getBody().asPrettyString());
        String resp = response.getBody().asString();
        if (resp.length() > 2) {
            assertNotNull(response.getBody().jsonPath().get("[0].commentId"), "Comment id is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].content"), "Comment content is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].postId"), "Post id is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].userId"), "User id is empty.");
            System.out.printf("List of all existing comments below: %s",response.getBody().asPrettyString());
        }else {
            assertEquals(response.body().asString(), "[]", "Response body isn't empty.");
            System.out.println("No comments added yet.");
        }
    }
}

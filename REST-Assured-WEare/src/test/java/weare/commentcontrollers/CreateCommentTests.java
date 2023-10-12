package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.API_COMMENTS;
import static com.api.utils.Endpoints.CREATE_COMMENTS;
import static com.api.utils.RequestJSON.COMMENT_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class CreateCommentTests extends BaseTest {
    @Test(priority = 1)
    public void createCommentTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, regularUserId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();

        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("content"), COMMENT_CONTENT,
                "Response comment is different than provided.");

        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("\nComment with id %s was created.\n", commentId);

    }
    @Test(priority = 2)
    public void createComment_when_1001charsLongTextIsProvided() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT_1001_CHARS, postId, regularUserId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("message"), "Content size must be up to 1000 symbols",
                "Response message is not correct.");
        assertEquals(response.getBody().jsonPath().get("error"), "Bad Request",
                "Error message is not correct.");
    }
}

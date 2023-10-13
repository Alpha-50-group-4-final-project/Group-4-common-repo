package weare.commentcontrollers;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.API_COMMENTS;
import static com.api.utils.Endpoints.CREATE_COMMENTS;
import static com.api.utils.RequestJSON.COMMENT_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateCommentTests extends BaseTest {
    @Test(priority = 1)
    public void commentCreated_When_ValidDataProvided() {
        if (isNull(postId)) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);
        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, regularUserId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("content"), COMMENT_CONTENT,
                "Response comment is different than provided.");
        assertNotNull(response.getBody().jsonPath().get("commentId"), "Comment id is empty.");
        assertNotNull(response.getBody().jsonPath().get("date"), "Date comment created is empty.");
        assertNotNull(response.getBody().jsonPath().get("liked"), "Liked field is empty.");

        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("\nComment with id %s was created.\n", commentId);
    }
    @Test(priority = 2)
    public void commentNotCreated_When_1001charsLongTextProvided() {
        if (isNull(postId)) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);
        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT_1001_CHARS, postId, regularUserId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_BAD_REQUEST, format("Incorrect status code. Expected %s.", SC_BAD_REQUEST));
        assertEquals(response.getBody().jsonPath().get("message"), CONTENT_SIZE_ERROR,
                format("Response message is not correct. Expected: %s", CONTENT_SIZE_ERROR));
        assertEquals(response.getBody().jsonPath().get("error"), BAD_REQUEST_ERROR,
                format("Error message is not correct. Expected: %s", BAD_REQUEST_ERROR));
    }
}

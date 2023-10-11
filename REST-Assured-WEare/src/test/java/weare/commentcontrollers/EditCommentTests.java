package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.EDIT_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class EditCommentTests extends BaseTest {
    @Test(priority = 1)
    public void editExistingCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        response = requestSpecificationWithAuthentication()
                .queryParam("commentId", commentId)
                .queryParam("content", EDITED_COMMENT_CONTENT)
                .put(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
    }
    @Test(priority = 2)
    public void editExistingComment_when_1001charsTextIsProvided() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        response = requestSpecificationWithAuthentication()
                .queryParam("commentId", commentId)
                .queryParam("content",COMMENT_CONTENT_1001_CHARS)
                .put(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("message"), "Content size must be up to 1000 symbols",
                "Response message is not correct.");
        assertEquals(response.getBody().jsonPath().get("error"), "Bad Request",
                "Error message is not correct.");
    }
}

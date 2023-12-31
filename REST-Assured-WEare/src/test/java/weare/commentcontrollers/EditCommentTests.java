package weare.commentcontrollers;

import base.BaseTest;

import static org.apache.http.HttpStatus.*;

import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.EDIT_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class EditCommentTests extends BaseTest {
    @Test(priority = 1)
    @Label("Jira - FPW-255")
    public void commentEdited_When_ValidDataProvided() {
        createComment();

        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        response = requestSpecificationWithAuthentication()
                .queryParam("commentId", commentId)
                .queryParam("content", EDITED_COMMENT_CONTENT)
                .put(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
        System.out.printf("Comment with id %s was successfully edited.", commentId);
        deleteComment(commentId);
    }

    @Test(priority = 2)
    @Label("Jira - FPW-270")
    public void commentNotEdited_When_ContentProvidedIsTooLong() {
        createComment();

        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        response = requestSpecificationWithAuthentication()
                .queryParam("commentId", commentId)
                .queryParam("content", COMMENT_CONTENT_1001_CHARS)
                .put(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_BAD_REQUEST, format("Incorrect status code. Expected %s.", SC_BAD_REQUEST));
        assertEquals(response.getBody().jsonPath().get("message"), CONTENT_SIZE_ERROR,
                format("Response message is not correct. Expected: %s", CONTENT_SIZE_ERROR));
        assertEquals(response.getBody().jsonPath().get("error"), BAD_REQUEST_ERROR,
                format("Error message is not correct. Expected: %s", BAD_REQUEST_ERROR));
        deleteComment(commentId);
    }
}

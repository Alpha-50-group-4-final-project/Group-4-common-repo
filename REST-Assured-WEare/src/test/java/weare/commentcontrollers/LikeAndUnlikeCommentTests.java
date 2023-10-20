package weare.commentcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class LikeAndUnlikeCommentTests extends BaseTest {

    @Test(priority = 1)
    @Label("Jira - FPW-256")
    public void commentLiked_When_ValidRequestSent() {
        createComment();

        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertTrue(response.getBody().jsonPath().get("liked"), "Post is not liked.");
        deleteComment(commentId);
    }

    @Test(priority = 2)
    @Label("Jira - FPW-257")
    public void commentUnliked_When_ValidRequestSent() {
        likeComment();

        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                post(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
        deleteComment(commentId);
    }
}

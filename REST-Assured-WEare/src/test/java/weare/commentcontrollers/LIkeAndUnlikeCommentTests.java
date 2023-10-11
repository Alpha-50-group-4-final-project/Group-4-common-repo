package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.*;

public class LIkeAndUnlikeCommentTests extends BaseTest {

    @Test(priority = 1)
    public void likeACommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().jsonPath().get("liked"), "Post is not liked.");
    }

    @Test(priority = 2)
    public void unlikeACommentTest() {
        if (commentId == null) {
            createComment();
            likeACommentTest();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                post(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
    }


}

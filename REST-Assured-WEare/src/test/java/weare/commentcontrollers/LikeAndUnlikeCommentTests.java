package weare.commentcontrollers;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class LikeAndUnlikeCommentTests extends BaseTest {

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
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
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
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
    }


}

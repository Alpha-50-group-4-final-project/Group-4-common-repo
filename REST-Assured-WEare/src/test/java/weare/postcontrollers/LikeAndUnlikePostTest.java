package weare.postcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class LikeAndUnlikePostTest extends BaseTest {

    @Test(priority = 1)
    @Label("Jira - FPW-249")
    public void postLiked_When_ValidRequestSent() {
        if (isNull(postId)) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));

        response = requestSpecificationWithAuthentication()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        assertTrue(response.getBody().jsonPath().get("liked"), "Post is not liked.");
        assertEquals(response.getBody().jsonPath().get("postId").toString(),postId, "Post is not liked.");
    }

    @Test(priority = 2)
    @Label("Jira - FPW-250")
    public void postUnliked_When_ValidRequestSent() {
        if (isNull(postId)) {
            likeExistingPost();
        }
        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));

        response = requestSpecificationWithAuthentication()
                .post(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
        assertEquals(response.getBody().jsonPath().get("postId").toString(),postId, "Post is not liked.");
    }
}



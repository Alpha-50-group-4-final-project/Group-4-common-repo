package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class LikeAndUnlikePostTest extends BaseTest {

    @Test(priority = 1)
    public void likeExistingPostTest() {
        if (postId == null) {
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
    public void unlikeExistingPostTest() {
        if (postId == null) {
            createPost();
            likeExistingPostTest();
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



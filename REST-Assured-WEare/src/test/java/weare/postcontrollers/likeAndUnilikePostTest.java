package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.CREATE_POST_BODY;
import static com.api.utils.RequestJSON.EDIT_POST_BODY;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class likeAndUnilikePostTest extends BaseTest {

    @Test(priority = 3)
    public void likeExistingPostTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));

        response = requestSpecificationWithAuthentication()
                .post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().jsonPath().get("liked"), "Post is not liked.");
    }

    @Test(priority = 4)
    public void unlikeExistingPostTest() {
        if (postId == null) {
            createPost();
            likeExistingPostTest();
        }
        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));

        response = requestSpecificationWithAuthentication()
                .post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
    }




}



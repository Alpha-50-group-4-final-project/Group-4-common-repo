package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.CREATE_POST;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.CREATE_POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CreatePostsTests extends BaseTest {


    @Test(priority = 1)
    public void createPostTest() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY, POST_CONTENT, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("postId"), "Post id is empty.");
        assertNotNull(response.getBody().jsonPath().get("rank"), "Rank is empty.");
        assertEquals(response.getBody().jsonPath().get("content"), POST_CONTENT,
                "Response post content is different than provided.");
        assertTrue(response.getBody().jsonPath().get("public").toString().equals(PUBLIC_CONTENT),
                "Response public/private field is different than provided.");

        postId = response.getBody().jsonPath().get("postId").toString();
        System.out.println("New post was successfully created.");
        //deletePost();
    }
    @Test(priority = 2)
    public void createPostTest_when_1001charsTextIsProvided() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY,POST_CONTENT_1001_CHARS, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
//        System.out.println(statusCode);
//        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, format("Incorrect status code. Expected %s.", HttpStatus.SC_BAD_REQUEST));
        assertEquals(response.getBody().jsonPath().get("message"), CONTENT_SIZE_ERROR,
                format("Response message is not correct. Expected: %s.", CONTENT_SIZE_ERROR));
        assertEquals(response.getBody().jsonPath().get("error"), BAD_REQUEST_ERROR,
                format("Error message is not correct.Expected: %s.", BAD_REQUEST_ERROR));

    }
}

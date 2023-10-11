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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreatePostsTests extends BaseTest {

    @Test(priority = 1)
    public void createPostTest() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY, POST_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("content"), POST_CONTENT,
                "Response post content is different than provided.");

        postId = response.getBody().jsonPath().get("postId").toString();
        System.out.println("New post was successfully created.");
    }
    @Test(priority = 1)
    public void createPostTest_when_1001charsTextIsProvided() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY,POST_CONTENT_1001_CHARS);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("message"), "Content size must be up to 1000 symbols",
                "Response message is not correct.");
        assertEquals(response.getBody().jsonPath().get("error"), "Bad Request",
                "Error message is not correct.");


    }
}

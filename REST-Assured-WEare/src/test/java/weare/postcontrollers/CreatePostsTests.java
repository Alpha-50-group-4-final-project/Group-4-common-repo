package weare.postcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.CREATE_POST;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.CREATE_POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CreatePostsTests extends BaseTest {


    @Test(priority = 1)
    @Label("Jira - FPW-247")
    public void postCreated_When_ValidDataProvided() {
        if (isNull(regularUserId)) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY, POST_CONTENT, NO_PICTURE, PUBLIC_CONTENT);
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

        System.out.printf("New post with id: %s was successfully created.", postId);
        deletePost();
    }
    @Test(priority = 2)
    @Label("Jira - FPW-266")
    public void postNotCreated_When_ContentProvidedIsTooLong() {
        if (isNull(regularUserId)) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY,POST_CONTENT_1001_CHARS, NO_PICTURE, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, format("Incorrect status code. Expected %s.", HttpStatus.SC_BAD_REQUEST));
        assertEquals(response.getBody().jsonPath().get("message"), CONTENT_SIZE_ERROR,
                format("Incorrect response message. Expected: %s.", CONTENT_SIZE_ERROR));
        assertEquals(response.getBody().jsonPath().get("error"), BAD_REQUEST_ERROR,
                format("Incorrect response error. Expected: %s.", BAD_REQUEST_ERROR));

    }
}

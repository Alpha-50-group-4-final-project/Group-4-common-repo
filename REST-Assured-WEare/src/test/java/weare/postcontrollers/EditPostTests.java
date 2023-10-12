package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.EDIT_POST;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.EDIT_POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditPostTests extends BaseTest {

    @Test(priority = 1)
    public void editExistingPostTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(EDIT_POST, postId));

        String requestBody = format(EDIT_POST_BODY, EDITED_POST_CONTENT, NO_PICTURE, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .put(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.",HttpStatus.SC_OK));
        assertEquals(response.body().asString(), "", "Response body isn't empty as expected.");

        System.out.printf("Post with id %s was successfully edited.", postId);
        deletePost();
    }
    @Test(priority = 2)
    public void editExistingPostError_when_1001charsTextIsProvided() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(EDIT_POST, postId));

        String requestBody = format(EDIT_POST_BODY, POST_CONTENT_1001_CHARS, NO_PICTURE, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .put(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST, format("Incorrect status code. Expected: %s.", HttpStatus.SC_BAD_REQUEST));
        assertEquals(response.getBody().jsonPath().get("message"), CONTENT_SIZE_ERROR,
                format("Incorrect response message. Expected: %s.", CONTENT_SIZE_ERROR));
        assertEquals(response.getBody().jsonPath().get("error"), BAD_REQUEST_ERROR,
                format("Incorrect response error. Expected: %s.", BAD_REQUEST_ERROR));

    }
}

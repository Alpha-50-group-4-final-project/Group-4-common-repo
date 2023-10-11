package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Constants.EDITED_POST_CONTENT;
import static com.api.utils.Endpoints.EDIT_POST;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.EDIT_POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditPostTests extends BaseTest {
    @Test(priority = 2)
    public void editExistingPostTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(EDIT_POST, postId));

        String requestBody = format(EDIT_POST_BODY, EDITED_POST_CONTENT, "No picki");
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .put(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
    }
}

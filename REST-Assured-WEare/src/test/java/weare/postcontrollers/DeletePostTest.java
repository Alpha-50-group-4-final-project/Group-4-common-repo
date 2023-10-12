package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.DELETE_POST;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.post;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class DeletePostTest extends BaseTest {
    @Test
    public void deletePostTest() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(DELETE_POST, postId));

        response = requestSpecificationWithAuthentication()
                .delete(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
        postId=null;
    }
}

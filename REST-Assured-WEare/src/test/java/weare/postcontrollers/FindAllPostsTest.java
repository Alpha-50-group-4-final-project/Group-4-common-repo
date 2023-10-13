package weare.postcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.GET_POSTS;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FindAllPostsTest extends BaseTest {
    @Test
    @Label("Jira - FPW-246")
    public static void allPostShown_When_FindALlPostRequestSent() {
        baseURI = format("%s%s", BASE_URL, GET_POSTS);

        response = getRequest(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        String resp = response.getBody().asString();
        if (resp.length() > 2) {
            assertNotNull(response.getBody().jsonPath().get("[0].postId"), "Post id is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].content"), "Post content is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].category"), "Category is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].picture"), "Picture is empty.");
            System.out.printf("List of all existing posts below: %s",response.getBody().asPrettyString() );
        }else {
            assertEquals(response.body().asString(), "[]", "Response body isn't empty.");
            System.out.println("No posts created yet.");
        }
    }
}

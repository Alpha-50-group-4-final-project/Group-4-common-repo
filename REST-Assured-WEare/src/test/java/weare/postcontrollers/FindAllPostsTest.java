package weare.postcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.GET_POSTS;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class FindAllPostsTest extends BaseTest {
    @Test(priority = 5)
    public static void findAllPostsTest() {
        baseURI = format("%s%s", BASE_URL, GET_POSTS);

        response = getRequest(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");

    }
}

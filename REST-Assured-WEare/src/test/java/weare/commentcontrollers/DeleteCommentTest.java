package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.DELETE_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class DeleteCommentTest extends BaseTest {
    @Test
    public void deleteCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                delete(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals("", response.body().asString(), "Response body isn't empty.");
        commentId=null;
    }
}

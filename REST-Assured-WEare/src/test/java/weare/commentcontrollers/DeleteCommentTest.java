package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.DELETE_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
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
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals("", response.body().asString(), "Response body isn't empty.");
        System.out.println("Comment deleted.");
        commentId=null;
    }
}

package weare.commentcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;

public class GetCommentsTests extends BaseTest {
    @Test
    public void getAllExistingCommentsTest() {
        if(commentId==null){
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, API_COMMENTS);

        response = requestSpecificationWithAuthentication().
                get(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("commentId").toString(),"Comment id is empty.");
        assertNotNull(response.getBody().jsonPath().get("content").toString(),"Comment content field is empty.");
        assertNotNull(response.getBody().jsonPath().get("likes").toString(),"Comment likes field is empty.");
        assertNotNull(response.getBody().jsonPath().get("date").toString(),"Comment date field is empty.");
        assertNotNull(response.getBody().jsonPath().get("liked").toString(),"Comment liked field is empty.");
        System.out.printf("All existing comments below: %s", response.getBody().asPrettyString());
    }

    @Test
    public void getAllCommentsByPostTest() {
        if (postId == null || commentId == null) {
            createPost();
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, GET_COMMENTS_BY_POST);

        response = requestSpecificationWithAuthentication().
                queryParam("postId", postId).
                get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("commentId").toString(),"Comment id is empty.");
        assertNotNull(response.getBody().jsonPath().get("content").toString(),"Comment content field is empty.");
        assertNotNull(response.getBody().jsonPath().get("likes").toString(),"Comment likes field is empty.");
        assertNotNull(response.getBody().jsonPath().get("date").toString(),"Comment date field is empty.");
        assertNotNull(response.getBody().jsonPath().get("liked").toString(),"Comment liked field is empty.");
        System.out.printf("All comments on post: %s below: %s", postId, response.getBody().asPrettyString());
    }
    @Test
    public void getAllCommentsByPostTest_when_postDoesNotHaveComments() {
            createPost();

        baseURI = format("%s%s", BASE_URL, GET_COMMENTS_BY_POST);

        response = requestSpecificationWithAuthentication().
                queryParam("postId", postId).
                get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getList("comments").size(), 0,
                "There is no comment content field in response body.");
        System.out.printf("No comments on post with id %s", postId);

    }
    @Test
    public void getOneCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, GET_ONE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertNotNull(response.getBody().jsonPath().get("commentId").toString(),"Comment id is empty.");
        assertNotNull(response.getBody().jsonPath().get("content").toString(),"Comment content field is empty.");
        assertNotNull(response.getBody().jsonPath().get("likes").toString(),"Comment likes field is empty.");
        assertNotNull(response.getBody().jsonPath().get("date").toString(),"Comment date field is empty.");
        assertNotNull(response.getBody().jsonPath().get("liked").toString(),"Comment liked field is empty.");
        System.out.printf("Content on comment with id: %s below: %s", commentId, response.getBody().asPrettyString());
    }
}

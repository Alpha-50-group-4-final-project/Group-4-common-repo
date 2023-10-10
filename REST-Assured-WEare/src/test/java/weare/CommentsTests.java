package weare;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.RequestJSON.COMMENT_BODY;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.*;

public class CommentsTests extends BaseTest {

    @Test(priority = 7)
    public void getAllExistingCommentsTest() {
        baseURI = format("%s%s", BASE_URL, API_COMMENTS);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                get(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }

    @Test(priority = 1)
    public void createCommentTest() {
        if (postId == null) {
         createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, regularUserId);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword)
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("content"), COMMENT_CONTENT,
                "Response comment is different than provided.");

        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("\nComment with id %s was created.\n", commentId);
    }

    @Test(priority = 2)
    public void editExistingCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword)
                .queryParam("commentId", commentId)
                .queryParam("content", EDITED_COMMENT_CONTENT)
                .put(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
    }

    @Test(priority = 3)
    public void likeACommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                queryParam("commentId", commentId).
                post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().jsonPath().get("liked"), "Post is not liked.");
    }

    @Test(priority = 4)
    public void unlikeACommentTest() {
        if (commentId == null) {
            createComment();
            likeACommentTest();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                queryParam("commentId", commentId).
                post(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
    }

    @Test(priority = 5)
    public void getAllCommentsByPostTest() {
        if (postId == null) {
            PostTest posts = new PostTest();
            posts.findAllPostsTest();
        }

        baseURI = format("%s%s", BASE_URL, GET_COMMENTS_BY_POST);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                queryParam("postId", postId).
                get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }

    @Test(priority = 6)
    public void getOneCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, GET_ONE_COMMENT);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                queryParam("commentId", commentId).
                get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }

    @Test(priority = 8)
    public void deleteCommentTest() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);

        response = requestSpecificationWithAuthentication(registeredUsername, registeredPassword).
                queryParam("commentId", commentId).
                delete(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals("", response.body().asString(), "Response body isn't empty.");
    }
}

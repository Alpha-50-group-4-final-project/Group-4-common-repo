package weare;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
    public void getAllExistingComments() {
        baseURI = format("%s%s", BASE_URL, API_COMMENTS);
        System.out.println(baseURI);

        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType(ContentType.JSON).
                when().
                get(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
    }

    @Test(priority = 1)
    public void createComment() {
        if (postId == null) {
            PostTest post = new PostTest();
            post.createPost();
        }

        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);
        System.out.println(baseURI);
        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, dumboUserID);

        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType(ContentType.JSON).
                when().
                body(requestBody).
                post(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("content"),
                COMMENT_CONTENT,
                "Response comment is different than provided.");

        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("\nComment with id %s was created.\n", commentId);
    }

    @Test(priority = 2)
    public void editExistingComment() {
        if (commentId == null) {
            createComment();
        }

        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);
        System.out.println(baseURI);

        Response response = given().cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD))
                .contentType(ContentType.JSON)
                .queryParam("commentId", commentId)
                .queryParam("content", EDITED_COMMENT_CONTENT)
                .when()
                .put(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(),
                "",
                "Response body isn't empty.");
    }

    @Test(priority = 3)
    public void likeAComment() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);
        System.out.println(baseURI);
        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD))
                .contentType(ContentType.JSON).
                queryParam("commentId", commentId).
                when().
                post(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().jsonPath().get("liked"),
                "Post is not liked.");
    }

    @Test(priority = 4)
    public void unlikeAComment() {
        if (commentId == null) {
            createComment();
            likeAComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);
        System.out.println(baseURI);
        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType(ContentType.JSON).
                queryParam("commentId", commentId)
                .when().
                post(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
        assertFalse(response.getBody().jsonPath().get("liked"),
                "Post is not unliked.");
    }

    @Test(priority = 5)
    public void getAllCommentsByPost() {
        if (postId == null) {
            PostTest posts = new PostTest();
            posts.findAllPostsTest();
        }

        baseURI = format("%s%s", BASE_URL, GET_COMMENTS_BY_POST);
        System.out.println(baseURI);
        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType(ContentType.JSON).
                queryParam("postId", postId).
                when().
                get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
    }

    @Test(priority = 6)
    public void getOneComment() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, GET_ONE_COMMENT);
        System.out.println(baseURI);
        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType("application/json").
                queryParam("commentId", commentId).
                when().
                get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
    }

    @Test(priority = 8)
    public void deleteComment() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);
        System.out.println(baseURI);
        Response response = given().
                cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD)).
                contentType(ContentType.JSON).
                queryParam("commentId", commentId).
                when().
                delete(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                HttpStatus.SC_OK,
                "Incorrect status code. Expected 200.");
        assertEquals("",
                response.body().asString(),
                "Response body isn't empty.");
    }
}

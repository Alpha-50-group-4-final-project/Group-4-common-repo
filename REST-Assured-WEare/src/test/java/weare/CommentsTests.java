package weare;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.RequestJSON.COMMENT_BODY;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;

public class CommentsTests extends BaseTest {

    @Test
    public void getAllExistingComments() {
        baseURI = format("%s%s", BASE_URL, API_COMMENTS);
        System.out.println(baseURI);

        Response response = given().cookies(cookies).contentType("application/son").when().get(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
    }

    @Test
    public void createComment() {
        if (postId == null) {
            PostTest post = new PostTest();
            post.createPost();
        }

        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);
        System.out.println(baseURI);
        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, dumboUserID);

        Response response = given().cookies(cookies).contentType("application/json").when().body(requestBody).post(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        Assertions.assertEquals(response.getBody().jsonPath().get("content"), COMMENT_CONTENT, "Response comment is different than provided.");


        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("\nComment with id %s was created.\n", commentId);
    }

    @Test
    public void editExistingComment() {
        if (commentId == null) {
            createComment();
        }

        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);
        System.out.println(baseURI);

        Response response = given().cookies(cookies).contentType("application/json").queryParam("commentId", commentId).queryParam("content", EDITED_COMMENT_CONTENT).when().put(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
    }

    @Test
    public void likeAComment() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);
        System.out.println(baseURI);
        Response response = given().cookies(cookies).contentType("application/json").queryParam("commentId", commentId).when().post(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(200, statusCode, "Incorrect status code. Expected 200.");
        Assertions.assertTrue(() -> response.getBody().jsonPath().get("liked"), "Comment hasn't been liked.");
    }

    @Test
    public void unlikeAComment() {
        if (commentId == null) {
            createComment();
            likeAComment();
        }
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);
        System.out.println(baseURI);
        Response response = given().cookies(cookies).contentType("application/json").queryParam("commentId", commentId).when().post(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(200, statusCode, "Incorrect status code. Expected 200.");
        Assertions.assertFalse(() -> response.getBody().jsonPath().get("liked"), "Comment hasn't been liked.");
    }

    @Test
    public void getAllCommentsByPost() {
        if (postId == null) {
            PostTest posts = new PostTest();
            posts.findAllPostsTest();
        }

        baseURI = format("%s%s", BASE_URL, GET_COMMENTS_BY_POST);
        System.out.println(baseURI);
        Response response = given().cookies(cookies).contentType("application/json").queryParam("postId", postId).when().get(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(200, statusCode, "Incorrect status code. Expected 200.");
    }

    @Test
    public void getOneComment() {
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, GET_ONE_COMMENT);
        System.out.println(baseURI);
        Response response = given().cookies(cookies).contentType("application/json").queryParam("commentId", commentId).when().get(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(200, statusCode, "Incorrect status code. Expected 200.");
    }
    @Test
    public void deleteComment(){
        if (commentId == null) {
            createComment();
        }
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);
        System.out.println(baseURI);
        Response response = given().cookies(cookies).contentType("application/json").queryParam("commentId", commentId).when().delete(baseURI);

        int statusCode = response.getStatusCode();

        System.out.println(response.getBody().asPrettyString());
        Assertions.assertEquals(200, statusCode, "Incorrect status code. Expected 200.");
        Assertions.assertEquals("", response.body().asString(), "Response body isn't empty.");
    }
}
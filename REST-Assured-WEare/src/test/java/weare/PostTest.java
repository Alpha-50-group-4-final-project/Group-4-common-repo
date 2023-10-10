package weare;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.CREATE_POST_BODY;
import static com.api.utils.RequestJSON.EDIT_POST_BODY;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class PostTest extends BaseTest {


    @Test(priority = 1)
    public void createPost() {
        if (regularUserId == null) {
            RegisterUserTest reg = new RegisterUserTest();
            reg.registerNewUserTest();
        }

        baseURI = format("%s%s", BASE_URL, CREATE_POST);
        System.out.println(baseURI);
        String requestBody = format(CREATE_POST_BODY, POST_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD).body(requestBody)
                .post();

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("content"),
                POST_CONTENT,
                "Response post content is different than provided.");
        postId = response.getBody().jsonPath().get("postId").toString();
        System.out.println(postId);
        System.out.println("New post was successfully created.");
    }

    @Test(priority = 5)
    public void findAllPostsTest() {
        baseURI = format("%s%s", BASE_URL, GET_POSTS);
        System.out.println(baseURI);

        response = getRequest(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        if (postId == null) {
            postId = response.getBody().jsonPath().get("postId[0]").toString();
        }
    }

    @Test(priority = 2)
    public void editExistingPostTest() {
        if (postId == null) {
            createPost();
        }

        System.out.println(postId);
        baseURI = format("%s%s", BASE_URL, format(EDIT_POST, postId));
        System.out.println(baseURI);

        String requestBody = format(EDIT_POST_BODY, EDITED_POST_CONTENT, "No picki");
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        System.out.println(EDITED_POST_CONTENT);
        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD).body(requestBody)
                .put(baseURI);

        System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(),
                "",
                "Response body isn't empty.");

    }

    @Test(priority = 3)
    public void likeExistingPost() {

        if (postId == null) {
            createPost();
        }

        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));
        System.out.println(baseURI);

        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD)
                .post(baseURI);
        System.out.println(response.asPrettyString());
        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().jsonPath().get("liked"),
                "Post is not liked.");
    }

    @Test(priority = 4)
    public void unlikeExistingPost() {

        if (postId == null) {
            createPost();
            likeExistingPost();
        }

        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD).put(baseURI);
        System.out.println(response.asPrettyString());
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        assertFalse(response.getBody().jsonPath().get("liked"), "Post is not unliked.");
    }

    @Test(priority = 4)
    public void showCommentsOnPost() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s", BASE_URL, format(SHOW_COMMENTS, postId));
        System.out.println(baseURI);
        response = getRequest(baseURI);
        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
    }

    @Test(priority = 6)
    public void deletePost() {
        if (postId == null) {
            createPost();
        }

        baseURI = format("%s%s", BASE_URL, format(DELETE_POST, postId));
        System.out.println(baseURI);
        response = postRequestSpecificationWithAuthentication(EXISTING_USER, EXISTING_USER_PASSWORD).delete(baseURI);
        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        assertEquals(response.body().asString(), "", "Response body isn't empty.");
    }
}



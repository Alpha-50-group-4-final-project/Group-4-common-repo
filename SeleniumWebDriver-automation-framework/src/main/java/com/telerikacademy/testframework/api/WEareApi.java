package com.telerikacademy.testframework.api;


import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.telerikacademy.testframework.api.Constants.*;
import static com.telerikacademy.testframework.api.EndPoints.*;
import static com.telerikacademy.testframework.api.JsonBodies.*;
import static weare.database.manipulation.BaseSetup.freshUsernames;
import static weare.database.manipulation.BaseSetup.freshUsersIds;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class WEareApi {
    private Cookies cookies;
    public static String USER_ID;

    protected RequestSpecification requestSpecificationWithoutAuthentication() {
        Gson deserializer = new Gson();
        return given().
                contentType(ContentType.JSON).
                when().log().all();
    }

    private Cookies getAuthCookie(String username, String password) {
        return cookies = given().queryParam("username", username).
                queryParam("password", password).
                when().
                post(authenticate).
                then().statusCode(302).
                extract().response().
                getDetailedCookies();
    }

    public void registerUser(String username, String password) {
        String baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        String requestBody = (format(REGISTER_USER_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                password,
                EMAIL,
                password,
                username));
        Response response = requestSpecificationWithoutAuthentication().body(requestBody).post(baseURI);
        String[] responseBody = response.asString().split(" ");
        USER_ID = responseBody[6];
        freshUsersIds.add(Integer.parseInt(USER_ID));
        freshUsernames.add(responseBody[3]);
    }

    public Response updateUserProfile(String username, String password, String firstName, String lastName) {
        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, USER_ID)));

        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY,
                TODAY_DATE,
                firstName,
                USER_ID,
                lastName,
                CITY_ID,
                PERSONAL_REVIEW);

        return requestSpecificationWithoutAuthentication().cookies(getAuthCookie(username, password)).body(requestBody).post(baseURI);
    }

    public PostModel createPost(String username, String password, String postContent) {
        String baseURI = format("%s%s", BASE_URL, CREATE_POST);
        String requestBody = format(getCreatePostBody(), postContent, NO_PICTURE, PUBLIC_CONTENT);
        return requestSpecificationWithoutAuthentication()
                .cookies(getAuthCookie(username, password))
                .body(requestBody)
                .post(baseURI)
                .as(PostModel.class);
    }

    public CommentModel createComment(String username, String password, String commentText, String postId) {
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, commentText, postId, USER_ID);

        return requestSpecificationWithoutAuthentication().cookies(getAuthCookie(username, password))
                .body(requestBody)
                .post(baseURI)
                .as(CommentModel.class);
    }

    public Response deletePost(String username, String password, String postId) {
        baseURI = format("%s%s", BASE_URL, format(DELETE_POST, postId));
        return requestSpecificationWithoutAuthentication()
                .cookies(getAuthCookie(username, password))
                .delete(baseURI);
    }

    public Response likePost(String username, String password, String postId) {
        baseURI = format("%s%s", BASE_URL, format(LIKE_POST, postId));
        return requestSpecificationWithoutAuthentication()
                .cookies(getAuthCookie(username, password))
                .post(baseURI);
    }

    public Response deleteComment(String username, String password, int commentId) {
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);
        return requestSpecificationWithoutAuthentication()
                .cookies(getAuthCookie(username, password)).
                queryParam("commentId", commentId).
                delete(baseURI);
    }

    public Response sendConnectionRequest(String username, String password,String recipientId,String recipientName) {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);

        String requestBody = format(SEND_CONNECTION_REQ_BODY, recipientId, recipientName);

        return requestSpecificationWithoutAuthentication()
                .cookies(getAuthCookie(username, password))
                .body(requestBody)
                .post(baseURI);
    }
}

package base;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static weare.database.manipulation.BaseSetup.freshUsernames;
import static weare.database.manipulation.BaseSetup.freshUsersIds;
import static weare.database.manipulation.UserManipulation.DeleteCurrentUserById.deleteUserById;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.testng.Assert.assertTrue;

public class BaseTest {
    protected static String userId;
    protected static String adminUserId;
    protected static String expertiseProfileId;
    protected static String postId;
    protected static String commentId;
    protected static Cookies cookies;
    protected static String skillId;
    protected static String connectionId;
    protected static String registeredUsername;

    protected static Response response;
    protected static String expertiseUpdateUsername;
    protected static DateTimeFormatter dtf;
    protected boolean isConnectionSend = false;
    protected static String userReceivingRequestId;
    protected static String userReceivingRequestName;

    @AfterClass
    public  static void deleteUser()  {
        deleteUserById();
        userId=null;
        commentId=null;
        postId=null;
        expertiseProfileId=null;
        System.gc();
    }

    protected String timeStamp() {
        String timestamp;
        dtf = DateTimeFormatter.ISO_INSTANT;
        Instant time = Instant.now();
        timestamp = dtf.format(time);
        return timestamp;
    }

    protected Cookies getAuthCookie(String username, String password) {
        return cookies = given().queryParam("username", username).
                queryParam("password", password).
                when().
                post(authenticate).
                then().statusCode(SC_MOVED_TEMPORARILY).
                extract().response().
                getDetailedCookies();
    }

    protected RequestSpecification requestSpecificationWithoutAuthentication() {
        return given().
                contentType(ContentType.JSON).
                when().log().all();
    }

    protected  RequestSpecification requestSpecificationWithAuthentication() {
        return given().
                cookies(getAuthCookie(registeredUsername, PASSWORD)).
                contentType(ContentType.JSON).
                when().log().all();
    }

    protected void registerNewUser() {
        USERNAME = generateUsername();

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        String requestBody = (format(REGISTER_USER_BODY, ROLE_USER,
                CATEGORY_ID, CATEGORY_NAME,
                PASSWORD, EMAIL, PASSWORD,
                USERNAME));

        response = requestSpecificationWithoutAuthentication().body(requestBody)
                .post();

        String[] responseBody = response.asString().split(" ");
        userId = responseBody[6];
        registeredUsername = responseBody[3];

        freshUsernames.add(registeredUsername);
        freshUsersIds.add(Integer.parseInt(userId));
    }

    protected void registerAnotherUser() {
        USERNAME = generateUsername();

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_USER_BODY, ROLE_USER,
                CATEGORY_ID, CATEGORY_NAME,
                PASSWORD, EMAIL, PASSWORD,
                USERNAME));
        response = requestSpecificationWithoutAuthentication().body(requestBody)
                .post();

        String[] responseBody = response.asString().split(" ");
        userReceivingRequestId = responseBody[6];
        userReceivingRequestName = responseBody[3];

        freshUsernames.add(userReceivingRequestName);
        freshUsersIds.add(Integer.parseInt(userReceivingRequestId));

    }

    protected void createPost() {
        if (userId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY, POST_CONTENT, NO_PICTURE, PUBLIC_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        postId = response.getBody().jsonPath().get("postId").toString();
        System.out.printf("New post with id: %s was successfully created.", postId);
    }

    protected void createComment() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId, userId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        commentId = response.getBody().jsonPath().get("commentId").toString();
        System.out.printf("New comment with id: %s was successfully created.", commentId);
    }

    protected void createSkill() {
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, format(SKILL + timeStamp()), SKILL_ID);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        skillId = (response.path("skillId").toString());
        System.out.printf("New skill with id: %s was successfully created.", skillId);
    }

    protected void getLastUser() {
        baseURI = format("%s%s", BASE_URL, GET_REGISTER_USERS);

        String requestBody = (format(GET_ALL_REGISTER_USERS_BODY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        expertiseProfileId = response.getBody().jsonPath().get("expertiseProfile[0].id").toString();
        expertiseUpdateUsername = response.getBody().jsonPath().getString("username[0]");
    }

    protected void deletePost(String postIds) {
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        response = requestSpecificationWithAuthentication()
                .queryParam("postId",postIds)
                .delete(baseURI);

        postId = null;
    }

    protected void deleteComment(String commentIds) {
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentIds).
                delete(baseURI);

        commentId = null;
    }

    protected void deleteSkill() {
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_DELETE);

        response = requestSpecificationWithAuthentication()
                .queryParam("skillId", intSkillId)
                .put(baseURI);
        skillId = null;
    }

    protected void sendConnectionRequest() {
        registerNewUser();
        registerAnotherUser();

        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);
        String requestBody = format(SEND_CONNECTION_REQ_BODY, userReceivingRequestId, userReceivingRequestName);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();
        isConnectionSend = true;
        System.out.println("Connection request was successfully sent.");
    }

    protected void getConnectionRequest() {
        sendConnectionRequest();
        baseURI = format("%s%s", BASE_URL, format(GET_REQUEST, userReceivingRequestId));
        response = given()
                .cookies(getAuthCookie(userReceivingRequestName, PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI);
        connectionId = response.getBody().jsonPath().getString("[0].id");
        System.out.printf("Connection request with id: %s received.\n", connectionId);
    }

    protected void likeComment() {
        createComment();
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        response = requestSpecificationWithAuthentication().
                queryParam("commentId", commentId).
                post(baseURI);
    }

    protected void likeExistingPost() {
        createPost();
        baseURI = format("%s%s", BASE_URL,LIKE_POST);

        response = requestSpecificationWithAuthentication()
                .queryParam("postId", postId)
                .post(baseURI);
    }
}


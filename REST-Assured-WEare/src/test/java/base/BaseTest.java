package base;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

public class BaseTest {
    public static String regularUserId;
    public static String adminUserId;
    public static String expertiseProfileId;
    public static String postId;
    public static String commentId;
    public static Cookies cookies;
    public static List<String> usernames = new ArrayList<>();
    public static String skillId;
    public static String connectionId;

    public static String registeredUsername;

    public static Response response;

    public static DateTimeFormatter dtf;
    public boolean isConnectionSend=false;


    public String timeStamp() {
        String timestamp;
        dtf = DateTimeFormatter.ISO_INSTANT;
        Instant time = Instant.now();
        timestamp = dtf.format(time);
        return timestamp;
    }


    public Cookies getAuthCookie(String username, String password) {
        return cookies = given().queryParam("username", username).
                queryParam("password", password).
                when().
                post(authenticate).
                then().statusCode(302).
                extract().response().
                getDetailedCookies();
    }


    protected RequestSpecification requestSpecificationWithoutAuthentication() {
        return given().
                contentType(ContentType.JSON).
                when().log().all();
    }

    protected RequestSpecification requestSpecificationWithAuthentication() {
        return given().
                cookies(getAuthCookie(registeredUsername, PASSWORD)).
                contentType(ContentType.JSON).
                when().log().all();
    }

    public Response getRequest(String URL) {
        return response = given().contentType(ContentType.JSON).get(URL);
    }

    public void registerNewUser() {
        USERNAME = faker.name().firstName();
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        String requestBody = (format(REGISTER_USER_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                USERNAME));
        response = requestSpecificationWithoutAuthentication().body(requestBody)
                .post();
        System.out.println(response.getBody().asPrettyString());
        String[] responseBody = response.asString().split(" ");

        regularUserId = responseBody[6];
        registeredUsername = responseBody[3];
        usernames.add(registeredUsername);
    }

    public void createPost() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", BASE_URL, CREATE_POST);

        String requestBody = format(CREATE_POST_BODY, POST_CONTENT);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);
        System.out.println(response.getBody().asPrettyString());

        postId = response.getBody().jsonPath().get("postId").toString();
        System.out.println("New post was successfully created.");
    }

    public void createComment() {
        if (postId == null) {
            createPost();
        }
        baseURI = format("%s%s%s", BASE_URL, API_COMMENTS, CREATE_COMMENTS);

        String requestBody = format(COMMENT_BODY, COMMENT_CONTENT, postId,regularUserId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        commentId = response.getBody().jsonPath().get("commentId").toString();
    }

    public void createSkill() {
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);
        skillId = (response.path("skillId").toString());
    }

}

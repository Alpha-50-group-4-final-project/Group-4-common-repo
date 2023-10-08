package weare;

import com.api.utils.Constants;
import io.restassured.RestAssured;


import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class RegisterUserTest extends BaseTest {


    public static String registeredUsername;
    public static String registeredPassword;

    @Test(priority = 1)

    public void registerNewUserTest() {

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_USER_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post();

        response.print();
        String responseBodyText = response.asString();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");


        assertTrue(responseBodyText.matches("User with name .* and id \\d+ was created"));

        registeredUsername = Constants.USERNAME;
        registeredPassword = Constants.PASSWORD;

        System.out.println("Registered username: " + registeredUsername);
        System.out.println("Registered password: " + registeredPassword);

        String[] responseBody = response.asString().split(" ");
        regularUserId = responseBody[6];
        expertiseProfileId = regularUserId;
        System.out.println(regularUserId);
        usernames.add(registeredUsername);

    }

    @Test(priority = 3)
    public void getAllRegisterUsersTest() {

        baseURI = format("%s%s", BASE_URL, GET_REGISTER_USERS);
        System.out.println(baseURI);

        String requestBody = (format(GET_ALL_REGISTER_USERS_BODY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post();

//        response.print();

        String userId = String.valueOf(response.getBody().jsonPath().getInt("userId[0]"));
        System.out.println(userId);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");

        System.out.println(response.getBody().jsonPath().prettify());

        expertiseProfileId = response.getBody().jsonPath().get("expertiseProfile[0].id").toString();
        System.out.println(expertiseProfileId);

    }

    @Test(priority = 2)
    public void registerAdminUser() {

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_ADMIN_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                ADMIN_USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertTrue(response.asString().contains(ADMIN_USERNAME),
                "Admin is registered with different username");
        assertTrue(response.asString().contains("id"),
                "Newly registered admin doesn't have ID");
        String[] responseBody = response.asString().split(" ");
        adminUserId = responseBody[6];
        System.out.println(adminUserId);
        System.out.printf("Admin user %s was registered successfully.%n", ADMIN_USERNAME);
    }

    @Test(priority = 4)
    public void upgradePersonalProfile() {
        if (regularUserId == null) {
            registerNewUserTest();
        }


        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY,
                TODAY_DATE,
                FIRSTNAME,
                regularUserId,
                LAST_NAME,
                CITY_ID,
                PERSONAL_REVIEW);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = given()
                .cookies(getAuthCookie(registeredUsername, registeredPassword))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("firstName"),
                FIRSTNAME,
                "Provided first name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("lastName"),
                LAST_NAME,
                "Provided last name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("personalReview"),
                PERSONAL_REVIEW,
                "Provided personal review is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("id").toString(),
                regularUserId,
                "User id is not correct.");
        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }

    @Test(priority = 5)
    public void upgradeExpertiseProfileTest() {
        if (expertiseProfileId == null) {
            registerNewUserTest();
        }

        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_EXPERTISE_PROFILE, expertiseProfileId)));
        System.out.println(baseURI);

        String requestBody = format(UPGRADE_EXPERTISE_PROFILE_BODY,
                AVAILABILITY,
                CATEGORY_ID,
                CATEGORY_NAME,
                expertiseProfileId,
                faker.lorem().word(),
                faker.lorem().word(),
                faker.lorem().word(),
                faker.lorem().word(),
                faker.lorem().word(),
                faker.lorem().word()
        );
        System.out.println(requestBody);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = given()
                .cookies(getAuthCookie(registeredUsername, registeredPassword))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
    }

    @Test(priority = 6)
    public void showUserPostsByUserID() {
        if (regularUserId == null) {
            registerNewUserTest();
        }


        baseURI = (format("%s%s", BASE_URL, format(SHOW_USER_POSTS_BY_ID, regularUserId)));
        System.out.println(baseURI);
        Response response = given()
                .cookies(getAuthCookie(registeredUsername, registeredPassword))
                .contentType(ContentType.JSON)
                .body(SHOW_USER_BY_ID_BODY)
                .when()
                .get(baseURI);
        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
    }

    @Test(priority = 7)
    public void getUserById() {
        if (regularUserId == null) {
            registerNewUserTest();
        }
        baseURI = (format("%s%s", BASE_URL, format(GET_USER_BY_ID, regularUserId)));
        System.out.println(baseURI);
        System.out.println(registeredUsername.toLowerCase());
        Response response = given()
                .cookies(getAuthCookie(registeredUsername, registeredPassword))
                .contentType(ContentType.JSON)
                .queryParam("principal", registeredUsername)
                .when()
                .get(baseURI);
        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();

        assertEquals(statusCode,
                200,
                "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("id").toString(),
                regularUserId,
                "User id is not correct.");
        assertEquals(response.getBody().jsonPath().get("username"),
                registeredUsername,
                "Username is not correct.");
    }
}

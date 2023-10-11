package weare.usercotrollers;

import base.BaseTest;


import io.restassured.response.Response;

import org.apache.http.HttpStatus;
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

String expertiseUpdateUsername;


    @Test(priority = 1)

    public void registerNewUserTest() {
        USERNAME = letsTryIt();

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

         response= requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        String responseBodyText = response.asString();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");

        assertTrue(responseBodyText.matches("User with name .* and id \\d+ was created"));

        String[] responseBody = response.asString().split(" ");

        regularUserId = responseBody[6];
        registeredUsername=responseBody[3];
        usernames.add(registeredUsername);

    }

    @Test(priority = 3)
    public void getAllRegisterUsersTest() {
        baseURI = format("%s%s", BASE_URL, GET_REGISTER_USERS);

        String requestBody = (format(GET_ALL_REGISTER_USERS_BODY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        System.out.println(response.getBody().asPrettyString());

        expertiseProfileId = response.getBody().jsonPath().get("expertiseProfile[0].id").toString();
        expertiseUpdateUsername=response.getBody().jsonPath().getString("username[0]");

    }

    @Test(priority = 2)
    public void registerAdminUser() {
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);

        String requestBody = (format(REGISTER_ADMIN_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                ADMIN_USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = requestSpecificationWithoutAuthentication()
                .body(requestBody)
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, format("Incorrect status code. Expected %s.", HttpStatus.SC_OK));
        assertTrue(response.asString().contains(ADMIN_USERNAME), "Admin is registered with different username");
        assertTrue(response.asString().contains("id"), "Newly registered admin doesn't have ID");

        String[] responseBody = response.asString().split(" ");
        adminUserId = responseBody[6];

        System.out.printf("Admin user %s was registered successfully.%n", ADMIN_USERNAME);
    }

    @Test(priority = 4)
    public void upgradePersonalProfile() {
        if (regularUserId == null) {
            registerNewUser();
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

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("firstName"), FIRSTNAME,
                "Provided first name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("lastName"), LAST_NAME,
                "Provided last name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("personalReview"), PERSONAL_REVIEW,
                "Provided personal review is not equal to response.");

        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }

    @Test(priority = 5)
    public void upgradeExpertiseProfileTest() {
        if (expertiseProfileId == null) {
            registerNewUser();
            getAllRegisterUsersTest();
        }
        String firstRowSkill=faker.lorem().word()+timeStamp();
        String secondRowKill=faker.lorem().word()+timeStamp();
        String skill=faker.lorem().word()+timeStamp();

        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_EXPERTISE_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_EXPERTISE_PROFILE_BODY,
                AVAILABILITY,
                CATEGORY_ID,
                CATEGORY_NAME,
                expertiseProfileId,
                firstRowSkill,
                secondRowKill,
                skill
        );
        System.out.println(requestBody);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(expertiseUpdateUsername);
        System.out.println(expertiseProfileId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().asString().contains(firstRowSkill));
        assertTrue(response.getBody().asString().contains(secondRowKill));
        assertTrue(response.getBody().asString().contains(skill));
    }

    @Test(priority = 4)
    public void upgradePersonalProfile_When_Invalid_SElfDescriptionProvided() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY,
                TODAY_DATE,
                FIRSTNAME,
                regularUserId,
                LAST_NAME,
                CITY_ID,
                PERSONAL_REVIEW_INVALID);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        System.out.println(response.getBody().asPrettyString());

//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        assertEquals(response.getBody().jsonPath().get("firstName"), FIRSTNAME,
//                "Provided first name is not equal to response.");
//        assertEquals(response.getBody().jsonPath().get("lastName"), LAST_NAME,
//                "Provided last name is not equal to response.");
//        assertEquals(response.getBody().jsonPath().get("personalReview"), PERSONAL_REVIEW,
//                "Provided personal review is not equal to response.");
//
//        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }



    @Test(priority = 6)
    public void showUserPostsByUserID() {
        if (regularUserId == null || postId==null) {
            registerNewUser();
            createPost();
        }
        baseURI = (format("%s%s", BASE_URL, format(SHOW_USER_POSTS_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .body(SHOW_USER_BY_ID_BODY)
                .get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
    }

    @Test(priority = 7)
    public void getUserById() {
        if (regularUserId == null) {
            registerNewUser();
        }
        baseURI = (format("%s%s", BASE_URL, format(GET_USER_BY_ID, regularUserId)));

        response = requestSpecificationWithAuthentication()
                .queryParam("principal", registeredUsername)
                .get(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("id").toString(), regularUserId,
                "User id is not correct.");
        assertEquals(response.getBody().jsonPath().get("username"), registeredUsername,
                "Username is not correct.");
    }
}

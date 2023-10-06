package weare;

import com.api.utils.Constants;
import io.restassured.RestAssured;


import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


@TestMethodOrder(OrderAnnotation.class)
public class RegisterUserTest extends BaseTest {


    public static String registeredUsername;
    public static String registeredPassword;

    @Test
    @Order(1)
    public void registerNewUserTest() {

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_USER_BODY, PASSWORD, EMAIL, PASSWORD, USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post();

        response.print();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");

        String responseBodyText = response.asString();
        assertTrue(responseBodyText.matches("User with name .* and id \\d+ was created"));

        registeredUsername = Constants.USERNAME;
        registeredPassword = Constants.PASSWORD;

        System.out.println("Registered username: " + registeredUsername);
        System.out.println("Registered password: " + registeredPassword);

        String[] responseBody = response.asString().split(" ");
        regularUserId = responseBody[6];
        expertiseProfileId=regularUserId;
        System.out.println(regularUserId);
        usernames.add(registeredUsername);

    }

    @Test
    @Order(2)
    public void getAllRegisterUsersTest() {

        baseURI = format("%s%s", BASE_URL, GET_REGISTER_USERS);
        System.out.println(baseURI);

        String requestBody = (format(GET_ALL_REGISTER_USERS_BODY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = RestAssured.given()
                .contentType("application/json")
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

    @Test
    public void registerAdminUser() {

        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        System.out.println(baseURI);

        String requestBody = (format(REGISTER_ADMIN_BODY, CATEGORY_ID, CATEGORY_NAME, PASSWORD, EMAIL, PASSWORD, ADMIN_USERNAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        Assertions.assertTrue(response.asString().contains(ADMIN_USERNAME), "Admin is registered with different username");
        Assertions.assertTrue(response.asString().contains("id"), "Newly registered admin doesn't have ID");
        String[] responseBody = response.asString().split(" ");
        adminUserId = responseBody[6];
        System.out.println(adminUserId);
        System.out.printf("Admin user %s was registered successfully.%n", ADMIN_USERNAME);
    }

    @Test
    public void upgradePersonalProfile() {
        if (regularUserId == null) {
            registerNewUserTest();
        }
        Cookies cookie = given().queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .when().post(authenticate).then().statusCode(302).extract().response().getDetailedCookies();

        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY, TODAY_DATE, FIRSTNAME, regularUserId, LAST_NAME, CITY_ID, PERSONAL_REVIEW);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = given()
                .cookies(cookie)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURI);

        response.print();

        int statusCode = response.getStatusCode();

        Assertions.assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");
        Assertions.assertEquals(response.getBody().jsonPath().get("firstName"), FIRSTNAME, "Provided first name is not equal to response.");
        Assertions.assertEquals(response.getBody().jsonPath().get("lastName"), LAST_NAME, "Provided last name is not equal to response.");
        Assertions.assertEquals(response.getBody().jsonPath().get("personalReview"), PERSONAL_REVIEW, "Provided personal review is not equal to response.");
        Assertions.assertEquals(response.getBody().jsonPath().get("id").toString(), regularUserId, "User id is not correct.");
        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }

    @Test
    public void upgradeExpertiseProfileTest() {
        if (expertiseProfileId == null) {
            registerNewUserTest();
        }
        Cookies cookie = given().queryParam("username",registeredUsername)
                .queryParam("password", registeredPassword)
                .when().post(authenticate).then().statusCode(302).extract().response().getDetailedCookies();

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
                .cookies(cookie)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();

        Assertions.assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");

    }


}

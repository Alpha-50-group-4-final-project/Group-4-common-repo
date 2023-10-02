package weare;

import com.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.GET_REGISTER_USERS;
import static com.api.utils.Endpoints.REGISTER_USER;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.GET_ALL_REGISTER_USERS_BODY;
import static com.api.utils.RequestJSON.REGISTER_USER_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;



@TestMethodOrder(OrderAnnotation.class)
public class RegisterUserTest {


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

        String responseBody = response.asString();
        assertTrue(responseBody.matches("User with name .* and id \\d+ was created"));

        registeredUsername = Constants.USERNAME;
        registeredPassword = Constants.PASSWORD;

        System.out.println("Registered username: " + registeredUsername);
        System.out.println("Registered password: " + registeredPassword);

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

        response.print();

        String userId = String.valueOf(response.getBody().jsonPath().getInt("userId[0]"));
        System.out.println(userId);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code. Expected 200.");

        System.out.println(response.getBody().jsonPath().prettify());

    }
}

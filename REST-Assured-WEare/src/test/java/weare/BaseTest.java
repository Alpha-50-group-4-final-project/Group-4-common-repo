package weare;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.LOGIN_USER;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BaseTest {

    public static String regularUserId;
    public static String adminUserId;

    @BeforeAll
    public static void takeCookiesTests() {
        baseURI = format("%s%s", BASE_URL, LOGIN_USER);

        RestAssured.given()
                .when()
                .post(baseURI)
                .then()
                .statusCode(302)
                .extract()
                .response()
                .getDetailedCookies();

    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .param("username", "Dumbo")
                .param("password", "12345678").when()
                .log().all();
    }
}
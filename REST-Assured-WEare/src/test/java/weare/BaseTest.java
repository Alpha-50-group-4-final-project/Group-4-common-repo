package weare;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.LOGIN_USER;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class BaseTest {

    @BeforeEach
    public void takeCookiesTests() {
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
}

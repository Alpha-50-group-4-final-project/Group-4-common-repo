package weare.login;

import base.BaseTest;
import com.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Label;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.apache.http.HttpStatus.*;
import static com.api.utils.Constants.PASSWORD;
import static com.api.utils.Endpoints.LOGIN_USER;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class LoginUserTest extends BaseTest {
    @Test
    @Label("Jira - FPW-23")
    public void userLoggedIn_When_ValidDataProvided() {
        if (registeredUsername == null) {
            registerNewUser();
        }
        baseURI = format("%s%s", Constants.BASE_URL, LOGIN_USER);

        System.out.println("Using username: " + registeredUsername);
        System.out.println("Using password: " + PASSWORD);

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(registeredUsername, PASSWORD)
                .header("Accept", "application/json")
                .log().all()
                .post(baseURI);

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),
                SC_MOVED_TEMPORARILY, format("Incorrect status code. Expected %s.", SC_MOVED_TEMPORARILY));
    }
}





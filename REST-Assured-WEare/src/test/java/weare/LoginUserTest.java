package weare;

import base.BaseTest;
import com.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.utils.Constants.PASSWORD;
import static com.api.utils.Endpoints.LOGIN_USER;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class LoginUserTest extends BaseTest {


    @Test
    public void loginTest() {
        if (registeredUsername == null) {
            RegisterUserTest registerUserTest = new RegisterUserTest();
            registerUserTest.registerNewUserTest();
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
        Assert.assertEquals(response.getStatusCode(), 302, "Incorrect status code. Expected 302.");

    }
}





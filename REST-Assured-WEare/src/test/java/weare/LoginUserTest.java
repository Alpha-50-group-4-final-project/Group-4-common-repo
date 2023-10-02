package weare;

import com.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.junit.jupiter.api.Test;
import static com.api.utils.Endpoints.LOGIN_USER;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class LoginUserTest extends BaseTest {

    RegisterUserTest registerUserTest = new RegisterUserTest();


    @Test
    public void loginTest() {

        RegisterUserTest registerUserTest = new RegisterUserTest();
        registerUserTest.registerNewUserTest();

        baseURI = format("%s%s", Constants.BASE_URL, LOGIN_USER);

        System.out.println("Using username: " + RegisterUserTest.registeredUsername);
        System.out.println("Using password: " + RegisterUserTest.registeredPassword);


        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(RegisterUserTest.registeredUsername, RegisterUserTest.registeredPassword)
                .header("Accept", "application/json")
                .log().all()
                .post(baseURI);

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 302, "Incorrect status code. Expected 302.");

    }
}





package weare;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.CREATE_POST;
import static com.api.utils.Endpoints.authenticate;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.CREATE_POST_BODY;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;


public class PostTest extends BaseTest{


    @Test
    public void createPost(){



        Cookies cookiesJar=given().queryParam("username","Dumbo")
                .queryParam("password","12345678")
                .when().post(authenticate).then().statusCode(302).extract().response().getDetailedCookies();
        System.out.println(cookiesJar);


        baseURI=format("%s%s",BASE_URL,CREATE_POST);
        System.out.println(baseURI);
        String requestBody=format(CREATE_POST_BODY,"SHAKA LQKA MAKA");
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(requestBody);

        Response response = given()
                .cookies(cookiesJar)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURI);

        response.print();

    }

}

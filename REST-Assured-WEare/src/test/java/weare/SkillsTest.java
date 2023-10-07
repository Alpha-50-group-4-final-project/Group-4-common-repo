package weare;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.RequestJSON.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class SkillsTest extends BaseTest {
    String skillId;


    @Test
    public void getAllExistingSkills() {
        baseURI = format("%s%s", BASE_URL, SKILLS_FIND_ALL);

        Response response = given()
                .cookies(cookies)
                .contentType("application/json")
                .when()
                .get(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        Assert.assertNotNull(response.path("skill"), "Incorrect information returned");
    }

    @Test
    public void createSkill() {
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);
        //String requestBody = format(SKILL_BODY_OLD, SKILL, SKILL_ID);

        Response response = given()
                .cookies(cookies)
                .contentType("application/json")
                .when()
                .body(requestBody)
                .post(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("skill"), SKILL, "Response  is different than provided.");

        skillId = (response.path("skillId").toString());
        System.out.printf("\nSkill with id %s was created.\n", skillId);

    }

    @Test
    public void editSkill() {
        if (skillId == null) {
            createSkill();
        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_EDIT);

        Response response = given()
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("skill", EDITED_SKILL)
                .queryParam("skillId", intSkillId)
                .when()
                .put(baseURI);

        //System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("skill"), EDITED_SKILL, "Response  is different than provided.");
        System.out.printf("\nSkill with id %s was edited.\n", skillId);
    }

//    @Test
//    public void getSkillById(){
//        //the createSkill does not save the created skill ID WHYYYY
//        int intSkillId = Integer.parseInt(skillId);
//        baseURI = format("%s%s", BASE_URL, SKILLS_GET_ONE);
//
//        Response response = given()
//                .cookies(cookies)
//                .contentType("application/json")
//                .queryParam("skillId", intSkillId)
//                .when()
//                .get(baseURI);
//
//        System.out.println(response.getBody().asPrettyString());
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        assertEquals(response.getBody().jsonPath().get("skill"), EDITED_SKILL, "Response  is different than provided.");
//        System.out.printf("\nSkill with id %s was edited.\n", skillId);
//    }

    @Test
    public void deleteSkill() {
//        if (skillId == null) {
//            createSkill();
//        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_DELETE);

        Response response = given()
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("skillId", intSkillId)
                .when()
                .put(baseURI);

        System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals("", response.body().asString(), "Response body isn't empty.");
    }
}

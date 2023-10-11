package weare.skillcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class GetExistingSkillsTest extends BaseTest {


    @Test(priority = 1)
    public void getAllExistingSkillsTest() {
        baseURI = format("%s%s", BASE_URL, SKILLS_FIND_ALL);

        response= requestSpecificationWithoutAuthentication()
                .get(baseURI);

        System.out.println(response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        Assert.assertNotNull(response.path("skill"), "Incorrect information returned");
    }

//    @Test(priority = 2)
//    public void createSkill() {
//        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
//        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);
//        //String requestBody = format(SKILL_BODY_OLD, SKILL, SKILL_ID);
//
//        response = requestSpecificationWithAuthentication()
//                .body(requestBody)
//                .post(baseURI);
//
//        System.out.println(response.getBody().asPrettyString());
//
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        assertEquals(response.getBody().jsonPath().get("skill"), SKILL, "Response  is different than provided.");
//
//        skillId = (response.path("skillId").toString());
//        System.out.printf("\nSkill with id %s was created.\n", skillId);
//
//    }

//    @Test(priority = 3)
//    public void editSkill() {
//        if (skillId == null) {
//            createSkill();
//        }
//        int intSkillId = Integer.parseInt(skillId);
//        baseURI = format("%s%s", BASE_URL, SKILLS_EDIT);
//
//        response = requestSpecificationWithAuthentication()
//                .queryParam("skill", EDITED_SKILL)
//                .queryParam("skillId", intSkillId)
//                .put(baseURI);
//
//        //System.out.println(response.getBody().asPrettyString());
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        assertEquals("", response.body().asString(), "Response body isn't empty.");
//        System.out.printf("\nSkill with id %s was edited.\n", skillId);
//    }

//    @Test(priority = 4)
//    public void getSkillById(){
//        if (skillId == null) {
//        createSkill();
//    }
//        int intSkillId = Integer.parseInt(skillId);
//        baseURI = format("%s%s", BASE_URL, SKILLS_GET_ONE);
//
//        response = requestSpecificationWithAuthentication()
//                .queryParam("skillId", intSkillId)
//                .get(baseURI);
//
//        //System.out.println(response.getBody().asPrettyString());
//        String resp = response.getBody().asPrettyString();
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        //assertEquals(response.getBody().jsonPath().get("skill"), EDITED_SKILL, "Response  is different than provided.");
//        System.out.printf("\nSkill with id %s :  %s.\n", skillId, resp);
//
//    }

//    @Test(priority = 5)
//    public void deleteSkill() {
//        if (skillId == null) {
//            createSkill();
//        }
//        int intSkillId = Integer.parseInt(skillId);
//        baseURI = format("%s%s", BASE_URL, SKILLS_DELETE);
//
//        response = requestSpecificationWithAuthentication()
//                .queryParam("skillId", intSkillId)
//                .put(baseURI);
//
//        //System.out.println(response.getBody().asPrettyString());
//        int statusCode = response.getStatusCode();
//        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
//        assertEquals("", response.body().asString(), "Response body isn't empty.");
//        System.out.printf("\nSkill with id %s was deleted.\n", skillId);
//    }
}

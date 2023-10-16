package weare.skillcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GetSkillTest extends BaseTest {

    @Test
    @Label("Jira - FPW-238")
    public void getAllExistingSkills_When_SearchedForAllSkills() {
        baseURI = format("%s%s", BASE_URL, SKILLS_FIND_ALL);

        response = requestSpecificationWithoutAuthentication()
                .get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        String resp = response.getBody().asString();
        if (resp.length() > 2) {
            assertNotNull(response.getBody().jsonPath().get("[0].skillId"), "Skill id is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].skill"), "Skill is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].category"), "Category is empty.");
            System.out.printf("List of all existing skills below: %s", response.getBody().asPrettyString());
        } else {
            assertEquals(response.body().asString(), "[]", "Response body isn't empty.");
            System.out.println("No skills created yet.");
        }

    }

    @Test
    @Label("Jira - FPW-242")
    public void getOneSkillById_When_SearchedForSkillById() {
        createSkill();

        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_GET_ONE);

        response = requestSpecificationWithAuthentication()
                .queryParam("skillId", intSkillId)
                .get(baseURI);

        String resp = response.getBody().asPrettyString();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("skillId").toString(), skillId, "Skill id is different than provided.");
        System.out.printf("\nSkill with id %s :  %s.\n", skillId, resp);
    }
}

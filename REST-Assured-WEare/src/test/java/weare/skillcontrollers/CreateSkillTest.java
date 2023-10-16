package weare.skillcontrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.SKILL_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateSkillTest extends BaseTest {
    @Test
    @Label("Jira - FPW-239")
    public void skillCreated_When_ValidDataProvided() {
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("skill"), SKILL, "Response is different than provided.");
        assertEquals(response.getBody().jsonPath().get("category.id").toString(), CATEGORY_ID, "Response is different than provided.");
        assertEquals(response.getBody().jsonPath().get("category.name"), CATEGORY_NAME, "Response is different than provided.");
        skillId = (response.path("skillId").toString());
        System.out.printf("\nSkill with id %s was created.\n", skillId);

    }
}

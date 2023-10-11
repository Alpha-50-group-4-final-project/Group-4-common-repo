package weare.skillcontrollers;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.RequestJSON.SKILL_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
public class CreateSkillTest extends BaseTest {
        @Test
    public void createSkill() {
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);

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

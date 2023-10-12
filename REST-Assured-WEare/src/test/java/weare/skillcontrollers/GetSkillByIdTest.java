package weare.skillcontrollers;
import base.BaseTest;

import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SKILLS_GET_ONE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class GetSkillByIdTest extends BaseTest {
        @Test
    public void getSkillByIdTest(){
        if (skillId == null) {
        createSkill();
    }
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

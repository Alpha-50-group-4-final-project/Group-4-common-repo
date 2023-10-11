package weare.skillcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SKILLS_GET_ONE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class GetSkillByIdTest extends BaseTest {
    @Test(priority = 4)
    public void getSkillById(){
        if (skillId == null) {
            createSkill();
        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_GET_ONE);

        response = requestSpecificationWithAuthentication()
                .queryParam("skillId", intSkillId)
                .get(baseURI);

        //System.out.println(response.getBody().asPrettyString());
        String resp = response.getBody().asPrettyString();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        //assertEquals(response.getBody().jsonPath().get("skill"), EDITED_SKILL, "Response  is different than provided.");
        System.out.printf("\nSkill with id %s :  %s.\n", skillId, resp);

    }
}

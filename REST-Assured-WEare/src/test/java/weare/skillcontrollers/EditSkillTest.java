package weare.skillcontrollers;
import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;


public class EditSkillTest extends BaseTest {
    @Test
    public void editSkill() {
        if (skillId == null) {
            createSkill();
        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_EDIT);

        response = requestSpecificationWithAuthentication()
                .queryParam("skill", EDITED_SKILL)
                .queryParam("skillId", intSkillId)
                .put(baseURI);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals("", response.body().asString(), "Response body isn't empty as expected.");
        System.out.printf("\nSkill with id %s was edited.\n", skillId);
        System.out.println(response.getBody().asPrettyString());

    }
}

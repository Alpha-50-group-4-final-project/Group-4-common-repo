package weare.skillcontrollers;

import base.BaseTest;
import org.testng.annotations.Test;
import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SKILLS_DELETE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class DeleteSkillTest extends BaseTest {
    @Test
    public void deleteSkillTest() {
        if (skillId == null) {
            createSkill();
        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_DELETE);

        response = requestSpecificationWithAuthentication()
                .queryParam("skillId", intSkillId)
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals("", response.body().asString(), "Response body isn't empty as expected.");
        System.out.printf("\nSkill with id %s was deleted.\n", skillId);
    }
}

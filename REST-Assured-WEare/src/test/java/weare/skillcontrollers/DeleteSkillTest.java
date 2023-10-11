package weare.skillcontrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.BASE_URL;
import static com.api.utils.Endpoints.SKILLS_DELETE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class DeleteSkillTest extends BaseTest {
    @Test(priority = 5)
    public void deleteSkillTest() {
        if (skillId == null) {
            createSkill();
        }
        int intSkillId = Integer.parseInt(skillId);
        baseURI = format("%s%s", BASE_URL, SKILLS_DELETE);

        response = requestSpecificationWithAuthentication()
                .queryParam("skillId", intSkillId)
                .put(baseURI);

        //System.out.println(response.getBody().asPrettyString());
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals("", response.body().asString(), "Response body isn't empty.");
        System.out.printf("\nSkill with id %s was deleted.\n", skillId);
    }
}

package weare.skillcontrollers;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GetExistingSkillsTest extends BaseTest {


    @Test
    public void getAllExistingSkillsTest() {
        baseURI = format("%s%s", BASE_URL, SKILLS_FIND_ALL);

        response = requestSpecificationWithoutAuthentication()
                .get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        String resp = response.getBody().toString();
        if (resp.length() != 0) {
            assertNotNull(response.getBody().jsonPath().get("[0].skillId"), "Skill id is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].skill"), "Skill is empty.");
            assertNotNull(response.getBody().jsonPath().get("[0].category"), "Category is empty.");
        }
        System.out.printf("List of all existing skills below: %s",response.getBody().asPrettyString());

    }
}

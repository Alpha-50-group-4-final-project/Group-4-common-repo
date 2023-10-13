package weare.usercotrollers;

import base.BaseTest;
import jdk.jfr.Label;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.UPGRADE_EXPERTISE_PROFILE;
import static com.api.utils.Endpoints.UPGRADE_PERSONAL_PROFILE;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.UPGRADE_EXPERTISE_PROFILE_BODY;
import static com.api.utils.RequestJSON.UPGRADE_PERSONAL_PROFILE_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UpgradeUserTests extends BaseTest {

    @Test
    @Label("Jira - FPW-237")
    public void personalProfileUpgraded_When_ValidDataProvided() {
        if (isNull(regularUserId)) {
            registerNewUser();
        }
        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, regularUserId)));
        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY,
                TODAY_DATE,
                FIRSTNAME,
                regularUserId,
                LAST_NAME,
                CITY_ID,
                PERSONAL_REVIEW, NO_PICTURE);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("firstName"), FIRSTNAME,
                "Provided first name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("lastName"), LAST_NAME,
                "Provided last name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("location.city.id").toString(), CITY_ID,
                "Provided city id is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("personalReview"), PERSONAL_REVIEW,
                "Provided personal review is not equal to response.");
        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }

    @Test
    @Label("Jira - FPW-236")
    public void expertiseProfileUpgraded_When_ValidDataProvided() {
        if (isNull(expertiseProfileId)) {
            registerNewUser();
            getLastUser();
        }
        String firstRowSkill = faker.lorem().word() + timeStamp();
        String secondRowKill = faker.lorem().word() + timeStamp();
        String skill = faker.lorem().word() + timeStamp();

        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_EXPERTISE_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_EXPERTISE_PROFILE_BODY,
                AVAILABILITY,
                CATEGORY_ID,
                CATEGORY_NAME,
                expertiseProfileId,
                firstRowSkill,
                secondRowKill,
                skill);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected: %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().get("category.id").toString(), CATEGORY_ID,
                "Provided category id is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("category.name"), CATEGORY_NAME,
                "Provided category name is not equal to response.");
        assertTrue(response.getBody().asString().contains(firstRowSkill));
        assertTrue(response.getBody().asString().contains(secondRowKill));
        assertTrue(response.getBody().asString().contains(skill));
    }

}

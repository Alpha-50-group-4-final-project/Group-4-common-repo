package weare.usercotrollers;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.api.utils.Constants.*;
import static com.api.utils.Constants.PERSONAL_REVIEW_INVALID;
import static com.api.utils.Endpoints.UPGRADE_EXPERTISE_PROFILE;
import static com.api.utils.Endpoints.UPGRADE_PERSONAL_PROFILE;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.UPGRADE_EXPERTISE_PROFILE_BODY;
import static com.api.utils.RequestJSON.UPGRADE_PERSONAL_PROFILE_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UpgradeUsersTests extends BaseTest {

    @Test(priority = 4)
    public void upgradePersonalProfile() {
        if (regularUserId == null) {
            registerNewUser();
        }

        baseURI = (format("%s%s", BASE_URL, format(UPGRADE_PERSONAL_PROFILE, regularUserId)));

        String requestBody = format(UPGRADE_PERSONAL_PROFILE_BODY,
                TODAY_DATE,
                FIRSTNAME,
                regularUserId,
                LAST_NAME,
                CITY_ID,
                PERSONAL_REVIEW);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post(baseURI);

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().get("firstName"), FIRSTNAME,
                "Provided first name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("lastName"), LAST_NAME,
                "Provided last name is not equal to response.");
        assertEquals(response.getBody().jsonPath().get("personalReview"), PERSONAL_REVIEW,
                "Provided personal review is not equal to response.");

        System.out.printf("\nPersonal profile of user with id %s was updated successfully\n", regularUserId);
    }

    @Test(priority = 5)
    public void upgradeExpertiseProfileTest() {
        if (expertiseProfileId == null) {
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
                skill
        );
        System.out.println(requestBody);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");
        System.out.println(expertiseUpdateUsername);
        System.out.println(expertiseProfileId);

        response = requestSpecificationWithAuthentication()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());
        assertEquals(statusCode, HttpStatus.SC_OK, "Incorrect status code. Expected 200.");
        assertTrue(response.getBody().asString().contains(firstRowSkill));
        assertTrue(response.getBody().asString().contains(secondRowKill));
        assertTrue(response.getBody().asString().contains(skill));
    }

}

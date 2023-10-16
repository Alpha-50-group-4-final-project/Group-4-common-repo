package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class AdminEnableDisableUserTest extends BaseTest {

    private static String userForTesting = getUIMappingByKey("adminPage.enableDisable.nameUpdate");

    @BeforeAll
    public static void testSetUp() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, userForTesting, lastNameRandom);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
    }

    @AfterAll
    public static void clearData() {
        homePage.navigateToHomePage();
        logout();
    }

    @Test
    @Label("Jira FPW-146")
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(userForTesting);
        adminPage.clickDisableButton();
        adminPage.validateProfileDisabled();
    }

    @Test
    @Label("Jira FPW-147")
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(userForTesting);
        adminPage.clickOnEnableButton();
        adminPage.validateProfileEnabled();
    }
}
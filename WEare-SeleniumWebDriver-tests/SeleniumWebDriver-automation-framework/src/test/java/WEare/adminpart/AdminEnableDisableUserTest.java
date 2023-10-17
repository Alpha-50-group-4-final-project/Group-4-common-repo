package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.annotation.Testable;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
@Testable
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
    @Tag("HappyPath")
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        homePage.searchUserByName(userForTesting);
        adminPage.clickDisableButton();
        adminPage.validateProfileDisabled();
    }

    @Test
    @Label("Jira FPW-147")
    @Tag("HappyPath")
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        homePage.searchUserByName(userForTesting);
        adminPage.clickOnEnableButton();
        adminPage.validateProfileEnabled();
    }
}

package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminEnableDisableUserTest extends BaseTest {

    private static String userForTesting = getUIMappingByKey("adminPage.enableDisable.nameUpdate");

    @BeforeAll
    public static void testSetUp(){
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom, userForTesting,lastNameRandom);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
    }

    @Test
    @Order(1)
    @Label("Jira FPW-146")
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(userForTesting);
        adminPage.clickDisableButton();
        adminPage.validateProfileDisabled();
    }
    @Test
    @Order(2)
    @Label("Jira FPW-147")
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(userForTesting);
        adminPage.clickOnEnableButton();
        adminPage.validateProfileEnabled();
    }
}

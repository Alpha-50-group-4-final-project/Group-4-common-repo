package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

public class AdminEnableDisableUserTest extends BaseTest {

    @BeforeAll
    public static void testSetUp(){
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom, PROFILE_FOR_ENABLE_DISABLE,lastNameRandom);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
    }

    @Test
    @Order(1)
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        adminPage.clickDisableButton();
        adminPage.validateProfileDisabled();
    }
    @Test
    @Order(2)
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchPage.findUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        adminPage.clickOnEnableButton();
        adminPage.validateProfileEnabled();
    }





}

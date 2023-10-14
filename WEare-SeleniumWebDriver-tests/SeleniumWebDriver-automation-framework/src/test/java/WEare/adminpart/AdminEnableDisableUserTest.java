package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

public class AdminEnableDisableUserTest extends BaseTest {

    @BeforeAll
    public static void testSetUp(){
        homePage.navigateToHomePage();
        api.registerUser(adminUsername, adminPassword);
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom, PROFILE_FOR_ENABLE_DISABLE,lastNameRandom);
    }

    @BeforeEach
    public void adminLogIn() {
        homePage.navigateToHomePage();
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();

    }
    @AfterEach
    public void adminLogout(){
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }

    @Test
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
    }
    @Test
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }





}

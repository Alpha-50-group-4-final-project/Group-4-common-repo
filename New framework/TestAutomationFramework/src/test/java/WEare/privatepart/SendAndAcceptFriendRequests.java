package WEare.privatepart;

import WEare.BaseTest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;

public class SendAndAcceptFriendRequests extends BaseTest {

    public static String FIRST_USER;
    public static String SECOND_USER;

    private static String firstUserFirstName;
    private static String secondUserFirstName;

    public static final String USERS_PASSWORD = "pass_123";

    @BeforeAll
    public static void testSetUp() {
        FIRST_USER = faker.name().firstName();
        SECOND_USER = faker.name().firstName();
        firstUserFirstName = faker.name().firstName();
        secondUserFirstName = faker.name().firstName();
        registerUser(FIRST_USER, USERS_PASSWORD);
        registerUser(SECOND_USER, USERS_PASSWORD);
        login(FIRST_USER, USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(firstUserFirstName, "Snow", "01-01-1970");
        loginPage.clickOnLogOutButton();
        login(SECOND_USER, USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(secondUserFirstName, "Stark", "01-01-1970");
    }

    @AfterAll
    public static void clean() {
        loginPage.clickOnLogOutButton();
    }

    @Test
    public void sendConnectRequest() {
        editProfilePage.navigateToHomePage();
        homePage.typeIntoNameSearchBox(firstUserFirstName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
    }

    @Test
    public void acceptConnectRequest() {
        editProfilePage.navigateToHomePage();
        homePage.typeIntoNameSearchBox(firstUserFirstName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
        loginPage.clickOnLogOutButton();
        login(FIRST_USER, USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        homePage.navigateToPersonalProfileButton();
        searchingPage.clickOnNewFriendRequestButton();
        searchingPage.approveRequestByUserFirstName(secondUserFirstName);

    }
}

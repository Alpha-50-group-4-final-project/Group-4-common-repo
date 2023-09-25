package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendAndAcceptFriendRequestsTests extends BaseTest {

    public static String FIRST_USER;
    public static String SECOND_USER;

    private static String firstUserFirstName;
    private static String secondUserFirstName;

    public static final String USERS_PASSWORD = "pass_123";


    @BeforeAll
    public static void initialSetUp() {
        FIRST_USER = faker.name().firstName();
        SECOND_USER = faker.name().firstName();
        registerUser(FIRST_USER, USERS_PASSWORD);
        registerUser(SECOND_USER, USERS_PASSWORD);

    }

    @BeforeEach
    public void usersFirstNameSetUps() {
        firstUserFirstName = faker.name().firstName();
        secondUserFirstName = faker.name().firstName();
        String lastNameFirstUser = faker.name().firstName();
        String lastNameSecondUser = faker.name().firstName();
        loginAndSetupUsers(FIRST_USER, USERS_PASSWORD, firstUserFirstName, lastNameFirstUser);
        loginPage.clickOnLogOutButton();
        loginAndSetupUsers(SECOND_USER, USERS_PASSWORD, secondUserFirstName, lastNameSecondUser);
    }


    @AfterEach
    public void clean() {
        loginPage.clickOnLogOutButton();
    }

    @Test
    public void sendConnectRequest() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
    }

    @Test
    public void acceptConnectRequest() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
        loginSendsApproveRequests(FIRST_USER, secondUserFirstName);
    }

    @Test
    public void disconnectAcceptedFriendShip() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
        loginSendsApproveRequests(FIRST_USER, secondUserFirstName);
        searchAndFindCurrentProfileByName(secondUserFirstName);
        searchingPage.clickOnDisconnectButton();
        searchingPage.assertElementPresent("ProfileConnectionPageConnectButton");
    }

    private static void loginSendsApproveRequests(String username, String userFirstName) {
        loginPage.clickOnLogOutButton();
        login(username, SendAndAcceptFriendRequestsTests.USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        homePage.navigateToPersonalProfileButton();
        searchingPage.clickOnNewFriendRequestButton();
        searchingPage.approveRequestByUserFirstName(userFirstName);
    }

    private static void searchAndFindCurrentProfileByName(String userName) {
        editProfilePage.navigateToHomePage();
        homePage.typeIntoNameSearchBox(userName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(userName);
    }

    private static void loginAndSetupUsers(String username, String password, String userFirstname, String userLastname) {
        login(username, password);
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(userFirstname, userLastname, "01-01-1970");
    }
}

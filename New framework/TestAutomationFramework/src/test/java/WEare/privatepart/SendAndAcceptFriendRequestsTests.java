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
        loginAndSetupUsers(FIRST_USER, firstUserFirstName, lastNameFirstUser);
        loginPage.clickOnLogOutButton();
        loginAndSetupUsers(SECOND_USER, secondUserFirstName, lastNameSecondUser);
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

        homePage.navigateToPersonalProfileButton();
        searchingPage.clickOnNewFriendRequestButton();
        searchingPage.approveRequestByUserFirstName(userFirstName);
    }

    private static void searchAndFindCurrentProfileByName(String userName) {

        homePage.typeIntoNameSearchBox(userName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(userName);
    }

    private static void loginAndSetupUsers(String username, String userFirstname, String userLastname) {
        login(username, SendAndAcceptFriendRequestsTests.USERS_PASSWORD);

        editProfilePage.navigateToEditProfileMenu();
        userSetUP(userFirstname, userLastname, "01-01-1970");
    }
}

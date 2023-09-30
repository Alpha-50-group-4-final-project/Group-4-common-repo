package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendAndAcceptFriendRequestsTests extends BaseTest {

    public static String SECOND_USER;

    private static String firstUserFirstName;
    private static String secondUserFirstName;

    @BeforeAll
    public static void initialSetUp() {
        SECOND_USER = faker.name().firstName();
        registerUser(usernameRandom, passwordRandom);
        registerUser(SECOND_USER, passwordRandom);
    }

    @BeforeEach
    public void usersFirstNameSetUps() {
        firstUserFirstName = faker.name().firstName();
        secondUserFirstName = faker.name().firstName();
        String lastNameFirstUser = faker.name().firstName();
        String lastNameSecondUser = faker.name().firstName();
        loginAndSetupUsers(usernameRandom, firstUserFirstName, lastNameFirstUser);
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
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
    }

    @Test
    public void disconnectAcceptedFriendShip() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchAndFindCurrentProfileByName(secondUserFirstName);
        searchingPage.clickOnDisconnectButton();
        searchingPage.assertElementPresent("ProfileConnectionPageConnectButton");
    }

    private static void loginSendsApproveRequests(String username, String userFirstName) {
        loginPage.clickOnLogOutButton();
        login(username, SendAndAcceptFriendRequestsTests.passwordRandom);

        homePage.navigateToPersonalProfileButton();
        searchingPage.clickOnNewFriendRequestButton();
        searchingPage.approveRequestByUserFirstName(userFirstName);
    }

    private static void searchAndFindCurrentProfileByName(String userName) {
        actions.navigateToPage("http://localhost:8081/");
        homePage.typeIntoNameSearchBox(userName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(userName);
    }

    private static void loginAndSetupUsers(String username, String userFirstname, String userLastname) {
        login(username, SendAndAcceptFriendRequestsTests.passwordRandom);
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(userFirstname, userLastname, "01-01-1970");
    }
}

package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.WEareApi.USER_ID;

@DisplayName("UserConnectionsTests")
public class ConnectToUserTests extends BaseTest {
    private static final String PASSWORD = "123456";
    private static String SECOND_USER;
    private static String firstUserFirstName;
    private static String secondUserFirstName;
    private static String recipientID;

    @BeforeAll
    protected static void initialSetUp() {
        firstUserFirstName = faker.name().firstName();
        String lastNameFirstUser = faker.name().firstName();

        secondUserFirstName = faker.name().firstName();
        String lastNameSecondUser = faker.name().firstName();
        SECOND_USER = faker.name().firstName();
        api.registerUser(usernameRandom, PASSWORD);
        api.updateUserProfile(usernameRandom, PASSWORD, firstUserFirstName, lastNameFirstUser);
        recipientID=USER_ID;
        api.registerUser(SECOND_USER, PASSWORD);
        api.updateUserProfile(SECOND_USER,PASSWORD, secondUserFirstName, lastNameSecondUser);

    }
    @BeforeEach
    protected void usersFirstNameSetUps() {
        login(SECOND_USER, PASSWORD);
    }

    @AfterEach
    protected void logOut() {
        loginPage.clickOnLogOutButton();
    }

    @Test
    @Label("Jira FPW-140")
    @Tag("HappyPath")
    @DisplayName("Sending connection request")
    public void sendConnectRequest_when_connectButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchPage.clickConnectButton();
        searchPage.validateConnectionRequestSend();
    }

    @Test
    @Label("Jira FPW-141")
    @Tag("HappyPath")
    @DisplayName("Accept connection request.")
    public void acceptConnectRequest_when_approveRequestButtonClicked() {
        api.sendConnectionRequest(SECOND_USER,PASSWORD,recipientID,firstUserFirstName);
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchPage.validateRequestAccepted(USER_ID);
    }

    @Test
    @Label("Jira FPW-142")
    @Tag("HappyPath")
    @DisplayName("Disconnect friendships")
    public void disconnectAcceptedFriendShip_when_disconnectButtonClicked() {
        api.sendConnectionRequest(SECOND_USER,PASSWORD,recipientID,firstUserFirstName);
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchAndFindCurrentProfileByName(secondUserFirstName);
        searchPage.clickDisconnectButton();
        searchPage.assertElementPresent("ProfileConnectionPageConnectButton");
    }

    private static void loginSendsApproveRequests(String username, String userFirstName) {
        loginPage.clickOnLogOutButton();
        login(username, PASSWORD);

        homePage.navigateToPersonalProfileButton();
        searchPage.clickOnNewFriendRequestButton();
        searchPage.approveRequestByUserFirstName(userFirstName);
    }

    private static void searchAndFindCurrentProfileByName(String userName) {
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox(userName);
        homePage.clickOnSearchButton();
        homePage.searchUserByName(userName);
    }

}

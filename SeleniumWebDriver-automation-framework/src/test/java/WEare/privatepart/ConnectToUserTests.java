package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.api.WEareApi.USER_ID;

@DisplayName("UserConnectionsTests")
public class ConnectToUserTests extends BaseTest {
    private static final String password = "123456";
    private static String firstUserFirstName;
    private static String firstUserLastName;
    private static String secondUserFirstName;
    private static String secondUserLastName;
    private static String recipientID;

    @BeforeAll
    protected static void initialSetUp() {
        firstUserFirstName = faker.name().firstName();
        firstUserLastName = faker.name().firstName();
        secondUserFirstName = faker.name().firstName();
        secondUserLastName = faker.name().firstName();
        api.registerUser(usernameRandom, password);
        api.updateUserProfile(usernameRandom, password, firstUserFirstName, firstUserLastName);
        recipientID = USER_ID;
        api.registerUser(secondUserFirstName, password);
        api.updateUserProfile(secondUserFirstName, password, secondUserFirstName, secondUserLastName);
    }

    @BeforeEach
    protected void usersFirstNameSetUps() {
        login(secondUserFirstName, password);
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
        api.sendConnectionRequest(secondUserFirstName, password, recipientID, firstUserFirstName);
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchPage.validateRequestAccepted(USER_ID);
    }

    @Test
    @Label("Jira FPW-142")
    @Tag("HappyPath")
    @DisplayName("Disconnect friendships")
    public void disconnectAcceptedFriendShip_when_disconnectButtonClicked() {
        api.sendConnectionRequest(secondUserFirstName, password, recipientID, firstUserFirstName);
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchAndFindCurrentProfileByName(secondUserFirstName);
        searchPage.clickDisconnectButton();
        searchPage.assertElementPresent("ProfileConnectionPageConnectButton");
    }

    private static void loginSendsApproveRequests(String username, String userFirstName) {
        loginPage.clickOnLogOutButton();
        login(username, password);
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

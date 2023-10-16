package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectToUserTests extends BaseTest {


    private static final String PASSWORD = "123456";
    public static String SECOND_USER;
    private static String firstUserFirstName;
    private static String secondUserFirstName;

    @BeforeAll
    public static void initialSetUp() {
        firstUserFirstName = faker.name().firstName();
        String lastNameFirstUser = faker.name().firstName();

        secondUserFirstName = faker.name().firstName();
        String lastNameSecondUser = faker.name().firstName();
        SECOND_USER = faker.name().firstName();
        api.registerUser(usernameRandom, PASSWORD);
        api.updateUserProfile(usernameRandom, PASSWORD, firstUserFirstName, lastNameFirstUser);
        api.registerUser(SECOND_USER, PASSWORD);
        api.updateUserProfile(SECOND_USER,PASSWORD, secondUserFirstName, lastNameSecondUser);
    }

    @BeforeEach
    public void usersFirstNameSetUps() {
        login(SECOND_USER, PASSWORD);
    }

    @AfterEach
    public void clean() {
        loginPage.clickOnLogOutButton();
    }


    @Test
    @Label("Jira FPW-140")
    public void sendConnectRequest_when_connectButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchPage.clickOnConnectButton();
        searchPage.assertRequestIsSend();
    }

    @Test
    @Label("Jira FPW-141")
    public void acceptConnectRequest_when_approveRequestButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchPage.clickOnConnectButton();
        searchPage.assertRequestIsSend();
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
    }

    @Test
    @Label("Jira FPW-142")
    public void disconnectAcceptedFriendShip_when_disconnectButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchPage.clickOnConnectButton();
        searchPage.assertRequestIsSend();
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
        searchAndFindCurrentProfileByName(secondUserFirstName);
        searchPage.clickOnDisconnectButton();
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
        searchPage.findUserProfileByName(userName);
    }

}

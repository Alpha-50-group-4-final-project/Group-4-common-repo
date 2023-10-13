package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendAndAcceptFriendRequestsTests extends BaseTest {


    private static final String PASSWORD = "testBot_123";
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
    public void sendConnectRequest_when_connectButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
    }

    @Test
    public void acceptConnectRequest_when_approveRequestButtonClicked() {
        searchAndFindCurrentProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();
        loginSendsApproveRequests(usernameRandom, secondUserFirstName);
    }

    @Test
    public void disconnectAcceptedFriendShip_when_disconnectButtonClicked() {
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
        login(username, PASSWORD);

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

}

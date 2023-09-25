package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUserFirstName;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendAndAcceptFriendRequestsTests extends BaseTest {

    public static String FIRST_USER;
    public static String SECOND_USER;

    private static String firstUserFirstName;
    private static String secondUserFirstName;

    public static final String USERS_PASSWORD = "pass_123";


    @BeforeEach
    public void usersFirstNameSetUps() {
        FIRST_USER = faker.name().firstName();
        SECOND_USER = faker.name().firstName();
        registerUser(FIRST_USER, USERS_PASSWORD);
        registerUser(SECOND_USER, USERS_PASSWORD);
        firstUserFirstName = faker.name().firstName();
        LOGGER.info(firstUserFirstName + " was generated");
        secondUserFirstName = faker.name().firstName();
        LOGGER.info(secondUserFirstName + " was generated");
        String lastNameFirstUser=randomUserFirstName();
        String lastNameSecondUser=randomUserFirstName();
        System.out.println(firstUserFirstName);
        System.out.println(secondUserFirstName);
        login(FIRST_USER, USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(firstUserFirstName, lastNameFirstUser, "01-01-1970");
        loginPage.clickOnLogOutButton();
        login(SECOND_USER, USERS_PASSWORD);
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
        userSetUP(secondUserFirstName, lastNameSecondUser, "01-01-1970");

    }

    @AfterEach
    public  void clean() {
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
        searchingPage.clickOnDisconnectButton();

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

    @Test
    public void disconnectAcceptedFriendShip(){
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

        editProfilePage.navigateToHomePage();
        homePage.typeIntoNameSearchBox(secondUserFirstName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(secondUserFirstName);
        searchingPage.clickOnDisconnectButton();
        searchingPage.assertElementPresent("ProfileConnectionPageConnectButton");
    }
}

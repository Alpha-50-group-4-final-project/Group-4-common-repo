package WEare.privatepart;

import WEare.BaseTest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import javax.swing.plaf.PanelUI;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendAndAcceptFriendRequests extends BaseTest {

    public static String FIRST_USER;
    public static String SECOND_USER;

    private static String firstUserFirstName;
    private static String secondUserFirstName;

    public static final String USERS_PASSWORD = "pass_123";



    public String firstUser(){
        return faker.name().firstName();
    }

    @BeforeAll
    public static void testSetUp() {
        FIRST_USER = faker.name().firstName();
        SECOND_USER = faker.name().firstName();

        registerUser(FIRST_USER, USERS_PASSWORD);
        registerUser(SECOND_USER, USERS_PASSWORD);

    }

    @BeforeEach
    public void usersFirstNameSetUps() {
        firstUserFirstName = firstUser();
        secondUserFirstName = firstUser();
        System.out.println(firstUserFirstName);
        System.out.println(secondUserFirstName);
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

    @AfterEach
    public  void clean() {
        loginPage.clickOnLogOutButton();
    }

    @Test
    @Order(1)
    public void sendConnectRequest() {
        editProfilePage.navigateToHomePage();
        homePage.typeIntoNameSearchBox(firstUserFirstName);
        homePage.clickOnSearchButton();
        searchingPage.seeCurrentUserProfileByName(firstUserFirstName);
        searchingPage.clickOnConnectButton();
        searchingPage.assertRequestIsSend();

    }

    @Test
    @Order(2)
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

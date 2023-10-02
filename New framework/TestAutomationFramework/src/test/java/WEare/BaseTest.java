package WEare;

import com.github.javafaker.Faker;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import pages.WEare.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static java.lang.String.format;


public class BaseTest {

    public static String usernameRandom;
    public static String passwordRandom;
    public static HomePage homePage;
    protected static UserActions actions = new UserActions();

    public static UserRegistrationPage registrationPage;
    public static LoginPage loginPage;

    public static PersonalProfilePage editProfilePage;
    public static LatestPostPage latestPostPage;
    public static SearchingPage searchingPage;
    public static AdminPage adminPage;
    public static Faker faker;
    public static PostsPage postsPage;

    public static CommentsPage comments;
    public static SimpleDateFormat dtf;
    public static List<String> usernames;

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("homePage");
        faker = new Faker();

        homePage = new HomePage(actions.getDriver());
        registrationPage = new UserRegistrationPage(actions.getDriver());
        loginPage = new LoginPage(actions.getDriver());
        editProfilePage = new PersonalProfilePage(actions.getDriver());
        latestPostPage = new LatestPostPage(actions.getDriver());
        searchingPage = new SearchingPage(actions.getDriver());
        adminPage = new AdminPage(actions.getDriver());
        postsPage = new PostsPage(actions.getDriver());
        comments = new CommentsPage(actions.getDriver());

        usernameRandom = faker.name().firstName();
        LOGGER.info("The follow username was generated: " + usernameRandom);
        passwordRandom = faker.internet().password(8, 20, true, true);
        LOGGER.info("The follow password was generated: " + usernameRandom);
        dtf = new SimpleDateFormat("yyyy/MM/dd");
        usernames = new ArrayList<>();
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void userSetUP(String firstName, String lastName, String birthdayDate) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirthdayField(birthdayDate);
        editProfilePage.clickPersonalInformationUpdateButton();
    }

    public static void registerUser(String username, String password) {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(username);
        registrationPage.fillEmailField(username);
        registrationPage.fillPasswordFields(password);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        usernames.add(username);
        LOGGER.info("User with the following user name: " + username + "and password: " + password + " was registered.");
        System.out.println(username + " was added to list of user names");
    }

    public static void login(String username, String password) {
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("homePage.LogoutButton");
        LOGGER.info("User with the following user name: " + username + "and password: " + password + " has logged in successfully.");
    }
    @AfterAll
    public static void logOutFromAccount(){
        if(actions.isElementVisible("homePage.LogoutButton")){
            actions.clickElement("homePage.LogoutButton");
        }
    }

    @AfterAll
    public static void deleteDataBase() {
        String jdbcUrl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11650430?useSSL=false&serverTimezone=UTC";
        String username = "sql11650430";
        String password = "UAkqL5USKZ";
        System.out.println(usernames.size());

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            for (String name : usernames) {
                try (Statement statement = connection.createStatement()) {
                    System.out.println(name);
                    String userIdQuery = format("SELECT user_id FROM users WHERE users.username='%s'", name);
                    ResultSet rs = statement.executeQuery(userIdQuery);

                    while (rs.next()) {
                        int id = rs.getInt("user_id");
                        System.out.println(id);

                        try (Statement deleteStatement = connection.createStatement()) {
                            String sqlDeleteConnections = format("DELETE FROM `requests` WHERE `requests`.`sender_user_id` = %d OR `requests`.`receiver_user_id`=%d", id, id);
                            System.out.println(sqlDeleteConnections);
                            deleteStatement.executeUpdate(sqlDeleteConnections);

                            String sqlDeleteConnectionUsers = format("DELETE FROM `connection_users` WHERE `connection_users`.`user_a` = %d OR `connection_users`.`user_b` = %d", id, id);
                            System.out.println(sqlDeleteConnectionUsers);
                            deleteStatement.executeUpdate(sqlDeleteConnectionUsers);

                            String sqlDeletePosts = format("DELETE FROM posts_table WHERE posts_table.user_id = %d", id);
                            System.out.println(sqlDeletePosts);
                            deleteStatement.executeUpdate(sqlDeletePosts);

                            String sqlQuery = format("DELETE FROM `users` WHERE `users`.`username` = '%s'", name);
                            deleteStatement.executeUpdate(sqlQuery);
                            LOGGER.info(name + " was deleted from the database");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


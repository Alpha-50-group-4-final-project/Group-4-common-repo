package WEare;

import com.github.javafaker.Faker;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.api.PostModel;
import com.telerikacademy.testframework.api.WEareApi;

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
    public static String lastNameRandom;
    public static String passwordRandom;
    public static HomePage homePage;
    protected static UserActions actions = new UserActions();

    public static WEareApi api;
    public static PostModel apiPost;
    public static UserRegistrationPage registrationPage;
    public static LoginPage loginPage;

    public static PersonalProfilePage editProfilePage;
    public static SearchingPage searchPage;
    public static AdminPage adminPage;
    public static Faker faker;
    public static PostsPage postsPage;

    public static CommentsPage commentsPage;
    public static SimpleDateFormat dtf;
    public static List<String> usernames;

    public static String adminUsername;
    public static String adminPassword;
    public static final String postMessage = "This post was made by Selenium WebDriver";

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("homePage");
        faker = new Faker();
        homePage = new HomePage(actions.getDriver());
        registrationPage = new UserRegistrationPage(actions.getDriver());
        loginPage = new LoginPage(actions.getDriver());
        editProfilePage = new PersonalProfilePage(actions.getDriver());
        searchPage = new SearchingPage(actions.getDriver());
        adminPage = new AdminPage(actions.getDriver());
        postsPage = new PostsPage(actions.getDriver());
        commentsPage = new CommentsPage(actions.getDriver());
        api = new WEareApi();
        apiPost = new PostModel();
        usernameRandom = faker.name().firstName();
        lastNameRandom = faker.name().lastName();
        adminUsername = faker.name().firstName() + "admin";
        adminPassword = "12345678";
        LOGGER.info("The follow username was generated: " + usernameRandom);
        passwordRandom = faker.internet().password(8, 20, true, true);
        LOGGER.info("The follow password was generated: " + usernameRandom);
        dtf = new SimpleDateFormat("yyyy/MM/dd");
        usernames = new ArrayList<>();
    }

//    @AfterAll
//    public static void logOutFromAccount() {
//        logout();
//        UserActions.quitDriver();
//    }

    public static void login(String username, String password) {
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("homePage.LogoutButton");
        LOGGER.info("User with the following user name: " + username + "and password: " + password + " has logged in successfully.");
    }

    public static void logout() {
        homePage.navigateToHomePage();
        if (actions.isElementVisible("homePage.LogoutButton")) {
            actions.clickElement("homePage.LogoutButton");
        }
    }


    //    @AfterAll
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

                            String sqlDeleteLikedComments = format("DELETE FROM `comment_likes_table` WHERE user_id=%d", id);
                            System.out.println(sqlDeleteLikedComments);
                            deleteStatement.executeUpdate(sqlDeleteLikedComments);

                            String sqlDeleteCommentsTable = format("DELETE FROM `comments_table` WHERE user_id=%d", id);
                            System.out.println(sqlDeleteCommentsTable);
                            deleteStatement.executeUpdate(sqlDeleteCommentsTable);

                            String sqlDeleteLikedPosts = format("DELETE FROM `post_likes_table` WHERE user_id= %d", id);
                            System.out.println(sqlDeleteLikedPosts);
                            deleteStatement.executeUpdate(sqlDeleteLikedPosts);

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


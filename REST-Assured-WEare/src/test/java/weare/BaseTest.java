package weare;

import com.api.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.api.utils.Constants.*;
import static com.api.utils.Endpoints.*;
import static com.api.utils.Helper.isValid;
import static com.api.utils.RequestJSON.REGISTER_USER_BODY;
import static com.api.utils.RequestJSON.SKILL_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

public class BaseTest {
    public static String regularUserId;
    public static String adminUserId;
    public static String expertiseProfileId;
    public static String postId;
    public static String commentId;
    public static Cookies cookies;
    public static List<String> usernames = new ArrayList<>();
    public static String skillId;
    public static String connectionId;
    public static String newUserName;
    public static String newUserPass;


    public Cookies getAuthCookie(String username, String password) {
        return cookies = given().given().queryParam("username", username)
                .queryParam("password", password)
                .when().
                post(authenticate).
                then().statusCode(302).
                extract().response().
                getDetailedCookies();
    }

    public void registerNewUser(){
        baseURI = format("%s%s", BASE_URL, REGISTER_USER);
        String requestBody = (format(REGISTER_USER_BODY,
                CATEGORY_ID,
                CATEGORY_NAME,
                PASSWORD,
                EMAIL,
                PASSWORD,
                USERNAME));
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post();

        newUserName = Constants.USERNAME;
        newUserPass = Constants.PASSWORD;
        String[] responseBody = response.asString().split(" ");
        regularUserId=responseBody[6];
    }

    public void createSkill(){
        baseURI = format("%s%s", BASE_URL, SKILLS_CREATE);
        String requestBody = format(SKILL_BODY, CATEGORY_ID_SKILL, CATEGORY_NAME, SKILL, SKILL_ID);
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = given()
                .cookies(getAuthCookie(EXISTING_USER, EXISTING_USER_PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .post(baseURI);
        skillId = (response.path("skillId").toString());
    }

    @AfterClass
    public static void deleteDataBase() {
        String jdbcUrl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11651330?useSSL=false&serverTimezone=UTC";
        String username = "sql11651330";
        String password = "GhyP5jmfiK";
        //System.out.println(usernames.size());

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
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
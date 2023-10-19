package weare.database.manipulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;


public class BaseSetup {

    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<Integer> usersIds = new ArrayList<>();
    public ArrayList<Integer> usersExpertise = new ArrayList<>();
    public ArrayList<Integer> userProfiles = new ArrayList<>();
    public static List<Integer> freshUsersIds = new ArrayList<>();
    public static ArrayList<String> freshUsernames = new ArrayList<>();

    static String jdbcUrl = getConfigPropertyByKey("database.url");
    static String dataBaseUsername = getConfigPropertyByKey("database.username");
    static String dataBasePassword = getConfigPropertyByKey("database.password");

    public static int maxPersonalProfileId;
    public static int maxExpertiseProfileId;

    protected static Connection connection;
    protected static Statement statement;
    public static ResultSet rs;

    static {
        try {
            connection = DriverManager.getConnection(jdbcUrl, dataBaseUsername, dataBasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

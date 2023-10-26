package weare.database.manipulation.UserManipulation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import weare.database.manipulation.BaseSetup;

import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.String.format;


public class InsertNewUser extends BaseSetup {

    private final String username;
    private final String email;
    private final String password;
    private final String role;
    private final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

    public InsertNewUser(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void insertNewUser() {
        try {
            String encodedPassword = getEncodedPassword(getPassword());


            insertIntoAuthoritiesTable(getUsername(),getRole());
            insertIDIntoPersonalProfileTable(timeStamp);
            insertExpertiseProfileId();
            int newExpertiseId = getNewExpertiseId();
            int newPersonalProfileId = getNewPersonalProfileId();
            insertUserIntoUsersTable(getEmail(), encodedPassword, getUsername(), newExpertiseId, newPersonalProfileId);
            System.out.println("User " + getUsername() + " was  added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String getEncodedPassword(String rawPassword) throws SQLException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    private int getNewPersonalProfileId() throws SQLException {
        String getMaxExistingPersonalProfileId = "SELECT MAX(id) FROM personal_profile";
        System.out.println(getMaxExistingPersonalProfileId);

        rs = statement.executeQuery(getMaxExistingPersonalProfileId);
        int newPersonalProfileId = 0;
        while (rs.next()) {
            newPersonalProfileId= rs.getInt("MAX(id)");
            System.out.println(newPersonalProfileId);
        }
        return newPersonalProfileId;
    }

    private int getNewExpertiseId() throws SQLException {
        String maxExistingExpertiseId = "SELECT MAX(id) FROM expertise_profile";
        System.out.println(maxExistingExpertiseId);

        rs = statement.executeQuery(maxExistingExpertiseId);
        int newExpertiseId = 0;
        while (rs.next()) {
            newExpertiseId= rs.getInt("MAX(id)");
            System.out.println(newExpertiseId);
        }
        return newExpertiseId;
    }

    private void insertIntoAuthoritiesTable(String username, String role) throws SQLException {
        if (role.equalsIgnoreCase("regular user")) {
            String sql = format("INSERT INTO `authorities`(`username`, `authority`) VALUES ('%s','ROLE_USER')", username);
            statement.executeUpdate(sql);
            System.out.println(sql);
        }
        if (role.equalsIgnoreCase("admin")) {
            String sql1 = format("INSERT INTO `authorities`(`username`, `authority`) VALUES ('%s','ROLE_USER')", username);
            String sql2 = format("INSERT INTO `authorities`(`username`, `authority`) VALUES ('%s','ROLE_ADMIN')", username);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            System.out.println(sql1);
            System.out.println(sql2);
        }
    }

    private void insertIDIntoPersonalProfileTable(String timeStamp) throws SQLException {
        String sql = format("INSERT INTO personal_profile (member_since,picture_privacy) VALUES('%s',0)", timeStamp);
        statement.executeUpdate(sql);
        System.out.println(sql);
    }


    private void insertExpertiseProfileId() throws SQLException {
        String insert = format("INSERT INTO expertise_profile (category_skill_category_id,availability) VALUES(100,0)");
        System.out.println(insert);
        statement.executeUpdate(insert);

    }

    private static void insertUserIntoUsersTable(String email, String password, String usernameForRegister, int newExpertiseId, int newPersonalProfileId) throws SQLException {
        String queryInsert = "INSERT INTO users (email, enabled, password, username, expertise_profile_id, personal_profile_id) VALUES (?, 1, ?, ?, ?, ?)";
        System.out.println(queryInsert);

        PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
        preparedStatement.setString(1, email); // Email
        preparedStatement.setString(2, password); // Password
        preparedStatement.setString(3, usernameForRegister); // Username
        preparedStatement.setInt(4, newExpertiseId); // Expertise Profile ID
        preparedStatement.setInt(5, newPersonalProfileId); // Personal Profile ID

        preparedStatement.executeUpdate();
    }

}

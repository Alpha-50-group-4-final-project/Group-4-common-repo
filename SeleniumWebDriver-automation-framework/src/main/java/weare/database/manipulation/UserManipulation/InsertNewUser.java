package weare.database.manipulation.UserManipulation;

import weare.database.manipulation.BaseSetup;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            int newExpertiseId = getNewExpertiseId();
            int newPersonalProfileId = getNewPersonalProfileId();

            insertIntoAuthoritiesTable(getUsername(),getRole());
            insertIDIntoPersonalProfileTable(newPersonalProfileId, timeStamp);
            insertExpertiseProfileId(newExpertiseId);
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
            int personalId = rs.getInt("MAX(id)");
            System.out.println(personalId);
            if (personalId > maxPersonalProfileId) {
                newPersonalProfileId = personalId + 1;
            } else {
                newPersonalProfileId = maxPersonalProfileId + 1;
            }
            maxPersonalProfileId = newPersonalProfileId;
            System.out.println(maxPersonalProfileId);
        }
        return newPersonalProfileId;
    }

    private int getNewExpertiseId() throws SQLException {
        String maxExistingExpertiseId = "SELECT MAX(id) FROM expertise_profile";
        System.out.println(maxExistingExpertiseId);

        rs = statement.executeQuery(maxExistingExpertiseId);
        int newExpertiseId = 0;
        while (rs.next()) {
            int expertiseId = rs.getInt("MAX(id)");
            System.out.println(expertiseId);
            if (expertiseId > maxExpertiseProfileId) {
                newExpertiseId = expertiseId + 1;

            } else {
                newExpertiseId = maxExpertiseProfileId + 1;
            }
            maxExpertiseProfileId = newExpertiseId;
            System.out.println(newExpertiseId);
            System.out.println(maxExpertiseProfileId);
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

    private void insertIDIntoPersonalProfileTable(int newPersonalProfileId, String timeStamp) throws SQLException {
        String sql = format("INSERT INTO personal_profile (id,member_since,picture_privacy) VALUES(%d,'%s',0)", newPersonalProfileId, timeStamp);
        statement.executeUpdate(sql);
        System.out.println(sql);
    }


    private void insertExpertiseProfileId(int newExpertiseId) throws SQLException {
        String insert = format("INSERT INTO expertise_profile (id,category_skill_category_id,availability) VALUES(%s,100,0)", newExpertiseId);
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

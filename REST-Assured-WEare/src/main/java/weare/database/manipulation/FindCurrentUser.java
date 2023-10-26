package weare.database.manipulation;

import java.sql.SQLException;

import static java.lang.String.format;

public class FindCurrentUser extends BaseSetup {
    public static String checkForUser(String user) {
        String name=null;
        try {
            String selectEverythingFromUsersTableByName= format("SELECT * FROM users WHERE users.username='%s'",user);
            rs = statement.executeQuery(selectEverythingFromUsersTableByName);
            String getEverythingForUserByUsername = format("SELECT * FROM users WHERE users.username='%s'",user);
            rs = statement.executeQuery(getEverythingForUserByUsername);
            while (rs.next()) {
                name = rs.getString("username");
                usernames.add(name);
                usersIds.add(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name; }
    }


package dataBaseManipulations;

import java.sql.SQLException;
import java.util.List;

public class SelectAllUsers extends BaseSetup {

    public static void selectAllUsers() {
        try {
            String maxExistingExpertiseId = "SELECT * FROM users";
            rs = statement.executeQuery(maxExistingExpertiseId);
            while (rs.next()) {
                String names = rs.getString("username");
                usernames.add(names);
                usersIds.add(rs.getInt("user_id"));
            }

        } catch (SQLException e) {

        }
    }
}

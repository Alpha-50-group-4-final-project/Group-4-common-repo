package weare.database.manipulation.UserManipulation;

import weare.database.manipulation.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DeletePersonalProfileTable extends BaseSetup {
    public static void main(String[] args) throws SQLException {

        List<Integer> usersExpertiseIds = new ArrayList<>();
        List<Integer> locationsIds = new ArrayList<>();

        String getExpertiseProfileTable = "SELECT * FROM personal_profile";
        rs = statement.executeQuery(getExpertiseProfileTable);
        while (rs.next()) {
            usersExpertiseIds.add(rs.getInt("id"));
            locationsIds.add(rs.getInt("location_id"));
        }
        for (int id : usersExpertiseIds) {
            String sqlDeleteConnections = format("DELETE FROM `personal_profile` WHERE id=%d", id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);
        }
        for (int id:locationsIds) {
            String sqlDeleteConnections = format("DELETE FROM `locations` WHERE location_id=%d", id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);
        }

    }
}



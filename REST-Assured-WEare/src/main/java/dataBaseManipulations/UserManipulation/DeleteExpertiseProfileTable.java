package dataBaseManipulations.UserManipulation;

import dataBaseManipulations.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DeleteExpertiseProfileTable extends BaseSetup {
    public static void main(String[] args) throws SQLException {

        List<Integer> usersExpertiseIds = new ArrayList<>();
        String truncateTable = "TRUNCATE TABLE  expertise_profile_skills";
        statement.execute(truncateTable);
        System.out.println("Table was truncated");

        String getExpertiseProfileTable = "SELECT * FROM expertise_profile ";
        rs = statement.executeQuery(getExpertiseProfileTable);
        while (rs.next()) {

            usersExpertiseIds.add(rs.getInt("id"));
        }

        if(usersExpertiseIds.size()==0){
            System.out.println("ExpertiseIdTable is empty");
        }

        for (int id : usersExpertiseIds) {
            String sqlDeleteConnections = format("DELETE FROM `expertise_profile` WHERE id=%d", id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);
        }
    }
}



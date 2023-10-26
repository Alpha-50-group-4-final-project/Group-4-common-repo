package weare.database.manipulation.UserManipulation;

import weare.database.manipulation.BaseSetup;
import weare.database.manipulation.PostManipulation.DeleteAllExistingCommentsAndPosts;
import weare.database.manipulation.skillsManipulations.DeleteAllAddedSkills;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


public class DeleteAllExistingUsers extends BaseSetup {


    public void deleteEveryone()throws SQLException{

        for (String name : usernames) {
            System.out.println(name);
        }
        System.out.println(usernames.size());
        DeleteAllExistingCommentsAndPosts.deleteAllCommentsAndPostsRequest();
        DeleteAllAddedSkills.deleteAllAddedSkills();


        List<Integer> locationsIds = new ArrayList<>();

        String getExpertiseProfileTable = "SELECT * FROM personal_profile";
        rs = statement.executeQuery(getExpertiseProfileTable);
        while (rs.next()) {
            locationsIds.add(rs.getInt("location_id"));
        }


        String maxExistingExpertiseId = "SELECT * FROM users WHERE users.username=%s";
        rs = statement.executeQuery(maxExistingExpertiseId);
        while (rs.next()) {
            String content = rs.getString("username");
            usernames.add(content);
            usersIds.add(rs.getInt("user_id"));
            usersExpertise.add(rs.getInt("expertise_profile_id"));
            userProfiles.add(rs.getInt("personal_profile_id"));
        }




        for (int id : usersIds) {
            String sqlDeleteConnections = format("DELETE FROM `requests` WHERE `requests`.`sender_user_id` = %d OR `requests`.`receiver_user_id`=%d", id, id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);
            String sqlDeleteConnectionUsers = format("DELETE FROM `connection_users` WHERE `connection_users`.`user_a` = %d OR `connection_users`.`user_b` = %d", id, id);
            System.out.println(sqlDeleteConnectionUsers);
            statement.executeUpdate(sqlDeleteConnectionUsers);
        }

        for (String name : usernames) {
            String sqlQuery = format("DELETE FROM `users` WHERE `users`.`username` = '%s'", name);
            statement.executeUpdate(sqlQuery);
            String deleteAuthorities = format("DELETE FROM `authorities` WHERE `username` = '%s'", name);
            statement.executeUpdate(deleteAuthorities);
        }

        for (int personalProfileId : userProfiles) {
            String deleteAllPerosnalProfiles = format("DELETE FROM `personal_profile` WHERE id=%d", personalProfileId);
            System.out.println(deleteAllPerosnalProfiles);
            statement.executeUpdate(deleteAllPerosnalProfiles);
        }
        for (int expertiseID : usersExpertise) {
            String deleteAllExpertiseProfiles = format("DELETE FROM `expertise_profile` WHERE id=%d", expertiseID);
            System.out.println(deleteAllExpertiseProfiles);
            statement.executeUpdate(deleteAllExpertiseProfiles);
        }
        for (int id:locationsIds) {
            String sqlDeleteConnections = format("DELETE FROM `locations` WHERE location_id=%d", id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);
        }

        for (String name : usernames) {
            System.out.println(name);
        }
        System.out.println(usernames.size());
    }
}


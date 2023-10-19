package dataBaseManipulations.UserManipulation;

import dataBaseManipulations.BaseSetup;

import java.sql.SQLException;

import static dataBaseManipulations.skillsManipulations.DeleteAllAddedSkills.deleteAllAddedSkills;
import static dataBaseManipulations.PostManipulation.DeleteAllExistingCommentsAndPosts.deleteAllCommentsAndPostsRequest;
import static java.lang.String.format;


public class DeleteCurrentUserById extends BaseSetup {


    public static void deleteUserById() throws SQLException {

        for (int id : freshUsersIds) {
//            int locationId=0;
//
//
//
//            String getLocationIdByUserId = format("SELECT * FROM personal_profile WHERE id IN (SELECT perosnal_profile_id FROM users WHERE user_id =%d)",id);
//            rs = statement.executeQuery(getLocationIdByUserId);
//            while (rs.next()) {
//                locationId=rs.getInt("location_id");
//            }
//            String sqlDeleteLocation = format("DELETE FROM `locations` WHERE location_id=%d", locationId);
//            System.out.println(sqlDeleteLocation);
//            statement.executeUpdate(sqlDeleteLocation);

            String sqlDeleteConnections = format("DELETE FROM `requests` WHERE `requests`.`sender_user_id` = %d OR `requests`.`receiver_user_id`=%d", id, id);
            System.out.println(sqlDeleteConnections);
            statement.executeUpdate(sqlDeleteConnections);

            String sqlDeleteConnectionUsers = format("DELETE FROM `connection_users` WHERE `connection_users`.`user_a` = %d OR `connection_users`.`user_b` = %d", id, id);
            System.out.println(sqlDeleteConnectionUsers);
            statement.executeUpdate(sqlDeleteConnectionUsers);

            String sqlDeleteLikedComments = format("DELETE FROM `comment_likes_table` WHERE user_id=%d", id);
            System.out.println(sqlDeleteLikedComments);
            statement.executeUpdate(sqlDeleteLikedComments);

            String sqlDeleteCommentsTable = format("DELETE FROM `comments_table` WHERE user_id=%d", id);
            System.out.println(sqlDeleteCommentsTable);
            statement.executeUpdate(sqlDeleteCommentsTable);

            String sqlDeleteLikedPosts = format("DELETE FROM `post_likes_table` WHERE user_id= %d", id);
            System.out.println(sqlDeleteLikedPosts);
            statement.executeUpdate(sqlDeleteLikedPosts);

            String sqlDeletePosts = format("DELETE FROM posts_table WHERE posts_table.user_id = %d", id);
            System.out.println(sqlDeletePosts);
            statement.executeUpdate(sqlDeletePosts);

            String sqlQuery = format("DELETE FROM `users` WHERE `users`.`user_id` = %d", id);
            statement.executeUpdate(sqlQuery);

        }
        for (String name:freshUsernames) {
            String deleteAuthorities = format("DELETE FROM `authorities` WHERE `username` = '%s'", name);
            statement.executeUpdate(deleteAuthorities);
            System.out.printf("User %s was deleted from database", name);
        }
    }
}


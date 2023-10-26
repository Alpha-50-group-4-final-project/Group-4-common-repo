package weare.database.manipulation.UserManipulation;

import weare.database.manipulation.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DeleteCurrentUserById extends BaseSetup {
  static   List<Integer> locationsIds = new ArrayList<>();
    public static void deleteUserById() {
        try {
            for(String name:freshUsernames){
                String getUserId=format("SELECT * FROM users WHERE username='%s'",name);
                rs=statement.executeQuery(getUserId);
                while (rs.next()){
                    int user_id=rs.getInt("user_id");
                    if(!freshUsersIds.contains(user_id)){
                        freshUsersIds.add(user_id);
                    }
                }
            }

            for (int id : freshUsersIds) {
                String sqlDeleteConnections = format("DELETE FROM `requests` WHERE `requests`.`sender_user_id` = %d OR `requests`.`receiver_user_id`=%d", id, id);
                statement.executeUpdate(sqlDeleteConnections);

                String sqlDeleteConnectionUsers = format("DELETE FROM `connection_users` WHERE `connection_users`.`user_a` = %d OR `connection_users`.`user_b` = %d", id, id);
                statement.executeUpdate(sqlDeleteConnectionUsers);

                String sqlDeleteLikedComments = format("DELETE FROM `comment_likes_table` WHERE user_id=%d", id);
                statement.executeUpdate(sqlDeleteLikedComments);

                String sqlDeleteCommentsTable = format("DELETE FROM `comments_table` WHERE user_id=%d", id);
                statement.executeUpdate(sqlDeleteCommentsTable);

                String sqlDeleteLikedPosts = format("DELETE FROM `post_likes_table` WHERE user_id= %d", id);
                statement.executeUpdate(sqlDeleteLikedPosts);

                String sqlDeletePosts = format("DELETE FROM posts_table WHERE posts_table.user_id = %d", id);
                statement.executeUpdate(sqlDeletePosts);

                String sqlQuery = format("DELETE FROM `users` WHERE `users`.`user_id` = %d", id);
                statement.executeUpdate(sqlQuery);

            }
            for (String name : freshUsernames) {
                String deleteAuthorities = format("DELETE FROM `authorities` WHERE `username` = '%s'", name);
                statement.executeUpdate(deleteAuthorities);
                System.out.printf("User %s was deleted from database\n", name);
                usernames.remove(name);
            }


        } catch (SQLException e) {
            System.out.println("Couldn't delete users.");
        }
    }
}


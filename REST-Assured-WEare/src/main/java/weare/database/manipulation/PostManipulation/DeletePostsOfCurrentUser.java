package weare.database.manipulation.PostManipulation;


import weare.database.manipulation.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


public class DeletePostsOfCurrentUser extends BaseSetup {

    private final String nameOfTheUSer;
    private int userID;


    List<String> posts = new ArrayList<>();
    List<Integer> posts_id = new ArrayList<>();
    List<Integer> comments_id = new ArrayList<>();

    public DeletePostsOfCurrentUser(String nameOfTheUSer)  {
        this.nameOfTheUSer = nameOfTheUSer;
    }

    public String getNameOfTheUSer() {
        return nameOfTheUSer;
    }


    public void deleteByUsername() throws SQLException {
        getUserIdByUsername();
        getPostIdByUserId();
        for (int postid:posts_id) {
            getCommentIdByPostId(postid);
            System.out.printf("\nPost with id= %d was deleted\n", postid);
        }


        for (int comment_id : comments_id) {
            DeleteAllExistingCommentsAndPosts.deleteCommentLikeById(comment_id);
            System.out.printf("Comment like with id=%d was deelted",comment_id);
        }

        for (int comment_id : comments_id) {
            DeleteAllExistingCommentsAndPosts.deleteCommentById(comment_id);
            System.out.printf("Comment with %s was deleted", comment_id);
        }

        for (int postid:posts_id) {
            DeleteAllExistingCommentsAndPosts.deletePostLikeById(postid);
            System.out.printf("\nPost like  with id= %d was deleted\n", postid);
        }
        for (int postid:posts_id) {
            DeleteAllExistingCommentsAndPosts.deletePostById(postid);
            System.out.printf("\nPost with id= %d was deleted\n", postid);
        }

    }

    private void getUserIdByUsername() throws SQLException {
        String getUserIdByUsername = format("SELECT user_id FROM users WHERE users.username= '%s'", getNameOfTheUSer());
        rs = statement.executeQuery(getUserIdByUsername);
        while (rs.next()) {
            userID = rs.getInt("user_id");
        }
    }

    private void getPostIdByUserId() throws SQLException {
        String getPostIdByUserID = format("SELECT * FROM posts_table WHERE user_id=%d", userID);
        System.out.println(getPostIdByUserID);

        rs = statement.executeQuery(getPostIdByUserID);
        while (rs.next()) {
            String content = rs.getString("content");
            int id = rs.getInt("post_id");
            posts.add(content);
            posts_id.add(id);
        }
    }

    private void getCommentIdByPostId(int postid) throws SQLException {
        String getCommentIdByPostId = format("SELECT comment_id FROM comments_table WHERE post_id=%d", postid);
        System.out.println(getCommentIdByPostId);

        rs = statement.executeQuery(getCommentIdByPostId);
        while (rs.next()) {
            int id = rs.getInt("comment_id");
            comments_id.add(id);
        }
    }

}

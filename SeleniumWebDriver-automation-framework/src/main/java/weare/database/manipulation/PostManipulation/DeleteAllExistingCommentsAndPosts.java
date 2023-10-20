package weare.database.manipulation.PostManipulation;


import weare.database.manipulation.BaseSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DeleteAllExistingCommentsAndPosts extends BaseSetup {

    public static void deleteAllCommentsAndPostsRequest() throws SQLException {
        List<String> posts = new ArrayList<>();
        List<Integer> posts_id = new ArrayList<>();
        List<Integer> comments_likes_id = new ArrayList<>();
        List<Integer> comments_id = new ArrayList<>();

        String getPostsInformation = "SELECT * FROM posts_table ";
        System.out.println(getPostsInformation);

        rs = statement.executeQuery(getPostsInformation);
        while (rs.next()) {
            String content = rs.getString("content");
            int id = rs.getInt("post_id");
            posts.add(content);
            posts_id.add(id);
            System.out.println(content);
        }
        String commentLikesIds = "SELECT * FROM comment_likes_table ";
        System.out.println(comments_likes_id);

        rs = statement.executeQuery(commentLikesIds);
        while (rs.next()) {
            int id = rs.getInt("comment_id");
            comments_likes_id.add(id);
        }
        String commentsIds = "SELECT * FROM comments_table ";
        System.out.println(commentsIds);

        rs = statement.executeQuery(commentsIds);
        while (rs.next()) {
            int id = rs.getInt("comment_id");
            comments_id.add(id);
        }

        for (int likeID : comments_likes_id) {
            deleteCommentLikeById(likeID);
        }

        for (int commentId : comments_id) {
            deleteCommentById(commentId);
        }
        for (int id : posts_id) {
            deletePostLikeById(id);
        }
        for (String post : posts) {
            deletePostByContent(post);
        }
        if (posts.size() == 0) {
            System.out.println("There are no existing posts in the system.");
        }
    }

    private static void deletePostByContent(String post) throws SQLException {
        String sqlDeletePosts = format("DELETE FROM posts_table WHERE content='%s'", post);
        statement.executeUpdate(sqlDeletePosts);
        System.out.println("Post was deleted.");
    }

    protected static void deletePostById(int postId) throws SQLException {
        String sqlDeletePosts = format("DELETE FROM posts_table WHERE post_id= %d", postId);
        statement.executeUpdate(sqlDeletePosts);
        System.out.println("Post was deleted.");
    }

    protected static void deletePostLikeById(int id) throws SQLException {
        String deletePostlikes = format("DELETE FROM `post_likes_table` WHERE post_id= %d", id);
        statement.executeUpdate(deletePostlikes);
        System.out.println("post like was deleted");
    }

    protected static void deleteCommentById(int commentId) throws SQLException {
        String deleteComments = format("DELETE FROM `comments_table` WHERE comment_id=%d ", commentId);
        statement.executeUpdate(deleteComments);
        System.out.println("Comment was deleted");
    }

    protected static void deleteCommentLikeById(int likeID) throws SQLException {
        String deleteCommentsLikes = format("DELETE FROM `comment_likes_table` WHERE comment_id=%d", likeID);
        statement.executeUpdate(deleteCommentsLikes);
        System.out.println("Comment like was deleted.");
    }
}
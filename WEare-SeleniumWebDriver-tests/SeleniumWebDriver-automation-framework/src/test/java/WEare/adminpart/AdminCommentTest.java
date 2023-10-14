package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminCommentTest extends BaseTest {
    @BeforeAll
    public static void testSetUp(){
        api.registerUser(usernameRandom,passwordRandom);
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        api.createComment(usernameRandom,passwordRandom,TEST_SET_UP_COMMENT,apiPost.postId);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
    }

    @Test
    @Order(1)
    public void editLatestComment_when_editCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }


    @Test
    @Order(2)
    public void deleteLatestComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        commentsPage.validateCommentDeleted();
    }
}

package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class PostsTests extends BaseTest {
    private static String postBody = getUIMappingByKey("postPage.postMessage");
    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postBody);
        login(usernameRandom, passwordRandom);
    }
    @AfterAll
    public static void returnHome() {
        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-70")
    @Tag("HappyPath")
    @Tag("SmokeTest")
    public void createPublicPost_when_addNewPostButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickAddNewPost();
        postsPage.selectPostVisibility();
        postsPage.writePostMessage();
        postsPage.submitPost();
        postsPage.validatePostCreated();
    }

    @Test
    @Label("Jira FPW-83")
    @Tag("HappyPath")
    public void editPublicPost_when_editPostButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        postsPage.clickEditPost();
        postsPage.selectPostVisibility();
        postsPage.editPostMessage();
        postsPage.submitPost();
        postsPage.validatePostEdited();
    }

    @Test
    @Label("Jira FPW-94")
    @Tag("HappyPath")
    @Tag("SmokeTest")
    public void likePublicPost_when_likeButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.likePostByUsername(usernameRandom);
        postsPage.validatePostLiked();
    }

    @Test
    @Label("Jira FPW-96")
    @Tag("HappyPath")
    @Tag("SmokeTest")
    public void dislikePublicPost_when_dislikeButtonClicked() {
        api.likePost(usernameRandom, passwordRandom, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.dislikePostByUsername(usernameRandom);
        postsPage.validatePostDisliked();
    }

    @Test
    @Label("Jira FPW-85")
    @Tag("HappyPath")
    public void deletePost_when_deleteButtonClicked() {
        api.createPost(usernameRandom, passwordRandom, postBody);
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        postsPage.clickDeletePost();
        postsPage.confirmDeletion();
        postsPage.submitDeletion();
        postsPage.validatePostDeleted();
    }
}




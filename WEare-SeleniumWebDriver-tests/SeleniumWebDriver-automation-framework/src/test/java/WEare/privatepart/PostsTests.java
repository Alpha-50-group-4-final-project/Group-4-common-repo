package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostsTests extends BaseTest {
    private static String postBody = getUIMappingByKey("postPage.postMessage");

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postBody);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public void returnHome() {
//        homePage.navigateToHomePage();
//        if (actions.isElementVisible("homePage.LogoutButton")) {
//            actions.clickElement("homePage.LogoutButton");
//        }

        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-70")
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
    public void likePublicPost_when_likeButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.likePostByUsername(usernameRandom);
        postsPage.validatePostLiked();
    }

    @Test
    @Label("Jira FPW-96")
    public void dislikePublicPost_when_dislikeButtonClicked() {
        api.likePost(usernameRandom, passwordRandom, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.dislikePostByUsername(usernameRandom);
        postsPage.validatePostDisliked();
    }

    @Test
    @Label("Jira FPW-85")
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




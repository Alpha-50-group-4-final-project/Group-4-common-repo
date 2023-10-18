package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.annotation.Testable;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@Testable
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostsTests extends BaseTest {
    private static final String postBody = getUIMappingByKey("postPage.postMessage");

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postBody);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public void returnHome() {
        homePage.navigateToHomePage();
        if (actions.isElementVisible("homePage.LogoutButton")) {
            actions.clickElement("homePage.LogoutButton");
        }

        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-70")
    @Tag("HappyPath")
    @DisplayName("Create a New Public Post")
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
    @DisplayName("Validate the User Can Edit His Public Post")
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
    @DisplayName("Validate User Can Like a Public Post")
    public void likePublicPost_when_likeButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.likePostByUsername(usernameRandom);
        postsPage.validatePostLiked();
    }

    @Test
    @Label("Jira FPW-96")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Unlike a Public Post")
    public void dislikePublicPost_when_dislikeButtonClicked() {
        api.likePost(usernameRandom, passwordRandom, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.dislikePostByUsername(usernameRandom);
        postsPage.validatePostDisliked();
    }

    @Test
    @Label("Jira FPW-85")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Delete His Public PostValidate User Can Delete His Public Post")
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




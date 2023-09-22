package WEare;

public class PublicPartTests extends BaseTest {

    public void viewLandingPageWithLinks_when_homePageOpened(){

        //assert registration,login, profile search, posts are accessable
    }
    public void viewPublicPosts_when_latestPostsClicked(){
        homePage.navigateToLatestPosts();
        homePage.assertPageNavigated();
        latestPostPage.assertPublicPostShown();
    }

    public void viewPublicProfiles_when_searchProfilesPerformed(){}
}

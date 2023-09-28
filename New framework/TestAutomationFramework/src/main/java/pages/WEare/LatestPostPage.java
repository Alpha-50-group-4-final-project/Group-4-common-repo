package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class LatestPostPage extends WEareBasePage {
    public LatestPostPage(WebDriver driver){
        super(driver,"latestPostsPage");
    }

    public void assertPublicPostOrdered() {
        //by date: //span[@class='seen']
        //by rank://span[contains(@id, 'rank')]

           List<WebElement> rankList = driver.findElements(By.xpath("//span[@class='seen']"));
           List<String> ranks = rankList.stream().map(n -> n.getText()).collect(Collectors.toList());
           List<String> unsortedRanks = List.copyOf(ranks);
           Collections.sort(ranks);
           Assertions.assertTrue(ranks.equals(unsortedRanks), "Public posts were not ordered by date.");
           LOGGER.info("Public posts ordered by date.");
    }

    public void clickExplorePost(){
        actions.clickElement("latestPosts.exploreThisPost");
    }

    public void clickSeeProfile(){
        actions.clickElement("latestPosts.seeProfile");
    }


}

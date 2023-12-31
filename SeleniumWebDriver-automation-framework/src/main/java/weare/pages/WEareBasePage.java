package weare.pages;

import com.telerikacademy.testframework.pages.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.LOGGER;

public abstract class WEareBasePage extends BasePage {

    public WEareBasePage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }

    public void validateHeader(String message){
        actions.waitForElementVisible("page.headers");
        try {
            actions.waitForElementVisible("page.headers");
            actions.waitForElementPresent("page.headers");
            actions.assertElementText("page.headers",message);
        LOGGER.info("Page accessible and navigated successfully.");
        } catch  (AssertionFailedError e) {
            Assertions.fail("Page was not navigated successfully.");
        }
   }
}

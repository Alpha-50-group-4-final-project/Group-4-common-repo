package pages.WEare;

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
        try {
            actions.assertElementAttribute("page.headers", "innerHTML", message);
        LOGGER.info("Page accessible and navigated successfully.");
        } catch  (AssertionFailedError e) {
            Assertions.fail("Page was not navigated successfully.");
        }
   }
}

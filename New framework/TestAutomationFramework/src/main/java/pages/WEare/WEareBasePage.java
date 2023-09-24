package pages.WEare;

import com.telerikacademy.testframework.pages.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.telerikacademy.testframework.Utils.LOGGER;

public abstract class WEareBasePage extends BasePage {

    public WEareBasePage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }

    public void validateHeader(String message){
        WebElement pageMessage = driver.findElement(By.xpath("//h1[contains(@class, 'bread')]"));
        actions.waitForElementVisible("Weare.pageHeaders", message);
        Assertions.assertEquals(message, pageMessage.getAttribute("innerText"), "Page message doesn't match. Expected: " + message + ". Actual: " + pageMessage.getText());
        LOGGER.info("Page accessible and navigated successfully.");
    }
}

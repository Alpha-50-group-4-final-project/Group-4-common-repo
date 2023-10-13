package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class UserRegistrationPage extends WEareBasePage {

    public UserRegistrationPage(WebDriver driver) {
        super(driver, "registerPage");
    }

    public void fillUsernameField(String username) {
        actions.waitForElementPresent("userRegistrationPage.UsernameField");
        actions.typeValueInField(username, "userRegistrationPage.UsernameField");
    }

    public void fillEmailField(String username) {
        String email = username + "@abv.bg";
        actions.typeValueInField(email, "userRegistrationPage.EmailField");
    }

    public void fillPasswordFields(String password) {
        actions.typeValueInField(password, "userRegistrationPage.PasswordField");
        actions.typeValueInField(password, "userRegistrationPage.ConfirmPasswordField");
    }

    public void selectCategoryField() {
        actions.clickElement("userRegistrationPage.ProfessionalCategoryButton");
        List<WebElement> itemsDropDown = driver.findElements(By.xpath("//option"));
        int maxSize = itemsDropDown.size();
        Random random = new Random();
        int randomCategory = random.nextInt(maxSize);
        itemsDropDown.get(randomCategory).click();
    }

    public void clickRegistryButton() {
        actions.clickElement("userRegistrationPage.RegisterButton");
    }

    public void assertUserCreatedWithWelcomeText() {
        try {
            actions.waitForElementVisible("RegisterLoginSuccessMessage");
            actions.waitForElementVisible("RegisterLoginSuccessMessage");
            actions.assertElementText("RegisterLoginSuccessMessage","Welcome to our community.");
            LOGGER.info(getConfigPropertyByKey("User was successfully registered. Welcome message displayed."));
        } catch (Exception e) {
            Assertions.fail("Registration was not successful.");
        }

    }

    public void validateRegistryNotSuccessful() {
        try {
            actions.assertElementAttribute("userRegistrationPage.RegisterButton", "value", "Register");
            LOGGER.info("User was not registered. Proper error message shown.");
        } catch (Exception e) {
            Assertions.fail("Registration was successful although registration criteria  not met.");
            LOGGER.info("Registration test fails");
        }
        //getElementByID("username").value==null
    }


}
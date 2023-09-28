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
        actions.waitForElementPresent("WEare.userRegistrationPage.UsernameField");
        actions.typeValueInField(username, "WEare.userRegistrationPage.UsernameField");
    }

    public void fillEmailField(String username) {
        String email = username + "@abv.bg";
        actions.typeValueInField(email, "WEare.userRegistrationPage.EmailField");
    }
    public void leaveEmailEmpty(String email){
        actions.typeValueInField(email, "WEare.userRegistrationPage.EmailField");
    }

    public void fillPasswordFields(String password) {
        actions.typeValueInField(password, "WEare.userRegistrationPage.PasswordField");
        actions.typeValueInField(password, "WEare.userRegistrationPage.ConfirmPasswordField");
    }

    public void selectCategoryField() {
        actions.clickElement("WEare.userRegistrationPage.ProfessionalCategoryButton");
        List<WebElement> itemsDropDown = driver.findElements(By.xpath("//option"));
        int maxSize = itemsDropDown.size();
        Random random = new Random();
        int randomCategory = random.nextInt(maxSize);
        itemsDropDown.get(randomCategory).click();
    }

    public void clickRegistryButton() {
        actions.clickElement("WEare.userRegistrationPage.RegisterButton");
    }

    public void assertUserCreatedWithWelcomeText() {
        try {
            actions.assertElementPresent("WEare.RegisterLoginSuccessMessage");
            LOGGER.info(getConfigPropertyByKey("User was successfully registered. Welcome message displayed."));
        } catch (Exception e) {
            Assertions.fail("Registration was not successful.");
        }

    }

    public void validateRegistryNotSuccessful() {
        try {
            actions.assertElementAttribute("WEare.userRegistrationPage.RegisterButton", "value", "Register");
            LOGGER.info("User was not registered when a field was left empty.");
        } catch (Exception e) {
            Assertions.fail("Registration was successful although criterials were not met.");
        }
        //getElementByID("username").value==null
    }

    public void validateErrorMessageForInvalidData() {
        try {
            actions.assertElementPresent("WEare.RegisterErrorMessage.Username");
            LOGGER.info("User was not registered: username requires no whitespaces, only character");
        } catch (Exception e) {
            Assertions.fail("Proper error message not presented when invalid username provided");
        }

        //String expectedErrorMsg = "Password should be at least 8 characters long";
        //WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Password should be at least 8 characters long')]"));
        //String actualErrorMsg = exp.getText();
        //Assertions.assertEquals(actualErrorMsg, expectedErrorMsg);
    }


}

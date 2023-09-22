package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class UserRegistrationPage extends WEareBasePаge {

    public UserRegistrationPage(WebDriver driver){
        super(driver,"WEare.registerPage");
    }

    public void registerNewUser(String username,String password){
        //driver.get(url);
        String email=username+"anjibon@abv.bg";
        actions.waitForElementPresent("WEare.newuserregistrationpage.UsernameField");
        actions.typeValueInField(username,"WEare.newuserregistrationpage.UsernameField");
        actions.typeValueInField(email,"WEare.newuserregistrationpage.EmailField");
        actions.typeValueInField(password,"WEare.newuserregistrationpage.PasswordField");
        actions.typeValueInField(password,"WEare.newuserregistrationpage.ConfirmPasswordField");
        actions.clickElement("WEare.newuserregistrationpage.ProfessionalCategoryButton");
        actions.clickElement("WEare.newuserregistrationpage.ProfessionalCategoryDropDown","Baker");
        actions.clickElement("WEare.newuserregistrationpage.RegisterButton");
    }

    public void assertUserCreatedWithWelcomeText(){
        try{
            actions.assertElementPresent("WEare.RegisterLoginSuccessMessage");
            LOGGER.info(getConfigPropertyByKey("WEare.RegisterLoginSuccessMessage"));
        } catch (Exception e) {
            Assertions.fail("Registration was not successful.");
        }

    }
    public void validateErrorMessageForEmptyField(){
        try{
            actions.assertElementPresent("");
            LOGGER.info(getConfigPropertyByKey(""));
        } catch (Exception e) {
            Assertions.fail("Proper error message not presented when empty field provided");
        }

        //getElementByID("username").value==null
    }
    public void validateErrorMessageForInvalidUsername(){
        try{
            actions.assertElementPresent("WEare.RegisterErrorMessage.Username");
            LOGGER.info("username requires no whitespaces, only character");
        } catch (Exception e) {
            Assertions.fail("Proper error message not presented when invalid username provided");
        }

        //String expectedErrorMsg = "Password should be at least 8 characters long";
        //WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Password should be at least 8 characters long')]"));
        //String actualErrorMsg = exp.getText();
        //Assertions.assertEquals(actualErrorMsg, expectedErrorMsg);
    }



}

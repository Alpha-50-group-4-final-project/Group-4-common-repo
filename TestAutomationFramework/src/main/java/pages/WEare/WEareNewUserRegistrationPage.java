package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomUsername;

public class WEareNewUserRegistrationPage extends WEareBasePAge{

    public WEareNewUserRegistrationPage(WebDriver driver){
        super(driver,"");
    }

    public void registerNewUser(String username,String password){

        String email="ivan@abv.bg";

        actions.waitForElementPresent("WEare.homepageRegisterButton");
        actions.clickElement("WEare.homepageRegisterButton");

        actions.waitForElementPresent("WEare.registrationFormUsernameFIeld");
        actions.typeValueInField(username,"WEare.registrationFormUsernameFIeld");

        actions.waitForElementPresent("WEare.registrationForEmailField");
        actions.typeValueInField(email,"WEare.registrationForEmailField");

        actions.waitForElementPresent("WEare.registrationPasswordField","WEare.registrationPasswordField");
        actions.typeValueInField(password,"WEare.registrationPasswordField");

        actions.waitForElementPresent("WEare.registrationFormConfirmPasswordField");
        actions.typeValueInField(password,"WEare.registrationFormConfirmPasswordField");

        actions.waitForElementPresent("WEare.registrationFormProfessionalCategoryButton");
        actions.clickElement("WEare.registrationFormProfessionalCategoryButton");

        actions.waitForElementVisible("WEare.registrationFOrmProfessionalCategoryDropDown","Baker");
        actions.clickElement("WEare.registrationFOrmProfessionalCategoryDropDown","Baker");

        actions.waitForElementVisible("WEare.registrationFormRegisterButton");
        actions.clickElement("WEare.registrationFormRegisterButton");
    }

}

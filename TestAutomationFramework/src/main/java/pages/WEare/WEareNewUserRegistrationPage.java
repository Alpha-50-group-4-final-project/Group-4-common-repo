package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomUsername;

public class WEareNewUserRegistrationPage extends WEareBasePAge{

    public WEareNewUserRegistrationPage(WebDriver driver){
        super(driver,"");
    }

    public void registerNewUser(String username,String password){

        String email=username+"@abv.bg";

        actions.waitForElementPresent("WEare.homepageRegisterButton");
        actions.clickElement("WEare.homepageRegisterButton");

        actions.waitForElementPresent("WEare.newuserregistrationpage.UsernameField");
        actions.typeValueInField(username,"WEare.newuserregistrationpage.UsernameField");

        actions.waitForElementPresent("WEare.newuserregistrationpage.EmailField");
        actions.typeValueInField(email,"WEare.newuserregistrationpage.EmailField");

        actions.waitForElementPresent("WEare.newuserregistrationpage.PasswordField");
        actions.typeValueInField(password,"WEare.newuserregistrationpage.PasswordField");

        actions.waitForElementPresent("WEare.newuserregistrationpage.ConfirmPasswordField");
        actions.typeValueInField(password,"WEare.newuserregistrationpage.ConfirmPasswordField");

        actions.waitForElementPresent("WEare.newuserregistrationpage.ProfessionalCategoryButton");
        actions.clickElement("WEare.newuserregistrationpage.ProfessionalCategoryButton");

        actions.waitForElementVisible("WEare.newuserregistrationpage.ProfessionalCategoryDropDown","Baker");
        actions.clickElement("WEare.newuserregistrationpage.ProfessionalCategoryDropDown","Baker");

        actions.waitForElementVisible("WEare.newuserregistrationpage.RegisterButton");
        actions.clickElement("WEare.newuserregistrationpage.RegisterButton");
    }

}

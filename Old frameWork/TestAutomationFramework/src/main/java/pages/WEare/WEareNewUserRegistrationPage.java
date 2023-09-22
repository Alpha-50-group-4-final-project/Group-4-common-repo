package pages.WEare;

import org.openqa.selenium.WebDriver;

public class WEareNewUserRegistrationPage extends WEareBasePAge{

    public WEareNewUserRegistrationPage(WebDriver driver){
        super(driver,"");
    }

    public void registerNewUser(String username,String password){

        String email=username+"@abv.bg";

        actions.waitForElementPresent("WEare.homepage.RegisterButton");
        actions.clickElement("WEare.homepage.RegisterButton");

        actions.waitForElementPresent("WEare.userRegistrationPage.UsernameField");
        actions.typeValueInField(username, "WEare.userRegistrationPage.UsernameField");

        actions.waitForElementPresent("WEare.userRegistrationPage.EmailField");
        actions.typeValueInField(email, "WEare.userRegistrationPage.EmailField");

        actions.waitForElementPresent("WEare.userRegistrationPage.PasswordField");
        actions.typeValueInField(password, "WEare.userRegistrationPage.PasswordField");

        actions.waitForElementPresent("WEare.userRegistrationPage.ConfirmPasswordField");
        actions.typeValueInField(password, "WEare.userRegistrationPage.ConfirmPasswordField");

        actions.waitForElementPresent("WEare.userRegistrationPage.ProfessionalCategoryButton");
        actions.clickElement("WEare.userRegistrationPage.ProfessionalCategoryButton");

        actions.waitForElementVisible("WEare.userRegistrationPage.ProfessionalCategoryDropDown","Baker");
        actions.clickElement("WEare.userRegistrationPage.ProfessionalCategoryDropDown","Baker");

        actions.waitForElementVisible("WEare.userRegistrationPage.RegisterButton");
        actions.clickElement("WEare.userRegistrationPage.RegisterButton");
    }

}

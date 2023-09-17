package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.RandomPasswordGenerator.randomPassword;

public class WEareNewUserRegistrationPage extends WEareBasePAge{

    public WEareNewUserRegistrationPage(WebDriver driver){
        super(driver,"");
    }

    public void registerNewUser(){
        actions.waitForElementPresent("WEare.homepageRegisterButton");
        actions.clickElement("WEare.homepageRegisterButton");

        actions.waitForElementPresent("WEare.registrationFormUsernameFIeld");
        actions.typeValueInField("Peshaka","WEare.registrationFormUsernameFIeld");

        actions.waitForElementPresent("WEare.registrationForEmailField");
        actions.typeValueInField("gosho@abv.bg","WEare.registrationForEmailField");

        actions.waitForElementPresent("WEare.registrationPasswordField","WEare.registrationPasswordField");
        actions.typeValueInField(randomPassword(),"WEare.registrationPasswordField");

        actions.waitForElementPresent("WEare.registrationFormConfirmPasswordField");
        actions.typeValueInField("123Pass2","WEare.registrationFormConfirmPasswordField");

        actions.waitForElementPresent("WEare.registrationFormProfessionalCategoryButton");
        actions.clickElement("WEare.registrationFormProfessionalCategoryButton");

        actions.waitForElementVisible("WEare.registrationFOrmProfessionalCategoryDropDown","Baker");
        actions.clickElement("WEare.registrationFOrmProfessionalCategoryDropDown","Baker");

        actions.waitForElementVisible("WEare.registrationFormRegisterButton");
        actions.clickElement("WEare.registrationFormRegisterButton");
    }

}

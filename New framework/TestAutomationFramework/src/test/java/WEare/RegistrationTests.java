package WEare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegistrationTests extends  BaseTest {

    @Test
    public void registerNewUserTest_when_validCredentialsPassed() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
    }
    @ParameterizedTest
    @CsvSource({ "а", "UserTest", "UserTest", "itIsBoundryUpperleveltest"})
    public void registerNewUser_when_invalidUsernamePassed(String username){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(username);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        //registrationPage.validateErrorMessageForInvalidUsername();
    }
    @Test
    public void registerNewUser_when_emptyUsernamePassed(){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField("");
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotDoneForEmptyField();
    }
    //notReadY:
    @Test
    public void registerNewUser_when_invalidEmailPassed(){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField("");
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotDoneForEmptyField();
    }
    @Test
    public void registerNewUser_when_emptyEmailPassed(){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField("");
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotDoneForEmptyField();
    }
    @ParameterizedTest
    @CsvSource({ "12345", "123456", "12345678","123456789"})
    public void registerNewUser_when_invalidPasswordPassed(String password){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(password);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        //registrationPage.validateErrorMessageForInvalidUsername();
    }
    @Test
    public void registerNewUser_when_emptyPasswordPassed(){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields("");
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotDoneForEmptyField();
    }
    @Test
    public void registerNewUser_when_optionalField_emptyDropdownPassed(){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(usernameRandom);
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
    }
}

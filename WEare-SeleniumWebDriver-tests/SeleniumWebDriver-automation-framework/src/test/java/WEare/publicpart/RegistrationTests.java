package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class RegistrationTests extends BaseTest {

    @Test
    public void registerNewUserTest_when_validCredentialsPassed() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        usernames.add(usernameRandom);
    }
    @ParameterizedTest
    @CsvSource({ "а", "''"})
    public void registerNewUser_when_invalidUsernamePassed(String username){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(username);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotSuccessful();
    }

    @ParameterizedTest
    @CsvSource({ "anna@sd.comsa", "''"})
    public void registerNewUser_when_invalidEmailPassed(String email){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotSuccessful();
    }

    @ParameterizedTest
    @CsvSource({ "12345", "''"})
    public void registerNewUser_when_invalidPasswordPassed(String password){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(password);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotSuccessful();
    }
    @Test
    public void registerNewUser_when_optionalField_emptyDropdownPassed(){
        String name=faker.name().firstName();
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(name);
        registrationPage.fillEmailField(name);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        LOGGER.info("User was successfully registered.");
        usernames.add(name);
    }

}

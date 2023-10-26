package WEare.publicpart;

import WEare.BaseTest;
import io.qameta.allure.Description;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static weare.database.manipulation.BaseSetup.freshUsernames;

@DisplayName("RegistrationTests")
public class RegistrationTests extends BaseTest {
    @Test
    @Label("Jira FPW-4")
    @Tag("HappyPath")
    @DisplayName("User registration with valid email, username, password (letters)")
    public void registerNewUserTest_when_validCredentialsPassed() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        freshUsernames.add(usernameRandom);
    }

    @ParameterizedTest
    @CsvSource({ "а", "''"})
    @Label("Jira FPW-6, Jira FPW-10")
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid username")
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
    @Label("Jira FPW-11, Jira FPW-13")
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid email")
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
    @Label("Jira FPW-14, Jira FPW-18")
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid password")
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
    @Label("Jira FPW-19")
    @Tag("UnHappyPath")
    @DisplayName("Register new user without choosing option.")
    public void registerNewUser_when_optionalField_emptyDropdownPassed(){
        String name=faker.name().firstName();
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(name);
        registrationPage.fillEmailField(name);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        LOGGER.info("User was successfully registered.");
        freshUsernames.add(usernameRandom);
    }
    @Test
    @Label("Jira FPW-9")
    @Tag("UnHappyPath")
    @DisplayName("User registration with invalid credentials -username already used for registration")
    public void registerNewUser_when_usernameAlreadyExist(){
        api.registerUser(usernameRandom,passwordRandom);
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.validateRegistryNotSuccessful();
        registrationPage.validateRegistrationErrorMessage("User with this username already exist");
    }
}

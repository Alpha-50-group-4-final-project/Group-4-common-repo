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
@DisplayName("RegistrationTests")
public class RegistrationTests extends BaseTest {
    @Tag("Happypath")
    @Tag("SmokeTest")
    @DisplayName("User registration with valid email, username, password (letters)")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-4")
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
    @CsvSource({ "а", "''"})
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid username")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-4, Jira FPW-10")
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
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid email")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-11, Jira FPW-13")
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
    @Tag("UnHappyPath")
    @DisplayName("Register new user with invalid password")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-14, Jira FPW-15")
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
    @Tag("UnHappyPath")
    @DisplayName("Register new user without choosing option.")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-14, Jira FPW-15")
    public void registerNewUser_when_optionalField_emptyDropdownPassed(){
        String name=faker.name().firstName();
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(name);
        registrationPage.fillEmailField(name);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        LOGGER.info("User was successfully registered.");
    }
    @Test
    @Tag("UnHappyPath")
    @DisplayName("User registration with invalid credentials -username already used for registration")
    @Description("As a user I want to register to use app's full list of features")
    @Label("Jira FPW-9")
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

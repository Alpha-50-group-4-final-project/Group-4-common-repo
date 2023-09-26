package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.*;

public class LoginTests extends BaseTest {


    @BeforeAll
    public static void registerUser_when_ValidCredentialsArePassed() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
    }


    @Test
    public void login_when_ValidCredentialsArePassed() {
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(usernameRandom);
        loginPage.fillPasswordField(passwordRandom);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("WEare.homePage.LogoutButton");
        loginPage.clickOnLogOutButton();
    }

    @ParameterizedTest
    @CsvSource({"T st,Valid_123", "Testbot,vald12", "'',vald12", "Testbot,''", "'',''"})
    public void login_When_InvalidCredentialsArePassed(String username, String password) {
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertErrorMessage("Wrong username or password.");
    }
}

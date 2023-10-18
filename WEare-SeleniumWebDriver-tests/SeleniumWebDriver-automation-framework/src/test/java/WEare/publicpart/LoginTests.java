package WEare.publicpart;

import WEare.BaseTest;

import jdk.jfr.Label;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTests extends BaseTest {

    @BeforeAll
    public static void registerUser_when_ValidCredentialsArePassed() {
       api.registerUser(usernameRandom,passwordRandom);
    }
    @BeforeEach
    public  void takeMeToLoginForm(){
        homePage.navigateToPage();
        homePage.navigateToLoginPage();
    }

    @Test
    @Label("Jira FPW-23")
    @DisplayName("Login with valid credentials")
    @Tag("HappyPath")
    public void login_when_validCredentialsArePassed() {
        loginPage.fillUsername(usernameRandom);
        loginPage.fillPassword(passwordRandom);
        loginPage.submitForm();
        loginPage.validateLoggedIn();
        loginPage.clickOnLogOutButton();
    }

    @ParameterizedTest
    @Label("Jira FPW-24, FPW-25, FPW-26, FPW-27, FPW-28")
    @CsvSource({"T st,Valid_123", "Testbot,vald12", "'',vald12", "Testbot,''", "'',''"})
    @DisplayName("Login with invalid credentials")
    @Tag("UnHappyPath")
    public void login_when_invalidCredentialsArePassed(String username, String password) {
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.submitForm();
        loginPage.validateErrorMessage();
    }
}

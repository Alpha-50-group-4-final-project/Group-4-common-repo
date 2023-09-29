package WEare.publicpart;

import WEare.BaseTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



public class LoginTests extends BaseTest {


    @BeforeAll
    public static void registerUser_when_ValidCredentialsArePassed() {
       registerUser(usernameRandom,passwordRandom);
    }

    @Test
    public void login_when_ValidCredentialsArePassed() {
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(usernameRandom);
        loginPage.fillPasswordField(passwordRandom);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("homePage.LogoutButton");
        loginPage.clickOnLogOutButton();
        System.out.println("Login has been successfull.");
    }

    @ParameterizedTest
    @CsvSource({"T st,Valid_123", "Testbot,vald12", "'',vald12", "Testbot,''", "'',''"})
    public void login_When_InvalidCredentialsArePassed(String username, String password) {
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertErrorMessage("Wrong username or password.");
        System.out.println("Expected error message showed up.");
    }
}

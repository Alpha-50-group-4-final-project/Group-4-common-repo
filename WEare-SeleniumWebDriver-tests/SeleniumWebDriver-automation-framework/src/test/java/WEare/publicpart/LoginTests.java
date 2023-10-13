package WEare.publicpart;

import WEare.BaseTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



public class LoginTests extends BaseTest {


    @BeforeAll
    public static void registerUser_when_ValidCredentialsArePassed() {
       api.registerUser(usernameRandom,passwordRandom);
    }
    @BeforeEach
    public  void takeMeToHomePage(){
        actions.navigateToPage("http://localhost:8081/");
        loginPage.clickOnLoginButton();
    }

    @Test
    public void login_when_validCredentialsArePassed() {


        loginPage.fillUsernameField(usernameRandom);
        loginPage.fillPasswordField(passwordRandom);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("homePage.LogoutButton");
        loginPage.clickOnLogOutButton();
        System.out.println("Login has been successful.");
    }

    @ParameterizedTest
    @CsvSource({"T st,Valid_123", "Testbot,vald12", "'',vald12", "Testbot,''", "'',''"})
    public void login_when_invalidCredentialsArePassed(String username, String password) {
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertErrorMessage("Wrong username or password.");
        System.out.println("Expected error message showed up.");
    }


}

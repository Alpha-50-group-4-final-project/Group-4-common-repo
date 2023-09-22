package WEare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.*;

public class LoginTests extends BaseTest {
 private static  final    String testName=randomUsername();
  private static final   String testPass=randomPassword();

   @BeforeAll
   public  static  void  registerUser(){
       homePage.navigateToRegisterPage();
       registrationPage.fillUsernameField(usernameRandom);
       registrationPage.fillEmailField(usernameRandom);
       registrationPage.fillPasswordFields(passwordRandom);
       registrationPage.selectCategoryField();
       registrationPage.clickRegistryButton();
   }
    @BeforeEach
    public  void navigateToTheHomePage(){
        actions.goToHomePage();
        loginPage.clickOnLoginButton();
    }

    @Test
    public  void  loginWithValidCredentials(){
        loginPage.fillUsernameField(testName);
        loginPage.fillPasswordField(testPass);
        loginPage.clickOnCreateButton();

        actions.assertElementPresent("WEare.homePage.LogoutButton");
        LOGGER.info("User successfully logged in when provided valid credentials.");
        loginPage.clickOnLogOutButton();
    }

    @ParameterizedTest
    @CsvSource({ "T st,Valid_123", "Testbot,vald12", "'',vald12","Testbot,''","'',''"})
    public void login_When_InvalidCredentialsArePassed(String username,String password){
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnCreateButton();
        actions.assertElementText("WEare.RegisterLoginErrorMessage","Wrong username or password.");
        LOGGER.info("Correct error message is shown.");
    }
}

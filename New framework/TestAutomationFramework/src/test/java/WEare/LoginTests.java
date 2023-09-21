package WEare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.UserRegistrationPage;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.*;

public class LoginTests extends BaseTest {

//these two we already have at the base test - named usernameRandom and passwordRandom
 private static  final    String testName=randomUsername();
  private static final   String testPass=randomPassword();

  //the previous initialization of the page below will not work as the method is static
   @BeforeAll
   public  static  void  registerUser(){
       UserRegistrationPage register=new UserRegistrationPage(actions.getDriver());
       register.registerNewUser(testName,testPass);
   }
    @BeforeEach
    public  void navigateToTheHomePage(){
        actions.goToHomePage();
        login.clickOnLoginButton();
    }

    @Test
    public  void  loginWithValidCredentials(){
        login.fillUsernameField(testName);
        login.fillPasswordField(testPass);
        login.clickOnCreateButton();

        actions.assertElementPresent("WEare.homePage.LogoutButton");
        LOGGER.info("User successfully logged in when provided valid credentials.");
        login.clickOnLogOutButton();
    }

    @ParameterizedTest
    @CsvSource({ "T st,Valid_123", "Testbot,vald12", "'',vald12","Testbot,''","'',''"})
    public void login_When_InvalidCredentialsArePassed(String username,String password){
        login.fillUsernameField(username);
        login.fillPasswordField(password);
        login.clickOnCreateButton();
        actions.assertElementText("WEare.RegisterLoginErrorMessage","Wrong username or password.");
        LOGGER.info("Correct error message is shown.");
    }
}

package WEare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTests extends BaseTest {



    @BeforeEach
    public  void navigateToTheHomePage(){
        actions.goToHomePage();
        login.clickOnLoginButton();
    }
    @Test
    public  void  loginWithValidCredentials(){

        login.fillUsernameField("Pesho");
        login.fillPasswordField("123pass");
        login.clickOnCreateButton();
        login.clickOnLogOutButton();
    }

    @ParameterizedTest
    @CsvSource({ "T st,Valid_123", "Testbot,vald12", "'',vald12","Testbot,''","'',''"})
    public void login_When_InvalidCredentialsArePassed(String username,String password){
        login.clickOnLoginButton();
        login.fillUsernameField(username);
        login.fillPasswordField(password);
        login.clickOnCreateButton();
    }
}

package WEare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegistrationTests extends  BaseTest {

    @Test
    public void registerNewUserTest_when_validCredentialsPassed() {
        homePage.navigateToRegisterPage();
        registerUser.registerNewUser(usernameRandom, passwordRandom);
        registerUser.assertUserCreatedWithWelcomeText();
    }
    @ParameterizedTest
    @CsvSource({ "а", "itIsBoundryUpperleveltest"})
    public void registerNewUser_when_invalidUsernamePassed(String username){
        homePage.navigateToRegisterPage();
        registerUser.registerNewUser(username, passwordRandom);
        registerUser.validateErrorMessageForInvalidUsername();
    }
    @Test
    public void registerNewUser_when_emptyUsernamePassed(){
        homePage.navigateToRegisterPage();
        registerUser.registerNewUser("",passwordRandom);
        //assertion
    }
    @Test
    public void registerNewUser_when_invalidEmailPassed(String email){
        homePage.navigateToRegisterPage();
    }
    @Test
    public void registerNewUser_when_emptyEmailPassed(){
        homePage.navigateToRegisterPage();

    }
    @ParameterizedTest
    @CsvSource({ "12345", "aa", "itIsBoundryUpperlevel"})
    public void registerNewUser_when_invalidPasswordPassed(String password){
        homePage.navigateToRegisterPage();
        registerUser.registerNewUser(usernameRandom, password);
        registerUser.assertUserCreatedWithWelcomeText();
    }
    @Test
    public void registerNewUser_when_emptyPasswordPassed(){
        homePage.navigateToRegisterPage();
        registerUser.registerNewUser(usernameRandom, "");
        //assertion
    }

    //invalid dropdown? optional field
    @Test
    public void registerNewUser_when_invalidDropdownPassed(){
        homePage.navigateToRegisterPage();

    }
}

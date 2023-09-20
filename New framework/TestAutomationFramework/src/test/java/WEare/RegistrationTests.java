package WEare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegistrationTests extends  BaseTest {

    @Test
    public void registerNewUserTest_when_validCredentialsPassed() {
        registerNewUser.registerNewUser(usernameRandom, passwordRandom);
        registerNewUser.assertUserCreated();
    }
    @ParameterizedTest
    @CsvSource({ "а", "aa", "itIsBoundryUpperlevel"})
    public void registerNewUser_when_invalidUsernamePassed(String username){
        registerNewUser.registerNewUser(username, passwordRandom);
        registerNewUser.assertUserCreated();

    }
    @Test
    public void registerNewUser_when_emptyUsernamePassed(){
        registerNewUser.registerNewUser("",passwordRandom);
        registerNewUser.assertUserCreated();
    }
    @Test
    public void registerNewUser_when_invalidEmailPassed(String email){
    }
    @Test
    public void registerNewUser_when_emptyEmailPassed(){

    }
    @ParameterizedTest
    @CsvSource({ "12345", "aa", "itIsBoundryUpperlevel"})
    public void registerNewUser_when_invalidPasswordPassed(String password){
    }
    @Test
    public void registerNewUser_when_emptyPasswordPassed(){
        registerNewUser.registerNewUser(usernameRandom, "");
        registerNewUser.assertUserCreated();
    }

    //invalid dropdown? could it be?
    @Test
    public void registerNewUser_when_invalidDropdownPassed(){

    }
}

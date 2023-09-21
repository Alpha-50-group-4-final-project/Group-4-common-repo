package WEare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegistrationTests extends  BaseTest {

    @Test
    public void registerNewUserTest_when_validCredentialsPassed() {
        registerUser.registerNewUser(usernameRandom, passwordRandom);
        registerUser.assertUserCreated();
    }
    @ParameterizedTest
    @CsvSource({ "а", "aa", "itIsBoundryUpperlevel"})
    public void registerNewUser_when_invalidUsernamePassed(String username){
        registerUser.registerNewUser(username, passwordRandom);
        registerUser.assertUserCreated();

    }
    @Test
    public void registerNewUser_when_emptyUsernamePassed(){
        registerUser.registerNewUser("",passwordRandom);
        registerUser.assertUserCreated();
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
        registerUser.registerNewUser(usernameRandom, password);
        registerUser.assertUserCreated();
    }
    @Test
    public void registerNewUser_when_emptyPasswordPassed(){
        registerUser.registerNewUser(usernameRandom, "");
        registerUser.assertUserCreated();
    }

    //invalid dropdown? could it be?
    @Test
    public void registerNewUser_when_invalidDropdownPassed(){

    }
}

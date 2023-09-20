package WEare;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegistrationTests extends  BaseTest {

    @ParameterizedTest
    @CsvSource({ "Bork,123Password_","Dinko,123."})
    public void registerNewUserTest_When_validCredentials_arePassed(String username,String password) {
        registerNewUser.registerNewUser(username, password);
        registerNewUser.assertUserCreated();
    }
}

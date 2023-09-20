package WEare;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.WEareCreateNewPostPage;
import pages.WEare.WEareLoginPage;
import pages.WEare.WEareNewUserRegistrationPage;

public class WEareTests extends BaseTest {


    @Test
    public void loginS() {
        registerNewUser.registerNewUser(usernameRandom, passwordRandom);

        login.login(usernameRandom,passwordRandom);
    }

    @Test
    public void createNewPost(){
        registerNewUser.registerNewUser(usernameRandom, passwordRandom);

        login.login(usernameRandom,passwordRandom);

        post.createNewPost();
    }

//    comment
}


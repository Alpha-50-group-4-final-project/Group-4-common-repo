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
        registerNewUser.registerNewUser(username, passsword);

        login.login(username,passsword);
    }

    @Test
    public void createNewPost(){
        registerNewUser.registerNewUser(username, passsword);

        login.login(username,passsword);

        post.createNewPost();
    }

//    comment
}


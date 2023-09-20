package WEare;


import org.junit.jupiter.api.Test;

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


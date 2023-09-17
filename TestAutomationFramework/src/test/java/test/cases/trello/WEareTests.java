package test.cases.trello;

import org.junit.Test;
import pages.WEare.WEareHomePage;

public class WEareTests extends BaseTest {




    @Test
    public void test(){
        WEareHomePage homePage=new WEareHomePage(actions.getDriver());
        homePage.registerNewUser();
    }
}


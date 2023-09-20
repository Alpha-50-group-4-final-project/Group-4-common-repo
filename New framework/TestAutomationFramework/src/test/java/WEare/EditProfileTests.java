package WEare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.WEare.LoginPage;
import pages.WEare.UserRegistrationPage;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class EditProfileTests extends BaseTest {


    private static final String testName = randomUsername();
    private static final String testPass = randomPassword();

    @BeforeAll
    public static   void testSetup(){
        UserRegistrationPage register = new UserRegistrationPage(actions.getDriver());
        register.registerNewUser(testName, testPass);
        actions.goToHomePage();
        LoginPage login = new LoginPage(actions.getDriver());
        login.clickOnLoginButton();
        login.fillUsernameField(testName);
        login.fillPasswordField(testPass);
        login.clickOnCreateButton();
        actions.assertElementPresent("WEare.homePage.LogoutButton");
        actions.goToHomePage();
    }



    @Test
    public void editFirstnameLastNameBirday_when_validDataIsProvided() {

        profileEdit.navigateToEditProfileMenu();
        profileEdit.fillUpFirstNameField("Boris");
        profileEdit.fillUpLastNameField("Yurukov");
        profileEdit.fillBirtdayField("01", "15", "2023");

        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField","value","Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField","value","Yurukov");
        actions.assertElementAttribute("Eare.PersonalProfilePageBirthdayField","value","2023-01-15");
    }
}

package WEare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.LoginPage;
import pages.WEare.UserRegistrationPage;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class EditProfileTests extends BaseTest {


    private static final String testName = randomUsername();
    private static final String testPass = randomPassword();

    @BeforeAll
    public static void testSetup() {
        UserRegistrationPage register = new UserRegistrationPage(actions.getDriver());
        register.registerNewUser(testName, testPass);
        actions.goToHomePage();
        LoginPage login = new LoginPage(actions.getDriver());
        login.clickOnLoginButton();
        login.fillUsernameField(testName);
        login.fillPasswordField(testPass);
        login.clickOnCreateButton();
        actions.assertElementPresent("WEare.homePage.LogoutButton");
    }

    @BeforeEach
    public void cleanfields() {
        actions.goToHomePage();
        profileEdit.navigateToEditProfileMenu();

    }

    @Test
    public void editFirstnameLastNameBirthday_when_validDataIsProvided() {

        profileEdit.fillUpFirstNameField("Boris");
        profileEdit.fillUpLastNameField("Yurukov");
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }

    @Test
    public void addSelfDescription_when_validDataIsProvided() {

        profileEdit.fillUpFirstNameField("Boris");
        profileEdit.fillUpLastNameField("Yurukov");
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.fillSelfDescriptionField("Hello its my first time here.Im trying to be smart but im not.");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementText("WEare.PersonalProfilePageSelfDescriptionField", "Hello its my first time here.Im trying to be smart but im not.");
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }

    @Test
    public void changeGender() {
        profileEdit.fillUpFirstNameField("Boris");
        profileEdit.fillUpLastNameField("Yurukov");
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.changeGender("FEMALE");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageGenderButton", "value", "FEMALE", "FEMALE");
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }

    @Test
    public void changeEmail() {
        profileEdit.fillUpFirstNameField("Boris");
        profileEdit.fillUpLastNameField("Yurukov");
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.changeEmail("peshakaa@abv.bg");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageEmailField", "value", "peshakaa@abv.bg" );
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }


    @ParameterizedTest
    @CsvSource({"'',Patkanov", "Iv,Patkanov"})
    public void editFirstnameLastNameBirthday_when_invalidFirstNameISprovided(String firstName, String lastName) {
        profileEdit.fillUpFirstNameField(firstName);
        profileEdit.fillUpLastNameField(lastName);
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        actions.assertElementText("WEare.PersonalProfilePageEditErrorMessage", "first name must have at least 3 symbols!");
    }

    @ParameterizedTest
    @CsvSource({"Patkan,''", "Patkan,Pa"})
    public void editFirstnameLastNameBirthday_when_invalidLastNameISprovided(String firstName, String lastName) {
        profileEdit.fillUpFirstNameField(firstName);
        profileEdit.fillUpLastNameField(lastName);
        profileEdit.fillBirtdayField("01", "15", "2023");
        profileEdit.clickPersonalInformationUpdateButton();

        actions.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        actions.assertElementText("WEare.PersonalProfilePageEditErrorMessage", "last name must have at least 3 symbols!");
    }

    @Test
    public void addPicture() {
        profileEdit.addPicture();
        profileEdit.personalInfoUpdateButton();
    }

}
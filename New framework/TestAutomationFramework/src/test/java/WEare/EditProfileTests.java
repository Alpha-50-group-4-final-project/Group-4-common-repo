package WEare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.LoginPage;

import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class EditProfileTests extends BaseTest {

    @BeforeAll
    public static void testSetup() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        actions.goToHomePage();
       // LoginPage login = new LoginPage(actions.getDriver());
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(usernameRandom);
        loginPage.fillPasswordField(passwordRandom);
        loginPage.clickOnCreateButton();
        actions.assertElementPresent("WEare.homePage.LogoutButton");
    }

    @BeforeEach
    public void cleanfields() {
        actions.goToHomePage();
        editProfilePage.navigateToEditProfileMenu();

    }

    @Test
    public void editFirstnameLastNameBirthday_when_validDataIsProvided() {

        editProfilePage.fillUpFirstNameField("Boris");
        editProfilePage.fillUpLastNameField("Yurukov");
        editProfilePage.fillBirtdayField("01", "01", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-01");
    }

    @Test
    public void addSelfDescription_when_validDataIsProvided() {

        editProfilePage.fillUpFirstNameField("Boris");
        editProfilePage.fillUpLastNameField("Yurukov");
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.fillSelfDescriptionField("Hello its my first time here.Im trying to be smart but im not.");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementText("WEare.PersonalProfilePageSelfDescriptionField", "Hello its my first time here.Im trying to be smart but im not.");
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }

    @Test
    public void changeGender() {
        editProfilePage.fillUpFirstNameField("Boris");
        editProfilePage.fillUpLastNameField("Yurukov");
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.changeGender("FEMALE");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageGenderButton", "value", "FEMALE", "FEMALE");
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }

    @Test
    public void changeEmail() {
        editProfilePage.fillUpFirstNameField("Boris");
        editProfilePage.fillUpLastNameField("Yurukov");
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.changeEmail("peshakaa@abv.bg");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementAttribute("WEare.PersonalProfilePageEmailField", "value", "peshakaa@abv.bg" );
        actions.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        actions.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        actions.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");
    }


    @ParameterizedTest
    @CsvSource({"'',Patkanov", "Iv,Patkanov"})
    public void editFirstnameLastNameBirthday_when_invalidFirstNameISprovided(String firstName, String lastName) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        actions.assertElementText("WEare.PersonalProfilePageEditErrorMessage", "first name must have at least 3 symbols!");
    }

    @ParameterizedTest
    @CsvSource({"Patkan,''", "Patkan,Pa"})
    public void editFirstnameLastNameBirthday_when_invalidLastNameISprovided(String firstName, String lastName) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        actions.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        actions.assertElementText("WEare.PersonalProfilePageEditErrorMessage", "last name must have at least 3 symbols!");
    }

    @Test
    public void addPicture() {
        editProfilePage.addPicture();
        editProfilePage.personalInfoUpdateButton();
    }

}

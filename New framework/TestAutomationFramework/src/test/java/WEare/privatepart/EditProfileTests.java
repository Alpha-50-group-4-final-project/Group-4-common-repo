package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.LoginPage;

import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class EditProfileTests extends BaseTest {

    public static final String SELF_DESCRIPTION = "Hello its my first time here.Im trying to be smart but im not.";
    public static final String CITY = "Burgas";
    public static final String SET_UP_FIRSTNAME = "PEsho";
    public static final String SET_UP_LASTNAME = "Gosho";

    @BeforeAll
    public static void testSetup() {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(usernameRandom);
        registrationPage.fillEmailField(usernameRandom);
        registrationPage.fillPasswordFields(passwordRandom);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
//        loginPage.navigateToHomePage();
        // LoginPage login = new LoginPage(actions.getDriver());
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(usernameRandom);
        loginPage.fillPasswordField(passwordRandom);
        loginPage.clickOnCreateButton();
        loginPage.assertElementPresent("WEare.homePage.LogoutButton");

    }

    @BeforeEach
    public void pageSetUp() {
        editProfilePage.navigateToHomePage();
        editProfilePage.navigateToEditProfileMenu();
    }

    @Test
    public void editFirstnameLastNameBirthday_when_validDataIsProvided() {

        editProfilePage.fillUpFirstNameField("Boris");
        editProfilePage.fillUpLastNameField("Yurukov");
        editProfilePage.fillBirtdayField("01", "01", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", "Boris");
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", "Yurukov");
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-01");
    }

    @ParameterizedTest
    @CsvSource({"Patkan,''", "Patkan,Pa"})
    public void editFirstnameLastNameBirthday_when_invalidLastNameISprovided(String firstName, String lastName) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        editProfilePage.assertErrorMessage("WEare.PersonalProfilePageEditErrorMessage", "last name must have at least 3 symbols!");
    }


    @Test
    public void addSelfDescription_when_validDataIsProvided() {

        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.fillSelfDescriptionField(SELF_DESCRIPTION);
        editProfilePage.clickPersonalInformationUpdateButton();


        editProfilePage.assertErrorMessage("WEare.PersonalProfilePageSelfDescriptionField", SELF_DESCRIPTION);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", SET_UP_LASTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");

    }

    @Test
    public void changeGender() {
        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeGender("FEMALE");
        editProfilePage.clickPersonalInformationUpdateButton();


        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageGenderButton", "value", "FEMALE", "FEMALE");
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", SET_UP_LASTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");

    }

    @Test
    public void changeEmail() {
        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeEmail("peshakaa@abv.bg");
        editProfilePage.clickPersonalInformationUpdateButton();


        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageEmailField", "value", "peshakaa@abv.bg");
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageLastNameField", "value", SET_UP_LASTNAME);
        editProfilePage.assertElementAttribute("WEare.PersonalProfilePageBirthdayField", "value", "2023-01-15");


    }


    @ParameterizedTest
    @CsvSource({"'',Patkanov", "Iv,Patkanov"})
    public void editFirstnameLastNameBirthday_when_invalidFirstNameISprovided(String firstName, String lastName) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirtdayField("01", "15", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementPresent("WEare.PersonalProfilePageEditErrorMessage");
        editProfilePage.assertErrorMessage("WEare.PersonalProfilePageEditErrorMessage", "first name must have at least 3 symbols!");
    }


    @Test
    public void addPicture() {
        editProfilePage.addPicture();
        editProfilePage.personalInfoUpdateButton();
    }

    @Test
    public void changeCity() {
        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.clickOnCityButon();
        editProfilePage.selectCity(CITY);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertErrorMessage("PersonalProfilePageCityAssert", CITY);
    }

    @Test
    public void changeProfessionalCategory() {
        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeProfessionalCategory("Doctor");

    }

    @Test
    public void changeServices() {
        userSetUP(SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeServices("Nothing special.", "8");
    }
}

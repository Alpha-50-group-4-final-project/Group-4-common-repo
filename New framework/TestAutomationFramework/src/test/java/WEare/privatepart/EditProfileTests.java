package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditProfileTests extends BaseTest {

    public static final String SELF_DESCRIPTION = "Hello its my first time here.Im trying to be smart but im not.";
    public static final String CITY = "Burgas";
    public static final String SET_UP_FIRSTNAME = "Jon";
    public static final String SET_UP_LASTNAME = "Snow";
    public static final String SET_UP_BIRTHDAY_DATE = "01-01-1970";


    @BeforeAll
    public static void testSetup() {
        registerUser(usernameRandom,passwordRandom);
        login(usernameRandom,passwordRandom);
    }

    @BeforeEach
    public void pageSetUp() {
        editProfilePage.navigateToEditProfileMenu();
    }

    @Test
    public void editFirstnameLastNameBirthday_when_validDataIsProvided() {

        editProfilePage.fillUpFirstNameField(SET_UP_FIRSTNAME);
        editProfilePage.fillUpLastNameField(SET_UP_LASTNAME);
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
//        editProfilePage.assertElementAttribute("personalProfilePageBirthdayField", "value",SET_UP_BIRTHDAY_DATE );
    }

    @Test
    public void editFirstnameLastNameBirthday_when_invalidLastNameIsProvided() {
        editProfilePage.fillUpFirstNameField("Patkan");
        editProfilePage.fillUpLastNameField("");
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementPresent("personalProfilePageEditErrorMessage");
        editProfilePage.assertLastNameErrorMessage();
    }


    @Test
    public void addSelfDescription_when_validDataIsProvided() {

        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.fillSelfDescriptionField(SELF_DESCRIPTION);
        editProfilePage.clickPersonalInformationUpdateButton();


        editProfilePage.assertSelfDescription(SELF_DESCRIPTION);
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
//        editProfilePage.assertElementAttribute("personalProfilePageBirthdayField", "value", SET_UP_BIRTHDAY_DATE);

    }

    @Test
    public void changeGender() {
        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.changeGender("FEMALE");
        editProfilePage.clickPersonalInformationUpdateButton();


        editProfilePage.assertElementAttribute("personalProfilePageGenderButton", "value", "FEMALE", "FEMALE");
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
//        editProfilePage.assertElementAttribute("personalProfilePageBirthdayField", "value", SET_UP_BIRTHDAY_DATE);

    }

    @Test
    public void changeEmail_when_ValidNewEmailIsProvided() {
        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.changeEmail("peshakaa@abv.bg");
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("personalProfilePageEmailField", "value", "peshakaa@abv.bg");
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
//        editProfilePage.assertElementAttribute("personalProfilePageBirthdayField", "value", SET_UP_BIRTHDAY_DATE);


    }


    @Test
    public void editFirstnameLastNameBirthday_when_invalidFirstNameIsProvided() {
        editProfilePage.fillUpFirstNameField("");
        editProfilePage.fillUpLastNameField("Patkanov");
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.assertElementPresent("personalProfilePageEditErrorMessage");
        editProfilePage.assertFirstNameErrorMessage();
    }

    @Test
    public void changeCity() {
        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickOnCityButton();
        editProfilePage.selectCity(CITY);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.assertCity(CITY);
    }

    @Test
    public void changeProfessionalCategory() {
        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.changeProfessionalCategory("Doctor");

    }

    @Test
    public void changeServices() {
        userSetUP(SET_UP_FIRSTNAME, SET_UP_LASTNAME, SET_UP_BIRTHDAY_DATE);
        editProfilePage.changeServices("Nothing special.", "8");
    }


}

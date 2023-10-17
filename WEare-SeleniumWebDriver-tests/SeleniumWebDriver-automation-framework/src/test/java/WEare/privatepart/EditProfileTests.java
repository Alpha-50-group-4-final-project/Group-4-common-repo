package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

public class EditProfileTests extends BaseTest {

    public static final String SELF_DESCRIPTION = "Hello its my first time here.Im trying to be smart but im not.";
    public static final String CITY = "Burgas";
    public static final String SET_UP_FIRSTNAME = "Jon";
    public static final String SET_UP_LASTNAME = "Snow";
    public static final String SET_UP_BIRTHDAY_DATE = "07/05/1970";
    public static final String PROFESSION = "Doctor";
    public static final String SERVICE_PROVIDED = "Nothing special.";
    public static final String WEEKLY_AVAILABILITY = "8";
    public static final String NEW_EMAIL = "peshakaa@abv.bg";
    public static final String GENDER_TYPE = "FEMALE";
    public static final String VALID_FIRST_NAME = "Patkan";
    public static final String VALID_LAST_NAME = "Patkanov";


    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        login(usernameRandom, passwordRandom);
        editProfilePage.navigateToEditProfileMenu();
    }

    @BeforeEach
    public void pageSetUp() {

    }

    @Test
    @Label("Jira FPW-43")
    @Tag("HappyPath")
    public void editFirstnameLastNameBirthday_when_validDataIsProvided() {
        editProfilePage.fillUpFirstNameField(SET_UP_FIRSTNAME);
        editProfilePage.fillUpLastNameField(SET_UP_LASTNAME);
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
    }

    @Test
    @Label("Jira FPW-46")
    @Tag("Unhappypath")
    public void editFirstnameLastNameBirthday_when_emptyLastNameIsProvided() {
        editProfilePage.fillUpFirstNameField(VALID_FIRST_NAME);
        editProfilePage.fillUpLastNameField("");
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementPresent("personalProfilePageEditErrorMessage");
        editProfilePage.assertLastNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-58")
    @Tag("HappyPath")
    public void addSelfDescription_when_validDataIsProvided() {
        editProfilePage.navigateToEditProfileMenu();
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.fillSelfDescriptionField(SELF_DESCRIPTION);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertSelfDescription(SELF_DESCRIPTION);
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
    }

    @Test
    @Label("Jira FPW-57")
    @Tag("HappyPath")
    public void changeGender_when_validDataProvided() {
        editProfilePage.navigateToEditProfileMenu();
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeGender(GENDER_TYPE);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("personalProfilePageGenderButton", "value", GENDER_TYPE, GENDER_TYPE);
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
    }

    @Test
    @Label("Jira FPW-56")
    @Tag("HappyPath")
    public void changeEmail_when_validNewEmailIsProvided() {
        editProfilePage.navigateToEditProfileMenu();
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeEmail(NEW_EMAIL);
        editProfilePage.clickPersonalInformationUpdateButton();

        editProfilePage.assertElementAttribute("personalProfilePageEmailField", "value", NEW_EMAIL);
        editProfilePage.assertElementAttribute("personalProfilePageFirstNameField", "value", SET_UP_FIRSTNAME);
        editProfilePage.assertElementAttribute("personalProfilePageLastNameField", "value", SET_UP_LASTNAME);
    }

    @Test
    @Label("Jira FPW-45")
    @Tag("HappyPath")
    public void editFirstnameLastNameBirthday_when_invalidFirstNameIsProvided() {
        editProfilePage.fillUpFirstNameField("");
        editProfilePage.fillUpLastNameField(VALID_LAST_NAME);
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.assertElementPresent("personalProfilePageEditErrorMessage");
        editProfilePage.assertFirstNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-61")
    @Tag("HappyPath")
    public void changeCity_when_differentCityIsChosen() {
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.clickOnCityButton();
        editProfilePage.selectCity(CITY);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.assertCity(CITY);
    }

    @Test
    @Label("Jira FPW-62")
    @Tag("HappyPath")
    public void changeProfessionalCategory_when_validProfessionProvided() {
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeProfessionalCategory(PROFESSION);
    }

    @Test
    @Label("Jira FPW-63")
    @Tag("HappyPath")
    public void changeServices_when_validServiceClicked() {
        editProfilePage.navigateToEditProfileMenu();
        api.updateUserProfile(usernameRandom, passwordRandom,SET_UP_FIRSTNAME,SET_UP_LASTNAME);
        editProfilePage.changeServices(SERVICE_PROVIDED, WEEKLY_AVAILABILITY);
    }
}

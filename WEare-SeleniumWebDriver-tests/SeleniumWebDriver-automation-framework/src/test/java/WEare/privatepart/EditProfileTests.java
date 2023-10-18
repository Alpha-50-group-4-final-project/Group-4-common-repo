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

    public static final String NEW_EMAIL = "peshakaa@abv.bg";
    public static final String GENDER_TYPE = "FEMALE";
    public static final String VALID_FIRST_NAME = "Patkan";
    public static final String VALID_LAST_NAME = "Patkanov";


    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, SET_UP_FIRSTNAME, SET_UP_LASTNAME);
        login(usernameRandom, passwordRandom);
    }
    @BeforeEach
    public  void  resetPage(){
        editProfilePage.navigateToEditProfileMenu();
    }

    @Test
    @Label("Jira FPW-46")
    @Tag("UnHappyPath")
    public void editFirstnameLastNameBirthdayFails_when_emptyLastNameIsProvided() {
        editProfilePage.fillUpFirstNameField(VALID_FIRST_NAME);
        editProfilePage.fillUpLastNameField("");
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEditFailed();
        editProfilePage.assertLastNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-58")
    @Tag("HappyPath")
    public void addSelfDescription_when_validDataIsProvided() {
        editProfilePage.fillSelfDescriptionField(SELF_DESCRIPTION);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateSelfDescriptionUpdated(SELF_DESCRIPTION);
    }

    @Test
    @Label("Jira FPW-57")
    @Tag("HappyPath")
    public void changeGender_when_validDataProvided() {
        editProfilePage.changeGender(GENDER_TYPE);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateGenderUpdated(GENDER_TYPE);
    }

    @Test
    @Label("Jira FPW-56")
    @Tag("HappyPath")
    public void changeEmail_when_validNewEmailIsProvided() {
        editProfilePage.navigateToEditProfileMenu();
        editProfilePage.changeEmail(NEW_EMAIL);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEmailUpdated(NEW_EMAIL);
       }
    @Test
    @Label("Jira FPW-45")
    @Tag("UnHappyPath")
    public void editFirstnameLastNameBirthdayFail_when_invalidFirstNameIsProvided() {
        editProfilePage.fillUpFirstNameField("");
        editProfilePage.fillUpLastNameField(VALID_LAST_NAME);
        editProfilePage.fillBirthdayField(SET_UP_BIRTHDAY_DATE);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEditFailed();
        editProfilePage.assertFirstNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-61")
    @Tag("HappyPath")
    public void changeCity_when_differentCityIsChosen() {
        editProfilePage.clickOnCityButton();
        editProfilePage.selectCity(CITY);
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateCityUpdated(CITY);
    }

    @Test
    @Label("Jira FPW-62")
    @Tag("HappyPath")
    public void changeProfessionalCategory_when_validProfessionProvided() {
        editProfilePage.changeProfessionalCategory(PROFESSION);
        editProfilePage.validateCategoryUpdated(PROFESSION);
    }
}

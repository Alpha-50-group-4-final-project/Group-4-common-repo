package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@DisplayName("EditUserProfileTests")
public class EditProfileTests extends BaseTest {

    public static final String SET_UP_FIRSTNAME = getUIMappingByKey("personalProfileUpdatePage.firstName");
    public static final String SET_UP_LASTNAME = getUIMappingByKey("personalProfileUpdatePage.lastName");

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
    @DisplayName("Edit profile of registered user without last name, only valid first name and birthday are provided")
    public void editFirstnameLastNameBirthdayFails_when_emptyLastNameIsProvided() {
        editProfilePage.fillUpFirstNameField(getUIMappingByKey("personalProfileUpdatePage.firstNameUpdate"));
        editProfilePage.fillUpLastNameField("");
        editProfilePage.fillBirthdayField(getUIMappingByKey("personalProfileUpdatePage.birthDate"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEditFailed();
        editProfilePage.assertLastNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-58")
    @Tag("HappyPath")
    @DisplayName("Edit profile by adding valid self description.")
    public void addSelfDescription_when_validDataIsProvided() {
        editProfilePage.fillSelfDescriptionField(getUIMappingByKey("personalProfileUpdatePage.selfDescription"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateSelfDescriptionUpdated(getUIMappingByKey("personalProfileUpdatePage.selfDescription"));
    }

    @Test
    @Label("Jira FPW-57")
    @Tag("HappyPath")
    @DisplayName("Edit profile by changing gender from MALE to FEMALE")
    public void changeGender_when_validDataProvided() {
        editProfilePage.changeGender(getUIMappingByKey("personalProfileUpdatePage.gender"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateGenderUpdated(getUIMappingByKey("personalProfileUpdatePage.gender"));
    }

    @Test
    @Label("Jira FPW-56")
    @Tag("HappyPath")
    @DisplayName("Edit profile by changing existing  email")
    public void changeEmail_when_validNewEmailIsProvided() {
        editProfilePage.navigateToEditProfileMenu();
        editProfilePage.changeEmail(getUIMappingByKey("personalProfileUpdatePage.email"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEmailUpdated(getUIMappingByKey("personalProfileUpdatePage.email"));
       }
    @Test
    @Label("Jira FPW-45")
    @Tag("UnHappyPath")
    @DisplayName("Edit profile of registered user without a first name, only valid last name and birthday are provided")
    public void editFirstnameLastNameBirthdayFail_when_invalidFirstNameIsProvided() {
        editProfilePage.fillUpFirstNameField("");
        editProfilePage.fillUpLastNameField(getUIMappingByKey("personalProfileUpdatePage.lastNameUpdate"));
        editProfilePage.fillBirthdayField(getUIMappingByKey("personalProfileUpdatePage.birthDate"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateEditFailed();
        editProfilePage.assertFirstNameErrorMessage();
    }

    @Test
    @Label("Jira FPW-61")
    @Tag("HappyPath")
    @DisplayName("Edit profile by changing city")
    public void changeCity_when_differentCityIsChosen() {
        editProfilePage.clickOnCityButton();
        editProfilePage.selectCity(getUIMappingByKey("personalProfileUpdatePage.city"));
        editProfilePage.clickPersonalInformationUpdateButton();
        editProfilePage.validateCityUpdated(getUIMappingByKey("personalProfileUpdatePage.city"));
    }

    @Test
    @Label("Jira FPW-62")
    @Tag("HappyPath")
    @DisplayName("Edit profile by changing industry/workspace category")
    public void changeProfessionalCategory_when_validProfessionProvided() {
        editProfilePage.changeProfessionalCategory(getUIMappingByKey("personalProfileUpdatePage.profession"));
        editProfilePage.validateCategoryUpdated(getUIMappingByKey("personalProfileUpdatePage.profession"));
    }
}

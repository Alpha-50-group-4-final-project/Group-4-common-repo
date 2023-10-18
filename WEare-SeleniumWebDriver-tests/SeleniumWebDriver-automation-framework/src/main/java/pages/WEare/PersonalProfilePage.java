package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static java.lang.String.format;

public class PersonalProfilePage extends WEareBasePage {
    public PersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }

    public void navigateToEditProfileMenu() {
        actions.navigateToPage("http://localhost:8081/");

        actions.waitForElementClickable("homePage.PersonalProfileButton");
        actions.assertElementPresent("homePage.PersonalProfileButton");
        actions.clickElement("homePage.PersonalProfileButton");

        actions.waitForElementPresent("personalProfilePageEditButton");
        actions.assertElementPresent("personalProfilePageEditButton");
        actions.clickElement("personalProfilePageEditButton");
    }

    public void fillUpFirstNameField(String name) {
        actions.waitForElementPresent("personalProfilePageFirstNameField");
        actions.assertElementPresent("personalProfilePageFirstNameField");
        actions.clearingFiled("personalProfilePageFirstNameField");
        actions.typeValueInField(name, "personalProfilePageFirstNameField");
        LOGGER.info(format("First name was set to : \"%s\"", name));
    }

    public void fillUpLastNameField(String lastName) {
        actions.waitForElementPresent("personalProfilePageLastNameField");
        actions.assertElementPresent("personalProfilePageLastNameField");
        actions.clearingFiled("personalProfilePageLastNameField");
        actions.typeValueInField(lastName, "personalProfilePageLastNameField");
        LOGGER.info(format("Last name was set to : \"%s\"", lastName));
    }

    public void fillBirthdayField(String date) {
        actions.waitForElementPresent("personalProfilePageBirthdayField");
        actions.assertElementPresent("personalProfilePageBirthdayField");
        actions.typeValueInField(date, "personalProfilePageBirthdayField");
        LOGGER.info(format("Birthday date was set to : \"%s\"", date));
    }

    public void changeGender(String genderType) {
        actions.waitForElementPresent("personalProfilePageGenderSelectionButton");
        actions.assertElementPresent("personalProfilePageGenderSelectionButton");
        actions.clickElement("personalProfilePageGenderSelectionButton");

        actions.waitForElementPresent("personalProfilePageGenderButton", genderType);
        actions.assertElementPresent("personalProfilePageGenderButton", genderType);
        actions.clickElement("personalProfilePageGenderButton", genderType);
        LOGGER.info(format("Gender was changed to : \"%s\"", genderType));
    }

    public void changeEmail(String newEmail) {
        actions.waitForElementPresent("personalProfilePageEmailField");
        actions.assertElementPresent("personalProfilePageEmailField");
        actions.clearingFiled("personalProfilePageEmailField");

        actions.typeValueInField(newEmail, "personalProfilePageEmailField");
        LOGGER.info(format("Email date was changed to : \"%s\"", newEmail));
    }

    public void fillSelfDescriptionField(String description) {
        actions.clearingFiled("personalProfilePageSelfDescriptionField");
        actions.waitForElementPresent("personalProfilePageSelfDescriptionField");
        actions.assertElementPresent("personalProfilePageSelfDescriptionField");
        actions.typeValueInField(description, "personalProfilePageSelfDescriptionField");
    }

    public void clickOnCityButton() {
        actions.waitForElementPresent("personalProfile.PersonalProfileCityButton");
        actions.assertElementPresent("personalProfile.PersonalProfileCityButton");
        actions.clickElement("personalProfile.PersonalProfileCityButton");
    }

    public void selectCity(String city) {
        actions.waitForElementPresent("personalProfilePageChooseCity", city);
        actions.assertElementPresent("personalProfilePageChooseCity", city);
        actions.clickElement("personalProfilePageChooseCity", city);
    }


    public void clickPersonalInformationUpdateButton() {
        actions.waitForElementClickable("homePage.PersonalProfileUpdateProfileButton");
        actions.assertElementPresent("homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("homePage.PersonalProfileUpdateProfileButton");
    }

    public void changeProfessionalCategory(String profession) {
        actions.assertElementPresent("personalProfileUpdatePageProfessionalCategoryButton");
        actions.waitForElementPresent("personalProfileUpdatePageProfessionalCategoryButton");
        actions.moveToElementAndClickOnit("personalProfileUpdatePageProfessionalCategoryButton");
        actions.waitForElementPresent("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.waitForElementClickable("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.assertElementPresent("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.clickElement("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.assertElementPresent("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        actions.waitForElementClickable("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        actions.clickElement("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        LOGGER.info(format("Profession was changed to : \"%s\"", profession));
    }

    public void assertFirstNameErrorMessage() {
        actions.assertElementText("personalProfilePageEditErrorMessage", "first name must have at least 3 symbols!");
    }

    public void assertLastNameErrorMessage() {
        actions.assertElementText("personalProfilePageEditErrorMessage", "last name must have at least 3 symbols!");
    }

    public void validateEditFailed() {
        try {
            actions.assertElementPresent("personalProfilePageEditErrorMessage");
            LOGGER.info("User personal information not updated successfully as invalid data passed.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User personal information was updated successfully despite invalid data passed.");
        }
    }

    public void validateSelfDescriptionUpdated(String selfDescription) {
        try {
            actions.assertElementText("personalProfilePageSelfDescriptionField", selfDescription);
            LOGGER.info("User self description was updated successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User self description was not updated successfully.");
        }
    }

    public void validateGenderUpdated(String gender) {
        try {
            actions.assertElementAttribute("personalProfilePageGenderButton", "value", gender, gender);
            LOGGER.info("User gender type was updated successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User gender type was not updated successfully.");
        }
    }

    public void validateEmailUpdated(String newEmail) {
        try{
            actions.assertElementAttribute("personalProfilePageEmailField", "value", newEmail);
            LOGGER.info("User email was updated successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User email was not updated successfully.");
        }
    }

    public void validateCityUpdated(String city) {
        try{
            actions.assertElementText("personalProfilePageCityAssert", city);
            LOGGER.info("User city was updated successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User city was not updated successfully.");
        }
    }

    public void validateCategoryUpdated(String profession) {
        try{
            actions.assertElementText("personalProfilePage.ProfessionalCategoryAssert",profession,profession);
            LOGGER.info("User category profession was updated successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("User category profession was not updated successfully.");
        }
    }

}

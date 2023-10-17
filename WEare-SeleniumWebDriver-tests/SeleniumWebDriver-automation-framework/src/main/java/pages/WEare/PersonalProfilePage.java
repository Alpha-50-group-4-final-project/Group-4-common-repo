package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static java.lang.String.format;

public class PersonalProfilePage extends WEareBasePage {

    public PersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }

    public void navigateToEditProfileMenu() {
        actions.navigateToPage("http://localhost:8081/");

        actions.waitForElementClickable("homePage.PersonalProfileButton");
        actions.clickElement("homePage.PersonalProfileButton");

        actions.waitForElementPresent("personalProfilePageEditButton");
        actions.clickElement("personalProfilePageEditButton");
    }

    public void fillUpFirstNameField(String name) {
        actions.waitForElementPresent("personalProfilePageFirstNameField");
        actions.clearingFiled("personalProfilePageFirstNameField");
        actions.typeValueInField(name, "personalProfilePageFirstNameField");
        LOGGER.info(format("First name was set to : \"%s\"",name));
    }

    public void fillUpLastNameField(String lastName) {
        actions.waitForElementPresent("personalProfilePageLastNameField");
        actions.clearingFiled("personalProfilePageLastNameField");
        actions.typeValueInField(lastName, "personalProfilePageLastNameField");
        LOGGER.info(format("Last name was set to : \"%s\"",lastName));
    }

    public void fillBirthdayField(String date) {
        actions.waitForElementPresent("personalProfilePageBirthdayField");
        actions.typeValueInField(date, "personalProfilePageBirthdayField");
        LOGGER.info(format("Birthday date was set to : \"%s\"",date));
    }

    public void changeGender(String genderType) {
        actions.waitForElementPresent("personalProfilePageGenderSelectionButton");
        actions.clickElement("personalProfilePageGenderSelectionButton");

        actions.waitForElementPresent("personalProfilePageGenderButton", genderType);
        actions.clickElement("personalProfilePageGenderButton", genderType);
        LOGGER.info(format("Gender was changed to : \"%s\"",genderType));
    }

    public void changeEmail(String newEmail) {
        actions.waitForElementPresent("personalProfilePageEmailField");
        actions.clearingFiled("personalProfilePageEmailField");

        actions.typeValueInField(newEmail, "personalProfilePageEmailField");
        LOGGER.info(format("Email date was changed to : \"%s\"",newEmail));
    }

    public void fillSelfDescriptionField(String description) {
        actions.clearingFiled("personalProfilePageSelfDescriptionField");
        actions.waitForElementPresent("personalProfilePageSelfDescriptionField");
        actions.typeValueInField(description, "personalProfilePageSelfDescriptionField");
    }

    public void clickOnCityButton() {
        actions.waitForElementPresent("personalProfile.PersonalProfileCityButton");
        actions.clickElement("personalProfile.PersonalProfileCityButton");
    }

    public void selectCity(String city) {
        actions.waitForElementPresent("personalProfilePageChooseCity", city);
        actions.clickElement("personalProfilePageChooseCity", city);
    }


    public void clickPersonalInformationUpdateButton() {
        actions.waitForElementClickable("homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("homePage.PersonalProfileUpdateProfileButton");
    }

    public void changeProfessionalCategory(String profession) {
        actions.waitForElementPresent("personalProfileUpdatePageProfessionalCategoryButton");
        actions.clickElement("personalProfileUpdatePageProfessionalCategoryButton");
        actions.waitForElementClickable("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.clickElement("personalProfileUpdatePage.ProfessionalCategoryDropDown", profession);
        actions.waitForElementClickable("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        actions.clickElement("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        LOGGER.info(format("Profession was changed to : \"%s\"",profession));
    }

    public void changeServices(String service, String weeklyAvailability) {
        actions.waitForElementVisible("personalProfilePageUpdateServiceField1");
        actions.typeValueInField(service, "personalProfilePageUpdateServiceField1");
        actions.waitForElementVisible("personalProfilePageWeeklyAvailabilityField");
        actions.typeValueInField(weeklyAvailability, "personalProfilePageWeeklyAvailabilityField");

        actions.waitForElementClickable("personalProfileUpdatePage.PersonalProfileUpdateServiceButton");
        actions.clickElement("personalProfileUpdatePage.PersonalProfileUpdateServiceButton");

    }

    public void assertFirstNameErrorMessage() {
        actions.assertElementText("personalProfilePageEditErrorMessage", "first name must have at least 3 symbols!");
    }

    public void assertLastNameErrorMessage() {
        actions.assertElementText("personalProfilePageEditErrorMessage", "last name must have at least 3 symbols!");
    }
    public void assertSelfDescription(String selfDescriptionText) {
        actions.assertElementText("personalProfilePageSelfDescriptionField", selfDescriptionText);
    }

    public void assertCity(String city) {
        actions.assertElementText("personalProfilePageCityAssert", city);
    }

    public void assertElementPresent(String locator) {
        actions.assertElementPresent(locator);
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue, Object... arguments) {
        actions.assertElementAttribute(locator, attributeName, attributeValue, arguments);
    }

}

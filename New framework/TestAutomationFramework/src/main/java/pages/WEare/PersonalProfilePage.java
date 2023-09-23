package pages.WEare;

import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends WEareBasePage {

    public PersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }


    public void navigateToEditProfileMenu() {
        actions.waitForElementPresent("WEare.homePage.PersonalProfileButton");
        actions.clickElement("WEare.homePage.PersonalProfileButton");

        actions.waitForElementPresent("WEare.PersonalProfilePageEditButton");
        actions.clickElement("WEare.PersonalProfilePageEditButton");
    }

    public void cleanAllFields() {
        actions.waitForElementPresent("WEare.PersonalProfilePageFirstNameField");
        actions.clearingFiled("WEare.PersonalProfilePageFirstNameField");

        actions.waitForElementPresent("WEare.PersonalProfilePageLastNameField");
        actions.clearingFiled("WEare.PersonalProfilePageFirstNameField");

    }


    public void fillUpFirstNameField(String name) {

        actions.waitForElementPresent("WEare.PersonalProfilePageFirstNameField");
        actions.clearingFiled("WEare.PersonalProfilePageFirstNameField");
        actions.typeValueInField(name, "WEare.PersonalProfilePageFirstNameField");
    }

    public void fillUpLastNameField(String lastName) {
        actions.waitForElementPresent("WEare.PersonalProfilePageLastNameField");
        actions.clearingFiled("WEare.PersonalProfilePageLastNameField");
        actions.typeValueInField(lastName, "WEare.PersonalProfilePageLastNameField");
    }

    public void fillBirthdayField(String month, String day, String year) {
        actions.waitForElementPresent("WEare.PersonalProfilePageBirthdayField");
        actions.clearingFiled("WEare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(month, "WEare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(day, "WEare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(year, "WEare.PersonalProfilePageBirthdayField");
    }

    public void changeGender(String genderType) {
        actions.waitForElementPresent("WEare.PersonalProfilePageGenderSelectionButton");
        actions.clickElement("WEare.PersonalProfilePageGenderSelectionButton");

        actions.waitForElementPresent("WEare.PersonalProfilePageGenderButton", genderType);
        actions.clickElement("WEare.PersonalProfilePageGenderButton", genderType);
    }

    public void changeEmail(String newEmail){
        actions.waitForElementPresent("WEare.PersonalProfilePageEmailField");
        actions.clearingFiled("WEare.PersonalProfilePageEmailField");

        actions.typeValueInField(newEmail,"WEare.PersonalProfilePageEmailField");
    }

    public void fillSelfDescriptionField(String description) {
        actions.waitForElementPresent("WEare.PersonalProfilePageSelfDescriptionField");
        actions.typeValueInField(description, "WEare.PersonalProfilePageSelfDescriptionField");
    }
    public void clickOnCityButon(){
        actions.waitForElementPresent("WEare.PersonalProfile.PersonalProfileCityButton");
        actions.clickElement("WEare.PersonalProfile.PersonalProfileCityButton");
    }
    public void selectCity(String city){
        actions.waitForElementPresent("WEare.PersonalProfilePageChooseCity",city);
        actions.clickElement("WEare.PersonalProfilePageChooseCity",city);
    }


    public void clickPersonalInformationUpdateButton() {
        actions.waitForElementClickable("WEare.homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("WEare.homePage.PersonalProfileUpdateProfileButton");
    }

    public void changeProfessionalCategory(String profession){
        actions.waitForElementPresent("WEare.PersonalProfileUpdatePageProfessionalCategoryButton");
        actions.clickElement("WEare.PersonalProfileUpdatePageProfessionalCategoryButton");
        actions.waitForElementClickable("WEare.PersonalProfileUpdatePage.ProfessionalCategoryDropDown",profession);
        actions.clickElement("WEare.PersonalProfileUpdatePage.ProfessionalCategoryDropDown",profession);
        actions.waitForElementClickable("WEare.PersonalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        actions.clickElement("WEare.PersonalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
    }

    public void changeServices(String service,String weeklyAvailability){
        actions.waitForElementVisible("WEare.PersonalProfilePageUpdateServiceField1");
        actions.typeValueInField(service,"WEare.PersonalProfilePageUpdateServiceField1");
        actions.waitForElementVisible("WEare.PersonalProfilePageWeeklyAvailabilityField");
        actions.typeValueInField(weeklyAvailability,"WEare.PersonalProfilePageWeeklyAvailabilityField");

        actions.waitForElementClickable("WEare.PersonalProfileUpdatePage.PersonalProfileUpdateServiceButton");
        actions.clickElement("WEare.PersonalProfileUpdatePage.PersonalProfileUpdateServiceButton");
    }


    public void addPicture() {
        actions.waitForElementPresent("WEare.PersonalProfilePagePersonalInfoPhotoField");
        actions.typeValueInField("K:\\Group-4-common-repo\\New framework\\TestAutomationFramework\\src\\test\\resources\\Patkan.jpg", "WEare.PersonalProfilePagePersonalInfoPhotoField");
    }

    public void personalInfoUpdateButton() {
        actions.waitForElementClickable("WEare.PersonalProfilePagePersonalInfoUpdateButton");
        actions.clickElement("WEare.PersonalProfilePagePersonalInfoUpdateButton");
    }

    public void navigateToHomePage() {
        driver.get("http://localhost:8081/");
    }

    public void assertErrorMessage(String locator,String message,Object... arguments) {
        actions.assertElementText(locator, message);
    }

    public void assertElementPresent(String locator) {
        actions.assertElementPresent(locator);
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue,Object... arguments) {
        actions.assertElementAttribute(locator, attributeName, attributeValue,arguments);
    }

}

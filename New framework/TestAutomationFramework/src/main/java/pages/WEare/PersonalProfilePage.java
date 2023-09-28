package pages.WEare;

import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends WEareBasePage {

    public PersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }


    public void navigateToEditProfileMenu() {
        actions.waitForElementPresent("homePage.PersonalProfileButton");
        actions.clickElement("homePage.PersonalProfileButton");

        actions.waitForElementPresent("personalProfilePageEditButton");
        actions.clickElement("rsonalProfilePageEditButton");
    }


    public void cleanAllFields() {
        actions.waitForElementPresent("personalProfilePageFirstNameField");
        actions.clearingFiled("personalProfilePageFirstNameField");

        actions.waitForElementPresent("personalProfilePageLastNameField");
        actions.clearingFiled("personalProfilePageFirstNameField");

    }


    public void fillUpFirstNameField(String name) {

        actions.waitForElementPresent("personalProfilePageFirstNameField");
        actions.clearingFiled("personalProfilePageFirstNameField");
        actions.typeValueInField(name, "personalProfilePageFirstNameField");
    }

    public void fillUpLastNameField(String lastName) {
        actions.waitForElementPresent("personalProfilePageLastNameField");
        actions.clearingFiled("personalProfilePageLastNameField");
        actions.typeValueInField(lastName, "personalProfilePageLastNameField");
    }

    public void fillBirthdayField(String date) {
        actions.waitForElementPresent("personalProfilePageBirthdayField");
        actions.clearingFiled("personalProfilePageBirthdayField");
        actions.typeValueInField(date, "personalProfilePageBirthdayField");

    }

    public void changeGender(String genderType) {
        actions.waitForElementPresent("personalProfilePageGenderSelectionButton");
        actions.clickElement("personalProfilePageGenderSelectionButton");

        actions.waitForElementPresent("personalProfilePageGenderButton", genderType);
        actions.clickElement("personalProfilePageGenderButton", genderType);
    }

    public void changeEmail(String newEmail){
        actions.waitForElementPresent("personalProfilePageEmailField");
        actions.clearingFiled("personalProfilePageEmailField");

        actions.typeValueInField(newEmail,"personalProfilePageEmailField");
    }

    public void fillSelfDescriptionField(String description) {
        actions.waitForElementPresent("personalProfilePageSelfDescriptionField");
        actions.typeValueInField(description, "personalProfilePageSelfDescriptionField");
    }
    public void clickOnCityButton(){
        actions.waitForElementPresent("personalProfile.PersonalProfileCityButton");
        actions.clickElement("personalProfile.PersonalProfileCityButton");
    }
    public void selectCity(String city){
        actions.waitForElementPresent("personalProfilePageChooseCity",city);
        actions.clickElement("personalProfilePageChooseCity",city);
    }


    public void clickPersonalInformationUpdateButton() {
        actions.waitForElementClickable("homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("homePage.PersonalProfileUpdateProfileButton");
    }

    public void changeProfessionalCategory(String profession){
        actions.waitForElementPresent("personalProfileUpdatePageProfessionalCategoryButton");
        actions.clickElement("personalProfileUpdatePageProfessionalCategoryButton");
        actions.waitForElementClickable("personalProfileUpdatePage.ProfessionalCategoryDropDown",profession);
        actions.clickElement("personalProfileUpdatePage.ProfessionalCategoryDropDown",profession);
        actions.waitForElementClickable("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
        actions.clickElement("personalProfileUpdatePage.PersonalProfileUpdateCategoryButton");
    }

    public void changeServices(String service,String weeklyAvailability){
        actions.waitForElementVisible("personalProfilePageUpdateServiceField1");
        actions.typeValueInField(service,"personalProfilePageUpdateServiceField1");
        actions.waitForElementVisible("personalProfilePageWeeklyAvailabilityField");
        actions.typeValueInField(weeklyAvailability,"personalProfilePageWeeklyAvailabilityField");

        actions.waitForElementClickable("personalProfileUpdatePage.PersonalProfileUpdateServiceButton");
        actions.clickElement("personalProfileUpdatePage.PersonalProfileUpdateServiceButton");
    }


    public void addPicture() {
        actions.waitForElementPresent("personalProfilePagePersonalInfoPhotoField");
        actions.typeValueInField("K:\\Group-4-common-repo\\New framework\\TestAutomationFramework\\src\\test\\resources\\Patkan.jpg", "personalProfilePagePersonalInfoPhotoField");
    }

    public void personalInfoUpdateButton() {
        actions.waitForElementClickable("personalProfilePagePersonalInfoUpdateButton");
        actions.clickElement("personalProfilePagePersonalInfoUpdateButton");
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

package pages.WEare;

import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends WEareBasePаge {

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

    public void fillBirtdayField(String month, String day, String year) {
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


    public void clickPersonalInformationUpdateButton() {
        actions.waitForElementClickable("WEare.homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("WEare.homePage.PersonalProfileUpdateProfileButton");
    }


    public void addPicture() {
        actions.waitForElementPresent("WEare.PersonalProfilePagePersonalInfoPhotoField");
        actions.typeValueInField("K:\\Group-4-common-repo\\New framework\\TestAutomationFramework\\src\\test\\resources\\Patkan.jpg", "WEare.PersonalProfilePagePersonalInfoPhotoField");
    }

    public void personalInfoUpdateButton() {
        actions.waitForElementClickable("WEare.PersonalProfilePagePersonalInfoUpdateButton");
        actions.clickElement("WEare.PersonalProfilePagePersonalInfoUpdateButton");
    }
}

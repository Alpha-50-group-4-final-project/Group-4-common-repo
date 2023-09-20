package pages.WEare;

import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends WEareBasePаge {

    public PersonalProfilePage(WebDriver driver){
        super(driver,"");
    }


    public void  navigateToEditProfileMenu(){
        actions.waitForElementPresent("WEare.homePage.PersonalProfileButton");
        actions.clickElement("WEare.homePage.PersonalProfileButton");

        actions.waitForElementPresent("WEare.PersonalProfilePageEditButton");
        actions.clickElement("WEare.PersonalProfilePageEditButton");
    }

    public void cleanAllFields(){
        actions.waitForElementPresent("WEare.PersonalProfilePageFirstNameField");
        actions.clearingFiled("WEare.PersonalProfilePageFirstNameField");

        actions.waitForElementPresent("WEare.PersonalProfilePageLastNameField");
        actions.clearingFiled("WEare.PersonalProfilePageFirstNameField");

    }


    public void fillUpFirstNameField(String name){
        actions.waitForElementPresent("WEare.PersonalProfilePageFirstNameField");
        actions.typeValueInField(name,"WEare.PersonalProfilePageFirstNameField");
    }
    public void fillUpLastNameField(String lastName){
        actions.waitForElementPresent("WEare.PersonalProfilePageLastNameField");
        actions.typeValueInField(lastName,"WEare.PersonalProfilePageLastNameField");
    }
    public void fillBirtdayField(String month,String day,String year){
        actions.waitForElementPresent("Eare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(month,"Eare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(day,"Eare.PersonalProfilePageBirthdayField");
        actions.typeValueInField(year,"Eare.PersonalProfilePageBirthdayField");
    }
    public void changeGender(String genderType){
        actions.waitForElementPresent("Eare.PersonalProfilePageGenderSelectionButton");
        actions.clickElement("Eare.PersonalProfilePageGenderSelectionButton");

        actions.waitForElementPresent("Eare.PersonalProfilePageGenderButton",genderType);
        actions.clickElement("Eare.PersonalProfilePageGenderButton",genderType);
    }
    public  void clickPersonalInformationUpdateButton(){
        actions.waitForElementClickable("WEare.homePage.PersonalProfileUpdateProfileButton");
        actions.clickElement("WEare.homePage.PersonalProfileUpdateProfileButton");
    }


    public void addPicture(){
        actions.waitForElementPresent("WEare.PersonalProfilePagePersonalInfoPhotoField");
        actions.typeValueInField("K:\\Group-4-common-repo\\New framework\\TestAutomationFramework\\src\\test\\resources\\Patkan.jpg","WEare.PersonalProfilePagePersonalInfoPhotoField");
    }
    public void personalInfoUpdateButton(){
        actions.waitForElementClickable("WEare.PersonalProfilePagePersonalInfoUpdateButton");
        actions.clickElement("WEare.PersonalProfilePagePersonalInfoUpdateButton");
    }
}

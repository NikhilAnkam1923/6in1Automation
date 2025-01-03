package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.GlobalContactPage;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import cucumber.api.java.en.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;


public class GlobalContactsSteps {

    @When("^user login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public static void userLoginTo6in1(String userEmail, String password) throws AutomationException {
        PageFactory.loginPage().doLogoutFrom6in1IfAlreadyLoggedIn();
        userEmail = CommonUtil.processString(userEmail);
        password = CommonUtil.processString(password);
        CommonSteps.logInfo("User login with user: " + userEmail + " and password: *********");
        PageFactory.loginPage().loginTo6in1(userEmail, password);
    }

    @When("^user navigate to \"([^\"]*)\"$")
    public void userNavigateToTab(String tab) throws AutomationException, IOException {
        PageFactory.globalContactPage().tabNavigation(tab);
    }


    @And("^user \"([^\"]*)\" global contact of \"([^\"]*)\"$")
    public void userCreatesGlobalContact(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        if (action.equals("Create")) {
            PageFactory.globalContactPage().globalContactCreation(contactType);
            CommonSteps.takeScreenshot();
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEdit(contactType);
            CommonSteps.takeScreenshot();
        }
    }

    @And("^user fills all the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.globalContactPage().fillGlobalContactDetails(contactType);
        CommonSteps.takeScreenshot();
    }

    @And("^user fills all the details for \"([^\"]*)\" with spaces$")
    public void userFillsAllTheDetailsForGlobalContactWithSpaces(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.globalContactPage().fillGlobalContactDetailsWithSpaces(contactType);
        CommonSteps.takeScreenshot();
    }

    @Then("user save the global contact")
    public void userSaveTheGlobalContact() throws AutomationException, IOException {
        PageFactory.globalContactPage().saveGlobalContact();
    }

    @And("user verifies global contact saved successful message")
    public void userVerifiesGlobalContactSavedSuccessfulMessage() throws AutomationException {
        PageFactory.globalContactPage().verifyGlobalContactSaved();

    }

    @Then("^user verifies authorization for \"([^\"]*)\"$")
    public void userVerifiesAuthorizationFor(String userType) throws AutomationException {
        CommonSteps.logInfo("user verifies authorization for user type: " + userType);
        PageFactory.globalContactPage().verifyUserType(userType);
        CommonSteps.takeScreenshot();

    }

    @And("user should see an error message for duplicate EIN")
    public void userShouldSeeAnErrorMessageForDuplicateEIN() throws Throwable {
        CommonSteps.logInfo("Verifying the error message for duplicate EIN.");
        PageFactory.globalContactPage().verifyDuplicateEINError();
    }

    @And("user attempts to save the global contact without filling the required fields")
    public void userAttemptsToSaveTheGlobalContactWithoutFillingTheRequiredFields() throws AutomationException, IOException {
        CommonSteps.logInfo("Attempting to save the global contact without filling the required fields.");
        PageFactory.globalContactPage().clearNameFields();
        PageFactory.globalContactPage().clickButtonNext();
    }

    @Then("user should see validation error messages for the required fields")
    public void userShouldSeeValidationErrorMessagesForTheRequiredFields() throws AutomationException {
        CommonSteps.logInfo("Verifying validation error messages for the required fields.");
        PageFactory.globalContactPage().verifyRequiredFieldValidationErrors();
        CommonSteps.takeScreenshot();
    }

    @When("^user fills in the previously empty required fields for \"([^\"]*)\"$")
    public void userFillsInThePreviouslyEmptyRequiredFieldsFor(String contactType) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Filling in the previously empty required fields for contact type: " + contactType);
        PageFactory.globalContactPage().fillRequiredFields(contactType);
        CommonSteps.takeScreenshot();
    }


    @Then("user navigates to the page with the records")
    public void userNavigatesToThePageWithTheRecords() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the page with the records");
        PageFactory.globalContactPage().clickButtonCreateIndividualContact();
    }

    @And("user selects a radio button for a record")
    public void userSelectsARadioButtonForARecord() throws AutomationException, IOException {
        CommonSteps.logInfo("user select the radio button");
        PageFactory.globalContactPage().clickButtonRadioButton();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the \"([^\"]*)\" button is enabled$")
    public void userVerifiesTheButtonIsEnabled(String buttonName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that the " + buttonName + " button is enabled.");
        PageFactory.globalContactPage().isButtonEnabled(buttonName);
        CommonSteps.takeScreenshot();
    }

    @Then("^user enters \"([^\"]*)\" as the first name and \"([^\"]*)\" as the last name$")
    public void userEntersFirstAndLastName(String firstName, String lastName) throws AutomationException {
        CommonSteps.logInfo("Entering first and last name");
        PageFactory.globalContactPage().enterFirstnameAndLastNameFields(firstName, lastName);
        CommonSteps.takeScreenshot();
    }

    @And("Entity Name fields is pre-filled")
    public void entityNameFieldsIsPreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field Entity Name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyentityNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }


    @Then("^user enters data in Zip Field$")
    public void userEntersDataInZipField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Entering data in the Zip field");
        PageFactory.globalContactPage().enterDataInZipField();
        CommonSteps.takeScreenshot();
    }


    @Then("^verify that city, state, and county are automatically fetched$")
    public void verifyCityStateCountyAreFetched() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that city, state, and county are automatically fetched.");
        PageFactory.globalContactPage().verifyAutoFetchedFields();
        CommonSteps.takeScreenshot();
    }


    @And("^user \"([^\"]*)\" global contact of \"([^\"]*)\" with leading and trailing spaces$")
    public void userCreatesGlobalContactWithSpaces(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        if (action.equals("Create")) {
            PageFactory.globalContactPage().globalContactCreationWithSpaces(contactType);
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEditWithSpaces(contactType);
        }
    }

    @Then("^user verifies all the matching records are displayed for Entity Global Contact$")
    public void userVerifiesAllMatchingRecordsDisplayed() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that all displayed records match the entity name");
        PageFactory.globalContactPage().verifyMatchingRecordsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies background color of the \"([^\"]*)\"$")
    public void userVerifiesBackgroundColorOfContactType(String contactType) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying background color for the contact type: " + contactType);
        PageFactory.globalContactPage().verifyBackgroundColorForContactType(contactType);
        CommonSteps.takeScreenshot();
    }



    @Then("^user verifies radio buttons are available for all the contacts$")
    public void userVerifiesRadioButtonsForAllContacts() throws AutomationException {
        CommonSteps.logInfo("Verifying that radio buttons are available for all contacts");
        PageFactory.globalContactPage().verifyRadioButtonsForContacts();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the matching records are displayed for Individual Global Contact$")
    public void userVerifiesAllMatchingRecordsForIndividualGlobalContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying all displayed contact names match the expected individual global contact name");
        PageFactory.globalContactPage().verifyMatchingRecordsForIndividualGlobalContact();
        CommonSteps.takeScreenshot();
    }

    @And("First Name and Last Name fields are pre-filled")
    public void firstNameAndLastNameFieldsArePreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field for First Name and Last name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyfirstNamelastNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }

    @And("user click on the \"([^\"]*)\" button")
    public void userClickOnTheButton(String button) throws AutomationException {
        PageFactory.globalContactPage().clickOn(button);
        CommonSteps.logInfo("click on " + button + "button");
    }

    @Then("user enters already existed EIN")
    public void userEntersAlreadyExistedEIN() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters already existed EIN");
        PageFactory.globalContactPage().enterExistedEIN();

    }

    @And("^user click on \"([^\"]*)\" Button in Footer$")
    public void userClickOnButtonInFooter(String btn) throws AutomationException {
        CommonSteps.logInfo("user clicks on " + btn + " Button");
        PageFactory.globalContactPage().clickButtonInFooter(btn);
    }

    @And("^user enters data Address Line 1 Field$")
    public void userEntersDataAddressLineField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters  data Address Line 1 Field");
        PageFactory.globalContactPage().enterAddressLine1();
    }

    @Then("user verify all the error messages are removed")
    public void userVerifyAllTheErrorMessagesAreRemoved() throws AutomationException {
        CommonSteps.logInfo("verify all the error messages are removed");
        PageFactory.globalContactPage().verifyNoValidationErrors();
    }

    @And("^user verifies \"([^\"]*)\" button is available$")
    public void userVerifiesButtonIsAvailable(String button) throws AutomationException {
        PageFactory.globalContactPage().isButtonAvailable(button);
        CommonSteps.logInfo("Verifies the " + button + " button availablability");
        CommonSteps.takeScreenshot();
    }


    @Then("Verify that the system validates the EIN and SSN formats correctly")
    public void verifyThatTheSystemValidatesTheEINAndSSNFormatsCorrectly() throws AutomationException {
        PageFactory.globalContactPage().validateSSNAndEINFormat();
        CommonSteps.logInfo("Verifies the EIN and SSN formats");
        CommonSteps.takeScreenshot();

    }

    @When("user clicks on the Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        PageFactory.globalContactPage().clickButtonCreate();
    }

    @And("user click on the Close button")
    public void userClickOnTheCloseButton() throws AutomationException {
        PageFactory.globalContactPage().clickButtonClose();
    }

    @Then("user verifies the Global Contacts page")
    public void userVerifiesTheGlobalContactsPage() throws AutomationException {
        CommonSteps.logInfo("User verifies the Global Contacts page");
        PageFactory.globalContactPage().verifyPageGlobalContacts();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the Global Contact Creation page")
    public void userVerifiesTheGlobalContactCreationPage() throws AutomationException {
        CommonSteps.logInfo("User verifies the Global Contact Creation page");
        PageFactory.globalContactPage().verifyPageGlobalContactCreation();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the Contact \\(Select or Create New) page")
    public void userVerifiesTheContactSelectOrCreateNewPage() throws AutomationException {
        CommonSteps.logInfo("User verifies the Contact (Select or Create New) page");
        PageFactory.globalContactPage().verifyPageContactSelectorCreateNew();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on the Close button")
    public void userClicksOnTheCloseButton() throws AutomationException {
        PageFactory.globalContactPage().clickOn("Close");
    }

    @Then("user verifies the Home page")
    public void userVerifiesTheHomePage() throws AutomationException {
        CommonSteps.logInfo("User verifies the Home page");
        PageFactory.globalContactPage().verifyPageHome();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on Mange Address button")
    public void userClicksOnMangeAddressButton() throws AutomationException {
        PageFactory.globalContactPage().clickBtnManageAddress();
    }

    @And("user clicks on Add New Address button")
    public void userClicksOnAddNewAddressButton() throws AutomationException {
        PageFactory.globalContactPage().clickBtnAddNewAddress();
    }

    @And("user fills Address information")
    public void userFillsAddressInformation() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().fillAddressInfo();
    }

    @And("user click on Save button")
    public void userClickOnSaveButton() throws AutomationException {
        PageFactory.globalContactPage().clickButtonSave();
    }

    @And("user switched to edit mode")
    public void userSwitchedToEditMode() throws AutomationException {
        PageFactory.globalContactPage().userSwitchedToEditMode();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies global contact is created successfully")
    public void userVerifiesGlobalContactCreatedSuccessfulMessage() throws AutomationException {
        PageFactory.globalContactPage().verifyGlobalContactCreated();
    }

    @Then("user fills entity and contact information")
    public void userFillsEntityAndContactInformation() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().fillEntityAndContactInfo();
    }

    @Then("user verifies address information saved successfully")
    public void userVerifiesAddressInformationSavedSuccessfully() throws AutomationException {
        PageFactory.globalContactPage().verifyAddressSavedSuccessfully();
    }

    @Then("user fills Contact Person's Details and contact information")
    public void userFillsContactPersonSDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().fillsContactPersonSDetailsAndContactInformation();
    }

    @And("user close the Address bar")
    public void userCloseTheAddressBar() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address updated successfully.")));
        PageFactory.globalContactPage().closeTheAddressBar();
    }

    @Then("user verifies multiple addresses can be added")
    public void userVerifiesMultipleAddressesCanBeAdded() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().addMultipleAddresses();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies address can be edited and reflected the changed address")
    public void userVerifiesAddressCanBeEditedAndReflectedTheChangedAddress() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().editAddress();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies added addresses list displayed correctly")
    public void userVerifiesAddedAddressesListDisplayedCorrectly() throws AutomationException {
        PageFactory.globalContactPage().verifyAddedAddressesListDisplayedCorrectly();
    }
}


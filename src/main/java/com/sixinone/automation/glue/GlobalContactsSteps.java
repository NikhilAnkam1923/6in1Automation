package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.GlobalContactPage;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import cucumber.api.java.en.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.awt.*;
import java.io.IOException;


public class GlobalContactsSteps {
    @When("^user navigate to \"([^\"]*)\"$")
    public void userNavigateToTab(String tab) throws AutomationException, IOException {
        PageFactory.globalContactPage().tabNavigation(tab);
    }


    @And("^user \"([^\"]*)\" global contact of \"([^\"]*)\"$")
    public void userCreatesGlobalContact(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        if (action.equals("Create")) {
            PageFactory.globalContactPage().globalContactCreation(contactType);
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEdit(contactType);
        }
    }

    @And("^user fills all the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        CommonSteps.logInfo("user fills all the details for "+contactType);
        PageFactory.globalContactPage().fillGlobalContactDetails(contactType);
    }

    @And("^user fills all the details for \"([^\"]*)\" with spaces$")
    public void userFillsAllTheDetailsForGlobalContactWithSpaces(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        CommonSteps.logInfo("user fills all the details for "+contactType+" with spaces" );
        PageFactory.globalContactPage().fillGlobalContactDetailsWithSpaces(contactType);
    }

    @Then("user save the global contact")
    public void userSaveTheGlobalContact() throws AutomationException, IOException {
        CommonSteps.logInfo("User save the global contact");
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
        PageFactory.globalContactPage().verifyDuplicateEINError();
        CommonSteps.takeScreenshot();
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


    @Then("user navigates to the page with the records")
    public void userNavigatesToThePageWithTheRecords() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the page with the records");
        PageFactory.globalContactPage().clickButtonCreateIndividualContact();
    }

    @And("user selects a radio button for a record")
    public void userSelectsARadioButtonForARecord() throws AutomationException, IOException {
        CommonSteps.logInfo("user select the radio button");
        PageFactory.globalContactPage().clickButtonRadioButton();
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
        PageFactory.globalContactPage().verifyMatchingRecordsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies background color of the \"([^\"]*)\"$")
    public void userVerifiesBackgroundColorOfContactType(String contactType) throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().verifyBackgroundColorForContactType(contactType);
        CommonSteps.takeScreenshot();
    }


    @Then("^user verifies radio buttons are available for all the contacts$")
    public void userVerifiesRadioButtonsForAllContacts() throws AutomationException {
        CommonSteps.logInfo("Verified that radio buttons are available for all the contacts.");
        PageFactory.globalContactPage().verifyRadioButtonsForContacts();
        CommonSteps.takeScreenshot();
    }

    @And("First Name and Last Name fields are pre-filled")
    public void firstNameAndLastNameFieldsArePreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field for First Name and Last name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyfirstNamelastNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }

    @Then("user enters already existed EIN")
    public void userEntersAlreadyExistedEIN() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters already existed EIN");
        PageFactory.globalContactPage().enterExistedEIN();

    }

    @Then("user verify all the error messages are removed")
    public void userVerifyAllTheErrorMessagesAreRemoved() throws AutomationException {
        PageFactory.globalContactPage().verifyNoValidationErrors();
    }

    @And("^user verifies \"([^\"]*)\" button is available$")
    public void userVerifiesButtonIsAvailable(String button) throws AutomationException {
        CommonSteps.logInfo("Verifies the " + button + " button availability");
        PageFactory.globalContactPage().isButtonAvailable(button);
        CommonSteps.takeScreenshot();
    }


    @Then("Verify that the system validates the EIN and SSN formats correctly")
    public void verifyThatTheSystemValidatesTheEINAndSSNFormatsCorrectly() throws AutomationException {
        CommonSteps.logInfo("Verifies the EIN and SSN formats");
        PageFactory.globalContactPage().validateSSNAndEINFormat();
        CommonSteps.takeScreenshot();

    }

    @When("user clicks on the Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Create button");
        PageFactory.globalContactPage().clickButtonCreate();
    }

    @And("user click on the Close button")
    public void userClickOnTheCloseButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Close button");
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
        CommonSteps.logInfo("User clicks on the Close button");
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
        CommonSteps.logInfo("User clicks on Mange Address button");
        PageFactory.globalContactPage().clickBtnManageAddress();
    }

    @And("user fills Address information")
    public void userFillsAddressInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills Address information");
        PageFactory.globalContactPage().fillAddressInfo();
    }

    @And("user click on Save button")
    public void userClickOnSaveButton() throws AutomationException {
        CommonSteps.logInfo("User click on Save button");
        PageFactory.globalContactPage().clickButtonSave();
    }

    @And("user switched to edit mode")
    public void userSwitchedToEditMode() throws AutomationException {
        CommonSteps.logInfo("User switched to edit mode");
        PageFactory.globalContactPage().userSwitchedToEditMode();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies global contact is created successfully")
    public void userVerifiesGlobalContactCreatedSuccessfulMessage() throws AutomationException {
        PageFactory.globalContactPage().verifyGlobalContactCreated();
    }

    @Then("user fills entity and contact information")
    public void userFillsEntityAndContactInformation() throws AutomationException, IOException, ParseException, AWTException {
        CommonSteps.logInfo("User fills entity and contact information");
        PageFactory.globalContactPage().fillEntityAndContactInfo();
    }

    @Then("user verifies address information saved successfully")
    public void userVerifiesAddressInformationSavedSuccessfully() throws AutomationException {
        PageFactory.globalContactPage().verifyAddressSavedSuccessfully();
    }

    @Then("user fills Contact Person's Details and contact information")
    public void userFillsContactPersonSDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills Contact Person's Details and contact information");
        PageFactory.globalContactPage().fillsContactPersonSDetailsAndContactInformation();
    }

    @And("user close the Address bar")
    public void userCloseTheAddressBar() throws AutomationException {
        CommonSteps.logInfo("User close the Address bar");
        PageFactory.globalContactPage().closeTheAddressBar();
    }

    @Then("user verifies multiple addresses can be added")
    public void userVerifiesMultipleAddressesCanBeAdded() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that multiple addresses can be added");
        PageFactory.globalContactPage().addMultipleAddresses();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies address can be edited and reflected the changed address")
    public void userVerifiesAddressCanBeEditedAndReflectedTheChangedAddress() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that address can be edited and reflected the changed address");
        PageFactory.globalContactPage().editAddress();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies added addresses list displayed correctly")
    public void userVerifiesAddedAddressesListDisplayedCorrectly() throws AutomationException {
        CommonSteps.logInfo("Verified that added addresses list displayed correctly");
        PageFactory.globalContactPage().verifyAddedAddressesListDisplayedCorrectly();
    }

    @Then("user verifies all details of Individual Global Contact are auto-saved")
    public void userVerifiesAllDetailsOfGlobalContactAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that all details of global contact are auto-saved.");
        PageFactory.globalContactPage().verifyIndividualGlobalContactDetailsAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies all details of Entity Global Contact are auto-saved")
    public void userVerifiesAllDetailsOfEntityGlobalContactAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that all details of global contact are auto-saved.");
        PageFactory.globalContactPage().verifyEntityGlobalContactDetailsAutoSaved();
        CommonSteps.takeScreenshot();
    }
}


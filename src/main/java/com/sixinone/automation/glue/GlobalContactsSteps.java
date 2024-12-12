package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.GlobalContactPage;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GlobalContactsSteps {

    @When("^user login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public static void userLoginTo6in1(String userEmail, String password) throws AutomationException {
        PageFactory.loginPage().doLogoutFrom6in1IfAlreadyLoggedIn();
        userEmail = CommonUtil.processString(userEmail);
        password = CommonUtil.processString(password);
        CommonSteps.logInfo("User login with user: " + userEmail + " and password: *********");
        PageFactory.loginPage().loginTo6in1(userEmail, password);
    }

    @When("^user clicks on the \"([^\"]*)\" button$")
    public void userClicksOnButton(String buttonName) throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().clickButton(buttonName);
        CommonSteps.logInfo("Click on the " + buttonName + " button");
    }


    @Then("^user verifies the \"([^\"]*)\" page$")
    public void userVerifiesPage(String pageElement) throws AutomationException, IOException {
        CommonSteps.logInfo("User verifies the " + pageElement + " page");
        PageFactory.globalContactPage().verifyPageElements(pageElement);
        CommonSteps.takeScreenshot();
    }

    @When("^user navigate to \"([^\"]*)\"$")
    public void userNavigateToTab(String tab) throws AutomationException, IOException {
        PageFactory.globalContactPage().tabNavigation(tab);
    }


    @And("^user \"([^\"]*)\" global contact of \"([^\"]*)\"$")
    public void userCreatesGlobalContact(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        if (action.equals("Create")) {
            PageFactory.globalContactPage().globalContactCreation(action, contactType);
            CommonSteps.takeScreenshot();
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEdit(action, contactType);
            CommonSteps.takeScreenshot();
        }
    }

    @And("^user fills all the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.globalContactPage().fillGlobalContactDetails(contactType);
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
        PageFactory.globalContactPage().clearFields();
        PageFactory.globalContactPage().clickButton("Save");
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

    @Then("user should see that validation error messages are removed")
    public void userShouldSeeValidationMessagesRemoved() throws AutomationException {
        CommonSteps.logInfo("Verifying that validation error messages are removed after correcting the fields.");
        PageFactory.globalContactPage().verifyNoValidationErrors();
    }

    @Then("user navigates to the page with the records")
    public void userNavigatesToThePageWithTheRecords() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the page with the records");
        PageFactory.globalContactPage().clickButton("Create Individual Contact");
    }

    @And("user selects a radio button for a record")
    public void userSelectsARadioButtonForARecord() throws AutomationException, IOException {
        CommonSteps.logInfo("user select the radio button");
        PageFactory.globalContactPage().clickButton("Radio Button");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the \"([^\"]*)\" button is enabled$")
    public void userVerifiesTheButtonIsEnabled(String buttonName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that the " + buttonName + " button is enabled.");
        PageFactory.globalContactPage().isButtonEnabled(buttonName);
        CommonSteps.takeScreenshot();
        PageFactory.globalContactPage().clickButton("Close");
    }

    @Then("^user enters \"([^\"]*)\" as the first name and \"([^\"]*)\" as the last name$")
    public void userEntersFirstAndLastName(String firstName, String lastName) throws AutomationException {
        CommonSteps.logInfo("Entering first and last name");
        PageFactory.globalContactPage().enterFirstnameAndLastNameFields(firstName, lastName);
        CommonSteps.takeScreenshot();
    }


    @And("^user \"([^\"]*)\" global contact of \"([^\"]*)\"$")
    public void userCreatesGlobalContact(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        if (action.equals("Create")) {
            PageFactory.globalContactPage().globalContactCreation(action, contactType);
            CommonSteps.takeScreenshot();
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEdit(action, contactType);
            CommonSteps.takeScreenshot();
        }
    }

    @And("^user fills all the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.globalContactPage().fillGlobalContactDetails(contactType);
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
        PageFactory.globalContactPage().clearFields();
        PageFactory.globalContactPage().clickButton("Save");
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

    @Then("user should see that validation error messages are removed")
    public void userShouldSeeValidationMessagesRemoved() throws AutomationException {
        CommonSteps.logInfo("Verifying that validation error messages are removed after correcting the fields.");
        PageFactory.globalContactPage().verifyNoValidationErrors();
    }

    @Then("user navigates to the page with the records")
    public void userNavigatesToThePageWithTheRecords() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the page with the records");
        PageFactory.globalContactPage().clickButton("Create Individual Contact");
    }

    @And("user selects a radio button for a record")
    public void userSelectsARadioButtonForARecord() throws AutomationException, IOException {
        CommonSteps.logInfo("user select the radio button");
        PageFactory.globalContactPage().clickButton("Radio Button");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the \"([^\"]*)\" button is enabled$")
    public void userVerifiesTheButtonIsEnabled(String buttonName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that the " + buttonName + " button is enabled.");
        PageFactory.globalContactPage().isButtonEnabled(buttonName);
        CommonSteps.takeScreenshot();
        PageFactory.globalContactPage().clickButton("Close");
    }


    @And("^user enters SSN and EIN details$")
    public void userEntersSSNAndEIN(DataTable dataTable) throws AutomationException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String ssn = row.get("ssn");
            String ein = row.get("ein");
            PageFactory.globalContactPage().enterSSNAndEIN(ssn, ein);
        }
    }

    @Then("^user enters data in Zip Field$")
    public void userEntersDataInZipField() throws AutomationException {
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
            PageFactory.globalContactPage().globalContactCreationWithSpaces(action, contactType);
        } else if (action.equals("Edit")) {
            PageFactory.globalContactPage().globalContactEdit(action, contactType);
        }
    }

    @And("^user enter data in name fields$")
    public void userEnterDataInNameFields() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().enterDataInNameFields();
        CommonSteps.logInfo("Entered value " + fieldData + " in the field: " + fieldName);
        CommonSteps.takeScreenshot();
    }

    @When("^user edit contact from Global Contact List$")
    public void userEditContactFromGlobalContactList() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Editing contact from Global Contact List");
        PageFactory.globalContactPage().editContact();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies updated values are reflected in Global Contact List$")
    public void userVerifiesUpdatedValuesInGlobalContactList() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying updated values are reflected in Global Contact List using filter");
        PageFactory.globalContactPage().verifyUpdatedValuesInGlobalContactListUsingFilter();
    }

    @And("^Name fields are pre-filled$")
    public void NameFieldsArePreFilled() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying Name fields are pre-filled");
        PageFactory.globalContactPage().verifyNameFieldsArePreFilled();
        CommonSteps.takeScreenshot();
    }

    @And("^user selects Suffix$")
    public void userSelectsSuffix() throws AutomationException {
        CommonSteps.logInfo("Selecting Suffix from the dropdown");
        PageFactory.globalContactPage().selectSuffixOption();
        CommonSteps.takeScreenshot();
    }

    @And("^user verifies Suffix is selected from Dropdown$")
    public void verifyOptionIsSelectedFromSuffixDropdown() throws AutomationException {
        CommonSteps.logInfo("Verifying the option is selected from the Suffix dropdown");
        PageFactory.globalContactPage().verifySuffixOptionSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the matching records are displayed for Entity Global Contact$")
    public void userVerifiesAllMatchingRecordsDisplayed() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that all displayed records match the entity name");
        PageFactory.globalContactPage().verifyMatchingRecordsDisplayed();
        CommonSteps.takeScreenshot();
    }
    @Then("^user verifies background color of the contact type$")
    public void userVerifiesBackgroundColorOfContactType() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying background color for the contact type");
        PageFactory.globalContactPage().verifyBackgroundColorForContactType();
        CommonSteps.takeScreenshot();
    }
    @Then("^user verifies radio buttons are available for all the contacts$")
    public void userVerifiesRadioButtonsForAllContacts() throws AutomationException {
        CommonSteps.logInfo("Verifying that radio buttons are available for all contacts");
        PageFactory.globalContactPage().verifyRadioButtonsForContacts();
        CommonSteps.takeScreenshot();
    }
    @Then("^user verifies Create Entity Contact button is available$")
    public void userVerifiesCreateEntityContactButtonIsAvailable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying the 'Create Entity Contact' button is available");
        PageFactory.globalContactPage().verifyCreateEntityContactButtonAvailable();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the matching records are displayed for Individual Global Contact$")
    public void userVerifiesAllMatchingRecordsForIndividualGlobalContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying all displayed contact names match the expected individual global contact name");
        PageFactory.globalContactPage().verifyMatchingRecordsForIndividualGlobalContact();
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on Name: \"([^\"]*)\" from Global Contact List with Type as \"([^\"]*)\"$")
    public void userClicksOnNameFromGlobalContactListWithType(String name, String type) throws AutomationException {
        PageFactory.globalContactPage().clickNameWithType(name, type);
    }

    @And("^user enters SSN details$")
    public void userEntersSSNDetails(DataTable dataTable) throws AutomationException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String ssn = row.get("ssn");
            PageFactory.globalContactPage().enterSSN(ssn);
        }
    }

    @When("^user enters Entity Name: \"([^\"]*)\"$")
    public void userEntersEntityName(String entityName) throws AutomationException {
        boolean isEntityNameEntered = PageFactory.globalContactPage().enterEntityName(entityName);
        if (!isEntityNameEntered) {
            throw new AutomationException("Failed to enter Entity Name: '" + entityName + "'.");
        }
        CommonSteps.logInfo("User entered Entity Name: '" + entityName + "'.");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the matching records are displayed for Entity Name: \"([^\"]*)\"$")
    public void userVerifiesMatchingRecordsDisplayed(String entityName) throws AutomationException {
        List<String> matchingEntityNames = PageFactory.globalContactPage().getAllDisplayedEntityNames();
        boolean allMatch = matchingEntityNames.stream().allMatch(name -> name.toLowerCase().contains(entityName.toLowerCase()));
        if (!allMatch) {
            throw new AutomationException("Not all displayed entity names match the provided name: '" + entityName + "'. Displayed names: " + matchingEntityNames);
        }
        CommonSteps.logInfo("All displayed entity names match the provided name: '" + entityName + "'.");
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify background color of the contact type: \"([^\"]*)\"$")
    public void verifyBackgroundColorBasedOnClass(String contactType) throws AutomationException {
        List<String> mismatchedRows = PageFactory.globalContactPage().verifyClassForContactType(contactType);
        if (!mismatchedRows.isEmpty()) {
            throw new AutomationException("The background color for the following rows does not match the expected class for contact type '" + contactType + "': " + mismatchedRows);
        }
        CommonSteps.logInfo("Verified background color for all rows with contact type: '" + contactType + "'.");
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify radio buttons are available for all the contacts$")
    public void verifyRadioButtonsForAllContacts() throws AutomationException {
        boolean areRadioButtonsAvailable = PageFactory.globalContactPage().verifyRadioButtonsForContacts();
        if (!areRadioButtonsAvailable) {
            throw new AutomationException("Radio buttons are not available for all the contacts.");
        }
        CommonSteps.logInfo("Verified that radio buttons are available for all the contacts.");
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify \"([^\"]*)\" button is available$")
    public void verifyButtonIsAvailable(String buttonName) throws AutomationException {
        boolean isButtonAvailable = PageFactory.globalContactPage().isButtonAvailable(buttonName);
        if (!isButtonAvailable) {
            throw new AutomationException("The button '" + buttonName + "' is not available.");
        }
        CommonSteps.logInfo("Verified that the '" + buttonName + "' button is available.");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the matching records are displayed for Contact Name: \"([^\"]*)\"$")
    public void userVerifiesAllMatchingRecordsForContactName(String expectedContactName) throws AutomationException {
        List<String> matchingContactNames = PageFactory.globalContactPage().getAllDisplayedContactNames();
        boolean allMatch = matchingContactNames.stream().allMatch(name -> name.equalsIgnoreCase(expectedContactName));
        if (!allMatch) {
            throw new AutomationException("Not all displayed contact names match the provided name: '" + expectedContactName + "'. Displayed names: " + matchingContactNames);
        }
        CommonSteps.logInfo("Verified All displayed contact names match the provided name: '" + expectedContactName + "'.");
        CommonSteps.takeScreenshot();
    }

    @And("First Name and Last Name fields are pre-filled")
    public void firstNameAndLastNameFieldsArePreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field for First Name and Last name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyfirstNamelastNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }

    @And("Entity Name fields is pre-filled")
    public void entityNameFieldsIsPreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field Entity Name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyentityNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }

    @Then("user enters already existed EIN")
    public void userEntersAlreadyExistedEIN() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters already existed EIN");
        PageFactory.globalContactPage().enterExistedEIN();

    }
}


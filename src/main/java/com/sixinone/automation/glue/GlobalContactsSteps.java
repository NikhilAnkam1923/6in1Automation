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
    public void userClicksOnButton(String buttonName) throws AutomationException, IOException {
        Map<String, String> buttonDetails = new HashMap<>();

        switch (buttonName) {
            case "Create":
                buttonDetails.put(buttonName, GlobalContactPage.CREATE_BUTTON);
                break;
            case "Create Individual Contact":
                buttonDetails.put(buttonName, GlobalContactPage.CREATE_INDIVIDUAL_CONTACT_BTN);
                break;
            case "Create Entity Contact":
                buttonDetails.put(buttonName, GlobalContactPage.CREATE_ENTITY_CONTACT_BTN);
                break;
            case "Save":
                buttonDetails.put(buttonName, GlobalContactPage.SAVE_BUTTON);
                break;
            default:
                throw new AutomationException("Unknown button: " + buttonName);
        }
        new GlobalContactPage().clickButton(buttonDetails);

    }


    @Then("^user verifies the \"([^\"]*)\" page$")
    public void userVerifiesPage(String pageName) throws AutomationException, IOException {
        Map<String, String> pageDetails = new HashMap<>();

        switch (pageName) {
            case "Global Contacts":
                pageDetails.put(pageName, GlobalContactPage.GLOBAL_CONTACTS_PAGE);
                break;
            case "Home":
                pageDetails.put(pageName, GlobalContactPage.HOME_PAGE);
                break;
            case "Global Contact Creation":
                pageDetails.put(pageName, GlobalContactPage.GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Individual Global Contact Creation":
                pageDetails.put(pageName, GlobalContactPage.INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Entity Global Contact Creation":
                pageDetails.put(pageName, GlobalContactPage.ENTITY_GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Contact (Select or Create New)":
                pageDetails.put(pageName, GlobalContactPage.SELECT_OR_CREATENEW_PAGE);
                break;
            default:
                throw new AutomationException("Unknown page: " + pageName);
        }
        new GlobalContactPage().verifyPageElements(pageDetails);
        CommonSteps.takeScreenshot();
    }

    @Then("^user enters \"([^\"]*)\" as the first name and \"([^\"]*)\" as the last name$")
    public void userEntersFirstAndLastName(String firstName, String lastName) throws AutomationException {
        CommonSteps.logInfo("Entering first and last name");
        PageFactory.globalContactPage().enterFirstnameAndLastNameFields(firstName, lastName);
        CommonSteps.takeScreenshot();
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

    @Then("^user enter \"([^\"]*)\" in \"([^\"]*)\" Field$")
    public void userEnterInField(String fieldData, String fieldName) throws AutomationException {
        CommonSteps.logInfo("User enters '" + fieldData + "' in the field '" + fieldName + "'.");
        boolean isInputSuccessful = PageFactory.globalContactPage().enterDataInFieldByLabel(fieldData, fieldName);
        if (!isInputSuccessful) {
            throw new AutomationException("Failed to enter data in field with label: '" + fieldName + "'.");
        }
        CommonSteps.logInfo("Entered value " + fieldData + " in the field: " + fieldName);
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify user is on Edit Contact Page$")
    public void verifyUserIsOnEditContactPage() throws AutomationException {
        boolean isOnEditPage = PageFactory.globalContactPage().isUserOnEditContactPage();
        if (!isOnEditPage) {
            throw new AutomationException("User is not on the Edit Contact Page.");
        }
        CommonSteps.logInfo("User successfully navigated to the Edit Contact Page.");
        CommonSteps.takeScreenshot();
    }

    @And("^user verifies updated values \"([^\"]*)\" is reflected in Global Contact List$")
    public void userVerifiesUpdatedValuesAreReflectedInGlobalContactList(String expectedFullName) throws AutomationException {
        boolean isUpdated = PageFactory.globalContactPage().isContactNameUpdated(expectedFullName);
        if (!isUpdated) {
            throw new AutomationException("Updated name '" + expectedFullName + "' is not reflected in the Global Contact List or not found.");
        }
        CommonSteps.logInfo("User Verified updated values are reflected in the Global Contact List: " + expectedFullName);
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify the city \"([^\"]*)\", state \"([^\"]*)\", and county \"([^\"]*)\" are automatically fetched$")
    public void verifyCityStateCountyAreFetched(String expectedCity, String expectedState, String expectedCounty) throws AutomationException {
        CommonSteps.logInfo("Verifying that city, state, and county are auto-fetched.");
        boolean isDataCorrect = PageFactory.globalContactPage().verifyFetchedFields(expectedCity, expectedState, expectedCounty);
        if (!isDataCorrect) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically.");
        }
        CommonSteps.logInfo("Verified auto-fetched values: City - " + expectedCity + ", State - " + expectedState + ", County - " + expectedCounty);
        CommonSteps.takeScreenshot();
    }


    @Then("^user verifies that a confirmation message \"([^\"]*)\" is displayed$")
    public void userVerifiesConfirmationMessage(String confirmationMsg) throws AutomationException {
        PageFactory.globalContactPage().verifyConfirmationMessage(confirmationMsg);
        CommonSteps.takeScreenshot();
    }

    @Then("^user enters \"([^\"]*)\" as the entity name$")
    public void userEntersAsTheEntityName(String entityName) throws AutomationException {
        CommonSteps.logInfo("Entering entity name");
        PageFactory.globalContactPage().enterEntityNameFields(entityName);
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
        } else if (action.equals("Edit"))
            PageFactory.globalContactPage().globalContactEdit(action, contactType);
    }

    @And("^user fills all the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.globalContactPage().fillGlobalContactDetails(contactType);
    }

    @Then("user save the global contact")
    public void userSaveTheGlobalContact() throws AutomationException, IOException {
        PageFactory.globalContactPage().saveGlobalContact();
        CommonSteps.takeScreenshot();
    }

    @And("^user verifies global contact of \"([^\"]*)\" is saved successfully$")
    public void userVerifiesGlobalContactOfIsSavedSuccessfully(String contactType) throws AutomationException {
        PageFactory.globalContactPage().verifyGlobalContactSaved(contactType);
    }

    @And("^user verifies authorization for \"([^\"]*)\"$")
    public void userVerifiesAuthorizationFor(String userType) {
        CommonSteps.logInfo("user verifies authorization for user type: " + userType);
        PageFactory.globalContactPage().verifyUserType(userType);

    }

    @Then("user should see an error message for duplicate EIN")
    public void userShouldSeeAnErrorMessageForDuplicateEIN() throws Throwable {    // Write code here that turns the phrase above into concrete actions    throw new cucumber.api.PendingException();}
        CommonSteps.logInfo("Verifying the error message for invalid or duplicate EIN.");
        PageFactory.globalContactPage().verifyDuplicateEINError();
    }

    @And("user attempts to save the global contact without filling the required fields")
    public void userAttemptsToSaveTheGlobalContactWithoutFillingTheRequiredFields() throws AutomationException, IOException {
        CommonSteps.logInfo("Attempting to save the global contact without filling the required fields.");
        PageFactory.globalContactPage().clearFields();
        PageFactory.globalContactPage().buttonClick("Save");
    }

    @Then("user should see validation error messages for the required fields")
    public void userShouldSeeValidationErrorMessagesForTheRequiredFields() throws AutomationException {
        CommonSteps.logInfo("Verifying validation error messages for the required fields.");
        PageFactory.globalContactPage().verifyRequiredFieldValidationErrors();
    }

    @When("^user fills in the previously empty required fields for \"([^\"]*)\"$")
    public void userFillsInThePreviouslyEmptyRequiredFieldsFor(String contactType) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Filling in the previously empty required fields for contact type: " + contactType);
        PageFactory.globalContactPage().fillRequiredFields(contactType);
    }

    @Then("user should see that validation error messages are removed")
    public void userShouldSeeValidationMessagesRemoved() throws AutomationException {
        CommonSteps.logInfo("Verifying that validation error messages are removed after correcting the fields.");
        PageFactory.globalContactPage().verifyNoValidationErrors();
    }

    @Then("user navigates to the page with the records")
    public void userNavigatesToThePageWithTheRecords() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the page with the records");
        PageFactory.globalContactPage().buttonClick("Create Individual Contact");
    }

    @And("user selects a radio button for a record")
    public void userSelectsARadioButtonForARecord() throws AutomationException, IOException {
        CommonSteps.logInfo("user select the radio button");
        PageFactory.globalContactPage().buttonClick("Radio Button");
    }

    @Then("^user verifies the \"([^\"]*)\" button is enabled$")
    public void userVerifiesTheButtonIsEnabled(String buttonName) throws AutomationException {
        CommonSteps.logInfo("Verifying that the " + buttonName + " button is enabled.");
        boolean isButtonEnabled = PageFactory.globalContactPage().isButtonEnabled(buttonName);
        if (!isButtonEnabled) {
            throw new AutomationException(buttonName + " button is not enabled as expected.");
        }
        CommonSteps.logInfo(buttonName + " button is enabled as expected.");
    }

    @And("^user is on first page$")
    public void userIsOnFirstPage() throws AutomationException {
        boolean isOnFirstPage = PageFactory.globalContactPage().navigateToFirstPage();
        if (isOnFirstPage) {
            CommonSteps.logInfo("User is already on the first page.");
        } else {
            CommonSteps.logInfo("Navigated to the first page.");
        }
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
        boolean allMatch = matchingEntityNames.stream()
                .allMatch(name -> name.toLowerCase().contains(entityName.toLowerCase()));
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
        boolean allMatch = matchingContactNames.stream()
                .allMatch(name -> name.equalsIgnoreCase(expectedContactName));
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
        CommonSteps.logInfo("Verifying field for First Name and Last name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyentityNameFieldPrefilled();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Select & Proceed button")
    public void userClicksOnTheSelectProceedButton() throws AutomationException, IOException {
        CommonSteps.logInfo("user navigates to the Select & Proceed button");
        PageFactory.globalContactPage().buttonClick("Select & Proceed");
    }
}


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

    @When("^user clicks on the \"([^\"]*)\" button$")
    public void userClicksOnButton(String buttonName) throws AutomationException {
        Map<String, String> buttonDetails = new HashMap<>();

        switch (buttonName) {
            case "Global Contact":
                buttonDetails.put(buttonName, GlobalContactPage.GLOBALCONTACT_BUTTON);
                break;
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
    public void userVerifiesPage(String pageName) throws AutomationException {
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
            default:
                throw new AutomationException("Unknown page: " + pageName);
        }
        new GlobalContactPage().verifyPageElements(pageDetails);
        CommonSteps.takeScreenshot();
    }

    @When("^user enters SSN and EIN details$")
    public void userEntersSSNAndEINDetails() throws AutomationException, IOException, ParseException {
        PageFactory.globalContactPage().enterSSNAndEIN();
    }


    @Then("^user enters SSN details$")
    public void userEntersSSNDetails() throws AutomationException {
        CommonSteps.logInfo("Entering data in the SSN field");
        PageFactory.globalContactPage().enterDataInSSNField();
        CommonSteps.takeScreenshot();
    }

    @And("user enters data in Address Line 1 Field")
    public void userEntersDataInAddressLine1Field() throws AutomationException {
        CommonSteps.logInfo("Entering data in the Address Line 1 field");
        PageFactory.globalContactPage().enterAddressLine1Data();
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

    @And("First Name and Last Name fields are pre-filled")
    public void firstNameAndLastNameFieldsArePreFilled() throws AutomationException {
        CommonSteps.logInfo("Verifying field for First Name and Last name is pre-filled with expected value");
        PageFactory.globalContactPage().verifyfirstNamelastNameFieldPrefilled();
        CommonSteps.takeScreenshot();
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


}




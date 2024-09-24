package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.awt.*;

public class AddClientSteps {


    @When("user click on new client button")
    public void userClickOnNewClientButton() throws AutomationException {
        CommonSteps.logInfo("User click on new client button");
        PageFactory.addClientPage().clickOnNewClientButton();
    }

    @When("user verify add client page")
    public void userVerifyAddClientPage() throws AutomationException {
        CommonSteps.logInfo("User verify add client page");
        PageFactory.addClientPage().verifyAddClientPage();
        CommonSteps.takeScreenshot();
    }

    @When("user enter below detail to add new client")
    public void userEnterBelowDetails(DataTable clientData) throws AutomationException, AWTException {
        CommonSteps.logInfo("User enter detail to add new client");
        PageFactory.addClientPage().enterDetails(clientData);
        CommonSteps.takeScreenshot();
    }

    @When("user clicks the Save button to add a new client and verify success message")
    public void userClickOnSaveButton() throws AutomationException {
        CommonSteps.logInfo("User clicks the Save button to add a new client and verify success message");
        PageFactory.addClientPage().clickOnSaveButton();
    }

    @When("user search and update the client record with below details for Primary Contact Name")
    public void userSearchAndUpdateRecord(DataTable clientDetails) throws AutomationException {
        CommonSteps.logInfo("User search and update the client record with below details ");
        PageFactory.addClientPage().updateClientsDetails(clientDetails);
        CommonSteps.takeScreenshot();
    }

    @When("user clicks the Save button and verify updated successfully message")
    public void userClickOnSaveButtonToUpdateRecord() throws AutomationException {
        CommonSteps.logInfo("User clicks the Save button and verify updated successfully message");
        PageFactory.addClientPage().clickOnSaveButtonToUpdateRecord();
    }

    @When("^user deactivating record with client name \"([^\"]*)\"$")
    public void userDeactivatingRecord(String name) throws AutomationException {
        CommonSteps.logInfo("User deactivating record with client name " + name);
        PageFactory.addClientPage().deactivatingRecord(name.trim());

    }

    @When("user search and add the client contact record with below details")
    public void userSearchAndCreateContactRecord(DataTable clientDetails) throws AutomationException {
        CommonSteps.logInfo("user search and add the client contact record with below details ");
        PageFactory.addClientPage().createClientContactDetails(clientDetails);
        CommonSteps.takeScreenshot();
    }
    @When("user clicks the create contact button to add a new client contact and verify success message")
    public void userClickOnCreateContactToSaveRecord() throws AutomationException {
        CommonSteps.logInfo("user clicks the create contact button to add a new client contact and verify success message");
        PageFactory.addClientPage().clickOnCreateContactButtonToSaveRecord();
    }
    @When("user clicks the create contact button to add a new client contact and verify error message")
    public void userClickOnCreateContactToInvalidRecord() throws AutomationException {
        CommonSteps.logInfo("user clicks the create contact button to add a new client contact and verify error message");
        PageFactory.addClientPage().clickOnCreateContactButtonToInvalidRecord();
    }

    @Then("user click on client button")
    public void userClickOnClientButton() throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.addClientPage().clickOnClientButton();
        CommonSteps.takeScreenshot();
    }

    @Then("^user select the client \"([^\"]*)\"$")
    public void ClickOnCampaignButton(String clientName) throws AutomationException {
        CommonSteps.logInfo("user select the client "+clientName);
        PageFactory.addClientPage().selectTheClient(clientName);

    }

    @Then("^user verify the redirected to the campaign page \"([^\"]*)\"$")
    public void SelectOneOfTheCampaignNameFromCampaignPage(String campaignName) throws AutomationException {
        CommonSteps.logInfo("user verify the redirected to the campaign page");
        PageFactory.addClientPage().verifyCampaignPage(campaignName);
        CommonSteps.takeScreenshot();
    }

    @Then("user click on report button")
    public void userSelectOneOfTheClientsFromTheListAndClickOnReportButton() throws AutomationException {
        CommonSteps.logInfo("user click on report button");
        PageFactory.addClientPage().clickOnReportButton();

    }

    @Then("^user click on the \"([^\"]*)\" report button$")
    public void userClickOnReportButtonAndVerifyUserShouldBeRedirectedToTheReportPage(String reportName) throws AutomationException {
        CommonSteps.logInfo("user click on the "+reportName+" report button");
        PageFactory.addClientPage().clickOnReportAndVerifyReportPage(reportName);
        CommonSteps.takeScreenshot();
    }

    @And("^user deactivating record with contact name \"([^\"]*)\" \"([^\"]*)\"$")
    public void userDeactivatingClientRecord(String firstName,String lastName) throws AutomationException {
        CommonSteps.logInfo("user deactivating record with contact name " + firstName+" "+lastName);
        PageFactory.addClientPage().userDeactivatingClientRecord(firstName,lastName);

    }

    @Then("user click on campaign button")
    public void userClickOnCampaignButton() throws AutomationException {
        CommonSteps.logInfo("user click on campaign button");
        PageFactory.addClientPage().clickOnCampaignButton();
    }

    @And("^user Select the Campaign Name \"([^\"]*)\"$")
    public void userSelectTheCampaignName(String campaignName) throws AutomationException {
        CommonSteps.logInfo("user Select the Campaign Name "+campaignName);
        PageFactory.addClientPage().selectCampaign(campaignName);
    }

    @And("user verify the redirected to the report page")
    public void verifyUserShouldBeRedirectedToTheReportPage() throws AutomationException {
        CommonSteps.logInfo("user verify the redirected to the report page");
        PageFactory.addClientPage().verifyReportPage();
    }

    @Then("user click on edit contact button to verify update successfully message")
    public void userClickOnEditContactButtonToVerifyUpdateSuccessfullyMessage() throws AutomationException {
        CommonSteps.logInfo("user click on edit contact button to verify update successfully message");
        PageFactory.addClientPage().clickOnSaveUpdateRecord();
    }

    @Then("user click on Contact button")
    public void userClickOnContactButton() throws AutomationException {
        CommonSteps.logInfo("user click on Contact button");
        PageFactory.addClientPage().clickOnContactButton();
    }

    @And("user search and update the client contact record with below details")
    public void userSearchAndUpdateTheClientContactRecordWithBelowDetails(DataTable contacrDetails) throws AutomationException {
        CommonSteps.logInfo("user search and update the client contact record with below details");
        PageFactory.addClientPage().createContactUpdateDetails(contacrDetails);
        CommonSteps.takeScreenshot();
    }

    @And("user click on edit contact button to verify update contact with invalid dat and verify error message")
    public void userClickOnEditContactButtonToVerifyUpdateContactWithInvalidDatAndVerifyErrorMessage() throws AutomationException {
        CommonSteps.logInfo("user click on edit contact button to verify update contact with invalid dat and verify error message");
        PageFactory.addClientPage().clickOnSaveButtonToInvalidRecord();
    }

    @And("user click on proposal button")
    public void userClickOnProposalButton() throws AutomationException {
        CommonSteps.logInfo("user click on proposal button");
        PageFactory.addClientPage().clickOnProposalButton();
    }

    @And("^user verify the redirected to the proposal page \"([^\"]*)\"$")
    public void userVerifyTheRedirectedToTheProposalPage(String proposalName) throws AutomationException {
        CommonSteps.logInfo("user verify the redirected to the proposal page");
        PageFactory.addClientPage().verifyProposalPage(proposalName);
    }

    @Then("^user search the client \"([^\"]*)\"$")
    public void userSearchTheClient(String clientName) throws AutomationException {
        CommonSteps.logInfo("user search the client "+clientName);
        PageFactory.addClientPage().searchTheClient(clientName);

    }

    @Then("user verify the below details are correctly displayed in the client list")
    public void userVerifyTheBelowDetailsAreCorrectlyDisplayedInTheClientList(DataTable clientDetails) throws AutomationException {
        CommonSteps.logInfo("user verify the below details are correctly displayed in the client list ");
        PageFactory.addClientPage().userVerifyClientDetails(clientDetails);
    }

    @Then("user select the inactive client \"([^\"]*)\"$")
    public void userSelectTheInactiveClient(String status) throws AutomationException {
        CommonSteps.logInfo("user select the inactive client");
        PageFactory.addClientPage().SelectTheInactiveClient(status);
    }

    @And("^user click on active button \"([^\"]*)\"$")
    public void userClickOnActiveButton(String name) throws AutomationException {
        CommonSteps.logInfo("user click on active button:"+name);
        PageFactory.addClientPage().userClickOnActiveButton(name);
    }

    @And("^user click on edit client button and enter empty name \"([^\"]*)\"")
    public void userClickOnEditClientButtonAndEnterEmptyName(String name) throws AutomationException {
        CommonSteps.logInfo("user click on edit client button and enter empty name:");
        PageFactory.addClientPage().userClickOnEditClientButton(name);
    }

    @And("user clicks the Save button and verify error message")
    public void userClicksTheSaveButtonAndVerifyErrorMessage() throws AutomationException {
        CommonSteps.logInfo("user clicks the Save button and verify error message");
        PageFactory.addClientPage().clickOnSaveButtonInvalidRecord();
    }

    @And("user click on the Save button and verify error message")
    public void userClicksTheSaveButtonToVerifyErrorMessage() throws AutomationException {
        CommonSteps.logInfo("user click on the Save button to verify error message");
        PageFactory.addClientPage().clickOnSaveButtonVerifyErrorMessage();
    }
}

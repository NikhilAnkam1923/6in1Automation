package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

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
    public void userEnterBelowDetails(DataTable clientData) throws AutomationException {
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
        CommonSteps.logInfo("User search and update the client record with below details " + clientDetails.toString());
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
        CommonSteps.logInfo("user search and add the client contact record with below details " + clientDetails.toString());
        PageFactory.addClientPage().createClientContactDetails(clientDetails);
        CommonSteps.takeScreenshot();
    }
    @When("user clicks the create contact button to add a new client contact and verify success message")
    public void userClickOnCreateContactToSaveRecord() throws AutomationException {
        CommonSteps.logInfo("user clicks the create contact button to add a new client contact and verify success message");
        PageFactory.addClientPage().clickOnCreateContactButtonToSaveRecord();
    }

    @Then("user click on client button")
    public void userClickOnClientButton() throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.addClientPage().clickOnClientButton();
        CommonSteps.takeScreenshot();
    }

    @Then("user Select one of the clients from the list \"([^\"]*)\" and click on campaign button")
    public void ClickOnCampaignButton(String clientName) throws AutomationException {
        CommonSteps.logInfo("user Select one of the clients from the list "+clientName+" and click on campaign button");
        PageFactory.addClientPage().clickOnCampaignButton(clientName);
        CommonSteps.takeScreenshot();
    }

    @Then("user Select one of the Campaign  Name from the list {string} and verify user should be redirected to the campaign page")
    public void SelectOneOfTheCampaignNameFromCampaignPage(String campaignName) throws AutomationException {
        CommonSteps.logInfo("user Select one of the Campaign  Name from the list "+campaignName+" and verify user should be redirected to the campaign page");
        PageFactory.addClientPage().clickOnCampaignNameAndVerifyCampaignPage(campaignName);
        CommonSteps.takeScreenshot();

    }

    @Then("user Select one of the clients from the list {string} and click on report button")
    public void userSelectOneOfTheClientsFromTheListAndClickOnReportButton(String clientName) throws AutomationException {
        CommonSteps.logInfo("user Select one of the clients from the list"+clientName+" and click on campaign button");
        PageFactory.addClientPage().clickOnReportButton(clientName);
        CommonSteps.takeScreenshot();
    }

    @Then("user click on {string} report button and verify user should be redirected to the report page")
    public void userClickOnReportButtonAndVerifyUserShouldBeRedirectedToTheReportPage(String reportName) throws AutomationException {
        CommonSteps.logInfo("user click on "+reportName+" report button and verify user should be redirected to the report page");
        PageFactory.addClientPage().clickOnReportAndVerifyReportPage(reportName);
        CommonSteps.takeScreenshot();
    }
}

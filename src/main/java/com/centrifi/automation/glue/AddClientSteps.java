package com.centrifi.automation.glue;


import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
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
        CommonSteps.logInfo("user enter detail to add new client " + clientData.toString());
        PageFactory.addClientPage().enterDetails(clientData);
        CommonSteps.takeScreenshot();
    }

    @When("user clicks the Save button to add a new client and verify success message")
    public void userClickOnSaveButton() throws AutomationException {
        CommonSteps.logInfo("user clicks the Save button to add a new client and verify success message");
        PageFactory.addClientPage().clickOnSaveButton();
    }

    @When("user search and update the client record with below details for Primary Contact Name")
    public void userSearchAndUpdateRecord(DataTable clientDetails) throws AutomationException {
        CommonSteps.logInfo("user search and update the client record with below details " + clientDetails.toString());
        PageFactory.addClientPage().updateClientsDetails(clientDetails);
        CommonSteps.takeScreenshot();
    }

    @When("user clicks the Save button and verify updated successfully message")
    public void userClickOnSaveButtonToUpdateRecord() throws AutomationException {
        CommonSteps.logInfo("user clicks the Save button and verify updated successfully message");
        PageFactory.addClientPage().clickOnSaveButtonToUpdateRecord();
    }

    @When("^user deactivating record with client name \"([^\"]*)\"$")
    public void userDeactivatingRecord(String name) throws AutomationException {
        CommonSteps.logInfo("user deactivating record with client name " + name);
        PageFactory.addClientPage().deactivatingRecord(name.trim());

    }

}

package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

public class AddClientIntegrationSteps {

    @Then("user click on integration button")
    public void ClickOnIntegrationButton() throws AutomationException {
        CommonSteps.logInfo("user click on integration button");
        PageFactory.addClientIntegrationPage().clickOnIntegrationButton();

    }

    @Then("user enter below details to connect email platform details")
    public void enterTheValidInputFromEmailPlatformAndClickOnConnectButton(DataTable dataTable) throws AutomationException {
        CommonSteps.logInfo("user enter below details to connect email platform details");
        PageFactory.addClientIntegrationPage().enterTheEmailDetails(dataTable);
        CommonSteps.takeScreenshot();
    }
    @And("click on Email connect button")
    public void clickOnEmailConnectButton() throws AutomationException {
        CommonSteps.logInfo("click on Email connect button");
        PageFactory.addClientIntegrationPage().clickOnEmailContinueButton();
        CommonSteps.takeScreenshot();
    }

    @Then("^user click on \"([^\"]*)\" disconnect button and verify disconnected successfully Message$")
    public void userClickOnDisconnectButton(String platFromName) throws AutomationException {
        CommonSteps.logInfo("user click on "+platFromName+" disconnect button and verify disconnected successfully Message");
        PageFactory.addClientIntegrationPage().userClickOnDisconnectButton(platFromName);
        CommonSteps.takeScreenshot();
    }

    @Then("user enter below detail to connect to facebook")
    public void ClickOnConnectButtonFacebookPlatform(DataTable faceBookDetails) throws AutomationException {
        CommonSteps.logInfo("user enter below detail to connect to facebook");
        PageFactory.addClientIntegrationPage().enterFaceBookDetails(faceBookDetails);
        CommonSteps.takeScreenshot();
    }

    @Then("user enter below details to connect Simpli platform")
    public void enterTheInputInputDetailstoConnectSimpliPlatform(DataTable simpliDetails) throws AutomationException {
        CommonSteps.logInfo("user enter below details to connect Simpli platform");
        PageFactory.addClientIntegrationPage().enterTheSimpliplatformDetails(simpliDetails);
        CommonSteps.takeScreenshot();
    }

    @Then("user enter below details to connect google platform")
    public void enterTheValidInputFromGooglePlatform(DataTable googleDetails) throws AutomationException {
        CommonSteps.logInfo("user enter below details to connect google platform");
        PageFactory.addClientIntegrationPage().clickOnGoogleContinueButton(googleDetails);
        CommonSteps.takeScreenshot();

    }

    @And("click on facebook connect button")
    public void clickOnFacebookConnectButton() throws AutomationException {
        CommonSteps.logInfo("click on Email connect button");
        PageFactory.addClientIntegrationPage().clickOnFaceBookContinueButton();
        CommonSteps.takeScreenshot();
    }

    @And("click on Simpli connect button")
    public void clickOnSimpliConnectButton() throws AutomationException {
        CommonSteps.logInfo("click on Simpli connect button");
        PageFactory.addClientIntegrationPage().clickOnSimpliPlatform();
        CommonSteps.takeScreenshot();
    }

    @And("^click on google connect button$")
    public void clickOnGoogleConnectButton() throws AutomationException {
        CommonSteps.logInfo("click on google connect button");
        PageFactory.addClientIntegrationPage().clickOnGooglePlatform();
        CommonSteps.takeScreenshot();
    }
}

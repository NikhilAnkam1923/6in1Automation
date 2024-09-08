package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.Then;

public class AddClientIntegrationSteps {

    @Then("user Select one of the clients from the list \"([^\"]*)\" and click on integration button")
    public void ClickOnCampaignButton(String clientName) throws AutomationException {
        CommonSteps.logInfo("user Select one of the clients from the list "+clientName+" and click on integration button");
        PageFactory.addClientIntegrationPage().clickOnIntegrationButton(clientName);
        CommonSteps.takeScreenshot();
    }

    @Then("Enter the valid input from email platform {string} {string} {string} {string} and click on connect button")
    public void enterTheValidInputFromEmailPlatformAndClickOnConnectButton(String clientName, String authName,String reportFlag, String reportName) throws AutomationException {
        CommonSteps.logInfo("Enter the valid input from email platform "+clientName, authName+" and click on connect button");
        PageFactory.addClientIntegrationPage().clickOnEmailContinueButton(clientName,authName,reportFlag,reportName);
        CommonSteps.takeScreenshot();
    }

    @Then("user click on {string} disconnect button and verify connected platform removed successfully or not")
    public void userClickOnDisconnectButton(String platFromName) throws AutomationException {
        CommonSteps.logInfo("user click on "+platFromName+" disconnect button and verify connected platform removed successfully or not");
        PageFactory.addClientIntegrationPage().userClickOnDisconnectButton(platFromName);
        CommonSteps.takeScreenshot();
    }

    @Then("Enter the valid input from facebook platform {string} {string} {string} {string} {string} and click on connect button")
    public void ClickOnConnectButtonFacebookPlatform(String acctId, String pageId, String authId,String reportFlag, String reportName) throws AutomationException {
        CommonSteps.logInfo("Enter the valid input from facebook platform "+acctId,pageId,authId,reportFlag,reportName+" and click on connect button");
        PageFactory.addClientIntegrationPage().clickOnFaceBookContinueButton(acctId,pageId,authId,reportFlag,reportName);
        CommonSteps.takeScreenshot();

    }

    @Then("Enter the valid input from Simpli platform {string} {string} {string} {string} and click on connect button")
    public void enterTheValidInputFromSimpliPlatformAndClickOnConnectButton(String OrgId, String authName, String reportFlag , String reportName) throws AutomationException {
        CommonSteps.logInfo("Enter the valid input from Simpli platform "+OrgId, reportName+" and click on connect button");
        PageFactory.addClientIntegrationPage().clickOnSimpliContinueButton(OrgId,authName,reportFlag,reportName);
        CommonSteps.takeScreenshot();
    }

    @Then("Enter the valid input from google platform {string} {string} {string} {string} {string} {string} {string} and click on connect button")
    public void enterTheValidInputFromGooglePlatform(String integrationType, String accountId_Name, String ownerAccId_PropertyId, String authName, String ReportFlag, String ReportName, String LocationName) throws AutomationException {
        CommonSteps.logInfo("Enter the valid input from google platform "+integrationType, accountId_Name,ownerAccId_PropertyId,LocationName+" and click on connect button");
        PageFactory.addClientIntegrationPage().clickOnGoogleContinueButton(integrationType,accountId_Name,ownerAccId_PropertyId,authName,ReportFlag,ReportName,LocationName);
        CommonSteps.takeScreenshot();

    }
}

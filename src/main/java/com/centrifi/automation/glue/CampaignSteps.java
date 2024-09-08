package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CampaignSteps {

    @When("user click on Campaigns button")
    public void userClickOnACampaignsButton() throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.campaignPage().clickOnCampaignsButton();
    }

    @Then("user click on Add new campaign button")
    public void userClickOnAddNewCampaignButton() throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.campaignPage().clickOnCAddNewCampaignButton();
    }

    @And("user enter the campaign input details and click on next {string} {string} {string} {string}")
    public void userEnterTheCampaignInputDetailsAndClickOnNext(String campaignName,String Budget,String ClientName,String CampaignGoal) throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.campaignPage().enterCampaignInputDetails(campaignName,Budget,ClientName,CampaignGoal);
    }
}


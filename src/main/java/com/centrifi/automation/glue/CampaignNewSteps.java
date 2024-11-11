package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.*;

import java.util.Arrays;


public class CampaignNewSteps {

    @When("user click on Campaign in the menu")
    public void userClickOnCampaign() throws AutomationException {
        CommonSteps.logInfo("User clicks on Campaign in the menu");
        PageFactory.campaignNewPage().clickOnCampaignsButton();
    }

    @Then("user observe the page")
    public void userObserveThePage() throws AutomationException {
        CommonSteps.logInfo("user observe the page");
        PageFactory.campaignNewPage().verifyThePage();
    }

    @And("^user select All Statuses as \"([^\"]*)\"$")
    public void userSelectAllStatuses(String allStatusOption) throws AutomationException {
        CommonSteps.logInfo("user select All Statuses as " + allStatusOption);
        PageFactory.campaignNewPage().selectAllStatusDropdown(allStatusOption);
    }

    @And("user select All Date Ranges as \"([^\"]*)\"$")
    public void userSelectAllDateRanges(String allDateRanges) throws AutomationException {
        CommonSteps.logInfo("user select All Date Ranges as " + allDateRanges);
        PageFactory.campaignNewPage().selectAllDateRangeDropdown(allDateRanges);
    }

    @And("user select Organization as \"([^\"]*)\"$")
    public void userSelectOrganization(String organization) throws AutomationException {
        CommonSteps.logInfo("user select Organization as " + organization);
        PageFactory.campaignNewPage().selectOrganizationDropdown(organization);

    }

    @And("User enters in the search field as \"([^\"]*)\"$")
    public void userEntersInTheSearchFieldAs(String search) throws AutomationException {
        CommonSteps.logInfo("User enters in the search field as " + search);
        PageFactory.campaignNewPage().entersValuesInSearchField(search);
    }

    @And("user click on New Campaign button")
    public void userClickOnNewCampaignButton() throws AutomationException {
        CommonSteps.logInfo("user click on New Campaign button:");
        PageFactory.campaignNewPage().clickOnNewCampaignButton();
    }


    @Then("user enter the campaign overview input details {string} {string} {string} {string} and click on next")
    public void userEnterTheCampaignOverviewInputDetailsAndClickOnNext(String campaignName,String campaignGoal,String client,String budget) throws AutomationException {
        CommonSteps.logInfo("user enter the campaign overview input details and click on next");
        PageFactory.campaignNewPage().enterCampaignOverviewInputDetailsAndClickOnNext(campaignName,campaignGoal,client,budget);
    }
}

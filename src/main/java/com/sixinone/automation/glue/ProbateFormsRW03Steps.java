package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsRW03Steps {

    @When("user navigates to the probate forms tab")
    public void userNavigatesToTheProbateFormsTab() throws AutomationException {
        PageFactory.probateFormsRW03Page().navigateToEstateContactsTab();
    }

    @Then("user click on the RW{int}")
    public void userClickOnTheRW(int arg0) {
    }

    @When("user select estate and retrieve the entered county, estate and aka names")
    public void userSelectEstateAndRetrieveTheEnteredCountyEstateAndAkaNames() {
    }
}

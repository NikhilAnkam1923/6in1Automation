package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProbateFormsRW05Steps {

    @When("user check the notary checkbox")
    public void userCheckTheNotaryCheckbox() throws AutomationException {
        CommonSteps.logInfo("user check the notary checkbox");
        PageFactory.probateFormsRW05Page().userCheckTheNotaryCheckbox();
    }

    @Then("user verifies notary block is displayed")
    public void userVerifiesNotaryBlockIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that notary block is displayed");
        PageFactory.probateFormsRW05Page().verifyNotaryBlockIsDisplayed();
        CommonSteps.takeScreenshot();
    }
}

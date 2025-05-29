package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class probateFormsOC05Steps {
    @Then("user selects new Counsel")
    public void userSelectsNewCounsel() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user selects new Counsel");
        PageFactory.probateFormsOC05Page().userSelectsNewCounsel();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies new Counsel details are populated correctly on the form")
    public void userVerifiesNewCounselDetailsArePopulatedCorrectlyOnTheForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that new Counsel details are populated correctly on the form");
        PageFactory.probateFormsOC05Page().verifyNewCounselDetailsArePopulatedCorrectlyOnTheForm();
        CommonSteps.takeScreenshot();
    }
}
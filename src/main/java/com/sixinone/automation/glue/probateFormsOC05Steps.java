package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class probateFormsOC05Steps {
    @Then("user selects new Counsel")
    public void userSelectsNewCounsel() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user selects new Counsel");
        PageFactory.probateFormsOC05Page().userSelectsNewCounsel();
    }

    @Then("user verifies new Counsel details are populated correctly on the form")
    public void userVerifiesNewCounselDetailsArePopulatedCorrectlyOnTheForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that new Counsel details are populated correctly on the form");
        PageFactory.probateFormsOC05Page().verifyNewCounselDetailsArePopulatedCorrectlyOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @When("user enters agent's name and address details")
    public void userEntersAgentSNameAndAddressDetails() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("user enters agent's name and address details");
        PageFactory.probateFormsOC05Page().userEntersAgentSNameAndAddressDetails();
    }

    @Then("user verifies agent's name and address details are auto saved")
    public void userVerifiesAgentSNameAndAddressDetailsAreAutoSaved() throws AutomationException {
        CommonSteps.logInfo("Verified that agent's name and address details are auto saved");
        PageFactory.probateFormsOC05Page().verifyAgentSNameAndAddressDetailsAreAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @When("user edit agent's name and address details")
    public void userEditAgentSNameAndAddressDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user edit agent's name and address details");
        PageFactory.probateFormsOC05Page().userEditAgentSNameAndAddressDetails();
    }

    @Then("user verifies estate's name is auto fetched and correctly displayed on page 3")
    public void userVerifiesEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that estate's name is auto fetched and correctly displayed on page 3");
        PageFactory.probateFormsOC05Page().verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage3();
        CommonSteps.takeScreenshot();
    }
}
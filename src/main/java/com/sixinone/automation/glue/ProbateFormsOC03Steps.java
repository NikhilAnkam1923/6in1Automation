package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsOC03Steps {
    @Then("^user verifies the 'Account of' field is populated with the Fiduciary’s name$")
    public void userVerifiesTheAccountOfFieldIsPopulatedWithTheFiduciarySName() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that the 'Account of' field is populated with the Fiduciary’s name");
        PageFactory.probateFormsOC03Page().verifyAccountOfFieldIsPopulatedWithTheFiduciarySName();
        CommonSteps.takeScreenshot();
    }
}

package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC07Steps {

    @Then("user verifies the estate name, address, date of death, and file number are auto-filled from estate records")
    public void userVerifiesTheEstateNameAddressDateOfDeathAndFileNumberAreAutoFilledFromEstateRecords() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verified the estate name, address, date of death, and file number are auto-filled from estate records");
        PageFactory.probateFormsOC07Page().verifyEstateNameAddressDateOfDeathAndFileNumberAutofilled();
        CommonSteps.takeScreenshot();
    }
}

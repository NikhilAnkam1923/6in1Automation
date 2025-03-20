package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRWxxSteps {
    @Then("user verifies text can be entered in all the text areas and is auto saved")
    public void userVerifiesTextCanBeEnteredInAllTheTextAreasAndIsAutoSaved() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that text can be entered in all the text areas and is auto saved");
        PageFactory.probateFormsRWxxPage().verifyTextCanBeEnteredInAllTheTextAreasAndIsAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies name entered in first text field is reflected in signature field")
    public void userVerifiesNameEnteredInFirstTextAreaIsReflectedInSignatureField() throws AutomationException {
        CommonSteps.logInfo("Verified that name entered in first text area is reflected in signature field");
        PageFactory.probateFormsRWxxPage().verifyNameEnteredInFirstTextAreaIsReflectedInSignatureField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies name updated in signature field is reflected in the reviewer name Field")
    public void userVerifiesNamesUpdatedInSignatureFieldIsReflectedInTheReviewerNameField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that name updated in signature field is reflected in the reviewer name Field");
        PageFactory.probateFormsRWxxPage().verifyNamesUpdatedInSignatureFieldIsReflectedInTheReviewerNameField();
        CommonSteps.takeScreenshot();
    }
}

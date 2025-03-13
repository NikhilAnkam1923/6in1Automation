package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class ProbateFormsRW03Steps {

//    @When("user click on print form button")
//    public void userClickOnPrintFormButton() throws AutomationException, InterruptedException, AWTException {
//        CommonSteps.logInfo("user click on print form button");
//        PageFactory.probateFormsRW03Page().clickOnPrintFormButton();
//
//    }

//    @Then("verify form can be printed in pdf with name as {string}")
//    public void verifyFormCanBePrintedInPdfWithNameAsRW(String formName) throws AutomationException {
//        CommonSteps.logInfo("verify form can be printed in pdf");
//        PageFactory.probateFormsRW03Page().verifyFormPrintedInPDFForm(formName);
//    }

    @And("verify all the fields entered are correctly reflected in the pdf")
    public void verifyAllTheFieldsEnteredAreCorrectlyReflectedInThePdf() throws AutomationException, IOException {
        CommonSteps.logInfo("verify all the fields entered are correctly reflected in the pdf");
        PageFactory.probateFormsRW03Page().verifyAllFieldsInDownloadedPDF();
    }

}

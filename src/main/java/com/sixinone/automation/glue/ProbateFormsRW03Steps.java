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

    @And("user verifies the county, estate and aka names are auto-populated on the form")
    public void userVerifiesTheCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        PageFactory.probateFormsRW03Page().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
        CommonSteps.logInfo("Verified that the county, estate and aka names are auto-populated on the form");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies witness's name is not auto populated and the fields are empty")
    public void userVerifiesWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty() throws Exception {
        CommonSteps.logInfo("Verified that witness's name is not auto populated and the fields are empty");
        PageFactory.probateFormsRW03Page().verifyWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies witness fields accept names and same names are reflected in signature fields")
    public void userVerifiesWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that witness fields accept names and same names are reflected in signature fields");
        PageFactory.probateFormsRW03Page().verifyWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields();
        CommonSteps.takeScreenshot();
    }

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

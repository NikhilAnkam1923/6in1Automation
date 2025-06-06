package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW04Steps {

    @Then("user verifies correct estate, county and AKA names are displayed under header")
    public void userVerifiesCorrectCountyNameIsDisplayedUnderHeader() throws AutomationException {
        CommonSteps.logInfo("Verified that correct estate, county and AKA names are displayed under header");
        PageFactory.probateFormsRW04Page().verifyEstateCountyAKANameUnderHeader();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies county and aka names are auto populated on the form")
    public void userVerifiesCountyAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that county and aka names are auto populated on the form");
        PageFactory.probateFormsRW04Page().verifyCountyAndAkaNamesAreAutoPopulatedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies correct estate's name is displayed across the form")
    public void userVerifiesCorrectEstateSNameIsDisplayedAcrossTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that correct estate's name is displayed across the form");
        PageFactory.probateFormsRW04Page().verifyCorrectEstateSNameIsDisplayedAcrossTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent name on the form is auto populated from the estate")
    public void userVerifiesDecedentNameOnTheFormIsAutoPopulatedFromTheEstate() throws AutomationException {
        CommonSteps.logInfo("Verified that decedent name on the form is auto populated from the estate");
        PageFactory.probateFormsRW04Page().verifyDecedentNameOnTheFormIsAutoPopulatedFromTheEstate();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies names can be entered in both the witness fields and reflected below in signature fields")
    public void userVerifiesNamesCanBeEnteredInBothTheWitnessFieldsAndReflectedBelowInSignatureFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that names can be entered in both the witness fields and reflected below in signature fields");
        PageFactory.probateFormsRW04Page().verifyNamesCanBeEnteredAndReflectedInSignatureFields();
        CommonSteps.takeScreenshot();
    }


}

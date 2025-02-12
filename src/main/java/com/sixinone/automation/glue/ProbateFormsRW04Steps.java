package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW04Steps {

    @And("user saves entered Estate information")
    public void userSavesEstateInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user saves Estate Information");
        PageFactory.probateFormsRW04Page().userSavesEstateInfo();
    }

    @When("user navigate to the Probate forms Tab")
    public void userNavigatesToTheProbateFormsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the probate forms tab");
        PageFactory.probateFormsRW04Page().navigateToProbateFormsTab();
    }

    @Then("^user click on the \"([^\"]*)\" Form$")
    public void userClickOnTheRW(String formToSelect) throws AutomationException {
        CommonSteps.logInfo("user click on the "+formToSelect+" form");
        PageFactory.probateFormsRW04Page().clickOnRWForm(formToSelect);
    }

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

    @Then("user verifies witnesses name, address and signature fields are Editable and in yellow background")
    public void userVerifiesWitnessesNameAddressAndSignatureFieldsAreEditableAndInYellowBackground() throws AutomationException {
        CommonSteps.logInfo("Verified that witnesses name, address and signature fields are Editable and in yellow background");
        PageFactory.probateFormsRW04Page().verifyFieldsAreEditableAndYellowInBackground();
        CommonSteps.takeScreenshot();
    }
}

package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW02Steps {

    @When("^user opens \"([^\"]*)\" Estate$")
    public void userOpensEstate(String estateName) throws AutomationException {
        CommonSteps.logInfo("user opens "+estateName+" Estate");
        PageFactory.probateFormsRW02Page().openEstate(estateName);
    }

    @When("user navigate to the Probate forms tab")
    public void userNavigateToTheProbateFormsTab() throws AutomationException {
        CommonSteps.logInfo("user navigate to the Probate forms tab");
        PageFactory.probateFormsRW02Page().navigateToProbateFormsTab();
    }

    @Then("^user clicks on the \"([^\"]*)\" Form$")
    public void userClickOnTheRW(String formToSelect) throws AutomationException {
        CommonSteps.logInfo("user click on the "+formToSelect+" form");
        PageFactory.probateFormsRW02Page().clickOnRWForm(formToSelect);
    }

    @And("user saves entered Estate Information")
    public void userSavesEstateInformation() throws AutomationException {
        CommonSteps.logInfo("user saves Estate Information");
        PageFactory.probateFormsRW02Page().userSavesEstateInfo();
    }

    @Then("user verifies correct county name is fetched from the decedent info")
    public void userVerifiesCorrectCountyNameIsFetchedFromTheDecedentInfo() throws AutomationException {
        CommonSteps.logInfo("Verified that correct county name is fetched from the decedent info");
        PageFactory.probateFormsRW02Page().verifyCorrectCountyName();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent information is auto populated on the form")
    public void userVerifiesDecedentInformationIsAutoPopulatedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that decedent information is auto populated on the form");
        PageFactory.probateFormsRW02Page().verifyDecedentInformationIsAutoPopulatedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the auto-populated fields are not Editable")
    public void userVerifiesTheAutoPopulatedFieldsAreNotEditable() throws Exception {
        CommonSteps.logInfo("Verified that the auto-populated fields are not editable");
        PageFactory.probateFormsRW02Page().verifyAutoPopulatedFieldsAreNotEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies multiple aka names can be added separated by comma")
    public void userVerifiesMultipleAkaNamesCanBeAddedSeparatedByComma() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that multiple aka names are added separated by comma");
        PageFactory.probateFormsRW02Page().verifyMultipleAkaNamesCanBeAddedSeparatedByComma();
    }
}

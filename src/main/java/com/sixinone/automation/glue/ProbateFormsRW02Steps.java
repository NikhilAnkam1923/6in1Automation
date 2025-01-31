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

    @When("user navigates to Estate contacts tab")
    public void userNavigatesToTheEstateContactsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the estate contacts tab");
        PageFactory.probateFormsRW02Page().navigateToEstateContactsTab();
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
        PageFactory.probateFormsRW02Page().verifyDecedentInformationIsAutoPopulatedOnTheForm();
        CommonSteps.logInfo("Verified that decedent information is auto populated on the form");
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

    @Then("user saves entered Information of all the Estate Contacts")
    public void userSavesEnteredInformationOfAllTheEstateContacts() throws AutomationException {
        CommonSteps.logInfo("user saves entered Information of all the Estate Contacts");
        PageFactory.probateFormsRW02Page().userSavesEstateContactsInfo();
    }

    @Then("user verifies Fiduciary type of contacts are displayed at the top")
    public void userVerifiesFiduciaryTypeOfContactsAreDisplayedAtTheTop() throws AutomationException {
        PageFactory.probateFormsRW02Page().verifyFiduciaryTypeOfContactsAreDisplayed();
        CommonSteps.logInfo("Verified that Fiduciary type of contacts are displayed at the top");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when names exceed the line, the contacts are displayed in the attachment")
    public void userVerifiesWhenNamesExceedTheLineTheContactsAreDisplayedInTheAttachment() throws AutomationException {
        PageFactory.probateFormsRW02Page().verifyNamesExceedTheLineTheContactsAreDisplayedInTheAttachment();
        CommonSteps.logInfo("Verified that when names exceed the line, the contacts are displayed in the attachment");
    }

    @And("user selects options from all the dropdowns of 'Estimate of value of decedents property at death'")
    public void userSelectsOptionsFromAllTheDropdownsOfEstimateOfValueOfDecedentsPropertyAtDeath() throws AutomationException {
        CommonSteps.logInfo("user selects options from all the dropdowns of 'Estimate of value of decedents property at death'");
        PageFactory.probateFormsRW02Page().userSelectsOptionsFromAllTheDropdowns();
    }

    @Then("user verifies All the selected values are retained and auto saved")
    public void userVerifiesAllTheSelectedValuesAreRetainedAndAutoSaved() throws AutomationException {
        CommonSteps.logInfo("Verified that All the selected values are retained and auto saved");
        PageFactory.probateFormsRW02Page().verifySelectedValuesAreRetainedAndAutoSaved();
    }

    @Then("user verifies Amount can be entered in all the fields")
    public void userVerifiesAmountCanBeEnteredInAllTheFieldsAndAutoSaved() throws AutomationException {
        CommonSteps.logInfo("Verified that Amount is entered in all the fields");
        PageFactory.probateFormsRW02Page().verifyAmountCanBeEnteredInAllTheFieldsAndAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Amount entered in all the fields are auto saved")
    public void userVerifiesAmountEnteredInAllTheFieldsAreAutoSaved() throws AutomationException {
        CommonSteps.logInfo("Verified that Amount entered in all the fields are auto saved");
        PageFactory.probateFormsRW02Page().verifyAmountEnteredInAllTheFieldsAreAutoSaved();
        CommonSteps.takeScreenshot();

    }
}

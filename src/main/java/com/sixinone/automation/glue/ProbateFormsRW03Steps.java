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

    @And("user save entered Estate information")
    public void userSavesEstateInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user saves Estate Information");
        PageFactory.probateFormsRW03Page().userSavesEstateInfo();
    }

    @When("user navigates to the probate forms tab")
    public void userNavigatesToTheProbateFormsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the probate forms tab");
        PageFactory.probateFormsRW03Page().navigateToEstateContactsTab();
    }

    @Then("^user click on the \"([^\"]*)\" form$")
    public void userClickOnTheRW(String formToSelect) throws AutomationException {
        CommonSteps.logInfo("user click on the "+formToSelect+" form");
        PageFactory.probateFormsRW03Page().clickOnRWForm(formToSelect);
    }

    @Then("user selects the aka checkbox")
    public void userSelectsTheAkaCheckbox() throws AutomationException {
        CommonSteps.logInfo("user selects the aka checkbox");
        PageFactory.probateFormsRW03Page().selectAKACheckbox();
    }

    @And("user verifies the county, estate and aka names are auto-populated on the form")
    public void userVerifiesTheCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        PageFactory.probateFormsRW03Page().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
        CommonSteps.logInfo("Verified that the county, estate and aka names are auto-populated on the form");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the auto-populated fields are not editable")
    public void userVerifiesTheAutoPopulatedFieldsAreNotEditable() throws Exception {
        CommonSteps.logInfo("Verified that the auto-populated fields are not editable");
        PageFactory.probateFormsRW03Page().verifyAutoPopulatedFieldsAreNotEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies witness's name is not auto populated and the fields are empty")
    public void userVerifiesWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty() throws Exception {
        CommonSteps.logInfo("Verified that witness's name is not auto populated and the fields are empty");
        PageFactory.probateFormsRW03Page().verifyWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies witnesses name, address and signature fields are editable and in yellow background")
    public void userVerifiesWitnessesNameAddressAndSignatureFieldsAreEditableAndInYellowBackground() throws AutomationException {
        CommonSteps.logInfo("Verified that witnesses name, address and signature fields are editable and in yellow background");
        PageFactory.probateFormsRW03Page().verifyFieldsAreEditableAndYellowBackground();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies witness fields accept names and same names are reflected in signature fields")
    public void userVerifiesWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that witness fields accept names and same names are reflected in signature fields");
        PageFactory.probateFormsRW03Page().verifyWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies names updated in signature fields are reflected in the witness fields")
    public void userVerifiesNamesUpdatedInSignatureFieldsAreReflectedInTheWitnessFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that names updated in signature fields are reflected in the witness fields");
        PageFactory.probateFormsRW03Page().verifyNamesUpdatedInSignatureFieldsAreReflectedInTheWitnessFields();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies both the address, city, zip fields accept correct text")
    public void userVerifiesBothTheAddressCityZipFieldsAcceptCorrectText() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that both the address, city, zip fields accept correct text");
        PageFactory.probateFormsRW03Page().verifyTheAddressCityZipFieldsAcceptCorrectText();
        CommonSteps.takeScreenshot();
    }

    @When("user click on print form button")
    public void userClickOnPrintFormButton() throws AutomationException, InterruptedException, AWTException {
        CommonSteps.logInfo("user click on print form button");
        PageFactory.probateFormsRW03Page().clickOnPrintFormButton();

    }

    @Then("verify form can be printed in pdf with name as {string}")
    public void verifyFormCanBePrintedInPdfWithNameAsRW(String formName) throws AutomationException {
        CommonSteps.logInfo("verify form can be printed in pdf");
        PageFactory.probateFormsRW03Page().verifyFormPrintedInPDFForm(formName);
    }


    @And("verify all the fields entered are correctly reflected in the pdf")
    public void verifyAllTheFieldsEnteredAreCorrectlyReflectedInThePdf() {
        CommonSteps.logInfo("verify all the fields entered are correctly reflected in the pdf");
        PageFactory.probateFormsRW03Page().verifyAllFieldsInDownloadedPDF();
    }

    @Then("user verifies all the input fields in the form are auto saved")
    public void userVerifiesAllTheInputFieldsInTheFormAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that all the input fields in the form are auto saved");
        PageFactory.probateFormsRW03Page().verifyAllTheInputFieldsInTheFormAreAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @When("user resets the RW03 form")
    public void userResetsTheRWForm() throws AutomationException {
        CommonSteps.logInfo("user resets the RW03 form");
        PageFactory.probateFormsRW03Page().userResetsTheRWForm();
    }
}

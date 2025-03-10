package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class ProbateFormsRW02Steps {

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

    @Then("user verifies multiple aka names can be added separated by comma")
    public void userVerifiesMultipleAkaNamesCanBeAddedSeparatedByComma() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that multiple aka names are added separated by comma");
        PageFactory.probateFormsRW02Page().verifyMultipleAkaNamesCanBeAddedSeparatedByComma();
    }

    @Then("user verifies Fiduciary type of contacts are displayed at the top")
    public void userVerifiesFiduciaryTypeOfContactsAreDisplayedAtTheTop() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyFiduciaryTypeOfContactsAreDisplayed();
        CommonSteps.logInfo("Verified that Fiduciary type of contacts are displayed at the top");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when names exceed the line, the contacts are displayed in the attachment")
    public void userVerifiesWhenNamesExceedTheLineTheContactsAreDisplayedInTheAttachment() throws AutomationException, IOException, ParseException {
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

    @Then("user verifies total estimated value is the total of first and last fields only")
    public void userVerifiesTotalEstimatedValueIsTheTotalOfFirstAndLastFieldsOnly() throws AutomationException {
        CommonSteps.logInfo("Verified that total estimated value is the total of first and last fields only");
        PageFactory.probateFormsRW02Page().verifyTotalEstimatedValueIsTheTotalOfFirstAndLastFieldsOnly();
        CommonSteps.takeScreenshot();
    }

    @And("user checks 'Use Principal Residence' checkbox")
    public void userChecksUsePrincipalResidenceCheckbox() throws AutomationException {
        CommonSteps.logInfo("user checks 'Use Principal Residence' checkbox");
        PageFactory.probateFormsRW02Page().userChecksUsePrincipalResidenceCheckbox();
    }

    @Then("user verifies The address from the 'principal residence at' field is copied to the 'Real estate in Pennsylvania situated at' field")
    public void userVerifiesTheAddressFromThePrincipalResidenceAtFieldIsCopiedToTheRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        CommonSteps.logInfo("Verified that The address from the 'principal residence at' field is copied to the 'Real estate in Pennsylvania situated at' field");
        PageFactory.probateFormsRW02Page().verifyAddressFromPrincipalResidenceAtFieldIsCopiedToRealEstateInPennsylvaniaSituatedAtField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies unchecking the checkbox does not clear the 'Real estate in Pennsylvania situated at' field")
    public void userVerifiesUncheckingTheCheckboxDoesNotClearTheRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        CommonSteps.logInfo("Verified that unchecking the checkbox does not clear the 'Real estate in Pennsylvania situated at' field");
        PageFactory.probateFormsRW02Page().verifyUncheckingTheCheckboxDoesNotClearTheRealEstateInPennsylvaniaSituatedAtField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the copied address is retained and auto-saved")
    public void userVerifiesTheCopiedAddressIsRetainedAndAutoSaved() throws AutomationException {
        CommonSteps.logInfo("Verified that the copied address is retained and auto-saved");
        PageFactory.probateFormsRW02Page().verifyCopiedAddressIsRetainedAndAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("user modifies the address in the 'Real estate in Pennsylvania situated at' field")
    public void userModifiesTheAddressInTheField() throws AutomationException {
        CommonSteps.logInfo("user modifies the address in the 'Real estate in Pennsylvania situated at' field");
        PageFactory.probateFormsRW02Page().userModifiesTheAddressInTheField();
    }

    @Then("user verifies the modifications are saved successfully")
    public void userVerifiesTheModificationsAreSavedSuccessfully() throws AutomationException {
        CommonSteps.logInfo("Verified that the modifications are saved successfully");
        PageFactory.probateFormsRW02Page().verifyModificationsAreSavedSuccessfully();
    }

    @And("user checks Option A checkbox")
    public void userChecksOptionACheckbox() {
        CommonSteps.logInfo("user checks Option A checkbox");
        PageFactory.probateFormsRW02Page().userChecksOptionACheckbox();
    }

    @Then("user verifies Option A remains selected, and option B is unaffected")
    public void userVerifiesOptionARemainsSelectedAndOptionBIsUnaffected() throws AutomationException {
        CommonSteps.logInfo("Verified that Option A remains selected, and option B is unaffected");
        PageFactory.probateFormsRW02Page().verifyOptionARemainsSelectedAndOptionBIsUnaffected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent died date is auto fetched")
    public void userVerifiesDecedentDiedDateIsAutoFetched() throws AutomationException {
        CommonSteps.logInfo("Verified that decedent died date is auto fetched");
        PageFactory.probateFormsRW02Page().verifyDecedentDiedDateIsAutoFetched();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies codicil date values are auto fetched")
    public void userVerifiesCodicilDateValuesAreAutoFetched() throws AutomationException {
        CommonSteps.logInfo("Verified that codicil date values are auto fetched");
        PageFactory.probateFormsRW02Page().verifyCodicilDateValuesAreAutoFetched();
        CommonSteps.takeScreenshot();
    }

    @Then("user updates the codicil dates")
    public void userUpdatesCodicilDates() throws AutomationException {
        CommonSteps.logInfo("user updates the codicil dates");
        PageFactory.probateFormsRW02Page().userUpdatesCodicilDates();
    }

    @Then("user verifies updated codicil dates in form are reflected in the codicil dates in decedent tab")
    public void userVerifiesUpdatedCodicilDatesInFormAreReflectedInTheCodicilDatesInDecedentTab() throws AutomationException {
        CommonSteps.logInfo("Verified that updated codicil dates in form are reflected in the codicil dates in decedent tab");
        PageFactory.probateFormsRW02Page().verifyCodicilDatesReflectedInTheCodicilDatesInDecedentTab();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies text can be entered in the state relevant circumstances text fields")
    public void userVerifiesTextCanBeEnteredInTheStateRelevantCircumstancesTextFields() throws AutomationException {
        CommonSteps.logInfo("Verified that text can be entered in the state relevant circumstances text fields");
        PageFactory.probateFormsRW02Page().verifyTextCanBeEnteredInTheStateRelevantCircumstancesTextFields();
        CommonSteps.takeScreenshot();
    }

    @And("user checks exceptions checkbox from Option A")
    public void userChecksExceptionsCheckboxFromOptionA() {
        CommonSteps.logInfo("user checks exceptions checkbox from Option A");
        PageFactory.probateFormsRW02Page().userChecksExceptionsCheckboxFromOptionA();
    }

    @Then("user verifies the text field is enabled and text can be entered")
    public void userVerifiesTheTextFieldIsEnabledAndTextCanBeEntered() throws AutomationException {
        CommonSteps.logInfo("Verified that the text field is enabled and text can be entered");
        PageFactory.probateFormsRW02Page().verifyTextFieldIsEnabledAndTextCanBeEntered();
        CommonSteps.takeScreenshot();
    }

    @And("user checks Option B checkbox")
    public void userChecksOptionBCheckbox() {
        CommonSteps.logInfo("user checks Option B checkbox");
        PageFactory.probateFormsRW02Page().userChecksOptionBCheckbox();
    }

    @Then("user verifies Option B remains selected, and option A is unaffected")
    public void userVerifiesOptionBRemainsSelectedAndOptionAIsUnaffected() throws AutomationException {
        CommonSteps.logInfo("Verified that Option B remains selected, and option A is unaffected");
        PageFactory.probateFormsRW02Page().verifyOptionBRemainsSelectedAndOptionAIsUnaffected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the beneficiaries' selection field at the bottom of page 1 is enabled")
    public void userVerifiesTheBeneficiariesSelectionFieldAtTheBottomOfPageIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that the beneficiaries' selection field at the bottom of page 1 is enabled");
        PageFactory.probateFormsRW02Page().verifyBeneficiariesSelectionFieldAtTheBottomOfPageIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies multiple beneficiaries can be added")
    public void userVerifiesMultipleBeneficiariesCanBeAdded() throws AutomationException {
        CommonSteps.logInfo("Verified that multiple beneficiaries can be added");
        PageFactory.probateFormsRW02Page().verifyMultipleBeneficiariesCanBeAdded();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on Accept Button")
    public void userClicksOnAcceptButton() throws AutomationException {
        CommonSteps.logInfo("user clicks on Accept Button");
        PageFactory.probateFormsRW02Page().userClicksOnAcceptButton();
    }

    @Then("user verifies correct beneficiary name, relationship and address is displayed in the table")
    public void userVerifiesCorrectBeneficiaryNameRelationshipAndAddressIsDisplayedInTheTable() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyCorrectBeneficiaryNameRelationshipAndAddressIsDisplayed();
        CommonSteps.logInfo("Verified that correct beneficiary name, relationship and address is displayed in the table");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies if the selected contacts are exceed count of 4 then it should be transferred to attachment")
    public void userVerifiesIfTheSelectedContactsAreExceedCountOfThenItShouldBeTransferredToAttachment() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyContactsAreTransferredToAttachment();
        CommonSteps.logInfo("Verified that when the selected contacts are exceed count of 4 then it gets transferred to attachment");
    }

    @And("user checks 'Display ALL heirs on attachment' checkbox")
    public void userChecksDisplayALLHeirsOnAttachmentCheckbox() {
        CommonSteps.logInfo("user checks 'Display ALL heirs on attachment' checkbox");
        PageFactory.probateFormsRW02Page().userChecksDisplayALLHeirsOnAttachmentCheckbox();
    }

    @Then("user verifies all the contacts are transferred to attachment")
    public void userVerifiesAllTheContactsAreTransferredToAttachment() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyAllTheContactsAreTransferredToAttachment();
        CommonSteps.logInfo("Verified that all the contacts are transferred to attachment");
    }

    @And("user deselects Option B")
    public void userDeselectsOptionB() throws AutomationException {
        CommonSteps.logInfo("user deselects Option B");
        PageFactory.probateFormsRW02Page().userDeselectsOptionB();
    }

    @Then("user verifies the beneficiaries' selection field is disabled")
    public void userVerifiesTheBeneficiariesSelectionFieldIsDisabled() throws AutomationException {
        CommonSteps.logInfo("Verified that the beneficiaries' selection field is disabled");
        PageFactory.probateFormsRW02Page().verifyBeneficiariesSelectionFieldIsDisabled();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies on page 2 petitioner's name are by default printed on the table")
    public void userVerifiesOnPagePetitionerSNameAreByDefaultPrintedOnTheTable() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyPetitionerSNameAreByDefaultPrintedOnTheTable();
        CommonSteps.logInfo("Verified that on page 2 petitioner's name are by default printed on the table");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decree of the register information is displayed correctly")
    public void userVerifiesEstateSNameAkaNameFileNoFiduciaryNamesAndCodicilDatesAreDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyDecreeOfRegisterInformationDisplayedCorrectly();
        CommonSteps.logInfo("Verified that decree of the register information is displayed correctly");
        CommonSteps.takeScreenshot();
    }

    @And("user enters values in letters fields")
    public void userEntersValuesInLettersField() throws AutomationException {
        CommonSteps.logInfo("user enters values in letters fields");
        PageFactory.probateFormsRW02Page().userEntersValuesInLettersField();
    }

    @And("user enters data in Other field")
    public void userEntersDataInOtherField() throws AutomationException {
        CommonSteps.logInfo("user enters data in Other field");
        PageFactory.probateFormsRW02Page().userEntersDataInOtherField();
    }

    @And("user adds amount in front of the respective fields")
    public void userAddsAmountInFrontOfTheRespectiveFields() throws AutomationException {
        CommonSteps.logInfo("user adds amount in front of the respective fields");
        PageFactory.probateFormsRW02Page().userAddsAmountInFrontOfTheRespectiveFields();
    }

    @Then("user verifies total is displayed correctly")
    public void userVerifiesTotalIsDisplayedCorrectly() throws AutomationException {
        CommonSteps.logInfo("Verified that total is displayed correctly");
        PageFactory.probateFormsRW02Page().verifyTotalIsDisplayedCorrectly();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies only 1 contact can be selected from the list")
    public void userVerifiesOnlyContactCanBeSelectedFromTheList() throws AutomationException {
        CommonSteps.logInfo("Verified that only 1 contact can be selected from the list");
        PageFactory.probateFormsRW02Page().verifyOnlyContactCanBeSelectedFromTheList();
    }

    @Then("user verifies selected attorney contact's information is displayed correctly")
    public void userVerifiesSelectedContactSInformationIsDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsRW02Page().verifyAttorneyContactSInformationIsDisplayedCorrectly();
        CommonSteps.logInfo("Verified that selected attorney contact's information is displayed correctly");
        CommonSteps.takeScreenshot();
    }


    @When("user click on print form button")
    public void userClickOnPrintFormButton() throws AutomationException, InterruptedException, AWTException {
        CommonSteps.logInfo("user click on print form button");
        PageFactory.probateFormsRW02Page().clickOnPrintFormButton();
    }

}

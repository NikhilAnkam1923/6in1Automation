package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW08Steps {
    @Then("user verifies will number and dates can be entered and are auto saved")
    public void userVerifiesWillNumberAndDatesCanBeEnteredAndAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that will number and dates can be entered and are auto saved");
        PageFactory.probateFormsRW08Page().verifyWillNumberAndDatesCanBeEnteredAndAreAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @When("user checks the Use 4 digit year checkbox")
    public void userCheckTheCheckbox() throws AutomationException {
        CommonSteps.logInfo("user checks the Use 4 digit year checkbox");
        PageFactory.probateFormsRW08Page().userCheckTheCheckbox();
    }

    @Then("user verifies 4 digit year is displayed in file number field")
    public void userVerifiesIsDisplayedInFileNumber() throws AutomationException {
        CommonSteps.logInfo("user verifies 4 digit year is displayed in file number field");
        PageFactory.probateFormsRW08Page().verifyDisplayedFileNumber();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies multiple beneficiary contacts can be selected and displayed on the form")
    public void userVerifiesMultipleBeneficiaryContactsCanBeSelectedAndDisplayedOnTheForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that multiple beneficiary contacts can be selected and displayed on the form");
        PageFactory.probateFormsRW08Page().verifyMultipleBeneficiaryContactsCanBeSelectedAndDisplayedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies beneficiary contacts selected beyond 6 are displayed on the attachment")
    public void userVerifiesBeneficiaryContactsSelectedBeyondAreDisplayedOnTheAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that beneficiary contacts selected beyond 6 are displayed on the attachment");
        PageFactory.probateFormsRW08Page().verifyBeneficiaryContactsSelectedBeyond6AreDisplayedOnTheAttachment();
    }

    @Then("user verifies Main and Attachment count is displayed correctly")
    public void userVerifiesMainAndAttachmentCountIsDisplayedCorrectly() throws AutomationException {
        CommonSteps.logInfo("Verified that Main and Attachment count is displayed correctly");
        PageFactory.probateFormsRW08Page().verifyMainAndAttachmentCountIsDisplayedCorrectly();
        CommonSteps.takeScreenshot();
    }

    @When("user checks Display ALL beneficiary on attachment checkbox")
    public void userChecksDisplayALLBeneficiaryOnAttachmentCheckbox() {
        CommonSteps.logInfo("user checks Display ALL beneficiary on attachment checkbox");
        PageFactory.probateFormsRW08Page().userChecksDisplayALLBeneficiaryOnAttachmentCheckbox();
    }

    @Then("user verifies all the beneficiaries are transferred to attachment and count is reflected accordingly")
    public void userVerifiesAllTheBeneficiariesAreTransferredToAttachmentAndCountIsReflectedAccordingly() throws AutomationException {
        CommonSteps.logInfo("Verified that all the beneficiaries are transferred to attachment and count is reflected accordingly");
        PageFactory.probateFormsRW08Page().verifyAllTheBeneficiariesAreTransferredToAttachment();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Corporate Fiduciary and Person sections information is common for RW07 and RW08")
    public void userVerifiesCorporateFiduciaryAndPersonSectionsInformationIsCommonForRWAndRW() throws AutomationException {
        CommonSteps.logInfo("Verified that Corporate Fiduciary and Person sections information is common for RW07 and RW08");
        PageFactory.probateFormsRW08Page().verifyCorporateFiduciaryAndPersonSectionsInformationIsCommon();
        CommonSteps.takeScreenshot();
    }
}

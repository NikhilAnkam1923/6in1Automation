package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC03Steps {
    @Then("^user verifies the 'Estate of' field is populated automatically with the correct estate name$")
    public void userVerifiesTheEstateOfFieldIsPopulatedAutomaticallyWithTheCorrectEstateName() throws AutomationException {
        CommonSteps.logInfo("Verified that the 'Estate of' field is populated automatically with the correct estate name");
        PageFactory.probateFormsOC03Page().verifyEstateOfFieldIsPopulatedAutomaticallyWithTheCorrectEstateName();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the 'Account of' field is populated with the Fiduciary’s name$")
    public void userVerifiesTheAccountOfFieldIsPopulatedWithTheFiduciarySName() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that the 'Account of' field is populated with the Fiduciary’s name");
        PageFactory.probateFormsOC03Page().verifyAccountOfFieldIsPopulatedWithTheFiduciarySName();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Counsel details are populated correctly")
    public void userVerifiesCounselDetailsArePopulatedCorrectly() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that Counsel details are populated correctly");
        PageFactory.probateFormsOC03Page().verifyCounselDetailsArePopulatedCorrectly();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the estate name is pre-filled from the estate records")
    public void userVerifiesTheEstateNameIsPreFilledFromTheEstateRecords() throws AutomationException {
        CommonSteps.logInfo("Verified that the estate name is pre-filled from the estate records");
        PageFactory.probateFormsOC03Page().verifyEstateNameIsPreFilledFromTheEstateRecords();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the field is populated with the correct estate name")
    public void userVerifiesTheFieldIsPopulatedWithTheCorrectEstateName() throws AutomationException {
        CommonSteps.logInfo("Verified that the field is populated with the correct estate name");
        PageFactory.probateFormsOC03Page().verifyFieldIsPopulatedWithTheCorrectEstateName();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies fee claims \\(amount, start date, and end date\\) are saved and displayed correctly$")
    public void userVerifiesFeeClaimsAmountStartDateAndEndDateAreSavedAndDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that fee claims (amount, start date, and end date) are saved and displayed correctly");
        PageFactory.probateFormsOC03Page().verifyFeeClaimsAmountStartDateAndEndDateAreSavedAndDisplayedCorrectly();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the field is populated with the correct estate name on page 4")
    public void userVerifiesTheFieldIsPopulatedWithTheCorrectEstateNameOnPage4() throws AutomationException {
        CommonSteps.logInfo("Verified that the field is populated with the correct estate name on page 4");
        PageFactory.probateFormsOC03Page().verifyFieldIsPopulatedWithTheCorrectEstateNameOnPage4();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the field is populated with the correct estate name on page 5")
    public void userVerifiesTheFieldIsPopulatedWithTheCorrectEstateNameOnPage5() throws AutomationException {
        CommonSteps.logInfo("Verified that the field is populated with the correct estate name on page 5");
        PageFactory.probateFormsOC03Page().verifyFieldIsPopulatedWithTheCorrectEstateNameOnPage5();
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on the 'Edit Amounts/Proportions' button for income$")
    public void userClicksOnTheEditAmountsProportionsButtonForIncome() throws AutomationException {
        CommonSteps.logInfo("user clicks on the 'Edit Amounts/Proportions' button for income");
        PageFactory.probateFormsOC03Page().userClicksOnTheEditAmountsProportionsButtonForIncome();
    }

    @Then("user verifies the sidebar opens displaying a list of beneficiaries")
    public void userVerifiesTheSidebarOpensDisplayingAListOfBeneficiaries() throws AutomationException {
        CommonSteps.logInfo("Verified that the sidebar opens displaying a list of beneficiaries");
        PageFactory.probateFormsOC03Page().verifyTheSidebarOpensDisplayingAListOfBeneficiaries();
        CommonSteps.takeScreenshot();
    }

    @When("user enters amounts and proportions for beneficiaries")
    public void userEntersAmountsAndProportionsForBeneficiaries() throws AutomationException {
        CommonSteps.logInfo("user enters amounts and proportions for beneficiaries");
        PageFactory.probateFormsOC03Page().userEntersAmountsAndProportionsForBeneficiaries();
    }

    @Then("user verifies the entered data is saved and displayed correctly on the form")
    public void userVerifiesTheEnteredDataIsSavedAndDisplayedCorrectlyOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that the entered data is saved and displayed correctly on the form");
        PageFactory.probateFormsOC03Page().verifyEnteredDataIsSavedAndDisplayedCorrectlyOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the form displays 'See continuation schedule attached' and extra beneficiaries appear in an attachment$")
    public void userVerifiesTheFormDisplaysSeeContinuationScheduleAttachedAndExtraBeneficiariesAppearInAnAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that the form displays 'See continuation schedule attached' and extra beneficiaries appear in an attachment");
        PageFactory.probateFormsOC03Page().verifyFormDisplaysSeeContinuationScheduleAttachedAndExtraBeneficiariesAppearInAnAttachment();
    }

    @Then("user verifies correct trust name is displayed on the form")
    public void userVerifiesCorrectTrustNameIsDisplayedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that correct trust name is displayed on the form");
        PageFactory.probateFormsOC03Page().verifyCorrectTrustNameIsDisplayedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @And("user adds new petitioner")
    public void userAddsNewPetitioner() throws AutomationException {
        CommonSteps.logInfo("user adds new petitioner");
        PageFactory.probateFormsOC03Page().userAddsNewPetitioner();
    }

    @Then("user verifies newly added petitioner is displayed in the attachment")
    public void userVerifiesNewlyAddedPetitionerIsDisplayedInTheAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that newly added petitioner is displayed in the attachment");
        PageFactory.probateFormsOC03Page().verifyNewlyAddedPetitionerIsDisplayedInTheAttachment();
    }

    @Then("user verifies after removing the existing contact next inline contact is displayed on the form")
    public void userVerifiesAfterRemovingTheExistingContactNextInlineContactIsDisplayedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that after removing the existing contact next inline contact is displayed on the form");
        PageFactory.probateFormsOC03Page().verifyAfterRemovingTheExistingContactNextInlineContactIsDisplayedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the contact removed from the estate contacts is also removed from the attachment as well")
    public void userVerifiesTheContactRemovedFromTheEstateContactsIsAlsoRemovedFromTheAttachmentAsWell() throws AutomationException {
        CommonSteps.logInfo("Verified that the contact removed from the estate contacts is also removed from the attachment as well");
        PageFactory.probateFormsOC03Page().verifyContactRemovedFromTheEstateContactsIsAlsoRemovedFromTheAttachmentAsWell();
    }
}

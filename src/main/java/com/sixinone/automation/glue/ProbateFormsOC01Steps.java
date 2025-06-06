package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsOC01Steps {
    @Then("user verifies estate name and county fields display preloaded data and are non-editable")
    public void userVerifiesEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable();
        CommonSteps.logInfo("Verified that estate name and county fields display preloaded data and are non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the attorney details are synced with page 11 of same form")
    public void userVerifiesTheAttorneyDetailsAreSyncedWithPageOfSameForm() throws AutomationException {
        CommonSteps.logInfo("Verified that the attorney details are synced with page 11 of same form");
        PageFactory.probateFormsOC01Page().verifyAttorneyDetailsAreSyncedWithPageOfSameForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies estate name and date of death fields display preloaded data and are non-editable")
    public void userVerifiesEstateNameAndDateOfDeathFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyEstateNameAndDateOfDeathFieldsDisplayPreloadedDataAndAreNonEditable();
        CommonSteps.logInfo("Verified that estate name and date of death fields display preloaded data and are non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies a sidebar is opens and fiduciary contacts can be selected")
    public void userVerifiesASidebarIsOpensAndFiduciaryContactsCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that a sidebar is opens and fiduciary contacts can be selected");
        PageFactory.probateFormsOC01Page().verifySidebarIsOpensAndFiduciaryContactsCanBeSelected();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies the selected fiduciaries populate in the Petitioner fields on the form")
    public void userVerifiesTheSelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsOC01Page().verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm();
        CommonSteps.logInfo("Verified that the selected fiduciaries populate in the Petitioner fields on the form");
        CommonSteps.takeScreenshot();
    }

    @And("user selects multiple fiduciary contacts")
    public void userSelectsMultipleFiduciaryContacts() throws AutomationException {
        CommonSteps.logInfo("user selects multiple fiduciary contacts");
        PageFactory.probateFormsOC01Page().userSelectsMultipleFiduciaryContacts();
    }

    @And("user swap the selected fiduciary contacts")
    public void userSwapTheSelectedFiduciaryContacts() throws AutomationException {
        CommonSteps.logInfo("user swap the selected fiduciary contacts");
        PageFactory.probateFormsOC01Page().userSwapTheSelectedFiduciaryContacts();
    }

    @When("user modifies the 'Date of Will' and 'Date of Codicil' fields")
    public void userModifiesTheDateOfWillAndDateOfCodicilFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user modifies the 'Date of Will' and 'Date of Codicil' fields");
        PageFactory.probateFormsOC01Page().userModifiesTheDateOfWillAndDateOfCodicilFields();
    }

    @Then("user verifies the updated dates are reflected in the estate record")
    public void userVerifiesTheUpdatedDatesAreReflectedInTheEstateRecord() throws AutomationException {
        CommonSteps.logInfo("Verified that the updated dates are reflected in the estate record");
        PageFactory.probateFormsOC01Page().verifyUpdatedDatesAreReflectedInTheEstateRecord();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent's name is displayed and is non-editable")
    public void userVerifiesDecedentSNameIsDisplayedAndIsNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyDecedentSNameIsDisplayedAndIsNonEditable();
        CommonSteps.logInfo("Verified that decedent's name is displayed and is non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies multiple children and DoB can be added")
    public void userVerifiesMultipleChildrenAndDoBCanBeAdded() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that multiple children and DoB can be added");
        PageFactory.probateFormsOC01Page().verifyMultipleChildrenAndDoBCanBeAdded();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Name of the Trust is auto fetched from page 2 on page 4")
    public void userVerifiesNameOfTheTrustIsAutoFetchedFromPageOnPage4() throws AutomationException {
        CommonSteps.logInfo("Verified that Name of the Trust is auto fetched from page 2 on page 4");
        PageFactory.probateFormsOC01Page().verifyNameOfTheTrustIsAutoFetchedFromPageOnPage4();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Name of the Trust is auto fetched from page 2 on page 5")
    public void userVerifiesNameOfTheTrustIsAutoFetchedFromPageOnPage5() throws AutomationException {
        CommonSteps.logInfo("Verified that Name of the Trust is auto fetched from page 2 on page 5");
        PageFactory.probateFormsOC01Page().verifyNameOfTheTrustIsAutoFetchedFromPageOnPage5();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies attachment is displayed on page 4")
    public void userVerifiesAttachmentIsDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that attachment is displayed on page 4");
        PageFactory.probateFormsOC01Page().verifyAttachmentIsDisplayedOnPage4();
    }

    @And("user verifies same attachment is displayed on page 5")
    public void userVerifiesSameAttachmentIsDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that same attachment is displayed on page 5");
        PageFactory.probateFormsOC01Page().verifySameAttachmentIsDisplayedOnPage5();
    }

    @Then("user verifies decedent's name is displayed and is not editable")
    public void userVerifiesDecedentSNameIsDisplayedAndIsNotEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyDecedentSNameIsDisplayedAndIsNotEditable();
        CommonSteps.logInfo("Verified that decedent's name is displayed and is not editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the preloaded decedent's name is displayed and is read-only")
    public void userVerifiesThePreloadedDecedentSNameIsDisplayedAndIsReadOnly() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyPreloadedDecedentSNameIsDisplayedAndIsReadOnly();
        CommonSteps.logInfo("Verified that the preloaded decedent's name is displayed and is read-only");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the decedent's name is displayed and non-editable")
    public void userVerifiesTheDecedentSNameIsDisplayedAndNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyDecedentSNameIsDisplayedAndNonEditable();
        CommonSteps.logInfo("Verified that the decedent's name is displayed and non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the decedent's name is displayed correctly and is non-editable")
    public void userVerifiesTheDecedentSNameIsDisplayedCorrectlyAndIsNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyDecedentSNameIsDisplayedCorrectlyAndIsNonEditable();
        CommonSteps.logInfo("Verified that the decedent's name is displayed correctly and is non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Estate's name is auto fetched and correctly displayed")
    public void userVerifiesEstateSNameIsAutoFetchedAndCorrectlyDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that Estate's name is auto fetched and correctly displayed");
        PageFactory.probateFormsOC01Page().verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayed();
        CommonSteps.takeScreenshot();
    }

    @When("user select 'Yes' for litigation status")
    public void userSelectYesForLitigationStatus() {
        CommonSteps.logInfo("user select 'Yes' for litigation status");
        PageFactory.probateFormsOC01Page().userSelectYesForLitigationStatus();
    }

    @Then("user verifies text fields are enabled dynamically when litigation status is 'Yes'")
    public void userVerifiesTextFieldsAreEnabledDynamicallyWhenLitigationStatusIsYes() throws AutomationException {
        CommonSteps.logInfo("Verified that text fields are enabled dynamically when litigation status is 'Yes'");
        PageFactory.probateFormsOC01Page().verifyTextFieldsAreEnabledDynamicallyWhenLitigationStatusIsYes();
        CommonSteps.takeScreenshot();
    }

    @When("user select 'Yes' for fiduciary status")
    public void userSelectYesForFiduciaryStatus() {
        CommonSteps.logInfo("user select 'Yes' for fiduciary status");
        PageFactory.probateFormsOC01Page().userSelectYesForFiduciaryStatus();
    }

    @Then("user verifies additional fiduciary fields appear and are editable")
    public void userVerifiesAdditionalFiduciaryFieldsAppearAndAreEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that additional fiduciary fields appear and are editable");
        PageFactory.probateFormsOC01Page().verifyAdditionalFiduciaryFieldsAppearAndAreEditable();
        CommonSteps.takeScreenshot();
    }

    @When("user check both the checkboxes as yes")
    public void userCheckBothTheCheckboxesAsYes() {
        CommonSteps.logInfo("user check both the checkboxes as yes");
        PageFactory.probateFormsOC01Page().userCheckBothTheCheckboxesAsYes();
    }

    @Then("user verifies family exemption claimant‘s name field is enabled")
    public void userVerifiesFamilyExemptionClaimantSNameFieldIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that family exemption claimant‘s name field is enabled");
        PageFactory.probateFormsOC01Page().verifyFamilyExemptionClaimantSNameFieldIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies date, payment and interest can be added in correct format")
    public void userVerifiesDatePaymentAndInterestCanBeAddedInCorrectFormat() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that date, payment and interest can be added in correct format");
        PageFactory.probateFormsOC01Page().verifyDatePaymentAndInterestCanBeAddedInCorrectFormat();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies date, description and amount can be added in the receipts and disbursements table")
    public void userVerifiesDateDescriptionAndAmountCanBeAddedInTheReceiptsDisbursementsTable() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that date, description and amount can be added in the receipts and disbursements table");
        PageFactory.probateFormsOC01Page().verifyDateDescriptionAndAmountCanBeAddedInTheReceiptsDisbursementsTable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Reserve request amount can be added")
    public void userVerifiesReserveRequestAmountCanBeAdded() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that Reserve request amount can be added");
        PageFactory.probateFormsOC01Page().verifyReserveRequestAmountCanBeAdded();
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on Add\\/Edit Claimants$")
    public void userClicksOnAddEditClaimants() throws AutomationException {
        CommonSteps.logInfo("user clicks on Add/Edit Claimants");
        PageFactory.probateFormsOC01Page().userClicksOnAddEditClaimants();
    }

    @And("user clicks on Add New Claimant button")
    public void userClicksOnAddNewClaimantButton() throws AutomationException {
        CommonSteps.logInfo("user clicks on Add New Claimant button");
        PageFactory.probateFormsOC01Page().userClicksOnAddNewClaimantButton();
    }

    @And("user adds multiple claimants")
    public void userAddsMultipleClaimants() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("user adds multiple claimants");
        PageFactory.probateFormsOC01Page().userAddsMultipleClaimants();
    }

    @Then("user verifies the claimant is added to the list and totals are updated dynamically")
    public void userVerifiesTheClaimantIsAddedToTheListAndTotalsAreUpdatedDynamically() throws AutomationException {
        CommonSteps.logInfo("Verified that the claimant is added to the list and totals are updated dynamically");
        PageFactory.probateFormsOC01Page().verifyClaimantIsAddedToTheListAndTotalsAreUpdatedDynamically();;
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies first four claimants remain in the main table and additional claimants are displayed in the attachment")
    public void userVerifiesFirstFourClaimantsRemainInTheMainTableAndAdditionalClaimantsAreDisplayedInTheAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that first four claimants remain in the main table and additional claimants are displayed in the attachment");
        PageFactory.probateFormsOC01Page().verifyFirstFourClaimantsRemainInTheMainTableAndAdditionalClaimantsAreDisplayedInTheAttachment();;
    }

    @Then("user verifies name and Address fields are required if initial field is empty")
    public void userVerifiesNameAndAddressFieldsAreRequiredIfInitialFieldIsEmpty() throws AutomationException {
        CommonSteps.logInfo("Verified that name and Address fields are required if initial field is empty");
        PageFactory.probateFormsOC01Page().verifyNameAndAddressFieldsAreRequiredIfInitialFieldIsEmpty();;
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies if initials exist then Name and Address are not required")
    public void userVerifiesIfInitialsExistThenNameAndAddressAreNotRequired() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that if initials exist then Name and Address are not required");
        PageFactory.probateFormsOC01Page().verifyIfInitialsExistThenNameAndAddressAreNotRequired();;
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies initials are not required if Name and Address is there")
    public void userVerifiesInitialsAreNotRequiredIfNameAndAddressIsThere() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that initials are not required if Name and Address is there");
        PageFactory.probateFormsOC01Page().verifyInitialsAreNotRequiredIfNameAndAddressIsThere();;
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the totals displayed correctly for 'Above' and 'Attachment'")
    public void userVerifiesTheTotalsDisplayedCorrectlyForAboveAndAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that the totals displayed correctly for 'Above' and 'Attachment'");
        PageFactory.probateFormsOC01Page().verifyTheTotalsDisplayedCorrectlyForAboveAndAttachment();;
        CommonSteps.takeScreenshot();
    }

    @And("user enters proportion for beneficiaries")
    public void userEntersProportionForBeneficiaries() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters proportion for beneficiaries");
        PageFactory.probateFormsOC01Page().userEntersProportionForBeneficiaries();
    }

    @And("user verifies entered proportion values are correctly displayed on the form")
    public void userVerifiesEnteredProportionValuesAreCorrectlyDisplayedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that entered proportion values are correctly displayed on the form");
        PageFactory.probateFormsOC01Page().userVerifiesDisplayedProportionsOnForm();;
        CommonSteps.takeScreenshot();
    }
}

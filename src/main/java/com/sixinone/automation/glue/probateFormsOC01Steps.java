package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC01Steps {
    @Then("user verifies estate name and county fields display preloaded data and are non-editable")
    public void userVerifiesEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable();
        CommonSteps.logInfo("Verified that estate name and county fields display preloaded data and are non-editable");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies file number field is editable")
    public void userVerifiesFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that file number field is editable");
        PageFactory.probateFormsOC01Page().verifyFileNumberFieldIsEditable();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on Name of Counsel field")
    public void userClicksOnNameOfCounselField() throws AutomationException {
        CommonSteps.logInfo("user clicks on Name of Counsel field");
        PageFactory.probateFormsOC01Page().userClicksOnNameOfCounselField();
    }

    @Then("user verifies a sidebar appears and attorney can be selected")
    public void userVerifiesASidebarAppearsAndAttorneyCanBeSelected() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that a sidebar appears and attorney can be selected");
        PageFactory.probateFormsOC01Page().verifySidebarAppearsAndAttorneyCanBeSelected();
    }

    @And("user verifies selected attorney’s details are populated in the 'Name of Counsel' field")
    public void userVerifiesSelectedAttorneySDetailsArePopulatedInTheField() throws AutomationException {
        CommonSteps.logInfo("Verified that selected attorney’s details are populated in the 'Name of Counsel' field");
        PageFactory.probateFormsOC01Page().verifySelectedAttorneySDetailsArePopulatedInTheField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies attorney details are auto fetched and correctly displayed")
    public void userVerifiesAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that attorney details are auto fetched and correctly displayed");
        PageFactory.probateFormsOC01Page().verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed();
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

    @When("user click on Petitioner name field")
    public void userClickOnPetitionerNameField() throws AutomationException {
        CommonSteps.logInfo("user click on Petitioner name field");
        PageFactory.probateFormsOC01Page().userClickOnPetitionerNameField();
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

    @Then("user verifies out of the selected petitioners only 2 are visible on the form and rest are on the attachment")
    public void userVerifiesOutOfTheSelectedPetitionersOnlyAreVisibleOnTheFormAndRestAreOnTheAttachment() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsOC01Page().verify2PetitionersAreVisibleOnTheFormAndRestAreOnTheAttachment();
        CommonSteps.logInfo("Verified that out of the selected petitioners only 2 are visible on the form and rest are on the attachment");
    }

    @And("user swap the selected fiduciary contacts")
    public void userSwapTheSelectedFiduciaryContacts() throws AutomationException {
        CommonSteps.logInfo("user swap the selected fiduciary contacts");
        PageFactory.probateFormsOC01Page().userSwapTheSelectedFiduciaryContacts();
    }

    @Then("user verifies swapped petitioner names are reflected on UI accordingly")
    public void userVerifiesSwappedPetitionerNamesAreReflectedOnUIAccordingly() throws AutomationException, IOException, ParseException {
        PageFactory.probateFormsOC01Page().verifySwappedPetitionerNamesAreReflectedOnUIAccordingly();
        CommonSteps.logInfo("Verified that swapped petitioner names are reflected on UI accordingly");
        CommonSteps.takeScreenshot();
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

    @Then("user saves selected beneficiaries details")
    public void userSavesSelectedBeneficiariesDetails() throws AutomationException {
        CommonSteps.logInfo("user saves selected beneficiaries details");
        PageFactory.probateFormsOC01Page().userSavesSelectedBeneficiariesDetails();
    }

    @Then("user verifies rest of the selected beneficiaries are displayed on the attachment")
    public void userVerifiesRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that rest of the selected beneficiaries are displayed on the attachment");
        PageFactory.probateFormsOC01Page().verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
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

    @And("user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox")
    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
        CommonSteps.logInfo("user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox");
        PageFactory.probateFormsOC01Page().userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox();
    }

    @Then("user verifies all the beny users are displayed as a part of attachment")
    public void userVerifiesAllTheBenyUsersAreDisplayedAsAPartOfAttachment() throws AutomationException {
        CommonSteps.logInfo("Verified that all the beny users are displayed as a part of attachment");
        PageFactory.probateFormsOC01Page().verifyAllTheBenyUsersAreDisplayedAsAPartOfAttachment();
    }

    @Then("user verifies the Main's count is turn to zero and only Attachment count is displayed correctly")
    public void userVerifiesTheMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly() throws AutomationException {
        CommonSteps.logInfo("Verified that the Main's count is turn to zero and only Attachment count is displayed correctly");
        PageFactory.probateFormsOC01Page().verifyMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent's name is displayed and is not editable")
    public void userVerifiesDecedentSNameIsDisplayedAndIsNotEditable() throws AutomationException {
        PageFactory.probateFormsOC01Page().verifyDecedentSNameIsDisplayedAndIsNotEditable();
        CommonSteps.logInfo("Verified that decedent's name is displayed and is not editable");
        CommonSteps.takeScreenshot();
    }
}

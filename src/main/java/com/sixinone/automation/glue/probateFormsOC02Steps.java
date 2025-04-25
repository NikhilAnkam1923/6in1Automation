package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC02Steps {
    @Then("user verifies by default page 1 should is opened and correct county name is fetched and displayed at the top of the form")
    public void userVerifiesByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that by default page 1 should is opened and correct county name is fetched and displayed at the top of the form");
        PageFactory.probateFormsOC02Page().verifyByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm();
        CommonSteps.takeScreenshot();
    }

    @When("user checks trust under will checkbox")
    public void userChecksTrustUnderWillCheckbox() {
        CommonSteps.logInfo("user checks trust under will checkbox");
        PageFactory.probateFormsOC02Page().userChecksTrustUnderWillCheckbox();
    }

    @Then("user verifies trust under will field is enabled")
    public void userVerifiesTrustUnderWillFieldIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that trust under will field is enabled");
        PageFactory.probateFormsOC02Page().verifyTrustUnderWillFieldIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @When("user checks trust under deed checkbox")
    public void userChecksTrustUnderDeedCheckbox() {
        CommonSteps.logInfo("user checks trust under deed checkbox");
        PageFactory.probateFormsOC02Page().userChecksTrustUnderDeedCheckbox();
    }

    @Then("user verifies trust under deed field is enabled")
    public void userVerifiesTrustUnderDeedFieldIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that trust under deed field is enabled");
        PageFactory.probateFormsOC02Page().verifyTrustUnderDeedFieldIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies entered name is displayed on the form against the \"([^\"]*)\" field and other field is empty$")
    public void userVerifiesEnteredNameIsDisplayedOnTheFormAgainstTheFieldAndOtherFieldIsEmpty(String fieldName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verifies entered name is displayed on the form against the "+fieldName+" field and other field is empty");
        switch (fieldName) {
            case "Trust under will":
                PageFactory.probateFormsOC02Page().verifyNameIsDisplayedOnTheFormAgainstWillFieldAndOtherFieldIsEmpty();
                break;
            case "Trust under deed":
                PageFactory.probateFormsOC02Page().verifyNameIsDisplayedOnTheFormAgainstDeedFieldAndOtherFieldIsEmpty();
                break;
            default:
                throw new AutomationException("Unsupported field name: " + fieldName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies entered name of decedent is displayed on all the OC02 pages")
    public void userVerifiesEnteredNameOfDecedentIsDisplayedOnAllTheOCPages() throws AutomationException {
        CommonSteps.logInfo("Verified that entered name of decedent is displayed on all the OC02 pages");
        PageFactory.probateFormsOC02Page().verifyEnteredNameOfDecedentIsDisplayedOnAllTheOCPages();
        CommonSteps.takeScreenshot();
    }

    @And("user selects two petitioners")
    public void userSelectsTwoPetitioners() throws AutomationException {
        CommonSteps.logInfo("user selects multiple petitioners");
        PageFactory.probateFormsOC02Page().userSelectsTwoPetitioners();
    }

    @Then("user verifies selected names of petitioner are displayed with address")
    public void userVerifiesSelectedNamesOfPetitionerAreDisplayedWithAddress() throws AutomationException {
        CommonSteps.logInfo("Verified that selected names of petitioner are displayed with address");
        PageFactory.probateFormsOC02Page().verifySelectedNamesOfPetitionerAreDisplayedWithAddress();
        CommonSteps.takeScreenshot();
    }

    @And("user selects multiple petitioners")
    public void userSelectsMultiplePetitioners() throws AutomationException {
        CommonSteps.logInfo("user selects multiple petitioners");
        PageFactory.probateFormsOC02Page().userSelectsMultiplePetitioners();
    }

    @Then("user verifies attachment icon is visible and the petitioner details are correctly visible on the attachment")
    public void userVerifiesAttachmentIconIsVisibleAndThePetitionerDetailsAreCorrectlyVisibleOnTheAttachment() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that attachment icon is visible and the petitioner details are correctly visible on the attachment");
        PageFactory.probateFormsOC02Page().verifyAttachmentIconIsVisibleAndThePetitionerDetailsAreCorrectlyVisibleOnTheAttachment();
        CommonSteps.takeScreenshot();
    }

    @And("user swap the selected petitioner contacts")
    public void userSwapTheSelectedPetitionerContacts() throws AutomationException {
        CommonSteps.logInfo("user swap the selected petitioner contacts");
        PageFactory.probateFormsOC02Page().userSwapTheSelectedPetitionerContacts();
    }

    @And("user select Testamentary option")
    public void userSelectTestamentaryOption() {
        CommonSteps.logInfo("user select Testamentary option");
        PageFactory.probateFormsOC02Page().userSelectTestamentaryOption();
    }

    @When("user select Inter Vivos option")
    public void userSelectInterVivosOption() {
        CommonSteps.logInfo("user select Inter Vivos option");
        PageFactory.probateFormsOC02Page().userSelectInterVivosOption();
    }

    @Then("user verifies Testamentary trust section is enabled and inter vivos trust section is disabled")
    public void userVerifiesTestamentaryTrustSectionIsEnabledAndInterVivosTrustSectionIsDisabled() throws AutomationException {
        CommonSteps.logInfo("Verified that Testamentary trust section is enabled and inter vivos trust section is disabled");
        PageFactory.probateFormsOC02Page().verifyTestamentaryTrustSectionIsEnabledAndInterVivosTrustSectionIsDisabled();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Inter Vivos trust section is enabled and Testamentary trust section is disabled")
    public void userVerifiesInterVivosTrustSectionIsEnabledAndTestamentaryTrustSectionIsDisabled() throws AutomationException {
        CommonSteps.logInfo("Verified that Inter Vivos trust section is enabled and Testamentary trust section is disabled");
        PageFactory.probateFormsOC02Page().verifyInterVivosTrustSectionIsEnabledAndTestamentaryTrustSectionIsDisabled();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Date of death is auto fetched from the decedent info")
    public void userVerifiesDateOfDeathIsAutoFetchedFromTheDecedentInfo() throws AutomationException {
        CommonSteps.logInfo("Verified that Date of death is auto fetched from the decedent info");
        PageFactory.probateFormsOC02Page().verifyDateOfDeathIsAutoFetchedFromTheDecedentInfo();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Codicil dates are auto fetched from decedent tab")
    public void userVerifiesCodicilDatesAreAutoFetchedFromDecedentTab() throws AutomationException {
        CommonSteps.logInfo("Verified that Codicil dates are auto fetched from decedent tab");
        PageFactory.probateFormsOC02Page().verifyCodicilDatesAreAutoFetchedFromDecedentTab();
        CommonSteps.takeScreenshot();
    }

    @When("user modifies the codicil dates")
    public void userModifiesTheCodicilDates() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user modifies the codicil dates");
        PageFactory.probateFormsOC02Page().userModifiesTheCodicilDates();
    }

    @Then("user verifies updated codicil dates are reflected in the decedent tab")
    public void userVerifiesUpdatedCodicilDatesAreReflectedInTheDecedentTab() throws AutomationException {
        CommonSteps.logInfo("Verified that updated codicil dates are reflected in the decedent tab");
        PageFactory.probateFormsOC02Page().verifyUpdatedCodicilDatesAreReflectedInTheDecedentTab();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Judicial county is auto fetched from the decedent info tab")
    public void userVerifiesJudicialCountyIsAutoFetchedFromTheDecedentInfoTab() throws AutomationException {
        CommonSteps.logInfo("Verified that Judicial county is auto fetched from the decedent info tab");
        PageFactory.probateFormsOC02Page().verifyJudicialCountyIsAutoFetchedFromTheDecedentInfoTab();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies all the details can be entered and auto saved")
    public void userVerifiesAllTheDetailsCanBeEnteredAndAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that all the details can be entered and auto saved");
        PageFactory.probateFormsOC02Page().verifyAllTheDetailsCanBeEnteredAndAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Name of the Trust is auto fetched from page 2")
    public void userVerifiesNameOfTheTrustIsAutoFetchedFromPage() throws AutomationException {
        CommonSteps.logInfo("Verified that Name of the Trust is auto fetched from page 2");
        PageFactory.probateFormsOC02Page().verifyNameOfTheTrustIsAutoFetchedFromPage();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies attachment is displayed on page 6")
    public void userVerifiesAttachmentIsDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that attachment is displayed on page 6");
        PageFactory.probateFormsOC02Page().verifyAttachmentIsDisplayedOnPage6();
    }

    @And("user verifies same attachment is displayed on page 7")
    public void userVerifiesSameAttachmentIsDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that same attachment is displayed on page 7");
        PageFactory.probateFormsOC02Page().verifySameAttachmentIsDisplayedOnPage7();
    }

    @Then("user verifies correct trust name is displayed on page 8 and is not editable")
    public void userVerifiesCorrectTrustNameIsDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that correct trust name is displayed on page 8 and is not editable");
        PageFactory.probateFormsOC02Page().verifyCorrectTrustNameIsDisplayedOnPage();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies correct trust name is displayed on page 9 and is not editable")
    public void userVerifiesCorrectTrustNameIsDisplayedOnPage9() throws AutomationException {
        CommonSteps.logInfo("Verified that correct trust name is displayed on page 9 and is not editable");
        PageFactory.probateFormsOC02Page().verifyCorrectTrustNameIsDisplayedOnPage9();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies principal amount and date can be added")
    public void userVerifiesPrincipalAmountAndDateCanBeAdded() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that principal amount and date can be added");
        PageFactory.probateFormsOC02Page().verifyPrincipalAmountAndDateCanBeAdded();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies estate's name is auto fetched and correctly displayed")
    public void userVerifiesEstateSNameIsAutoFetchedAndCorrectlyDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that estate's name is auto fetched and correctly displayed");
        PageFactory.probateFormsOC02Page().verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies both the fields are editable and accept values")
    public void userVerifiesBothTheFieldsAreEditableAndAcceptValues() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that both the fields are editable and accept values");
        PageFactory.probateFormsOC02Page().verifyBothTheFieldsAreEditableAndAcceptValues();
        CommonSteps.takeScreenshot();
    }
}

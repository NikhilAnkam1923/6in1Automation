package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.util.logging.Commons;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.CookieManager;

public class probateFormsOC07Steps {

    @Then("user verifies the estate name, address, date of death, and file number are auto-filled from estate records")
    public void userVerifiesTheEstateNameAddressDateOfDeathAndFileNumberAreAutoFilledFromEstateRecords() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verified the estate name, address, date of death, and file number are auto-filled from estate records");
        PageFactory.probateFormsOC07Page().verifyEstateNameAddressDateOfDeathAndFileNumberAutofilled();
        CommonSteps.takeScreenshot();
    }

    @When("user enter a valid numeric value into the file number field")
    public void userEnterValidNumericValueIntoFileNumberField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enter a valid numeric value into the file number field");
        PageFactory.probateFormsOC07Page().enterValidFileNumber();
        CommonSteps.takeScreenshot();
    }

    @Then("the file number should be update and saved automatically")
    public void theFileNumberShouldBeUpdateAndSavedAutomatically() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("the file number should be update and saved automatically");
        PageFactory.probateFormsOC07Page().verifyFileNumberUpdatedAndSaved();
        CommonSteps.takeScreenshot();
    }

    @When("user click the Use 4 digit year checkbox")
    public void userClickTheUseDigitYearCheckbox() {
        CommonSteps.logInfo("user click the Use 4 digit year checkbox");
        PageFactory.probateFormsOC07Page().userClicksTheUseDigitYearCheckbox();
    }

    @Then("user verifies the file number with four digit year format is displayed in the file number box")
    public void userVerifiesTheFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox() throws AutomationException {
        CommonSteps.logInfo("Verified that file number with four digit year format is displayed in the file number box correctly");
        PageFactory.probateFormsOC07Page().verifyFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox();
        CommonSteps.takeScreenshot();
    }

    @When("the user navigates to the amount field")
    public void theUserNavigatesToTheAmountField() throws AutomationException {
        CommonSteps.logInfo("the user navigates to the amount field successfully");
        PageFactory.probateFormsOC07Page().userNavigatesToTheAmountField();
    }

    @Then("user verify that able to edit the amount field")
    public void userVerifyThatAbleToEditTheAmountField() throws AutomationException {
        CommonSteps.logInfo("user verified that able to edit the amount field successfully");
        PageFactory.probateFormsOC07Page().verifyEditabilityofAmountField();
        CommonSteps.takeScreenshot();
    }

    @And("user verify amount field should accept only numeric characters")
    public void userVerifyAmountFieldShouldAcceptOnlyNumericCharacters() throws AutomationException {
        CommonSteps.logInfo("user verified amount field accepted only numeric characters");
        PageFactory.probateFormsOC07Page().verifyAmountFieldAcceptOnlyNumericValue();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify that user can add will number on the form")
    public void userVerifyThatUserCanAddWillNumberOnTheForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verified that user can add will number on the form");
        PageFactory.probateFormsOC07Page().verifyUserCanAddWillNumber();
        CommonSteps.takeScreenshot();
    }

    @When("user enters claimantNameX in the Claim of field")
    public void userEntersClaimantNameXInTheClaimOfField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user entered the claimantNameX in the Claim of field");
        PageFactory.probateFormsOC07Page().entersClaimantNameXInTheClaimOfField();

    }

    @Then("the Claimant field should display claimantNameX")
    public void theClaimantFieldShouldDisplayClaimantNameX() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verified that the Claimant field display same claimantNameX which entered at claim of field");
        PageFactory.probateFormsOC07Page().verifyClaimantFieldDisplaySameClaimantName();
        CommonSteps.takeScreenshot();

    }

    @When("user clears and enters claimantNameY in the Claimant field")
    public void userClearsAndEntersClaimantNameYInTheClaimantField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user entered the claimantNameY in the Claimant field");
        PageFactory.probateFormsOC07Page().userClearsAndEntersClaimantNameYInTheClaimantField();

    }

    @Then("the Claim of field should display claimantNameY")
    public void theClaimOfFieldShouldDisplayClaimantNameY() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("(user verified that the claim of field display same claimantNameY which entered at Claimant field");
        PageFactory.probateFormsOC07Page().verifyClaimOfFieldDisplaySameClaimantName();
        CommonSteps.takeScreenshot();
    }
}

package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pa;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW07Steps {
    @When("user check the Use 4 digit year checkbox")
    public void userCheckTheUseDigitYearCheckbox() {
        CommonSteps.logInfo("user check the Use 4 digit year checkbox");
        PageFactory.probateFormsRW07Page().userCheckTheUseDigitYearCheckbox();
    }

    @Then("user verifies 4 digit year is displayed in file number")
    public void userVerifiesDigitYearIsDisplayedInFileNumber() throws AutomationException {
        CommonSteps.logInfo("Verified that 4 digit year is displayed in file number");
        PageFactory.probateFormsRW07Page().verify4DigitYearIsDisplayedInFileNumber();
        CommonSteps.takeScreenshot();
    }

    @When("user click on bene address field")
    public void userClickOnBeneAddressField() throws AutomationException {
        CommonSteps.logInfo("user click on bene address field");
        PageFactory.probateFormsRW07Page().userClickOnBeneAddressField();
    }

    @And("user verifies multiple beneficiaries can be selected")
    public void userVerifiesMultipleBeneficiariesCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that multiple beneficiaries are selected");
        PageFactory.probateFormsRW07Page().verifyMultipleBeneficiariesCanBeSelected();
    }

    @And("user verifies form is repeated based on the number of beneficiaries selected")
    public void userVerifiesFormIsRepeatedBasedOnTheNumberOfBeneficiariesSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that form is repeated based on the number of beneficiaries selected");
        PageFactory.probateFormsRW07Page().verifyFormIsRepeatedBasedOnTheNumberOfBeneficiariesSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies name and address of the beneficiary should be correctly displayed on each form")
    public void userVerifiesNameAndAddressOfTheBeneficiaryShouldBeCorrectlyDisplayedOnEachForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that name and address of the beneficiary should be correctly displayed on each form");
        PageFactory.probateFormsRW07Page().verifyNameAndAddressOfTheBeneficiaryCorrectlyDisplayedOnEachForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies decedent died date and county is auto fetched and non-editable")
    public void userVerifiesDecedentDiedDateAndCountyIsAutoFetchedAndNonEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that decedent died date and county is auto fetched and non-editable");
        PageFactory.probateFormsRW07Page().verifyDecedentDiedDateAndCountyIsAutoFetchedAndNonEditable();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on name column")
    public void userClicksOnNameColumn() throws AutomationException {
        CommonSteps.logInfo("user clicks on name column");
        PageFactory.probateFormsRW07Page().userClicksOnNameColumn();
    }

    @Then("user verifies multiple fiduciary contacts can be selected")
    public void userVerifiesMultipleFiduciaryContactsCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that multiple fiduciary contacts are selected");
        PageFactory.probateFormsRW07Page().verifyMultipleFiduciaryContactsCanBeSelected();
    }

    @Then("user verifies selected fiduciary contacts are common for all the forms")
    public void userVerifiesSelectedFiduciaryContactsAreCommonForAllTheForms() throws AutomationException {
        CommonSteps.logInfo("Verified that selected fiduciary contacts are common for all the forms");
        PageFactory.probateFormsRW07Page().verifySelectedFiduciaryContactsAreCommonForAllTheForms();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies date is entered in correct format")
    public void userVerifiesDateIsEnteredInCorrectFormat() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that date is entered in correct format");
        PageFactory.probateFormsRW07Page().verifyDateIsEnteredInCorrectFormat();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies registrars address is auto fetched and the field is editable")
    public void userVerifiesRegistrarsAddressIsAutoFetchedAndTheFieldIsEditable() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that registrars address is auto fetched and the field is editable");
        PageFactory.probateFormsRW07Page().verifyRegistrarsAddressIsAutoFetchedAndTheFieldIsEditable();
        CommonSteps.takeScreenshot();
    }
}

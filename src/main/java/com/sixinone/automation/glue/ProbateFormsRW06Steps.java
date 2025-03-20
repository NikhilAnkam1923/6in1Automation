package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW06Steps {
    @And("user selects multiple Corporate Fiduciary contacts")
    public void userSelectsMultipleCorporateFiduciaryContacts() throws AutomationException {
        CommonSteps.logInfo("user selects multiple Corporate Fiduciary contacts");
        PageFactory.probateFormsRW06Page().userSelectsMultipleCorporateFiduciaryContacts();
    }

    @Then("user verifies selected contacts names are reflected in the corporate fiduciary name and signature field on each form")
    public void userVerifiesSelectedContactsNamesAreReflectedInTheCorporateFiduciaryNameFieldOnEachForm() throws AutomationException {
        CommonSteps.logInfo("Verified that selected contacts names are reflected in the corporate fiduciary name and signature field on each form");
        PageFactory.probateFormsRW06Page().verifyCorporateFiduciaryNameIsReflectedOnEachForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies form is repeated based on the number of fiduciary contacts selected")
    public void userVerifiesFormIsRepeatedBasedOnTheNumberOfContactsSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that form is repeated based on the number of fiduciary contacts selected");
        PageFactory.probateFormsRW06Page().verifyFormIsRepeatedBasedOnTheNumberOfContactsSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies correct corporate fiduciary contact details are displayed on each form")
    public void userVerifiesCorrectContactDetailsAreDisplayedOnEachForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that correct corporate fiduciary contact details are displayed on each form");
        PageFactory.probateFormsRW06Page().verifyCorrectCorporateFiduciaryContactDetailsAreDisplayedOnEachForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies multiple beneficiary contacts can be selected")
    public void userVerifiesMultipleBeneficiaryContactsCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that multiple beneficiary contacts are selected");
        PageFactory.probateFormsRW06Page().verifyMultipleBeneficiaryContactsCanBeSelected();
    }

    @Then("user verifies form is repeated based on the number of beneficiary contacts selected")
    public void userVerifiesFormIsRepeatedBasedOnTheNumberOfBeneficiaryContactsSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that form is repeated based on the number of beneficiary contacts selected");
        PageFactory.probateFormsRW06Page().verifyFormIsRepeatedBasedOnTheNumberOfBeneficiaryContactsSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies correct beneficiary contact details are displayed correctly on each form")
    public void userVerifiesCorrectBeneficiaryContactDetailsAreDisplayedCorrectlyOnEachForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that correct beneficiary contact details are displayed correctly on each form");
        PageFactory.probateFormsRW06Page().verifyCorrectBeneficiaryContactDetailsAreDisplayedOnEachForm();
        CommonSteps.takeScreenshot();
    }

    @When("user verifies entered date and reason details on each form are not same")
    public void userEntersRelationshipDateAndReasonDetailsOnEachForm() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that entered date and reason details on each form are not same");
        PageFactory.probateFormsRW06Page().userEntersDateAndReasonDetailsOnEachForm();
        CommonSteps.takeScreenshot();
    }
}

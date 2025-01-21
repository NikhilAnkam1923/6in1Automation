package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW03Steps {

    @When("user clicks on Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Create button");
        PageFactory.probateFormsRW03Page().clickButtonCreate();
    }

    @Then("user fills first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled the first name, last name, and SSN details.");
        PageFactory.probateFormsRW03Page().enterFirstAndLastNameAndSSN();
    }

    @And("user clicks on Proceed button")
    public void userClickOnProceedButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Proceed button.");
        PageFactory.probateFormsRW03Page().clickOnProceedButton();
    }

    @And("^user click on Create a new estate with the entered name button$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on 'Create a new estate with the entered name' button for new user.");
        PageFactory.probateFormsRW03Page().clickCreateNewEstateButton();
    }

    @Then("^user fills decedent basic information$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled decedent basic information for new user.");
        PageFactory.probateFormsRW03Page().fillDecedentBasicInformation();
    }

    @And("user clicks on Next button")
    public void userClickOnNextButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Next button.");
        PageFactory.probateFormsRW03Page().clickOnNextButton();
    }

    @When("^user fills Last Address\\/Domicile details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Last Address/Domicile details.");
        PageFactory.probateFormsRW03Page().fillLastAddressDomicileDetails();
    }

    @When("user fills Life Details")
    public void userFillsLifeDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user fills Life Details and validate the fields");
        PageFactory.probateFormsRW03Page().fillLifeDetails();
    }

    @When("user fills the Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Place of Death details.");
        PageFactory.probateFormsRW03Page().fillPlaceOfDeathDetails();
    }

    @When("user click on Estate tab")
    public void userClicksOnEstateTab() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Estate tab.");
        PageFactory.probateFormsRW03Page().clickOnEstateTab();
    }

    @And("user fills the Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Estate details.");
        PageFactory.probateFormsRW03Page().fillEstateDetails();
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
}

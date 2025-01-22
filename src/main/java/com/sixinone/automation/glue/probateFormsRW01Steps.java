package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsRW01Steps {

    @When("user click on Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Create button");
        PageFactory.probateFormsRW01Page().clickButtonCreate();
    }

    @Then("user fill first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled the first name, last name, and SSN details.");
        PageFactory.probateFormsRW01Page().enterFirstAndLastNameAndSSN();
    }

    @And("user clicks on Proceed Button")
    public void userClickOnProceedButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Proceed button.");
        PageFactory.probateFormsRW01Page().clickOnProceedButton();
    }

    @And("^user clicks on Create a new estate with the entered name button$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on 'Create a new estate with the entered name' button for new user.");
        PageFactory.probateFormsRW01Page().clickCreateNewEstateButton();
    }

    @Then("^user fill decedent basic information$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled decedent basic information for new user.");
        PageFactory.probateFormsRW01Page().fillDecedentBasicInformation();
    }

    @And("user clicks on Next Button")
    public void userClickOnNextButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Next button.");
        PageFactory.probateFormsRW01Page().clickOnNextButton();
    }

    @When("^user fills Last Address\\/Domicile Details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Last Address/Domicile details.");
        PageFactory.probateFormsRW01Page().fillLastAddressDomicileDetails();
    }

    @When("user fill Life Details")
    public void userFillsLifeDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user fills Life Details and validate the fields");
        PageFactory.probateFormsRW01Page().fillLifeDetails();
    }

    @When("user fill the Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Place of Death details.");
        PageFactory.probateFormsRW01Page().fillPlaceOfDeathDetails();
    }

    @When("user click on Estate Tab")
    public void userClicksOnEstateTab() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Estate tab.");
        PageFactory.probateFormsRW01Page().clickOnEstateTab();
    }

    @And("user fill the Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Estate details.");
        PageFactory.probateFormsRW01Page().fillEstateDetails();
    }

    @When("user verifies correct file number is displayed at the top of the form")
    public void userVerifyCorrectFileNumber() throws AutomationException {
        CommonSteps.logInfo("Verified that correct file number is displayed at the top of the form");
        PageFactory.probateFormsRW01Page().verifyCorrectFileNumber();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies all fields of section 1 displays correct information fetched from the decedent info tab")
    public void userVerifiesAllFieldsOfSectionDisplaysCorrectInformationFetchedFromTheDecedentInfoTab() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that correct file number is displayed at the top of the form");
        PageFactory.probateFormsRW01Page().verifyCorrectInformationFetchedFromTheDecedentInfoTab();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on {string}")
    public void userClicksOn(String section) throws AutomationException {
        CommonSteps.logInfo("user clicks on "+section);
        PageFactory.probateFormsRW01Page().clickOnSection(section);
    }

    @Then("user verifies on clicking section 2 an informative text box is displayed")
    public void userVerifiesOnClickingSection2AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 2 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection2InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies in section 2 only one checkbox should be able to be checked")
    public void userVerifiesInSectionOnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        CommonSteps.logInfo("Verified that in section 2 only one checkbox should be able to be checked");
        PageFactory.probateFormsRW01Page().verifyInSection2OnlyOneCheckboxShouldBeAbleToBeChecked();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies on clicking section 3 an informative text box is displayed")
    public void userVerifiesOnClickingSection3AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 3 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection3InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies in section 3 only one checkbox should be able to be checked")
    public void userVerifiesInSection3OnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        CommonSteps.logInfo("Verified that in section 3 only one checkbox should be able to be checked");
        PageFactory.probateFormsRW01Page().verifyInSection3OnlyOneCheckboxShouldBeAbleToBeChecked();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies on clicking section 4 an informative text box is displayed")
    public void userVerifiesOnClickingSection4AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 4 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection4InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }
}

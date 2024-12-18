package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pa;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class EstateCreationSteps {

    @Then("user fills the first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().enterFirstAndLastNameAndSSN();
    }

    @And("user click on Proceed button")
    public void userClickOnProceedButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnProceedButton();
    }

    @Then("^user see the Create a new estate with the entered name button for new user$")
    public void userSeeCreateNewEstateButton() throws AutomationException {
        PageFactory.estateCreationPage().verifyCreateNewEstateButtonIsDisplayed();
        CommonSteps.logInfo("Verified the 'Create a new estate with the entered name' button for new user is displayed.");
        CommonSteps.takeScreenshot();
    }

    @And("^user click on Create a new estate with the entered name button for new user$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        PageFactory.estateCreationPage().clickCreateNewEstateButton();
    }

    @Then("^user fills decedent basic information for new user$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillDecedentBasicInformation();
    }

    @And("user click on Next button")
    public void userClickOnNextButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnNextButton();
    }

    @Then("^verify Decedent details page is opened$")
    public void verifyOtherDetailsPageIsOpened() throws AutomationException {
        PageFactory.estateCreationPage().verifyDecedentDetailsPageIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @When("^user fill Last Address\\/Domicile details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillLastAddressDomicileDetails();
    }

    @Then("^user verify validations for all the fields of Last Address\\/Domicile$")
    public void userVerifyValidationsForAllFields() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyDomicileAddressFieldValidations();
    }

    @And("user verifies Township and Borough radio buttons toggle correctly")
    public void userVerifiesTownshipAndBoroughRadioButtons() throws AutomationException {
        PageFactory.estateCreationPage().verifyTownshipBoroughRadioButtons();
        CommonSteps.takeScreenshot();
    }


    @When("user fills Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillPlaceOfDeathDetails();
    }

    @Then("user verify validations for all the fields of Place of Death")
    public void userVerifyValidationsForAllTheFieldsOfPlaceOfDeath() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyPlaceOfDeathFieldValidations();
    }

    @When("user fill Life Details")
    public void userFillLifeDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillLifeDetails();
    }

    @And("user clicks on Estate tab")
    public void userClicksOnEstateTab() throws AutomationException {
        PageFactory.estateCreationPage().clickOnEstateTab();
    }

    @And("user fills Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillEstateDetails();
    }

    @And("user clicks on Decedent Info tab")
    public void userClicksOnDecedentInfoTab() throws AutomationException {
        PageFactory.estateCreationPage().clickOnDecedentInfoTab();
    }

    @Then("user verify each field of Decedent Info retained the entered value")
    public void userVerifyEachFieldOfDecedentInfoRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyDecedentInfoRetainedTheEnteredValue();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify each field of Estate retained the entered value")
    public void userVerifyEachFieldOfEstateRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyEstateRetainedTheEnteredValue();
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on Actions menu of Estate \"([^\"]*)\"$")
    public void userClicksOnActionsMenuOfEstate(String estateName) throws AutomationException {
        PageFactory.estateCreationPage().clickOnActionsMenu(estateName);
    }

    @And("^user selects \"([^\"]*)\" option$")
    public void userSelectsOption(String actionsOption) throws AutomationException {
        PageFactory.estateCreationPage().selectActionsOption(actionsOption);
    }

    @And("user selects Reason For Archive")
    public void userSelectsReasonForArchive() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().selectReasonForArchive();
    }

    @And("user enters Archive Description")
    public void userEntersArchiveDescription() throws AutomationException {
        PageFactory.estateCreationPage().enterArchiveDescription();
    }

    @And("user clicks on Archive Button")
    public void userClicksOnArchiveButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnArchiveBtn();
    }

    @And("user verifies Estate archived successful message")
    public void userVerifiesEstateArchivedSuccessfulMessage() throws AutomationException {
        PageFactory.estateCreationPage().verifyEstateArchivedSuccessfully();
    }

    @When("user verifies validations for File Number Fields")
    public void userVerifiesFileNumberFieldValidations() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyFileNumberFieldValidations();
    }

}

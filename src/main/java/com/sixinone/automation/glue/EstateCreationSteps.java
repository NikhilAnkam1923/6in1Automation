package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
        CommonSteps.logInfo("Verifying the 'Create a new estate with the entered name' button for new user.");
        PageFactory.estateCreationPage().verifyCreateNewEstateButtonIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @And("^user click on Create a new estate with the entered name button for new user$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        PageFactory.estateCreationPage().clickCreateNewEstateButton();
    }

    @Then("^user fills decedent basic information for new user$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Filling in decedent's basic information for a new user.");
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

    @Then("user verify validations for all the fields")
    public void userVerifyValidationsForAllFields() throws AutomationException {
        PageFactory.estateCreationPage().verifyFieldValidations();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies Township and Borough radio buttons toggle correctly")
    public void userVerifiesTownshipAndBoroughRadioButtons() throws AutomationException {
        PageFactory.estateCreationPage().verifyTownshipBoroughRadioButtons();
        CommonSteps.takeScreenshot();
    }


}

package com.sixinone.automation.glue;

import com.aspose.pdf.Page;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class EstateCreationSteps {

    @Then("user fills the first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().enterFirstAndLastNameAndSSN();
        CommonSteps.takeScreenshot();

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
        CommonSteps.takeScreenshot();
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

}

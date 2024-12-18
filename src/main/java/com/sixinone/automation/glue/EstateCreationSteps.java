package com.sixinone.automation.glue;

import com.aspose.pdf.Page;
import com.beust.jcommander.JCommander;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.xmlbeans.impl.jam.JComment;
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

    @When("user enters invalid input in the Last Residence field")
    public void userEntersInvalidInputInTheLastResidenceField() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().enterInvalidLastResidence();
        CommonSteps.logInfo("user enters invalid input in the Last Residence field");

    }

    @Then("the system displays the respective validation error messages")
    public void theSystemDisplaysTheRespectiveValidationErrorMessages() throws AutomationException {
        PageFactory.estateCreationPage().verifyLastResidenceFieldValidationErrors();
        CommonSteps.logInfo("The Last Residence field displays the respective validation error messages");
        CommonSteps.takeScreenshot();
    }

    @And("user enters valid input in the Last Residence field")
    public void userEntersValidInputInTheLastResidenceField() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().enterValidLastResidence();
        CommonSteps.logInfo("user enters valid input in the Last Residence field");
    }

    @Then("the system accepts the input without any error")
    public void theSystemAcceptsTheInputWithoutAnyError() throws AutomationException {
        PageFactory.estateCreationPage().lastResidenceNotDisplayErrorForValidInput();
        CommonSteps.logInfo("The system accepts the valid input without any error");
    }

    @And("user clicks on the date fields date picker should open for these fields")
    public void userClicksOnTheDateFieldsDatePickerShouldOpenForTheseFields() throws AutomationException {
        PageFactory.estateCreationPage().clickOnDatesDatePickerOpen();
        CommonSteps.logInfo("user clicks on the date fields to check date picker open for these fields");
    }

    @And("user enters valid Date of Birth and Date of Death")
    public void userEntersValidDateOfBirthAndDateOfDeath() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().entersDOBandDOD();
        CommonSteps.logInfo("user enters valid Date of Birth and Date of Death");
    }

    @Then("the system calculate and displays correct Age at Death")
    public void theSystemCalculateAndDisplaysCorrectAgeAtDeath() throws AutomationException {
        PageFactory.estateCreationPage().calculateAgeAtDeath();
        CommonSteps.logInfo("the system calculate and displays correct Age at Death");
        CommonSteps.takeScreenshot();
    }

    @And("user selects Divorced in the Marital Status dropdown")
    public void userSelectsDivorcedInTheMaritalStatusDropdown() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().selectMaritalStatusOptionDivorced();
        CommonSteps.logInfo("user selects Divorced in the Marital Status dropdown");
    }

    @Then("the Date Divorced Decree field should be displayed")
    public void theDateDivorcedDecreeFieldShouldBeDisplayed() throws AutomationException {
        PageFactory.estateCreationPage().divorcedDecreeFieldDisplayCheck();
        CommonSteps.logInfo("the Date Divorced Decree field should be displayed");
        CommonSteps.takeScreenshot();
    }

    @And("user selects Marital Status from Divorced to any other")
    public void userSelectsMaritalStatusFromDivorcedToAnyOther() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().selectMaritalStatusOptionOthers();
        CommonSteps.logInfo("user selects Marital Status from Divorced to any other");
    }

    @Then("the Date Divorced Decree field should be hidden")
    public void theDateDivorcedDecreeFieldShouldBeHidden() throws AutomationException {
        PageFactory.estateCreationPage().divorcedDecreeFieldNotDisplayCheck();
        CommonSteps.logInfo("the Date Divorced Decree field should be hidden");
        CommonSteps.takeScreenshot();
    }
}

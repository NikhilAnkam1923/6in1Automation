package com.sixinone.automation.glue;

import com.aspose.pdf.Page;
import com.beust.jcommander.JCommander;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pa;
import org.apache.xmlbeans.impl.jam.JComment;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class EstateCreationSteps {

    @Then("user fills the first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().enterFirstAndLastNameAndSSN();
        CommonSteps.logInfo("User filled the first name, last name, and SSN details.");
    }

    @And("user click on Proceed button")
    public void userClickOnProceedButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnProceedButton();
        CommonSteps.logInfo("User clicked on the Proceed button.");
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
        CommonSteps.logInfo("User clicked on 'Create a new estate with the entered name' button for new user.");
    }

    @Then("^user fills decedent basic information for new user$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillDecedentBasicInformation();
        CommonSteps.logInfo("User filled decedent basic information for new user.");
    }

    @And("user click on Next button")
    public void userClickOnNextButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnNextButton();
        CommonSteps.logInfo("User clicked on the Next button.");
    }

    @Then("^verify Decedent details page is opened$")
    public void verifyOtherDetailsPageIsOpened() throws AutomationException {
        PageFactory.estateCreationPage().verifyDecedentDetailsPageIsDisplayed();
        CommonSteps.logInfo("Verified that the Decedent details page is opened.");
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
    @When("^user fill Last Address\\/Domicile details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillLastAddressDomicileDetails();
        CommonSteps.logInfo("User filled Last Address/Domicile details.");
    }

    @Then("^user verify validations for all the fields of Last Address\\/Domicile$")
    public void userVerifyValidationsForAllFields() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyDomicileAddressFieldValidations();
        CommonSteps.logInfo("User verified validations for all the fields of Last Address/Domicile.");
    }

    @And("user verifies Township and Borough radio buttons toggle correctly")
    public void userVerifiesTownshipAndBoroughRadioButtons() throws AutomationException {
        PageFactory.estateCreationPage().verifyTownshipBoroughRadioButtons();
        CommonSteps.logInfo("User verified Township and Borough radio buttons toggle correctly.");
        CommonSteps.takeScreenshot();
    }


    @When("user fills Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillPlaceOfDeathDetails();
        CommonSteps.logInfo("User filled Place of Death details.");
    }

    @Then("user verify validations for all the fields of Place of Death")
    public void userVerifyValidationsForAllTheFieldsOfPlaceOfDeath() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyPlaceOfDeathFieldValidations();
        CommonSteps.logInfo("User verified validations for all the fields of Place of Death.");
    }

    @When("user fill Life Details")
    public void userFillLifeDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillLifeDetails();
        CommonSteps.logInfo("User filled Life Details.");
    }

    @And("user clicks on Estate tab")
    public void userClicksOnEstateTab() throws AutomationException {
        PageFactory.estateCreationPage().clickOnEstateTab();
        CommonSteps.logInfo("User clicked on the Estate tab.");
    }

    @And("user fills Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillEstateDetails();
        CommonSteps.logInfo("User filled Estate details.");
    }

    @And("user clicks on Decedent Info tab")
    public void userClicksOnDecedentInfoTab() throws AutomationException {
        PageFactory.estateCreationPage().clickOnDecedentInfoTab();
        CommonSteps.logInfo("User clicked on the Decedent Info tab.");
    }

    @Then("user verify each field of Decedent Info retained the entered value")
    public void userVerifyEachFieldOfDecedentInfoRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyDecedentInfoRetainedTheEnteredValue();
        CommonSteps.logInfo("User verified that each field of Decedent Info retained the entered value.");
        CommonSteps.takeScreenshot();
    }

    @Then("user verify each field of Estate retained the entered value")
    public void userVerifyEachFieldOfEstateRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyEstateRetainedTheEnteredValue();
        CommonSteps.logInfo("User verified that each field of Estate retained the entered value.");
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on Actions menu of Estate \"([^\"]*)\"$")
    public void userClicksOnActionsMenuOfEstate(String estateName) throws AutomationException {
        PageFactory.estateCreationPage().clickOnActionsMenu(estateName);
        CommonSteps.logInfo("User clicked on the Actions menu of Estate: " + estateName);
    }

    @And("^user selects \"([^\"]*)\" option$")
    public void userSelectsOption(String actionsOption) throws AutomationException {
        PageFactory.estateCreationPage().selectActionsOption(actionsOption);
        CommonSteps.logInfo("User selected the option: " + actionsOption);
    }

    @And("^user selects Reason \"([^\"]*)\" For Archive$")
    public void userSelectsReasonForArchive(String reason) throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().selectReasonForArchive(reason);
        CommonSteps.logInfo("User selected the Reason for Archive: "+reason);
    }

    @And("user enters Archive Description")
    public void userEntersArchiveDescription() throws AutomationException {
        PageFactory.estateCreationPage().enterArchiveDescription();
        CommonSteps.logInfo("User entered the Archive Description.");
    }

    @And("user clicks on Archive Button")
    public void userClicksOnArchiveButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnArchiveBtn();
        CommonSteps.logInfo("User clicked on the Archive button.");
    }

    @And("user verifies Estate archived successful message")
    public void userVerifiesEstateArchivedSuccessfulMessage() throws AutomationException {
        PageFactory.estateCreationPage().verifyEstateArchivedSuccessfully();
        CommonSteps.logInfo("User verified the successful message for Estate archived.");
    }

    @When("user verifies validations for File Number Fields")
    public void userVerifiesFileNumberFieldValidations() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyFileNumberFieldValidations();
        CommonSteps.logInfo("User verified validations for File Number Fields.");
    }

}

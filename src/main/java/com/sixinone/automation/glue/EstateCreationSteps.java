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
        CommonSteps.takeScreenshot();

    }

    @And("user click on Proceed button")
    public void userClickOnProceedButton() throws AutomationException {
        PageFactory.estateCreationPage().clickOnProceedButton();
    }

    @And("^user see the Create a new estate with the entered name button for new user$")
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

    @Then("for different SSN number no validation should be thrown")
    public void forDifferentSSNNumberNoValidationShouldBeThrown() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().validateSSNForSameName();
        CommonSteps.logInfo("For different SSN number with same first and last name no validation should be thrown");
    }
    @And("user clicks on the codicil date fields date picker should open for these fields")
    public void userClicksOnTheCodicilDateFieldsDatePickerShouldOpenForTheseFields() throws AutomationException {
        PageFactory.estateCreationPage().clickOnCodicilDatesDatePickerOpen();
        CommonSteps.logInfo("user clicks on the codicil date fields date picker should open for these fields");
    }


    @And("user enters codicil dates")
    public void userEntersCodicilDates() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().entersCodicilDates();
        CommonSteps.logInfo("Enter codicil dates");
    }

    @Then("user verify values stored in correct date format")
    public void userVerifyValuesStoredInCorrectDateFormat() throws AutomationException {
            PageFactory.estateCreationPage().validateDateFormat();
            CommonSteps.logInfo("user verify values stored in correct date format mm/dd/yyyy");
        }

//    @Then("Fiduciary Address or Attny should be selected by default")
//    public void fiduciaryAddressShouldBeSelectedByDefault() throws AutomationException {
//        PageFactory.estateCreationPage().verifyDefaultFiduciaryAddressSelected();
//        CommonSteps.logInfo("Fiduciary Address or Attny is selected by default.");
//    }
//
//    @When("I select Accountant address option")
//    public void iSelectAccountantAddressOption() throws AutomationException {
//        PageFactory.estateCreationPage().selectAccountantAddress();
//        CommonSteps.logInfo("User selects the Accountant address option.");
//    }
//
//    @Then("only Accountant address should be selected")
//    public void onlyAccountantAddressShouldBeSelected() throws AutomationException {
//        PageFactory.estateCreationPage().verifyOnlyAccountantAddressSelected();
//        CommonSteps.logInfo("Only the Accountant address is selected.");
//    }
//
//    @And("no other address should be selected")
//    public void noOtherAddressShouldBeSelected() throws AutomationException {
//        PageFactory.estateCreationPage().verifyNoOtherAddressSelected();
//        CommonSteps.logInfo("No other address is selected.");
//    }
//
//    @When("I select Preparer Address or Fiduciary Address or Attny address option")
//    public void iSelectPreparerAddressOption() throws AutomationException {
//        PageFactory.estateCreationPage().selectPreparerAddressOption();
//        CommonSteps.logInfo("User selects the Preparer Address or Fiduciary Address or Attny option.");
//    }
//
//    @Then("only Preparer Address or Fiduciary Address or Attny should be selected")
//    public void onlyPreparerAddressShouldBeSelected() throws AutomationException {
//        PageFactory.estateCreationPage().verifyOnlyPreparerAddressSelected();
//        CommonSteps.logInfo("Only the Preparer Address or Fiduciary Address or Attny is selected.");
//    }

    @Then("an address should be selected by default")
    public void verifyAddressSelectedByDefault() throws AutomationException {
        String selectedAddress = PageFactory.estateCreationPage().getSelectedAddress();
        CommonSteps.logInfo("The default selected address is: " + selectedAddress);
    }

    @When("I select {string} address option")
    public void selectAddressOption(String address) throws AutomationException {
        PageFactory.estateCreationPage().selectAddress(address);
        CommonSteps.logInfo("User selected the " + address + " address option.");
    }

    @Then("only {string} address should be selected")
    public void verifyOnlyAddressSelected(String address) throws AutomationException {
        PageFactory.estateCreationPage().verifyOnlyOneAddressSelected(address);
        CommonSteps.logInfo("Verified that only the " + address + " address is selected.");
    }




}

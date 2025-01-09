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

    @And("^user see the Create a new estate with the entered name button for new user$")
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

    @And("user enters valid Date of Birth and Date of Death")
    public void userEntersValidDateOfBirthAndDateOfDeath() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().entersDOBandDOD();
        CommonSteps.logInfo("user enters valid Date of Birth and Date of Death");
    }

    @Then("the system calculate and displays correct Age at Death")
    public void theSystemCalculateAndDisplaysCorrectAgeAtDeath() throws AutomationException {
        PageFactory.estateCreationPage().calculateAgeAtDeath();
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
    }

    @When("user clicks on Estate tab")
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

    @When("^user clicks on Actions menu of Estate$")
    public void userClicksOnActionsMenuOfEstate() throws AutomationException {
        PageFactory.estateCreationPage().clickOnActionsMenu();
        CommonSteps.logInfo("User clicked on the Actions menu of Estate");
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
    }

    @When("user verifies validations for File Number Fields")
    public void userVerifiesFileNumberFieldValidations() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().verifyFileNumberFieldValidations();
        CommonSteps.logInfo("Verified validations for File Number fields.");
    }

    @Then("for different SSN number no validation should be thrown")
    public void forDifferentSSNNumberNoValidationShouldBeThrown() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().validateSSNForSameName();
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
    }

    @Then("an address should be selected by default")
    public void verifyAddressSelectedByDefault() throws AutomationException {
        String selectedAddress = PageFactory.estateCreationPage().getSelectedAddress();
        CommonSteps.logInfo("The default selected address is: " + selectedAddress);
    }

    @When("^I select \"([^\"]*)\" address option$")
    public void selectAddressOption(String address) throws AutomationException {
        PageFactory.estateCreationPage().selectAddress(address);
        CommonSteps.logInfo("User selected the " + address + " address option.");
    }

    @Then("^only \"([^\"]*)\" address should be selected$")
    public void verifyOnlyAddressSelected(String address) throws AutomationException {
        PageFactory.estateCreationPage().verifyOnlyOneAddressSelected(address);
        CommonSteps.logInfo("Verified that only the " + address + " address is selected.");
        CommonSteps.takeScreenshot();
    }

    @When("user fills Life Details and validate the fields")
    public void userFillsLifeDetailsAndValidateTheFields() throws AutomationException, IOException, ParseException {
        PageFactory.estateCreationPage().fillLifeDetailsAndValidatefields();
        CommonSteps.logInfo("user fills Life Details and validate the fields");
    }

    @When("user verifies estate is displayed on listing page")
    public void userVerifiesEstateIsDisplayedOnListingPage() throws AutomationException {
        PageFactory.estateCreationPage().verifyEstateOnListingPage();
        CommonSteps.logInfo("Verified that Estate is displayed on listing page.");
        CommonSteps.takeScreenshot();
    }
}

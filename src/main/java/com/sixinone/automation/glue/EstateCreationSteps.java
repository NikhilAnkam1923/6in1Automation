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
        CommonSteps.logInfo("User filled the first name, last name, and SSN details.");
        PageFactory.estateCreationPage().enterFirstAndLastNameAndSSN();
    }

    @And("user click on Proceed button")
    public void userClickOnProceedButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Proceed button.");
        PageFactory.estateCreationPage().clickOnProceedButton();
    }

    @And("^user see the Create a new estate with the entered name button for new user$")
    public void userSeeCreateNewEstateButton() throws AutomationException {
        CommonSteps.logInfo("Verified the 'Create a new estate with the entered name' button for new user is displayed.");
        PageFactory.estateCreationPage().verifyCreateNewEstateButtonIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @And("^user click on Create a new estate with the entered name button for new user$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on 'Create a new estate with the entered name' button for new user.");
        PageFactory.estateCreationPage().clickCreateNewEstateButton();
    }

    @Then("^user fills decedent basic information for new user$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled decedent basic information for new user.");
        PageFactory.estateCreationPage().fillDecedentBasicInformation();
    }

    @And("user click on Next button")
    public void userClickOnNextButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Next button.");
        PageFactory.estateCreationPage().clickOnNextButton();
    }

    @Then("^verify Decedent details page is opened$")
    public void verifyOtherDetailsPageIsOpened() throws AutomationException {
        CommonSteps.logInfo("Verified that the Decedent details page is opened.");
        PageFactory.estateCreationPage().verifyDecedentDetailsPageIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("the system calculate and displays correct Age at Death")
    public void theSystemCalculateAndDisplaysCorrectAgeAtDeath() throws AutomationException {
        PageFactory.estateCreationPage().calculateAgeAtDeath();
    }

    @And("user selects Divorced in the Marital Status dropdown")
    public void userSelectsDivorcedInTheMaritalStatusDropdown() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user selects Divorced in the Marital Status dropdown");
        PageFactory.estateCreationPage().selectMaritalStatusOptionDivorced();
    }

    @Then("the Date Divorced Decree field should be displayed")
    public void theDateDivorcedDecreeFieldShouldBeDisplayed() throws AutomationException {
        CommonSteps.logInfo("the Date Divorced Decree field should be displayed");
        PageFactory.estateCreationPage().divorcedDecreeFieldDisplayCheck();
        CommonSteps.takeScreenshot();
    }

    @And("user selects Marital Status from Divorced to any other")
    public void userSelectsMaritalStatusFromDivorcedToAnyOther() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user selects Marital Status from Divorced to any other");
        PageFactory.estateCreationPage().selectMaritalStatusOptionOthers();
    }

    @When("^user fill Last Address\\/Domicile details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Last Address/Domicile details.");
        PageFactory.estateCreationPage().fillLastAddressDomicileDetails();
    }

    @When("user fills Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Place of Death details.");
        PageFactory.estateCreationPage().fillPlaceOfDeathDetails();
    }

    @When("user clicks on Estate tab")
    public void userClicksOnEstateTab() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Estate tab.");
        PageFactory.estateCreationPage().clickOnEstateTab();
    }

    @And("user fills Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Estate details.");
        PageFactory.estateCreationPage().fillEstateDetails();
    }

    @And("user clicks on Decedent Info tab")
    public void userClicksOnDecedentInfoTab() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Decedent Info tab.");
        PageFactory.estateCreationPage().clickOnDecedentInfoTab();
    }

    @Then("user verify each field of Decedent Info retained the entered value")
    public void userVerifyEachFieldOfDecedentInfoRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that each field of Decedent Info retained the entered value.");
        PageFactory.estateCreationPage().verifyDecedentInfoRetainedTheEnteredValue();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify each field of Estate retained the entered value")
    public void userVerifyEachFieldOfEstateRetainedTheEnteredValue() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that each field of Estate retained the entered value.");
        PageFactory.estateCreationPage().verifyEstateRetainedTheEnteredValue();
        CommonSteps.takeScreenshot();
    }

    @When("^user clicks on Actions menu of Estate$")
    public void userClicksOnActionsMenuOfEstate() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Actions menu of Estate");
        PageFactory.estateCreationPage().clickOnActionsMenu();
    }

    @And("^user selects \"([^\"]*)\" option$")
    public void userSelectsOption(String actionsOption) throws AutomationException {
        CommonSteps.logInfo("User selected the option: " + actionsOption);
        PageFactory.estateCreationPage().selectActionsOption(actionsOption);
    }

    @And("^user selects Reason \"([^\"]*)\" For Archive$")
    public void userSelectsReasonForArchive(String reason) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Reason for Archive: "+reason);
        PageFactory.estateCreationPage().selectReasonForArchive(reason);
    }

    @And("user enters Archive Description")
    public void userEntersArchiveDescription() throws AutomationException {
        CommonSteps.logInfo("User entered the Archive Description.");
        PageFactory.estateCreationPage().enterArchiveDescription();
    }

    @And("user clicks on Archive Button")
    public void userClicksOnArchiveButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Archive button.");
        PageFactory.estateCreationPage().clickOnArchiveBtn();
    }

    @And("user verifies Estate archived successful message")
    public void userVerifiesEstateArchivedSuccessfulMessage() throws AutomationException {
        PageFactory.estateCreationPage().verifyEstateArchivedSuccessfully();
    }

    @When("user verifies validations for File Number Fields")
    public void userVerifiesFileNumberFieldValidations() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified validations for File Number fields.");
        PageFactory.estateCreationPage().verifyFileNumberFieldValidations();
    }

    @And("user clicks on the codicil date fields date picker should open for these fields")
    public void userClicksOnTheCodicilDateFieldsDatePickerShouldOpenForTheseFields() throws AutomationException {
        CommonSteps.logInfo("user clicks on the codicil date fields date picker should open for these fields");
        PageFactory.estateCreationPage().clickOnCodicilDatesDatePickerOpen();
    }

    @And("user enters codicil dates")
    public void userEntersCodicilDates() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Enter codicil dates");
        PageFactory.estateCreationPage().entersCodicilDates();
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
        CommonSteps.logInfo("User selected the " + address + " address option.");
        PageFactory.estateCreationPage().selectAddress(address);
    }

    @Then("^only \"([^\"]*)\" address should be selected$")
    public void verifyOnlyAddressSelected(String address) throws AutomationException {
        CommonSteps.logInfo("Verified that only the " + address + " address is selected.");
        PageFactory.estateCreationPage().verifyOnlyOneAddressSelected(address);
        CommonSteps.takeScreenshot();
    }

    @When("user fills Life Details and validate the fields")
    public void userFillsLifeDetailsAndValidateTheFields() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user fills Life Details and validate the fields");
        PageFactory.estateCreationPage().fillLifeDetailsAndValidatefields();
    }

    @When("user verifies estate is displayed on listing page")
    public void userVerifiesEstateIsDisplayedOnListingPage() throws AutomationException {
        CommonSteps.logInfo("Verified that Estate is displayed on listing page.");
        PageFactory.estateCreationPage().verifyEstateOnListingPage();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Create button");
        PageFactory.estateCreationPage().clickButtonCreate();
    }
}

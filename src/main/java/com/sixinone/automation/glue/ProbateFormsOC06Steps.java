package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProbateFormsOC06Steps {

    @Then("user verifies by default page 1 should be opened and correct estate name and file number are auto-filled")
    public void userVerifiesByDefaultPage1ShouldBeOpenedAndCorrectEstateNameAndFileNumberAreAutoFilled() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verifies by default page 1 should be opened and correct estate name and file number are auto-filled");
        PageFactory.probateFormsOC06Page().verifyEstateNameAndFileNumberAutofetch();
        CommonSteps.takeScreenshot();
    }

    @When("user enters a valid numeric value into the file number field")
    public void userEntersValidNumericValueIntoFileNumberField() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user enters a valid numeric value into the file number field");
        PageFactory.probateFormsOC06Page().enterValidFileNumber();
        CommonSteps.takeScreenshot();
    }

    @Then("the file number should be updated and saved automatically")
    public void theFileNumberShouldBeUpdatedAndSavedAutomatically() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("the file number should be updated and saved automatically");
        PageFactory.probateFormsOC06Page().verifyFileNumberUpdatedAndSaved();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies correct county name is fetched and displayed at the top of the \"([^\"]*)\" form$")
    public void userVerifiesCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm(String formName) throws AutomationException {
        CommonSteps.logInfo("user verifies correct county name is fetched and displayed at the top of the page");
        PageFactory.probateFormsOC06Page().verifyCountyName(formName);
        CommonSteps.takeScreenshot();
    }


    @When("user clicks the Use 4 digit year checkbox")
    public void userClicksTheUseDigitYearCheckbox() {
        CommonSteps.logInfo("user clicks the Use 4 digit year checkbox");
        PageFactory.probateFormsOC06Page().userClicksTheUseDigitYearCheckbox();
    }

    @Then("user verifies file number with four digit year format is displayed in the file number box")
    public void userVerifiesFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox() throws AutomationException {
        CommonSteps.logInfo("Verified that file number with four digit year format is displayed in the file number box");
        PageFactory.probateFormsOC06Page().verifyFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies \"Settlor & Deceased\" these two checkboxes are there on the page 1 and either of them can be selected")
    public void userVerifiesTheseTwoCheckboxesAreThereOnThePageAndEitherOfThemCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that \"Settlor & Deceased\" these two checkboxes are there on the page 1 and either of them can be selected");
        PageFactory.probateFormsOC06Page().verifyTwoCheckboxesAreThereOnThePageAndEitherOfThemCanBeSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the estate name is preloaded correctly from the estate records")
    public void userVerifiesTheEstateNameIsPreloadedCorrectlyFromTheEstateRecords() throws AutomationException {
        CommonSteps.logInfo("Verified that the estate name is preloaded correctly from the estate records");
        PageFactory.probateFormsOC06Page().verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecords();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies selected checkboxes \"Settlor & Deceased\" from Page 1 are displayed accurately on Page 2")
    public void userVerifiesSelectedCheckboxesFromPageAreDisplayedAccuratelyOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that selected checkboxes \"Settlor & Deceased\" from Page 1 are displayed accurately on Page 2");
        PageFactory.probateFormsOC06Page().verifySelectedCheckboxesFromPage1AreDisplayedAccuratelyOnPage2();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the estate name is preloaded correctly from the estate records on page 3")
    public void userVerifiesTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that the estate name is preloaded correctly from the estate records on page 3");
        PageFactory.probateFormsOC06Page().verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage3();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies selected checkboxes from \"Settlor & Deceased\" are displayed accurately on Page 3")
    public void userVerifiesSelectedCheckboxesFromAreDisplayedAccuratelyOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that selected checkboxes from \"Settlor & Deceased\" are displayed accurately on Page 3");
        PageFactory.probateFormsOC06Page().verifySelectedCheckboxesFromAreDisplayedAccuratelyOnPage3();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the estate name is preloaded correctly from the estate records on page 4")
    public void userVerifiesTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage4() throws AutomationException {
        CommonSteps.logInfo("Verified that the estate name is preloaded correctly from the estate records on page 4");
        PageFactory.probateFormsOC06Page().verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage4();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the selections made on Page 1 are accurately displayed on Page 4")
    public void userVerifiesTheSelectionsMadeOnPageAreAccuratelyDisplayedOnPage() throws AutomationException {
        CommonSteps.logInfo("Verified that the selections made on Page 1 are accurately displayed on Page 4");
        PageFactory.probateFormsOC06Page().verifyTheSelectionsMadeOnPage1AreAccuratelyDisplayedOnPage4();
        CommonSteps.takeScreenshot();
    }

    @When("user selects attorney contact")
    public void userSelectsAttorneyContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user selects attorney contact");
        PageFactory.probateFormsOC06Page().userSelectsAttorneyContact();
    }
}

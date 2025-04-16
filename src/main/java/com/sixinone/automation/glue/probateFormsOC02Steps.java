package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC02Steps {
    @Then("user verifies by default page 1 should is opened and correct county name is fetched and displayed at the top of the form")
    public void userVerifiesByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that by default page 1 should is opened and correct county name is fetched and displayed at the top of the form");
        PageFactory.probateFormsOC02Page().verifyByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm();
        CommonSteps.takeScreenshot();
    }

    @When("user checks trust under will checkbox")
    public void userChecksTrustUnderWillCheckbox() {
        CommonSteps.logInfo("user checks trust under will checkbox");
        PageFactory.probateFormsOC02Page().userChecksTrustUnderWillCheckbox();
    }

    @Then("user verifies trust under will field is enabled")
    public void userVerifiesTrustUnderWillFieldIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that trust under will field is enabled");
        PageFactory.probateFormsOC02Page().verifyTrustUnderWillFieldIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @When("user checks trust under deed checkbox")
    public void userChecksTrustUnderDeedCheckbox() {
        CommonSteps.logInfo("user checks trust under deed checkbox");
        PageFactory.probateFormsOC02Page().userChecksTrustUnderDeedCheckbox();
    }

    @Then("user verifies trust under deed field is enabled")
    public void userVerifiesTrustUnderDeedFieldIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that trust under deed field is enabled");
        PageFactory.probateFormsOC02Page().verifyTrustUnderDeedFieldIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies entered name is displayed on the form against the \"([^\"]*)\" field and other field is empty$")
    public void userVerifiesEnteredNameIsDisplayedOnTheFormAgainstTheFieldAndOtherFieldIsEmpty(String fieldName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user verifies entered name is displayed on the form against the "+fieldName+" field and other field is empty");
        switch (fieldName) {
            case "Trust under will":
                PageFactory.probateFormsOC02Page().verifyNameIsDisplayedOnTheFormAgainstWillFieldAndOtherFieldIsEmpty();
                break;
            case "Trust under deed":
                PageFactory.probateFormsOC02Page().verifyNameIsDisplayedOnTheFormAgainstDeedFieldAndOtherFieldIsEmpty();
                break;
            default:
                throw new AutomationException("Unsupported field name: " + fieldName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies entered name of decedent is displayed on all the OC02 pages")
    public void userVerifiesEnteredNameOfDecedentIsDisplayedOnAllTheOCPages() throws AutomationException {
        CommonSteps.logInfo("Verified that entered name of decedent is displayed on all the OC02 pages");
        PageFactory.probateFormsOC02Page().verifyEnteredNameOfDecedentIsDisplayedOnAllTheOCPages();
        CommonSteps.takeScreenshot();
    }

    @Then("user verified file number field is editable")
    public void userVerifiesFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that file number field is editable");
        PageFactory.probateFormsOC02Page().verifyFileNumberFieldIsEditable();
        CommonSteps.takeScreenshot();
    }

    @When("user click on Name of Counsel field")
    public void userClickOnNameOfCounselField() throws AutomationException {
        CommonSteps.logInfo("user clicks on Name of Counsel field");
        PageFactory.probateFormsOC02Page().userClicksOnNameOfCounselField();
    }

    @Then("user verify a sidebar appears and attorney can be selected")
    public void userVerifyASidebarAppearsAndAttorneyCanBeSelected() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that a sidebar appears and attorney can be selected");
        PageFactory.probateFormsOC02Page().verifySidebarAppearsAndAttorneyCanBeSelected();
    }

    @And("user verify selected attorney’s details are populated in the 'Name of Counsel' field")
    public void userVerifySelectedAttorneySDetailsArePopulatedInTheField() throws AutomationException {
        CommonSteps.logInfo("Verified that selected attorney’s details are populated in the 'Name of Counsel' field");
        PageFactory.probateFormsOC02Page().verifySelectedAttorneySDetailsArePopulatedInTheField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify attorney details are auto fetched and correctly displayed")
    public void userVerifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that attorney details are auto fetched and correctly displayed");
        PageFactory.probateFormsOC02Page().verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed();
        CommonSteps.takeScreenshot();
    }
}

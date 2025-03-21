package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC01Steps {
    @Then("user verifies estate name and county fields display preloaded data and are non-editable")
    public void userVerifiesEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that estate name and county fields display preloaded data and are non-editable");
        PageFactory.probateFormsOC01Page().verifyEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies file number field is editable")
    public void userVerifiesFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that file number field is editable");
        PageFactory.probateFormsOC01Page().verifyFileNumberFieldIsEditable();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on Name of Counsel field")
    public void userClicksOnNameOfCounselField() throws AutomationException {
        CommonSteps.logInfo("user clicks on Name of Counsel field");
        PageFactory.probateFormsOC01Page().userClicksOnNameOfCounselField();
    }

    @Then("user verifies a sidebar appears and attorney can be selected")
    public void userVerifiesASidebarAppearsAndAttorneyCanBeSelected() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that a sidebar appears and attorney can be selected");
        PageFactory.probateFormsOC01Page().verifySidebarAppearsAndAttorneyCanBeSelected();
    }

    @And("user verifies selected attorney’s details are populated in the 'Name of Counsel' field")
    public void userVerifiesSelectedAttorneySDetailsArePopulatedInTheField() throws AutomationException {
        CommonSteps.logInfo("Verified that selected attorney’s details are populated in the 'Name of Counsel' field");
        PageFactory.probateFormsOC01Page().verifySelectedAttorneySDetailsArePopulatedInTheField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies attorney details are auto fetched and correctly displayed")
    public void userVerifiesAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that attorney details are auto fetched and correctly displayed");
        PageFactory.probateFormsOC01Page().verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the attorney details are synced with page 11 of same form")
    public void userVerifiesTheAttorneyDetailsAreSyncedWithPageOfSameForm() throws AutomationException {
        CommonSteps.logInfo("Verified that the attorney details are synced with page 11 of same form");
        PageFactory.probateFormsOC01Page().verifyAttorneyDetailsAreSyncedWithPageOfSameForm();
        CommonSteps.takeScreenshot();
    }
}

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


}

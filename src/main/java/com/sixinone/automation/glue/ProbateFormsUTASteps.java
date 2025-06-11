package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsUTASteps {
    @And("user selects the \"Use Decedent Name\" checkbox")
    public void userSelectsTheCheckbox() {
        CommonSteps.logInfo("user selects the \"Use Decedent Name\" checkbox");
        PageFactory.probateFormsUTAPage().userSelectsTheCheckbox();
    }

    @Then("user verifies The Settlor of the Trust was: field display decedent's name")
    public void userVerifiesTheSettlorOfTheTrustWasFieldDisplayDecedentSName() throws AutomationException {
        CommonSteps.logInfo("Verified that The Settlor of the Trust was: field display decedent's name");
        PageFactory.probateFormsUTAPage().verifyTheSettlorOfTheTrustWasFieldDisplayDecedentSName();
        CommonSteps.takeScreenshot();
    }

    @When("user click on 'Name and Address' field")
    public void userClickOnNameAndAddressField() throws AutomationException {
        CommonSteps.logInfo("user click on 'Name and Address' field");
        PageFactory.probateFormsUTAPage().userClickOnNameAndAddressField();
    }

    @Then("user selects multiple beneficiaries from sidebar")
    public void userSelectsMultipleBeneficiariesFromSidebar() throws AutomationException {
        CommonSteps.logInfo("user selects multiple beneficiaries from sidebar");
        PageFactory.probateFormsUTAPage().userSelectsMultipleBeneficiariesFromSidebar();
    }

    @And("user verifies form is repeated depending on the number of beneficiaries selected")
    public void userVerifiesFormIsRepeatedDependingOnTheNumberOfBeneficiariesSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that form is repeated depending on the number of beneficiaries selected");
        PageFactory.probateFormsUTAPage().verifyFormIsRepeatedDependingOnTheNumberOfBeneficiariesSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the 'Date of Notice' field is editable")
    public void userVerifiesTheDateOfNoticeFieldIsEditable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that the 'Date of Notice' field is editable");
        PageFactory.probateFormsUTAPage().verifyTheDateOfNoticeFieldIsEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Beneficiary name is displayed at the bottom of the form along with a editable date field where user can enter date")
    public void userVerifiesBeneficiaryNameIsDisplayedAtTheBottomOfTheFormAlongWithAEditableDateFieldWhereUserCanEnterDate() throws AutomationException {
        CommonSteps.logInfo("Verified that Beneficiary name is displayed at the bottom of the form along with a editable date field where user can enter date");
        PageFactory.probateFormsUTAPage().verifyBeneficiaryNameIsDisplayedAtTheBottomOfTheFormAlongWithAEditableDateFieldWhereUserCanEnterDate();
        CommonSteps.takeScreenshot();
    }
}

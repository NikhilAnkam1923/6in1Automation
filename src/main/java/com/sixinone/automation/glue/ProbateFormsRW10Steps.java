package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProbateFormsRW10Steps {
    @Then("user verifies title of the form is correctly displayed and county is fetched from decedent info")
    public void userVerifiesTitleOfTheFormIsCorrectlyDisplayedAndCountyIsFetchedFromDecedentInfo() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that title of the form is correctly displayed and county is fetched from decedent info");
        PageFactory.probateFormsRW10Page().verifyTitleOfTheFormIsCorrectlyDisplayedAndCountyIsFetchedFromDecedentInfo();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies 'Name of Decedent', 'Date of Death', 'File Number' is fetched from the decedent info")
    public void userVerifiesNameOfDecedentDateOfDeathFileNumberIsFetchedFromTheDecedentInfo() throws AutomationException {
        CommonSteps.logInfo("Verified that 'Name of Decedent', 'Date of Death', 'File Number' is fetched from the decedent info");
        PageFactory.probateFormsRW10Page().verifyNameOfDecedentDateOfDeathFileNumberIsFetchedFromTheDecedentInfo();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Name, Date of death and File number fields are not editable")
    public void userVerifiesNameDateOfDeathAndFileNumberFieldsAreNotEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that Name, Date of death and File number fields are not editable");
        PageFactory.probateFormsRW10Page().verifyNameDateOfDeathAndFileNumberFieldsAreNotEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies all the questionnaire is properly displayed, with options yes or no")
    public void userVerifiesAllTheQuestionnaireIsProperlyDisplayedWithOptionsYesOrNo() throws AutomationException {
        CommonSteps.logInfo("Verified that all the questionnaire is properly displayed, with options yes or no");
        PageFactory.probateFormsRW10Page().verifyAllTheQuestionnaireIsProperlyDisplayedWithOptionsYesOrNo();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies for all the questionnaire either yes or no option can be selected")
    public void userVerifiesForAllTheQuestionnaireEitherYesOrNoOptionCanBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that for all the questionnaire either yes or no option can be selected");
        PageFactory.probateFormsRW10Page().verifyForAllTheQuestionnaireEitherYesOrNoOptionCanBeSelected();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when 'Yes' option is selected in point 1, then the text box in point 2 is not enabled and not editable")
    public void userVerifiesIfYesOptionIsSelectedInPointThenTheTextBoxInPointIsNotEnabledAndNotEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that when 'Yes' option is selected in point 1, then the text box in point 2 is not enabled and not editable");
        PageFactory.probateFormsRW10Page().verifyWhenYesOptionIsSelectedTextBoxInPoint2IsNotEnabledAndNotEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when 'No' option is selected in point 1, then the text box in point 2 is enabled and editable")
    public void userVerifiesIfNoOptionIsSelectedInPointThenTheTextBoxInPointIsEnabledAndEditable() throws AutomationException {
        CommonSteps.logInfo("Verified that when 'No' option is selected in point 1, then the text box in point 2 is enabled and editable");
        PageFactory.probateFormsRW10Page().verifyWhenNoOptionIsSelectedTextBoxInPoint2IsEnabledAndEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies reason text box field is able to accept the text")
    public void userVerifiesReasonTextBoxFieldIsAbleToAcceptTheText() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that reason text box field is able to accept the text");
        PageFactory.probateFormsRW10Page().verifyReasonTextBoxFieldIsAbleToAcceptTheText();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when Yes is ticked in point 1, then text field in point 3 is enabled and editable")
    public void userVerifiesWhenYesIsTickedInPointThenTextFieldInPointIsEnabledAndEditable() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that when Yes is ticked in point 1, then text field in point 3 is enabled and editable");
        PageFactory.probateFormsRW10Page().verifyYesIsTickedInPoint1ThenTextFieldInPoint3IsEnabledAndEditable();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when No is ticked in point 1, then text field in point 3 is disabled")
    public void userVerifiesWhenNoIsTickedInPointThenTextFieldInPointIsDisabled() throws AutomationException {
        CommonSteps.logInfo("Verified that when No is ticked in point 1, then text field in point 3 is disabled");
        PageFactory.probateFormsRW10Page().verifyOtherInputFieldsAreEditableExceptPointNo2TextField();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when Yes is ticked in point 1, then Text entered in point 2 gets disappear")
    public void userVerifiesWhenYesIsTickedInPointThenTextEnteredInPointGetsDisappear() throws AutomationException {
        CommonSteps.logInfo("Verified that when Yes is ticked in point 1, then Text entered in point 2 gets disappear");
        PageFactory.probateFormsRW10Page().verifyWhenYesIsTickedInPointThenTextEnteredInPointGetsDisappear();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when No is ticked in point 1, then text box in point 2 is editable, enabled and empty")
    public void userVerifiesWhenNoIsTickedThenTextBoxInPointIsEditableEnabledAndEmpty() throws AutomationException {
        CommonSteps.logInfo("Verified that when No is ticked in point 1, then text box in point 2 is editable, enabled and empty");
        PageFactory.probateFormsRW10Page().verifyWhenNoIsTickedThenTextBoxInPoint2IsEditableEnabledAndEmpty();
        CommonSteps.takeScreenshot();
    }

    @When("user enters date in date field")
    public void userEntersDateInDateField() throws AutomationException {
        CommonSteps.logInfo("user enters date in date field");
        PageFactory.probateFormsRW10Page().userEntersDateInDateField();
    }

    @Then("user verifies entered date follows the correct date format")
    public void userVerifiesDateFollowsTheCorrectFormatMmDdYyyy() throws AutomationException {
        CommonSteps.logInfo("Verified that entered date follows the correct date format");
        PageFactory.probateFormsRW10Page().verifyDateFollowsTheCorrectFormat();
        CommonSteps.takeScreenshot();
    }


    @Then("user verifies Corporate Fiduciary and Person sections information is common for RW07 and RW10")
    public void userVerifiesCorporateFiduciaryAndPersonSectionsInformationIsCommonForRW07AndRW10() throws AutomationException {
        CommonSteps.logInfo("Verified that Corporate Fiduciary and Person sections information is common for RW07 and RW10");
        PageFactory.probateFormsRW10Page().verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor7And10();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Corporate Fiduciary and Person sections information is common for RW08 and RW10")
    public void userVerifiesCorporateFiduciaryAndPersonSectionsInformationIsCommonForRW08AndRW10() throws AutomationException {
        CommonSteps.logInfo("Verified that Corporate Fiduciary and Person sections information is common for RW08 and RW10");
        PageFactory.probateFormsRW10Page().verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor8And10();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies when No is ticked in point 1, then point 3 is disabled and text entered in it gets disappear")
    public void userVerifiesWhenNoIsTickedInPointThenPointIsDisabledAndTextEnteredInItGetsDisappear() throws AutomationException {
        CommonSteps.logInfo("Verified that when No is ticked in point 1, then point 3 is disabled and text entered in it gets disappear");
        PageFactory.probateFormsRW10Page().verifyWhenNoIsTickedInPointThenPointIsDisabledAndTextEnteredInItGetsDisappear();
        CommonSteps.takeScreenshot();
    }
}

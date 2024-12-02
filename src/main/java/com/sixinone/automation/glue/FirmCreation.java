package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class FirmCreation {

    @And("^user verify firm page$")
    public void userVerifyFirmPage() throws AutomationException {
        CommonSteps.logInfo("User verifies the breadcrumb is set to 'Firm'.");

        boolean isBreadcrumbValid = PageFactory.firmCreationPage().isFirmBreadcrumbVisible();

        if (!isBreadcrumbValid) {
            throw new AutomationException("Breadcrumb validation failed: 'Firm' breadcrumb is not visible.");
        }

        CommonSteps.logInfo("Breadcrumb validation successful: 'Firm' is correctly displayed.");
        CommonSteps.takeScreenshot();
    }

    @And("^user verify create page$")
    public void userVerifyCreatePage() throws AutomationException {
        CommonSteps.logInfo("User verifies the breadcrumb is set to 'Firm > New'.");

        boolean isBreadcrumbValid = PageFactory.firmCreationPage().isFirmBreadcrumbVisibleForCreate();

        if (!isBreadcrumbValid) {
            throw new AutomationException("Breadcrumb validation failed: 'Firm > New' breadcrumb is not visible.");
        }

        CommonSteps.logInfo("Breadcrumb validation successful: 'Firm > New' is correctly displayed.");
        CommonSteps.takeScreenshot();
    }

    @Then("^user enter \"([^\"]*)\" in \"([^\"]*)\" Field$")
    public void userEnterInField(String fieldData, String fieldName) throws AutomationException {
        CommonSteps.logInfo("User enters '" + fieldData + "' in the field '" + fieldName + "'.");

        boolean isInputSuccessful = PageFactory.firmCreationPage().enterDataInFieldByLabel(fieldData, fieldName);

        if (!isInputSuccessful) {
            throw new AutomationException("Failed to enter data in field with label: '" + fieldName + "'.");
        }

        CommonSteps.logInfo("Successfully entered data in the field: " + fieldName);
        CommonSteps.takeScreenshot();

    }

    @And("^user enter phone \"([^\"]*)\" for Firm$")
    public void userEnterPhoneOfFirm(String phone) throws AutomationException {
        CommonSteps.logInfo("User enters the phone '" + phone + "' for the firm.");

        boolean isInputSuccessful = PageFactory.firmCreationPage().enterFirmPhone(phone);

        if (!isInputSuccessful) {
            throw new AutomationException("Failed to enter phone for the firm.");
        }

        CommonSteps.logInfo("Successfully entered phone for the firm: " + phone);
        CommonSteps.takeScreenshot();
    }


    @And("^user enter phone \"([^\"]*)\" for Primary User$")
    public void userEnterPhoneOfUser(String phone) throws AutomationException {
        CommonSteps.logInfo("User enters the phone '" + phone + "' for the Primary User.");

        boolean isInputSuccessful = PageFactory.firmCreationPage().enterUserPhone(phone);

        if (!isInputSuccessful) {
            throw new AutomationException("Failed to enter phone for the Primary User.");
        }

        CommonSteps.logInfo("Successfully entered phone for the Primary User: " + phone);
        CommonSteps.takeScreenshot();
    }

    @And("^user select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void userSelectAsLicenseType(String labelName, String option) throws AutomationException {
        CommonSteps.logInfo("User selects '" + option + "' from the '" + labelName + "' dropdown.");

        boolean isSelectionSuccessful = PageFactory.firmCreationPage().selectOption(labelName, option);

        if (!isSelectionSuccessful) {
            throw new AutomationException("Failed to select '" + option + "' from the '" + labelName + "' dropdown.");
        }

        CommonSteps.logInfo("Successfully selected '" + option + "' from the '" + labelName + "' dropdown.");
        CommonSteps.takeScreenshot();
    }

    @Then("^Verify the success message \"([^\"]*)\"$")
    public void verifySuccessMessage(String expectedMessage) throws AutomationException {
        CommonSteps.logInfo("Verifying the success message: '" + expectedMessage + "'.");

        boolean isMessageDisplayed = PageFactory.firmCreationPage().verifySuccessMessage(expectedMessage);

        if (!isMessageDisplayed) {
            throw new AutomationException("Expected success message '" + expectedMessage + "' was not displayed.");
        }

        CommonSteps.logInfo("Successfully verified the success message: " + expectedMessage);
        CommonSteps.takeScreenshot();
    }


}

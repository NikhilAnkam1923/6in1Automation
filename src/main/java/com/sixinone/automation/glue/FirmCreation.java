package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.*;


import static com.sixinone.automation.pages.FirmCreationPage.driverUtil;
import static com.sixinone.automation.pages.PageFactory.firmCreationPage;

public class FirmCreation {

    @And("^user verify firm page$")
    public void userVerifyFirmPage() throws AutomationException {
        CommonSteps.logInfo("User verifies the breadcrumb is set to 'Firm'.");

        boolean isBreadcrumbValid = firmCreationPage().isFirmBreadcrumbVisible();

        if (!isBreadcrumbValid) {
            throw new AutomationException("Breadcrumb validation failed: 'Firm' breadcrumb is not visible.");
        }

        CommonSteps.logInfo("Breadcrumb validation successful: 'Firm' is correctly displayed.");
        CommonSteps.takeScreenshot();
    }

    @And("^user verify create page$")
    public void userVerifyCreatePage() throws AutomationException {
        CommonSteps.logInfo("User verifies the breadcrumb is set to 'Firm > New'.");

        boolean isBreadcrumbValid = firmCreationPage().isFirmBreadcrumbVisibleForCreate();

        if (!isBreadcrumbValid) {
            throw new AutomationException("Breadcrumb validation failed: 'Firm > New' breadcrumb is not visible.");
        }

        CommonSteps.logInfo("Breadcrumb validation successful: 'Firm > New' is correctly displayed.");
        CommonSteps.takeScreenshot();
    }

    @When("^user enters \"([^\"]*)\" in the \"([^\"]*)\"$")
    public void theUserEntersInTheInput(String input, String textBox) throws AutomationException {
        CommonSteps.logInfo("verify user able to enters the '"+input+"' as input in the '"+textBox+"' field");
        PageFactory.firmCreationPage().verifyUserEntersTheInput(textBox, input);
    }

    @And("^click on \"([^\"]*)\" button$")
    public void clickOnButton(String saveButton) throws AutomationException {
        CommonSteps.logInfo("user click on '"+saveButton+"' button");
        PageFactory.firmCreationPage().clickOnSaveButton(saveButton);
    }

    @Then("user verify the system displays \"([^\"]*)\" for \"([^\"]*)\"$")
    public void userVerifyTheSystemDisplaysErrorMsg(String expectedMsg,String field) throws AutomationException {
        CommonSteps.logInfo("Verifying the error message: " + expectedMsg);
        PageFactory.firmCreationPage().verifyDisplayedErrorMessage(expectedMsg,field);
        CommonSteps.takeScreenshot();
    }
}



package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;

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

}

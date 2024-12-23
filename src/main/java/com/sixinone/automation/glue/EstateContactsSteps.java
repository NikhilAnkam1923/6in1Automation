package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class EstateContactsSteps {

    @When("user navigates to the estate contacts tab")
    public void userNavigatesToTheEstateContactsTab() throws AutomationException {
        PageFactory.estateContactsPage().navigateToEstateContactsTab();
        CommonSteps.logInfo("user navigates to the estate contacts tab");
    }
}

package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EstateContactsSteps {

    @When("user navigates to the estate contacts tab")
    public void userNavigatesToTheEstateContactsTab() throws AutomationException {
        PageFactory.estateContactsPage().navigateToEstateContactsTab();
        CommonSteps.logInfo("user navigates to the estate contacts tab");
    }

    @Then("user verifies that the left pane contains Name and Roles columns")
    public void userVerifiesLeftPaneColumns() throws AutomationException {
        PageFactory.estateContactsPage().verifyNameAndRolesColumns();
        CommonSteps.logInfo("Verified that the left pane contains Name and Roles columns.");
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Add Contact button")
    public void userClicksOnAddContactButton() throws AutomationException {
        PageFactory.estateContactsPage().clickAddContactButton();
        CommonSteps.logInfo("Clicked on the Add Contact button.");
    }

    @Then("user verifies the list of global contacts with Add button at the start")
    public void userVerifiesListOfGlobalContactsWithAddButton() throws AutomationException {
        PageFactory.estateContactsPage().verifyAddButtonInGlobalContacts();
        CommonSteps.logInfo("Verified the list of global contacts with Add button at the start.");
        CommonSteps.takeScreenshot();
    }

    @And("user verifies that Create New Individual and Create New Entity buttons are displayed")
    public void userVerifiesCreateIndividualAndEntityButtons() throws AutomationException {
        PageFactory.estateContactsPage().verifyCreateContactButtons();
        CommonSteps.logInfo("Verified that Create New Individual Contact and Create New Entity Contact buttons are displayed.");
        CommonSteps.takeScreenshot();
    }

}

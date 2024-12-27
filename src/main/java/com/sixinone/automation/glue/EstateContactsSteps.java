package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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

    @And("^user fills the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForNewGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        PageFactory.estateContactsPage().fillNewGlobalContactDetails(contactType);
        CommonSteps.logInfo("User fills all the details for " + contactType);
    }

    @When("user clicks on the Create New Individual Contact button")
    public void userClicksOnTheCreateNewIndividualContactButton() throws AutomationException {
        PageFactory.estateContactsPage().clickOnNewIndividualContactBtn();
        CommonSteps.logInfo("Clicked on the Create New Individual Contact button.");

    }

    @Then("user verifies the contact is visible in the Estate Contacts list")
    public void userVerifiesTheContactIsVisibleInTheEstateContactsList() throws AutomationException {
        PageFactory.estateContactsPage().verifyContactInEstateContactsList();
        CommonSteps.logInfo("Verified that the contact is visible in the Estate Contacts list.");
        CommonSteps.takeScreenshot();
    }

    @And("user verifies the contact is visible in the Global Contacts list")
    public void userVerifiesTheContactIsVisibleInTheGlobalContactsList() throws AutomationException, IOException {
        PageFactory.estateContactsPage().verifyContactInGlobalContactsList();
        CommonSteps.logInfo("Verified that the contact is visible in the Global Contacts list.");
        CommonSteps.takeScreenshot();
    }

    @And("user verifies that the newly created contact is selected by default")
    public void userVerifiesThatTheNewlyCreatedContactIsSelectedByDefault() throws AutomationException {
        PageFactory.estateContactsPage().verifyNewlyCreatedContactIsSelectedByDefault();
        CommonSteps.logInfo("Verified that the newly created contact is selected by default.");
        CommonSteps.takeScreenshot();
    }

    @And("user selects the Role for Contact")
    public void userSelectsTheRoleForContact() throws AutomationException, IOException, ParseException {
        PageFactory.estateContactsPage().selectRoles();
        CommonSteps.logInfo("User selected the Role for Contact.");
    }

    @Then("user verifies that the role is assigned successfully")
    public void userVerifiesThatTheRoleIsAssignedSuccessfully() throws AutomationException {
        PageFactory.estateContactsPage().verifyRoleAssignedSuccessMessage();
        CommonSteps.logInfo("Verified that the role is assigned successfully.");
        CommonSteps.takeScreenshot();
    }

    @When("user selects the Estate Contact")
    public void userSelectsTheEstateContact() throws AutomationException {
        PageFactory.estateContactsPage().selectEstateContact();
        CommonSteps.logInfo("User selected the Estate Contact.");
    }

    @And("user clicks on Estate-Specific Fields")
    public void userClicksOnEstateSpecificFields() throws AutomationException {
        PageFactory.estateContactsPage().clickOnEstateSpecificFields();
        CommonSteps.logInfo("Clicked on the Estate-Specific Fields.");
    }

    @And("user clicks on Select Role button and uncheck the checked role")
    public void userClicksOnSelectRoleButtonAndUncheckTheCheckedRole() throws AutomationException, IOException, ParseException {
        PageFactory.estateContactsPage().uncheckTheCheckedRole();
        CommonSteps.logInfo("Clicked on Select Role button and unchecked the checked role.");
    }

    @Then("user verifies that notification is displayed on removing the role")
    public void userVerifiesThatNotificationIsDisplayedOnRemovingTheRole() throws AutomationException {
        PageFactory.estateContactsPage().verifyNotificationIsDisplayedOnRemovingTheRole();
        CommonSteps.logInfo("Verified that notification is displayed on removing the role.");
        CommonSteps.takeScreenshot();
    }

    @When("user click on Add button for selected \"([^\"]*)\" contact with a White background")
    public void userClickOnAddButtonForSelectedContactWithAWhiteBackground(String contactType) throws AutomationException {
        PageFactory.estateContactsPage().clickOnAddButtonForSpecificContactType(contactType);
    }

    @Then("user verify landed on Select Role page")
    public void userVerifyLandedOnSelectRolePage() throws AutomationException {
        PageFactory.estateContactsPage().verifySelectRolePage();
        CommonSteps.takeScreenshot();
    }


}

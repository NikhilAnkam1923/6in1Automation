package com.sixinone.automation.glue;

import com.aspose.pdf.Page;
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

    @Then("^user verifies the \"([^\"]*)\" contact is visible in the Estate Contacts list$")
    public void userVerifiesTheContactIsVisibleInTheEstateContactsList(String contactType) throws AutomationException {
        PageFactory.estateContactsPage().verifyContactInEstateContactsList(contactType);
        CommonSteps.logInfo("Verified that the contact is visible in the Estate Contacts list.");
        CommonSteps.takeScreenshot();
    }

    @And("user verifies the contact is visible in the Global Contacts list")
    public void userVerifiesTheContactIsVisibleInTheGlobalContactsList() throws AutomationException, IOException {
        PageFactory.estateContactsPage().verifyContactInGlobalContactsList();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies that the newly created contact is selected by default")
    public void userVerifiesThatTheNewlyCreatedContactIsSelectedByDefault() throws AutomationException {
        PageFactory.estateContactsPage().verifyNewlyCreatedContactIsSelectedByDefault();
        CommonSteps.logInfo("Verified that the newly created contact is selected by default.");
        CommonSteps.takeScreenshot();
    }

    @Then("user click on Select Role button for Contact")
    public void clickOnSelectRoleButton() throws AutomationException {
        PageFactory.estateContactsPage().selectRoleButton();
    }

    @And("user selects the Role for Contact")
    public void userSelectsTheRoleForContact() throws AutomationException, IOException, ParseException {
        PageFactory.estateContactsPage().selectRoles();
        CommonSteps.logInfo("User selected the Role for Contact.");
    }

    @Then("^user verifies that the role is assigned successfully for \"([^\"]*)\" contact$")
    public void userVerifiesThatTheRoleIsAssignedSuccessfully(String contactType) throws AutomationException, IOException, ParseException {
        PageFactory.estateContactsPage().verifyRoleAssignedSuccessfully(contactType);
        CommonSteps.logInfo("Verified that the role is assigned successfully.");
        CommonSteps.takeScreenshot();
    }

    @When("user selects the Estate Contact")
    public void userSelectsTheEstateContact() throws AutomationException, IOException, ParseException {
        PageFactory.estateContactsPage().selectEstateContact();
        CommonSteps.logInfo("User selected the Estate Contact.");
    }

    @Then("user clicks on Estate-Specific Fields")
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

    @When("^user click on Add button for selected \"([^\"]*)\" contact with a \"([^\"]*)\" background$")
    public void userClickOnAddButtonForSelectedContactWithABackground(String contactType, String color) throws AutomationException {
        PageFactory.estateContactsPage().clickOnAddButtonForSpecificContactType(contactType);
    }

    @Then("user verify landed on Select Role page")
    public void userVerifyLandedOnSelectRolePage() throws AutomationException {
        PageFactory.estateContactsPage().verifySelectRolePage();
        CommonSteps.takeScreenshot();
    }


    @And("user saves the Estate Contact without roles")
    public void userSavesTheEstateContactWithoutRoles() throws AutomationException {
        PageFactory.estateContactsPage().saveWithoutRole();
        CommonSteps.logInfo("Saved the Estate Contact without roles.");
    }

    @Then("user verifies that the Remove Contact from Estate button is enabled")
    public void userVerifiesThatTheRemoveContactFromEstateButtonIsEnabled() throws AutomationException {
        PageFactory.estateContactsPage().verifyRemoveContactButtonEnabled();
        CommonSteps.logInfo("Verified that the 'Remove Contact from Estate' button is enabled.");
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies global contact created successful message")
    public void userVerifiesGlobalContactCreatedSuccessfulMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyGlobalContactCreated();
    }

    @And("user clicks on Remove Contact from Estate button")
    public void userClicksOnRemoveContactFromEstateButton() throws AutomationException {
        PageFactory.estateContactsPage().clickOnRemoveContactFromEstateBtn();
        CommonSteps.logInfo("Clicked on Remove Contact from Estate button.");
    }

    @And("user clicks on Remove button")
    public void userClicksOnRemoveButton() throws AutomationException {
        PageFactory.estateContactsPage().clickOnRemoveBtn();
        CommonSteps.logInfo("Clicked on Remove button.");
    }

    @Then("user verifies global contact removed from estate successful message")
    public void userVerifiesGlobalContactRemovedFromEstateSuccessfulMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyContactRemovedSuccessMessage();
    }

    @Then("user verifies removed contact is displayed in the contact list to add back to the estate")
    public void userVerifiesRemovedContactIsDisplayedInTheContactListToAddBackToTheEstate() throws AutomationException {
        PageFactory.estateContactsPage().verifyContactInAddContactsList();
        CommonSteps.logInfo("Verified that the removed contact is displayed in the contact list to add back to the estate.");
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Create New Entity Contact button")
    public void userClicksOnTheCreateNewEntityContactButton() throws AutomationException {
        PageFactory.estateContactsPage().clickOnNewEntityContactBtn();
        CommonSteps.logInfo("Clicked on the Create New Entity Contact button.");

    }

    @Then("^verify user is on create contact page for \"([^\"]*)\" type$")
    public void verifyUserIsOnCreateContactPageForType(String contactPage) throws AutomationException {
        PageFactory.estateContactsPage().verifyContactPage(contactPage);
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on Save Button without selecting any role")
    public void userClicksOnSaveButtonWithoutSelectingAnyRole() throws AutomationException {
        PageFactory.estateContactsPage().clickOnAfterSelectRoleSaveButton();
    }

    @Then("^\"([^\"]*)\" Error should be thrown$")
    public void errorShouldBeThrown(String errorMsg) throws AutomationException {
        PageFactory.estateContactsPage().errorMsgCheck(errorMsg);

    }

    @And("user verifies that the Remove Contact from Estate button is disable")
    public void userVerifiesThatTheRemoveContactFromEstateButtonIsDisable() throws AutomationException {
        PageFactory.estateContactsPage().verifyRemoveContactButtonDisabled();
        CommonSteps.logInfo("Verified that the 'Remove Contact from Estate' button is disabled.");
        CommonSteps.takeScreenshot();
    }

    @Then("if the contact has multiple addresses, address selection page appear,user able to handle address selection task")
    public void ifTheContactHasMultipleAddressesAddressSelectionPageAppearHandleAddressSelectionTask() throws AutomationException {
        PageFactory.estateContactsPage().handleAddressSelection();
    }

    @And("user clicks on Save Button of error pop up")
    public void userClicksOnSaveButtonOfErrorPopUp() throws AutomationException {
        PageFactory.estateContactsPage().clickOnErrorPopUpSaveButton();
        CommonSteps.logInfo("user clicks on Save Button of error pop up");
    }

    @Then("user verifies contact can be saved without selecting any role message")
    public void userVerifiesContactCanBeSavedWithoutSelectingAnyRoleMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyWithoutSelectingRoleContactSavedMsg();
    }
}
    



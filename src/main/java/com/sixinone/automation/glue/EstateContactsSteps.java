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
        CommonSteps.logInfo("user navigates to the estate contacts tab");
        PageFactory.estateContactsPage().navigateToEstateContactsTab();
    }

    @Then("user verifies that the left pane contains Name and Roles columns")
    public void userVerifiesLeftPaneColumns() throws AutomationException {
        CommonSteps.logInfo("Verified that the left pane contains Name and Roles columns.");
        PageFactory.estateContactsPage().verifyNameAndRolesColumns();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Add Contact button")
    public void userClicksOnAddContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Add Contact button.");
        PageFactory.estateContactsPage().clickAddContactButton();
    }

    @Then("user verifies the list of global contacts with Add button at the start")
    public void userVerifiesListOfGlobalContactsWithAddButton() throws AutomationException {
        CommonSteps.logInfo("Verified the list of global contacts with Add button at the start.");
        PageFactory.estateContactsPage().verifyAddButtonInGlobalContacts();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies that Create New Individual and Create New Entity buttons are displayed")
    public void userVerifiesCreateIndividualAndEntityButtons() throws AutomationException {
        CommonSteps.logInfo("Verified that Create New Individual Contact and Create New Entity Contact buttons are displayed.");
        PageFactory.estateContactsPage().verifyCreateContactButtons();
        CommonSteps.takeScreenshot();
    }

    @And("^user fills the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForNewGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        CommonSteps.logInfo("User fills all the details for " + contactType);
        PageFactory.estateContactsPage().fillNewGlobalContactDetails(contactType);
    }

    @When("user clicks on the Create New Individual Contact button")
    public void userClicksOnTheCreateNewIndividualContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Create New Individual Contact button.");
        PageFactory.estateContactsPage().clickOnNewIndividualContactBtn();

    }

    @Then("^user verifies the \"([^\"]*)\" contact is visible in the Estate Contacts list$")
    public void userVerifiesTheContactIsVisibleInTheEstateContactsList(String contactType) throws AutomationException {
        CommonSteps.logInfo("Verified that the contact is visible in the Estate Contacts list.");
        PageFactory.estateContactsPage().verifyContactInEstateContactsList(contactType);
        CommonSteps.takeScreenshot();
    }

    @And("user verifies the contact is visible in the Global Contacts list")
    public void userVerifiesTheContactIsVisibleInTheGlobalContactsList() throws AutomationException, IOException {
        PageFactory.estateContactsPage().verifyContactInGlobalContactsList();
        CommonSteps.takeScreenshot();
    }

    @And("user verifies that the newly created contact is selected by default")
    public void userVerifiesThatTheNewlyCreatedContactIsSelectedByDefault() throws AutomationException {
        CommonSteps.logInfo("Verified that the newly created contact is selected by default.");
        PageFactory.estateContactsPage().verifyNewlyCreatedContactIsSelectedByDefault();
        CommonSteps.takeScreenshot();
    }

    @Then("user click on Select Role button for Contact")
    public void clickOnSelectRoleButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on Select Role button for Contact");
        PageFactory.estateContactsPage().selectRoleButton();
    }

    @And("user selects the Role for Contact")
    public void userSelectsTheRoleForContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.estateContactsPage().selectRoles();
    }

    @Then("^user verifies that the role is assigned successfully for \"([^\"]*)\" contact$")
    public void userVerifiesThatTheRoleIsAssignedSuccessfully(String contactType) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that the role is assigned successfully.");
        PageFactory.estateContactsPage().verifyRoleAssignedSuccessfully(contactType);
        CommonSteps.takeScreenshot();
    }

    @When("user selects the Estate Contact")
    public void userSelectsTheEstateContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Estate Contact.");
        PageFactory.estateContactsPage().selectEstateContact();
    }

    @Then("user clicks on Estate-Specific Fields")
    public void userClicksOnEstateSpecificFields() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Estate-Specific Fields.");
        PageFactory.estateContactsPage().clickOnEstateSpecificFields();
    }

    @And("user clicks on Select Role button and uncheck the checked role")
    public void userClicksOnSelectRoleButtonAndUncheckTheCheckedRole() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Clicked on Select Role button and unchecked the checked role.");
        PageFactory.estateContactsPage().uncheckTheCheckedRole();
    }

    @Then("user verifies that notification is displayed on removing the role")
    public void userVerifiesThatNotificationIsDisplayedOnRemovingTheRole() throws AutomationException {
        CommonSteps.logInfo("Verified that notification is displayed on removing the role.");
        PageFactory.estateContactsPage().verifyNotificationIsDisplayedOnRemovingTheRole();
        CommonSteps.takeScreenshot();
    }

    @When("^user click on Add button for selected \"([^\"]*)\" contact with a \"([^\"]*)\" background$")
    public void userClickOnAddButtonForSelectedContactWithABackground(String contactType, String color) throws AutomationException {
        CommonSteps.logInfo("User clicks on Add button for selected "+contactType+" contact with a "+color+" background");
        PageFactory.estateContactsPage().clickOnAddButtonForSpecificContactType(contactType);
    }

    @Then("user verify landed on Select Role page")
    public void userVerifyLandedOnSelectRolePage() throws AutomationException {
        PageFactory.estateContactsPage().verifySelectRolePage();
        CommonSteps.takeScreenshot();
    }


    @And("user saves the Estate Contact without roles")
    public void userSavesTheEstateContactWithoutRoles() throws AutomationException {
        CommonSteps.logInfo("Saved the Estate Contact without roles.");
        PageFactory.estateContactsPage().saveWithoutRole();
    }

    @Then("user verifies that the Remove Contact from Estate button is enabled")
    public void userVerifiesThatTheRemoveContactFromEstateButtonIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that the 'Remove Contact from Estate' button is enabled.");
        PageFactory.estateContactsPage().verifyRemoveContactButtonEnabled();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies global contact created successful message")
    public void userVerifiesGlobalContactCreatedSuccessfulMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyGlobalContactCreated();
    }

    @And("user clicks on Remove Contact from Estate button")
    public void userClicksOnRemoveContactFromEstateButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on Remove Contact from Estate button.");
        PageFactory.estateContactsPage().clickOnRemoveContactFromEstateBtn();
    }

    @And("user clicks on Remove button")
    public void userClicksOnRemoveButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on Remove button.");
        PageFactory.estateContactsPage().clickOnRemoveBtn();
    }

    @Then("user verifies global contact removed from estate successful message")
    public void userVerifiesGlobalContactRemovedFromEstateSuccessfulMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyContactRemovedSuccessMessage();
    }

    @Then("user verifies removed contact is displayed in the contact list to add back to the estate")
    public void userVerifiesRemovedContactIsDisplayedInTheContactListToAddBackToTheEstate() throws AutomationException {
        CommonSteps.logInfo("Verified that the removed contact is displayed in the contact list to add back to the estate.");
        PageFactory.estateContactsPage().verifyContactInAddContactsList();
        CommonSteps.takeScreenshot();
    }

    @When("user clicks on the Create New Entity Contact button")
    public void userClicksOnTheCreateNewEntityContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Create New Entity Contact button.");
        PageFactory.estateContactsPage().clickOnNewEntityContactBtn();

    }

    @Then("^verify user is on create contact page for \"([^\"]*)\" type$")
    public void verifyUserIsOnCreateContactPageForType(String contactPage) throws AutomationException {
        PageFactory.estateContactsPage().verifyContactPage(contactPage);
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on Save Button without selecting any role")
    public void userClicksOnSaveButtonWithoutSelectingAnyRole() throws AutomationException {
        CommonSteps.logInfo("User clicks on Save Button without selecting any role");
        PageFactory.estateContactsPage().clickOnAfterSelectRoleSaveButton();
    }

    @Then("^\"([^\"]*)\" Error should be thrown$")
    public void errorShouldBeThrown(String errorMsg) throws AutomationException {
        PageFactory.estateContactsPage().errorMsgCheck(errorMsg);
        CommonSteps.takeScreenshot();

    }

    @And("user verifies that the Remove Contact from Estate button is disable")
    public void userVerifiesThatTheRemoveContactFromEstateButtonIsDisable() throws AutomationException {
        CommonSteps.logInfo("Verified that the 'Remove Contact from Estate' button is disabled.");
        PageFactory.estateContactsPage().verifyRemoveContactButtonDisabled();
        CommonSteps.takeScreenshot();
    }

    @Then("if the contact has multiple addresses, address selection page appear,user able to handle address selection task")
    public void ifTheContactHasMultipleAddressesAddressSelectionPageAppearHandleAddressSelectionTask() throws AutomationException {
        PageFactory.estateContactsPage().handleAddressSelection();
    }

    @And("user clicks on Save Button of error pop up")
    public void userClicksOnSaveButtonOfErrorPopUp() throws AutomationException {
        CommonSteps.logInfo("user clicks on Save Button of error pop up");
        PageFactory.estateContactsPage().clickOnErrorPopUpSaveButton();
    }

    @Then("user verifies contact can be saved without selecting any role message")
    public void userVerifiesContactCanBeSavedWithoutSelectingAnyRoleMessage() throws AutomationException {
        PageFactory.estateContactsPage().verifyWithoutSelectingRoleContactSavedMsg();
    }

    @And("user fills other Basic details, Birth details and Contact Information for Individual Contact")
    public void userFillsOtherBasicDetailsBirthDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills other Basic details, Birth details and Contact Information for Individual Contact");
        PageFactory.estateContactsPage().fillsOtherBasicDetailsBirthDetailsAndContactInformation();
    }

    @Then("user verifies all details of new Individual Global Contact are auto-saved")
    public void userVerifiesAllDetailsOfNewIndividualGlobalContactAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that all details of newly added Individual global contact are auto-saved.");
        PageFactory.estateContactsPage().verifyIndividualGlobalContactDetailsAutoSaved();
        CommonSteps.takeScreenshot();
    }

    @And("user fills other Basic details, Birth details and Contact Information for Entity Contact")
    public void userFillsOtherBasicDetailsBirthDetailsAndContactInformationForEntityContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills other Basic details, Birth details and Contact Information for Entity Contact");
        PageFactory.estateContactsPage().fillsOtherBasicDetailsBirthDetailsAndContactInformationForEntity();
    }

    @Then("user verifies all details of new Entity Global Contact are auto-saved")
    public void userVerifiesAllDetailsOfNewEntityGlobalContactAreAutoSaved() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User verified that all details of newly added Entity global contact are auto-saved.");
        PageFactory.estateContactsPage().verifyEntityGlobalContactDetailsAutoSaved();
        CommonSteps.takeScreenshot();
    }
}
    



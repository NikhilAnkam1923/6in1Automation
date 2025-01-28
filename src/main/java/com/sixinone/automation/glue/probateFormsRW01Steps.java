package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class probateFormsRW01Steps {

    @When("user click on Create button")
    public void userClicksOnTheCreateButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on the Create button");
        PageFactory.probateFormsRW01Page().clickButtonCreate();
    }

    @Then("user fill first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled the first name, last name, and SSN details.");
        PageFactory.probateFormsRW01Page().enterFirstAndLastNameAndSSN();
    }

    @And("user clicks on Proceed Button")
    public void userClickOnProceedButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Proceed button.");
        PageFactory.probateFormsRW01Page().clickOnProceedButton();
    }

    @And("^user clicks on Create a new estate with the entered name button$")
    public void userClickCreateNewEstateButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on 'Create a new estate with the entered name' button for new user.");
        PageFactory.probateFormsRW01Page().clickCreateNewEstateButton();
    }

    @Then("^user fill decedent basic information$")
    public void userFillsDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled decedent basic information for new user.");
        PageFactory.probateFormsRW01Page().fillDecedentBasicInformation();
    }

    @And("user clicks on Next Button")
    public void userClickOnNextButton() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Next button.");
        PageFactory.probateFormsRW01Page().clickOnNextButton();
    }

    @When("^user fills Last Address\\/Domicile Details$")
    public void userFillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Last Address/Domicile details.");
        PageFactory.probateFormsRW01Page().fillLastAddressDomicileDetails();
    }

    @When("user fill Life Details")
    public void userFillsLifeDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user fills Life Details and validate the fields");
        PageFactory.probateFormsRW01Page().fillLifeDetails();
    }

    @When("user fill the Place of Death details")
    public void userFillsPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Place of Death details.");
        PageFactory.probateFormsRW01Page().fillPlaceOfDeathDetails();
    }

    @When("user click on Estate Tab")
    public void userClicksOnEstateTab() throws AutomationException {
        CommonSteps.logInfo("User clicked on the Estate tab.");
        PageFactory.probateFormsRW01Page().clickOnEstateTab();
    }

    @And("user fill the Estate details")
    public void userFillsEstateDetails() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User filled Estate details.");
        PageFactory.probateFormsRW01Page().fillEstateDetails();
    }

    @When("user verifies correct file number is displayed at the top of the form")
    public void userVerifyCorrectFileNumber() throws AutomationException {
        CommonSteps.logInfo("Verified that correct file number is displayed at the top of the form");
        PageFactory.probateFormsRW01Page().verifyCorrectFileNumber();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies all fields of section 1 displays correct information fetched from the decedent info tab")
    public void userVerifiesAllFieldsOfSectionDisplaysCorrectInformationFetchedFromTheDecedentInfoTab() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that all fields of section 1 displays correct information fetched from the decedent info tab");
        PageFactory.probateFormsRW01Page().verifyCorrectInformationFetchedFromTheDecedentInfoTab();
        CommonSteps.takeScreenshot();
    }

    @And("^user clicks on \"([^\"]*)\"$")
    public void userClicksOn(String section) throws AutomationException {
        CommonSteps.logInfo("user clicks on "+section);
        PageFactory.probateFormsRW01Page().clickOnSection(section);
    }

    @Then("user verifies on clicking section 2 an informative text box is displayed")
    public void userVerifiesOnClickingSection2AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 2 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection2InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies in section 2 only one checkbox should be able to be checked")
    public void userVerifiesInSectionOnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        CommonSteps.logInfo("Verified that in section 2 only one checkbox should be able to be checked");
        PageFactory.probateFormsRW01Page().verifyInSection2OnlyOneCheckboxShouldBeAbleToBeChecked();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies on clicking section 3 an informative text box is displayed")
    public void userVerifiesOnClickingSection3AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 3 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection3InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies in section 3 only one checkbox should be able to be checked")
    public void userVerifiesInSection3OnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        CommonSteps.logInfo("Verified that in section 3 only one checkbox should be able to be checked");
        PageFactory.probateFormsRW01Page().verifyInSection3OnlyOneCheckboxShouldBeAbleToBeChecked();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies on clicking section 4 an informative text box is displayed")
    public void userVerifiesOnClickingSection4AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 4 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection4InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @When("Temporary")
    public void temporary() throws AutomationException, InterruptedException, AWTException {
        PageFactory.probateFormsRW01Page().tempDelete();
    }

    @Then("user verifies on clicking 'Other' checkbox, text area is enabled")
    public void userVerifiesOnClickingOtherCheckboxTextAreaIsEnabled() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking 'Other' checkbox, text area is enabled");
        PageFactory.probateFormsRW01Page().verifyOtherCheckboxTextAreaIsEnabled();
        CommonSteps.takeScreenshot();
    }

    @And("user click on Last Name field")
    public void userClickOnLastNameField() throws AutomationException {
        CommonSteps.logInfo("user click on Last Name field");
        PageFactory.probateFormsRW01Page().clickOnLastNameField();
    }

    @Then("^user verifies a side bar with title 'Select Attorney/Correspondent' is displayed$")
    public void userVerifiesASideBarIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that a side bar with title 'Select Attorney/Correspondent' is displayed");
        PageFactory.probateFormsRW01Page().verifySideBarIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies on clicking section 5 an informative text box is displayed")
    public void userVerifiesOnClickingSection5AnInformativeTextBoxIsDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that on clicking section 5 an informative text box is displayed");
        PageFactory.probateFormsRW01Page().verifySection5InformativeTextBoxIsDisplayed();
        CommonSteps.takeScreenshot();
    }

    @When("^user click on Add button for selected \"([^\"]*)\" contact$")
    public void userClickOnAddButtonForSelectedContactWithABackground(String contactType) throws AutomationException {
        CommonSteps.logInfo("User clicks on Add button for selected "+contactType+" contact");
        PageFactory.probateFormsRW01Page().clickOnAddButtonForSpecificContactType(contactType);
    }

    @And("user selects the Accountant Role for Contact")
    public void userSelectsTheRoleForContact1() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.probateFormsRW01Page().selectRoleAccountant();
    }

    @Then("user click on the Select Role button for Contact")
    public void clickOnSelectRoleButton() throws AutomationException {
        CommonSteps.logInfo("User clicks on Select Role button for Contact");
        PageFactory.probateFormsRW01Page().selectRoleButton();
    }

    @And("user selects the Attorney Role for Contact")
    public void userSelectsTheRoleForContact2() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.probateFormsRW01Page().selectRoleAttorney();
    }

    @And("user selects the Beneficiary Role for Contact")
    public void userSelectsTheRoleForContact3() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.probateFormsRW01Page().selectRoleBeneficiary();
    }

    @And("user selects the Correspondent Role for Contact")
    public void userSelectsTheRoleForContact4() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.probateFormsRW01Page().selectRoleCorrespondent();
    }

    @And("user selects the Fiduciary Role for Contact")
    public void userSelectsTheRoleForContact() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User selected the Role for Contact.");
        PageFactory.probateFormsRW01Page().selectRoleFiduciary();
    }

    @When("user navigates to estate contacts tab")
    public void userNavigatesToTheEstateContactsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the estate contacts tab");
        PageFactory.probateFormsRW01Page().navigateToEstateContactsTab();
    }

    @When("user click on the Create New Individual Contact button")
    public void userClicksOnTheCreateNewIndividualContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Create New Individual Contact button.");
        PageFactory.probateFormsRW01Page().clickOnNewIndividualContactBtn();
    }

    @And("^user fill the details for \"([^\"]*)\"$")
    public void userFillsAllTheDetailsForNewGlobalContact(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        CommonSteps.logInfo("User fills all the details for " + contactType);
        PageFactory.probateFormsRW01Page().fillNewGlobalContactDetails(contactType);
    }

    @When("user click on the Add Contact button")
    public void userClicksOnAddContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicked on the Add Contact button.");
        PageFactory.probateFormsRW01Page().clickAddContactButton();
    }

    @And("user fills other Basic details, Birth details and Contact Information")
    public void userFillsOtherBasicDetailsBirthDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills other Basic details, Birth details and Contact Information for Individual Contact");
        PageFactory.probateFormsRW01Page().fillsOtherBasicDetailsBirthDetailsAndContactInformation();
    }

    @And("user fills the Address information")
    public void userFillsAddressInformation() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("User fills Address information");
        PageFactory.probateFormsRW01Page().fillAddressInfo();
    }

    @When("user navigates to the Probate forms tab")
    public void userNavigatesToTheProbateFormsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the probate forms tab");
        PageFactory.probateFormsRW01Page().navigateToProbateFormsTab();
    }

    @Then("^user clicks on the \"([^\"]*)\" form$")
    public void userClickOnTheRW(String formToSelect) throws AutomationException {
        CommonSteps.logInfo("user click on the "+formToSelect+" form");
        PageFactory.probateFormsRW01Page().clickOnRWForm(formToSelect);
    }

    @And("^user selects Role as \"([^\"]*)\"$")
    public void userSelectsRoleAs(String role) throws AutomationException {
        CommonSteps.logInfo("User selects Role as "+role);
        PageFactory.probateFormsRW01Page().selectRoleForAttorney(role);
    }

    @And("user verifies out of the available roles and contact name, only 1 contact is able to be selected")
    public void userVerifiesOutOfTheAvailableRolesAndContactNameOnlyContactIsAbleToBeSelected() throws AutomationException {
        CommonSteps.logInfo("Verified that out of the available roles and contact name, only 1 contact is able to be selected");
        PageFactory.probateFormsRW01Page().verifyOnlyContactIsAbleToBeSelected();
        CommonSteps.takeScreenshot();
    }

    @And("user click on the Proceed button")
    public void userClickOnTheProceedButton() throws AutomationException {
        CommonSteps.logInfo("user click on the Proceed button");
        PageFactory.probateFormsRW01Page().clickOnTheProceedButton();
    }

    @And("user click on Executor Last Name field")
    public void userClickOnExecutorLastNameField() throws AutomationException {
        CommonSteps.logInfo("user click on Executor Last Name field");
        PageFactory.probateFormsRW01Page().clickOnExecutorLastNameField();
    }

    @Then("^user verifies a side bar with title 'Select Representatives' is displayed$")
    public void userVerifiesASideBarIsDisplayedForSection5() throws AutomationException {
        CommonSteps.logInfo("Verified that a side bar with title 'Select Representatives' is displayed");
        PageFactory.probateFormsRW01Page().verifySideBarIsDisplayedForSection5();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies Executor, co-executor, secondary co-executor selection types are displayed")
    public void userVerifiesExecutorCoExecutorSecondaryCoExecutorSelectionTypesAreDisplayed() throws AutomationException {
        CommonSteps.logInfo("Verified that Executor, co-executor, secondary co-executor selection types are displayed");
        PageFactory.probateFormsRW01Page().verifySelectionTypesAreDisplayed();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies only 1 contact can be dragged and dropped into each selection type")
    public void userDragAndDropContactsIntoSelectionTypes() throws AutomationException {
        CommonSteps.logInfo("Verified that only 1 contact can be dragged and dropped into each selection type");
        PageFactory.probateFormsRW01Page().dragAndDropContactsIntoSelectionTypes();
        CommonSteps.takeScreenshot();
    }

    @And("user clicks on Accept button")
    public void userClicksOnAcceptButton() throws AutomationException {
        CommonSteps.logInfo("user clicks on Accept button");
        PageFactory.probateFormsRW01Page().clicksOnAcceptButton();
    }

    @Then("user verifies on selecting the contact its information is displayed in section 4")
    public void userVerifiesOnSelectingTheContactItsInformationIsDisplayedInSection4() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that on selecting the contact its information is displayed in section 4");
        PageFactory.probateFormsRW01Page().verifyContactItsInformationIsDisplayedInSection4();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the selected contacts are displayed under executor, co-executor and secondary co-executor")
    public void userVerifiesTheSelectedContactsAreDisplayedUnderExecutorCoExecutorAndSecondaryCoExecutor() throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that the selected contacts are displayed under executor, co-executor and secondary co-executor");
        PageFactory.probateFormsRW01Page().verifySelectedContactsAreDisplayedUnderExecutorCoExecutorAndSecondaryCoExecutor();
        CommonSteps.takeScreenshot();
    }
}

package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.*;

import static com.sixinone.automation.pages.BasePage.driverUtil;

public class GlobalContactsSteps {

    @When("^user enters \"([^\"]*)\" as the first name and \"([^\"]*)\" as the last name$")
    public void userEntersFirstAndLastName(String firstName, String lastName) throws AutomationException {
        CommonSteps.logInfo("Entering first and last name");
        PageFactory.globalContactPage().enterFirstnameAndLastNameFields(firstName, lastName);
    }

    @And("user clicks on the Create Individual Contact button")
    public void userClicksOnCreateIndividualContactButton() throws AutomationException {
        CommonSteps.logInfo("Clicking on the Create Individual Contact button");
        PageFactory.globalContactPage().clicksOnCreateIndividualContactButton();
    }

    @Then("user lands on the Individual Global Contact Creation page")
    public void userLandsOnTheIndividualGlobalContactCreationPage() {
        CommonSteps.logInfo("user landed on the Individual Global Contact Creation page successfully");
        PageFactory.globalContactPage().verifyIndividualGlobalContactCreationgPage();
        CommonSteps.takeScreenshot();
    }

    @And("^\"([^\"]*)\" field is pre-filled with \"([^\"]*)\"$")
    public void fieldIsPrefilledWith(String fieldName, String expectedValue) throws AutomationException {
        CommonSteps.logInfo("Verifying field " + fieldName + " is pre-filled with value: " + expectedValue);
        PageFactory.globalContactPage().verifyFieldPrefilled(fieldName, expectedValue);
        CommonSteps.takeScreenshot();
    }

    @When("^user enters all required and optional fields$")
    public void userEntersAllFields(String firstName, String lastName, String phoneNumber, String emailAddress, String middleName, String maidenName, String entityName) throws AutomationException {
        CommonSteps.logInfo("user enters all required and optional fields");
        PageFactory.globalContactPage().enterRequiredFields(firstName, lastName, phoneNumber, emailAddress);
        PageFactory.globalContactPage().enterOptionalFields(middleName, maidenName, entityName);
        CommonSteps.takeScreenshot();
    }

    @And("^user enters SSN and EIN details$")
    public void userEntersSSNAndEIN(String ssn, String ein) throws AutomationException {
        PageFactory.globalContactPage().enterSSNAndEIN(ssn, ein);
    }

    @And("user clicks on the save button")
    public void userClicksOnButton() throws AutomationException {
        PageFactory.globalContactPage().clickOnSaveButton();
    }

    @Then("^user verifies that a confirmation message is displayed$")
    public void userVerifiesConfirmationMessage() throws AutomationException {
        PageFactory.globalContactPage().verifyConfirmationMessage();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify Landing page")
    public void userVerifyLandingPage() throws AutomationException {
        CommonSteps.logInfo("User verify home page ");
        driverUtil.waitForLoaderToDisappear();
        PageFactory.globalContactPage().verifyLandingPage();
        CommonSteps.takeScreenshot();
    }

    @And("user click on Global Contact tab")
    public void userClickOnGlobalContactTab() throws AutomationException {
        PageFactory.globalContactPage().clickOnGlobalContactButton();
    }

    @When("user click on Create button")
    public void userClickOnCreateButton() throws AutomationException {
        PageFactory.globalContactPage().clickOnCreateButton();
    }

    @Then("verify user is on the Global Contact page")
    public void verifyUserIsOnTheGlobalContactPage() throws AutomationException {
        CommonSteps.logInfo("User verify user is on the Global Contact page ");
        driverUtil.waitForLoaderToDisappear();
        PageFactory.globalContactPage().verifyLandingOnGlobalContactPage();
        CommonSteps.takeScreenshot();
    }

    @Then("verify user is on the Global Contact Creation page")
    public void verifyUserIsOnTheGlobalContactCreationPage() throws AutomationException {
        CommonSteps.logInfo("user is on the Global Contact Creation page ");
        driverUtil.waitForLoaderToDisappear();
        PageFactory.globalContactPage().verifyLandingOnGlobalContactCreationPage();
        CommonSteps.takeScreenshot();
    }


}


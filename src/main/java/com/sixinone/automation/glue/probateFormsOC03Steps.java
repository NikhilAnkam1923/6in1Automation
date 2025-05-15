package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class probateFormsOC03Steps {
    @Then("^user verifies the 'Account of' field is populated with the Fiduciary’s name$")
    public void userVerifiesTheAccountOfFieldIsPopulatedWithTheFiduciarySName() throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that the 'Account of' field is populated with the Fiduciary’s name");
        PageFactory.probateFormsOC03Page().verifyAccountOfFieldIsPopulatedWithTheFiduciarySName();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies after removing the existing contact next inline contact is displayed on the form")
    public void userVerifiesAfterRemovingTheExistingContactNextInlineContactIsDisplayedOnTheForm() throws AutomationException {
        CommonSteps.logInfo("Verified that after removing the existing contact next inline contact is displayed on the form");
        PageFactory.probateFormsOC03Page().verifyAfterRemovingTheExistingContactNextInlineContactIsDisplayedOnTheForm();
        CommonSteps.takeScreenshot();
    }

    @Then("user verifies the contact removed from the estate contacts is also removed from the attachment as well")
    public void userVerifiesTheContactRemovedFromTheEstateContactsIsAlsoRemovedFromTheAttachmentAsWell() throws AutomationException {
        CommonSteps.logInfo("Verified that the contact removed from the estate contacts is also removed from the attachment as well");
        PageFactory.probateFormsOC03Page().verifyContactRemovedFromTheEstateContactsIsAlsoRemovedFromTheAttachmentAsWell();
    }
}

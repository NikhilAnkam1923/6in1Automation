package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.GlobalContactPage;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sixinone.automation.glue.CommonSteps.driverUtil;


public class GlobalContactsSteps {

    @When("^user clicks on the \"([^\"]*)\" button$")
    public void userClicksOnButton(String buttonName) throws AutomationException {
        Map<String, String> buttonDetails = new HashMap<>();

        switch (buttonName) {
            case "Global Contact":
                buttonDetails.put(buttonName, GlobalContactPage.GLOBALCONTACT_BUTTON);
                break;
            case "Create":
                buttonDetails.put(buttonName, GlobalContactPage.CREATE_BUTTON);
                break;
            case "Create Individual Contact":
                buttonDetails.put(buttonName, GlobalContactPage.CREATE_INDIVIDUAL_CONTACT_BTN);
                break;
            case "Save":
                buttonDetails.put(buttonName, GlobalContactPage.SAVE_BUTTON);
                break;
            default:
                throw new AutomationException("Unknown button: " + buttonName);
        }
        new GlobalContactPage().clickButton(buttonDetails);

    }


    @Then("^user verifies the \"([^\"]*)\" page$")
    public void userVerifiesPage(String pageName) throws AutomationException {
        Map<String, String> pageDetails = new HashMap<>();

        switch (pageName) {
            case "Global Contacts":
                pageDetails.put(pageName, GlobalContactPage.GLOBAL_CONTACTS_PAGE);
                break;
            case "Home":
                pageDetails.put(pageName, GlobalContactPage.HOME_PAGE);
                break;
            case "Global Contact Creation":
                pageDetails.put(pageName, GlobalContactPage.GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Individual Global Contact Creation":
                pageDetails.put(pageName, GlobalContactPage.INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE);
                break;
            default:
                throw new AutomationException("Unknown page: " + pageName);
        }
        new GlobalContactPage().verifyPageElements(pageDetails);
        CommonSteps.takeScreenshot();
    }

    @When("^user enters \"([^\"]*)\" as the first name and \"([^\"]*)\" as the last name$")
    public void userEntersFirstAndLastName(String firstName, String lastName) throws AutomationException {
        CommonSteps.logInfo("Entering first and last name");
        PageFactory.globalContactPage().enterFirstnameAndLastNameFields(firstName, lastName);
        CommonSteps.takeScreenshot();
    }

    @And("^\"([^\"]*)\" field is pre-filled with \"([^\"]*)\"$")
    public void fieldIsPrefilledWith(String fieldName, String expectedValue) throws AutomationException {
        CommonSteps.logInfo("Verifying field " + fieldName + " is pre-filled with value: " + expectedValue);
        PageFactory.globalContactPage().verifyFieldPrefilled(fieldName, expectedValue);
        CommonSteps.takeScreenshot();
    }

    @When("^user enters all required and optional fields$")
    public void userEntersAllFields(DataTable dataTable) throws AutomationException {
        List<Map<Object, Object>> data = dataTable.asMaps(String.class, String.class);


        Map<Object, Object> row = data.get(0);


        String middleName = row.get("middleName").toString();
        String maidenName = row.get("maidenName").toString();
        String entityName = row.get("entityName").toString();
        String emailAddress = row.get("emailAddress").toString();
        String PTIN = row.get("PTIN").toString();
        String PINeFile = row.get("PINeFile").toString();
        String BarID = row.get("BarID").toString();
        String CAF = row.get("CAF").toString();



        String addressLine1 = row.get("AddressLine1").toString();
        String addressLine2 = row.get("AddressLine2").toString();
        String zip = row.get("Zip").toString();


        PageFactory.globalContactPage().enterRequiredFields(addressLine1, zip);
        PageFactory.globalContactPage().enterOptionalFields(middleName, maidenName, entityName,emailAddress,PTIN,PINeFile,BarID,CAF,addressLine2);
        CommonSteps.takeScreenshot();
    }


    @And("^user enters SSN and EIN details$")
    public void userEntersSSNAndEIN(DataTable dataTable) throws AutomationException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String ssn = row.get("ssn");
            String ein = row.get("ein");
            PageFactory.globalContactPage().enterSSNAndEIN(ssn, ein);
        }
    }

    @And("user enters SSN,EIN,Phone Number,workPhone and fax details")
    public void userEntersSSNEINPhoneNumberWorkPhoneAndFaxDetails(DataTable dataTable) throws AutomationException {
        List<Map<Object, Object>> data = dataTable.asMaps(String.class, String.class);

        Map<Object, Object> row = data.get(0);

        String ssn = row.get("ssn").toString();
        String ein = row.get("ein").toString();
        String phoneNumber = row.get("phoneNumber").toString();
        String workNumber = row.get("workPhone").toString();
        String fax = row.get("fax").toString();

        PageFactory.globalContactPage().enterSSNEINPhoneNumberWorkNumberAndFax(ssn, ein, phoneNumber, workNumber, fax);
        CommonSteps.takeScreenshot();
    }

    @Then("^user enter \"([^\"]*)\" in \"([^\"]*)\" Field$")
    public void userEnterInField(String fieldData, String fieldName) throws AutomationException {
        boolean isInputSuccessful = PageFactory.globalContactPage().enterDataInFieldByLabel(fieldData, fieldName);
        if (!isInputSuccessful) {
            throw new AutomationException("Failed to enter data in field with label: '" + fieldName + "'.");
        }
        CommonSteps.logInfo("Entered value "+fieldData+" in the field: " + fieldName);
//        CommonSteps.takeScreenshot();
    }

    @Then("^Verify the city \"([^\"]*)\", state \"([^\"]*)\", and county \"([^\"]*)\" are automatically fetched$")
    public void verifyCityStateCountyAreFetched(String expectedCity, String expectedState, String expectedCounty) throws AutomationException {
        boolean isDataCorrect = PageFactory.globalContactPage().verifyFetchedFields(expectedCity, expectedState, expectedCounty);
        if (!isDataCorrect) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically.");
        }
        CommonSteps.logInfo("Verified auto-fetched values: City - " + expectedCity + ", State - " + expectedState + ", County - " + expectedCounty);
        CommonSteps.takeScreenshot();
    }



    @And("^user verifies that a confirmation message \"([^\"]*)\" is displayed$")
    public void userVerifiesConfirmationMessage(String confirmationMsg) throws AutomationException {
        PageFactory.globalContactPage().verifyConfirmationMessage(confirmationMsg);
        CommonSteps.takeScreenshot();
        PageFactory.globalContactPage().waitForToasterToInvisible(confirmationMsg);
    }
}




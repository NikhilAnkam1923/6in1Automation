package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.bouncycastle.oer.Switch;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.sixinone.automation.util.WebDriverUtil.*;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;


public class GlobalContactPage extends BasePage {

    // public static final String TAB_NAME = "//span[text()='%s']";
    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@name='contact.maidenName']";
    private static final String CONTACT_ENTITY_NAME_FIELD = "//input[@name='contact.entityName']";
    private static final String SSN_FIELD = "//input[@id='contact.ssn']";
    private static final String EIN_FIELD = "//input[@id='contact.ein']";
    public static final String SAVE_BUTTON = "//button[text()='Save']";
    private static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    public static final String SPINNER = "//div[contains(class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    private static final String FIELD_DYNAMIC_XPATH = "//input[contains(@name,'%s')]";
    private static final String ADDRESS_LINE1 = "//input[@name='address.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='address.addressLine2']";
    private static final String ZIP = "//input[@name='address.zip']";
    public static final String GLOBAL_CONTACTS_PAGE = "//a[text()='Global Contacts']";
    public static final String GLOBAL_CONTACTS_TAB = "//a[@href='/law-firm/global-contacts']";
    public static final String HOME_PAGE = "//a[text()='Estate']";
    public static final String GLOBAL_CONTACT_CREATION_PAGE = "//a[text()='New']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";
    public static final String FIELD_XPATH = "//label[text()='%s']/following-sibling::input";
    public static final String ENTITY_NAME_FIELD = "//input[@name='entityName']";
    public static final String CREATE_ENTITY_CONTACT_BTN = "//button[text()='Create Entity Contact']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Entity Details']";
    public static final String USERS = "src/test/resources/test-data/${env}/users.json";
    public static final String EIN_DUPLICATION_ERROR = "//div[text()='EIN must be unique.']";
    public static final String RADIO_BUTTON = "//input[@aria-label='radio-70']";
    public static final String SELECT_OR_CREATENEW_PAGE = "//div[text()='Contact (Select or Create New)']";
    public static final String INVALID_EIN_ERROR_MESSAGE = "///input[@id='contact.ein']/following-sibling::div";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='3'] //input[@aria-label='Filter']";
    public static final String ACTIONS_BUTTON = "//td[@class='action-column text-center']//button[@class='dropdown-toggle btn btn-primary']";
    public static final String EDIT_BUTTON = "//a[text()='Edit']";
    public String firstName;
    public static final String ESATE_TAB = "//a[@class='active-link nav-link']";
    public static final String REQUIRED_FIELD_ERROR_XPATH = "//input[contains(@aria-required,'true')]/following-sibling::div";
    public static final String ADDRESS_LINE1_ERROR_REQUIRED_XPATH = "//input[@id='address.addressLine1']/following-sibling::div";
    public static final String ZIP_ERROR_REQUIRED_XPATH = "//input[@id='address.zip']/following-sibling::div";
    public static final String CITY_ERROR_REQUIRED_XPATH = "//input[@id='address.city']/following-sibling::div";
    public static final String STATE_ERROR_REQUIRED_XPATH = "//div[@id='State']";
    public static final String FIRSTNAME_ERROR_REQUIRED_XPATH = "//input[@id='contact.firstName']/following-sibling::div";
    public static final String LASTNAME_REQUIRED_XPATH = "//input[@id='contact.lastName']/following-sibling::div";

    public GlobalContactPage() throws IOException {

    }

    public void tabNavigation(String tab) throws AutomationException, IOException {
        driverUtil.getWebElementAndScroll(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a",tab)).click();
    }



    public void buttonClick(String buttonName) throws AutomationException, IOException {
        Map<String, String> buttonDetails = new HashMap<>();

        switch (buttonName) {
            case "Create":
                buttonDetails.put(buttonName, CREATE_BUTTON);
                break;
            case "Create Individual Contact":
                buttonDetails.put(buttonName, CREATE_INDIVIDUAL_CONTACT_BTN);
                break;
            case "Create Entity Contact":
                buttonDetails.put(buttonName, CREATE_ENTITY_CONTACT_BTN);
                break;
            case "Save":
                buttonDetails.put(buttonName, SAVE_BUTTON);
                break;
            case "Radio Button":
                buttonDetails.put(buttonName, RADIO_BUTTON);
                break;
            case "Edit":
                buttonDetails.put(buttonName, EDIT_BUTTON);
                break;
            default:
                throw new AutomationException("Unknown button: " + buttonName);
        }
        new GlobalContactPage().clickButton(buttonDetails);

    }

    String LAST_NAME_FIELD_CREATE = "//input[@id='contact.lastName']";
    String FIRST_NAME_FIELD_CREATE = "//input[@id='contact.firstName']";

    public void clearFields() throws AutomationException {
        driverUtil.getWebElementAndScroll(LAST_NAME_FIELD_CREATE).clear();
        driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD_CREATE).clear();
    }

    public void globalContactCreation(String action, String contactType) throws AutomationException, IOException, ParseException {
        Map<String, String> buttonDetails = new HashMap<>();
        buttonClick(action);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(CREATE_INDIVIDUAL_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(LAST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.lastName").toString() + CommonUtil.currentDateAndTime());
                driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.firstName").toString() + CommonUtil.currentDateAndTime());
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                System.out.println(firstName + "is the value while creation");
                buttonClick("Create Individual Contact");
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString() + CommonUtil.currentDateAndTime());
                buttonClick("Create Entity Contact");

                break;
            default:
                throw new AutomationException("Unknown button: " + "");
        }
        new GlobalContactPage().clickButton(buttonDetails);
        CommonSteps.takeScreenshot();
    }

    public String filterByName() throws AutomationException {
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(firstName);
        return firstName;
    }

    public String filterByContactType(String contactType) throws AutomationException {
        driverUtil.getWebElement(CONTACT_TYPE_FILTER_INPUT).click();
        driverUtil.getWebElement(CONTACT_TYPE_FILTER_INPUT).sendKeys(contactType);
        return contactType;
    }


    public void globalContactEdit(String action, String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        Map<String, String> buttonDetails = new HashMap<>();
        firstName = filterByName();
        filterByContactType(contactType.replace("Global Contact", "").trim());
        driverUtil.getWebElement(ACTIONS_BUTTON).click();
        //driverUtil.waitForElementToBeClickable(action);
        buttonClick(action);
        driverUtil.waitForLoaderToDisappear();
        switch (contactType) {
            case "Individual Global Contact":
                contactInformationFieldsForEdit("Individual Global Contact");
                buttonClick("Save");
                break;
            case "Entity Global Contact":
                contactInformationFieldsForEdit("Entity Global Contact");
                break;
        }
        new GlobalContactPage().clickButton(buttonDetails);
        CommonSteps.takeScreenshot();
    }

    public void contactInformationFieldsForEdit(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                WebElement emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                emailField.clear();
                emailField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.emailId").toString());
                WebElement phoneNumberField = driverUtil.getWebElementAndScroll(PHONE_NUMBER_FIELD);
                WebDriverUtil.waitForVisibleElement(phoneNumberField);
                setInputUsingJavaScript(phoneNumberField, CommonUtil.getJsonPath("Edit").get("Edit.phoneNumber").toString());
                WebElement workNumberField = driverUtil.getWebElementAndScroll(WORK_NUMBER_FIELD);
                setInputUsingJavaScript(workNumberField, CommonUtil.getJsonPath("Edit").get("Edit.workNumber").toString());
                WebElement faxField = driverUtil.getWebElementAndScroll(FAX_FIELD);
                setInputUsingJavaScript(faxField, CommonUtil.getJsonPath("Edit").get("Edit.fax").toString());
                WebElement ssnField = driverUtil.getWebElement(SSN_FIELD);
                setInputUsingJavaScript(ssnField, CommonUtil.getJsonPath("Edit").get("Edit.ssn").toString());
                WebElement ptinField = driverUtil.getWebElementAndScroll(PTIN_FIELD);
                ptinField.clear();
                ptinField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ptin").toString());
                WebElement pinEFileField = driverUtil.getWebElementAndScroll(PINEFILE_FIELD);
                pinEFileField.clear();
                pinEFileField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.pinEFile").toString());
                WebElement barIDField = driverUtil.getWebElementAndScroll(BARID_FIELD);
                barIDField.clear();
                barIDField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.barId").toString());
                WebElement cafField = driverUtil.getWebElementAndScroll(CAF_FIELD);
                cafField.clear();
                cafField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.caf").toString());
                break;

            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                WebElement entityEmailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                entityEmailField.clear();
                entityEmailField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.emailId").toString());
                WebElement entityPhoneField = driverUtil.getWebElementAndScroll(PHONE_NUMBER_FIELD);
                WebDriverUtil.waitForVisibleElement(entityPhoneField);
                setInputUsingJavaScript(entityPhoneField, CommonUtil.getJsonPath("Edit").get("Edit.phoneNumber").toString());
                WebElement entityWorkNumberField = driverUtil.getWebElementAndScroll(WORK_NUMBER_FIELD);
                setInputUsingJavaScript(entityWorkNumberField, CommonUtil.getJsonPath("Edit").get("Edit.workNumber").toString());
                WebElement entityFaxField = driverUtil.getWebElementAndScroll(FAX_FIELD);
                setInputUsingJavaScript(entityFaxField, CommonUtil.getJsonPath("Edit").get("Edit.fax").toString());
                WebElement einField = driverUtil.getWebElementAndScroll(EIN_FIELD);
                setInputUsingJavaScript(einField, CommonUtil.getJsonPath("Edit").get("Edit.ein").toString());
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
        CommonSteps.takeScreenshot();
    }


    public void fillGlobalContactDetails(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Map<String, String> buttonDetails = new HashMap<>();
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
                middleNameField.clear();
                middleNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.middleName").toString());
                WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
                maidenNameField.clear();
                maidenNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.maidenName").toString());
                WebElement entityNameField = driverUtil.getWebElementAndScroll(CONTACT_ENTITY_NAME_FIELD);
                entityNameField.clear();
                entityNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString());
                WebElement emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                emailField.clear();
                emailField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.emailId").toString());
                WebElement ptinField = driverUtil.getWebElementAndScroll(PTIN_FIELD);
                ptinField.clear();
                ptinField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.ptin").toString());
                WebElement pinEFileField = driverUtil.getWebElementAndScroll(PINEFILE_FIELD);
                pinEFileField.clear();
                pinEFileField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.pinEFile").toString());
                WebElement barIDField = driverUtil.getWebElementAndScroll(BARID_FIELD);
                barIDField.clear();
                barIDField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.barId").toString());
                WebElement cafField = driverUtil.getWebElementAndScroll(CAF_FIELD);
                cafField.clear();
                cafField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.caf").toString());
                WebElement addressLine2Field = driverUtil.getWebElementAndScroll(ADDRESS_LINE2);
                addressLine2Field.clear();
                addressLine2Field.sendKeys(CommonUtil.getJsonPath("Create").get("Create.addressLine2").toString());
                WebElement zipField = driverUtil.getWebElementAndScroll(ZIP);
                zipField.clear();
                zipField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.zip").toString());
                WebElement addressLine1Field = driverUtil.getWebElementAndScroll(ADDRESS_LINE1);
                addressLine1Field.clear();
                addressLine1Field.sendKeys(CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString());
                WebElement SSNField = driverUtil.getWebElement(SSN_FIELD);
                WebElement EINField = driverUtil.getWebElement(EIN_FIELD);
                WebElement phoneNumberField = driverUtil.getWebElement(PHONE_NUMBER_FIELD);
                WebElement workNumberField = driverUtil.getWebElement(WORK_NUMBER_FIELD);
                WebElement faxField = driverUtil.getWebElement(FAX_FIELD);
                WebDriverUtil.waitForVisibleElement(SSNField);
                setInputUsingJavaScript(SSNField, CommonUtil.getJsonPath("Create").get("Create.ssn").toString());
                setInputUsingJavaScript(EINField, CommonUtil.getJsonPath("Create").get("Create.ein").toString());
                WebDriverUtil.waitForVisibleElement(phoneNumberField);
                setInputUsingJavaScript(phoneNumberField, CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString());
                setInputUsingJavaScript(workNumberField, CommonUtil.getJsonPath("Create").get("Create.workNumber").toString());
                setInputUsingJavaScript(faxField, CommonUtil.getJsonPath("Create").get("Create.fax").toString());
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                // Required Fields for Entity
                WebElement EntityEINField = driverUtil.getWebElementAndScroll(EIN_FIELD);
                EntityEINField.clear();
                EntityEINField.click();
                setInputUsingJavaScript(EntityEINField, CommonUtil.getJsonPath("Create").get("Create.ein").toString());
                WebElement entityContactNameField = driverUtil.getWebElementAndScroll(CONTACT_ENTITY_NAME_FIELD);
                entityContactNameField.clear();
                entityContactNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString());
                WebElement entityEmailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                entityEmailField.clear();
                entityEmailField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.emailId").toString());
                WebElement entityPhoneField = driverUtil.getWebElementAndScroll(PHONE_NUMBER_FIELD);
                entityPhoneField.clear();
                entityPhoneField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString());
                WebElement entityAddressField = driverUtil.getWebElementAndScroll(ADDRESS_LINE1);
                entityAddressField.clear();
                entityAddressField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString());
                WebElement entityZipField = driverUtil.getWebElementAndScroll(ZIP);
                entityZipField.clear();
                entityZipField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.zip").toString());
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
        CommonSteps.takeScreenshot();
    }

    public void saveGlobalContact() throws AutomationException, IOException {
        driverUtil.waitForElementToBeClickable(SAVE_BUTTON);
        buttonClick("Save");
    }

    public void verifyGlobalContactSaved(String contactType) throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact details have been successfully saved."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(CONFIRMATION_MESSAGE));
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyPageElements(Map<String, String> pageDetails) throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);

        for (Map.Entry<String, String> entry : pageDetails.entrySet()) {
            String pageName = entry.getKey();
            String elementXpath = entry.getValue();
            waitForVisibleElement(By.xpath(elementXpath));
            WebElement element = driverUtil.getWebElementAndScroll(elementXpath);
            if (element == null) {
                throw new AutomationException(String.format("%s Page is not displayed or it takes time to load", pageName));
            }
        }
    }

    public void enterFirstnameAndLastNameFields(String firstName, String lastName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.sendKeys(lastName);
    }

    public void verifyFieldPrefilled(String fieldName, String expectedValue) throws AutomationException {
        String fieldXpath = String.format(FIELD_DYNAMIC_XPATH, fieldName);
        WebElement fieldElement = driverUtil.getWebElementAndScroll(fieldXpath);
        String actualValue = fieldElement.getAttribute("value");
        if (!actualValue.equals(expectedValue)) {
            throw new AutomationException("Field " + fieldName + " is not pre-filled correctly. " + "Expected: " + expectedValue + ", Found: " + actualValue);
        }
    }


    private void setInputUsingJavaScript(WebElement element, String value) throws AutomationException {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
        js.executeScript("arguments[0].value=arguments[1];", element, value);
    }

    public void verifyConfirmationMessage(String confirmationMsg) throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, confirmationMsg));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
    }

    public boolean enterDataInFieldByLabel(String fieldData, String fieldName) {
        String inputFieldXPath = String.format(FIELD_XPATH, fieldName);
        try {
            WebElement inputField = driverUtil.getWebElement(inputFieldXPath, 5);
            if (inputField == null) {
                throw new AutomationException("Input field with label '" + fieldName + "' not found.");
            }
            inputField.clear();
            inputField.sendKeys(fieldData + Keys.ENTER);
            return true;
        } catch (Exception e) {
            CommonSteps.logError("Error entering data in field with label '" + fieldName + "': " + e.getMessage());
            return false;
        }
    }

    public boolean verifyFetchedFields(String expectedCity, String expectedState, String expectedCounty) {
        try {
            WebDriverUtil.waitForAWhile(2);
            WebElement cityField = driverUtil.getWebElement("//input[@id='address.city']", 10);
            String actualCity = cityField.getAttribute("value");
            WebElement stateField = driverUtil.getWebElement("//label[contains(text(),'State')]//following-sibling::div//div[contains(@class, 'select__single-value')]", 5);
            String actualState = stateField.getText();
            WebElement countyField = driverUtil.getWebElement("//input[@id='address.county']", 5);
            String actualCounty = countyField.getAttribute("value");
            return expectedCity.equals(actualCity) && expectedState.equals(actualState) && expectedCounty.equals(actualCounty);
        } catch (Exception e) {
            CommonSteps.logError("Failed to verify fetched city, state, or county values: " + e.getMessage());
            return false;
        }
    }

    public void enterEntityNameFields(String entityName) throws AutomationException {
        WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
        entityNameField.clear();
        entityNameField.sendKeys(entityName);
    }

    @Override
    String getName() {
        return "";
    }

    public void verifyUserType(String userType) {
        try {
            switch (userType) {
                case "Licensed":
                case "Reviewer":
                    try {
                        waitForVisibleElement(By.xpath(CREATE_BUTTON), 10);
                        waitForElementClickable(By.xpath(CREATE_BUTTON), 10);
                    } catch (TimeoutException ex) {
                        CommonSteps.logError(userType + " user: Create button is not visible or clickable within the timeout.");
                        throw ex;
                    }
                    buttonClick("Create");
                    CommonSteps.logInfo(userType + " user: Successfully clicked the Create button means eligible for create the Global Contacts.");
                    break;
                case "View Only":
                    try {
                        waitForInvisibleElement(By.xpath(CREATE_BUTTON), 5);
                        CommonSteps.logInfo("View Only user: Create button is not visible as expected.");
                    } catch (TimeoutException ex) {
                        throw new AutomationException("Create button should not be visible for View Only user.");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Invalid user type: " + userType);
            }
        } catch (Exception e) {
            System.err.println("Validation failed: " + e.getMessage());
        }

    }

    public void verifyDuplicateEINError() throws AutomationException {
        WebDriverUtil.waitForVisibleElement(By.xpath(EIN_DUPLICATION_ERROR));
        WebElement errorMessage = driverUtil.getWebElement(EIN_DUPLICATION_ERROR);
        if (errorMessage == null || !errorMessage.isDisplayed()) {
            throw new AutomationException("EIN error message is not displayed.");
        }
        String actualErrorMessage = errorMessage.getText().trim();
        String expectedErrorMessage = "EIN must be unique.";
        if (!actualErrorMessage.equals(expectedErrorMessage)) {
            throw new AutomationException("EIN error message mismatch. Expected: " + expectedErrorMessage + ", but found: " + actualErrorMessage);
        }
        CommonSteps.logInfo("EIN error message is displayed correctly for duplications: " + actualErrorMessage);
    }

    public void verifyRequiredFieldValidationErrors() throws AutomationException {
        try {
            driverUtil.getWebElement(ADDRESS_LINE1_ERROR_REQUIRED_XPATH).isDisplayed();
            driverUtil.getWebElement(ZIP_ERROR_REQUIRED_XPATH).isDisplayed();
            driverUtil.getWebElement(CITY_ERROR_REQUIRED_XPATH).isDisplayed();
            driverUtil.getWebElement(STATE_ERROR_REQUIRED_XPATH).isDisplayed();
        } catch (Exception e) {
            throw new AutomationException("Error messages not displayed");
        }
    }

    public void fillRequiredFields(String contactType) throws AutomationException, IOException, ParseException {
        switch (contactType) {
            case "Individual Global Contact":
                    WebElement zipField = driverUtil.getWebElementAndScroll(ZIP);
                    zipField.clear();
                    zipField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.zip").toString());
                    WebElement addressLine1Field = driverUtil.getWebElementAndScroll(ADDRESS_LINE1);
                    addressLine1Field.clear();
                    addressLine1Field.sendKeys(CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString());
                    WebDriverUtil.waitForAWhile();
                WebElement stateErrorElement = driverUtil.getWebElement(STATE_ERROR_REQUIRED_XPATH);
                if (stateErrorElement != null && stateErrorElement.isDisplayed()) {
                    throw new AutomationException("All the required fields are not filled123");
                }

                break;
            case "Entity Global Contact":
                break;
        }

    }

    private void fillField(String fieldName, String value) throws AutomationException {
        String fieldXpath = String.format("//input[contains(@placeholder, '%s')]", fieldName);
        WebElement field = driverUtil.getWebElementAndScroll(fieldXpath);
        field.sendKeys(value);
        CommonSteps.logInfo("Filled the field: " + fieldName + " with value: " + value);
    }

    private void waitForFieldToUpdate(String fieldXpath, String expectedText) throws AutomationException {
        driverUtil.waitForElement(By.xpath(fieldXpath), 10);
        WebElement field = driverUtil.getWebElementWithoutWait(fieldXpath);
        if (!field.getText().contains(expectedText)) {
            throw new AutomationException("Expected text '" + expectedText + "' not found in element: " + fieldXpath);
        }
    }

    public void verifyNoValidationErrors() throws AutomationException {
        List<WebElement> errorMessages = driverUtil.getWebElements(REQUIRED_FIELD_ERROR_XPATH);
        List<WebElement> visibleErrors = errorMessages.stream()
                .filter(WebElement::isDisplayed)
                .toList();
        if (!visibleErrors.isEmpty()) {
            String errorDetails = visibleErrors.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.joining(", "));
            throw new AutomationException("Validation errors are still visible after corrections: " + errorDetails);
        }
        CommonSteps.logInfo("No validation error messages are visible. Fields have been corrected successfully.");
    }

    public boolean isButtonEnabled(String buttonName) throws AutomationException {
        String buttonXpath = String.format("//button[contains(text(), '%s')", buttonName, buttonName);
        WebElement button = driverUtil.getWebElement(buttonXpath);
        return button != null && button.isEnabled();
    }

}





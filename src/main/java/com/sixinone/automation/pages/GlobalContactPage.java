package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import com.sun.source.tree.AssertTree;
import org.bouncycastle.oer.Switch;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
    private static final String LAST_NAME_FIELD_CREATE = "//input[@id='contact.lastName']";
    private static final String FIRST_NAME_FIELD_CREATE = "//input[@id='contact.firstName']";
    private static final String ENTITY_NAME_FIELD_CREATE = "//input[@name='contact.entityName']";
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
    public static final String GLOBAL_CONTACTS_TAB = "//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a";
    public static final String GLOBAL_CONTACTS_PAGE = "//a[text()='Global Contacts']";
    public static final String HOME_PAGE = "//a[text()='Estate']";
    public static final String GLOBAL_CONTACT_CREATION_PAGE = "//a[text()='New']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[@class='section-header mt-0' and following-sibling::div//input[@id='contact.firstName']]";
    public static final String SELECT_OR_CREATENEW_PAGE = "//div[text()='Contact (Select or Create New)']";
    private static final String FIRST_PAGE_BUTTON = "//button[@title='Go to the first page']";
    private static final String NEXT_PAGE = "//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";
    public static final String FIELD_XPATH = "//label[text()='%s']/following-sibling::input";
    public static final String ENTITY_NAME_FIELD = "//input[@name='entityName']";
    public static final String CREATE_ENTITY_CONTACT_BTN = "//button[text()='Create Entity Contact']";
    public static final String USERS = "src/test/resources/test-data/${env}/users.json";
    public static final String EIN_ERROR = "//div[contains(text(),'EIN')]";
    public static final String RADIO_BUTTON = "//input[@type='radio']";
    public static final String INVALID_EIN_ERROR_MESSAGE = "///input[@id='contact.ein']/following-sibling::div";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='3'] //input[@aria-label='Filter']";
    public static final String ACTIONS_BUTTON = "//td[@class='action-column text-center']//button[@class='dropdown-toggle btn btn-primary']";
    public static final String EDIT_BUTTON = "//a[text()='Edit']";
    public static String firstName;
    public static String lastName;
    public static String entityName;
    public static final String REQUIRED_FIELD_ERROR_XPATH = "//input[contains(@aria-required,'true')]/following-sibling::div";
    public static final String ADDRESS_LINE1_ERROR_REQUIRED_XPATH = "//input[@id='address.addressLine1']/following-sibling::div";
    public static final String ZIP_ERROR_REQUIRED_XPATH = "//input[@id='address.zip']/following-sibling::div";
    public static final String CITY_ERROR_REQUIRED_XPATH = "//input[@id='address.city']/following-sibling::div";
    public static final String STATE_ERROR_REQUIRED_XPATH = "//div[@id='State']";
    public static final String FIRSTNAME_ERROR_REQUIRED_XPATH = "//input[@id='contact.firstName']/following-sibling::div";
    public static final String LASTNAME_REQUIRED_XPATH = "//input[@id='contact.lastName']/following-sibling::div";
    private static final String EDIT_CONTACT = "//a[contains(@class,'column-edit-link')]";
    private static final String ROW_NAME_TYPE = "//tr[td//a[text()='%s'] and td[text()='%s']]";
    private static final String CONTACT_NAME = "//a[contains(@class,'column-edit-link') and text()='%s']";
    public static final String ENTITY_NAMES_COLUMN = "//td[@aria-colindex='4']";
    public static final String ALL_DATA_ROWS_XPATH = "//tbody//tr";
    private static final String BUTTON_IN_FOOTER = "//div[@class='modal-footer']//button[contains(text(),'%s')]";
    private static final String CONTACT_NAMES_COLUMN = "//td[@aria-colindex='2']";
    public static final String SELECT_AND_PROCEED_BTN = "//button[contains(text(),'Select & Proceed')]";
    public static final String CLOSE_BUTTON = "//button[text()='Close']";

    public void tabNavigation(String tab) throws AutomationException, IOException {
        driverUtil.getWebElement(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a", tab)).click();
    }

    public void clickButton(String buttonName) throws AutomationException {
        switch (buttonName) {
            case "Create":
                driverUtil.getWebElement(CREATE_BUTTON).click();
                break;
            case "Create Individual Contact":
                driverUtil.getWebElement(CREATE_INDIVIDUAL_CONTACT_BTN).click();
                break;
            case "Create Entity Contact":
                driverUtil.getWebElement(CREATE_ENTITY_CONTACT_BTN).click();
                break;
            case "Save":
                driverUtil.getWebElement(SAVE_BUTTON).click();
                break;
            case "Radio Button":
                driverUtil.getWebElement(RADIO_BUTTON).click();
                break;
            case "Edit":
                driverUtil.getWebElement(EDIT_BUTTON).click();
                break;
            case "Select & Proceed":
                driverUtil.getWebElement(SELECT_AND_PROCEED_BTN).click();
                break;
            case "Close":
                driverUtil.getWebElement(CLOSE_BUTTON).click();
                break;
            default:
                throw new AutomationException("Unknown button: " + buttonName);
        }
    }

    public void clearFields() throws AutomationException {
        driverUtil.getWebElementAndScroll(LAST_NAME_FIELD_CREATE).clear();
        driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD_CREATE).clear();
    }

    public void globalContactCreation(String action, String contactType) throws AutomationException, IOException, ParseException {
        clickButton(action);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(CREATE_INDIVIDUAL_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(LAST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.lastName").toString() + CommonUtil.currentDateAndTime());
                driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.firstName").toString() + CommonUtil.currentDateAndTime());
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                clickButton("Create Individual Contact");
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString() + CommonUtil.currentDateAndTime());
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                clickButton("Create Entity Contact");
                break;
            default:
                throw new AutomationException("Unknown button: " + " ");
        }
    }

    public void fillGlobalContactDetails(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(MIDDLE_NAME_FIELD, "Create.middleName");
                fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
                fillField(ENTITY_NAME_FIELD_CREATE, "Create.entityName");
                fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
                fillField(PTIN_FIELD, "Create.ptin");
                fillField(PINEFILE_FIELD, "Create.pinEFile");
                fillField(BARID_FIELD, "Create.barId");
                fillField(CAF_FIELD, "Create.caf");
                fillField(ADDRESS_LINE2, "Create.addressLine2");
                fillField(ZIP, "Create.zip");
                fillField(ADDRESS_LINE1, "Create.addressLine1");
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber", actions);
                fillField(WORK_NUMBER_FIELD, "Create.workNumber", actions);
                fillField(FAX_FIELD, "Create.fax", actions);
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillField(ENTITY_NAME_FIELD_CREATE, "Create.entityName");
                fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
                fillField(ADDRESS_LINE1, "Create.addressLine1");
                fillField(ZIP, "Create.zip");
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber", actions);
                fillField(WORK_NUMBER_FIELD, "Create.workNumber", actions);
                fillField(FAX_FIELD, "Create.fax", actions);
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    private void fillField(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        field.clear();
        field.sendKeys(CommonUtil.getJsonPath("Create").get(jsonKey).toString());
    }

    private void fillField(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(CommonUtil.getJsonPath("Create").get(jsonKey).toString())
                .perform();
    }

    private void fillFieldWithRandom(String fieldLocator, String value, Actions actions) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(value)
                .perform();
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
        firstName = filterByName();
        filterByContactType(contactType.replace("Global Contact", "").trim());
        driverUtil.getWebElement(ACTIONS_BUTTON).click();
        //driverUtil.waitForElementToBeClickable(action);
        clickButton(action);
        //driverUtil.waitForLoaderToDisappear();
        switch (contactType) {
            case "Individual Global Contact":
                contactInformationFieldsForEdit("Individual Global Contact");
                clickButton("Save");
                break;
            case "Entity Global Contact":
                contactInformationFieldsForEdit("Entity Global Contact");
                clickButton("Save");
                break;
        }
        CommonSteps.takeScreenshot();
    }

    public void contactInformationFieldsForEdit(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "Individual Global Contact":
            case "Entity Global Contact":
                fillField(FIRST_NAME_FIELD_CREATE, "Edit.firstName");
                fillField(MIDDLE_NAME_FIELD, "Edit.middleName");
                fillField(LAST_NAME_FIELD_CREATE, "Edit.lastName");
                fillField(MAIDEN_NAME_FIELD, "Edit.maidenName");
                fillField(ENTITY_NAME_FIELD_CREATE, "Edit.entityName");
                fillField(EMAIL_ADDRESS_FIELD, "Edit.emailId");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillField(PHONE_NUMBER_FIELD, "Edit.phoneNumber", actions);
                fillField(WORK_NUMBER_FIELD, "Edit.workNumber", actions);
                fillField(FAX_FIELD, "Edit.fax", actions);
                fillField(PTIN_FIELD, "Edit.ptin");
                fillField(PINEFILE_FIELD, "Edit.pinEFile");
                fillField(BARID_FIELD, "Edit.barId");
                fillField(CAF_FIELD, "Edit.caf");
                break;

            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void saveGlobalContact() throws AutomationException, IOException {
        clickButton("Save");
    }

    public void verifyGlobalContactSaved() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact details have been successfully saved."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(CONFIRMATION_MESSAGE));
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyPageElements(String pageElement) throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebElement element = null;
        switch (pageElement) {
            case "Global Contacts":
                element = driverUtil.getWebElement(GLOBAL_CONTACTS_PAGE);
                break;
            case "Home":
                element = driverUtil.getWebElement(HOME_PAGE);
                break;
            case "Global Contact Creation":
                element = driverUtil.getWebElement(GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Individual Global Contact Creation":
                element = driverUtil.getWebElement(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Entity Global Contact Creation":
                element = driverUtil.getWebElement(ENTITY_GLOBAL_CONTACT_CREATION_PAGE);
                break;
            case "Contact (Select or Create New)":
                element = driverUtil.getWebElement(SELECT_OR_CREATENEW_PAGE);
                break;
            default:
                throw new AutomationException("Unknown page: " + element);
        }
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException(String.format("%s Page is not displayed or it takes time to load", pageElement));
        }
    }

    public void enterSSNAndEIN(String ssn, String ein) throws AutomationException {
        String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
        String einPattern = "^\\d{2}-\\d{7}$";
        if (!ssn.matches(ssnPattern)) {
            throw new AutomationException("Invalid SSN format. Expected format: 000-00-0000. Provided: " + ssn);
        }
        if (!ein.matches(einPattern)) {
            throw new AutomationException("Invalid EIN format. Expected format: 00-0000000. Provided: " + ein);
        }
        WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
        SSNField.clear();
        SSNField.sendKeys(ssn);
        WebElement EINField = driverUtil.getWebElementAndScroll(EIN_FIELD);
        EINField.clear();
        EINField.sendKeys(ein);
    }

    public boolean isUserOnEditContactPage() {
        String currentUrl = driverUtil.getCurrentUrl();
        String expectedUrlPattern = "/law-firm/global-contacts/person/edit/";
        return currentUrl != null && currentUrl.contains(expectedUrlPattern);
    }

    public boolean navigateToFirstPage() throws AutomationException {
        try {
            WebElement firstPageButton = driverUtil.getWebElement(FIRST_PAGE_BUTTON, 5);
            String isDisabled = firstPageButton.getAttribute("aria-disabled");
            if ("true".equalsIgnoreCase(isDisabled)) {
                return true;
            }
            firstPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
            return false;
        } catch (Exception e) {
            throw new AutomationException("Failed to verify or navigate to the first page.");
        }
    }

    public void clickNameWithType(String name, String type) throws AutomationException {
        String rowXPath = String.format(ROW_NAME_TYPE, name, type);
        String linkXPath = rowXPath + EDIT_CONTACT;
        while (true) {
            WebElement linkElement = driverUtil.getWebElementAndScroll(linkXPath);
            if (linkElement != null) {
                linkElement.click();
                WebDriverUtil.waitForElementNotVisible(60, SPINNER);
                CommonSteps.logInfo("User clicked on the Name: '" + name + "' with Contact Type: '" + type + "'.");
                return;
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null) {
                throw new AutomationException("Unable to find a contact with Name: '" + name + "' and Type: '" + type + "' after checking all pages.");
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }
    }

    public void enterFirstnameAndLastNameFields(String firstName, String lastName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.sendKeys(lastName);
    }

    public void verifyfirstNamelastNameFieldPrefilled() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);

        String actualFirstName = driverUtil.getWebElement(FIRST_NAME_FIELD_CREATE).getAttribute("value");
        String actualLastName = driverUtil.getWebElement(LAST_NAME_FIELD_CREATE).getAttribute("value");

        if (actualFirstName == null || !actualFirstName.equals(firstName) || actualLastName == null || !actualLastName.equals(lastName)) {
            throw new AutomationException("First Name or Last Name are not prefilled correctly.");
        }
        CommonSteps.logInfo("First Name and Last Name are prefilled correctly.");
    }


    public void verifyentityNameFieldPrefilled() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        String actualEntityName = driverUtil.getWebElement(ENTITY_NAME_FIELD_CREATE).getAttribute("value");
        if (actualEntityName == null || !actualEntityName.equals(entityName)) {
            throw new AutomationException("Entity Name is not prefilled correctly.");
        }
        CommonSteps.logInfo("Entity Name is prefilled correctly.");
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

    public void verifyUserType(String userType) throws AutomationException {
        switch (userType) {
            case "Licensed":
            case "Reviewer":
                waitForVisibleElement(By.xpath(CREATE_BUTTON), 3);
                waitForElementClickable(By.xpath(CREATE_BUTTON), 3);
                if (driverUtil.getWebElement(CREATE_BUTTON).isDisplayed() && driverUtil.getWebElement(CREATE_BUTTON).isEnabled()) {
                    clickButton("Create");
                    CommonSteps.logInfo(userType + " user: Create button is Visible & Clickable and indicating eligibility to create Global Contacts.");
                } else {
                    throw new AutomationException(userType + " user: Create button is not visible or clickable within the timeout.");
                }
                break;
            case "View Only":
                waitForInvisibleElement(By.xpath(CREATE_BUTTON), 3);
                WebElement createButton = driverUtil.getWebElement(CREATE_BUTTON);
                if (createButton != null && createButton.isDisplayed()) {
                    throw new AutomationException("Create button should not be visible for View Only user.");
                } else {
                    CommonSteps.logInfo("View Only user: Create button is not visible as expected.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    public void verifyDuplicateEINError() throws AutomationException {
//            WebDriverUtil.waitForVisibleElement(By.xpath(EIN_DUPLICATION_ERROR));
        WebElement errorMessage = driverUtil.getWebElement(EIN_ERROR);
        CommonSteps.takeScreenshot();
        if (errorMessage == null || !errorMessage.isDisplayed()) {
            throw new AutomationException("EIN error message is not displayed.");
        } else {
            String ErrorMessage = errorMessage.getText().trim();
            CommonSteps.logInfo("Error message display for duplicate EIN as expected  : " + ErrorMessage);
        }
    }

    public void verifyRequiredFieldValidationErrors() throws AutomationException {
        if (!driverUtil.getWebElement(ADDRESS_LINE1_ERROR_REQUIRED_XPATH).isDisplayed() ||
                !driverUtil.getWebElement(ZIP_ERROR_REQUIRED_XPATH).isDisplayed() ||
                !driverUtil.getWebElement(CITY_ERROR_REQUIRED_XPATH).isDisplayed() ||
                !driverUtil.getWebElement(STATE_ERROR_REQUIRED_XPATH).isDisplayed()) {
            throw new AutomationException("Error messages are not displayed.");
        }
        CommonSteps.logInfo("Error messages are displayed.");
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
                    throw new AutomationException("All the required fields are not filled");
                }
                break;
            case "Entity Global Contact":
                break;
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

    public void isButtonEnabled(String buttonName) throws AutomationException {
        String buttonXpath = String.format("//button[contains(text(), '%s')]", buttonName);
        WebElement button = driverUtil.getWebElement(buttonXpath);
        if (button != null && button.isEnabled()) {
            CommonSteps.logInfo(buttonName + " button is enable as expected");
        } else {
            throw new AutomationException(buttonName + " button is disable");
        }
    }

    public boolean isContactNameUpdated(String expectedFullName) throws AutomationException {
        String contactNameXPath = String.format(CONTACT_NAME, expectedFullName);
        while (true) {
            WebElement contactElement = driverUtil.getWebElementAndScroll(contactNameXPath);
            if (contactElement != null) {
                return true;
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null) {
                return false;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }
    }

    public void enterSSN(String ssn) throws AutomationException {
        String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
        if (!ssn.matches(ssnPattern)) {
            throw new AutomationException("Invalid SSN format. Expected format: 000-00-0000. Provided: " + ssn);
        }
        WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
        SSNField.clear();
        SSNField.sendKeys(ssn);
    }

    public boolean enterEntityName(String entityName) {
        try {
            WebElement entityNameField = driverUtil.getWebElement(ENTITY_NAME_FIELD, 5);
            entityNameField.clear();
            entityNameField.sendKeys(entityName);
            String enteredValue = entityNameField.getAttribute("value");
            return entityName.equals(enteredValue);
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getAllDisplayedEntityNames() throws AutomationException {
        List<String> entityNames = new ArrayList<>();
        while (true) {
            List<WebElement> nameElements = driverUtil.getWebElements(ENTITY_NAMES_COLUMN);
            for (WebElement nameElement : nameElements) {
                String nameText = nameElement.getText().trim();
                if (!nameText.isEmpty()) {
                    entityNames.add(nameText);
                }
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }
        return entityNames;
    }

    public List<String> verifyClassForContactType(String contactType) throws AutomationException {
        List<String> mismatchedRows = new ArrayList<>();
        String expectedClass = contactType.equalsIgnoreCase("Entity") ? "entity-row-color" : "";
        while (true) {
            List<WebElement> rows = driverUtil.getWebElements(ALL_DATA_ROWS_XPATH);
            for (WebElement row : rows) {
                String actualClass = row.getAttribute("class");
                if (!actualClass.contains(expectedClass)) {
                    String rowDetails = row.getText();
                    mismatchedRows.add(rowDetails);
                }
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }

        return mismatchedRows;
    }


    public boolean verifyRadioButtonsForContacts() throws AutomationException {
        while (true) {
            List<WebElement> radioButtons = driverUtil.getWebElements(RADIO_BUTTON);
            List<WebElement> rows = driverUtil.getWebElements(ALL_DATA_ROWS_XPATH);
            if (radioButtons.size() != rows.size()) {
                return false;
            }
            WebElement nextPageButton = driverUtil.getWebElement("//button[@title='Go to the next page']", 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }
        return true;
    }

    public void isButtonAvailable(String buttonName) throws AutomationException {
        String buttonXPath = String.format(BUTTON_IN_FOOTER, buttonName);
        WebElement buttonElement = driverUtil.getWebElement(buttonXPath, 2);
        if (buttonElement != null && buttonElement.isDisplayed() && buttonElement.isEnabled()) {
            CommonSteps.logInfo(buttonName + " button is display");
        } else {
            throw new AutomationException(buttonName + " button is not display");
        }
    }

    public List<String> getAllDisplayedContactNames() throws AutomationException {
        List<String> contactNames = new ArrayList<>();
        while (true) {
            List<WebElement> contactNameElements = driverUtil.getWebElements(CONTACT_NAMES_COLUMN);
            for (WebElement nameElement : contactNameElements) {
                String nameText = nameElement.getText().trim();
                if (!nameText.isEmpty()) {
                    contactNames.add(nameText);
                }
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        }
        return contactNames;
    }

    public void enterExistedEIN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        WebElement EINField = driverUtil.getWebElement(EIN_FIELD);
        String baseEIN = CommonUtil.getJsonPath("Create").get("Create.ein").toString();
        actions.moveToElement(EINField).click().sendKeys(baseEIN).build().perform();
    }
}






package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.sixinone.automation.util.WebDriverUtil.*;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;


public class GlobalContactPage extends BasePage {

    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[contains(@name,'firstName')]";
    private static final String LAST_NAME_FIELD = "//input[contains(@name,'lastName')]";
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
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    public static final String CREATE_ENTITY_CONTACT_BTN = "//button[text()='Create Entity Contact']";
    private static final String ADDRESS_LINE1 = "//input[@name='address.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='address.addressLine2']";
    private static final String ZIP = "//input[@name='address.zip']";
    private static final String CITY = "//input[@id='address.city']";
    private static final String STATE = "//label[contains(text(),'State')]//following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String COUNTRY = "//input[@id='address.county']";
    public static final String GLOBAL_CONTACTS_PAGE = "//a[text()='Global Contacts']";
    public static final String HOME_PAGE = "//a[text()='Estate']";
    public static final String GLOBAL_CONTACT_CREATION_PAGE = "//a[text()='New']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[@class='section-header mt-0' and following-sibling::div//input[@id='contact.firstName']]";
    public static final String SELECT_OR_CREATENEW_PAGE = "//div[text()='Contact (Select or Create New)']";
    private static final String NEXT_PAGE = "//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String SELECTED_OPTION = "//div[contains(@class,'select__single-value')]";
    public static final String ENTITY_NAMES_COLUMN = "//td[@aria-colindex='4']";
    public static final String ALL_DATA_ROWS_XPATH = "//tbody//tr";
    public static final String RADIO_BUTTONS_XPATH = "//td//div//input[@type='radio']";
    private static final String BUTTON_IN_FOOTER = "//div[@class='modal-footer']//button[contains(text(),'%s')]";
    private static final String CONTACT_NAMES_COLUMN = "//td[@aria-colindex='2']";
    public static final String RADIO_BUTTON = "//input[@type='radio']";
    public static final String EDIT_BUTTON = "//a[text()='Edit']";
    public static final String SELECT_AND_PROCEED_BTN ="//button[contains(text(),'Select & Proceed')]";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='3'] //input[@aria-label='Filter']";
    public static final String CONTACT_TYPE_FILTER_INPUT_RECORDS = "//th[@aria-colindex='5'] //input[@aria-label='Filter']";
    public static final String ACTIONS_BUTTON = "//td[@class='action-column text-center']//button[@class='dropdown-toggle btn btn-primary']";
    public static final String CONTACT_NAME_XPATH = "(//a[contains(@class,'column-edit-link')][text()='%s, %s'])[1]";
    public static final String ENTITY_NAME_FIELD = "//input[@name='entityName']";
    public static final String EIN_ERROR = "//div[contains(text(),'EIN')]";
    public static String firstName;
    public static String lastName;
    public static String entityName;
    public static final String REQUIRED_FIELD_ERROR_XPATH = "//input[contains(@aria-required,'true')]/following-sibling::div";
    public static final String ADDRESS_LINE1_ERROR_REQUIRED_XPATH = "//input[@id='address.addressLine1']/following-sibling::div";
    public static final String ZIP_ERROR_REQUIRED_XPATH = "//input[@id='address.zip']/following-sibling::div";
    public static final String CITY_ERROR_REQUIRED_XPATH = "//input[@id='address.city']/following-sibling::div";
    public static final String STATE_ERROR_REQUIRED_XPATH = "//div[@id='State']";
    public static final String CLOSE_BUTTON = "//button[text()='Close']";

    public void tabNavigation(String tab) throws AutomationException, IOException {
        driverUtil.getWebElement(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a", tab)).click();
    }

    public void clickButtonCreate() throws AutomationException {
        driverUtil.getWebElement(CREATE_BUTTON).click();
    }

    public void clickButtonCreateIndividualContact() throws AutomationException {
        waitForAWhile(1);
        driverUtil.getWebElement(CREATE_INDIVIDUAL_CONTACT_BTN).click();
    }

    public void clickButtonCreateEntityContact() throws AutomationException {
        driverUtil.getWebElement(CREATE_ENTITY_CONTACT_BTN).click();
    }

    public void clickButtonSave() throws AutomationException {
        driverUtil.getWebElement(SAVE_BUTTON).click();
    }

    public void clickButtonRadioButton() throws AutomationException {
        driverUtil.getWebElement(RADIO_BUTTON).click();
    }

    public void clickButtonEdit() throws AutomationException {
        driverUtil.getWebElement(EDIT_BUTTON).click();
    }

    public void clickButtonSelectAndProceed() throws AutomationException {
        driverUtil.getWebElement(SELECT_AND_PROCEED_BTN).click();
    }

    public void clickButtonClose() throws AutomationException {
        driverUtil.getWebElement(CLOSE_BUTTON).click();
    }

    public void clearFields() throws AutomationException {
        driverUtil.getWebElementAndScroll(LAST_NAME_FIELD_CREATE).clear();
        driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD_CREATE).clear();
    }

    public void globalContactCreation(String contactType) throws AutomationException, IOException, ParseException {
        //clickButton(action);
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(CREATE_INDIVIDUAL_CONTACT_BTN));
                driverUtil.getWebElement(LAST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.lastName").toString() + CommonUtil.currentDateAndTime());
                driverUtil.getWebElement(FIRST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.firstName").toString() + CommonUtil.currentDateAndTime());
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                clickButtonCreateIndividualContact();
                break;
            case "Entity Global Contact":
                clickButtonCreate();
                waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString() + CommonUtil.currentDateAndTime());
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                clickButtonCreateEntityContact();
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
                selectSuffixOption();
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
                selectSuffixOption();
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

    public void fillGlobalContactDetailsWithSpaces(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                fillFieldWithSpaces(MIDDLE_NAME_FIELD, "CreateWithSpaces.middleName");
                fillFieldWithSpaces(MAIDEN_NAME_FIELD, "CreateWithSpaces.maidenName");
                selectSuffixOption();
                fillFieldWithSpaces(ENTITY_NAME_FIELD_CREATE, "CreateWithSpaces.entityName");
                fillFieldWithSpaces(EMAIL_ADDRESS_FIELD, "CreateWithSpaces.emailId");
                fillFieldWithSpaces(PTIN_FIELD, "CreateWithSpaces.ptin");
                fillFieldWithSpaces(PINEFILE_FIELD, "CreateWithSpaces.pinEFile");
                fillFieldWithSpaces(BARID_FIELD, "CreateWithSpaces.barId");
                fillFieldWithSpaces(CAF_FIELD, "CreateWithSpaces.caf");
                fillFieldWithSpaces(ADDRESS_LINE2, "CreateWithSpaces.addressLine2");
                fillFieldWithSpaces(ZIP, "CreateWithSpaces.zip");
                fillFieldWithSpaces(ADDRESS_LINE1, "CreateWithSpaces.addressLine1");
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillFieldWithSpaces(PHONE_NUMBER_FIELD, "CreateWithSpaces.phoneNumber", actions);
                fillFieldWithSpaces(WORK_NUMBER_FIELD, "CreateWithSpaces.workNumber", actions);
                fillFieldWithSpaces(FAX_FIELD, "CreateWithSpaces.fax", actions);
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillFieldWithSpaces(ENTITY_NAME_FIELD_CREATE, "CreateWithSpaces.entityName");
                selectSuffixOption();
                fillFieldWithSpaces(EMAIL_ADDRESS_FIELD, "CreateWithSpaces.emailId");
                fillFieldWithSpaces(ADDRESS_LINE1, "CreateWithSpaces.addressLine1");
                fillFieldWithSpaces(ZIP, "CreateWithSpaces.zip");
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillFieldWithSpaces(PHONE_NUMBER_FIELD, "CreateWithSpaces.phoneNumber", actions);
                fillFieldWithSpaces(WORK_NUMBER_FIELD, "CreateWithSpaces.workNumber", actions);
                fillFieldWithSpaces(FAX_FIELD, "CreateWithSpaces.fax", actions);
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
                .build()
                .perform();
    }

    private void fillFieldWithSpaces(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        field.clear();
        field.sendKeys(CommonUtil.getJsonPath("CreateWithSpaces").get(jsonKey).toString().trim());
    }

    private void fillFieldWithSpaces(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(CommonUtil.getJsonPath("CreateWithSpaces").get(jsonKey).toString().trim())
                .build()
                .perform();
    }

    private void fillFieldWithRandom(String fieldLocator, String value, Actions actions) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(value)
                .build()
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

    public String filterByContactTypeOnRecords(String contactType) throws AutomationException {
        waitForVisibleElement(By.xpath(CONTACT_TYPE_FILTER_INPUT_RECORDS));
        driverUtil.getWebElement(CONTACT_TYPE_FILTER_INPUT_RECORDS).click();
        driverUtil.getWebElement(CONTACT_TYPE_FILTER_INPUT_RECORDS).sendKeys(contactType);
        return contactType;
    }

    public void globalContactEdit(String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        firstName = filterByName();
        filterByContactType(contactType.replace("Global Contact", "").trim());
        driverUtil.getWebElement(ACTIONS_BUTTON).click();
        //driverUtil.waitForElementToBeClickable(action);
        clickButtonEdit();
        //driverUtil.waitForLoaderToDisappear();
        switch (contactType) {
            case "Individual Global Contact":
                contactInformationFieldsForEdit("Individual Global Contact");
                clickButtonSave();
                break;
            case "Entity Global Contact":
                contactInformationFieldsForEdit("Entity Global Contact");
                clickButtonSave();
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
                selectSuffixOption();
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
        clickButtonSave();
    }

    public void verifyGlobalContactSaved() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact details have been successfully saved."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact details have been successfully saved.")));
        //WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyPageGlobalContacts() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement element = driverUtil.getWebElement(GLOBAL_CONTACTS_PAGE);
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
    }

    public void verifyPageHome() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement element = driverUtil.getWebElement(HOME_PAGE);
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
    }

    public void verifyPageGlobalContactCreation() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement element = driverUtil.getWebElement(GLOBAL_CONTACT_CREATION_PAGE);
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
    }

    public void verifyPageIndividualGlobalContactCreation() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement element = driverUtil.getWebElement(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE);
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
    }

    public void enterDataInZipField() throws AutomationException, IOException, ParseException {
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(ZIP).sendKeys(CommonUtil.getJsonPath("Create").get("Create.zip").toString() + Keys.ENTER);
        waitForAWhile();
    }

    public void enterFirstnameAndLastNameFields(String firstName, String lastName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.sendKeys(lastName);
    }

    public void verifyfirstNamelastNameFieldPrefilled() throws AutomationException {
        String actualFirstName = driverUtil.getWebElement(FIRST_NAME_FIELD_CREATE).getAttribute("value");
        String actualLastName = driverUtil.getWebElement(LAST_NAME_FIELD_CREATE).getAttribute("value");

        if (actualFirstName == null || !actualFirstName.equals(firstName) || actualLastName == null || !actualLastName.equals(lastName)) {
            throw new AutomationException("First Name or Last Name are not prefilled correctly.");
        }
        CommonSteps.logInfo("First Name and Last Name are prefilled correctly.");
    }

    public void verifyentityNameFieldPrefilled() throws AutomationException {
        String actualEntityName = driverUtil.getWebElement(ENTITY_NAME_FIELD_CREATE).getAttribute("value");
        if (actualEntityName == null || !actualEntityName.equals(entityName)) {
            throw new AutomationException("Entity Name is not prefilled correctly.");
        }
        CommonSteps.logInfo("Entity Name is prefilled correctly.");
    }

    public void verifyAutoFetchedFields() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("Create").get("Create.city").toString();
        String expectedState = CommonUtil.getJsonPath("Create").get("Create.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("Create").get("Create.country").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(CITY, "value");
        String actualState = getFieldValue(STATE, "text");
        String actualCounty = getFieldValue(COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
        CommonSteps.logInfo("Verified auto-fetched values successfully ");
    }

    private String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    public void globalContactCreationWithSpaces(String contactType) throws AutomationException, IOException, ParseException {
        switch (contactType) {
            case "Individual Global Contact":
                //waitForVisibleElement(By.xpath(CREATE_INDIVIDUAL_CONTACT_BTN));
                String firstNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.firstName").toString() + CommonUtil.currentDateAndTime();
                String lastNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.lastName").toString() + CommonUtil.currentDateAndTime();
                WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys("  " + lastNameWithSpaces + "    ");
                firstNameField.clear();
                firstNameField.sendKeys("  " + firstNameWithSpaces + "    ");
                CommonSteps.takeScreenshot();
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value").trim();
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value").trim();
                clickButtonCreateIndividualContact();
                break;
            case "Entity Global Contact":
                //waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                String entityNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.entityName").toString();
                WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
                entityNameField.clear();
                entityNameField.sendKeys("    " + entityNameWithSpaces + "    ");
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value").trim();
                clickButtonCreateEntityContact();
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void verifyPageContactSelectorCreateNew() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement element = driverUtil.getWebElement(SELECT_OR_CREATENEW_PAGE);
        if (element != null) {
            waitForVisibleElement(element);
        } else {
            throw new AutomationException("Page is not displayed or it takes time to load");
        }
    }

    public void globalContactEditWithSpaces(String contactType) throws AutomationException, IOException, ParseException, InterruptedException {
        firstName = filterByName();
        filterByContactType(contactType.replace("Global Contact", "").trim());
        driverUtil.getWebElement(ACTIONS_BUTTON).click();
        clickButtonEdit();
        WebDriverUtil.waitForAWhile(1);
        switch (contactType) {
            case "Individual Global Contact":
                String updatedFirstName = CommonUtil.getJsonPath("EditWithSpaces").get("EditWithSpaces.firstName").toString();
                String updatedLastName = CommonUtil.getJsonPath("EditWithSpaces").get("EditWithSpaces.lastName").toString();
                WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys(updatedLastName);
                firstNameField.clear();
                firstNameField.sendKeys(updatedFirstName);
                CommonSteps.takeScreenshot();
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value").trim();
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value").trim();
                clickButtonSave();
                break;
            case "Entity Global Contact":
                String updatedEntityName = CommonUtil.getJsonPath("EditWithSpaces").get("EditWithSpaces.entityName").toString();
                WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
                entityNameField.clear();
                entityNameField.sendKeys(updatedEntityName);
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value").trim();
                clickButtonSave();
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void selectSuffixOption() throws AutomationException, IOException, ParseException {
        String suffixValue = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        WebElement suffixDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Suffix"));
        suffixDropdown.click();
        WebElement suffixOption = driverUtil.getWebElement(String.format(SELECT_OPTION, suffixValue));
        suffixOption.click();
    }

    public void verifyMatchingRecordsDisplayed() throws AutomationException, IOException, ParseException {
        waitForInvisibleElement(By.xpath(SPINNER));
        String expectedEntityName = CommonUtil.getJsonPath("GlobalContactVerification").get("GlobalContactVerification.entityName").toString();
        List<String> displayedEntityNames = getAllDisplayedEntityNames();
        boolean allMatch = displayedEntityNames.stream()
                .allMatch(name -> name.toLowerCase().contains(expectedEntityName.toLowerCase()));
        if (!allMatch) {
            throw new AutomationException("Not all displayed entity names match the provided name: '" + expectedEntityName + "'. Displayed names: " + displayedEntityNames);
        }
        CommonSteps.logInfo("All displayed entity names match the expected name: '" + expectedEntityName + "'.");
    }

    public void verifyBackgroundColorForContactType(String contactType) throws AutomationException, IOException, ParseException {
        filterByContactTypeOnRecords(contactType.replace("Global Contact", "").trim());
        String expectedClass = contactType.equalsIgnoreCase("Entity") ? "entity-row-color" : "";
        List<String> mismatchedRows = new ArrayList<>();
        do {
            waitForVisibleElement(By.xpath(ALL_DATA_ROWS_XPATH));
            List<WebElement> rows = driverUtil.getWebElements(ALL_DATA_ROWS_XPATH);
            rows.stream()
                    .filter(row -> !row.getAttribute("class").contains(expectedClass))
                    .forEach(row -> mismatchedRows.add(row.getText()));
        } while (clickNextPage());

        if (!mismatchedRows.isEmpty()) {
            throw new AutomationException("The background color does not match for Entity Global Contact: " + mismatchedRows);
        }
        CommonSteps.logInfo("Verified background color for all rows with contact type: '" + contactType + "'.");
    }


    private boolean clickNextPage() throws AutomationException {
        WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
        if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
            return false;
        }
        nextPageButton.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        return true;
    }

    public void verifyRadioButtonsForContacts() throws AutomationException {
        while (true) {
            List<WebElement> radioButtons = driverUtil.getWebElements(RADIO_BUTTONS_XPATH);
            List<WebElement> rows = driverUtil.getWebElements(ALL_DATA_ROWS_XPATH);
            if (radioButtons.size() != rows.size()) {
                throw new AutomationException("Mismatch in the number of radio buttons and data rows.");
            }
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            waitForInvisibleElement(By.xpath(SPINNER));

        }
        CommonSteps.logInfo("Verified that radio buttons are available for all the contacts.");
    }

    public void verifyMatchingRecordsForIndividualGlobalContact() throws AutomationException, IOException, ParseException {
        String lastName = CommonUtil.getJsonPath("GlobalContactVerification").get("GlobalContactVerification.lastName").toString();
        String firstName = CommonUtil.getJsonPath("GlobalContactVerification").get("GlobalContactVerification.firstName").toString();
        String expectedContactName = lastName + ", " + firstName;
        List<String> displayedContactNames = getAllDisplayedContactNames();
        boolean allMatch = displayedContactNames.stream()
                .allMatch(name -> name.equalsIgnoreCase(expectedContactName));
        if (!allMatch) {
            throw new AutomationException("Not all displayed contact names match the expected name'");
        }
        CommonSteps.logInfo("Verified all displayed contact names match the expected name: '" + expectedContactName + "'.");
    }

    @Override
    String getName() {
        return "";
    }

    public void verifyUserType(String userType) throws AutomationException {
        switch (userType) {
            case "Licensed":
                waitForVisibleElement(By.xpath(CREATE_BUTTON));
                if (driverUtil.getWebElement(CREATE_BUTTON).isDisplayed() && driverUtil.getWebElement(CREATE_BUTTON).isEnabled()) {
                    //clickButton("Create");
                    CommonSteps.logInfo(userType + " user: Create button is Visible & Clickable and indicating eligibility to create Global Contacts.");
                } else {
                    throw new AutomationException(userType + " user: Create button is not visible or clickable within the timeout.");
                }
                break;
            case "View Only":
                //waitForInvisibleElement(By.xpath(CREATE_BUTTON), 2);
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
        WebDriverUtil.waitForVisibleElement(By.xpath(EIN_ERROR));
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

            if (nextPageButton == null) {
                CommonSteps.logInfo("Next page button is not found. Exiting pagination.");
                break;
            }

            String isDisabled = nextPageButton.getAttribute("aria-disabled");
            if ("true".equals(isDisabled)) {
                CommonSteps.logInfo("Next page button is disabled. Reached the last page.");
                break;
            }

            nextPageButton.click();
            WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        }
        return entityNames;
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
            WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        }
        return contactNames;
    }

    public void enterExistedEIN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        WebElement EINField = driverUtil.getWebElement(EIN_FIELD);
        String baseEIN = CommonUtil.getJsonPath("Create").get("Create.ein").toString();
        actions.moveToElement(EINField).click().sendKeys(baseEIN).build().perform();
    }

    public void clickButtonInFooter(String btn) throws AutomationException {
        WebElement FooterBtn = driverUtil.getWebElement(String.format(BUTTON_IN_FOOTER, btn));
        if (FooterBtn == null) {
            throw new AutomationException("Button is not found.");
        }
        FooterBtn.click();
    }

    public void validateSSNAndEINFormat() throws AutomationException {

        String SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD).getAttribute("value");
        String EINField = driverUtil.getWebElement(EIN_FIELD).getAttribute("value");
        String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
        String einPattern = "^\\d{2}-\\d{7}$";
        if (!SSNField.matches(ssnPattern)) {
            throw new AutomationException("Invalid SSN format. Expected format: 000-00-0000. Provided: " + SSNField);
        }
        if (!EINField.matches(einPattern)) {
            throw new AutomationException("Invalid EIN format. Expected format: 00-0000000. Provided: " + EINField);
        }
        CommonSteps.logInfo("Correct SSN and EIN format provided");
    }

    public void enterAddressLine1() throws IOException, ParseException, AutomationException {
        String addressLine1 = CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString();
        WebElement AddressLine1Field = driverUtil.getWebElement(ADDRESS_LINE1);
        if (AddressLine1Field != null) {
            AddressLine1Field.clear();
            AddressLine1Field.sendKeys(addressLine1);
            WebDriverUtil.waitForAWhile(2);
            CommonSteps.logInfo("Entered value in Address Line 1 field: " + addressLine1);
        } else {
            throw new AutomationException("Failed to locate the Address Line 1 field.");
        }
    }

    public void clickOn(String button) throws AutomationException {
        String buttonXPath = String.format(BUTTON_IN_FOOTER, button);
        driverUtil.getWebElement(buttonXPath).click();
    }
}






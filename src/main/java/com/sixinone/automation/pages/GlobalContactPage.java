package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.WebElement;
import java.io.IOException;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.sixinone.automation.util.WebDriverUtil.*;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;


public class GlobalContactPage extends BasePage {

    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[contains(@name,'firstName')]";
    private static final String LAST_NAME_FIELD = "//input[contains(@name,'lastName')]";
    private static final String CONTACT_NAME = "//td//a[text()='%s']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@id='contact.maidenName']";
    private static final String LAST_NAME_FIELD_CREATE = "//input[@id='contact.lastName']";
    private static final String FIRST_NAME_FIELD_CREATE = "//input[@id='contact.firstName']";
    private static final String ENTITY_NAME_FIELD_CREATE = "//input[@name='contact.entityName']";
    private static final String ENTITY_NAME_FIELD_FILLED = "//input[@id='contact.entityName']";
    private static final String SSN_FIELD = "//input[@name='contact.ssn']";
    private static final String EIN_FIELD = "//input[@name='contact.ein']";
    private static final String ENTITY_EIN_FIELD = "//input[@id='contact.ein']";
    public static final String SAVE_BUTTON = "//button[text()='Save']";
    public static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    public static final String CREATE_ENTITY_CONTACT_BTN = "//button[text()='Create Entity Contact']";
    private static final String ADDRESS_LINE1 = "//input[@name='addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='addressLine2']";
    private static final String ZIP = "//input[@name='zip']";
    private static final String CITY = "//input[@id='city']";
    private static final String STATE = "//label[contains(text(),'State')]//following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String COUNTY = "//input[@id='county']";
    private static final String ADDRESS_COMMENTS = "//textarea[@name='comments']";
    public static final String GLOBAL_CONTACTS_PAGE = "//a[text()='Global Contacts']";
    public static final String HOME_PAGE = "//a[text()='Estate']";
    public static final String GLOBAL_CONTACT_CREATION_PAGE = "//a[text()='New']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Entity Details']";
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
    private static final String SELECTED_SUFFIX = "//div[contains(@class,'select__single-value')]";
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
    public static final String ENTITY_NAME_FIELD = "//input[@name='entityName']";
    public static final String EIN_ERROR = "//div[contains(text(),'EIN')]";
    public static final String REQUIRED_FIELD_ERROR_XPATH = "//input[contains(@aria-required,'true')]/following-sibling::div";
    public static final String STATE_ERROR_REQUIRED_XPATH = "//div[@id='State']";
    public static final String CLOSE_BUTTON = "//button[text()='Close']";
    public static final String NEXT_BUTTON = "//button[text()='Next']";
    public static final String MANAGE_ADDRESS_BTN = "//button[text()='Manage Addresses']";
    public static final String ADD_NEW_ADDRESS_BTN = "//div[@class='modal-content']//button[text()='Add New Address']";
    public static final String CLOSE_ADDRESS_BAR = "//button[@aria-label='Close']";
    public static final String EDIT_ADDRESS = "//button[text()='Edit']";
    public static final String ADDRESS_IN_LIST_MODAL = "//div[@class='modal-body']//tbody//tr//td[contains(text(),'%s')]";
    public static final String ADDRESS_COLUMN_MODAL = "//div[@class='modal-body']//table//tr/th[contains(text(), 'Address')]/ancestor::table//tbody//tr//td[position() = count(//tr/th[contains(text(), 'Address')]/preceding-sibling::th) + 1]";
    public static final String ADDRESS_ROW_ON_EDIT_PAGE = "//ol[@class='mb-2 list-group list-group-numbered']//div[@class='font14 list-group-item']";

    public static String firstName;
    public static String lastName;
    public static String entityName;
    List<String> modalAddresses;
    static String enteredEIN;
    static String enteredSSN;
    static String enteredEntityEIN;
    static String enteredEntitySSN;


    public static void tabNavigation(String tab) throws AutomationException, IOException {
        scrollPageUp();
        driverUtil.getWebElement(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a", tab)).click();
    }

    public void clickButtonCreate() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CREATE_BUTTON).click();
    }

    public void clickButtonCreateIndividualContact() throws AutomationException {
        waitForAWhile(1);
        driverUtil.getWebElement(CREATE_INDIVIDUAL_CONTACT_BTN).click();
    }

    public void clickButtonCreateEntityContact() throws AutomationException {
        driverUtil.getWebElement(CREATE_ENTITY_CONTACT_BTN).click();
    }

    public static void clickButtonSave() throws AutomationException {
        driverUtil.getWebElement(SAVE_BUTTON).click();
    }

    public static void clickButtonNext() throws AutomationException {
        driverUtil.getWebElement(NEXT_BUTTON).click();
    }

    public void clickButtonRadioButton() throws AutomationException {
        driverUtil.getWebElement(RADIO_BUTTON).click();
    }

    public void clickButtonEdit() throws AutomationException {
        driverUtil.getWebElement(EDIT_BUTTON).click();
    }

    public static void clickButtonClose() throws AutomationException {
        driverUtil.getWebElement(CLOSE_BUTTON).click();
    }

    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void clearNameFields() throws AutomationException {
        clearField(LAST_NAME_FIELD_CREATE);
        clearField(FIRST_NAME_FIELD_CREATE);
    }

    public void globalContactCreation(String contactType) throws AutomationException, IOException, ParseException {
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
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                driverUtil.getWebElement(FIRST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.firstName").toString() + CommonUtil.currentDateAndTime());
                fillField(MIDDLE_NAME_FIELD, "Create.middleName");
                driverUtil.getWebElement(LAST_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.lastName").toString() + CommonUtil.currentDateAndTime());
                selectSuffixOption();
                fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillFieldWithRandom(ENTITY_EIN_FIELD, randomEIN, actions);
                enteredEntityEIN = driverUtil.getWebElement(ENTITY_EIN_FIELD).getAttribute("value");
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void fillGlobalContactDetailsWithSpaces(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(MIDDLE_NAME_FIELD, "CreateWithSpaces.middleName");
                selectSuffixOption();
                fillField(MAIDEN_NAME_FIELD, "CreateWithSpaces.maidenName");
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillFieldWithRandom(ENTITY_EIN_FIELD, randomEIN, actions);
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

    private void fillFieldWithKeyStrokes(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        String value = CommonUtil.getJsonPath("Create").get(jsonKey).toString();
        for (char c : value.toCharArray()) {
            field.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }

    private void fillFieldWithKeyStrokes(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        String value = CommonUtil.getJsonPath("Create").get(jsonKey).toString();
        actions.moveToElement(field).click().build().perform();
        for (char c : value.toCharArray()) {
            actions.sendKeys(String.valueOf(c)).build().perform();
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }

    private void fillFieldWithRandomWithKeyStrokes(String fieldLocator, String value, Actions actions) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field).click().build().perform();
        for (char c : value.toCharArray()) {
            actions.sendKeys(String.valueOf(c)).build().perform();
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }

    private void fillFieldWithRandom(String fieldLocator, String value, Actions actions) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(value)
                .build()
                .perform();
    }

    public static String filterByName() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        clearField(NAME_FILTER_INPUT);
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(firstName);
        return firstName;
    }

    public static String filterByEntity() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        clearField(NAME_FILTER_INPUT);
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(entityName);
        return entityName;
    }

    public String filterByContactType(String contactType) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(CONTACT_TYPE_FILTER_INPUT));
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
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ACTIONS_BUTTON));
        driverUtil.getWebElement(ACTIONS_BUTTON).click();
        clickButtonEdit();
        switch (contactType) {
            case "Individual Global Contact":
                contactInformationFieldsForEdit("Individual Global Contact");
                break;
            case "Entity Global Contact":
                contactInformationFieldsForEdit("Entity Global Contact");
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
                clearField(FIRST_NAME_FIELD);
                fillField(FIRST_NAME_FIELD, "Edit.firstName");
                clearField(MIDDLE_NAME_FIELD);
                fillField(MIDDLE_NAME_FIELD, "Edit.middleName");
                clearField(LAST_NAME_FIELD);
                fillField(LAST_NAME_FIELD, "Edit.lastName");
                clearField(MAIDEN_NAME_FIELD);
                fillField(MAIDEN_NAME_FIELD, "Edit.maidenName");
                clearField(ENTITY_NAME_FIELD_CREATE);
                fillField(ENTITY_NAME_FIELD_CREATE, "Edit.entityName");
                clearField(EIN_FIELD);
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                clearField(PHONE_NUMBER_FIELD);
                fillField(PHONE_NUMBER_FIELD, "Edit.phoneNumber", actions);
                clearField(WORK_NUMBER_FIELD);
                fillField(WORK_NUMBER_FIELD, "Edit.workNumber", actions);
                clearField(EMAIL_ADDRESS_FIELD);
                fillField(EMAIL_ADDRESS_FIELD, "Edit.emailId");
                clearField(FAX_FIELD);
                fillField(FAX_FIELD, "Edit.fax", actions);
                clearField(SSN_FIELD);
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                clearField(PTIN_FIELD);
                fillField(PTIN_FIELD, "Edit.ptin");
                clearField(PINEFILE_FIELD);
                fillField(PINEFILE_FIELD, "Edit.pinEFile");
                clearField(BARID_FIELD);
                fillField(BARID_FIELD, "Edit.barId");
                clearField(CAF_FIELD);
                fillField(CAF_FIELD, "Edit.caf");
                break;
            case "Entity Global Contact":
                clearField(ENTITY_NAME_FIELD_CREATE);
                fillField(ENTITY_NAME_FIELD_CREATE, "Edit.entityName");
                clearField(ENTITY_EIN_FIELD);
                fillFieldWithRandom(ENTITY_EIN_FIELD, randomEIN, actions);
                clearField(FIRST_NAME_FIELD);
                fillField(FIRST_NAME_FIELD, "Edit.firstName");
                clearField(MIDDLE_NAME_FIELD);
                fillField(MIDDLE_NAME_FIELD, "Edit.middleName");
                clearField(LAST_NAME_FIELD);
                fillField(LAST_NAME_FIELD, "Edit.lastName");
                clearField(EMAIL_ADDRESS_FIELD);
                fillField(EMAIL_ADDRESS_FIELD, "Edit.emailId");
                clearField(PTIN_FIELD);
                fillField(PTIN_FIELD, "Edit.ptin");
                clearField(PINEFILE_FIELD);
                fillField(PINEFILE_FIELD, "Edit.pinEFile");
                clearField(BARID_FIELD);
                fillField(BARID_FIELD, "Edit.barId");
                clearField(CAF_FIELD);
                fillField(CAF_FIELD, "Edit.caf");
                clearField(SSN_FIELD);
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                clearField(PHONE_NUMBER_FIELD);
                fillField(PHONE_NUMBER_FIELD, "Edit.phoneNumber", actions);
                clearField(WORK_NUMBER_FIELD);
                fillField(WORK_NUMBER_FIELD, "Edit.workNumber", actions);
                clearField(FAX_FIELD);
                fillField(FAX_FIELD, "Edit.fax", actions);
                break;

            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void saveGlobalContact() throws AutomationException, IOException {
        clickButtonSave();
    }

    public void verifyGlobalContactSaved() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact created successfully."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact created successfully.")));
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
    }

    public void verifyentityNameFieldPrefilled() throws AutomationException {
        String actualEntityName = driverUtil.getWebElement(ENTITY_NAME_FIELD_FILLED).getAttribute("value");
        if (actualEntityName == null || !actualEntityName.equals(entityName)) {
            throw new AutomationException("Entity Name is not prefilled correctly.");
        }
    }

    public void verifyAutoFetchedFields() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("Create").get("Create.city").toString();
        String expectedState = CommonUtil.getJsonPath("Create").get("Create.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("Create").get("Create.country").toString();
        WebDriverUtil.waitForAWhile(3);
        String actualCity = getFieldValue(CITY, "value");
        String actualState = getFieldValue(STATE, "text");
        String actualCounty = getFieldValue(COUNTY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    private static String getFieldValue(String locator, String attribute) throws AutomationException {
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
                String firstNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.firstName").toString() + CommonUtil.currentDateAndTime();
                String lastNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.lastName").toString() + CommonUtil.currentDateAndTime();
                WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys("  " + lastNameWithSpaces + "    ");
                firstNameField.clear();
                firstNameField.sendKeys("  " + firstNameWithSpaces + "    ");
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value").trim();
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value").trim();
                clickButtonCreateIndividualContact();
                break;
            case "Entity Global Contact":
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
                    CommonSteps.logInfo(userType + " user: Create button is Visible & Clickable and indicating eligibility to create Global Contacts.");
                } else {
                    throw new AutomationException(userType + " user: Create button is not visible or clickable within the timeout.");
                }
                break;
            case "View Only":
                WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
                WebElement createButton = driverUtil.getWebElement(CREATE_BUTTON,2);
                if (createButton!=null && createButton.isDisplayed()) {
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
        if (!driverUtil.getWebElement(REQUIRED_FIELD_ERROR_XPATH).isDisplayed()) {
            throw new AutomationException("Error messages are not displayed.");
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

    public void enterExistedEIN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        WebElement EINField = driverUtil.getWebElement(ENTITY_EIN_FIELD);
        String baseEIN = CommonUtil.getJsonPath("Create").get("Create.ein").toString();
        actions.moveToElement(EINField).click().sendKeys(baseEIN).build().perform();
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
    }

    public void clickOn(String button) throws AutomationException {
        String buttonXPath = String.format(BUTTON_IN_FOOTER, button);
        driverUtil.getWebElement(buttonXPath).click();
    }

    public void clickBtnManageAddress() throws AutomationException {
        driverUtil.getWebElement(MANAGE_ADDRESS_BTN).click();
    }

    public void fillAddressInfo() throws AutomationException, IOException, ParseException {
        driverUtil.getWebElement(ADD_NEW_ADDRESS_BTN).click();

        fillField(ADDRESS_LINE1, "Create.addressLine1");
        fillField(ADDRESS_LINE2, "Create.addressLine2");
        fillField(ZIP, "Create.zip");
        driverUtil.getWebElement(ZIP).sendKeys(Keys.TAB);
        fillField(ADDRESS_COMMENTS,"Create.addressComments");
    }

    public void userSwitchedToEditMode() throws AutomationException {
        String currentUrl = DriverFactory.drivers.get().getCurrentUrl();
        if (!currentUrl.contains("edit")) {
            throw new AutomationException("User is not in edit mode. Current URL: " + currentUrl);
        }
    }

    public void verifyGlobalContactCreated() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Contact created successfully."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified that Global Contact is created successfully.");
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Contact created successfully.")));
    }


    public void fillEntityAndContactInfo() throws AutomationException, IOException, ParseException, AWTException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        fillFieldWithKeyStrokes(ENTITY_NAME_FIELD_CREATE, "Create.entityName");
        fillFieldWithRandomWithKeyStrokes(EIN_FIELD, randomEIN, actions);
        enteredEIN=driverUtil.getWebElement(EIN_FIELD).getAttribute("value");
        fillFieldWithKeyStrokes(PHONE_NUMBER_FIELD, "Create.phoneNumber",actions);
        fillFieldWithRandomWithKeyStrokes(SSN_FIELD, randomSSN, actions);
        enteredSSN=driverUtil.getWebElement(SSN_FIELD).getAttribute("value");
        fillFieldWithKeyStrokes(BARID_FIELD, "Create.barId");
        fillFieldWithKeyStrokes(EMAIL_ADDRESS_FIELD, "Create.emailId");
        fillFieldWithKeyStrokes(CAF_FIELD, "Create.caf");
        fillFieldWithKeyStrokes(WORK_NUMBER_FIELD, "Create.workNumber",actions);
        fillFieldWithKeyStrokes(PTIN_FIELD, "Create.ptin");
        fillFieldWithKeyStrokes(FAX_FIELD, "Create.fax",actions);
        fillFieldWithKeyStrokes(PINEFILE_FIELD, "Create.pinEFile");
    }

    public void verifyAddressSavedSuccessfully() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified that Address is added successfully.");
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully.")));

    }

    public void fillsContactPersonSDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));

        selectSuffixOption();
        WebDriverUtil.waitForAWhile();
        fillFieldWithKeyStrokes(FIRST_NAME_FIELD, "Create.firstName");
        fillFieldWithKeyStrokes(MIDDLE_NAME_FIELD, "Create.middleName");
        fillFieldWithKeyStrokes(LAST_NAME_FIELD, "Create.lastName");
        fillFieldWithKeyStrokes(EMAIL_ADDRESS_FIELD, "Create.emailId");
        fillFieldWithKeyStrokes(PTIN_FIELD, "Create.ptin");
        fillFieldWithKeyStrokes(PINEFILE_FIELD, "Create.pinEFile");
        fillFieldWithKeyStrokes(BARID_FIELD, "Create.barId");
        fillFieldWithKeyStrokes(CAF_FIELD, "Create.caf");
        fillFieldWithRandomWithKeyStrokes(SSN_FIELD, randomSSN, actions);
        enteredEntitySSN=driverUtil.getWebElement(SSN_FIELD).getAttribute("value");
        fillFieldWithKeyStrokes(PHONE_NUMBER_FIELD, "Create.phoneNumber", actions);
        fillFieldWithKeyStrokes(WORK_NUMBER_FIELD, "Create.workNumber", actions);
        fillFieldWithKeyStrokes(FAX_FIELD, "Create.fax", actions);
    }

    public void closeTheAddressBar() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address updated successfully.")));
        driverUtil.getWebElement(CLOSE_ADDRESS_BAR).click();
    }

    public void addMultipleAddresses() throws AutomationException, IOException, ParseException {
        fillAddressInfo();
        verifyAutoFetchedFields();
        clickButtonSave();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForVisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully.")));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully.")));
        fillAddressInfo();
        verifyAutoFetchedFields();
        clickButtonSave();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForVisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully.")));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address added successfully.")));
    }


    public void editAddress() throws AutomationException, IOException, ParseException {
        driverUtil.getWebElement(EDIT_ADDRESS).click();
        WebDriverUtil.waitForAWhile(3);
        fillField(ADDRESS_LINE1, "Edit.addressLine1");
        clickButtonSave();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Address updated successfully.")));
        String expectedAddress = CommonUtil.getJsonPath("Edit").get("Edit.addressLine1").toString();
        WebElement actualAddressInList = driverUtil.getWebElement(String.format(ADDRESS_IN_LIST_MODAL,expectedAddress));
        if(!actualAddressInList.isDisplayed()){
            throw new AutomationException("Address is not edited and not reflected in the list");
        }

        List<WebElement> addressListModal = driverUtil.getWebElements(ADDRESS_COLUMN_MODAL);
        modalAddresses = addressListModal.stream()
                .map(WebElement::getText)
                .toList();

    }

    public void verifyAddedAddressesListDisplayedCorrectly() throws AutomationException {
        WebDriverUtil.waitForAWhile(3);
        List<WebElement> addressListOnEditPage = driverUtil.getWebElements(ADDRESS_ROW_ON_EDIT_PAGE);
        List<String> editPageAddresses = addressListOnEditPage.stream()
                .map(WebElement::getText)
                .toList();

        for (String modalAddress : modalAddresses) {
            boolean isAddressFound = editPageAddresses.stream()
                    .anyMatch(editPageAddress -> editPageAddress.contains(getAddressLine1(modalAddress)));

            if (!isAddressFound) {
                throw new AssertionError("Address not found on edit page: " + modalAddress);
            }
        }
    }

    private static String getAddressLine1(String address) {
        return address.split(",")[0].trim();
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public static void verifyIndividualGlobalContactDetailsAutoSaved() throws IOException, ParseException, AutomationException {
        String expectedMiddleName = CommonUtil.getJsonPath("Create").get("Create.middleName").toString();
        String expectedSuffix = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        String expectedMaidenName = CommonUtil.getJsonPath("Create").get("Create.maidenName").toString();
        String expectedEntityName = CommonUtil.getJsonPath("Create").get("Create.entityName").toString();
        String expectedPhone = CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString();
        String expectedWorkPhone = CommonUtil.getJsonPath("Create").get("Create.workNumber").toString();
        String expectedEmailAddress = CommonUtil.getJsonPath("Create").get("Create.emailId").toString();
        String expectedFax = CommonUtil.getJsonPath("Create").get("Create.fax").toString();
        String expectedPTIN = CommonUtil.getJsonPath("Create").get("Create.ptin").toString();
        String expectedPINeFile = CommonUtil.getJsonPath("Create").get("Create.pinEFile").toString();
        String expectedBarID = CommonUtil.getJsonPath("Create").get("Create.barId").toString();
        String expectedCAF = CommonUtil.getJsonPath("Create").get("Create.caf").toString();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        filterByName();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        String contactName = lastName+", "+firstName;
        driverUtil.getWebElement(String.format(CONTACT_NAME,contactName)).click();


        WebDriverUtil.waitForAWhile(1);
        String actualFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        String actualMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        String actualLastName = getFieldValue(LAST_NAME_FIELD, "value");
        String actualSuffix = getFieldValue(SELECTED_SUFFIX, "text");
        String actualMaidenName = getFieldValue(MAIDEN_NAME_FIELD, "value");

        String actualEntityName = getFieldValue(ENTITY_NAME_FIELD_CREATE, "value");
        String actualEIN = getFieldValue(EIN_FIELD, "value");

        String actualPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        String actualWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        String actualEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        String actualFax = getFieldValue(FAX_FIELD, "value");
        String actualSSN = getFieldValue(SSN_FIELD, "value");
        String actualPTIN = getFieldValue(PTIN_FIELD, "value");
        String actualPINeFile = getFieldValue(PINEFILE_FIELD, "value");
        String actualBarID = getFieldValue(BARID_FIELD, "value");
        String actualCAF = getFieldValue(CAF_FIELD, "value");

        verifyField("First Name", firstName, actualFirstName);
        verifyField("Middle Name", expectedMiddleName, actualMiddleName);
        verifyField("Last Name", lastName, actualLastName);
        verifyField("Suffix", expectedSuffix, actualSuffix);
        verifyField("Maiden Name",expectedMaidenName,actualMaidenName);
        verifyField("Entity Name",expectedEntityName,actualEntityName);
        verifyField("EIN",enteredEIN,actualEIN);
        verifyField("Phone",expectedPhone,actualPhone);
        verifyField("Work Phone",expectedWorkPhone,actualWorkPhone);
        verifyField("Email Address",expectedEmailAddress,actualEmailAddress);
        verifyField("Fax",expectedFax,actualFax);
        verifyField("SSN",enteredSSN,actualSSN);
        verifyField("PTIN",expectedPTIN,actualPTIN);
        verifyField("PIN e-File",expectedPINeFile,actualPINeFile);
        verifyField("Bar ID",expectedBarID,actualBarID);
        verifyField("CAF",expectedCAF,actualCAF);
    }

    public static void verifyEntityGlobalContactDetailsAutoSaved() throws IOException, ParseException, AutomationException {
        String expectedFirstName = CommonUtil.getJsonPath("Create").get("Create.firstName").toString();
        String expectedMiddleName = CommonUtil.getJsonPath("Create").get("Create.middleName").toString();
        String expectedLastName = CommonUtil.getJsonPath("Create").get("Create.lastName").toString();
        String expectedSuffix = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        String expectedPhone = CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString();
        String expectedWorkPhone = CommonUtil.getJsonPath("Create").get("Create.workNumber").toString();
        String expectedEmailAddress = CommonUtil.getJsonPath("Create").get("Create.emailId").toString();
        String expectedFax = CommonUtil.getJsonPath("Create").get("Create.fax").toString();
        String expectedPTIN = CommonUtil.getJsonPath("Create").get("Create.ptin").toString();
        String expectedPINeFile = CommonUtil.getJsonPath("Create").get("Create.pinEFile").toString();
        String expectedBarID = CommonUtil.getJsonPath("Create").get("Create.barId").toString();
        String expectedCAF = CommonUtil.getJsonPath("Create").get("Create.caf").toString();

        clearField(CONTACT_TYPE_FILTER_INPUT);
        filterByEntity();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(String.format(CONTACT_NAME,entityName)).click();


        WebDriverUtil.waitForAWhile(1);
        String actualEntityName = getFieldValue(ENTITY_NAME_FIELD_CREATE, "value");
        String actualEIN = getFieldValue(EIN_FIELD, "value");

        String actualFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        String actualMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        String actualLastName = getFieldValue(LAST_NAME_FIELD, "value");
        String actualSuffix = getFieldValue(SELECTED_SUFFIX, "text");

        String actualPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        String actualWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        String actualEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        String actualFax = getFieldValue(FAX_FIELD, "value");
        String actualSSN = getFieldValue(SSN_FIELD, "value");
        String actualPTIN = getFieldValue(PTIN_FIELD, "value");
        String actualPINeFile = getFieldValue(PINEFILE_FIELD, "value");
        String actualBarID = getFieldValue(BARID_FIELD, "value");
        String actualCAF = getFieldValue(CAF_FIELD, "value");

        verifyField("Entity Name",entityName,actualEntityName);
        verifyField("EIN",enteredEntityEIN,actualEIN);
        verifyField("First Name", expectedFirstName, actualFirstName);
        verifyField("Middle Name", expectedMiddleName, actualMiddleName);
        verifyField("Last Name", expectedLastName, actualLastName);
        verifyField("Suffix", expectedSuffix, actualSuffix);
        verifyField("Phone",expectedPhone,actualPhone);
        verifyField("Work Phone",expectedWorkPhone,actualWorkPhone);
        verifyField("Email Address",expectedEmailAddress,actualEmailAddress);
        verifyField("Fax",expectedFax,actualFax);
        verifyField("SSN",enteredEntitySSN,actualSSN);
        verifyField("PTIN",expectedPTIN,actualPTIN);
        verifyField("PIN e-File",expectedPINeFile,actualPINeFile);
        verifyField("Bar ID",expectedBarID,actualBarID);
        verifyField("CAF",expectedCAF,actualCAF);
    }
}





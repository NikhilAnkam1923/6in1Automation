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
    public static final String RADIO_BUTTON = "//input[@type='radio']";
    public static final String SELECT_OR_CREATENEW_PAGE = "//div[text()='Contact (Select or Create New)']";
    public static final String INVALID_EIN_ERROR_MESSAGE = "///input[@id='contact.ein']/following-sibling::div";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='3'] //input[@aria-label='Filter']";
    public static final String ACTIONS_BUTTON = "//td[@class='action-column text-center']//button[@class='dropdown-toggle btn btn-primary']";
    public static final String EDIT_BUTTON = "//a[text()='Edit']";
    public static String firstName;
    public static String lastName;
    public static String entityName;
    public static final String ESATE_TAB = "//a[@class='active-link nav-link']";
    public static final String REQUIRED_FIELD_ERROR_XPATH = "//input[contains(@aria-required,'true')]/following-sibling::div";
    public static final String ADDRESS_LINE1_ERROR_REQUIRED_XPATH = "//input[@id='address.addressLine1']/following-sibling::div";
    public static final String ZIP_ERROR_REQUIRED_XPATH = "//input[@id='address.zip']/following-sibling::div";
    public static final String CITY_ERROR_REQUIRED_XPATH = "//input[@id='address.city']/following-sibling::div";
    public static final String STATE_ERROR_REQUIRED_XPATH = "//div[@id='State']";
    public static final String FIRSTNAME_ERROR_REQUIRED_XPATH = "//input[@id='contact.firstName']/following-sibling::div";
    public static final String LASTNAME_REQUIRED_XPATH = "//input[@id='contact.lastName']/following-sibling::div";
    private static final String FIRST_PAGE_BUTTON = "//button[@title='Go to the first page']";
    private static final String EDIT_CONTACT = "//a[contains(@class,'column-edit-link')]";
    private static final String ROW_NAME_TYPE = "//tr[td//a[text()='%s'] and td[text()='%s']]";
    private static final String NEXT_PAGE = "//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    private static final String CONTACT_NAME = "//a[contains(@class,'column-edit-link') and text()='%s']";
    public static final String ENTITY_NAMES_COLUMN = "//td[@aria-colindex='4']";
    public static final String ALL_DATA_ROWS_XPATH = "//tbody//tr";
    private static final String BUTTON_IN_FOOTER = "//div[@class='modal-footer']//button[contains(text(),'%s')]";
    private static final String CONTACT_NAMES_COLUMN = "//td[@aria-colindex='2']";
    public static final String SELECT_AND_PROCEED_BTN ="//button[contains(text(),'Select & Proceed')]";

    public GlobalContactPage() throws IOException {

    }

    public void tabNavigation(String tab) throws AutomationException, IOException {
        WebElement tabElement = driverUtil.getWebElement(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a", tab));
        waitForVisibleElement(tabElement);
        tabElement.click();
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
            case "Select & Proceed":
                buttonDetails.put(buttonName,SELECT_AND_PROCEED_BTN);
            default:
                throw new AutomationException("Unknown button: " + buttonName);
        }
        new GlobalContactPage().clickButton(buttonDetails);

    }

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
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                buttonClick("Create Individual Contact");
                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD).sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString() + CommonUtil.currentDateAndTime());
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                buttonClick("Create Entity Contact");
                break;
            default:
                throw new AutomationException("Unknown button: " + " ");
        }
        new GlobalContactPage().clickButton(buttonDetails);
        CommonSteps.takeScreenshot();
    }

    public void fillGlobalContactDetails(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Map<String, String> buttonDetails = new HashMap<>();
        Actions actions = new Actions(DriverFactory.drivers.get());
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
                middleNameField.clear();
                middleNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.middleName").toString());
                WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
                maidenNameField.clear();
                maidenNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.maidenName").toString());
                WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD_CREATE);
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
                actions.moveToElement(SSNField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.ssn").toString()).perform();
                WebElement EINField = driverUtil.getWebElement(EIN_FIELD);
                actions.moveToElement(EINField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.ein").toString()).perform();
                WebElement phoneNumberField = driverUtil.getWebElement(PHONE_NUMBER_FIELD);
                actions.moveToElement(phoneNumberField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString()).perform();
                WebElement workNumberField = driverUtil.getWebElement(WORK_NUMBER_FIELD);
                actions.moveToElement(workNumberField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.workNumber").toString()).perform();
                WebElement faxField = driverUtil.getWebElement(FAX_FIELD);
                actions.moveToElement(faxField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.fax").toString()).perform();

                break;
            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                // Required Fields for Entity
                WebElement EntityEINField = driverUtil.getWebElementAndScroll(EIN_FIELD);
                actions.moveToElement(EntityEINField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.ein").toString()).perform();

                WebElement entityContactNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD_CREATE);
                entityContactNameField.clear();
                entityContactNameField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.entityName").toString());
                WebElement entityEmailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                entityEmailField.clear();
                entityEmailField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.emailId").toString());
                WebElement entityAddressField = driverUtil.getWebElementAndScroll(ADDRESS_LINE1);
                entityAddressField.clear();
                entityAddressField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString());
                WebElement entityZipField = driverUtil.getWebElementAndScroll(ZIP);
                entityZipField.clear();
                entityZipField.sendKeys(CommonUtil.getJsonPath("Create").get("Create.zip").toString());
                SSNField = driverUtil.getWebElement(SSN_FIELD);
                actions.moveToElement(SSNField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.ssn").toString()).perform();
                phoneNumberField = driverUtil.getWebElement(PHONE_NUMBER_FIELD);
                actions.moveToElement(phoneNumberField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString()).perform();
                workNumberField = driverUtil.getWebElement(WORK_NUMBER_FIELD);
                actions.moveToElement(workNumberField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.workNumber").toString()).perform();
                faxField = driverUtil.getWebElement(FAX_FIELD);
                actions.moveToElement(faxField).click().sendKeys(CommonUtil.getJsonPath("Create").get("Create.fax").toString()).perform();
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
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
        Actions actions = new Actions(DriverFactory.drivers.get());
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                firstNameField.clear();
                firstNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.firstName").toString());
                WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
                middleNameField.clear();
                middleNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.middleName").toString());
                WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.lastName").toString());
                WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
                maidenNameField.clear();
                maidenNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.maidenName").toString());
                WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD_CREATE);
                entityNameField.clear();
                entityNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.entityName").toString());

                WebElement emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                emailField.clear();
                emailField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.emailId").toString());

                WebElement EINField = driverUtil.getWebElement(EIN_FIELD);
                EINField.clear();
                actions.moveToElement(EINField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ein").toString()).perform();
                WebElement SSNField = driverUtil.getWebElement(SSN_FIELD);
                SSNField.clear();
                actions.moveToElement(SSNField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ssn").toString()).perform();
                WebElement phoneNumberField = driverUtil.getWebElement(PHONE_NUMBER_FIELD);
                phoneNumberField.clear();
                actions.moveToElement(phoneNumberField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.phoneNumber").toString()).perform();
                WebElement workNumberField = driverUtil.getWebElement(WORK_NUMBER_FIELD);
                workNumberField.clear();
                actions.moveToElement(workNumberField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.workNumber").toString()).perform();
                WebElement faxField = driverUtil.getWebElement(FAX_FIELD);
                faxField.clear();
                actions.moveToElement(faxField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.fax").toString()).perform();


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

                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                firstNameField.clear();
                firstNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.firstName").toString());
                middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
                middleNameField.clear();
                middleNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.middleName").toString());
                lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.lastName").toString());
                maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
                maidenNameField.clear();
                maidenNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.maidenName").toString());
                entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD_CREATE);
                entityNameField.clear();
                entityNameField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.entityName").toString());
                emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
                emailField.clear();
                emailField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.emailId").toString());

                EINField = driverUtil.getWebElement(EIN_FIELD);
                EINField.clear();
                actions.moveToElement(EINField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ein").toString()).perform();
                SSNField = driverUtil.getWebElement(SSN_FIELD);
                SSNField.clear();
                actions.moveToElement(SSNField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ssn").toString()).perform();
                phoneNumberField = driverUtil.getWebElement(PHONE_NUMBER_FIELD);
                phoneNumberField.clear();
                actions.moveToElement(phoneNumberField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.phoneNumber").toString()).perform();
                workNumberField = driverUtil.getWebElement(WORK_NUMBER_FIELD);
                workNumberField.clear();
                actions.moveToElement(workNumberField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.workNumber").toString()).perform();
                faxField = driverUtil.getWebElement(FAX_FIELD);
                faxField.clear();
                actions.moveToElement(faxField).click().sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.fax").toString()).perform();

                ptinField = driverUtil.getWebElementAndScroll(PTIN_FIELD);
                ptinField.clear();
                ptinField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.ptin").toString());
                pinEFileField = driverUtil.getWebElementAndScroll(PINEFILE_FIELD);
                pinEFileField.clear();
                pinEFileField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.pinEFile").toString());
                barIDField = driverUtil.getWebElementAndScroll(BARID_FIELD);
                barIDField.clear();
                barIDField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.barId").toString());
                cafField = driverUtil.getWebElementAndScroll(CAF_FIELD);
                cafField.clear();
                cafField.sendKeys(CommonUtil.getJsonPath("Edit").get("Edit.caf").toString());

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
        try {
            Assert.assertEquals(driverUtil.getWebElement(FIRST_NAME_FIELD_CREATE).getAttribute("value"), firstName);
            Assert.assertEquals(driverUtil.getWebElement(LAST_NAME_FIELD_CREATE).getAttribute("value"), lastName);
        } catch (AutomationException e) {
            throw new AutomationException("first Name and Last Name fields are empty");
        }
    }

    public void verifyentityNameFieldPrefilled() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        try {
            Assert.assertEquals(driverUtil.getWebElement(ENTITY_NAME_FIELD_CREATE).getAttribute("value"), entityName);
        } catch (AutomationException e) {
            throw new AutomationException("Entity Name fields are empty");
        }
    }
        public void verifyConfirmationMessage (String confirmationMsg) throws AutomationException {
            WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, confirmationMsg));
            if (confirmationElement == null || !confirmationElement.isDisplayed()) {
                throw new AutomationException("Confirmation message not displayed");
            }
            CommonSteps.logInfo("Confirmation message is: " + confirmationElement.getText());
        }

        public boolean enterDataInFieldByLabel (String fieldData, String fieldName){
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

        public boolean verifyFetchedFields (String expectedCity, String expectedState, String expectedCounty){
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

        public void enterEntityNameFields (String entityName) throws AutomationException {
            WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
            entityNameField.clear();
            entityNameField.sendKeys(entityName);
        }

        @Override
        String getName () {
            return "";
        }

        public void verifyUserType (String userType){
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

        public void verifyDuplicateEINError () throws AutomationException {
            WebDriverUtil.waitForVisibleElement(By.xpath(EIN_DUPLICATION_ERROR));
            WebElement errorMessage = driverUtil.getWebElement(EIN_DUPLICATION_ERROR);
            if (errorMessage == null || !errorMessage.isDisplayed()) {
                throw new AutomationException("EIN error message is not displayed.");
            } else {
                String ErrorMessage = errorMessage.getText().trim();
                CommonSteps.logInfo("Error message display for duplicate EIN as expected  : " + ErrorMessage);
            }
        }

        public void verifyRequiredFieldValidationErrors () throws AutomationException {
            try {
                driverUtil.getWebElement(ADDRESS_LINE1_ERROR_REQUIRED_XPATH).isDisplayed();
                driverUtil.getWebElement(ZIP_ERROR_REQUIRED_XPATH).isDisplayed();
                driverUtil.getWebElement(CITY_ERROR_REQUIRED_XPATH).isDisplayed();
                driverUtil.getWebElement(STATE_ERROR_REQUIRED_XPATH).isDisplayed();
            } catch (Exception e) {
                throw new AutomationException("Error messages not displayed");
            }
        }

        public void fillRequiredFields (String contactType) throws AutomationException, IOException, ParseException {
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

        private void fillField (String fieldName, String value) throws AutomationException {
            String fieldXpath = String.format("//input[contains(@placeholder, '%s')]", fieldName);
            WebElement field = driverUtil.getWebElementAndScroll(fieldXpath);
            field.sendKeys(value);
            CommonSteps.logInfo("Filled the field: " + fieldName + " with value: " + value);
        }

        private void waitForFieldToUpdate (String fieldXpath, String expectedText) throws AutomationException {
            driverUtil.waitForElement(By.xpath(fieldXpath), 10);
            WebElement field = driverUtil.getWebElementWithoutWait(fieldXpath);
            if (!field.getText().contains(expectedText)) {
                throw new AutomationException("Expected text '" + expectedText + "' not found in element: " + fieldXpath);
            }
        }

        public void verifyNoValidationErrors () throws AutomationException {
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

        public boolean isButtonEnabled (String buttonName) throws AutomationException {
            String buttonXpath = String.format("//button[contains(text(), '%s')]", buttonName);
            WebElement button = driverUtil.getWebElement(buttonXpath);
            return button != null && button.isEnabled();
        }

        public boolean isContactNameUpdated (String expectedFullName) throws AutomationException {
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

        public void enterSSN (String ssn) throws AutomationException {
            String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
            if (!ssn.matches(ssnPattern)) {
                throw new AutomationException("Invalid SSN format. Expected format: 000-00-0000. Provided: " + ssn);
            }
            WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
            SSNField.clear();
            SSNField.sendKeys(ssn);
        }

        public boolean enterEntityName (String entityName){
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

        public List<String> getAllDisplayedEntityNames () throws AutomationException {
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

        public List<String> verifyClassForContactType (String contactType) throws AutomationException {
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


        public boolean verifyRadioButtonsForContacts () throws AutomationException {
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

        public boolean isButtonAvailable (String buttonName) throws AutomationException {
            try {
                String buttonXPath = String.format(BUTTON_IN_FOOTER, buttonName);
                WebElement buttonElement = driverUtil.getWebElement(buttonXPath, 5);
                return buttonElement != null && buttonElement.isDisplayed() && buttonElement.isEnabled();
            } catch (Exception e) {
                return false;
            }
        }

        public List<String> getAllDisplayedContactNames () throws AutomationException {
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

    }






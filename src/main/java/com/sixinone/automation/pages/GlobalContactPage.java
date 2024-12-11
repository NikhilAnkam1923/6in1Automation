package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.stringtemplate.v4.ST;
import org.testng.Assert;


import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sixinone.automation.util.WebDriverUtil.*;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;


public class GlobalContactPage extends BasePage {

    public static final String GLOBALCONTACT_BUTTON = "//span[text()='Global Contact']";
    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@name='contact.maidenName']";
    private static final String ENTITY_NAME_FIELD = "//input[@name='contact.entityName']";
    private static final String SSN_FIELD = "//input[@name='contact.ssn']";
    private static final String EIN_FIELD = "//input[@name='contact.ein']";
    public static final String SAVE_BUTTON = "//button[text()='Save']";
    private static final String LAST_NAME_FIELD_CREATE = "//input[@id='contact.lastName']";
    private static final String FIRST_NAME_FIELD_CREATE = "//input[@id='contact.firstName']";
    private static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    public static final String SPINNER = "//div[contains(class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    public static final String CREATE_ENTITY_CONTACT_BTN = "//button[text()='Create Entity Contact']";
    private static final String FIELD_DYNAMIC_XPATH = "//input[contains(@name,'%s')]";
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
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Entity Details']";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";
    public static final String FIELD_XPATH = "//label[text()='%s']/following-sibling::input";
    public static final String TOAST_MSG = "//div[@class='Toastify__toast-body']//div/following-sibling::div[contains(text(),'%s')]";
    private static final String EDIT_CONTACT = "//a[contains(@class,'column-edit-link')]";
    private static final String ROW_NAME_TYPE = "//tr[td//a[text()='%s'] and td[text()='%s']]";
    private static final String CONTACT_NAME = "//a[contains(@class,'column-edit-link') and text()='%s']";
    private static final String NEXT_PAGE = "//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String SELECTED_OPTION = "//div[contains(@class,'select__single-value')]";
    private static final String FIRST_PAGE_BUTTON = "//button[@title='Go to the first page']";
    public static final String ENTITY_NAME_INPUT = "//input[@name='entityName']";
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
    private static final String ENTITY_NAME_FIELD_CREATE = "//input[@name='contact.entityName']";
    public static final String ACTIONS_BUTTON = "//td[@class='action-column text-center']//button[@class='dropdown-toggle btn btn-primary']";


    public static String firstName;
    public static String lastName;
    public static String entityName;


    public void clickButton(Map<String, String> buttonDetails) throws AutomationException {
        for (Map.Entry<String, String> entry : buttonDetails.entrySet()) {
            String buttonName = entry.getKey();
            String buttonXPath = entry.getValue();

            WebElement button = driverUtil.getWebElement(buttonXPath,20);
            if (button == null) {
                throw new AutomationException("Unable to find the " + buttonName + " button.");
            }

            button.click();
            WebDriverUtil.waitForAWhile();
        }
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

    public void enterSSNAndEIN() throws AutomationException, IOException, ParseException {
        String ssn = CommonUtil.getJsonPath("Create").get("Create.ssn").toString();
        String ein = CommonUtil.getJsonPath("Create").get("Create.ein").toString();
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

    public void enterSSN(String ssn) throws AutomationException {
        String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
        if (!ssn.matches(ssnPattern)) {
            throw new AutomationException("Invalid SSN format. Expected format: 000-00-0000. Provided: " + ssn);
        }
        WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
        SSNField.clear();
        SSNField.sendKeys(ssn);
    }


    public void enterFirstnameAndLastNameFields(String firstName, String lastName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void verifyFieldPrefilled(String fieldName, String expectedValue) throws AutomationException {
        String fieldXpath = String.format(FIELD_DYNAMIC_XPATH, fieldName);
        WebElement fieldElement = driverUtil.getWebElementAndScroll(fieldXpath);

        String actualValue = fieldElement.getAttribute("value");

        actualValue = actualValue.trim();
        expectedValue = expectedValue.trim();

        if (!actualValue.equals(expectedValue)) {
            throw new AutomationException("Field " + fieldName + " is not pre-filled correctly. " + "Expected: " + expectedValue + ", Found: " + actualValue);
        }
    }

    public void enterRequiredFields(String addressLine1, String zip) throws AutomationException {
        WebElement addressLine1Field = driverUtil.getWebElementAndScroll(ADDRESS_LINE1);
        addressLine1Field.clear();
        addressLine1Field.sendKeys(addressLine1);

        WebElement zipField = driverUtil.getWebElementAndScroll(ZIP);
        zipField.clear();
        zipField.sendKeys(zip);
    }

    public void enterOptionalFields(String middleName, String maidenName, String entityName, String emailAddress, String ptin, String pinEFile, String barID, String caf, String addressLine2) throws AutomationException {
        WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
        middleNameField.clear();
        middleNameField.sendKeys(middleName);

        WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
        maidenNameField.clear();
        maidenNameField.sendKeys(maidenName);

        WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
        entityNameField.clear();
        entityNameField.sendKeys(entityName);

        WebElement emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
        emailField.clear();
        emailField.sendKeys(emailAddress);

        WebElement ptinField = driverUtil.getWebElementAndScroll(PTIN_FIELD);
        ptinField.clear();
        ptinField.sendKeys(ptin);

        WebElement pinEFileField = driverUtil.getWebElementAndScroll(PINEFILE_FIELD);
        pinEFileField.clear();
        pinEFileField.sendKeys(pinEFile);

        WebElement barIDField = driverUtil.getWebElementAndScroll(BARID_FIELD);
        barIDField.clear();
        barIDField.sendKeys(barID);

        WebElement cafField = driverUtil.getWebElementAndScroll(CAF_FIELD);
        cafField.clear();
        cafField.sendKeys(caf);

        WebElement addressLine2Field = driverUtil.getWebElementAndScroll(ADDRESS_LINE2);
        addressLine2Field.clear();
        addressLine2Field.sendKeys(addressLine2);
    }

    public void enterIndividualDetailsFields(String firstName, String middleName, String lastName, String maidenName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
        middleNameField.clear();
        middleNameField.sendKeys(middleName);

        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
        maidenNameField.clear();
        maidenNameField.sendKeys(maidenName);
    }

    public void enterSSNEINPhoneNumberWorkNumberAndFax(String ssn, String ein, String phoneNumber, String workNumber, String fax) throws AutomationException {
        WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
        WebElement EINField = driverUtil.getWebElementAndScroll(EIN_FIELD);
        WebElement phoneNumberField = driverUtil.getWebElementAndScroll(PHONE_NUMBER_FIELD);
        WebElement workNumberField = driverUtil.getWebElementAndScroll(WORK_NUMBER_FIELD);
        WebElement faxField = driverUtil.getWebElementAndScroll(FAX_FIELD);

        setInputUsingJavaScript(SSNField, ssn);
        setInputUsingJavaScript(EINField, ein);
        setInputUsingJavaScript(phoneNumberField, phoneNumber);
        setInputUsingJavaScript(workNumberField, workNumber);
        setInputUsingJavaScript(faxField, fax);
    }

    private void setInputUsingJavaScript(WebElement element, String value) {
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

    @Override
    String getName() {
        return "GLOBAL CONTACTS";
    }

    public void enterDataInZipField() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        try {
            String fieldData = CommonUtil.getJsonPath("Create").get("Create.zip").toString();
            WebElement zipField = driverUtil.getWebElement(ZIP);
            zipField.clear();
            zipField.sendKeys(fieldData + Keys.ENTER);
            WebDriverUtil.waitForAWhile(2);
            CommonSteps.logInfo("Entered value in Zip field: " + fieldData);
        } catch (Exception e) {
            throw new AutomationException("Failed to enter data in Zip field: " + e.getMessage());
        }
    }


    public boolean verifyFetchedFields(String expectedCity, String expectedState, String expectedCounty) {
        try {
            WebDriverUtil.waitForAWhile(2);
            WebElement cityField = driverUtil.getWebElement(CITY, 10);
            String actualCity = cityField.getAttribute("value");
            WebElement stateField = driverUtil.getWebElement(STATE, 5);
            String actualState = stateField.getText();
            WebElement countyField = driverUtil.getWebElement(COUNTRY, 5);
            String actualCounty = countyField.getAttribute("value");
            return expectedCity.equals(actualCity) && expectedState.equals(actualState) && expectedCounty.equals(actualCounty);
        } catch (Exception e) {
            CommonSteps.logError("Failed to verify fetched city, state, or county values: " + e.getMessage());
            return false;
        }
    }

    public void waitForToasterToInvisible(String toastMessage) {
        String toastMessagePopup = String.format(TOAST_MSG, toastMessage);
        WebDriverUtil.waitForInvisibleElement(By.xpath(toastMessagePopup),30);
    }

    public boolean isUserOnEditContactPage() {
        String currentUrl = driverUtil.getCurrentUrl();
        String expectedUrlPattern = "/law-firm/global-contacts/person/edit/";
        return currentUrl != null && currentUrl.contains(expectedUrlPattern);
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

    public boolean selectOption(String labelName, String option){
        try {
            WebElement comboBox = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, labelName), 5);
            comboBox.click();
            WebElement selectOption = driverUtil.getWebElement(String.format(SELECT_OPTION, option), 5);
            selectOption.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifySelectedOption(String expectedOption) {
        try {
            WebElement selectedOption = driverUtil.getWebElement(SELECTED_OPTION);
            String actualOption = selectedOption.getText().trim();
            return actualOption.equals(expectedOption);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterEntityName(String entityName) {
        try {
            WebElement entityNameField = driverUtil.getWebElement(ENTITY_NAME_INPUT, 5);
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
            WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE ,2);
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
            List<WebElement> radioButtons = driverUtil.getWebElements(RADIO_BUTTONS_XPATH);
            List<WebElement> rows = driverUtil.getWebElements(ALL_DATA_ROWS_XPATH);
            if (radioButtons.size() != rows.size()) {
                return false;
            }
            WebElement nextPageButton = driverUtil.getWebElement("//button[@title='Go to the next page']", 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            WebDriverUtil.waitForElementNotVisible(60, "//div[@class='loading-spinner']"); // Adjust spinner locator
        }
        return true;
    }

    public boolean isButtonAvailable(String buttonName) throws AutomationException {
        try {
            String buttonXPath = String.format(BUTTON_IN_FOOTER, buttonName);
            WebElement buttonElement = driverUtil.getWebElement(buttonXPath, 5);
            return buttonElement != null && buttonElement.isDisplayed() && buttonElement.isEnabled();
        } catch (Exception e) {
            return false;
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

    public void tabNavigation(String tab) throws AutomationException, IOException {
        WebElement tabElement = driverUtil.getWebElement(String.format("//div[@class='nav']//div//*[contains(text(),'%s')]//parent::a", tab));
//        waitForVisibleElement(tabElement);
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

    public void verifyfirstNamelastNameFieldPrefilled() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        try {
            Assert.assertEquals(driverUtil.getWebElement(FIRST_NAME_FIELD_CREATE).getAttribute("value"), firstName);
            Assert.assertEquals(driverUtil.getWebElement(LAST_NAME_FIELD_CREATE).getAttribute("value"), lastName);
        } catch (AutomationException e) {
            throw new AutomationException("first Name and Last Name fields are empty");
        }
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

    public void enterAddressLine1Data() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        try {
            String fieldData = CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString();
            WebElement addressLine1Field = driverUtil.getWebElement(ADDRESS_LINE1);
            addressLine1Field.clear();
            addressLine1Field.sendKeys(fieldData + Keys.ENTER);
            WebDriverUtil.waitForAWhile(2);
            CommonSteps.logInfo("Entered value in Address Line 1 field: " + fieldData);
        } catch (Exception e) {
            throw new AutomationException("Failed to enter data in Address Line 1 field: " + e.getMessage());
        }
    }


    public void globalContactCreationWithSpaces(String action, String contactType) throws AutomationException, IOException, ParseException {
        Map<String, String> buttonDetails = new HashMap<>();
        buttonClick(action);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);
        switch (contactType) {
            case "Individual Global Contact":
                waitForVisibleElement(By.xpath(CREATE_INDIVIDUAL_CONTACT_BTN));
                String firstNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.firstName").toString().trim() + CommonUtil.currentDateAndTime();
                String lastNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.lastName").toString().trim() + CommonUtil.currentDateAndTime();
                WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
                WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
                lastNameField.clear();
                lastNameField.sendKeys(lastNameWithSpaces);
                firstNameField.clear();
                firstNameField.sendKeys(firstNameWithSpaces);
                CommonSteps.takeScreenshot();
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                buttonClick("Create Individual Contact");
                break;

            case "Entity Global Contact":
                waitForVisibleElement(By.xpath(CREATE_ENTITY_CONTACT_BTN));
                String entityNameWithSpaces = CommonUtil.getJsonPath("CreateWithSpaces").get("CreateWithSpaces.entityName").toString().trim() + CommonUtil.currentDateAndTime();
                WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
                entityNameField.clear();
                entityNameField.sendKeys(entityNameWithSpaces);
                entityName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                buttonClick("Create Entity Contact");
                break;

            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
        new GlobalContactPage().clickButton(buttonDetails);
        CommonSteps.takeScreenshot();
    }

}



package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;


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
    private static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    public static final String SPINNER = "//div[contains(class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    private static final String FIELD_DYNAMIC_XPATH = "//input[contains(@name,'%s')]";
    private static final String ADDRESS_LINE1 = "//input[@name='address.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='address.addressLine2']";
    private static final String ZIP = "//input[@name='address.zip']";
    public static final String GLOBAL_CONTACTS_PAGE = "//a[text()='Global Contacts']";
    public static final String HOME_PAGE = "//a[text()='Estate']";
    public static final String GLOBAL_CONTACT_CREATION_PAGE = "//a[text()='New']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";



    public void clickButton(Map<String, String> buttonDetails) throws AutomationException {
        for (Map.Entry<String, String> entry : buttonDetails.entrySet()) {
            String buttonName = entry.getKey();
            String buttonXPath = entry.getValue();

            WebElement button = driverUtil.getWebElement(buttonXPath);
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

            WebDriverUtil.waitForVisibleElement(By.xpath(elementXpath));

            WebElement element = driverUtil.getWebElementAndScroll(elementXpath);
            if (element == null) {
                throw new AutomationException(String.format("%s Page is not displayed or it takes time to load", pageName));
            }
        }
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
}


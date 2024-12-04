package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class GlobalContactPage extends BasePage {

    private static final String GLOBALCONTACT_BUTTON = "//span[text()='Global Contact']";
    private static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@name='contact.maidenName']";
    private static final String ENTITY_NAME_FIELD = "//input[@name='contact.entityName']";
    private static final String SSN_FIELD = "//input[@name='contact.ssn']";
    private static final String EIN_FIELD = "//input[@name='contact.ein']";
    private static final String SAVE_BUTTON = "//button[text()='Save']";
    private static final String CONFIRMATION_MESSAGE = "//div[text()='Contact details have been successfully saved.']";
    public static final String SPINNER = "//div[contains(class,'spinner')]";
    public static final String CREATE_INDIVIDUAL_CONTACT_BTN = "//button[text()='Create Individual Contact']";
    private static final String FIELD_DYNAMIC_XPATH = "//input[@name='%s']";
    public static final String FIELD_XPATH = "//label[text()='%s']/following-sibling::input";


    public void verifyLandingPage() {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForVisibleElement(By.xpath("//a[text()='Estate']"));
    }

    public void clickOnGlobalContactButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(GLOBALCONTACT_BUTTON).click();
    }

    public void verifyLandingOnGlobalContactPage() {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForVisibleElement(By.xpath("//a[text()='Global Contacts']"));
    }

    public void clickOnCreateButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(CREATE_BUTTON).click();
    }

    public void verifyLandingOnGlobalContactCreationPage() {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForVisibleElement(By.xpath("//a[text()='New']"));
    }

    public void verifyFieldPrefilled(String fieldName, String expectedValue) throws AutomationException {
        String fieldXpath = String.format(FIELD_DYNAMIC_XPATH, fieldName);
        WebElement fieldElement = driverUtil.getWebElementAndScroll(fieldXpath);
        String actualValue = fieldElement.getAttribute("value");

        if (!actualValue.equals(expectedValue)) {
            throw new AutomationException("Field " + fieldName + " is not pre-filled correctly. " + "Expected: " + expectedValue + ", Found: " + actualValue);
        }
        CommonSteps.logInfo("Field " + fieldName + " is pre-filled with value: " + expectedValue);
    }

    public void enterOptionalFields(String middleName, String maidenName, String entityName) throws AutomationException {
        WebElement middleNameField = driverUtil.getWebElementAndScroll(MIDDLE_NAME_FIELD);
        middleNameField.clear();
        middleNameField.sendKeys(middleName);

        WebElement maidenNameField = driverUtil.getWebElementAndScroll(MAIDEN_NAME_FIELD);
        maidenNameField.clear();
        maidenNameField.sendKeys(maidenName);

        WebElement entityNameField = driverUtil.getWebElementAndScroll(ENTITY_NAME_FIELD);
        entityNameField.clear();
        entityNameField.sendKeys(entityName);
    }

    public void enterSSNAndEIN(String ssn, String ein) throws AutomationException {
        WebElement SSNField = driverUtil.getWebElementAndScroll(SSN_FIELD);
        SSNField.clear();
        SSNField.sendKeys(ssn);

        WebElement EINField = driverUtil.getWebElementAndScroll(EIN_FIELD);
        EINField.clear();
        EINField.sendKeys(ein);
    }

    public void clickOnSaveButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(SAVE_BUTTON).click();
    }

    public void verifyConfirmationMessage() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(CONFIRMATION_MESSAGE);
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }

        CommonSteps.logInfo("Confirmation message is displayed successfully: " + confirmationElement.getText());
    }

    public void enterRequiredFields(String firstName, String lastName, String phoneNumber, String emailAddress) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement phoneNumberField = driverUtil.getWebElementAndScroll(PHONE_NUMBER_FIELD);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);

        WebElement emailField = driverUtil.getWebElementAndScroll(EMAIL_ADDRESS_FIELD);
        emailField.clear();
        emailField.sendKeys(emailAddress);
    }

    @Override
    String getName() {
        return "GLOBAL CONTACTS";
    }

    public void enterFirstnameAndLastNameFields(String firstName, String lastName) throws AutomationException {
        WebElement firstNameField = driverUtil.getWebElementAndScroll(FIRST_NAME_FIELD);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driverUtil.getWebElementAndScroll(LAST_NAME_FIELD);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void clicksOnCreateIndividualContactButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(CREATE_INDIVIDUAL_CONTACT_BTN).click();
    }

    public void verifyIndividualGlobalContactCreationgPage() {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForVisibleElement(By.xpath("//div[text()='Individual Details']"));
    }

    public boolean enterDataInFieldByLabel(String fieldData, String fieldName){
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

}

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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.waitForAWhile;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsRW03Page extends BasePage {

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String SSN_FIELD = "//input[@name='ssn']";
    private static final String PROCEED_BUTTON = "//button[@type='submit']";
    private static final String CREATE_NEW_ESTATE_BTN = "//button[contains(text(),'Create a new estate with the name')]";
    private static final String DECEDENT_MIDDLE_NAME = "//input[@name='decedentInfo.middleName']";
    private static final String DECEDENT_DISPLAY_NAME = "//input[@name='decedentInfo.displayNameAs']";
    private static final String DECEDENT_ALSO_KNOWN_AS = "//textarea[@name='decedentInfo.alsoKnownAs']";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String NEXT_BTN = "//button[text()='Next']";
    private static final String DOMICILE_ADDRESS_LINE1 = "//input[@name='domicileAddress.addressLine1']";
    private static final String DOMICILE_ADDRESS_LINE2 = "//input[@name='domicileAddress.addressLine2']";
    private static final String DOMICILE_ZIP = "//input[@name='domicileAddress.zip']";
    private static final String DOMICILE_CITY = "//input[@name='domicileAddress.city']";
    private static final String DOMICILE_STATE = "//div[text()='Last Address/Domicile']/following-sibling::div//input[@name='domicileAddress.city'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'State')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String DOMICILE_COUNTRY = "//input[@name='domicileAddress.county']";
    private static final String DOMICILE_MUNICIPALITY = "//input[@name='domicileAddress.municipality']";
    private static final String LAST_RESIDENCE_FIELD = "//input[@name='lifeDetails.lastResidence']";
    private static final String DATE_OF_BIRTH_FIELD = "//label[text()='Date of Birth']/following-sibling::div//div//input";
    private static final String DATE_OF_DEATH_FIELD = "//label[text()='Date of Death']/following-sibling::div//div//input";
    private static final String AGE_AT_DEATH_FIELD = "//input[@name='lifeDetails.ageAtDeath']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE1 = "//input[@name='placeOfDeath.addressLine1']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE2 = "//input[@name='placeOfDeath.addressLine2']";
    private static final String PLACE_OF_DEATH_ZIP = "//input[@name='placeOfDeath.zip']";
    private static final String PLACE_OF_DEATH_CITY = "//input[@name='placeOfDeath.city']";
    private static final String PLACE_OF_DEATH_STATE = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/label[text()='State']/following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_COUNTRY = "//input[@name='placeOfDeath.county']";
    private static final String ESTATE_TAB = "//button[@role='tab' and text()='Estate']";
    private static final String ESTATE_CHECKBOX_XPATH = "//input[contains(@aria-label,'%s')]";
    private static final String DATE_OF_WILL = "//label[text()='Date of Will']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_1 = "//label[text()='Codicil Date #1']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_2 = "//label[text()='Codicil Date #2']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_3 = "//label[text()='Codicil Date #3']/following-sibling::div//div//input";
    private static final String PROBATE_COURT_NAME = "//input[@name='probateCourtName']";
    private static final String PROBATE_COURT_LOCATION = "//input[@name='probateCourtLocation']";
    private static final String FILE_NUMBER_PART_1 = "//input[@name='fileNumberPart1']";
    private static final String FILE_NUMBER_PART_2 = "//input[@name='fileNumberPart2']";
    private static final String FILE_NUMBER_PART_3 = "//input[@name='fileNumberPart3']";
    private static final String ADDRESS_RADIO_BTN_XPATH = "//div//label[text()='%s']/preceding-sibling::input[@name='defaultAddress']";
    private static final String TOWNSHIP_RADIO = "//input[@name='domicileAddress.residenceType' and @value='1']";
    private static final String BOROUGH_RADIO = "//input[@name='domicileAddress.residenceType' and @value='2']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String WITNESS_NAME_1 = "//input[@name='witness1Name']";
    private static final String WITNESS_NAME_2 = "//input[@name='witness2Name']";
    private static final String WITNESS_1_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness1Name']";
    private static final String WITNESS_2_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness2Name']";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String TEMP = "//a[text()='Baby John']";

    static String decedentSSN;
    static String displayName;
    static String ageAtDeath;
    static String decedentAKA;
    static String domicileCounty;

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void selectCheckBox(String checkboxName) throws AutomationException {
        WebElement checkbox = driverUtil.getWebElement(String.format(ESTATE_CHECKBOX_XPATH, checkboxName));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectDefaultAddressRadioButton(String checkboxName) throws AutomationException {
        WebElement clickRadioBtn = driverUtil.getWebElement(String.format(ADDRESS_RADIO_BTN_XPATH, checkboxName));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();
        jsExecutor.executeScript("arguments[0].click();", clickRadioBtn);
    }

    public void fillEstateDetails() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        selectCheckBox("Use model accounting");
        clearField(DATE_OF_WILL);
        fillField(DATE_OF_WILL, "EstateCreate.dateOfWill");

        clearField(CODICILE_DATE_1);
        fillField(CODICILE_DATE_1, "EstateCreate.codicilDate1");
        actions.sendKeys(Keys.ENTER);
        clearField(CODICILE_DATE_2);
        fillField(CODICILE_DATE_2, "EstateCreate.codicilDate2");
        actions.sendKeys(Keys.ENTER);
        clearField(CODICILE_DATE_3);
        fillField(CODICILE_DATE_3, "EstateCreate.codicilDate3");
        actions.sendKeys(Keys.ENTER);

        clearField(PROBATE_COURT_NAME);
        fillField(PROBATE_COURT_NAME, "EstateCreate.probateCourtName");
        clearField(PROBATE_COURT_LOCATION);
        fillField(PROBATE_COURT_LOCATION, "EstateCreate.probateCourtLocation");
        clearField(FILE_NUMBER_PART_1);
        fillField(FILE_NUMBER_PART_1, "EstateCreate.fileNumberPart1");
        clearField(FILE_NUMBER_PART_2);
        fillField(FILE_NUMBER_PART_2, "EstateCreate.fileNumberPart2");
        clearField(FILE_NUMBER_PART_3);
        fillField(FILE_NUMBER_PART_3, "EstateCreate.fileNumberPart3");
        selectDefaultAddressRadioButton("Accountant");

        driverUtil.getWebElement("//body").click();

    }

    public static void verifyAutoFetchedFieldsOfPlaceOfDeathAddress() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODcity").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODstate").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODcountry").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(PLACE_OF_DEATH_CITY, "value");
        String actualState = getFieldValue(PLACE_OF_DEATH_STATE, "text");
        String actualCounty = getFieldValue(PLACE_OF_DEATH_COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public static void scrollPageToTop() throws AutomationException {
        WebElement body = DriverFactory.drivers.get().findElement(By.tagName("body"));
        body.click();
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
    }

    public void clickOnEstateTab() throws AutomationException {
        scrollPageToTop();
        driverUtil.getWebElement(ESTATE_TAB).click();
    }

    public void fillPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        clearField(PLACE_OF_DEATH_ADDRESS_LINE1);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE1, "EstateCreate.PODaddressLine1");
        clearField(PLACE_OF_DEATH_ADDRESS_LINE2);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE2, "EstateCreate.PODaddressLine2");
        clearField(PLACE_OF_DEATH_ZIP);
        fillField(PLACE_OF_DEATH_ZIP, "EstateCreate.PODzip");
        driverUtil.getWebElement(PLACE_OF_DEATH_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfPlaceOfDeathAddress();
    }

    public void selectMaritalStatusOptionOthers() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.maritalStatusOthers").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
    }

    public void fillLifeDetails() throws AutomationException, IOException, ParseException {

        Actions actions = new Actions(DriverFactory.drivers.get());

        clearField(LAST_RESIDENCE_FIELD);
        fillField(LAST_RESIDENCE_FIELD, "EstateCreate.lastResidence");


        clearField(DATE_OF_BIRTH_FIELD);
        fillField(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth");
        actions.sendKeys(Keys.ENTER);

        clearField(DATE_OF_DEATH_FIELD);
        fillField(DATE_OF_DEATH_FIELD, "EstateCreate.dateOfDeath");
        actions.sendKeys(Keys.ENTER);

        selectMaritalStatusOptionOthers();

        ageAtDeath = driverUtil.getWebElement(AGE_AT_DEATH_FIELD).getAttribute("value");
    }

    public static void verifyAutoFetchedFieldsOfDomicileAddress() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.city").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.country").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(DOMICILE_CITY, "value");
        String actualState = getFieldValue(DOMICILE_STATE, "text");
        String actualCounty = getFieldValue(DOMICILE_COUNTRY, "value");

        domicileCounty = getFieldValue(DOMICILE_COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public void fillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement townshipRadio = driverUtil.getWebElement(TOWNSHIP_RADIO);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();

        clearField(DOMICILE_ADDRESS_LINE1);
        fillField(DOMICILE_ADDRESS_LINE1, "EstateCreate.addressLine1");
        clearField(DOMICILE_ADDRESS_LINE2);
        fillField(DOMICILE_ADDRESS_LINE2, "EstateCreate.addressLine2");
        clearField(DOMICILE_ZIP);
        fillField(DOMICILE_ZIP, "EstateCreate.zip");
        driverUtil.getWebElement(DOMICILE_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfDomicileAddress();
        clearField(DOMICILE_MUNICIPALITY);
        fillField(DOMICILE_MUNICIPALITY, "EstateCreate.municipality");
        jsExecutor.executeScript("arguments[0].click();", townshipRadio);
    }

    public void clickOnNextButton() throws AutomationException {
        driverUtil.getWebElement(NEXT_BTN).click();
    }

    public void selectSuffixOption() throws AutomationException, IOException, ParseException {
        String suffixValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.suffix").toString();
        WebElement suffixDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Suffix"));
        suffixDropdown.click();
        WebElement suffixOption = driverUtil.getWebElement(String.format(SELECT_OPTION, suffixValue));
        suffixOption.click();
    }

    private static String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }


    public void fillDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        fillField(DECEDENT_MIDDLE_NAME, "EstateCreate.middleName");
        fillField(DECEDENT_DISPLAY_NAME, "EstateCreate.displayName");
        selectSuffixOption();
        fillField(DECEDENT_ALSO_KNOWN_AS, "EstateCreate.alsoKnownAs");
        displayName = getFieldValue(DECEDENT_DISPLAY_NAME, "value");
        decedentAKA = getFieldValue(DECEDENT_ALSO_KNOWN_AS, "value");
    }

    public void clickButtonCreate() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CREATE_BUTTON).click();
    }

    private void fillField(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        field.clear();
        field.sendKeys(CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString());
    }

    private void fillField(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString())
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

    public void enterFirstAndLastNameAndSSN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));

        fillField(FIRST_NAME_FIELD, "EstateCreate.firstName");
        fillField(LAST_NAME_FIELD, "EstateCreate.lastName");
        fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
        decedentSSN = randomSSN;
    }

    public void clickCreateNewEstateButton() throws AutomationException {
        driverUtil.getWebElement(CREATE_NEW_ESTATE_BTN).click();
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickOnProceedButton() throws AutomationException {
        driverUtil.getWebElement(PROCEED_BUTTON).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }


    @Override
    String getName() {
        return "";
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void selectAKACheckbox() throws AutomationException {
        driverUtil.getWebElement(SHOW_AKA_CHECkBOX).click();
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        verifyAutoPopulatedValue(domicileCounty);
        verifyAutoPopulatedValue(displayName);
        verifyAutoPopulatedValue(decedentAKA);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws Exception {
        WebElement field = driverUtil.getWebElement(fieldLocator);

        if (field.isEnabled()) {
            throw new Exception("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws Exception {
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, domicileCounty));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, displayName));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, decedentAKA));
    }

    public void verifyFieldsIsEmpty(String fieldLocator) throws Exception {
        WebElement field = driverUtil.getWebElement(fieldLocator);

        if (!field.getAttribute("value").isEmpty()) {
            throw new Exception("Field is not empty");
        }
    }

    public void verifyWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty() throws Exception {
        verifyFieldsIsEmpty(WITNESS_NAME_1);
        verifyFieldsIsEmpty(WITNESS_NAME_2);
    }

    public void clickOnPrintFormButton() throws AutomationException, AWTException, InterruptedException {
        driverUtil.getWebElement(PRINTFORM_BUTTON).click();

        // Use Robot to handle the Save As dialog
        Robot robot = new Robot();
        waitForAWhile(2); // Wait for the dialog to appear


        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void verifyFormPrintedInPDFForm(String formName) throws AutomationException {
            String downloadPath = ((System.getProperty(OS)==null || System.getProperty(OS)==WINDOWS)?System.getProperty("user.dir"):System.getProperty("user.dir").replace("\\", "/"))+"/Downloads";
            File pdfFile = new File(downloadPath, formName);

            // Wait for the file to be created
            int elapsedTime = 0;
            while (!pdfFile.exists() && elapsedTime < 10) { // Wait up to 10 seconds
               waitForAWhile(); // Wait for 1 second
                elapsedTime++;
            }

            // Validate if the file exists
            if (pdfFile.exists()) {
                CommonSteps.logInfo("PDF downloaded successfully: " + pdfFile.getAbsolutePath());
            } else {
                throw new AutomationException("PDF download failed. File not found: " + formName);
            }
        }

    public void verifyAllFieldsInDownloadedPDF() {

    }

    public void tempdelete() throws AutomationException, AWTException, InterruptedException {
        driverUtil.getWebElement(TEMP).click();
        navigateToEstateContactsTab();
    }
}

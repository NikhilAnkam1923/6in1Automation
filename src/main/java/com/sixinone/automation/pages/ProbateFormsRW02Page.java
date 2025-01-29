package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsRW02Page extends BasePage{
    @Override
    String getName() { return ""; }

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    private static final String TEMP_ESTATE = "//a[text()='%s']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
    private static final String DECEDENT_FIRST_NAME_FIELD = "//input[@name='decedentInfo.firstName']";
    private static final String DECEDENT_MIDDLE_NAME = "//input[@name='decedentInfo.middleName']";
    private static final String DECEDENT_LAST_NAME_FIELD = "//input[@name='decedentInfo.lastName']";
    private static final String DECEDENT_DISPLAY_NAME = "//input[@name='decedentInfo.displayNameAs']";
    private static final String SELECTED_SUFFIX = "//label[text()='Suffix']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String DECEDENT_SSN_FIELD = "//input[@name='decedentInfo.SSN']";
    private static final String DECEDENT_ALSO_KNOWN_AS = "//textarea[@name='decedentInfo.alsoKnownAs']";
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
    private static final String SELECTED_MARITAL_STATUS = "//div[text()='Life Details']/following-sibling::div//input[@name='lifeDetails.ageAtDeath'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'Marital Status')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE1 = "//input[@name='placeOfDeath.addressLine1']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE2 = "//input[@name='placeOfDeath.addressLine2']";
    private static final String PLACE_OF_DEATH_ZIP = "//input[@name='placeOfDeath.zip']";
    private static final String PLACE_OF_DEATH_CITY = "//input[@name='placeOfDeath.city']";
    private static final String PLACE_OF_DEATH_STATE = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/label[text()='State']/following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_COUNTRY = "//input[@name='placeOfDeath.county']";
    private static final String DATE_OF_WILL = "//label[text()='Date of Will']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_1 = "//label[text()='Codicil Date #1']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_2 = "//label[text()='Codicil Date #2']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_3 = "//label[text()='Codicil Date #3']/following-sibling::div//div//input";
    private static final String PROBATE_COURT_NAME = "//input[@name='probateCourtName']";
    private static final String PROBATE_COURT_LOCATION = "//input[@name='probateCourtLocation']";
    private static final String FILE_NUMBER_PART_1 = "//input[@name='fileNumberPart1']";
    private static final String FILE_NUMBER_PART_2 = "//input[@name='fileNumberPart2']";
    private static final String FILE_NUMBER_PART_3 = "//input[@name='fileNumberPart3']";
    private static final String ESTATE_TAB = "//button[@role='tab' and text()='Estate']";
    private static final String RW02_FETCHED_COUNTY = "//input[@class='noneditable Decedent_decd_county']";
    private static final String RW_AUTO_POPULATED_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String RW_AKA_FIELD1 = "//input[@name='alsoKnownAs1']";
    private static final String RW_AKA_FIELD2 = "//input[@name='alsoKnownAs2']";
    private static final String RW_AKA_FIELD3 = "//input[@name='alsoKnownAs3']";


    static String enteredFirstName;
    static String enteredMiddleName;
    static String enteredLastName;
    static String enteredDisplayName;
    static String selectedSuffix;
    static String enteredSSN;
    static String enteredAlsoKnownAs;
    static String enteredDomicileAddressLine1;
    static String enteredDomicileAddressLine2;
    static String enteredDomicileZip;
    static String enteredDomicileCity;
    static String selectedDomicileState;
    static String enteredDomicileCountry;
    static String enteredDomicileMunicipality;
    static String enteredLastResidence;
    static String enteredDateOfBirth;
    static String enteredDateOfDeath;
    static String enteredAgeAtDeath;
    static String selectedMaritalStatus;
    static String enteredPlaceOfDeathAddressLine1;
    static String enteredPlaceOfDeathAddressLine2;
    static String enteredPlaceOfDeathZip;
    static String enteredPlaceOfDeathCity;
    static String selectedPlaceOfDeathState;
    static String enteredPlaceOfDeathCountry;
    static String enteredDateOfWill;
    static String enteredCodicilDate1;
    static String enteredCodicilDate2;
    static String enteredCodicilDate3;
    static String enteredProbateCourtName;
    static String enteredProbateCourtLocation;
    static String enteredFileNumberPart1;
    static String enteredFileNumberPart2;
    static String enteredFileNumberPart3;


    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public static void filterByName(String name) throws AutomationException {
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        clearField(NAME_FILTER_INPUT);
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(name);
    }

    public void openEstate(String estateName) throws AutomationException {
        filterByName(estateName);
        driverUtil.getWebElement(String.format(TEMP_ESTATE,estateName)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void navigateToProbateFormsTab() throws AutomationException {
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH,formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void userSavesEstateInfo() throws AutomationException {
        enteredFirstName = driverUtil.getWebElement(DECEDENT_FIRST_NAME_FIELD).getAttribute("value");
        enteredMiddleName = driverUtil.getWebElement(DECEDENT_MIDDLE_NAME).getAttribute("value");
        enteredLastName = driverUtil.getWebElement(DECEDENT_LAST_NAME_FIELD).getAttribute("value");
        enteredDisplayName = driverUtil.getWebElement(DECEDENT_DISPLAY_NAME).getAttribute("value");
        selectedSuffix = driverUtil.getWebElement(SELECTED_SUFFIX).getText();
        enteredSSN = driverUtil.getWebElement(DECEDENT_SSN_FIELD).getAttribute("value");
        enteredAlsoKnownAs = driverUtil.getWebElement(DECEDENT_ALSO_KNOWN_AS).getAttribute("value");
        enteredDomicileAddressLine1 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE1).getAttribute("value");
        enteredDomicileAddressLine2 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE2).getAttribute("value");
        enteredDomicileZip = driverUtil.getWebElement(DOMICILE_ZIP).getAttribute("value");
        enteredDomicileCity = driverUtil.getWebElement(DOMICILE_CITY).getAttribute("value");
        selectedDomicileState = driverUtil.getWebElement(DOMICILE_STATE).getText();
        enteredDomicileCountry = driverUtil.getWebElement(DOMICILE_COUNTRY).getAttribute("value");
        enteredDomicileMunicipality = driverUtil.getWebElement(DOMICILE_MUNICIPALITY).getAttribute("value");
        enteredLastResidence = driverUtil.getWebElement(LAST_RESIDENCE_FIELD).getAttribute("value");
        enteredDateOfBirth = driverUtil.getWebElement(DATE_OF_BIRTH_FIELD).getAttribute("value");
        enteredDateOfDeath = driverUtil.getWebElement(DATE_OF_DEATH_FIELD).getAttribute("value");
        enteredAgeAtDeath = driverUtil.getWebElement(AGE_AT_DEATH_FIELD).getAttribute("value");
        selectedMaritalStatus = driverUtil.getWebElement(SELECTED_MARITAL_STATUS).getText();
        enteredPlaceOfDeathAddressLine1 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE1).getAttribute("value");
        enteredPlaceOfDeathAddressLine2 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE2).getAttribute("value");
        enteredPlaceOfDeathZip = driverUtil.getWebElement(PLACE_OF_DEATH_ZIP).getAttribute("value");
        enteredPlaceOfDeathCity = driverUtil.getWebElement(PLACE_OF_DEATH_CITY).getAttribute("value");
        selectedPlaceOfDeathState = driverUtil.getWebElement(PLACE_OF_DEATH_STATE).getText();
        enteredPlaceOfDeathCountry = driverUtil.getWebElement(PLACE_OF_DEATH_COUNTRY).getAttribute("value");

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        enteredDateOfWill = driverUtil.getWebElement(DATE_OF_WILL).getAttribute("value");
        enteredCodicilDate1 = driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value");
        enteredCodicilDate2 = driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value");
        enteredCodicilDate3 = driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value");
        enteredProbateCourtName = driverUtil.getWebElement(PROBATE_COURT_NAME).getAttribute("value");
        enteredProbateCourtLocation = driverUtil.getWebElement(PROBATE_COURT_LOCATION).getAttribute("value");
        enteredFileNumberPart1 = driverUtil.getWebElement(FILE_NUMBER_PART_1).getAttribute("value");
        enteredFileNumberPart2 = driverUtil.getWebElement(FILE_NUMBER_PART_2).getAttribute("value");
        enteredFileNumberPart3 = driverUtil.getWebElement(FILE_NUMBER_PART_3).getAttribute("value");
    }

    public void verifyCorrectCountyName() throws AutomationException {
        waitForVisibleElement(By.xpath(RW02_FETCHED_COUNTY));
        String countyOnForm = driverUtil.getWebElement(RW02_FETCHED_COUNTY).getAttribute("value");
        if(!countyOnForm.equals(enteredDomicileCountry)){
            throw new AutomationException("County name is not fetched correctly from the decedent info.");
        }
    }

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyDecedentInformationIsAutoPopulatedOnTheForm() throws AutomationException {
        verifyAutoPopulatedValue(enteredFirstName+" "+enteredLastName);
        verifyAutoPopulatedValue(enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3);
        verifyAutoPopulatedValue(enteredDateOfDeath);
        verifyAutoPopulatedValue(enteredSSN);
        verifyAutoPopulatedValue(enteredAgeAtDeath);
        verifyAutoPopulatedValue(enteredDomicileAddressLine1);
        verifyAutoPopulatedValue(enteredDomicileCity);
        verifyAutoPopulatedValue(enteredPlaceOfDeathAddressLine1);
        verifyAutoPopulatedValue(enteredPlaceOfDeathCity);
        verifyAutoPopulatedValue(enteredPlaceOfDeathCountry);
        verifyAutoPopulatedValue(enteredAlsoKnownAs);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws Exception {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.isEnabled()) {
            throw new Exception("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws Exception {
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDomicileCountry));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredFirstName+" "+enteredLastName));
//        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDateOfDeath));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredSSN));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredAgeAtDeath));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDomicileAddressLine1));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDomicileCity));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathAddressLine1));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathCity));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathCountry));
    }

    public void verifyMultipleAkaNamesCanBeAddedSeparatedByComma() throws IOException, ParseException, AutomationException {
        String akaName1 = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName1").toString();
        String akaName2 = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName2").toString();
        String akaName3 = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName3").toString();
        String akaNameInitial = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaNameInitial").toString();

        clearField(RW_AKA_FIELD1);
        driverUtil.getWebElement(RW_AKA_FIELD1).sendKeys(akaName1);
        clearField(RW_AKA_FIELD2);
        driverUtil.getWebElement(RW_AKA_FIELD2).sendKeys(akaName2);
        clearField(RW_AKA_FIELD3);
        driverUtil.getWebElement(RW_AKA_FIELD3).sendKeys(akaName3);
        CommonSteps.takeScreenshot();

        clearField(RW_AKA_FIELD1);
        driverUtil.getWebElement(RW_AKA_FIELD1).sendKeys(akaNameInitial);
        WebDriverUtil.waitForAWhile();
        clearField(RW_AKA_FIELD2);
        clearField(RW_AKA_FIELD3);
    }
}

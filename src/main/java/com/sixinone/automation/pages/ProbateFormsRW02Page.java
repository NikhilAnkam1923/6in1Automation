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
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
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
    private static final String RW_AUTO_POPULATED_MODAL_INPUT_FIELD_XPATH = "//div[@class='modal-body']//input[@type='text' and @value='%s']";
    private static final String RW_AKA_FIELD1 = "//input[@name='alsoKnownAs1']";
    private static final String RW_AKA_FIELD2 = "//input[@name='alsoKnownAs2']";
    private static final String RW_AKA_FIELD3 = "//input[@name='alsoKnownAs3']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    public static final String ESTATE_NAMES = "//tbody//td[@aria-colindex='1']";
    private static final String ESTATE_CONTACT_FIRST_NAME_FIELD = "//input[@name='contact.firstName']";
    private static final String ESTATE_CONTACT_MIDDLE_NAME = "//input[@name='contact.middleName']";
    private static final String ESTATE_CONTACT_LAST_NAME_FIELD = "//input[@name='contact.lastName']";
    private static final String ESTATE_CONTACT_SUFFIX = "//label[text()='Suffix']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String ESTATE_CONTACT_MAIDEN_NAME = "//input[@name='contact.maidenName']";
    private static final String ESTATE_CONTACT_ENTITY_NAME_FIELD = "//input[contains(@name,'entityName')]";
    private static final String ESTATE_CONTACT_EIN_FIELD = "//input[contains(translate(@name, 'EIN', 'ein'), 'ein')]";
    private static final String ESTATE_CONTACT_SELECTED_GENDER = "//label[text()='Gender']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String ESTATE_CONTACT_DATE_OF_BIRTH = "//label[text()='Date of Birth']/following-sibling::div//div//input";
    private static final String ESTATE_CONTACT_PLACE_OF_BIRTH = "//input[@name='contact.placeOfBirth']";
    private static final String ESTATE_CONTACT_PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String ESTATE_CONTACT_WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String ESTATE_CONTACT_EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String ESTATE_CONTACT_FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String ESTATE_CONTACT_SSN_FIELD = "//input[@name='contact.SSN']";
    private static final String ESTATE_CONTACT_ADDRESS_LINE1 = "//input[@name='address.addressLine1']";
    private static final String ESTATE_CONTACT_ADDRESS_LINE2 = "//input[@name='address.addressLine2']";
    private static final String ESTATE_CONTACT_ZIP = "//input[@name='address.zip']";
    private static final String ESTATE_CONTACT_ITY = "//input[@name='address.city']";
    private static final String ESTATE_CONTACT_STATE = "//label[contains(text(), 'State')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String ESTATE_CONTACT_COUNTY = "//input[@name='address.county']";
    private static final String ESTATE_CONTACT_COUNTRY = "//label[contains(text(), 'Country')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    public static final String ESTATE_ROLES = "//tbody//td[@aria-colindex='1']/following-sibling::td";
    private static final String NEXT_PAGE = "//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    private static final String VIEW_ATTACHMENT_HEADER = "//span[@class='view-attachment']";
    private static final String MODAL_CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String PERSONAL_PROPERTY_DRDWN = "//select[@name='allPersonalPropertyOption']";
    private static final String PENNSYLVANIA_PROPERTY_DRDWN = "//select[@name='propertyInPennsylvaniaOption']";
    private static final String COUNTY_PROPERTY_DRDWN = "//select[@name='propertyInCountyOption']";
    private static final String REAL_ESTATE_PROPERTY_DRDWN = "//select[@name='realEstateInPennsylvaniaOption']";

    private static final String PERSONAL_PROPERTY_AMOUNT = "//input[@name='allPersonalPropertyValue']";
    private static final String PENNSYLVANIA_PROPERTY_AMOUNT = "//input[@name='propertyInPennsylvaniaValue']";
    private static final String COUNTY_PROPERTY_AMOUNT = "//input[@name='propertyInCountyValue']";
    private static final String REAL_ESTATE_PROPERTY_AMOUNT = "//input[@name='realEstateInPennsylvaniaValue']";
    private static final String TOTAL_ESTIMATED_VALUE = "//input[@name='totalEstimatedValue']";

    HashMap<String, HashMap<String, String>> estateContact = new HashMap<>();

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
    static String personalProperty;
    static String pennsylvaniaProperty;
    static String countryProperty;
    static String realEstateProperty;
    static String enteredPersonalPropertyAmount;
    static String enteredPennsylvaniaPropertyAmount;
    static String enteredCountyPropertyAmount;
    static String enteredRealEstatePropertyAmount;


    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public static void clearFieldUsingJS(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
        js.executeScript("arguments[0].value = '';", fieldElement);
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

    public void verifyAutoPopulatedValueOnAttachment(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_AUTO_POPULATED_MODAL_INPUT_FIELD_XPATH, expectedValue));
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

        WebDriverUtil.waitForAWhile();
        clearField(RW_AKA_FIELD1);
        driverUtil.getWebElement(RW_AKA_FIELD1).sendKeys(akaNameInitial);
        WebDriverUtil.waitForAWhile();
        clearFieldUsingJS(RW_AKA_FIELD2);
        driverUtil.getWebElement(RW_AKA_FIELD2).sendKeys(" ");
        WebDriverUtil.waitForAWhile();
        clearField(RW_AKA_FIELD3);
        driverUtil.getWebElement(RW_AKA_FIELD3).sendKeys(" ");
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    private boolean clickNextPage() throws AutomationException {
        WebElement nextPageButton = driverUtil.getWebElement(NEXT_PAGE, 2);
        if (nextPageButton == null) {
            return false;
        }
        nextPageButton.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        return true;
    }

    public void userSavesEstateContactsInfo() throws AutomationException {


        do{
            List<WebElement> estateNames = driverUtil.getWebElements(ESTATE_NAMES);
            for (int i = 0; i < estateNames.size(); i++) {
                List<WebElement> EstateNames = driverUtil.getWebElements(ESTATE_NAMES);
                List<WebElement> EstateRoles = driverUtil.getWebElements(ESTATE_ROLES);
                WebElement estateName = EstateNames.get(i);
                WebElement estateRole = EstateRoles.get(i);
                estateName.click();
                waitForInvisibleElement(By.xpath(SPINNER));

                HashMap<String, String> estateContactInfo = new HashMap<>();

                estateContactInfo.put("firstName", driverUtil.getWebElement(ESTATE_CONTACT_FIRST_NAME_FIELD).getAttribute("value"));
                estateContactInfo.put("middleName", driverUtil.getWebElement(ESTATE_CONTACT_MIDDLE_NAME).getAttribute("value"));
                estateContactInfo.put("lastName", driverUtil.getWebElement(ESTATE_CONTACT_LAST_NAME_FIELD).getAttribute("value"));
                estateContactInfo.put("suffix", driverUtil.getWebElement(ESTATE_CONTACT_SUFFIX).getText());
                estateContactInfo.put("maidenName", driverUtil.getWebElement(ESTATE_CONTACT_MAIDEN_NAME).getAttribute("value"));
                estateContactInfo.put("entityName", driverUtil.getWebElement(ESTATE_CONTACT_ENTITY_NAME_FIELD).getAttribute("value"));
                estateContactInfo.put("ein", driverUtil.getWebElement(ESTATE_CONTACT_EIN_FIELD).getAttribute("value"));
                estateContactInfo.put("selectedGender", driverUtil.getWebElement(ESTATE_CONTACT_SELECTED_GENDER).getText());
                estateContactInfo.put("dateOfBirth", driverUtil.getWebElement(ESTATE_CONTACT_DATE_OF_BIRTH).getAttribute("value"));
                estateContactInfo.put("placeOfBirth", driverUtil.getWebElement(ESTATE_CONTACT_PLACE_OF_BIRTH).getAttribute("value"));
                estateContactInfo.put("phoneNumber", driverUtil.getWebElement(ESTATE_CONTACT_PHONE_NUMBER_FIELD).getAttribute("value"));
                estateContactInfo.put("workPhone", driverUtil.getWebElement(ESTATE_CONTACT_WORK_NUMBER_FIELD).getAttribute("value"));
                estateContactInfo.put("emailAddress", driverUtil.getWebElement(ESTATE_CONTACT_EMAIL_ADDRESS_FIELD).getAttribute("value"));
                estateContactInfo.put("faxNumber", driverUtil.getWebElement(ESTATE_CONTACT_FAX_FIELD).getAttribute("value"));
                estateContactInfo.put("ssn", driverUtil.getWebElement(ESTATE_CONTACT_SSN_FIELD).getAttribute("value"));
                estateContactInfo.put("addressLine1", driverUtil.getWebElement(ESTATE_CONTACT_ADDRESS_LINE1).getAttribute("value"));
                estateContactInfo.put("addressLine2", driverUtil.getWebElement(ESTATE_CONTACT_ADDRESS_LINE2).getAttribute("value"));
                estateContactInfo.put("zip", driverUtil.getWebElement(ESTATE_CONTACT_ZIP).getAttribute("value"));
                estateContactInfo.put("city", driverUtil.getWebElement(ESTATE_CONTACT_ITY).getAttribute("value"));
                estateContactInfo.put("state", driverUtil.getWebElement(ESTATE_CONTACT_STATE).getText());
                estateContactInfo.put("county", driverUtil.getWebElement(ESTATE_CONTACT_COUNTY).getAttribute("value"));
                estateContactInfo.put("country", driverUtil.getWebElement(ESTATE_CONTACT_COUNTRY).getText());
                estateContactInfo.put("role", estateRole.getText());

                estateContact.put(estateName.getText(),estateContactInfo);
            }
        } while (clickNextPage());
    }

    public void verifyFiduciaryTypeOfContactsAreDisplayed() throws AutomationException {
        String fiduciaryContact1 = estateContact.get("Clark, David").get("firstName")+" "+estateContact.get("Clark, David").get("middleName")+" "+estateContact.get("Clark, David").get("lastName")+" "+estateContact.get("Clark, David").get("suffix");
        String fiduciaryContact2 = estateContact.get("Harris, Daniel").get("firstName")+" "+estateContact.get("Harris, Daniel").get("middleName")+" "+estateContact.get("Harris, Daniel").get("lastName")+" "+estateContact.get("Harris, Daniel").get("suffix");
        String fiduciaryContact3 = estateContact.get("Lawns, Pritav").get("firstName")+" "+estateContact.get("Lawns, Pritav").get("middleName")+" "+estateContact.get("Lawns, Pritav").get("lastName")+" "+estateContact.get("Lawns, Pritav").get("suffix");
        String fiduciaryContact4 = estateContact.get("Smith, Michael").get("firstName")+" "+estateContact.get("Smith, Michael").get("middleName")+" "+estateContact.get("Smith, Michael").get("lastName")+" "+estateContact.get("Smith, Michael").get("suffix");
        String fiduciaryContact5 = estateContact.get("Wilson, Emily").get("firstName")+" "+estateContact.get("Wilson, Emily").get("middleName")+" "+estateContact.get("Wilson, Emily").get("lastName")+" "+estateContact.get("Wilson, Emily").get("suffix");

        verifyAutoPopulatedValue(fiduciaryContact1+", "+fiduciaryContact2+", "+fiduciaryContact3+", "+fiduciaryContact4+", "+fiduciaryContact5);
    }

    public void verifyNamesExceedTheLineTheContactsAreDisplayedInTheAttachment() throws AutomationException {
        String fiduciaryContact1 = estateContact.get("Clark, David").get("firstName")+" "+estateContact.get("Clark, David").get("middleName")+" "+estateContact.get("Clark, David").get("lastName")+" "+estateContact.get("Clark, David").get("suffix");
        String fiduciaryContact2 = estateContact.get("Harris, Daniel").get("firstName")+" "+estateContact.get("Harris, Daniel").get("middleName")+" "+estateContact.get("Harris, Daniel").get("lastName")+" "+estateContact.get("Harris, Daniel").get("suffix");
        String fiduciaryContact3 = estateContact.get("Lawns, Pritav").get("firstName")+" "+estateContact.get("Lawns, Pritav").get("middleName")+" "+estateContact.get("Lawns, Pritav").get("lastName")+" "+estateContact.get("Lawns, Pritav").get("suffix");
        String fiduciaryContact4 = estateContact.get("Smith, Michael").get("firstName")+" "+estateContact.get("Smith, Michael").get("middleName")+" "+estateContact.get("Smith, Michael").get("lastName")+" "+estateContact.get("Smith, Michael").get("suffix");
        String fiduciaryContact5 = estateContact.get("Wilson, Emily").get("firstName")+" "+estateContact.get("Wilson, Emily").get("middleName")+" "+estateContact.get("Wilson, Emily").get("lastName")+" "+estateContact.get("Wilson, Emily").get("suffix");

        driverUtil.getWebElement(VIEW_ATTACHMENT_HEADER).click();
        verifyAutoPopulatedValueOnAttachment(fiduciaryContact1+", "+fiduciaryContact2+", "+fiduciaryContact3+", "+fiduciaryContact4+", "+fiduciaryContact5);
        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
    }

    public void selectPropertyOption(String field, String selectOption) throws AutomationException {
        WebElement dropDown = driverUtil.getWebElement(field);
        Select select = new Select(dropDown);
        select.selectByValue(selectOption);
    }

    private String getSelectedOption(String field) throws AutomationException {
        WebElement dropDown = driverUtil.getWebElement(field);
        Select select = new Select(dropDown);
        return select.getFirstSelectedOption().getText();
    }

    public void userSelectsOptionsFromAllTheDropdowns() throws AutomationException {
        selectPropertyOption(PERSONAL_PROPERTY_DRDWN, "1");
        selectPropertyOption(PENNSYLVANIA_PROPERTY_DRDWN, "2");
        selectPropertyOption(COUNTY_PROPERTY_DRDWN, "3");
        selectPropertyOption(REAL_ESTATE_PROPERTY_DRDWN, "1");

        personalProperty = getSelectedOption(PERSONAL_PROPERTY_DRDWN);
        pennsylvaniaProperty = getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN);
        countryProperty = getSelectedOption(COUNTY_PROPERTY_DRDWN);
        realEstateProperty = getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN);
    }

    public void verifySelectedValuesAreRetainedAndAutoSaved() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        if(!getSelectedOption(PERSONAL_PROPERTY_DRDWN).equals(personalProperty)){
            throw new AutomationException("Selected value "+personalProperty+" is not retained");
        }

        if(!getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN).equals(pennsylvaniaProperty)){
            throw new AutomationException("Selected value "+pennsylvaniaProperty+" is not retained");
        }

        if(!getSelectedOption(COUNTY_PROPERTY_DRDWN).equals(countryProperty)){
            throw new AutomationException("Selected value "+countryProperty+" is not retained");
        }

        if(!getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN).equals(realEstateProperty)){
            throw new AutomationException("Selected value "+realEstateProperty+" is not retained");
        }

        CommonSteps.takeScreenshot();

        selectPropertyOption(PERSONAL_PROPERTY_DRDWN, "0");
        selectPropertyOption(PENNSYLVANIA_PROPERTY_DRDWN, "0");
        selectPropertyOption(COUNTY_PROPERTY_DRDWN, "0");
        selectPropertyOption(REAL_ESTATE_PROPERTY_DRDWN, "0");

    }

    public void verifyAmountCanBeEnteredInAllTheFieldsAndAutoSaved() throws AutomationException {
        WebElement personalPropertyAmount = driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT);
        WebElement pennsylvaniaPropertyAmount = driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT);
        WebElement countyPropertyAmount = driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT);
        WebElement realEstatePropertyAmount = driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT);

        clearField(PERSONAL_PROPERTY_AMOUNT);
        personalPropertyAmount.sendKeys("$220.00");
        clearField(PENNSYLVANIA_PROPERTY_AMOUNT);
        pennsylvaniaPropertyAmount.sendKeys("$430.00");
        clearField(COUNTY_PROPERTY_AMOUNT);
        countyPropertyAmount.sendKeys("$550.00");
        clearField(REAL_ESTATE_PROPERTY_AMOUNT);
        realEstatePropertyAmount.sendKeys("$670.00");
        realEstatePropertyAmount.sendKeys(Keys.TAB);

        enteredPersonalPropertyAmount = personalPropertyAmount.getAttribute("value").replace("$","");
        enteredPennsylvaniaPropertyAmount = pennsylvaniaPropertyAmount.getAttribute("value").replace("$","");
        enteredCountyPropertyAmount = countyPropertyAmount.getAttribute("value").replace("$","");
        enteredRealEstatePropertyAmount = realEstatePropertyAmount.getAttribute("value").replace("$","");
    }

    public void verifyAmountEnteredInAllTheFieldsAreAutoSaved() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        if(!driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT).getAttribute("value").replace("$","").equals(enteredPersonalPropertyAmount)){
            throw new AutomationException("Entered value "+enteredPersonalPropertyAmount+" is not retained");
        }

        if(!driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT).getAttribute("value").replace("$","").equals(enteredPennsylvaniaPropertyAmount)){
            throw new AutomationException("Entered value "+enteredPennsylvaniaPropertyAmount+" is not retained");
        }

        if(!driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT).getAttribute("value").replace("$","").equals(enteredCountyPropertyAmount)){
            throw new AutomationException("Entered value "+enteredCountyPropertyAmount+" is not retained");
        }

        if(!driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT).getAttribute("value").replace("$","").equals(enteredRealEstatePropertyAmount)){
            throw new AutomationException("Entered value "+enteredRealEstatePropertyAmount+" is not retained");
        }
    }
}

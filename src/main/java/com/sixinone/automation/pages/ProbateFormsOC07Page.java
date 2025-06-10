package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProbateFormsOC07Page extends BasePage {

    private final Map<String, String> estateInfo = new HashMap<>();
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
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
    private static final String FIRST_PAGE_ACTIVE_XPATH = "//a[@role='tab' and text()='1' and @class='nav-link active']";
    private static final String ESTATE_PAGE_1_XPATH = "//input[@value='%s']";
    private static final String COUNTY_PAGE_1_XPATH = "//div[@class='county-title']//span[@class='county-name']";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String OC_FORM_XPATH = "//a//p[text()='%s']";
    private static final String USE_4_DIGIT_YEAR_CHECKBOX = "//input[@name='isUseFourDigit']";
    private static final String SETTLOR_CHECKBOX = "//input[@name='isSettlorOrDeceased' and @value='1']";
    private static final String DECEASED_CHECKBOX = "//input[@name='isSettlorOrDeceased' and @value='0']";
    private static final String ESTATE_NAME_PAGE_2 = "//input[@id='Decedent_fullname']";
    private static final String ESTATE_NAME_PAGE_3 = "//input[@id='Decedent_fullname']";
    private static final String ESTATE_NAME_PAGE_4 = "//input[@id='Decedent_fullname']";
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String ESTATE_ADDRESS_FIELD1 = "//p[contains(text(), 'The Decedent, who resided at')]//span//input";
    private static final String ESTATE_ADDRESS_FIELD2 = "//p[contains(text(), 'The Decedent, who resided at')]/following-sibling::p//span[1]//input";
    private static final String DATE_OF_DEATH_FIELDOC7 = "//p[contains(text(), 'The Decedent, who resided at')]/following-sibling::p//span[2]//input";

    static String estateNameForm;
    static String fileNumberForm;

    public void userSavesEstateInfo() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();

        estateInfo.put("FirstName", getFieldValue(DECEDENT_FIRST_NAME_FIELD));
        estateInfo.put("MiddleName", getFieldValue(DECEDENT_MIDDLE_NAME));
        estateInfo.put("LastName", getFieldValue(DECEDENT_LAST_NAME_FIELD));
        estateInfo.put("DisplayName", getFieldValue(DECEDENT_DISPLAY_NAME));
        estateInfo.put("Suffix", getFieldValue(SELECTED_SUFFIX));
        estateInfo.put("SSN", getFieldValue(DECEDENT_SSN_FIELD));
        estateInfo.put("AlsoKnownAs", getFieldValue(DECEDENT_ALSO_KNOWN_AS));
        estateInfo.put("DomicileAddressLine1", getFieldValue(DOMICILE_ADDRESS_LINE1));
        estateInfo.put("DomicileAddressLine2", getFieldValue(DOMICILE_ADDRESS_LINE2));
        estateInfo.put("DomicileZip", getFieldValue(DOMICILE_ZIP));
        estateInfo.put("DomicileCity", getFieldValue(DOMICILE_CITY));
        estateInfo.put("DomicileState", getFieldValue(DOMICILE_STATE));
        estateInfo.put("DomicileCountry", getFieldValue(DOMICILE_COUNTRY));
        estateInfo.put("DomicileMunicipality", getFieldValue(DOMICILE_MUNICIPALITY));
        estateInfo.put("LastResidence", getFieldValue(LAST_RESIDENCE_FIELD));
        estateInfo.put("DateOfBirth", getFieldValue(DATE_OF_BIRTH_FIELD));
        estateInfo.put("DateOfDeath", getFieldValue(DATE_OF_DEATH_FIELD));
        estateInfo.put("AgeAtDeath", getFieldValue(AGE_AT_DEATH_FIELD));
        estateInfo.put("MaritalStatus", getFieldValue(SELECTED_MARITAL_STATUS));
        estateInfo.put("PlaceOfDeathAddressLine1", getFieldValue(PLACE_OF_DEATH_ADDRESS_LINE1));
        estateInfo.put("PlaceOfDeathAddressLine2", getFieldValue(PLACE_OF_DEATH_ADDRESS_LINE2));
        estateInfo.put("PlaceOfDeathZip", getFieldValue(PLACE_OF_DEATH_ZIP));
        estateInfo.put("PlaceOfDeathCity", getFieldValue(PLACE_OF_DEATH_CITY));
        estateInfo.put("PlaceOfDeathState", getFieldValue(PLACE_OF_DEATH_STATE));
        estateInfo.put("PlaceOfDeathCountry", getFieldValue(PLACE_OF_DEATH_COUNTRY));

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        estateInfo.put("DateOfWill", getFieldValue(DATE_OF_WILL));
        estateInfo.put("CodicilDate1", getFieldValue(CODICILE_DATE_1));
        estateInfo.put("CodicilDate2", getFieldValue(CODICILE_DATE_2));
        estateInfo.put("CodicilDate3", getFieldValue(CODICILE_DATE_3));
        estateInfo.put("ProbateCourtName", getFieldValue(PROBATE_COURT_NAME));
        estateInfo.put("ProbateCourtLocation", getFieldValue(PROBATE_COURT_LOCATION));
        estateInfo.put("FileNumberPart1", getFieldValue(FILE_NUMBER_PART_1));
        estateInfo.put("FileNumberPart2", getFieldValue(FILE_NUMBER_PART_2));
        estateInfo.put("FileNumberPart3", getFieldValue(FILE_NUMBER_PART_3));
    }

    private static String getFieldValue(String locator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            String value = field.getAttribute("value");
            return (value != null && !value.trim().isEmpty()) ? value.trim() : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    private String getEstateValue(String key) {
        return estateInfo.getOrDefault(key, "");
    }

    @Override
    String getName() {
        return "";
    }

    public void verifyEstateNameAddressDateOfDeathAndFileNumberAutofilled() throws AutomationException, IOException, ParseException, IOException, ParseException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);

        if (!page1.isDisplayed()) {
            throw new AutomationException("OC-07 Page 1 did not load correctly.");
        }

        // ✅ Estate Name
        String expectedEstateName = getEstateValue("DisplayName");
        WebElement estateNameElement = driverUtil.getWebElement(String.format(ESTATE_PAGE_1_XPATH, expectedEstateName));
        estateNameForm = estateNameElement.getAttribute("value");

        if (!estateNameForm.equals(expectedEstateName)) {
            throw new AutomationException("Estate name mismatch.\nExpected: " + expectedEstateName + "\nFound: " + estateNameForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Estate Name: " + estateNameForm);
        }

        // ✅ Address

        String expectedAddressLine1 = getEstateValue("DomicileAddressLine1");
        String expectedAddressLine2 = getEstateValue("DomicileAddressLine2");
        String expectedAddress3City = getEstateValue("DomicileCity");
        String expectedAddress4State = getEstateValue("DomicileState");
        String expectedAddress5Zip = getEstateValue("DomicileZip");

        if ("Pennsylvania".equalsIgnoreCase(expectedAddress4State)) {
            expectedAddress4State = "PA";
        }

        String expectedAddress = expectedAddressLine1 + ", " + expectedAddressLine2 + " " + expectedAddress3City +", "+expectedAddress4State +" " + expectedAddress5Zip;

        WebElement addressElement1 = driverUtil.getWebElement(ESTATE_ADDRESS_FIELD1);
        WebElement addressElement2 = driverUtil.getWebElement(ESTATE_ADDRESS_FIELD2);

        String addressForm1 = addressElement1.getAttribute("value");
        String addressForm2 = addressElement2.getAttribute("value");

        String addressForm = addressForm1 + " " + addressForm2;

        if (!expectedAddress.equalsIgnoreCase(addressForm)) {
            throw new AutomationException("Estate address mismatch.\nExpected: " + expectedAddress + "\nFound: " + addressForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Estate Address: " + addressForm);
        }

        // ✅ Date of Death
        String expectedDoD = getEstateValue("DateOfDeath");

        WebElement dodElement = driverUtil.getWebElement(DATE_OF_DEATH_FIELDOC7);
        String dodForm = dodElement.getAttribute("value");

        if (!expectedDoD.equals(dodForm)) {
            throw new AutomationException("Date of Death mismatch.\nExpected: " + expectedDoD + "\nFound: " + dodForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Date of Death: " + dodForm);
        }

        // ✅ File Number
        String part1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart1").toString();
        String part2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart2").toString();
        String part3 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart3").toString();
        String expectedFileNumber = part1 + "-" + part2 + "-" + part3;

        WebElement fileNumberElement = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberForm = fileNumberElement.getAttribute("value");

        if (!fileNumberForm.equals(expectedFileNumber)) {
            throw new AutomationException("File number mismatch.\nExpected: " + expectedFileNumber + "\nFound: " + fileNumberForm);
        } else {
            CommonSteps.logInfo("✅ Verified → File Number: " + fileNumberForm);
        }
    }


}

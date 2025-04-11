package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProbateFormsOC02Page extends BasePage{
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
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String FIRST_PAGE_ACTIVE_XPATH = "//a[@role='tab' and text()='1' and @class='nav-link active']";
    private static final String COUNTY_PAGE_1_XPATH = "//p[text()='COURT OF COMMON PLEAS OF']/following-sibling::p//span//input";
    private static final String TRUST_UNDER_WILL_RADIO_BTN = "//input[@name='trustIsUnderType' and @value='1']";
    private static final String TRUST_UNDER_DEED_RADIO_BTN = "//input[@name='trustIsUnderType' and @value='2']";
    private static final String TRUST_UNDER_WILL_TEXT_FIELD = "//p[contains(text(),'(TRUST UNDER WILL OF')]//input[@name='descendantName']";
    private static final String TRUST_UNDER_DEED_TEXT_FIELD = "//p[contains(text(),'TRUST UNDER DEED OF')]//input[@name='descendantName']";
    private static final String NAME_OF_TRUST_FIELD_2_AND_4_PAGE = "//p//span[contains(text(),'Name of Trust')]/following-sibling::input";
    private static final String NAME_OF_TRUST_FIELD_OTHER_PAGES = "//p[contains(text(),'Name of Trust')]//input";


    private final Map<String, String> estateInfo = new HashMap<>();

    static String countyNameForm;
    static String enteredDecedentNameForm;

    @Override
    String getName() {
        return "";
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

    private static void switchToPage(int pageNumber) throws AutomationException {
        driverUtil.getWebElement(String.format(PAGE_NUMBER_DYNAMIC_XPATH, pageNumber)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void userSavesEstateInfo() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();

        estateInfo.put("FirstName", getFieldValue(DECEDENT_FIRST_NAME_FIELD));
        estateInfo.put("MiddleName", getFieldValue(DECEDENT_MIDDLE_NAME));
        estateInfo.put("LastName", getFieldValue(DECEDENT_LAST_NAME_FIELD));
        estateInfo.put("DisplayName", getFieldValue(DECEDENT_DISPLAY_NAME));
//        estateInfo.put("Suffix", getFieldValue(SELECTED_SUFFIX));
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

    private String getEstateValue(String key) {
        return estateInfo.getOrDefault(key, "");
    }

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    public void verifyByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm() throws AutomationException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);
        WebElement countyName = driverUtil.getWebElement(COUNTY_PAGE_1_XPATH);
        String enteredCountyName = getEstateValue("DomicileCountry");
        countyNameForm = countyName.getAttribute("value");

        if(!page1.isDisplayed()){
            throw new AutomationException("On clicking OC02, page 1 is not opened by default.");
        }

        if(!countyNameForm.equals(enteredCountyName)){
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm);
        }
    }

    public void userChecksTrustUnderWillCheckbox() {
        scrollToElement(TRUST_UNDER_WILL_RADIO_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(TRUST_UNDER_WILL_RADIO_BTN)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyTrustUnderWillFieldIsEnabled() throws AutomationException {
        WebElement trustOfWillField = driverUtil.getWebElement(TRUST_UNDER_WILL_TEXT_FIELD);

        if(!trustOfWillField.isEnabled() && trustOfWillField.getAttribute("disabled") != null && trustOfWillField.getAttribute("readonly") != null){
            throw new AutomationException("Trust under Will text field is not gets enabled");
        }
    }

    public void userChecksTrustUnderDeedCheckbox() {
        scrollToElement(TRUST_UNDER_DEED_RADIO_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(TRUST_UNDER_DEED_RADIO_BTN)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyTrustUnderDeedFieldIsEnabled() throws AutomationException {
        WebElement trustOfDeedField = driverUtil.getWebElement(TRUST_UNDER_DEED_TEXT_FIELD);

        if(!trustOfDeedField.isEnabled() && trustOfDeedField.getAttribute("disabled") != null && trustOfDeedField.getAttribute("readonly") != null){
            throw new AutomationException("Trust under deed text field is not gets enabled");
        }
    }

    public void verifyNameIsDisplayedOnTheFormAgainstWillFieldAndOtherFieldIsEmpty() throws AutomationException, IOException, ParseException {
        String nameOfDecedent = CommonUtil.getJsonPath("OC02Form").get("OC02Form.nameOfDecedent").toString();
        WebElement trustUnderWillField = driverUtil.getWebElement(TRUST_UNDER_WILL_TEXT_FIELD);

        scrollToElement(TRUST_UNDER_WILL_TEXT_FIELD);
        trustUnderWillField.sendKeys(nameOfDecedent);
        trustUnderWillField.sendKeys(Keys.TAB);

        String trustUnderWillName = trustUnderWillField.getAttribute("value");
        if(!trustUnderWillName.equals(nameOfDecedent)){
            throw new AutomationException("Entered name is not displayed on the form against the Trust under will field");
        }

        String otherFieldText = driverUtil.getWebElement(TRUST_UNDER_DEED_TEXT_FIELD).getAttribute("value");
        if(!otherFieldText.isEmpty()){
            throw new AutomationException("Other field is not empty. Found text: "+otherFieldText);
        }

    }

    public void verifyNameIsDisplayedOnTheFormAgainstDeedFieldAndOtherFieldIsEmpty() throws IOException, ParseException, AutomationException {
        String nameOfDecedent = CommonUtil.getJsonPath("OC02Form").get("OC02Form.nameOfDecedent").toString();
        WebElement trustUnderDeedField = driverUtil.getWebElement(TRUST_UNDER_DEED_TEXT_FIELD);

        scrollToElement(TRUST_UNDER_DEED_TEXT_FIELD);
        trustUnderDeedField.sendKeys(nameOfDecedent);
        trustUnderDeedField.sendKeys(Keys.TAB);

        String trustUnderDeedName = trustUnderDeedField.getAttribute("value");
        if(!trustUnderDeedName.equals(nameOfDecedent)){
            throw new AutomationException("Entered name is not displayed on the form against the Trust under deed field");
        }

        String otherFieldText = driverUtil.getWebElement(TRUST_UNDER_WILL_TEXT_FIELD).getAttribute("value");
        if(!otherFieldText.isEmpty()){
            throw new AutomationException("Other field is not empty. Found text: "+otherFieldText);
        }

        enteredDecedentNameForm = trustUnderDeedField.getAttribute("value");
    }

    public void verifyEnteredNameOfDecedentIsDisplayedOnAllTheOCPages() throws AutomationException {
        for(int i=2; i<=11; i++){
            switchToPage(i);
            String actualNameOfTrust;
            if(i==2 || i==4){
                actualNameOfTrust = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_2_AND_4_PAGE).getAttribute("value");
            } else {
                actualNameOfTrust = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES).getAttribute("value");
            }

            if(!actualNameOfTrust.equals(enteredDecedentNameForm)){
                throw new AutomationException("Decedent Name is not reflected on page: "+i+". Expected: "+enteredDecedentNameForm+" ,Found: "+actualNameOfTrust);
            }
        }

    }
}

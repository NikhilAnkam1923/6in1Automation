package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.FileUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsRW06Page extends BasePage {
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
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String HEADER_COUNTY_FIELD_XPATH = "//p[contains(text(),'%s')]";
    private static final String DATE_FIELD = "//input[@name='formItems[%s].signedDate']";
    private static final String LETTERS_ISSUED_TO_FIELD = "//input[@name='formItems[%s].letterIssuedTo']";
    private static final String CORPORATE_FIDUCIARY_SIGN_FIELD = "//div[@id='id_2_1']//p[contains(text(),'Name or Corporate Fiduciary (if applicable)')]/preceding-sibling::p//input";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String SAVE_BTN = "//button[text()='Save']";
    private static final String UNDERSIGNED_CORPORATE_NAME = "//tr//td//p[contains(text(),'The undersigned')]/ancestor::td/following-sibling::td//p//input";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String SIGN_OF_REPRESENTATIVE = "//input[contains(@name,'officerSignature') and not(@value='')]";
    private static final String CORPORATE_FIDUCIARY_ADDRESS = "//div[@id='id_2_1']//p[text()='Address']/preceding-sibling::p//textarea[text()='%s']";
    private static final String CORPORATE_FIDUCIARY_CITY_STATE_ZIP = "//div[@id='id_2_1']//p[text()='City, State, Zip']/preceding-sibling::p//span//input[@disabled and not(@value='')]";
    private static final String CORPORATE_FIDUCIARY_TELEPHONE = "//div[@id='id_2_1']//p[text()='City, State, Zip']/following-sibling::p//span/input[@disabled and @value='%s']";
    private static final String CORPORATE_FIDUCIARY_EMAIL = "//div[@id='id_2_1']//p[text()='Telephone']/following-sibling::p//span/input[@disabled and not(@value='')]";
    private static final String BENEFICIARY_SIGN_FIELD = "//div[@id='id_2_2']//p[text()='Signature']//span//input";
    private static final String SIGN_OF_BENEFICIARY = "//div[@id='id_2_2']//p[text()='Signature']//span//input[not(@value='')]";
    private static final String BENEFICIARY_ADDRESS = "//div[@id='id_2_2']//p[text()='Address']/preceding-sibling::p//textarea[text()='%s']";
    private static final String BENEFICIARY_CITY_STATE_ZIP = "//div[@id='id_2_2']//p[text()='City, State, Zip']/preceding-sibling::p//span//input[@disabled and not(@value='')]";
    private static final String BENEFICIARY_TELEPHONE = "//div[@id='id_2_2']//p[text()='City, State, Zip']/following-sibling::p//span/input[@disabled and @value='%s']";
    private static final String BENEFICIARY_EMAIL = "//div[@id='id_2_2']//p[text()='Telephone']/following-sibling::p//span/input[@disabled and not(@value='')]";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String PRINT_FORM_TOOLTIP = "//div[@role='tooltip']";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String downloadedFileName;

    static String CorporateFiduciary1Form;
    static String CorporateFiduciary2Form;
    static String CorporateFiduciary3Form;
    static String CorporateFiduciary4Form;
    static String CorporateFiduciary5Form;
    static String Beneficiary1Form;
    static String Beneficiary2Form;
    static String Beneficiary3Form;
    static String Beneficiary4Form;
    static String Beneficiary5Form;
    static String dateDataForm1;
    static String dateDataForm2;
    static String dateDataForm3;
    static String dateDataForm4;
    static String dateDataForm5;
    static String dateDataForm6;
    static String dateDataForm7;
    static String dateDataForm8;
    static String dateDataForm9;
    static String dateDataForm10;
    static String reasonDataForm1;
    static String reasonDataForm2;
    static String reasonDataForm3;
    static String reasonDataForm4;
    static String reasonDataForm5;
    static String reasonDataForm6;
    static String reasonDataForm7;
    static String reasonDataForm8;
    static String reasonDataForm9;
    static String reasonDataForm10;
    static String corporateFiduciary1NameForm;
    static String corporateFiduciary1AddressForm;
    static String corporateFiduciary1CityStateZipForm;
    static String corporateFiduciary1TelephoneForm;
    static String corporateFiduciary1EmailForm;
    static String corporateFiduciary2NameForm;
    static String corporateFiduciary2AddressForm;
    static String corporateFiduciary2CityStateZipForm;
    static String corporateFiduciary2TelephoneForm;
    static String corporateFiduciary2EmailForm;
    static String corporateFiduciary3NameForm;
    static String corporateFiduciary3AddressForm;
    static String corporateFiduciary3CityStateZipForm;
    static String corporateFiduciary3TelephoneForm;
    static String corporateFiduciary3EmailForm;
    static String corporateFiduciary4NameForm;
    static String corporateFiduciary4AddressForm;
    static String corporateFiduciary4CityStateZipForm;
    static String corporateFiduciary4TelephoneForm;
    static String corporateFiduciary4EmailForm;
    static String corporateFiduciary5NameForm;
    static String corporateFiduciary5AddressForm;
    static String corporateFiduciary5CityStateZipForm;
    static String corporateFiduciary5TelephoneForm;
    static String corporateFiduciary5EmailForm;
    static String beneficiary1NameForm;
    static String beneficiary1AddressForm;
    static String beneficiary1CityStateZipForm;
    static String beneficiary1TelephoneForm;
    static String beneficiary1EmailForm;
    static String beneficiary2NameForm;
    static String beneficiary2AddressForm;
    static String beneficiary2CityStateZipForm;
    static String beneficiary2TelephoneForm;
    static String beneficiary2EmailForm;
    static String beneficiary3NameForm;
    static String beneficiary3AddressForm;
    static String beneficiary3CityStateZipForm;
    static String beneficiary3TelephoneForm;
    static String beneficiary3EmailForm;
    static String beneficiary4NameForm;
    static String beneficiary4AddressForm;
    static String beneficiary4CityStateZipForm;
    static String beneficiary4TelephoneForm;
    static String beneficiary4EmailForm;
    static String beneficiary5NameForm;
    static String beneficiary5AddressForm;
    static String beneficiary5CityStateZipForm;
    static String beneficiary5TelephoneForm;
    static String beneficiary5EmailForm;
    static String signatureOfficerOrRepresentative1Form;
    static String signatureOfficerOrRepresentative2Form;
    static String signatureOfficerOrRepresentative3Form;
    static String signatureOfficerOrRepresentative4Form;
    static String signatureOfficerOrRepresentative5Form;
    static String domicileCountryForm;
    static String displayNameForm;
    static String alsoKnownAsForm;


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

    public void userSavesEstateInfo() throws AutomationException, IOException, ParseException {
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

    private String getEstateValue(String key) {
        return estateInfo.getOrDefault(key, "");
    }

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyCountyInHeader(String countyName) throws AutomationException {
        WebElement county = driverUtil.getWebElement(String.format(HEADER_COUNTY_FIELD_XPATH, countyName));

        if (county != null && county.getText().contains(countyName)) {
            CommonSteps.logInfo("County is auto-populated correctly: " + countyName);
        } else {
            throw new AutomationException("County is not auto-populated correctly. Expected: " + countyName);
        }
    }

    public void verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        domicileCountryForm = getEstateValue("DomicileCountry");
        displayNameForm = getEstateValue("DisplayName");
        alsoKnownAsForm = getEstateValue("AlsoKnownAs");

        verifyCountyInHeader(domicileCountryForm);
        verifyAutoPopulatedValue(displayNameForm);
        verifyAutoPopulatedValue(alsoKnownAsForm);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.isEnabled() && field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        String displayNameField = String.format(RW_INPUT_FIELD_XPATH, displayNameForm);
        String alsoKnownAsField = String.format(RW_INPUT_FIELD_XPATH, alsoKnownAsForm);

        WebDriverUtil.waitForAWhile(2);
        verifyFieldIsNotEditable(displayNameField);
        verifyFieldIsNotEditable(alsoKnownAsField);
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    public void userSelectsMultipleCorporateFiduciaryContacts() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        scrollToElementAndClick(CORPORATE_FIDUCIARY_SIGN_FIELD);

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        CorporateFiduciary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
    }

    public void verifyCorporateFiduciaryNameIsReflectedOnEachForm() throws AutomationException {
        List<String> expectedNames = Arrays.asList(
                CorporateFiduciary1Form,
                CorporateFiduciary4Form,
                CorporateFiduciary5Form,
                CorporateFiduciary3Form,
                CorporateFiduciary2Form
        );

        List<WebElement> CorporateNameFields = driverUtil.getWebElements(UNDERSIGNED_CORPORATE_NAME);
        List<WebElement> CorporateFiduciarySignFields = driverUtil.getWebElements(CORPORATE_FIDUCIARY_SIGN_FIELD);

        for (int i = 0; i < expectedNames.size(); i++) {
            String expectedName = expectedNames.get(i).trim();
            String actualCorporateName = CorporateNameFields.get(i).getAttribute("value").trim();
            String actualCorporateFiduciarySign = CorporateFiduciarySignFields.get(i).getAttribute("value").trim();

            if (!actualCorporateName.equals(expectedName)) {
                throw new AutomationException("Corporate name is not reflected, Expected: " + expectedName + " ,Found: " + actualCorporateName);
            }

            if (!actualCorporateFiduciarySign.equals(expectedName)) {
                throw new AutomationException("Corporate Fiduciary Sign is not reflected, Expected: " + expectedName + " ,Found: " + actualCorporateFiduciarySign);
            }
        }
    }

    public void verifyFormIsRepeatedBasedOnTheNumberOfContactsSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                CorporateFiduciary1Form,
                CorporateFiduciary4Form,
                CorporateFiduciary5Form,
                CorporateFiduciary3Form,
                CorporateFiduciary2Form
        );

        List<WebElement> CorporateFiduciarySignFields = driverUtil.getWebElements(SIGN_OF_REPRESENTATIVE);

        if (CorporateFiduciarySignFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + CorporateFiduciarySignFields.size() + " forms");
        }
    }

    public void userResetsTheRWForm() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        actions.moveToElement(driverUtil.getWebElement(PRINTFORM_BUTTON), -50, -50).perform();
        WebDriverUtil.waitForInvisibleElement(By.xpath(PRINT_FORM_TOOLTIP));

        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(CORPORATE_FIDUCIARY_SIGN_FIELD);

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Fiduciary contacts updated successfully.")));

        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(BENEFICIARY_SIGN_FIELD);

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiary contacts updated successfully.")));

        scrollToElementAndClick(SHOW_AKA_CHECkBOX);
        WebDriverUtil.waitForAWhile(2);
    }

    public void verifyCorrectCorporateFiduciaryContactDetailsAreDisplayedOnEachForm() throws AutomationException, IOException, ParseException {
        List<Integer> corporateFiduciaryNum = Arrays.asList(3, 5, 2, 4, 1);

        for (int i = 0; i < corporateFiduciaryNum.size(); i++) {
            int fiduciaryIndex = corporateFiduciaryNum.get(i);
            String fiduciaryKey = "corporateFiduciary" + fiduciaryIndex;

            String expectedFirstName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".firstName").toString();
            String expectedMiddleName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".middleName").toString();
            String expectedLastName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".lastName").toString();
            String expectedSuffix = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".suffix").toString();
            String expectedCity = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".city").toString();
            String expectedState = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".zip").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".addressLine1").toString();
            String expectedTelephone = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".workNumber").toString();
            String expectedEmail = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".emailId").toString();

            String expectedFullName = expectedFirstName + " " + expectedMiddleName + " " + expectedLastName + "," + " " + expectedSuffix;
            String expectedCityStateZip = expectedCity + ", " + expectedState + " " + expectedZip;


            List<WebElement> nameFields = driverUtil.getWebElements(SIGN_OF_REPRESENTATIVE);
            String actualName = nameFields.get(i).getAttribute("value");

            WebElement addressElement = driverUtil.getWebElement(String.format(CORPORATE_FIDUCIARY_ADDRESS, expectedAddressLine1));
            String actualAddress = addressElement.getText();

            List<WebElement> cityStateZipElement = driverUtil.getWebElements(CORPORATE_FIDUCIARY_CITY_STATE_ZIP);
            String actualCityStateZip = cityStateZipElement.get(i).getAttribute("value");

            WebElement telephoneElement = driverUtil.getWebElement(String.format(CORPORATE_FIDUCIARY_TELEPHONE, expectedTelephone));
            String actualTelephone = telephoneElement.getAttribute("value");

            List<WebElement> emailElement = driverUtil.getWebElements(CORPORATE_FIDUCIARY_EMAIL);
            String actualEmail = emailElement.get(i).getAttribute("value");

            switch (fiduciaryIndex) {
                case 3:
                    corporateFiduciary1NameForm = actualName;
                    corporateFiduciary1AddressForm = actualAddress;
                    corporateFiduciary1CityStateZipForm = actualCityStateZip;
                    corporateFiduciary1TelephoneForm = actualTelephone;
                    corporateFiduciary1EmailForm = actualEmail;
                    signatureOfficerOrRepresentative1Form = expectedFullName;
                    break;
                case 5:
                    corporateFiduciary2NameForm = actualName;
                    corporateFiduciary2AddressForm = actualAddress;
                    corporateFiduciary2CityStateZipForm = actualCityStateZip;
                    corporateFiduciary2TelephoneForm = actualTelephone;
                    corporateFiduciary2EmailForm = actualEmail;
                    signatureOfficerOrRepresentative2Form = expectedFullName;
                    break;
                case 2:
                    corporateFiduciary3NameForm = actualName;
                    corporateFiduciary3AddressForm = actualAddress;
                    corporateFiduciary3CityStateZipForm = actualCityStateZip;
                    corporateFiduciary3TelephoneForm = actualTelephone;
                    corporateFiduciary3EmailForm = actualEmail;
                    signatureOfficerOrRepresentative3Form = expectedFullName;
                    break;
                case 4:
                    corporateFiduciary4NameForm = actualName;
                    corporateFiduciary4AddressForm = actualAddress;
                    corporateFiduciary4CityStateZipForm = actualCityStateZip;
                    corporateFiduciary4TelephoneForm = actualTelephone;
                    corporateFiduciary4EmailForm = actualEmail;
                    signatureOfficerOrRepresentative4Form = expectedFullName;
                    break;
                case 1:
                    corporateFiduciary5NameForm = actualName;
                    corporateFiduciary5AddressForm = actualAddress;
                    corporateFiduciary5CityStateZipForm = actualCityStateZip;
                    corporateFiduciary5TelephoneForm = actualTelephone;
                    corporateFiduciary5EmailForm = actualEmail;
                    signatureOfficerOrRepresentative5Form = expectedFullName;
                    break;
            }

            if (!actualName.equals(expectedFullName)) {
                throw new AutomationException("Mismatch in Corporate Fiduciary Name. Expected: " + expectedFullName + ", Found: " + actualName);
            }

            if (!actualAddress.equals(expectedAddressLine1)) {
                throw new AutomationException("Address mismatch for " + expectedFullName);
            }

            if (!actualCityStateZip.equals(expectedCityStateZip)) {
                throw new AutomationException("City, State, Zip not populated correctly for " + expectedFullName);
            }

            if (!actualTelephone.equals(expectedTelephone)) {
                throw new AutomationException("Telephone mismatch for " + expectedFullName);
            }

            if (!actualEmail.equals(expectedEmail)) {
                throw new AutomationException("Email mismatch for " + expectedFullName);
            }
        }
    }

    public void verifyMultipleBeneficiaryContactsCanBeSelected() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        scrollToElementAndClick(BENEFICIARY_SIGN_FIELD);

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        Beneficiary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
    }

    public void verifyFormIsRepeatedBasedOnTheNumberOfBeneficiaryContactsSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary5Form,
                Beneficiary3Form,
                Beneficiary2Form
        );

        List<WebElement> BeneficiarySignFields = driverUtil.getWebElements(SIGN_OF_BENEFICIARY);

        if (BeneficiarySignFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + BeneficiarySignFields.size() + " forms");
        }
    }

    public void verifyCorrectBeneficiaryContactDetailsAreDisplayedOnEachForm() throws IOException, ParseException, AutomationException {
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary5Form,
                Beneficiary3Form,
                Beneficiary2Form
        );

        List<Integer> beneficiaryNum = Arrays.asList(1, 5, 4, 2, 3);

        for (int i = 0; i < beneficiaryNum.size(); i++) {
            int beneficiaryIndex = beneficiaryNum.get(i);
            String beneficiaryKey = "beneficiary" + beneficiaryIndex;

            String expectedCity = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".city").toString();
            String expectedState = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".zip").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".addressLine1").toString();
            String expectedTelephone = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".workNumber").toString();
            String expectedEmail = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".emailId").toString();

            String expectedName = expectedNames.get(i).trim();
            String expectedCityStateZip = expectedCity + ", " + expectedState + " " + expectedZip;

            List<WebElement> nameFields = driverUtil.getWebElements(SIGN_OF_BENEFICIARY);
            String actualName = nameFields.get(i).getAttribute("value");

            WebElement addressElement = driverUtil.getWebElement(String.format(BENEFICIARY_ADDRESS, expectedAddressLine1));
            String actualAddress = addressElement.getText();

            List<WebElement> cityStateZipElement = driverUtil.getWebElements(BENEFICIARY_CITY_STATE_ZIP);
            String actualCityStateZip = cityStateZipElement.get(i).getAttribute("value");

            WebElement telephoneElement = driverUtil.getWebElement(String.format(BENEFICIARY_TELEPHONE, expectedTelephone));
            String actualTelephone = telephoneElement.getAttribute("value");

            List<WebElement> emailElement = driverUtil.getWebElements(BENEFICIARY_EMAIL);
            String actualEmail = emailElement.get(i).getAttribute("value");

            switch (beneficiaryIndex) {
                case 1:
                    beneficiary1NameForm = actualName;
                    beneficiary1AddressForm = actualAddress;
                    beneficiary1CityStateZipForm = actualCityStateZip;
                    beneficiary1TelephoneForm = actualTelephone;
                    beneficiary1EmailForm = actualEmail;
                    break;
                case 5:
                    beneficiary2NameForm = actualName;
                    beneficiary2AddressForm = actualAddress;
                    beneficiary2CityStateZipForm = actualCityStateZip;
                    beneficiary2TelephoneForm = actualTelephone;
                    beneficiary2EmailForm = actualEmail;
                    break;
                case 4:
                    beneficiary3NameForm = actualName;
                    beneficiary3AddressForm = actualAddress;
                    beneficiary3CityStateZipForm = actualCityStateZip;
                    beneficiary3TelephoneForm = actualTelephone;
                    beneficiary3EmailForm = actualEmail;
                    break;
                case 2:
                    beneficiary4NameForm = actualName;
                    beneficiary4AddressForm = actualAddress;
                    beneficiary4CityStateZipForm = actualCityStateZip;
                    beneficiary4TelephoneForm = actualTelephone;
                    beneficiary4EmailForm = actualEmail;
                    break;
                case 3:
                    beneficiary5NameForm = actualName;
                    beneficiary5AddressForm = actualAddress;
                    beneficiary5CityStateZipForm = actualCityStateZip;
                    beneficiary5TelephoneForm = actualTelephone;
                    beneficiary5EmailForm = actualEmail;
                    break;
            }

            if (!actualName.equals(expectedName)) {
                throw new AutomationException("Mismatch in Beneficiary Name. Expected: " + expectedName + ", Found: " + actualName);
            }

            if (!actualAddress.equals(expectedAddressLine1)) {
                throw new AutomationException("Address mismatch for " + expectedName);
            }

            if (!actualCityStateZip.equals(expectedCityStateZip)) {
                throw new AutomationException("City, State, Zip not populated correctly for " + expectedName);
            }

            if (!actualTelephone.equals(expectedTelephone)) {
                throw new AutomationException("Telephone mismatch for " + expectedName);
            }

            if (!actualEmail.equals(expectedEmail)) {
                throw new AutomationException("Email mismatch for " + expectedName);
            }
        }
    }

    public void userEntersDateAndReasonDetailsOnEachForm() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiary contacts updated successfully.")));

        dateDataForm1 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm1").toString();
        dateDataForm2 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm2").toString();
        dateDataForm3 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm3").toString();
        dateDataForm4 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm4").toString();
        dateDataForm5 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm5").toString();
        dateDataForm6 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm6").toString();
        dateDataForm7 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm7").toString();
        dateDataForm8 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm8").toString();
        dateDataForm9 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm9").toString();
        dateDataForm10 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.dateDataForm10").toString();
        reasonDataForm1 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm1").toString();
        reasonDataForm2 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm2").toString();
        reasonDataForm3 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm3").toString();
        reasonDataForm4 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm4").toString();
        reasonDataForm5 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm5").toString();
        reasonDataForm6 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm6").toString();
        reasonDataForm7 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm7").toString();
        reasonDataForm8 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm8").toString();
        reasonDataForm9 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm9").toString();
        reasonDataForm10 = CommonUtil.getJsonPath("RW06Form").get("RW06Form.reasonDataForm10").toString();

        List<String> dateDataForm = Arrays.asList(
                dateDataForm1, dateDataForm2, dateDataForm3, dateDataForm4, dateDataForm5,
                dateDataForm6, dateDataForm7, dateDataForm8, dateDataForm9, dateDataForm10
        );

        List<String> reasonDataForm = Arrays.asList(
                reasonDataForm1, reasonDataForm2, reasonDataForm3, reasonDataForm4, reasonDataForm5,
                reasonDataForm6, reasonDataForm7, reasonDataForm8, reasonDataForm9, reasonDataForm10
        );

        Actions actions = new Actions(DriverFactory.drivers.get());

        for (int i = 0; i < 10; i++) {
            WebDriverUtil.waitForAWhile();
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELD, i)));

            scrollToElementAndClick(String.format(DATE_FIELD, i));
            dateField.clear();
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(dateDataForm.get(i)), null);

            actions.moveToElement(dateField)
                    .click()
                    .keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();

            driverUtil.getWebElement("//body").click();

            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELD, i))).getAttribute("value");

            WebDriverUtil.waitForAWhile();
            WebElement reasonField = driverUtil.getWebElementAndScroll(String.format(LETTERS_ISSUED_TO_FIELD, i));

            reasonField.sendKeys(Keys.SPACE);
            reasonField.sendKeys(Keys.BACK_SPACE);
            reasonField.sendKeys(reasonDataForm.get(i));

            driverUtil.getWebElement("//body").click();

            WebDriverUtil.waitForAWhile(2);
            String actualReason = DriverFactory.drivers.get().findElement(By.xpath(String.format(LETTERS_ISSUED_TO_FIELD, i))).getAttribute("value");


            if (!actualDate.equals(dateDataForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered date correctly. Expected: " + dateDataForm.get(i) + ", Found: " + actualDate);
            }

            if (!actualReason.equals(reasonDataForm.get(i))) {
                throw new AutomationException("Reason field did not accept the entered text correctly. Expected: " + reasonDataForm.get(i) + ", Found: " + actualReason);
            }
        }
    }

    public void verifyFormPrintedInPDFForm(String fileName) throws AutomationException {
        boolean isFileFound = false;
        int counter = 0;
        File[] files = null;
        do {
            try {
                files = FileUtil.getAllFiles((System.getProperty(OS) == null || System.getProperty(OS).equals(WINDOWS))
                        ? System.getProperty("user.dir") + "\\downloads"
                        : System.getProperty("user.dir").replace("\\", "/") + "/downloads");

                CommonSteps.logInfo("Iterating over files");
                for (File file : files) {
                    if (file.exists() && !file.isDirectory()) {
                        CommonSteps.logInfo(file.getName());
                        downloadedFileName = file.getName();

                        // Check if file is a PDF
                        if (file.getName().toLowerCase().endsWith(".pdf")) {
                            // Check if the file name matches the expected file name
                            if (file.getName().toLowerCase().contains(fileName.toLowerCase())) {
                                isFileFound = true;
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            counter++;
            WebDriverUtil.waitForAWhile(10);
        } while (!isFileFound && counter < 5);
        if (!isFileFound)
            throw new AutomationException("The expected file was probably not downloaded or taking to long time to download");
    }

    public void verifyAllFieldsInDownloadedPDF() throws Exception {
        String pdfFilePath = ((System.getProperty("os.name").toLowerCase().contains("win"))
                ? System.getProperty("user.dir") + "\\downloads\\"
                : System.getProperty("user.dir") + "/downloads/") + downloadedFileName;

        verifyDate(pdfFilePath);
        verifyFiduciaryBeneficiaryDetailsForm(pdfFilePath);
    }

    private static void verifyDate(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "(Date)";  // The next line after the date

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("📄 **Full PDF Content (All Lines):**");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("Line " + (i + 1) + ": " + allLines[i]); // Log every line
        }

        List<String> extractedDates = new ArrayList<>();

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Find every occurrence of "(Date)"
            if (trimmedLine.equalsIgnoreCase(beforeLine) && i > 0) {
                String extractedDate = allLines[i - 1].trim();  // Extract date from the previous line
                if (!extractedDate.isEmpty()) {
                    extractedDates.add(extractedDate);
                }
            }
        }

        if (extractedDates.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No Date field found before (Date).");
        }

        // 📌 Log all extracted dates
        CommonSteps.logInfo("📌 Extracted Date(s): " + extractedDates);

        // Expected Dates for comparison
        List<String> expectedDates = Arrays.asList(dateDataForm1, dateDataForm2, dateDataForm3, dateDataForm4, dateDataForm5,dateDataForm6,dateDataForm7,dateDataForm8,dateDataForm9,dateDataForm10);

        // 🔍 Validate extracted dates
        for (String extracted : extractedDates) {
            CommonSteps.logInfo("🔍 Comparing -> Expected: " + expectedDates + ", Extracted: '" + extracted + "'");

            if (expectedDates.stream().noneMatch(expected -> expected.equalsIgnoreCase(extracted))) {
                throw new AutomationException("❌ Validation Failed: Extracted Date '" + extracted + "' does not match any expected value.");
            }
        }

        // ✅ Final validation success message
        CommonSteps.logInfo("✅ Validation Passed: All extracted dates match expected values.");
    }

    public static void verifyFiduciaryBeneficiaryDetailsForm(String pdfFilePath) throws IOException, AutomationException {
        // Define expected values dynamically
        Map<String, List<String>> fiduciaryFields = new HashMap<>();

        fiduciaryFields.put("Name or Corporate Name", Arrays.asList(
                CorporateFiduciary1Form, CorporateFiduciary2Form, CorporateFiduciary3Form, CorporateFiduciary4Form, CorporateFiduciary5Form));

        fiduciaryFields.put("Issued To", Arrays.asList(reasonDataForm1, reasonDataForm2, reasonDataForm3, reasonDataForm4, reasonDataForm5,
                reasonDataForm6, reasonDataForm7, reasonDataForm8, reasonDataForm9, reasonDataForm10));

        fiduciaryFields.put("Signature of Officer/Representative", Arrays.asList(
                signatureOfficerOrRepresentative1Form, signatureOfficerOrRepresentative2Form, signatureOfficerOrRepresentative3Form, signatureOfficerOrRepresentative4Form, signatureOfficerOrRepresentative5Form));

        fiduciaryFields.put("Fiduciary Address", Arrays.asList(
                corporateFiduciary1AddressForm, corporateFiduciary2AddressForm, corporateFiduciary3AddressForm, corporateFiduciary4AddressForm,
                corporateFiduciary5AddressForm));

        fiduciaryFields.put("City, State, Zip", Arrays.asList(
                corporateFiduciary1CityStateZipForm, corporateFiduciary2CityStateZipForm, corporateFiduciary3CityStateZipForm, corporateFiduciary4CityStateZipForm,
                corporateFiduciary5CityStateZipForm, beneficiary1CityStateZipForm, beneficiary2CityStateZipForm, beneficiary3CityStateZipForm, beneficiary4CityStateZipForm, beneficiary5CityStateZipForm));

        fiduciaryFields.put("Telephone", Arrays.asList(
                corporateFiduciary1TelephoneForm, corporateFiduciary2TelephoneForm, corporateFiduciary3TelephoneForm, corporateFiduciary4TelephoneForm,
                corporateFiduciary5TelephoneForm, beneficiary1TelephoneForm, beneficiary2TelephoneForm, beneficiary3TelephoneForm, beneficiary4TelephoneForm, beneficiary5TelephoneForm));

        fiduciaryFields.put("Email", Arrays.asList(
                corporateFiduciary1EmailForm, corporateFiduciary2EmailForm, corporateFiduciary3EmailForm, corporateFiduciary4EmailForm,
                corporateFiduciary5EmailForm, beneficiary1EmailForm, beneficiary2EmailForm, beneficiary3EmailForm, beneficiary4EmailForm, beneficiary5EmailForm));

        fiduciaryFields.put("Beneficiary Address", Arrays.asList(
                beneficiary1AddressForm, beneficiary2AddressForm, beneficiary3AddressForm, beneficiary4AddressForm, beneficiary5AddressForm));


        // Define mapping of before and after lines
        Map<String, String[]> fieldMarkers = new HashMap<>();

        fieldMarkers.put("Name or Corporate Name", new String[]{
                "(Date)",
                "Name or Corporate Fiduciary (if applicable)"});

        fieldMarkers.put("Issued To", new String[]{
                "the Estate of the Decedent and, to the extent permitted by law pursuant to 20 Pa.C.S. § 3155, respectfully request",
                "(Name or Corporate Name)"});

        fieldMarkers.put("Signature of Officer/Representative", new String[]{
                "Representative",
                "Address"}); //first 5 is ok

        fieldMarkers.put("Fiduciary Address", new String[]{
                "Title of Officer/Representative",
                "Address"});  //first 5 correct

        fieldMarkers.put("City, State, Zip", new String[]{
                "Address",
                "City, State, Zip"});  // alternate

        fieldMarkers.put("Telephone", new String[]{
                "City, State, Zip",
                "Telephone"});

        fieldMarkers.put("Email", new String[]{
                "Telephone",
                "Email"});

        fieldMarkers.put("Beneficiary Address", new String[]{
                "Representative",
                "Address"});   // last 5

        // Iterate over all fields and validate dynamically
        for (Map.Entry<String, List<String>> entry : fiduciaryFields.entrySet()) {
            String fieldName = entry.getKey();
            List<String> expectedValues = entry.getValue();
            String beforeLine = fieldMarkers.get(fieldName)[0];
            String afterLine = fieldMarkers.get(fieldName)[1];

            verifyFieldInPDF(pdfFilePath, beforeLine, afterLine, expectedValues, fieldName);
        }
    }

    public static void verifyFieldInPDF(String pdfFilePath, String beforeLine, String afterLine, List<String> expectedValues, String fieldName) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        List<String> extractedValues = new ArrayList<>();
        int startIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Find start index
            if (trimmedLine.equals(beforeLine.trim())) {
                startIndex = i;
            }

            // Find end index and extract data
            if (startIndex != -1 && trimmedLine.equals(afterLine.trim())) {
                StringBuilder extractedValue = new StringBuilder();
                for (int j = startIndex + 1; j < i; j++) {
                    String currentLine = allLines[j].trim();
                    if (!currentLine.isBlank()) {
                        extractedValue.append(currentLine).append(" ");
                    }
                }
                if (extractedValue.length() > 0) {
                    extractedValues.add(cleanExtractedValue(extractedValue.toString().trim(), fieldName));
                }
                startIndex = -1; // Reset for next occurrences

                // ✅ Limit to first 5 occurrences for specific fields
                if ((fieldName.equals("Signature of Officer/Representative") || fieldName.equals("Fiduciary Address") || fieldName.equals("Name or Corporate Name"))
                        && extractedValues.size() == 5) {
                    break; // Stop extracting after 5 occurrences
                }

                if (fieldName.equals("Beneficiary Address") && extractedValues.size() > 5) {
                    extractedValues = extractedValues.subList(extractedValues.size() - 5, extractedValues.size());
                    //for last 5 occurences
                }
            }
        }

        if (extractedValues.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No '" + fieldName + "' found in the document.");
        }

        // 📌 Log all extracted values
        CommonSteps.logInfo("📌 Extracted '" + fieldName + "' values (up to 5 if applicable): " + extractedValues);

        // 🔍 Validate extracted values against expected ones
        for (String extracted : extractedValues) {
            CommonSteps.logInfo("🔍 Comparing -> Expected: " + expectedValues + ", Extracted: '" + extracted + "'");

            if (expectedValues.stream().noneMatch(expected -> expected.equalsIgnoreCase(extracted))) {
                throw new AutomationException("❌ Validation Failed: '" + fieldName + "' value '" + extracted + "' does not match any expected value.");
            }
        }

        // ✅ Final validation success message
        CommonSteps.logInfo("✅ Validation Passed: All occurrences of '" + fieldName + "' match expected values.");
    }

    private static String cleanExtractedValue(String rawText, String fieldName) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        switch (fieldName) {
            case "Issued To":
                return rawText.replaceAll("(?i)\\b(that Letters be issued to )\\b", "")
                        .replaceAll("[,\\.\\s]+$", "")
                        .trim();


            case "Corporate Name":
                return rawText.replaceAll("[,\\.\\s]+$", "").trim();

            case "Address":
                return rawText.replaceAll("[,]+$", "")
                        .replaceAll("\\s{2,}", " ")
                        .trim();

            case "City, State, Zip":
                return rawText.replaceAll("[,\\.]+$", "").trim();

            case "Telephone":
                return rawText.replaceAll("[^0-9\\-() ]", "")
                        .trim();

            case "Email":
                return rawText.replaceAll("[^a-zA-Z0-9@._-]", "")
                        .trim();

            default:
                return rawText;
        }
    }
}








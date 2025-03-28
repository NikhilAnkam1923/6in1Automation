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
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.*;

public class ProbateFormsRW02Page extends BasePage {
    @Override
    String getName() {
        return "";
    }

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
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
    public static final String DECEDENT_TAB = "//span[text()='Decedent']";
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
    private static final String ESTATE_PENNSYLVANIA_ADDRESS = "//input[@name='estatePennsylvaniaAddress']";
    private static final String ESTATE_PENNSYLVANIA_CITY = "//input[@name='estatePennsylvaniaCity']";
    private static final String ESTATE_PENNSYLVANIA_COUNTY = "//input[@name='estatePennsylvaniaCounty']";
    private static final String USE_PRINCIPAL_RESIDENCE = "//input[@name='usePrincipalResidence']";
    private static final String OPTION_A_CHECKBOX = "//input[@name='petitionForTestamentary']";
    private static final String OPTION_B_CHECKBOX = "//input[@name='petitionForAdministration']";
    private static final String A_NO_EXCEPTION = "//div[text()='NO EXCEPTIONS']//input[@name='isTestamentaryException']";
    private static final String A_EXCEPTION = "//span[text()='EXCEPTIONS']//input[@name='isTestamentaryException']";
    private static final String B_NO_EXCEPTION = "//div[text()='NO EXCEPTIONS']//input[@name='isAdminException']";
    private static final String B_EXCEPTION = "//div[text()='EXCEPTIONS']//input[@name='isAdminException']";
    private static final String A_EXCEPTION_TEXT = "//input[@name='testamentaryExceptionText']";
    private static final String B_EXCEPTION_TEXT = "//input[@name='adminExceptionText']";
    private static final String STATE_RELEVANT_CIRCUMSTANCES_1 = "//input[@name='stateRelevantCircumstancesLine1']";
    private static final String STATE_RELEVANT_CIRCUMSTANCES_2 = "//input[@name='stateRelevantCircumstancesLine2']";
    private static final String FETCHED_DECEDENT_DIED_DATE = "//p[contains(text(),'Petitioner(s) aver(s) he/she/they is/are the Executor(s) named in the Last Will of the Decedent, dated')]//input";
    private static final String FETCHED_CODICIL_DATE_1 = "//input[@name='codicilDate1']";
    private static final String FETCHED_CODICIL_DATE_2 = "//input[@name='codicilDate2']";
    private static final String FETCHED_CODICIL_DATE_3 = "//input[@name='codicilDate3']";
    private static final String SHOW_ALL_TABS_BTN = "//button[@class='hamburger btn btn-primary']";
    private static final String BENEFICIARY_SELECTION_FIELD = "//input[@class='prn_bords']";
    private static final String DRAG_BENE = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_BENE_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String ACCEPT_BTN = "//button[text()='Accept']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    public static final String BENE_CONTACT_INPUT_XPATH = "//table[@id='beny_display']//input[@type='text' and @value='%s']";
    private static final String BENE_CONTACT_MODAL_INPUT_XPATH = "//div[@class='modal-body']//input[@type='text' and @value='%s']";
    private static final String BENE_CONTACT_ADDRESS_RELATION_XPATH = "//div[@class='modal-body']//textarea[contains(text(),'%s')]";
    private static final String BENE_VIEW_ATTACHMENT = "//span[@class='ms-2 view-attachment']";
    private static final String DISPLAY_ALL_HEIRS_ON_ATTACHMENT_CHKBX = "//input[@name='displayAllHeirsOnAttachment']";
    private static final String MODAL_DANGER_SAVE_BTN = "//div[@class='modal-body']//button[@class='btn btn-danger' and text()='Save']";
    private static final String SECOND_PAGE_BTN = "//div[@class='nav-item']//a[text()='2']";
    private static final String FIRST_PAGE_BTN = "//div[@class='nav-item']//a[text()='1']";
    private static final String SHORT_CERTIFICATE_NUM = "//input[@name='noOfShortCertificate']";
    private static final String RENUNCIATION_NUM = "//input[@name='noOfRenunciation']";
    private static final String CODICIL_NUM = "//input[@name='noOfCodicil']";
    private static final String AFFIDAVIT_NUM = "//input[@name='noOfAffidavit']";
    private static final String OTHER_FIELD_1 = "//input[@name='otherLabel1']";
    private static final String OTHER_FIELD_2 = "//input[@name='otherLabel2']";
    private static final String OTHER_FIELD_3 = "//input[@name='otherLabel3']";
    private static final String OTHER_FIELD_4 = "//input[@name='otherLabel4']";
    private static final String OTHER_FIELD_5 = "//input[@name='otherLabel5']";
    private static final String LETTER_FEES = "//input[@name='lettersFees']";
    private static final String SHORT_CERTIFICATE_FEES = "//input[@name='shortCertificateFees']";
    private static final String RENUNCIATION_FEES = "//input[@name='renunciationFees']";
    private static final String CODICIL_FEES = "//input[@name='codicilFees']";
    private static final String AFFIDAVIT_FEES = "//input[@name='affidavitFees']";
    private static final String BOND_FEES = "//input[@name='bondFees']";
    private static final String COMMISSION_FEES = "//input[@name='commissionFees']";
    private static final String OTHER_1_FEES = "//input[@name='otherFees1']";
    private static final String OTHER_2_FEES = "//input[@name='otherFees2']";
    private static final String OTHER_3_FEES = "//input[@name='otherFees3']";
    private static final String OTHER_4_FEES = "//input[@name='otherFees4']";
    private static final String OTHER_5_FEES = "//input[@name='otherFees5']";
    private static final String AUTOMATION_FEES = "//input[@name='automationFees']";
    private static final String JCS_FEES = "//input[@name='jcsFees']";
    private static final String TOTAL_FEES = "//input[@name='totalFees']";
    private static final String ATTORNEY_SELECTION_FIELD = "//div//p[text()='Attorney Signature:']/following-sibling::p//input[@class='prn_bords']";
    private static final String ATTORNEY_FETCHED_FIELD = "//div//p[text()='Attorney Signature:']/following-sibling::p//input[contains(@value,'%s')]";
    private static final String PAGE_2_COUNTY = "//p[contains(text(),'COUNTY OF')]//input";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String personalPropertyForm;
    static String pennsylvaniaPropertyForm;
    static String countryPropertyForm;
    static String realEstatePropertyForm;
    static String enteredPersonalPropertyAmountForm;
    static String enteredPennsylvaniaPropertyAmountForm;
    static String enteredCountyPropertyAmountForm;
    static String enteredRealEstatePropertyAmountForm;
    static String copiedEstateAddressForm;
    static String copiedEstateCityForm;
    static String copiedEstateCountyForm;
    static String BeneContact1Form;
    static String BeneContact2Form;
    static String BeneContact3Form;
    static String BeneContact4Form;
    static String BeneContact5Form;
    static String BeneContactAddress1Form;
    static String BeneContactAddress2Form;
    static String BeneContactAddress3Form;
    static String BeneContactAddress4Form;
    static String BeneContactAddress5Form;
    static String enteredAKA1Form;
    static String enteredAKA2Form;
    static String enteredAKA3Form;
    static String letterFeesForm;
    static String shortCertificateFeesForm;
    static String renunciationFeesForm;
    static String codicilFeesForm;
    static String affidavitFeesForm;
    static String bondFeesForm;
    static String commissionFeesForm;
    static String otherFees1Form;
    static String otherFees2Form;
    static String otherFees3Form;
    static String otherFees4Form;
    static String otherFees5Form;
    static String automationFeesForm;
    static String jcsFeesForm;
    static String totalFeesForm;
    static String selectedAttorneyContactForm;
    static String rwCodicilDate1Form;
    static String rwCodicilDate2Form;
    static String rwCodicilDate3Form;
    static String modifiedEstateAddressForm;
    static String modifiedEstateCityForm;
    static String modifiedEstateCountyForm;
    static String countyOnForm;
    static String fullNameForm;
    static String fileNumberForm;
    static String dateOfDeathForm;
    static String ssnForm;
    static String ageAtDeathForm;
    static String domicileAddressForm;
    static String domicileCityForm;
    static String placeOfDeathAddressForm;
    static String placeOfDeathCityForm;
    static String placeOfDeathCountryForm;
    static String AKA1Form;
    static String akaName2Form;
    static String akaName3Form;
    static String allFiduciaryContactsForm;
    static String attorneyFirmNameForm;
    static String attorneyAddressLine1Form;
    static String attorneyAddressLine2Form;
    static String attorneyCityStateZipForm;
    static String attorneyPhoneForm;
    static String attorneyFaxForm;
    static String totalEstimatedValueForm;
    static String stateRelevantCircumstances1Form;
    static String stateRelevantCircumstances2Form;
    static String exceptionTextForm;
    static String Petitioner1Form;
    static String Petitioner2Form;
    static String Petitioner3Form;
    static String Petitioner4Form;

    static String DownloadedFileName;

    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
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

    public void verifyCorrectCountyName() throws AutomationException {
        waitForVisibleElement(By.xpath(RW02_FETCHED_COUNTY));
        String domicileCountry = getEstateValue("DomicileCountry");
        countyOnForm = driverUtil.getWebElement(RW02_FETCHED_COUNTY).getAttribute("value");
        if (!countyOnForm.equals(domicileCountry)) {
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

    public void verifyBeneContactInTable(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(BENE_CONTACT_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyBeneContactOnAttachment(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(BENE_CONTACT_MODAL_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyBeneContactAddressAndRelationOnAttachment(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(BENE_CONTACT_ADDRESS_RELATION_XPATH, expectedValue));
        if (field != null && field.getText().contains(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyFetchedAttorneyContact(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(ATTORNEY_FETCHED_FIELD, expectedValue));
        if (field != null && field.getAttribute("value").contains(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {

            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyDecedentInformationIsAutoPopulatedOnTheForm() throws AutomationException {
        fullNameForm = getEstateValue("FirstName") + " " + getEstateValue("LastName");
        fileNumberForm = getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3");
        dateOfDeathForm = getEstateValue("DateOfDeath");
        ssnForm = getEstateValue("SSN");
        ageAtDeathForm = getEstateValue("AgeAtDeath");
        domicileAddressForm = getEstateValue("DomicileAddressLine1") + ", " + getEstateValue("DomicileAddressLine2") + ", " + getEstateValue("DomicileZip");
        domicileCityForm = getEstateValue("DomicileCity");
        placeOfDeathAddressForm = getEstateValue("PlaceOfDeathAddressLine1") + ", " + getEstateValue("PlaceOfDeathAddressLine2") + ", " + getEstateValue("PlaceOfDeathZip");
        placeOfDeathCityForm = getEstateValue("PlaceOfDeathCity");
        placeOfDeathCountryForm = getEstateValue("PlaceOfDeathCountry");
        AKA1Form = getEstateValue("AlsoKnownAs");

        verifyAutoPopulatedValue(fullNameForm);
        verifyAutoPopulatedValue(fileNumberForm);
        verifyAutoPopulatedValue(dateOfDeathForm);
        verifyAutoPopulatedValue(ssnForm);
        verifyAutoPopulatedValue(ageAtDeathForm);
        verifyAutoPopulatedValue(domicileAddressForm);
        verifyAutoPopulatedValue(domicileCityForm);
        verifyAutoPopulatedValue(placeOfDeathAddressForm);
        verifyAutoPopulatedValue(placeOfDeathCityForm);
        verifyAutoPopulatedValue(placeOfDeathCountryForm);
        verifyAutoPopulatedValue(AKA1Form);
    }


    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.isEnabled() && field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        String domicileCountryField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("DomicileCountry"));
        String fullNameField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("FirstName") + " " + getEstateValue("LastName"));
        String fileNumberField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3"));
        String dateOfDeathField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("DateOfDeath"));
        String ssnField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("SSN"));
        String ageAtDeathField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("AgeAtDeath"));
        String domicileAddressField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("DomicileAddressLine1") + ", " + getEstateValue("DomicileAddressLine2") + ", " + getEstateValue("DomicileZip"));
        String domicileCityField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("DomicileCity"));
        String placeOfDeathAddressField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("PlaceOfDeathAddressLine1") + ", " + getEstateValue("PlaceOfDeathAddressLine2") + ", " + getEstateValue("PlaceOfDeathZip"));
        String placeOfDeathCityField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("PlaceOfDeathCity"));
        String placeOfDeathCountryField = String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH, getEstateValue("PlaceOfDeathCountry"));

        verifyFieldIsNotEditable(domicileCountryField);
        verifyFieldIsNotEditable(fullNameField);
        verifyFieldIsNotEditable(fileNumberField);
        verifyFieldIsNotEditable(dateOfDeathField);
        verifyFieldIsNotEditable(ssnField);
        verifyFieldIsNotEditable(ageAtDeathField);
        verifyFieldIsNotEditable(domicileAddressField);
        verifyFieldIsNotEditable(domicileCityField);
        verifyFieldIsNotEditable(placeOfDeathAddressField);
        verifyFieldIsNotEditable(placeOfDeathCityField);
        verifyFieldIsNotEditable(placeOfDeathCountryField);
    }

    public void verifyMultipleAkaNamesCanBeAddedSeparatedByComma() throws IOException, ParseException, AutomationException {
        akaName2Form = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName2").toString();
        akaName3Form = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName3").toString();

        driverUtil.getWebElement(RW_AKA_FIELD2).sendKeys(akaName2Form);
        driverUtil.getWebElement(RW_AKA_FIELD3).sendKeys(akaName3Form);
        CommonSteps.takeScreenshot();
    }

    public void verifyFiduciaryTypeOfContactsAreDisplayed() throws AutomationException, IOException, ParseException {
        List<String> corporateFiduciaryContacts = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String entityName = CommonUtil.getJsonPath("corporateFiduciary" + i).get("corporateFiduciary" + i + ".entityName").toString();

            corporateFiduciaryContacts.add(String.join(" ", entityName).trim());
        }

        String firstName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.firstName").toString();
        String lastName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.lastName").toString() + ",";
        String middleName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.middleName").toString();
        String suffix = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.suffix").toString();

        String fiduciaryContact = String.join(" ", firstName, middleName, lastName, suffix).trim();

        String expectedCorporateFiduciaryContact = String.join(", ", corporateFiduciaryContacts);
        String expectedFiduciaryContacts = String.join(", ", fiduciaryContact, expectedCorporateFiduciaryContact);

        verifyAutoPopulatedValue(expectedFiduciaryContacts);
    }

    public void verifyNamesExceedTheLineTheContactsAreDisplayedInTheAttachment() throws AutomationException, IOException, ParseException {
        driverUtil.getWebElement(VIEW_ATTACHMENT_HEADER).click();

        List<String> fiduciaryContacts = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String firstName = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".firstName").toString();
            String lastName = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".lastName").toString() + ",";
            String middleName = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".middleName").toString();
            String suffix = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".suffix").toString();

            fiduciaryContacts.add(String.join(" ", firstName, middleName, lastName, suffix).trim());
        }

        String expectedFiduciaryContacts = String.join(", ", fiduciaryContacts);

        verifyAutoPopulatedValueOnAttachment(expectedFiduciaryContacts);

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

        WebDriverUtil.waitForAWhile();

        personalPropertyForm = getSelectedOption(PERSONAL_PROPERTY_DRDWN);
        pennsylvaniaPropertyForm = getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN);
        countryPropertyForm = getSelectedOption(COUNTY_PROPERTY_DRDWN);
        realEstatePropertyForm = getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN);
    }

    public void verifySelectedValuesAreRetainedAndAutoSaved() throws AutomationException {
        clickOnRWForm("RW 03");
        clickOnRWForm("RW 02");

        if (!getSelectedOption(PERSONAL_PROPERTY_DRDWN).equals(personalPropertyForm)) {
            throw new AutomationException("Selected value " + personalPropertyForm + " is not retained");
        }

        if (!getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN).equals(pennsylvaniaPropertyForm)) {
            throw new AutomationException("Selected value " + pennsylvaniaPropertyForm + " is not retained");
        }

        if (!getSelectedOption(COUNTY_PROPERTY_DRDWN).equals(countryPropertyForm)) {
            throw new AutomationException("Selected value " + countryPropertyForm + " is not retained");
        }

        if (!getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN).equals(realEstatePropertyForm)) {
            throw new AutomationException("Selected value " + realEstatePropertyForm + " is not retained");
        }

        CommonSteps.takeScreenshot();

        enteredAKA1Form = driverUtil.getWebElement(RW_AKA_FIELD1).getAttribute("value");
        enteredAKA2Form = driverUtil.getWebElement(RW_AKA_FIELD2).getAttribute("value");
        enteredAKA3Form = driverUtil.getWebElement(RW_AKA_FIELD3).getAttribute("value");

    }

    private void fillField(String fieldLocator, String data) throws AutomationException {
        waitForVisibleElement(By.xpath(fieldLocator));
        WebElement field = DriverFactory.drivers.get().findElement(By.xpath(fieldLocator));
        field.click();
        field.sendKeys(data);

        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }


    public void verifyAmountCanBeEnteredInAllTheFieldsAndAutoSaved() throws AutomationException {
        WebElement personalPropertyAmount = driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT);
        WebElement pennsylvaniaPropertyAmount = driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT);
        WebElement countyPropertyAmount = driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT);
        WebElement realEstatePropertyAmount = driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT);

        clearField(PERSONAL_PROPERTY_AMOUNT);
        fillField(PERSONAL_PROPERTY_AMOUNT, "320.00");
        clearField(PENNSYLVANIA_PROPERTY_AMOUNT);
        fillField(PENNSYLVANIA_PROPERTY_AMOUNT, "430.00");
        clearField(COUNTY_PROPERTY_AMOUNT);
        fillField(COUNTY_PROPERTY_AMOUNT, "530.00");
        clearField(REAL_ESTATE_PROPERTY_AMOUNT);
        fillField(REAL_ESTATE_PROPERTY_AMOUNT, "670.00");
        realEstatePropertyAmount.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile(2);

        enteredPersonalPropertyAmountForm = personalPropertyAmount.getAttribute("value");
        enteredPennsylvaniaPropertyAmountForm = pennsylvaniaPropertyAmount.getAttribute("value");
        enteredCountyPropertyAmountForm = countyPropertyAmount.getAttribute("value");
        enteredRealEstatePropertyAmountForm = realEstatePropertyAmount.getAttribute("value");
    }

    public void verifyAmountEnteredInAllTheFieldsAreAutoSaved() throws AutomationException {
        clickOnRWForm("RW 03");
        clickOnRWForm("RW 02");

        if (!driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT).getAttribute("value").equals(enteredPersonalPropertyAmountForm)) {
            throw new AutomationException("Entered value " + enteredPersonalPropertyAmountForm + " is not retained");
        }

        if (!driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT).getAttribute("value").equals(enteredPennsylvaniaPropertyAmountForm)) {
            throw new AutomationException("Entered value " + enteredPennsylvaniaPropertyAmountForm + " is not retained");
        }

        if (!driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT).getAttribute("value").equals(enteredCountyPropertyAmountForm)) {
            throw new AutomationException("Entered value " + enteredCountyPropertyAmountForm + " is not retained");
        }

        if (!driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT).getAttribute("value").equals(enteredRealEstatePropertyAmountForm)) {
            throw new AutomationException("Entered value " + enteredRealEstatePropertyAmountForm + " is not retained");
        }
    }

    public void verifyTotalEstimatedValueIsTheTotalOfFirstAndLastFieldsOnly() throws AutomationException {
        String sumOfFistAndLastFields = String.format("%.2f", Double.parseDouble(enteredPersonalPropertyAmountForm) + Double.parseDouble(enteredRealEstatePropertyAmountForm));
        totalEstimatedValueForm = driverUtil.getWebElement(TOTAL_ESTIMATED_VALUE).getAttribute("value");
        if (!totalEstimatedValueForm.equals(sumOfFistAndLastFields)) {
            throw new AutomationException("The total estimated value is not the total of first and last fields only");
        }
    }

    public void userChecksUsePrincipalResidenceCheckbox() throws AutomationException {
        DriverFactory.drivers.get().findElement(By.xpath(USE_PRINCIPAL_RESIDENCE)).click();
    }

    public void verifyAddressFromPrincipalResidenceAtFieldIsCopiedToRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String expectedEstateAddress = getEstateValue("DomicileAddressLine1") + ", " + getEstateValue("DomicileAddressLine2") + ", " + getEstateValue("DomicileZip");
        String expectedEstateCity = getEstateValue("DomicileCity");
        String expectedEstateCounty = getEstateValue("DomicileCountry");

        String estateAddress = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        String estateCity = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        String estateCounty = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");

        if (!expectedEstateAddress.equals(estateAddress)) {
            throw new AutomationException("Address is not copied correctly");
        }

        if (!expectedEstateCity.equals(estateCity)) {
            throw new AutomationException("City is not copied correctly");
        }

        if (!expectedEstateCounty.equals(estateCounty)) {
            throw new AutomationException("County is not copied correctly");
        }

        copiedEstateAddressForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        copiedEstateCityForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        copiedEstateCountyForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");
    }

    public void verifyUncheckingTheCheckboxDoesNotClearTheRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        userChecksUsePrincipalResidenceCheckbox();

        if (driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value") == null) {
            throw new AutomationException("The address does not retained, the field is empty.");
        }

        if (driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value") == null) {
            throw new AutomationException("The address does not retained, the field is empty.");
        }

        if (driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value") == null) {
            throw new AutomationException("The address does not retained, the field is empty.");
        }
    }

    public void verifyCopiedAddressIsRetainedAndAutoSaved() throws AutomationException {
        clickOnRWForm("RW 03");
        clickOnRWForm("RW 02");

        WebDriverUtil.waitForAWhile();

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value").equals(copiedEstateAddressForm)) {
            throw new AutomationException("Copied value " + copiedEstateAddressForm + " is not retained");
        }

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value").equals(copiedEstateCityForm)) {
            throw new AutomationException("Copied value " + copiedEstateCityForm + " is not retained");
        }

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value").equals(copiedEstateCountyForm)) {
            throw new AutomationException("Copied value " + copiedEstateCountyForm + " is not retained");
        }
    }

    public void userModifiesTheAddressInTheField() throws AutomationException {
        clearField(ESTATE_PENNSYLVANIA_ADDRESS);
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).sendKeys("46A Wall Road");
        clearField(ESTATE_PENNSYLVANIA_CITY);
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).sendKeys("Ambridge");
        clearField(ESTATE_PENNSYLVANIA_COUNTY);
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).sendKeys(Keys.TAB);
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).sendKeys("Beaver");
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile(2);

        modifiedEstateAddressForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        modifiedEstateCityForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        modifiedEstateCountyForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");
    }

    public void verifyModificationsAreSavedSuccessfully() throws AutomationException {
        clickOnRWForm("RW 03");
        clickOnRWForm("RW 02");

        WebDriverUtil.waitForAWhile();

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value").equals(modifiedEstateAddressForm)) {
            throw new AutomationException("Modified value " + modifiedEstateAddressForm + " is not retained");
        }

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value").equals(modifiedEstateCityForm)) {
            throw new AutomationException("Modified value " + modifiedEstateCityForm + " is not retained");
        }

        if (!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value").equals(modifiedEstateCountyForm)) {
            throw new AutomationException("Modified value " + modifiedEstateCountyForm + " is not retained");
        }

        CommonSteps.takeScreenshot();
    }

    public void userChecksOptionACheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(OPTION_A_CHECKBOX)).click();
    }

    public void verifyOptionARemainsSelectedAndOptionBIsUnaffected() throws AutomationException {
        WebElement optionA = DriverFactory.drivers.get().findElement(By.xpath(OPTION_A_CHECKBOX));
        WebElement optionB = DriverFactory.drivers.get().findElement(By.xpath(OPTION_B_CHECKBOX));

        WebDriverUtil.waitForAWhile();
        if (!optionA.isSelected() && optionB.isSelected()) {
            throw new AutomationException("Option A does not remain selected, and Option B is affected");
        }
    }

    public void verifyDecedentDiedDateIsAutoFetched() throws AutomationException {
        String fetchedDiedDate = driverUtil.getWebElement(FETCHED_DECEDENT_DIED_DATE).getAttribute("value");
        String expectedDateOfWill = getEstateValue("DateOfWill");

        if (!fetchedDiedDate.equals(expectedDateOfWill)) {
            throw new AutomationException("Decedent died date is not auto fetched");
        }
    }

    public void verifyCodicilDateValuesAreAutoFetched() throws AutomationException {
        String codicilDate1 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_1).getAttribute("value");
        String codicilDate2 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_2).getAttribute("value");
        String codicilDate3 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_3).getAttribute("value");

        String expectedCodicilDate1 = getEstateValue("CodicilDate1");
        String expectedCodicilDate2 = getEstateValue("CodicilDate2");
        String expectedCodicilDate3 = getEstateValue("CodicilDate3");

        if (!codicilDate1.equals(expectedCodicilDate1)) {
            throw new AutomationException("Codicil date 1 is not auto fetched");
        }
        if (!codicilDate2.equals(expectedCodicilDate2)) {
            throw new AutomationException("Codicil date 2 is not auto fetched");
        }
        if (!codicilDate3.equals(expectedCodicilDate3)) {
            throw new AutomationException("Codicil date 3 is not auto fetched");
        }
    }

    public void userUpdatesCodicilDates() throws AutomationException {
        WebElement codicilDate1 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_1);
        WebElement codicilDate2 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_2);
        WebElement codicilDate3 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_3);

        scrollToElementAndClick(FETCHED_CODICIL_DATE_1);

        clearField(FETCHED_CODICIL_DATE_1);
        codicilDate1.sendKeys("02/17/2023");
        clearField(FETCHED_CODICIL_DATE_2);
        codicilDate2.sendKeys("02/20/2023");
        clearField(FETCHED_CODICIL_DATE_3);
        codicilDate3.sendKeys("02/26/2023");
        codicilDate3.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();

        rwCodicilDate1Form = codicilDate1.getAttribute("value");
        rwCodicilDate2Form = codicilDate2.getAttribute("value");
        rwCodicilDate3Form = codicilDate3.getAttribute("value");
    }

    public void verifyCodicilDatesReflectedInTheCodicilDatesInDecedentTab() throws AutomationException {
        waitForVisibleElement(By.xpath(DECEDENT_TAB));
        driverUtil.getWebElement(DECEDENT_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        if (!driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value").equals(rwCodicilDate1Form)) {
            throw new AutomationException("Updated codicil date 1 is not reflected");
        }

        if (!driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value").equals(rwCodicilDate2Form)) {
            throw new AutomationException("Updated codicil date 2 is not reflected");
        }

        if (!driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value").equals(rwCodicilDate3Form)) {
            throw new AutomationException("Updated codicil date 3 is not reflected");
        }

    }

    public void verifyTextCanBeEnteredInTheStateRelevantCircumstancesTextFields() throws AutomationException, IOException, ParseException {
        stateRelevantCircumstances1Form = CommonUtil.getJsonPath("RW02Form").get("RW02Form.stateRelevantCircumstances1Form").toString();
        stateRelevantCircumstances2Form = CommonUtil.getJsonPath("RW02Form").get("RW02Form.stateRelevantCircumstances2Form").toString();

        driverUtil.getWebElement(STATE_RELEVANT_CIRCUMSTANCES_1).sendKeys(stateRelevantCircumstances1Form);
        driverUtil.getWebElement(STATE_RELEVANT_CIRCUMSTANCES_2).sendKeys(stateRelevantCircumstances2Form);
    }

    public void userChecksExceptionsCheckboxFromOptionA() {
        DriverFactory.drivers.get().findElement(By.xpath(A_EXCEPTION)).click();
    }

    public void verifyTextFieldIsEnabledAndTextCanBeEntered() throws AutomationException, IOException, ParseException {
        exceptionTextForm = CommonUtil.getJsonPath("RW02Form").get("RW02Form.exceptionTextForm").toString();

        WebElement textField = driverUtil.getWebElement(A_EXCEPTION_TEXT);
        WebDriverUtil.waitForAWhile();
        if (!textField.isEnabled()) {
            throw new AutomationException("Text field is not enabled");
        }

        textField.sendKeys(exceptionTextForm);
    }

    public void userChecksOptionBCheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(OPTION_B_CHECKBOX)).click();
    }

    public void verifyOptionBRemainsSelectedAndOptionAIsUnaffected() throws AutomationException {
        WebElement optionA = DriverFactory.drivers.get().findElement(By.xpath(OPTION_A_CHECKBOX));
        WebElement optionB = DriverFactory.drivers.get().findElement(By.xpath(OPTION_B_CHECKBOX));

        WebDriverUtil.waitForAWhile();
        if (!optionA.isSelected() && !optionB.isSelected()) {
            throw new AutomationException("Option B does not remain selected, and Option A is affected");
        }
    }

    public void verifyBeneficiariesSelectionFieldAtTheBottomOfPageIsEnabled() throws AutomationException {
        WebElement beneField = driverUtil.getWebElement(BENEFICIARY_SELECTION_FIELD);
        WebDriverUtil.waitForAWhile(3);
        if (!beneField.isEnabled()) {
            throw new AutomationException("Beneficiaries' selection field at the bottom of page 1 is not enabled");
        }
    }

    public void verifyMultipleBeneficiariesCanBeAdded() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebDriverUtil.waitForAWhile();

        driverUtil.getWebElement(BENEFICIARY_SELECTION_FIELD).click();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_BENE_XPATH);

        BeneContact1Form = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        BeneContact2Form = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        BeneContact3Form = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        BeneContact4Form = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        BeneContact5Form = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
    }

    public void userClicksOnAcceptButton() throws AutomationException {

        driverUtil.getWebElement(ACCEPT_BTN).click();
    }

    public void verifyCorrectBeneficiaryNameRelationshipAndAddressIsDisplayed() throws AutomationException, IOException, ParseException {
        String beneficiary1AddressLine1 = CommonUtil.getJsonPath("beneficiary1").get("beneficiary1.addressLine1").toString();
        String beneficiary1City = CommonUtil.getJsonPath("beneficiary1").get("beneficiary1.city").toString();
        String beneficiary1StateCode = CommonUtil.getJsonPath("beneficiary1").get("beneficiary1.stateCode").toString();
        String beneficiary1Zip = CommonUtil.getJsonPath("beneficiary1").get("beneficiary1.zip").toString();

        String beneficiary2AddressLine1 = CommonUtil.getJsonPath("beneficiary2").get("beneficiary2.addressLine1").toString();
        String beneficiary2City = CommonUtil.getJsonPath("beneficiary2").get("beneficiary2.city").toString();
        String beneficiary2StateCode = CommonUtil.getJsonPath("beneficiary2").get("beneficiary2.stateCode").toString();
        String beneficiary2Zip = CommonUtil.getJsonPath("beneficiary2").get("beneficiary2.zip").toString();

        String beneficiary3AddressLine1 = CommonUtil.getJsonPath("beneficiary3").get("beneficiary3.addressLine1").toString();
        String beneficiary3City = CommonUtil.getJsonPath("beneficiary3").get("beneficiary3.city").toString();
        String beneficiary3StateCode = CommonUtil.getJsonPath("beneficiary3").get("beneficiary3.stateCode").toString();
        String beneficiary3Zip = CommonUtil.getJsonPath("beneficiary3").get("beneficiary3.zip").toString();

        String beneficiary4AddressLine1 = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.addressLine1").toString();
        String beneficiary4City = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.city").toString();
        String beneficiary4StateCode = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.stateCode").toString();
        String beneficiary4Zip = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.zip").toString();

        String beneficiary5AddressLine1 = CommonUtil.getJsonPath("beneficiary5").get("beneficiary5.addressLine1").toString();
        String beneficiary5City = CommonUtil.getJsonPath("beneficiary5").get("beneficiary5.city").toString();
        String beneficiary5StateCode = CommonUtil.getJsonPath("beneficiary5").get("beneficiary5.stateCode").toString();
        String beneficiary5Zip = CommonUtil.getJsonPath("beneficiary5").get("beneficiary5.zip").toString();

        BeneContactAddress1Form = beneficiary1AddressLine1 + ", " + beneficiary1City + ", " + beneficiary1StateCode + " " + beneficiary1Zip;
        BeneContactAddress2Form = beneficiary2AddressLine1 + ", " + beneficiary2City + ", " + beneficiary2StateCode + " " + beneficiary2Zip;
        BeneContactAddress3Form = beneficiary3AddressLine1 + ", " + beneficiary3City + ", " + beneficiary3StateCode + " " + beneficiary3Zip;
        BeneContactAddress4Form = beneficiary4AddressLine1 + ", " + beneficiary4City + ", " + beneficiary4StateCode + " " + beneficiary4Zip;
        BeneContactAddress5Form = beneficiary5AddressLine1 + ", " + beneficiary5City + ", " + beneficiary5StateCode + " " + beneficiary5Zip;

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Bene/Heirs updated successfully.")));

        verifyBeneContactInTable(BeneContact1Form);
        verifyBeneContactInTable(BeneContact4Form);
        verifyBeneContactInTable(BeneContact5Form);
        verifyBeneContactInTable(BeneContact3Form);

        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");

        verifyBeneContactInTable(BeneContactAddress5Form);
        verifyBeneContactInTable(BeneContactAddress2Form);
        verifyBeneContactInTable(BeneContactAddress1Form);
        verifyBeneContactInTable(BeneContactAddress3Form);
    }

    public void verifyContactsAreTransferredToAttachment() throws AutomationException, IOException, ParseException {
        String beneficiary4AddressLine1 = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.addressLine1").toString();

        driverUtil.getWebElement(BENE_VIEW_ATTACHMENT).click();
        WebDriverUtil.waitForAWhile();

        verifyBeneContactOnAttachment(BeneContact2Form);
        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment(beneficiary4AddressLine1);

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
    }

    public void userChecksDisplayALLHeirsOnAttachmentCheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_HEIRS_ON_ATTACHMENT_CHKBX)).click();
    }

    public void verifyAllTheContactsAreTransferredToAttachment() throws AutomationException, IOException, ParseException {
        String beneficiary1AddressLine1 = CommonUtil.getJsonPath("beneficiary1").get("beneficiary1.addressLine1").toString();
        String beneficiary2AddressLine1 = CommonUtil.getJsonPath("beneficiary2").get("beneficiary2.addressLine1").toString();
        String beneficiary3AddressLine1 = CommonUtil.getJsonPath("beneficiary3").get("beneficiary3.addressLine1").toString();
        String beneficiary4AddressLine1 = CommonUtil.getJsonPath("beneficiary4").get("beneficiary4.addressLine1").toString();
        String beneficiary5AddressLine1 = CommonUtil.getJsonPath("beneficiary5").get("beneficiary5.addressLine1").toString();

        driverUtil.getWebElement(BENE_VIEW_ATTACHMENT).click();
        WebDriverUtil.waitForAWhile();

        verifyBeneContactOnAttachment(BeneContact1Form);
        verifyBeneContactOnAttachment(BeneContact2Form);
        verifyBeneContactOnAttachment(BeneContact3Form);
        verifyBeneContactOnAttachment(BeneContact4Form);
        verifyBeneContactOnAttachment(BeneContact5Form);

        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment("None");

        verifyBeneContactAddressAndRelationOnAttachment(beneficiary1AddressLine1);
        verifyBeneContactAddressAndRelationOnAttachment(beneficiary2AddressLine1);
        verifyBeneContactAddressAndRelationOnAttachment(beneficiary3AddressLine1);
        verifyBeneContactAddressAndRelationOnAttachment(beneficiary4AddressLine1);
        verifyBeneContactAddressAndRelationOnAttachment(beneficiary5AddressLine1);

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
    }

    public void userDeselectsOptionB() throws AutomationException {
        DriverFactory.drivers.get().findElement(By.xpath(OPTION_B_CHECKBOX)).click();

        driverUtil.getWebElement(MODAL_DANGER_SAVE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyBeneficiariesSelectionFieldIsDisabled() throws AutomationException {
        WebElement beneField = driverUtil.getWebElement(BENEFICIARY_SELECTION_FIELD);
        WebDriverUtil.waitForAWhile(2);
        if (beneField.isEnabled()) {
            throw new AutomationException("Beneficiaries' selection field is enabled");
        }
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    public void verifyPetitionerSNameAreByDefaultPrintedOnTheTable() throws AutomationException, IOException, ParseException {
        String fiduciary5FirstName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.firstName").toString();
        String fiduciary5LastName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.lastName").toString();
        String fiduciary5MiddleName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.middleName").toString();
        String fiduciary5Suffix = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.suffix").toString();

        Petitioner2Form = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.entityName").toString();
        Petitioner3Form = CommonUtil.getJsonPath("corporateFiduciary2").get("corporateFiduciary2.entityName").toString();
        Petitioner4Form = CommonUtil.getJsonPath("corporateFiduciary3").get("corporateFiduciary3.entityName").toString();

        Petitioner1Form = fiduciary5FirstName + " " + fiduciary5MiddleName + " " + fiduciary5LastName + ", " + fiduciary5Suffix;

        driverUtil.getWebElement(SECOND_PAGE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));

        scrollToElementAndClick(PAGE_2_COUNTY);

        verifyAutoPopulatedValue(Petitioner1Form);
        verifyAutoPopulatedValue(Petitioner2Form);
        verifyAutoPopulatedValue(Petitioner3Form);
        verifyAutoPopulatedValue(Petitioner4Form);
    }

    public void verifyDecreeOfRegisterInformationDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        List<String> corporateFiduciaryContacts = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String entityName = CommonUtil.getJsonPath("corporateFiduciary" + i).get("corporateFiduciary" + i + ".entityName").toString();

            corporateFiduciaryContacts.add(String.join(" ", entityName).trim());
        }

        String firstName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.firstName").toString();
        String lastName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.lastName").toString() + ",";
        String middleName = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.middleName").toString();
        String suffix = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.suffix").toString();

        String fiduciaryContact = String.join(" ", firstName, middleName, lastName, suffix).trim();

        String expectedCorporateFiduciaryContact = String.join(", ", corporateFiduciaryContacts);
        String expectedCorporateAndFiduciaryContacts = String.join(", ", fiduciaryContact, expectedCorporateFiduciaryContact);


        List<String> fiduciaryContacts = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String firstNameFiduciary = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".firstName").toString();
            String lastNameFiduciary = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".lastName").toString() + ",";
            String middleNameFiduciary = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".middleName").toString();
            String suffixFiduciary = CommonUtil.getJsonPath("fiduciary" + i).get("fiduciary" + i + ".suffix").toString();

            fiduciaryContacts.add(String.join(" ", firstNameFiduciary, middleNameFiduciary, lastNameFiduciary, suffixFiduciary).trim());
        }

        String expectedFiduciaryContacts = String.join(", ", fiduciaryContacts);

        String decedentName = getEstateValue("FirstName") + " " + getEstateValue("LastName");
        String akaNames = getEstateValue("AlsoKnownAs1") + " " + getEstateValue("AlsoKnownAs2") + " " + getEstateValue("AlsoKnownAs3");
        String fileNumberForm = getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3");
        allFiduciaryContactsForm = expectedCorporateAndFiduciaryContacts + ", " + expectedFiduciaryContacts;
        String dateOfWill = getEstateValue("DateOfWill");

        verifyAutoPopulatedValue(decedentName);
        verifyAutoPopulatedValue(fileNumberForm);
        verifyAutoPopulatedValue(akaNames.trim());
        verifyAutoPopulatedValue(allFiduciaryContactsForm);
        verifyAutoPopulatedValue(dateOfWill);
        verifyAutoPopulatedValue(rwCodicilDate1Form);
        verifyAutoPopulatedValue(rwCodicilDate2Form);
        verifyAutoPopulatedValue(rwCodicilDate3Form);
    }

    public void userEntersValuesInLettersField() throws AutomationException {
        clearField(SHORT_CERTIFICATE_NUM);
        driverUtil.getWebElement(SHORT_CERTIFICATE_NUM).sendKeys("2");
        clearField(RENUNCIATION_NUM);
        driverUtil.getWebElement(RENUNCIATION_NUM).sendKeys("1");
        clearField(CODICIL_NUM);
        driverUtil.getWebElement(CODICIL_NUM).sendKeys("3");
        clearField(AFFIDAVIT_NUM);
        driverUtil.getWebElement(AFFIDAVIT_NUM).sendKeys("1");
    }

    public void userEntersDataInOtherField() throws AutomationException {
        clearField(OTHER_FIELD_1);
        driverUtil.getWebElement(OTHER_FIELD_1).sendKeys("Expedited Processing");
        clearField(OTHER_FIELD_2);
        driverUtil.getWebElement(OTHER_FIELD_2).sendKeys("Copy Request");
        clearField(OTHER_FIELD_3);
        driverUtil.getWebElement(OTHER_FIELD_3).sendKeys("Administrative Handling");
        clearField(OTHER_FIELD_4);
        driverUtil.getWebElement(OTHER_FIELD_4).sendKeys("Notary");
        clearField(OTHER_FIELD_5);
        driverUtil.getWebElement(OTHER_FIELD_5).sendKeys("Document Retrieval");
    }

    public void userAddsAmountInFrontOfTheRespectiveFields() throws AutomationException {
        clearField(LETTER_FEES);
        driverUtil.getWebElement(LETTER_FEES).sendKeys("30.00");
        clearField(SHORT_CERTIFICATE_FEES);
        driverUtil.getWebElement(SHORT_CERTIFICATE_FEES).sendKeys("50.00");
        clearField(RENUNCIATION_FEES);
        driverUtil.getWebElement(RENUNCIATION_FEES).sendKeys("20.00");
        clearField(CODICIL_FEES);
        driverUtil.getWebElement(CODICIL_FEES).sendKeys("60.00");
        clearField(AFFIDAVIT_FEES);
        driverUtil.getWebElement(AFFIDAVIT_FEES).sendKeys("30.00");
        clearField(BOND_FEES);
        driverUtil.getWebElement(BOND_FEES).sendKeys("40.00");
        clearField(COMMISSION_FEES);
        driverUtil.getWebElement(COMMISSION_FEES).sendKeys("20.00");
        clearField(OTHER_1_FEES);
        driverUtil.getWebElement(OTHER_1_FEES).sendKeys("30.00");
        clearField(OTHER_2_FEES);
        driverUtil.getWebElement(OTHER_2_FEES).sendKeys("10.00");
        clearField(OTHER_3_FEES);
        driverUtil.getWebElement(OTHER_3_FEES).sendKeys("50.00");
        clearField(OTHER_4_FEES);
        driverUtil.getWebElement(OTHER_4_FEES).sendKeys("20.00");
        clearField(OTHER_5_FEES);
        driverUtil.getWebElement(OTHER_5_FEES).sendKeys("30.00");
        clearField(AUTOMATION_FEES);
        driverUtil.getWebElement(AUTOMATION_FEES).sendKeys("40.00");
        clearField(JCS_FEES);
        driverUtil.getWebElement(JCS_FEES).sendKeys("20.00");

        letterFeesForm = driverUtil.getWebElement(LETTER_FEES).getAttribute("value");
        shortCertificateFeesForm = driverUtil.getWebElement(SHORT_CERTIFICATE_FEES).getAttribute("value");
        renunciationFeesForm = driverUtil.getWebElement(RENUNCIATION_FEES).getAttribute("value");
        codicilFeesForm = driverUtil.getWebElement(CODICIL_FEES).getAttribute("value");
        affidavitFeesForm = driverUtil.getWebElement(AFFIDAVIT_FEES).getAttribute("value");
        bondFeesForm = driverUtil.getWebElement(BOND_FEES).getAttribute("value");
        commissionFeesForm = driverUtil.getWebElement(COMMISSION_FEES).getAttribute("value");
        otherFees1Form = driverUtil.getWebElement(OTHER_1_FEES).getAttribute("value");
        otherFees2Form = driverUtil.getWebElement(OTHER_2_FEES).getAttribute("value");
        otherFees3Form = driverUtil.getWebElement(OTHER_3_FEES).getAttribute("value");
        otherFees4Form = driverUtil.getWebElement(OTHER_4_FEES).getAttribute("value");
        otherFees5Form = driverUtil.getWebElement(OTHER_5_FEES).getAttribute("value");
        automationFeesForm = driverUtil.getWebElement(AUTOMATION_FEES).getAttribute("value");
        jcsFeesForm = driverUtil.getWebElement(JCS_FEES).getAttribute("value");
    }

    public void verifyTotalIsDisplayedCorrectly() throws AutomationException {
        DecimalFormat df = new DecimalFormat("0.00");
        double totalFees = 0.0;

        totalFees += Double.parseDouble(letterFeesForm);
        totalFees += Double.parseDouble(shortCertificateFeesForm);
        totalFees += Double.parseDouble(renunciationFeesForm);
        totalFees += Double.parseDouble(codicilFeesForm);
        totalFees += Double.parseDouble(affidavitFeesForm);
        totalFees += Double.parseDouble(bondFeesForm);
        totalFees += Double.parseDouble(commissionFeesForm);
        totalFees += Double.parseDouble(otherFees1Form);
        totalFees += Double.parseDouble(otherFees2Form);
        totalFees += Double.parseDouble(otherFees3Form);
        totalFees += Double.parseDouble(otherFees4Form);
        totalFees += Double.parseDouble(otherFees5Form);
        totalFees += Double.parseDouble(automationFeesForm);
        totalFees += Double.parseDouble(jcsFeesForm);

        WebDriverUtil.waitForAWhile();
        String calculatedTotalFees = df.format(totalFees);
        WebDriverUtil.waitForAWhile();

        totalFeesForm = driverUtil.getWebElement(TOTAL_FEES).getAttribute("value");

        if (!totalFeesForm.equals(calculatedTotalFees)) {
            throw new AutomationException("Total is incorrect.");
        }
    }

    public void verifyOnlyContactCanBeSelectedFromTheList() throws AutomationException {
        driverUtil.getWebElement(ATTORNEY_SELECTION_FIELD).click();

        WebDriverUtil.waitForAWhile(4);

        Actions actions = new Actions(DriverFactory.drivers.get());

        WebElement dropHereSection = driverUtil.getWebElement(DROP_BENE_XPATH);

        selectedAttorneyContactForm = driverUtil.getWebElement(DRAG_BENE).getText();
        if (!(selectedAttorneyContactForm.contains("Jr.") || selectedAttorneyContactForm.contains("Sr."))) {
            selectedAttorneyContactForm = selectedAttorneyContactForm.replace(",", "");
        }
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contacts updated successfully.")));
    }

    public void verifyAttorneyContactSInformationIsDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        attorneyFirmNameForm = CommonUtil.getJsonPath("attorney2").get("attorney2.entityName").toString();
        attorneyAddressLine1Form = CommonUtil.getJsonPath("attorney2").get("attorney2.addressLine1").toString();
        attorneyAddressLine2Form = CommonUtil.getJsonPath("attorney2").get("attorney2.addressLine2").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney2").get("attorney2.city").toString();
        String attorneyStateCode = CommonUtil.getJsonPath("attorney2").get("attorney2.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney2").get("attorney2.zip").toString();
        attorneyPhoneForm = CommonUtil.getJsonPath("attorney2").get("attorney2.phoneNumber").toString();
        attorneyFaxForm = CommonUtil.getJsonPath("attorney2").get("attorney2.fax").toString();

        attorneyCityStateZipForm = attorneyCity + ", " + attorneyStateCode + " " + attorneyZip;

        WebDriverUtil.waitForAWhile();

        verifyFetchedAttorneyContact(selectedAttorneyContactForm);
        verifyFetchedAttorneyContact(attorneyFirmNameForm);
        verifyFetchedAttorneyContact(attorneyAddressLine1Form);
        verifyFetchedAttorneyContact(attorneyAddressLine2Form);
        verifyFetchedAttorneyContact(attorneyCityStateZipForm);
        verifyFetchedAttorneyContact(attorneyPhoneForm);
        verifyFetchedAttorneyContact(attorneyFaxForm);
    }

    public void clickOnPrintFormButton() throws AutomationException, AWTException, InterruptedException {
        WebElement printButton = driverUtil.getWebElementAndScroll(PRINTFORM_BUTTON);
        ((JavascriptExecutor) DriverFactory.drivers.get()).executeScript("arguments[0].scrollIntoView({block: 'center'});", printButton);
        waitForVisibleElement(By.xpath(PRINTFORM_BUTTON));
        scrollToElementAndClick(PRINTFORM_BUTTON);

        Robot robot = new Robot();
        waitForAWhile(2); // Wait for the dialog to appear
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void verifyFormPrintedInPDFForm(String fileName) throws AutomationException {
        boolean isFileFound = false;
        int counter = 0;
        File[] files = null;
        do {
            try {
                files = FileUtil.getAllFiles((System.getProperty(OS) == null || System.getProperty(OS).equals(WINDOWS)) ? System.getProperty("user.dir") + "\\downloads" : System.getProperty("user.dir").replace("\\", "/") + "/downloads");

                CommonSteps.logInfo("Iterating over files");
                for (File file : files) {
                    if (file.exists() && !file.isDirectory()) {
                        CommonSteps.logInfo(file.getName());
                        DownloadedFileName = file.getName();

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


    public void verifyAllFieldsInDownloadedPDF() throws AutomationException, IOException {
        String pdfFilePath = ((System.getProperty("os.name").toLowerCase().contains("win"))
                ? System.getProperty("user.dir") + "\\downloads\\"
                : System.getProperty("user.dir") + "/downloads/")
                + DownloadedFileName;

        try {
            List<String> expectedAKANames = Arrays.asList(AKA1Form, akaName2Form, akaName3Form);
            boolean isverifiedAKANames = verifyAKANames(pdfFilePath, expectedAKANames);

            Map<String, String> expectedPropertyValues = new HashMap<>();
            expectedPropertyValues.put("enteredPersonalPropertyAmountForm", enteredPersonalPropertyAmountForm);
            expectedPropertyValues.put("enteredPennsylvaniaPropertyAmountForm", enteredPennsylvaniaPropertyAmountForm);
            expectedPropertyValues.put("enteredCountyPropertyAmountForm", enteredCountyPropertyAmountForm);
            expectedPropertyValues.put("enteredRealEstatePropertyAmountForm", enteredRealEstatePropertyAmountForm);
            expectedPropertyValues.put("totalEstimatedValueForm", totalEstimatedValueForm);

            boolean isverifiedPropertyAmount = verifyPropertyAmounts(pdfFilePath, expectedPropertyValues);


            Map<String, String> expectedAddressValues = new HashMap<>();
            expectedAddressValues.put("Street address, Post Office and Zip Code", modifiedEstateAddressForm);
            expectedAddressValues.put("City, Township or Borough", modifiedEstateCityForm);
            expectedAddressValues.put("County", modifiedEstateCountyForm);
            boolean isverifiedAddressDetails = verifyAddressDetails(pdfFilePath, expectedAddressValues);

            Map<String, String> expectedCodicilValues = new HashMap<>();
            expectedPropertyValues.put("Codicil Date1", rwCodicilDate1Form);
            expectedPropertyValues.put("Codicil Date2", rwCodicilDate2Form);
            expectedPropertyValues.put("Codicil Date3", rwCodicilDate3Form);


            boolean isverifiedCodicilDates = verifyCodicilDates(pdfFilePath,
                    "Petitioner(s) aver(s) he/she/they is/are the Executor(s) named in the Last Will of the Decedent, dated  12/09/2023 and Codicil(s)",
                    "Original Executor renounced.",
                    expectedCodicilValues,
                    "Codicil Dates");

            Map<String, String> expectedStateRelevantCircumstances = new HashMap<>();
            expectedStateRelevantCircumstances.put("State Relevant Circumstance 1", stateRelevantCircumstances1Form); // Expected: "Original Executor renounced."
            expectedStateRelevantCircumstances.put("State Relevant Circumstance 2", stateRelevantCircumstances2Form); // Expected: "Named Executor is deceased."

            boolean isverifiedStateRelevantCircumstances =  verifyStateRelevantCircumstances(pdfFilePath,
                    "thereto dated 02/17/2023 02/20/2023 02/26/2023",
                    "Estimate of value of decedents property at death:",
                    expectedStateRelevantCircumstances,
                    "State Relevant Circumstances");


            if (!isverifiedAKANames && !isverifiedPropertyAmount && !isverifiedAddressDetails && !isverifiedCodicilDates && !isverifiedStateRelevantCircumstances) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }
            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (AutomationException | IOException e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }


    public static boolean verifyAKANames(String pdfFilePath, List<String> expectedAKANames) throws IOException, AutomationException {

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Define markers
        String beforeLine = "Name: William John  File No: 22-23-1234";
        String afterLine = "Date of Death: 12/05/2020  Age at Death: 70";

        Set<String> extractedAKANames = new HashSet<>();
        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1;
        boolean captureNextLine = false;

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);

            // Identify start of occurrence
            if (trimmedLine.equalsIgnoreCase(beforeLine)) {
                startIndex = i;
            }

            // Identify end of occurrence
            if (startIndex != -1 && trimmedLine.equalsIgnoreCase(afterLine)) {
                startIndex = -1; // Reset for next occurrence
            }

            // Extract AKA names
            if (startIndex != -1) {
                if (trimmedLine.startsWith("a/k/a:")) {
                    // If line starts with a/k/a:, extract normally
                    extractedAKANames.addAll(extractAKANames(trimmedLine));
                    captureNextLine = true;  // Enable flag to capture next line
                } else if (captureNextLine) {
                    // If previous line was a/k/a, process this as a continuation
                    extractedAKANames.addAll(extractAKANames(trimmedLine));
                    captureNextLine = false;  // Reset flag after capturing
                }
            }
        }

        // Log extracted names
        for (String extractedName : extractedAKANames) {
            CommonSteps.logInfo("Expected AKA Names: " + expectedAKANames + " Extracted AKA Names: [" + extractedName + "] found");
        }

        // Validate extracted values
        if (extractedAKANames.isEmpty()) {
            throw new AutomationException("❌ No AKA Names found in the PDF.");
        }

        // Convert expected names to a Set for better comparison
        Set<String> expectedAKANamesSet = new HashSet<>(expectedAKANames);

        if (!expectedAKANamesSet.equals(extractedAKANames)) {
            throw new AutomationException("❌ Validation Failed: Extracted AKA Names '" + extractedAKANames
                    + "' do not match expected values '" + expectedAKANamesSet + "'.");
        }

        CommonSteps.logInfo("✅ Validation Passed: All extracted AKA Names match expected values.");
        return true;
    }

    /**
     * Extracts AKA names while handling edge cases like SSNs and parentheses.
     */
    private static Set<String> extractAKANames(String rawText) {
        Set<String> akaNames = new HashSet<>();

        // ✅ Remove "a/k/a:" prefix if present
        String cleanedText = rawText.replace("a/k/a:", "").trim();

        // ✅ Remove unnecessary suffixes (like "(Assigned by Register)")
        cleanedText = cleanedText.replaceAll("\\(.*?\\)", "").trim();  // Removes anything inside parentheses

        // ✅ Ignore SSNs (if they exist in the text)
        if (!cleanedText.matches(".*\\d{3}-\\d{2}-\\d{4}.*")) {
            akaNames.addAll(Arrays.asList(cleanedText.split("\\s+"))); // Split by spaces for multi-word names
        }

        return akaNames;
    }
    
    public static boolean verifyPropertyAmounts(String pdfFilePath, Map<String, String> expectedValues) throws IOException, AutomationException {

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Define markers
        String beforeLine = "Date of Death: 12/05/2020  Age at Death: 70";
        String afterLine = "A. Petition for Probate and Grant of Letters Testamentary";

        // Extract occurrences dynamically
        Map<String, String> extractedValues = new LinkedHashMap<>();
        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Identify start of occurrence
            if (trimmedLine.replaceAll("\\s+", " ").contains(beforeLine.replaceAll("\\s+", " "))) {
                startIndex = i;
            }

            // Identify end of occurrence
            if (startIndex != -1 && trimmedLine.replaceAll("\\s+", " ").contains(afterLine.replaceAll("\\s+", " "))) {
                // Extract values from lines between beforeLine and afterLine
                for (int j = startIndex + 1; j < i; j++) {
                    extractAndStorePropertyAmount(allLines[j].trim(), extractedValues);
                }
                startIndex = -1; // Reset for next occurrence
            }
        }

        // Log extracted values
        CommonSteps.logInfo("📌 Extracted Property Amounts: " + extractedValues);

        // Validate extracted values
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();
            String extractedValue = extractedValues.get(expectedKey);

            if (extractedValue == null) {
                throw new AutomationException("❌ Missing Property Amount: No value found for '" + expectedKey + "'");
            }

            CommonSteps.logInfo("Expected Value for " + expectedKey + ": " + expectedValue + " | Extracted: " + extractedValue);

            if (!expectedValue.equals(extractedValue)) {
                throw new AutomationException("❌ Validation Failed: Expected '" + expectedKey + "' = '" + expectedValue +
                        "', but Extracted '" + extractedValue + "'");
            }
        }

        CommonSteps.logInfo("✅ Validation Passed: All property amounts match expected values.");
        return true;
    }


    private static void extractAndStorePropertyAmount(String line, Map<String, String> extractedValues) {
        // Define known mappings for expected property amounts
        Map<String, String> propertyMapping = new LinkedHashMap<>();
        propertyMapping.put("All personal property", "enteredPersonalPropertyAmountForm");
        propertyMapping.put("Personal property in Pennsylvania", "enteredPennsylvaniaPropertyAmountForm");
        propertyMapping.put("Personal property in County", "enteredCountyPropertyAmountForm");
        propertyMapping.put("Value of real estate in Pennsylvania", "enteredRealEstatePropertyAmountForm");
        propertyMapping.put("Total Estimated Value", "totalEstimatedValueForm"); // More specific match

        boolean containsDollarOnly = line.trim().matches("^\\$\\s*[\\d,.]+$");

        for (Map.Entry<String, String> entry : propertyMapping.entrySet()) {
            String key = entry.getKey();
            String variableName = entry.getValue();

            if (line.contains(key)) {
                Matcher matcher = Pattern.compile("\\$\\s*([\\d,.]+)").matcher(line);
                if (matcher.find()) {
                    extractedValues.put(variableName, matcher.group(1));
                }
                return; // Exit early after finding the match
            }
        }

        // Handle a standalone total amount line (e.g., "$ 990.00")
        if (containsDollarOnly && !extractedValues.containsKey("totalEstimatedValueForm")) {
            Matcher matcher = Pattern.compile("\\$\\s*([\\d,.]+)").matcher(line);
            if (matcher.find()) {
                extractedValues.put("totalEstimatedValueForm", matcher.group(1));
            }
        }
    }
    public static boolean verifyAddressDetails(String pdfFilePath, Map<String, String> expectedValues) throws AutomationException {
        // Define the expected keys
        String streetKey = "Street address, Post Office and Zip Code";
        String cityKey = "City, Township or Borough";
        String countyKey = "County";

        Map<String, String> extractedValues = new LinkedHashMap<>();
        CommonSteps.logInfo("🔍 Starting Address Verification");

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfContent = pdfStripper.getText(document);

            // Split PDF content into lines
            String[] pdfLines = pdfContent.split("\\r?\\n");
            int occurrenceCount = 0; // Counter for occurrences
            int targetIndex = -1;    // Index of the 3rd occurrence

            // Find the 3rd occurrence of the header
            for (int i = 0; i < pdfLines.length; i++) {
                if (pdfLines[i].contains("Street address, Post Office and Zip Code City, Township or Borough County")) {
                    occurrenceCount++;
                    if (occurrenceCount == 3) {
                        targetIndex = i;
                        break;
                    }
                }
            }

            // Validate if the 3rd occurrence was found
            if (targetIndex == -1 || targetIndex < 2) {
                throw new AutomationException("⚠️ Could not find the 3rd occurrence of the address header in the PDF.");
            }

            // Extract the address from two lines above the 3rd occurrence
            String addressLine = pdfLines[targetIndex - 2].trim();
            CommonSteps.logInfo("📌 Identified Address Line (3rd Occurrence): '" + addressLine + "'");

            // 🔹 Clean extracted address line
            addressLine = cleanAddressLine(addressLine);

            // Split and extract street, city, and county
            String[] addressParts = addressLine.split("\\s+");

            if (addressParts.length >= 3) {
                // Assuming last 2 words are city and county, rest is the street
                String county = addressParts[addressParts.length - 1].trim();
                String city = addressParts[addressParts.length - 2].trim();
                String street = String.join(" ", Arrays.copyOf(addressParts, addressParts.length - 2)).trim();

                extractedValues.put(streetKey, street);
                extractedValues.put(cityKey, city);
                extractedValues.put(countyKey, county);

                CommonSteps.logInfo("📌 Extracted Address Details: " + extractedValues);
            } else {
                throw new AutomationException("⚠️ Address line format unexpected -> " + addressLine);
            }

        } catch (IOException e) {
            throw new AutomationException("Error reading PDF file: " + pdfFilePath);
        }

        // Validate extracted values against expected values
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();
            String extractedValue = extractedValues.get(expectedKey);

            if (extractedValue == null || !extractedValue.equalsIgnoreCase(expectedValue)) {
                throw new AutomationException("❌ Mismatch for '" + expectedKey + "': Expected '" + expectedValue + "' but extracted '" + extractedValue + "'");
            } else {
                CommonSteps.logInfo("✅ Verified " + expectedKey + ": " + extractedValue);
            }
        }

        CommonSteps.logInfo("✅ Address verification PASSED!");
        return true;
    }


    private static String cleanAddressLine(String addressLine) {
        return addressLine.replaceAll("Real estate in Pennsylvania situated at", "").trim();
    }




    private static boolean verifyCodicilDates(String pdfFilePath, String beforeLine, String afterLine, Map<String, String> expectedValues, String fieldName) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1, endIndex = -1;
        List<String> extractedValues = new ArrayList<>();

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            if (trimmedLine.contains(beforeLine.trim())) {
                startIndex = i;
            }
            if (trimmedLine.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                String currentLine = allLines[i].trim();
                if (!currentLine.isBlank()) {
                    extractedValues.add(clean(currentLine, fieldName)); // Store each extracted value
                }
            }

            if (extractedValues.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No '" + fieldName + "' found between specified lines.");
            }

            // Check extracted values against expected values from the map
            for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
                String expectedKey = entry.getKey();  // e.g., "Codicil Date1"
                String expectedValue = entry.getValue(); // e.g., "02/17/2023"
                boolean matchFound = false;

                for (String extracted : extractedValues) {
                    if (expectedValue.equalsIgnoreCase(extracted)) {
                        CommonSteps.logInfo("Expected " + " (" + expectedKey + "): [" + expectedValue + "] Extracted: [" + extracted + "] ✅ Matched!");
                        matchFound = true;
                        break; // Move to the next expected value
                    }
                }

                if (!matchFound) {
                    CommonSteps.logInfo("Expected " + " (" + expectedKey + "): [" + expectedValue + "] ❌ Not Found!");
                    throw new AutomationException("❌ Validation Failed: Expected '" + expectedKey + "' value not found in extracted values.");
                }
            }
        } else {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }
        return true;
    }

    private static String clean(String rawText, String fieldType) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        String cleanedText = rawText.trim();

        switch (fieldType.toLowerCase()) {
            case "codicil dates":
                cleanedText = cleanedText.replaceAll("(?i)\\b(thereto dated )\\b", "").trim();
                break;
        }
        return cleanedText;
    }

    private static boolean verifyStateRelevantCircumstances(String pdfFilePath, String beforeLine, String afterLine, Map<String, String> expectedValues, String fieldName) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1, endIndex = -1;
        List<String> extractedValues = new ArrayList<>();

        // Identify the start and end indexes
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            if (trimmedLine.contains(beforeLine.trim())) {
                startIndex = i;
            }
            if (trimmedLine.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            // Extract relevant lines between beforeLine and afterLine
            for (int i = startIndex + 1; i < endIndex; i++) {
                String currentLine = allLines[i].trim();
                if (!currentLine.isBlank()) {
                    extractedValues.add(currentLine);
                }
            }

            if (extractedValues.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No '" + fieldName + "' found between specified lines.");
            }

            // Validate extracted values against expected values
            for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
                String expectedKey = entry.getKey();
                String expectedValue = entry.getValue();
                boolean found = extractedValues.stream().anyMatch(val -> val.equalsIgnoreCase(expectedValue));

                CommonSteps.logInfo("Expected " + fieldName + ": " + expectedValue + ", Extracted: " + extractedValues + " -> " + (found ? "✅ Found" : "❌ Not Found"));

                if (!found) {
                    throw new AutomationException("❌ Validation Failed: '" + expectedKey + "' does not match expected value.");
                }
            }

            CommonSteps.logInfo("✅ Validation Passed for " + fieldName);
        } else {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }
        return true;
    }


    public void userResetsTheRWForm() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        driverUtil.getWebElement("//body").click();

        driverUtil.getWebElement(ATTORNEY_SELECTION_FIELD).click();

        driverUtil.getWebElement("//span[@class='cursor']").click();

        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contacts updated successfully.")));

        driverUtil.getWebElement(FIRST_PAGE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));

        DriverFactory.drivers.get().findElement(By.xpath(OPTION_A_CHECKBOX)).click();

        DriverFactory.drivers.get().findElement(By.xpath(RW_AKA_FIELD2)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(RW_AKA_FIELD3)).clear();

        selectPropertyOption(PERSONAL_PROPERTY_DRDWN, "0");
        selectPropertyOption(PENNSYLVANIA_PROPERTY_DRDWN, "0");
        selectPropertyOption(COUNTY_PROPERTY_DRDWN, "0");
        selectPropertyOption(REAL_ESTATE_PROPERTY_DRDWN, "0");

        DriverFactory.drivers.get().findElement(By.xpath(PERSONAL_PROPERTY_AMOUNT)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(PENNSYLVANIA_PROPERTY_AMOUNT)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(COUNTY_PROPERTY_AMOUNT)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(REAL_ESTATE_PROPERTY_AMOUNT)).clear();
        actions.sendKeys(Keys.TAB);

        DriverFactory.drivers.get().findElement(By.xpath(ESTATE_PENNSYLVANIA_ADDRESS)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(ESTATE_PENNSYLVANIA_CITY)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(ESTATE_PENNSYLVANIA_COUNTY)).clear();
        actions.sendKeys(Keys.TAB);

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebDriverUtil.waitForAWhile();

        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_HEIRS_ON_ATTACHMENT_CHKBX)).click();
    }
}

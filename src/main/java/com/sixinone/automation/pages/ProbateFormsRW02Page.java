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

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.*;

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
    public static final String DECEDENT_TAB = "//span[text()='Decedent']";
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
    private static final String ATTORNEY_ERROR_ALERT = "//div[text()='Only one Attorney contacts is allowed to be selected.']";
    private static final String ATTORNEY_FETCHED_FIELD = "//div//p[text()='Attorney Signature:']/following-sibling::p//input[contains(@value,'%s')]";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";


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
    static String selectedAttorneyContactForm;
    static String rwCodicilDate1Form;
    static String rwCodicilDate2Form;
    static String rwCodicilDate3Form;
    static String modifiedEstateAddressForm;
    static String modifiedEstateCityForm;
    static String modifiedEstateCountyForm;

    static String DownloadedFileName;

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

    private void fillFieldWithClipboard(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        String value = CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString();
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);

        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }

    public void userSavesEstateInfo() throws AutomationException, IOException, ParseException {
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

        //temp use
        Actions actions = new Actions(DriverFactory.drivers.get());
        fillFieldWithClipboard(CODICILE_DATE_1, "EstateCreate.codicilDate1",actions);
        fillFieldWithClipboard(CODICILE_DATE_2, "EstateCreate.codicilDate2",actions);
        fillFieldWithClipboard(CODICILE_DATE_3, "EstateCreate.codicilDate3",actions);
        driverUtil.getWebElement("//body").click();

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
        verifyAutoPopulatedValue(enteredFirstName+" "+enteredLastName);
        verifyAutoPopulatedValue(enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3);
        verifyAutoPopulatedValue(enteredDateOfDeath);
        verifyAutoPopulatedValue(enteredSSN);
        verifyAutoPopulatedValue(enteredAgeAtDeath);
        verifyAutoPopulatedValue(enteredDomicileAddressLine1+", "+enteredDomicileAddressLine2+", "+enteredDomicileZip);
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
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDomicileAddressLine1+", "+enteredDomicileAddressLine2+", "+enteredDomicileZip));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredDomicileCity));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathAddressLine1));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathCity));
        verifyFieldIsNotEditable(String.format(RW_AUTO_POPULATED_INPUT_FIELD_XPATH,enteredPlaceOfDeathCountry));
    }

    public void verifyMultipleAkaNamesCanBeAddedSeparatedByComma() throws IOException, ParseException, AutomationException {
        String akaName2 = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName2").toString();
        String akaName3 = CommonUtil.getJsonPath("RW02Form").get("RW02Form.akaName3").toString();

        DriverFactory.drivers.get().findElement(By.xpath(RW_AKA_FIELD2)).clear();
        driverUtil.getWebElement(RW_AKA_FIELD2).sendKeys(akaName2);
        DriverFactory.drivers.get().findElement(By.xpath(RW_AKA_FIELD3)).clear();
        driverUtil.getWebElement(RW_AKA_FIELD3).sendKeys(akaName3);
        CommonSteps.takeScreenshot();
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

        WebDriverUtil.waitForAWhile();

        personalPropertyForm = getSelectedOption(PERSONAL_PROPERTY_DRDWN);
        pennsylvaniaPropertyForm = getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN);
        countryPropertyForm = getSelectedOption(COUNTY_PROPERTY_DRDWN);
        realEstatePropertyForm = getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN);
    }

    public void verifySelectedValuesAreRetainedAndAutoSaved() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        if(!getSelectedOption(PERSONAL_PROPERTY_DRDWN).equals(personalPropertyForm)){
            throw new AutomationException("Selected value "+personalPropertyForm+" is not retained");
        }

        if(!getSelectedOption(PENNSYLVANIA_PROPERTY_DRDWN).equals(pennsylvaniaPropertyForm)){
            throw new AutomationException("Selected value "+pennsylvaniaPropertyForm+" is not retained");
        }

        if(!getSelectedOption(COUNTY_PROPERTY_DRDWN).equals(countryPropertyForm)){
            throw new AutomationException("Selected value "+countryPropertyForm+" is not retained");
        }

        if(!getSelectedOption(REAL_ESTATE_PROPERTY_DRDWN).equals(realEstatePropertyForm)){
            throw new AutomationException("Selected value "+realEstatePropertyForm+" is not retained");
        }

        CommonSteps.takeScreenshot();

        selectPropertyOption(PERSONAL_PROPERTY_DRDWN, "0");
        selectPropertyOption(PENNSYLVANIA_PROPERTY_DRDWN, "0");
        selectPropertyOption(COUNTY_PROPERTY_DRDWN, "0");
        selectPropertyOption(REAL_ESTATE_PROPERTY_DRDWN, "0");

        enteredAKA1Form = driverUtil.getWebElement(RW_AKA_FIELD1).getAttribute("value");
        enteredAKA2Form = driverUtil.getWebElement(RW_AKA_FIELD2).getAttribute("value");
        enteredAKA3Form = driverUtil.getWebElement(RW_AKA_FIELD3).getAttribute("value");

    }

    public void verifyAmountCanBeEnteredInAllTheFieldsAndAutoSaved() throws AutomationException {
        WebElement personalPropertyAmount = driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT);
        WebElement pennsylvaniaPropertyAmount = driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT);
        WebElement countyPropertyAmount = driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT);
        WebElement realEstatePropertyAmount = driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT);

        clearField(PERSONAL_PROPERTY_AMOUNT);
        personalPropertyAmount.sendKeys("220.00");
        clearField(PENNSYLVANIA_PROPERTY_AMOUNT);
        pennsylvaniaPropertyAmount.sendKeys("430.00");
        clearField(COUNTY_PROPERTY_AMOUNT);
        countyPropertyAmount.sendKeys("550.00");
        clearField(REAL_ESTATE_PROPERTY_AMOUNT);
        realEstatePropertyAmount.sendKeys("670.00");
        realEstatePropertyAmount.sendKeys(Keys.TAB);

        enteredPersonalPropertyAmountForm = personalPropertyAmount.getAttribute("value");
        enteredPennsylvaniaPropertyAmountForm = pennsylvaniaPropertyAmount.getAttribute("value");
        enteredCountyPropertyAmountForm = countyPropertyAmount.getAttribute("value");
        enteredRealEstatePropertyAmountForm = realEstatePropertyAmount.getAttribute("value");
    }

    public void verifyAmountEnteredInAllTheFieldsAreAutoSaved() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        if(!driverUtil.getWebElement(PERSONAL_PROPERTY_AMOUNT).getAttribute("value").equals(enteredPersonalPropertyAmountForm)){
            throw new AutomationException("Entered value "+enteredPersonalPropertyAmountForm+" is not retained");
        }

        if(!driverUtil.getWebElement(PENNSYLVANIA_PROPERTY_AMOUNT).getAttribute("value").equals(enteredPennsylvaniaPropertyAmountForm)){
            throw new AutomationException("Entered value "+enteredPennsylvaniaPropertyAmountForm+" is not retained");
        }

        if(!driverUtil.getWebElement(COUNTY_PROPERTY_AMOUNT).getAttribute("value").equals(enteredCountyPropertyAmountForm)){
            throw new AutomationException("Entered value "+enteredCountyPropertyAmountForm+" is not retained");
        }

        if(!driverUtil.getWebElement(REAL_ESTATE_PROPERTY_AMOUNT).getAttribute("value").equals(enteredRealEstatePropertyAmountForm)){
            throw new AutomationException("Entered value "+enteredRealEstatePropertyAmountForm+" is not retained");
        }
    }

    public void verifyTotalEstimatedValueIsTheTotalOfFirstAndLastFieldsOnly() throws AutomationException {
        String sumOfFistAndLastFields = String.format("%.2f", Double.parseDouble(enteredPersonalPropertyAmountForm) + Double.parseDouble(enteredRealEstatePropertyAmountForm));
        String totalEstimatedValue = driverUtil.getWebElement(TOTAL_ESTIMATED_VALUE).getAttribute("value");
        if(!totalEstimatedValue.equals(sumOfFistAndLastFields)){
            throw new AutomationException("The total estimated value is not the total of first and last fields only");
        }
    }

    public void userChecksUsePrincipalResidenceCheckbox() throws AutomationException {
        DriverFactory.drivers.get().findElement(By.xpath(USE_PRINCIPAL_RESIDENCE)).click();
    }

    public void verifyAddressFromPrincipalResidenceAtFieldIsCopiedToRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String estateAddress = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        String estateCity = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        String estateCounty = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");

        if(!(enteredDomicileAddressLine1+", "+enteredDomicileAddressLine2+", "+enteredDomicileZip).equals(estateAddress)){
            throw new AutomationException("Address is not copied correctly");
        }

        if(!enteredDomicileCity.equals(estateCity)){
            throw new AutomationException("City is not copied correctly");
        }

        if(!enteredDomicileCountry.equals(estateCounty)){
            throw new AutomationException("County is not copied correctly");
        }

        copiedEstateAddressForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        copiedEstateCityForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        copiedEstateCountyForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");
    }

    public void verifyUncheckingTheCheckboxDoesNotClearTheRealEstateInPennsylvaniaSituatedAtField() throws AutomationException {
        userChecksUsePrincipalResidenceCheckbox();

        if(driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value")==null){
            throw new AutomationException("The address does not retained, the field is empty.");
        }

        if(driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value")==null){
            throw new AutomationException("The address does not retained, the field is empty.");
        }

        if(driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value")==null){
            throw new AutomationException("The address does not retained, the field is empty.");
        }
    }

    public void verifyCopiedAddressIsRetainedAndAutoSaved() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        WebDriverUtil.waitForAWhile();

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value").equals(copiedEstateAddressForm)){
            throw new AutomationException("Copied value "+copiedEstateAddressForm+" is not retained");
        }

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value").equals(copiedEstateCityForm)){
            throw new AutomationException("Copied value "+copiedEstateCityForm+" is not retained");
        }

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value").equals(copiedEstateCountyForm)){
            throw new AutomationException("Copied value "+copiedEstateCountyForm+" is not retained");
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

        WebDriverUtil.waitForAWhile();

        modifiedEstateAddressForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value");
        modifiedEstateCityForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value");
        modifiedEstateCountyForm = driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value");
    }

    public void verifyModificationsAreSavedSuccessfully() throws AutomationException {
        clickOnRWForm("RW 01");
        clickOnRWForm("RW 02");

        WebDriverUtil.waitForAWhile();

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_ADDRESS).getAttribute("value").equals(modifiedEstateAddressForm)){
            throw new AutomationException("Modified value "+modifiedEstateAddressForm+" is not retained");
        }

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_CITY).getAttribute("value").equals(modifiedEstateCityForm)){
            throw new AutomationException("Modified value "+modifiedEstateCityForm+" is not retained");
        }

        if(!driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).getAttribute("value").equals(modifiedEstateCountyForm)){
            throw new AutomationException("Modified value "+modifiedEstateCountyForm+" is not retained");
        }

        CommonSteps.takeScreenshot();

        clearField(ESTATE_PENNSYLVANIA_ADDRESS);
        clearField(ESTATE_PENNSYLVANIA_CITY);
        clearField(ESTATE_PENNSYLVANIA_COUNTY);
        driverUtil.getWebElement(ESTATE_PENNSYLVANIA_COUNTY).sendKeys(Keys.TAB);
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

        if(!fetchedDiedDate.equals(enteredDateOfWill)){
            throw new AutomationException("Decedent died date is not auto fetched");
        }
    }

    public void verifyCodicilDateValuesAreAutoFetched() throws AutomationException {
        String codicilDate1 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_1).getAttribute("value");
        String codicilDate2 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_2).getAttribute("value");
        String codicilDate3 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_3).getAttribute("value");

        System.out.println("fetched codicil 1: "+codicilDate1);
        System.out.println("entered codicil 1: "+enteredCodicilDate1);

        System.out.println("fetched codicil 2: "+codicilDate2);
        System.out.println("entered codicil 2: "+enteredCodicilDate2);

        System.out.println("fetched codicil 3: "+codicilDate3);
        System.out.println("entered codicil 3: "+enteredCodicilDate3);


        if(!codicilDate1.equals(enteredCodicilDate1)){
            throw new AutomationException("Codicil date 1 is not auto fetched");
        }
        if(!codicilDate2.equals(enteredCodicilDate2)){
            throw new AutomationException("Codicil date 2 is not auto fetched");
        }
        if(!codicilDate3.equals(enteredCodicilDate3)){
            throw new AutomationException("Codicil date 3 is not auto fetched");
        }
    }

    public void userUpdatesCodicilDates() throws AutomationException {
        WebElement codicilDate1 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_1);
        WebElement codicilDate2 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_2);
        WebElement codicilDate3 = driverUtil.getWebElement(FETCHED_CODICIL_DATE_3);

        clearField(FETCHED_CODICIL_DATE_1);
        codicilDate1.sendKeys("02/14/2023");
        clearField(FETCHED_CODICIL_DATE_2);
        codicilDate2.sendKeys("02/22/2023");
        clearField(FETCHED_CODICIL_DATE_3);
        codicilDate3.sendKeys("02/26/2023");
        codicilDate3.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();

        rwCodicilDate1Form = codicilDate1.getAttribute("value");
        rwCodicilDate2Form = codicilDate2.getAttribute("value");
        rwCodicilDate3Form = codicilDate3.getAttribute("value");
    }

    public void verifyCodicilDatesReflectedInTheCodicilDatesInDecedentTab() throws AutomationException {
        driverUtil.getWebElement(SHOW_ALL_TABS_BTN).click();
        WebDriverUtil.waitForAWhile();

        waitForVisibleElement(By.xpath(DECEDENT_TAB));
        driverUtil.getWebElement(DECEDENT_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        if(!driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value").equals(rwCodicilDate1Form)){
            throw new AutomationException("Updated codicil date 1 is not reflected");
        }

        if(!driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value").equals(rwCodicilDate2Form)){
            throw new AutomationException("Updated codicil date 2 is not reflected");
        }

        if(!driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value").equals(rwCodicilDate3Form)){
            throw new AutomationException("Updated codicil date 3 is not reflected");
        }

    }

    public void verifyTextCanBeEnteredInTheStateRelevantCircumstancesTextFields() throws AutomationException {
        driverUtil.getWebElement(STATE_RELEVANT_CIRCUMSTANCES_1).sendKeys("Original Executor renounced.");
        driverUtil.getWebElement(STATE_RELEVANT_CIRCUMSTANCES_2).sendKeys("Named Executor is deceased.");
    }

    public void userChecksExceptionsCheckboxFromOptionA() {
        DriverFactory.drivers.get().findElement(By.xpath(A_EXCEPTION)).click();
    }

    public void verifyTextFieldIsEnabledAndTextCanBeEntered() throws AutomationException {
        WebElement textField = driverUtil.getWebElement(A_EXCEPTION_TEXT);
        WebDriverUtil.waitForAWhile();
        if(!textField.isEnabled()){
            throw new AutomationException("Text field is not enabled");
        }

        textField.sendKeys("The originally named Executor has renounced their right to serve.");
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
        WebDriverUtil.waitForAWhile();
        if(!beneField.isEnabled()){
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

    public void verifyCorrectBeneficiaryNameRelationshipAndAddressIsDisplayed() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contacts updated successfully.")));

        verifyBeneContactInTable(BeneContact1Form);
        verifyBeneContactInTable(BeneContact4Form);
        verifyBeneContactInTable(BeneContact5Form);
        verifyBeneContactInTable(BeneContact3Form);

        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");
        verifyBeneContactInTable("None");


        BeneContactAddress1Form = estateContact.get("Garcia, Olivia").get("addressLine1")+", "+estateContact.get("Garcia, Olivia").get("city")+", "+"AZ, "+estateContact.get("Garcia, Olivia").get("zip");
        BeneContactAddress4Form = estateContact.get("Rodriguez, Isabella").get("addressLine1")+", "+estateContact.get("Rodriguez, Isabella").get("city")+", "+"CO, "+estateContact.get("Rodriguez, Isabella").get("zip");
        BeneContactAddress5Form = estateContact.get("White, Matthew").get("addressLine1")+", "+estateContact.get("White, Matthew").get("city")+", "+"WA, "+estateContact.get("White, Matthew").get("zip");
        BeneContactAddress3Form = estateContact.get("Martinez, Sophia").get("addressLine1")+", "+estateContact.get("Martinez, Sophia").get("city")+", "+"FL, "+estateContact.get("Martinez, Sophia").get("zip");
        BeneContactAddress2Form = estateContact.get("Lee, Christopher").get("addressLine1")+", "+estateContact.get("Lee, Christopher").get("city")+", "+"CA, "+estateContact.get("Lee, Christopher").get("zip");

        verifyBeneContactInTable(BeneContactAddress1Form);
        verifyBeneContactInTable(BeneContactAddress4Form);
        verifyBeneContactInTable(BeneContactAddress5Form);
        verifyBeneContactInTable(BeneContactAddress3Form);
    }

    public void verifyContactsAreTransferredToAttachment() throws AutomationException {
        driverUtil.getWebElement(BENE_VIEW_ATTACHMENT).click();
        WebDriverUtil.waitForAWhile();

        verifyBeneContactOnAttachment(BeneContact2Form);
        verifyBeneContactAddressAndRelationOnAttachment("None");
        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("Lee, Christopher").get("addressLine1"));

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
    }

    public void userChecksDisplayALLHeirsOnAttachmentCheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_HEIRS_ON_ATTACHMENT_CHKBX)).click();
    }

    public void verifyAllTheContactsAreTransferredToAttachment() throws AutomationException {
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

        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("Garcia, Olivia").get("addressLine1"));
        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("Rodriguez, Isabella").get("addressLine1"));
        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("White, Matthew").get("addressLine1"));
        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("Martinez, Sophia").get("addressLine1"));
        verifyBeneContactAddressAndRelationOnAttachment(estateContact.get("Lee, Christopher").get("addressLine1"));

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
        if(beneField.isEnabled()){
            throw new AutomationException("Beneficiaries' selection field is enabled");
        }
    }

    public void verifyPetitionerSNameAreByDefaultPrintedOnTheTable() throws AutomationException {
        String fiduciaryContact1 = estateContact.get("Clark, David").get("firstName")+" "+estateContact.get("Clark, David").get("middleName")+" "+estateContact.get("Clark, David").get("lastName")+" "+estateContact.get("Clark, David").get("suffix");
        String fiduciaryContact2 = estateContact.get("Harris, Daniel").get("firstName")+" "+estateContact.get("Harris, Daniel").get("middleName")+" "+estateContact.get("Harris, Daniel").get("lastName")+" "+estateContact.get("Harris, Daniel").get("suffix");
        String fiduciaryContact3 = estateContact.get("Lawns, Pritav").get("firstName")+" "+estateContact.get("Lawns, Pritav").get("middleName")+" "+estateContact.get("Lawns, Pritav").get("lastName")+" "+estateContact.get("Lawns, Pritav").get("suffix");
        String fiduciaryContact4 = estateContact.get("Smith, Michael").get("firstName")+" "+estateContact.get("Smith, Michael").get("middleName")+" "+estateContact.get("Smith, Michael").get("lastName")+" "+estateContact.get("Smith, Michael").get("suffix");

        driverUtil.getWebElement(SECOND_PAGE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));

        verifyAutoPopulatedValue(fiduciaryContact1);
        verifyAutoPopulatedValue(fiduciaryContact2);
        verifyAutoPopulatedValue(fiduciaryContact3);
        verifyAutoPopulatedValue(fiduciaryContact4);
    }

    public void verifyDecreeOfRegisterInformationDisplayedCorrectly() throws AutomationException {
        String fiduciaryContact1 = estateContact.get("Clark, David").get("firstName")+" "+estateContact.get("Clark, David").get("middleName")+" "+estateContact.get("Clark, David").get("lastName")+" "+estateContact.get("Clark, David").get("suffix");
        String fiduciaryContact2 = estateContact.get("Harris, Daniel").get("firstName")+" "+estateContact.get("Harris, Daniel").get("middleName")+" "+estateContact.get("Harris, Daniel").get("lastName")+" "+estateContact.get("Harris, Daniel").get("suffix");
        String fiduciaryContact3 = estateContact.get("Lawns, Pritav").get("firstName")+" "+estateContact.get("Lawns, Pritav").get("middleName")+" "+estateContact.get("Lawns, Pritav").get("lastName")+" "+estateContact.get("Lawns, Pritav").get("suffix");
        String fiduciaryContact4 = estateContact.get("Smith, Michael").get("firstName")+" "+estateContact.get("Smith, Michael").get("middleName")+" "+estateContact.get("Smith, Michael").get("lastName")+" "+estateContact.get("Smith, Michael").get("suffix");
        String fiduciaryContact5 = estateContact.get("Wilson, Emily").get("firstName")+" "+estateContact.get("Wilson, Emily").get("middleName")+" "+estateContact.get("Wilson, Emily").get("lastName")+" "+estateContact.get("Wilson, Emily").get("suffix");

        verifyAutoPopulatedValue(enteredFirstName+" "+enteredLastName);
        verifyAutoPopulatedValue(enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3);
        verifyAutoPopulatedValue(enteredAKA1Form+" "+enteredAKA2Form+" "+enteredAKA3Form);
        verifyAutoPopulatedValue(fiduciaryContact1+", "+fiduciaryContact2+", "+fiduciaryContact3+", "+fiduciaryContact4+", "+fiduciaryContact5);

        verifyAutoPopulatedValue(enteredDateOfWill);
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

        String calculatedTotalFees = df.format(totalFees);
        WebDriverUtil.waitForAWhile();

        if(!driverUtil.getWebElement(TOTAL_FEES).getAttribute("value").equals(calculatedTotalFees)){
            throw new AutomationException("Total is incorrect.");
        }
    }

    public void verifyOnlyContactCanBeSelectedFromTheList() throws AutomationException {
        driverUtil.getWebElement(ATTORNEY_SELECTION_FIELD).click();

        //temp use
        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
        driverUtil.getWebElement(ATTORNEY_SELECTION_FIELD).click();

        Actions actions = new Actions(DriverFactory.drivers.get());

        WebElement dropHereSection = driverUtil.getWebElement(DROP_BENE_XPATH);

        selectedAttorneyContactForm = driverUtil.getWebElement(DRAG_BENE).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

//        actions.dragAndDrop(driverUtil.getWebElement(DRAG_BENE), dropHereSection).perform();
//        WebDriverUtil.waitForAWhile();
//
//        if(!driverUtil.getWebElement(ATTORNEY_ERROR_ALERT).isDisplayed()){
//            throw new AutomationException("Multiple Attorneys are selected");
//        }

        CommonSteps.takeScreenshot();

//        WebDriverUtil.waitForInvisibleElement(By.xpath(ATTORNEY_ERROR_ALERT));

        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contacts updated successfully.")));
    }

    public static String getKeyFromName(String fullName) {
        String[] parts = fullName.split(" ");
        String lastName = parts[parts.length - 2];
        String firstName = parts[0];
        return lastName + ", " + firstName;
    }

    public void verifyAttorneyContactSInformationIsDisplayedCorrectly() throws AutomationException {
        String attorneyContactKey = getKeyFromName(selectedAttorneyContactForm);

        String attorneyFirmName = estateContact.get(attorneyContactKey).get("entityName");
        String attorneyAddressLine1 = estateContact.get(attorneyContactKey).get("addressLine1");
        String attorneyAddressLine2 = estateContact.get(attorneyContactKey).get("addressLine2");
        String attorneyCity = estateContact.get(attorneyContactKey).get("city");
        String attorneyPhone = estateContact.get(attorneyContactKey).get("phoneNumber");
        String attorneyFax = estateContact.get(attorneyContactKey).get("faxNumber");

        WebDriverUtil.waitForAWhile();

        verifyFetchedAttorneyContact(selectedAttorneyContactForm);
        verifyFetchedAttorneyContact(attorneyFirmName);
        verifyFetchedAttorneyContact(attorneyAddressLine1);
        verifyFetchedAttorneyContact(attorneyAddressLine2);
        verifyFetchedAttorneyContact(attorneyCity);
        verifyFetchedAttorneyContact(attorneyPhone);
        verifyFetchedAttorneyContact(attorneyFax);
    }

    public void clickOnPrintFormButton() throws AutomationException, AWTException, InterruptedException {
        driverUtil.getWebElement(PRINTFORM_BUTTON).click();

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
                files = FileUtil.getAllFiles((System.getProperty(OS) == null || System.getProperty(OS).equals(WINDOWS))
                        ? System.getProperty("user.dir") + "\\downloads"
                        : System.getProperty("user.dir").replace("\\", "/") + "/downloads");

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
                : System.getProperty("user.dir") + "/downloads/") + DownloadedFileName;

        verifyPrintNames(pdfFilePath);


    }

    public static void verifyPrintNames(String pdfFilePath) throws IOException {
        String beforeLine = "Estate of William John  ,Deceased";
        String afterLine = "(each) a subscribing witness to";

        List<String> names = new ArrayList<>();
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;

        // Log each line and find start/end indexes
        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);

            if (trimmedLine.contains(beforeLine.trim())) startIndex = i;
            if (trimmedLine.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                if (!allLines[i].isBlank()) {
                    names.add(allLines[i].trim());
                }
            }

            CommonSteps.logInfo("\nPrint Names:");
            names.forEach(CommonSteps::logInfo);

            if (names.isEmpty()) {
                CommonSteps.logInfo("❌ Validation Failed: No names found between the specified lines.");
            } else {
                // Create a map of expected names
                Map<String, String> expectedNames = new LinkedHashMap<>();
//                expectedNames.put("First Witness", enteredWitness1Form);
//                expectedNames.put("Second Witness", enteredWitness2Form);

                boolean allMatch = true;
                for (int i = 0; i < expectedNames.size(); i++) {
                    String expectedValue = (i < expectedNames.size()) ? expectedNames.values().toArray(new String[0])[i] : "No Name";
                    String actualValue = (i < names.size()) ? names.get(i) : "No Name";

                    if (!expectedValue.equalsIgnoreCase(actualValue)) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) {
                    CommonSteps.logInfo("✅ Validation Passed: Print names are " + String.join(" ", names) + " as expected.");
                } else {
                    CommonSteps.logInfo("❌ Validation Failed: Print names do not match the expected values.");
                }
            }
        } else {
            CommonSteps.logInfo("❌ Before or after line not found!");
        }
    }



    public void userResetsTheRWForm() throws AutomationException {
        driverUtil.getWebElement("//body").click();

        driverUtil.getWebElement(ATTORNEY_SELECTION_FIELD).click();

        driverUtil.getWebElement("//span[@class='cursor']").click();

        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contacts updated successfully.")));

        driverUtil.getWebElement(FIRST_PAGE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));

        DriverFactory.drivers.get().findElement(By.xpath(OPTION_A_CHECKBOX)).click();

        Actions actions = new Actions(DriverFactory.drivers.get());

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebDriverUtil.waitForAWhile();

        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_HEIRS_ON_ATTACHMENT_CHKBX)).click();
    }
}

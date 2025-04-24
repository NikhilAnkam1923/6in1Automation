package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.FileUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

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
    private static final String COUNTY_PAGE_1_XPATH = "//div[@class='county-title']//span[@class='county-name']";
    private static final String TRUST_UNDER_WILL_RADIO_BTN = "//input[@name='trustIsUnderType' and @value='1']";
    private static final String TRUST_UNDER_DEED_RADIO_BTN = "//input[@name='trustIsUnderType' and @value='2']";
    private static final String TRUST_UNDER_WILL_TEXT_FIELD = "//p[contains(text(),'(TRUST UNDER WILL OF')]//input[@name='descendantName']";
    private static final String TRUST_UNDER_DEED_TEXT_FIELD = "//p[contains(text(),'TRUST UNDER DEED OF')]//input[@name='descendantName']";
    private static final String NAME_OF_TRUST_FIELD_2_AND_4_PAGE = "//p//span[contains(text(),'Name of Trust')]/following-sibling::input";
    private static final String NAME_OF_TRUST_FIELD_OTHER_PAGES = "//p[contains(text(),'Name of Trust')]//input";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPa']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String PROCEED_BTN = "//button[text()='Proceed']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String NAME_OF_LAW_FIRM_FIELD = "//p//span[text()='Name of Law Firm:']/following-sibling::span//input";
    private static final String TELEPHONE_FIELD = "//p//span[contains(text(),'Telephone: ')]/following-sibling::span//input";
    private static final String FAX_FIELD = "//p//span[text()='Fax:']/following-sibling::span//input";
    private static final String EMAIL_FIELD = "//p//span[contains(text(),'E-mail:')]/following-sibling::span//input";
    private static final String ADDRESS_LINE_1_FIELD = "//p//span[contains(text(),'Address:')]/following-sibling::span//input";
    private static final String ADDRESS_LINE_2_FIELD = "//span[contains(text(),'Address:')]/ancestor::p/following-sibling::p//span//input";
    private static final String NAME_OF_COUNSEL_FIELD = "//p//span[text()='Name of Counsel:']/following-sibling::span//input";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String ACCEPT_BTN = "//button[text()='Accept']";
    private static final String VIEW_ATTACHMENT_BTN = "//span[contains(@class,'view-attachment')]";
    private static final String PETITIONER_MODAL_INPUT_XPATH = "//div[@class='modal-body']//input[@type='text' and @value='%s']";
    private static final String PETITIONER_INPUT_XPATH = "//input[@type='text' and @value='%s']";
    private static final String MODAL_CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String DROPPED_CONTACTS_XPATH = "//div[@class='drag-names-list drop-box h-100']//div//div";
    private static final String PETITIONER_NAME_FIELD = "//td[@class='tr5 td9']//input";
    private static final String PETITIONER_NAME_FIELD_2 = "//td[@class='tr5 td10']//input";
    private static final String SELECTED_PETITIONER_NAMES = "//div[@class='drag-names-list drop-box h-100']//div//div//span";
    private static final String TESTAMENTARY_OPTION = "//input[@name='typeOfTrust' and @value='1']";
    private static final String INTER_VIVOS_OPTION = "//input[@name='typeOfTrust' and @value='2']";
    private static final String TESTAMENTARY_TRUST_FIELD = "//span[text()='Date of Decedent‘s Will:']/following-sibling::span//input";
    private static final String INTER_VIVOS_FIELD = "//input[@name='dateOfTrust']";
    private static final String DECEDENT_DATE_OF_DEATH = "//span[text()='Decedent‘s date of death:']/following-sibling::span//input";
    private static final String FETCHED_CODICIL_DATE_1 = "//input[@name='codicilDate1']";
    private static final String FETCHED_CODICIL_DATE_2 = "//input[@name='codicilDate2']";
    private static final String FETCHED_CODICIL_DATE_3 = "//input[@name='codicilDate3']";
    private static final String DECEDENT_TAB = "//span[text()='Decedent']";
    private static final String JUDICIAL_COUNTY_FIELD = "//span[text()='Judicial District or County where Letters were issued:']/following-sibling::span//input";
    private static final String DATE_OF_TRUST = "//input[@name='dateOfTrust']";
    private static final String DATE_OF_AMENDMENT_1 = "//input[@name='amendmentDate1']";
    private static final String DATE_OF_AMENDMENT_2 = "//input[@name='amendmentDate2']";
    private static final String DATE_OF_AMENDMENT_3 = "//input[@name='amendmentDate3']";
    private static final String DATE_OF_AMENDMENT_4 = "//input[@name='amendmentDate4']";
    private static final String ADVERTISING_DATE_1 = "//input[@name='advertisingDate1']";
    private static final String ADVERTISING_DATE_2 = "//input[@name='advertisingDate2']";
    private static final String ADVERTISING_DATE_3 = "//input[@name='advertisingDate3']";
    private static final String BENE_DETAILS_FORM = "//p//div";
    private static final String BENE_DETAILS_ATTACHMENT = "//div[@class='attachment-css border-0']//tr/td[textarea[contains(text(), ',')]]";
    private static final String BENE_RELATIONSHIP_FORM = "//td[@class='borderNewLeft2']//p[@class='p8-1 ft27-2 newstyle']//input";
    private static final String BENE_INTEREST_FORM = "//td[@class='borderNewLeft2 borderNewBottom2']//p[@class='p8-1 ft27-2 newstyle']//input";
    private static final String BENE_RELATIONSHIP_ATTACHMENT = "//div[@class='modal-body']//table/tbody/tr/td[position()=3]/textarea";
    private static final String BENE_INTEREST_ATTACHMENT = "//div[@class='modal-body']//table/tbody/tr/td[position()=4]/textarea";
    private static final String CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String MAIN_COUNT = "//div[@class='main_count blue-text']//span";
    private static final String ATTACHMENT_COUNT = "//div[@class='attached_count blue-text']//b";
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllBenyOnAttachment']";

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    private static final List<String> selectedPetitioner = new ArrayList<>();
    private static final List<String> Fiduciaries = new ArrayList<>();
    private static final List<String> corporateFiduciaries = new ArrayList<>();
    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneRelationship = new ArrayList<>();
    private static final List<String> beneInterest = new ArrayList<>();
    private static final List<String> beneficiaryKeys = new ArrayList<>();

    static String countyNameForm;
    static String enteredDecedentNameForm;
    static String fileNumberForm;
    static String initialFileNumber;
    static String selectedAttorney;
    static String nameOfCounselForm;
    static String nameOfLawFirmForm;
    static String attorneyTelephoneForm;
    static String attorneyFaxForm;
    static String attorneyEmailForm;
    static String attorneyAddressLine1Form;
    static String attorneyAddressLine2Form;
    static String Fiduciary1Form;
    static String Fiduciary2Form;
    static String Fiduciary3Form;
    static String Fiduciary4Form;
    static String nameOfPetitionerForm;
    static String nameOfPetitioner2Form;
    static String petitioner1AddressLine1Form;
    static String petitioner1CityStateCodeZipForm;
    static String petitioner2AddressLine1Form;
    static String petitioner2CityStateCodeZipForm;
    static String petitioner3AddressLine1Form;
    static String petitioner3CityStateCodeZipForm;
    static String petitioner4AddressLine1Form;
    static String petitioner4CityStateCodeZipForm;
    static String decedentDateOfDeath;
    static String codicilDate1Form;
    static String codicilDate2Form;
    static String codicilDate3Form;
    static String nameOfTrustPage2;
    static int expectedAttachmentCountForm;
    static String dateOfTrust;
    static String amendmentDate1;
    static String amendmentDate2;
    static String amendmentDate3;
    static String amendmentDate4;
    static String advertisingDate1;
    static String advertisingDate2;
    static String advertisingDate3;

    static String downloadedFileName;

    public ProbateFormsOC02Page() throws IOException, ParseException {
    }

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

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    public void verifyByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm() throws AutomationException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);
        WebElement countyName = driverUtil.getWebElement(COUNTY_PAGE_1_XPATH);
        String enteredCountyName = getEstateValue("DomicileCountry").toUpperCase();
        countyNameForm = countyName.getText();

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
                if(i==2){
                    nameOfTrustPage2 = actualNameOfTrust;
                }
            } else {
                actualNameOfTrust = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES).getAttribute("value");
            }

            if(!actualNameOfTrust.equals(enteredDecedentNameForm)){
                throw new AutomationException("Decedent Name is not reflected on page: "+i+". Expected: "+enteredDecedentNameForm+" ,Found: "+actualNameOfTrust);
            }
        }
    }

    public void verifyFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        if (!fileNumberField.isEnabled()) {
            throw new AutomationException("File number field is not editable.");
        }

        initialFileNumber = fileNumberField.getAttribute("value");
        String newFileNumber = CommonUtil.getJsonPath("OC02Form").get("OC02Form.fileNumber").toString();
        fileNumberField.clear();
        fileNumberField.sendKeys(newFileNumber);
        WebDriverUtil.waitForAWhile();

        fileNumberForm = fileNumberField.getAttribute("value");

        if (!fileNumberForm.equals(newFileNumber) && fileNumberForm.equals(initialFileNumber)) {
            throw new AutomationException("File number not updated correctly. Expected: " + newFileNumber + " ,Found: " + fileNumberForm);
        }

        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();

        //use in reset
//        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberField.clear();
        fileNumberField.sendKeys(initialFileNumber);
        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();
    }

    public void verifySidebarAppearsAndAttorneyCanBeSelected() throws AutomationException, IOException, ParseException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney1").get("attorney1.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney1").get("attorney1.lastName").toString();
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney1").get("attorney1.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney1").get("attorney1.suffix").toString();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Attorney")) {
            throw new AutomationException("Sidebar is not displayed for attorney contact.");
        }

        selectedAttorney = attorneyFirstName + " " + attorneyMiddleName + " " + attorneyLastName + ", " + attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, selectedAttorney));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if (!attorneyToSelect.isSelected()) {
            throw new AutomationException("Unable to select the attorney contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(PROCEED_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel updated successfully.")));
    }

    public void verifySelectedAttorneySDetailsArePopulatedInTheField() throws AutomationException {
        WebElement nameOfCounselField = driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD);

        nameOfCounselForm = nameOfCounselField.getAttribute("value");
        if (!nameOfCounselForm.equals(selectedAttorney)) {
            throw new AutomationException("Attorney details not populated correctly in 'Name of Counsel' field. Expected: " + selectedAttorney + " ,Found: " + nameOfCounselForm);
        }
    }

    public void verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed() throws IOException, ParseException, AutomationException {
        String attorneyFirmName = CommonUtil.getJsonPath("attorney1").get("attorney1.entityName").toString();
        String attorneyAddressLine1 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String attorneyAddressLine2 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine2").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney1").get("attorney1.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney1").get("attorney1.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney1").get("attorney1.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney1").get("attorney1.phoneNumber").toString();
        String attorneyFax = CommonUtil.getJsonPath("attorney1").get("attorney1.fax").toString();
        String attorneyEmail = CommonUtil.getJsonPath("attorney1").get("attorney1.emailId").toString();

        String attorneyCitySateZip = attorneyCity + ", " + attorneySateCode + " " + attorneyZip;
        String attorneyAddress = attorneyAddressLine1 + ", " + attorneyAddressLine2;

        WebElement nameOfLawFirmField = driverUtil.getWebElement(NAME_OF_LAW_FIRM_FIELD);
        WebElement telephoneField = driverUtil.getWebElement(TELEPHONE_FIELD);
        WebElement faxField = driverUtil.getWebElement(FAX_FIELD);
        WebElement emailField = driverUtil.getWebElement(EMAIL_FIELD);
        WebElement addressLine1Field = driverUtil.getWebElement(ADDRESS_LINE_1_FIELD);
        WebElement addressLine2Field = driverUtil.getWebElement(ADDRESS_LINE_2_FIELD);

        nameOfLawFirmForm = nameOfLawFirmField.getAttribute("value");
        attorneyTelephoneForm = telephoneField.getAttribute("value");
        attorneyFaxForm = faxField.getAttribute("value");
        attorneyEmailForm = emailField.getAttribute("value");
        attorneyAddressLine1Form = addressLine1Field.getAttribute("value");
        attorneyAddressLine2Form = addressLine2Field.getAttribute("value");

        if (!attorneyFirmName.equals(nameOfLawFirmForm)) {
            throw new AutomationException("Name of Law Firm not correctly fetched. Expected: " + attorneyFirmName + ", Found: " + nameOfLawFirmForm);
        }

        if (!attorneyTelephone.equals(attorneyTelephoneForm)) {
            throw new AutomationException("Attorney Telephone not correctly fetched. Expected: " + attorneyTelephone + ", Found: " + attorneyTelephoneForm);
        }

        if (!attorneyFax.equals(attorneyFaxForm)) {
            throw new AutomationException("Attorney Fax not correctly fetched. Expected: " + attorneyFax + ", Found: " + attorneyFaxForm);
        }

        if (!attorneyEmail.equals(attorneyEmailForm)) {
            throw new AutomationException("Attorney Email not correctly fetched. Expected: " + attorneyEmail + ", Found: " + attorneyEmailForm);
        }

        if (!attorneyAddress.equals(attorneyAddressLine1Form)) {
            throw new AutomationException("Attorney Address Line 1 not correctly fetched. Expected: " + attorneyAddressLine1 + ", Found: " + attorneyAddressLine1Form);
        }

        if (!attorneyCitySateZip.equals(attorneyAddressLine2Form)) {
            throw new AutomationException("Attorney Address Line 2 (City, State code, Zip) not correctly fetched. Expected: " + attorneyCitySateZip + ", Found: " + attorneyAddressLine2Form);
        }
    }

    public void userSelectsTwoPetitioners() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        waitForVisibleElement(By.xpath(DRAG_CONTACT_XPATH));
        Fiduciary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));
    }

    public void verifyPetitionerOnForm(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(PETITIONER_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public static String findFiduciaryKeyByName(String name, JSONObject jsonObject) {
        for (Object keyObj : jsonObject.keySet()) {
            String key = keyObj.toString();

            if (key.startsWith("fiduciary") || key.startsWith("corporateFiduciary")) {
                Object dataObj = jsonObject.get(key);
                if (!(dataObj instanceof JSONObject)) continue;

                JSONObject fidData = (JSONObject) dataObj;

                // Build full individual name
                String firstName = (String) fidData.getOrDefault("firstName", "");
                String middleName = (String) fidData.getOrDefault("middleName", "");
                String lastName = (String) fidData.getOrDefault("lastName", "");
                String suffix = (String) fidData.getOrDefault("suffix", "");

                String fullName = String.join(" ", firstName, middleName, lastName).trim();
                if (!suffix.isEmpty()) {
                    fullName += ", " + suffix;
                }

                fullName = fullName.replaceAll(" +", " "); // Normalize spaces

                if (name.equalsIgnoreCase(fullName)) {
                    return key;
                }

                // Check for corporate entity
                String entityName = (String) fidData.getOrDefault("entityName", "");
                if (name.equalsIgnoreCase(entityName)) {
                    return key;
                }
            }
        }
        return null;
    }

    public void verifySelectedNamesOfPetitionerAreDisplayedWithAddress() throws AutomationException {
        String key1 = findFiduciaryKeyByName(Fiduciary1Form, jsonData);
        JSONObject contact1 = (JSONObject) jsonData.get(key1);
        petitioner1AddressLine1Form = contact1.get("addressLine1").toString();
        String city1 = contact1.get("city").toString() + ",";
        String stateCode1 = contact1.get("stateCode").toString();
        String zip1 = contact1.get("zip").toString();
        petitioner1CityStateCodeZipForm = city1 + " " + stateCode1 + " " + zip1;

        String key2 = findFiduciaryKeyByName(Fiduciary2Form, jsonData);
        JSONObject contact2 = (JSONObject) jsonData.get(key2);
        petitioner2AddressLine1Form = contact2.get("addressLine1").toString();
        String city2 = contact2.get("city").toString() + ",";
        String stateCode2 = contact2.get("stateCode").toString();
        String zip2 = contact2.get("zip").toString();
        petitioner2CityStateCodeZipForm = city2 + " " + stateCode2 + " " + zip2;

        WebElement nameOfPetitionerField = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);

        nameOfPetitionerForm = nameOfPetitionerField.getAttribute("value");
        if (!nameOfPetitionerForm.equals(Fiduciary1Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary1Form + " ,Found: " + nameOfPetitionerForm);
        }
        verifyPetitionerOnForm(petitioner1AddressLine1Form);
        verifyPetitionerOnForm(petitioner1CityStateCodeZipForm);


        nameOfPetitioner2Form = nameOfPetitioner2Field.getAttribute("value");
        if (!nameOfPetitioner2Form.equals(Fiduciary2Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary2Form + " ,Found: " + nameOfPetitioner2Form);
        }
        verifyPetitionerOnForm(petitioner2AddressLine1Form);
        verifyPetitionerOnForm(petitioner2CityStateCodeZipForm);
    }

    public void userSelectsMultiplePetitioners() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        Fiduciary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));
    }

    public void verifyPetitionerOnAttachment(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(PETITIONER_MODAL_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyAttachmentIconIsVisibleAndThePetitionerDetailsAreCorrectlyVisibleOnTheAttachment() throws IOException, ParseException, AutomationException {
        String key3 = findFiduciaryKeyByName(Fiduciary3Form, jsonData);

        JSONObject contact3 = (JSONObject) jsonData.get(key3);
        String address3Line1 = contact3.get("addressLine1").toString();
        String address3Line2 = contact3.get("addressLine2").toString();
        String city3 = contact3.get("city").toString() + ",";
        String stateCode3 = contact3.get("stateCode").toString();
        String zip3 = contact3.get("zip").toString();
        petitioner3CityStateCodeZipForm = city3 + " " + stateCode3 + " " + zip3;
        petitioner3AddressLine1Form = address3Line1 + ", " + address3Line2;

        String key4 = findFiduciaryKeyByName(Fiduciary4Form, jsonData);

        JSONObject contact4 = (JSONObject) jsonData.get(key4);
        String address4Line1 = contact4.get("addressLine1").toString();
        String address4Line2 = contact4.get("addressLine2").toString();
        String city4 = contact4.get("city").toString() + ",";
        String stateCode4 = contact4.get("stateCode").toString();
        String zip4 = contact4.get("zip").toString();
        petitioner4CityStateCodeZipForm = city4 + " " + stateCode4 + " " + zip4;
        petitioner4AddressLine1Form = address4Line1 + ", " + address4Line2;

        verifySelectedNamesOfPetitionerAreDisplayedWithAddress();

        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        verifyPetitionerOnAttachment(Fiduciary3Form);
        verifyPetitionerOnAttachment(petitioner3AddressLine1Form);
        verifyPetitionerOnAttachment(petitioner3CityStateCodeZipForm);

        verifyPetitionerOnAttachment(Fiduciary4Form);
        verifyPetitionerOnAttachment(petitioner4AddressLine1Form);
        verifyPetitionerOnAttachment(petitioner4CityStateCodeZipForm);

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
    }

    public void userSwapTheSelectedPetitionerContacts() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        List<WebElement> droppedContacts = driverUtil.getWebElements(DROPPED_CONTACTS_XPATH);

        if (droppedContacts.size() >= 2) {
            actions.clickAndHold(droppedContacts.get(0))
                    .moveToElement(droppedContacts.get(1))
                    .release()
                    .perform();
            WebDriverUtil.waitForAWhile();
        } else {
            throw new AutomationException("Not enough contacts in the drop section to perform a swap.");
        }

        WebDriverUtil.waitForAWhile(2);

        List<WebElement> selectedContacts = driverUtil.getWebElements(SELECTED_PETITIONER_NAMES);
        for (WebElement selectedContact : selectedContacts) {
            String petitioner = selectedContact.getText().trim();
            selectedPetitioner.add(petitioner);

            if (isIndividualFiduciary(petitioner)) {
                Fiduciaries.add(petitioner);
            } else {
                corporateFiduciaries.add(petitioner);
            }
        }

        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));
    }

    private boolean isIndividualFiduciary(String name) {
        return name.contains(",");
    }

    public void verifySwappedPetitionerNamesAreReflectedOnUIAccordingly() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForAWhile();
        WebElement nameOfPetitionerField = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);

        nameOfPetitionerForm = nameOfPetitionerField.getAttribute("value");
        if (!nameOfPetitionerForm.equals(Fiduciary2Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary2Form + " ,Found: " + nameOfPetitionerForm);
        }
        verifyPetitionerOnForm(petitioner2AddressLine1Form);
        verifyPetitionerOnForm(petitioner2CityStateCodeZipForm);

        nameOfPetitioner2Form = nameOfPetitioner2Field.getAttribute("value");
        if (!nameOfPetitioner2Form.equals(Fiduciary1Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary1Form + " ,Found: " + nameOfPetitioner2Form);
        }
        verifyPetitionerOnForm(petitioner1AddressLine1Form);
        verifyPetitionerOnForm(petitioner1CityStateCodeZipForm);
    }

    public void userSelectTestamentaryOption() {
        scrollToElement(TESTAMENTARY_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(TESTAMENTARY_OPTION)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void userSelectInterVivosOption() {
        scrollToElement(INTER_VIVOS_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(INTER_VIVOS_OPTION)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyTestamentaryTrustSectionIsEnabledAndInterVivosTrustSectionIsDisabled() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement testamentaryTrustField = driverUtil.getWebElement(TESTAMENTARY_TRUST_FIELD);
        WebElement interVivosTrustField = driverUtil.getWebElement(INTER_VIVOS_FIELD);

        if (!testamentaryTrustField.isEnabled()) {
            throw new AutomationException("Testamentary Trust section is not enabled.");
        }

        if (interVivosTrustField.isEnabled()) {
            throw new AutomationException("Inter Vivos Trust section is not disabled.");
        }
    }

    public void verifyInterVivosTrustSectionIsEnabledAndTestamentaryTrustSectionIsDisabled() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement interVivosTrustField = driverUtil.getWebElement(INTER_VIVOS_FIELD);
        WebElement testamentaryTrustField = driverUtil.getWebElement(TESTAMENTARY_TRUST_FIELD);

        if (!interVivosTrustField.isEnabled()) {
            throw new AutomationException("Inter Vivos Trust section is not enabled.");
        }

        if (testamentaryTrustField.isEnabled()) {
            throw new AutomationException("Testamentary Trust section is not disabled.");
        }
    }

    public void verifyDateOfDeathIsAutoFetchedFromTheDecedentInfo() throws AutomationException {
        WebElement decedentDateOfDeathField = driverUtil.getWebElement(DECEDENT_DATE_OF_DEATH);
        scrollToElement(DECEDENT_DATE_OF_DEATH);

        decedentDateOfDeath = decedentDateOfDeathField.getAttribute("value");

        String enteredDateOfDeathName = getEstateValue("DateOfDeath");

        if (!enteredDateOfDeathName.equals(decedentDateOfDeath)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredDateOfDeathName + " ,Found: " + decedentDateOfDeath);
        }
    }

    public void verifyCodicilDatesAreAutoFetchedFromDecedentTab() throws AutomationException {
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

    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void userModifiesTheCodicilDates() throws IOException, ParseException, AutomationException {
        String codicilDate1 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.codicilDate1").toString();
        String codicilDate2 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.codicilDate2").toString();
        String codicilDate3 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.codicilDate3").toString();

        WebElement codicilDate1Field = driverUtil.getWebElement(FETCHED_CODICIL_DATE_1);
        WebElement codicilDate2Field = driverUtil.getWebElement(FETCHED_CODICIL_DATE_2);
        WebElement codicilDate3Field = driverUtil.getWebElement(FETCHED_CODICIL_DATE_3);

        scrollToElement(FETCHED_CODICIL_DATE_1);

        clearField(FETCHED_CODICIL_DATE_1);
        codicilDate1Field.sendKeys(codicilDate1);
        clearField(FETCHED_CODICIL_DATE_2);
        codicilDate2Field.sendKeys(codicilDate2);
        clearField(FETCHED_CODICIL_DATE_3);
        codicilDate3Field.sendKeys(codicilDate3);
        codicilDate3Field.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();

        codicilDate1Form = codicilDate1Field.getAttribute("value");
        codicilDate2Form = codicilDate2Field.getAttribute("value");
        codicilDate3Form = codicilDate3Field.getAttribute("value");
    }

    public void verifyUpdatedCodicilDatesAreReflectedInTheDecedentTab() throws AutomationException {
        waitForVisibleElement(By.xpath(DECEDENT_TAB));
        driverUtil.getWebElement(DECEDENT_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        if (!driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value").equals(codicilDate1Form)) {
            throw new AutomationException("Updated codicil date 1 is not reflected");
        }

        if (!driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value").equals(codicilDate2Form)) {
            throw new AutomationException("Updated codicil date 2 is not reflected");
        }

        if (!driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value").equals(codicilDate3Form)) {
            throw new AutomationException("Updated codicil date 3 is not reflected");
        }
    }

    public void verifyJudicialCountyIsAutoFetchedFromTheDecedentInfoTab() throws AutomationException {
        WebElement judicialCountyField = driverUtil.getWebElement(JUDICIAL_COUNTY_FIELD);

        String actualJudicialCounty = judicialCountyField.getAttribute("value");
        String enteredJudicialCounty = getEstateValue("DomicileCountry");

        if(!actualJudicialCounty.contains(enteredJudicialCounty)){
            throw new AutomationException("Judicial county is not fetched correctly. Expected: "+enteredJudicialCounty+" ,Found: "+actualJudicialCounty);
        }
    }

    public void verifyAllTheDetailsCanBeEnteredAndAutoSaved() throws AutomationException, IOException, ParseException {
        dateOfTrust = CommonUtil.getJsonPath("OC02Form").get("OC02Form.dateOfTrust").toString();
        amendmentDate1 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.amendmentDate1").toString();
        amendmentDate2 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.amendmentDate2").toString();
        amendmentDate3 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.amendmentDate3").toString();
        amendmentDate4 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.amendmentDate4").toString();
        advertisingDate1 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.advertisingDate1").toString();
        advertisingDate2 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.advertisingDate2").toString();
        advertisingDate3 = CommonUtil.getJsonPath("OC02Form").get("OC02Form.advertisingDate3").toString();

        List<String> fieldXpaths = Arrays.asList(
                DATE_OF_TRUST, DATE_OF_AMENDMENT_1, DATE_OF_AMENDMENT_2, DATE_OF_AMENDMENT_3,
                DATE_OF_AMENDMENT_4, ADVERTISING_DATE_1, ADVERTISING_DATE_2, ADVERTISING_DATE_3
        );

        List<String> Dates = Arrays.asList(
                dateOfTrust, amendmentDate1, amendmentDate2, amendmentDate3,
                amendmentDate4, advertisingDate1, advertisingDate2, advertisingDate3
        );

        List<String> enteredDates = new ArrayList<>();

        for (int i = 0; i < fieldXpaths.size(); i++) {
            String xpath = fieldXpaths.get(i);
            String Date = Dates.get(i);

            WebElement field = driverUtil.getWebElement(xpath);
            field.click();
            field.sendKeys(Keys.BACK_SPACE);
            for (char c : Date.toCharArray()) {
                field.sendKeys(String.valueOf(c));
            }

            driverUtil.getWebElement("//body").click();

            WebDriverUtil.waitForAWhile();

            String actualValue = driverUtil.getWebElement(xpath).getAttribute("value");
            enteredDates.add(actualValue);
        }

        switchToPage(4);
        WebDriverUtil.waitForAWhile();
        switchToPage(3);
        WebDriverUtil.waitForAWhile();

        for (int i = 0; i < fieldXpaths.size(); i++) {
            String xpath = fieldXpaths.get(i);
            String enteredDate = enteredDates.get(i);
            String actualDate = driverUtil.getWebElement(xpath).getAttribute("value");

            if(!enteredDate.equals(actualDate)){
                throw new AutomationException("Entered date is not auto saved. Expected: "+enteredDate+" ,Found: "+actualDate);
            }
        }


        //use in reset
        for (String fieldXpath : fieldXpaths) {
            DriverFactory.drivers.get().findElement(By.xpath(fieldXpath)).clear();
        }
    }

    public void verifyNameOfTheTrustIsAutoFetchedFromPage() throws AutomationException {
        WebElement nameOfTrustField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String actualNameOfTrust = nameOfTrustField.getAttribute("value");

        if(!actualNameOfTrust.equals(nameOfTrustPage2)){
            throw new AutomationException("Name of Trust is not fetched from Page 2. Expected: "+nameOfTrustPage2+" ,Found: "+actualNameOfTrust);
        }
    }

    public static String findBeneficiaryKeyByName(String fullName, JSONObject jsonData) {
        for (Object keyObj : jsonData.keySet()) {
            String key = keyObj.toString();
            if (key.startsWith("beneficiary")) {
                JSONObject beneData = (JSONObject) jsonData.get(key);

                String jsonFullName = beneData.getOrDefault("firstName", "") + " " +
                        beneData.getOrDefault("middleName", "") + " " +
                        beneData.getOrDefault("lastName", "") + ", " +
                        beneData.getOrDefault("suffix", "");

                jsonFullName = jsonFullName.trim().replaceAll(" +", " ");

                if (fullName.equalsIgnoreCase(jsonFullName)) {
                    return key;
                }
            }
        }
        return null;
    }

    public String extractFullName(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        String[] lines = input.split("\\r?\\n");
        return lines.length > 0 ? lines[0].trim() : "";
    }

    public void userSavesSelectedBeneficiariesDetails() throws AutomationException {
        WebDriverUtil.waitForAWhile();

        List<WebElement> beneDetailsPage4Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        List<WebElement> beneRelationshipPage4Fields = driverUtil.getWebElements(BENE_RELATIONSHIP_FORM);
        List<WebElement> beneInterestPage4Fields = driverUtil.getWebElements(BENE_INTEREST_FORM);

        for (int i = 0; i < 2; i++) {
            beneDetails.add(beneDetailsPage4Fields.get(i).getText());
            beneRelationship.add(beneRelationshipPage4Fields.get(i).getAttribute("value"));
            beneInterest.add(beneInterestPage4Fields.get(i).getAttribute("value"));
        }

        switchToPage(7);

        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsPage5Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        List<WebElement> beneRelationshipPage5Fields = driverUtil.getWebElements(BENE_RELATIONSHIP_FORM);
        List<WebElement> beneInterestPage5Fields = driverUtil.getWebElements(BENE_INTEREST_FORM);

        for (int i = 0; i < 2; i++) {
            beneDetails.add(beneDetailsPage5Fields.get(i).getText());
            beneRelationship.add(beneRelationshipPage5Fields.get(i).getAttribute("value"));
            beneInterest.add(beneInterestPage5Fields.get(i).getAttribute("value"));
        }

        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();

        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsOnAttachmentFields = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);
        List<WebElement> beneRelationshipOnAttachmentFields = driverUtil.getWebElements(BENE_RELATIONSHIP_ATTACHMENT);
        List<WebElement> beneInterestOnAttachmentFields = driverUtil.getWebElements(BENE_INTEREST_ATTACHMENT);

        for (int i = 0; i < 3; i++) {
            beneDetails.add(beneDetailsOnAttachmentFields.get(i).getText());
            beneRelationship.add(beneRelationshipOnAttachmentFields.get(i).getText());
            beneInterest.add(beneInterestOnAttachmentFields.get(i).getText());
        }

        driverUtil.getWebElement(CLOSE_BTN).click();

        for (String detail : beneDetails) {
            String fullName = extractFullName(detail); // Your custom logic to get full name
            String matchedKey = findBeneficiaryKeyByName(fullName, jsonData);

            if (matchedKey != null) {
                beneficiaryKeys.add(matchedKey);
            } else {
                throw new AutomationException("Beneficiary key not found for full name: " + fullName);
            }
        }
    }

    public void verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        if (beneDetails.size() <= 2) {
            throw new AutomationException("There are no additional beneficiaries to verify in the attachment.");
        }

        List<WebElement> attachmentBeneficiaries = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);

        for (int i = 4; i < beneDetails.size(); i++) {
            String expectedBeneficiary = beneDetails.get(i);
            boolean found = false;

            for (WebElement attachmentBeneficiary : attachmentBeneficiaries) {
                if (attachmentBeneficiary.getText().equals(expectedBeneficiary)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new AutomationException("Beneficiary not found in attachment: " + expectedBeneficiary);
            }
        }

        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyAttachmentIsDisplayedOnPage6() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void verifySameAttachmentIsDisplayedOnPage7() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void verifyAllTheBenyUsersAreDisplayedAsAPartOfAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        List<WebElement> attachmentBeneficiaries = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);

        for (String expectedBeneficiary : beneDetails) {
            boolean found = false;

            for (WebElement attachmentBeneficiary : attachmentBeneficiaries) {
                if (attachmentBeneficiary.getText().equals(expectedBeneficiary)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new AutomationException("Beneficiary not found in attachment: " + expectedBeneficiary);
            }
        }

        expectedAttachmentCountForm = attachmentBeneficiaries.size();

        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
        scrollToElement(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly() throws AutomationException {
        WebElement mainCountElement = driverUtil.getWebElement(MAIN_COUNT);
        WebElement attachmentCountElement = driverUtil.getWebElement(ATTACHMENT_COUNT);

        int actualMainCount = Integer.parseInt(mainCountElement.getText().trim());
        int actualAttachmentCount = Integer.parseInt(attachmentCountElement.getText().split(":")[1].trim());

        if (actualMainCount != 0) {
            throw new AutomationException("Mismatch in Main Count. Expected: 0 " + ", Found: " + actualMainCount);
        }
        if (actualAttachmentCount != expectedAttachmentCountForm) {
            throw new AutomationException("Mismatch in Attachment Count. Expected: " + expectedAttachmentCountForm + ", Found: " + actualAttachmentCount);
        }

        CommonSteps.takeScreenshot();

        userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox();
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

    public void verifyAllFieldsInDownloadedPDF() throws AutomationException, IOException {
        String pdfFilePath = ((System.getProperty("os.name").toLowerCase().contains("win"))
                ? System.getProperty("user.dir") + "\\downloads\\"
                : System.getProperty("user.dir") + "/downloads/") + downloadedFileName;

        try {

            boolean isVerifiedFileNumber = verifyFieldsInPDF(pdfFilePath,
                    "DATED",
                    "PETITION FOR ADJUDICATION /",
                    fileNumberForm,
                    "file number");

            Map<String, String> expectedCounselDetails = new HashMap<>();
            expectedCounselDetails.put("Name of Counsel", nameOfCounselForm);
            expectedCounselDetails.put("Name of Law Firm", nameOfLawFirmForm);
            String attorneyAddressLineForm = attorneyAddressLine1Form+" "+attorneyAddressLine2Form;
            expectedCounselDetails.put("Address", attorneyAddressLineForm);
            expectedCounselDetails.put("Telephone", attorneyTelephoneForm);
            expectedCounselDetails.put("Fax", attorneyFaxForm);
            expectedCounselDetails.put("Email", attorneyEmailForm);

            boolean isVerifiedCounselDetails = verifyCounselDetails(pdfFilePath, expectedCounselDetails);


            // If any verification fails, throw an exception
            if (!isVerifiedFileNumber || !isVerifiedCounselDetails) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }

            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (AutomationException | IOException e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }

    private static boolean verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String
            expectedValue, String fieldName) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1, endIndex = -1;
        String extractedValue = "";

        // 🔍 Log Full PDF Content with Line Numbers
        CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);
        }

        // Find the start and end indexes
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

        // Extract and validate the field value
        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                String currentLine = allLines[i].trim();
                if (!currentLine.isBlank()) {
                    extractedValue = cleanFields(currentLine, fieldName);
                    break; // Assuming only one line needs to be extracted
                }
            }

            if (extractedValue.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No '" + fieldName + "' found between specified lines.");
            }

            CommonSteps.logInfo("🔍 Comparing -> for " + fieldName + " Expected: '" + expectedValue + "', Extracted: '" + extractedValue + "'");

            if (!expectedValue.equalsIgnoreCase(extractedValue)) {
                throw new AutomationException("❌ Validation Failed: '" + fieldName + "' does not match expected value.");
            }

            CommonSteps.logInfo("✅ Validation Passed: '" + fieldName + "' matches expected.");
            return true;
        } else {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }
    }

    private static String cleanFields(String rawText, String fieldType) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        String cleanedText = rawText.trim();

        switch (fieldType.toLowerCase()) {
            case "file number":
                cleanedText = cleanedText.replaceAll("(?i)\\b(No. )\\b", "").trim();
                break;

            default:
                // Generic cleanup for any other case
                cleanedText = cleanedText.replaceAll("[:,\\.\\s]+$", "").trim();
                break;
        }
        return cleanedText;
    }


    private static boolean verifyCounselDetails(String pdfFilePath, Map<String, String> expectedDetails) throws
            IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        Map<String, String> extractedDetails = new HashMap<>();
        String addressLine1 = "";
        String addressLine2 = "";

        boolean nameOfCounselFound = false;
        boolean nameOfLawFirmFound = false;
        boolean addressFound = false;
        boolean telephoneFound = false;
        boolean faxFound = false;
        boolean emailFound = false;

        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();

            // Check for "Name of Counsel" only once
            if (!nameOfCounselFound && line.startsWith("Name of Counsel:")) {
                extractedDetails.put("Name of Counsel", clean(line.replace("Name of Counsel:", "").trim()));
                nameOfCounselFound = true;
            }
            // Check for "Name of Law Firm" only once
            else if (!nameOfLawFirmFound && line.startsWith("Name of Law Firm:")) {
                extractedDetails.put("Name of Law Firm", clean(line.replace("Name of Law Firm:", "").trim()));
                nameOfLawFirmFound = true;
            }
            // Check for "Address" only once
            else if (!addressFound && line.startsWith("Address:")) {
                addressLine1 = clean(line.replace("Address:", "").trim());
                if (i + 1 < allLines.length) {
                    addressLine2 = clean(allLines[i + 1].trim()); // Next line contains city/state/zip
                }
                addressFound = true;
            }
            // Check for "Telephone" only once
            else if (!telephoneFound && line.startsWith("Telephone:")) {
                extractedDetails.put("Telephone", clean(line.replace("Telephone:", "").trim()));
                telephoneFound = true;
            }
            // Check for "Fax" only once
            else if (!faxFound && line.startsWith("Fax:")) {
                extractedDetails.put("Fax", clean(line.replace("Fax:", "").trim()));
                faxFound = true;
            }
            // Check for "Email" only once
            else if (!emailFound && line.startsWith("E-mail:")) {
                extractedDetails.put("Email", clean(line.replace("E-mail:", "").trim()));
                emailFound = true;
            }
        }

        // Combine address lines if both are found
        if (!addressLine1.isEmpty() && !addressLine2.isEmpty()) {
            extractedDetails.put("Address", addressLine1 + " " + addressLine2);
        } else if (!addressLine1.isEmpty()) {
            extractedDetails.put("Address", addressLine1);
        } else if (!addressLine2.isEmpty()) {
            extractedDetails.put("Address", addressLine2);
        }

        // Validate extracted details
        for (Map.Entry<String, String> entry : expectedDetails.entrySet()) {
            String field = entry.getKey();
            String expectedValue = entry.getValue();
            String extractedValue = extractedDetails.get(field);

            // Clean both the expected and extracted values before comparing
            expectedValue = clean(expectedValue);
            extractedValue = clean(extractedValue);

            CommonSteps.logInfo("🔍 Comparing -> " + field + " | Expected: '" + expectedValue + "', Extracted: '" + extractedValue + "'");

            if (extractedValue == null || extractedValue.isEmpty()) {
                throw new AutomationException("⚠️ Warning: '" + field + "' not found in document.");
            }

            if (!expectedValue.equalsIgnoreCase(extractedValue)) {
                throw new AutomationException("⚠️ Warning: '" + field + "' does not match expected value.");
            }

            CommonSteps.logInfo("✅ Validation Passed: '" + field + "' processed successfully.");
        }

        CommonSteps.logInfo("✅ Validation Passed: Counsel details successfully verified.");
        return true;
    }


    // Utility method to clean the string, removing unwanted characters or spaces
    private static String clean(String value) {
        if (value != null) {
            // Remove unwanted punctuation like commas, periods, etc. and trim spaces
            value = value.replaceAll("[,\\.]", "").trim();
        }
        return value;
    }

    public void userResetsTheRWForm() throws AutomationException {
        //Page 2
        switchToPage(2);
        scrollToElement(PETITIONER_NAME_FIELD);
        driverUtil.getWebElement(PETITIONER_NAME_FIELD).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));
    }
}


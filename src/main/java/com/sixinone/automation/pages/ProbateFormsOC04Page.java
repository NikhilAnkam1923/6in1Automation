package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.util.WebDriverUtil.*;
import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;

public class ProbateFormsOC04Page extends BasePage{
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
    private static final String ESTATE_OF_FIELD = "//p[contains(text(),'ESTATE OF')]//input";
    private static final String ESTATE_OF_FIELD_PAGE_2 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_OF_FIELD_PAGE_3 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_OF_FIELD_PAGE_4 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_OF_FIELD_PAGE_5 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_OF_FIELD_PAGE_6 = "//p[contains(text(),'Estate of')]//input";
    private static final String AMOUNT_FIELDS = "//input[@name='guardianFees[%s].amount']";
    private static final String START_DATE_FIELDS = "//input[@name='guardianFees[%s].beginDate']";
    private static final String END_DATE_FIELDS = "//input[@name='guardianFees[%s].endDate']";
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
    private static final String BENE_DETAILS_FORM = "//p//div";
    private static final String BENE_RELATIONSHIP_FORM = "//td[@class='borderNewLeft2']//p[@class='p8-1 ft27-2 newstyle']//input";
    private static final String BENE_INTEREST_FORM = "//td[@class='borderNewLeft2 borderNewBottom2']//p[@class='p8-1 ft27-2 newstyle']//textarea";
    private static final String BENE_DETAILS_ATTACHMENT = "//div[@class='attachment-css border-0']//tr/td[textarea[contains(text(), ',')]]";
    private static final String BENE_RELATIONSHIP_ATTACHMENT = "//div[@class='modal-body']//table/following-sibling::table//tr//td[position()='3']//textarea";
    private static final String BENE_INTEREST_ATTACHMENT = "//div[@class='modal-body']//table/following-sibling::table//tr//td[position()='4']//textarea";
    private static final String CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String MAIN_COUNT = "//div[@class='main_count blue-text']//span";
    private static final String ATTACHMENT_COUNT = "//div[@class='attached_count blue-text']//b";
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllBenyOnAttachment']";
    private static final String INITIALS_FIELD = "//input[@name='beneficiaries[0].initials']";
    private static final String SAVE_BTN = "//div[@class='modal-footer']//button[text()='Save']";
    private static final String EDIT_AMOUNT_PROPORTION_FIELD = "//p[@class='p0-3 ft12 newstyle position-relative']//input[@class='yellowbg bold']";
    private static final String EDIT_AMOUNT_PROPORTION_MODAL = "//div[@class='modal-title h4']";
    private static final String EDIT_AMOUNT_PROPORTION_NAME_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='2']";
    private static final String EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='3']//input[@type='checkbox']";
    private static final String PROPORTION_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='5']//input[@type='text']";
    private static final String AMOUNT_COLUMNS_MODAL = "//div[@class='modal-body']//tbody//tr//td[position()='4']//input[@type='text']";
    private static final String BENE_PROPORTION_INCOME_PAGE_5 = "//tr//td[position()='3']//p[@class='p0-3 ft12 newstyle']//input[@value and normalize-space(@value)]";
    private static final String BENE_AMOUNT_INCOME_PAGE_5 = "//tr//td[position()='2']//p[@class='p0-3 ft12 newstyle']//input[@value and normalize-space(@value)]";
    private static final String SCHEDULE_ATTACHED_MSG = "//p[text()='See continuation schedule attached']";
    private static final String BENE_ON_ATTACHMENT_PAGE_5 = "//div[@class='modal-body']//tr//td//p//input[@class='ft-1 bold' and @value='%s']";
    private static final String SIGN_OF_PETITIONER_PAGE_6 = "//div[contains(text(),'Signature of Petitioner')]//input[@id='fullname']";
    private static final String SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_6 = "//div[@class='modal-content']//div[contains(text(),'Signature of')]//span//*[self::input or self::textarea]";
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input";
    private static final String CONTACT_NAME_IN_ESTATE_CONTACT = "//td[@aria-colindex='1' and text()='%s']";
    private static final String ESTATE_SPECIFIC_FIELDS_TAB = "//div[@class='nav-item']/a[text()='Estate-Specific Fields']";
    private static final String ESTATE_SPECIFIC_SELECT_ROLE_BTN = "//button[text()='Select Role']";
    private static final String ROLE_CHECKBOX = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input[not(@checked)]";
    private static final String ROLE_UNCHECK = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String ROLE_SAVE_BTN = "//div[@class='fade custom-modal center-small-modal modal show']//button[text()='Save']";
    private static final String CONTACT_USED_IN_OC04_FORM_NOTIFICATION = "//div[@class='modal-inner-content']//li[text()='ProbateOC04']";
    private static final String NO_ROLES_SAVE_BTN = "//button[@class='btn btn-danger' and text()='Save']";
    private static final String SELECT_RELATIONSHIP_BTN = "//button[@aria-label='Select Relationship']";
    private static final String RELATIONSHIP_OPTION = "//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String SHARE_OF_ESTATE_IN_WORDS = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='4']//input";
    private static final String AMOUNT_OF_ESTATE = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='5']//input";
    private static final String BENEFICIAL_INTEREST = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='6']//input";
    private static final String BENY_WORKSHEET_TAB = "//a//span[text()='Beny Worksheet']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    private static final String COMMENTS_FIELD_XPATH = "//textarea[@name='beneficiaries[%s].comments']";
    private static final String PETITIONER_1_ADDRESS_LINE = "//td[@class='trnew10 td11']//span[@class='p0 ft12 newstyle']//input";
    private static final String PETITIONER_1_CITY_STATE_ZIP = "//td[@class='trnew10 td11']//span[@class='p0 ft0 newstyle']//input";
    private static final String PETITIONER_2_ADDRESS_LINE = "//td[@class='trnew10 td12']//span[@class='p0 ft12 newstyle']//input[not(contains(@value,','))]";
    private static final String PETITIONER_2_CITY_STATE_ZIP = "//td[@class='trnew10 td12']//span[@class='p0 ft12 newstyle']//input[contains(@value,',')]";


    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    private static final List<String> selectedPetitioner = new ArrayList<>();
    private static final List<String> Fiduciaries = new ArrayList<>();
    private static final List<String> corporateFiduciaries = new ArrayList<>();
    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneRelationship = new ArrayList<>();
    private static final List<String> beneInterest = new ArrayList<>();
    private static final List<String> beneficiaryKeys = new ArrayList<>();
    private static final List<String> selectedContactNamesPage5 = new ArrayList<>();
    private static final List<String> beneDetailsAfterRoleRemoved = new ArrayList<>();
    private static final List<String> newSelectedPetitioner = new ArrayList<>();
    private static final List<String> newFiduciaries = new ArrayList<>();
    private static final List<String> newCorporateFiduciaries = new ArrayList<>();

    static String[] enteredProportionValues;
    static String[] enteredAmountValues;

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
    static String Fiduciary5Form;
    static String newlyAddedPetitioner;
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
    static String amountForm1;
    static String amountForm2;
    static String amountForm3;
    static String startDateForm1;
    static String startDateForm2;
    static String startDateForm3;
    static String endDateForm1;
    static String endDateForm2;
    static String endDateForm3;
    static int expectedAttachmentCountForm;
    static String comment1Form;
    static String comment2Form;

    static String downloadedFileName;

    public ProbateFormsOC04Page() throws IOException, ParseException {
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

    private static String getFieldValue(WebElement field) throws AutomationException {
        if (field != null) {
            String value = field.getAttribute("value");
            return (value != null && !value.trim().isEmpty()) ? value.trim() : field.getText().trim();
        } else {
            throw new AutomationException("WebElement is null.");
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

    public void verifyEstateOfFieldIsPopulatedAutomaticallyWithTheCorrectEstateName() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Estate of field is not populated correctly with Estate name. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
        }
    }

    @Override
    String getName() {
        return "";
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

    public void verifyCounselDetailsArePopulatedCorrectly() throws AutomationException, IOException, ParseException {
        String attorneyFirmName = CommonUtil.getJsonPath("attorney1").get("attorney1.entityName").toString();
        String attorneyAddressLine1 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String attorneyAddressLine2 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine2").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney1").get("attorney1.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney1").get("attorney1.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney1").get("attorney1.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney1").get("attorney1.phoneNumber").toString();
        String attorneyFax = CommonUtil.getJsonPath("attorney1").get("attorney1.fax").toString();
        String attorneyEmail = CommonUtil.getJsonPath("attorney1").get("attorney1.emailId").toString();

        WebElement nameOfCounselField = driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD);

        nameOfCounselForm = nameOfCounselField.getAttribute("value");
        if (!nameOfCounselForm.equals(selectedAttorney)) {
            throw new AutomationException("Attorney details not populated correctly in 'Name of Counsel' field. Expected: " + selectedAttorney + " ,Found: " + nameOfCounselForm);
        }

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

    public void verifyEstateNameIsPreFilledFromTheEstateRecords() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD_PAGE_2);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Estate of field is not populated correctly on Page 2. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
        }
    }

    public void userSelectsMultiplePetitioners() throws AutomationException {
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

    public static String findFiduciaryKeyByName(String name, JSONObject jsonObject) {
        for (Object keyObj : jsonObject.keySet()) {
            String key = keyObj.toString();

            if (key.startsWith("fiduciary") || key.startsWith("corporateFiduciary")) {
                Object dataObj = jsonObject.get(key);
                if (!(dataObj instanceof JSONObject)) continue;

                JSONObject fidData = (JSONObject) dataObj;

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

                String entityName = (String) fidData.getOrDefault("entityName", "");
                if (name.equalsIgnoreCase(entityName)) {
                    return key;
                }
            }
        }
        return null;
    }

    public void verifyPetitionerOnForm(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(PETITIONER_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifyPetitionerOnAttachment(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(PETITIONER_MODAL_INPUT_XPATH, expectedValue));
        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is displayed correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not displayed in table. Expected: " + expectedValue);
        }
    }

    public void verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm() throws AutomationException, IOException, ParseException {
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

    public void verify2PetitionersAreVisibleOnTheFormAndRestAreOnTheAttachment() throws AutomationException, IOException, ParseException {
        JSONObject jsonData = CommonUtil.getJSONObject("");

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

        verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm();

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

    private boolean isIndividualFiduciary(String name) {
        return name.contains(",");
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

    public void verifyFieldIsPopulatedWithTheCorrectEstateName() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD_PAGE_3);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Estate of field is not populated correctly on Page 3. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
        }
    }

    public void clearFieldUntilEmpty(WebElement element) {
        int attempts = 0;
        while (element != null && !element.getAttribute("value").isEmpty() && attempts < 5) {
            element.clear();
            WebDriverUtil.waitForAWhile(1);
            attempts++;
        }

        if (!element.getAttribute("value").isEmpty()) {
            CommonSteps.logInfo("⚠️ Field not cleared after max attempts. Value: " + element.getAttribute("value"));
        }
    }

    private void retryUntilValueIsSet(WebElement element, String expectedValue, int maxRetries, String fieldLabel, int rowIndex) throws AutomationException {
        int attempts = 0;
        while (attempts < maxRetries) {
            element.click();
            element.clear();
            element.sendKeys(expectedValue);
            element.sendKeys(Keys.TAB);
            WebDriverUtil.waitForAWhile(2);

            String actualValue = element.getAttribute("value");
            if (expectedValue.equals(actualValue)) {
                return;
            }
            attempts++;
        }

        throw new AutomationException(fieldLabel + " mismatch at row " + (rowIndex + 1) +
                ". Expected: " + expectedValue + ", but value was not retained after " + maxRetries + " attempts.");
    }


    public void verifyFeeClaimsAmountStartDateAndEndDateAreSavedAndDisplayedCorrectly() throws AutomationException, IOException, ParseException {
        amountForm1 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.amountForm1").toString();
        amountForm2 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.amountForm2").toString();
        amountForm3 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.amountForm3").toString();

        startDateForm1 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.startDateForm1").toString();
        startDateForm2 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.startDateForm2").toString();
        startDateForm3 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.startDateForm3").toString();

        endDateForm1 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.endDateForm1").toString();
        endDateForm2 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.endDateForm2").toString();
        endDateForm3 = CommonUtil.getJsonPath("OC03Form").get("OC03Form.endDateForm3").toString();

        List<String> amountForms = Arrays.asList( amountForm1, amountForm2, amountForm3 );

        List<String> startDateForms = Arrays.asList( startDateForm1, startDateForm2, startDateForm3 );

        List<String> endDateForms = Arrays.asList( endDateForm1, endDateForm2, endDateForm3 );

        int maxRetries = 3;

        for (int i = 0; i < 3; i++) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(AMOUNT_FIELDS, i));

            WebElement endDateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(END_DATE_FIELDS, i)));
            WebElement startDateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(START_DATE_FIELDS, i)));
            WebElement amountField = DriverFactory.drivers.get().findElement(By.xpath(String.format(AMOUNT_FIELDS, i)));

            retryUntilValueIsSet(endDateField, endDateForms.get(i), maxRetries, "End Date", i);

            retryUntilValueIsSet(startDateField, startDateForms.get(i), maxRetries, "Start Date", i);

            retryUntilValueIsSet(amountField, amountForms.get(i), maxRetries, "Amount", i);

            WebDriverUtil.waitForAWhile(2);

            String actualAmount = amountField.getAttribute("value");
            String actualStartDate = startDateField.getAttribute("value");
            String actualEndDate = endDateField.getAttribute("value");

            if (!actualAmount.equals(amountForms.get(i))) {
                throw new AutomationException("Amount mismatch at row " + (i + 1) + ". Expected: " + amountForms.get(i) + ", Found: " + actualAmount);
            }
            if (!actualStartDate.equals(startDateForms.get(i))) {
                throw new AutomationException("Start Date mismatch at row " + (i + 1) + ". Expected: " + startDateForms.get(i) + ", Found: " + actualStartDate);
            }
            if (!actualEndDate.equals(endDateForms.get(i))) {
                throw new AutomationException("End Date mismatch at row " + (i + 1) + ". Expected: " + endDateForms.get(i) + ", Found: " + actualEndDate);
            }
        }
    }

    public void verifyFieldIsPopulatedWithTheCorrectEstateNameOnPage4() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD_PAGE_4);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Estate of field is not populated correctly on Page 4. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
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

        scrollToElement(BENE_DETAILS_FORM);
        List<WebElement> beneDetailsPage4Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        List<WebElement> beneRelationshipPage4Fields = driverUtil.getWebElements(BENE_RELATIONSHIP_FORM);
        List<WebElement> beneInterestPage4Fields = driverUtil.getWebElements(BENE_INTEREST_FORM);

        for (int i = 0; i < 2; i++) {
            beneDetails.add(beneDetailsPage4Fields.get(i).getText());
            beneRelationship.add(beneRelationshipPage4Fields.get(i).getAttribute("value"));
            beneInterest.add(beneInterestPage4Fields.get(i).getText());
        }

        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();

        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsOnAttachmentFields = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);
        List<WebElement> beneRelationshipOnAttachmentFields = driverUtil.getWebElements(BENE_RELATIONSHIP_ATTACHMENT);
        List<WebElement> beneInterestOnAttachmentFields = driverUtil.getWebElements(BENE_INTEREST_ATTACHMENT);

        for (int i = 0; i < 5; i++) {
            beneDetails.add(beneDetailsOnAttachmentFields.get(i).getText());
            beneRelationship.add(beneRelationshipOnAttachmentFields.get(i).getText());
            beneInterest.add(beneInterestOnAttachmentFields.get(i).getText());
        }

        driverUtil.getWebElement(CLOSE_BTN).click();

        for (String detail : beneDetails) {
            String fullName = extractFullName(detail);
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
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
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

    public void userAddsInitials() throws IOException, ParseException, AutomationException {
        String initials = CommonUtil.getJsonPath("OC01Form").get("OC01Form.initials").toString();
        scrollToElement(INITIALS_FIELD);
        driverUtil.getWebElement(INITIALS_FIELD).sendKeys(initials);
        driverUtil.getWebElement(INITIALS_FIELD).sendKeys(Keys.TAB);
    }

    public void verifyNameAndAddressGetsDisappear() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        WebElement nameAndAddressField = DriverFactory.drivers.get().findElement(By.xpath(BENE_DETAILS_FORM));

        if (!nameAndAddressField.getText().isEmpty()) {
            throw new AutomationException("Name and address does not gets disappear.");
        }
    }

    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void userRemovesInitials() throws AutomationException {
        clearField(INITIALS_FIELD);
        driverUtil.getWebElement(INITIALS_FIELD).sendKeys(Keys.TAB);
    }

    public void verifyNameAndAddressOfTheBeneficiariesIsDisplayed() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement nameAndAddressField = DriverFactory.drivers.get().findElement(By.xpath(BENE_DETAILS_FORM));

        if (nameAndAddressField.getText().isEmpty()) {
            throw new AutomationException("Name and address is not displayed.");
        }
    }

    public void userAddsComments() throws AutomationException, IOException, ParseException {
        for (int i = 0; i < 2; i++) {
            WebElement commentsField = driverUtil.getWebElement(String.format(COMMENTS_FIELD_XPATH, i));
            String dataKey = String.valueOf(i + 1);
            String comment = CommonUtil.getJsonPath("OC01Form").get("OC01Form.comment" + dataKey).toString();

            commentsField.click();
            commentsField.sendKeys(comment);
            driverUtil.getWebElement("//body").click();
            WebDriverUtil.waitForAWhile();

            switch (i) {
                case 0:
                    comment1Form = commentsField.getText();
                    break;
                case 1:
                    comment2Form = commentsField.getText();
                    break;
            }

        }
    }

    public void verifyCommentsAreAddedAndAutoSaved() throws AutomationException {
        switchToPage(5);
        switchToPage(4);

        List<String> comments = Arrays.asList(comment1Form, comment2Form);

        for (int i = 0; i < 2; i++) {
            WebElement commentsField = driverUtil.getWebElement(String.format(COMMENTS_FIELD_XPATH, i));
            String actualComment = commentsField.getText();
            String expectedComment = comments.get(i);

            if (!actualComment.equals(expectedComment)) {
                throw new AutomationException("Comment " + (i + 1) + " is not auto-saved. Expected: " + expectedComment + " ,Found: " + actualComment);
            }
        }
    }

    public void verifyCorrectRelationshipIsAutoFetchedAndDisplayedUnderRelationshipSection() throws IOException, ParseException, AutomationException {
        for (int i = 0; i < 7; i++) {
            String key = beneficiaryKeys.get(i);
            String expectedRelationship = CommonUtil.getJsonPath(key).get(key + ".Relationship").toString();
            String actualRelationship = beneRelationship.get(i).trim();

            if (!actualRelationship.equals(expectedRelationship)) {
                throw new AutomationException("Relationship is not fetched correctly. Expected: " + expectedRelationship + " ,Found: " + actualRelationship);
            }
        }
    }

    public void verifyInterestIsAutoFetchedFromBenyWorksheet() throws AutomationException, IOException, ParseException {
        for (int i = 0; i < 7; i++) {
            String key = beneficiaryKeys.get(i);
            String expectedBeneficialInterest = CommonUtil.getJsonPath(key).get(key + ".BeneficialInterest").toString();
            String actualBeneficialInterest = beneInterest.get(i).trim();

            if (!actualBeneficialInterest.equals(expectedBeneficialInterest)) {
                throw new AutomationException("Beneficial Interest is not fetched correctly. Expected: " + expectedBeneficialInterest + " ,Found: " + actualBeneficialInterest);
            }
        }
    }

    public void verifyFieldIsPopulatedWithTheCorrectEstateNameOnPage5() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD_PAGE_5);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Estate of field is not populated correctly on Page 5. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
        }
    }

    public void userClicksOnTheEditAmountsProportionsButtonForIncome() throws AutomationException {
        scrollToElement(EDIT_AMOUNT_PROPORTION_FIELD);
        driverUtil.getWebElement(EDIT_AMOUNT_PROPORTION_FIELD).click();
    }

    public void verifyTheSidebarOpensDisplayingAListOfBeneficiaries() throws AutomationException {
        waitForVisibleElement(By.xpath(EDIT_AMOUNT_PROPORTION_MODAL));
        WebElement modalHeader = driverUtil.getWebElement(EDIT_AMOUNT_PROPORTION_MODAL);

        if (!modalHeader.getText().contains("Edit Amounts/Proportions")) {
            throw new AutomationException("Sidebar is not displayed for Edit Amounts/Proportions.");
        }
    }

    public void userEntersAmountsAndProportionsForBeneficiaries() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> checkboxElements = driverUtil.getWebElements(EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS);

        int[] contactsToDisplay = {0, 2, 3, 4, 5};

        for (int index : contactsToDisplay) {
            if (index < checkboxElements.size()) {
                WebElement checkbox = checkboxElements.get(index);
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

        List<WebElement> nameElements = driverUtil.getWebElements(EDIT_AMOUNT_PROPORTION_NAME_COLUMNS);

        for (int i = 0; i < checkboxElements.size(); i++) {
            if (checkboxElements.get(i).isSelected()) {
                selectedContactNamesPage5.add(nameElements.get(i).getText());
            }
        }

        List<WebElement> amountFields = driverUtil.getWebElements(AMOUNT_COLUMNS_MODAL);

        int[] contactsToEnterAmount = {0, 2, 3, 4, 5};
        enteredAmountValues = new String[]{"23,000.00", "18,000.00", "34,000.00", "20,000.00", "25,000.00"};

        for (int i = 0; i < contactsToEnterAmount.length; i++) {
            int index = contactsToEnterAmount[i];
            String value = enteredAmountValues[i];

            if (index < amountFields.size()) {
                WebElement inputField = amountFields.get(index);
                inputField.clear();
                inputField.sendKeys(value);
            }
        }


        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);

        int[] contactsToEnterProportion = {0, 2, 3, 4, 5};
        enteredProportionValues = new String[]{"25.0000", "35.0000", "20.0000", "20.0000", "23.0000"};

        for (int i = 0; i < contactsToEnterProportion.length; i++) {
            int index = contactsToEnterProportion[i];
            String value = enteredProportionValues[i];

            if (index < proportionFields.size()) {
                WebElement inputField = proportionFields.get(index);
                inputField.clear();
                inputField.sendKeys(value);
            }
        }

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyEnteredDataIsSavedAndDisplayedCorrectlyOnTheForm() throws AutomationException {
        waitForAWhile(2);
        List<WebElement> displayedProportionElements = driverUtil.getWebElements(BENE_PROPORTION_INCOME_PAGE_5);

        for (int i = 0; i < 4; i++) {
            String expectedValue = enteredProportionValues[i];
            String actualValue = displayedProportionElements.get(i).getAttribute("value").trim().replace("%", "");

            if (!expectedValue.equals(actualValue)) {
                throw new AutomationException("Mismatch at row " + (i + 1) + ": expected " + expectedValue + ", got " + actualValue);
            }
        }

        List<WebElement> displayedAmountElements = driverUtil.getWebElements(BENE_AMOUNT_INCOME_PAGE_5);

        for (int i = 0; i < 4; i++) {
            String expectedValue = enteredAmountValues[i];
            String actualValue = displayedAmountElements.get(i).getAttribute("value").trim().replace("%", "");

            if (!expectedValue.equals(actualValue)) {
                throw new AutomationException("Mismatch at row " + (i + 1) + ": expected " + expectedValue + ", got " + actualValue);
            }
        }
    }

    public void verifyFormDisplaysSeeContinuationScheduleAttachedAndExtraBeneficiariesAppearInAnAttachment() throws AutomationException {
        WebElement scheduleAttachedMsg = driverUtil.getWebElement(SCHEDULE_ATTACHED_MSG);

        if(selectedContactNamesPage5.size() <=4 ){
            throw new AutomationException("There are no additional beneficiaries to verify in the attachment.");
        }

        if(!scheduleAttachedMsg.isDisplayed()){
            throw new AutomationException("'See continuation schedule attached' is not displayed on form.");
        }

        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        waitForAWhile();

        for (int i = 4; i < selectedContactNamesPage5.size(); i++) {
            String expectedBeneficiary = selectedContactNamesPage5.get(i).trim();
            String xpath = String.format(BENE_ON_ATTACHMENT_PAGE_5, expectedBeneficiary);

            WebElement beneficiaryElement = driverUtil.getWebElement(xpath);
            if (!beneficiaryElement.isDisplayed()) {
                throw new AutomationException("Beneficiary not found or not visible in attachment: " + expectedBeneficiary);
            }
        }

        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyCorrectTrustNameIsDisplayedOnTheForm() throws AutomationException {
        WebElement estateOfField = driverUtil.getWebElement(ESTATE_OF_FIELD_PAGE_6);
        String actualEstateName = estateOfField.getAttribute("value");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredEstateName.equals(actualEstateName)){
            throw new AutomationException("Correct Trust Name is not displayed on the form. Expected: "+enteredEstateName+" ,Found: "+actualEstateName);
        }
    }

    public void verify1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner() throws AutomationException {
        WebElement signOfPetitionerField = driverUtil.getWebElement(SIGN_OF_PETITIONER_PAGE_6);
        String petitionerNamePage11 = signOfPetitionerField.getAttribute("value");
        String expectedPetitionerName = Fiduciaries.get(0);

        if (!petitionerNamePage11.equals(expectedPetitionerName)) {
            throw new AutomationException("1st individual petitioner selected on page 2 is not displayed under individual petitioner. Expected: " + expectedPetitionerName + " ,Found: " + petitionerNamePage11);
        }
    }

    public void verifyAllTheRemainingPetitionersAreDisplayedAsAPartOfAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        List<String> expectedPetitionersOnAttachment = new ArrayList<>();
        List<String> expectedFiduciary = new ArrayList<>();
        List<String> expectedCorporateFiduciary =new ArrayList<>();

        for(int i=0; i<Fiduciaries.size(); i++){
            if(i!=0){
                expectedFiduciary.add(Fiduciaries.get(i));
            }
        }

        for(int i=0; i<corporateFiduciaries.size(); i++){
            if(i!=0){
                expectedCorporateFiduciary.add(corporateFiduciaries.get(i));
            }
        }

        expectedPetitionersOnAttachment.addAll(expectedFiduciary);
        expectedPetitionersOnAttachment.addAll(expectedCorporateFiduciary);

        List<WebElement> petitionerOnAttachmentFields = driverUtil.getWebElements(SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_6);
        List<String> actualPetitionersOnAttachment = new ArrayList<>();

        for (WebElement element : petitionerOnAttachmentFields) {
            actualPetitionersOnAttachment.add(getFieldValue(element));
        }

        for (int i = 0; i < expectedPetitionersOnAttachment.size(); i++) {
            String expected = expectedPetitionersOnAttachment.get(i);
            String actual = actualPetitionersOnAttachment.get(i);

            if (!actual.contains(expected)) {
                throw new AutomationException("Petitioner mismatch at sign " + (i + 1) +
                        "\nExpected: " + expected + "\nActual: " + actual);
            }
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void userAddsNewPetitioner() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        waitForVisibleElement(By.xpath(DRAG_CONTACT_XPATH));
        newlyAddedPetitioner = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

        WebDriverUtil.waitForAWhile(2);
        List<WebElement> fiduciaryNames = driverUtil.getWebElements(SELECTED_PETITIONER_NAMES);

        for (int i = 0; i < fiduciaryNames.size(); i++) {
            String name = fiduciaryNames.get(i).getText().trim();
            switch (i) {
                case 0:
                    Fiduciary1Form = name;
                    break;
                case 1:
                    Fiduciary2Form = name;
                    break;
                case 2:
                    Fiduciary3Form = name;
                    break;
                case 3:
                    Fiduciary4Form = name;
                    break;
                case 4:
                    Fiduciary5Form = name;
                    break;
            }
        }

        List<WebElement> selectedContacts = driverUtil.getWebElements(SELECTED_PETITIONER_NAMES);
        for (WebElement selectedContact : selectedContacts) {
            String petitioner = selectedContact.getText().trim();
            newSelectedPetitioner.add(petitioner);

            if (isIndividualFiduciary(petitioner)) {
                newFiduciaries.add(petitioner);
            } else {
                newCorporateFiduciaries.add(petitioner);
            }
        }

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));

        WebElement nameOfPetitionerField = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);
        nameOfPetitionerForm = nameOfPetitionerField.getAttribute("value");
        nameOfPetitioner2Form = nameOfPetitioner2Field.getAttribute("value");
        petitioner1AddressLine1Form = driverUtil.getWebElement(PETITIONER_1_ADDRESS_LINE).getAttribute("value");
        petitioner1CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_1_CITY_STATE_ZIP).getAttribute("value");
        petitioner2AddressLine1Form = driverUtil.getWebElement(PETITIONER_2_ADDRESS_LINE).getAttribute("value");
        petitioner2CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_2_CITY_STATE_ZIP).getAttribute("value");
    }

    public void verifyNewlyAddedPetitionerIsDisplayedInTheAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        List<WebElement> petitionerOnAttachmentFields = driverUtil.getWebElements(SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_6);
        List<String> actualPetitionersOnAttachment = new ArrayList<>();

        for (WebElement element : petitionerOnAttachmentFields) {
            actualPetitionersOnAttachment.add(getFieldValue(element));
        }

        if (!actualPetitionersOnAttachment.contains(newlyAddedPetitioner)) {
            throw new AutomationException("Newly added petitioner: " + newlyAddedPetitioner + " not found on attachment.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void filterByContactName(String contactName) throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CONTACT_NAME_FILTER).click();
        clearField(CONTACT_NAME_FILTER);
        driverUtil.getWebElement(CONTACT_NAME_FILTER).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
    }

    public String getEstateContactName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }

        if (!fullName.contains(" ")) {
            return fullName.trim();
        }

        String nameWithoutSuffix = fullName.replaceAll(",\\s*\\w+\\.*", "").trim();

        String[] nameParts = nameWithoutSuffix.split("\\s+");

        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[nameParts.length - 1];
            return lastName + ", " + firstName;
        }

        return nameParts[0].trim();
    }

    public void checkTheFiduciaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleFiduciary").toString();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void uncheckTheFiduciaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleFiduciary").toString();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_UNCHECK, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void checkTheBeneficiaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleBeneficiary").toString();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void uncheckTheBeneficiaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleBeneficiary").toString();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_UNCHECK, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void verifyNotificationIsDisplayedOnRemovingTheRole() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        waitForVisibleElement(By.xpath(CONTACT_USED_IN_OC04_FORM_NOTIFICATION));
        WebElement rolesNotification = driverUtil.getWebElement(CONTACT_USED_IN_OC04_FORM_NOTIFICATION);
        if (!rolesNotification.isDisplayed()) {
            throw new AutomationException("Notification is not displayed on removing the role");
        }
    }

    public void verifyNotificationIsDisplayedWhenTheContactSelectedAsThePetitionerIsRemovedFromTheEstateContacts() throws AutomationException, IOException, ParseException {
        String petitioner1 = getEstateContactName(nameOfPetitionerForm);
        String petitioner2 = getEstateContactName(nameOfPetitioner2Form);
        String petitionerOnAttachment;

        if (isIndividualFiduciary(Fiduciary5Form)) {
            petitionerOnAttachment = getEstateContactName(Fiduciary5Form);;
        } else {
            petitionerOnAttachment = Fiduciary5Form;
        }

        filterByContactName(petitioner1);
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner1)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheFiduciaryRole();
        verifyNotificationIsDisplayedOnRemovingTheRole();
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        filterByContactName(petitioner2);
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner2)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheFiduciaryRole();
        verifyNotificationIsDisplayedOnRemovingTheRole();
        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        filterByContactName(petitionerOnAttachment);
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitionerOnAttachment)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheFiduciaryRole();
        verifyNotificationIsDisplayedOnRemovingTheRole();
        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));
    }

    public void verifyNotificationIsDisplayedWhenTheBeneficiaryContactIsRemovedFromTheEstateContacts() throws AutomationException, IOException, ParseException {
        String fullName = extractFullName(beneDetails.get(0));
        String beneficiaryContact = getEstateContactName(fullName);

        filterByContactName(beneficiaryContact);
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, beneficiaryContact)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheBeneficiaryRole();
        verifyNotificationIsDisplayedOnRemovingTheRole();
        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));
    }

    public void verifyRemovedPetitionerContactsFromTheEstateContactsIsAlsoRemovedFromTheForm() throws AutomationException {
        WebElement nameOfPetitioner1Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);

        String nameOfPetitioner1 = nameOfPetitioner1Field.getAttribute("value");
        if (nameOfPetitioner1.equals(nameOfPetitionerForm)) {
            throw new AutomationException("Removed petitioner contact " + nameOfPetitionerForm + " from the estate contacts is not gets removed from the form");
        }

        String nameOfPetitioner2 = nameOfPetitioner2Field.getAttribute("value");
        if (nameOfPetitioner2.equals(nameOfPetitioner2Form)) {
            throw new AutomationException("Removed petitioner contact " + nameOfPetitioner2Form + " from the estate contacts is not gets removed from the form");
        }
    }

    public void verifyRemovedBeneficiaryContactFromTheEstateContactsIsAlsoGetsRemovedFromTheForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsPage4Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        for (int i = 0; i < 2; i++) {
            beneDetailsAfterRoleRemoved.add(beneDetailsPage4Fields.get(i).getText());
        }

        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsOnAttachmentFields = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);
        for (int i = 0; i < 4; i++) {
            beneDetailsAfterRoleRemoved.add(beneDetailsOnAttachmentFields.get(i).getText());
        }
        driverUtil.getWebElement(CLOSE_BTN).click();

        String removedBene = beneDetails.get(0);
        if (beneDetailsAfterRoleRemoved.contains(removedBene)) {
            throw new AutomationException("Beneficiary contact still exists on the form even after role was removed: " + removedBene);
        }
    }

    public void resetRolesOfContacts() throws AutomationException, IOException, ParseException {
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        String petitioner1 = getEstateContactName(nameOfPetitionerForm);
        String petitioner2 = getEstateContactName(nameOfPetitioner2Form);
        String petitionerOnAttachment;

        if (isIndividualFiduciary(Fiduciary5Form)) {
            petitionerOnAttachment = getEstateContactName(Fiduciary5Form);;
        } else {
            petitionerOnAttachment = Fiduciary5Form;
        }

        filterByContactName(petitioner1);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner1)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        checkTheFiduciaryRole();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        filterByContactName(petitioner2);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner2)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        checkTheFiduciaryRole();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        filterByContactName(petitionerOnAttachment);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitionerOnAttachment)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        checkTheFiduciaryRole();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        String fullName = extractFullName(beneDetails.get(0));
        String beneficiaryContact = getEstateContactName(fullName);
        String beneKey = beneficiaryKeys.get(0);
        String relationship = CommonUtil.getJsonPath(beneKey).get(beneKey + ".Relationship").toString();

        filterByContactName(beneficiaryContact);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, beneficiaryContact)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        checkTheBeneficiaryRole();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));
        driverUtil.getWebElement(SELECT_RELATIONSHIP_BTN).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(RELATIONSHIP_OPTION,relationship)).click();
        driverUtil.getWebElement(SAVE_BTN).click();
        WebDriverUtil.waitForAWhile();

        String shareOfEstateInWord = CommonUtil.getJsonPath(beneKey).get(beneKey + ".ShareOfEstate").toString();
        String amountOfEstate = CommonUtil.getJsonPath(beneKey).get(beneKey + ".AmountOfEstate").toString();
        String beneficialInterest = CommonUtil.getJsonPath(beneKey).get(beneKey + ".BeneficialInterest").toString();


        driverUtil.getWebElement(BENY_WORKSHEET_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(String.format(SHARE_OF_ESTATE_IN_WORDS, fullName)).sendKeys(shareOfEstateInWord);
        driverUtil.getWebElement(String.format(SHARE_OF_ESTATE_IN_WORDS, fullName)).sendKeys(Keys.TAB);
        driverUtil.getWebElement(String.format(AMOUNT_OF_ESTATE, fullName)).sendKeys(amountOfEstate);
        driverUtil.getWebElement(String.format(AMOUNT_OF_ESTATE, fullName)).sendKeys(Keys.TAB);
        driverUtil.getWebElement(String.format(BENEFICIAL_INTEREST, fullName)).sendKeys(beneficialInterest);
        driverUtil.getWebElement(String.format(BENEFICIAL_INTEREST, fullName)).sendKeys(Keys.TAB);
    }

    public void verifyAfterRemovingTheExistingContactNextInlineContactIsDisplayedOnTheForm() throws AutomationException {
        WebElement signOfPetitionerField = driverUtil.getWebElement(SIGN_OF_PETITIONER_PAGE_6);
        String petitionerNamePage6 = signOfPetitionerField.getAttribute("value");
        String expectedPetitionerName = newFiduciaries.get(2);

        if (!petitionerNamePage6.equals(expectedPetitionerName)) {
            throw new AutomationException("Next inline contact is not displayed on the form after removing the existing contact. Expected: " + expectedPetitionerName + " ,Found: " + petitionerNamePage6);
        }
    }

    public void verifyContactRemovedFromTheEstateContactsIsAlsoRemovedFromTheAttachmentAsWell() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        List<WebElement> petitionerOnAttachmentFields = driverUtil.getWebElements(SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_6);
        List<String> actualPetitionersOnAttachment = new ArrayList<>();

        for (WebElement element : petitionerOnAttachmentFields) {
            actualPetitionersOnAttachment.add(getFieldValue(element));
        }

        if (actualPetitionersOnAttachment.contains(Fiduciary5Form)) {
            throw new AutomationException("The contact removed from the estate contacts is not gets removed from the attachment. Found: "+Fiduciary5Form+ " on attachment.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();
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
        driverUtil.getWebElement(ACCEPT_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));

        //Page 3
        switchToPage(3);
        for (int i = 2; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(AMOUNT_FIELDS, i));

            WebElement amountField = driverUtil.getWebElement(String.format(AMOUNT_FIELDS, i));
            clearFieldUntilEmpty(amountField);

            WebElement startDateField = driverUtil.getWebElement(String.format(START_DATE_FIELDS, i));
            clearFieldUntilEmpty(startDateField);

            WebElement endDateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(END_DATE_FIELDS, i)));
            clearFieldUntilEmpty(endDateField);
        }

        //Page 4
        switchToPage(4);
        for (int i = 0; i < 2; i++) {
            scrollToElement(String.format(COMMENTS_FIELD_XPATH, i));
            WebElement commentsField = driverUtil.getWebElement(String.format(COMMENTS_FIELD_XPATH, i));
            commentsField.clear();
        }

        //Page 5
        switchToPage(5);
        scrollToElement(EDIT_AMOUNT_PROPORTION_FIELD);
        driverUtil.getWebElement(EDIT_AMOUNT_PROPORTION_FIELD).click();
        WebDriverUtil.waitForAWhile();
        List<WebElement> checkboxElements = driverUtil.getWebElements(EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS);
        int[] contactsToClear = {0, 2, 3, 4, 5};
        for (int index : contactsToClear) {
            if (index < checkboxElements.size()) {
                WebElement checkbox = checkboxElements.get(index);
                checkbox.click();
            }
        }

        List<WebElement> amountFields = driverUtil.getWebElements(AMOUNT_COLUMNS_MODAL);
        int[] contactsToEnterAmount = {0, 2, 3, 4, 5};
        for (int i = 0; i < contactsToEnterAmount.length; i++) {
            int index = contactsToEnterAmount[i];

            if (index < amountFields.size()) {
                WebElement inputField = amountFields.get(index);
                inputField.clear();
            }
        }

        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);
        int[] contactsToEnterProportion = {0, 2, 3, 4, 5};
        for (int i = 0; i < contactsToEnterProportion.length; i++) {
            int index = contactsToEnterProportion[i];

            if (index < proportionFields.size()) {
                WebElement inputField = proportionFields.get(index);
                inputField.clear();
            }
        }
        driverUtil.getWebElement(CLOSE_BTN).click();
    }
}

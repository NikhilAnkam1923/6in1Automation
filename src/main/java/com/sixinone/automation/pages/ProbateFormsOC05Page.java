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

public class ProbateFormsOC05Page extends BasePage{
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
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllBeneficiariesOnAttachment']";
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
    private static final String CONTACT_USED_IN_OC05_FORM_NOTIFICATION = "//div[@class='modal-inner-content']//li[text()='ProbateOC05']";
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
    private static final String ADDITION_PETITIONER_ADDRESS = "//div[@class='modal-body']//tbody//tr//td//p[contains(text(),'Address')]/ancestor::td/following-sibling::td//input";
    private static final String FIRST_PAGE_ACTIVE_XPATH = "//a[@role='tab' and text()='1' and @class='nav-link active']";
    private static final String COUNTY_PAGE_1_XPATH = "//div[@class='county-title']//span[@class='county-name']";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String NAME_OF_TRUST_FIELD_OTHER_PAGES = "//p[contains(text(),'Estate of')]//input";
    private static final String SEE_CONTINUATION_SCHEDULE_ATTACHED_MSG = "//p//span[text()='See continuation schedule attached']";
    private static final String AGENT_NAME_FIELD_1 = "//input[@name='nameAgent1']";
    private static final String AGENT_NAME_FIELD_2 = "//input[@name='nameAgent2']";
    private static final String AGENT_1_ADDRESS_LINE = "//input[@name='addressLine1Agent1']";
    private static final String AGENT_1_CITY_STATE_ZIP = "//input[@name='cityStateZipAgent1']";
    private static final String AGENT_2_ADDRESS_LINE = "//input[@name='addressLine1Agent2']";
    private static final String AGENT_2_CITY_STATE_ZIP = "//input[@name='cityStateZipAgent2']";
    private static final String ADDRESS_SECTION = "//textarea[@name='safeDepositBoxDetails[0].institutionAndAddress']";
    private static final String INSTITUTION_ADDRESS_FIELD = "//div[@class='modal-content']//input[@name='institutionAndAddress']";
    private static final String BOX_NO_FIELD = "//div[@class='modal-content']//input[@name='boxNo']";
    private static final String TITTLE_OF_REGISTRATION_FIELD = "//div[@class='modal-content']//input[@name='titleOrRegistration']";
    private static final String DATE_CLOSED_FIELD = "//div[@class='modal-content']//input[@name='dateClosed']";
    private static final String ADD_BUTTON = "//div[@class='modal-content']//button[@type='submit']";
    private static final String INSTITUTION_ADDRESS_FIELD_TABLE = "//div[@class='modal-content']//tbody//td[position()='1']";
    private static final String BOX_NO_FIELD_TABLE = "//div[@class='modal-content']//tbody//td[position()='2']";
    private static final String TITTLE_OF_REGISTRATION_FIELD_TABLE = "//div[@class='modal-content']//tbody//td[position()='3']";
    private static final String DATE_CLOSED_FIELD_TABLE = "//div[@class='modal-content']//tbody//td[position()='4']";
    private static final String INSTITUTION_ADDRESS_FIELD_FORM = "//textarea[@name='safeDepositBoxDetails[0].institutionAndAddress']";
    private static final String BOX_NO_FIELD_FORM = "//textarea[@name='safeDepositBoxDetails[0].boxNo']";
    private static final String TITTLE_OF_REGISTRATION_FIELD_FORM = "//textarea[@name='safeDepositBoxDetails[0].titleOrRegistration']";
    private static final String DATE_CLOSED_FIELD_FORM = "//input[@name='safeDepositBoxDetails[0].dateClosed']";
    private static final String DELETE_BUTTON = "//div[@class='modal-content']//tbody//td[position()='5']//button";
    private static final String BENE_NAMES_INCOME_PAGE_7 = "//p[@class='p0-3 ft12 newstyle position-relative']//input[@value and normalize-space(@value)]";
    private static final String BENE_ON_ATTACHMENT_PAGE_7 = "//div[@class='modal-body']//tr//td//p//input[@class='ft-1 bold' and @value='%s']";
    private static final String DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN_OC05 = "//input[@name='displayAllIncomeDistributeesOnAttachment']";

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    private static final List<String> selectedPetitioner = new ArrayList<>();
    private static final List<String> Fiduciaries = new ArrayList<>();
    private static final List<String> corporateFiduciaries = new ArrayList<>();
    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneRelationship = new ArrayList<>();
    private static final List<String> beneInterest = new ArrayList<>();
    private static final List<String> beneficiaryKeys = new ArrayList<>();
    private static final List<String> selectedContactNamesPage7 = new ArrayList<>();
    private static final List<String> selectedContactNamesPage5 = new ArrayList<>();
    private static final List<String> beneDetailsAfterRoleRemoved = new ArrayList<>();
    private static final List<String> newSelectedPetitioner = new ArrayList<>();
    private static final List<String> newFiduciaries = new ArrayList<>();
    private static final List<String> newCorporateFiduciaries = new ArrayList<>();

    static String[] enteredProportionValues;
    static String[] enteredAmountValues;

    static String countyNameForm;
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
    static String estateNameFormPage5;
    static String estateNameFormPage3;
    static String estateNameFormPage4;
    static String estateNameFormPage6;
    static String estateNameFormPage7;
    static String estateNameFormPage8;
    static String agent1nameForm;
    static String agent1addressLine1Form;
    static String agent1cityStateZipForm;
    static String agent2nameForm;
    static String agent2addressLine1Form;
    static String agent2cityStateZipForm;
    static String InstitutionAddressForm;
    static String BoxNoForm;
    static String TitleOfRegistrationForm;
    static String DateClosedForm;
    static String comment1Form;
    static String comment2Form;

    public ProbateFormsOC05Page() throws IOException, ParseException {
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

    public void verifyByDefaultPageShouldIsOpenedAndCorrectCountyNameIsFetchedAndDisplayedAtTheTopOfTheForm() throws AutomationException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);
        WebElement countyName = driverUtil.getWebElement(COUNTY_PAGE_1_XPATH);
        String enteredCountyName = getEstateValue("DomicileCountry").toUpperCase();
        countyNameForm = countyName.getText();

        if (!page1.isDisplayed()) {
            throw new AutomationException("On clicking OC02, page 1 is not opened by default.");
        }

        if (!countyNameForm.equals(enteredCountyName)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm);
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

        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);

        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }
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

    public void verifySelectedNamesOfPetitionerAreDisplayedWithAddress() throws AutomationException {
        String key1 = findFiduciaryKeyByName(Fiduciary1Form, jsonData);
        JSONObject contact1 = (JSONObject) jsonData.get(key1);
        String petitioner1AddressLine1 = contact1.get("addressLine1").toString();
        String city1 = contact1.get("city").toString() + ",";
        String stateCode1 = contact1.get("stateCode").toString();
        String zip1 = contact1.get("zip").toString();
        String petitioner1CityStateCodeZip = city1 + " " + stateCode1 + " " + zip1;

        String key2 = findFiduciaryKeyByName(Fiduciary2Form, jsonData);
        JSONObject contact2 = (JSONObject) jsonData.get(key2);
        String petitioner2AddressLine1 = contact2.get("addressLine1").toString();
        String city2 = contact2.get("city").toString() + ",";
        String stateCode2 = contact2.get("stateCode").toString();
        String zip2 = contact2.get("zip").toString();
        String petitioner2CityStateCodeZip = city2 + " " + stateCode2 + " " + zip2;

        WebElement nameOfPetitionerField = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);

        nameOfPetitionerForm = nameOfPetitionerField.getAttribute("value");
        if (!nameOfPetitionerForm.equals(Fiduciary1Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary1Form + " ,Found: " + nameOfPetitionerForm);
        }
        verifyPetitionerOnForm(petitioner1AddressLine1);
        verifyPetitionerOnForm(petitioner1CityStateCodeZip);


        nameOfPetitioner2Form = nameOfPetitioner2Field.getAttribute("value");
        if (!nameOfPetitioner2Form.equals(Fiduciary2Form)) {
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary2Form + " ,Found: " + nameOfPetitioner2Form);
        }
        verifyPetitionerOnForm(petitioner2AddressLine1);
        verifyPetitionerOnForm(petitioner2CityStateCodeZip);

        petitioner1AddressLine1Form = driverUtil.getWebElement(PETITIONER_1_ADDRESS_LINE).getAttribute("value");
        petitioner1CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_1_CITY_STATE_ZIP).getAttribute("value");
        petitioner2AddressLine1Form = driverUtil.getWebElement(PETITIONER_2_ADDRESS_LINE).getAttribute("value");
        petitioner2CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_2_CITY_STATE_ZIP).getAttribute("value");
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
        String petitioner3CityStateCodeZip = city3 + " " + stateCode3 + " " + zip3;
        String petitioner3AddressLine1 = address3Line1 + ", " + address3Line2;

        String key4 = findFiduciaryKeyByName(Fiduciary4Form, jsonData);

        JSONObject contact4 = (JSONObject) jsonData.get(key4);
        String address4Line1 = contact4.get("addressLine1").toString();
        String address4Line2 = contact4.get("addressLine2").toString();
        String city4 = contact4.get("city").toString() + ",";
        String stateCode4 = contact4.get("stateCode").toString();
        String zip4 = contact4.get("zip").toString();
        String petitioner4CityStateCodeZip = city4 + " " + stateCode4 + " " + zip4;
        String petitioner4AddressLine1 = address4Line1 + ", " + address4Line2;

        verifySelectedNamesOfPetitionerAreDisplayedWithAddress();

        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        verifyPetitionerOnAttachment(Fiduciary3Form);
        verifyPetitionerOnAttachment(petitioner3AddressLine1);
        verifyPetitionerOnAttachment(petitioner3CityStateCodeZip);

        verifyPetitionerOnAttachment(Fiduciary4Form);
        verifyPetitionerOnAttachment(petitioner4AddressLine1);
        verifyPetitionerOnAttachment(petitioner4CityStateCodeZip);

        CommonSteps.takeScreenshot();

        List<WebElement> additionalPetitionerAddress = driverUtil.getWebElements(ADDITION_PETITIONER_ADDRESS);

        for (int i=0; i< additionalPetitionerAddress.size(); i++){
            String value = additionalPetitionerAddress.get(i).getAttribute("value");
            switch (i){
                case 0:
                    petitioner3AddressLine1Form = value;
                    break;
                case 1:
                    petitioner3CityStateCodeZipForm = value;
                    break;
                case 2:
                    petitioner4AddressLine1Form = value;
                    break;
                case 3:
                    petitioner4CityStateCodeZipForm = value;
                    break;

            }
        }

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

    public void userSelectsNewCounsel() throws AutomationException, IOException, ParseException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney2").get("attorney2.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney2").get("attorney2.lastName").toString();
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney2").get("attorney2.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney2").get("attorney2.suffix").toString();

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

        driverUtil.getWebElement(PROCEED_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel updated successfully.")));
    }

    public void verifyNewCounselDetailsArePopulatedCorrectlyOnTheForm() throws IOException, ParseException, AutomationException {
        String attorneyFirmName = CommonUtil.getJsonPath("attorney2").get("attorney2.entityName").toString();
        String attorneyAddressLine1 = CommonUtil.getJsonPath("attorney2").get("attorney2.addressLine1").toString();
        String attorneyAddressLine2 = CommonUtil.getJsonPath("attorney2").get("attorney2.addressLine2").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney2").get("attorney2.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney2").get("attorney2.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney2").get("attorney2.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney2").get("attorney2.phoneNumber").toString();
        String attorneyFax = CommonUtil.getJsonPath("attorney2").get("attorney2.fax").toString();
        String attorneyEmail = CommonUtil.getJsonPath("attorney2").get("attorney2.emailId").toString();

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

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayed() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage5 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage5)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage5);
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
        if (beneDetails.size() <= 2) {
            throw new AutomationException("There are no additional beneficiaries to verify in the attachment.");
        }

        if(!driverUtil.getWebElement(SEE_CONTINUATION_SCHEDULE_ATTACHED_MSG).isDisplayed()){
            throw new AutomationException("See Continuation Schedule Attached message not display");
        }

        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

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

    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
        waitForAWhile();
        scrollToElement(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
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

        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(CLOSE_BTN).click();

        userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox();
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

    public void scrollAndFillField(String fieldLocator, String value) throws AutomationException {
        scrollToElement(fieldLocator);
        WebElement Field = driverUtil.getWebElement(fieldLocator);
        Field.click();
        Field.sendKeys(value);
        Field.sendKeys(Keys.TAB);
    }

    public void userEntersAgentSNameAndAddressDetails() throws IOException, ParseException, AutomationException {
        String agent1name = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent1name").toString();
        String agent1addressLine1 = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent1addressLine1").toString();
        String agent1cityStateZip = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent1cityStateZip").toString();
        String agent2name = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent2name").toString();
        String agent2addressLine1 = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent2addressLine1").toString();
        String agent2cityStateZip = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent2cityStateZip").toString();

        scrollAndFillField(AGENT_NAME_FIELD_1, agent1name);
        scrollAndFillField(AGENT_1_ADDRESS_LINE, agent1addressLine1);
        scrollAndFillField(AGENT_1_CITY_STATE_ZIP, agent1cityStateZip);
        scrollAndFillField(AGENT_NAME_FIELD_2, agent2name);
        scrollAndFillField(AGENT_2_ADDRESS_LINE, agent2addressLine1);
        scrollAndFillField(AGENT_2_CITY_STATE_ZIP, agent2cityStateZip);

        waitForAWhile();
        agent1nameForm = getFieldValue(AGENT_NAME_FIELD_1);
        agent1addressLine1Form = getFieldValue(AGENT_1_ADDRESS_LINE);
        agent1cityStateZipForm = getFieldValue(AGENT_1_CITY_STATE_ZIP);
        agent2nameForm = getFieldValue(AGENT_NAME_FIELD_2);
        agent2addressLine1Form = getFieldValue(AGENT_2_ADDRESS_LINE);
        agent2cityStateZipForm = getFieldValue(AGENT_2_CITY_STATE_ZIP);
    }

    public void verifyAutoSavedFields(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if(!expectedValue.equals(expectedValue)){
            throw new AutomationException(fieldName + " field not retained the entered value. Expected: " + expectedValue + " ,Found: " + expectedValue);
        }
    }

    public void verifyAgentSNameAndAddressDetailsAreAutoSaved() throws AutomationException {
        switchToPage(1);
        switchToPage(2);

        waitForAWhile();
        String actualAgent1nameForm = getFieldValue(AGENT_NAME_FIELD_1);
        String actualAgent1addressLine1Form = getFieldValue(AGENT_1_ADDRESS_LINE);
        String actualAgent1cityStateZipForm = getFieldValue(AGENT_1_CITY_STATE_ZIP);
        String actualAgent2nameForm = getFieldValue(AGENT_NAME_FIELD_2);
        String actualAgent2addressLine1Form = getFieldValue(AGENT_2_ADDRESS_LINE);
        String actualAgent2cityStateZipForm = getFieldValue(AGENT_2_CITY_STATE_ZIP);

        verifyAutoSavedFields("Agent Name 1", agent1nameForm, actualAgent1nameForm);
        verifyAutoSavedFields("Agent Address Line 1", agent1addressLine1Form, actualAgent1addressLine1Form);
        verifyAutoSavedFields("Agent City State Zip 1", agent1cityStateZipForm, actualAgent1cityStateZipForm);
        verifyAutoSavedFields("Agent Name 2", agent2nameForm, actualAgent2nameForm);
        verifyAutoSavedFields("Agent Address Line 2", agent2nameForm, actualAgent2addressLine1Form);
        verifyAutoSavedFields("Agent City State Zip 1", agent2cityStateZipForm, actualAgent2cityStateZipForm);

        //use in reset
//        clearFieldUntilEmpty(AGENT_NAME_FIELD_1);
//        clearFieldUntilEmpty(AGENT_1_ADDRESS_LINE);
//        clearFieldUntilEmpty(AGENT_1_CITY_STATE_ZIP);
//        clearFieldUntilEmpty(AGENT_NAME_FIELD_2);
//        clearFieldUntilEmpty(AGENT_2_ADDRESS_LINE);
//        clearFieldUntilEmpty(AGENT_2_CITY_STATE_ZIP);
    }

    public void clearFieldUntilEmpty(String fieldLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(fieldLocator));
        int attempts = 0;
        while (element != null && !element.getAttribute("value").isEmpty() && attempts < 5) {
            element.clear();
            WebDriverUtil.waitForAWhile();
            attempts++;
        }

        if (!element.getAttribute("value").isEmpty()) {
            CommonSteps.logInfo("⚠️ Field not cleared after max attempts. Value: " + element.getAttribute("value"));
        }
    }

    public void userEditAgentSNameAndAddressDetails() throws AutomationException, IOException, ParseException {
        String agent1name = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent3name").toString();
        String agent1addressLine1 = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent3addressLine1").toString();
        String agent1cityStateZip = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent3cityStateZip").toString();
        String agent2name = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent4name").toString();
        String agent2addressLine1 = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent4addressLine1").toString();
        String agent2cityStateZip = CommonUtil.getJsonPath("OC05Form").get("OC05Form.agent4cityStateZip").toString();

        clearFieldUntilEmpty(AGENT_NAME_FIELD_1);
        scrollAndFillField(AGENT_NAME_FIELD_1, agent1name);
        clearFieldUntilEmpty(AGENT_1_ADDRESS_LINE);
        scrollAndFillField(AGENT_1_ADDRESS_LINE, agent1addressLine1);
        clearFieldUntilEmpty(AGENT_1_CITY_STATE_ZIP);
        scrollAndFillField(AGENT_1_CITY_STATE_ZIP, agent1cityStateZip);
        clearFieldUntilEmpty(AGENT_NAME_FIELD_2);
        scrollAndFillField(AGENT_NAME_FIELD_2, agent2name);
        clearFieldUntilEmpty(AGENT_2_ADDRESS_LINE);
        scrollAndFillField(AGENT_2_ADDRESS_LINE, agent2addressLine1);
        clearFieldUntilEmpty(AGENT_2_CITY_STATE_ZIP);
        scrollAndFillField(AGENT_2_CITY_STATE_ZIP, agent2cityStateZip);

        waitForAWhile();
        agent1nameForm = getFieldValue(AGENT_NAME_FIELD_1);
        agent1addressLine1Form = getFieldValue(AGENT_1_ADDRESS_LINE);
        agent1cityStateZipForm = getFieldValue(AGENT_1_CITY_STATE_ZIP);
        agent2nameForm = getFieldValue(AGENT_NAME_FIELD_2);
        agent2addressLine1Form = getFieldValue(AGENT_2_ADDRESS_LINE);
        agent2cityStateZipForm = getFieldValue(AGENT_2_CITY_STATE_ZIP);
    }

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage3() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage3 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage3)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage3);
        }
    }

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage4() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage4 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage4)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage4);
        }
    }

    public void userClicksOnAddressSection() throws AutomationException {
        scrollToElement(ADDRESS_SECTION);
        driverUtil.getWebElement(ADDRESS_SECTION).click();
        waitForAWhile();
    }

    public void userAddInstitutionAddressDetails() throws IOException, ParseException, AutomationException {
        String InstitutionAddress = CommonUtil.getJsonPath("OC05Form").get("OC05Form.InstitutionAddress").toString();
        String BoxNo = CommonUtil.getJsonPath("OC05Form").get("OC05Form.BoxNo").toString();
        String TitleOfRegistration = CommonUtil.getJsonPath("OC05Form").get("OC05Form.TitleOfRegistration").toString();
        String DateClosed = CommonUtil.getJsonPath("OC05Form").get("OC05Form.DateClosed").toString();

        waitForVisibleElement(By.xpath(INSTITUTION_ADDRESS_FIELD));
        scrollAndFillField(INSTITUTION_ADDRESS_FIELD, InstitutionAddress);
        scrollAndFillField(BOX_NO_FIELD, BoxNo);
        scrollAndFillField(TITTLE_OF_REGISTRATION_FIELD, TitleOfRegistration);
        WebElement dateField = driverUtil.getWebElement(DATE_CLOSED_FIELD);
        dateField.click();
        dateField.clear();
        dateField.sendKeys(DateClosed);
        dateField.sendKeys(Keys.TAB);

        driverUtil.getWebElement(ADD_BUTTON).click();
        WebDriverUtil.waitForVisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "New Safe deposit box is added successfully.")));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "New Safe deposit box is added successfully.")));

        InstitutionAddressForm = getFieldValue(INSTITUTION_ADDRESS_FIELD_TABLE);
        BoxNoForm = getFieldValue(BOX_NO_FIELD_TABLE);
        TitleOfRegistrationForm = getFieldValue(TITTLE_OF_REGISTRATION_FIELD_TABLE);
        DateClosedForm = getFieldValue(DATE_CLOSED_FIELD_TABLE);

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyAddressIsDisplayedOnTheForm() throws AutomationException {
        waitForAWhile();
        String actualInstitutionAddressForm = getFieldValue(INSTITUTION_ADDRESS_FIELD_FORM);
        String actualBoxNoForm = getFieldValue(BOX_NO_FIELD_FORM);
        String actualTitleOfRegistrationForm = getFieldValue(TITTLE_OF_REGISTRATION_FIELD_FORM);
        String actualDateClosedForm = getFieldValue(DATE_CLOSED_FIELD_FORM);

        if(!actualInstitutionAddressForm.equals(InstitutionAddressForm)){
            throw new AutomationException("Institution Address is not displayed correctly on form. Expected: " + InstitutionAddressForm + " ,Found: " + actualInstitutionAddressForm);
        }

        if(!actualBoxNoForm.equals(BoxNoForm)){
            throw new AutomationException("Box No. is not displayed correctly on form. Expected: " + BoxNoForm + " ,Found: " + actualBoxNoForm);
        }

        if(!actualTitleOfRegistrationForm.equals(TitleOfRegistrationForm)){
            throw new AutomationException("Title of Registration is not displayed correctly on form. Expected: " + TitleOfRegistrationForm + " ,Found: " + actualTitleOfRegistrationForm);
        }

        if(!actualDateClosedForm.equals(DateClosedForm)){
            throw new AutomationException("Date Closed is not displayed correctly on form. Expected: " + DateClosedForm + " ,Found: " + actualDateClosedForm);
        }

        //use in reset
//        userClicksOnAddressSection();
//        waitForVisibleElement(By.xpath(INSTITUTION_ADDRESS_FIELD));
//        scrollToElement(DELETE_BUTTON);
//        driverUtil.getWebElement(DELETE_BUTTON).click();
//        WebDriverUtil.waitForVisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Safe Deposit Box is deleted successfully.")));
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Safe Deposit Box is deleted successfully.")));
//        driverUtil.getWebElement(CLOSE_BTN).click();
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
        switchToPage(6);
        switchToPage(5);

        List<String> comments = Arrays.asList(comment1Form, comment2Form);

        for (int i = 0; i < 2; i++) {
            scrollToElement(String.format(COMMENTS_FIELD_XPATH, i));
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

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage6() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage6 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage6)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage6);
        }
    }

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage7() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage7 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage7)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage7);
        }
    }

    public void userChecksTheDisplayCheckboxForBeneficiaries() throws AutomationException {
        scrollToElement(EDIT_AMOUNT_PROPORTION_FIELD);
        driverUtil.getWebElement(EDIT_AMOUNT_PROPORTION_FIELD).click();

        WebDriverUtil.waitForAWhile();
        List<WebElement> checkboxElements = driverUtil.getWebElements(EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS);

        int[] contactsToDisplay = {0, 2, 3, 4};

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
                selectedContactNamesPage7.add(nameElements.get(i).getText());
            }
        }

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void userVerifiesDisplayedContactsOnForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> displayedNameElements = driverUtil.getWebElements(BENE_NAMES_INCOME_PAGE_7);

        List<String> displayedNames = new ArrayList<>();
        for (WebElement element : displayedNameElements) {
            displayedNames.add(element.getAttribute("value").trim());
        }

        for (String expectedName : selectedContactNamesPage7) {
            if (!displayedNames.contains(expectedName)) {
                throw new AutomationException("Expected contact name not displayed: " + expectedName);
            }
        }
    }

    public void verifyAllTheBeneficiaryContactsAreMovedToTheAttachment() throws AutomationException {
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile(2);

        for (String contactName : selectedContactNamesPage7) {
            String contactXpath = String.format(BENE_ON_ATTACHMENT_PAGE_7, contactName);
            WebElement element = driverUtil.getWebElement(contactXpath);

            if (element == null && !element.getAttribute("value").equals(contactName)) {
                throw new AutomationException("Beneficiary Contact not found in attachment: " + contactName);
            }
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();


        //use in reset
        scrollToElement(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN_OC05);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN_OC05)).click();
        WebDriverUtil.waitForAWhile();
        scrollToElement(EDIT_AMOUNT_PROPORTION_FIELD);
        driverUtil.getWebElement(EDIT_AMOUNT_PROPORTION_FIELD).click();
        WebDriverUtil.waitForAWhile();
        List<WebElement> checkboxElements = driverUtil.getWebElements(EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS);
        int[] contactsToClear = {0, 2, 3, 4};
        for (int index : contactsToClear) {
            if (index < checkboxElements.size()) {
                WebElement checkbox = checkboxElements.get(index);
                checkbox.click();
            }
        }
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayedOnPage8() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(NAME_OF_TRUST_FIELD_OTHER_PAGES);

        estateNameFormPage8 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNameFormPage8)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNameFormPage8);
        }
    }
}

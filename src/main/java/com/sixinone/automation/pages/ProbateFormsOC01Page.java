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
import java.util.List;
import java.util.stream.Collectors;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsOC01Page extends BasePage {
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
    private static final String HEADER_COUNTY_NAME = "//div[@class='county-title']//span[@class='county-name']";
    private static final String ESTATE_NAME = "//p//span[contains(text(),'ESTATE OF')]/following-sibling::input";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String NAME_OF_COUNSEL_FIELD = "//p//span[text()='Name of Counsel:']/following-sibling::span//input";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String PROCEED_BTN = "//button[text()='Proceed']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String NAME_OF_LAW_FIRM_FIELD = "//p//span[text()='Name of Law Firm:']/following-sibling::span//input";
    private static final String TELEPHONE_FIELD = "//p//span[text()='Telephone:']/following-sibling::span//input";
    private static final String FAX_FIELD = "//p//span[text()='Fax:']/following-sibling::span//input";
    private static final String EMAIL_FIELD = "//p//span[text()='Email:']/following-sibling::span//input";
    private static final String ADDRESS_LINE_1_FIELD = "//p//span[text()='Address:']/following-sibling::span//input";
    private static final String ADDRESS_LINE_2_FIELD = "//span[text()='Address:']/ancestor::p/following-sibling::p//span//input";
    private static final String SIGN_OF_COUNSEL_PAGE_11 = "//div[text()='Signature of Counsel for Petitioner']//span//input";
    private static final String ESTATE_NAME_PAGE_2 = "//p[contains(text(),'Estate of')]//input";
    private static final String DECEDENT_DIED_ON_FIELD = "//td//p[contains(text(),'Decedent died on')]/following::td//p//span//input";
    private static final String PETITIONER_NAME_FIELD = "//td[@class='tr5 td9']//input";
    private static final String PETITIONER_NAME_FIELD_2 = "//td[@class='tr5 td10']//input";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String ACCEPT_BTN = "//button[text()='Accept']";
    private static final String VIEW_ATTACHMENT_BTN = "//span[contains(@class,'view-attachment')]";
    private static final String PETITIONER_MODAL_INPUT_XPATH = "//div[@class='modal-body']//input[@type='text' and @value='%s']";
    private static final String PETITIONER_INPUT_XPATH = "//input[@type='text' and @value='%s']";
    private static final String MODAL_CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String DROPPED_CONTACTS_XPATH = "//div[@class='drag-names-list drop-box h-100']//div//div";
    private static final String FORM_CODICIL_DATE_1 = "//input[@name='codicilDate1']";
    private static final String FORM_CODICIL_DATE_2 = "//input[@name='codicilDate2']";
    private static final String FORM_CODICIL_DATE_3 = "//input[@name='codicilDate3']";
    private static final String DATE_OF_WILL_FORM = "//input[@name='dateOfWill']";
    private static final String DECEDENT_TAB = "//span[text()='Decedent']";
    private static final String ESTATE_NAME_PAGE_3 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_NAME_PAGE_6 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_NAME_PAGE_7 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_NAME_PAGE_8 = "//p[contains(text(),'Estate of')]//input";
    private static final String DATE_FIELDS_PAGE_3 = "//input[@name='childrenDetails[%s].dateOfBirth']";
    private static final String CHILDREN_DETAILS_FIELDS_PAGE_3 = "//input[@name='childrenDetails[%s].name']";
    private static final String NAME_OF_TRUST_PAGE_4 = "//p[contains(text(),'Estate of')]//input";
    private static final String NAME_OF_TRUST_PAGE_5 = "//p[contains(text(),'Estate of')]//input";
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String BENE_DETAILS_FORM = "//p//div";
    private static final String BENE_DETAILS_ATTACHMENT = "//div[@class='attachment-css border-0']//tr/td[textarea[contains(text(), ',')]]";
    private static final String CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllBenyOnAttachment']";
    private static final String DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllIncomeOnAttachment']";
    private static final String MAIN_COUNT = "//div[@class='main_count blue-text']//span";
    private static final String ATTACHMENT_COUNT = "//div[@class='attached_count blue-text']//b";
    private static final String ESTATE_NAME_PAGE_9 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_NAME_PAGE_10 = "//p[contains(text(),'Estate of')]//input";
    private static final String ESTATE_NAME_PAGE_11 = "//p[contains(text(),'Estate of')]//input";
    private static final String LITIGATION_YES_OPTION = "//input[@name='wasDecedentAPartyInLitigation' and @value='1']";
    private static final String LITIGATION_TEXT_FIELD = "//textarea[@name='litigationDetails']";
    private static final String FIDUCIARY_STATUS_YES_OPTION = "//input[@name='wasDecedentAFiduciaryOnDod' and @value='1']";
    private static final String FIDUCIARY_TEXT_FIELD = "//textarea[@name='nameOfTheEstate']";
    private static final String INITIALS_FIELD = "//input[@name='beneficiaries[0].initials']";
    private static final String COMMENTS_FIELD_XPATH = "//textarea[@name='beneficiaries[%s].comments']";
    private static final String BENE_RELATIONSHIP_FORM = "//td[@class='borderNewLeft2']//p[@class='p8-1 ft27-2 newstyle']//input";
    private static final String BENE_INTEREST_FORM = "//td[@class='borderNewLeft2 borderNewBottom2']//p[@class='p8-1 ft27-2 newstyle']//textarea";
    private static final String BENE_RELATIONSHIP_ATTACHMENT = "//div[@class='modal-body']//table/tbody/tr/td[position()=3]/textarea";
    private static final String BENE_INTEREST_ATTACHMENT = "//div[@class='modal-body']//table/tbody/tr/td[position()=4]/textarea";
    private static final String CLAIMANT_INITIALS_FIELD = "//input[@class='yellowbg no-bords']";
    private static final String ADD_NEW_CLAIMANT_BTN = "//button[text()='Add New Claimant']";
    private static final String INITIALS_MODAL_FIELD = "//div[@class='modal-body']//input[@name='initials']";
    private static final String CLAIMANT_NAME_FIELD = "//div[@class='modal-body']//input[@name='name']";
    private static final String CLAIMANT_ADDRESS_FIELD = "//div[@class='modal-body']//input[@name='address']";
    private static final String CLAIM_AMOUNT_FIELD = "//div[@class='modal-body']//input[@name='claimAmount']";
    private static final String CLAIM_ADMITTED_YES_OPT = "//div[@class='modal-body']//input[@id='isClaimAdmitted_1']";
    private static final String CLAIM_ADMITTED_NO_OPT = "//div[@class='modal-body']//input[@id='isClaimAdmitted_0']";
    private static final String CLAIM_PAID_YES_OPT = "//div[@class='modal-body']//input[@id='willClaimPaidInFull_1']";
    private static final String CLAIM_PAID_NO_OPT = "//div[@class='modal-body']//input[@id='willClaimPaidInFull_0']";
    private static final String ADD_BUTTON = "//div[@class='modal-body']//button[text()='Add']";
    private static final String DELETE_BUTTON = "//div[@class='modal-body']//button[text()='Delete']";
    private static final String FAMILY_EXEMPTION_CLAIMED_YES_OPT = "//input[@name='wasFamilyExemptionClaimed' and @value='1']";
    private static final String FAMILY_EXEMPTION_ALLOWED_YES_OPT = "//input[@name='wasFamilyExemptionAllowed' and @value='1']";
    private static final String FAMILY_EXEMPTION_FIELD = "//p[@class='p50 ft47']/ancestor::td/preceding-sibling::td//p//input";
    private static final String PA_DATE_FIELDS = "//input[@name='paInheritanceTaxPaids[%s].date']";
    private static final String PA_PAYMENT_FIELDS = "//input[@name='paInheritanceTaxPaids[%s].payment']";
    private static final String PA_INTEREST_FIELDS = "//input[@name='paInheritanceTaxPaids[%s].interest']";
    private static final String RAD_DATE_FIELDS = "//input[@name='additionalRADs[%s].date']";
    private static final String RAD_DESCRIPTION_FIELDS = "//input[@name='additionalRADs[%s].description']";
    private static final String RAD_AMOUNT_FIELDS = "//input[@name='additionalRADs[%s].payment']";
    private static final String RESERVE_REQUEST_AMOUNT = "//input[@name='reserveRequestedAmount']";
    private static final String SIGN_OF_PETITIONER_PAGE_11 = "//div[contains(text(),'Signature of Petitioner')]//input[@id='fullname']";
    private static final String SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_11 = "//div[@class='modal-content']//div[contains(text(),'Signature of')]//span//*[self::input or self::textarea]";
    private static final String SAVE_BTN = "//button[text()='Save']";
    private static final String NOTIFICATION_DELETE_BUTTON = "//button[@class='btn btn-danger' and text()='Delete']";
    private static final String CLAIMANT_INITIALS_ERR_MSG = "//div[@class='invalid-feedback' and text()='Initials are required when Name and Address are not provided.']";
    private static final String CLAIMANT_NAME_ERR_MSG = "//div[@class='invalid-feedback' and text()='Name is required when Initials are not provided.']";
    private static final String CLAIMANT_ADDRESS_ERR_MSG = "//div[@class='invalid-feedback' and text()='Address is required when Initials are not provided.']";
    private static final String CLAIMANT_INITIALS_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='1']";
    private static final String CLAIMANT_NAME_ADDRESS_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='2']";
    private static final String CLAIMANT_AMOUNT_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='3']";
    private static final String CLAIM_ADMITTED_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='4']";
    private static final String CLAIM_PAID_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='5']";
    private static final String CLAIMANT_INITIALS_FORM_LIST = "//input[contains(@class,'no-bords')]";
    private static final String CLAIMANT_AMOUNT_FORM = "//td//p//input[contains(@id,'claimed_amount') and not(contains(@class,'bold'))]";
    private static final String ABOVE_ATTACHMENT_TOTAL_FIELDS = "//td//p//input[contains(@id,'claimed_amount') and @class='noneditable bold']";
    private static final String CLAIMANT_INITIALS_ATTACHMENT_LIST = "//div[@class='modal-body']//td[position()='1']//p[@class='ft-1']";
    private static final String CLAIMANT_AMOUNT_ATTACHMENT = "//div[@class='modal-body']//td[position()='3']//p[@class='ft-1']";
    private static final String EDIT_AMOUNT_PROPORTION_FIELD = "//p[@class='p0-3 ft12 newstyle position-relative']//input[@class='yellowbg bold']";
    private static final String EDIT_AMOUNT_PROPORTION_NAME_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='2']";
    private static final String EDIT_AMOUNT_PROPORTION_DISPLAY_CHECKBOX_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='3']//input[@type='checkbox']";
    private static final String PROPORTION_COLUMNS = "//div[@class='modal-body']//tbody//tr//td[position()='5']//input[@type='text']";
    private static final String BENE_NAMES_INCOME_PAGE_10 = "//p[@class='p0-3 ft12 newstyle position-relative']//input[@value and normalize-space(@value)]";
    private static final String BENE_PROPORTION_INCOME_PAGE_10 = "//p[@class='p0-3 ft12 newstyle']//input[@value and normalize-space(@value)]";
    private static final String BENE_ON_ATTACHMENT_PAGE_10 = "//div[@class='modal-body']//tr//td//p//input[@class='ft-1 bold' and @value='%s']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input";
    private static final String CONTACT_NAME_IN_ESTATE_CONTACT = "//td[@aria-colindex='1' and text()='%s']";
    private static final String ESTATE_SPECIFIC_FIELDS_TAB = "//div[@class='nav-item']/a[text()='Estate-Specific Fields']";
    private static final String ESTATE_SPECIFIC_SELECT_ROLE_BTN = "//button[text()='Select Role']";
    private static final String ROLE_CHECKBOX = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input[not(@checked)]";
    private static final String ROLE_UNCHECK = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String ROLE_SAVE_BTN = "//div[@class='fade custom-modal center-small-modal modal show']//button[text()='Save']";
    private static final String CONTACT_USED_IN_OC01_FORM_NOTIFICATION = "//div[@class='modal-inner-content']//li[text()='ProbateOC01']";
    private static final String NO_ROLES_SAVE_BTN = "//button[@class='btn btn-danger' and text()='Save']";
    private static final String SELECT_RELATIONSHIP_BTN = "//button[@aria-label='Select Relationship']";
    private static final String RELATIONSHIP_OPTION = "//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String SHARE_OF_ESTATE_IN_WORDS = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='4']//input";
    private static final String AMOUNT_OF_ESTATE = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='5']//input";
    private static final String BENEFICIAL_INTEREST = "//tr//td//span[contains(text(),'%s')]/ancestor::td/ancestor::tr//td[position()='6']//input";
    private static final String BENY_WORKSHEET_TAB = "//a//span[text()='Beny Worksheet']";
    private static final String SELECTED_PETITIONER_NAMES = "//div[@class='drag-names-list drop-box h-100']//div//div//span";
    private static final String PETITIONER_1_ADDRESS_LINE = "//td[@class='trnew10 td11']//span[@class='p0 ft12 newstyle']//input";
    private static final String PETITIONER_1_CITY_STATE_ZIP = "//td[@class='trnew10 td11']//span[@class='p0 ft0 newstyle']//input";
    private static final String PETITIONER_2_ADDRESS_LINE = "//td[@class='trnew10 td12']//span[@class='p0 ft12 newstyle']//input[not(contains(@value,','))]";
    private static final String PETITIONER_2_CITY_STATE_ZIP = "//td[@class='trnew10 td12']//span[@class='p0 ft12 newstyle']//input[contains(@value,',')]";
    private static final String ADDITION_PETITIONER_ADDRESS = "//div[@class='modal-body']//tbody//tr//td//p[contains(text(),'Address')]/ancestor::td/following-sibling::td//input";

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    static String downloadedFileName;

    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneRelationship = new ArrayList<>();
    private static final List<String> beneInterest = new ArrayList<>();
    private static final List<String> claimantInitialsForm = new ArrayList<>();
    private static final List<String> claimantNameForm = new ArrayList<>();
    private static final List<String> claimantAddressForm = new ArrayList<>();
    private static final List<String> claimAmountForm = new ArrayList<>();
    private static final List<String> claimAdmittedForm = new ArrayList<>();
    private static final List<String> claimPaidForm = new ArrayList<>();
    private static final List<String> mainTableClaimAmount = new ArrayList<>();
    private static final List<String> attachmentClaimAmount = new ArrayList<>();
    private static final List<String> selectedContactNamesPage10 = new ArrayList<>();
    private static final List<String> beneDetailsAfterRoleRemoved = new ArrayList<>();
    private static final List<String> beneficiaryKeys = new ArrayList<>();
    private static final List<String> selectedPetitioner = new ArrayList<>();
    private static final List<String> Fiduciaries = new ArrayList<>();
    private static final List<String> corporateFiduciaries = new ArrayList<>();

    static String[] enteredProportionValues;

    static String countyNameForm;
    static String estateNameForm;
    static String fileNumberForm;
    static String selectedAttorney;
    static String nameOfCounselForm;
    static String nameOfLawFirmForm;
    static String attorneyTelephoneForm;
    static String attorneyFaxForm;
    static String attorneyEmailForm;
    static String attorneyAddressLine1Form;
    static String attorneyAddressLine2Form;
    static String signOfCounselPage11Form;
    static String estateNamePage2Form;
    static String decedentDiedOnForm;
    static String Fiduciary1Form;
    static String Fiduciary2Form;
    static String Fiduciary3Form;
    static String Fiduciary4Form;
    static String nameOfPetitionerForm;
    static String nameOfPetitioner2Form;
    static String petitioner1AddressLineForm;
    static String petitioner2AddressLineForm;
    static String petitioner1AddressLine1Form;
    static String petitioner1CityStateCodeZipForm;
    static String petitioner2AddressLine1Form;
    static String petitioner2CityStateCodeZipForm;
    static String petitioner3AddressLine1Form;
    static String petitioner3CityStateCodeZipForm;
    static String petitioner4AddressLine1Form;
    static String petitioner4CityStateCodeZipForm;
    static String dateOfWillForm;
    static String codicilDate1Form;
    static String codicilDate2Form;
    static String codicilDate3Form;
    static String estateNameFormPage3;
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
    static String childrenDetailDataForm1;
    static String childrenDetailDataForm2;
    static String childrenDetailDataForm3;
    static String childrenDetailDataForm4;
    static String childrenDetailDataForm5;
    static String childrenDetailDataForm6;
    static String childrenDetailDataForm7;
    static String childrenDetailDataForm8;
    static String childrenDetailDataForm9;
    static String childrenDetailDataForm10;
    static int expectedAttachmentCountForm;
    static String estateNameFormPage6;
    static String estateNameFormPage7;
    static String estateNameFormPage8;
    static String estateNameFormPage9;
    static String estateNameFormPage10;
    static String estateNameFormPage11;
    static String comment1Form;
    static String comment2Form;
    static String paDateForm1;
    static String paDateForm2;
    static String paDateForm3;
    static String paDateForm4;
    static String paDateForm5;
    static String paDateForm6;
    static String paDateForm7;
    static String paDateForm8;
    static String paPaymentForm1;
    static String paPaymentForm2;
    static String paPaymentForm3;
    static String paPaymentForm4;
    static String paPaymentForm5;
    static String paPaymentForm6;
    static String paPaymentForm7;
    static String paPaymentForm8;
    static String paInterestForm1;
    static String paInterestForm2;
    static String paInterestForm3;
    static String paInterestForm4;
    static String paInterestForm5;
    static String paInterestForm6;
    static String paInterestForm7;
    static String paInterestForm8;
    static String radDateForm1;
    static String radDateForm2;
    static String radDateForm3;
    static String radDateForm4;
    static String radDateForm5;
    static String radDateForm6;
    static String radDateForm7;
    static String radDateForm8;
    static String radDateForm9;
    static String radDescriptionForm1;
    static String radDescriptionForm2;
    static String radDescriptionForm3;
    static String radDescriptionForm4;
    static String radDescriptionForm5;
    static String radDescriptionForm6;
    static String radDescriptionForm7;
    static String radDescriptionForm8;
    static String radDescriptionForm9;
    static String radAmountForm1;
    static String radAmountForm2;
    static String radAmountForm3;
    static String radAmountForm4;
    static String radAmountForm5;
    static String radAmountForm6;
    static String radAmountForm7;
    static String radAmountForm8;
    static String radAmountForm9;
    static String reserveRequestAmountForm;
    static String nameOfCorporateFiduciary;
    static String corporateFiduciaryFullNameForm;
    static String nameOFPetitionerPage10Form;
    static String Initials1Form;
    static String Name1Form;
    static String Address1Form;
    static String Amount_of_Claim1Form;
    static String Claim_Admitted1Form;
    static String Will_Claim_Be_Paid_In_Full1Form;
    static String Initials2Form;
    static String Name2Form;
    static String Address2Form;
    static String Amount_of_Claim2Form;
    static String Claim_Admitted2Form;
    static String Will_Claim_Be_Paid_In_Full2Form;
    static String Initials3Form;
    static String Name3Form;
    static String Address3Form;
    static String Amount_of_Claim3Form;
    static String Claim_Admitted3Form;
    static String Will_Claim_Be_Paid_In_Full3Form;
    static String Initials4Form;
    static String Name4Form;
    static String Address4Form;
    static String Amount_of_Claim4Form;
    static String Claim_Admitted4Form;
    static String Will_Claim_Be_Paid_In_Full4Form;
    static String Initials5Form;
    static String Name5Form;
    static String Address5Form;
    static String Amount_of_Claim5Form;
    static String Claim_Admitted5Form;
    static String Will_Claim_Be_Paid_In_Full5Form;
    static String aboveAmountForm;
    static String attachmentAmountForm;
    static String totalAmountForm;
    static String initialFileNumber;

    public ProbateFormsOC01Page() throws IOException, ParseException {
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

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement field = driverUtil.getWebElement(fieldLocator);

        if (field.isEnabled() && field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable: " + fieldLocator);
        } else {
            CommonSteps.logInfo("Field is not editable: " + fieldLocator);
        }
    }

    public void verifyEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        WebElement countyNameField = driverUtil.getWebElement(HEADER_COUNTY_NAME);
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME);

        countyNameForm = countyNameField.getText();
        estateNameForm = estateNameField.getAttribute("value");

        String enteredCountyName = getEstateValue("DomicileCountry").toUpperCase();
        String enteredEstateName = getEstateValue("DisplayName");

        if (!enteredCountyName.equals(countyNameForm)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm);
        }

        if (!enteredEstateName.equals(estateNameForm)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameForm);
        }

        String contentEditable = countyNameField.getAttribute("contenteditable");
        if ("true".equalsIgnoreCase(contentEditable)) {
            throw new AutomationException("County name field is editable.");
        }

        verifyFieldIsNotEditable(ESTATE_NAME);
    }

    public void verifyFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        if (!fileNumberField.isEnabled()) {
            throw new AutomationException("File number field is not editable.");
        }

        initialFileNumber = fileNumberField.getAttribute("value");
        String newFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();

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

    public void verifyAttorneyDetailsAreSyncedWithPageOfSameForm() throws AutomationException {
        WebElement signOfCounselField = driverUtil.getWebElement(SIGN_OF_COUNSEL_PAGE_11);
        signOfCounselPage11Form = signOfCounselField.getAttribute("value");

        if (!selectedAttorney.equals(signOfCounselPage11Form)) {
            throw new AutomationException("Attorney details are not synced with page 11. Expected: " + selectedAttorney + " ,Found: " + signOfCounselPage11Form);
        }
    }

    public void verifyEstateNameAndDateOfDeathFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_2);
        WebElement decedentDiedOnField = driverUtil.getWebElement(DECEDENT_DIED_ON_FIELD);
        scrollToElement(ESTATE_NAME_PAGE_2);

        estateNamePage2Form = estateNameField.getAttribute("value");
        decedentDiedOnForm = decedentDiedOnField.getAttribute("value");

        String enteredEstateName = getEstateValue("DisplayName");
        String enteredDateOfDeathName = getEstateValue("DateOfDeath");

        if (!enteredEstateName.equals(estateNamePage2Form)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNamePage2Form);
        }

        if (!enteredDateOfDeathName.equals(decedentDiedOnForm)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredDateOfDeathName + " ,Found: " + decedentDiedOnForm);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_2);
        verifyFieldIsNotEditable(DECEDENT_DIED_ON_FIELD);
    }

    public void userClickOnPetitionerNameField() throws AutomationException {
        scrollToElement(PETITIONER_NAME_FIELD);
        driverUtil.getWebElement(PETITIONER_NAME_FIELD).click();
    }

    public void verifySidebarIsOpensAndFiduciaryContactsCanBeSelected() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Select Contact")) {
            throw new AutomationException("Sidebar is not displayed for fiduciary contact.");
        }

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        waitForVisibleElement(By.xpath(DRAG_CONTACT_XPATH));
        Fiduciary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();

        CommonSteps.takeScreenshot();

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


    public void verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm() throws AutomationException, IOException, ParseException {
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

    public void userSelectsMultipleFiduciaryContacts() throws AutomationException {
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

        verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm();

        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        verifyPetitionerOnAttachment(Fiduciary3Form);
        verifyPetitionerOnAttachment(petitioner3AddressLine1);
        verifyPetitionerOnAttachment(petitioner3CityStateCodeZip);

        verifyPetitionerOnAttachment(Fiduciary4Form);
        verifyPetitionerOnAttachment(petitioner4AddressLine1);
        verifyPetitionerOnAttachment(petitioner4CityStateCodeZip);

        CommonSteps.takeScreenshot();

        List<WebElement> additionalPetitonerAddress = driverUtil.getWebElements(ADDITION_PETITIONER_ADDRESS);

        for (int i = 0; i < additionalPetitonerAddress.size(); i++) {
            String value = additionalPetitonerAddress.get(i).getAttribute("value");
            switch (i) {
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

    public void userSwapTheSelectedFiduciaryContacts() throws AutomationException {
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

        petitioner1AddressLine1Form = driverUtil.getWebElement(PETITIONER_1_ADDRESS_LINE).getAttribute("value");
        petitioner1CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_1_CITY_STATE_ZIP).getAttribute("value");
        petitioner2AddressLine1Form = driverUtil.getWebElement(PETITIONER_2_ADDRESS_LINE).getAttribute("value");
        petitioner2CityStateCodeZipForm = driverUtil.getWebElement(PETITIONER_2_CITY_STATE_ZIP).getAttribute("value");

        petitioner1AddressLineForm = petitioner1AddressLine1Form + " " + petitioner1CityStateCodeZipForm;
        petitioner2AddressLineForm = petitioner2AddressLine1Form + " " + petitioner2CityStateCodeZipForm;
    }

    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void userModifiesTheDateOfWillAndDateOfCodicilFields() throws AutomationException, IOException, ParseException {
        String dateOfWill = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateOfWill").toString();
        String codicilDate1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.codicilDate1").toString();
        String codicilDate2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.codicilDate2").toString();
        String codicilDate3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.codicilDate3").toString();

        WebElement dateOfWillField = driverUtil.getWebElement(DATE_OF_WILL_FORM);
        WebElement codicilDate1Field = driverUtil.getWebElement(FORM_CODICIL_DATE_1);
        WebElement codicilDate2Field = driverUtil.getWebElement(FORM_CODICIL_DATE_2);
        WebElement codicilDate3Field = driverUtil.getWebElement(FORM_CODICIL_DATE_3);

        scrollToElement(DATE_OF_WILL_FORM);

        clearField(DATE_OF_WILL_FORM);
        dateOfWillField.sendKeys(dateOfWill);
        clearField(FORM_CODICIL_DATE_1);
        codicilDate1Field.sendKeys(codicilDate1);
        clearField(FORM_CODICIL_DATE_2);
        codicilDate2Field.sendKeys(codicilDate2);
        clearField(FORM_CODICIL_DATE_3);
        codicilDate3Field.sendKeys(codicilDate3);
        codicilDate3Field.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();

        dateOfWillForm = dateOfWillField.getAttribute("value");
        codicilDate1Form = codicilDate1Field.getAttribute("value");
        codicilDate2Form = codicilDate2Field.getAttribute("value");
        codicilDate3Form = codicilDate3Field.getAttribute("value");
    }

    public void verifyUpdatedDatesAreReflectedInTheEstateRecord() throws AutomationException {
        waitForVisibleElement(By.xpath(DECEDENT_TAB));
        driverUtil.getWebElement(DECEDENT_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        if (!driverUtil.getWebElement(DATE_OF_WILL).getAttribute("value").equals(dateOfWillForm)) {
            throw new AutomationException("Updated date of will is not reflected");
        }

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

    public void verifyDecedentSNameIsDisplayedAndIsNonEditable() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_3);
        scrollToElement(ESTATE_NAME_PAGE_3);

        estateNameFormPage3 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage3)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage3);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_3);
    }

    public static void fillFieldUntilValueHolds(WebElement element, String expectedValue, int maxRetries) throws AutomationException {
        int retryCount = 0;
        while (retryCount < maxRetries) {
            element.clear();
            element.sendKeys(expectedValue);
            element.sendKeys(Keys.TAB);
            WebDriverUtil.waitForAWhile();

            String actualValue = element.getAttribute("value");
            if (expectedValue.equals(actualValue)) {
                break;
            }
            retryCount++;
        }

        if (retryCount == maxRetries) {
            throw new AutomationException("Failed to set value after " + maxRetries + " attempts. Expected: " + expectedValue);
        }
    }


    public void verifyMultipleChildrenAndDoBCanBeAdded() throws AutomationException, IOException, ParseException {
        String dateData1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm1").toString();
        String dateData2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm2").toString();
        String dateData3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm3").toString();
        String dateData4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm4").toString();
        String dateData5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm5").toString();
        String dateData6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm6").toString();
        String dateData7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm7").toString();
        String dateData8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm8").toString();
        String dateData9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm9").toString();
        String dateData10 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm10").toString();

        String childrenDetailData1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm1").toString();
        String childrenDetailData2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm2").toString();
        String childrenDetailData3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm3").toString();
        String childrenDetailData4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm4").toString();
        String childrenDetailData5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm5").toString();
        String childrenDetailData6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm6").toString();
        String childrenDetailData7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm7").toString();
        String childrenDetailData8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm8").toString();
        String childrenDetailData9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm9").toString();
        String childrenDetailData10 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm10").toString();

        List<String> dateDataForm = Arrays.asList(
                dateData1, dateData2, dateData3, dateData4, dateData5,
                dateData6, dateData7, dateData8, dateData9, dateData10
        );

        List<String> childrenDetailDataForm = Arrays.asList(
                childrenDetailData1, childrenDetailData2, childrenDetailData3, childrenDetailData4, childrenDetailData5,
                childrenDetailData6, childrenDetailData7, childrenDetailData8, childrenDetailData9, childrenDetailData10
        );

        for (int i = 0; i < 10; i++) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
            WebElement childrenDetailField = driverUtil.getWebElementAndScroll(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
//            childrenDetailField.sendKeys(childrenDetailDataForm.get(i));
//            childrenDetailField.sendKeys(Keys.TAB);
            fillFieldUntilValueHolds(childrenDetailField, childrenDetailDataForm.get(i), 3);

            WebDriverUtil.waitForAWhile();
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i)));
//            dateField.click();
//            dateField.clear();
//            dateField.sendKeys(dateDataForm.get(i));
//            dateField.sendKeys(Keys.TAB);
            fillFieldUntilValueHolds(dateField, dateDataForm.get(i), 3);

            WebDriverUtil.waitForAWhile();
            String actualChildrenDetail = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i))).getAttribute("value");
            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i))).getAttribute("value");

            if (!actualChildrenDetail.equals(childrenDetailDataForm.get(i))) {
                throw new AutomationException("Reason field did not accept the entered text correctly. Expected: " + childrenDetailDataForm.get(i) + ", Found: " + actualChildrenDetail);
            }

            if (!actualDate.equals(dateDataForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered date correctly. Expected: " + dateDataForm.get(i) + ", Found: " + actualDate);
            }

            switch (i) {
                case 0:
                    dateDataForm1 = actualDate;
                    childrenDetailDataForm1 = actualChildrenDetail;
                    break;
                case 1:
                    dateDataForm2 = actualDate;
                    childrenDetailDataForm2 = actualChildrenDetail;
                    break;
                case 2:
                    dateDataForm3 = actualDate;
                    childrenDetailDataForm3 = actualChildrenDetail;
                    break;
                case 3:
                    dateDataForm4 = actualDate;
                    childrenDetailDataForm4 = actualChildrenDetail;
                    break;
                case 4:
                    dateDataForm5 = actualDate;
                    childrenDetailDataForm5 = actualChildrenDetail;
                    break;
                case 5:
                    dateDataForm6 = actualDate;
                    childrenDetailDataForm6 = actualChildrenDetail;
                    break;
                case 6:
                    dateDataForm7 = actualDate;
                    childrenDetailDataForm7 = actualChildrenDetail;
                    break;
                case 7:
                    dateDataForm8 = actualDate;
                    childrenDetailDataForm8 = actualChildrenDetail;
                    break;
                case 8:
                    dateDataForm9 = actualDate;
                    childrenDetailDataForm9 = actualChildrenDetail;
                    break;
                case 9:
                    dateDataForm10 = actualDate;
                    childrenDetailDataForm10 = actualChildrenDetail;
                    break;
            }
        }
    }

    public void verifyNameOfTheTrustIsAutoFetchedFromPageOnPage4() throws AutomationException {
        WebElement nameOfTrustField = driverUtil.getWebElement(NAME_OF_TRUST_PAGE_4);
        scrollToElement(NAME_OF_TRUST_PAGE_4);
        String nameOfTrustPage4 = nameOfTrustField.getAttribute("value");

        if (!nameOfTrustPage4.equals(estateNamePage2Form)) {
            throw new AutomationException("Name of Trust from page 2 is not fetched correctly on page 4. Expected: " + estateNamePage2Form + " , Found: " + nameOfTrustPage4);
        }
    }

    public void verifyNameOfTheTrustIsAutoFetchedFromPageOnPage5() throws AutomationException {
        WebElement nameOfTrustField = driverUtil.getWebElement(NAME_OF_TRUST_PAGE_5);
        scrollToElement(NAME_OF_TRUST_PAGE_5);
        String nameOfTrustPage5 = nameOfTrustField.getAttribute("value");

        if (!nameOfTrustPage5.equals(estateNamePage2Form)) {
            throw new AutomationException("Name of Trust from page 2 is not fetched correctly on page 5. Expected: " + estateNamePage2Form + " , Found: " + nameOfTrustPage5);
        }
    }

    public void userSavesSelectedBeneficiariesDetails() throws AutomationException {
        WebDriverUtil.waitForAWhile();

        List<WebElement> beneDetailsPage4Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        List<WebElement> beneRelationshipPage4Fields = driverUtil.getWebElements(BENE_RELATIONSHIP_FORM);
        List<WebElement> beneInterestPage4Fields = driverUtil.getWebElements(BENE_INTEREST_FORM);

        for (int i = 0; i < 2; i++) {
            beneDetails.add(beneDetailsPage4Fields.get(i).getText());
            beneRelationship.add(beneRelationshipPage4Fields.get(i).getAttribute("value"));
            beneInterest.add(beneInterestPage4Fields.get(i).getText());
        }

        switchToPage(5);

        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsPage5Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        List<WebElement> beneRelationshipPage5Fields = driverUtil.getWebElements(BENE_RELATIONSHIP_FORM);
        List<WebElement> beneInterestPage5Fields = driverUtil.getWebElements(BENE_INTEREST_FORM);

        for (int i = 0; i < 2; i++) {
            beneDetails.add(beneDetailsPage5Fields.get(i).getText());
            beneRelationship.add(beneRelationshipPage5Fields.get(i).getAttribute("value"));
            beneInterest.add(beneInterestPage5Fields.get(i).getText());
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

    public void verifyAttachmentIsDisplayedOnPage4() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void verifySameAttachmentIsDisplayedOnPage5() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
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

        expectedAttachmentCountForm = attachmentBeneficiaries.size();

        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(CLOSE_BTN).click();
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

    public void verifyDecedentSNameIsDisplayedAndIsNotEditable() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_6);
        scrollToElement(ESTATE_NAME_PAGE_6);

        estateNameFormPage6 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage6)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage6);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_6);
    }

    public void verifyPreloadedDecedentSNameIsDisplayedAndIsReadOnly() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_7);
        scrollToElement(ESTATE_NAME_PAGE_7);

        estateNameFormPage7 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage7)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage7);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_7);
    }

    public void verifyDecedentSNameIsDisplayedAndNonEditable() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);
        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }

        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_8);
        scrollToElement(ESTATE_NAME_PAGE_8);

        estateNameFormPage8 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage8)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage8);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_8);
    }

    public void verifyDecedentSNameIsDisplayedCorrectlyAndIsNonEditable() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_9);
        scrollToElement(ESTATE_NAME_PAGE_9);

        estateNameFormPage9 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage9)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage9);
        }

        verifyFieldIsNotEditable(ESTATE_NAME_PAGE_9);
    }

    public void verifyEstateSNameIsAutoFetchedAndCorrectlyDisplayed() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_10);
        scrollToElement(ESTATE_NAME_PAGE_10);

        estateNameFormPage10 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage10)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage10);
        }
    }

    public void verifyCorrectTrustNameIsDisplayedOnTheForm() throws AutomationException {
        String enteredEstateName = getEstateValue("DisplayName");
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_11);
        scrollToElement(ESTATE_NAME_PAGE_11);

        estateNameFormPage11 = estateNameField.getAttribute("value");

        if (!enteredEstateName.equals(estateNameFormPage11)) {
            throw new AutomationException("Trust name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameFormPage11);
        }
    }

    public void userSelectYesForLitigationStatus() {
        scrollToElement(LITIGATION_YES_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(LITIGATION_YES_OPTION)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyTextFieldsAreEnabledDynamicallyWhenLitigationStatusIsYes() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement TextField = driverUtil.getWebElement(LITIGATION_TEXT_FIELD);

        if (!TextField.isEnabled() && TextField.getAttribute("disabled") != null && TextField.getAttribute("readonly") != null) {
            throw new AutomationException("Text fields are not enabled dynamically when litigation status is 'Yes'");
        }
    }

    public void userSelectYesForFiduciaryStatus() {
        scrollToElement(FIDUCIARY_STATUS_YES_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(FIDUCIARY_STATUS_YES_OPTION)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyAdditionalFiduciaryFieldsAppearAndAreEditable() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement TextField = driverUtil.getWebElement(FIDUCIARY_TEXT_FIELD);

        if (!TextField.isDisplayed() && !TextField.isEnabled() && TextField.getAttribute("disabled") != null && TextField.getAttribute("readonly") != null) {
            throw new AutomationException("Additional fiduciary fields are not appear or are not editable");
        }
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

    public void userCheckBothTheCheckboxesAsYes() {
        scrollToElement(FAMILY_EXEMPTION_CLAIMED_YES_OPT);
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_ALLOWED_YES_OPT)).click();
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_CLAIMED_YES_OPT)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyFamilyExemptionClaimantSNameFieldIsEnabled() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement familyExemptionField = driverUtil.getWebElement(FAMILY_EXEMPTION_FIELD);

        if (!familyExemptionField.isEnabled() && familyExemptionField.getAttribute("disabled") != null && familyExemptionField.getAttribute("readonly") != null) {
            throw new AutomationException("Family Exemption Claimant fields are not enabled.");
        }
    }

    public void verifyDatePaymentAndInterestCanBeAddedInCorrectFormat() throws IOException, ParseException, AutomationException {
        String paDate1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm1").toString();
        String paDate2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm2").toString();
        String paDate3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm3").toString();
        String paDate4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm4").toString();
        String paDate5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm5").toString();
        String paDate6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm6").toString();
        String paDate7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm7").toString();
        String paDate8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm8").toString();

        String paPayment1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm1").toString();
        String paPayment2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm2").toString();
        String paPayment3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm3").toString();
        String paPayment4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm4").toString();
        String paPayment5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm5").toString();
        String paPayment6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm6").toString();
        String paPayment7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm7").toString();
        String paPayment8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm8").toString();

        String paInterest1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm1").toString();
        String paInterest2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm2").toString();
        String paInterest3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm3").toString();
        String paInterest4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm4").toString();
        String paInterest5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm5").toString();
        String paInterest6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm6").toString();
        String paInterest7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm7").toString();
        String paInterest8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm8").toString();

        List<String> paDateForm = Arrays.asList(
                paDate1, paDate2, paDate3, paDate4, paDate5,
                paDate6, paDate7, paDate8
        );

        List<String> paPaymentForm = Arrays.asList(
                paPayment1, paPayment2, paPayment3, paPayment4, paPayment5,
                paPayment6, paPayment7, paPayment8
        );

        List<String> paInterestForm = Arrays.asList(
                paInterest1, paInterest2, paInterest3, paInterest4, paInterest5,
                paInterest6, paInterest7, paInterest8
        );

        for (int i = 0; i < 8; i++) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(PA_DATE_FIELDS, i));
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_DATE_FIELDS, i)));
            dateField.click();
            dateField.clear();
            dateField.sendKeys(paDateForm.get(i));
            dateField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            WebElement paymentField = driverUtil.getWebElementAndScroll(String.format(PA_PAYMENT_FIELDS, i));
            paymentField.sendKeys(paPaymentForm.get(i));
            paymentField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            WebElement interestField = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_INTEREST_FIELDS, i)));
            interestField.sendKeys(paInterestForm.get(i));
            interestField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile(2);
            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_DATE_FIELDS, i))).getAttribute("value");
            String actualPayment = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_PAYMENT_FIELDS, i))).getAttribute("value");
            String actualInterest = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_INTEREST_FIELDS, i))).getAttribute("value");

            if (!actualPayment.equals(paPaymentForm.get(i))) {
                throw new AutomationException("Payment field did not accept the entered text correctly. Expected: " + paPaymentForm.get(i) + ", Found: " + actualPayment);
            }

            if (!actualDate.equals(paDateForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered date correctly. Expected: " + paDateForm.get(i) + ", Found: " + actualDate);
            }

            if (!actualInterest.equals(paInterestForm.get(i))) {
                throw new AutomationException("Interest field did not accept the entered text correctly. Expected: " + paInterestForm.get(i) + ", Found: " + actualInterest);
            }

            switch (i) {
                case 0:
                    paDateForm1 = actualDate;
                    paPaymentForm1 = actualPayment;
                    paInterestForm1 = actualInterest;
                    break;
                case 1:
                    paDateForm2 = actualDate;
                    paPaymentForm2 = actualPayment;
                    paInterestForm2 = actualInterest;
                    break;
                case 2:
                    paDateForm3 = actualDate;
                    paPaymentForm3 = actualPayment;
                    paInterestForm3 = actualInterest;
                    break;
                case 3:
                    paDateForm4 = actualDate;
                    paPaymentForm4 = actualPayment;
                    paInterestForm4 = actualInterest;
                    break;
                case 4:
                    paDateForm5 = actualDate;
                    paPaymentForm5 = actualPayment;
                    paInterestForm5 = actualInterest;
                    break;
                case 5:
                    paDateForm6 = actualDate;
                    paPaymentForm6 = actualPayment;
                    paInterestForm6 = actualInterest;
                    break;
                case 6:
                    paDateForm7 = actualDate;
                    paPaymentForm7 = actualPayment;
                    paInterestForm7 = actualInterest;
                    break;
                case 7:
                    paDateForm8 = actualDate;
                    paPaymentForm8 = actualPayment;
                    paInterestForm8 = actualInterest;
                    break;
            }
        }
    }

    public void verifyDateDescriptionAndAmountCanBeAddedInTheReceiptsDisbursementsTable() throws IOException, ParseException, AutomationException {
        String radDate1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm1").toString();
        String radDate2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm2").toString();
        String radDate3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm3").toString();
        String radDate4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm4").toString();
        String radDate5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm5").toString();
        String radDate6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm6").toString();
        String radDate7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm7").toString();
        String radDate8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm8").toString();
        String radDate9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm9").toString();

        String radDescription1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm1").toString();
        String radDescription2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm2").toString();
        String radDescription3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm3").toString();
        String radDescription4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm4").toString();
        String radDescription5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm5").toString();
        String radDescription6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm6").toString();
        String radDescription7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm7").toString();
        String radDescription8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm8").toString();
        String radDescription9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm9").toString();

        String radAmount1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm1").toString();
        String radAmount2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm2").toString();
        String radAmount3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm3").toString();
        String radAmount4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm4").toString();
        String radAmount5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm5").toString();
        String radAmount6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm6").toString();
        String radAmount7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm7").toString();
        String radAmount8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm8").toString();
        String radAmount9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm9").toString();

        List<String> radDateForm = Arrays.asList(
                radDate1, radDate2, radDate3, radDate4, radDate5,
                radDate6, radDate7, radDate8, radDate9
        );

        List<String> radDescriptionForm = Arrays.asList(
                radDescription1, radDescription2, radDescription3, radDescription4, radDescription5,
                radDescription6, radDescription7, radDescription8, radDescription9
        );

        List<String> radAmountForm = Arrays.asList(
                radAmount1, radAmount2, radAmount3, radAmount4, radAmount5,
                radAmount6, radAmount7, radAmount8, radAmount9
        );


        for (int i = 0; i < 9; i++) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(RAD_DATE_FIELDS, i));
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_DATE_FIELDS, i)));
            dateField.click();
            dateField.clear();
            dateField.sendKeys(radDateForm.get(i));
            dateField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            WebElement descriptionField = driverUtil.getWebElement(String.format(RAD_DESCRIPTION_FIELDS, i));
            descriptionField.sendKeys(radDescriptionForm.get(i));
            descriptionField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            WebElement amountField = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_AMOUNT_FIELDS, i)));
            amountField.sendKeys(radAmountForm.get(i));
            amountField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile(4);
            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_DATE_FIELDS, i))).getAttribute("value");
            String actualDescription = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_DESCRIPTION_FIELDS, i))).getAttribute("value");
            String actualAmount = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_AMOUNT_FIELDS, i))).getAttribute("value");

            if (!actualDate.equals(radDateForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered text correctly. Expected: " + radDateForm.get(i) + ", Found: " + actualDate);
            }

            if (!actualDescription.equals(radDescriptionForm.get(i))) {
                throw new AutomationException("Description field did not accept the entered date correctly. Expected: " + radDescriptionForm.get(i) + ", Found: " + actualDescription);
            }

            if (!actualAmount.equals(radAmountForm.get(i))) {
                throw new AutomationException("Amount field did not accept the entered text correctly. Expected: " + radAmountForm.get(i) + ", Found: " + actualAmount);
            }

            switch (i) {
                case 0:
                    radDateForm1 = actualDate;
                    radDescriptionForm1 = actualDescription;
                    radAmountForm1 = actualAmount;
                    break;
                case 1:
                    radDateForm2 = actualDate;
                    radDescriptionForm2 = actualDescription;
                    radAmountForm2 = actualAmount;
                    break;
                case 2:
                    radDateForm3 = actualDate;
                    radDescriptionForm3 = actualDescription;
                    radAmountForm3 = actualAmount;
                    break;
                case 3:
                    radDateForm4 = actualDate;
                    radDescriptionForm4 = actualDescription;
                    radAmountForm4 = actualAmount;
                    break;
                case 4:
                    radDateForm5 = actualDate;
                    radDescriptionForm5 = actualDescription;
                    radAmountForm5 = actualAmount;
                    break;
                case 5:
                    radDateForm6 = actualDate;
                    radDescriptionForm6 = actualDescription;
                    radAmountForm6 = actualAmount;
                    break;
                case 6:
                    radDateForm7 = actualDate;
                    radDescriptionForm7 = actualDescription;
                    radAmountForm7 = actualAmount;
                    break;
                case 7:
                    radDateForm8 = actualDate;
                    radDescriptionForm8 = actualDescription;
                    radAmountForm8 = actualAmount;
                    break;
                case 8:
                    radDateForm9 = actualDate;
                    radDescriptionForm9 = actualDescription;
                    radAmountForm9 = actualAmount;
                    break;
            }
        }
    }

    public void verifyReserveRequestAmountCanBeAdded() throws IOException, ParseException, AutomationException {
        String enteredReserveRequestAmountForm = CommonUtil.getJsonPath("OC01Form").get("OC01Form.ReserveRequestAmount").toString();

        WebDriverUtil.waitForAWhile();
        scrollToElement(RESERVE_REQUEST_AMOUNT);
        WebElement amountField = DriverFactory.drivers.get().findElement(By.xpath(RESERVE_REQUEST_AMOUNT));
        amountField.sendKeys(enteredReserveRequestAmountForm);
        amountField.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();
        reserveRequestAmountForm = DriverFactory.drivers.get().findElement(By.xpath(RESERVE_REQUEST_AMOUNT)).getAttribute("value");

        if (!enteredReserveRequestAmountForm.equals(reserveRequestAmountForm)) {
            throw new AutomationException("Reserve Request Amount field did not accept the entered amount correctly. Expected: " + enteredReserveRequestAmountForm + ", Found: " + reserveRequestAmountForm);
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


    public void userResetsTheRWForm() throws AutomationException {
        //page 1
        switchToPage(1);
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberField.clear();
        fileNumberField.sendKeys(initialFileNumber);
        WebDriverUtil.waitForAWhile();
        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);
        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }


        //page 2
        switchToPage(2);
        userClickOnPetitionerNameField();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Petitioner(s) updated successfully.")));


        //page 3
        switchToPage(3);
        for (int i = 9; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));

            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i)));
            clearFieldUntilEmpty(dateField);

            WebElement fieldElement = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i)));
            clearFieldUntilEmpty(fieldElement);
        }


        //page 4
        switchToPage(4);
        for (int i = 0; i < 2; i++) {
            scrollToElement(String.format(COMMENTS_FIELD_XPATH, i));
            WebElement commentsField = driverUtil.getWebElement(String.format(COMMENTS_FIELD_XPATH, i));
            commentsField.clear();
        }


        //page 6
        switchToPage(6);
        userClicksOnAddEditClaimants();
        for (int i = 0; i < 5; i++) {
            driverUtil.getWebElement(DELETE_BUTTON).click();
            WebDriverUtil.waitForAWhile();
            driverUtil.getWebElement(NOTIFICATION_DELETE_BUTTON).click();

            WebDriverUtil.waitForVisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Claimant is removed, and totals are updated.")));
            WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Claimant is removed, and totals are updated.")));
        }
        driverUtil.getWebElement(CLOSE_BTN).click();


        //page 7
        switchToPage(7);
        scrollToElement(FAMILY_EXEMPTION_CLAIMED_YES_OPT);
        List<WebElement> toasterBtns7 = driverUtil.getWebElements(CLOSE_TOASTER_BTN);
        if (!toasterBtns7.isEmpty() && toasterBtns7.get(0).isDisplayed()) {
            toasterBtns7.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_ALLOWED_YES_OPT)).click();
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_CLAIMED_YES_OPT)).click();
        scrollToElement(String.format(PA_DATE_FIELDS, 7));
        WebDriverUtil.waitForAWhile();

        for (int i = 7; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(PA_DATE_FIELDS, i));

            WebElement interestField = driverUtil.getWebElement(String.format(PA_INTEREST_FIELDS, i));
            clearFieldUntilEmpty(interestField);

            WebElement paymentField = driverUtil.getWebElement(String.format(PA_PAYMENT_FIELDS, i));
            clearFieldUntilEmpty(paymentField);

            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_DATE_FIELDS, i)));
            clearFieldUntilEmpty(dateField);
        }
        scrollToElement(FIDUCIARY_STATUS_YES_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(FIDUCIARY_STATUS_YES_OPTION)).click();

        //page 8
        switchToPage(8);
        scrollToElement(LITIGATION_YES_OPTION);
        DriverFactory.drivers.get().findElement(By.xpath(LITIGATION_YES_OPTION)).click();

        //page 9
        switchToPage(9);
        WebDriverUtil.waitForAWhile();
        for (int i = 8; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(RAD_DATE_FIELDS, i));

            WebElement amountField = driverUtil.getWebElement(String.format(RAD_AMOUNT_FIELDS, i));
            clearFieldUntilEmpty(amountField);

            WebElement descriptionField = driverUtil.getWebElement(String.format(RAD_DESCRIPTION_FIELDS, i));
            clearFieldUntilEmpty(descriptionField);

            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_DATE_FIELDS, i)));
            clearFieldUntilEmpty(dateField);
        }
        scrollToElement(RESERVE_REQUEST_AMOUNT);
        driverUtil.getWebElement(RESERVE_REQUEST_AMOUNT).clear();

        //page 10
        switchToPage(10);
        scrollToElement(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN)).click();
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
        WebDriverUtil.waitForAWhile();
        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);
        int[] contactsToEnterProportion = {0, 2, 3, 4};
        for (int i = 0; i < contactsToEnterProportion.length; i++) {
            int index = contactsToEnterProportion[i];

            if (index < proportionFields.size()) {
                WebElement inputField = proportionFields.get(index);
                inputField.clear();
            }
        }
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verify1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner() throws AutomationException {
        WebElement signOfPetitionerField = driverUtil.getWebElement(SIGN_OF_PETITIONER_PAGE_11);
        String petitionerNamePage11 = signOfPetitionerField.getAttribute("value");
        String expectedPetitionerName = Fiduciaries.get(0);

        if (!petitionerNamePage11.equals(expectedPetitionerName)) {
            throw new AutomationException("1st individual petitioner selected on page 2 is not displayed under individual petitioner. Expected: " + expectedPetitionerName + " ,Found: " + petitionerNamePage11);
        }
    }

    public void userClicksOnAddEditClaimants() throws AutomationException {
        scrollToElement(CLAIMANT_INITIALS_FIELD);
        driverUtil.getWebElement(CLAIMANT_INITIALS_FIELD).click();
    }

    public void userClicksOnAddNewClaimantButton() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ADD_NEW_CLAIMANT_BTN).click();
    }

    public void userAddsMultipleClaimants() throws IOException, ParseException, AutomationException {
        String Initials1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials1").toString();
        String Name1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name1").toString();
        String Address1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address1").toString();
        String Amount_of_Claim1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim1").toString();
        String Claim_Admitted1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted1").toString();
        String Will_Claim_Be_Paid_In_Full1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full1").toString();

        String Initials2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials2").toString();
        String Name2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name2").toString();
        String Address2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address2").toString();
        String Amount_of_Claim2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim2").toString();
        String Claim_Admitted2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted2").toString();
        String Will_Claim_Be_Paid_In_Full2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full2").toString();

        String Initials3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials3").toString();
        String Name3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name3").toString();
        String Address3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address3").toString();
        String Amount_of_Claim3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim3").toString();
        String Claim_Admitted3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted3").toString();
        String Will_Claim_Be_Paid_In_Full3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full3").toString();

        String Initials4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials4").toString();
        String Name4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name4").toString();
        String Address4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address4").toString();
        String Amount_of_Claim4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim4").toString();
        String Claim_Admitted4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted4").toString();
        String Will_Claim_Be_Paid_In_Full4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full4").toString();

        String Initials5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials5").toString();
        String Name5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name5").toString();
        String Address5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address5").toString();
        String Amount_of_Claim5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim5").toString();
        String Claim_Admitted5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted5").toString();
        String Will_Claim_Be_Paid_In_Full5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full5").toString();

        List<String> InitialsForm = Arrays.asList(
                Initials1, Initials2, Initials3, Initials4, Initials5
        );

        List<String> NameForm = Arrays.asList(
                Name1, Name2, Name3, Name4, Name5
        );

        List<String> AddressForm = Arrays.asList(
                Address1, Address2, Address3, Address4, Address5
        );

        List<String> AmountOfClaimForm = Arrays.asList(
                Amount_of_Claim1, Amount_of_Claim2, Amount_of_Claim3, Amount_of_Claim4, Amount_of_Claim5
        );

        List<String> ClaimAdmittedForm = Arrays.asList(
                Claim_Admitted1, Claim_Admitted2, Claim_Admitted3, Claim_Admitted4, Claim_Admitted5
        );

        List<String> WillClaimBePaidInFullForm = Arrays.asList(
                Will_Claim_Be_Paid_In_Full1, Will_Claim_Be_Paid_In_Full2,
                Will_Claim_Be_Paid_In_Full3, Will_Claim_Be_Paid_In_Full4, Will_Claim_Be_Paid_In_Full5
        );

        clearField(INITIALS_MODAL_FIELD);
        clearField(CLAIMANT_NAME_FIELD);
        clearField(CLAIMANT_ADDRESS_FIELD);

        for (int i = 0; i < InitialsForm.size(); i++) {
            WebElement initialsField = driverUtil.getWebElement(INITIALS_MODAL_FIELD);
            initialsField.sendKeys(InitialsForm.get(i));

            WebElement nameField = driverUtil.getWebElement(CLAIMANT_NAME_FIELD);
            nameField.sendKeys(NameForm.get(i));

            WebElement addressField = driverUtil.getWebElement(CLAIMANT_ADDRESS_FIELD);
            addressField.sendKeys(AddressForm.get(i));

            WebElement claimAmountField = driverUtil.getWebElement(CLAIM_AMOUNT_FIELD);
            claimAmountField.sendKeys(AmountOfClaimForm.get(i));

            if (ClaimAdmittedForm.get(i).equalsIgnoreCase("Yes")) {
                driverUtil.getWebElement(CLAIM_ADMITTED_YES_OPT).click();
            } else {
                driverUtil.getWebElement(CLAIM_ADMITTED_NO_OPT).click();
            }

            if (WillClaimBePaidInFullForm.get(i).equalsIgnoreCase("Yes")) {
                driverUtil.getWebElement(CLAIM_PAID_YES_OPT).click();
            } else {
                driverUtil.getWebElement(CLAIM_PAID_NO_OPT).click();
            }

            driverUtil.getWebElement(ADD_BUTTON).click();

            WebDriverUtil.waitForVisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "New claimant is added successfully, and totals are updated.")));
            WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "New claimant is added successfully, and totals are updated.")));
        }

        WebDriverUtil.waitForAWhile();
        List<WebElement> claimantInitialsFields = driverUtil.getWebElements(CLAIMANT_INITIALS_LIST);
        List<WebElement> claimantNameAddressFields = driverUtil.getWebElements(CLAIMANT_NAME_ADDRESS_LIST);
        List<WebElement> claimantAmountFields = driverUtil.getWebElements(CLAIMANT_AMOUNT_LIST);
        List<WebElement> claimAdmittedFields = driverUtil.getWebElements(CLAIM_ADMITTED_LIST);
        List<WebElement> claimPaidFields = driverUtil.getWebElements(CLAIM_PAID_LIST);

        for (int i = 0; i < claimantInitialsFields.size(); i++) {
            claimantInitialsForm.add(claimantInitialsFields.get(i).getText().trim());
            claimAmountForm.add(claimantAmountFields.get(i).getText().trim());
            claimAdmittedForm.add(claimAdmittedFields.get(i).getText().trim());
            claimPaidForm.add(claimPaidFields.get(i).getText().trim());
        }

        for (WebElement nameAddress : claimantNameAddressFields) {
            String text = nameAddress.getText().trim();
            if (!text.isEmpty()) {
                String[] lines = text.split("\\r?\\n");
                if (lines.length >= 2) {
                    claimantNameForm.add(lines[0].trim());
                    claimantAddressForm.add(lines[1].trim());
                } else {
                    CommonSteps.logInfo("Invalid claimant format: " + text);
                }
            }
        }
        driverUtil.getWebElement(CLOSE_BTN).click();

        for (int i = 0; i < claimantInitialsForm.size(); i++) {
            switch (i) {
                case 0:
                    Initials1Form = claimantInitialsForm.get(i);
                    Name1Form = claimantNameForm.get(i);
                    Address1Form = claimantAddressForm.get(i);
                    Amount_of_Claim1Form = claimAmountForm.get(i);
                    Claim_Admitted1Form = claimAdmittedForm.get(i);
                    Will_Claim_Be_Paid_In_Full1Form = claimPaidForm.get(i);
                    break;
                case 1:
                    Initials2Form = claimantInitialsForm.get(i);
                    Name2Form = claimantNameForm.get(i);
                    Address2Form = claimantAddressForm.get(i);
                    Amount_of_Claim2Form = claimAmountForm.get(i);
                    Claim_Admitted2Form = claimAdmittedForm.get(i);
                    Will_Claim_Be_Paid_In_Full2Form = claimPaidForm.get(i);
                    break;
                case 2:
                    Initials3Form = claimantInitialsForm.get(i);
                    Name3Form = claimantNameForm.get(i);
                    Address3Form = claimantAddressForm.get(i);
                    Amount_of_Claim3Form = claimAmountForm.get(i);
                    Claim_Admitted3Form = claimAdmittedForm.get(i);
                    Will_Claim_Be_Paid_In_Full3Form = claimPaidForm.get(i);
                    break;
                case 3:
                    Initials4Form = claimantInitialsForm.get(i);
                    Name4Form = claimantNameForm.get(i);
                    Address4Form = claimantAddressForm.get(i);
                    Amount_of_Claim4Form = claimAmountForm.get(i);
                    Claim_Admitted4Form = claimAdmittedForm.get(i);
                    Will_Claim_Be_Paid_In_Full4Form = claimPaidForm.get(i);
                    break;
                case 4:
                    Initials5Form = claimantInitialsForm.get(i);
                    Name5Form = claimantNameForm.get(i);
                    Address5Form = claimantAddressForm.get(i);
                    Amount_of_Claim5Form = claimAmountForm.get(i);
                    Claim_Admitted5Form = claimAdmittedForm.get(i);
                    Will_Claim_Be_Paid_In_Full5Form = claimPaidForm.get(i);
                    break;
            }
        }
    }

    public void verifyClaimantIsAddedToTheListAndTotalsAreUpdatedDynamically() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> aboveAttachmentTotalFields = driverUtil.getWebElements(ABOVE_ATTACHMENT_TOTAL_FIELDS);
        for (int i = 0; i < aboveAttachmentTotalFields.size(); i++) {
            String amount = aboveAttachmentTotalFields.get(i).getAttribute("value").replace(",", "");
            switch (i) {
                case 0:
                    aboveAmountForm = amount;
                    break;
                case 1:
                    attachmentAmountForm = amount;
                    break;
                case 2:
                    totalAmountForm = amount;
                    break;
            }
        }

        List<WebElement> initialsFields = driverUtil.getWebElements(CLAIMANT_INITIALS_FORM_LIST);

        for (int i = 0; i < initialsFields.size(); i++) {
            String expectedInitial = claimantInitialsForm.get(i);
            String actualInitial = initialsFields.get(i).getAttribute("value").trim();

            if (!actualInitial.equals(expectedInitial)) {
                throw new AutomationException("Claimant is not added to the list correctly. Expected: " + expectedInitial + " ,Found: " + actualInitial);
            }
        }

        double totalClaimAmount = 0.0;

        for (String amount : claimAmountForm) {
            totalClaimAmount += Double.parseDouble(amount.replace(",", ""));
        }

        if (Double.parseDouble(totalAmountForm) != totalClaimAmount) {
            throw new AutomationException("Totals are not updated dynamically. Expected: " + totalAmountForm + " ,Found: " + totalClaimAmount);
        }

    }

    public void verifyFirstFourClaimantsRemainInTheMainTableAndAdditionalClaimantsAreDisplayedInTheAttachment() throws AutomationException {
        List<String> mainTableClaimants = new ArrayList<>();
        List<String> attachmentClaimants = new ArrayList<>();

        WebDriverUtil.waitForAWhile();
        List<WebElement> claimantMainTableFields = driverUtil.getWebElements(CLAIMANT_INITIALS_FORM_LIST);
        List<WebElement> claimAmountMainTableFields = driverUtil.getWebElements(CLAIMANT_AMOUNT_FORM);

        for (WebElement mainTableElement : claimAmountMainTableFields) {
            mainTableClaimAmount.add(mainTableElement.getAttribute("value").trim().replace(",", ""));
        }

        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();

        WebDriverUtil.waitForAWhile();
        List<WebElement> claimantAttachmentFields = driverUtil.getWebElements(CLAIMANT_INITIALS_ATTACHMENT_LIST);
        List<WebElement> claimAmountAttachmentFields = driverUtil.getWebElements(CLAIMANT_AMOUNT_ATTACHMENT);

        for (WebElement attachmentElement : claimAmountAttachmentFields) {
            attachmentClaimAmount.add(attachmentElement.getText().trim().replace(",", ""));
        }

        for (int i = 0; i < claimantInitialsForm.size(); i++) {
            String claimantInitials = claimantInitialsForm.get(i);

            if (i < 4) {
                mainTableClaimants.add(claimantInitials);
            } else {
                attachmentClaimants.add(claimantInitials);
            }
        }

        List<String> expectedMainTableClaimants = new ArrayList<>();
        for (WebElement mainTableElement : claimantMainTableFields) {
            expectedMainTableClaimants.add(mainTableElement.getAttribute("value").trim());
        }

        List<String> expectedAttachmentClaimants = new ArrayList<>();
        for (WebElement attachmentElement : claimantAttachmentFields) {
            expectedAttachmentClaimants.add(attachmentElement.getText().trim());
        }

        if (!expectedMainTableClaimants.equals(mainTableClaimants)) {
            throw new AutomationException("Mismatch in main table claimants. Expected: " + expectedMainTableClaimants + ", Found: " + mainTableClaimants);
        }

        if (!expectedAttachmentClaimants.equals(attachmentClaimants)) {
            throw new AutomationException("Mismatch in attachment claimants. Expected: " + expectedAttachmentClaimants + ", Found: " + attachmentClaimants);
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyNameAndAddressFieldsAreRequiredIfInitialFieldIsEmpty() throws AutomationException {
        WebElement initialsField = driverUtil.getWebElement(INITIALS_MODAL_FIELD);
        WebElement nameField = driverUtil.getWebElement(CLAIMANT_NAME_FIELD);
        WebElement addressField = driverUtil.getWebElement(CLAIMANT_ADDRESS_FIELD);

        initialsField.clear();
        nameField.clear();
        addressField.clear();

        driverUtil.getWebElement(ADD_BUTTON).click();

        WebDriverUtil.waitForAWhile();
        WebElement nameErrMsg = driverUtil.getWebElement(CLAIMANT_NAME_ERR_MSG);
        WebElement addressErrMsg = driverUtil.getWebElement(CLAIMANT_ADDRESS_ERR_MSG);

        if (!nameErrMsg.isDisplayed() || !addressErrMsg.isDisplayed()) {
            throw new AutomationException("Error message Name and Address are required is not displayed when Initials are empty.");
        }
    }

    public void verifyIfInitialsExistThenNameAndAddressAreNotRequired() throws AutomationException, IOException, ParseException {
        String Initials = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials1").toString();

        WebElement initialsField = driverUtil.getWebElement(INITIALS_MODAL_FIELD);
        WebElement nameField = driverUtil.getWebElement(CLAIMANT_NAME_FIELD);
        WebElement addressField = driverUtil.getWebElement(CLAIMANT_ADDRESS_FIELD);

        initialsField.clear();
        nameField.clear();
        addressField.clear();

        initialsField.sendKeys(Initials);

        driverUtil.getWebElement(ADD_BUTTON).click();

        WebDriverUtil.waitForAWhile();
        boolean isNameErrorPresent = !DriverFactory.drivers.get().findElements(By.xpath(CLAIMANT_NAME_ERR_MSG)).isEmpty();
        boolean isAddressErrorPresent = !DriverFactory.drivers.get().findElements(By.xpath(CLAIMANT_ADDRESS_ERR_MSG)).isEmpty();

        if (isNameErrorPresent || isAddressErrorPresent) {
            throw new AutomationException("Name or Address is still required even when Initials are provided.");
        }
    }

    public void verifyInitialsAreNotRequiredIfNameAndAddressIsThere() throws IOException, ParseException, AutomationException {
        String NameForm = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name1").toString();
        String AddressForm = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address1").toString();

        WebElement nameField = driverUtil.getWebElement(CLAIMANT_NAME_FIELD);
        WebElement addressField = driverUtil.getWebElement(CLAIMANT_ADDRESS_FIELD);

        clearField(INITIALS_MODAL_FIELD);
        nameField.clear();
        addressField.clear();

        nameField.sendKeys(NameForm);
        addressField.sendKeys(AddressForm);

        driverUtil.getWebElement(ADD_BUTTON).click();

        WebDriverUtil.waitForAWhile();
        boolean isInitialsErrorPresent = !DriverFactory.drivers.get().findElements(By.xpath(CLAIMANT_INITIALS_ERR_MSG)).isEmpty();

        if (isInitialsErrorPresent) {
            throw new AutomationException("Initials is still required even when Name and Address are provided.");
        }
    }

    public void verifyTheTotalsDisplayedCorrectlyForAboveAndAttachment() throws AutomationException {
        double calculatedMainTableClaimAmount = 0.0;

        for (String amount : mainTableClaimAmount) {
            calculatedMainTableClaimAmount += Double.parseDouble(amount);
        }

        if (Double.parseDouble(aboveAmountForm) != calculatedMainTableClaimAmount) {
            throw new AutomationException("Above total is not updated dynamically. Expected: " + calculatedMainTableClaimAmount + " ,Found: " + aboveAmountForm);
        }

        double calculatedAttachmentTableClaimAmount = 0.0;

        for (String amount : attachmentClaimAmount) {
            calculatedAttachmentTableClaimAmount += Double.parseDouble(amount);
        }

        if (Double.parseDouble(attachmentAmountForm) != calculatedAttachmentTableClaimAmount) {
            throw new AutomationException("Attachment total is not updated dynamically. Expected: " + calculatedAttachmentTableClaimAmount + " ,Found: " + attachmentAmountForm);
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
                selectedContactNamesPage10.add(nameElements.get(i).getText());
            }
        }

    }

    public void userEntersProportionForBeneficiaries() throws AutomationException, IOException, ParseException {
        String proportionValue1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.proportionValue1").toString();
        String proportionValue2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.proportionValue2").toString();
        String proportionValue3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.proportionValue3").toString();
        String proportionValue4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.proportionValue4").toString();

        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);

        int[] contactsToEnterProportion = {0, 2, 3, 4};
        enteredProportionValues = new String[]{proportionValue1, proportionValue2, proportionValue3, proportionValue4};

        for (int i = 0; i < contactsToEnterProportion.length; i++) {
            int index = contactsToEnterProportion[i];
            String value = enteredProportionValues[i];

            if (index < proportionFields.size()) {
                WebElement inputField = proportionFields.get(index);
                inputField.clear();
                inputField.sendKeys(value);
            }
        }
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void userVerifiesDisplayedContactsOnForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        List<WebElement> displayedNameElements = driverUtil.getWebElements(BENE_NAMES_INCOME_PAGE_10);

        List<String> displayedNames = new ArrayList<>();
        for (WebElement element : displayedNameElements) {
            displayedNames.add(element.getAttribute("value").trim());
        }

        for (String expectedName : selectedContactNamesPage10) {
            if (!displayedNames.contains(expectedName)) {
                throw new AutomationException("Expected contact name not displayed: " + expectedName);
            }
        }
    }

    public void userVerifiesDisplayedProportionsOnForm() throws AutomationException {
        List<WebElement> displayedProportionElements = driverUtil.getWebElements(BENE_PROPORTION_INCOME_PAGE_10);

        for (int i = 0; i < enteredProportionValues.length; i++) {
            String expectedValue = enteredProportionValues[i];
            String actualValue = displayedProportionElements.get(i).getAttribute("value").trim().replace("%", "");

            if (!expectedValue.equals(actualValue)) {
                throw new AutomationException("Mismatch at row " + (i + 1) + ": expected " + expectedValue + ", got " + actualValue);
            }
        }
    }

    public void verifyAllTheBeneficiaryContactsAreMovedToTheAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile(2);

        for (String contactName : selectedContactNamesPage10) {
            String contactXpath = String.format(BENE_ON_ATTACHMENT_PAGE_10, contactName);
            WebElement element = driverUtil.getWebElement(contactXpath);

            if (element == null && !element.getAttribute("value").equals(contactName)) {
                throw new AutomationException("Beneficiary Contact not found in attachment: " + contactName);
            }
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void verifyAllTheRemainingPetitionersAreDisplayedAsAPartOfAttachment() throws AutomationException {
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();

        List<String> expectedPetitionersOnAttachment = new ArrayList<>();
        List<String> expectedFiduciary = new ArrayList<>();
        List<String> expectedCorporateFiduciary = new ArrayList<>();

        for (int i = 0; i < Fiduciaries.size(); i++) {
            if (i != 0) {
                expectedFiduciary.add(Fiduciaries.get(i));
            }
        }

        for (int i = 0; i < corporateFiduciaries.size(); i++) {
            if (i != 0) {
                expectedCorporateFiduciary.add(corporateFiduciaries.get(i));
            }
        }

        expectedPetitionersOnAttachment.addAll(expectedFiduciary);
        expectedPetitionersOnAttachment.addAll(expectedCorporateFiduciary);

        List<WebElement> petitionerOnAttachmentFields = driverUtil.getWebElements(SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_11);
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


    public String extractFullName(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        String[] lines = input.split("\\r?\\n");
        return lines.length > 0 ? lines[0].trim() : "";
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
        waitForVisibleElement(By.xpath(CONTACT_USED_IN_OC01_FORM_NOTIFICATION));
        WebElement rolesNotification = driverUtil.getWebElement(CONTACT_USED_IN_OC01_FORM_NOTIFICATION);
        if (!rolesNotification.isDisplayed()) {
            throw new AutomationException("Notification is not displayed on removing the role");
        }
    }


    public void verifyNotificationIsDisplayedWhenTheContactSelectedAsThePetitionerIsRemovedFromTheEstateContacts() throws AutomationException, IOException, ParseException {
        String petitioner1 = getEstateContactName(nameOfPetitionerForm);
        String petitioner2 = getEstateContactName(nameOfPetitioner2Form);

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

    public void resetRolesOfContacts() throws AutomationException, IOException, ParseException {
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));

        String petitioner1 = getEstateContactName(nameOfPetitionerForm);
        String petitioner2 = getEstateContactName(nameOfPetitioner2Form);

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
        driverUtil.getWebElement(String.format(RELATIONSHIP_OPTION, relationship)).click();
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
        switchToPage(5);
        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsPage5Fields = driverUtil.getWebElements(BENE_DETAILS_FORM);
        for (int i = 0; i < 2; i++) {
            beneDetailsAfterRoleRemoved.add(beneDetailsPage5Fields.get(i).getText());
        }
        scrollToElement(VIEW_ATTACHMENT_BTN);
        driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
        WebDriverUtil.waitForAWhile();
        List<WebElement> beneDetailsOnAttachmentFields = driverUtil.getWebElements(BENE_DETAILS_ATTACHMENT);
        for (int i = 0; i < 2; i++) {
            beneDetailsAfterRoleRemoved.add(beneDetailsOnAttachmentFields.get(i).getText());
        }
        driverUtil.getWebElement(CLOSE_BTN).click();

        String removedBene = beneDetails.get(0);
        if (beneDetailsAfterRoleRemoved.contains(removedBene)) {
            throw new AutomationException("Beneficiary contact still exists on the form even after role was removed: " + removedBene);
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


    public void verifyAllFieldsInDownloadedPDF() throws AutomationException, IOException {
        String pdfFilePath = ((System.getProperty("os.name").toLowerCase().contains("win"))
                ? System.getProperty("user.dir") + "\\downloads\\"
                : System.getProperty("user.dir") + "/downloads/") + downloadedFileName;
        try {
            boolean isVerifiedFileNumber = verifyFieldsInPDF(pdfFilePath,
                    "ORPHANS' COURT DIVISION",
                    "This form shall be used in all cases involving the Audit or Confirmation of the Account of a",
                    fileNumberForm,
                    "file number");


            Map<String, String> expectedCounselDetails = new HashMap<>();
            expectedCounselDetails.put("Name of Counsel", nameOfCounselForm);
            expectedCounselDetails.put("Name of Law Firm", nameOfLawFirmForm);
            String attorneyAddressLineForm = attorneyAddressLine1Form + " " + attorneyAddressLine2Form;
            expectedCounselDetails.put("Address", attorneyAddressLineForm);
            expectedCounselDetails.put("Telephone 1", attorneyTelephoneForm);
            expectedCounselDetails.put("Telephone 2", attorneyFaxForm);
            expectedCounselDetails.put("Email", attorneyEmailForm);
            boolean isVerifiedCounselDetails = verifyCounselDetails(pdfFilePath, expectedCounselDetails);

            Map<String, String> expectedPetitioners = new LinkedHashMap<>();
            expectedPetitioners.put(nameOfPetitionerForm, petitioner1AddressLineForm);
            expectedPetitioners.put(nameOfPetitioner2Form, petitioner2AddressLineForm);
            boolean isValidatedPetitionerAddressMapping = validatePetitionerAddressMapping(pdfFilePath, expectedPetitioners);

            boolean isverifiedDateOfWill = verifyFieldsInPDF(pdfFilePath,
                    "Letters Testamentary or  Letters of Administration were granted to Petitioner(s) on",
                    "Date(s) of Codicil(s) (if applicable): 02/17/2023 02/20/2023 02/26/2023",
                    dateOfWillForm,
                    "Date of Will");

            Map<String, String> expectedCodicilValues = new HashMap<>();
            expectedCodicilValues.put("Codicil Date1", codicilDate1Form);
            expectedCodicilValues.put("Codicil Date2", codicilDate2Form);
            expectedCodicilValues.put("Codicil Date3", codicilDate3Form);
            boolean isVerifiedCodicilDates = verifyCodicilDates(pdfFilePath,
                    "Date of Will (if applicable): 10/26/2015",
                    "Date of probate (if different from date Letters granted):",
                    expectedCodicilValues,
                    "Codicil Dates");

            Map<String, String> expectedValues = new HashMap<>();
            expectedValues.put("additionalPetitioner1 Name", Fiduciary3Form);
            String petitioner3AddressLineForm = petitioner3AddressLine1Form + " " + petitioner3CityStateCodeZipForm;
            expectedValues.put("additionalPetitioner1 Address", petitioner3AddressLineForm);
            expectedValues.put("additionalPetitioner2 Name", Fiduciary4Form);
            String petitioner4AddressLineForm = petitioner4AddressLine1Form + " " + petitioner4CityStateCodeZipForm;
            expectedValues.put("additionalPetitioner2 Address", petitioner4AddressLineForm);
            boolean isVerifiedAdditionalPetitioners = verifyAdditionalPetitioners(pdfFilePath,
                    "(Additional Petitioner)",               // beforeLine
                    "PETITION FOR ADJUDICATION", // afterLine
                    expectedValues
            );

            Map<String, String> expectedChildren = new LinkedHashMap<>();
            expectedChildren.put(childrenDetailDataForm1, dateDataForm1);
            expectedChildren.put(childrenDetailDataForm2, dateDataForm2);
            expectedChildren.put(childrenDetailDataForm3, dateDataForm3);
            expectedChildren.put(childrenDetailDataForm4, dateDataForm4);
            expectedChildren.put(childrenDetailDataForm5, dateDataForm5);
            expectedChildren.put(childrenDetailDataForm6, dateDataForm6);
            expectedChildren.put(childrenDetailDataForm7, dateDataForm7);
            expectedChildren.put(childrenDetailDataForm8, dateDataForm8);
            expectedChildren.put(childrenDetailDataForm9, dateDataForm9);
            expectedChildren.put(childrenDetailDataForm10, dateDataForm10);
            boolean isVerifiedChildrenNamesAndDOBs = verifyChildrenNamesAndDOBs(
                    pdfFilePath,
                    "If yes, give names and dates of birth:",
                    "7. Was a request for a statement of claim, as required by the Medical",
                    expectedChildren
            );

            Map<String, String> expectedDatePaymentInterestMap = new LinkedHashMap<>();
            expectedDatePaymentInterestMap.put("Date_1", paDateForm1);
            expectedDatePaymentInterestMap.put("Payment_1", paPaymentForm1);
            expectedDatePaymentInterestMap.put("Interest_1", paInterestForm1);
            expectedDatePaymentInterestMap.put("Date_2", paDateForm2);
            expectedDatePaymentInterestMap.put("Payment_2", paPaymentForm2);
            expectedDatePaymentInterestMap.put("Interest_2", paInterestForm2);
            expectedDatePaymentInterestMap.put("Date_3", paDateForm3);
            expectedDatePaymentInterestMap.put("Payment_3", paPaymentForm3);
            expectedDatePaymentInterestMap.put("Interest_3", paInterestForm3);
            expectedDatePaymentInterestMap.put("Date_4", paDateForm4);
            expectedDatePaymentInterestMap.put("Payment_4", paPaymentForm4);
            expectedDatePaymentInterestMap.put("Interest_4", paInterestForm4);
            expectedDatePaymentInterestMap.put("Date_5", paDateForm5);
            expectedDatePaymentInterestMap.put("Payment_5", paPaymentForm5);
            expectedDatePaymentInterestMap.put("Interest_5", paInterestForm5);
            expectedDatePaymentInterestMap.put("Date_6", paDateForm6);
            expectedDatePaymentInterestMap.put("Payment_6", paPaymentForm6);
            expectedDatePaymentInterestMap.put("Interest_6", paInterestForm6);
            expectedDatePaymentInterestMap.put("Date_7", paDateForm7);
            expectedDatePaymentInterestMap.put("Payment_7", paPaymentForm7);
            expectedDatePaymentInterestMap.put("Interest_7", paInterestForm7);
            expectedDatePaymentInterestMap.put("Date_8", paDateForm8);
            expectedDatePaymentInterestMap.put("Payment_8", paPaymentForm8);
            expectedDatePaymentInterestMap.put("Interest_8", paInterestForm8);
            boolean isVerifiedTaxPaymentData = verifyTaxPaymentData(pdfFilePath,
                    "Date Payment Interest",
                    "13. On the date of death, was the decedent a fiduciary",
                    expectedDatePaymentInterestMap);

            Map<String, String> expectedDateDescriptionAmountMap = new LinkedHashMap<>();
            expectedDateDescriptionAmountMap.put("Date_1", radDateForm1);
            expectedDateDescriptionAmountMap.put("Description_1", radDescriptionForm1);
            expectedDateDescriptionAmountMap.put("Amount_1", radAmountForm1);
            expectedDateDescriptionAmountMap.put("Date_2", radDateForm2);
            expectedDateDescriptionAmountMap.put("Description_2", radDescriptionForm2);
            expectedDateDescriptionAmountMap.put("Amount_2", radAmountForm2);
            expectedDateDescriptionAmountMap.put("Date_3", radDateForm3);
            expectedDateDescriptionAmountMap.put("Description_3", radDescriptionForm3);
            expectedDateDescriptionAmountMap.put("Amount_3", radAmountForm3);
            expectedDateDescriptionAmountMap.put("Date_4", radDateForm4);
            expectedDateDescriptionAmountMap.put("Description_4", radDescriptionForm4);
            expectedDateDescriptionAmountMap.put("Amount_4", radAmountForm4);
            expectedDateDescriptionAmountMap.put("Date_5", radDateForm5);
            expectedDateDescriptionAmountMap.put("Description_5", radDescriptionForm5);
            expectedDateDescriptionAmountMap.put("Amount_5", radAmountForm5);
            expectedDateDescriptionAmountMap.put("Date_6", radDateForm6);
            expectedDateDescriptionAmountMap.put("Description_6", radDescriptionForm6);
            expectedDateDescriptionAmountMap.put("Amount_6", radAmountForm6);
            expectedDateDescriptionAmountMap.put("Date_7", radDateForm7);
            expectedDateDescriptionAmountMap.put("Description_7", radDescriptionForm7);
            expectedDateDescriptionAmountMap.put("Amount_7", radAmountForm7);
            expectedDateDescriptionAmountMap.put("Date_8", radDateForm8);
            expectedDateDescriptionAmountMap.put("Description_8", radDescriptionForm8);
            expectedDateDescriptionAmountMap.put("Amount_8", radAmountForm8);
            expectedDateDescriptionAmountMap.put("Date_9", radDateForm9);
            expectedDateDescriptionAmountMap.put("Description_9", radDescriptionForm9);
            expectedDateDescriptionAmountMap.put("Amount_9", radAmountForm9);
            boolean isVerifiedTransactionDetails = verifyTransactionDetails(
                    pdfFilePath,
                    "Date Description Amount",
                    "B. Has notice of the additional receipts and disbursements been",
                    expectedDateDescriptionAmountMap

            );

            Map<String, String> expectedSubmittedByPetitioners = new LinkedHashMap<>();
            expectedSubmittedByPetitioners.put("Name of Petitioner1", nameOfPetitionerForm);
            expectedSubmittedByPetitioners.put("Name of Petitioner2", nameOfPetitioner2Form);
            boolean isVerifiedSubmittedByPetitionerNames = verifySubmittedByPetitionerNames(
                    pdfFilePath,
                    "in proportions, not amounts) are as follows:",
                    "Signature of Petitioner",
                    expectedSubmittedByPetitioners
            );

            List<Map<String, String>> expectedClaimants = new ArrayList<>();
            Map<String, String> claimant1 = new LinkedHashMap<>();
            claimant1.put("Claimant", Initials1Form);
            claimant1.put("Amount", Amount_of_Claim1Form);
            claimant1.put("ClaimAdmitted", Claim_Admitted1Form);
            claimant1.put("PaidInFull", Will_Claim_Be_Paid_In_Full1Form);
            expectedClaimants.add(claimant1);
            Map<String, String> claimant2 = new LinkedHashMap<>();
            claimant2.put("Claimant", Initials2Form);
            claimant2.put("Amount", Amount_of_Claim2Form);
            claimant2.put("ClaimAdmitted", Claim_Admitted2Form);
            claimant2.put("PaidInFull", Will_Claim_Be_Paid_In_Full2Form);
            expectedClaimants.add(claimant2);
            Map<String, String> claimant3 = new LinkedHashMap<>();
            claimant3.put("Claimant", Initials3Form);
            claimant3.put("Amount", Amount_of_Claim3Form);
            claimant3.put("ClaimAdmitted", Claim_Admitted3Form);
            claimant3.put("PaidInFull", Will_Claim_Be_Paid_In_Full3Form);
            expectedClaimants.add(claimant3);
            Map<String, String> claimant4 = new LinkedHashMap<>();
            claimant4.put("Claimant", Initials4Form);
            claimant4.put("Amount", Amount_of_Claim4Form);
            claimant4.put("ClaimAdmitted", Claim_Admitted4Form);
            claimant4.put("PaidInFull", Will_Claim_Be_Paid_In_Full4Form);
            expectedClaimants.add(claimant4);
            String expectedAbove = aboveAmountForm;
            String expectedAttachment = attachmentAmountForm;
            String expectedTotal = totalAmountForm;
            boolean isVerifiedClaimantsData =  verifyClaimantsData(pdfFilePath, expectedClaimants, expectedAbove, expectedAttachment, expectedTotal);

            if (!isVerifiedFileNumber || !isVerifiedCounselDetails || !isValidatedPetitionerAddressMapping || !isverifiedDateOfWill || !isVerifiedCodicilDates || !isVerifiedAdditionalPetitioners || !isVerifiedChildrenNamesAndDOBs || !isVerifiedTaxPaymentData || !isVerifiedTransactionDetails || !isVerifiedSubmittedByPetitionerNames || !isVerifiedClaimantsData) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }

            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (
                Exception e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }

    private static boolean verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName) throws IOException, AutomationException {
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

    private static boolean verifyCounselDetails(String pdfFilePath, Map<String, String> expectedDetails) throws IOException, AutomationException {
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
                extractedDetails.put("Telephone 1", clean(line.replace("Telephone:", "").trim()));
                telephoneFound = true;
            }
            // Check for "Fax" only once
            else if (!faxFound && line.startsWith("Fax:")) {
                extractedDetails.put("Telephone 2", clean(line.replace("Fax:", "").trim()));
                faxFound = true;
            }
            // Check for "Email" only once
            else if (!emailFound && line.startsWith("Email:")) {
                extractedDetails.put("Email", clean(line.replace("Email:", "").trim()));
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


    private static String

    cleanFields(String rawText, String fieldType) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        String cleanedText = rawText.trim();

        switch (fieldType.toLowerCase()) {
            case "file number":
                cleanedText = cleanedText.replaceAll("(?i)\\b(No.  )\\b", "").trim();
                break;

            case "date of will":
                cleanedText = cleanedText.replaceAll("(?i)date of will\\s*\\(if applicable\\):?\\s*", "").trim();
                break;

            case "codicil dates":
                cleanedText = cleanedText.replaceAll("(?i)^date\\(s\\) of codicil\\(s\\) \\(if applicable\\):\\s*", "").trim();
                break;

            default:
                // Generic cleanup for any other case
                cleanedText = cleanedText.replaceAll("[:,\\.\\s]+$", "").trim();
                break;
        }
        return cleanedText;
    }


    public static boolean validatePetitionerAddressMapping(String pdfFilePath, Map<String, String> expectedNameAddressMap)
            throws IOException, AutomationException {
        Map<String, String> extractedMap = extractPetitionerAddressMapping(pdfFilePath);

        int index = 1;
        for (Map.Entry<String, String> expectedEntry : expectedNameAddressMap.entrySet()) {
            String expectedName = expectedEntry.getKey().trim();
            String expectedAddress = expectedEntry.getValue().trim();

            String extractedName = "";
            String extractedAddress = "";

            // Extract values based on index order
            List<String> extractedNames = new ArrayList<>(extractedMap.keySet());
            List<String> extractedAddresses = new ArrayList<>(extractedMap.values());

            if (index - 1 < extractedNames.size()) {
                extractedName = extractedNames.get(index - 1);
                extractedAddress = extractedAddresses.get(index - 1);
            }

            String petitionerLabel = "petitioner" + index;

            // 🔍 Compare Name
            CommonSteps.logInfo("🔍 Comparing -> for " + petitionerLabel + " Name Expected: '" + expectedName + "', Extracted: '" + extractedName + "'");
            if (expectedName.equalsIgnoreCase(extractedName)) {
                CommonSteps.logInfo("✅ Validation Passed: '" + petitionerLabel + " Name' matches expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: '" + petitionerLabel + " Name' mismatch. Expected: '" + expectedName + "', Found: '" + extractedName + "'");
            }

            // 🔍 Compare Address
            CommonSteps.logInfo("🔍 Comparing -> for " + petitionerLabel + " Address Expected: '" + expectedAddress + "', Extracted: '" + extractedAddress + "'");
            if (expectedAddress.equalsIgnoreCase(extractedAddress)) {
                CommonSteps.logInfo("✅ Validation Passed: '" + petitionerLabel + " Address' matches expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: '" + petitionerLabel + " Address' mismatch. Expected: '" + expectedAddress + "', Found: '" + extractedAddress + "'");
            }
            index++;
        }
        return true;
    }

    public static Map<String, String> extractPetitionerAddressMapping(String pdfFilePath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] lines = pdfText.split("\\r?\\n");
        List<String> trimmedLines = Arrays.stream(lines).map(String::trim).collect(Collectors.toList());

        Map<String, String> nameAddressMap = new LinkedHashMap<>();

        for (int i = 0; i < trimmedLines.size(); i++) {
            String line = trimmedLines.get(i);
            if (line.startsWith("Name:") && i + 2 < trimmedLines.size()) {
                String namesLine = trimmedLines.get(i).replace("Name:", "").trim();
                String addressLine = trimmedLines.get(i + 1).replace("Address:", "").trim();
                String cityStateLine = trimmedLines.get(i + 2).trim();

                // Split by Jr./Sr. endings or two spaces
                List<String> names = Arrays.asList(namesLine.split("(?<=, (Jr\\.|Sr\\.|II|III|IV|V))\\s+"));
                List<String> streets = Arrays.asList(addressLine.split("\\s{2,}|(?<=Street|Avenue|Road|Lane|Drive)\\s+"));
                List<String> cities = Arrays.asList(cityStateLine.split("(?<=\\d{5})\\s*"));

                for (int j = 0; j < names.size(); j++) {
                    String name = names.get(j).trim();
                    String street = j < streets.size() ? streets.get(j).trim() : "";
                    String cityState = j < cities.size() ? cities.get(j).trim() : "";

                    String fullAddress = (street + " " + cityState).trim();
                    nameAddressMap.put(name, fullAddress);

                    CommonSteps.logInfo("✅ " + name + " -> " + fullAddress);
                }
                break; // Done once we parse the first "Name:" block
            }
        }

        return nameAddressMap;
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
                    String cleaned = cleanFields(currentLine, fieldName);
                    if (!cleaned.isEmpty()) {
                        String[] splitDates = cleaned.split("\\s+"); // Split multiple dates by spaces
                        extractedValues.addAll(Arrays.asList(splitDates));
                    }
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

    private static boolean verifyAdditionalPetitioners(String pdfFilePath, String beforeLine, String afterLine, Map<String, String> expectedValues) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;
        for (int i = 0; i < allLines.length; i++) {
            if (allLines[i].trim().equalsIgnoreCase(beforeLine.trim())) {
                startIndex = i;
            }
            if (allLines[i].trim().equalsIgnoreCase(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ 'Before' or 'After' line not found for Additional Petitioners.");
        }

        List<String> extractedNames = new ArrayList<>();
        List<String> extractedAddresses = new ArrayList<>();

        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();

            if (line.startsWith("Name:")) {
                extractedNames.add(line.replace("Name:", "").trim());
            }


            if (line.startsWith("Address:") && i + 1 < endIndex) {
                String line1 = line.replace("Address:", "").trim();
                String line2 = allLines[i + 1].trim();
                extractedAddresses.add((line1 + " " + line2).trim());
            }

        }

        // ✅ Build expected sets
        Set<String> expectedNames = new HashSet<>();
        Set<String> expectedAddresses = new HashSet<>();

        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            if (entry.getKey().toLowerCase().contains("name")) {
                expectedNames.add(entry.getValue().trim().toLowerCase());
            } else if (entry.getKey().toLowerCase().contains("address")) {
                expectedAddresses.add(entry.getValue().trim().toLowerCase());
            }
        }

        // ✅ Build extracted sets
        Set<String> extractedNamesSet = extractedNames.stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> extractedAddressesSet = extractedAddresses.stream().map(String::toLowerCase).collect(Collectors.toSet());

        // 🔍 Log comparisons
        for (int i = 0; i < extractedNames.size(); i++) {
            String name = extractedNames.get(i);
            String address = (i < extractedAddresses.size()) ? extractedAddresses.get(i) : "Address Not Found";

            CommonSteps.logInfo("O] ✅ " + name + " -> " + address);
        }

        CommonSteps.logInfo("🔍 Comparing extracted names set with expected names set");
        if (!expectedNames.equals(extractedNamesSet)) {
            throw new AutomationException("❌ Validation Failed: Extracted names do not match expected names.");
        } else {
            CommonSteps.logInfo("✅ Validation Passed: All names matched expected values.");
        }

        CommonSteps.logInfo("🔍 Comparing extracted addresses set with expected addresses set...");
        if (!expectedAddresses.equals(extractedAddressesSet)) {
            throw new AutomationException("❌ Validation Failed: Extracted addresses do not match expected addresses.");
        } else {
            CommonSteps.logInfo("✅ Validation Passed: All addresses matched expected values.");
        }
        return true;
    }

    private static boolean verifyChildrenNamesAndDOBs(String pdfFilePath,
                                                      String beforeLine,
                                                      String afterLine,
                                                      Map<String, String> expectedNameDobMap) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1;
        int endIndex = -1;

        // Find start and end index using beforeLine and afterLine
        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();
            if (line.equalsIgnoreCase(beforeLine.trim())) {
                startIndex = i;
            } else if (line.equalsIgnoreCase(afterLine.trim())) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            throw new AutomationException("❌ Could not find valid range between beforeLine and afterLine.");
        }

        // Extract children name + DOB pairs
        Map<String, String> extractedMap = new LinkedHashMap<>();
        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();
            if (line.isEmpty() || line.contains("Name:") || line.contains("Date of Birth:")) continue;

            String[] tokens = line.trim().split("\\s+");
            if (tokens.length >= 2) {
                String dob = tokens[tokens.length - 1];
                String name = line.replace(dob, "").trim();
                extractedMap.put(name, dob);
            }
        }

        // ✅ Compare with expected map
        for (Map.Entry<String, String> expectedEntry : expectedNameDobMap.entrySet()) {
            String expectedName = expectedEntry.getKey().trim();
            String expectedDob = expectedEntry.getValue().trim();

            if (!extractedMap.containsKey(expectedName)) {
                throw new AutomationException("❌ Child name not found in PDF: " + expectedName);
            }

            String actualDob = extractedMap.get(expectedName).trim();
            if (!actualDob.equals(expectedDob)) {
                throw new AutomationException("❌ DOB mismatch for '" + expectedName + "' → Expected: " + expectedDob + ", Found: " + actualDob);
            }

            CommonSteps.logInfo("✅ Verified: " + expectedName + " → DOB: " + actualDob);
        }
        return true;
    }

    public static boolean verifyTaxPaymentData(String pdfFilePath,
                                               String beforeLine,
                                               String afterLine,
                                               Map<String, String> expectedMap) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();
            if (line.equalsIgnoreCase(beforeLine.trim())) {
                startIndex = i;
            } else if (line.startsWith(afterLine.trim())) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            throw new AutomationException("❌ Could not find valid data block between specified lines.");
        }

        // Extract data from lines between startIndex and endIndex
        Map<String, String> actualMap = new LinkedHashMap<>();

        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length >= 3) {
                String date = parts[0].trim();
                String payment = parts[1].trim();
                String interest = parts[2].trim();

                int index = (i - startIndex);
                actualMap.put("Date_" + index, date);
                actualMap.put("Payment_" + index, payment);
                actualMap.put("Interest_" + index, interest);
            }
        }

        // Validate actualMap vs expectedMap
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();
            String actualValue = actualMap.get(expectedKey);

            if (!expectedValue.equals(actualValue)) {
                throw new AutomationException("❌ Mismatch for key: " + expectedKey +
                        "\nExpected: " + expectedValue +
                        "\nFound   : " + actualValue);
            } else {
                CommonSteps.logInfo("✅ Verified" +
                        " → " + expectedKey + ": " + actualValue);
            }
        }
        return true;
    }

    public static boolean verifyTransactionDetails(String pdfFilePath,
                                                   String beforeLine,
                                                   String afterLine,
                                                   Map<String, String> expectedMap) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();
            if (line.equalsIgnoreCase(beforeLine.trim())) {
                startIndex = i;
            } else if (line.startsWith(afterLine.trim())) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            throw new AutomationException("❌ Could not find valid transaction data block between specified lines.");
        }

        Map<String, String> actualMap = new LinkedHashMap<>();
        int rowCounter = 1;

        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();
            if (line.isEmpty()) continue;

            // Extracting date, description, and amount
            String[] tokens = line.split("\\s+");
            if (tokens.length >= 3) {
                String date = tokens[0].trim();
                String amount = tokens[tokens.length - 1].trim();
                StringBuilder descriptionBuilder = new StringBuilder();
                for (int j = 1; j < tokens.length - 1; j++) {
                    descriptionBuilder.append(tokens[j]).append(" ");
                }
                String description = descriptionBuilder.toString().trim();

                actualMap.put("Date_" + rowCounter, date);
                actualMap.put("Description_" + rowCounter, description);
                actualMap.put("Amount_" + rowCounter, amount);
                rowCounter++;
            }
        }

        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();
            String actualValue = actualMap.get(expectedKey);

            if (!expectedValue.equals(actualValue)) {
                throw new AutomationException("❌ Mismatch for key: " + expectedKey +
                        "\nExpected: " + expectedValue +
                        "\nFound   : " + actualValue);
            } else {
                CommonSteps.logInfo("✅ Verified → " + expectedKey + ": " + actualValue);
            }
        }
        return true;
    }

    private static boolean verifySubmittedByPetitionerNames(String pdfFilePath,
                                                            String beforeLine,
                                                            String afterLine,
                                                            Map<String, String> expectedPetitionerMap)
            throws IOException, AutomationException {

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("[INFO] Line " + (i + 1) + ": " + allLines[i].trim());
        }

        // Track all occurrences of "Signature of Petitioner"
        List<Integer> signatureIndexes = new ArrayList<>();
        for (int i = 0; i < allLines.length; i++) {
            if (allLines[i].trim().equalsIgnoreCase("Signature of Petitioner")) {
                signatureIndexes.add(i);
            }
        }

        if (signatureIndexes.size() < 2) {
            throw new AutomationException("❌ Less than 2 occurrences of 'Signature of Petitioner' found in the PDF.");
        }

        // Start processing from the second occurrence
        int secondSignatureIndex = signatureIndexes.get(1);

        // We'll go backwards in chunks of 3: [name line, "Name of Petitioner", "Signature of Petitioner"]
        List<String> extractedNames = new ArrayList<>();
        for (int i = secondSignatureIndex; i >= 2; i--) {
            String line3 = allLines[i].trim();
            String line2 = allLines[i - 1].trim();
            String line1 = allLines[i - 2].trim();

            if (line2.equalsIgnoreCase("Name of Petitioner") &&
                    line3.equalsIgnoreCase("Signature of Petitioner") &&
                    !line1.isEmpty()) {
                extractedNames.add(0, line1); // add to beginning to maintain order
                i = i - 2; // move past this group
            }
        }

        CommonSteps.logInfo("✅ Extracted petitioner names: " + extractedNames);

        if (extractedNames.size() != expectedPetitionerMap.size()) {
            throw new AutomationException("❌ Petitioner count mismatch. Expected: " +
                    expectedPetitionerMap.size() + ", Found: " + extractedNames.size());
        }

        // Sort map keys to preserve order like Petitioner1, Petitioner2
        List<String> sortedKeys = expectedPetitionerMap.keySet()
                .stream()
                .sorted(Comparator.comparing(s -> s.replaceAll("\\D+", "")))
                .collect(Collectors.toList());

        for (int i = 0; i < sortedKeys.size(); i++) {
            String key = sortedKeys.get(i);
            String expected = expectedPetitionerMap.get(key);

            if (expected == null) {
                throw new AutomationException("❌ Expected value missing for key: " + key);
            }

            expected = expected.trim();
            String actual = extractedNames.get(i).trim();

            CommonSteps.logInfo("🔍 Comparing " + key + " -> Expected: '" + expected + "', Extracted: '" + actual + "'");

            if (!expected.equalsIgnoreCase(actual)) {
                throw new AutomationException("❌ Mismatch in " + key + "\nExpected: " + expected + "\nFound:    " + actual);
            }
        }

        CommonSteps.logInfo("✅ All petitioner names verified successfully.");
        return true;
    }

    private static boolean verifyClaimantsData(String pdfFilePath,
                                           List<Map<String, String>> expectedClaimants,
                                           String expectedAbove,
                                           String expectedAttachment,
                                           String expectedTotal) throws IOException, AutomationException {

        List<String> lines;
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            lines = Arrays.asList(text.split("\\r?\\n"));
        }

        List<String> dataLines = new ArrayList<>();
        boolean capture = false;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.equalsIgnoreCase("Full?")) {
                capture = true;
                continue;
            }

            if (trimmed.startsWith("If the estate is insolvent") || trimmed.startsWith("20 Pa.C.S.")) {
                break;
            }

            if (capture && !trimmed.isEmpty()) {
                dataLines.add(trimmed);
            }
        }

        // Split claim rows and summary rows
        List<String> claimLines = new ArrayList<>();
        String above = "", attachment = "", total = "";

        for (String line : dataLines) {
            if (line.startsWith("Above")) {
                above = line.replace("Above", "").trim();
            } else if (line.startsWith("Attachment")) {
                attachment = line.replace("Attachment", "").trim();
            } else if (line.startsWith("Total")) {
                total = line.replace("Total", "").trim();
            } else {
                claimLines.add(line);
            }
        }

        if (claimLines.size() != expectedClaimants.size()) {
            throw new AutomationException("❌ Mismatch in number of claimants. Expected: " +
                    expectedClaimants.size() + ", Found: " + claimLines.size());
        }

        int rowNumber = 1;
        for (int i = 0; i < claimLines.size(); i++) {
            String[] parts = claimLines.get(i).split("\\s+");
            if (parts.length < 4) {
                throw new AutomationException("❌ Invalid data format in claimant row: " + claimLines.get(i));
            }

            String initials = parts[0];
            String amount = parts[1];
            String claimAdmitted = parts[2];
            String paidInFull = parts[3];

            // Get expected data from the map for the current claimant
            Map<String, String> expected = expectedClaimants.get(i);

            // Perform manual validation and logging
            if (!initials.equals(expected.get("Claimant"))) {
                throw new AutomationException("❌ Mismatch in Claimant_" + rowNumber + ":\nExpected: " + expected.get("Claimant") + "\nFound   : " + initials);
            } else {
                CommonSteps.logInfo("✅ Verified → Claimant_" + rowNumber + ": " + initials);
            }

            if (!amount.equals(expected.get("Amount"))) {
                throw new AutomationException("❌ Mismatch in Amount_" + rowNumber + ":\nExpected: " + expected.get("Amount") + "\nFound   : " + amount);
            } else {
                CommonSteps.logInfo("✅ Verified → Amount_" + rowNumber + ": " + amount);
            }

            if (!claimAdmitted.equals(expected.get("ClaimAdmitted"))) {
                throw new AutomationException("❌ Mismatch in ClaimAdmitted_" + rowNumber + ":\nExpected: " + expected.get("ClaimAdmitted") + "\nFound   : " + claimAdmitted);
            } else {
                CommonSteps.logInfo("✅ Verified → ClaimAdmitted_" + rowNumber + ": " + claimAdmitted);
            }

            if (!paidInFull.equals(expected.get("PaidInFull"))) {
                throw new AutomationException("❌ Mismatch in PaidInFull_" + rowNumber + ":\nExpected: " + expected.get("PaidInFull") + "\nFound   : " + paidInFull);
            } else {
                CommonSteps.logInfo("✅ Verified → PaidInFull_" + rowNumber + ": " + paidInFull);
            }

            rowNumber++;
        }

        // Validate summary fields: Above, Attachment, Total
        if (!above.replace(",", "").equals(expectedAbove)) {
            throw new AutomationException("❌ Mismatch in Above:\nExpected: " + expectedAbove + "\nFound   : " + above.replace(",", ""));
        } else {
            CommonSteps.logInfo("✅ Verified → Above: " + above);
        }

        if (!attachment.replace(",", "").equals(expectedAttachment)) {
            throw new AutomationException("❌ Mismatch in Attachment:\nExpected: " + expectedAttachment + "\nFound   : " + attachment.replace(",", ""));
        } else {
            CommonSteps.logInfo("✅ Verified → Attachment: " + attachment);
        }

        if (!total.replace(",", "").equals(expectedTotal)) {
            throw new AutomationException("❌ Mismatch in Total:\nExpected: " + expectedTotal + "\nFound   : " + total.replace(",", ""));
        } else {
            CommonSteps.logInfo("✅ Verified → Total: " + total);
        }
        return true;
    }
}
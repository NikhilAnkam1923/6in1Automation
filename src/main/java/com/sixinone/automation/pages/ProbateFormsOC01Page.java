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
import org.stringtemplate.v4.ST;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private static final String HEADER_COUNTY_NAME = "//input[contains(@class,'county')]";
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
    private static final String BENE_INTEREST_FORM = "//td[@class='borderNewLeft2 borderNewBottom2']//p[@class='p8-1 ft27-2 newstyle']//input";
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
    private static final String SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_11 = "//div[@class='modal-content']//div[contains(text(),'Signature of')]";
    private static final String SAVE_BTN = "//button[text()='Save']";
    private static final String NAME_OF_CORPORATE_FIDUCIARY = "//input[@name='fullname']";
    private static final String NAME_OF_REPRESENTATIVE = "//input[@name='contact_fullname_title']";
    private static final String PERSON_CLEAR_SELECTION_BTN = "//p[contains(text(),'Capacity')]/following-sibling::div//button[text()='Clear Selection']";
    private static final String CORPORATE_FIDUCIARY_CLEAR_SELECTION_BTN = "//p[contains(text(),'Corporate Fiduciary (if applicable)')]//button[text()='Clear Selection']";
    private static final String NAME_OF_PETITIONER_PAGE_10 = "//p[text()='Name of Petitioner']/preceding-sibling::p//input";
    private static final String WARNING_MESSAGE = "//div[@role='alert']//div[text()='Please select the capacity before selecting the contact.']";
    private static final String NOTIFICATION_DELETE_BUTTON = "//button[@class='btn btn-danger' and text()='Delete']";
    private static final String CLAIMANT_INITIALS_ERR_MSG = "//div[@class='invalid-feedback' and text()='Initials are required when Name and Address are not provided.']";
    private static final String CLAIMANT_NAME_ERR_MSG = "//div[@class='invalid-feedback' and text()='Name is required when Initials are not provided.']";
    private static final String CLAIMANT_ADDRESS_ERR_MSG = "//div[@class='invalid-feedback' and text()='Address is required when Initials are not provided.']";
    private static final String CLAIMANT_INITIALS_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='1']";
    private static final String CLAIMANT_AMOUNT_LIST = "//div[@class='modal-dialog modal-xl']//td[position()='3']";
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
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input[@aria-label='Filter']";
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

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    static String downloadedFileName;

    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneRelationship = new ArrayList<>();
    private static final List<String> beneInterest = new ArrayList<>();
    private static final List<String> claimantInitialsForm = new ArrayList<>();
    private static final List<String> claimAmountForm = new ArrayList<>();
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
    static String Fiduciary5Form;
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

        countyNameForm = countyNameField.getAttribute("value");
        estateNameForm = estateNameField.getAttribute("value");

        String enteredCountyName = getEstateValue("DomicileCountry");
        String enteredEstateName = getEstateValue("DisplayName");

        if (!enteredCountyName.equals(countyNameForm)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm);
        }

        if (!enteredEstateName.equals(estateNameForm)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameForm);
        }

        verifyFieldIsNotEditable(HEADER_COUNTY_NAME);
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

        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();
    }

    public void userClicksOnNameOfCounselField() throws AutomationException {
        scrollToElement(NAME_OF_COUNSEL_FIELD);
        driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD).click();
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




    public void verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm() throws AutomationException, IOException, ParseException {
//        petitioner1AddressLine1Form = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.addressLine1").toString();
//        String fiduciary1City = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.city").toString() + ",";
//        String fiduciary1StateCode = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.stateCode").toString();
//        String fiduciary1Zip = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.zip").toString();
//        petitioner1CityStateCodeZipForm = fiduciary1City + " " + fiduciary1StateCode + " " + fiduciary1Zip;
//
//        petitioner2AddressLine1Form = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.addressLine1").toString();
//        String fiduciary2City = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.city").toString() + ",";
//        String fiduciary2StateCode = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.stateCode").toString();
//        String fiduciary2Zip = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.zip").toString();
//        petitioner2CityStateCodeZipForm = fiduciary2City + " " + fiduciary2StateCode + " " + fiduciary2Zip;

//        String petitioner1Name = "Daniel Robert Harris, Sr."; // Or from test data
        String key1 = findFiduciaryKeyByName(Fiduciary1Form, jsonData);

        JSONObject contact1 = (JSONObject) jsonData.get(key1);
        petitioner1AddressLine1Form = contact1.get("addressLine1").toString();
        String city1 = contact1.get("city").toString() + ",";
        String stateCode1 = contact1.get("stateCode").toString();
        String zip1 = contact1.get("zip").toString();
        petitioner1CityStateCodeZipForm = city1 + " " + stateCode1 + " " + zip1;

// Petitioner 2 - Dynamic retrieval
//        String petitioner2Name = "thetaCorporation"; // Or from test data
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
//        petitioner3AddressLine1Form = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.addressLine1").toString();
//        String fiduciary3City = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.city").toString() + ",";
//        String fiduciary3StateCode = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.stateCode").toString();
//        String fiduciary3Zip = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.zip").toString();
//        petitioner3CityStateCodeZipForm = fiduciary3City + " " + fiduciary3StateCode + " " + fiduciary3Zip;
//
//        petitioner4AddressLine1Form = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.addressLine1").toString();
//        String fiduciary4City = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.city").toString() + ",";
//        String fiduciary4StateCode = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.stateCode").toString();
//        String fiduciary4Zip = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.zip").toString();
//        petitioner4CityStateCodeZipForm = fiduciary4City + " " + fiduciary4StateCode + " " + fiduciary4Zip;

        JSONObject jsonData = CommonUtil.getJSONObject(""); // Load full JSON only once

// Petitioner 3 - Dynamic retrieval
//        String petitioner3Name = "Some Individual Name or Entity for Fiduciary5"; // Replace with actual name
        String key3 = findFiduciaryKeyByName(Fiduciary3Form, jsonData);

        JSONObject contact3 = (JSONObject) jsonData.get(key3);
        String address3Line1 = contact3.get("addressLine1").toString();
        String address3Line2 = contact3.get("addressLine2").toString();
        String city3 = contact3.get("city").toString() + ",";
        String stateCode3 = contact3.get("stateCode").toString();
        String zip3 = contact3.get("zip").toString();
        petitioner3CityStateCodeZipForm = city3 + " " + stateCode3 + " " + zip3;
        petitioner3AddressLine1Form = address3Line1 + ", " + address3Line2;

// Petitioner 4 - Dynamic retrieval
//        String petitioner4Name = "Some Individual Name or Entity for Fiduciary4"; // Replace with actual name
        String key4 = findFiduciaryKeyByName(Fiduciary4Form, jsonData);

        JSONObject contact4 = (JSONObject) jsonData.get(key4);
//        petitioner4AddressLine1Form = contact4.get("addressLine1").toString();
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

    public void verifyMultipleChildrenAndDoBCanBeAdded() throws AutomationException, IOException, ParseException {
        dateDataForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm1").toString();
        dateDataForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm2").toString();
        dateDataForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm3").toString();
        dateDataForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm4").toString();
        dateDataForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm5").toString();
        dateDataForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm6").toString();
        dateDataForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm7").toString();
        dateDataForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm8").toString();
        dateDataForm9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm9").toString();
        dateDataForm10 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.dateDataForm10").toString();

        childrenDetailDataForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm1").toString();
        childrenDetailDataForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm2").toString();
        childrenDetailDataForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm3").toString();
        childrenDetailDataForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm4").toString();
        childrenDetailDataForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm5").toString();
        childrenDetailDataForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm6").toString();
        childrenDetailDataForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm7").toString();
        childrenDetailDataForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm8").toString();
        childrenDetailDataForm9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm9").toString();
        childrenDetailDataForm10 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.childrenDetailDataForm10").toString();

        List<String> dateDataForm = Arrays.asList(
                dateDataForm1, dateDataForm2, dateDataForm3, dateDataForm4, dateDataForm5,
                dateDataForm6, dateDataForm7, dateDataForm8, dateDataForm9, dateDataForm10
        );

        List<String> childrenDetailDataForm = Arrays.asList(
                childrenDetailDataForm1, childrenDetailDataForm2, childrenDetailDataForm3, childrenDetailDataForm4, childrenDetailDataForm5,
                childrenDetailDataForm6, childrenDetailDataForm7, childrenDetailDataForm8, childrenDetailDataForm9, childrenDetailDataForm10
        );

        for (int i = 0; i < 10; i++) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
            WebElement childrenDetailField = driverUtil.getWebElementAndScroll(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
            childrenDetailField.sendKeys(childrenDetailDataForm.get(i));
            childrenDetailField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i)));
            dateField.click();
            dateField.clear();
            dateField.sendKeys(dateDataForm.get(i));
            dateField.sendKeys(Keys.TAB);

            WebDriverUtil.waitForAWhile();
            String actualChildrenDetail = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i))).getAttribute("value");
            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i))).getAttribute("value");

            if (!actualChildrenDetail.equals(childrenDetailDataForm.get(i))) {
                throw new AutomationException("Reason field did not accept the entered text correctly. Expected: " + childrenDetailDataForm.get(i) + ", Found: " + actualChildrenDetail);
            }

            if (!actualDate.equals(dateDataForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered date correctly. Expected: " + dateDataForm.get(i) + ", Found: " + actualDate);
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
            beneInterest.add(beneInterestPage4Fields.get(i).getAttribute("value"));
        }

        switchToPage(5);

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

    public void verifyAttachmentIsDisplayedOnPage4() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void verifySameAttachmentIsDisplayedOnPage5() throws AutomationException {
        verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
    }

    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
        scrollToElement(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN)).click();
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
        WebDriverUtil.waitForAWhile();
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
            System.out.println("bene keys: "+key);
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
            System.out.println("bene keys: "+key);
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
        paDateForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm1").toString();
        paDateForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm2").toString();
        paDateForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm3").toString();
        paDateForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm4").toString();
        paDateForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm5").toString();
        paDateForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm6").toString();
        paDateForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm7").toString();
        paDateForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PADateForm8").toString();

        paPaymentForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm1").toString();
        paPaymentForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm2").toString();
        paPaymentForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm3").toString();
        paPaymentForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm4").toString();
        paPaymentForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm5").toString();
        paPaymentForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm6").toString();
        paPaymentForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm7").toString();
        paPaymentForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAPaymentForm8").toString();

        paInterestForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm1").toString();
        paInterestForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm2").toString();
        paInterestForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm3").toString();
        paInterestForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm4").toString();
        paInterestForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm5").toString();
        paInterestForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm6").toString();
        paInterestForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm7").toString();
        paInterestForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.PAInterestForm8").toString();

        List<String> paDateForm = Arrays.asList(
                paDateForm1, paDateForm2, paDateForm3, paDateForm4, paDateForm5,
                paDateForm6, paDateForm7, paDateForm8
        );

        List<String> paPaymentForm = Arrays.asList(
                paPaymentForm1, paPaymentForm2, paPaymentForm3, paPaymentForm4, paPaymentForm5,
                paPaymentForm6, paPaymentForm7, paPaymentForm8
        );

        List<String> paInterestForm = Arrays.asList(
                paInterestForm1, paInterestForm2, paInterestForm3, paInterestForm4, paInterestForm5,
                paInterestForm6, paInterestForm7, paInterestForm8
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
        }

    }

    public void verifyDateDescriptionAndAmountCanBeAddedInTheReceiptsDisbursementsTable() throws IOException, ParseException, AutomationException {
        radDateForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm1").toString();
        radDateForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm2").toString();
        radDateForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm3").toString();
        radDateForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm4").toString();
        radDateForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm5").toString();
        radDateForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm6").toString();
        radDateForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm7").toString();
        radDateForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm8").toString();
        radDateForm9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDateForm9").toString();

        radDescriptionForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm1").toString();
        radDescriptionForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm2").toString();
        radDescriptionForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm3").toString();
        radDescriptionForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm4").toString();
        radDescriptionForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm5").toString();
        radDescriptionForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm6").toString();
        radDescriptionForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm7").toString();
        radDescriptionForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm8").toString();
        radDescriptionForm9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADDescriptionForm9").toString();

        radAmountForm1 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm1").toString();
        radAmountForm2 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm2").toString();
        radAmountForm3 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm3").toString();
        radAmountForm4 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm4").toString();
        radAmountForm5 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm5").toString();
        radAmountForm6 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm6").toString();
        radAmountForm7 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm7").toString();
        radAmountForm8 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm8").toString();
        radAmountForm9 = CommonUtil.getJsonPath("OC01Form").get("OC01Form.RADAmountForm9").toString();

        List<String> radDateForm = Arrays.asList(
                radDateForm1, radDateForm2, radDateForm3, radDateForm4, radDateForm5,
                radDateForm6, radDateForm7, radDateForm8, radDateForm9
        );

        List<String> radDescriptionForm = Arrays.asList(
                radDescriptionForm1, radDescriptionForm2, radDescriptionForm3, radDescriptionForm4, radDescriptionForm5,
                radDescriptionForm6, radDescriptionForm7, radDescriptionForm8, radDescriptionForm9
        );

        List<String> radAmountForm = Arrays.asList(
                radAmountForm1, radAmountForm2, radAmountForm3, radAmountForm4, radAmountForm5,
                radAmountForm6, radAmountForm7, radAmountForm8, radAmountForm9
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

            WebDriverUtil.waitForAWhile(3);
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
        }
    }

    public void verifyReserveRequestAmountCanBeAdded() throws IOException, ParseException, AutomationException {
        reserveRequestAmountForm = CommonUtil.getJsonPath("OC01Form").get("OC01Form.ReserveRequestAmount").toString();

        WebDriverUtil.waitForAWhile();
        scrollToElement(RESERVE_REQUEST_AMOUNT);
        WebElement amountField = DriverFactory.drivers.get().findElement(By.xpath(RESERVE_REQUEST_AMOUNT));
        amountField.sendKeys(reserveRequestAmountForm);
        amountField.sendKeys(Keys.TAB);

        WebDriverUtil.waitForAWhile();
        String actualAmount = DriverFactory.drivers.get().findElement(By.xpath(RESERVE_REQUEST_AMOUNT)).getAttribute("value");

        if (!actualAmount.equals(reserveRequestAmountForm)) {
            throw new AutomationException("Reserve Request Amount field did not accept the entered amount correctly. Expected: " + reserveRequestAmountForm + ", Found: " + actualAmount);
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
            System.out.println("⚠️ Field not cleared after max attempts. Value: " + element.getAttribute("value"));
        }
    }


    public void userResetsTheRWForm() throws AutomationException {
        //page 1
        switchToPage(1);
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberField.clear();
        fileNumberField.sendKeys(initialFileNumber);
        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();


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
//        for (int i = 9; i >= 0; i--) {
//            WebDriverUtil.waitForAWhile(2);
//            scrollToElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
//            clearField(String.format(DATE_FIELDS_PAGE_3, i));
//
//            WebElement fieldElement = driverUtil.getWebElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
//            fieldElement.click();
//            fieldElement.sendKeys(Keys.CONTROL + "a");
//            fieldElement.sendKeys(Keys.BACK_SPACE);
//            fieldElement.sendKeys(Keys.TAB);
//        }
        for (int i = 9; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));

            // Clear the DATE field reliably
//            WebElement dateField = driverUtil.getWebElement(String.format(DATE_FIELDS_PAGE_3, i));
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELDS_PAGE_3, i)));
            clearFieldUntilEmpty(dateField);

            // Clear CHILDREN DETAILS field manually using CTRL+A + BACKSPACE
//            WebElement fieldElement = driverUtil.getWebElement(String.format(CHILDREN_DETAILS_FIELDS_PAGE_3, i));
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
        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_ALLOWED_YES_OPT)).click();
        DriverFactory.drivers.get().findElement(By.xpath(FAMILY_EXEMPTION_CLAIMED_YES_OPT)).click();
        scrollToElement(String.format(PA_DATE_FIELDS, 7));
        WebDriverUtil.waitForAWhile();
//        for (int i = 7; i >= 0; i--) {
//            WebDriverUtil.waitForAWhile(2);
//            scrollToElement(String.format(PA_DATE_FIELDS, i));
//
//            driverUtil.getWebElement(String.format(PA_INTEREST_FIELDS, i)).clear();
//
//            driverUtil.getWebElement(String.format(PA_PAYMENT_FIELDS, i)).clear();
//
//            DriverFactory.drivers.get().findElement(By.xpath(String.format(PA_DATE_FIELDS, i))).clear();
//        }
        for (int i = 7; i >= 0; i--) {
            WebDriverUtil.waitForAWhile(2);
            scrollToElement(String.format(PA_DATE_FIELDS, i));

            // Clear PA_INTEREST_FIELDS
            WebElement interestField = driverUtil.getWebElement(String.format(PA_INTEREST_FIELDS, i));
            clearFieldUntilEmpty(interestField);

            // Clear PA_PAYMENT_FIELDS
            WebElement paymentField = driverUtil.getWebElement(String.format(PA_PAYMENT_FIELDS, i));
            clearFieldUntilEmpty(paymentField);

            // Clear PA_DATE_FIELDS
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
//        for (int i = 8; i >= 0; i--) {
//            WebDriverUtil.waitForAWhile(2);
//            scrollToElement(String.format(RAD_DATE_FIELDS, i));
//            driverUtil.getWebElement(String.format(RAD_AMOUNT_FIELDS, i)).clear();
//            driverUtil.getWebElement(String.format(RAD_DESCRIPTION_FIELDS, i)).clear();
//            DriverFactory.drivers.get().findElement(By.xpath(String.format(RAD_DATE_FIELDS, i))).clear();
//        }
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
        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);
        int[] contactsToEnterProportion = {0, 2, 3, 4};
        for (int i = 0; i < contactsToEnterProportion.length; i++) {
            int index = contactsToEnterProportion[i];

            if (index < proportionFields.size()) {
                WebElement inputField = proportionFields.get(index);
                inputField.clear();
            }
        }
        driverUtil.getWebElement(CLOSE_BTN).click();
//        WebDriverUtil.waitForAWhile();
//        scrollToElement(PERSON_CLEAR_SELECTION_BTN);
//        driverUtil.getWebElement(PERSON_CLEAR_SELECTION_BTN).click();
//        WebDriverUtil.waitForAWhile(2);

    }

    public void verify1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner() throws AutomationException {
        WebElement signOfPetitionerField = driverUtil.getWebElement(SIGN_OF_PETITIONER_PAGE_11);
        String petitionerNamePage11 = signOfPetitionerField.getAttribute("value");
        String expectedPetitionerName = Fiduciaries.get(0);

        if (!petitionerNamePage11.equals(expectedPetitionerName)) {
            throw new AutomationException("1st individual petitioner selected on page 2 is not displayed under individual petitioner. Expected: " + expectedPetitionerName + " ,Found: " + petitionerNamePage11);
        }
    }

    public void verifyAnyOneOfTheFiduciaryContactsCanBeSelected() throws AutomationException, IOException, ParseException {
        nameOfCorporateFiduciary = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.entityName").toString();

        String corporateFiduciaryFirstName = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.firstName").toString();
        String corporateFiduciaryLastName = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.lastName").toString() + ",";
        String corporateFiduciaryMiddleName = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.middleName").toString();
        String corporateFiduciarySuffix = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.suffix").toString();

        corporateFiduciaryFullNameForm = corporateFiduciaryFirstName + " " + corporateFiduciaryMiddleName + " " + corporateFiduciaryLastName + " " + corporateFiduciarySuffix;

        scrollToElement(NAME_OF_CORPORATE_FIDUCIARY);
        driverUtil.getWebElement(NAME_OF_CORPORATE_FIDUCIARY).click();
        WebDriverUtil.waitForAWhile();

        WebElement corporateFiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, nameOfCorporateFiduciary));

        corporateFiduciaryToSelect.click();

        if (!corporateFiduciaryToSelect.isSelected()) {
            throw new AutomationException("Unable to select the corporate fiduciary contact.");
        }

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Corporate Fiduciary updated successfully.")));

        String actualNameOfCorporateFiduciary = driverUtil.getWebElement(NAME_OF_CORPORATE_FIDUCIARY).getAttribute("value");
        String actualNameOfRepresentative = driverUtil.getWebElement(NAME_OF_REPRESENTATIVE).getAttribute("value");

        if (!actualNameOfCorporateFiduciary.equals(nameOfCorporateFiduciary)) {
            throw new AutomationException("Name of Corporate Fiduciary not fetched correctly. Expected: " + nameOfCorporateFiduciary + " ,Found: " + actualNameOfCorporateFiduciary);
        }

        if (!actualNameOfRepresentative.equals(corporateFiduciaryFullNameForm)) {
            throw new AutomationException("Name of Representative not fetched correctly. Expected: " + corporateFiduciaryFullNameForm + " ,Found: " + actualNameOfRepresentative);
        }
    }

    public void userClicksOnNameOfPetitionerField() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        scrollToElement(NAME_OF_PETITIONER_PAGE_10);
        driverUtil.getWebElement(NAME_OF_PETITIONER_PAGE_10).click();
    }

    public void verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected() throws AutomationException, IOException, ParseException {
        String fiduciaryFirstName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.firstName").toString();
        String fiduciaryLastName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.lastName").toString() + ",";
        String fiduciaryMiddleName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.middleName").toString();
        String fiduciarySuffix = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.suffix").toString();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Fiduciary")) {
            throw new AutomationException("Fiduciary type of contacts are not displayed.");
        }

        String nameOFPetitioner = fiduciaryFirstName + " " + fiduciaryMiddleName + " " + fiduciaryLastName + " " + fiduciarySuffix;

        WebElement fiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, nameOFPetitioner));

        WebDriverUtil.waitForAWhile();
        fiduciaryToSelect.click();

        if (!fiduciaryToSelect.isSelected()) {
            throw new AutomationException("Unable to select the fiduciary contact.");
        }

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Individual Fiduciary updated successfully.")));

        nameOFPetitionerPage10Form = driverUtil.getWebElement(NAME_OF_PETITIONER_PAGE_10).getAttribute("value");

        if (!nameOFPetitionerPage10Form.equals(nameOFPetitioner)) {
            throw new AutomationException("Name of Corporate Fiduciary not fetched correctly. Expected: " + nameOFPetitioner + " ,Found: " + nameOFPetitionerPage10Form);
        }
    }

    public void verifyAttorneyTypeOfContactAreDisplayedInTheListAndCanBeSelected() throws IOException, ParseException, AutomationException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney2").get("attorney2.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney2").get("attorney2.lastName").toString() + ",";
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney2").get("attorney2.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney2").get("attorney2.suffix").toString();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        WebDriverUtil.waitForAWhile();
        if (!modalHeader.getText().contains("Attorney")) {
            throw new AutomationException("Attorney type of contacts are not displayed.");
        }

        String nameOFPetitioner = attorneyFirstName + " " + attorneyMiddleName + " " + attorneyLastName + " " + attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, nameOFPetitioner));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if (!attorneyToSelect.isSelected()) {
            throw new AutomationException("Unable to select the attorney contact.");
        }

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel (Attorney) updated successfully.")));

        nameOFPetitionerPage10Form = driverUtil.getWebElement(NAME_OF_PETITIONER_PAGE_10).getAttribute("value");

        if (!nameOFPetitionerPage10Form.equals(nameOFPetitioner)) {
            throw new AutomationException("Name of Corporate Fiduciary not fetched correctly. Expected: " + nameOFPetitioner + " ,Found: " + nameOFPetitionerPage10Form);
        }
    }

    public void verifyWarningIsDisplayedForSelectingCapacity() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement warningMessage = driverUtil.getWebElement(WARNING_MESSAGE);

        if (!warningMessage.isDisplayed()) {
            throw new AutomationException("Warning is not displayed for selecting capacity");
        }

        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForInvisibleElement(By.xpath(WARNING_MESSAGE));
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
        Initials1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials1").toString();
        Name1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name1").toString();
        Address1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address1").toString();
        Amount_of_Claim1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim1").toString();
        Claim_Admitted1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted1").toString();
        Will_Claim_Be_Paid_In_Full1Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full1").toString();

        Initials2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials2").toString();
        Name2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name2").toString();
        Address2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address2").toString();
        Amount_of_Claim2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim2").toString();
        Claim_Admitted2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted2").toString();
        Will_Claim_Be_Paid_In_Full2Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full2").toString();

        Initials3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials3").toString();
        Name3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name3").toString();
        Address3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address3").toString();
        Amount_of_Claim3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim3").toString();
        Claim_Admitted3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted3").toString();
        Will_Claim_Be_Paid_In_Full3Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full3").toString();

        Initials4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials4").toString();
        Name4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name4").toString();
        Address4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address4").toString();
        Amount_of_Claim4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim4").toString();
        Claim_Admitted4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted4").toString();
        Will_Claim_Be_Paid_In_Full4Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full4").toString();

        Initials5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Initials5").toString();
        Name5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Name5").toString();
        Address5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Address5").toString();
        Amount_of_Claim5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Amount_of_Claim5").toString();
        Claim_Admitted5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Claim_Admitted5").toString();
        Will_Claim_Be_Paid_In_Full5Form = CommonUtil.getJsonPath("OC01Form").get("OC01Form.Will_Claim_Be_Paid_In_Full5").toString();

        List<String> InitialsForm = Arrays.asList(
                Initials1Form, Initials2Form, Initials3Form, Initials4Form, Initials5Form
        );

        List<String> NameForm = Arrays.asList(
                Name1Form, Name2Form, Name3Form, Name4Form, Name5Form
        );

        List<String> AddressForm = Arrays.asList(
                Address1Form, Address2Form, Address3Form, Address4Form, Address5Form
        );

        List<String> AmountOfClaimForm = Arrays.asList(
                Amount_of_Claim1Form, Amount_of_Claim2Form, Amount_of_Claim3Form, Amount_of_Claim4Form, Amount_of_Claim5Form
        );

        List<String> ClaimAdmittedForm = Arrays.asList(
                Claim_Admitted1Form, Claim_Admitted2Form, Claim_Admitted3Form, Claim_Admitted4Form, Claim_Admitted5Form
        );

        List<String> WillClaimBePaidInFullForm = Arrays.asList(
                Will_Claim_Be_Paid_In_Full1Form, Will_Claim_Be_Paid_In_Full2Form,
                Will_Claim_Be_Paid_In_Full3Form, Will_Claim_Be_Paid_In_Full4Form, Will_Claim_Be_Paid_In_Full5Form
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
        List<WebElement> claimantAmountFields = driverUtil.getWebElements(CLAIMANT_AMOUNT_LIST);

        for (int i = 0; i < claimantInitialsFields.size(); i++) {
            claimantInitialsForm.add(claimantInitialsFields.get(i).getText().trim());
            claimAmountForm.add(claimantAmountFields.get(i).getText().trim());
        }

        driverUtil.getWebElement(CLOSE_BTN).click();
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

    public void userEntersProportionForBeneficiaries() throws AutomationException {
        List<WebElement> proportionFields = driverUtil.getWebElements(PROPORTION_COLUMNS);

        // Indices of the beneficiaries (0-based): 1st, 3rd, 4th, and 5th
        int[] contactsToEnterProportion = {0, 2, 3, 4};
        enteredProportionValues = new String[]{"25.0000", "35.0000", "20.0000", "20.0000"};

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

    public void userChecksDisplayALLINCOMEDistributeesOnAttachmentCheckbox() {
        scrollToElement(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyAllTheBeneficiaryContactsAreMovedToTheAttachment() throws AutomationException {
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

        //dubbuggging
        for (String petitioner : expectedPetitionersOnAttachment) {
            System.out.println("Petitioner on attachment: " + petitioner);
        }


        List<WebElement> petitionerOnAttachmentFields = driverUtil.getWebElements(SIGN_OF_PETITIONER_ON_ATTACHMENT_PAGE_11);
        List<String> actualPetitionersOnAttachment = new ArrayList<>();

        for (WebElement element : petitionerOnAttachmentFields) {
            actualPetitionersOnAttachment.add(getFieldValue(element));
        }

        //deubuggibg
        for (String name : actualPetitionersOnAttachment){
            System.out.println("Petitioner actual: "+name);
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

//        driverUtil.getWebElement(CLOSE_BTN).click();
    }

    public void userNavigatesToEstateContactsTab() throws AutomationException {
        driverUtil.getWebElement(CLOSE_BTN).click();

        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void filterByContactName(String contactName) throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CONTACT_NAME_FILTER).click();
        clearField(CONTACT_NAME_FILTER);
        driverUtil.getWebElement(CONTACT_NAME_FILTER).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
    }

//    public String getEstateContactName(String fullName) {
//        if (fullName == null || fullName.trim().isEmpty()) {
//            return "";
//        }
//        String nameWithoutSuffix = fullName.replaceAll(",\\s*\\w+\\.*", "").trim();
//        String[] nameParts = nameWithoutSuffix.split("\\s+");
//        if (nameParts.length >= 2) {
//            String firstName = nameParts[0];
//            String lastName = nameParts[nameParts.length - 1];
//            return lastName + ", " + firstName;
//        }
//        return "";
//    }

    public String getEstateContactName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }

        // Check for non-traditional names (e.g., omicronSolution) and return as-is
        if (!fullName.contains(" ")) {
            return fullName.trim();
        }

        // Remove suffix (e.g., Jr., Sr.)
        String nameWithoutSuffix = fullName.replaceAll(",\\s*\\w+\\.*", "").trim();

        // Split name into parts (First name, Last name)
        String[] nameParts = nameWithoutSuffix.split("\\s+");

        // If there are more than 1 part, return as "Last Name, First Name"
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[nameParts.length - 1];
            return lastName + ", " + firstName;
        }

        // Return as-is if there's only one part (e.g., omicronSolution)
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
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void uncheckTheFiduciaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleFiduciary").toString();
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_UNCHECK, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void checkTheBeneficiaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleBeneficiary").toString();
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void uncheckTheBeneficiaryRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleBeneficiary").toString();
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_UNCHECK, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void checkTheAttorneyRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAttorney").toString();
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void uncheckTheAttorneyRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAttorney").toString();
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

        System.out.println("Petitioner 1 name: "+petitioner1);
        System.out.println("Petitioner 2 name: "+petitioner2);

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

    public void verifyNotificationIsDisplayedWhenTheCorporateFiduciaryAndAttorneyContactsAreRemovedFromTheEstateContacts() throws AutomationException, IOException, ParseException {
        String petitioner = getEstateContactName(nameOFPetitionerPage10Form);

        filterByContactName(nameOfCorporateFiduciary);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, nameOfCorporateFiduciary)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheFiduciaryRole();
        verifyNotificationIsDisplayedOnRemovingTheRole();
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

        filterByContactName(petitioner);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner)).click();
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        uncheckTheAttorneyRole();
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
        driverUtil.getWebElement(String.format(RELATIONSHIP_OPTION,relationship)).click();
        driverUtil.getWebElement(SAVE_BTN).click();
        WebDriverUtil.waitForAWhile();

        String petitioner = getEstateContactName(nameOFPetitionerPage10Form);

//        filterByContactName(nameOfCorporateFiduciary);
//        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
//        WebDriverUtil.waitForAWhile();
//        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, nameOfCorporateFiduciary)).click();
//        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
//        WebDriverUtil.waitForAWhile();
//        checkTheFiduciaryRole();
//        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

//        filterByContactName(petitioner);
//        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
//        WebDriverUtil.waitForAWhile();
//        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, petitioner)).click();
//        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
//        WebDriverUtil.waitForAWhile();
//        checkTheAttorneyRole();
//        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));

//send data through json here
//        String fullName = extractFullName(beneDetails.get(0));
//        String beneficiaryContact = getEstateContactName(fullName);
        String shareOfEstateInWord = CommonUtil.getJsonPath(beneKey).get(beneKey + ".ShareOfEstate").toString();
        String amountOfEstate = CommonUtil.getJsonPath(beneKey).get(beneKey + ".AmountOfEstate").toString();
        String beneficialInterest = CommonUtil.getJsonPath(beneKey).get(beneKey + ".BeneficialInterest").toString();


        driverUtil.getWebElement(BENY_WORKSHEET_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(String.format(SHARE_OF_ESTATE_IN_WORDS, fullName)).sendKeys(shareOfEstateInWord);
        driverUtil.getWebElement(String.format(SHARE_OF_ESTATE_IN_WORDS, fullName)).sendKeys(Keys.TAB);
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Changes saved successfully.")));
        driverUtil.getWebElement(String.format(AMOUNT_OF_ESTATE, fullName)).sendKeys(amountOfEstate);
        driverUtil.getWebElement(String.format(AMOUNT_OF_ESTATE, fullName)).sendKeys(Keys.TAB);
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Changes saved successfully.")));
        driverUtil.getWebElement(String.format(BENEFICIAL_INTEREST, fullName)).sendKeys(beneficialInterest);
        driverUtil.getWebElement(String.format(BENEFICIAL_INTEREST, fullName)).sendKeys(Keys.TAB);
//        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Changes saved successfully.")));

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

    public void verifyRemovedCorporateFiduciaryAndAttorneyContactsFromTheEstateContactsAreAlsoGetsRemovedFromTheForm() throws AutomationException {
        scrollToElement(NAME_OF_CORPORATE_FIDUCIARY);
        String actualNameOfCorporateFiduciary = driverUtil.getWebElement(NAME_OF_CORPORATE_FIDUCIARY).getAttribute("value");
        if (actualNameOfCorporateFiduciary.equals(nameOfCorporateFiduciary)) {
            throw new AutomationException("Removed corporate fiduciary contact " + nameOfCorporateFiduciary + " from the estate contacts is not gets removed from the form");
        }

        String actualNameOFPetitioner = driverUtil.getWebElement(NAME_OF_PETITIONER_PAGE_10).getAttribute("value");
        if (actualNameOFPetitioner.equals(nameOFPetitionerPage10Form)) {
            throw new AutomationException("Removed corporate fiduciary contact " + nameOFPetitionerPage10Form + " from the estate contacts is not gets removed from the form");
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
            verifyFieldsInPDF(pdfFilePath,
                    "ORPHANS' COURT DIVISION",
                    "PETITION FOR ADJUDICATION /",
                    "12-24-2001",
                    "file number");


            Map<String, String> expectedCounselDetails = new HashMap<>();
            expectedCounselDetails.put("Name of Counsel", "Ethan Benjamin Walker, Jr.");
            expectedCounselDetails.put("Name of Law Firm", "sigmaConsulting");
            expectedCounselDetails.put("Address", "Riverside Drive Kansas City, MO 64101");
            expectedCounselDetails.put("Telephone 1", "(816) 555-4321");
            expectedCounselDetails.put("Telephone 2", "(816) 888-6543");
            expectedCounselDetails.put("Email", "ethan.walker@business.com");

            verifyCounselDetails(pdfFilePath, expectedCounselDetails);


            Map<String, String> expectedPetitioners = new LinkedHashMap<>();
            expectedPetitioners.put("Michael Andrew Smith, Sr.", "Oak Street Atlanta, GA 30305");
            expectedPetitioners.put("David James Clark, Jr.", "Pine Street San Francisco, CA 94102");

            validatePetitionerAddressMapping(pdfFilePath, expectedPetitioners);

            verifyFieldsInPDF(pdfFilePath,
                    "Letters Testamentary or  Letters of Administration were granted to Petitioner(s) on",
                    "Date(s) of Codicil(s) (if applicable): 02/17/2023 02/20/2023 02/26/2023",
                    dateOfWillForm,
                    "Date of Will");

            Map<String, String> expectedCodicilValues = new HashMap<>();
            expectedCodicilValues.put("Codicil Date1", codicilDate1Form);
            expectedCodicilValues.put("Codicil Date2", codicilDate2Form);
            expectedCodicilValues.put("Codicil Date3", codicilDate3Form);

            boolean isverifiedCodicilDates = verifyCodicilDates(pdfFilePath,
                    "Date of Will (if applicable): 10/26/2015",
                    "Date of probate (if different from date Letters granted):",
                    expectedCodicilValues,
                    "Codicil Dates");

            Map<String, String> expectedValues = new HashMap<>();
            expectedValues.put("additionalPetitioner1 Name", "Emily Ann Wilson, Sr.");
            expectedValues.put("additionalPetitioner1 Address", "Cedar Lane New York, NY 10001");
            expectedValues.put("additionalPetitioner2 Name", "Daniel Robert Harris, Sr.");
            expectedValues.put("additionalPetitioner2 Address", "Willow Drive Dallas, TX 75201");

            verifyAdditionalPetitioners(pdfFilePath,
                    "(Additional Petitioner)",               // beforeLine
                    "PETITION FOR ADJUDICATION", // afterLine
                    expectedValues
            );

            Map<String, String> expectedChildren = new LinkedHashMap<>();
            expectedChildren.put("Alice Johnson", "03/01/2024");
            expectedChildren.put("Bob Smith", "03/02/2024");
            expectedChildren.put("Charlie Brown", "03/03/2024");
            expectedChildren.put("David Wilson", "03/04/2024");
            expectedChildren.put("Emma Davis", "03/05/2024");
            expectedChildren.put("Fiona White", "03/06/2024");
            expectedChildren.put("George Hall", "03/07/2024");
            expectedChildren.put("Hannah Lee", "03/08/2024");
            expectedChildren.put("Isaac Martin", "03/09/2024");

            verifyChildrenNamesAndDOBs(
                    pdfFilePath,
                    "If yes, give names and dates of birth:",
                    "7. Was a request for a statement of claim, as required by the Medical",
                    expectedChildren
            );

            Map<String, String> expectedDatePaymentInterestMap = new LinkedHashMap<>();
            expectedDatePaymentInterestMap.put("Date_1", "01/15/2025");
            expectedDatePaymentInterestMap.put("Payment_1", "1,000.00");
            expectedDatePaymentInterestMap.put("Interest_1", "5");

            expectedDatePaymentInterestMap.put("Date_2", "02/20/2025");
            expectedDatePaymentInterestMap.put("Payment_2", "1,500.00");
            expectedDatePaymentInterestMap.put("Interest_2", "4.5");

            expectedDatePaymentInterestMap.put("Date_3", "03/10/2025");
            expectedDatePaymentInterestMap.put("Payment_3", "1,200.00");
            expectedDatePaymentInterestMap.put("Interest_3", "6");

            expectedDatePaymentInterestMap.put("Date_4", "04/25/2025");
            expectedDatePaymentInterestMap.put("Payment_4", "2,000.00");
            expectedDatePaymentInterestMap.put("Interest_4", "5.2");

            expectedDatePaymentInterestMap.put("Date_5", "05/30/2025");
            expectedDatePaymentInterestMap.put("Payment_5", "1,800.00");
            expectedDatePaymentInterestMap.put("Interest_5", "4.8");

            expectedDatePaymentInterestMap.put("Date_6", "06/05/2025");
            expectedDatePaymentInterestMap.put("Payment_6", "2,200.00");
            expectedDatePaymentInterestMap.put("Interest_6", "5.5");

            expectedDatePaymentInterestMap.put("Date_7", "07/12/2025");
            expectedDatePaymentInterestMap.put("Payment_7", "1,700.00");
            expectedDatePaymentInterestMap.put("Interest_7", "6.2");

            expectedDatePaymentInterestMap.put("Date_8", "08/22/2025");
            expectedDatePaymentInterestMap.put("Payment_8", "2,500.00");
            expectedDatePaymentInterestMap.put("Interest_8", "4.9");

            boolean isVerifiedTaxPaymentData = verifyTaxPaymentData(pdfFilePath,
                    "Date Payment Interest",
                    "13. On the date of death, was the decedent a fiduciary",
                    expectedDatePaymentInterestMap);

            Map<String, String> expectedDateDescriptionAmountMap = new LinkedHashMap<>();
            expectedDateDescriptionAmountMap.put("Date_1", "01/10/2025");
            expectedDateDescriptionAmountMap.put("Description_1", "Payment for invoice #123");
            expectedDateDescriptionAmountMap.put("Amount_1", "1,000.00");

            expectedDateDescriptionAmountMap.put("Date_2", "02/15/2025");
            expectedDateDescriptionAmountMap.put("Description_2", "Refund for order #456");
            expectedDateDescriptionAmountMap.put("Amount_2", "1,500.00");

            expectedDateDescriptionAmountMap.put("Date_3", "03/20/2025");
            expectedDateDescriptionAmountMap.put("Description_3", "Service charge for March");
            expectedDateDescriptionAmountMap.put("Amount_3", "1,200.00");

            expectedDateDescriptionAmountMap.put("Date_4", "04/25/2025");
            expectedDateDescriptionAmountMap.put("Description_4", "Reimbursement for expenses");
            expectedDateDescriptionAmountMap.put("Amount_4", "2,000.00");

            expectedDateDescriptionAmountMap.put("Date_5", "05/30/2025");
            expectedDateDescriptionAmountMap.put("Description_5", "Bonus payout");
            expectedDateDescriptionAmountMap.put("Amount_5", "1,800.00");

            expectedDateDescriptionAmountMap.put("Date_6", "06/05/2025");
            expectedDateDescriptionAmountMap.put("Description_6", "Monthly subscription fee");
            expectedDateDescriptionAmountMap.put("Amount_6", "2,200.00");

            expectedDateDescriptionAmountMap.put("Date_7", "07/10/2025");
            expectedDateDescriptionAmountMap.put("Description_7", "Annual maintenance charge");
            expectedDateDescriptionAmountMap.put("Amount_7", "1,700.00");

            expectedDateDescriptionAmountMap.put("Date_8", "08/15/2025");
            expectedDateDescriptionAmountMap.put("Description_8", "Late fee adjustment");
            expectedDateDescriptionAmountMap.put("Amount_8", "2,500.00");

            expectedDateDescriptionAmountMap.put("Date_9", "09/20/2025");
            expectedDateDescriptionAmountMap.put("Description_9", "Tax refund");
            expectedDateDescriptionAmountMap.put("Amount_9", "3,000.00");


            verifyTransactionDetails(
                    pdfFilePath,
                    "Date Description Amount",
                    "B. Has notice of the additional receipts and disbursements been",
                    expectedDateDescriptionAmountMap

            );


            Map<String, String> expected = new HashMap<>();
            expected.put("Name of Corporate Fiduciary", "zetaConsulting"); // ✔️ Key matches exactly
            expected.put("Name of Representative and Title", "Liam Noah Anderson, Jr.");
            expected.put("Name of Petitioner", "Rihan Benjamin Miles, Jr.");

            verifySubmittedByFiduciaryAndPetitionerDetails(pdfFilePath, expected);




            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
    } catch(AutomationException |
    IOException e)

    {
        throw new AutomationException("❌ Verification failed: " + e.getMessage());
    } catch(
    Exception e)

    {
        throw new RuntimeException(e);
    }
}


private static void verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName) throws IOException, AutomationException {
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
    } else {
        throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
    }
}

private static void verifyCounselDetails(String pdfFilePath, Map<String, String> expectedDetails) throws IOException, AutomationException {
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
}


// Utility method to clean the string, removing unwanted characters or spaces
private static String clean(String value) {
    if (value != null) {
        // Remove unwanted punctuation like commas, periods, etc. and trim spaces
        value = value.replaceAll("[,\\.]", "").trim();
    }
    return value;
}


private static String cleanFields(String rawText, String fieldType) {
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


public static void validatePetitionerAddressMapping(String pdfFilePath, Map<String, String> expectedNameAddressMap)
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

        if (line.equalsIgnoreCase("Name:") && i + 1 < endIndex) {
            extractedNames.add(allLines[i + 1].trim());
        }

        if (line.equalsIgnoreCase("Address:") && i + 2 < endIndex) {
            String line1 = allLines[i + 1].trim();
            String line2 = allLines[i + 2].trim();
            extractedAddresses.add(line1 + " " + line2);
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

    CommonSteps.logInfo("🔍 Comparing extracted names set with expected names set...");
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

    public static boolean verifySubmittedByFiduciaryAndPetitionerDetails(String pdfFilePath,
                                                                         Map<String, String> expectedValues)
            throws AutomationException {

        List<String> extractedLines = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);
            extractedLines = Arrays.asList(pdfText.split("\\r?\\n"));
        } catch (IOException e) {
            throw new AutomationException("❌ Failed to read PDF: " + e.getMessage());
        }

        String corporateFiduciary = "";
        String representativeName = "";
        String petitionerName = "";

        for (int i = 0; i < extractedLines.size(); i++) {
            String line = extractedLines.get(i).trim();

            if (line.equalsIgnoreCase("Name of Corporate Fiduciary") && i > 0) {
                corporateFiduciary = extractedLines.get(i - 1).trim();
            } else if (line.equalsIgnoreCase("Name of Representative and Title") && i > 0) {
                representativeName = extractedLines.get(i - 1).trim();
            } else if (line.equalsIgnoreCase("Name of Petitioner") && i > 0) {
                petitionerName = extractedLines.get(i - 1).trim();
            }
        }

        // Validation
        validateField("Corporate Fiduciary", corporateFiduciary, expectedValues.get("Name of Corporate Fiduciary"));
        validateField("Representative Name", representativeName, expectedValues.get("Name of Representative and Title"));
        validateField("Petitioner Name", petitionerName, expectedValues.get("Name of Petitioner"));


        CommonSteps.logInfo("✅ All 'Submitted By' fiduciary and petitioner fields verified successfully.");
        return true;
    }

    private static void validateField(String fieldName, String actual, String expected) throws AutomationException {
        if (expected == null) {
            throw new AutomationException("⚠️ Expected value is missing for field: " + fieldName);
        }

        if (!expected.equals(actual)) {
            throw new AutomationException("❌ Mismatch in " + fieldName +
                    "\nExpected: '" + expected + "'\nFound:    '" + actual + "'");
        } else {
            CommonSteps.logInfo("✅ " + fieldName + " matched successfully: " + actual);
        }
    }

}

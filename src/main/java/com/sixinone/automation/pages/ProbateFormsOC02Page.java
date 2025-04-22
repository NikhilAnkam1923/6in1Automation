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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String COUNTY_PAGE_1_XPATH = "//p[text()='COURT OF COMMON PLEAS OF']/following-sibling::p//span//input";
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

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    private static final List<String> selectedPetitioner = new ArrayList<>();
    private static final List<String> Fiduciaries = new ArrayList<>();
    private static final List<String> corporateFiduciaries = new ArrayList<>();

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


        //use in reset
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
}

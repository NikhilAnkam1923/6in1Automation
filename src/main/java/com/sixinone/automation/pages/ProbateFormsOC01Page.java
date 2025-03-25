package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProbateFormsOC01Page extends BasePage{
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

    private final Map<String, String> estateInfo = new HashMap<>();

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

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);

        if (field.isEnabled() && field.getAttribute("disabled")==null && field.getAttribute("readonly")==null) {
            throw new AutomationException("Field is editable: " + fieldLocator);
        } else {
            CommonSteps.logInfo("Field is not editable: " + fieldLocator);
        }
    }

    public void verifyEstateNameAndCountyFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        WebElement countyNameField = driverUtil.getWebElement(HEADER_COUNTY_NAME);
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME);

        countyNameForm = countyNameField.getAttribute("value");
        estateNameForm =  estateNameField.getAttribute("value");

        String enteredCountyName = getEstateValue("DomicileCountry");
        String enteredEstateName = getEstateValue("DisplayName");

        if(!enteredCountyName.equals(countyNameForm)){
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm);
        }

        if(!enteredEstateName.equals(estateNameForm)){
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNameForm);
        }

        verifyFieldIsNotEditable(HEADER_COUNTY_NAME);
        verifyFieldIsNotEditable(ESTATE_NAME);
    }

    public void verifyFileNumberFieldIsEditable() throws AutomationException, IOException, ParseException {
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        if(!fileNumberField.isEnabled()){
            throw new AutomationException("File number field is not editable.");
        }

        String initialFileNumber = fileNumberField.getAttribute("value");
        String newFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();

        fileNumberField.clear();
        fileNumberField.sendKeys(newFileNumber);
        WebDriverUtil.waitForAWhile();

        fileNumberForm = fileNumberField.getAttribute("value");

        if(!fileNumberForm.equals(newFileNumber) && fileNumberForm.equals(initialFileNumber)){
            throw new AutomationException("File number not updated correctly. Expected: " + newFileNumber + " ,Found: " + fileNumberForm);
        }

        //use in reset
        fileNumberField.clear();
        fileNumberField.sendKeys(initialFileNumber);
        driverUtil.getWebElement(CLOSE_TOASTER_BTN).click();

        //temp use
        WebDriverUtil.waitForAWhile();

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

        if(!modalHeader.getText().contains("Attorney")){
            throw new AutomationException("Sidebar is not displayed for attorney contact.");
        }

        selectedAttorney = attorneyFirstName+" "+attorneyMiddleName+" "+attorneyLastName+", "+attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, selectedAttorney));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if(!attorneyToSelect.isSelected()){
            throw new AutomationException("Unable to select the attorney contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(PROCEED_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel updated successfully.")));

    }

    public void verifySelectedAttorneySDetailsArePopulatedInTheField() throws AutomationException {
        WebElement nameOfCounselField = driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD);

        nameOfCounselForm = nameOfCounselField.getAttribute("value");
        if(!nameOfCounselForm.equals(selectedAttorney.replace(",",""))){
            throw new AutomationException("Attorney details not populated correctly in 'Name of Counsel' field. Expected: " + selectedAttorney + " ,Found: " + nameOfCounselForm);
        }
    }

    public void verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed() throws IOException, ParseException, AutomationException {
        String attorneyFirmName = CommonUtil.getJsonPath("attorney1").get("attorney1.entityName").toString();
        String attorneyAddressLine1 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney1").get("attorney1.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney1").get("attorney1.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney1").get("attorney1.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney1").get("attorney1.phoneNumber").toString();
        String attorneyFax = CommonUtil.getJsonPath("attorney1").get("attorney1.fax").toString();
        String attorneyEmail = CommonUtil.getJsonPath("attorney1").get("attorney1.emailId").toString();

        String attorneyCitySateZip = attorneyCity + ", " + attorneySateCode + " " + attorneyZip;

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

        if (!attorneyAddressLine1.equals(attorneyAddressLine1Form)) {
            throw new AutomationException("Attorney Address Line 1 not correctly fetched. Expected: " + attorneyAddressLine1 + ", Found: " + attorneyAddressLine1Form);
        }

        if (!attorneyCitySateZip.equals(attorneyAddressLine2Form)) {
            throw new AutomationException("Attorney Address Line 2 (City, State code, Zip) not correctly fetched. Expected: " + attorneyCitySateZip + ", Found: " + attorneyAddressLine2Form);
        }
    }

    public void verifyAttorneyDetailsAreSyncedWithPageOfSameForm() throws AutomationException {
        WebElement signOfCounselField = driverUtil.getWebElement(SIGN_OF_COUNSEL_PAGE_11);
        signOfCounselPage11Form = signOfCounselField.getAttribute("value");

        if(!selectedAttorney.equals(signOfCounselPage11Form)){
            throw new AutomationException("Attorney details are not synced with page 11. Expected: " + selectedAttorney + " ,Found: " + signOfCounselPage11Form);
        }
    }

    public void verifyEstateNameAndDateOfDeathFieldsDisplayPreloadedDataAndAreNonEditable() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_2);
        WebElement decedentDiedOnField = driverUtil.getWebElement(DECEDENT_DIED_ON_FIELD);

        estateNamePage2Form =  estateNameField.getAttribute("value");
        decedentDiedOnForm = decedentDiedOnField.getAttribute("value");

        String enteredEstateName = getEstateValue("DisplayName");
        String enteredDateOfDeathName = getEstateValue("DateOfDeath");

        if(!enteredEstateName.equals(estateNamePage2Form)){
            throw new AutomationException("Estate name not fetched correctly. Expected: " + enteredEstateName + " ,Found: " + estateNamePage2Form);
        }

        if(!enteredDateOfDeathName.equals(decedentDiedOnForm)){
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

        if(!modalHeader.getText().contains("Select Contact")){
            throw new AutomationException("Sidebar is not displayed for fiduciary contact.");
        }

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

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

    public void verifySelectedFiduciariesPopulateInThePetitionerFieldsOnTheForm() throws AutomationException, IOException, ParseException {
        petitioner1AddressLine1Form = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.addressLine1").toString();
        String fiduciary1City = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.city").toString() + ",";
        String fiduciary1StateCode = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.stateCode").toString();
        String fiduciary1Zip = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.zip").toString();
        petitioner1CityStateCodeZipForm = fiduciary1City + " " + fiduciary1StateCode + " " + fiduciary1Zip;

        petitioner2AddressLine1Form = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.addressLine1").toString();
        String fiduciary2City = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.city").toString() + ",";
        String fiduciary2StateCode = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.stateCode").toString();
        String fiduciary2Zip = CommonUtil.getJsonPath("fiduciary1").get("fiduciary1.zip").toString();
        petitioner2CityStateCodeZipForm = fiduciary2City + " " + fiduciary2StateCode + " " + fiduciary2Zip;

        WebElement nameOfPetitionerField = driverUtil.getWebElement(PETITIONER_NAME_FIELD);
        WebElement nameOfPetitioner2Field = driverUtil.getWebElement(PETITIONER_NAME_FIELD_2);

        nameOfPetitionerForm = nameOfPetitionerField.getAttribute("value");
        if(!nameOfPetitionerForm.equals(Fiduciary1Form)){
            throw new AutomationException("Fiduciary details not populated correctly in 'Name of Counsel' field. Expected: " + Fiduciary1Form + " ,Found: " + nameOfPetitionerForm);
        }
        verifyPetitionerOnForm(petitioner1AddressLine1Form);
        verifyPetitionerOnForm(petitioner1CityStateCodeZipForm);


        nameOfPetitioner2Form = nameOfPetitioner2Field.getAttribute("value");
        if(!nameOfPetitioner2Form.equals(Fiduciary2Form)){
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
//        Fiduciary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
//        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

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
        petitioner3AddressLine1Form = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.addressLine1").toString();
        String fiduciary3City = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.city").toString() + ",";
        String fiduciary3StateCode = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.stateCode").toString();
        String fiduciary3Zip = CommonUtil.getJsonPath("fiduciary5").get("fiduciary5.zip").toString();
        petitioner3CityStateCodeZipForm = fiduciary3City + " " + fiduciary3StateCode + " " + fiduciary3Zip;

        petitioner4AddressLine1Form = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.addressLine1").toString();
        String fiduciary4City = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.city").toString() + ",";
        String fiduciary4StateCode = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.stateCode").toString();
        String fiduciary4Zip = CommonUtil.getJsonPath("fiduciary4").get("fiduciary4.zip").toString();
        petitioner4CityStateCodeZipForm = fiduciary4City + " " + fiduciary4StateCode + " " + fiduciary4Zip;

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
}

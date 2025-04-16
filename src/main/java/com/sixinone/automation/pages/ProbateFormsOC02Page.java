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

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsOC02Page extends BasePage {
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
    private static final String NAME_OF_COUNSEL_FIELD = "//p//span[text()='Name of Counsel:']/following-sibling::span//input";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String PROCEED_BTN = "//button[text()='Proceed']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String NAME_OF_LAW_FIRM_FIELD = "//p//span[text()='Name of Law Firm:']/following-sibling::span//input";
    private static final String TELEPHONE_FIELD = "//p//span[text()='Telephone: ']/following-sibling::span//input";
    private static final String FAX_FIELD = "//p//span[text()='Fax:']/following-sibling::span//input";
    private static final String EMAIL_FIELD = "//p//span[text()='E-mail: ']/following-sibling::span//input";
    private static final String ADDRESS_LINE_1_FIELD = "//p//span[text()='Address: ']/following-sibling::span//input";
    private static final String ADDRESS_LINE_2_FIELD = "//span[text()='Address: ']/ancestor::p/following-sibling::p//span//input";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String countyNameForm;
    static String enteredDecedentNameForm;
    static String initialFileNumber;
    static String fileNumberForm;
    static String selectedAttorney;
    static String nameOfLawFirmForm;
    static String attorneyTelephoneForm;
    static String attorneyFaxForm;
    static String attorneyEmailForm;
    static String attorneyAddressLine1Form;
    static String attorneyAddressLine2Form;
    static String nameOfCounselForm;

    static String downloadedFileName;

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

        if (!page1.isDisplayed()) {
            throw new AutomationException("On clicking OC02, page 1 is not opened by default.");
        }

        if (!countyNameForm.equals(enteredCountyName)) {
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

        if (!trustOfWillField.isEnabled() && trustOfWillField.getAttribute("disabled") != null && trustOfWillField.getAttribute("readonly") != null) {
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

        if (!trustOfDeedField.isEnabled() && trustOfDeedField.getAttribute("disabled") != null && trustOfDeedField.getAttribute("readonly") != null) {
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
        if (!trustUnderWillName.equals(nameOfDecedent)) {
            throw new AutomationException("Entered name is not displayed on the form against the Trust under will field");
        }

        String otherFieldText = driverUtil.getWebElement(TRUST_UNDER_DEED_TEXT_FIELD).getAttribute("value");
        if (!otherFieldText.isEmpty()) {
            throw new AutomationException("Other field is not empty. Found text: " + otherFieldText);
        }

    }

    public void verifyNameIsDisplayedOnTheFormAgainstDeedFieldAndOtherFieldIsEmpty() throws IOException, ParseException, AutomationException {
        String nameOfDecedent = CommonUtil.getJsonPath("OC02Form").get("OC02Form.nameOfDecedent").toString();
        WebElement trustUnderDeedField = driverUtil.getWebElement(TRUST_UNDER_DEED_TEXT_FIELD);

        scrollToElement(TRUST_UNDER_DEED_TEXT_FIELD);
        trustUnderDeedField.sendKeys(nameOfDecedent);
        trustUnderDeedField.sendKeys(Keys.TAB);

        String trustUnderDeedName = trustUnderDeedField.getAttribute("value");
        if (!trustUnderDeedName.equals(nameOfDecedent)) {
            throw new AutomationException("Entered name is not displayed on the form against the Trust under deed field");
        }

        String otherFieldText = driverUtil.getWebElement(TRUST_UNDER_WILL_TEXT_FIELD).getAttribute("value");
        if (!otherFieldText.isEmpty()) {
            throw new AutomationException("Other field is not empty. Found text: " + otherFieldText);
        }

        enteredDecedentNameForm = trustUnderDeedField.getAttribute("value");
    }

    public void verifyEnteredNameOfDecedentIsDisplayedOnAllTheOCPages() throws AutomationException {
        for (int i = 2; i <= 11; i++) {
            switchToPage(i);
            String actualNameOfTrust;
            if (i == 2 || i == 4) {
                actualNameOfTrust = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_2_AND_4_PAGE).getAttribute("value");
            } else {
                actualNameOfTrust = driverUtil.getWebElement(NAME_OF_TRUST_FIELD_OTHER_PAGES).getAttribute("value");
            }

            if (!actualNameOfTrust.equals(enteredDecedentNameForm)) {
                throw new AutomationException("Decedent Name is not reflected on page: " + i + ". Expected: " + enteredDecedentNameForm + " ,Found: " + actualNameOfTrust);
            }
        }
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

        String addressLine1 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String addressLine2 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine2").toString();

        String attorneyAddressLine1 = addressLine1 + ", " + addressLine2;

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
                    "12-24-2001",
                    "file number");

            Map<String, String> expectedCounselDetails = new HashMap<>();
            expectedCounselDetails.put("Name of Counsel", "Ethan Benjamin Walker, Jr.");
            expectedCounselDetails.put("Name of Law Firm", "sigmaConsulting");
            expectedCounselDetails.put("Address", "Riverside Drive Kansas City, MO 64101");
            expectedCounselDetails.put("Telephone 1", "(816) 555-4321");
            expectedCounselDetails.put("Telephone 2", "(816) 888-6543");
            expectedCounselDetails.put("Email", "ethan.walker@business.com");

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
                cleanedText = cleanedText.replaceAll("(?i)\\b(No.  )\\b", "").trim();
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
}


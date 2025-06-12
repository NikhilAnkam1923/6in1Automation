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
import org.openqa.selenium.WebElement;

import javax.security.sasl.AuthenticationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsOC06Page extends BasePage {


    private final Map<String, String> estateInfo = new HashMap<>();
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
    private static final String FIRST_PAGE_ACTIVE_XPATH = "//a[@role='tab' and text()='1' and @class='nav-link active']";
    private static final String ESTATE_PAGE_1_XPATH = "//input[@value='%s']";
    private static final String COUNTY_PAGE_1_XPATH = "//div[@class='county-title']//span[@class='county-name']";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String OC_FORM_XPATH = "//a//p[text()='%s']";
    private static final String USE_4_DIGIT_YEAR_CHECKBOX = "//input[@name='isUseFourDigit']";
    private static final String SETTLOR_CHECKBOX = "//input[@name='isSettlorOrDeceased' and @value='1']";
    private static final String DECEASED_CHECKBOX = "//input[@name='isSettlorOrDeceased' and @value='0']";
    private static final String ESTATE_NAME_PAGE_2 = "//input[@id='Decedent_fullname']";
    private static final String ESTATE_NAME_PAGE_3 = "//input[@id='Decedent_fullname']";
    private static final String ESTATE_NAME_PAGE_4 = "//input[@id='Decedent_fullname']";
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String NAME_OF_COUNSEL_FIELD = "//p[@class='p35']/following-sibling::p//input[@id='Contact_indfullnamecommasuffix_6578']";
    private static final String NAME_OF_LAW_FIRM = "//input[@id='Contact_organization_name']";
    private static final String ATTORNEY_ADDRESS = "//input[@id='Contact_fulladdress']";
    private static final String ATTORNEY_CITY_STATE_ZIP = "//input[@id='Contact_citystatezip']";
    private static final String ATTORNEY_TELEPHONE = "//input[@id='Contact_phone_number']";
    private static final String ATTORNEY_EMAIL = "//input[@id='Contact_email_address']";
    private static final String PROCEED_BTN = "//div[@class='modal-footer']//button[text()='Proceed']";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";


    static String downloadedFileName;

    static String estateNameForm;
    static String fileNumberForm;
    static String countyNameForm;
    static String initialFileNumber;
    static String fourDigitFileNumberForm;
    static String estateNamePage2;
    static String selectedRolePage1;
    static String estateNamePage3;
    static String estateNamePage4;
    static String selectedAttorney;
    static String nameOfCounselForm;
    static String nameOfLawFirmForm;
    static String attorneyAddressForm;
    static String attorneyCityStateZipForm;
    static String attorneyTelephoneForm;
    static String attorneyEmailForm;

    public ProbateFormsOC06Page() throws IOException, ParseException {
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

    public void userSavesEstateInfo() throws AutomationException {
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

    @Override
    String getName() {
        return "";
    }

    public void verifyEstateNameAndFileNumberAutofetch() throws AutomationException, IOException, ParseException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);

        if (!page1.isDisplayed()) {
            throw new AutomationException("On clicking OC06, page 1 is not opened by default.");
        }

        // ✅ Estate Name Verification
        String expectedEstateName = getEstateValue("DisplayName");
        WebElement estateNameElement = driverUtil.getWebElement(String.format(ESTATE_PAGE_1_XPATH, expectedEstateName));
        estateNameForm = estateNameElement.getAttribute("value");

        if (!estateNameForm.equals(expectedEstateName)) {
            throw new AutomationException("Estate name not fetched correctly.\nExpected: " + expectedEstateName + "\nFound   : " + estateNameForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Estate Name: " + estateNameForm);
        }

        // ✅ File Number Verification
        String part1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart1").toString();
        String part2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart2").toString();
        String part3 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart3").toString();
        String expectedFileNumber = part1 + "-" + part2 + "-" + part3;

        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberForm = fileNumberField.getAttribute("value");

        if (!fileNumberForm.equals(expectedFileNumber)) {
            throw new AutomationException("File number not fetched correctly.\nExpected: " + expectedFileNumber + "\nFound   : " + fileNumberForm);
        } else {
            CommonSteps.logInfo("✅ Verified → File Number: " + fileNumberForm);
        }
    }

    public void enterValidFileNumber() throws AutomationException, IOException, ParseException {
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        if (!fileNumberField.isEnabled()) {
            throw new AutomationException("File number field is not editable.");
        }

        initialFileNumber = fileNumberField.getAttribute("value");

        String newFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();

        fileNumberField.clear();
        fileNumberField.sendKeys(newFileNumber);

        WebElement toasterbtn = driverUtil.getWebElement(CLOSE_TOASTER_BTN);
        toasterbtn.click();


        WebDriverUtil.waitForAWhile(); // small wait for UI update

        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);

        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }
    }

    public void clickOnOCForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(OC_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyFileNumberUpdatedAndSaved() throws AutomationException, IOException, ParseException {
        clickOnOCForm("OC 05");
        clickOnOCForm("OC 06");

        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        String enteredFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();
        String actualFileNumber = fileNumberField.getAttribute("value");

        if (!actualFileNumber.equals(enteredFileNumber)) {
            throw new AutomationException("File number not updated correctly. Expected: " + enteredFileNumber + ", Found: " + actualFileNumber);
        }

        WebElement filenumberfield = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        filenumberfield.clear();
        filenumberfield.sendKeys("22-23-1234");

        WebElement toasterbtn = driverUtil.getWebElement(CLOSE_TOASTER_BTN);
        toasterbtn.click();

    }

    public void verifyCountyName(String formName) throws AutomationException {
        WebElement countyName = driverUtil.getWebElement(COUNTY_PAGE_1_XPATH);
        String enteredCountyName = getEstateValue("DomicileCountry").toUpperCase();
        countyNameForm = countyName.getText();
        if (!countyNameForm.equals(enteredCountyName)) {
            throw new AutomationException("County name not fetched correctly. Expected: " + enteredCountyName + " ,Found: " + countyNameForm + "for" + formName);
        }
    }

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    public void userClicksTheUseDigitYearCheckbox() {
        scrollToElement(USE_4_DIGIT_YEAR_CHECKBOX);
        DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();
    }

    public void verifyFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox() throws AutomationException {
        fourDigitFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        String expectedFourDigitFileNumber = fileNumberForm.replaceFirst("-(\\d{2})-", "-20$1-");

        if (!fourDigitFileNumberForm.equals(expectedFourDigitFileNumber)) {
            throw new AutomationException("File number did not update correctly. Expected: " + expectedFourDigitFileNumber + ", Found: " + fourDigitFileNumberForm);
        }
    }

    public void verifyTwoCheckboxesAreThereOnThePageAndEitherOfThemCanBeSelected() throws AutomationException {
        WebElement settlorCheckbox = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX));
        WebElement deceasedCheckbox = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX));

        if (settlorCheckbox == null || deceasedCheckbox == null) {
            throw new AutomationException("Either 'Settlor' or 'Deceased' checkbox is not present on the page.");
        }

        scrollToElement(SETTLOR_CHECKBOX);
        DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).click();

        boolean isSettlorSelected = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).isSelected();
        boolean isDeceasedSelected = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX)).isSelected();

        if (!isSettlorSelected && !isDeceasedSelected) {
            throw new AutomationException("Neither 'Settlor' nor 'Deceased' checkbox is selected. One must be selected.");
        }

        selectedRolePage1 = null;
        if (isSettlorSelected) {
            selectedRolePage1 = "Settlor";
        } else if (isDeceasedSelected) {
            selectedRolePage1 = "Deceased";
        } else {
            throw new AutomationException("Neither 'Settlor' nor 'Deceased' checkbox is selected on Page 1.");
        }
    }

    public void verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecords() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_2);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(ESTATE_NAME_PAGE_2);

        estateNamePage2 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNamePage2)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNamePage2);
        }
    }

    public void verifySelectedCheckboxesFromPage1AreDisplayedAccuratelyOnPage2() throws AutomationException {
        WebElement settlorCheckbox = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX));
        WebElement deceasedCheckbox = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX));

        if (settlorCheckbox == null || deceasedCheckbox == null) {
            throw new AutomationException("Either 'Settlor' or 'Deceased' checkbox is not present on the page 2.");
        }

        boolean isSettlorSelected = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).isSelected();
        boolean isDeceasedSelected = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX)).isSelected();

        String selectedRolePage2 = null;
        if (isSettlorSelected) {
            selectedRolePage2 = "Settlor";
        } else if (isDeceasedSelected) {
            selectedRolePage2 = "Deceased";
        } else {
            throw new AutomationException("Neither 'Settlor' nor 'Deceased' checkbox is selected on Page 2.");
        }

        if (!selectedRolePage2.equals(selectedRolePage1)) {
            throw new AutomationException("Selected checkboxes \"Settlor & Deceased\" from Page 1 are not displayed accurately on Page 2.");
        }
    }

    public void verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage3() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_3);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(ESTATE_NAME_PAGE_3);

        estateNamePage3 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNamePage3)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNamePage3);
        }
    }

    public void verifySelectedCheckboxesFromAreDisplayedAccuratelyOnPage3() throws AutomationException {
        WebElement settlorCheckbox = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX));
        WebElement deceasedCheckbox = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX));

        if (settlorCheckbox == null || deceasedCheckbox == null) {
            throw new AutomationException("Either 'Settlor' or 'Deceased' checkbox is not present on the page 3.");
        }

        boolean isSettlorSelected = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).isSelected();
        boolean isDeceasedSelected = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX)).isSelected();

        String selectedRolePage3 = null;
        if (isSettlorSelected) {
            selectedRolePage3 = "Settlor";
        } else if (isDeceasedSelected) {
            selectedRolePage3 = "Deceased";
        } else {
            throw new AutomationException("Neither 'Settlor' nor 'Deceased' checkbox is selected on Page 3.");
        }

        if (!selectedRolePage3.equals(selectedRolePage1)) {
            throw new AutomationException("Selected checkboxes \"Settlor & Deceased\" from Page 1 are not displayed accurately on Page 3.");
        }
    }

    public void verifyTheEstateNameIsPreloadedCorrectlyFromTheEstateRecordsOnPage4() throws AutomationException {
        WebElement estateNameField = driverUtil.getWebElement(ESTATE_NAME_PAGE_4);
        String estateName = getEstateValue("DisplayName");
        scrollToElement(ESTATE_NAME_PAGE_4);

        estateNamePage4 = estateNameField.getAttribute("value");

        if (!estateName.equals(estateNamePage4)) {
            throw new AutomationException("Estate name not fetched correctly. Expected: " + estateName + " ,Found: " + estateNamePage4);
        }
    }

    public void verifyTheSelectionsMadeOnPage1AreAccuratelyDisplayedOnPage4() throws AutomationException {
        WebElement settlorCheckbox = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX));
        WebElement deceasedCheckbox = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX));

        if (settlorCheckbox == null || deceasedCheckbox == null) {
            throw new AutomationException("Either 'Settlor' or 'Deceased' checkbox is not present on the page 4.");
        }

        boolean isSettlorSelected = DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).isSelected();
        boolean isDeceasedSelected = DriverFactory.drivers.get().findElement(By.xpath(DECEASED_CHECKBOX)).isSelected();

        String selectedRolePage4 = null;
        if (isSettlorSelected) {
            selectedRolePage4 = "Settlor";
        } else if (isDeceasedSelected) {
            selectedRolePage4 = "Deceased";
        } else {
            throw new AutomationException("Neither 'Settlor' nor 'Deceased' checkbox is selected on Page 4.");
        }

        if (!selectedRolePage4.equals(selectedRolePage1)) {
            throw new AutomationException("Selected checkboxes \"Settlor & Deceased\" from Page 1 are not displayed accurately on Page 4.");
        }
    }

    private static void switchToPage(int pageNumber) throws AutomationException {
        driverUtil.getWebElement(String.format(PAGE_NUMBER_DYNAMIC_XPATH, pageNumber)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void userResetsTheOCForm() throws AutomationException {
        switchToPage(1);
        userClicksTheUseDigitYearCheckbox();
        WebDriverUtil.waitForAWhile();
        WebElement fileNumberFieldReset = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberFieldReset.clear();
        fileNumberFieldReset.sendKeys(initialFileNumber);
        WebDriverUtil.waitForAWhile();
        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);
        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }

        scrollToElement(SETTLOR_CHECKBOX);
        DriverFactory.drivers.get().findElement(By.xpath(SETTLOR_CHECKBOX)).click();
    }

    public void userSelectsAttorneyContact() throws IOException, ParseException, AutomationException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney1").get("attorney1.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney1").get("attorney1.lastName").toString();
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney1").get("attorney1.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney1").get("attorney1.suffix").toString();

        scrollToElement(NAME_OF_COUNSEL_FIELD);
        driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD).click();

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
        String attorneyAddress = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney1").get("attorney1.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney1").get("attorney1.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney1").get("attorney1.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney1").get("attorney1.phoneNumber").toString();
        String attorneyEmail = CommonUtil.getJsonPath("attorney1").get("attorney1.emailId").toString();

        nameOfCounselForm = getFieldValue(NAME_OF_COUNSEL_FIELD);
        if (!nameOfCounselForm.equals(selectedAttorney)) {
            throw new AutomationException("Attorney details not populated correctly in 'Name of Counsel' field. Expected: " + selectedAttorney + " ,Found: " + nameOfCounselForm);
        }

        String attorneyCitySateZip = attorneyCity + ", " + attorneySateCode + " " + attorneyZip;

        nameOfLawFirmForm = getFieldValue(NAME_OF_LAW_FIRM);
        attorneyAddressForm = getFieldValue(ATTORNEY_ADDRESS);
        attorneyCityStateZipForm = getFieldValue(ATTORNEY_CITY_STATE_ZIP);
        attorneyTelephoneForm = getFieldValue(ATTORNEY_TELEPHONE);
        attorneyEmailForm = getFieldValue(ATTORNEY_EMAIL);

        if (!attorneyFirmName.equals(nameOfLawFirmForm)) {
            throw new AutomationException("Name of Law Firm not correctly fetched. Expected: " + attorneyFirmName + ", Found: " + nameOfLawFirmForm);
        }

        if (!attorneyAddress.equals(attorneyAddressForm)) {
            throw new AutomationException("Attorney Address Line 1 not correctly fetched. Expected: " + attorneyAddress + ", Found: " + attorneyAddressForm);
        }

        if (!attorneyCitySateZip.equals(attorneyCityStateZipForm)) {
            throw new AutomationException("Attorney Address Line 2 (City, State code, Zip) not correctly fetched. Expected: " + attorneyCitySateZip + ", Found: " + attorneyCityStateZipForm);
        }

        if (!attorneyTelephone.equals(attorneyTelephoneForm)) {
            throw new AutomationException("Attorney Telephone not correctly fetched. Expected: " + attorneyTelephone + ", Found: " + attorneyTelephoneForm);
        }

        if (!attorneyEmail.equals(attorneyEmailForm)) {
            throw new AutomationException("Attorney Email not correctly fetched. Expected: " + attorneyEmail + ", Found: " + attorneyEmailForm);
        }
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
                : System.getProperty("user.dir") + "/downloads/")
                + downloadedFileName;
        try {

            boolean isVerifiedFileNumber = verifyFieldsInPDF(pdfFilePath,
                    "ESTATE OF Sara Watt   SETTLOR    DECEASED",
                    "Dear Sir or Madam:",
                    fileNumberForm,
                    "file number");

            Map<String, String> expectedCounselDetails = new HashMap<>();
            expectedCounselDetails.put("Name of Counsel", nameOfCounselForm);
            expectedCounselDetails.put("Name of Law Firm", nameOfLawFirmForm);
            expectedCounselDetails.put("Address", attorneyAddressForm);
            expectedCounselDetails.put("CityStateZip", attorneyCityStateZipForm);
            expectedCounselDetails.put("Telephone", attorneyTelephoneForm);
            expectedCounselDetails.put("Email", attorneyEmailForm);
            boolean isVerifiedCounselDetails = verifyCounselDetails(pdfFilePath, expectedCounselDetails);

            if (!isVerifiedFileNumber || !isVerifiedCounselDetails) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }

            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (Exception e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }

    private static boolean verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName)
            throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        // 🔍 Log Full PDF Content with Line Numbers
        CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);
        }
        int startIndex = -1, endIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();
            if (line.contains(beforeLine.trim())) {
                startIndex = i;
            }
            if (line.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }

        StringBuilder extractedBlock = new StringBuilder();
        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();
            if (!line.isEmpty()) {
                extractedBlock.append(line).append(" ");
            }
        }

        String actual = extractedBlock.toString()
                .replaceFirst("(?i)^No\\.\\s*", "") // Remove leading 'No.' (case-insensitive)
                .replaceAll("\\s+", " ")
                .trim();
        String expected = expectedValue.replaceAll("\\s+", " ").trim();

        CommonSteps.logInfo("🔍 Comparing block -> Expected: '" + expected + "', Extracted: '" + actual + "'");

        if (!expected.equalsIgnoreCase(actual)) {
            throw new AutomationException("❌ Validation Failed: Block content does not match for '" + fieldName + "'");
        }

        CommonSteps.logInfo("✅ Validation Passed: '" + fieldName + "' block matches expected.");
        return true;
    }

    public static boolean verifyCounselDetails(String pdfFilePath, Map<String, String> expected) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(document);
        document.close();

        // Normalize text by removing extra spaces and splitting by lines
        List<String> lines = Arrays.stream(pdfText.split("\\r?\\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        Map<String, String> extracted = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            switch (lines.get(i)) {
                case "Name of Counsel":
                    extracted.put("Name of Counsel", lines.get(i - 1));
                    break;
                case "Name of Law Firm":
                    extracted.put("Name of Law Firm", lines.get(i - 1));
                    break;
                case "Address":
                    extracted.put("Address", lines.get(i - 1));
                    break;
                case "City, State, Zip":
                    extracted.put("CityStateZip", lines.get(i - 1));
                    break;
                case "Telephone":
                    extracted.put("Telephone", lines.get(i - 1));
                    break;
                case "Email":
                    extracted.put("Email", lines.get(i - 1));
                    break;
            }
        }

        boolean isValid = true;
        for (String key : expected.keySet()) {
            String expectedValue = expected.get(key).trim();
            String actualValue = extracted.getOrDefault(key, "").trim();

            CommonSteps.logInfo("🔍 Comparing -> Field: " + key + " | Expected: '" + expectedValue + "' | Extracted: '" + actualValue + "'");

            if (!expectedValue.equalsIgnoreCase(actualValue)) {
                throw new AutomationException("❌ Mismatch in field '" + key + "': Expected '" + expectedValue + "' but found '" + actualValue + "'");
            }
        }

        CommonSteps.logInfo("[SUCCESS] ✅ All Counsel Details Matched.");
        return true;
    }
}


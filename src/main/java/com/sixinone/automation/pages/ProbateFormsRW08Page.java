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
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsRW08Page extends BasePage {
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
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String WILL_NUMBER_FIELD = "//input[@name='willNumber']";
    private static final String DATE_LETTER_GRANTED = "//input[@name='dateLetterGranted']";
    private static final String SIGNED_DATE = "//input[@name='signedDate']";
    private static final String SERVED_DATE = "//input[@name='date']";
    private static final String USE_WILL_NUM_CHECKBOX = "//input[@name='fileNumIsWill' and @value='1']";
    private static final String USE_4_DIGIT_YEAR_CHECKBOX = "//input[@name='fileNumIsWill' and @value='2']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String SAVE_BTN = "//button[text()='Save']";
    private static final String BENE_NAME_FIELD = "//td[@class='tr1']//p//input[not(@disabled)]";
    private static final String TABLE_NAME_COLUMN = "//td[@class='tr1']//p//input";
    private static final String VIEW_ATTACHMENT_BTN = "//span[@class='ms-2 view-attachment']";
    private static final String ATTACHMENT_NAME_COLUMN = "//div[@class='modal-body']//td[@class='tr1 td23']//p//input";
    private static final String CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String MAIN_COUNT = "//div[@class='main_count blue-text']//span";
    private static final String ATTACHMENT_COUNT = "//div[@class='attached_count blue-text']//b";
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT = "//input[@name='displayAllBeny']";
    private static final String CORPORATE_FIDUCIARY_NAME_FIELD = "//input[@name='fullname']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String PERSON_NAME_FIELD_RW08 = "//p[text()='Name of Person']/preceding-sibling::div//p//input";
    private static final String PERSON_NAME_FIELD_RW07 = "//p[text()='Name of Person']/preceding-sibling::p//input";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String PRINT_FORM_TOOLTIP = "//div[@role='tooltip']";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String downloadedFileName;

    static String initialFileNumberForm;
    static String fourDigitFileNumberForm;
    static String willNumberForm;
    static String dateLetterGrantedForm;
    static String signedDateForm;
    static String servedDateForm;
    static String Beneficiary1Form;
    static String Beneficiary2Form;
    static String Beneficiary3Form;
    static String Beneficiary4Form;
    static String Beneficiary5Form;
    static String Beneficiary6Form;
    static String Beneficiary7Form;
    static int selectedContactsCount;
    static int expectedMainCountForm;
    static int expectedAttachmentCountForm;
    static String selectedNameOfCorporateFiduciary;
    static String selectedNameOfPerson;
    static String domicileCountryForm;
    static String displayNameForm;
    static String alsoKnownAsForm;
    static String fileNumberForm;
    static String fiduciaryCityStateCodeZip;
    static String fiduciaryAddressLine1Form;
    static String corporateFiduciaryFirm;
    static String fiduciaryPhoneForm;
    static String fiduciaryEmailForm;
    static String attorneyAddressLine1Form;
    static String attorneyPhoneForm;
    static String attorneyEmailForm;
    static String attorneyCityStateCodeZip;
    static String beneAddress1Form;
    static String beneAddress2Form;
    static String beneAddress3Form;
    static String beneAddress4Form;
    static String beneAddress5Form;
    static String beneAddress6Form;
    static String beneAddress7Form;


    static String enteredCityStateZipForm;

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
        estateInfo.put("SSN", getFieldValue(DECEDENT_SSN_FIELD));
        estateInfo.put("AlsoKnownAs", getFieldValue(DECEDENT_ALSO_KNOWN_AS));
        estateInfo.put("DomicileAddressLine1", getFieldValue(DOMICILE_ADDRESS_LINE1));
        estateInfo.put("DomicileAddressLine2", getFieldValue(DOMICILE_ADDRESS_LINE2));
        String enteredZipForm = estateInfo.put("DomicileZip", getFieldValue(DOMICILE_ZIP));
        String enteredCityForm = estateInfo.put("DomicileCity", getFieldValue(DOMICILE_CITY));
        String enteredStateForm = estateInfo.put("DomicileState", getFieldValue(DOMICILE_STATE));
        enteredCityStateZipForm = enteredZipForm + "," + enteredCityForm + enteredStateForm;
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

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyCountyEstateFileNumberAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        domicileCountryForm = getEstateValue("DomicileCountry");
        displayNameForm = getEstateValue("DisplayName");
        alsoKnownAsForm = getEstateValue("AlsoKnownAs");
        fileNumberForm = getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3");

        verifyAutoPopulatedValue(domicileCountryForm);
        verifyAutoPopulatedValue(displayNameForm);
        verifyAutoPopulatedValue(alsoKnownAsForm);
        verifyAutoPopulatedValue(fileNumberForm);

        initialFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);

        if (field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        String domicileCountryField = String.format(RW_INPUT_FIELD_XPATH, domicileCountryForm);
        String displayNameField = String.format(RW_INPUT_FIELD_XPATH, displayNameForm);
        String alsoKnownAsField = String.format(RW_INPUT_FIELD_XPATH, alsoKnownAsForm);

        WebDriverUtil.waitForAWhile(2);
        verifyFieldIsNotEditable(domicileCountryField);
        verifyFieldIsNotEditable(displayNameField);
        verifyFieldIsNotEditable(alsoKnownAsField);
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    private void fillFieldWithKeystrokes(WebElement field, String data) throws AutomationException {
        for (char c : data.toCharArray()) {
            field.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();
    }

    public void verifyWillNumberAndDatesCanBeEnteredAndAreAutoSaved() throws AutomationException, IOException, ParseException {
        String dateLetterGranted = CommonUtil.getJsonPath("RW08Form").get("RW08Form.dateLetterGranted").toString();
        String signedDate = CommonUtil.getJsonPath("RW08Form").get("RW08Form.signedDate").toString();
        String servedDate = CommonUtil.getJsonPath("RW08Form").get("RW08Form.servedDate").toString();

        String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        Pattern pattern = Pattern.compile(datePattern);

        WebElement dateLetterGrantedField = driverUtil.getWebElement(DATE_LETTER_GRANTED);
        WebElement signedDateField = driverUtil.getWebElement(SIGNED_DATE);
        WebElement servedDateField = driverUtil.getWebElement(SERVED_DATE);

        scrollToElementAndClick(DATE_LETTER_GRANTED);
        fillFieldWithKeystrokes(dateLetterGrantedField, dateLetterGranted);

        scrollToElementAndClick(SIGNED_DATE);
        fillFieldWithKeystrokes(signedDateField, signedDate);

        scrollToElementAndClick(SERVED_DATE);
        fillFieldWithKeystrokes(servedDateField, servedDate);


        WebDriverUtil.waitForAWhile();
        dateLetterGrantedForm = driverUtil.getWebElement(DATE_LETTER_GRANTED).getAttribute("value");
        signedDateForm = driverUtil.getWebElement(SIGNED_DATE).getAttribute("value");
        servedDateForm = driverUtil.getWebElement(SERVED_DATE).getAttribute("value");

        if (!pattern.matcher(dateLetterGrantedForm).matches()) {
            throw new AutomationException("Invalid Letter Granted date format: " + dateLetterGrantedForm + ". Expected format is MM/DD/YYYY.");
        }

        if (!pattern.matcher(signedDateForm).matches()) {
            throw new AutomationException("Invalid Singed date format: " + signedDateForm + ". Expected format is MM/DD/YYYY.");
        }

        if (!pattern.matcher(servedDateForm).matches()) {
            throw new AutomationException("Invalid Served date format: " + servedDateForm + ". Expected format is MM/DD/YYYY.");
        }
    }

    public void userCheckTheCheckbox() throws AutomationException {
        DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();
    }

    public void verifyDisplayedFileNumber() throws AutomationException {
        fourDigitFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        String expectedFourDigitFileNumber = initialFileNumberForm.replaceFirst("-(\\d{2})-", "-20$1-");

        if (!fourDigitFileNumberForm.equals(expectedFourDigitFileNumber)) {
            throw new AutomationException("File number did not update correctly. Expected: " + expectedFourDigitFileNumber + ", Found: " + fourDigitFileNumberForm);
        }
    }

    public void verifyMultipleBeneficiaryContactsCanBeSelectedAndDisplayedOnTheForm() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        scrollToElementAndClick(BENE_NAME_FIELD);

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        Beneficiary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary6Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary7Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Additional beneficiaries will be added to an attachment.")));

        verifyBeneficiariesDisplayedCorrectlyOnForm();


        for (int i = 1; i <= 7; i++) {
            String beneficiaryPrefix = "beneficiary" + i;

            String beneficiaryCity = CommonUtil.getJsonPath(beneficiaryPrefix).get(beneficiaryPrefix + ".city").toString();
            String beneficiaryState = CommonUtil.getJsonPath(beneficiaryPrefix).get(beneficiaryPrefix + ".stateCode").toString();
            String beneficiaryZip = CommonUtil.getJsonPath(beneficiaryPrefix).get(beneficiaryPrefix + ".zip").toString();
            String beneficiaryAddress = CommonUtil.getJsonPath(beneficiaryPrefix).get(beneficiaryPrefix + ".addressLine1").toString();

            String beneficiaryCityStateZip = beneficiaryCity + ", " + beneficiaryState + " " + beneficiaryZip;
            String beneAddressForm = beneficiaryAddress + ", " + beneficiaryCityStateZip;

            switch (i) {
                case 1:
                    beneAddress1Form = beneAddressForm;
                    break;
                case 2:
                    beneAddress2Form = beneAddressForm;
                    break;
                case 3:
                    beneAddress3Form = beneAddressForm;
                    break;
                case 4:
                    beneAddress4Form = beneAddressForm;
                    break;
                case 5:
                    beneAddress5Form = beneAddressForm;
                    break;
                case 6:
                    beneAddress6Form = beneAddressForm;
                    break;
                case 7:
                    beneAddress7Form = beneAddressForm;
                    break;
            }
        }


    }

    private void verifyBeneficiariesDisplayedCorrectlyOnForm() throws AutomationException {
        WebDriverUtil.waitForAWhile(3);
        List<WebElement> nameFields = driverUtil.getWebElements(TABLE_NAME_COLUMN);

        List<String> selectedBeneficiaries = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary6Form,
                Beneficiary7Form,
                Beneficiary5Form,
                Beneficiary2Form,
                Beneficiary3Form
        );

        selectedContactsCount = selectedBeneficiaries.size();
        expectedMainCountForm = nameFields.size();

        for (int row = 0; row < nameFields.size(); row++) {
            String actualName = nameFields.get(row).getAttribute("value").trim();

            if (!actualName.equals(selectedBeneficiaries.get(row))) {
                throw new AutomationException("Mismatch in Name at Row " + (row + 1) + ",Expected: " + selectedBeneficiaries.get(row) + " ,Found: " + actualName);
            }
        }
    }

    public void verifyBeneficiaryContactsSelectedBeyond6AreDisplayedOnTheAttachment() throws AutomationException {
        scrollToElementAndClick(VIEW_ATTACHMENT_BTN);

        WebDriverUtil.waitForAWhile();

        List<WebElement> nameOnAttachment = driverUtil.getWebElements(ATTACHMENT_NAME_COLUMN);

        expectedAttachmentCountForm = nameOnAttachment.size();

        for (int row = 0; row < nameOnAttachment.size(); row++) {
            String actualName = nameOnAttachment.get(row).getAttribute("value").trim();

            if (!actualName.equals(Beneficiary3Form)) {
                throw new AutomationException("Mismatch in Name at Row " + (row + 1) + ",Expected: " + Beneficiary3Form + " ,Found: " + actualName);
            }
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(CLOSE_BTN).click();

    }

    public void verifyMainAndAttachmentCountIsDisplayedCorrectly() throws AutomationException {
        WebElement mainCountElement = driverUtil.getWebElement(MAIN_COUNT);
        WebElement attachmentCountElement = driverUtil.getWebElement(ATTACHMENT_COUNT);

        int actualMainCount = Integer.parseInt(mainCountElement.getText().trim());
        int actualAttachmentCount = Integer.parseInt(attachmentCountElement.getText().split(":")[1].trim());

        if (actualMainCount != expectedMainCountForm) {
            throw new AutomationException("Mismatch in Main Count. Expected: " + expectedMainCountForm + ", Found: " + actualMainCount);
        }
        if (actualAttachmentCount != expectedAttachmentCountForm) {
            throw new AutomationException("Mismatch in Attachment Count. Expected: " + expectedAttachmentCountForm + ", Found: " + actualAttachmentCount);
        }
    }

    public void userChecksDisplayALLBeneficiaryOnAttachmentCheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT)).click();
    }

    public void verifyAllTheBeneficiariesAreTransferredToAttachment() throws AutomationException {
        List<String> selectedBeneficiaries = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary6Form,
                Beneficiary7Form,
                Beneficiary5Form,
                Beneficiary2Form,
                Beneficiary3Form
        );

        scrollToElementAndClick(VIEW_ATTACHMENT_BTN);

        WebDriverUtil.waitForAWhile(2);
        List<WebElement> nameOnAttachment = driverUtil.getWebElements(ATTACHMENT_NAME_COLUMN);

        expectedAttachmentCountForm = nameOnAttachment.size();

        for (int row = 0; row < nameOnAttachment.size(); row++) {
            String actualName = nameOnAttachment.get(row).getAttribute("value").trim();

            if (!actualName.equals(selectedBeneficiaries.get(row))) {
                throw new AutomationException("Mismatch in Name at Row " + (row + 1) + ",Expected: " + selectedBeneficiaries.get(row) + " ,Found: " + actualName);
            }
        }

        driverUtil.getWebElement(CLOSE_BTN).click();

        WebDriverUtil.waitForAWhile();
        List<WebElement> nameFields = driverUtil.getWebElements(TABLE_NAME_COLUMN);
        expectedMainCountForm = nameFields.size();

        verifyMainAndAttachmentCountIsDisplayedCorrectly();
    }

    public void verifyAnyOneOfTheFiduciaryContactsCanBeSelected() throws AutomationException, IOException, ParseException {
        corporateFiduciaryFirm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.entityName").toString();

        String fiduciaryCityForm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.city").toString();
        String fiduciaryStateForm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.stateCode").toString();
        String fiduciaryZipForm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.zip").toString();
        fiduciaryAddressLine1Form = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.addressLine1").toString();
        fiduciaryPhoneForm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.phoneNumber").toString();
        fiduciaryEmailForm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.emailId").toString();

        fiduciaryCityStateCodeZip = fiduciaryCityForm + ", " + fiduciaryStateForm + " " + fiduciaryZipForm;

        scrollToElementAndClick(CORPORATE_FIDUCIARY_NAME_FIELD);

        WebDriverUtil.waitForAWhile();

        WebElement corporateFiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, corporateFiduciaryFirm));

        corporateFiduciaryToSelect.click();

        if (!corporateFiduciaryToSelect.isSelected()) {
            throw new AutomationException("Unable to select the corporate fiduciary contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Corporate Fiduciary updated successfully.")));

        selectedNameOfCorporateFiduciary = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
    }

    public void verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected() throws AutomationException, IOException, ParseException {
        String fiduciaryFirstName = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.firstName").toString();
        String fiduciaryLastName = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.lastName").toString() + ",";
        String fiduciaryMiddleName = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.middleName").toString();
        String fiduciarySuffix = CommonUtil.getJsonPath("fiduciary3").get("fiduciary3.suffix").toString();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Fiduciary")) {
            throw new AutomationException("Fiduciary type of contacts are not displayed.");
        }

        String fiduciaryContactToSelect = fiduciaryFirstName + " " + fiduciaryMiddleName + " " + fiduciaryLastName + " " + fiduciarySuffix;

        WebDriverUtil.waitForAWhile();
        WebElement fiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, fiduciaryContactToSelect));

        WebDriverUtil.waitForAWhile();
        fiduciaryToSelect.click();

        if (!fiduciaryToSelect.isSelected()) {
            throw new AutomationException("Unable to select the fiduciary contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Individual Fiduciary updated successfully.")));
    }

    public void verifyAttorneyTypeOfContactAreDisplayedInTheListAndCanBeSelected() throws IOException, ParseException, AutomationException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney2").get("attorney2.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney2").get("attorney2.lastName").toString() + ",";
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney2").get("attorney2.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney2").get("attorney2.suffix").toString();

        String attorneyCityForm = CommonUtil.getJsonPath("attorney2").get("attorney2.city").toString();
        String attorneyStateForm = CommonUtil.getJsonPath("attorney2").get("attorney2.stateCode").toString();
        String attorneyZipForm = CommonUtil.getJsonPath("attorney2").get("attorney2.zip").toString();
        attorneyAddressLine1Form = CommonUtil.getJsonPath("attorney2").get("attorney2.addressLine1").toString();
        attorneyPhoneForm = CommonUtil.getJsonPath("attorney2").get("attorney2.phoneNumber").toString();
        attorneyEmailForm = CommonUtil.getJsonPath("attorney2").get("attorney2.emailId").toString();

        attorneyCityStateCodeZip = attorneyCityForm + ", " + attorneyStateForm + " " + attorneyZipForm;


        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Attorney")) {
            throw new AutomationException("Attorney type of contacts are not displayed.");
        }

        String attorneyContactToSelect = attorneyFirstName + " " + attorneyMiddleName + " " + attorneyLastName + " " + attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, attorneyContactToSelect));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if (!attorneyToSelect.isSelected()) {
            throw new AutomationException("Unable to select the attorney contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel (Attorney) updated successfully.")));

        selectedNameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD_RW08).getAttribute("value");
        if (!(selectedNameOfPerson.contains("Jr.") || selectedNameOfPerson.contains("Sr."))) {
            selectedNameOfPerson = selectedNameOfPerson.replace(",", "");
        }
    }

    public void verifySelectedContactsAreCleared() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryValue = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value").trim();
        String personNameValue = driverUtil.getWebElement(PERSON_NAME_FIELD_RW08).getAttribute("value").trim();

        if (!corporateFiduciaryValue.isEmpty()) {
            throw new AutomationException("Corporate Fiduciary selection is not cleared. Found value: " + corporateFiduciaryValue);
        }

        if (!personNameValue.isEmpty()) {
            throw new AutomationException("Fiduciary/Attorney selection is not cleared. Found value: " + personNameValue);
        }
    }

    public void verifyCorporateFiduciaryAndPersonSectionsInformationIsCommon() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryName = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD_RW07).getAttribute("value");

        if (!corporateFiduciaryName.equals(selectedNameOfCorporateFiduciary)) {
            throw new AutomationException("Changes made in Corporate Fiduciary section on RW07 are not reflected on RW08. Expected Name: " + selectedNameOfCorporateFiduciary + " ,Found: " + corporateFiduciaryName);
        }

        if (!nameOfPerson.equals(selectedNameOfPerson)) {
            throw new AutomationException("Changes made in Person section on RW07 are not reflected on RW08. Expected Name: " + selectedNameOfPerson + " ,Found: " + nameOfPerson);
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
            boolean isVerifiedDateLettersGranted = verifyDateLettersGranted(pdfFilePath);

            boolean isVerifiedServedDate = verifyFieldsInPDF(pdfFilePath,
                    "Date of Death: 12/05/2023 File Number:22-2023-1234",
                    "See Attachment",
                    servedDateForm,
                    "Served Date");

            boolean isVerifiedSignedDate = verifyFieldsInPDF(pdfFilePath,
                    "Rules was served on or mailed to the following beneficiaries of the above-captioned estate on",
                    "Corporate Fiduciary (if applicable)",
                    signedDateForm,
                    "Signed Date");

            boolean isVerifiedFileNumber = verifyFieldsInPDF(pdfFilePath,
                    "a/k/a Krish",
                    "02/25/2025 :",
                    fourDigitFileNumberForm,
                    "File Number");

            boolean isverifiedCorporateFiduciaryAndPersonDetails = verifyCorporateFiduciaryAndPersonDetails(pdfFilePath);

            List<String> expectedNames = Arrays.asList(
                    Beneficiary1Form,
                    Beneficiary2Form,
                    Beneficiary3Form,
                    Beneficiary4Form,
                    Beneficiary5Form,
                    Beneficiary6Form,
                    Beneficiary7Form
            );

            List<String> expectedAddresses = Arrays.asList(
                    beneAddress1Form,
                    beneAddress4Form,
                    beneAddress6Form,
                    beneAddress7Form,
                    beneAddress3Form,
                    beneAddress5Form,
                    beneAddress2Form
            );

            boolean isVerifiedAllNamesAndAddresses = verifyAllNamesAndAddresses(pdfFilePath, expectedNames, expectedAddresses);

            if (!isVerifiedDateLettersGranted || isVerifiedServedDate || isVerifiedSignedDate || !isVerifiedFileNumber || !isverifiedCorporateFiduciaryAndPersonDetails || !isVerifiedAllNamesAndAddresses) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }
            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (AutomationException | IOException e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }

    private static boolean verifyDateLettersGranted(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "To the Register:";  // The next line after the date

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("📄 **Full PDF Content (All Lines):**");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("Line " + (i + 1) + ": " + allLines[i]); // Log every line
        }

        String extractedDate = null;
        boolean beforeLineFound = false;

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Find the first occurrence of "To the Register:"
            if (trimmedLine.equalsIgnoreCase(beforeLine)) {
                beforeLineFound = true;
                if (i > 0) {
                    extractedDate = allLines[i - 1].trim();  // Extract date from the previous line
                    extractedDate = clean(extractedDate, "Date Letters Granted"); // Clean the extracted value

                    if (!extractedDate.isEmpty()) {
                        break;  // Stop after the first valid extraction
                    }
                }
            }
        }

        // Log a warning if beforeLine is missing
        if (!beforeLineFound) {
            CommonSteps.logInfo("⚠️ Warning: 'To the Register:' not found in the PDF.");
        }

        if (extractedDate == null || extractedDate.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No Date field found before 'To the Register:'.");
        }

        // 📌 Log the extracted date
        CommonSteps.logInfo("📌 Extracted Date Letters Granted: '" + extractedDate + "'");

        // Expected Date for comparison (assuming it holds only one expected date)
        String expectedDate = dateLetterGrantedForm;

        // 🔍 Validate extracted date
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedDate + "', Extracted: '" + extractedDate + "'");

        if (!expectedDate.equalsIgnoreCase(extractedDate)) {
            throw new AutomationException("❌ Validation Failed: Extracted Date '" + extractedDate + "' does not match expected value.");
        }

        // ✅ Final validation success message
        CommonSteps.logInfo("✅ Validation Passed: Extracted date matches the expected value.");
        return true;
    }


    private static boolean verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");
        int startIndex = -1, endIndex = -1;
        String extractedValue = "";

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
                    extractedValue = clean(currentLine, fieldName);
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

    private static boolean verifyCorporateFiduciaryAndPersonDetails(String pdfFilePath) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        // Fields for Corporate Fiduciary (1st occurrence)
        String corporateFiduciary = null, corpAddress = null, corpCityStateZip = null, corpTelephone = null, corpEmail = null;

        // Fields for Name of Person (2nd occurrence)
        String personName = null, personAddress = null, personCityStateZip = null, personTelephone = null, personEmail = null;

        boolean personFoundFirstTime = false; // Track first occurrence of "Name of Person"

        for (int i = 0; i < allLines.length; i++) {
            String currentLine = allLines[i].trim();

            // Extract Corporate Fiduciary (1st occurrence)
            if (corporateFiduciary == null && currentLine.equalsIgnoreCase("Name of Corporate Fiduciary") && i > 0) {
                corporateFiduciary = clean(allLines[i - 1], "Corporate Fiduciary");
            } else if (corporateFiduciary != null && corpAddress == null && currentLine.equalsIgnoreCase("Address") && i > 0) {
                corpAddress = clean(allLines[i - 1], "Address");
            } else if (corporateFiduciary != null && corpCityStateZip == null && currentLine.equalsIgnoreCase("City, State, Zip") && i > 0) {
                corpCityStateZip = clean(allLines[i - 1], "CityStateZip");
            } else if (corporateFiduciary != null && corpTelephone == null && currentLine.equalsIgnoreCase("Telephone") && i > 0) {
                corpTelephone = clean(allLines[i - 1], "Telephone");
            } else if (corporateFiduciary != null && corpEmail == null && currentLine.equalsIgnoreCase("Email") && i > 0) {
                corpEmail = clean(allLines[i - 1], "Email");
            }

            // Extract Name of Person (2nd occurrence)
            if (currentLine.equalsIgnoreCase("Name of Person") && i > 0) {
                if (!personFoundFirstTime) {
                    personFoundFirstTime = true; // Mark the first occurrence but do nothing
                } else if (personName == null) { // Now process the second occurrence
                    personName = clean(allLines[i - 1], "Person Name");
                }
            } else if (personFoundFirstTime && personName != null && personAddress == null && currentLine.equalsIgnoreCase("Address") && i > 0) {
                personAddress = clean(allLines[i - 1], "Address");
            } else if (personFoundFirstTime && personName != null && personCityStateZip == null && currentLine.equalsIgnoreCase("City, State, Zip") && i > 0) {
                personCityStateZip = clean(allLines[i - 1], "CityStateZip");
            } else if (personFoundFirstTime && personName != null && personTelephone == null && currentLine.equalsIgnoreCase("Telephone") && i > 0) {
                personTelephone = clean(allLines[i - 1], "Telephone");
            } else if (personFoundFirstTime && personName != null && personEmail == null && currentLine.equalsIgnoreCase("Email") && i > 0) {
                personEmail = clean(allLines[i - 1], "Email");
            }

            // Exit loop early if all values are found
            if (corporateFiduciary != null && personName != null &&
                    corpAddress != null && corpCityStateZip != null && corpTelephone != null && corpEmail != null &&
                    personAddress != null && personCityStateZip != null && personTelephone != null && personEmail != null) {
                break;
            }
        }

        // Validate Corporate Fiduciary Details (1st occurrence)
        if (corporateFiduciary != null) {
            validateField("Corporate Fiduciary", corporateFiduciary, corporateFiduciaryFirm);
            validateField("Corporate Address", corpAddress, fiduciaryAddressLine1Form);
            validateField("Corporate City, State, Zip", corpCityStateZip, fiduciaryCityStateCodeZip);
            validateField("Corporate Telephone", corpTelephone, fiduciaryPhoneForm);
            validateField("Corporate Email", corpEmail, fiduciaryEmailForm);
        }

        // Validate Name of Person Details (2nd occurrence)
        if (personName != null) {
            validateField("Name of Person", personName, selectedNameOfPerson);
            validateField("Person Address", personAddress, attorneyAddressLine1Form);
            validateField("Person City, State, Zip", personCityStateZip, attorneyCityStateCodeZip);
            validateField("Person Telephone", personTelephone, attorneyPhoneForm);
            validateField("Person Email", personEmail, attorneyEmailForm);
        }

        if (corporateFiduciary == null && personName == null) {
            throw new AutomationException("❌ Validation Failed: Neither Corporate Fiduciary nor Name of Person was found.");
        }

        CommonSteps.logInfo("✅ Validation Passed: Corporate Fiduciary and Name of Person details successfully matched.");
        return true;
    }

    private static boolean verifyAllNamesAndAddresses(String pdfFilePath, List<String> expectedNames, List<String> expectedAddresses)
            throws IOException, AutomationException {

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String beforeLine = "Address";
        String afterLine = "Copyright (c) 2016 form software only The Lackner Group, Inc.Form RW-08 eff. 09.01.16";

        List<String> extractedLines = extractDataAfterThirdOccurrence(pdfText, beforeLine, afterLine);
        if (extractedLines.isEmpty()) {
            throw new AutomationException("❌ No data found between the third occurrence of '" + beforeLine + "' and '" + afterLine + "'");
        }

        List<String> extractedNames = new ArrayList<>();
        List<String> extractedAddresses = new ArrayList<>();

        // Extracting names and addresses
        for (String line : extractedLines) {
            int firstCommaIndex = line.indexOf(".");
            if (firstCommaIndex != -1) {
                String name = line.substring(0, firstCommaIndex).trim();
                String address = line.substring(firstCommaIndex + 1).trim();

                extractedNames.add(name);
                extractedAddresses.add(address);
            } else {
                throw new AutomationException("❌ Could not split name and address properly in line: " + line);
            }
        }

        if (extractedNames.size() != expectedNames.size() || extractedAddresses.size() != expectedAddresses.size()) {
            throw new AutomationException("❌ Mismatch in extracted and expected name/address counts!");
        }

        for (int i = 0; i < expectedNames.size(); i++) {
            CommonSteps.logInfo("🔍Comparing Expected Name: " + expectedNames.get(i) + " and Address : " + expectedAddresses.get(i) +
                    ". Extracted -> Name: " + extractedNames.get(i) + " and Address : " + extractedAddresses.get(i));

        }
        return true;
    }

    private static List<String> extractDataAfterThirdOccurrence(String text, String beforeLine, String afterLine) {
        List<String> extractedData = new ArrayList<>();
        boolean capture = false;
        int addressCount = 0;

        for (String line : text.split("\\r?\\n")) {
            line = line.trim();

            if (line.equalsIgnoreCase(beforeLine)) {
                addressCount++;
                if (addressCount == 3) { // Start capturing after the third occurrence
                    capture = true;
                    continue;
                }
            }

            if (capture) {
                if (line.equalsIgnoreCase(afterLine)) {
                    break;  // Stop capturing at afterLine
                }
                if (!line.isEmpty()) {
                    extractedData.add(line);
                }
            }
        }
        return extractedData;
    }

    private static void validateField(String fieldName, String extracted, String expected) throws AutomationException {
        CommonSteps.logInfo("🔍 Comparing -> " + fieldName + " | Expected: '" + expected + "', Extracted: '" + extracted + "'");


        if (extracted == null) {
            throw new AutomationException("❌ Validation Failed: '" + fieldName + "' was not found in the document.");
        }

        if (!expected.equalsIgnoreCase(extracted)) {
            throw new AutomationException("❌ Validation Failed: '" + fieldName + "' does not match expected value.");
        }

        CommonSteps.logInfo("✅ Validation Passed: '" + fieldName + "' matches expected.");
    }


    private static String clean(String rawText, String fieldType) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        String cleanedText = rawText.trim();

        switch (fieldType.toLowerCase()) {
            case "date letters granted":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Date Letters Granted: )\\b", "").trim();
                break;

            case "decedent name":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Name of Decedent: )\\b", "").trim();
                break;

            case "signed date":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Date )\\b", "").trim();
                break;

            case "date of death":
                cleanedText = cleanedText.replaceAll("(?i)Date of Death:\\s*", "") // Remove "Date of Death:"
                        .replaceAll("\\s*File Number:.*$", "") // Remove "File Number" and everything after it
                        .replaceAll("[:]+$", "") // Remove trailing colons if any
                        .trim();
                break;

            case "file number":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Date of Death: 12/05/2023 File Number:)\\b", "").trim();
                break;

            default:
                // Generic cleanup for any other case
                cleanedText = cleanedText.replaceAll("[:,\\.\\s]+$", "").trim();
                break;
        }

        return cleanedText;
    }

    public void userResetsTheRWForm() throws AutomationException {
        scrollToElementAndClick(DATE_LETTER_GRANTED);
        DriverFactory.drivers.get().findElement(By.xpath(DATE_LETTER_GRANTED)).clear();

        DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();

        scrollToElementAndClick(SIGNED_DATE);
        DriverFactory.drivers.get().findElement(By.xpath(SIGNED_DATE)).clear();

        scrollToElementAndClick(SERVED_DATE);
        DriverFactory.drivers.get().findElement(By.xpath(SERVED_DATE)).clear();

        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT)).click();
        WebDriverUtil.waitForAWhile(3);

        scrollToElementAndClick(SHOW_AKA_CHECkBOX);

        scrollToElementAndClick(BENE_NAME_FIELD);

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
        WebDriverUtil.waitForAWhile(2);
    }
}


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
    private static final String PERSON_NAME_FIELD = "//p[text()='Name of Person']/preceding-sibling::p//input";
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
        String willNumber = CommonUtil.getJsonPath("RW08Form").get("RW08Form.willNumber").toString();
        String dateLetterGranted = CommonUtil.getJsonPath("RW08Form").get("RW08Form.dateLetterGranted").toString();
        String signedDate = CommonUtil.getJsonPath("RW08Form").get("RW08Form.signedDate").toString();
        String servedDate = CommonUtil.getJsonPath("RW08Form").get("RW08Form.servedDate").toString();

        String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        Pattern pattern = Pattern.compile(datePattern);

        WebElement willNumberField = driverUtil.getWebElement(WILL_NUMBER_FIELD);
        WebElement dateLetterGrantedField = driverUtil.getWebElement(DATE_LETTER_GRANTED);
        WebElement signedDateField = driverUtil.getWebElement(SIGNED_DATE);
        WebElement servedDateField = driverUtil.getWebElement(SERVED_DATE);

        scrollToElementAndClick(WILL_NUMBER_FIELD);
        fillFieldWithKeystrokes(willNumberField, willNumber);

        scrollToElementAndClick(DATE_LETTER_GRANTED);
        fillFieldWithKeystrokes(dateLetterGrantedField, dateLetterGranted);

        scrollToElementAndClick(SIGNED_DATE);
        fillFieldWithKeystrokes(signedDateField, signedDate);

        scrollToElementAndClick(SERVED_DATE);
        fillFieldWithKeystrokes(servedDateField, servedDate);


        WebDriverUtil.waitForAWhile();
        willNumberForm = driverUtil.getWebElement(WILL_NUMBER_FIELD).getAttribute("value");
        dateLetterGrantedForm = driverUtil.getWebElement(DATE_LETTER_GRANTED).getAttribute("value");
        signedDateForm = driverUtil.getWebElement(SIGNED_DATE).getAttribute("value");
        servedDateForm = driverUtil.getWebElement(SERVED_DATE).getAttribute("value");

        if (!willNumberForm.equals(willNumber)) {
            throw new AutomationException("Will number is not entered correctly. Expected: " + willNumber + " ,Found: " + willNumberForm);
        }

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

    public void userCheckTheCheckbox(String checkboxToSelect) throws AutomationException {
        switch (checkboxToSelect) {
            case "Use Will Number":
                DriverFactory.drivers.get().findElement(By.xpath(USE_WILL_NUM_CHECKBOX)).click();
                break;
            case "Use 4 digit year":
                DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();
                break;
            default:
                throw new AutomationException("Unsupported checkbox : " + checkboxToSelect);
        }
    }

    private void verify4DigitFileNumber() throws AutomationException {
        fourDigitFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        String expectedFourDigitFileNumber = initialFileNumberForm.replaceFirst("-(\\d{2})-", "-20$1-");

        if (!fourDigitFileNumberForm.equals(expectedFourDigitFileNumber)) {
            throw new AutomationException("File number did not update correctly. Expected: " + expectedFourDigitFileNumber + ", Found: " + fourDigitFileNumberForm);
        }
    }

    private void verifyWillNumber() throws AutomationException {
        String willNumberInFileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        if (!willNumberInFileNumberField.equals(willNumberForm)) {
            throw new AutomationException("File number did not update correctly. Expected: " + willNumberForm + ", Found: " + willNumberInFileNumberField);
        }
    }

    public void verifyDisplayedFileNumber(String displayedFileNumber) throws AutomationException {
        switch (displayedFileNumber) {
            case "will number":
                verifyWillNumber();
                break;
            case "4 digit year":
                verify4DigitFileNumber();
                break;
            default:
                throw new AutomationException("Unsupported value : " + displayedFileNumber);
        }
    }

    public void verifyMultipleBeneficiaryContactsCanBeSelectedAndDisplayedOnTheForm() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        scrollToElementAndClick(BENE_NAME_FIELD);

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        Beneficiary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary6Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Beneficiary7Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText().replace(",","");
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Additional beneficiaries will be added to an attachment.")));

        verifyBeneficiariesDisplayedCorrectlyOnForm();
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
        String corporateFiduciaryFirm = CommonUtil.getJsonPath("corporateFiduciary1").get("corporateFiduciary1.entityName").toString();

        scrollToElementAndClick(CORPORATE_FIDUCIARY_NAME_FIELD);

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
        String fiduciaryFirstName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.firstName").toString();
        String fiduciaryLastName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.lastName").toString() + ",";
        String fiduciaryMiddleName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.middleName").toString();
        String fiduciarySuffix = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.suffix").toString();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Fiduciary")) {
            throw new AutomationException("Fiduciary type of contacts are not displayed.");
        }

        String fiduciaryContactToSelect = fiduciaryFirstName + " " + fiduciaryMiddleName + " " + fiduciaryLastName + " " + fiduciarySuffix;

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

        selectedNameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value").replace(",","");
    }

    public void verifySelectedContactsAreCleared() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryValue = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value").trim();
        String personNameValue = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value").trim();

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
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");

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

        verifyFieldInPDF(pdfFilePath,
                "REGISTER OF WILLS OF INDIANA COUNTY, PENNSYLVANIA",
                "a/k/a Krish",
                "Kris Warner",
                "Decedent Name");

        verifyFieldInPDF(pdfFilePath,
                "a/k/a Krish",
                "02/25/2025 :",
                "12/05/2023",
                "Date of Death");

        verifyDate1(pdfFilePath);

        List<String> pdfLines = Arrays.asList(
                "Date Letters Granted: 02/15/2025",
                "Corporate Fiduciary (if applicable)",
                "zetaConsulting",
                "Name of Corporate Fiduciary",
                "Name of Representative and Title",
                "Mountain View Drive",
                "Address",
                "Seattle, WA 98101",
                "City, State, Zip",
                "(206) 555-6789",
                "Telephone",
                "liam.anderson@zetaconsulting.com",
                "Email",
                "Rihan Benjamin Miles Jr.",
                "Name of Person",
                "Riverside Drive",
                "Address",
                "Kansas City, MO 64101",
                "City, State, Zip",
                "(816) 555-4321",
                "Telephone",
                "rihan.miles@business.com",
                "Email"
        );

        verifyRelatedCorporateFiduciaryDetails(pdfLines);

        verifyFieldInPDF(pdfFilePath,
                "a/k/a Krish",
                "02/25/2025 :",
                fourDigitFileNumberForm,
                "File Number");
    }

    private static void verifyDate1(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "To the Register:";  // The next line after the date

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("📄 **Full PDF Content (All Lines):**");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("Line " + (i + 1) + ": " + allLines[i]); // Log every line
        }

        int endIndex = -1;
        String extractedDate = "";

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Find the first occurrence of "(Date)"
            if (trimmedLine.equalsIgnoreCase(beforeLine)) {
                endIndex = i;
                break;
            }
        }

        // Extract the line before "(Date)"
        if (endIndex > 0) {  // Ensure it's not the first line
            extractedDate = allLines[endIndex - 1].trim();
        }

        if (extractedDate.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No Date field found before (Date).");
        }

        String extractDate = cleanName(extractedDate, "Date Letters Granted");

        String expectedDateForm1 = dateLetterGrantedForm;
        // Expected Date for comparison
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + dateLetterGrantedForm + "', Extracted: '" + extractDate + "'");

        if (expectedDateForm1.equalsIgnoreCase(extractDate)) {
            CommonSteps.logInfo("✅ Validation Passed: Extracted Date matches expected.");
        } else {
            throw new AutomationException("❌ Validation Failed: Extracted Date does not match expected value.");
        }
    }

    public static void verifyFieldInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName) throws IOException, AutomationException {
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
                    extractedValue = cleanName(currentLine, fieldName);
                    break; // Assuming only one line needs to be extracted
                }
            }

            CommonSteps.logInfo("📌 Extracted " + fieldName + ": " + extractedValue);

            if (extractedValue.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No '" + fieldName + "' found between specified lines.");
            }

            CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedValue + "', Extracted: '" + extractedValue + "'");

            if (!expectedValue.equalsIgnoreCase(extractedValue)) {
                throw new AutomationException("❌ Validation Failed: '" + fieldName + "' does not match expected value.");
            }

            CommonSteps.logInfo("✅ Validation Passed: '" + fieldName + "' matches expected.");
        } else {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }
    }

    private static String cleanName(String rawText, String fieldType) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        String cleanedText = rawText.trim();

        switch (fieldType.toLowerCase()) {
            case "date letters granted":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Date Letters Granted: )\\b", "").trim();
                break;

            case "decedent name":
                cleanedText = cleanedText.replaceAll("(?i)\\b(Name of Decedent: )\\b", "").trim();
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

    public void verifyRelatedCorporateFiduciaryDetails(List<String> pdfLines) {




        // Extract and Map Data
        Map<String, String> extractedData = extractWitnessDetails(pdfLines);

        // Print Extracted Data
        extractedData.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    private static Map<String, String> extractWitnessDetails(List<String> pdfLines) {
        Map<String, String> witnessData = new LinkedHashMap<>();
        String key = null;

        for (String line : pdfLines) {
            if (isLabel(line)) {
                key = line; // Store label for the next line
            } else if (key != null) {
                witnessData.put(key, line); // Map label -> value
                key = null; // Reset key after assignment
            }
        }

        return witnessData;
    }

    private static boolean isLabel(String line) {
        return line.equalsIgnoreCase("Name of Corporate Fiduciary") ||
                line.equalsIgnoreCase("Name of Representative and Title") ||
                line.equalsIgnoreCase("Address") ||
                line.equalsIgnoreCase("City, State, Zip") ||
                line.equalsIgnoreCase("Telephone") ||
                line.equalsIgnoreCase("Email") ||
                line.equalsIgnoreCase("Name of Person") ||
                line.equalsIgnoreCase("Signature of Officer/Representative");
}

public void userResetsTheRWForm() throws AutomationException {
    Actions actions = new Actions(DriverFactory.drivers.get());
    actions.moveToElement(driverUtil.getWebElement(PRINTFORM_BUTTON), -50, -50).perform();
    WebDriverUtil.waitForInvisibleElement(By.xpath(PRINT_FORM_TOOLTIP));

    scrollToElementAndClick(WILL_NUMBER_FIELD);
    DriverFactory.drivers.get().findElement(By.xpath(WILL_NUMBER_FIELD)).clear();

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


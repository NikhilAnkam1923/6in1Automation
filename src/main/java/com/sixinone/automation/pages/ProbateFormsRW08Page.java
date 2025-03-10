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

    static String downloadedFileName;

    static String enteredFirstName;
    static String enteredMiddleName;
    static String enteredLastName;
    static String enteredDisplayName;
    static String selectedSuffix;
    static String enteredSSN;
    static String enteredAlsoKnownAs;
    static String enteredDomicileAddressLine1;
    static String enteredDomicileAddressLine2;
    static String enteredDomicileZip;
    static String enteredDomicileCity;
    static String selectedDomicileState;
    static String enteredDomicileCountry;
    static String enteredDomicileMunicipality;
    static String enteredLastResidence;
    static String enteredDateOfBirth;
    static String enteredDateOfDeath;
    static String enteredAgeAtDeath;
    static String selectedMaritalStatus;
    static String enteredPlaceOfDeathAddressLine1;
    static String enteredPlaceOfDeathAddressLine2;
    static String enteredPlaceOfDeathZip;
    static String enteredPlaceOfDeathCity;
    static String selectedPlaceOfDeathState;
    static String enteredPlaceOfDeathCountry;
    static String enteredDateOfWill;
    static String enteredCodicilDate1;
    static String enteredCodicilDate2;
    static String enteredCodicilDate3;
    static String enteredProbateCourtName;
    static String enteredProbateCourtLocation;
    static String enteredFileNumberPart1;
    static String enteredFileNumberPart2;
    static String enteredFileNumberPart3;
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

    public void userSavesEstateInfo() throws AutomationException, IOException, ParseException {
        enteredFirstName = driverUtil.getWebElement(DECEDENT_FIRST_NAME_FIELD).getAttribute("value");
        enteredMiddleName = driverUtil.getWebElement(DECEDENT_MIDDLE_NAME).getAttribute("value");
        enteredLastName = driverUtil.getWebElement(DECEDENT_LAST_NAME_FIELD).getAttribute("value");
        enteredDisplayName = driverUtil.getWebElement(DECEDENT_DISPLAY_NAME).getAttribute("value");
        selectedSuffix = driverUtil.getWebElement(SELECTED_SUFFIX).getText();
        enteredSSN = driverUtil.getWebElement(DECEDENT_SSN_FIELD).getAttribute("value");
        enteredAlsoKnownAs = driverUtil.getWebElement(DECEDENT_ALSO_KNOWN_AS).getAttribute("value");
        enteredDomicileAddressLine1 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE1).getAttribute("value");
        enteredDomicileAddressLine2 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE2).getAttribute("value");
        enteredDomicileZip = driverUtil.getWebElement(DOMICILE_ZIP).getAttribute("value");
        enteredDomicileCity = driverUtil.getWebElement(DOMICILE_CITY).getAttribute("value");
        selectedDomicileState = driverUtil.getWebElement(DOMICILE_STATE).getText();
        enteredDomicileCountry = driverUtil.getWebElement(DOMICILE_COUNTRY).getAttribute("value");
        enteredDomicileMunicipality = driverUtil.getWebElement(DOMICILE_MUNICIPALITY).getAttribute("value");
        enteredLastResidence = driverUtil.getWebElement(LAST_RESIDENCE_FIELD).getAttribute("value");
        enteredDateOfBirth = driverUtil.getWebElement(DATE_OF_BIRTH_FIELD).getAttribute("value");
        enteredDateOfDeath = driverUtil.getWebElement(DATE_OF_DEATH_FIELD).getAttribute("value");
        enteredAgeAtDeath = driverUtil.getWebElement(AGE_AT_DEATH_FIELD).getAttribute("value");
        selectedMaritalStatus = driverUtil.getWebElement(SELECTED_MARITAL_STATUS).getText();
        enteredPlaceOfDeathAddressLine1 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE1).getAttribute("value");
        enteredPlaceOfDeathAddressLine2 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE2).getAttribute("value");
        enteredPlaceOfDeathZip = driverUtil.getWebElement(PLACE_OF_DEATH_ZIP).getAttribute("value");
        enteredPlaceOfDeathCity = driverUtil.getWebElement(PLACE_OF_DEATH_CITY).getAttribute("value");
        selectedPlaceOfDeathState = driverUtil.getWebElement(PLACE_OF_DEATH_STATE).getText();
        enteredPlaceOfDeathCountry = driverUtil.getWebElement(PLACE_OF_DEATH_COUNTRY).getAttribute("value");

        driverUtil.getWebElement(ESTATE_TAB).click();
        WebDriverUtil.waitForAWhile();

        enteredDateOfWill = driverUtil.getWebElement(DATE_OF_WILL).getAttribute("value");
        enteredCodicilDate1 = driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value");
        enteredCodicilDate2 = driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value");
        enteredCodicilDate3 = driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value");
        enteredProbateCourtName = driverUtil.getWebElement(PROBATE_COURT_NAME).getAttribute("value");
        enteredProbateCourtLocation = driverUtil.getWebElement(PROBATE_COURT_LOCATION).getAttribute("value");
        enteredFileNumberPart1 = driverUtil.getWebElement(FILE_NUMBER_PART_1).getAttribute("value");
        enteredFileNumberPart2 = driverUtil.getWebElement(FILE_NUMBER_PART_2).getAttribute("value");
        enteredFileNumberPart3 = driverUtil.getWebElement(FILE_NUMBER_PART_3).getAttribute("value");
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
        verifyAutoPopulatedValue(enteredDomicileCountry);
        verifyAutoPopulatedValue(enteredDisplayName);
        verifyAutoPopulatedValue(enteredAlsoKnownAs);
        verifyAutoPopulatedValue(enteredFileNumberPart1 + "-" + enteredFileNumberPart2 + "-" + enteredFileNumberPart3);

        initialFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        WebDriverUtil.waitForAWhile(4);
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, enteredDomicileCountry));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, enteredDisplayName));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH, enteredAlsoKnownAs));
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
        String fiduciaryLastName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.lastName").toString();
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
        String attorneyLastName = CommonUtil.getJsonPath("attorney2").get("attorney2.lastName").toString();
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

        selectedNameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");
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

        verifyNameOfDecedent(pdfFilePath);
        verifyDateOfDeath(pdfFilePath);
        verifyDateLettersGranted(pdfFilePath);
        verifyFileNumber(pdfFilePath);
    }


    public void verifyNameOfDecedent(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "Name of Decedent:";
        String afterLine = "Date of Death:";

        String extractedName = extractValueBetweenLines(pdfFilePath, beforeLine, afterLine);

        String expectedName = "Kris Warner"; // Update as needed
        validateExtractedValue(expectedName, extractedName, "Decedent Name");
    }

    public void verifyDateOfDeath(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "Date of Death:";
        String afterLine = "Date Letters Granted:";

        String extractedDateOfDeath = extractValueBetweenLines(pdfFilePath, beforeLine, afterLine);

        String expectedDateOfDeath = "12/05/2023"; // Update as needed
        validateExtractedValue(expectedDateOfDeath, extractedDateOfDeath, "Date of Death");
    }

    public void verifyDateLettersGranted(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "Date Letters Granted:";
        String afterLine = "File Number:";

        String extractedDateLetters = extractValueBetweenLines(pdfFilePath, beforeLine, afterLine);

        String expectedDateLetters = "02/15/2025"; // Update as needed
        validateExtractedValue(expectedDateLetters, extractedDateLetters, "Date Letters Granted");
    }


    public void verifyFileNumber(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "File Number:";
        String afterLine = "Some other reference"; // Adjust if there's a specific end marker

        String extractedFileNumber = extractValueBetweenLines(pdfFilePath, beforeLine, afterLine);

        String expectedFileNumber = "22-2023-1234"; // Update as needed
        validateExtractedValue(expectedFileNumber, extractedFileNumber, "File Number");
    }

    private String extractValueBetweenLines(String pdfFilePath, String beforeLine, String afterLine) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("📄 **Full PDF Content (All Lines):**");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("Line " + (i + 1) + ": " + allLines[i]); // Log every line
        }

        int startIndex = -1, endIndex = -1;
        String extractedValue = "";

        // Find start and end lines
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            if (trimmedLine.equalsIgnoreCase(beforeLine)) startIndex = i;
            if (trimmedLine.contains(afterLine) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                String currentLine = allLines[i].trim();
                if (!currentLine.isBlank()) {
                    extractedValue = currentLine; // Store the first valid line
                    break;
                }
            }

            if (extractedValue.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No value found between '" + beforeLine + "' and '" + afterLine + "'.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found in PDF!");
        }

        return extractedValue;
    }


    private void validateExtractedValue(String expectedValue, String extractedValue, String fieldName) throws AutomationException {
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedValue + "', Extracted: '" + extractedValue + "'");

        if (expectedValue.equalsIgnoreCase(extractedValue)) {
            CommonSteps.logInfo("✅ Validation Passed: " + fieldName + " matches expected.");
        } else {
            throw new AutomationException("❌ Validation Failed: " + fieldName + " does not match expected value.");
        }
    }


    public void userResetsTheRWForm() throws AutomationException {
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

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsRW10Page extends BasePage {
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
    private static final String FORM_TITLE = "//div[@id='id_1']//p[@class='p0 ft0']";
    private static final String ALL_QUESTIONS_XPATH = "//span[@class='ft1']/following-sibling::span[not(contains(@class,'yellowC'))]";
    private static final String ALL_YES_OPTION_XPATH = "//span[contains(text(),'Yes')]";
    private static final String ALL_NO_OPTION_XPATH = "//td//span[contains(text(),'No')]";
    private static final String Q1_YES_OPTION = "//input[@name='isAdministrationCompleted' and @value='1']";
    private static final String Q2_YES_OPTION = "//input[@name='isAccountFileWithCourt' and @value='1']";
    private static final String Q3_YES_OPTION = "//input[@name='isAccountInformallyState' and @value='1']";
    private static final String Q1_NO_OPTION = "//input[@name='isAdministrationCompleted' and @value='0']";
    private static final String Q2_NO_OPTION = "//input[@name='isAccountFileWithCourt' and @value='0']";
    private static final String Q3_NO_OPTION = "//input[@name='isAccountInformallyState' and @value='0']";
    private static final String Q2_TEXTAREA = "//textarea[@name='administrationWillBeCompleted']";
    private static final String Q3_TEXT_FIELD = "//input[@name='orphansCourtNo']";
    private static final String DATE_FIELD = "//p[contains(text(),'Date')]//input[@name='date']";
    private static final String CORPORATE_FIDUCIARY_NAME_FIELD = "//input[@name='fullname']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String PERSON_NAME_FIELD = "//p[text()='Name of Person']/preceding-sibling::p//input";
    private static final String SAVE_BTN = "//button[text()='Save']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String downloadedFileName;
    static String enteredReasonForm;
    static String enteredDateForm;
    static String selectedNameOfCorporateFiduciary;
    static String selectedNameOfPerson;
    static String enteredOrphanCourtNumberForm;
    static String domicileCountryForm;
    static String displayNameForm;
    static String dateOfDeathForm;
    static String fileNumberForm;

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

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyTitleOfTheFormIsCorrectlyDisplayedAndCountyIsFetchedFromDecedentInfo() throws AutomationException, IOException, ParseException {
        String expectedTitle = CommonUtil.getJsonPath("RW10Form").get("RW10Form.title").toString();
        domicileCountryForm = getEstateValue("DomicileCountry");

        verifyAutoPopulatedValue(domicileCountryForm);

        String actualTitle = driverUtil.getWebElement(FORM_TITLE).getText();

        if (!actualTitle.equals(expectedTitle)) {
            throw new AutomationException("Form title is not displayed correctly. Expected: " + expectedTitle + " ,found: " + actualTitle);
        }
    }

    public void verifyNameOfDecedentDateOfDeathFileNumberIsFetchedFromTheDecedentInfo() throws AutomationException {
        displayNameForm = getEstateValue("DisplayName");
        dateOfDeathForm = getEstateValue("DateOfDeath");
        fileNumberForm = getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3");

        verifyAutoPopulatedValue(displayNameForm);
        verifyAutoPopulatedValue(dateOfDeathForm);
        verifyAutoPopulatedValue(fileNumberForm);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.getAttribute("disabled") == null && field.getAttribute("readonly") == null) {
            throw new AutomationException("Field is editable: " + fieldLocator);
        }
    }

    public void verifyNameDateOfDeathAndFileNumberFieldsAreNotEditable() throws AutomationException {
        String displayNameField = String.format(RW_INPUT_FIELD_XPATH, displayNameForm);
        String dateOfDeathField = String.format(RW_INPUT_FIELD_XPATH, dateOfDeathForm);
        String fileNumberField = String.format(RW_INPUT_FIELD_XPATH, fileNumberForm);

        WebDriverUtil.waitForAWhile(2);
        verifyFieldIsNotEditable(displayNameField);
        verifyFieldIsNotEditable(dateOfDeathField);
        verifyFieldIsNotEditable(fileNumberField);
    }

    public void verifyAllTheQuestionnaireIsProperlyDisplayedWithOptionsYesOrNo() throws AutomationException {
        List<WebElement> allQuestions = driverUtil.getWebElements(ALL_QUESTIONS_XPATH);
        List<WebElement> allYesOptions = driverUtil.getWebElements(ALL_YES_OPTION_XPATH);
        List<WebElement> allNoOptions = driverUtil.getWebElements(ALL_NO_OPTION_XPATH);

        if (allQuestions.size() != allYesOptions.size() || allQuestions.size() != allNoOptions.size()) {
            throw new AutomationException("Mismatch in count! Questions: " + allQuestions.size() +
                    ", Yes Options: " + allYesOptions.size() +
                    ", No Options: " + allNoOptions.size());
        }
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    private void verifyYesOrNoOptionSelection(String questionLabel, String yesXpath, String noXpath) throws AutomationException {
        WebElement yesOption = DriverFactory.drivers.get().findElement(By.xpath(yesXpath));
        WebElement noOption = DriverFactory.drivers.get().findElement(By.xpath(noXpath));

        if (yesOption == null || noOption == null) {
            throw new AutomationException("One or both radio buttons not found for " + questionLabel);
        }

        scrollToElementAndClick(yesXpath);
        WebDriverUtil.waitForAWhile();
        if (noOption.isSelected()) {
            throw new AutomationException(questionLabel + " - Clicking Yes did not uncheck No.");
        }

        scrollToElementAndClick(noXpath);
        WebDriverUtil.waitForAWhile();
        if (yesOption.isSelected()) {
            throw new AutomationException(questionLabel + " - Clicking No did not uncheck Yes.");
        }
    }

    public void verifyForAllTheQuestionnaireEitherYesOrNoOptionCanBeSelected() throws AutomationException {
        verifyYesOrNoOptionSelection("Question 1", Q1_YES_OPTION, Q1_NO_OPTION);
        scrollToElementAndClick(Q1_YES_OPTION);
        verifyYesOrNoOptionSelection("Question 2", Q2_YES_OPTION, Q2_NO_OPTION);
        verifyYesOrNoOptionSelection("Question 3", Q3_YES_OPTION, Q3_NO_OPTION);
    }

    public void verifyWhenYesOptionIsSelectedTextBoxInPoint2IsNotEnabledAndNotEditable() throws AutomationException {
        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if (point2Textarea.getAttribute("disabled") == null && point2Textarea.isEnabled()) {
            throw new AutomationException("Textarea Field is enabled and editable.");
        }

    }

    public void verifyWhenNoOptionIsSelectedTextBoxInPoint2IsEnabledAndEditable() throws AutomationException {
        scrollToElementAndClick(Q1_NO_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if (point2Textarea.getAttribute("disabled") != null && !point2Textarea.isEnabled()) {
            throw new AutomationException("Textarea Field is not enabled and not editable.");
        }
    }

    public void verifyReasonTextBoxFieldIsAbleToAcceptTheText() throws IOException, ParseException, AutomationException {
        String reason = CommonUtil.getJsonPath("RW10Form").get("RW10Form.reasonForIncompleteAdministration").toString();

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        point2Textarea.sendKeys(reason);

        WebDriverUtil.waitForAWhile();
        enteredReasonForm = point2Textarea.getText();

        if (!reason.equals(enteredReasonForm)) {
            throw new AutomationException("Reason text field did not accept the text correctly. Expected: " + reason + " ,found: " + enteredReasonForm);
        }
    }

    public void verifyYesIsTickedInPoint1ThenTextFieldInPoint3IsEnabledAndEditable() throws AutomationException, IOException, ParseException {
        String orphansCourtNumber = CommonUtil.getJsonPath("RW10Form").get("RW10Form.orphansCourtNumber").toString();

        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if (point3TextField.getAttribute("disabled") != null && !point3TextField.isEnabled()) {
            throw new AutomationException("Text field is not enabled and not editable.");
        }

        scrollToElementAndClick(Q3_TEXT_FIELD);
        point3TextField.sendKeys(orphansCourtNumber);
        driverUtil.getWebElement("//body").click();

        WebDriverUtil.waitForAWhile();
        enteredOrphanCourtNumberForm = point3TextField.getAttribute("value");

    }

    public void verifyOtherInputFieldsAreEditableExceptPointNo2TextField() throws AutomationException {
        scrollToElementAndClick(Q1_NO_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if (point3TextField.getAttribute("disabled") == null && point3TextField.isEnabled()) {
            throw new AutomationException("Text Field is enabled and editable.");
        }
    }

    public void verifyWhenYesIsTickedInPointThenTextEnteredInPointGetsDisappear() throws AutomationException {
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile(2);

        scrollToElementAndClick(Q1_YES_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if (!point2Textarea.getText().isEmpty() && point2Textarea.getText().contains(enteredReasonForm)) {
            throw new AutomationException("Reason entered in text area does not gets disappear. Found text: " + point2Textarea.getText());
        }
    }

    public void verifyWhenNoIsTickedThenTextBoxInPoint2IsEditableEnabledAndEmpty() throws AutomationException {
        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if (!point2Textarea.getText().isEmpty()) {
            throw new AutomationException("Text area is not empty. Found text: " + point2Textarea.getText());
        }
    }

    public void userEntersDateInDateField() throws AutomationException {
        WebElement dateField = driverUtil.getWebElement(DATE_FIELD);

        scrollToElementAndClick(DATE_FIELD);

        String value = "12/22/2024";
        for (char c : value.toCharArray()) {
            dateField.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();

        WebDriverUtil.waitForAWhile();
        enteredDateForm = driverUtil.getWebElement(DATE_FIELD).getAttribute("value");
    }

    public void verifyDateFollowsTheCorrectFormat() throws AutomationException {
        String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        Pattern pattern = Pattern.compile(datePattern);

        if (!pattern.matcher(enteredDateForm).matches()) {
            throw new AutomationException("Invalid date format: " + enteredDateForm + ". Expected format is MM/DD/YYYY.");
        }
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

        selectedNameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value").replace(",", "");
    }

    public void verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor7And10() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryName = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");

        if (!corporateFiduciaryName.equals(selectedNameOfCorporateFiduciary)) {
            throw new AutomationException("Changes made in Corporate Fiduciary section on RW10 are not reflected on RW07. Expected Name: " + selectedNameOfCorporateFiduciary + " ,Found: " + corporateFiduciaryName);
        }

        if (!nameOfPerson.equals(selectedNameOfPerson)) {
            throw new AutomationException("Changes made in Person section on RW10 are not reflected on RW07. Expected Name: " + selectedNameOfPerson + " ,Found: " + nameOfPerson);
        }
    }

    public void verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor8And10() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryName = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");

        if (!corporateFiduciaryName.equals(selectedNameOfCorporateFiduciary)) {
            throw new AutomationException("Changes made in Corporate Fiduciary section on RW10 are not reflected on RW08. Expected Name: " + selectedNameOfCorporateFiduciary + " ,Found: " + corporateFiduciaryName);
        }

        if (!nameOfPerson.equals(selectedNameOfPerson)) {
            throw new AutomationException("Changes made in Person section on RW10 are not reflected on RW08. Expected Name: " + selectedNameOfPerson + " ,Found: " + nameOfPerson);
        }
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

    public void verifyWhenNoIsTickedInPointThenPointIsDisabledAndTextEnteredInItGetsDisappear() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if (!point3TextField.getAttribute("value").isEmpty() && point3TextField.getAttribute("value").contains(enteredOrphanCourtNumberForm)) {
            throw new AutomationException("Reason entered in text area does not gets disappear. Found text: " + point3TextField.getAttribute("value"));
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

    public void verifyAllFieldsInDownloadedPDF() throws AutomationException {
        String pdfFilePath = ((System.getProperty("os.name").toLowerCase().contains("win"))
                ? System.getProperty("user.dir") + "\\downloads\\"
                : System.getProperty("user.dir") + "/downloads/") + downloadedFileName;
        try {
            verifyCorporateFiduciaryAndPersonDetails(pdfFilePath);
        } catch (IOException e) {
            CommonSteps.logInfo("Error reading PDF: " + e.getMessage());
        }
    }

    private static void verifyCorporateFiduciaryAndPersonDetails(String pdfFilePath) throws IOException, AutomationException {
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
            validateField("Corporate Fiduciary", corporateFiduciary, "zetaConsulting");
            validateField("Corporate Address", corpAddress, "Mountain View Drive");
            validateField("Corporate City, State, Zip", corpCityStateZip, "Seattle, WA 98101");
            validateField("Corporate Telephone", corpTelephone, "(206) 555-6789");
            validateField("Corporate Email", corpEmail, "liam.anderson@zetaconsulting.com");
        }

        // Validate Name of Person Details (2nd occurrence)
        if (personName != null) {
            validateField("Name of Person", personName, "Rihan Benjamin Miles Jr");
            validateField("Person Address", personAddress, "Riverside Drive");
            validateField("Person City, State, Zip", personCityStateZip, "Kansas City, MO 64101");
            validateField("Person Telephone", personTelephone, "412-716-7069");
            ;
            validateField("Person Email", personEmail, "rihan.miles@business.com");
        }

        if (corporateFiduciary == null && personName == null) {
            throw new AutomationException("❌ Validation Failed: Neither Corporate Fiduciary nor Name of Person was found.");
        }

        CommonSteps.logInfo("✅ Validation Passed: Corporate Fiduciary and Name of Person details successfully matched.");
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


    public static void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }


    public void userResetsTheRWForm() throws AutomationException {
        clearField(DATE_FIELD);
        driverUtil.getWebElement("//body").click();
    }
}

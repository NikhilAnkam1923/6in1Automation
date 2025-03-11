package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ProbateFormsRW10Page extends BasePage{
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
    static String enteredReasonForm;
    static String enteredDateForm;
    static String selectedNameOfCorporateFiduciary;
    static String selectedNameOfPerson;
    static String enteredOrphanCourtNumberForm;

    @Override
    String getName() {
        return "";
    }

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

        verifyAutoPopulatedValue(enteredDomicileCountry);

        String actualTitle = driverUtil.getWebElement(FORM_TITLE).getText();

        if(!actualTitle.equals(expectedTitle)){
            throw new AutomationException("Form title is not displayed correctly. Expected: "+expectedTitle+" ,found: "+actualTitle);
        }
    }

    public void verifyNameOfDecedentDateOfDeathFileNumberIsFetchedFromTheDecedentInfo() throws AutomationException {
        verifyAutoPopulatedValue(enteredDisplayName);
        verifyAutoPopulatedValue(enteredDateOfDeath);
        verifyAutoPopulatedValue(enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.getAttribute("disabled")==null && field.getAttribute("readonly")==null) {
            throw new AutomationException("Field is editable: "+fieldLocator);
        }
    }

    public void verifyNameDateOfDeathAndFileNumberFieldsAreNotEditable() throws AutomationException {
        WebDriverUtil.waitForAWhile(4);
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH,enteredDisplayName));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH,enteredDateOfDeath));
        String fileNumber = enteredFileNumberPart1+"-"+enteredFileNumberPart2+"-"+enteredFileNumberPart3;
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH,fileNumber));
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

        if (point2Textarea.getAttribute("disabled")==null && point2Textarea.isEnabled()) {
            throw new AutomationException("Textarea Field is enabled and editable.");
        }

    }

    public void verifyWhenNoOptionIsSelectedTextBoxInPoint2IsEnabledAndEditable() throws AutomationException {
        scrollToElementAndClick(Q1_NO_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if (point2Textarea.getAttribute("disabled")!=null && !point2Textarea.isEnabled()) {
            throw new AutomationException("Textarea Field is not enabled and not editable.");
        }
    }

    public void verifyReasonTextBoxFieldIsAbleToAcceptTheText() throws IOException, ParseException, AutomationException {
        String reason = CommonUtil.getJsonPath("RW10Form").get("RW10Form.reasonForIncompleteAdministration").toString();

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        point2Textarea.sendKeys(reason);

        WebDriverUtil.waitForAWhile();
        enteredReasonForm = point2Textarea.getText();

        if(!reason.equals(enteredReasonForm)){
            throw new AutomationException("Reason text field did not accept the text correctly. Expected: "+reason+" ,found: "+enteredReasonForm);
        }
    }

    public void verifyYesIsTickedInPoint1ThenTextFieldInPoint3IsEnabledAndEditable() throws AutomationException, IOException, ParseException {
        String orphansCourtNumber = CommonUtil.getJsonPath("RW10Form").get("RW10Form.orphansCourtNumber").toString();

        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if (point3TextField.getAttribute("disabled")!=null && !point3TextField.isEnabled()) {
            throw new AutomationException("Text field is not enabled and not editable.");
        }

        scrollToElementAndClick(Q3_TEXT_FIELD);
        point3TextField.sendKeys(orphansCourtNumber);

        WebDriverUtil.waitForAWhile();
        enteredOrphanCourtNumberForm = point3TextField.getAttribute("value");

    }

    public void verifyOtherInputFieldsAreEditableExceptPointNo2TextField() throws AutomationException {
        scrollToElementAndClick(Q1_NO_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if (point3TextField.getAttribute("disabled")==null && point3TextField.isEnabled()) {
            throw new AutomationException("Text Field is enabled and editable.");
        }
    }

    public void verifyWhenYesIsTickedInPointThenTextEnteredInPointGetsDisappear() throws AutomationException {
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile(2);

        scrollToElementAndClick(Q1_YES_OPTION);
        WebDriverUtil.waitForAWhile(2);

        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if(!point2Textarea.getText().isEmpty() && point2Textarea.getText().contains(enteredReasonForm)){
            throw new AutomationException("Reason entered in text area does not gets disappear. Found text: "+point2Textarea.getText());
        }
    }

    public void verifyWhenNoIsTickedThenTextBoxInPoint2IsEditableEnabledAndEmpty() throws AutomationException {
        WebElement point2Textarea = driverUtil.getWebElement(Q2_TEXTAREA);

        if(!point2Textarea.getText().isEmpty()){
            throw new AutomationException("Text area is not empty. Found text: "+point2Textarea.getText());
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

        WebElement corporateFiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH,corporateFiduciaryFirm));

        corporateFiduciaryToSelect.click();

        if(!corporateFiduciaryToSelect.isSelected()){
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

        if(!modalHeader.getText().contains("Fiduciary")){
            throw new AutomationException("Fiduciary type of contacts are not displayed.");
        }

        String fiduciaryContactToSelect = fiduciaryFirstName+" "+fiduciaryMiddleName+" "+fiduciaryLastName+" "+fiduciarySuffix;

        WebElement fiduciaryToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH,fiduciaryContactToSelect));

        WebDriverUtil.waitForAWhile();
        fiduciaryToSelect.click();

        if(!fiduciaryToSelect.isSelected()){
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

        if(!modalHeader.getText().contains("Attorney")){
            throw new AutomationException("Attorney type of contacts are not displayed.");
        }

        String attorneyContactToSelect = attorneyFirstName+" "+attorneyMiddleName+" "+attorneyLastName+" "+attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH,attorneyContactToSelect));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if(!attorneyToSelect.isSelected()){
            throw new AutomationException("Unable to select the attorney contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel (Attorney) updated successfully.")));

        selectedNameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");
    }

    public void verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor7And10() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryName = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");

        if (!corporateFiduciaryName.equals(selectedNameOfCorporateFiduciary)){
            throw new AutomationException("Changes made in Corporate Fiduciary section on RW10 are not reflected on RW07. Expected Name: "+selectedNameOfCorporateFiduciary+" ,Found: "+corporateFiduciaryName);
        }

        if (!nameOfPerson.equals(selectedNameOfPerson)){
            throw new AutomationException("Changes made in Person section on RW10 are not reflected on RW07. Expected Name: "+selectedNameOfPerson+" ,Found: "+nameOfPerson);
        }
    }

    public void verifyCorporateFiduciaryAndPersonSectionsInformationIsCommonFor8And10() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        String corporateFiduciaryName = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value");
        String nameOfPerson = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value");

        if (!corporateFiduciaryName.equals(selectedNameOfCorporateFiduciary)){
            throw new AutomationException("Changes made in Corporate Fiduciary section on RW10 are not reflected on RW08. Expected Name: "+selectedNameOfCorporateFiduciary+" ,Found: "+corporateFiduciaryName);
        }

        if (!nameOfPerson.equals(selectedNameOfPerson)){
            throw new AutomationException("Changes made in Person section on RW10 are not reflected on RW08. Expected Name: "+selectedNameOfPerson+" ,Found: "+nameOfPerson);
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
        WebElement point3TextField = driverUtil.getWebElement(Q3_TEXT_FIELD);

        if(!point3TextField.getAttribute("value").isEmpty() && point3TextField.getAttribute("value").contains(enteredOrphanCourtNumberForm)){
            throw new AutomationException("Reason entered in text area does not gets disappear. Found text: "+point3TextField.getAttribute("value"));
        }
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

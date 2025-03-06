package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProbateFormsRWxxPage extends BasePage {

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    private static final String DECEDENT_MIDDLE_NAME = "//input[@name='decedentInfo.middleName']";
    private static final String DECEDENT_DISPLAY_NAME = "//input[@name='decedentInfo.displayNameAs']";
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
    private static final String PLACE_OF_DEATH_ADDRESS_LINE1 = "//input[@name='placeOfDeath.addressLine1']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE2 = "//input[@name='placeOfDeath.addressLine2']";
    private static final String PLACE_OF_DEATH_ZIP = "//input[@name='placeOfDeath.zip']";
    private static final String PLACE_OF_DEATH_CITY = "//input[@name='placeOfDeath.city']";
    private static final String PLACE_OF_DEATH_STATE = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/label[text()='State']/following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_COUNTRY = "//input[@name='placeOfDeath.county']";
    private static final String ESTATE_TAB = "//button[@role='tab' and text()='Estate']";
    private static final String DATE_OF_WILL = "//label[text()='Date of Will']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_1 = "//label[text()='Codicil Date #1']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_2 = "//label[text()='Codicil Date #2']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_3 = "//label[text()='Codicil Date #3']/following-sibling::div//div//input";
    private static final String PROBATE_COURT_NAME = "//input[@name='probateCourtName']";
    private static final String PROBATE_COURT_LOCATION = "//input[@name='probateCourtLocation']";
    private static final String FILE_NUMBER_PART_1 = "//input[@name='fileNumberPart1']";
    private static final String FILE_NUMBER_PART_2 = "//input[@name='fileNumberPart2']";
    private static final String FILE_NUMBER_PART_3 = "//input[@name='fileNumberPart3']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String DECEDENT_FIRST_NAME_FIELD = "//input[@name='decedentInfo.firstName']";
    private static final String DECEDENT_LAST_NAME_FIELD = "//input[@name='decedentInfo.lastName']";
    private static final String SELECTED_SUFFIX = "//label[text()='Suffix']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String DECEDENT_SSN_FIELD = "//input[@name='decedentInfo.SSN']";
    private static final String SELECTED_MARITAL_STATUS = "//div[text()='Life Details']/following-sibling::div//input[@name='lifeDetails.ageAtDeath'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'Marital Status')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String REVIEWER_NAME = "//td//input[@name='reviewerName']";
    private static final String REVIEWER_DESIGNATION = "//td//input[@name='reviewerDesignation']";
    private static final String WITNESS_NAME_1 = "//td//input[@name='witness1Name']";
    private static final String WITNESS_NAME_2 = "//td//input[@name='witness2Name']";
    private static final String REASON = "//textarea[@name='reason']";
    private static final String REVIEWER_SIGN = "//p[contains(text(),'(Signature)')]//input[@name='reviewerName']";

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
    static String enteredReviewerName;
    static String enteredReviewerDesignation;
    static String enteredWitness1;
    static String enteredWitness2;
    static String enteredReason;
    static String enteredReviewerSign;

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
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

    private static String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    @Override
    String getName() {
        return "";
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH,formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        verifyAutoPopulatedValue(enteredDomicileCountry);
        verifyAutoPopulatedValue(enteredDisplayName);
        verifyAutoPopulatedValue(enteredAlsoKnownAs);
    }

    private void fillFieldWithKeyStrokes(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        String value = CommonUtil.getJsonPath("Create").get(jsonKey).toString();
        for (char c : value.toCharArray()) {
            field.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }


    public void userResetsTheRWForm() throws AutomationException {
        clearField(REVIEWER_NAME);
        clearField(REVIEWER_DESIGNATION);
        clearField(WITNESS_NAME_1);
        clearField(WITNESS_NAME_2);
        DriverFactory.drivers.get().findElement(By.xpath(REASON)).clear();
        driverUtil.getWebElement(REASON).sendKeys(Keys.ENTER);
    }

    public void verifyTextCanBeEnteredInAllTheTextAreasAndIsAutoSaved() throws IOException, ParseException, AutomationException {
        String reviewerName = CommonUtil.getJsonPath("RWxxForm").get("RWxxForm.reviewerName").toString();
        String reviewerDesignation = CommonUtil.getJsonPath("RWxxForm").get("RWxxForm.reviewerDesignation").toString();
        String witness1name = CommonUtil.getJsonPath("RWxxForm").get("RWxxForm.witness1name").toString();
        String witness2name = CommonUtil.getJsonPath("RWxxForm").get("RWxxForm.witness2name").toString();
        String reason = CommonUtil.getJsonPath("RWxxForm").get("RWxxForm.reason").toString();

        fillFieldWithKeyStrokes(REVIEWER_NAME,"RWxxForm.reviewerName");
        fillFieldWithKeyStrokes(REVIEWER_DESIGNATION,"RWxxForm.reviewerDesignation");
        fillFieldWithKeyStrokes(WITNESS_NAME_1,"RWxxForm.witness1name");
        fillFieldWithKeyStrokes(WITNESS_NAME_2,"RWxxForm.witness2name");
        fillFieldWithKeyStrokes(REASON,"RWxxForm.reason");

        WebDriverUtil.waitForAWhile();

        enteredReviewerName = driverUtil.getWebElement(REVIEWER_NAME).getAttribute("value");
        enteredReviewerDesignation = driverUtil.getWebElement(REVIEWER_DESIGNATION).getAttribute("value");
        enteredWitness1 = driverUtil.getWebElement(WITNESS_NAME_1).getAttribute("value");
        enteredWitness2 = driverUtil.getWebElement(WITNESS_NAME_2).getAttribute("value");
        enteredReason = driverUtil.getWebElement(REASON).getText();

        if (!enteredReviewerName.equals(reviewerName)) {
            throw new AutomationException("Reviewer name field did not accept the entered name: " + reviewerName);
        }

        if (!enteredReviewerDesignation.equals(reviewerDesignation)) {
            throw new AutomationException("Reviewer Designation field did not accept the entered name: " + reviewerDesignation);
        }

        if (!enteredWitness1.equals(witness1name)) {
            throw new AutomationException("Witness name 1 field did not accept the entered name: " + witness1name);
        }

        if (!enteredWitness2.equals(witness2name)) {
            throw new AutomationException("Witness name 2 field did not accept the entered name: " + witness2name);
        }

        if (!enteredReason.equals(reason)) {
            throw new AutomationException("Reason field did not accept the entered name: " + reason);
        }

        clickOnRWForm("RW 05");
        clickOnRWForm("RW xx");

        WebDriverUtil.waitForAWhile(1);

        String actualReviewerName = getFieldValue(REVIEWER_NAME, "value");
        String actualReviewerDesignation = getFieldValue(REVIEWER_DESIGNATION, "value");
        String actualWitness1name = getFieldValue(WITNESS_NAME_1, "value");
        String actualWitness2name = getFieldValue(WITNESS_NAME_2, "value");
        String actualReason = getFieldValue(REASON, "text");

        verifyField("Reviewer Name", enteredReviewerName, actualReviewerName);
        verifyField("Reviewer Designation", enteredReviewerDesignation, actualReviewerDesignation);
        verifyField("Witness name 1", enteredWitness1, actualWitness1name);
        verifyField("Witness name 2", enteredWitness2, actualWitness2name);
        verifyField("Reason", enteredReason, actualReason);
    }

    public void verifyNameEnteredInFirstTextAreaIsReflectedInSignatureField() throws AutomationException {
        String reviewerSign = driverUtil.getWebElement(REVIEWER_SIGN).getAttribute("value");

        if (!reviewerSign.equals(enteredReviewerName)) {
            throw new AutomationException("The signature field does not reflect the entered reviewer name. " +
                    "Expected: " + enteredReviewerName + ", Found: " + reviewerSign);
        }
    }

    public void verifyNamesUpdatedInSignatureFieldIsReflectedInTheReviewerNameField() throws AutomationException, IOException, ParseException {
        clearField(REVIEWER_SIGN);
        fillFieldWithKeyStrokes(REVIEWER_SIGN,"RWxxForm.reviewerSign");

        enteredReviewerSign = driverUtil.getWebElement(REVIEWER_SIGN).getAttribute("value");

        String updatedReviewerName = driverUtil.getWebElement(REVIEWER_NAME).getAttribute("value");

        if (!enteredReviewerSign.equals(updatedReviewerName)) {
            throw new AutomationException("The reviewer name field does not reflect the entered reviewer signature. " +
                    "Expected: " + enteredReviewerSign + ", Found: " + updatedReviewerName);
        }
    }
}

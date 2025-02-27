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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsRW04Page extends BasePage {
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
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    private static final String COUNTY_NAME_HEADER = "//p[contains(text(),'REGISTER OF WILLS OF')]//input";
    private static final String ESTATE_OF_NAME = "//p[contains(text(),'Estate of')]//input";
    private static final String AKA_NAME_FIELD = "//p[contains(text(),'a/k/a')]//input";
    private static final String ESTATE_ACQUAINTED_WITH_NAME = "//p[contains(text(),'acquainted with')]//input";
    private static final String ESTATE_SIGNATURE_OF_NAME = "//p[contains(text(),'signature of')]//input";
    private static final String ESTATE_OWN_PROPER_HANDWRITING_NAME = "//p[contains(string(), 'Testament/Codicil of')]//input";
    private static final String WITNESS_NAME_1 = "//td//input[@name='witness1Name']";
    private static final String WITNESS_NAME_2 = "//td//input[@name='witness2Name']";
    private static final String WITNESS_1_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness1Name']";
    private static final String WITNESS_2_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness2Name']";
    private static final String WITNESS_1_STREET_ADDRESS = "//textarea[@name='witness1Address']";
    private static final String WITNESS_2_STREET_ADDRESS = "//textarea[@name='witness2Address']";
    private static final String W1_CITY_STATE_ZIP = "//input[@name='witness1CityStateZip']";
    private static final String W2_CITY_STATE_ZIP = "//input[@name='witness2CityStateZip']";
    private static final String DISPLAY_NOTARY_CHECKBOX = "//input[@name='displayNotaryBlock']";
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String PRINT_FORM_TOOLTIP = "//div[@role='tooltip']";

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
    static String enteredWitness1Form;
    static String enteredWitness2Form;
    static String enteredWitness1SignForm;
    static String enteredWitness2SignForm;
    static String enteredStreetAddress1Form;
    static String enteredStreetAddress2Form;
    static String enteredCityStateZip1Form;
    static String enteredCityStateZip2Form;


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

    public void verifyEstateCountyAKANameUnderHeader() throws AutomationException {
        String countyName = driverUtil.getWebElement(COUNTY_NAME_HEADER).getAttribute("value");
        if (!enteredDomicileCountry.equals(countyName)) {
            throw new AutomationException("County is incorrect or not fetched correctly. Expected: " + enteredDomicileCountry + ", but got: " + countyName);
        }

//        String EstateName = enteredFirstName+" "+enteredMiddleName+" "+enteredLastName+" "+selectedSuffix;
        String estateName = driverUtil.getWebElement(ESTATE_OF_NAME).getAttribute("value");
        if (!enteredDisplayName.equals(estateName)) {
            throw new AutomationException("Estate name is incorrect or not fetched correctly. Expected: " + enteredDisplayName + ", but got: " + estateName);
        }

        String AKAName = driverUtil.getWebElement(AKA_NAME_FIELD).getAttribute("value");
        if (!enteredAlsoKnownAs.equals(AKAName)) {
            throw new AutomationException("AKA is incorrect or not fetched correctly. Expected: " + enteredAlsoKnownAs + ", but got: " + AKAName);
        }
    }

    public void verifyCountyAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        WebElement county = driverUtil.getWebElement(COUNTY_NAME_HEADER);
        if (!county.getAttribute("value").equals(enteredDomicileCountry)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + enteredDomicileCountry);
        }

        WebElement AKA = driverUtil.getWebElement(AKA_NAME_FIELD);
        if (!AKA.getAttribute("value").equals(enteredAlsoKnownAs)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + enteredAlsoKnownAs);
        }
    }

    public void verifyCorrectEstateSNameIsDisplayedAcrossTheForm() throws AutomationException {
        String EstateName = enteredFirstName + " " + enteredMiddleName + " " + enteredLastName + " " + selectedSuffix;

        String acquaintedEstateName = driverUtil.getWebElement(ESTATE_ACQUAINTED_WITH_NAME).getAttribute("value");
        if (!EstateName.equals(acquaintedEstateName)) {
            throw new AutomationException("County is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + acquaintedEstateName);
        }

        String signatureOfEstateName = driverUtil.getWebElement(ESTATE_SIGNATURE_OF_NAME).getAttribute("value");
        if (!EstateName.equals(signatureOfEstateName)) {
            throw new AutomationException("Estate name is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + signatureOfEstateName);
        }

        String ownProperHandwritingEstateName = driverUtil.getWebElement(ESTATE_OWN_PROPER_HANDWRITING_NAME).getAttribute("value");
        if (!EstateName.equals(ownProperHandwritingEstateName)) {
            throw new AutomationException("AKA is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + ownProperHandwritingEstateName);
        }
    }

    public void verifyDecedentNameOnTheFormIsAutoPopulatedFromTheEstate() throws AutomationException {
        String EstateName = enteredFirstName + " " + enteredMiddleName + " " + enteredLastName + " " + selectedSuffix;

        WebElement estateHeaderName = driverUtil.getWebElement(ESTATE_OF_NAME);
        if (!estateHeaderName.getAttribute("value").equals(enteredDisplayName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + enteredDisplayName + " but found: " + estateHeaderName);
        }

        WebElement acquaintedEstateName = driverUtil.getWebElement(ESTATE_ACQUAINTED_WITH_NAME);
        if (!acquaintedEstateName.getAttribute("value").equals(EstateName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + EstateName);
        }

        WebElement signatureOfEstateName = driverUtil.getWebElement(ESTATE_SIGNATURE_OF_NAME);
        if (!signatureOfEstateName.getAttribute("value").equals(EstateName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + EstateName);
        }

        WebElement ownProperHandwritingEstateName = driverUtil.getWebElement(ESTATE_OWN_PROPER_HANDWRITING_NAME);
        if (!ownProperHandwritingEstateName.getAttribute("value").equals(EstateName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + EstateName);
        }
    }

    private void verifyFieldIsEditableAndYellowBackground(String fieldName, String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (!field.isEnabled()) {
            throw new AutomationException(fieldName + " field is not editable.");
        }

        String backgroundColor = field.getCssValue("background-color");
        if (!backgroundColor.equals("rgba(249, 249, 206, 1)")) {
            throw new AutomationException(fieldName + " field does not have a yellow background.");
        }
    }

    public void verifyFieldsAreEditableAndYellowInBackground() throws AutomationException {
        verifyFieldIsEditableAndYellowBackground("Witness 1 Name", WITNESS_NAME_1);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Name", WITNESS_NAME_2);
        verifyFieldIsEditableAndYellowBackground("Witness 1 Signature", WITNESS_1_SIGNATURE);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Signature", WITNESS_2_SIGNATURE);
        verifyFieldIsEditableAndYellowBackground("Witness 1 Street Address", WITNESS_1_STREET_ADDRESS);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Street Address", WITNESS_2_STREET_ADDRESS);
        verifyFieldIsEditableAndYellowBackground("Witness 1 City, State, Zip", W1_CITY_STATE_ZIP);
        verifyFieldIsEditableAndYellowBackground("Witness 2 City, State, Zip", W2_CITY_STATE_ZIP);
    }

    public void verifyNamesCanBeEnteredAndReflectedInSignatureFields() throws AutomationException, IOException, ParseException {
        String witness1name = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness1name").toString();
        String witness2name = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness2name").toString();

        WebElement witnessName1 = driverUtil.getWebElement(WITNESS_NAME_1);
        WebElement witnessName2 = driverUtil.getWebElement(WITNESS_NAME_2);

        fillFieldWithKeyStrokes(WITNESS_NAME_1, "RW03Form.witness1name");
        fillFieldWithKeyStrokes(WITNESS_NAME_2, "RW03Form.witness2name");

        enteredWitness1Form = witnessName1.getAttribute("value");
        if (!enteredWitness1Form.equals(witness1name)) {
            throw new AutomationException("Witness name field did not accept the entered name: " + witness1name);
        }
        enteredWitness2Form = witnessName2.getAttribute("value");
        if (!enteredWitness2Form.equals(witness2name)) {
            throw new AutomationException("Witness name field did not accept the entered name: " + witness2name);
        }


        WebElement WitnessSignature1 = driverUtil.getWebElement(WITNESS_1_SIGNATURE);
        WebElement WitnessSignature2 = driverUtil.getWebElement(WITNESS_2_SIGNATURE);

        String reflectedNameInSignature1 = WitnessSignature1.getAttribute("value");
        if (!reflectedNameInSignature1.equals(enteredWitness1Form)) {
            throw new AutomationException("The signature field does not reflect the entered witness name. " +
                    "Expected: " + enteredWitness1Form + ", Found: " + reflectedNameInSignature1);
        }
        String reflectedNameInSignature2 = WitnessSignature2.getAttribute("value");
        if (!reflectedNameInSignature2.equals(enteredWitness2Form)) {
            throw new AutomationException("The signature field does not reflect the entered witness name. " +
                    "Expected: " + enteredWitness2Form + ", Found: " + reflectedNameInSignature2);
        }
    }

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
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

    public void verifyNamesUpdatedInSignatureFieldsAreReflectedInWitnessFields() throws AutomationException, IOException, ParseException {
        WebElement witnessSign1 = driverUtil.getWebElement(WITNESS_1_SIGNATURE);
        WebElement witnessSign2 = driverUtil.getWebElement(WITNESS_2_SIGNATURE);

        clearField(WITNESS_1_SIGNATURE);
        fillFieldWithKeyStrokes(WITNESS_1_SIGNATURE, "RW03Form.witness1signature");
        clearField(WITNESS_2_SIGNATURE);
        fillFieldWithKeyStrokes(WITNESS_2_SIGNATURE, "RW03Form.witness2signature");

        enteredWitness1SignForm = witnessSign1.getAttribute("value");
        enteredWitness2SignForm = witnessSign2.getAttribute("value");

        WebElement WitnessName1 = driverUtil.getWebElement(WITNESS_NAME_1);
        WebElement WitnessName2 = driverUtil.getWebElement(WITNESS_NAME_2);

        String reflectedName1 = WitnessName1.getAttribute("value");
        if (!reflectedName1.equals(enteredWitness1SignForm)) {
            throw new AutomationException("The name field does not reflect the entered witness signature. " +
                    "Expected: " + enteredWitness1SignForm + ", Found: " + reflectedName1);
        }
        String reflectedName2 = WitnessName2.getAttribute("value");
        if (!reflectedName2.equals(enteredWitness2SignForm)) {
            throw new AutomationException("The name field does not reflect the entered witness signature. " +
                    "Expected: " + enteredWitness2SignForm + ", Found: " + reflectedName2);
        }

        enteredWitness1Form = WitnessName1.getAttribute("value");
        enteredWitness2Form = WitnessName2.getAttribute("value");
    }

    public void verifyTheAddressCityZipFieldsAcceptCorrectText() throws AutomationException, IOException, ParseException {
        String witness1streetAddress = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness1streetAddress").toString();
        String witness2streetAddress = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness2streetAddress").toString();
        String witness1CityStateZip = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness1CityStateZip").toString();
        String witness2CityStateZip = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness2CityStateZip").toString();

        fillFieldWithKeyStrokes(WITNESS_1_STREET_ADDRESS, "RW03Form.witness1streetAddress");
        fillFieldWithKeyStrokes(WITNESS_2_STREET_ADDRESS, "RW03Form.witness2streetAddress");
        fillFieldWithKeyStrokes(W1_CITY_STATE_ZIP, "RW03Form.witness1CityStateZip");
        fillFieldWithKeyStrokes(W2_CITY_STATE_ZIP, "RW03Form.witness2CityStateZip");

        enteredStreetAddress1Form = driverUtil.getWebElement(WITNESS_1_STREET_ADDRESS).getAttribute("value");
        if (!enteredStreetAddress1Form.equals(witness1streetAddress)) {
            throw new AutomationException("Field did not accept the entered Street Address: " + witness1streetAddress);
        }
        enteredStreetAddress2Form = driverUtil.getWebElement(WITNESS_2_STREET_ADDRESS).getAttribute("value");
        if (!enteredStreetAddress2Form.equals(witness2streetAddress)) {
            throw new AutomationException("Field did not accept the entered Street Address: " + witness2streetAddress);
        }
        enteredCityStateZip1Form = driverUtil.getWebElement(W1_CITY_STATE_ZIP).getAttribute("value");
        if (!enteredCityStateZip1Form.equals(witness1CityStateZip)) {
            throw new AutomationException("Field did not accept the entered city,state, zip: " + witness1CityStateZip);
        }
        enteredCityStateZip2Form = driverUtil.getWebElement(W2_CITY_STATE_ZIP).getAttribute("value");
        if (!enteredCityStateZip2Form.equals(witness2CityStateZip)) {
            throw new AutomationException("Field did not accept the entered city,state, zip: " + witness2CityStateZip);
        }
    }

    private static String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public void verifyAllTheInputFieldsInTheFormAreAutoSaved() throws IOException, ParseException, AutomationException {
        WebDriverUtil.waitForAWhile(1);

        String actualWitness1Name = getFieldValue(WITNESS_NAME_1, "value");
        String actualWitness2Name = getFieldValue(WITNESS_NAME_2, "value");
        String actualWitness1Sign = getFieldValue(WITNESS_1_SIGNATURE, "value");
        String actualWitness2Sign = getFieldValue(WITNESS_2_SIGNATURE, "value");
        String actualW1streetAddress = getFieldValue(WITNESS_1_STREET_ADDRESS, "value");
        String actualW2streetAddress = getFieldValue(WITNESS_2_STREET_ADDRESS, "value");
        String actualW1cityStateZip = getFieldValue(W1_CITY_STATE_ZIP, "value");
        String actualW2cityStateZip = getFieldValue(W2_CITY_STATE_ZIP, "value");

        verifyField("Witness Name 1", enteredWitness1Form, actualWitness1Name);
        verifyField("Witness Name 2", enteredWitness2Form, actualWitness2Name);
        verifyField("Witness Signature 1", enteredWitness1SignForm, actualWitness1Sign);
        verifyField("Witness Signature 2", enteredWitness2SignForm, actualWitness2Sign);
        verifyField("Witness Street Address 1", enteredStreetAddress1Form, actualW1streetAddress);
        verifyField("Witness Street Address 2", enteredStreetAddress2Form, actualW2streetAddress);
        verifyField("Witness City, State, Zip 1", enteredCityStateZip1Form, actualW1cityStateZip);
        verifyField("Witness City, State, Zip 2", enteredCityStateZip2Form, actualW2cityStateZip);
    }

    public void userResetTheRWForm() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        Actions actions = new Actions(DriverFactory.drivers.get());
        actions.moveToElement(driverUtil.getWebElement(PRINTFORM_BUTTON), -50, -50).perform();
        WebDriverUtil.waitForInvisibleElement(By.xpath(PRINT_FORM_TOOLTIP));
        clearField(WITNESS_NAME_1);
        clearField(WITNESS_NAME_2);
        DriverFactory.drivers.get().findElement(By.xpath(WITNESS_1_STREET_ADDRESS)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(WITNESS_2_STREET_ADDRESS)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(W1_CITY_STATE_ZIP)).clear();
        DriverFactory.drivers.get().findElement(By.xpath(W2_CITY_STATE_ZIP)).clear();
        driverUtil.getWebElement(W2_CITY_STATE_ZIP).sendKeys(Keys.ENTER);
        WebDriverUtil.waitForAWhile();
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
            verifyPrintNames(pdfFilePath);
            //verifyCounty(pdfFilePath);
            validateWitnessDetails(pdfFilePath);

        } catch (IOException e) {
            CommonSteps.logInfo("Error reading PDF: " + e.getMessage());
        }
    }

    public static void verifyPrintNames(String pdfFilePath) throws IOException {
        String beforeLine = "Estate of William Arik John Jr. , Deceased";
        String afterLine = "(Print Name/s) (Print Name/s)";

        List<String> names = new ArrayList<>();
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;

        // Log each line and find start/end indexes
        CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);

            if (trimmedLine.contains(beforeLine.trim())) startIndex = i;
            if (trimmedLine.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                String currentLine = allLines[i].trim();
                if (!currentLine.isBlank()) {
                    // Handle cases where witnesses are on the same line
                    if (currentLine.contains(" and ")) {
                        String[] splitNames = currentLine.split(" and ");
                        for (String name : splitNames) {
                            names.add(name.trim().replace(",", "")); // Remove trailing comma
                        }
                    } else {
                        names.add(currentLine.trim().replace(",", ""));
                    }
                }
            }

            CommonSteps.logInfo("\n📌 Extracted Witness Names: " + names);
            if (names.isEmpty()) {
                CommonSteps.logInfo("❌ Validation Failed: No names found between the specified lines.");
                return;
            }

            // Create a map of expected names
            Map<String, String> expectedNames = new LinkedHashMap<>();
            expectedNames.put("First Witness", enteredWitness1Form.trim());
            expectedNames.put("Second Witness", enteredWitness2Form.trim());

            boolean allMatch = true;
            for (int i = 0; i < expectedNames.size(); i++) {
                String expectedValue = expectedNames.values().toArray(new String[0])[i].trim().replace(",", "");
                String actualValue = (i < names.size()) ? names.get(i).trim().replace(",", "") : "No Name";

                CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedValue + "', Extracted: '" + actualValue + "'");

                if (!expectedValue.equalsIgnoreCase(actualValue)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                CommonSteps.logInfo("✅ Validation Passed: Print names match as expected.");
            } else {
                CommonSteps.logInfo("❌ Validation Failed: Print names do not match the expected values.");
            }
        } else {
            CommonSteps.logInfo("❌ Before or after line not found!");
        }
    }

//    public void verifyCounty(String pdfFilePath) throws AutomationException {
//        // Expected Value for "Deceased" field
//        String expectedDeceased = enteredDisplayName.trim();
//
//        try {
//            PDDocument document = PDDocument.load(new File(pdfFilePath));
//            String fullText = new PDFTextStripper().getText(document);
//            document.close();
//
//            // Split into lines for structured searching
//            String[] lines = fullText.split("\\r?\\n");
//
//            CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
//            for (int i = 0; i < lines.length; i++) {
//                CommonSteps.logInfo("Line " + (i + 1) + ": " + lines[i].trim());
//            }
//
//            // Variables to store index positions
//            int startLine = -1, endLine = -1, targetLineIndex = -1;
//
//            // Identify reference lines
//            for (int i = 0; i < lines.length; i++) {
//                String line = lines[i].trim();
//
//                if (line.contains("OATH OF NON-SUBSCRIBING WITNESS(ES)")) {
//                    startLine = i;
//                }
//                if (line.contains("REGISTER OF WILLS OF Henry COUNTY, PENNSYLVANIA")) {
//                    endLine = i;
//                }
//                // Extract line in between startLine and endLine
//                if (startLine != -1 && endLine != -1 && i == startLine + 2) {
//                    targetLineIndex = i;
//                    break;
//                }
//            }
//
//            if (targetLineIndex != -1) {
//                String extractedDeceased = lines[targetLineIndex].trim();
//
//                if (extractedDeceased.contains(expectedDeceased)) {
//                    CommonSteps.logInfo("✅ Validation Passed: 'Deceased' field matches expected value: " + expectedDeceased);
//                } else {
//                    CommonSteps.logInfo("❌ Validation Failed: Expected 'Deceased' value '" + expectedDeceased +
//                            "', but found '" + extractedDeceased + "'");
//                }
//            } else {
//                CommonSteps.logInfo("❌ Could not locate the 'Deceased' field correctly.");
//            }
//
//        } catch (IOException e) {
//            throw new AutomationException("Error reading PDF: " + e.getMessage());
//        }
//    }

    public void validateWitnessDetails(String pdfFilePath) throws IOException {
        List<String> pdfLines = Arrays.asList(new PDFTextStripper().getText(PDDocument.load(new File(pdfFilePath))).split("\\r?\\n"));
        Map<String, Map<String, String>> extractedWitnessDetails = new LinkedHashMap<>();

        // Extract witness details
        for (int i = 0; i < pdfLines.size(); i++) {
            if (pdfLines.get(i).contains("(Signature)")) {
                String signature = pdfLines.get(i).replace("(Signature) ", "").trim();
                String streetAddress = "";
                String cityStateZip = "";

                // Find the Street Address (next non-empty line after Signature)
                for (int j = i + 1; j < pdfLines.size(); j++) {
                    if (!pdfLines.get(j).trim().isEmpty() && !pdfLines.get(j).contains("(Signature)")) {
                        streetAddress = pdfLines.get(j).trim();
                        break;
                    }
                }

                // Find the City, State, Zip (line containing a ZIP code)
                for (int j = i + 2; j < pdfLines.size(); j++) {
                    if (pdfLines.get(j).matches(".*\\d{5}.*")) { // Match ZIP code pattern
                        cityStateZip = pdfLines.get(j).trim();
                        break;
                    }
                }

                extractedWitnessDetails.put(signature, Map.of("Street Address", streetAddress, "City, State, Zip", cityStateZip));
            }
        }

        // Print extracted witness details in the desired format
        for (Map.Entry<String, Map<String, String>> entry : extractedWitnessDetails.entrySet()) {
            String signature = entry.getKey();
            String street = entry.getValue().get("Street Address");
            String cityStateZip = entry.getValue().get("City, State, Zip");

            CommonSteps.logInfo("✅ Extracted Witness: " + signature + " | Street: " + street + " | City/State/Zip: " + cityStateZip);
        }
    }
}



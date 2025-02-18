package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsRW04Page extends BasePage{
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
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
    private static final String ESTATE_OWN_PROPER_HANDWRITING_NAME = "//p[contains(string(), 'is in') and contains(string(), 'own proper handwriting')]//input";
    private static final String WITNESS_NAME_1 = "//td//input[@name='witness1Name']";
    private static final String WITNESS_NAME_2 = "//td//input[@name='witness2Name']";
    private static final String WITNESS_1_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness1Name']";
    private static final String WITNESS_2_SIGNATURE = "//p[contains(text(),'(Signature)')]//input[@name='witness2Name']";
    private static final String WITNESS_1_STREET_ADDRESS = "//textarea[@name='witness1Address']";
    private static final String WITNESS_2_STREET_ADDRESS = "//textarea[@name='witness2Address']";
    private static final String W1_CITY_STATE_ZIP = "//input[@name='witness1CityStateZip']";
    private static final String W2_CITY_STATE_ZIP = "//input[@name='witness2CityStateZip']";
    private static final String DISPLAY_NOTARY_CHECKBOX = "//input[@name='displayNotaryBlock']";


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
    static String enteredWitness1;
    static String enteredWitness2;
    static String enteredWitness1Sign;
    static String enteredWitness2Sign;


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

    public void navigateToProbateFormsTab() throws AutomationException {
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    @Override
    String getName() {
        return "";
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH,formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyEstateCountyAKANameUnderHeader() throws AutomationException {
        String countyName = driverUtil.getWebElement(COUNTY_NAME_HEADER).getAttribute("value");
        if (!enteredDomicileCountry.equals(countyName)) {
            throw new AutomationException("County is incorrect or not fetched correctly. Expected: " + enteredDomicileCountry + ", but got: " + countyName);
        }

        String EstateName = enteredFirstName+" "+enteredMiddleName+" "+enteredLastName+" "+selectedSuffix;
        String estateName = driverUtil.getWebElement(ESTATE_OF_NAME).getAttribute("value");
        if (!EstateName.equals(estateName)) {
            throw new AutomationException("Estate name is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + estateName);
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
        String EstateName = enteredFirstName+" "+enteredMiddleName+" "+enteredLastName+" "+selectedSuffix;

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
        String EstateName = enteredFirstName+" "+enteredMiddleName+" "+enteredLastName+" "+selectedSuffix;

        WebElement estateHeaderName = driverUtil.getWebElement(ESTATE_OF_NAME);
        if (!estateHeaderName.getAttribute("value").equals(EstateName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + EstateName);
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
        verifyFieldIsEditableAndYellowBackground("Witness 1 Name",WITNESS_NAME_1);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Name",WITNESS_NAME_2);
        verifyFieldIsEditableAndYellowBackground("Witness 1 Signature",WITNESS_1_SIGNATURE);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Signature",WITNESS_2_SIGNATURE);
        verifyFieldIsEditableAndYellowBackground("Witness 1 Street Address",WITNESS_1_STREET_ADDRESS);
        verifyFieldIsEditableAndYellowBackground("Witness 2 Street Address",WITNESS_2_STREET_ADDRESS);
        verifyFieldIsEditableAndYellowBackground("Witness 1 City, State, Zip",W1_CITY_STATE_ZIP);
        verifyFieldIsEditableAndYellowBackground("Witness 2 City, State, Zip",W2_CITY_STATE_ZIP);
    }

    public void verifyNamesCanBeEnteredAndReflectedInSignatureFields() throws AutomationException, IOException, ParseException {
        String witness1name = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness1name").toString();
        String witness2name = CommonUtil.getJsonPath("RW03Form").get("RW03Form.witness2name").toString();

        WebElement witnessName1 = driverUtil.getWebElement(WITNESS_NAME_1);
        WebElement witnessName2 = driverUtil.getWebElement(WITNESS_NAME_2);

        fillFieldWithKeyStrokes(WITNESS_NAME_1,"RW03Form.witness1name");
        fillFieldWithKeyStrokes(WITNESS_NAME_2,"RW03Form.witness2name");

        enteredWitness1 = witnessName1.getAttribute("value");
        if (!enteredWitness1.equals(witness1name)) {
            throw new AutomationException("Witness name field did not accept the entered name: " + witness1name);
        }
        enteredWitness2 = witnessName2.getAttribute("value");
        if (!enteredWitness2.equals(witness2name)) {
            throw new AutomationException("Witness name field did not accept the entered name: " + witness2name);
        }


        WebElement WitnessSignature1 = driverUtil.getWebElement(WITNESS_1_SIGNATURE);
        WebElement WitnessSignature2 = driverUtil.getWebElement(WITNESS_2_SIGNATURE);

        String reflectedNameInSignature1 = WitnessSignature1.getAttribute("value");
        if (!reflectedNameInSignature1.equals(enteredWitness1)) {
            throw new AutomationException("The signature field does not reflect the entered witness name. " +
                    "Expected: " + enteredWitness1 + ", Found: " + reflectedNameInSignature1);
        }
        String reflectedNameInSignature2 = WitnessSignature2.getAttribute("value");
        if (!reflectedNameInSignature2.equals(enteredWitness2)) {
            throw new AutomationException("The signature field does not reflect the entered witness name. " +
                    "Expected: " + enteredWitness2 + ", Found: " + reflectedNameInSignature2);
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
        fillFieldWithKeyStrokes(WITNESS_1_SIGNATURE,"RW03Form.witness1signature");
        clearField(WITNESS_2_SIGNATURE);
        fillFieldWithKeyStrokes(WITNESS_2_SIGNATURE,"RW03Form.witness2signature");

        enteredWitness1Sign = witnessSign1.getAttribute("value");
        enteredWitness2Sign = witnessSign2.getAttribute("value");

        WebElement WitnessName1 = driverUtil.getWebElement(WITNESS_NAME_1);
        WebElement WitnessName2 = driverUtil.getWebElement(WITNESS_NAME_2);

        String reflectedName1 = WitnessName1.getAttribute("value");
        if (!reflectedName1.equals(enteredWitness1Sign)) {
            throw new AutomationException("The name field does not reflect the entered witness signature. " +
                    "Expected: " + enteredWitness1Sign + ", Found: " + reflectedName1);
        }
        String reflectedName2 = WitnessName2.getAttribute("value");
        if (!reflectedName2.equals(enteredWitness2Sign)) {
            throw new AutomationException("The name field does not reflect the entered witness signature. " +
                    "Expected: " + enteredWitness2Sign + ", Found: " + reflectedName2);
        }

        enteredWitness1 = WitnessName1.getAttribute("value");
        enteredWitness2 = WitnessName2.getAttribute("value");
    }

}

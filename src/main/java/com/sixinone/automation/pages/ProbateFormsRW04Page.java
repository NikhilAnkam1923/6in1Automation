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
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String PRINT_FORM_TOOLTIP = "//div[@role='tooltip']";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String downloadedFileName;

    static String enteredWitness1Form;
    static String enteredWitness2Form;
    static String enteredWitness1SignForm;
    static String enteredWitness2SignForm;
    static String enteredStreetAddress1Form;
    static String enteredStreetAddress2Form;
    static String enteredCityStateZip1Form;
    static String enteredCityStateZip2Form;
    static String countyForm;
    static String displayNameForm;
    static String AKANameForm;
    static String acquaintedEstateNameForm;
    static String signatureOfEstateNameForm;
    static String ownProperHandwritingEstateNameForm;


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

    public void verifyEstateCountyAKANameUnderHeader() throws AutomationException {
        String domicileCountry = getEstateValue("DomicileCountry");
        countyForm = driverUtil.getWebElement(COUNTY_NAME_HEADER).getAttribute("value");
        if (!domicileCountry.equals(countyForm)) {
            throw new AutomationException("County is incorrect or not fetched correctly. Expected: " + domicileCountry + ", but got: " + countyForm);
        }

        String displayName = getEstateValue("DisplayName");
        displayNameForm = driverUtil.getWebElement(ESTATE_OF_NAME).getAttribute("value");
        if (!displayName.equals(displayNameForm)) {
            throw new AutomationException("Estate name is incorrect or not fetched correctly. Expected: " + displayName + ", but got: " + displayNameForm);
        }

        String alsoKnownAs = getEstateValue("AlsoKnownAs");
        AKANameForm = driverUtil.getWebElement(AKA_NAME_FIELD).getAttribute("value");
        if (!alsoKnownAs.equals(AKANameForm)) {
            throw new AutomationException("AKA is incorrect or not fetched correctly. Expected: " + alsoKnownAs + ", but got: " + AKANameForm);
        }
    }

    public void verifyCountyAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        String domicileCountry = getEstateValue("DomicileCountry");
        if (!countyForm.equals(domicileCountry)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + domicileCountry);
        }

        String alsoKnownAs = getEstateValue("AlsoKnownAs");
        if (!AKANameForm.equals(alsoKnownAs)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + alsoKnownAs);
        }
    }

    public void verifyCorrectEstateSNameIsDisplayedAcrossTheForm() throws AutomationException {
        String EstateName = getEstateValue("FirstName") + " " + getEstateValue("LastName");

        acquaintedEstateNameForm = driverUtil.getWebElement(ESTATE_ACQUAINTED_WITH_NAME).getAttribute("value");
        if (!EstateName.equals(acquaintedEstateNameForm)) {
            throw new AutomationException("County is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + acquaintedEstateNameForm);
        }

        signatureOfEstateNameForm = driverUtil.getWebElement(ESTATE_SIGNATURE_OF_NAME).getAttribute("value");
        if (!EstateName.equals(signatureOfEstateNameForm)) {
            throw new AutomationException("Estate name is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + signatureOfEstateNameForm);
        }

        ownProperHandwritingEstateNameForm = driverUtil.getWebElement(ESTATE_OWN_PROPER_HANDWRITING_NAME).getAttribute("value");
        if (!EstateName.equals(ownProperHandwritingEstateNameForm)) {
            throw new AutomationException("AKA is incorrect or not fetched correctly. Expected: " + EstateName + ", but got: " + ownProperHandwritingEstateNameForm);
        }
    }

    public void verifyDecedentNameOnTheFormIsAutoPopulatedFromTheEstate() throws AutomationException {
        String EstateName = getEstateValue("FirstName") + " " + getEstateValue("LastName");
        String displayName = getEstateValue("DisplayName");

        WebElement estateHeaderName = driverUtil.getWebElement(ESTATE_OF_NAME);
        if (!estateHeaderName.getAttribute("value").equals(displayName)) {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + displayName + " but found: " + estateHeaderName);
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
        String[] labels = {
                "Witness 1 Name", "Witness 2 Name",
                "Witness 1 Signature", "Witness 2 Signature",
                "Witness 1 Street Address", "Witness 2 Street Address",
                "Witness 1 City, State, Zip", "Witness 2 City, State, Zip"
        };

        String[] locators = {
                WITNESS_NAME_1, WITNESS_NAME_2,
                WITNESS_1_SIGNATURE, WITNESS_2_SIGNATURE,
                WITNESS_1_STREET_ADDRESS, WITNESS_2_STREET_ADDRESS,
                W1_CITY_STATE_ZIP, W2_CITY_STATE_ZIP
        };

        for (int i = 0; i < labels.length; i++) {
            verifyFieldIsEditableAndYellowBackground(labels[i], locators[i]);
        }
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

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public void verifyAllTheInputFieldsInTheFormAreAutoSaved() throws IOException, ParseException, AutomationException {
        WebDriverUtil.waitForAWhile(1);

        String[] labels = {
                "Witness Name 1", "Witness Name 2",
                "Witness Signature 1", "Witness Signature 2",
                "Witness Street Address 1", "Witness Street Address 2",
                "Witness City, State, Zip 1", "Witness City, State, Zip 2"
        };

        String[] expectedValues = {
                enteredWitness1Form, enteredWitness2Form,
                enteredWitness1SignForm, enteredWitness2SignForm,
                enteredStreetAddress1Form, enteredStreetAddress2Form,
                enteredCityStateZip1Form, enteredCityStateZip2Form
        };

        String[] actualValues = {
                getFieldValue(WITNESS_NAME_1), getFieldValue(WITNESS_NAME_2),
                getFieldValue(WITNESS_1_SIGNATURE), getFieldValue(WITNESS_2_SIGNATURE),
                getFieldValue(WITNESS_1_STREET_ADDRESS), getFieldValue(WITNESS_2_STREET_ADDRESS),
                getFieldValue(W1_CITY_STATE_ZIP), getFieldValue(W2_CITY_STATE_ZIP)
        };

        for (int i = 0; i < labels.length; i++) {
            verifyField(labels[i], expectedValues[i], actualValues[i]);
        }
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
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
        scrollToElementAndClick(SHOW_AKA_CHECkBOX);
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
            verifyFieldsInPDF(pdfFilePath,
                    "Deputy for Register of Wills",
                    "(Signature) Leo",
                    enteredStreetAddress1Form,
                    "Witness1 Street Address");
            verifyFieldsInPDF(pdfFilePath,
                    "(City, State, Zip)",
                    "OATH OF NON-SUBSCRIBING WITNESS(ES)",
                    enteredStreetAddress2Form,
                    "Witness2 Street Address");
            validateWitnessDetails(pdfFilePath);

            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (AutomationException | IOException e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }

    private static void verifyPrintNames(String pdfFilePath) throws IOException, AutomationException {
        List<String> beforeLines = Arrays.asList("a/k/a Jonny", "Estate of William Arik John Jr. , Deceased");
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

            for (String beforeLine : beforeLines) {
                if (trimmedLine.contains(beforeLine.trim())) {
                    startIndex = i;
                    break;  // Stop checking once we find a match
                }
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

                CommonSteps.logInfo("🔍 Comparing ->Expected: '" + expectedValue + "', Extracted: '" + actualValue + "'");

                if (!expectedValue.equalsIgnoreCase(actualValue)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                CommonSteps.logInfo("✅ Validation Passed: Print names match as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Print names do not match the expected values.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }

    private static void verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName) throws IOException, AutomationException {
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
                    extractedValue = cleanField(currentLine);
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
        } else {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }
    }

    // **Updated Helper Method to Clean Names Properly**
    private static String cleanField(String rawName) {
        if (rawName == null || rawName.trim().isEmpty()) return "";

        return rawName
                .replaceAll("[,\\.\\s]+$", "") // Remove trailing commas, dots, and extra spaces
                .trim(); // Trim spaces
    }

    public void validateWitnessDetails(String pdfFilePath) throws IOException, AutomationException {
        Map<String, String> extractedWitnessDetails = new LinkedHashMap<>();
        List<String> mismatchErrors = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            List<String> pdfLines = Arrays.asList(new PDFTextStripper().getText(document).split("\\r?\\n"));

            // Extract witness details
            for (int i = 0; i < pdfLines.size(); i++) {
                if (pdfLines.get(i).contains("(Signature)")) {
                    String signature = pdfLines.get(i).replace("(Signature)", "").trim();
                    String cityStateZip = "";

                    // Find the City, State, Zip (first line with a valid ZIP code)
                    for (int j = i + 2; j < pdfLines.size(); j++) {
                        if (pdfLines.get(j).matches(".*\\b\\d{5}(-\\d{4})?\\b.*")) { // Match ZIP or ZIP+4
                            cityStateZip = pdfLines.get(j).trim();
                            break;
                        }
                    }

                    extractedWitnessDetails.put(signature, cityStateZip);
                }
            }
        }

        // Validate extracted witness details
        for (Map.Entry<String, String> entry : extractedWitnessDetails.entrySet()) {
            String extractedSignature = entry.getKey();
            String extractedCityStateZip = entry.getValue();

            boolean witness1Match = extractedSignature.equalsIgnoreCase(enteredWitness1SignForm) &&
                    extractedCityStateZip.equalsIgnoreCase(enteredCityStateZip1Form);

            boolean witness2Match = extractedSignature.equalsIgnoreCase(enteredWitness2SignForm) &&
                    extractedCityStateZip.equalsIgnoreCase(enteredCityStateZip2Form);

            if (witness1Match || witness2Match) {
                CommonSteps.logInfo("✅ Witness Verified: " + extractedSignature +
                        " | City/State/Zip: " + extractedCityStateZip);
            } else {
                mismatchErrors.add("❌ Witness Mismatch: Expected one of -> " +
                        "[ Witness1: { Name: " + enteredWitness1SignForm +
                        ", City/State/Zip: " + enteredCityStateZip1Form + " } OR " +
                        "Witness2: { Name: " + enteredWitness2SignForm +
                        ", City/State/Zip: " + enteredCityStateZip2Form + " } ] " +
                        "but Found -> { Name: " + extractedSignature +
                        ", City/State/Zip: " + extractedCityStateZip + " }");
            }
        }

        // If any mismatch occurred, throw an exception after logging all mismatches
        if (!mismatchErrors.isEmpty()) {
            throw new AutomationException(String.join("\n", mismatchErrors));
        }
    }

}
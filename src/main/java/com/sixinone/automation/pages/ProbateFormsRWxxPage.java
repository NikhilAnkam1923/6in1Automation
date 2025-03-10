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

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.scrollPageUp;
import static com.sixinone.automation.util.WebDriverUtil.waitForAWhile;

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
        driverUtil.getWebElement(String.format(RW_FORM_XPATH, formToSelect)).click();
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

        fillFieldWithKeyStrokes(REVIEWER_NAME, "RWxxForm.reviewerName");
        fillFieldWithKeyStrokes(REVIEWER_DESIGNATION, "RWxxForm.reviewerDesignation");
        fillFieldWithKeyStrokes(WITNESS_NAME_1, "RWxxForm.witness1name");
        fillFieldWithKeyStrokes(WITNESS_NAME_2, "RWxxForm.witness2name");
        fillFieldWithKeyStrokes(REASON, "RWxxForm.reason");

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
        fillFieldWithKeyStrokes(REVIEWER_SIGN, "RWxxForm.reviewerSign");

        enteredReviewerSign = driverUtil.getWebElement(REVIEWER_SIGN).getAttribute("value");

        String updatedReviewerName = driverUtil.getWebElement(REVIEWER_NAME).getAttribute("value");

        if (!enteredReviewerSign.equals(updatedReviewerName)) {
            throw new AutomationException("The reviewer name field does not reflect the entered reviewer signature. " +
                    "Expected: " + enteredReviewerSign + ", Found: " + updatedReviewerName);
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
            verifyReviewerName(pdfFilePath);
            verifyreviewerDesignation(pdfFilePath);
            verifyWitnessNames(pdfFilePath);
            verifyReviewerReason(pdfFilePath);
            verifyReviewerSign(pdfFilePath);

        } catch (IOException e) {
            CommonSteps.logInfo("Error reading PDF: " + e.getMessage());
        }
    }


    public static void verifyReviewerName(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "Estate of William John  , Deceased";
        String afterLine = "depose and say that I, the Deputy Register of Wills in the above-referenced estate, declare that";

        List<String> names = new ArrayList<>();
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("📄 **Full PDF Content (All Lines):**");
        for (int i = 0; i < allLines.length; i++) {
            CommonSteps.logInfo("Line " + (i + 1) + ": " + allLines[i]); // Log every line
        }

        int startIndex = -1, endIndex = -1;

        //Find Start and End Lines
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

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
                    names.add(cleanReviewerName(currentLine));
                }
            }

            CommonSteps.logInfo("\n📌 Extracted reviewer Name: " + names);
            if (names.isEmpty()) {
                CommonSteps.logInfo("❌ Validation Failed: No reviewer found between the specified lines.");
                return;
            }

            // Create a map of expected names
            Map<String, String> expectedNames = new LinkedHashMap<>();
            expectedNames.put("Reviewer Name", cleanReviewerName(enteredReviewerName));


            boolean allMatch = true;
            for (int i = 0; i < expectedNames.size(); i++) {
                String expectedValue = expectedNames.values().toArray(new String[0])[i];
                String actualValue = (i < names.size()) ? names.get(i) : "No Name";

                CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedValue + "', Extracted: '" + actualValue + "'");

                if (!expectedValue.equalsIgnoreCase(actualValue)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                CommonSteps.logInfo("✅ Validation Passed: reviewer name match as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: reviewer name do not match the expected values.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }

    private static String cleanReviewerName(String rawName) {
        if (rawName == null || rawName.trim().isEmpty()) return "";

        return rawName
                .replaceAll("(?i)^\\s*(I,?)\\s*", "") // Remove "I," if it appears at the beginning
                .replaceAll("(?i)\\b(being duly sworn according to law)\\b", "") // Remove the phrase anywhere in the sentence
                .replaceAll("[,\\.\\s]+$", "") // Remove trailing commas, dots, and spaces
                .trim(); // Trim spaces at the beginning and end
    }


    public static void verifyreviewerDesignation(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "I, Louis Pat , being duly sworn according to law,";
        String afterLine = "Harry Steve ,";


        List<String> names = new ArrayList<>();
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;

        //Find Start and End Lines
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

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
                    names.add(cleanReviewerDesignation(currentLine));
                }
            }

            if (names.isEmpty()) {
                CommonSteps.logInfo("❌ Validation Failed: No reviewer designation found between the specified lines.");
                return;
            }

            // Create a map of expected names
            Map<String, String> expectedNames = new LinkedHashMap<>();
            expectedNames.put("Reviewer designation", cleanReviewerDesignation(enteredReviewerDesignation));


            boolean allMatch = true;
            for (int i = 0; i < expectedNames.size(); i++) {
                String expectedValue = expectedNames.values().toArray(new String[0])[i];
                String actualValue = (i < names.size()) ? names.get(i) : "No Name";

                CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedValue + "', Extracted: '" + actualValue + "'");

                if (!expectedValue.equalsIgnoreCase(actualValue)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                CommonSteps.logInfo("✅ Validation Passed: reviewer designation match as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: reviewer designation do not match the expected values.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }

    private static String cleanReviewerDesignation(String rawName) {
        if (rawName == null || rawName.trim().isEmpty()) return "";

        return rawName
                .replaceAll("(?i)^\\s*(depose and say that I, the ?)\\s*", "") // Remove "I," if it appears at the beginning
                .replaceAll("(?i)\\b( in the above-referenced estate, declare that)\\b", "") // Remove the phrase anywhere in the sentence
                .replaceAll("[,\\.\\s]+$", "") // Remove trailing commas, dots, and spaces
                .trim(); // Trim spaces at the beginning and end
    }

    public void verifyWitnessNames(String pdfFilePath) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String beforeLine = "depose and say that I, the Deputy Register of Wills in the above-referenced estate, declare that";
        String afterLine = "whose signature(s) appears as subscribing witness(es) to the WILL or CODICIL of the above";

        int startIndex = pdfText.indexOf(beforeLine);
        int endIndex = pdfText.indexOf(afterLine, startIndex);

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ Witness name section not found in the PDF.");
        }

        String[] extractedWitnesses = pdfText.substring(startIndex + beforeLine.length(), endIndex).trim()
                .replace(",", "").split("\\s+");

        if (extractedWitnesses.length != 2) {
            throw new AutomationException("❌ Expected 2 witness names, but found: " + Arrays.toString(extractedWitnesses));
        }

        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + enteredWitness1 + "', Extracted: '" + extractedWitnesses[0] + "'");
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + enteredWitness2 + "', Extracted: '" + extractedWitnesses[1] + "'");

        if (!enteredWitness1.equals(extractedWitnesses[0]) || !enteredWitness2.equals(extractedWitnesses[1])) {
            throw new AutomationException("❌ Witness name mismatch! Expected: [" + enteredWitness1 + ", " + enteredWitness2
                    + "], Extracted: " + Arrays.toString(extractedWitnesses));
        }

        CommonSteps.logInfo("✅ Witness names verified successfully: " + enteredWitness1 + " and " + enteredWitness2);
    }

    public static void verifyReviewerReason(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "is/are not readily available to prove the signature of the Testator by reason of";
        String afterLine = "Sworn to or affirmed and subscribed"; // Last identifiable text

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;
        StringBuilder extractedReason = new StringBuilder();

        // Find Start and End Lines
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            if (trimmedLine.contains(beforeLine)) startIndex = i;
            if (trimmedLine.contains(afterLine) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex + 1; i < endIndex; i++) {
                extractedReason.append(allLines[i].trim()).append(" ");
            }
            String finalExtractedReason = cleanReviewerReason(extractedReason.toString().trim());

            if (finalExtractedReason.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No reviewer reason found between specified lines.");
            }

            // Expected reason to compare
            String expectedReason = cleanReviewerReason(enteredReason);

            CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedReason + "', Extracted: '" + finalExtractedReason + "'");

            if (expectedReason.equalsIgnoreCase(finalExtractedReason)) {
                CommonSteps.logInfo("✅ Validation Passed: Reviewer reason matches expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Reviewer reason does not match expected value.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }

    // Cleaning function to normalize extracted text
    private static String cleanReviewerReason(String rawText) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        return rawText
                .replaceAll("\\s+", " ") // Normalize spaces
                .replaceAll("[,\\.]+$", "") // Remove trailing commas, dots
                .trim(); // Trim spaces
    }

    public static void verifyReviewerSign(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "and";
        String afterLine = "Executed in Register’s Office";

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;
        String extractedReviewerSign = "";

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
                    extractedReviewerSign = currentLine; // Directly store the name
                    break; // Stop after extracting the first valid name
                }
            }

            if (extractedReviewerSign.isEmpty()) {
                throw new AutomationException("❌ Validation Failed: No reviewer Signature found between specified lines.");
            }

            // Validate extracted name
            String expectedReviewerSign = enteredReviewerSign.trim();
            CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedReviewerSign + "', Extracted: '" + extractedReviewerSign
                    + "'");

            if (expectedReviewerSign.equalsIgnoreCase(extractedReviewerSign)) {
                CommonSteps.logInfo("✅ Validation Passed: Reviewer Signature matches expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Reviewer Signature does not match expected value.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }
}

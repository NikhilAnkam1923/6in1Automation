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
    private static final String PRINTFORM_BUTTON = "//*[local-name()='svg' and contains(@class, 'cursor')]";
    private static final String PRINT_FORM_TOOLTIP = "//div[@role='tooltip']";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String downloadedFileName;

    static String enteredReviewerName;
    static String enteredReviewerDesignation;
    static String enteredWitness1;
    static String enteredWitness2;
    static String enteredReason;
    static String enteredReviewerSign;
    static String domicileCountryForm;
    static String displayNameForm;
    static String alsoKnownAsForm;

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
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
        domicileCountryForm = getEstateValue("DomicileCountry");
        displayNameForm = getEstateValue("DisplayName");
        alsoKnownAsForm = getEstateValue("AlsoKnownAs");

        verifyAutoPopulatedValue(domicileCountryForm);
        verifyAutoPopulatedValue(displayNameForm);
        verifyAutoPopulatedValue(alsoKnownAsForm);
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

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    public void userResetsTheRWForm() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        actions.moveToElement(driverUtil.getWebElement(PRINTFORM_BUTTON), -50, -50).perform();
        WebDriverUtil.waitForInvisibleElement(By.xpath(PRINT_FORM_TOOLTIP));

        clearField(REVIEWER_NAME);
        clearField(REVIEWER_DESIGNATION);
        clearField(WITNESS_NAME_1);
        clearField(WITNESS_NAME_2);
        DriverFactory.drivers.get().findElement(By.xpath(REASON)).clear();
        driverUtil.getWebElement(REASON).sendKeys(Keys.ENTER);

        scrollToElementAndClick(SHOW_AKA_CHECkBOX);
        WebDriverUtil.waitForAWhile();
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

        WebDriverUtil.waitForAWhile(2);

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
        clickOnRWForm("UWA");

        WebDriverUtil.waitForAWhile(1);

        String actualReviewerName = getFieldValue(REVIEWER_NAME);
        String actualReviewerDesignation = getFieldValue(REVIEWER_DESIGNATION);
        String actualWitness1name = getFieldValue(WITNESS_NAME_1);
        String actualWitness2name = getFieldValue(WITNESS_NAME_2);
        String actualReason = getFieldValue(REASON);

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
            boolean isVerifiedReviewerName = verifyReviewerName(pdfFilePath);
            boolean isVerifiedReviewerDesignation = verifyPDFSection(pdfFilePath, "Reviewer Designation", "I, Louis Pat ,being duly sworn according to law", "Harry and Steve ,", enteredReviewerDesignation);
            boolean isVerifiedWitnessNames = verifyWitnessNames(pdfFilePath);
            boolean isVerifiedReason = verifyPDFSection(pdfFilePath, "Reviewer Reason", "is/are not readily available to prove the signature of the Testator by reason of", "Sworn to or affirmed and subscribed", enteredReason);
            boolean isVerifiedReviewerSign = verifyReviewerSign(pdfFilePath);

            if (!isVerifiedReviewerName || !isVerifiedReviewerDesignation || !isVerifiedWitnessNames || !isVerifiedReason || !isVerifiedReviewerSign) {
                throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
            }

            CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
        } catch (AutomationException | IOException e) {
            throw new AutomationException("❌ Verification failed: " + e.getMessage());
        }
    }


    private static boolean verifyReviewerName(String pdfFilePath) throws IOException, AutomationException {
        List<String> beforeLines = Arrays.asList("a/k/a Jonny", "Estate of William John , Deceased");
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

        // Find Start and End Lines
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

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
                    names.add(cleanReviewerName(currentLine));
                }
            }

            CommonSteps.logInfo("\n📌 Extracted reviewer Name: " + names);
            if (names.isEmpty()) {
                CommonSteps.logInfo("❌ Validation Failed: No reviewer found between the specified lines.");
                throw new AutomationException("❌ Validation Failed: No reviewer found between the specified lines.");
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
                CommonSteps.logInfo("✅ Validation Passed: reviewer name matches as expected.");
                return true; // Return true if all match
            } else {
                throw new AutomationException("❌ Validation Failed: reviewer names do not match the expected values.");
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

    private static boolean verifyPDFSection(String pdfFilePath, String sectionType, String beforeLine, String afterLine, String expectedValue) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split text into lines
        String[] allLines = pdfText.split("\\r?\\n");

        int startIndex = -1, endIndex = -1;
        StringBuilder extractedText = new StringBuilder();

        // Identify start and end indices
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            if (trimmedLine.contains(beforeLine)) startIndex = i;
            if (trimmedLine.contains(afterLine) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ Before or after line not found for: " + sectionType);
        }

        // Extract and clean text
        for (int i = startIndex + 1; i < endIndex; i++) {
            extractedText.append(allLines[i].trim()).append(" ");
        }

        String finalExtractedText = cleanExtractedText(extractedText.toString().trim(), sectionType);

        if (finalExtractedText.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No text extracted for section: " + sectionType);
        }

        String expectedCleanedValue = cleanExtractedText(expectedValue, sectionType);

        // Log comparison
        CommonSteps.logInfo("🔍 Comparing -> Section: " + sectionType + " | Expected: '" + expectedCleanedValue + "', Extracted: '" + finalExtractedText + "'");

        if (expectedCleanedValue.equalsIgnoreCase(finalExtractedText)) {
            CommonSteps.logInfo("✅ Validation Passed: " + sectionType + " matches expected.");
            return true;
        } else {
            throw new AutomationException("❌ Validation Failed: " + sectionType + " does not match expected value.");
        }
    }

    // Dynamic cleaning method using switch case
    private static String cleanExtractedText(String rawText, String type) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        switch (type) {
            case "Reviewer Designation":
                return rawText
                        .replaceAll("(?i)^\\s*(depose and say that I, the ?)\\s*", "")
                        .replaceAll("(?i)\\b( in the above-referenced estate, declare that)\\b", "")
                        .replaceAll("[,\\.\\s]+$", "")
                        .trim();

            case "Reviewer Reason":
                return rawText
                        .replaceAll("\\s+", " ")
                        .replaceAll("[,\\.]+$", "")
                        .trim();

            default:
                return rawText.trim();
        }
    }

    private static boolean verifyWitnessNames(String pdfFilePath) throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String beforeLine = "depose and say that I, the Deputy Register of Wills in the above-referenced estate, declare that";
        String afterLine = "whose signature(s) appears as subscribing witness(es) to the WILL or CODICIL of the above Testator,";

        int startIndex = pdfText.indexOf(beforeLine);
        int endIndex = pdfText.indexOf(afterLine, startIndex);

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ Witness name section not found in the PDF.");
        }

        // Extract witness section
        String extractedText = pdfText.substring(startIndex + beforeLine.length(), endIndex).trim();

        // Clean the extracted text using `cleanReviewerReason`
        extractedText = cleanWitness(extractedText);

        // Split names by " and " (assuming they are separated by "and")
        String[] witnessNames = extractedText.split("\\s+and\\s+");

        if (witnessNames.length != 2) {
            throw new AutomationException("❌ Expected 2 witness names, but found: " + extractedText);
        }

        String extractedWitness1 = witnessNames[0];
        String extractedWitness2 = witnessNames[1];

        // Log extracted values
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + enteredWitness1 + "', Extracted: '" + extractedWitness1 + "'");
        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + enteredWitness2 + "', Extracted: '" + extractedWitness2 + "'");

        // Validate extracted names with expected names
        if (!enteredWitness1.equalsIgnoreCase(extractedWitness1) || !enteredWitness2.equalsIgnoreCase(extractedWitness2)) {
            throw new AutomationException("❌ Witness name mismatch! Expected: [" + enteredWitness1 + ", " + enteredWitness2
                    + "], Extracted: [" + extractedWitness1 + ", " + extractedWitness2 + "]");
        }

        CommonSteps.logInfo("✅ Witness names verified successfully: " + extractedWitness1 + " and " + extractedWitness2);
        return true;
    }

    // Helper method for cleaning text
    private static String cleanWitness(String rawText) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        return rawText
                .replaceAll("\\s+", " ") // Normalize spaces
                .replaceAll("[,\\.]+$", "") // Remove trailing commas, dots
                .trim(); // Trim spaces
    }


    private static boolean verifyReviewerSign(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "UNAVAILABLE WITNESS AFFIDAVIT";  // The next line after the date

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        int endIndex = -1;
        String extractedReviewerSignature = "";

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Find the first occurrence of "UNAVAILABLE WITNESS AFFIDAVIT"
            if (trimmedLine.equalsIgnoreCase(beforeLine)) {
                endIndex = i;
                break;
            }
        }

        // Extract the line before "UNAVAILABLE WITNESS AFFIDAVIT"
        if (endIndex > 0) {  // Ensure it's not the first line
            extractedReviewerSignature = allLines[endIndex - 1].trim();
        }

        if (extractedReviewerSignature.isEmpty()) {
            throw new AutomationException("❌ Validation Failed: No Reviewer Signature found before (Date).");
        }

        // Clean the extracted signature
        extractedReviewerSignature = cleanReviewerSignature(extractedReviewerSignature);

        // Expected Signature for comparison
        String expectedReviewerSignature = enteredReviewerSign.trim();

        CommonSteps.logInfo("🔍 Comparing -> Expected: '" + expectedReviewerSignature + "', Extracted: '" + extractedReviewerSignature + "'");

        if (expectedReviewerSignature.equalsIgnoreCase(extractedReviewerSignature)) {
            CommonSteps.logInfo("✅ Validation Passed: Extracted Reviewer Signature matches expected.");
        return true;
        } else {
            throw new AutomationException("❌ Validation Failed: Extracted Reviewer Signature does not match expected value.");
        }
    }

    private static String cleanReviewerSignature(String rawText) {
        if (rawText == null || rawText.trim().isEmpty()) return "";

        return rawText
                .replaceAll("\\(Signature\\)", "") // Remove "(Signature)"
                .replaceAll("[,\\.]+$", "") // Remove trailing commas, dots
                .replaceAll("\\s+", " ") // Normalize spaces
                .trim(); // Trim spaces
    }
}

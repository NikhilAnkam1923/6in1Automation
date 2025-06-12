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
import java.util.*;

import static com.sixinone.automation.drivers.DriverFactory.*;

public class ProbateFormsOC07Page extends BasePage {

    private final Map<String, String> estateInfo = new HashMap<>();
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
    private static final String FIRST_PAGE_ACTIVE_XPATH = "//a[@role='tab' and text()='1' and @class='nav-link active']";
    private static final String ESTATE_PAGE_1_XPATH = "//input[@value='%s']";
    private static final String COUNTY_PAGE_1_XPATH = "//div[@class='county-title']//span[@class='county-name']";
    private static final String FILE_NUMBER_FIELD = "//input[@name='fileNumberPA']";
    private static final String CLOSE_TOASTER_BTN = "//button[@class='Toastify__close-button Toastify__close-button--light']";
    private static final String OC_FORM_XPATH = "//a//p[text()='%s']";
    private static final String USE_4_DIGIT_YEAR_CHECKBOX = "//input[@name='isUseFourDigit']";
    private static final String ESTATE_ADDRESS_FIELD1 = "//p[contains(text(), 'The Decedent, who resided at')]//span//input";
    private static final String ESTATE_ADDRESS_FIELD2 = "//p[contains(text(), 'The Decedent, who resided at')]/following-sibling::p//span[1]//input";
    private static final String DATE_OF_DEATH_FIELDOC7 = "//p[contains(text(), 'The Decedent, who resided at')]/following-sibling::p//span[2]//input";
    private static final String INPUT_FIELD_BY_NAME = "//input[@name='%s']";
    private static final String INPUT_CLAIMOFFIELD = "//p[contains(text(), 'Enter the claim of')]//input[@name='claimantName']";
    private static final String INPUT_CLAIMANTFIELD = "//p[text()='(Street Address)']/preceding::input[@name='claimantStreetAddress']/parent::p/preceding-sibling::p[@class='p9 ft3']/input[@name='claimantName']";

    static String downloadedFileName;

    static String estateNameForm;
    static String fileNumberForm;
    static String initialFileNumber;
    static String fourDigitFileNumberForm;

    public void userSavesEstateInfo() throws AutomationException {
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

    private static String getFieldValue(String locator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            String value = field.getAttribute("value");
            return (value != null && !value.trim().isEmpty()) ? value.trim() : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    private String getEstateValue(String key) {
        return estateInfo.getOrDefault(key, "");
    }

    @Override
    String getName() {
        return "";
    }

    public void verifyEstateNameAddressDateOfDeathAndFileNumberAutofilled() throws AutomationException, IOException, ParseException, IOException, ParseException {
        WebElement page1 = driverUtil.getWebElement(FIRST_PAGE_ACTIVE_XPATH);

        if (!page1.isDisplayed()) {
            throw new AutomationException("OC-07 Page 1 did not load correctly.");
        }

        // ✅ Estate Name
        String expectedEstateName = getEstateValue("DisplayName");
        WebElement estateNameElement = driverUtil.getWebElement(String.format(ESTATE_PAGE_1_XPATH, expectedEstateName));
        estateNameForm = estateNameElement.getAttribute("value");

        if (!estateNameForm.equals(expectedEstateName)) {
            throw new AutomationException("Estate name mismatch.\nExpected: " + expectedEstateName + "\nFound: " + estateNameForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Estate Name: " + estateNameForm);
        }

        // ✅ Address

        String expectedAddressLine1 = getEstateValue("DomicileAddressLine1");
        String expectedAddressLine2 = getEstateValue("DomicileAddressLine2");
        String expectedAddress3City = getEstateValue("DomicileCity");
        String expectedAddress4State = getEstateValue("DomicileState");
        String expectedAddress5Zip = getEstateValue("DomicileZip");

        if ("Pennsylvania".equalsIgnoreCase(expectedAddress4State)) {
            expectedAddress4State = "PA";
        }

        String expectedAddress = expectedAddressLine1 + ", " + expectedAddressLine2 + " " + expectedAddress3City + ", " + expectedAddress4State + " " + expectedAddress5Zip;

        WebElement addressElement1 = driverUtil.getWebElement(ESTATE_ADDRESS_FIELD1);
        WebElement addressElement2 = driverUtil.getWebElement(ESTATE_ADDRESS_FIELD2);

        String addressForm1 = addressElement1.getAttribute("value");
        String addressForm2 = addressElement2.getAttribute("value");

        String addressForm = addressForm1 + " " + addressForm2;

        if (!expectedAddress.equalsIgnoreCase(addressForm)) {
            throw new AutomationException("Estate address mismatch.\nExpected: " + expectedAddress + "\nFound: " + addressForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Estate Address: " + addressForm);
        }

        // ✅ Date of Death
        String expectedDoD = getEstateValue("DateOfDeath");

        WebElement dodElement = driverUtil.getWebElement(DATE_OF_DEATH_FIELDOC7);
        String dodForm = dodElement.getAttribute("value");

        if (!expectedDoD.equals(dodForm)) {
            throw new AutomationException("Date of Death mismatch.\nExpected: " + expectedDoD + "\nFound: " + dodForm);
        } else {
            CommonSteps.logInfo("✅ Verified → Date of Death: " + dodForm);
        }

        // ✅ File Number
        String part1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart1").toString();
        String part2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart2").toString();
        String part3 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart3").toString();
        String expectedFileNumber = part1 + "-" + part2 + "-" + part3;

        WebElement fileNumberElement = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        fileNumberForm = fileNumberElement.getAttribute("value");

        if (!fileNumberForm.equals(expectedFileNumber)) {
            throw new AutomationException("File number mismatch.\nExpected: " + expectedFileNumber + "\nFound: " + fileNumberForm);
        } else {
            CommonSteps.logInfo("✅ Verified → File Number: " + fileNumberForm);
        }
    }

    public void enterValidFileNumber() throws AutomationException, IOException, ParseException {
        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        if (!fileNumberField.isEnabled()) {
            throw new AutomationException("File number field is not editable.");
        }

        initialFileNumber = fileNumberField.getAttribute("value");

        String newFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();

        fileNumberField.clear();
        fileNumberField.sendKeys(newFileNumber);

        WebDriverUtil.waitForAWhile(); // small wait for UI update

        List<WebElement> toasterBtns = driverUtil.getWebElements(CLOSE_TOASTER_BTN);

        if (!toasterBtns.isEmpty() && toasterBtns.get(0).isDisplayed()) {
            toasterBtns.get(0).click();
            CommonSteps.logInfo("Toaster close button clicked.");
        } else {
            CommonSteps.logInfo("Toaster close button not present.");
        }
    }

    public void clickOnOCForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(OC_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyFileNumberUpdatedAndSaved() throws AutomationException, IOException, ParseException {
        clickOnOCForm("OC 06");
        clickOnOCForm("OC 07");

        WebElement fileNumberField = driverUtil.getWebElement(FILE_NUMBER_FIELD);

        String enteredFileNumber = CommonUtil.getJsonPath("OC01Form").get("OC01Form.fileNumber").toString();
        String actualFileNumber = fileNumberField.getAttribute("value");

        if (!actualFileNumber.equals(enteredFileNumber)) {
            throw new AutomationException("File number not updated correctly. Expected: " + enteredFileNumber + ", Found: " + actualFileNumber);
        }
        String fileNumber = CommonUtil.getJsonPath("OC07Form").get("OC07Form.fileNumber").toString();
        WebElement filenumberfield = driverUtil.getWebElement(FILE_NUMBER_FIELD);
        filenumberfield.clear();
        filenumberfield.sendKeys(fileNumber);

        WebElement toasterbtn = driverUtil.getWebElement(CLOSE_TOASTER_BTN);
        toasterbtn.click();

    }

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    public void userClicksTheUseDigitYearCheckbox() {
        scrollToElement(USE_4_DIGIT_YEAR_CHECKBOX);
        DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();
    }

    public void verifyFileNumberWithFourDigitYearFormatIsDisplayedInTheFileNumberBox() throws AutomationException {
        fourDigitFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        String expectedFourDigitFileNumber = fileNumberForm.replaceFirst("-(\\d{2})-", "-20$1-");

        if (!fourDigitFileNumberForm.equals(expectedFourDigitFileNumber)) {
            throw new AutomationException("File number did not update correctly. Expected: " + expectedFourDigitFileNumber + ", Found: " + fourDigitFileNumberForm);
        }

        userClicksTheUseDigitYearCheckbox();   //for uncheck fourdigit checkbox
    }

    public void userNavigatesToTheAmountField() throws AutomationException {
        WebElement claimAmountField = driverUtil.getWebElement(String.format(INPUT_FIELD_BY_NAME, "claimAmount"));
        ((JavascriptExecutor) DriverFactory.drivers.get())
                .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", claimAmountField);
        WebDriverUtil.waitForAWhile();
    }

    public void verifyEditabilityofAmountField() throws AutomationException {
        WebElement claimAmountField = driverUtil.getWebElement(String.format(INPUT_FIELD_BY_NAME, "claimAmount"));
        if (!claimAmountField.isEnabled() || !claimAmountField.isDisplayed()) {
            throw new AutomationException("❌ Amount field is not editable.");
        }
    }

    public void verifyAmountFieldAcceptOnlyNumericValue() throws AutomationException {
        WebElement claimAmountField = driverUtil.getWebElement(String.format(INPUT_FIELD_BY_NAME, "claimAmount"));

        for (String input : generateValidNumericInputs()) {
            enterAmountAndValidate(claimAmountField, input);
        }

        CommonSteps.logInfo("✅ Amount field validation completed with formatting support.");
    }

    private void enterAmountAndValidate(WebElement field, String input) throws AutomationException {
        for (int attempt = 0; attempt < 2; attempt++) {
            clearAndTypeSlowly(field, input);
            field.sendKeys(Keys.TAB);
            WebDriverUtil.waitForAWhile(2); // allow JS formatting

            String captured = field.getAttribute("value").trim();
            String normalizedInput = normalizeNumeric(input);
            String normalizedCaptured = normalizeNumeric(captured);

            if (normalizedInput.equals(normalizedCaptured)) {
                CommonSteps.logInfo("✅ Valid input accepted: '" + input + "' -> Captured: '" + captured + "'");
                return;
            }

            WebDriverUtil.waitForAWhile(1); // small wait before retry
        }

        throw new AutomationException("❌ Input rejected: '" + input + "' -> Captured: '" + field.getAttribute("value").trim() + "'");
    }

    private void clearAndTypeSlowly(WebElement field, String value) {
        field.click();
        field.clear();
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.DELETE);
        WebDriverUtil.waitForAWhile(1);

        for (char ch : value.toCharArray()) {
            field.sendKeys(Character.toString(ch));
            //WebDriverUtil.waitForAWhile(1); // slow typing if needed
        }
    }


    private String normalizeNumeric(String value) {
        // Remove commas and ensure two decimal places
        if (value == null || value.isEmpty()) return "";
        try {
            double d = Double.parseDouble(value.replace(",", ""));
            return String.format("%.2f", d);
        } catch (NumberFormatException e) {
            return value.replace(",", ""); // fallback for partial input
        }
    }


    private List<String> generateValidNumericInputs() {
        List<String> inputs = new ArrayList<>();
        inputs.add(generateRandomDigits(1));
        inputs.add(generateRandomDigits(2));
        inputs.add(generateRandomDigits(4));
        inputs.add(generateRandomDigits(6));
        inputs.add("0." + generateRandomDigits(2));
        inputs.add(generateRandomDigits(2) + "." + generateRandomDigits(1));
        inputs.add("." + generateRandomDigits(1)); // e.g., .5
        return inputs;
    }

    private String generateRandomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public void verifyUserCanAddWillNumber() throws AutomationException, IOException, ParseException {
        WebElement willNumberField = driverUtil.getWebElement(String.format(INPUT_FIELD_BY_NAME, "willNumber"));
        String existingValue = willNumberField.getAttribute("value").trim();
        String newWillNumber = CommonUtil.getJsonPath("OC07Form").get("OC07Form.willNumber").toString();

        if (!existingValue.isEmpty()) {
            CommonSteps.logInfo("Existing Will Number found: '" + existingValue + "'. It will be cleared and updated.");
        } else {
            CommonSteps.logInfo("Will Number field is blank. Adding new value.");
        }

        willNumberField.clear();
        willNumberField.sendKeys(newWillNumber);

        String finalValue = willNumberField.getAttribute("value").trim();
        if (!finalValue.equals(newWillNumber)) {
            throw new AutomationException("❌ Will number not set correctly. Expected: " + newWillNumber + ", Found: " + finalValue);
        }

        CommonSteps.logInfo("✅ Will number field successfully updated to: " + finalValue);
    }

    public void entersClaimantNameXInTheClaimOfField() throws AutomationException, IOException, ParseException {
        String claimantNameX = CommonUtil.getJsonPath("OC07Form").get("OC07Form.claimantNameX").toString();
        WebElement claimOfField = driverUtil.getWebElement(INPUT_CLAIMOFFIELD);
        clearAndType(claimOfField, claimantNameX);
        waitUntilClaimantFieldIsUpdated(claimantNameX);

    }

    public void verifyClaimantFieldDisplaySameClaimantName() throws AutomationException, IOException, ParseException {
        String expectedClaimantNameX = CommonUtil.getJsonPath("OC07Form").get("OC07Form.claimantNameX").toString();
        WebElement claimantField = driverUtil.getWebElement(INPUT_CLAIMANTFIELD);
        String claimantFieldValue = claimantField.getAttribute("value").trim();

        if (!claimantFieldValue.equals(expectedClaimantNameX)) {
            throw new AutomationException("❌ Claimant field mismatch. Expected: " + expectedClaimantNameX + ", Found: " + claimantFieldValue);
        }
    }

    public void userClearsAndEntersClaimantNameYInTheClaimantField() throws AutomationException, IOException, ParseException {
        String claimantNameY = CommonUtil.getJsonPath("OC07Form").get("OC07Form.claimantNameY").toString();
        WebElement claimantField = driverUtil.getWebElement(INPUT_CLAIMANTFIELD);
        clearAndType(claimantField, claimantNameY);
        waitUntilClaimantFieldIsUpdated(claimantNameY);

    }


    public void verifyClaimOfFieldDisplaySameClaimantName() throws AutomationException, IOException, ParseException {
        String expectedClaimantNameY = CommonUtil.getJsonPath("OC07Form").get("OC07Form.claimantNameY").toString();
        WebElement claimOfField = driverUtil.getWebElement(INPUT_CLAIMOFFIELD);
        String claimOfFieldValue = claimOfField.getAttribute("value").trim();

        if (!claimOfFieldValue.equals(expectedClaimantNameY)) {
            throw new AutomationException("❌ Claim of field mismatch. Expected: " + expectedClaimantNameY + ", Found: " + claimOfFieldValue);
        }
    }
    private void clearAndType(WebElement field, String value) {
        field.click();
        field.clear();

        ((JavascriptExecutor) DriverFactory.drivers.get())
                .executeScript("arguments[0].value = '';", field);

        field.sendKeys(value);
        field.sendKeys(Keys.TAB);
        WebDriverUtil.waitForAWhile(2);
    }



    public void waitUntilClaimantFieldIsUpdated(String expected) throws AutomationException {
        WebElement field = driverUtil.getWebElement(INPUT_CLAIMANTFIELD);
        for (int i = 0; i < 5; i++) {
            String val = field.getAttribute("value").trim();
            if (val.equals(expected)) return;
            WebDriverUtil.waitForAWhile(1);
        }
        throw new AutomationException("❌ Claimant field mismatch after waiting. Expected: " + expected + ", Found: " + field.getAttribute("value").trim());
    }


    public void verifyFormPrintedInPDFForm(String fileName) throws AutomationException {
        boolean isFileFound = false;
        int counter = 0;
        File[] files = null;
        do {
            try {
                files = FileUtil.getAllFiles((System.getProperty(OS) == null || System.getProperty(OS).equals(WINDOWS)) ? System.getProperty("user.dir") + "\\downloads" : System.getProperty("user.dir").replace("\\", "/") + "/downloads");

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
                : System.getProperty("user.dir") + "/downloads/")
                + downloadedFileName;
        try {

            boolean isVerifiedFileNumber = verifyFieldsInPDF(pdfFilePath,
                    "ESTATE OF Sara Watt , DECEASED",
                    "To the Clerk of the Orphans’ Court Division:",
                    fileNumberForm,
                    "file number");
        if (!isVerifiedFileNumber) {
            throw new AutomationException("❌ Verification failed: One or more checks did not pass.");
        }

        CommonSteps.logInfo("✅ Verification of downloaded PDF is done successfully.");
    } catch (Exception e) {
        throw new AutomationException("❌ Verification failed: " + e.getMessage());
    }
}
    private static boolean verifyFieldsInPDF(String pdfFilePath, String beforeLine, String afterLine, String expectedValue, String fieldName)
            throws IOException, AutomationException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        String[] allLines = pdfText.split("\\r?\\n");

        // 🔍 Log Full PDF Content with Line Numbers
        CommonSteps.logInfo("🔍 Full PDF Content with Line Numbers:");
        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);
        }
        int startIndex = -1, endIndex = -1;

        for (int i = 0; i < allLines.length; i++) {
            String line = allLines[i].trim();
            if (line.contains(beforeLine.trim())) {
                startIndex = i;
            }
            if (line.contains(afterLine.trim()) && startIndex != -1) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1) {
            throw new AutomationException("❌ Before or after line not found for '" + fieldName + "'!");
        }

        StringBuilder extractedBlock = new StringBuilder();
        for (int i = startIndex + 1; i < endIndex; i++) {
            String line = allLines[i].trim();
            if (!line.isEmpty()) {
                extractedBlock.append(line).append(" ");
            }
        }

        String actual = extractedBlock.toString()
                .replaceFirst("(?i)^No\\.\\s*", "") // Remove leading 'No.' (case-insensitive)
                .replaceAll("\\s+", " ")
                .trim();
        String expected = expectedValue.replaceAll("\\s+", " ").trim();

        CommonSteps.logInfo("🔍 Comparing block -> Expected: '" + expected + "', Extracted: '" + actual + "'");

        if (!expected.equalsIgnoreCase(actual)) {
            throw new AutomationException("❌ Validation Failed: Block content does not match for '" + fieldName + "'");
        }

        CommonSteps.logInfo("✅ Validation Passed: '" + fieldName + "' block matches expected.");
        return true;
    }
}

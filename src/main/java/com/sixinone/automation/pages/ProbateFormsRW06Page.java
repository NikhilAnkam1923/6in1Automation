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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;

public class ProbateFormsRW06Page extends BasePage {
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
    private static final String HEADER_COUNTY_FIELD_XPATH = "//p[contains(text(),'%s')]";
    private static final String DATE_FIELD = "//input[@name='formItems[%s].signedDate']";
    private static final String LETTERS_ISSUED_TO_FIELD = "//input[@name='formItems[%s].letterIssuedTo']";
    private static final String CORPORATE_FIDUCIARY_SIGN_FIELD = "//div[@id='id_2_1']//p[contains(text(),'Name or Corporate Fiduciary (if applicable)')]/preceding-sibling::p//input";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String SAVE_BTN = "//button[text()='Save']";
    private static final String UNDERSIGNED_CORPORATE_NAME = "//tr//td//p[contains(text(),'The undersigned')]/ancestor::td/following-sibling::td//p//input";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String SIGN_OF_REPRESENTATIVE = "//input[contains(@name,'officerSignature') and not(@value='')]";
    private static final String CORPORATE_FIDUCIARY_ADDRESS = "//div[@id='id_2_1']//p[text()='Address']/preceding-sibling::p//textarea[text()='%s']";
    private static final String CORPORATE_FIDUCIARY_CITY_STATE_ZIP = "//div[@id='id_2_1']//p[text()='City, State, Zip']/preceding-sibling::p//span//input[@disabled and not(@value='')]";
    private static final String CORPORATE_FIDUCIARY_TELEPHONE = "//div[@id='id_2_1']//p[text()='City, State, Zip']/following-sibling::p//span/input[@disabled and @value='%s']";
    private static final String CORPORATE_FIDUCIARY_EMAIL = "//div[@id='id_2_1']//p[text()='Telephone']/following-sibling::p//span/input[@disabled and not(@value='')]";
    private static final String BENEFICIARY_SIGN_FIELD = "//div[@id='id_2_2']//p[text()='Signature']//span//input";
    private static final String SIGN_OF_BENEFICIARY = "//div[@id='id_2_2']//p[text()='Signature']//span//input[not(@value='')]";
    private static final String BENEFICIARY_ADDRESS = "//div[@id='id_2_2']//p[text()='Address']/preceding-sibling::p//textarea[text()='%s']";
    private static final String BENEFICIARY_CITY_STATE_ZIP = "//div[@id='id_2_2']//p[text()='City, State, Zip']/preceding-sibling::p//span//input[@disabled and not(@value='')]";
    private static final String BENEFICIARY_TELEPHONE = "//div[@id='id_2_2']//p[text()='City, State, Zip']/following-sibling::p//span/input[@disabled and @value='%s']";
    private static final String BENEFICIARY_EMAIL = "//div[@id='id_2_2']//p[text()='Telephone']/following-sibling::p//span/input[@disabled and not(@value='')]";

    static List<String> dateDataForm = Arrays.asList(
            "02/11/2025", "02/12/2025", "02/13/2025", "02/14/2025", "02/15/2025",
            "02/16/2025", "02/17/2025", "02/18/2025", "02/19/2025", "02/20/2025"
    );

    static List<String> reasonDataForm = Arrays.asList(
            "Medical Emergency", "Legal Requirement", "Financial Support", "Personal Request", "Work Requirement",
            "Travel Authorization", "Educational Purpose", "Banking Documentation", "Family Consent", "Government Processing"
    );

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
    static String CorporateFiduciary1Form;
    static String CorporateFiduciary2Form;
    static String CorporateFiduciary3Form;
    static String CorporateFiduciary4Form;
    static String CorporateFiduciary5Form;
    static String Beneficiary1Form;
    static String Beneficiary2Form;
    static String Beneficiary3Form;
    static String Beneficiary4Form;
    static String Beneficiary5Form;

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

    public void verifyCountyInHeader(String countyName) throws AutomationException {
        WebElement county = driverUtil.getWebElement(String.format(HEADER_COUNTY_FIELD_XPATH, countyName));

        if (county != null && county.getText().contains(countyName)) {
            CommonSteps.logInfo("County is auto-populated correctly: " + countyName);
        } else {
            throw new AutomationException("County is not auto-populated correctly. Expected: " + countyName);
        }
    }

    public void verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        verifyCountyInHeader(enteredDomicileCountry);
        verifyAutoPopulatedValue(enteredDisplayName);
        verifyAutoPopulatedValue(enteredAlsoKnownAs);
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.isEnabled()) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        WebDriverUtil.waitForAWhile(4);
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH,enteredDisplayName));
        verifyFieldIsNotEditable(String.format(RW_INPUT_FIELD_XPATH,enteredAlsoKnownAs));
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    public void userSelectsMultipleCorporateFiduciaryContacts() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        scrollToElementAndClick(CORPORATE_FIDUCIARY_SIGN_FIELD);

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        CorporateFiduciary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        CorporateFiduciary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
    }

    public void verifyCorporateFiduciaryNameIsReflectedOnEachForm() throws AutomationException {
        List<String> expectedNames = Arrays.asList(
                CorporateFiduciary1Form,
                CorporateFiduciary4Form,
                CorporateFiduciary5Form,
                CorporateFiduciary3Form,
                CorporateFiduciary2Form
        );

        List<WebElement> CorporateNameFields = driverUtil.getWebElements(UNDERSIGNED_CORPORATE_NAME);
        List<WebElement> CorporateFiduciarySignFields = driverUtil.getWebElements(CORPORATE_FIDUCIARY_SIGN_FIELD);

        for (int i = 0; i < expectedNames.size(); i++) {
            String expectedName = expectedNames.get(i).trim();
            String actualCorporateName = CorporateNameFields.get(i).getAttribute("value").trim();
            String actualCorporateFiduciarySign = CorporateFiduciarySignFields.get(i).getAttribute("value").trim();

            if (!actualCorporateName.equals(expectedName)) {
                throw new AutomationException("Corporate name is not reflected, Expected: " + expectedName + " ,Found: " + actualCorporateName);
            }

            if (!actualCorporateFiduciarySign.equals(expectedName)) {
                throw new AutomationException("Corporate Fiduciary Sign is not reflected, Expected: " + expectedName + " ,Found: " + actualCorporateFiduciarySign);
            }
        }
    }

    public void verifyFormIsRepeatedBasedOnTheNumberOfContactsSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                CorporateFiduciary1Form,
                CorporateFiduciary4Form,
                CorporateFiduciary5Form,
                CorporateFiduciary3Form,
                CorporateFiduciary2Form
        );

        System.out.println("Fiduciary Contact Details (First Form):");
        for (int row = 0; row < 5; row++) {
            System.out.println("Row " + (row + 1) + " -> Name: " + expectedNames.get(row));
        }

        List<WebElement> CorporateFiduciarySignFields = driverUtil.getWebElements(SIGN_OF_REPRESENTATIVE);

        if (CorporateFiduciarySignFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + CorporateFiduciarySignFields.size() + " forms");
        }
    }

    public void userResetsTheRWForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(CORPORATE_FIDUCIARY_SIGN_FIELD);

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
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Fiduciary contacts updated successfully.")));

        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(BENEFICIARY_SIGN_FIELD);

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
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiary contacts updated successfully.")));
    }

    public void verifyCorrectCorporateFiduciaryContactDetailsAreDisplayedOnEachForm() throws AutomationException, IOException, ParseException {
        List<Integer> corporateFiduciaryNum = Arrays.asList(3, 5, 2, 4, 1);

        for (int i = 0; i < corporateFiduciaryNum.size(); i++) {
            int fiduciaryIndex = corporateFiduciaryNum.get(i);
            String fiduciaryKey = "corporateFiduciary" + fiduciaryIndex;

            String expectedFirstName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".firstName").toString();
            String expectedMiddleName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".middleName").toString();
            String expectedLastName = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".lastName").toString();
            String expectedSuffix = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".suffix").toString();
            String expectedCity = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".city").toString();
            String expectedState = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".zip").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".addressLine1").toString();
            String expectedTelephone = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".workNumber").toString();
            String expectedEmail = CommonUtil.getJsonPath(fiduciaryKey).get(fiduciaryKey + ".emailId").toString();


            String expectedFullName = expectedFirstName + " " + expectedMiddleName + " " + expectedLastName + " " + expectedSuffix;
            String expectedCityStateZip = expectedCity + ", " + expectedState + " " + expectedZip;

            List<WebElement> nameFields = driverUtil.getWebElements(SIGN_OF_REPRESENTATIVE);
            String actualName = nameFields.get(i).getAttribute("value");

            if (!actualName.equals(expectedFullName)) {
                throw new AutomationException("Mismatch in Corporate Fiduciary Name" + ". Expected: " + expectedFullName + ", Found: " + actualName);
            }

            WebElement addressElement = driverUtil.getWebElement(String.format(CORPORATE_FIDUCIARY_ADDRESS, expectedAddressLine1));
            if (!addressElement.getText().equals(expectedAddressLine1)) {
                throw new AutomationException("Address mismatch for " + expectedFullName);
            }

            List<WebElement> cityStateZipElement = driverUtil.getWebElements(CORPORATE_FIDUCIARY_CITY_STATE_ZIP);
            String actualCityStateZip = cityStateZipElement.get(i).getAttribute("value");
            if (!actualCityStateZip.equals(expectedCityStateZip)) {
                throw new AutomationException("City, State, Zip not populated for " + expectedFullName);
            }


            WebElement telephoneElement = driverUtil.getWebElement(String.format(CORPORATE_FIDUCIARY_TELEPHONE, expectedTelephone));
            if (!telephoneElement.getAttribute("value").equals(expectedTelephone)) {
                throw new AutomationException("Telephone mismatch for " + expectedFullName);
            }


            List<WebElement> emailElement = driverUtil.getWebElements(CORPORATE_FIDUCIARY_EMAIL);
            String actualEmail = emailElement.get(i).getAttribute("value");
            if (!actualEmail.equals(expectedEmail)) {
                throw new AutomationException("Email not populated for " + expectedFullName);
            }

            System.out.println("\nExpected Fiduciary Contact details");
            System.out.println(expectedFullName);
            System.out.println(expectedAddressLine1);
            System.out.println(expectedCityStateZip);
            System.out.println(expectedTelephone);
            System.out.println(expectedEmail);

            System.out.println("\nActual Fiduciary Contact details");
            System.out.println(actualName);
            System.out.println(addressElement.getText());
            System.out.println(actualCityStateZip);
            System.out.println(telephoneElement.getAttribute("value"));
            System.out.println(actualEmail);
        }
    }

    public void verifyMultipleBeneficiaryContactsCanBeSelected() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        scrollToElementAndClick(BENEFICIARY_SIGN_FIELD);

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

        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
    }

    public void verifyFormIsRepeatedBasedOnTheNumberOfBeneficiaryContactsSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary5Form,
                Beneficiary3Form,
                Beneficiary2Form
        );

        List<WebElement> BeneficiarySignFields = driverUtil.getWebElements(SIGN_OF_BENEFICIARY);

        if (BeneficiarySignFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + BeneficiarySignFields.size() + " forms");
        }
    }

    public void verifyCorrectBeneficiaryContactDetailsAreDisplayedOnEachForm() throws IOException, ParseException, AutomationException {
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary5Form,
                Beneficiary3Form,
                Beneficiary2Form
        );

        System.out.println("Beneficiary Contact Details (First Form):");
        for (int row = 0; row < 5; row++) {
            System.out.println("Row " + (row + 1) + " -> Name: " + expectedNames.get(row));
        }

        List<Integer> beneficiaryNum = Arrays.asList(1, 5, 4, 2, 3);

        for (int i = 0; i < beneficiaryNum.size(); i++) {
            int beneficiaryIndex = beneficiaryNum.get(i);
            String beneficiaryKey = "beneficiary" + beneficiaryIndex;

            String expectedCity = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".city").toString();
            String expectedState = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".zip").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".addressLine1").toString();
            String expectedTelephone = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".workNumber").toString();
            String expectedEmail = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".emailId").toString();

            String expectedName = expectedNames.get(i).trim();
            String expectedCityStateZip = expectedCity + ", " + expectedState + " " + expectedZip;

            List<WebElement> nameFields = driverUtil.getWebElements(SIGN_OF_BENEFICIARY);
            String actualName = nameFields.get(i).getAttribute("value");

            if (!actualName.equals(expectedName)) {
                throw new AutomationException("Mismatch in Beneficiary Name" + ". Expected: " + expectedName + ", Found: " + actualName);
            }

            WebElement addressElement = driverUtil.getWebElement(String.format(BENEFICIARY_ADDRESS, expectedAddressLine1));
            if (!addressElement.getText().equals(expectedAddressLine1)) {
                throw new AutomationException("Address mismatch for " + expectedName);
            }

            List<WebElement> cityStateZipElement = driverUtil.getWebElements(BENEFICIARY_CITY_STATE_ZIP);
            String actualCityStateZip = cityStateZipElement.get(i).getAttribute("value");
            if (!actualCityStateZip.equals(expectedCityStateZip)) {
                throw new AutomationException("City, State, Zip not populated for " + expectedName);
            }

            WebElement telephoneElement = driverUtil.getWebElement(String.format(BENEFICIARY_TELEPHONE, expectedTelephone));
            if (!telephoneElement.getAttribute("value").equals(expectedTelephone)) {
                throw new AutomationException("Telephone mismatch for " + expectedName);
            }

            List<WebElement> emailElement = driverUtil.getWebElements(BENEFICIARY_EMAIL);
            String actualEmail = emailElement.get(i).getAttribute("value");
            if (!actualEmail.equals(expectedEmail)) {
                throw new AutomationException("Email not populated for " + expectedName);
            }

            System.out.println("\nExpected Beneficiary Contact details");
            System.out.println(expectedName);
            System.out.println(expectedAddressLine1);
            System.out.println(expectedCityStateZip);
            System.out.println(expectedTelephone);
            System.out.println(expectedEmail);

            System.out.println("\nActual Beneficiary Contact details");
            System.out.println(actualName);
            System.out.println(addressElement.getText());
            System.out.println(actualCityStateZip);
            System.out.println(telephoneElement.getAttribute("value"));
            System.out.println(actualEmail);
        }
    }

    private void fillFieldWithFieldActivation(String fieldLocator, String data) throws AutomationException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);

        field.sendKeys(Keys.SPACE);
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(data);

        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }


    public void userEntersDateAndReasonDetailsOnEachForm() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiary contacts updated successfully.")));

        Actions actions = new Actions(DriverFactory.drivers.get());

        for (int i = 0; i < 10; i++) {
            WebDriverUtil.waitForAWhile();
            WebElement dateField = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELD, i)));

            scrollToElementAndClick(String.format(DATE_FIELD, i));
            dateField.clear();
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(dateDataForm.get(i)), null);

            actions.moveToElement(dateField)
                    .click()
                    .keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();

            driverUtil.getWebElement("//body").click();

            String actualDate = DriverFactory.drivers.get().findElement(By.xpath(String.format(DATE_FIELD,i))).getAttribute("value");

            fillFieldWithFieldActivation(String.format(LETTERS_ISSUED_TO_FIELD,i),reasonDataForm.get(i));

            WebDriverUtil.waitForAWhile(2);
            String actualReason = DriverFactory.drivers.get().findElement(By.xpath(String.format(LETTERS_ISSUED_TO_FIELD,i))).getAttribute("value");


            if (!actualDate.equals(dateDataForm.get(i))) {
                throw new AutomationException("Date field did not accept the entered date correctly. Expected: " + dateDataForm.get(i) + ", Found: " + actualDate);
            }

            if (!actualReason.equals(reasonDataForm.get(i))) {
                throw new AutomationException("Reason field did not accept the entered text correctly. Expected: " + reasonDataForm.get(i) + ", Found: " + actualReason);
            }
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
            verifyNameOrCorporateName(pdfFilePath);
            //verifyCounty(pdfFilePath);
            //validateWitnessDetails(pdfFilePath);

        } catch (IOException e) {
            CommonSteps.logInfo("Error reading PDF: " + e.getMessage());
        }
    }

    public void verifyNameOrCorporateName(String pdfFilePath) throws IOException, AutomationException {
        String beforeLine = "Estate of Kris Warner , Deceased";
        String afterLine = "(Name or Corporate Name)";

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
                            names.add(cleanName(name));
                        }
                    } else {
                        names.add(cleanName(currentLine));
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
            expectedNames.put("First Witness", cleanName(CorporateFiduciary1Form));

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
                CommonSteps.logInfo("✅ Validation Passed: Print names match as expected.");
            } else {
                CommonSteps.logInfo("❌ Validation Failed: Print names do not match the expected values.");
            }
        } else {
            throw new AutomationException("❌ Before or after line not found!");
        }
    }

    // **Updated Helper Method to Clean Names Properly**
    private static String cleanName(String rawName) {
        if (rawName == null || rawName.trim().isEmpty()) return "";

        return rawName
                .replaceAll("(?i)\\b(The undersigned,  )\\b", "")
                .replaceAll("(?i)\\b(, in my capacity/relationship as)\\b", "")

                .replaceAll("[,\\.\\s]+$", "") // Remove trailing commas, dots, and extra spaces
                .trim(); // Trim spaces
    }
}


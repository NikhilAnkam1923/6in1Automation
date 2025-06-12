package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.*;

import static com.sixinone.automation.util.WebDriverUtil.waitForAWhile;

public class ProbateFormsUTAPage extends BasePage {
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
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String NAME_OF_COUNSEL_FIELD = "//p[@class='p2 ft7 position-relative']//input";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String SAVE_BTN = "//div[@class='modal-footer']//button[text()='Save']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    private static final String USE_DECEDENT_NAME_CHECKBOX = "//input[@name='useDecedentName']";
    private static final String SETTLOR_OR_TRUST_NAME = "//textarea[@name='trustSettlorName']";
    private static final String NAME_AND_ADDRESS_FIELD = "//div[@id='beneficiarySection']//p//textarea";
    private static final String SELECTED_CONTACT = "//div[@class='drag-names-list drop-box h-100']//div//div//span";
    private static final String ATTORNEY_DETAILS_XPATH = "//p[@class='p2 ft7 newstyle']//input";
    private static final String DATE_OF_NOTICE_FIELD = "//input[@name='data[0].noticeDate']";
    private static final String BENEFICIARY_NAMES_AT_BOTTOM = "//p[@class='p20 ft4']//span[contains(text(),' ')]";
    private static final String DATE_FIELD_BOTTOM = "//input[@name='data[%s].utaSignatureDate']";
    private static final String TRUSTEE_SELECTION_FIELD = "//td[@class='tr14 td79 td82 ']//div[@id='trusteeSection']//input";
    private static final String TRUSTEE_NAME_COLUMN = "//p[@class='ft3 data-name']//input";
    private static final String TRUSTEE_ADDRESS_COLUMN = "//p[@class='ft3 data-address']//input";
    private static final String TRUSTEE_TELEPHONE_COLUMN = "//p[@class='ft3 data-telephone']//input";
    private static final String VIEW_ATTACHMENT_BTN = "//span[contains(@class,'view-attachment')]";
    private static final String CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String TRUSTEE_NAME_COLUMN_ATTACHMENT = "//div[@class='modal-body']//tbody//td[position()='1']";
    private static final String TRUSTEE_ADDRESS_COLUMN_ATTACHMENT = "//div[@class='modal-body']//tbody//td[position()='2']";
    private static final String TRUSTEE_TELEPHONE_COLUMN_ATTACHMENT = "//div[@class='modal-body']//tbody//td[position()='3']";

    private final Map<String, String> estateInfo = new HashMap<>();

    JSONObject jsonData = CommonUtil.getFullJsonObject();

    private static final List<String> beneDetails = new ArrayList<>();
    private static final List<String> beneficiaryKeys = new ArrayList<>();
    private static final List<String> trusteeDetails = new ArrayList<>();
    private static final List<String> trusteeKeys = new ArrayList<>();
    private static final List<String> selectedTrusteeNameForm = new ArrayList<>();
    private static final List<String> selectedTrusteeAddressForm = new ArrayList<>();
    private static final List<String> selectedTrusteeTelephoneForm = new ArrayList<>();

    static String settlorOrTrustNameForm;
    static String Beneficiary1Form;
    static String Beneficiary2Form;
    static String Beneficiary3Form;
    static String Beneficiary4Form;
    static String Beneficiary5Form;
    static String beneficiary1NameAddressForm;
    static String beneficiary2NameAddressForm;
    static String beneficiary3NameAddressForm;
    static String beneficiary4NameAddressForm;
    static String beneficiary5NameAddressForm;
    static String dateOfNoticeForm;
    static String selectedAttorney;
    static String nameOfCounselForm;
    static String attorneyTelephoneForm;
    static String attorneyAddressLine1Form;
    static String attorneyAddressLine2Form;
    static String attorneyCityStateZipForm;
    static String beneDateAtBottom1Form;
    static String beneDateAtBottom2Form;
    static String beneDateAtBottom3Form;
    static String beneDateAtBottom4Form;
    static String beneDateAtBottom5Form;
    static String Trustee1Form;
    static String Trustee2Form;
    static String Trustee3Form;
    static String Trustee4Form;
    static String Trustee5Form;
    static String Trustee1NameForm;
    static String Trustee2NameForm;
    static String Trustee3NameForm;
    static String Trustee4NameForm;
    static String Trustee5NameForm;
    static String Trustee1AddressForm;
    static String Trustee2AddressForm;
    static String Trustee3AddressForm;
    static String Trustee4AddressForm;
    static String Trustee5AddressForm;
    static String Trustee1TelephoneForm;
    static String Trustee2TelephoneForm;
    static String Trustee3TelephoneForm;
    static String Trustee4TelephoneForm;
    static String Trustee5TelephoneForm;


    public ProbateFormsUTAPage() throws IOException, ParseException {
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

    private static String getFieldValue(WebElement field) throws AutomationException {
        if (field != null) {
            String value = field.getAttribute("value");
            return (value != null && !value.trim().isEmpty()) ? value.trim() : field.getText().trim();
        } else {
            throw new AutomationException("WebElement is null.");
        }
    }

    private static void switchToPage(int pageNumber) throws AutomationException {
        driverUtil.getWebElement(String.format(PAGE_NUMBER_DYNAMIC_XPATH, pageNumber)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
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

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    @Override
    String getName() {
        return "";
    }

    public void userSelectsTheCheckbox() {
        scrollToElement(USE_DECEDENT_NAME_CHECKBOX);
        DriverFactory.drivers.get().findElement(By.xpath(USE_DECEDENT_NAME_CHECKBOX)).click();
        WebDriverUtil.waitForAWhile();
    }

    public void verifyTheSettlorOfTheTrustWasFieldDisplayDecedentSName() throws AutomationException {
        String decedentFirstName = getEstateValue("FirstName");
        String decedentMiddleName = getEstateValue("MiddleName");
        String decedentLastName = getEstateValue("LastName");
        String decedentSuffix = getEstateValue("Suffix");

        String expectedDecedentName = decedentFirstName + " " + decedentMiddleName + " " + decedentLastName + " " + decedentSuffix;
        settlorOrTrustNameForm = getFieldValue(SETTLOR_OR_TRUST_NAME);

        if(!settlorOrTrustNameForm.equals(expectedDecedentName)){
            throw new AutomationException("The Settlor of the Trust was: field not displayed the correct decedent's name. Expected: " + expectedDecedentName + " ,Found: " + settlorOrTrustNameForm);
        }
    }

    public void userClickOnNameAndAddressField() throws AutomationException {
        scrollToElement(NAME_AND_ADDRESS_FIELD);
        driverUtil.getWebElement(NAME_AND_ADDRESS_FIELD).click();
    }

    public static String findBeneficiaryKeyByName(String fullName, JSONObject jsonData) {
        for (Object keyObj : jsonData.keySet()) {
            String key = keyObj.toString();
            if (key.startsWith("beneficiary")) {
                JSONObject beneData = (JSONObject) jsonData.get(key);

                String jsonFullName = beneData.getOrDefault("firstName", "") + " " +
                        beneData.getOrDefault("middleName", "") + " " +
                        beneData.getOrDefault("lastName", "") + ", " +
                        beneData.getOrDefault("suffix", "");

                jsonFullName = jsonFullName.trim().replaceAll(" +", " ");

                if (fullName.equalsIgnoreCase(jsonFullName)) {
                    return key;
                }
            }
        }
        return null;
    }

    public static String findTrusteeKeyByName(String fullName, JSONObject jsonData) {
        for (Object keyObj : jsonData.keySet()) {
            String key = keyObj.toString();
            if (key.startsWith("trustee")) {
                JSONObject trusteeData = (JSONObject) jsonData.get(key);

                String jsonFullName = trusteeData.getOrDefault("firstName", "") + " " +
                        trusteeData.getOrDefault("middleName", "") + " " +
                        trusteeData.getOrDefault("lastName", "") + ", " +
                        trusteeData.getOrDefault("suffix", "");

                jsonFullName = jsonFullName.trim().replaceAll(" +", " ");

                if (fullName.equalsIgnoreCase(jsonFullName)) {
                    return key;
                }
            }
        }
        return null;
    }

    public void userSelectsMultipleBeneficiariesFromSidebar() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        WebDriverUtil.waitForAWhile();
        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForAWhile();
        List<WebElement> beneNames = driverUtil.getWebElements(SELECTED_CONTACT);

        for (int i = 0; i < beneNames.size(); i++) {
            String name = beneNames.get(i).getText().trim();
            beneDetails.add(name);
            switch (i) {
                case 0:
                    Beneficiary1Form = name;
                    break;
                case 1:
                    Beneficiary2Form = name;
                    break;
                case 2:
                    Beneficiary3Form = name;
                    break;
                case 3:
                    Beneficiary4Form = name;
                    break;
                case 4:
                    Beneficiary5Form = name;
                    break;
            }
        }

        for (String detail : beneDetails) {
            String matchedKey = findBeneficiaryKeyByName(detail, jsonData);

            if (matchedKey != null) {
                beneficiaryKeys.add(matchedKey);
            } else {
                throw new AutomationException("Beneficiary key not found for full name: " + detail);
            }
        }

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiaries updated successfully.")));
    }

    public void verifyFormIsRepeatedDependingOnTheNumberOfBeneficiariesSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary2Form,
                Beneficiary3Form,
                Beneficiary4Form,
                Beneficiary5Form
        );

        List<WebElement> BeneficiaryNameAndAddressFields = driverUtil.getWebElements(NAME_AND_ADDRESS_FIELD);

        if (BeneficiaryNameAndAddressFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + BeneficiaryNameAndAddressFields.size() + " forms");
        }

        for (int i = 0; i < BeneficiaryNameAndAddressFields.size(); i++) {
            String actualNameAndAddress = BeneficiaryNameAndAddressFields.get(i).getAttribute("value");

            switch (i) {
                case 0:
                    beneficiary1NameAddressForm = actualNameAndAddress;
                    break;
                case 1:
                    beneficiary2NameAddressForm = actualNameAndAddress;
                    break;
                case 2:
                    beneficiary3NameAddressForm = actualNameAndAddress;
                    break;
                case 3:
                    beneficiary4NameAddressForm = actualNameAndAddress;
                    break;
                case 4:
                    beneficiary5NameAddressForm = actualNameAndAddress;
                    break;
            }
        }
    }

    public void verifySidebarAppearsAndAttorneyCanBeSelected() throws AutomationException, IOException, ParseException {
        String attorneyFirstName = CommonUtil.getJsonPath("attorney1").get("attorney1.firstName").toString();
        String attorneyLastName = CommonUtil.getJsonPath("attorney1").get("attorney1.lastName").toString();
        String attorneyMiddleName = CommonUtil.getJsonPath("attorney1").get("attorney1.middleName").toString();
        String attorneySuffix = CommonUtil.getJsonPath("attorney1").get("attorney1.suffix").toString();

        List<WebElement> nameOfCounselFields = driverUtil.getWebElements(NAME_OF_COUNSEL_FIELD);
        scrollToElement(nameOfCounselFields.get(0));
        nameOfCounselFields.get(0).click();

        WebElement modalHeader = driverUtil.getWebElement(MODAL_HEADER);

        if (!modalHeader.getText().contains("Attorney")) {
            throw new AutomationException("Sidebar is not displayed for attorney contact.");
        }

        selectedAttorney = attorneyFirstName + " " + attorneyMiddleName + " " + attorneyLastName + ", " + attorneySuffix;

        WebElement attorneyToSelect = driverUtil.getWebElement(String.format(CONTACT_RADIO_BTN_DYNAMIC_XPATH, selectedAttorney));

        WebDriverUtil.waitForAWhile();
        attorneyToSelect.click();

        if (!attorneyToSelect.isSelected()) {
            throw new AutomationException("Unable to select the attorney contact.");
        }

        CommonSteps.takeScreenshot();

        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Counsel updated successfully.")));
    }

    public void verifyCounselDetailsArePopulatedCorrectly() throws AutomationException, IOException, ParseException {
        String attorneyAddressLine1 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine1").toString();
        String attorneyAddressLine2 = CommonUtil.getJsonPath("attorney1").get("attorney1.addressLine2").toString();
        String attorneyCity = CommonUtil.getJsonPath("attorney1").get("attorney1.city").toString();
        String attorneySateCode = CommonUtil.getJsonPath("attorney1").get("attorney1.stateCode").toString();
        String attorneyZip = CommonUtil.getJsonPath("attorney1").get("attorney1.zip").toString();
        String attorneyTelephone = CommonUtil.getJsonPath("attorney1").get("attorney1.phoneNumber").toString();

        List<WebElement> nameOfCounselFields = driverUtil.getWebElements(NAME_OF_COUNSEL_FIELD);
        nameOfCounselForm = getFieldValue(nameOfCounselFields.get(0));
        if (!nameOfCounselForm.equals(selectedAttorney)) {
            throw new AutomationException("Attorney details not populated correctly in 'Name of Counsel' field. Expected: " + selectedAttorney + " ,Found: " + nameOfCounselForm);
        }

        String attorneyCitySateZip = attorneyCity + ", " + attorneySateCode + " " + attorneyZip;

        List<WebElement> attorneyDetailsFields = driverUtil.getWebElements(ATTORNEY_DETAILS_XPATH);
        for (int i = 0; i < 4; i++) {
            String value = getFieldValue(attorneyDetailsFields.get(i));
            switch (i) {
                case 0:
                    attorneyAddressLine1Form = value;
                    break;
                case 1:
                    attorneyAddressLine2Form =  value;
                    break;
                case 2:
                    attorneyCityStateZipForm = value;
                    break;
                case 3:
                    attorneyTelephoneForm = value;
                    break;
            }
        }

        if (!attorneyAddressLine1.equals(attorneyAddressLine1Form)) {
            throw new AutomationException("Attorney Address Line 1 not correctly fetched. Expected: " + attorneyAddressLine1 + ", Found: " + attorneyAddressLine1Form);
        }

        if (!attorneyAddressLine2.equals(attorneyAddressLine2Form)) {
            throw new AutomationException("Attorney Address Line 2 not correctly fetched. Expected: " + attorneyAddressLine2 + ", Found: " + attorneyAddressLine2Form);
        }

        if (!attorneyCitySateZip.equals(attorneyCityStateZipForm)) {
            throw new AutomationException("Attorney City, State code, Zip not correctly fetched. Expected: " + attorneyCitySateZip + ", Found: " + attorneyCityStateZipForm);
        }

        if (!attorneyTelephone.equals(attorneyTelephoneForm)) {
            throw new AutomationException("Attorney Telephone not correctly fetched. Expected: " + attorneyTelephone + ", Found: " + attorneyTelephoneForm);
        }
    }

    public void scrollAndFillField(String fieldLocator, String value) throws AutomationException {
        scrollToElement(fieldLocator);
        WebElement field = driverUtil.getWebElement(fieldLocator);

        int maxAttempts = 5;
        int attempt = 0;
        boolean isValueSet = false;

        while (attempt < maxAttempts) {
            field.click();
            field.clear();
            field.sendKeys(value);
            field.sendKeys(Keys.TAB);
            WebDriverUtil.waitForAWhile();

            String actualValue = field.getAttribute("value");
            if (value.equals(actualValue)) {
                isValueSet = true;
                break;
            }
            attempt++;
        }

        if (!isValueSet) {
            throw new AutomationException("Failed to set value '" + value + "' in field located by: " + fieldLocator);
        }
    }

    public void verifyTheDateOfNoticeFieldIsEditable() throws IOException, ParseException, AutomationException {
        String dateOfNotice = CommonUtil.getJsonPath("UTAForm").get("UTAForm.dateOfNotice").toString();
        scrollAndFillField(DATE_OF_NOTICE_FIELD, dateOfNotice);
        WebDriverUtil.waitForAWhile();
        dateOfNoticeForm = getFieldValue(DATE_OF_NOTICE_FIELD);
    }

    public void userResetsTheRWForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        scrollToElement(NAME_AND_ADDRESS_FIELD);
        driverUtil.getWebElement(NAME_AND_ADDRESS_FIELD).click();

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
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Beneficiaries updated successfully.")));

        userSelectsTheCheckbox();
    }

    private void verifyFieldIsEditable(String fieldName, String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (!field.isEnabled()) {
            throw new AutomationException(fieldName + " is not editable.");
        }
    }

    public void verifyBeneficiaryNameIsDisplayedAtTheBottomOfTheFormAlongWithAEditableDateFieldWhereUserCanEnterDate() throws AutomationException, IOException, ParseException {
        String beneDateAtBottom = CommonUtil.getJsonPath("UTAForm").get("UTAForm.beneDateAtBottom").toString();
        List<WebElement> beneNamesAtBottomFields = driverUtil.getWebElements(BENEFICIARY_NAMES_AT_BOTTOM);
        List<String> beneNamesAtBottom = new ArrayList<>();
        for (WebElement element : beneNamesAtBottomFields) {
            String actualName = getFieldValue(element);
            beneNamesAtBottom.add(actualName);
        }

        for (int i=0; i<beneDetails.size(); i++) {
            String selectedBene = beneDetails.get(i);
            String beneAtBottom = beneNamesAtBottom.get(i);

            if (!selectedBene.equals(beneAtBottom)) {
                throw new AutomationException("Beneficiary names mismatch on page " + (i+1) + " ,Expected: " + selectedBene + " ,Found: " + beneAtBottom);
            }
        }

        for (int i=0; i<beneNamesAtBottomFields.size(); i++) {
            WebElement beneField = driverUtil.getWebElement(String.format(DATE_FIELD_BOTTOM,i));
            if (beneField == null) {
                throw new AutomationException("Date field along with Beneficiary name at the bottom is not present on page: " + (i+1));
            }

            verifyFieldIsEditable("Bene Date Field on page " + (i+1),String.format(DATE_FIELD_BOTTOM,i));

            scrollAndFillField(String.format(DATE_FIELD_BOTTOM,i),beneDateAtBottom);
            WebDriverUtil.waitForAWhile();
            String actualDate = getFieldValue(String.format(DATE_FIELD_BOTTOM,i));

            switch (i) {
                case 0:
                    beneDateAtBottom1Form = actualDate;
                    break;
                case 1:
                    beneDateAtBottom2Form = actualDate;
                    break;
                case 2:
                    beneDateAtBottom3Form = actualDate;
                    break;
                case 3:
                    beneDateAtBottom4Form = actualDate;
                    break;
                case 4:
                    beneDateAtBottom5Form = actualDate;
                    break;
            }
        }
    }

    public void userSavesTrusteesInformation() throws AutomationException, IOException, ParseException {
        scrollToElement(TRUSTEE_SELECTION_FIELD);
        driverUtil.getWebElement(TRUSTEE_SELECTION_FIELD).click();
        WebDriverUtil.waitForAWhile();

        List<WebElement> trusteeNames = driverUtil.getWebElements(SELECTED_CONTACT);

        for (int i = 0; i < trusteeNames.size(); i++) {
            String name = trusteeNames.get(i).getText().trim();
            trusteeDetails.add(name);
            switch (i) {
                case 0:
                    Trustee1Form = name;
                    break;
                case 1:
                    Trustee2Form = name;
                    break;
                case 2:
                    Trustee3Form = name;
                    break;
                case 3:
                    Trustee4Form = name;
                    break;
                case 4:
                    Trustee5Form = name;
                    break;
            }
        }

        for (String detail : trusteeDetails) {
            String matchedKey = findTrusteeKeyByName(detail, jsonData);

            if (matchedKey != null) {
                trusteeKeys.add(matchedKey);
            } else {
                throw new AutomationException("Trustee key not found for full name: " + detail);
            }
        }

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();

        for (int i = 0; i < trusteeDetails.size(); i++) {
            String trusteeKey = trusteeKeys.get(i);

            String expectedFirstName = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".firstName").toString();
            String expectedMiddleName = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".middleName").toString();
            String expectedLastName = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".lastName").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".addressLine1").toString();
            String expectedCity = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".city").toString();
            String expectedState = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".zip").toString();

            String Name = expectedFirstName + " " + expectedMiddleName + " " + expectedLastName;
            String Address = expectedAddressLine1 + ", " + expectedCity + ", " + expectedState + " " + expectedZip;
            String Telephone = CommonUtil.getJsonPath(trusteeKey).get(trusteeKey + ".phoneNumber").toString();

            selectedTrusteeNameForm.add(Name);
            selectedTrusteeAddressForm.add(Address);
            selectedTrusteeTelephoneForm.add(Telephone);
        }
    }

    public void verifySelectedTrusteesAreDisplayedInTheTable() throws AutomationException, IOException, ParseException {
        List<WebElement> trusteeNameColumn = driverUtil.getWebElements(TRUSTEE_NAME_COLUMN);
        List<WebElement> trusteeAddressColumn = driverUtil.getWebElements(TRUSTEE_ADDRESS_COLUMN);
        List<WebElement> trusteeTelephoneColumn = driverUtil.getWebElements(TRUSTEE_TELEPHONE_COLUMN);

        for (int i=0; i<4; i++) {
            String expectedName = selectedTrusteeNameForm.get(i);
            String expectedAddress = selectedTrusteeAddressForm.get(i);
            String expectedTelephone = selectedTrusteeTelephoneForm.get(i);

            String actualName = getFieldValue(trusteeNameColumn.get(i));
            String actualAddress = getFieldValue(trusteeAddressColumn.get(i));
            String actualTelephone = getFieldValue(trusteeTelephoneColumn.get(i));

            if (!actualName.equals(expectedName)) {
                throw new AutomationException("Trustee Name is not displayed correctly at row " + (i+1) + ". Expected: " + expectedName + " ,Found: " + actualName);
            }

            if (!actualAddress.equals(expectedAddress)) {
                throw new AutomationException("Trustee Address is not displayed correctly at row " + (i+1) + ". Expected: " + expectedAddress + " ,Found: " + actualAddress);
            }

            if (!actualTelephone.equals(expectedTelephone)) {
                throw new AutomationException("Trustee Telephone is not displayed correctly at row " + (i+1) + ". Expected: " + expectedTelephone + " ,Found: " + actualTelephone);
            }

            switch (i) {
                case 0:
                    Trustee1NameForm = actualName;
                    Trustee1AddressForm = actualAddress;
                    Trustee1TelephoneForm = actualTelephone;
                    break;
                case 1:
                    Trustee2NameForm = actualName;
                    Trustee2AddressForm = actualAddress;
                    Trustee2TelephoneForm = actualTelephone;
                    break;
                case 2:
                    Trustee3NameForm = actualName;
                    Trustee3AddressForm = actualAddress;
                    Trustee3TelephoneForm = actualTelephone;
                    break;
                case 3:
                    Trustee4NameForm = actualName;
                    Trustee4AddressForm = actualAddress;
                    Trustee4TelephoneForm = actualTelephone;
                    break;

            }
        }
    }

    public void verifyIfMoreThanTrusteesAreSelectedThenRestAreDisplayedInAttachment() throws AutomationException {
        if (trusteeDetails.size() <= 4) {
            CommonSteps.logInfo("There are no additional trustees to verify in the attachment.");
        } else {
            scrollToElement(VIEW_ATTACHMENT_BTN);
            driverUtil.getWebElement(VIEW_ATTACHMENT_BTN).click();
            waitForAWhile();
            List<WebElement> trusteeNameColumnAttachment = driverUtil.getWebElements(TRUSTEE_NAME_COLUMN_ATTACHMENT);
            List<WebElement> trusteeAddressColumnAttachment = driverUtil.getWebElements(TRUSTEE_ADDRESS_COLUMN_ATTACHMENT);
            List<WebElement> trusteeTelephoneColumnAttachment = driverUtil.getWebElements(TRUSTEE_TELEPHONE_COLUMN_ATTACHMENT);

            for (int i = 0; i < trusteeNameColumnAttachment.size(); i++) {
                int index = i + 4;
                String expectedName = selectedTrusteeNameForm.get(index);
                String expectedAddress = selectedTrusteeAddressForm.get(index);
                String expectedTelephone = selectedTrusteeTelephoneForm.get(index);

                String actualName = getFieldValue(trusteeNameColumnAttachment.get(i));
                String actualAddress = getFieldValue(trusteeAddressColumnAttachment.get(i));
                String actualTelephone = getFieldValue(trusteeTelephoneColumnAttachment.get(i));

                if (!actualName.equals(expectedName)) {
                    throw new AutomationException("Trustee Name is not displayed correctly on attachment at row " + (i+1) + ". Expected: " + expectedName + " ,Found: " + actualName);
                }

                if (!actualAddress.equals(expectedAddress)) {
                    throw new AutomationException("Trustee Address is not displayed correctly on attachment at row " + (i+1) + ". Expected: " + expectedAddress + " ,Found: " + actualAddress);
                }

                if (!actualTelephone.equals(expectedTelephone)) {
                    throw new AutomationException("Trustee Telephone is not displayed correctly on attachment at row " + (i+1) + ". Expected: " + expectedTelephone + " ,Found: " + actualTelephone);
                }

                Trustee5NameForm = actualName;
                Trustee5AddressForm = actualAddress;
                Trustee5TelephoneForm = actualTelephone;
            }

            CommonSteps.takeScreenshot();
            driverUtil.getWebElement(CLOSE_BTN).click();
        }
    }
}

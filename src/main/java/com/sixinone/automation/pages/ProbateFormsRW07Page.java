package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ProbateFormsRW07Page extends BasePage{
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
    private static final String USE_4_DIGIT_YEAR_CHECKBOX = "//span[contains(text(),'Use 4 digit year')]/preceding-sibling::span//input[@type='checkbox' and @value='2']";
    private static final String FILE_NUMBER_FIELD = "//p[text()='File Number:']//span//input[@readonly]";
    private static final String BENE_ADDRESS_FIELD = "//td[@colspan='2']//textarea";
    private static final String DRAG_CONTACT_XPATH = "//div[@class='drag-names-list']//div[@aria-roledescription='sortable']";
    private static final String DROP_CONTACT_FIELD_XPATH = "//div[@class='right-droppable']//div[@class='drag-names-list drop-box h-100']";
    private static final String SAVE_BTN = "//button[text()='Save']";
    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    private static final String DECEDENT_DIED_DATE = "//td[@class='tr4']/span[@class='newstyle'][input[@class='noneditable prn_bords bold']]/input";
    private static final String DECEDENT_DIED_COUNTY = "//td[@class='tr4']/span[@class='newstyle'][input[@class='noneditable center-text prn_bords bold']] /input";
    private static final String NAME_COLUMN = "//table[@class='t2 tbl_fiduciary']//tr[td[@class='tr14 td79']][1]/td[@class='tr14 td79']/p/input";
    private static final String TABLE_FORM = "//table[@class='t2 tbl_fiduciary']";
    private static final String TABLE_NAME_COLUMN = "//table[@class='t2 tbl_fiduciary']//td[@class='tr14 td79']/p/input";
    private static final String TABLE_ADDRESS_COLUMN = "//table[@class='t2 tbl_fiduciary']//td[@class='tr14 td80']/p/input";
    private static final String TABLE_TELEPHONE_COLUMN = "//table[@class='t2 tbl_fiduciary']//td[@class='tr14 td81']/p/input";
    private static final String DATE_FIELD = "//p[contains(text(),'Date')]//input[@name='formItems[0].date']";
    private static final String REGISTRARS_ADDRESS_FIELD = "//textarea[@name='formItems[0].countyAddress']";
    private static final String CORPORATE_FIDUCIARY_NAME_FIELD = "//input[@name='fullname']";
    private static final String CONTACT_RADIO_BTN_DYNAMIC_XPATH = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
    private static final String PERSON_NAME_FIELD = "//p[text()='Name of Person']/preceding-sibling::p//input";
    private static final String MODAL_HEADER = "//div[@class='modal-title h4']";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";

    private final Map<String, String> estateInfo = new HashMap<>();

    static String initialFileNumberForm;
    static String fourDigitFileNumberForm;
    static String Beneficiary1Form;
    static String Beneficiary2Form;
    static String Beneficiary3Form;
    static String Beneficiary4Form;
    static String Beneficiary5Form;
    static String Fiduciary1Form;
    static String Fiduciary2Form;
    static String Fiduciary3Form;
    static String Fiduciary4Form;
    static String Fiduciary5Form;
    static String enteredDateForm;
    static String beneficiary1NameAddressForm;
    static String beneficiary2NameAddressForm;
    static String beneficiary3NameAddressForm;
    static String beneficiary4NameAddressForm;
    static String beneficiary5NameAddressForm;
    static String domicileCountryForm;
    static String displayNameForm;
    static String alsoKnownAsForm;
    static String fileNumberForm;


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

    public void verifyAutoPopulatedValue(String expectedValue) throws AutomationException {
        WebElement field = driverUtil.getWebElement(String.format(RW_INPUT_FIELD_XPATH, expectedValue));

        if (field != null && field.getAttribute("value").equals(expectedValue)) {
            CommonSteps.logInfo("Value is auto-populated correctly: " + expectedValue);
        } else {
            throw new AutomationException("Value is not auto-populated correctly. Expected: " + expectedValue);
        }
    }

    public void verifyCountyEstateFileNumberAkaNamesAreAutoPopulatedOnTheForm() throws AutomationException {
        domicileCountryForm = getEstateValue("DomicileCountry");
        displayNameForm = getEstateValue("DisplayName");
        alsoKnownAsForm = getEstateValue("AlsoKnownAs");
        fileNumberForm = getEstateValue("FileNumberPart1") + "-" + getEstateValue("FileNumberPart2") + "-" + getEstateValue("FileNumberPart3");

        verifyAutoPopulatedValue(domicileCountryForm);
        verifyAutoPopulatedValue(displayNameForm);
        verifyAutoPopulatedValue(alsoKnownAsForm);
        verifyAutoPopulatedValue(fileNumberForm);

        initialFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");
    }

    public void verifyFieldIsNotEditable(String fieldLocator) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        if (field.isEnabled() && field.getAttribute("disabled")==null && field.getAttribute("readonly")==null) {
            throw new AutomationException("Field is editable");
        }
    }

    public void verifyAutoPopulatedFieldsAreNotEditable() throws AutomationException {
        String domicileCountryField = String.format(RW_INPUT_FIELD_XPATH, domicileCountryForm);
        String displayNameField = String.format(RW_INPUT_FIELD_XPATH, displayNameForm);
        String alsoKnownAsField = String.format(RW_INPUT_FIELD_XPATH, alsoKnownAsForm);

        WebDriverUtil.waitForAWhile(2);
        verifyFieldIsNotEditable(domicileCountryField);
        verifyFieldIsNotEditable(displayNameField);
        verifyFieldIsNotEditable(alsoKnownAsField);
    }

    public void userCheckTheUseDigitYearCheckbox() {
        DriverFactory.drivers.get().findElement(By.xpath(USE_4_DIGIT_YEAR_CHECKBOX)).click();
    }

    public void verify4DigitYearIsDisplayedInFileNumber() throws AutomationException {
        fourDigitFileNumberForm = driverUtil.getWebElement(FILE_NUMBER_FIELD).getAttribute("value");

        String expectedFourDigitFileNumber = initialFileNumberForm.replaceFirst("-(\\d{2})-", "-20$1-");

        if (!fourDigitFileNumberForm.equals(expectedFourDigitFileNumber)) {
            throw new AutomationException("File number did not update correctly. Expected: " + expectedFourDigitFileNumber + ", Found: " + fourDigitFileNumberForm);
        }
    }

    public void userClickOnBeneAddressField() throws AutomationException {
        driverUtil.getWebElement(BENE_ADDRESS_FIELD).click();
    }


    public void verifyMultipleBeneficiariesCanBeSelected() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

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

        WebDriverUtil.waitForAWhile();
        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForAWhile(2);
        driverUtil.getWebElement(SAVE_BTN).click();

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "contacts updated successfully.")));
    }

    public void verifyFormIsRepeatedBasedOnTheNumberOfBeneficiariesSelected() throws AutomationException {
        WebDriverUtil.waitForAWhile(2);
        List<String> expectedNames = Arrays.asList(
                Beneficiary1Form,
                Beneficiary4Form,
                Beneficiary5Form,
                Beneficiary3Form,
                Beneficiary2Form
        );

        List<WebElement> BeneficiaryAddressFields = driverUtil.getWebElements(BENE_ADDRESS_FIELD);

        if (BeneficiaryAddressFields.size() != expectedNames.size()) {
            throw new AutomationException("Mismatch: Selected " + expectedNames.size() + " Contacts, but found " + BeneficiaryAddressFields.size()+" forms");
        }
    }

    public void verifyNameAndAddressOfTheBeneficiaryCorrectlyDisplayedOnEachForm() throws AutomationException, IOException, ParseException {
        List<WebElement> BeneficiaryAddressFields = driverUtil.getWebElements(BENE_ADDRESS_FIELD);
        List<Integer> beneficiaryNum = Arrays.asList(1, 4, 3, 5, 2);

        for (int i = 0; i < BeneficiaryAddressFields.size(); i++) {
            int beneficiaryIndex = beneficiaryNum.get(i);
            String beneficiaryKey = "beneficiary" + beneficiaryIndex;

            String expectedLastName = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".lastName").toString();
            String expectedMiddleName = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".middleName").toString();
            String expectedFirstName = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".firstName").toString();
            String expectedCity = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".city").toString() + ",";
            String expectedState = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".stateCode").toString();
            String expectedZip = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".zip").toString();
            String expectedAddressLine1 = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".addressLine1").toString() + ",";
            String expectedAddressLine2 = CommonUtil.getJsonPath(beneficiaryKey).get(beneficiaryKey + ".addressLine2").toString() + ",";

            String expectedName = expectedFirstName + " " + expectedMiddleName + " " + expectedLastName + " ";
            String expectedStateCodeZip = expectedState + " " + expectedZip;
            String expectedNameAndAddress = expectedName + "\n" + expectedAddressLine1 + " " + expectedAddressLine2 + "\n" + expectedCity + "\n" + expectedStateCodeZip;


            String actualNameAndAddress = BeneficiaryAddressFields.get(i).getAttribute("value");


            switch (beneficiaryIndex) {
                case 1:
                    beneficiary1NameAddressForm = actualNameAndAddress;
                    break;
                case 4:
                    beneficiary2NameAddressForm = actualNameAndAddress;
                    break;
                case 3:
                    beneficiary3NameAddressForm = actualNameAndAddress;
                    break;
                case 5:
                    beneficiary4NameAddressForm = actualNameAndAddress;
                    break;
                case 2:
                    beneficiary5NameAddressForm = actualNameAndAddress;
                    break;
            }


            if (!actualNameAndAddress.equals(expectedNameAndAddress)) {
                throw new AutomationException("Mismatch in Beneficiary Name and Address" +
                        ". Expected: \n" + expectedNameAndAddress + "\n, Found: \n" + actualNameAndAddress);
            }
        }
    }

    public void verifyDecedentDiedDateAndCountyIsAutoFetchedAndNonEditable() throws AutomationException {
        String expectedDomicileCountry = getEstateValue("DomicileCountry");
        String expectedDateOfDeath = getEstateValue("DateOfDeath");
        String actualDecedentDiedDate = driverUtil.getWebElement(DECEDENT_DIED_DATE).getAttribute("value");
        String actualDiedCounty = driverUtil.getWebElement(DECEDENT_DIED_COUNTY).getAttribute("value");

        if(!actualDecedentDiedDate.equals(expectedDateOfDeath)){
            throw new AutomationException("Decedent died date is not fetched correctly. Expected: "+ expectedDateOfDeath + ", found: "+ actualDecedentDiedDate);
        }
        verifyFieldIsNotEditable(DECEDENT_DIED_DATE);

        if(!actualDiedCounty.equals(expectedDomicileCountry)){
            throw new AutomationException("Decedent died county is not fetched correctly. Expected: "+ expectedDomicileCountry + ", found: "+ actualDiedCounty);
        }
        verifyFieldIsNotEditable(DECEDENT_DIED_COUNTY);
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    public void userClicksOnNameColumn() throws AutomationException {
        scrollToElementAndClick(NAME_COLUMN);
    }

    public void verifyMultipleFiduciaryContactsCanBeSelected() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebDriverUtil.waitForAWhile();

        WebElement dropHereSection = driverUtil.getWebElement(DROP_CONTACT_FIELD_XPATH);

        Fiduciary1Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary2Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary3Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary4Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();
        WebDriverUtil.waitForAWhile();
        Fiduciary5Form = driverUtil.getWebElement(DRAG_CONTACT_XPATH).getText();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT_XPATH), dropHereSection).perform();

        CommonSteps.takeScreenshot();

        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SAVE_BTN).click();
    }

    public void verifySelectedFiduciaryContactsAreCommonForAllTheForms() throws AutomationException {
        List<WebElement> forms = driverUtil.getWebElements(TABLE_FORM);

        if (forms.isEmpty() || forms.size() < 5) {
            throw new AutomationException("Expected 5 forms but found: " + forms.size());
        }

        List<String> referenceNames = new ArrayList<>();
        List<String> referenceAddresses = new ArrayList<>();
        List<String> referenceTelephones = new ArrayList<>();

        WebDriverUtil.waitForAWhile(3);
        List<WebElement> nameFields = driverUtil.getWebElements(TABLE_NAME_COLUMN);
        List<WebElement> addressFields = driverUtil.getWebElements(TABLE_ADDRESS_COLUMN);
        List<WebElement> telephoneFields = driverUtil.getWebElements(TABLE_TELEPHONE_COLUMN);

        for (int row = 0; row < 5; row++) {
            referenceNames.add(nameFields.get(row).getAttribute("value").trim());
            referenceAddresses.add(addressFields.get(row).getAttribute("value").trim());
            referenceTelephones.add(telephoneFields.get(row).getAttribute("value").trim());
        }


        for (int formIndex = 1; formIndex < forms.size(); formIndex++) {
            for (int row = 0; row < 5; row++) {
                int index = (formIndex * 5) + row;

                String actualName = nameFields.get(index).getAttribute("value").trim();
                String actualAddress = addressFields.get(index).getAttribute("value").trim();
                String actualTelephone = telephoneFields.get(index).getAttribute("value").trim();

                if (!actualName.equals(referenceNames.get(row))) {
                    throw new AutomationException("Mismatch in Name at Form " + (formIndex + 1) + ", Row " + (row + 1));
                }
                if (!actualAddress.equals(referenceAddresses.get(row))) {
                    throw new AutomationException("Mismatch in Address at Form " + (formIndex + 1) + ", Row " + (row + 1));
                }
                if (!actualTelephone.equals(referenceTelephones.get(row))) {
                    throw new AutomationException("Mismatch in Telephone at Form " + (formIndex + 1) + ", Row " + (row + 1));
                }
            }
        }
    }


    public void userResetsTheRWForm() throws AutomationException {
        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(BENE_ADDRESS_FIELD);

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
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "contacts updated successfully.")));

        scrollToElementAndClick(SHOW_AKA_CHECkBOX);
        WebDriverUtil.waitForAWhile();
    }

    public void verifyDateIsEnteredInCorrectFormat() throws AutomationException {
        String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        Pattern pattern = Pattern.compile(datePattern);
        WebElement dateField = driverUtil.getWebElement(DATE_FIELD);

        scrollToElementAndClick(DATE_FIELD);

        String value = "12/22/2024";
        for (char c : value.toCharArray()) {
            dateField.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();

        WebDriverUtil.waitForAWhile();
        enteredDateForm = driverUtil.getWebElement(DATE_FIELD).getAttribute("value");

        if (!pattern.matcher(enteredDateForm).matches()) {
            throw new AutomationException("Invalid date format: " + enteredDateForm + ". Expected format is MM/DD/YYYY.");
        }
    }

    public void verifyRegistrarsAddressIsAutoFetchedAndTheFieldIsEditable() throws IOException, ParseException, AutomationException {
        String expectedRegisterOfWills = CommonUtil.getJsonPath("RW07Form").get("RW07Form.RegisterOfWills").toString();
        String expectedStreet = CommonUtil.getJsonPath("RW07Form").get("RW07Form.street").toString();
        String expectedCity = CommonUtil.getJsonPath("RW07Form").get("RW07Form.city").toString();
        String expectedStateCode = CommonUtil.getJsonPath("RW07Form").get("RW07Form.stateCode").toString();
        String expectedZip = CommonUtil.getJsonPath("RW07Form").get("RW07Form.zip").toString();
        String expectedPhone = CommonUtil.getJsonPath("RW07Form").get("RW07Form.phone").toString();

        String expectedRegistrarsAddress = expectedRegisterOfWills+"\\n"+expectedStreet+"\\n"+expectedCity+", "+expectedStateCode+" "+expectedZip+"\\n"+expectedPhone;

        String actualRegistrarsAddress =  driverUtil.getWebElement(REGISTRARS_ADDRESS_FIELD).getText();

        if(!actualRegistrarsAddress.equals(expectedRegistrarsAddress)){
            throw new AutomationException("Registrars address not fetched correctly. Expected: \n"+expectedRegistrarsAddress+" \nFound: \n"+actualRegistrarsAddress);
        }

        if(!driverUtil.getWebElement(REGISTRARS_ADDRESS_FIELD).isEnabled()){
            throw new AutomationException("Registrars address field is not editable.");
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
    }

    public void verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected() throws AutomationException, IOException, ParseException {
        String fiduciaryFirstName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.firstName").toString();
        String fiduciaryLastName = CommonUtil.getJsonPath("fiduciary2").get("fiduciary2.lastName").toString() + ",";
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
        String attorneyLastName = CommonUtil.getJsonPath("attorney2").get("attorney2.lastName").toString() + ",";
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

    }

    public void verifySelectedContactsAreCleared() throws AutomationException {
        String corporateFiduciaryValue = driverUtil.getWebElement(CORPORATE_FIDUCIARY_NAME_FIELD).getAttribute("value").trim();
        String personNameValue = driverUtil.getWebElement(PERSON_NAME_FIELD).getAttribute("value").trim();

        if (!corporateFiduciaryValue.isEmpty()) {
            throw new AutomationException("Corporate Fiduciary selection is not cleared. Found value: " + corporateFiduciaryValue);
        }

        if (!personNameValue.isEmpty()) {
            throw new AutomationException("Fiduciary/Attorney selection is not cleared. Found value: " + personNameValue);
        }
    }
}

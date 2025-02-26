package com.sixinone.automation.pages;


import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.sixinone.automation.util.WebDriverUtil.*;

public class EstateCreationPage extends BasePage {

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String SSN_FIELD = "//input[@name='ssn']";
    private static final String PROCEED_BUTTON = "//button[@type='submit']";
    private static final String CREATE_NEW_ESTATE_BTN = "//button[contains(text(),'Create a new estate with the name')]";
    private static final String DECEDENT_FIRST_NAME = "//input[@name='decedentInfo.firstName']";
    private static final String DECEDENT_MIDDLE_NAME = "//input[@name='decedentInfo.middleName']";
    private static final String DECEDENT_LAST_NAME = "//input[@name='decedentInfo.lastName']";
    private static final String DECEDENT_DISPLAY_NAME = "//input[@name='decedentInfo.displayNameAs']";
    private static final String DECEDENT_ALSO_KNOWN_AS = "//textarea[@name='decedentInfo.alsoKnownAs']";
    private static final String DECEDENT_SSN = "//input[@name='decedentInfo.SSN']";
    private static final String DECEDENT_SUFFIX = "//div[text()='Basic Info']/following-sibling::div//input[@name='decedentInfo.displayNameAs'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'Suffix')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String NEXT_BTN = "//button[text()='Next']";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String DECEDENT_DETAILS_PAGE = "//button[text()='Decedent Info']";
    private static final String DOMICILE_MAX_CHAR_LIMIT_ERROR = "//div[text()='Last Address/Domicile']/following-sibling::div//input[contains(@name,'domicileAddress')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'You have exceeded the maximum character limit of 100')]";
    private static final String DOMICILE_SPECIAL_CHAR_ERROR = "//div[text()='Last Address/Domicile']/following-sibling::div//input[contains(@name,'domicileAddress')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Allowed special characters are , / and &')]";
    private static final String MUNICIPALITY_MAX_CHAR_ERROR = "//div[@class='invalid-feedback' and contains(text(),'You have exceeded the maximum character limit of 50')]";
    private static final String MUNICIPALITY_SPECIAL_CHAR_ERROR = "//div[@class='invalid-feedback' and contains(text(),'Allowed special characters is -')]";
    private static final String DOMICILE_ADDRESS_LINE1 = "//input[@name='domicileAddress.addressLine1']";
    private static final String DOMICILE_ADDRESS_LINE2 = "//input[@name='domicileAddress.addressLine2']";
    private static final String DOMICILE_ZIP = "//input[@name='domicileAddress.zip']";
    private static final String DOMICILE_CITY = "//input[@name='domicileAddress.city']";
    private static final String DOMICILE_STATE = "//div[text()='Last Address/Domicile']/following-sibling::div//input[@name='domicileAddress.city'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'State')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String DOMICILE_COUNTRY = "//input[@name='domicileAddress.county']";
    private static final String DOMICILE_MUNICIPALITY = "//input[@name='domicileAddress.municipality']";
    private static final String TOWNSHIP_RADIO = "//input[@name='domicileAddress.residenceType' and @value='1']";
    private static final String BOROUGH_RADIO = "//input[@name='domicileAddress.residenceType' and @value='2']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE1 = "//input[@name='placeOfDeath.addressLine1']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE2 = "//input[@name='placeOfDeath.addressLine2']";
    private static final String PLACE_OF_DEATH_ZIP = "//input[@name='placeOfDeath.zip']";
    private static final String PLACE_OF_DEATH_CITY = "//input[@name='placeOfDeath.city']";
    private static final String PLACE_OF_DEATH_STATE = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/label[text()='State']/following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_COUNTRY = "//input[@name='placeOfDeath.county']";
    private static final String POD_MAX_CHAR_LIMIT_ERROR = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/div/label[text()='Address Line 1']/following-sibling::div[@class='invalid-feedback' and contains(text(),'You have exceeded the maximum character limit of 100')]";
    private static final String POD_SPECIAL_CHAR_ERROR = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/div/label[text()='Address Line 1']/following-sibling::div[@class='invalid-feedback' and contains(text(),'Allowed special characters are , / and &')]";
    private static final String DATE_OF_BIRTH_FIELD = "//label[text()='Date of Birth']/following-sibling::div//div//input";
    private static final String DATE_OF_DEATH_FIELD = "//label[text()='Date of Death']/following-sibling::div//div//input";
    private static final String AGE_AT_DEATH_FIELD = "//input[@name='lifeDetails.ageAtDeath']";
    private static final String ALT_VAL_DATE_FIELD = "//label[text()='Alt Val Date']/following-sibling::div//div//input";
    private static final String SELECTED_MARITAL_STATUS = "//div[text()='Life Details']/following-sibling::div//input[@name='lifeDetails.ageAtDeath'] /ancestor::div[contains(@class, 'col-')]/following-sibling::div//label[contains(text(), 'Marital Status')] /following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String LAST_RESIDENCE_FIELD = "//input[@name='lifeDetails.lastResidence']";
    private static final String LAST_RESIDENCE_ERROR_MSG = "//input[@name='lifeDetails.lastResidence']/following-sibling::div[@class='invalid-feedback'  and (text()='You have exceeded the maximum character limit of 50'  or text()='Allowed special characters are , and &')]";
    private static final String DATEPICKER = "//div[@class='react-datepicker__month-container']";
    private static final String DATE_DIVORCED_DECREE = "//label[text()='Date Divorced Decree']/following-sibling::div";
    private static final String ESTATE_TAB = "//button[@role='tab' and text()='Estate']";
    private static final String DECEDENT_INFO_TAB = "//button[@role='tab' and text()='Decedent Info']";
    private static final String ESTATE_CHECKBOX_XPATH = "//input[contains(@aria-label,'%s')]";
    private static final String DATE_OF_WILL = "//label[text()='Date of Will']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_1 = "//label[text()='Codicil Date #1']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_2 = "//label[text()='Codicil Date #2']/following-sibling::div//div//input";
    private static final String CODICILE_DATE_3 = "//label[text()='Codicil Date #3']/following-sibling::div//div//input";
    private static final String PROBATE_COURT_NAME = "//input[@name='probateCourtName']";
    private static final String PROBATE_COURT_LOCATION = "//input[@name='probateCourtLocation']";
    private static final String FILE_NUMBER_PART_1 = "//input[@name='fileNumberPart1']";
    private static final String FILE_NUMBER_PART_2 = "//input[@name='fileNumberPart2']";
    private static final String FILE_NUMBER_PART_3 = "//input[@name='fileNumberPart3']";
    private static final String ADDRESS_RADIO_BTN_XPATH = "//div//label[text()='%s']/preceding-sibling::input[@name='defaultAddress']";
    private static final String ESTATE_ACTION_BTN = "//tbody//tr//td//a[text()='%s'] /ancestor::td/following-sibling::td[contains(@class,'action-column')]/div/button";
    private static final String ACTIONS_OPTION_XPATH = "//div[@role='menu' and @class='slideright dropdown-menu show']/a[@role='menuitem' and text()='%s']";
    private static final String ARCHIVE_DESCRIPTION = "//textarea[@name='description']";
    private static final String ARCHIVE_BUTTON = "//button[text()='Archive']";
    private static final String ARCHIVE_CONFIRMATIONBUTTON = "//h4[@class='text-start mb-0']//following-sibling::div[@class='footer-modal']//button[text()='Archive']";
    private static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    private static final String FILE_NUMBER_1_ERR = "//div[@class='invalid-feedback' and contains(text(),'County Code must be a number between 1 and 99.')]";
    private static final String FILE_NUMBER_2_ERR = "//div[@class='invalid-feedback' and contains(text(),'Year must be exactly 2 digits.')]";
    private static final String FILE_NUMBER_3_ERR = "//div[@class='invalid-feedback' and contains(text(),'File Number must be at least 4 digits.')]";
    private static final String ESTATE_BREADCRUMB = "//a[@class='breadcrumb-item' and @href='/law-firm/estate']";
    private static final String SSN_ERROR_MESSAGE = "//div[@class='invalid-feedback' and contains(text(), 'SSN')]";
    private static final String NAME_FILTER = "//th[@aria-label='Filter' and @aria-colindex='1']/div/div/span/input";

    static String ageAtDeath;
    static String decedentSSN;
    public static String displayName;

    @Override
    String getName() {
        return "";
    }

    private void fillField(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        field.clear();
        field.sendKeys(CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString());
    }

    private void fillField(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString())
                .build()
                .perform();
    }

    private void fillFieldWithRandom(String fieldLocator, String value, Actions actions) throws AutomationException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(value)
                .build()
                .perform();
    }

    public static void scrollPageToTop() throws AutomationException {
        WebElement body = DriverFactory.drivers.get().findElement(By.tagName("body"));
        body.click();
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
    }

    public void enterFirstAndLastNameAndSSN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));

        fillField(FIRST_NAME_FIELD, "EstateCreate.firstName");
        fillField(LAST_NAME_FIELD, "EstateCreate.lastName");
        fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
        decedentSSN = randomSSN;
    }

    public void clickOnProceedButton() throws AutomationException {
        driverUtil.getWebElement(PROCEED_BUTTON).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyCreateNewEstateButtonIsDisplayed() throws AutomationException {
        WebElement createEstateButton = driverUtil.getWebElement(CREATE_NEW_ESTATE_BTN);
        if (createEstateButton == null || !createEstateButton.isDisplayed()) {
            throw new AutomationException("'Create a new estate with the entered name' button is not displayed.");
        }
    }

    public void clickCreateNewEstateButton() throws AutomationException {
        driverUtil.getWebElement(CREATE_NEW_ESTATE_BTN).click();
    }

    public void selectSuffixOption() throws AutomationException, IOException, ParseException {
        String suffixValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.suffix").toString();
        WebElement suffixDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Suffix"));
        suffixDropdown.click();
        WebElement suffixOption = driverUtil.getWebElement(String.format(SELECT_OPTION, suffixValue));
        suffixOption.click();
    }

    public void fillDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        fillField(DECEDENT_MIDDLE_NAME, "EstateCreate.middleName");
        fillField(DECEDENT_DISPLAY_NAME, "EstateCreate.displayName");
        selectSuffixOption();
        fillField(DECEDENT_ALSO_KNOWN_AS, "EstateCreate.alsoKnownAs");
        displayName = getFieldValue(DECEDENT_DISPLAY_NAME, "value");
    }

    public void selectMaritalStatusOptionDivorced() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.maritalStatusDivorced").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
    }

    public void selectMaritalStatusOptionOthers() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.maritalStatusOthers").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
    }

    public void clickOnNextButton() throws AutomationException {
        driverUtil.getWebElement(NEXT_BTN).click();
    }

    public void verifyDecedentDetailsPageIsDisplayed() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        WebElement page = driverUtil.getWebElement(DECEDENT_DETAILS_PAGE);
        if (page == null || !page.isDisplayed()) {
            throw new AutomationException("The 'Decedent Details' page is not opened.");
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

    public static void verifyAutoFetchedFieldsOfDomicileAddress() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.city").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.county").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(DOMICILE_CITY, "value");
        String actualState = getFieldValue(DOMICILE_STATE, "text");
        String actualCounty = getFieldValue(DOMICILE_COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public static void verifyAutoFetchedFieldsOfPlaceOfDeathAddress() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODcity").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODstate").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODcountry").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(PLACE_OF_DEATH_CITY, "value");
        String actualState = getFieldValue(PLACE_OF_DEATH_STATE, "text");
        String actualCounty = getFieldValue(PLACE_OF_DEATH_COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public void fillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        clearField(DOMICILE_ADDRESS_LINE1);
        fillField(DOMICILE_ADDRESS_LINE1, "EstateCreate.addressLine1");
        clearField(DOMICILE_ADDRESS_LINE2);
        fillField(DOMICILE_ADDRESS_LINE2, "EstateCreate.addressLine2");
        clearField(DOMICILE_ZIP);
        fillField(DOMICILE_ZIP, "EstateCreate.zip");
        driverUtil.getWebElement(DOMICILE_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfDomicileAddress();
        clearField(DOMICILE_MUNICIPALITY);
        fillField(DOMICILE_MUNICIPALITY, "EstateCreate.municipality");
    }

    public void verifyDomicileAddressFieldValidations() throws AutomationException, IOException, ParseException {
        WebElement addressLine1 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE1);
        WebElement addressLine2 = driverUtil.getWebElement(DOMICILE_ADDRESS_LINE2);
        WebElement municipality = driverUtil.getWebElement(DOMICILE_MUNICIPALITY);
        clearField(DOMICILE_ADDRESS_LINE1);
        addressLine1.sendKeys("A".repeat(101));
        clearField(DOMICILE_ADDRESS_LINE2);
        addressLine2.sendKeys("B".repeat(101));
        addressLine2.sendKeys(Keys.TAB);
        WebElement errorMaxChar = driverUtil.getWebElement(DOMICILE_MAX_CHAR_LIMIT_ERROR);
        if (errorMaxChar == null || !errorMaxChar.isDisplayed()) {
            throw new AutomationException("Validation for exceeding max character limit is not displayed.");
        }

        clearField(DOMICILE_ADDRESS_LINE1);
        addressLine1.sendKeys("Invalid@#$%");
        clearField(DOMICILE_ADDRESS_LINE2);
        addressLine2.sendKeys("##@@Invalid");
        addressLine2.sendKeys(Keys.TAB);
        WebElement errorSpecialChar = driverUtil.getWebElement(DOMICILE_SPECIAL_CHAR_ERROR);
        if (errorSpecialChar == null || !errorSpecialChar.isDisplayed()) {
            throw new AutomationException("Validation for allowed special characters is not displayed.");
        }

        clearField(DOMICILE_MUNICIPALITY);
        municipality.sendKeys("x".repeat(51));
        municipality.sendKeys(Keys.TAB);
        WebElement municipalityErrorMaxChar = driverUtil.getWebElement(MUNICIPALITY_MAX_CHAR_ERROR);
        if (municipalityErrorMaxChar == null || !municipalityErrorMaxChar.isDisplayed()) {
            throw new AutomationException("Validation for exceeding max character limit is not displayed.");
        }

        clearField(DOMICILE_MUNICIPALITY);
        municipality.sendKeys("##@@Invalid");
        municipality.sendKeys(Keys.TAB);
        WebElement municipalityErrorSpecialChar = driverUtil.getWebElement(MUNICIPALITY_SPECIAL_CHAR_ERROR);
        if (municipalityErrorSpecialChar == null || !municipalityErrorSpecialChar.isDisplayed()) {
            throw new AutomationException("Validation for exceeding max character limit is not displayed.");
        }

        CommonSteps.takeScreenshot();
        clearField(DOMICILE_ADDRESS_LINE1);
        fillField(DOMICILE_ADDRESS_LINE1, "EstateCreate.addressLine1");
        clearField(DOMICILE_ADDRESS_LINE2);
        fillField(DOMICILE_ADDRESS_LINE2, "EstateCreate.addressLine2");
        clearField(DOMICILE_MUNICIPALITY);
        fillField(DOMICILE_MUNICIPALITY, "EstateCreate.municipality");
    }

    private void verifyRadioButtonSelection(WebElement selected, WebElement unselected) throws AutomationException {
        if (!selected.isSelected() || unselected.isSelected()) {
            throw new AutomationException("Radio button selection behavior is incorrect.");
        }
    }

    public void verifyTownshipBoroughRadioButtons() throws AutomationException {
        WebElement townshipRadio = driverUtil.getWebElement(TOWNSHIP_RADIO);
        WebElement boroughRadio = driverUtil.getWebElement(BOROUGH_RADIO);
        if (townshipRadio == null || boroughRadio == null) {
            throw new AutomationException("Township or Borough radio buttons are not found.");
        }
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();

        jsExecutor.executeScript("arguments[0].click();", boroughRadio);
        verifyRadioButtonSelection(boroughRadio, townshipRadio);
        jsExecutor.executeScript("arguments[0].click();", townshipRadio);
        verifyRadioButtonSelection(townshipRadio, boroughRadio);
    }

    public void fillPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        clearField(PLACE_OF_DEATH_ADDRESS_LINE1);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE1, "EstateCreate.PODaddressLine1");
        clearField(PLACE_OF_DEATH_ADDRESS_LINE2);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE2, "EstateCreate.PODaddressLine2");
        clearField(PLACE_OF_DEATH_ZIP);
        fillField(PLACE_OF_DEATH_ZIP, "EstateCreate.PODzip");
        driverUtil.getWebElement(PLACE_OF_DEATH_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfPlaceOfDeathAddress();
    }

    public void verifyPlaceOfDeathFieldValidations() throws AutomationException, IOException, ParseException {
        WebElement addressLine1 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE1);
        WebElement addressLine2 = driverUtil.getWebElement(PLACE_OF_DEATH_ADDRESS_LINE2);
        clearField(PLACE_OF_DEATH_ADDRESS_LINE1);
        addressLine1.sendKeys("A".repeat(101));
        clearField(PLACE_OF_DEATH_ADDRESS_LINE2);
        addressLine2.sendKeys("B".repeat(101));
        addressLine2.sendKeys(Keys.TAB);
        WebElement errorMaxChar = driverUtil.getWebElement(POD_MAX_CHAR_LIMIT_ERROR);
        if (errorMaxChar == null || !errorMaxChar.isDisplayed()) {
            throw new AutomationException("Validation for exceeding max character limit is not displayed.");
        }

        clearField(PLACE_OF_DEATH_ADDRESS_LINE1);
        addressLine1.sendKeys("Invalid@#$%");
        clearField(PLACE_OF_DEATH_ADDRESS_LINE2);
        addressLine2.sendKeys("##@@Invalid");
        addressLine2.sendKeys(Keys.TAB);
        WebElement errorSpecialChar = driverUtil.getWebElement(POD_SPECIAL_CHAR_ERROR);
        if (errorSpecialChar == null || !errorSpecialChar.isDisplayed()) {
            throw new AutomationException("Validation for allowed special characters is not displayed.");
        }
        CommonSteps.logInfo("Verified Validations for all fields under Place of Death.");
        CommonSteps.takeScreenshot();
        clearField(PLACE_OF_DEATH_ADDRESS_LINE1);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE1, "EstateCreate.PODaddressLine1");
        clearField(PLACE_OF_DEATH_ADDRESS_LINE2);
        fillField(PLACE_OF_DEATH_ADDRESS_LINE2, "EstateCreate.PODaddressLine2");
    }

    public void clickOnEstateTab() throws AutomationException {
        scrollPageToTop();
        driverUtil.getWebElement(ESTATE_TAB).click();
    }

    public void selectCheckBox(String checkboxName) throws AutomationException {
        WebElement checkbox = driverUtil.getWebElement(String.format(ESTATE_CHECKBOX_XPATH, checkboxName));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void selectDefaultAddressRadioButton(String checkboxName) throws AutomationException {
        driverUtil.getWebElement(String.format(ADDRESS_RADIO_BTN_XPATH, checkboxName)).click();
    }

    public void verifyLastResidenceFieldValidationErrors() throws AutomationException {
        waitForVisibleElement(By.xpath(LAST_RESIDENCE_ERROR_MSG));
        if (!driverUtil.getWebElement(LAST_RESIDENCE_ERROR_MSG).isDisplayed()) {
            throw new AutomationException("The Last Residence field not displays the respective validation error messages for invalid inputs.");
        }
        CommonSteps.logInfo("The Last Residence field displays the respective validation error messages for invalid inputs.");
        CommonSteps.takeScreenshot();
    }

    public void fillEstateDetails() throws AutomationException, IOException, ParseException {
        selectCheckBox("Testate");
        clearField(DATE_OF_WILL);
        fillField(DATE_OF_WILL, "EstateCreate.dateOfWill");

        clearField(PROBATE_COURT_NAME);
        fillField(PROBATE_COURT_NAME, "EstateCreate.probateCourtName");
        clearField(PROBATE_COURT_LOCATION);
        fillField(PROBATE_COURT_LOCATION, "EstateCreate.probateCourtLocation");
        clearField(FILE_NUMBER_PART_1);
        fillField(FILE_NUMBER_PART_1, "EstateCreate.fileNumberPart1");
        clearField(FILE_NUMBER_PART_2);
        fillField(FILE_NUMBER_PART_2, "EstateCreate.fileNumberPart2");
        clearField(FILE_NUMBER_PART_3);
        fillField(FILE_NUMBER_PART_3, "EstateCreate.fileNumberPart3");
        selectDefaultAddressRadioButton("Accountant");

    }

    public void clickOnDecedentInfoTab() throws AutomationException {
        driverUtil.getWebElementAndScroll(DECEDENT_INFO_TAB).click();
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public static void verifyDecedentInfoRetainedTheEnteredValue() throws IOException, ParseException, AutomationException {
        String expectedFirstName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.firstName").toString();
        String expectedMiddleName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.middleName").toString();
        String expectedLastName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.lastName").toString();
        String expectedDisplayName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.displayName").toString();
        String expectedAlsoKnownAs = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.alsoKnownAs").toString();
        String expectedSuffix = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.suffix").toString();
        String expectedAddressLine1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.addressLine1").toString();
        String expectedAddressLine2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.addressLine2").toString();
        String expectedZip = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.zip").toString();
        String expectedMunicipality = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.municipality").toString();
        String expectedPODAddressLine1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODaddressLine1").toString();
        String expectedPODAddressLine2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODaddressLine2").toString();
        String expectedPODZip = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.PODzip").toString();
        String expectedLastResidence = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.lastResidence").toString();
        String expectedDateOfBirth = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.dateOfBirth").toString();
        String expectedDateOfDeath = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.dateOfDeath").toString();
        String expectedMaritalStatus = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.maritalStatusOthers").toString();

        WebDriverUtil.waitForAWhile(1);
        String actualFirstName = getFieldValue(DECEDENT_FIRST_NAME, "value");
        String actualMiddleName = getFieldValue(DECEDENT_MIDDLE_NAME, "value");
        String actualLastName = getFieldValue(DECEDENT_LAST_NAME, "value");
        String actualDisplayName = getFieldValue(DECEDENT_DISPLAY_NAME, "value");
        String actualAlsoKnownAs = getFieldValue(DECEDENT_ALSO_KNOWN_AS, "value");
        String actualSSN = getFieldValue(DECEDENT_SSN, "value");
        String actualSuffix = getFieldValue(DECEDENT_SUFFIX, "text");

        String actualAddressLine1 = getFieldValue(DOMICILE_ADDRESS_LINE1, "value");
        String actualAddressLine2 = getFieldValue(DOMICILE_ADDRESS_LINE2, "value");
        String actualZip = getFieldValue(DOMICILE_ZIP, "value");
        String actualMunicipality = getFieldValue(DOMICILE_MUNICIPALITY, "value");
        String actualPODAddressLine1 = getFieldValue(PLACE_OF_DEATH_ADDRESS_LINE1, "value");
        String actualPODAddressLine2 = getFieldValue(PLACE_OF_DEATH_ADDRESS_LINE2, "value");
        String actualPODZip = getFieldValue(PLACE_OF_DEATH_ZIP, "value");


        String actualLastResidence = getFieldValue(LAST_RESIDENCE_FIELD, "value");
        String actualDateOfBirth = getFieldValue(DATE_OF_BIRTH_FIELD, "value");
        String actualDateOfDeath = getFieldValue(DATE_OF_DEATH_FIELD, "value");
        String actualAgeAtDeath = getFieldValue(AGE_AT_DEATH_FIELD, "value");
        String actualMaritalStatus = getFieldValue(SELECTED_MARITAL_STATUS, "text");

        verifyField("First Name", expectedFirstName, actualFirstName);
        verifyField("Middle Name", expectedMiddleName, actualMiddleName);
        verifyField("Last Name", expectedLastName, actualLastName);
        verifyField("Display Name", expectedDisplayName, actualDisplayName);
        verifyField("Also Known As", expectedAlsoKnownAs, actualAlsoKnownAs);
        verifyField("SSN", decedentSSN, actualSSN);
        verifyField("Suffix", expectedSuffix, actualSuffix);
        verifyField("Address Line 1", expectedAddressLine1, actualAddressLine1);
        verifyField("Address Line 2", expectedAddressLine2, actualAddressLine2);
        verifyField("Zip", expectedZip, actualZip);
        verifyAutoFetchedFieldsOfDomicileAddress();
        verifyField("Municipality", expectedMunicipality, actualMunicipality);
        verifyField("POD Address Line 1", expectedPODAddressLine1, actualPODAddressLine1);
        verifyField("POD Address Line 2", expectedPODAddressLine2, actualPODAddressLine2);
        verifyField("POD Zip", expectedPODZip, actualPODZip);
        verifyAutoFetchedFieldsOfPlaceOfDeathAddress();
        verifyField("Last Residence", expectedLastResidence, actualLastResidence);
        verifyField("Date of Birth", expectedDateOfBirth, actualDateOfBirth);
        verifyField("Date of Death", expectedDateOfDeath, actualDateOfDeath);
        verifyField("Age at Death", ageAtDeath, actualAgeAtDeath);
        verifyField("Marital Status", expectedMaritalStatus, actualMaritalStatus);
    }


    public static void verifyEstateRetainedTheEnteredValue() throws IOException, ParseException, AutomationException {
        String expectedDateOfWill = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.dateOfWill").toString();
        String expectedCodicilDate1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.codicilDate1").toString();
        String expectedCodicilDate2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.codicilDate2").toString();
        String expectedCodicilDate3 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.codicilDate3").toString();
        String expectedProbateCourtName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.probateCourtName").toString();
        String expectedProbateCourtLocation = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.probateCourtLocation").toString();
        String expectedFileNumberPart1 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart1").toString();
        String expectedFileNumberPart2 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart2").toString();
        String expectedFileNumberPart3 = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.fileNumberPart3").toString();

        WebDriverUtil.waitForAWhile(1);

        String actualDateOfWill = getFieldValue(DATE_OF_WILL, "value");
        String actualCodicilDate1 = getFieldValue(CODICILE_DATE_1, "value");
        String actualCodicilDate2 = getFieldValue(CODICILE_DATE_2, "value");
        String actualCodicilDate3 = getFieldValue(CODICILE_DATE_3, "value");
        String actualProbateCourtName = getFieldValue(PROBATE_COURT_NAME, "value");
        String actualProbateCourtLocation = getFieldValue(PROBATE_COURT_LOCATION, "value");
        String actualFileNumberPart1 = getFieldValue(FILE_NUMBER_PART_1, "value");
        String actualFileNumberPart2 = getFieldValue(FILE_NUMBER_PART_2, "value");
        String actualFileNumberPart3 = getFieldValue(FILE_NUMBER_PART_3, "value");
        verifyField("Date of Will", expectedDateOfWill, actualDateOfWill);
        verifyField("Codicil Date 1", expectedCodicilDate1, actualCodicilDate1);
        verifyField("Codicil Date 2", expectedCodicilDate2, actualCodicilDate2);
        verifyField("Codicil Date 3", expectedCodicilDate3, actualCodicilDate3);
        verifyField("Probate Court Name", expectedProbateCourtName, actualProbateCourtName);
        verifyField("Probate Court Location", expectedProbateCourtLocation, actualProbateCourtLocation);
        verifyField("File Number Part 1", expectedFileNumberPart1, actualFileNumberPart1);
        verifyField("File Number Part 2", expectedFileNumberPart2, actualFileNumberPart2);
        verifyField("File Number Part 3", expectedFileNumberPart3, actualFileNumberPart3);
    }

    public void filterByEstateName(String estateName) throws AutomationException {
        driverUtil.getWebElement(NAME_FILTER).click();
        driverUtil.getWebElement(NAME_FILTER).sendKeys(estateName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickOnActionsMenu() throws AutomationException {
        driverUtil.getWebElement(String.format(ESTATE_ACTION_BTN, displayName)).click();
    }

    public void selectActionsOption(String actionsOption) throws AutomationException {
        driverUtil.getWebElement(String.format(ACTIONS_OPTION_XPATH, actionsOption)).click();
    }

    public void selectReasonForArchive(String reason) throws AutomationException, IOException, ParseException {
        WebElement reasonForArchiveDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Select Reason"));
        reasonForArchiveDropdown.click();
        WebElement reasonForArchive = driverUtil.getWebElement(String.format(SELECT_OPTION, reason));
        reasonForArchive.click();
    }

    public void enterArchiveDescription() throws AutomationException {
        driverUtil.getWebElement(ARCHIVE_DESCRIPTION).sendKeys("Automation Testing");
    }

    public void clickOnArchiveBtn() throws AutomationException {
        waitForVisibleElement(By.xpath(ARCHIVE_BUTTON));
        driverUtil.getWebElement(ARCHIVE_BUTTON).click();
        waitForVisibleElement(By.xpath(ARCHIVE_CONFIRMATIONBUTTON));
        driverUtil.getWebElement(ARCHIVE_CONFIRMATIONBUTTON).click();
    }

    public void verifyEstateArchivedSuccessfully() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Estate successfully archived."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Estate successfully archived.")));
    }

    public void verifyFileNumberFieldValidations() throws AutomationException, IOException, ParseException {
        WebElement fileNumberPart1 = driverUtil.getWebElement(FILE_NUMBER_PART_1);
        WebElement fileNumberPart2 = driverUtil.getWebElement(FILE_NUMBER_PART_2);
        WebElement fileNumberPart3 = driverUtil.getWebElement(FILE_NUMBER_PART_3);

        clearField(FILE_NUMBER_PART_1);
        fileNumberPart1.sendKeys("0");
        fileNumberPart1.sendKeys(Keys.TAB);
        WebElement fileNumberPart1Error = driverUtil.getWebElement(FILE_NUMBER_1_ERR);
        if (fileNumberPart1Error == null || !fileNumberPart1Error.isDisplayed()) {
            throw new AutomationException("Validation for File Number Part 1 is not displayed correctly.");
        }
        clearField(FILE_NUMBER_PART_1);
        fillField(FILE_NUMBER_PART_1, "EstateCreate.fileNumberPart1");

        clearField(FILE_NUMBER_PART_2);
        fileNumberPart2.sendKeys("123");
        fileNumberPart2.sendKeys(Keys.TAB);
        WebElement fileNumberPart2Error = driverUtil.getWebElement(FILE_NUMBER_2_ERR);
        if (fileNumberPart2Error == null || !fileNumberPart2Error.isDisplayed()) {
            throw new AutomationException("Validation for File Number Part 2 is not displayed correctly.");
        }
        clearField(FILE_NUMBER_PART_2);
        fillField(FILE_NUMBER_PART_2, "EstateCreate.fileNumberPart2");

        clearField(FILE_NUMBER_PART_3);
        fileNumberPart3.sendKeys("12");
        fileNumberPart3.sendKeys(Keys.TAB);
        WebElement fileNumberPart3Error = driverUtil.getWebElement(FILE_NUMBER_3_ERR);
        if (fileNumberPart3Error == null || !fileNumberPart3Error.isDisplayed()) {
            throw new AutomationException("Validation for File Number Part 3 is not displayed correctly.");
        }
        CommonSteps.takeScreenshot();
        clearField(FILE_NUMBER_PART_3);
        fillField(FILE_NUMBER_PART_3, "EstateCreate.fileNumberPart3");
        selectDefaultAddressRadioButton("Accountant");
    }

    public void verifyDatePickerIsDisplay() throws AutomationException {
        waitForVisibleElement(By.xpath(DATEPICKER));
        if (driverUtil.getWebElement(DATEPICKER).isDisplayed()) {
            CommonSteps.logInfo("Date picker open for Dates field");
        } else {
            throw new AutomationException("Date picker not opened for Dates field");
        }
        CommonSteps.takeScreenshot();
    }

    public void entersDOBandDOD() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        fillField(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth", actions);
        actions.sendKeys(Keys.ENTER);
        fillField(DATE_OF_DEATH_FIELD, "EstateCreate.dateOfDeath", actions);
        actions.sendKeys(Keys.ENTER);
        waitForAWhile(3);
    }

    public void calculateAgeAtDeath() throws AutomationException {
        String dob = driverUtil.getWebElement(DATE_OF_BIRTH_FIELD).getAttribute("value");
        String dod = driverUtil.getWebElement(DATE_OF_DEATH_FIELD).getAttribute("value");

        String displayedAge = driverUtil.getWebElement(AGE_AT_DEATH_FIELD).getAttribute("value");

        int expectedAge = calculateExpectedAge(dob, dod);

        if (Integer.parseInt(displayedAge) == expectedAge) {
            CommonSteps.logInfo("Age at Death is correctly calculated: " + displayedAge);
        } else {
            throw new AutomationException("Incorrect Age at Death. Expected: " + expectedAge + ", but Found: " + displayedAge);
        }
    }

    public int calculateExpectedAge(String dob, String dod) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate deathDate = LocalDate.parse(dod, formatter);

        return Period.between(birthDate, deathDate).getYears();
    }

    public void divorcedDecreeFieldDisplayCheck() throws AutomationException {
        WebElement dateDivorcedDecree = driverUtil.getWebElement(DATE_DIVORCED_DECREE);
        if (dateDivorcedDecree.isDisplayed()) {
            CommonSteps.logInfo("The Date Divorced Decree field displayed");
        } else {
            throw new AutomationException("The Date Divorced Decree field is not displayed");
        }
    }

    public void validateSSNForSameName() throws AutomationException, IOException, ParseException {
        WebElement errorSSNElement = driverUtil.getWebElement(SSN_ERROR_MESSAGE);
        if (errorSSNElement != null && errorSSNElement.isDisplayed()) {
            throw new AutomationException("Error displayed incorrectly for a different SSN also");
        } else {
            CommonSteps.logInfo("No error message display for different SSN as expected");
        }
    }

    public void clickOnCodicilDatesDatePickerOpen() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        driverUtil.getWebElement(CODICILE_DATE_1).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
        driverUtil.getWebElement(CODICILE_DATE_2).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
        driverUtil.getWebElement(CODICILE_DATE_3).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
    }

    public void entersCodicilDates() throws AutomationException, IOException, ParseException {

        Actions actions = new Actions(DriverFactory.drivers.get());
        clearField(CODICILE_DATE_1);
        fillField(CODICILE_DATE_1, "EstateCreate.codicilDate1");
        actions.sendKeys(Keys.ENTER);
        clearField(CODICILE_DATE_2);
        fillField(CODICILE_DATE_2, "EstateCreate.codicilDate2");
        actions.sendKeys(Keys.ENTER);
        clearField(CODICILE_DATE_3);
        fillField(CODICILE_DATE_3, "EstateCreate.codicilDate3");
        actions.sendKeys(Keys.ENTER);
    }

    public void validateDateFormat() throws AutomationException {
        String codicilDate1 = driverUtil.getWebElement(CODICILE_DATE_1).getAttribute("value");
        String codicilDate2 = driverUtil.getWebElement(CODICILE_DATE_2).getAttribute("value");
        String codicilDate3 = driverUtil.getWebElement(CODICILE_DATE_3).getAttribute("value");

        String expectedDateFormat = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/([0-9]{4})$";

        if (!codicilDate1.matches(expectedDateFormat)) {
            throw new AutomationException("The date " + codicilDate1 + " is not in the correct format (MM/dd/yyyy).");
        } else {
            CommonSteps.logInfo("The date " + codicilDate1 + " is in the correct format (MM/dd/yyyy).");
        }

        if (!codicilDate2.matches(expectedDateFormat)) {
            throw new AutomationException("The date " + codicilDate2 + " is not in the correct format (MM/dd/yyyy).");
        } else {
            CommonSteps.logInfo("The date " + codicilDate2 + " is in the correct format (MM/dd/yyyy).");
        }
        if (!codicilDate3.matches(expectedDateFormat)) {
            throw new AutomationException("The date " + codicilDate3 + " is not in the correct format (MM/dd/yyyy).");
        } else {
            CommonSteps.logInfo("The date " + codicilDate3 + " is in the correct format (MM/dd/yyyy).");
        }
    }

    public void selectAddress(String address) throws AutomationException {
        WebElement radioButton = driverUtil.getWebElement(String.format(ADDRESS_RADIO_BTN_XPATH, address));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public String getSelectedAddress() throws AutomationException {
        List<String> addresses = Arrays.asList("Fiduciary Address or Attny", "Accountant", "Preparer Address");
        for (String address : addresses) {
            WebElement radioButton = driverUtil.getWebElementAndScroll(String.format(ADDRESS_RADIO_BTN_XPATH, address));
            if (radioButton.isSelected()) {
                return address;
            }
        }
        throw new AutomationException("No address is selected by default.");
    }

    public void verifyOnlyOneAddressSelected(String expectedAddress) throws AutomationException {
        List<String> addresses = Arrays.asList("Fiduciary Address or Attny", "Accountant", "Preparer Address");
        for (String address : addresses) {
            WebElement radioButton = driverUtil.getWebElement(String.format(ADDRESS_RADIO_BTN_XPATH, address));
            if (address.equals(expectedAddress)) {
                if (!radioButton.isSelected()) {
                    throw new AutomationException(expectedAddress + " is not selected.");
                }
            } else {
                if (radioButton.isSelected()) {
                    throw new AutomationException(address + " is incorrectly selected.");
                }
            }
        }
    }

    public void verifyEstateOnListingPage() throws AutomationException {
        driverUtil.getWebElement(ESTATE_BREADCRUMB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        filterByEstateName(displayName);
    }

    public void fillLifeDetailsAndValidatefields() throws AutomationException, IOException, ParseException {

        Actions actions = new Actions(DriverFactory.drivers.get());

        clearField(LAST_RESIDENCE_FIELD);
        driverUtil.getWebElement(LAST_RESIDENCE_FIELD).sendKeys("A".repeat(51));
        driverUtil.getWebElement("//body").click();
        verifyLastResidenceFieldValidationErrors();

        clearField(LAST_RESIDENCE_FIELD);
        fillField(LAST_RESIDENCE_FIELD, "EstateCreate.lastResidence");


        clearField(DATE_OF_BIRTH_FIELD);
        verifyDatePickerIsDisplay();
        fillField(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth");
        actions.sendKeys(Keys.ENTER);

        clearField(DATE_OF_DEATH_FIELD);
        verifyDatePickerIsDisplay();
        fillField(DATE_OF_DEATH_FIELD, "EstateCreate.dateOfDeath");
        actions.sendKeys(Keys.ENTER);

        ageAtDeath = driverUtil.getWebElement(AGE_AT_DEATH_FIELD).getAttribute("value");
    }
}

package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.io.IOException;

import static com.sixinone.automation.util.WebDriverUtil.*;

public class ProbateFormsRW01Page extends BasePage {
    @Override
    String getName() {
        return "";
    }

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
    public static final String CREATE_BUTTON = "//button[text()='Create']";
    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String CREATE_LAST_NAME_FIELD = "//input[@name='contact.lastName']";
    private static final String SSN_FIELD = "//input[@name='ssn']";
    private static final String PROCEED_BUTTON = "//button[@type='submit']";
    private static final String CREATE_NEW_ESTATE_BTN = "//button[contains(text(),'Create a new estate with the name')]";
    private static final String DECEDENT_MIDDLE_NAME = "//input[@name='decedentInfo.middleName']";
    private static final String DECEDENT_DISPLAY_NAME = "//input[@name='decedentInfo.displayNameAs']";
    private static final String DECEDENT_ALSO_KNOWN_AS = "//textarea[@name='decedentInfo.alsoKnownAs']";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String NEXT_BTN = "//button[text()='Next']";
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
    private static final String PLACE_OF_DEATH_ADDRESS_LINE1 = "//input[@name='placeOfDeath.addressLine1']";
    private static final String PLACE_OF_DEATH_ADDRESS_LINE2 = "//input[@name='placeOfDeath.addressLine2']";
    private static final String PLACE_OF_DEATH_ZIP = "//input[@name='placeOfDeath.zip']";
    private static final String PLACE_OF_DEATH_CITY = "//input[@name='placeOfDeath.city']";
    private static final String PLACE_OF_DEATH_STATE = "//div[text()='Place of Death']/following-sibling::div/div/div/div/div/label[text()='State']/following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String PLACE_OF_DEATH_COUNTRY = "//input[@name='placeOfDeath.county']";
    private static final String ESTATE_TAB = "//button[@role='tab' and text()='Estate']";
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
    private static final String TOWNSHIP_RADIO = "//input[@name='domicileAddress.residenceType' and @value='1']";
    private static final String BOROUGH_RADIO = "//input[@name='domicileAddress.residenceType' and @value='2']";
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String RW1_SECTION4_INPUT_FIELD_XPATH = "//div[@id='attorneySection']//input[@type='text' and @value='%s']";
    private static final String RW1_SECTION5_INPUT_FIELD_XPATH = "//div[@id='executorSection']//input[@type='text' and @value='%s']";
    private static final String RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH = "//p[contains(text(),'Secondary Co-Executor')]/ancestor::td/ancestor::tr/following-sibling::tr//input[@type='text' and @value='%s']";
    private static final String SECTION_XPATH = "//span[text()='%s']";
    private static final String SECTION_2_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Click a box']";
    private static final String CHECKBOX_XPATH_DYNAMIC = "//p[text()='%s']//input[@type='checkbox']";
    private static final String SECTION_3_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Click a box']";
    private static final String SECTION_4_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Select the role and contact.']";
    private static final String TEMP = "//a[text()='Baby John']";
    private static final String OTHER_TEXT_AREA = "//input[@name='lettersGrantedOther']";
    private static final String SECTION_4_LAST_NAME = "//div[@id='attorneySection']//input[@class='greyInput ']";
    private static final String SECTION_4_SIDE_BAR_TITLE = "//div[@class='modal-title h4' and text()='Select Attorney/Correspondent']";
    private static final String SECTION_5_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Select the fiduciary contact.']";
    private static final String ROLE_CHECKBOX = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input[not(@checked)]";
    private static final String ROLE_SAVE_BTN = "//div[@class='fade custom-modal center-small-modal modal show']//button[text()='Save']";
    public static final String SAVE_BUTTON_AFTER_SELECTROLE = "//div[@class='fade custom-modal undefined modal show']//button[text()='Save']";
    public static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    private static final String ADDCONTACT_CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='5'] //input[@aria-label='Filter']";
    public static final String ADD_BUTTON = "//button[text()='Add']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
    public static final String ADD_CONTACT_BTN = "//button[text()='Add Contact']";
    private static final String ENTITY_NAME_FIELD = "//input[contains(@name,'entityName')]";
    private static final String EIN_FIELD = "//input[contains(translate(@name, 'EIN', 'ein'), 'ein')]";
    private static final String PLACE_OF_BIRTH = "//input[@name='contact.placeOfBirth']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String SSN_FIELD_ESTATE_CONTACT = "//input[@name='contact.SSN']";
    public static final String ADD_ADDRESS_BTN = "//button[text()='Add Address']";
    private static final String ADDRESS_LINE1 = "//input[@name='addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='addressLine2']";
    private static final String ZIP = "//input[@name='zip']";
    private static final String ADDRESS_COMMENTS = "//textarea[@name='comments']";
    public static final String SAVE_BUTTON = "//button[text()='Save']";
    private static final String SELECT_ROLE_BTN = "//div[@class='modal-content']//button[text()='Select Role']";
    private static final String ROLE_RADIO_BTN_XPATH = "//input[@id='%s']";
    private static final String CONTACT_RADIO_BTN_XPATH = "//input[@name='attorneyContact' and @type='radio']";
    private static final String PROCEED_BTN = "//button[text()='Proceed']";
    private static final String SECTION_5_LAST_NAME = "//div[@id='executorSection']//input[@class='greyInput ']";
    private static final String SECTION_5_SIDE_BAR_TITLE = "//div[@class='modal-title h4' and text()='Select Representatives']";
    private static final String SELECTION_TYPE = "//h4[text()='%s']";
    private static final String DRAG_CONTACT = "//div[@class='drag-names-list']//div[@aria-roledescription='draggable']";
    private static final String DROP_CONTACT_XPATH = "//h4[text()='%s']/following-sibling::div//div";
    private static final String ACCEPT_BTN = "//button[text()='Accept']";
    private static final String SECOND_PAGE_BTN = "//div[@class='nav-item']//a[text()='2']";
    public static final String CREATE_NEW_INDIVIDUAL_BTN = "//button[text()='Create New Individual']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Entity Details']";
    private static final String CREATE_MAIDEN_NAME_FIELD = "//input[@name='maidenName']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";

    static String decedentSSN;
    static String enteredFileNum;
    static String enteredDateOfBirth;
    static String enteredDateOfDeath;
    static String enteredEIN;
    static String enteredSSN;

    public void tempDelete() throws AutomationException, AWTException, InterruptedException {
        driverUtil.getWebElement(TEMP).click();
    }

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void selectCheckBox(String checkboxName) throws AutomationException {
        WebElement checkbox = driverUtil.getWebElement(String.format(ESTATE_CHECKBOX_XPATH, checkboxName));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectDefaultAddressRadioButton(String checkboxName) throws AutomationException {
        WebElement clickRadioBtn = driverUtil.getWebElement(String.format(ADDRESS_RADIO_BTN_XPATH, checkboxName));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();
        jsExecutor.executeScript("arguments[0].click();", clickRadioBtn);
    }

    public void fillEstateDetails() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        selectCheckBox("Use model accounting");
        fillField(DATE_OF_WILL, "EstateCreate.dateOfWill");

        fillField(CODICILE_DATE_1, "EstateCreate.codicilDate1");
        actions.sendKeys(Keys.ENTER);
        fillField(CODICILE_DATE_2, "EstateCreate.codicilDate2");
        actions.sendKeys(Keys.ENTER);
        fillField(CODICILE_DATE_3, "EstateCreate.codicilDate3");
        actions.sendKeys(Keys.ENTER);

        fillField(PROBATE_COURT_NAME, "EstateCreate.probateCourtName");
        fillField(PROBATE_COURT_LOCATION, "EstateCreate.probateCourtLocation");
        fillField(FILE_NUMBER_PART_1, "EstateCreate.fileNumberPart1");
        fillField(FILE_NUMBER_PART_2, "EstateCreate.fileNumberPart2");
        fillField(FILE_NUMBER_PART_3, "EstateCreate.fileNumberPart3");
        enteredFileNum = driverUtil.getWebElement(FILE_NUMBER_PART_3).getAttribute("value");
        selectDefaultAddressRadioButton("Accountant");

        driverUtil.getWebElement("//body").click();

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

    public static void scrollPageToTop() throws AutomationException {
        WebElement body = DriverFactory.drivers.get().findElement(By.tagName("body"));
        body.click();
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
        body.sendKeys(Keys.PAGE_UP);
    }

    public void clickOnEstateTab() throws AutomationException {
        scrollPageToTop();
        driverUtil.getWebElement(ESTATE_TAB).click();
    }

    public void fillPlaceOfDeathDetails() throws AutomationException, IOException, ParseException {
        fillField(PLACE_OF_DEATH_ADDRESS_LINE1, "EstateCreate.PODaddressLine1");
        fillField(PLACE_OF_DEATH_ADDRESS_LINE2, "EstateCreate.PODaddressLine2");
        fillField(PLACE_OF_DEATH_ZIP, "EstateCreate.PODzip");
        driverUtil.getWebElement(PLACE_OF_DEATH_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfPlaceOfDeathAddress();
    }

    public void selectMaritalStatusOptionOthers() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.maritalStatusOthers").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
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

    private void fillFieldWithKeyStrokes(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        String value = CommonUtil.getJsonPath("Create").get(jsonKey).toString();
        actions.moveToElement(field).click().build().perform();
        for (char c : value.toCharArray()) {
            actions.sendKeys(String.valueOf(c)).build().perform();
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
    }

    public void fillLifeDetails() throws AutomationException, IOException, ParseException {

        Actions actions = new Actions(DriverFactory.drivers.get());
        fillFieldWithKeyStrokes(LAST_RESIDENCE_FIELD, "EstateCreate.lastResidence");
        fillFieldWithClipboard(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth",actions);
        fillFieldWithClipboard(DATE_OF_DEATH_FIELD, "EstateCreate.dateOfDeath",actions);
        selectMaritalStatusOptionOthers();
        enteredDateOfBirth = driverUtil.getWebElement(DATE_OF_BIRTH_FIELD).getAttribute("value");
        enteredDateOfDeath = driverUtil.getWebElement(DATE_OF_DEATH_FIELD).getAttribute("value");
    }

    public static void verifyAutoFetchedFieldsOfDomicileAddress() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.city").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.country").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(DOMICILE_CITY, "value");
        String actualState = getFieldValue(DOMICILE_STATE, "text");
        String actualCounty = getFieldValue(DOMICILE_COUNTRY, "value");

        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public void fillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement townshipRadio = driverUtil.getWebElement(TOWNSHIP_RADIO);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();

        fillField(DOMICILE_ADDRESS_LINE1, "EstateCreate.addressLine1");
        fillField(DOMICILE_ADDRESS_LINE2, "EstateCreate.addressLine2");
        fillField(DOMICILE_ZIP, "EstateCreate.zip");
        driverUtil.getWebElement(DOMICILE_ZIP).sendKeys(Keys.TAB);
        verifyAutoFetchedFieldsOfDomicileAddress();
        fillField(DOMICILE_MUNICIPALITY, "EstateCreate.municipality");
        jsExecutor.executeScript("arguments[0].click();", townshipRadio);
    }

    public void clickOnNextButton() throws AutomationException {
        driverUtil.getWebElement(NEXT_BTN).click();
    }

    public void selectSuffixOption() throws AutomationException, IOException, ParseException {
        String suffixValue = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.suffix").toString();
        WebElement suffixDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Suffix"));
        suffixDropdown.click();
        WebElement suffixOption = driverUtil.getWebElement(String.format(SELECT_OPTION, suffixValue));
        suffixOption.click();
    }

    private static String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    public void clickOnProceedButton() throws AutomationException {
        driverUtil.getWebElement(PROCEED_BUTTON).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }


    public void fillDecedentBasicInformation() throws AutomationException, IOException, ParseException {
        fillField(DECEDENT_MIDDLE_NAME, "EstateCreate.middleName");
        fillField(DECEDENT_DISPLAY_NAME, "EstateCreate.displayName");
        selectSuffixOption();
        fillField(DECEDENT_ALSO_KNOWN_AS, "EstateCreate.alsoKnownAs");
    }

    public void clickButtonCreate() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CREATE_BUTTON).click();
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

    private void fillFieldWithClipboard(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        String value = CommonUtil.getJsonPath("EstateCreate").get(jsonKey).toString();
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);

        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
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

    public void clickCreateNewEstateButton() throws AutomationException {
        driverUtil.getWebElement(CREATE_NEW_ESTATE_BTN).click();
    }

    public void verifyFetchedInputField(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW_INPUT_FIELD_XPATH,value)));
        WebElement field = driverUtil.findElement(String.format(RW_INPUT_FIELD_XPATH,value));
        if(!field.isDisplayed()){
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSection4(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECTION4_INPUT_FIELD_XPATH,value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECTION4_INPUT_FIELD_XPATH,value));
        if(!field.isDisplayed()){
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSection5(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECTION5_INPUT_FIELD_XPATH,value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECTION5_INPUT_FIELD_XPATH,value));
        if(!field.isDisplayed()){
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSecondaryCoExecutive(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH,value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH,value));
        if(!field.isDisplayed()){
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyCorrectFileNumber() throws AutomationException {
        verifyFetchedInputField(enteredFileNum);
    }

    public void verifyCorrectInformationFetchedFromTheDecedentInfoTab() throws AutomationException, IOException, ParseException {
        String enteredLastName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.lastName").toString();
        String enteredFirstName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.firstName").toString();
        String selectedSuffix = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.suffix").toString();
        String enteredMiddleName = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.middleName").toString();

        verifyFetchedInputField(decedentSSN);
        verifyFetchedInputField(enteredLastName);
        verifyFetchedInputField(selectedSuffix);
        verifyFetchedInputField(enteredFirstName);
        verifyFetchedInputField(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputField(enteredDateOfBirth);
        verifyFetchedInputField(enteredDateOfDeath);
    }

    public void clickOnSection(String section) throws AutomationException {
        driverUtil.getWebElement(String.format(SECTION_XPATH,section)).click();
    }

    public void verifySection2InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section2InformativeTextBox =driverUtil.getWebElement(SECTION_2_INFORMATIVE_TEXTBOX);
        if(!section2InformativeTextBox.isDisplayed()){
            throw new AutomationException("On clicking section 2 an informative text box is not displayed.");
        }
    }

    public void verifyInSection2OnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        WebElement checkbox1 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Probate Return")));
        WebElement checkbox2 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Joint Assets Only")));
        WebElement checkbox3 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Non-probate Assets Only")));
        WebElement checkbox4 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Litigation Purposes (No Other Assets)")));

        checkbox1.click();
        WebDriverUtil.waitForAWhile();
        if (!checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the first checkbox should be selected.");
        }

        checkbox2.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || !checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the second checkbox should be selected.");
        }

        checkbox3.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || !checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the third checkbox should be selected.");
        }

        checkbox4.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || !checkbox4.isSelected()) {
            throw new AssertionError("Only the fourth checkbox should be selected.");
        }
    }

    public void verifySection3InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section3InformativeTextBox =driverUtil.getWebElement(SECTION_3_INFORMATIVE_TEXTBOX);
        if(!section3InformativeTextBox.isDisplayed()){
            throw new AutomationException("On clicking section 3 an informative text box is not displayed.");
        }
    }

    public void verifyInSection3OnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        WebElement checkbox1 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC,"Testamentary")));
        WebElement checkbox2 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC,"Administration")));
        WebElement checkbox3 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC,"No Letters")));
        WebElement checkbox4 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC,"Other (Please Explain)")));

        checkbox1.click();
        WebDriverUtil.waitForAWhile();
        if (!checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the first checkbox should be selected.");
        }

        checkbox2.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || !checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the second checkbox should be selected.");
        }

        checkbox3.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || !checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AssertionError("Only the third checkbox should be selected.");
        }

        checkbox4.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || !checkbox4.isSelected()) {
            throw new AssertionError("Only the fourth checkbox should be selected.");
        }
    }

    public void verifySection4InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section4InformativeTextBox =driverUtil.getWebElement(SECTION_4_INFORMATIVE_TEXTBOX);
        if(!section4InformativeTextBox.isDisplayed()){
            throw new AutomationException("On clicking section 4 an informative text box is not displayed.");
        }
    }

    public void verifyOtherCheckboxTextAreaIsEnabled() throws AutomationException {
        WebElement otherTextArea = driverUtil.getWebElement(OTHER_TEXT_AREA);
        if (!otherTextArea.isEnabled()) {
            throw new AutomationException("Other Text Area is not enabled.");
        }
    }

    public void clickOnLastNameField() throws AutomationException {
        driverUtil.getWebElement(SECTION_4_LAST_NAME).click();
    }

    public void verifySideBarIsDisplayed() throws AutomationException {
        WebElement sidebarHeader = driverUtil.getWebElement(SECTION_4_SIDE_BAR_TITLE);
        if (!sidebarHeader.isDisplayed()) {
            throw new AutomationException("Sidebar with title 'Select Attorney/Correspondent' is not displayed.");
        }
    }

    public void verifySection5InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section5InformativeTextBox =driverUtil.getWebElement(SECTION_5_INFORMATIVE_TEXTBOX);
        if(!section5InformativeTextBox.isDisplayed()){
            throw new AutomationException("On clicking section 5 an informative text box is not displayed.");
        }
    }

    public void selectRoleAccountant() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAccountant").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void selectRoleAttorney() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAttorney").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void selectRoleBeneficiary() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleBeneficiary").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void selectRoleCorrespondent() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleCorrespondent").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void selectRoleFiduciary() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleFiduciary").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void clickOnAddButtonForSpecificContactType(String contactType) throws AutomationException {
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).click();
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).sendKeys(contactType);
        waitForAWhile(2);
        driverUtil.getWebElement(ADD_BUTTON).click();
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
    }

    public void navigateToProbateFormsTab() throws AutomationException {
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH,formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickAddContactButton() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
        waitForVisibleElement(By.xpath(ADD_CONTACT_BTN));
        driverUtil.getWebElement(ADD_CONTACT_BTN).click();
    }

    public void selectGenderOption() throws AutomationException, IOException, ParseException {
        String genderValue = CommonUtil.getJsonPath("Create").get("Create.gender").toString();
        WebElement genderDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Gender"));
        genderDropdown.click();
        WebElement genderOption = driverUtil.getWebElement(String.format(SELECT_OPTION, genderValue));
        genderOption.click();
    }

    public void fillsOtherBasicDetailsBirthDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
        WebDriverUtil.waitForAWhile();
        fillFieldWithKeyStrokes(ENTITY_NAME_FIELD, "Create.entityName");
        fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
        enteredEIN=driverUtil.getWebElement(EIN_FIELD).getAttribute("value");
        selectGenderOption();
        fillField(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth");
        fillField(PLACE_OF_BIRTH,"EstateContact.placeOfBirth");
        fillField(WORK_NUMBER_FIELD, "Create.workNumber",actions);
        fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber",actions);
        fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
        fillField(FAX_FIELD, "Create.fax",actions);
        fillFieldWithRandom(SSN_FIELD_ESTATE_CONTACT, randomSSN, actions);
        enteredSSN=driverUtil.getWebElement(SSN_FIELD_ESTATE_CONTACT).getAttribute("value");
    }

    public void fillAddressInfo() throws AutomationException, IOException, ParseException {
        driverUtil.getWebElement(ADD_ADDRESS_BTN).click();
        fillField(ADDRESS_LINE1, "Create.addressLine1");
        fillField(ADDRESS_LINE2, "Create.addressLine2");
        fillField(ZIP, "Create.zip");
        driverUtil.getWebElement(ZIP).sendKeys(Keys.TAB);
        WebDriverUtil.waitForAWhile(2);
        fillField(ADDRESS_COMMENTS,"Create.addressComments");
        driverUtil.getWebElement(SAVE_BUTTON).click();
        WebDriverUtil.waitForAWhile(5);
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Address added successfully.")));
    }

    public void selectRoleButton() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact created successfully.")));
        waitForVisibleElement(By.xpath(SELECT_ROLE_BTN));
        waitForAWhile(3);
        driverUtil.getWebElement(SELECT_ROLE_BTN).click();
    }

    public void selectRoleForAttorney(String role) throws AutomationException {
        driverUtil.getWebElement(String.format(ROLE_RADIO_BTN_XPATH,role)).click();
    }

    public void verifyOnlyContactIsAbleToBeSelected() throws AutomationException {
        List<WebElement> radioButtons = driverUtil.getWebElements(CONTACT_RADIO_BTN_XPATH);
        if (radioButtons.isEmpty()) {
            throw new AutomationException("No radio buttons found with the specified XPath.");
        }

        driverUtil.getWebElement(CONTACT_RADIO_BTN_XPATH).click();

        int selectedCount = 0;
        for (WebElement radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                selectedCount++;
            }
        }
        if (selectedCount > 1) {
            throw new AutomationException("More than one radio button is selected. Selected count: " + selectedCount);
        }
    }

    public void clickOnTheProceedButton() throws AutomationException {
        driverUtil.getWebElement(PROCEED_BTN).click();
    }

    public void clickOnExecutorLastNameField() throws AutomationException {
        driverUtil.getWebElement(SECTION_5_LAST_NAME).click();
    }

    public void verifySideBarIsDisplayedForSection5() throws AutomationException {
        WebElement sidebarHeader = driverUtil.getWebElement(SECTION_5_SIDE_BAR_TITLE);
        if (!sidebarHeader.isDisplayed()) {
            throw new AutomationException("Sidebar with title 'Select Representatives' is not displayed.");
        }
    }

    public void verifySelectionTypesAreDisplayed() throws AutomationException {
        WebElement executor = driverUtil.getWebElement(String.format(SELECTION_TYPE,"Executor"));
        WebElement coExecutor = driverUtil.getWebElement(String.format(SELECTION_TYPE,"Co-Executor"));
        WebElement secondaryCoExecutor = driverUtil.getWebElement(String.format(SELECTION_TYPE,"Secondary Co-Executor"));

        if(!executor.isDisplayed() || !coExecutor.isDisplayed() || !secondaryCoExecutor.isDisplayed()){
            throw new AutomationException("All 3 selection types are not displayed");
        }
    }

    public void dragAndDropContactsIntoSelectionTypes() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebElement executorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH,"Executor"));
        WebElement coExecutorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH,"Co-Executor"));
        WebElement secondaryCoExecutorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH,"Secondary Co-Executor"));

        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), executorDrop).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), coExecutorDrop).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), secondaryCoExecutorDrop).perform();
    }

    public void clicksOnAcceptButton() throws AutomationException {
        driverUtil.getWebElement(ACCEPT_BTN).click();
        driverUtil.getWebElement(String.format(SECTION_XPATH,"Section V")).click();
    }

    public void verifyContactItsInformationIsDisplayedInSection4() throws IOException, ParseException, AutomationException {
        String enteredLastName = CommonUtil.getJsonPath("Create").get("Create.lastName").toString();
        String selectedSuffix = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        String enteredFirstName = CommonUtil.getJsonPath("Create").get("Create.firstName").toString();
        String enteredMiddleName = CommonUtil.getJsonPath("Create").get("Create.middleName").toString();
        String enteredTelephoneNum = CommonUtil.getJsonPath("Create").get("Create.phoneNumber").toString();
        String enteredEmail = CommonUtil.getJsonPath("Create").get("Create.emailId").toString();
        String enteredAddressLine1 = CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString();
        String enteredAddressLine2 = CommonUtil.getJsonPath("Create").get("Create.addressLine2").toString();
        String enteredCity = CommonUtil.getJsonPath("Create").get("Create.city").toString();
        String enteredState = CommonUtil.getJsonPath("Create").get("Create.stateCode").toString();
        String enteredZip = CommonUtil.getJsonPath("Create").get("Create.zip").toString();

        verifyFetchedInputFieldOfSection4(enteredLastName);
        verifyFetchedInputFieldOfSection4(selectedSuffix);
        verifyFetchedInputFieldOfSection4(enteredFirstName);
        verifyFetchedInputFieldOfSection4(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputFieldOfSection4(enteredTelephoneNum);
        verifyFetchedInputFieldOfSection4(enteredEmail);
        verifyFetchedInputFieldOfSection4(enteredAddressLine1);
        verifyFetchedInputFieldOfSection4(enteredAddressLine2);
        verifyFetchedInputFieldOfSection4(enteredCity);
        verifyFetchedInputFieldOfSection4(enteredState);
        verifyFetchedInputFieldOfSection4(enteredZip);
    }

    public void verifySelectedContactsAreDisplayedUnderExecutorCoExecutorAndSecondaryCoExecutor() throws AutomationException, IOException, ParseException {
        String enteredLastName = CommonUtil.getJsonPath("Create").get("Create.lastName").toString();
        String selectedSuffix = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        String enteredFirstName = CommonUtil.getJsonPath("Create").get("Create.firstName").toString();
        String enteredMiddleName = CommonUtil.getJsonPath("Create").get("Create.middleName").toString();
        String enteredAddressLine1 = CommonUtil.getJsonPath("Create").get("Create.addressLine1").toString();
        String enteredAddressLine2 = CommonUtil.getJsonPath("Create").get("Create.addressLine2").toString();
        String enteredCity = CommonUtil.getJsonPath("Create").get("Create.city").toString();
        String enteredState = CommonUtil.getJsonPath("Create").get("Create.stateCode").toString();
        String enteredZip = CommonUtil.getJsonPath("Create").get("Create.zip").toString();

        verifyFetchedInputFieldOfSection5(enteredLastName);
        verifyFetchedInputFieldOfSection5(selectedSuffix);
        verifyFetchedInputFieldOfSection5(enteredFirstName);
        verifyFetchedInputFieldOfSection5(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputFieldOfSection5(enteredAddressLine1);
        verifyFetchedInputFieldOfSection5(enteredAddressLine2);
        verifyFetchedInputFieldOfSection5(enteredCity);
        verifyFetchedInputFieldOfSection5(enteredState);
        verifyFetchedInputFieldOfSection5(enteredZip);

        driverUtil.getWebElement(SECOND_PAGE_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));

        verifyFetchedInputFieldOfSection5(enteredLastName);
        verifyFetchedInputFieldOfSection5(selectedSuffix);
        verifyFetchedInputFieldOfSection5(enteredFirstName);
        verifyFetchedInputFieldOfSection5(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputFieldOfSection5(enteredAddressLine1);
        verifyFetchedInputFieldOfSection5(enteredAddressLine2);
        verifyFetchedInputFieldOfSection5(enteredCity);
        verifyFetchedInputFieldOfSection5(enteredState);
        verifyFetchedInputFieldOfSection5(enteredZip);

        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredLastName);
        verifyFetchedInputFieldOfSecondaryCoExecutive(selectedSuffix);
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredFirstName);
        verifyFetchedInputFieldOfSecondaryCoExecutive(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredAddressLine1);
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredAddressLine2);
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredCity);
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredState);
        verifyFetchedInputFieldOfSecondaryCoExecutive(enteredZip);
    }

    public void clickOnNewIndividualContactBtn() throws AutomationException {
        clickAddContactButton();
        driverUtil.getWebElement(CREATE_NEW_INDIVIDUAL_BTN).click();
    }

    public void fillNewGlobalContactDetails(String contactType) throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "New Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(FIRST_NAME_FIELD, "Create.firstName");
                fillField(MIDDLE_NAME_FIELD, "Create.middleName");
                fillField(CREATE_LAST_NAME_FIELD, "Create.lastName");
                selectSuffixOption();
                fillField(CREATE_MAIDEN_NAME_FIELD, "Create.maidenName");
                break;
            case "New Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(ENTITY_NAME_FIELD, "Create.entityName");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

}

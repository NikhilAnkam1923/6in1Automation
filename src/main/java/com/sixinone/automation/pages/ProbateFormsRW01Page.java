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
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.*;
import java.io.IOException;

import static com.sixinone.automation.drivers.DriverFactory.OS;
import static com.sixinone.automation.drivers.DriverFactory.WINDOWS;
import static com.sixinone.automation.util.WebDriverUtil.*;

public class ProbateFormsRW01Page extends BasePage {
    @Override
    String getName() {
        return "";
    }

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
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
    private static final String RW_INPUT_FIELD_XPATH = "//input[@type='text' and @value='%s']";
    private static final String RW1_SECTION4_INPUT_FIELD_XPATH = "//div[@id='attorneySection']//input[@type='text' and @value='%s']";
    private static final String RW1_SECTION5_INPUT_FIELD_XPATH = "//div[@id='executorSection']//input[@type='text' and @value='%s']";
    private static final String RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH = "//p[contains(text(),'Secondary Co-Executor')]/ancestor::td/ancestor::tr/following-sibling::tr//input[@type='text' and @value='%s']";
    private static final String SECTION_XPATH = "//span[text()='%s']";
    private static final String SECTION_2_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Click a box']";
    private static final String CHECKBOX_XPATH_DYNAMIC = "//p[text()='%s']//input[@type='checkbox']";
    private static final String SECTION_3_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Click a box']";
    private static final String SECTION_4_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Select the role and contact.']";
    private static final String OTHER_TEXT_AREA = "//input[@name='lettersGrantedOther']";
    private static final String SECTION_4_LAST_NAME = "//div[@id='attorneySection']//input[@class='greyInput ']";
    private static final String SECTION_4_SIDE_BAR_TITLE = "//div[@class='modal-title h4' and text()='Select Attorney/Correspondent']";
    private static final String SECTION_5_INFORMATIVE_TEXTBOX = "//div[@class='white-bg' and text()='Select the fiduciary contact.']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
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
    private static final String FIRST_PAGE_BTN = "//div[@class='nav-item']//a[text()='1']";
    private static final String MODAL_CLOSE_BTN = "//div[@class='modal-footer']//button[text()='Close']";

    static String DownloadedFileName;

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

    public void verifyFetchedInputField(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW_INPUT_FIELD_XPATH, value)));
        WebElement field = driverUtil.findElement(String.format(RW_INPUT_FIELD_XPATH, value));
        if (!field.isDisplayed()) {
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSection4(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECTION4_INPUT_FIELD_XPATH, value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECTION4_INPUT_FIELD_XPATH, value));
        if (!field.isDisplayed()) {
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSection5(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECTION5_INPUT_FIELD_XPATH, value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECTION5_INPUT_FIELD_XPATH, value));
        if (!field.isDisplayed()) {
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyFetchedInputFieldOfSecondaryCoExecutive(String value) throws AutomationException {
        waitForVisibleElement(By.xpath(String.format(RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH, value)));
        WebElement field = driverUtil.findElement(String.format(RW1_SECONDARY_CO_EXECUTIVE_INPUT_FIELD_XPATH, value));
        if (!field.isDisplayed()) {
            throw new AutomationException("Field value is not displayed correctly or not fetched.");
        }
    }

    public void verifyCorrectFileNumber() throws AutomationException {
        verifyFetchedInputField(enteredFileNumberPart3);
    }

    public void verifyCorrectInformationFetchedFromTheDecedentInfoTab() throws AutomationException, IOException, ParseException {
        verifyFetchedInputField(enteredSSN);
        verifyFetchedInputField(enteredLastName);
        verifyFetchedInputField(selectedSuffix);
        verifyFetchedInputField(enteredFirstName);
        verifyFetchedInputField(String.valueOf(enteredMiddleName.charAt(0)));
        verifyFetchedInputField(enteredDateOfBirth);
        verifyFetchedInputField(enteredDateOfDeath);
    }

    public void clickOnSection(String section) throws AutomationException {
        driverUtil.getWebElement(String.format(SECTION_XPATH, section)).click();
    }

    public void verifySection2InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section2InformativeTextBox = driverUtil.getWebElement(SECTION_2_INFORMATIVE_TEXTBOX);
        if (!section2InformativeTextBox.isDisplayed()) {
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
            throw new AutomationException("Only the first checkbox should be selected.");
        }

        checkbox2.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || !checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AutomationException("Only the second checkbox should be selected.");
        }

        checkbox3.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || !checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AutomationException("Only the third checkbox should be selected.");
        }

        checkbox4.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || !checkbox4.isSelected()) {
            throw new AutomationException("Only the fourth checkbox should be selected.");
        }
    }

    public void verifySection3InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section3InformativeTextBox = driverUtil.getWebElement(SECTION_3_INFORMATIVE_TEXTBOX);
        if (!section3InformativeTextBox.isDisplayed()) {
            throw new AutomationException("On clicking section 3 an informative text box is not displayed.");
        }
    }

    public void verifyInSection3OnlyOneCheckboxShouldBeAbleToBeChecked() throws AutomationException {
        WebElement checkbox1 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Testamentary")));
        WebElement checkbox2 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Administration")));
        WebElement checkbox3 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "No Letters")));
        WebElement checkbox4 = DriverFactory.drivers.get().findElement(By.xpath(String.format(CHECKBOX_XPATH_DYNAMIC, "Other (Please Explain)")));

        checkbox1.click();
        WebDriverUtil.waitForAWhile();
        if (!checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AutomationException("Only the first checkbox should be selected.");
        }

        checkbox2.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || !checkbox2.isSelected() || checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AutomationException("Only the second checkbox should be selected.");
        }

        checkbox3.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || !checkbox3.isSelected() || checkbox4.isSelected()) {
            throw new AutomationException("Only the third checkbox should be selected.");
        }

        checkbox4.click();
        WebDriverUtil.waitForAWhile();
        if (checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected() || !checkbox4.isSelected()) {
            throw new AutomationException("Only the fourth checkbox should be selected.");
        }
    }

    public void verifySection4InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section4InformativeTextBox = driverUtil.getWebElement(SECTION_4_INFORMATIVE_TEXTBOX);
        if (!section4InformativeTextBox.isDisplayed()) {
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
        driverUtil.getWebElementAndScroll(SECTION_4_LAST_NAME).click();
    }

    public void verifySideBarIsDisplayed() throws AutomationException {
        WebElement sidebarHeader = driverUtil.getWebElement(SECTION_4_SIDE_BAR_TITLE);
        if (!sidebarHeader.isDisplayed()) {
            throw new AutomationException("Sidebar with title 'Select Attorney/Correspondent' is not displayed.");
        }
    }

    public void verifySection5InformativeTextBoxIsDisplayed() throws AutomationException {
        WebElement section5InformativeTextBox = driverUtil.getWebElement(SECTION_5_INFORMATIVE_TEXTBOX);
        if (!section5InformativeTextBox.isDisplayed()) {
            throw new AutomationException("On clicking section 5 an informative text box is not displayed.");
        }
    }

    public void navigateToProbateFormsTab() throws AutomationException {
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickOnRWForm(String formToSelect) throws AutomationException {
        driverUtil.getWebElement(String.format(RW_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void selectRoleForAttorney(String role) throws AutomationException {
        driverUtil.getWebElementAndScroll(String.format(ROLE_RADIO_BTN_XPATH, role)).click();
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
        WebElement executor = driverUtil.getWebElement(String.format(SELECTION_TYPE, "Executor"));
        WebElement coExecutor = driverUtil.getWebElement(String.format(SELECTION_TYPE, "Co-Executor"));
        WebElement secondaryCoExecutor = driverUtil.getWebElement(String.format(SELECTION_TYPE, "Secondary Co-Executor"));

        if (!executor.isDisplayed() || !coExecutor.isDisplayed() || !secondaryCoExecutor.isDisplayed()) {
            throw new AutomationException("All 3 selection types are not displayed");
        }
    }

    public void dragAndDropContactsIntoSelectionTypes() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());

        WebElement executorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH, "Executor"));
        WebElement coExecutorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH, "Co-Executor"));
        WebElement secondaryCoExecutorDrop = driverUtil.getWebElement(String.format(DROP_CONTACT_XPATH, "Secondary Co-Executor"));

        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), executorDrop).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), coExecutorDrop).perform();
        WebDriverUtil.waitForAWhile();
        actions.dragAndDrop(driverUtil.getWebElement(DRAG_CONTACT), secondaryCoExecutorDrop).perform();
    }

    public void clicksOnAcceptButton() throws AutomationException {
        driverUtil.getWebElement(ACCEPT_BTN).click();
        driverUtil.getWebElement(String.format(SECTION_XPATH, "Section V")).click();
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

    public void userResetsTheRWForm() throws AutomationException {
        driverUtil.getWebElement("//body").click();
        driverUtil.getWebElement(FIRST_PAGE_BTN).click();
        driverUtil.getWebElement(SECTION_5_LAST_NAME).click();
        driverUtil.getWebElement(MODAL_CLOSE_BTN).click();
        driverUtil.getWebElement(SECTION_5_LAST_NAME).click();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement("//span[@class='cursor']").click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(ACCEPT_BTN).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(SECTION_4_LAST_NAME).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(ROLE_RADIO_BTN_XPATH, "NONE")).click();
        driverUtil.getWebElement(CONTACT_RADIO_BTN_XPATH).click();
        driverUtil.getWebElement(PROCEED_BTN).click();
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
                        DownloadedFileName = file.getName();

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
                : System.getProperty("user.dir") + "/downloads/") + DownloadedFileName;

        verifyLastName(pdfFilePath);
        verifyExecutorOrAdministratorLastName(pdfFilePath);
        verifyCoExecutorOrAdministratorLastName(pdfFilePath);
        verifySecondaryCoExecutorOrAdministratorLastName(pdfFilePath);
    }

    public static void verifyLastName(String pdfFilePath) throws IOException, AutomationException {
        String targetLineIdentifier = "Last Name Suffix First Name MI"; // Identifier for the target line
        String expectedLastName = "Winter"; // The expected last name for validation

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
        int lastTargetIndex = -1; // To track the last occurrence of the identifier

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();
            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);

            // Update the index if this is an occurrence of the target line
            if (trimmedLine.contains(targetLineIdentifier)) {
                lastTargetIndex = i;
            }
        }

        // Process the last occurrence
        if (lastTargetIndex != -1 && lastTargetIndex + 1 < allLines.length) {
            String lastNameLine = allLines[lastTargetIndex + 1].trim(); // The next line after the last occurrence
            CommonSteps.logInfo("Extracted Last Name Line: " + lastNameLine);

            // Extract last name (assuming it's the first word in the line)
            String[] words = lastNameLine.split("\\s+"); // Split by spaces
            String actualLastName = words.length > 0 ? words[0] : "No Name";

            if (expectedLastName.equalsIgnoreCase(actualLastName)) {
                CommonSteps.logInfo("✅ Validation Passed: Last Name is '" + actualLastName + "' as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Expected '" + expectedLastName + "', but found '" + actualLastName + "'.");
            }
        } else {
            throw new AutomationException("❌ Target line identifier not found in the PDF.");
        }
    }

//    public static void verifyLastName(String pdfFilePath) throws IOException, AutomationException {
//        String targetLineIdentifier = "Last Name Suffix First Name MI"; // Identifier for the target line
//        String expectedLastName = "Winter"; // Expected last name
//
//        PDDocument document = PDDocument.load(new File(pdfFilePath));
//        String pdfText = new PDFTextStripper().getText(document);
//        document.close();
//
//        // Split the entire PDF content into lines
//        String[] allLines = pdfText.split("\\r?\\n");
//
//        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
//        int lastTargetIndex = -1; // Track the last occurrence of the identifier
//
//        for (int i = 0; i < allLines.length; i++) {
//            String trimmedLine = allLines[i].trim();
//            CommonSteps.logInfo("Line " + (i + 1) + ": " + trimmedLine);
//
//            // If this line contains the identifier, store its index
//            if (trimmedLine.equalsIgnoreCase(targetLineIdentifier)) {
//                lastTargetIndex = i;
//            }
//        }
//
//        // Process the last occurrence of the identifier
//        if (lastTargetIndex != -1 && lastTargetIndex + 1 < allLines.length) {
//            String lastNameLine = allLines[lastTargetIndex + 1].trim(); // The next line after the identifier
//            CommonSteps.logInfo("Extracted Line: " + lastNameLine);
//
//            // Split the extracted line into words (assuming space-separated values)
//            String[] values = lastNameLine.split("\\s+");
//
//            // Ensure the extracted line has at least one value (Last Name should be first)
//            if (values.length > 0) {
//                Map<String, String> extractedData = new HashMap<>();
//                extractedData.put("Last Name", values[0]); // Store first word as Last Name
//
//                String actualLastName = extractedData.get("Last Name");
//
//                if (expectedLastName.equalsIgnoreCase(actualLastName)) {
//                    CommonSteps.logInfo("✅ Validation Passed: Last Name is '" + actualLastName + "' as expected.");
//                } else {
//                    throw new AutomationException("❌ Validation Failed: Expected 'Last Name = " + expectedLastName + "', but found 'Last Name = " + actualLastName + "'.");
//                }
//            } else {
//                throw new AutomationException("❌ Validation Failed: No last name found in the extracted line.");
//            }
//        } else {
//            throw new AutomationException("❌ Target line identifier not found in the PDF.");
//        }
//    }


    public static void verifyExecutorOrAdministratorLastName(String pdfFilePath) throws IOException, AutomationException {
        String targetLineIdentifier = "Executor/Administrator Last Name (if necessary) Suffix First Name MI"; // Identifier for the target line
        String expectedExecutorOrAdministratorLastNameLine = "Winter"; // The expected ExecutorOrAdministratorLastName for validation

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
        int lastTargetIndex = -1; // To track the last occurrence of the identifier

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            // Update the index if this is an occurrence of the target line
            if (trimmedLine.contains(targetLineIdentifier)) {
                lastTargetIndex = i;
            }
        }

        // Process the last occurrence
        if (lastTargetIndex != -1 && lastTargetIndex + 1 < allLines.length) {
            String executorOrAdministratorLastNameLine = allLines[lastTargetIndex + 1].trim(); // The next line after the last occurrence
            CommonSteps.logInfo("Extracted Last Name Line: " + executorOrAdministratorLastNameLine);

            // Extract last name (assuming it's the first word in the line)
            String[] words = executorOrAdministratorLastNameLine.split("\\s+"); // Split by spaces
            String actualexecutorOrAdministratorLastName = words.length > 0 ? words[0] : "No Name";

            if (expectedExecutorOrAdministratorLastNameLine.equalsIgnoreCase(actualexecutorOrAdministratorLastName)) {
                CommonSteps.logInfo("✅ Validation Passed: ExecutorOrAdministratorLastName is '" + actualexecutorOrAdministratorLastName + "' as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Expected '" + expectedExecutorOrAdministratorLastNameLine + "', but found '" + actualexecutorOrAdministratorLastName + "'.");
            }
        } else {
            throw new AutomationException("❌ Target line identifier not found in the PDF.");
        }
    }

    public static void verifyCoExecutorOrAdministratorLastName(String pdfFilePath) throws IOException, AutomationException {
        String targetLineIdentifier = "Co-Executor/Administrator Last Name (if necessary) Suffix First Name MI"; // Identifier for the target line
        String expectedCoExecutorOrAdministratorLastNameLine = "Winter"; // Expected Last Name for validation

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
        int lastTargetIndex = -1; // To track the last occurrence of the identifier

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            if (trimmedLine.contains(targetLineIdentifier)) {
                lastTargetIndex = i; // Store the last occurrence
            }
        }

        // Process the last occurrence
        if (lastTargetIndex != -1 && lastTargetIndex + 1 < allLines.length) {
            String extractedCoExecutorOrAdministratorLastNameLine = allLines[lastTargetIndex + 1].trim(); // Next line after the identifier
            CommonSteps.logInfo("Extracted Last Name Line: " + extractedCoExecutorOrAdministratorLastNameLine);

            // Extract last name (assuming it's the first word)
            String[] words = extractedCoExecutorOrAdministratorLastNameLine.split("\\s+");
            String actualCoExecutorOrAdministratorLastNameLine = words.length > 0 ? words[0] : "No Name";

            if (expectedCoExecutorOrAdministratorLastNameLine.equalsIgnoreCase(actualCoExecutorOrAdministratorLastNameLine)) {
                CommonSteps.logInfo("✅ Validation Passed: Co-Executor Or Administrator Last Name is '" + actualCoExecutorOrAdministratorLastNameLine + "' as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Expected '" + expectedCoExecutorOrAdministratorLastNameLine + "', but found '" + actualCoExecutorOrAdministratorLastNameLine + "'.");
            }
        } else {
            throw new AutomationException("❌ Target line identifier not found in the PDF.");
        }
    }

    public static void verifySecondaryCoExecutorOrAdministratorLastName(String pdfFilePath) throws IOException, AutomationException {
        String targetLineIdentifier = "Secondary Co-Executor/Administrator Last Name (if necessary) Suffix First Name MI"; // Identifier for the target line
        String expectedSecondaryCoExecutorOrAdministratorLastNameLine = "Winter"; // Expected Last Name for validation

        PDDocument document = PDDocument.load(new File(pdfFilePath));
        String pdfText = new PDFTextStripper().getText(document);
        document.close();

        // Split the entire PDF content into lines
        String[] allLines = pdfText.split("\\r?\\n");

        CommonSteps.logInfo("Full PDF Content with Line Numbers:");
        int lastTargetIndex = -1; // To track the last occurrence of the identifier

        for (int i = 0; i < allLines.length; i++) {
            String trimmedLine = allLines[i].trim();

            if (trimmedLine.contains(targetLineIdentifier)) {
                lastTargetIndex = i; // Store the last occurrence
            }
        }

        // Process the last occurrence
        if (lastTargetIndex != -1 && lastTargetIndex + 1 < allLines.length) {
            String extractedSecondaryCoExecutorOrAdministratorLastNameLine = allLines[lastTargetIndex + 1].trim(); // Next line after the identifier
            CommonSteps.logInfo("Extracted Last Name Line: " + extractedSecondaryCoExecutorOrAdministratorLastNameLine);

            // Extract last name (assuming it's the first word)
            String[] words = extractedSecondaryCoExecutorOrAdministratorLastNameLine.split("\\s+");
            String actualSecondaryCoExecutorOrAdministratorLastNameLine = words.length > 0 ? words[0] : "No Name";

            if (expectedSecondaryCoExecutorOrAdministratorLastNameLine.equalsIgnoreCase(actualSecondaryCoExecutorOrAdministratorLastNameLine)) {
                CommonSteps.logInfo("✅ Validation Passed: Secondary Co-Executor Or Administrator Last Name is '" + actualSecondaryCoExecutorOrAdministratorLastNameLine + "' as expected.");
            } else {
                throw new AutomationException("❌ Validation Failed: Expected '" + expectedSecondaryCoExecutorOrAdministratorLastNameLine + "', but found '" + actualSecondaryCoExecutorOrAdministratorLastNameLine + "'.");
            }
        } else {
            throw new AutomationException("❌ Target line identifier not found in the PDF.");
        }
    }
}

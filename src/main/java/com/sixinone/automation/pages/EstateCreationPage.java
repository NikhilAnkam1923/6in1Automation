package com.sixinone.automation.pages;

import com.github.dockerjava.api.model.Driver;
import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
    private static final String DECEDENT_SSN = "//input[@name='decedentInfo.ssn']";
    private static final String NEXT_BTN = "//button[text()='Next']";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    private static final String DECEDENT_DETAILS_PAGE = "//button[text()='Decedent Info']";

    private static final String ADDRESS_LINE1 = "//input[@name='domicileAddress.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='domicileAddress.addressLine2']";
    private static final String ZIP = "//input[@name='domicileAddress.zip']";
    private static final String CITY = "//input[@name='domicileAddress.city']";
    private static final String STATE = "//label[contains(text(),'State')]//following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String COUNTRY = "//input[@name='domicileAddress.county']";
    private static final String MUNICIPALITY = "//input[@name='domicileAddress.municipality']";
    private static final String TOWNSHIP_RADIO = "//input[@name='domicileAddress.residenceType' and @value='1']";
    private static final String BOROUGH_RADIO = "//input[@name='domicileAddress.residenceType' and @value='2']";

    private static final String LAST_RESIDENCE_FIELD = "//input[@name='lifeDetails.lastResidence']";
    private static final String AGE_AT_DEATH = "//input[@name='lifeDetails.ageAtDeath']";
    private static final String MARITAL_STATUS_DROPDOWN = "//label[text()='Marital Status']/following-sibling::div";
    private static final String DATE_OF_BIRTH = "//label[text()='Date of Birth']/following-sibling::div//input";
    private static final String DATE_OF_DEATH = "//label[text()='Date of Death']/following-sibling::div//input";
    private static final String ALT_VAL_DATE = "//label[text()='Alt Val Date']/following-sibling::div";
    private static final String LAST_RESIDENCE_ERROR_MSG = "//input[@name='lifeDetails.lastResidence']/following-sibling::div[@class='invalid-feedback'  and (text()='You have exceeded the maximum character limit of 50'  or text()='Allowed special characters are , and &')]";
    private static final String DATEPICKER = "//div[@class='react-datepicker__month-container']";
    private static final String DATE_DIVORCED_DECREE = "//label[text()='Date Divorced Decree']/following-sibling::div";

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
                .sendKeys(CommonUtil.getJsonPath("EstateLifeDetails").get(jsonKey).toString())
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

    public void enterFirstAndLastNameAndSSN() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));

        fillField(FIRST_NAME_FIELD, "EstateCreate.firstName");
        fillField(LAST_NAME_FIELD, "EstateCreate.lastName");
        fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
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
        WebElement createEstateButton = driverUtil.getWebElement(CREATE_NEW_ESTATE_BTN);
        if (createEstateButton == null) {
            throw new AutomationException("'Create a new estate with the entered name' button is not found.");
        }
        createEstateButton.click();
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
        CommonSteps.logInfo("Decedent's basic information has been filled successfully.");
    }

    public void selectMaritalStatusOptionDivorced() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateLifeDetails").get("EstateLifeDetails.maritalStatusDivorced").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
    }

    public void selectMaritalStatusOptionOthers() throws AutomationException, IOException, ParseException {
        String maritalStatusValue = CommonUtil.getJsonPath("EstateLifeDetails").get("EstateLifeDetails.maritalStatusOthers").toString();
        WebElement maritalStatusDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Marital Status"));
        maritalStatusDropdown.click();
        WebElement maritalStatusOption = driverUtil.getWebElement(String.format(SELECT_OPTION, maritalStatusValue));
        maritalStatusOption.click();
    }

    public void clickOnNextButton() throws AutomationException {
        driverUtil.getWebElement(NEXT_BTN).click();
    }

    public void verifyDecedentDetailsPageIsDisplayed() throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement page = driverUtil.getWebElement(DECEDENT_DETAILS_PAGE);
        if (page == null || !page.isDisplayed()) {
            throw new AutomationException("The 'Decedent Details' page is not opened.");
        }
        CommonSteps.logInfo("Verified the 'Decedent Details' page is opened.");
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
        townshipRadio.click();
        verifyRadioButtonSelection(townshipRadio, boroughRadio);
        boroughRadio.click();
        verifyRadioButtonSelection(boroughRadio, townshipRadio);
        CommonSteps.logInfo("Township and Borough radio buttons toggle correctly.");
    }

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        waitForAWhile(2);
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void enterInvalidLastResidence() throws AutomationException, IOException, ParseException {
        String lastResidenceValue = driverUtil.getWebElement(LAST_RESIDENCE_FIELD).getAttribute("value");
        if (lastResidenceValue != null && !lastResidenceValue.isEmpty()) {
            clearField(LAST_RESIDENCE_FIELD);
        }
        fillField(LAST_RESIDENCE_FIELD, "EstateLifeDetails.lastResidenceInvalid");
        driverUtil.getWebElement("//body").click();
    }

    public void verifyLastResidenceFieldValidationErrors() throws AutomationException {
        waitForVisibleElement(By.xpath(LAST_RESIDENCE_ERROR_MSG));
        if (!driverUtil.getWebElement(LAST_RESIDENCE_ERROR_MSG).isDisplayed()) {
            throw new AutomationException("Error messages are not displayed for invalid input.");
        }
        CommonSteps.logInfo("Error messages are displayed for invalid inputs.");
    }

    public void enterValidLastResidence() throws AutomationException, IOException, ParseException {

        String lastResidenceValue = driverUtil.getWebElement(LAST_RESIDENCE_FIELD).getAttribute("value");
        if (lastResidenceValue != null && !lastResidenceValue.isEmpty()) {
            clearField(LAST_RESIDENCE_FIELD);
        }
        fillField(LAST_RESIDENCE_FIELD, "EstateLifeDetails.lastResidence");
        driverUtil.getWebElement("//body").click();
    }

    public void lastResidenceNotDisplayErrorForValidInput() throws AutomationException {
        waitForInvisibleElement(By.xpath(LAST_RESIDENCE_ERROR_MSG));
        WebElement errorMessage = driverUtil.getWebElement(LAST_RESIDENCE_ERROR_MSG);
        if (errorMessage == null || !errorMessage.isDisplayed()) {
            CommonSteps.logInfo("No validation error message is visible for valid Last Residence field. Field has been corrected successfully.");
        } else {
            throw new AutomationException("Validation error is still visible after corrections: " + errorMessage.getText());
        }
    }

    public void clickOnDatesDatePickerOpen() throws AutomationException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        driverUtil.getWebElement(DATE_OF_BIRTH).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
        driverUtil.getWebElement(DATE_OF_DEATH).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
        driverUtil.getWebElement(ALT_VAL_DATE).click();
        verifyDatePickerIsDisplay();
        actions.sendKeys(Keys.ENTER);
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

        //clearField(DATE_OF_BIRTH);
        fillField(DATE_OF_BIRTH, "EstateLifeDetails.dateOfBirth",actions);
        actions.sendKeys(Keys.ENTER);
        //clearField(DATE_OF_DEATH);
        fillField(DATE_OF_DEATH, "EstateLifeDetails.dateOfDeath",actions);
        actions.sendKeys(Keys.ENTER);
        waitForAWhile(3);
    }

    public void calculateAgeAtDeath() throws AutomationException {
        String dob = driverUtil.getWebElement(DATE_OF_BIRTH).getAttribute("value");
        String dod = driverUtil.getWebElement(DATE_OF_DEATH).getAttribute("value");

        String displayedAge = driverUtil.getWebElement(AGE_AT_DEATH).getAttribute("value");

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

    public void divorcedDecreeFieldNotDisplayCheck() throws AutomationException {
        waitForInvisibleElement(By.xpath(DATE_DIVORCED_DECREE));
        WebElement dateDivorcedDecree = driverUtil.getWebElement(DATE_DIVORCED_DECREE);
        if (dateDivorcedDecree==null || !dateDivorcedDecree.isDisplayed()) {
            CommonSteps.logInfo("The Date Divorced Decree field not displayed");
        } else {
            throw new AutomationException("The Date Divorced Decree field is displayed for other selections");
        }
    }
}

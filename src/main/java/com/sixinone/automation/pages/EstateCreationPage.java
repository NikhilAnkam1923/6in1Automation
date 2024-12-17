package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class EstateCreationPage extends BasePage{

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
    private static final String MAX_CHAR_LIMIT_ERROR = "//div[@class='invalid-feedback' and contains(text(),'You have exceeded the maximum character limit of 100')]";
    private static final String SPECIAL_CHAR_ERROR = "//div[@class='invalid-feedback' and contains(text(),'Allowed special characters are , / and &')]";
    private static final String ADDRESS_LINE1 = "//input[@name='domicileAddress.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='domicileAddress.addressLine2']";
    private static final String ZIP = "//input[@name='domicileAddress.zip']";
    private static final String CITY = "//input[@name='domicileAddress.city']";
    private static final String STATE = "//label[contains(text(),'State')]//following-sibling::div//div[contains(@class, 'select__single-value')]";
    private static final String COUNTRY = "//input[@name='domicileAddress.county']";
    private static final String MUNICIPALITY = "//input[@name='domicileAddress.municipality']";
    private static final String TOWNSHIP_RADIO = "//input[@name='domicileAddress.residenceType' and @value='1']";
    private static final String BOROUGH_RADIO = "//input[@name='domicileAddress.residenceType' and @value='2']";

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
        fillField(DECEDENT_MIDDLE_NAME,"EstateCreate.middleName");
        fillField(DECEDENT_DISPLAY_NAME,"EstateCreate.displayName");
        selectSuffixOption();
        fillField(DECEDENT_ALSO_KNOWN_AS,"EstateCreate.alsoKnownAs");
        CommonSteps.logInfo("Decedent's basic information has been filled successfully.");
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

    private String getFieldValue(String locator, String attribute) throws AutomationException {
        WebElement field = driverUtil.getWebElement(locator, 5);
        if (field != null) {
            return attribute.equalsIgnoreCase("value") ? field.getAttribute("value") : field.getText().trim();
        } else {
            throw new AutomationException("Failed to locate element for locator: " + locator);
        }
    }

    public void verifyAutoFetchedFields() throws AutomationException, IOException, ParseException {
        String expectedCity = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.city").toString();
        String expectedState = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.state").toString();
        String expectedCounty = CommonUtil.getJsonPath("EstateCreate").get("EstateCreate.country").toString();
        WebDriverUtil.waitForAWhile(2);
        String actualCity = getFieldValue(CITY, "value");
        String actualState = getFieldValue(STATE, "text");
        String actualCounty = getFieldValue(COUNTRY, "value");
        if (!expectedCity.equals(actualCity) || !expectedState.equals(actualState) || !expectedCounty.equals(actualCounty)) {
            throw new AutomationException("City, State, or County values are incorrect or not fetched automatically. ");
        }
    }

    public void fillLastAddressDomicileDetails() throws AutomationException, IOException, ParseException {
        fillField(ADDRESS_LINE1, "EstateCreate.addressLine1");
        fillField(ADDRESS_LINE2, "EstateCreate.addressLine2");
        fillField(ZIP, "EstateCreate.zip");
        driverUtil.getWebElement(ADDRESS_LINE2).click();
        verifyAutoFetchedFields();
        fillField(MUNICIPALITY, "EstateCreate.municipality");
        CommonSteps.logInfo("Last Address/Domicile details have been filled successfully.");
    }

    public void verifyFieldValidations() throws AutomationException {
        WebElement addressLine1 = driverUtil.getWebElement(ADDRESS_LINE1);
        WebElement addressLine2 = driverUtil.getWebElement(ADDRESS_LINE2);
        addressLine1.clear();
        addressLine2.clear();
        addressLine1.sendKeys("A".repeat(101));
        addressLine2.sendKeys("B".repeat(101));
        driverUtil.getWebElement(ZIP).click();
        WebElement errorMaxChar = driverUtil.getWebElement(MAX_CHAR_LIMIT_ERROR);
        if (errorMaxChar == null || !errorMaxChar.isDisplayed()) {
            throw new AutomationException("Validation for exceeding max character limit is not displayed.");
        }

        addressLine1.clear();
        addressLine2.clear();
        addressLine1.sendKeys("Invalid@#$%");
        addressLine2.sendKeys("##@@Invalid");
        driverUtil.getWebElement(ZIP).click();
        WebElement errorSpecialChar = driverUtil.getWebElement(SPECIAL_CHAR_ERROR);
        if (errorSpecialChar == null || !errorSpecialChar.isDisplayed()) {
            throw new AutomationException("Validation for allowed special characters is not displayed.");
        }
        CommonSteps.logInfo("Verified Validations for all fields under last address/domicile.");
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
        CommonSteps.logInfo("Verified Township and Borough radio buttons toggle correctly.");
    }

}

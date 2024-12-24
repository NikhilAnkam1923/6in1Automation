package com.sixinone.automation.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class EstateContactsPage extends BasePage {

    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    public static final String NAME_COLUMN_XPATH ="//span[@class='k-column-title' and text()='Name']";
    public static final String ROLES_COLUMN_XPATH ="//span[@class='k-column-title' and text()='Roles']";
    public static final String ADD_CONTACT_BTN = "//button[text()='Add Contact']";
    public static final String ADD_BUTTON = "//td//button[text()='Add']";
    public static final String CREATE_NEW_INDIVIDUAL_BTN = "//button[text()='Create New Individual']";
    public static final String CREATE_NEW_ENTITY_BTN = "//button[text()='Create New Entity']";
    private static final String MODAL_NEXT_PAGE = "//div[@class='modal-content']//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String ALL_MODAL_ROWS_XPATH = "//div[@class='modal-content']//tbody//tr";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[@class='section-header mt-0' and following-sibling::div//input[@id='contact.firstName']]";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String PTIN_FIELD = "//input[@name='contact.ptin']";
    private static final String PINEFILE_FIELD = "//input[@name='contact.pin']";
    private static final String BARID_FIELD = "//input[@name='contact.barID']";
    private static final String CAF_FIELD = "//input[@name='contact.caf']";
    private static final String ADDRESS_LINE1 = "//input[@name='address.addressLine1']";
    private static final String ADDRESS_LINE2 = "//input[@name='address.addressLine2']";
    private static final String ZIP = "//input[@name='address.zip']";
    private static final String FIRST_NAME_FIELD = "//input[contains(@name,'firstName')]";
    private static final String LAST_NAME_FIELD = "//input[contains(@name,'lastName')]";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@name='contact.maidenName']";
    private static final String ENTITY_NAME_FIELD_CREATE = "//input[@name='contact.entityName']";
    private static final String SSN_FIELD = "//input[@id='contact.ssn']";
    private static final String EIN_FIELD = "//input[@id='contact.ein']";
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input[@aria-label='Filter']";
    private static final String CONTACT_NAME_IN_ESTATE_CONTACT = "//td[@aria-colindex='1' and text()='%s']";
    private static final String ESTATE_BREADCRUMB = "//a[@class='breadcrumb-item' and @href='/law-firm/estate']";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    private static final String CONTACT_NAME_IN_GLOBAL_CONTACT = "//td//a[contains(@class,'column-edit-link') and text()='%s']";
    private static final String NEW_ESTATE_CONTACT_MODAL_TITLE = "//div[contains(@class,'modal-title') and text()='Add a New Estate Contact - %s']";
    private static final String SELECT_ROLE_BTN = "//div[@class='modal-content']//button[text()='Select Role']";
    private static final String ROLE_CHECKBOX = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String ROLE_SAVE_BTN = "//div[@class='modal-content']/div[@class='modal-header']/div[text()='Select Role']/ancestor::div[@class='modal-content']//button[text()='Save']";


    public static String individualContactName;

    String getName() {
        return "";
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        WebElement tempUser = driverUtil.getWebElement("//a[text()='Jonny Vasco']");
        tempUser.click();
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
    }

    public void verifyNameAndRolesColumns() throws AutomationException {
        WebElement nameColumn = driverUtil.getWebElement(NAME_COLUMN_XPATH);
        WebElement rolesColumn = driverUtil.getWebElement(ROLES_COLUMN_XPATH);
        if (!nameColumn.isDisplayed() || !rolesColumn.isDisplayed()) {
            throw new AutomationException("Name or Roles column is not displayed in the left pane.");
        }
    }

    public void clickAddContactButton() throws AutomationException {
        driverUtil.getWebElement(ADD_CONTACT_BTN).click();
    }

    public void verifyAddButtonInGlobalContacts() throws AutomationException {
        while (true) {
            List<WebElement> addButtons = driverUtil.getWebElements(ADD_BUTTON);
            List<WebElement> rows = driverUtil.getWebElements(ALL_MODAL_ROWS_XPATH);
            if (addButtons.size() != rows.size()) {
                throw new AutomationException("Mismatch in the number of Add buttons and data rows.");
            }
            WebElement nextPageButton = driverUtil.getWebElement(MODAL_NEXT_PAGE, 2);
            if (nextPageButton == null || nextPageButton.getAttribute("aria-disabled").equals("true")) {
                break;
            }
            nextPageButton.click();
            waitForInvisibleElement(By.xpath(SPINNER));
        }
    }

    public void verifyCreateContactButtons() throws AutomationException {
        WebElement createIndividualBtn = driverUtil.getWebElement(CREATE_NEW_INDIVIDUAL_BTN);
        WebElement createEntityBtn = driverUtil.getWebElement(CREATE_NEW_ENTITY_BTN);
        if (!createIndividualBtn.isDisplayed()) {
            throw new AutomationException("Create New Individual Contact button is not displayed.");
        }
        if (!createEntityBtn.isDisplayed()) {
            throw new AutomationException("Create New Entity Contact button is not displayed.");
        }
    }

    private void fillField(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        field.clear();
        field.sendKeys(CommonUtil.getJsonPath("Create").get(jsonKey).toString());
    }

    private void fillField(String fieldLocator, String jsonKey, Actions actions) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElement(fieldLocator);
        actions.moveToElement(field)
                .click()
                .sendKeys(CommonUtil.getJsonPath("Create").get(jsonKey).toString())
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

    public void selectSuffixOption() throws AutomationException, IOException, ParseException {
        String suffixValue = CommonUtil.getJsonPath("Create").get("Create.suffix").toString();
        WebElement suffixDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Suffix"));
        suffixDropdown.click();
        WebElement suffixOption = driverUtil.getWebElement(String.format(SELECT_OPTION, suffixValue));
        suffixOption.click();
    }

    public void fillNewGlobalContactDetails(String contactType) throws AutomationException, InterruptedException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        switch (contactType) {
            case "New Individual Global Contact":
                waitForVisibleElement(By.xpath(INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(FIRST_NAME_FIELD, "Create.firstName");
                fillField(MIDDLE_NAME_FIELD, "Create.middleName");
                fillField(LAST_NAME_FIELD,"Create.lastName");
                selectSuffixOption();
                fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
                individualContactName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value")+", "+driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                fillField(ENTITY_NAME_FIELD_CREATE, "Create.entityName");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber", actions);
                fillField(WORK_NUMBER_FIELD, "Create.workNumber", actions);
                fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
                fillField(FAX_FIELD, "Create.fax", actions);
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillField(PTIN_FIELD, "Create.ptin");
                fillField(PINEFILE_FIELD, "Create.pinEFile");
                fillField(BARID_FIELD, "Create.barId");
                fillField(CAF_FIELD, "Create.caf");
                fillField(ADDRESS_LINE1, "Create.addressLine1");
                fillField(ADDRESS_LINE2, "Create.addressLine2");
                fillField(ZIP, "Create.zip");
                driverUtil.getWebElement(ZIP).sendKeys(Keys.TAB);
                WebDriverUtil.waitForAWhile(1);
                break;
            case "New Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(ENTITY_NAME_FIELD_CREATE,"Create.entityName");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                fillField(FIRST_NAME_FIELD, "Create.firstName");
                fillField(MIDDLE_NAME_FIELD, "Create.middleName");
                fillField(LAST_NAME_FIELD,"Create.lastName");
                selectSuffixOption();
                fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
                fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber", actions);
                fillField(WORK_NUMBER_FIELD, "Create.workNumber", actions);
                fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
                fillField(FAX_FIELD, "Create.fax", actions);
                fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
                fillField(PTIN_FIELD, "Create.ptin");
                fillField(PINEFILE_FIELD, "Create.pinEFile");
                fillField(BARID_FIELD, "Create.barId");
                fillField(CAF_FIELD, "Create.caf");
                fillField(ADDRESS_LINE1, "Create.addressLine1");
                fillField(ADDRESS_LINE2, "Create.addressLine2");
                fillField(ZIP, "Create.zip");
                driverUtil.getWebElement(ZIP).sendKeys(Keys.TAB);
                WebDriverUtil.waitForAWhile(1);
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void clickOnNewIndividualContactBtn() throws AutomationException {
        driverUtil.getWebElement(CREATE_NEW_INDIVIDUAL_BTN).click();
    }

    public void filterByContactName(String contactName) throws AutomationException {
        driverUtil.getWebElement(CONTACT_NAME_FILTER).click();
        driverUtil.getWebElement(CONTACT_NAME_FILTER).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public  void filterGlobalContactByName(String contactName) throws AutomationException {
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyContactInEstateContactsList() throws AutomationException {
        filterByContactName(individualContactName);
        WebElement ContactNameInEstateList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT,individualContactName));
        if(!ContactNameInEstateList.isDisplayed()){
            throw new AutomationException("Contact is not visible in the Estate Contacts list");
        }
    }

    public void verifyContactInGlobalContactsList() throws AutomationException, IOException {
//        GlobalContactPage.clickButtonClose();
        driverUtil.getWebElement(ESTATE_BREADCRUMB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        GlobalContactPage.tabNavigation("Global Contact");
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        filterGlobalContactByName(individualContactName);
        WebElement ContactNameInGlobalList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_GLOBAL_CONTACT,individualContactName));
        if(!ContactNameInGlobalList.isDisplayed()){
            throw new AutomationException("Contact is not visible in the Global Contacts list");
        }
    }

    public void verifyNewlyCreatedContactIsSelectedByDefault() throws AutomationException {
        WebElement newEstateContact = driverUtil.getWebElement(String.format(NEW_ESTATE_CONTACT_MODAL_TITLE,individualContactName));
        if(!newEstateContact.isDisplayed()){
            throw new AutomationException("Newly created contact is not selected by default");
        }
    }

    public void selectRoles() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.role").toString();

        driverUtil.getWebElement(SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX,role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        GlobalContactPage.clickButtonSave();
    }

    public void verifyRoleAssignedSuccessMessage() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Roles assigned successfully."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified Confirmation message is: " + confirmationElement.getText());
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(GlobalContactPage.CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }
}

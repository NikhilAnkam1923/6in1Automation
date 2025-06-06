package com.sixinone.automation.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.CommonUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.interactions.Actions;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.sixinone.automation.util.WebDriverUtil.*;

public class EstateContactsPage extends BasePage {

    public static final String CONFIRMATION_MESSAGE = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']//div[text()='%s']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    public static final String NAME_COLUMN_XPATH = "//span[@class='k-column-title' and text()='Name']";
    public static final String ROLES_COLUMN_XPATH = "//span[@class='k-column-title' and text()='Roles']";
    public static final String ADD_CONTACT_BTN = "//button[text()='Create']";
    public static final String ADD_BUTTON = "//button[text()='Add']";
    public static final String CREATE_NEW_INDIVIDUAL_BTN = "//button[text()='Create New Individual']";
    public static final String CREATE_NEW_ENTITY_BTN = "//button[text()='Create New Entity']";
    private static final String MODAL_NEXT_PAGE = "//div[@class='modal-content']//button[@title='Go to the next page' and not(contains(@class,'k-disabled'))]";
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String ALL_MODAL_ROWS_XPATH = "//div[@class='modal-content']//tbody//tr";
    private static final String DROPDOWN_LABEL = "//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]";
    private static final String SELECT_OPTION = "//div[contains(@class,'select__menu-list')]//div[text()='%s']";
    public static final String INDIVIDUAL_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Individual Details']";
    public static final String ENTITY_GLOBAL_CONTACT_CREATION_PAGE = "//div[text()='Entity Details']";
    private static final String FIRST_NAME_FIELD = "//input[contains(@name,'firstName')]";
    private static final String LAST_NAME_FIELD = "//input[contains(@name,'lastName')]";
    private static final String CREATE_MAIDEN_NAME_FIELD = "//input[@name='maidenName']";
    private static final String SELECTED_SUFFIX = "//label[text()='Suffix']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String SELECTED_GENDER = "//label[text()='Gender']/following-sibling::div/div/div/div[contains(@class,'select__single-value')]";
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@name='contact.maidenName']";
    private static final String ENTITY_NAME_FIELD = "//input[contains(@name,'entityName')]";
    private static final String EIN_FIELD = "//input[contains(translate(@name, 'EIN', 'ein'), 'ein')]";
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input";
    private static final String CONTACT_NAME_IN_ESTATE_CONTACT = "//td[@aria-colindex='1' and text()='%s']";
    private static final String ESTATE_BREADCRUMB = "//a[@class='breadcrumb-item' and @href='/law-firm/estate']";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input";
    public static final String MODAL_NAME_FILTER_INPUT = "//div[@class='modal-body']//th[@aria-colindex='2'] //input";
    private static final String CONTACT_NAME_IN_GLOBAL_CONTACT = "//td//a[contains(@class,'column-edit-link') and text()='%s']";
    private static final String CONTACT_NAME_IN_ADD_CONTACT_LIST = "//div[@class='modal-body']//td[@aria-colindex='2' and text()='%s']";
    private static final String NEW_ESTATE_CONTACT_MODAL_TITLE = "//div[contains(@class,'modal-title') and text()='Add a New Estate Contact - %s']";
    private static final String SELECT_ROLE_BTN = "//div[@class='modal-content']//button[text()='Select Role']";
    private static final String ESTATE_SPECIFIC_SELECT_ROLE_BTN = "//button[text()='Select Role']";
    private static final String ROLE_CHECKBOX = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input[not(@checked)]";
    private static final String ROLE_UNCHECK = "//div[@class='modal-content']//label[@class='form-check-label' and text()='%s']/preceding-sibling::input";
    private static final String ROLE_SAVE_BTN = "//div[@class='fade custom-modal center-small-modal modal show']//button[text()='Save']";
    private static final String ESTATE_SPECIFIC_FIELDS_TAB = "//div[@class='nav-item']/a[text()='Estate-Specific Fields']";
    private static final String NO_ROLES_SELECTED_NOTIFICATION = "//div[@class='modal-inner-content']/div[@class='icon-danger']/following-sibling::div/p/span[contains(text(),'You have not selected any role for this contact in this Estate.')]";
    private static final String ADDCONTACT_CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='5'] //input";
    private static final String SELECTED_ROLES_PAGE_HEADING = "//div[@class='modal-content']//div[text()='Selected Roles']";
    public static final String SAVE_BUTTON_AFTER_SELECTROLE = "//div[@class='fade custom-modal undefined modal show']//button[text()='Save']";
    private static final String NO_ROLES_SAVE_BTN = "//button[@class='btn btn-danger' and text()='Save']";
    private static final String REMOVE_CONTACT_FROM_ESTATE_BTN = "//button[@aria-label='Delink']";
    private static final String REMOVE_BUTTON = "//button[@class='btn btn-danger' and text()='Remove']";
    public static final String CONTACTTYPEPAGE = "//div[text()='%s']";
    public static final String ERROR_MSG = "//span[text()='%s']";
    private static final String NAME_AND_ROLE_ROW = "//tbody//tr/td[text()='%s']/following-sibling::td[text()='%s']";
    private static final String CANCEL_BTN = "//button[text()='Cancel']";
    private static final String CLOSE_BTN_IN_FOOTER = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String SELECT_ADDRESS_PAGE = "//div[text()='Select Address']";
    private static final String ADDRESS_SELECT_BTN = "//input[@name='contact-selection']";
    private static final String SET_ADDRESS_BTN = "//button[text()='Set Address']";
    private static final String SET_ADDRESS_SUCCESSFUL_MSG = "//div[text()='Address successfully set for the Estate contact.']";
    private static final String ERROR_POPUP_SAVEBTN = "//button[@class='btn btn-danger']";
    public static final String CLOSE_BUTTON = "//button[text()='Close']";
    public static final String GLOBAL_FIELD = "//a[@role='tab' and text()='Global Fields']";
    private static final String DATE_OF_BIRTH_FIELD = "//label[text()='Date of Birth']/following-sibling::div//div//input";
    private static final String PLACE_OF_BIRTH = "//input[@name='contact.placeOfBirth']";
    private static final String PHONE_NUMBER_FIELD = "//input[@name='contact.phoneNumber']";
    private static final String WORK_NUMBER_FIELD = "//input[@name='contact.workPhone']";
    private static final String EMAIL_ADDRESS_FIELD = "//input[@name='contact.emailAddress']";
    private static final String FAX_FIELD = "//input[@name='contact.faxNumber']";
    private static final String SSN_FIELD = "//input[@name='contact.SSN']";
    private static final String ADDED_CONTACT_NAME = "//div[@class='modal-body']//tr[@aria-rowindex='3']//td[@aria-colindex='2']";
    private static final String ADDED_ENTITY_CONTACT_NAME = "//div[@class='modal-body']//tr[@aria-rowindex='3']//td[@aria-colindex='4']";

    public static String firstName;
    public static String lastName;
    public static String individualContactName;
    public static String entityContactName;
    static String enteredEIN;
    static String enteredSSN;
    static String enteredEntityEIN;
    static String enteredEntitySSN;
    static String addedIndividualContactName;
    static String addedEntityContactName;
    static String expectedEntityFirstName;
    static String expectedEntityMiddleName;
    static String expectedEntityLastName;
    static String expectedEntitySuffix;
    static String expectedEntityMaidenName;
    static String expectedEntityFirmName;
    static String expectedEntityGender;
    static String expectedEntityDateOfBirth;
    static String expectedEntityPlaceOfBirth;
    static String expectedEntityEIN;
    static String expectedEntityPhone;
    static String expectedEntityWorkPhone;
    static String expectedEntityEmailAddress;
    static String expectedEntityFax;
    static String expectedEntitySSN;
    static String expectedIndividualFirstName;
    static String expectedIndividualMiddleName;
    static String expectedIndividualLastName;
    static String expectedIndividualSuffix;
    static String expectedIndividualMaidenName;
    static String expectedIndividualFirmName;
    static String expectedIndividualGender;
    static String expectedIndividualDateOfBirth;
    static String expectedIndividualPlaceOfBirth;
    static String expectedIndividualEIN;
    static String expectedIndividualPhone;
    static String expectedIndividualWorkPhone;
    static String expectedIndividualEmailAddress;
    static String expectedIndividualFax;
    static String expectedIndividualSSN;



    String getName() {
        return "";
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        WebElement tempUser = driverUtil.getWebElement("//a[text()='John, Baby Arik Jr.']");
        tempUser.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
    }

    public void verifyNameAndRolesColumns() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        WebElement nameColumn = driverUtil.getWebElement(NAME_COLUMN_XPATH);
        WebElement rolesColumn = driverUtil.getWebElement(ROLES_COLUMN_XPATH);
        if (!nameColumn.isDisplayed() || !rolesColumn.isDisplayed()) {
            throw new AutomationException("Name or Roles column is not displayed in the left pane.");
        }
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickAddContactButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ADD_CONTACT_BTN));
        driverUtil.getWebElement(ADD_CONTACT_BTN).click();
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    private boolean clickNextPage() throws AutomationException {
        WebElement nextPageButton = driverUtil.getWebElement(MODAL_NEXT_PAGE, 2);
        if (nextPageButton == null) {
            return false;
        }
        nextPageButton.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        return true;
    }

    public void verifyAddButtonInGlobalContacts() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile(2);
        do {
            waitForInvisibleElement(By.xpath(SPINNER));
            List<WebElement> addButtons = driverUtil.getWebElements(ADD_BUTTON);
            List<WebElement> rows = driverUtil.getWebElements(ALL_MODAL_ROWS_XPATH);
            if (addButtons.size() != rows.size()) {
                throw new AutomationException("Mismatch in the number of Add buttons and data rows.");
            }
        } while (clickNextPage());
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

    private void fillFieldWithKeyStrokes(String fieldLocator, String jsonKey) throws AutomationException, IOException, ParseException {
        WebElement field = driverUtil.getWebElementAndScroll(fieldLocator);
        String value = CommonUtil.getJsonPath("Create").get(jsonKey).toString();
        for (char c : value.toCharArray()) {
            field.sendKeys(String.valueOf(c));
        }
        driverUtil.getWebElement("//body").click();
        WebDriverUtil.waitForAWhile();
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

    public void selectGenderOption() throws AutomationException, IOException, ParseException {
        String genderValue = CommonUtil.getJsonPath("Create").get("Create.gender").toString();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement genderDropdown = driverUtil.getWebElement(String.format(DROPDOWN_LABEL, "Gender"));
        genderDropdown.click();
        WebElement genderOption = driverUtil.getWebElement(String.format(SELECT_OPTION, genderValue));
        genderOption.click();
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
                fillField(LAST_NAME_FIELD, "Create.lastName");
                selectSuffixOption();
                fillField(CREATE_MAIDEN_NAME_FIELD, "Create.maidenName");
                firstName = driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                lastName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value");
                individualContactName = lastName + ", " + firstName;
                break;
            case "New Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(ENTITY_NAME_FIELD, "Create.entityName");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                entityContactName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                enteredEntityEIN = driverUtil.getWebElement(EIN_FIELD).getAttribute("value");
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void clickOnNewIndividualContactBtn() throws AutomationException {
        clickAddContactButton();
        driverUtil.getWebElement(CREATE_NEW_INDIVIDUAL_BTN).click();
    }

    public void clickOnNewEntityContactBtn() throws AutomationException {
        clickAddContactButton();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(CREATE_NEW_ENTITY_BTN).click();
    }

    public void clearField(String fieldXpath) throws AutomationException {
        WebElement fieldElement = driverUtil.getWebElement(fieldXpath);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public void filterByContactName(String contactName) throws AutomationException {
        driverUtil.getWebElement(CONTACT_NAME_FILTER).click();
        clearField(CONTACT_NAME_FILTER);
        driverUtil.getWebElement(CONTACT_NAME_FILTER).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void filterGlobalContactByName(String contactName) throws AutomationException {
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void filterContactByNameInAddContact(String contactName) throws AutomationException {
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(MODAL_NAME_FILTER_INPUT).click();
        driverUtil.getWebElement(MODAL_NAME_FILTER_INPUT).sendKeys(contactName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void verifyContactInEstateContactsList(String contactType) throws AutomationException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));

        switch (contactType) {
            case "Individual":
                filterByContactName(individualContactName);
                WebElement individualContactNameInEstateList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, individualContactName));
                if (!individualContactNameInEstateList.isDisplayed()) {
                    throw new AutomationException("Individual Contact is not visible in the Estate Contacts list");
                }
                break;
            case "Entity":
                filterByContactName(entityContactName);
                WebElement entityContactNameInEstateList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, entityContactName));
                if (!entityContactNameInEstateList.isDisplayed()) {
                    throw new AutomationException("Entity Contact is not visible in the Estate Contacts list");
                }
                break;
        }
    }

    public void verifyContactInGlobalContactsList() throws AutomationException, IOException {
        clickButtonClose();
        driverUtil.getWebElement(ESTATE_BREADCRUMB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        GlobalContactPage.tabNavigation("Global Contact");
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        filterGlobalContactByName(individualContactName);
        WebElement ContactNameInGlobalList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_GLOBAL_CONTACT, individualContactName));
        if (ContactNameInGlobalList.isDisplayed()) {
            CommonSteps.logInfo("verified same contact is visible in the Global Contacts list");
        } else {
            throw new AutomationException("Contact is not visible in the Global Contacts list");
        }
    }

    public static void clickButtonClose() throws AutomationException {
        driverUtil.getWebElement(CLOSE_BUTTON).click();
    }

    public void verifyNewlyCreatedContactIsSelectedByDefault(String contactType) throws AutomationException {
        String contactName;
        if(contactType.equals("Individual")){
            contactName = individualContactName;
        } else {
            contactName = entityContactName;
        }
        WebElement newEstateContact = driverUtil.getWebElement(String.format(NEW_ESTATE_CONTACT_MODAL_TITLE, contactName));
        if (!newEstateContact.isDisplayed()) {
            throw new AutomationException("Newly created contact is not selected by default");
        }
    }

    public void selectRoleButton() throws AutomationException {
        waitForVisibleElement(By.xpath(SELECT_ROLE_BTN));
        waitForAWhile(3);
        driverUtil.getWebElement(SELECT_ROLE_BTN).click();
    }

    public void selectRoles() throws AutomationException, IOException, ParseException {
        String roleAccountant = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAccountant").toString();
        driverUtil.getWebElement(String.format(ROLE_CHECKBOX, roleAccountant)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
    }

    public void verifyRoleAssignedSuccessfully(String contactType) throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAccountant").toString();
        String addedContactName;
        if(contactType.equals("Individual")){
            addedContactName = addedIndividualContactName;
        } else {
            addedContactName = addedEntityContactName;
        }
        filterByContactName(addedContactName);
        waitForVisibleElement(By.xpath(String.format(NAME_AND_ROLE_ROW, addedContactName, role)));
        WebElement nameAndRole = driverUtil.getWebElement(String.format(NAME_AND_ROLE_ROW, addedContactName, role));
        if (!nameAndRole.isDisplayed()) {
            throw new AutomationException("Role is not assigned to for the contact.");
        }
    }

    public void selectEstateContact() throws AutomationException, IOException, ParseException {
        String lastName = CommonUtil.getJsonPath("Create").get("Create.lastName").toString();
        String firstName = CommonUtil.getJsonPath("Create").get("Create.firstName").toString();
        String individualName = lastName + ", " + firstName;
        filterByContactName(individualName);
        driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, individualName)).click();
    }

    public void clickOnEstateSpecificFields() throws AutomationException {
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
    }

    public void uncheckTheCheckedRole() throws AutomationException, IOException, ParseException {
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAccountant").toString();
        driverUtil.getWebElement(ESTATE_SPECIFIC_SELECT_ROLE_BTN).click();
        driverUtil.getWebElement(String.format(ROLE_UNCHECK, role)).click();
        driverUtil.getWebElement(ROLE_SAVE_BTN).click();
    }

    public void verifyNotificationIsDisplayedOnRemovingTheRole() throws AutomationException {
        WebElement rolesNotification = driverUtil.getWebElement(NO_ROLES_SELECTED_NOTIFICATION);
        if (!rolesNotification.isDisplayed()) {
            throw new AutomationException("Notification is not displayed on removing the role");
        }
    }

    public void clickOnAddButtonForSpecificContactType(String contactType) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile(2);
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).click();
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).sendKeys(contactType);
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForAWhile(2);
        if(contactType.equals("Individual")){
            addedIndividualContactName = driverUtil.getWebElement(ADDED_CONTACT_NAME).getText();
        } else {
            addedEntityContactName = driverUtil.getWebElement(ADDED_ENTITY_CONTACT_NAME).getText();
        }
        driverUtil.getWebElement(ADD_BUTTON).click();
    }

    public void verifySelectRolePage() throws AutomationException {
        waitForVisibleElement(By.xpath(SELECTED_ROLES_PAGE_HEADING));
        WebElement selectedRolePageHeading = driverUtil.getWebElement(SELECTED_ROLES_PAGE_HEADING);
        if (selectedRolePageHeading != null && selectedRolePageHeading.isDisplayed()) {
            CommonSteps.logInfo("user landed on Select Role page");
        } else {
            throw new AutomationException("user fail to lands on Select Role page");
        }
    }

    public void saveWithoutRole() throws AutomationException {
        driverUtil.getWebElement(NO_ROLES_SAVE_BTN).click();
        driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully."));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles updated successfully.")));
    }

    public void verifyRemoveContactButtonEnabled() throws AutomationException {
        WebElement removeContactButton = driverUtil.getWebElement(REMOVE_CONTACT_FROM_ESTATE_BTN);
        if (!removeContactButton.isEnabled()) {
            throw new AutomationException("The 'Remove Contact from Estate' button is not enabled.");
        }
    }

    public void verifyGlobalContactCreated() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact created successfully."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified that Global Contact is created successfully.");
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact created successfully.")));
    }

    public void clickOnRemoveContactFromEstateBtn() throws AutomationException {
        driverUtil.getWebElement(REMOVE_CONTACT_FROM_ESTATE_BTN).click();
    }

    public void clickOnRemoveBtn() throws AutomationException {
        driverUtil.getWebElement(REMOVE_BUTTON).click();
    }

    public void verifyContactRemovedSuccessMessage() throws AutomationException {
        WebElement confirmationElement = driverUtil.getWebElementAndScroll(String.format(CONFIRMATION_MESSAGE, "Contact successfully delinked from the Estate."));
        if (confirmationElement == null || !confirmationElement.isDisplayed()) {
            throw new AutomationException("Confirmation message not displayed");
        }
        CommonSteps.logInfo("Verified that the Contact is removed from estate successfully.");
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact successfully delinked from the Estate.")));

    }

    public void verifyContactInAddContactsList() throws AutomationException {
        filterContactByNameInAddContact(individualContactName);
        WebElement ContactNameInAddContactList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ADD_CONTACT_LIST, individualContactName));
        if (!ContactNameInAddContactList.isDisplayed()) {
            throw new AutomationException("Removed contact is not displayed in the Add Contact list.");
        }
    }

    public void verifyContactPage(String contactPage) throws AutomationException {
        WebElement contactPageType = driverUtil.getWebElement(String.format(CONTACTTYPEPAGE, contactPage), 2);
        if (contactPageType != null && contactPageType.isDisplayed()) {
            CommonSteps.logInfo("verified user is on create contact page for" + " contactPage " + "type");
        } else {
            throw new AutomationException("user is fail to lands on create contact page for" + " contactPage " + "type");
        }

    }

    public void clickOnAfterSelectRoleSaveButton() throws AutomationException {
        driverUtil.getWebElement(SAVE_BUTTON_AFTER_SELECTROLE).click();
    }

    public void errorMsgCheck(String expectedErrorMessage) throws AutomationException {
        WebElement errorMessage = driverUtil.getWebElementAndScroll(String.format(ERROR_MSG, expectedErrorMessage));
        String actualErrorMessage = errorMessage.getText();
        if (!actualErrorMessage.equals(expectedErrorMessage)) {
            throw new AutomationException("Expected error message: '" + expectedErrorMessage + "' but found: '" + actualErrorMessage + "'");
        } else {
            CommonSteps.logInfo("Verified error thrown for saving contact without selecting any role");
        }
    }

    public void verifyRemoveContactButtonDisabled() throws AutomationException {
        WebElement removeContactButton = driverUtil.getWebElement(REMOVE_CONTACT_FROM_ESTATE_BTN);
        if (removeContactButton.isEnabled()) {
            throw new AutomationException("The 'Remove Contact from Estate' button is enabled.");
        }

    }

    public void handleAddressSelection() throws AutomationException {
        WebElement selectAddressPage = driverUtil.getWebElement(SELECT_ADDRESS_PAGE, 3);
        if (selectAddressPage != null && selectAddressPage.isDisplayed()) {
            CommonSteps.logInfo("The contact has multiple addresses to select.");

            WebDriverUtil.waitForAWhile(2);
            WebElement addressSelectBtn = driverUtil.getWebElement(ADDRESS_SELECT_BTN);
            if (addressSelectBtn != null) {
                addressSelectBtn.click();
                CommonSteps.logInfo("Address selection button clicked.");

                WebElement setAddressBtn = driverUtil.getWebElement(SET_ADDRESS_BTN);
                if (setAddressBtn != null) {
                    WebDriverUtil.waitForAWhile(2);
                    setAddressBtn.click();
                    CommonSteps.logInfo("Set Address button clicked.");

                    waitForVisibleElement(By.xpath(SET_ADDRESS_SUCCESSFUL_MSG));
                    WebElement successMsg = driverUtil.getWebElement(SET_ADDRESS_SUCCESSFUL_MSG);
                    if (successMsg != null && successMsg.isDisplayed()) {
                        CommonSteps.logInfo("Address is set successfully.");
                        waitForInvisibleElement(By.xpath(SET_ADDRESS_SUCCESSFUL_MSG));
                    } else {
                        throw new AutomationException("Address is not set successfully.");
                    }
                } else {
                    throw new AutomationException("Set Address button not found.");
                }
            } else {
                throw new AutomationException("Address select button not found.");
            }
        } else {
            CommonSteps.logInfo("The contact does not have multiple addresses. Skipping address selection.");
        }
    }

    public void clickOnErrorPopUpSaveButton() throws AutomationException {
        waitForVisibleElement(By.xpath(ERROR_POPUP_SAVEBTN));
        driverUtil.getWebElement(ERROR_POPUP_SAVEBTN).click();
    }

    public void verifyWithoutSelectingRoleContactSavedMsg() throws AutomationException {
        driverUtil.getWebElement(String.format(CONFIRMATION_MESSAGE, "Contact has been successfully added to the estate without any roles."));
        CommonSteps.logInfo("Verified that contact can be saved without selecting any role.");
        CommonSteps.takeScreenshot();
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact has been successfully added to the estate without any roles.")));
    }

    public void fillsOtherBasicDetailsBirthDetailsAndContactInformation() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomEINSuffix = String.format("%07d", (int) (Math.random() * 10000000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));
        String randomEIN = String.format("%02d-%07d", (int) (Math.random() * 100), Integer.parseInt(randomEINSuffix));

        WebDriverUtil.waitForAWhile();
        clearField(ENTITY_NAME_FIELD);
        fillFieldWithKeyStrokes(ENTITY_NAME_FIELD, "Create.entityName");
        fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
        enteredEIN=driverUtil.getWebElement(EIN_FIELD).getAttribute("value");
        selectGenderOption();
        fillField(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth");
        fillField(PLACE_OF_BIRTH,"EstateContact.placeOfBirth");
        fillField(WORK_NUMBER_FIELD, "Create.workNumber",actions);
        fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber",actions);
        clearField(EMAIL_ADDRESS_FIELD);
        fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
        fillField(FAX_FIELD, "Create.fax",actions);
        fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
        enteredSSN=driverUtil.getWebElement(SSN_FIELD).getAttribute("value");

        waitForAWhile(2);
        expectedIndividualFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        expectedIndividualMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        expectedIndividualLastName = getFieldValue(LAST_NAME_FIELD, "value");
        expectedIndividualSuffix = getFieldValue(SELECTED_SUFFIX, "text");
        expectedIndividualMaidenName = getFieldValue(MAIDEN_NAME_FIELD, "value");
        expectedIndividualFirmName = getFieldValue(ENTITY_NAME_FIELD, "value");
        expectedIndividualGender = getFieldValue(SELECTED_GENDER, "text");
        expectedIndividualDateOfBirth = getFieldValue(DATE_OF_BIRTH_FIELD, "value");
        expectedIndividualPlaceOfBirth = getFieldValue(PLACE_OF_BIRTH, "value");
        expectedIndividualEIN = getFieldValue(EIN_FIELD, "value");
        expectedIndividualPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        expectedIndividualWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        expectedIndividualEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        expectedIndividualFax = getFieldValue(FAX_FIELD, "value");
        expectedIndividualSSN = getFieldValue(SSN_FIELD, "value");
    }

    private static void verifyField(String fieldName, String expectedValue, String actualValue) throws AutomationException {
        if (!expectedValue.equals(actualValue)) {
            throw new AutomationException(fieldName + " is incorrect or not fetched correctly. Expected: " + expectedValue + ", but got: " + actualValue);
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

    public void verifyIndividualGlobalContactDetailsAutoSaved() throws IOException, ParseException, AutomationException {
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(GLOBAL_FIELD).click();

        WebDriverUtil.waitForAWhile(1);
        String actualFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        String actualMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        String actualLastName = getFieldValue(LAST_NAME_FIELD, "value");
        String actualSuffix = getFieldValue(SELECTED_SUFFIX, "text");
        String actualMaidenName = getFieldValue(MAIDEN_NAME_FIELD, "value");
        String actualFirmName = getFieldValue(ENTITY_NAME_FIELD, "value");
        String actualGender = getFieldValue(SELECTED_GENDER,"text");
        String actualDateOfBirth = getFieldValue(DATE_OF_BIRTH_FIELD,"value");
        String actualPlaceOfBirth = getFieldValue(PLACE_OF_BIRTH,"value");
        String actualEIN = getFieldValue(EIN_FIELD, "value");
        String actualPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        String actualWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        String actualEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        String actualFax = getFieldValue(FAX_FIELD, "value");
        String actualSSN = getFieldValue(SSN_FIELD, "value");

        verifyField("First Name", expectedIndividualFirstName, actualFirstName);
        verifyField("Middle Name", expectedIndividualMiddleName, actualMiddleName);
        verifyField("Last Name", expectedIndividualLastName, actualLastName);
        verifyField("Suffix", expectedIndividualSuffix, actualSuffix);
        verifyField("Maiden Name",expectedIndividualMaidenName,actualMaidenName);
        verifyField("Firm Name",expectedIndividualFirmName,actualFirmName);
        verifyField("Gender",expectedIndividualGender,actualGender);
        verifyField("Date of Birth",expectedIndividualDateOfBirth,actualDateOfBirth);
        verifyField("Place of Birth",expectedIndividualPlaceOfBirth,actualPlaceOfBirth);
        verifyField("EIN",expectedIndividualEIN,actualEIN);
        verifyField("Phone",expectedIndividualPhone,actualPhone);
        verifyField("Work Phone",expectedIndividualWorkPhone,actualWorkPhone);
        verifyField("Email Address",expectedIndividualEmailAddress,actualEmailAddress);
        verifyField("Fax",expectedIndividualFax,actualFax);
        verifyField("SSN",expectedIndividualSSN,actualSSN);
    }

    public void fillsOtherBasicDetailsBirthDetailsAndContactInformationForEntity() throws AutomationException, IOException, ParseException {
        Actions actions = new Actions(DriverFactory.drivers.get());
        String randomSSNSuffix = String.format("%04d", (int) (Math.random() * 10000));
        String randomSSN = String.format("%03d-%02d-%04d", (int) (Math.random() * 1000), (int) (Math.random() * 100), Integer.parseInt(randomSSNSuffix));

        clearField(FIRST_NAME_FIELD);
        fillFieldWithKeyStrokes(FIRST_NAME_FIELD, "Create.firstName");
        waitForInvisibleElement(By.xpath(SPINNER));
        clearField(LAST_NAME_FIELD);
        fillFieldWithKeyStrokes(LAST_NAME_FIELD, "Create.lastName");
        waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        clearField(MIDDLE_NAME_FIELD);
        fillField(MIDDLE_NAME_FIELD, "Create.middleName");
        clearField(MAIDEN_NAME_FIELD);
        fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
        selectSuffixOption();
        clearField(DATE_OF_BIRTH_FIELD);
        fillFieldWithKeyStrokes(DATE_OF_BIRTH_FIELD, "EstateCreate.dateOfBirth");
        selectGenderOption();
        clearField(PLACE_OF_BIRTH);
        fillField(PLACE_OF_BIRTH,"EstateContact.placeOfBirth");
        fillField(WORK_NUMBER_FIELD, "Create.workNumber",actions);
        fillField(PHONE_NUMBER_FIELD, "Create.phoneNumber",actions);
        clearField(EMAIL_ADDRESS_FIELD);
        fillField(EMAIL_ADDRESS_FIELD, "Create.emailId");
        fillField(FAX_FIELD, "Create.fax",actions);
        fillFieldWithRandom(SSN_FIELD, randomSSN, actions);
        enteredEntitySSN=driverUtil.getWebElement(SSN_FIELD).getAttribute("value");

        WebDriverUtil.waitForAWhile(2);
        expectedEntityFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        expectedEntityMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        expectedEntityLastName = getFieldValue(LAST_NAME_FIELD, "value");
        expectedEntitySuffix = getFieldValue(SELECTED_SUFFIX, "text");
        expectedEntityMaidenName = getFieldValue(MAIDEN_NAME_FIELD, "value");
        expectedEntityFirmName = getFieldValue(ENTITY_NAME_FIELD, "value");
        expectedEntityGender = getFieldValue(SELECTED_GENDER, "text");
        expectedEntityDateOfBirth = getFieldValue(DATE_OF_BIRTH_FIELD, "value");
        expectedEntityPlaceOfBirth = getFieldValue(PLACE_OF_BIRTH, "value");
        expectedEntityEIN = getFieldValue(EIN_FIELD, "value");
        expectedEntityPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        expectedEntityWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        expectedEntityEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        expectedEntityFax = getFieldValue(FAX_FIELD, "value");
        expectedEntitySSN = getFieldValue(SSN_FIELD, "value");
    }

    public void verifyEntityGlobalContactDetailsAutoSaved() throws AutomationException, IOException, ParseException {
        driverUtil.getWebElement(ESTATE_SPECIFIC_FIELDS_TAB).click();
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(GLOBAL_FIELD).click();

        WebDriverUtil.waitForAWhile(1);
        String actualFirstName = getFieldValue(FIRST_NAME_FIELD, "value");
        String actualMiddleName = getFieldValue(MIDDLE_NAME_FIELD, "value");
        String actualLastName = getFieldValue(LAST_NAME_FIELD, "value");
        String actualSuffix = getFieldValue(SELECTED_SUFFIX, "text");
        String actualMaidenName = getFieldValue(MAIDEN_NAME_FIELD, "value");
        String actualFirmName = getFieldValue(ENTITY_NAME_FIELD, "value");
        String actualGender = getFieldValue(SELECTED_GENDER,"text");
        String actualDateOfBirth = getFieldValue(DATE_OF_BIRTH_FIELD,"value");
        String actualPlaceOfBirth = getFieldValue(PLACE_OF_BIRTH,"value");
        String actualEIN = getFieldValue(EIN_FIELD, "value");
        String actualPhone = getFieldValue(PHONE_NUMBER_FIELD, "value");
        String actualWorkPhone = getFieldValue(WORK_NUMBER_FIELD, "value");
        String actualEmailAddress = getFieldValue(EMAIL_ADDRESS_FIELD, "value");
        String actualFax = getFieldValue(FAX_FIELD, "value");
        String actualSSN = getFieldValue(SSN_FIELD, "value");

        verifyField("First Name", expectedEntityFirstName, actualFirstName);
        verifyField("Middle Name", expectedEntityMiddleName, actualMiddleName);
        verifyField("Last Name", expectedEntityLastName, actualLastName);
        verifyField("Suffix", expectedEntitySuffix, actualSuffix);
        verifyField("Maiden Name",expectedEntityMaidenName,actualMaidenName);
        verifyField("Firm Name",expectedEntityFirmName,actualFirmName);
        verifyField("Gender",expectedEntityGender,actualGender);
        verifyField("Date of Birth",expectedEntityDateOfBirth,actualDateOfBirth);
        verifyField("Place of Birth",expectedEntityPlaceOfBirth,actualPlaceOfBirth);
        verifyField("EIN",expectedEntityEIN,actualEIN);
        verifyField("Phone",expectedEntityPhone,actualPhone);
        verifyField("Work Phone",expectedEntityWorkPhone,actualWorkPhone);
        verifyField("Email Address",expectedEntityEmailAddress,actualEmailAddress);
        verifyField("Fax",expectedEntityFax,actualFax);
        verifyField("SSN",expectedEntitySSN,actualSSN);
    }
}



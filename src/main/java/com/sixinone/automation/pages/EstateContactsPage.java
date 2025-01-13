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
import java.util.Objects;

import static com.sixinone.automation.util.WebDriverUtil.*;

public class EstateContactsPage extends BasePage {

    public static final String CONFIRMATION_MESSAGE = "//div[text()='%s']";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    public static final String NAME_COLUMN_XPATH = "//span[@class='k-column-title' and text()='Name']";
    public static final String ROLES_COLUMN_XPATH = "//span[@class='k-column-title' and text()='Roles']";
    public static final String ADD_CONTACT_BTN = "//button[text()='Add Contact']";
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
    private static final String MIDDLE_NAME_FIELD = "//input[@name='contact.middleName']";
    private static final String MAIDEN_NAME_FIELD = "//input[@id='contact.maidenName']";
    private static final String ENTITY_NAME_FIELD = "//input[contains(@name,'entityName')]";
    private static final String EIN_FIELD = "//input[contains(translate(@name, 'EIN', 'ein'), 'ein')]";
    private static final String CONTACT_NAME_FILTER = "//th[@aria-colindex='1']//input[@aria-label='Filter']";
    private static final String CONTACT_NAME_IN_ESTATE_CONTACT = "//td[@aria-colindex='1' and text()='%s']";
    private static final String ESTATE_BREADCRUMB = "//a[@class='breadcrumb-item' and @href='/law-firm/estate']";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String MODAL_NAME_FILTER_INPUT = "//div[@class='modal-body']//th[@aria-colindex='2'] //input[@aria-label='Filter']";
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
    private static final String ADDCONTACT_CONTACT_TYPE_FILTER_INPUT = "//th[@aria-colindex='5'] //input[@aria-label='Filter']";
    private static final String SELECTED_ROLES_PAGE_HEADING = "//div[@class='modal-content']//div[text()='Selected Roles']";
    public static final String SAVE_BUTTON_AFTER_SELECTROLE = "//div[@class='fade custom-modal undefined modal show']//button[text()='Save']";
    private static final String NO_ROLES_SAVE_BTN = "//button[@class='btn btn-danger' and text()='Save']";
    private static final String REMOVE_CONTACT_FROM_ESTATE_BTN = "//button[@aria-label='Delink' and text()='Remove Contact from Estate']";
    private static final String REMOVE_BUTTON = "//button[@class='btn btn-danger' and text()='Remove']";
    public static final String CONTACTTYPEPAGE = "//div[text()='%s']";
    public static final String ERROR_MSG = "//span[text()='%s']";
    private static final String NAME_AND_ROLE_ROW = "//tbody//tr/td[text()='%s']/following-sibling::td[text()='%s']";
    private static final String CANCEL_BTN = "//button[text()='Cancel']";
    private static final String CLOSE_BTN_IN_FOOTER = "//div[@class='modal-footer']//button[text()='Close']";
    private static final String SELECT_ADDRESS_PAGE = "//div[text()='Select Address']";
    private static final String ADDRESS_SELECT_BTN = "//input[@aria-label='radio-792']";
    private static final String SET_ADDRESS_BTN = "//button[text()='Set Address']";
    private static final String SET_ADDRESS_SUCCESSFUL_MSG = "//div[text()='Address successfully set for the Estate contact.']";
    private static final String ERROR_POPUP_SAVEBTN = "//button[@class='btn btn-danger']";
    public static final String CLOSE_BUTTON = "//button[text()='Close']";
    public static final String GLOBAL_FIELD = "//a[text()='Global Fields']";

    public static String individualContactName;
    public static String entityContactName;

    String getName() {
        return "";
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        WebElement tempUser = driverUtil.getWebElement("//a[text()='Baby John']");
        tempUser.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
    }

    public void verifyNameAndRolesColumns() throws AutomationException {
        WebElement nameColumn = driverUtil.getWebElement(NAME_COLUMN_XPATH);
        WebElement rolesColumn = driverUtil.getWebElement(ROLES_COLUMN_XPATH);
        if (!nameColumn.isDisplayed() || !rolesColumn.isDisplayed()) {
            throw new AutomationException("Name or Roles column is not displayed in the left pane.");
        }
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    public void clickAddContactButton() throws AutomationException {
        driverUtil.getWebElement(ADD_CONTACT_BTN).click();
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
        do {
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
                fillField(MAIDEN_NAME_FIELD, "Create.maidenName");
                individualContactName = driverUtil.getWebElement(LAST_NAME_FIELD).getAttribute("value") + ", " + driverUtil.getWebElement(FIRST_NAME_FIELD).getAttribute("value");
                break;
            case "New Entity Global Contact":
                waitForVisibleElement(By.xpath(ENTITY_GLOBAL_CONTACT_CREATION_PAGE));
                fillField(ENTITY_NAME_FIELD, "Create.entityName");
                fillFieldWithRandom(EIN_FIELD, randomEIN, actions);
                entityContactName = driverUtil.getWebElement(ENTITY_NAME_FIELD).getAttribute("value");
                break;
            default:
                throw new AutomationException("Unknown contact type: " + contactType);
        }
    }

    public void clickOnNewIndividualContactBtn() throws AutomationException {
//        driverUtil.getWebElement(CANCEL_BTN).click();
//        driverUtil.getWebElement(CLOSE_BTN_IN_FOOTER).click();
        clickAddContactButton();
        driverUtil.getWebElement(CREATE_NEW_INDIVIDUAL_BTN).click();
    }

    public void clickOnNewEntityContactBtn() throws AutomationException {
        clickAddContactButton();
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
            case "Entity":
                filterByContactName(entityContactName);
                WebElement entityContactNameInEstateList = driverUtil.getWebElement(String.format(CONTACT_NAME_IN_ESTATE_CONTACT, entityContactName));
                if (!entityContactNameInEstateList.isDisplayed()) {
                    throw new AutomationException("Entity Contact is not visible in the Estate Contacts list");
                }
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

    public void verifyNewlyCreatedContactIsSelectedByDefault() throws AutomationException {
        WebElement newEstateContact = driverUtil.getWebElement(String.format(NEW_ESTATE_CONTACT_MODAL_TITLE, individualContactName));
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
        String lastName = CommonUtil.getJsonPath("Create").get("Create.lastName").toString();
        String firstName = CommonUtil.getJsonPath("Create").get("Create.firstName").toString();
        String individualName = lastName + ", " + firstName;
        String entityName = CommonUtil.getJsonPath("Create").get("Create.entityName").toString();
        String role = CommonUtil.getJsonPath("EstateContact").get("EstateContact.roleAccountant").toString();
        String displayName = contactType.equals("Individual") ? individualName : entityName;

        filterByContactName(displayName);
        WebElement nameAndRole = driverUtil.getWebElement(String.format(NAME_AND_ROLE_ROW, displayName, role));
        if (!nameAndRole.isDisplayed()) {
            throw new AutomationException("Role is not assigned to for the contact.");
        }
    }

    public void selectEstateContact() throws AutomationException, IOException, ParseException {
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForInvisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Roles assigned successfully.")));
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
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).click();
        driverUtil.getWebElement(ADDCONTACT_CONTACT_TYPE_FILTER_INPUT).sendKeys(contactType);
        waitForAWhile(2);
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
        waitForVisibleElement(By.xpath(String.format(CONFIRMATION_MESSAGE, "Contact created successfully.")));
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

            WebElement addressSelectBtn = driverUtil.getWebElement(ADDRESS_SELECT_BTN);
            if (addressSelectBtn != null) {
                addressSelectBtn.click();
                CommonSteps.logInfo("Address selection button clicked.");

                WebElement setAddressBtn = driverUtil.getWebElement(SET_ADDRESS_BTN);
                if (setAddressBtn != null) {
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
}



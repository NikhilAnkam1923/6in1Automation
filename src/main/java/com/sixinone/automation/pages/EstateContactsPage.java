package com.sixinone.automation.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.CommonUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.sixinone.automation.util.WebDriverUtil;

import java.io.IOException;
import java.util.List;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;

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

}

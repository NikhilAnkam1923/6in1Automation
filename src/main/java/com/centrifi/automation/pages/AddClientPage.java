package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.centrifi.automation.util.WebDriverUtil.waitForVisibleElement;

public class AddClientPage extends BasePage {

    private static Map<String, String> clientDetails = new LinkedHashMap<>();
    private static final String CLIENT_NAME_INPUT = "//input[@name='name']";
    private static final String PRIMARY_CONTACT_NAME = "//input[@name='primaryContactName']";
    private static final String EMAIL = "//label[text()='Primary Contact Email']//following-sibling::div/input";
    private static final String PHONE = "//label[text()='Primary Contact Phone']//following-sibling::div/input";
    private static final String CONTACT_TITLE = "//div[@role='listbox']//div[text()='%s']";
    private static final String SELECT_TITLE = "//*[contains(text(),'Primary Contact Title')]//following-sibling::div";
    private static final String SELECT_BUSINESS_INPUT = "//*[contains(text(),'Business Sector')]//following-sibling::div";
    private static final String SELECT_BUSINESS_SECTOR = "//div[@role='listbox']//div[text()='%s']";
    private static final String WEBSITE = "//input[@name='website']";
    private static final String SAVE = "//button[contains(text(),'Save')]";
    private static final String UPDATE_SAVE = "//div[@class='css-spstuo']//button[contains(text(),'Save')]";
    private static final String NEW_CLIENT_BTN = "//*[text()='New Client']//parent::button[@type='button']";
    private static final String ADD_CLIENT_LABEL = "//section[contains(@class,'PageContainer')]/h2[contains(@class,'chakra-heading')]";
    private static final String ORGANIZATION_INPUT = "//input[@id='organizationId']";
    private static final String SELECT_ORGANIZATION = "//div[@role='listbox']//div[text()='%s']";
    private static final String TAG_INPUT = "//input[@id='tags']";
    private static final String SUCCESS_MSG_1 = "//div[contains(text(),'You successfully added the %s Client.')]";
    private static final String SUCCESS_MSG_2 = "//div[contains(text(),'You successfully added the %s contact')]";
    private static final String UPDATE_SUCCESS_MSG = "//div[contains(text(),'You successfully updated the %s Client.')]";
    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLIENT_NAME = "//tbody//td//div[text()='%s']//preceding::td//div//a";
    private static final String EDIT_CLIENT_BTN = "//*[text()='Edit Client']//parent::button[@type='button']";
    private static final String DELETE_BUTTON = "//tbody//td//div[text()='%s']//following::td//div//button";
    private static final String DEACTIVATE_BUTTON = "//button[text()='Deactivate']";
    private static final String ALERT = "//section[@role='alertdialog']";
    private static final String USER_MENU = "//header/button[contains(@class,'chakra-menu__menu-button')]";
    public static String cName;
    public static String contact;

    public void clickOnNewClientButton() throws AutomationException {
        clickOnSideBarMenuItem("Clients");
        driverUtil.getWebElement(NEW_CLIENT_BTN).click();
    }

    public void verifyAddClientPage() throws AutomationException {
        Assert.assertEquals("Add Client", driverUtil.getWebElementAndScroll(ADD_CLIENT_LABEL).getText().trim());
    }

    public void enterDetails(DataTable clientData) throws AutomationException {
        clientDetails = readData(clientData);
        cName = clientDetails.get("Client Name").trim();
        enterClientName(cName);
        contact = clientDetails.get("Primary Contact Name").trim();
        enterPrimaryContactNumber(contact);
        enterPrimaryContactEmail(clientDetails.get("Primary Contact Email").trim());
        enterPrimaryContactPhone(clientDetails.get("Primary Contact Phone").trim());
        selectPrimaryContactTitle(clientDetails.get("Primary Contact Title").trim());
        selectBusinessSector(clientDetails.get("Business Sector").trim());
        selectOrganization(clientDetails.get("Organization").trim());
        enterWebsite(clientDetails.get("Website").trim());
        enterTag(clientDetails.get("Tags").trim());
    }

    public void updateClientsDetails(DataTable clientData) throws AutomationException {
        clientDetails = readData(clientData);
        waitForInvisibleElement(By.xpath(SPINNER),3);
        String primaryContactName = clientDetails.get("Primary Contact Name").trim();
        driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).sendKeys(primaryContactName);
        driverUtil.getWebElementAndScroll(String.format(CLIENT_NAME, primaryContactName)).click();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElementAndScroll(EDIT_CLIENT_BTN).click();
        cName = clientDetails.get("Client Name").trim();
        enterClientName(cName);
        selectBusinessSector(clientDetails.get("Business Sector").trim());
        selectOrganization(clientDetails.get("Organization").trim());
        enterWebsite(clientDetails.get("Website").trim());
        enterTag(clientDetails.get("Tags").trim());
    }

    public void clickOnSaveButtonToUpdateRecord() throws AutomationException {
        WebElement saveUpdate = driverUtil.getWebElementAndScroll(UPDATE_SAVE, 2);
        driverUtil.moveToElementAndClick(saveUpdate);
        String updateMSG = String.format(UPDATE_SUCCESS_MSG, cName);
        WebElement w = driverUtil.getWebElementAndScroll(updateMSG);
        boolean successMSG1 = w.isDisplayed();

        if (!successMSG1) {
            throw new AutomationException("Client updated message is not displayed");
        }
        waitForInvisibleElement(By.xpath(updateMSG));
    }

    public void enterClientName(String clientName) throws AutomationException {
        if (clientName != null && !clientName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CLIENT_NAME_INPUT);
            name.clear();
            name.sendKeys(clientName);
        }
    }

    public void enterPrimaryContactNumber(String contactName) throws AutomationException {
        if (contactName != null && !contactName.isEmpty())
            driverUtil.getWebElementAndScroll(PRIMARY_CONTACT_NAME).sendKeys(contactName);
    }

    public void enterPrimaryContactEmail(String email) throws AutomationException {
        if (email != null && !email.isEmpty())
            driverUtil.getWebElementAndScroll(EMAIL).sendKeys(email);
    }

    public void enterPrimaryContactPhone(String phone) throws AutomationException {
        if (phone != null && !phone.isEmpty())
            driverUtil.getWebElementAndScroll(PHONE).sendKeys(phone);
    }

    public void selectPrimaryContactTitle(String contactTitle) throws AutomationException {
        if (contactTitle != null && !contactTitle.isEmpty()) {
            driverUtil.getWebElementAndScroll(SELECT_TITLE).click();
            driverUtil.clickUsingJavaScript(String.format(CONTACT_TITLE, contactTitle));
        }
    }

    public void selectBusinessSector(String businessSector) throws AutomationException {
        if (businessSector != null && !businessSector.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(SELECT_BUSINESS_INPUT, 4);
            driverUtil.moveToElementAndClick(business);
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, businessSector));
        }
    }

    public void selectOrganization(String organization) throws AutomationException {
        if (organization != null && !organization.isEmpty()) {
            driverUtil.getWebElementAndScroll(ORGANIZATION_INPUT, 4).click();
            driverUtil.clickUsingJavaScript(String.format(SELECT_ORGANIZATION, organization));
        }
    }

    public void enterWebsite(String website) throws AutomationException {
        if (website != null && !website.isEmpty()) {
            WebElement websiteInput = driverUtil.getWebElementAndScroll(WEBSITE);
            websiteInput.clear();
            websiteInput.sendKeys(website);
        }
    }

    public void enterTag(String tagValue) throws AutomationException {
        if (tagValue != null && !tagValue.isEmpty()) {
            WebElement tag = driverUtil.getWebElementAndScroll(TAG_INPUT);
            tag.clear();
            tag.sendKeys(Keys.BACK_SPACE);
            tag.sendKeys(tagValue);
            tag.sendKeys(Keys.ENTER);
        }
    }

    public void clickOnSaveButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(USER_MENU, 2);
        try {
            driverUtil.getWebElementAndScroll(SAVE, 2).click();
        } catch (Exception ae) {
            WebElement saveBTN = driverUtil.getWebElementAndScroll(SAVE, 2);
            Actions actions = new Actions(DriverFactory.drivers.get());
            actions.scrollToElement(saveBTN).perform();
            saveBTN.click();
        }
        String successMSG1 = String.format(SUCCESS_MSG_1, cName);
        String successMSG2 = String.format(SUCCESS_MSG_2, contact);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
        if (!isSuccessMSG1 && !isSuccessMSG2) {
            throw new AutomationException("Client save message is not displayed");
        }
        waitForInvisibleElement(By.xpath(successMSG1));
        waitForInvisibleElement(By.xpath(successMSG2));
    }

    public void deactivatingRecord(String name) throws AutomationException {
        clickOnSideBarMenuItem("Clients");
        driverUtil.getWebElementAndScroll(SEARCH_INPUT).sendKeys(name);
        waitForInvisibleElement(By.xpath(SPINNER));
        WebElement deleteIcon = driverUtil.getWebElementAndScroll(String.format(DELETE_BUTTON, name));
        driverUtil.moveToElementAndClick(deleteIcon);
        waitForVisibleElement(By.xpath(ALERT));
        driverUtil.getWebElementAndScroll(DEACTIVATE_BUTTON).click();
        WebElement w = driverUtil.getWebElementAndScroll("//div[contains(text(),'Client successfully deactivated')]");
        boolean successMSG1 = w.isDisplayed();
        if (!successMSG1) {
            throw new AutomationException("Client deactivated message is not displayed");
        }
        waitForInvisibleElement(By.xpath("//div[contains(text(),'Client successfully deactivated')]"));
    }

    @Override
    String getName() {
        return "AddClient";
    }

    public static Map<String, String> readData(DataTable parameters) {
        Map<String, String> parametersMap = new LinkedHashMap<>();
        List<Map<String, String>> rows = parameters.asMaps();
        for (Map<String, String> row : rows) {
            parametersMap.put(row.get("FieldName"), row.get("Value"));
        }
        return parametersMap;
    }
}

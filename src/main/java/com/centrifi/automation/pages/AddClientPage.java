package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.devtools.v85.network.model.DataReceived;
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
    private static final String NEW_CLIENT_BTN = "//*[text()='New Client']//parent::button[@type='button']";
    private static final String ADD_CLIENT_LABEL = "//section[contains(@class,'PageContainer')]/h2[contains(@class,'chakra-heading')]";
    private static final String ORGANIZATION_INPUT = "//input[@id='organizationId']";
    private static final String SELECT_ORGANIZATION = "//div[@role='listbox']//div[text()='%s']";
    private static final String TAG_INPUT = "//input[@id='tags']";
    private static final String SUCCESS_MSG_1 = "//div[@id='toast-1']";
    private static final String SUCCESS_MSG_2 = "//div[@id='toast-2']";
    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLIENT_NAME = "//tbody//td//div[text()='%s']//preceding::td//div//a";
    private static final String EDIT_CLIENT_BTN = "//*[text()='Edit Client']//parent::button[@type='button']";
    private static final String DELETE_BUTTON = "//tbody//td//div[text()='%s']//following::td//div//button";
    private static final String DEACTIVATE_BUTTON = "//button[text()='Deactivate']";
    private static final String ALERT = "//section[@role='alertdialog']";

    public void clickOnNewClientButton() throws AutomationException {
        clickOnSideBarMenuItem("Clients");
        driverUtil.getWebElement(NEW_CLIENT_BTN).click();
    }

    public void verifyAddClientPage() throws AutomationException {
        Assert.assertEquals("Add Client", driverUtil.getWebElementAndScroll(ADD_CLIENT_LABEL).getText().trim());
    }

    public void enterDetails(DataTable clientData) throws AutomationException {
        clientDetails = readData(clientData);
        enterClientName(clientDetails.get("Client Name").trim());
        enterPrimaryContactNumber(clientDetails.get("Primary Contact Name").trim());
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
        clickOnSideBarMenuItem("Clients");
        String primaryContactName = clientDetails.get("Primary Contact Name").trim();
        driverUtil.getWebElementAndScroll(SEARCH_INPUT).sendKeys(primaryContactName);
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElementAndScroll(String.format(CLIENT_NAME, primaryContactName)).click();
        waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElementAndScroll(EDIT_CLIENT_BTN).click();
        enterClientName(clientDetails.get("Client Name").trim());
        selectBusinessSector(clientDetails.get("Business Sector").trim());
        selectOrganization(clientDetails.get("Organization").trim());
        enterWebsite(clientDetails.get("Website").trim());
        enterTag(clientDetails.get("Tags").trim());
    }

    public void clickOnSaveButtonToUpdateRecord() throws AutomationException {
        driverUtil.getWebElementAndScroll(SAVE).click();
        WebElement w = driverUtil.getWebElement(SUCCESS_MSG_1);
        boolean successMSG1 = w.isDisplayed();

        if (!successMSG1) {
            throw new AutomationException("Client updated message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(SUCCESS_MSG_1));

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
            driverUtil.getWebElementAndScroll(String.format(CONTACT_TITLE, contactTitle)).click();
        }
    }

    public void selectBusinessSector(String businessSector) throws AutomationException {
        if (businessSector != null && !businessSector.isEmpty()) {
           WebElement business = driverUtil.getWebElementAndScroll(SELECT_BUSINESS_INPUT);
            driverUtil.moveToElementAndClick(business);
            WebElement businessSelect = driverUtil.getWebElementAndScroll(String.format(SELECT_BUSINESS_SECTOR, businessSector));
            driverUtil.moveToElementAndClick(businessSelect);
        }
    }

    public void selectOrganization(String organization) throws AutomationException {
        if (organization != null && !organization.isEmpty()) {
            WebElement organizationInput = driverUtil.getWebElement(ORGANIZATION_INPUT);
            driverUtil.moveToElementAndClick(organizationInput);
            WebElement organizationSelect = driverUtil.getWebElement(String.format(SELECT_ORGANIZATION, organization));
           // driverUtil.clickUsingJavaScript(String.format(SELECT_ORGANIZATION, organization));
            driverUtil.moveToElementAndClick(organizationSelect);
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
        WebElement save = driverUtil.getWebElement(SAVE);
        driverUtil.moveToElementAndClick(save);
        WebElement w = driverUtil.getWebElement(SUCCESS_MSG_1);
        WebElement w1 = driverUtil.getWebElement(SUCCESS_MSG_2);
        boolean successMSG1 = w.isDisplayed();
        boolean successMSG2 = w1.isDisplayed();
        String s1 = w.getText();
        String s2 = w1.getText();
        if (!successMSG1 && !successMSG2) {
            throw new AutomationException("Client save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(SUCCESS_MSG_1));
        waitForInvisibleElement(By.xpath(SUCCESS_MSG_2));
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
        CommonSteps.takeScreenshot();
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

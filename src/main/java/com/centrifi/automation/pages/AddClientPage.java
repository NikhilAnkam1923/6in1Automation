package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import com.centrifi.automation.util.WebDriverUtil;
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
    private static final String SUCCESS_MSG = "//div[contains(text(),'You successfully updated the %s contact')]";
    private static final String SUCCESS_MSG_2 = "//div[contains(text(),'You successfully added the %s contact')]";
    private static final String UPDATE_SUCCESS_MSG = "//div[contains(text(),'You successfully updated the %s Client.')]";
    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLIENT_NAME = "//tbody//td//div[text()='%s']//preceding::td//div//a";
    private static final String EDIT_CLIENT_BTN = "//*[text()='Edit Client']//parent::button[@type='button']";
    private static final String DELETE_BUTTON = "//tbody//td//div[text()='%s']//following::td//div//button";
    private static final String DELETE_CONTACT_BUTTON = "//tbody//td//div/p[text()='%s']//following::td//div//button";
    private static final String DEACTIVATE_BUTTON = "//button[text()='Deactivate']";
    private static final String ALERT = "//section[@role='alertdialog']";
    private static final String USER_MENU = "//header/button[contains(@class,'chakra-menu__menu-button')]";
    private static final String CLINT_CONTACT_BUTTON="//button[text()='Contacts']";
    private static final String ADD_CONTACT_BUTTON="//*[text()='Client Contact']//parent::button[@type='button']";
    private static final String CONTACT_FIRST_NAME="//input[@name='firstName']";
    private static final String CONTACT_LAST_NAME="//input[@name='lastName']";
    private static final String CONTACT_EMAIL="//input[@name='email']";
    private static final String CONTACT_PHONE="//input[@name='phone']";
    private static final String CONTACT_ADDRESS="//textarea[@name='businessAddress']";
    private static final String CONTACT_SELECT_TITLE="//*[contains(text(),'Select Title')]";
    private static final String CONTACT_PRIMARY="//*[@id='isPrimary-label']";
    private static final String CONTACT_CREATE_BUTTON="//button[text()='Create Contact']";
    private static final String CONTACT_CONFIRM_BUTTON="//button[text()='Confirm']";
    private static final String CONTACT_CANCEL_BUTTON="//button[text()='Cancel']";
    private static final String CONTACT_CAMPAIGN_BUTTON="//button[text()='Campaigns']";
    private static final String CONTACT_REPORT_BUTTON="//button[text()='Reporting']";
    private static final String CONTACT_CAMPAIGN_NAME="//*[text()='%s']";
    private static final String CONTACT_CAMPAIGN_NEXT_PAGE="//button[@aria-label='Next page']";
    private static final String CONTACT_CAMPAIGN_PREV_PAGE="//button[@aria-label='Previous page']";
    private static final String CLINT="//*[text()='%s']";



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
        CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.toString());
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
        CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.toString());
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

    public void createClientContactDetails(DataTable clientData) throws AutomationException {
        clientDetails = readData(clientData);
        CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.toString());
        clickOnSideBarMenuItem("Clients");
        waitForInvisibleElement(By.xpath(SPINNER),3);
        String primaryContactName = clientDetails.get("Client Name").trim();
        System.out.println("primaryContactName"+primaryContactName);
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).sendKeys(primaryContactName);
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(String.format(CLINT, primaryContactName)).click();
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CLINT_CONTACT_BUTTON).click();
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(ADD_CONTACT_BUTTON).click();
        cName = clientDetails.get("Contact First Name").trim()+" "+clientDetails.get("Contact Last Name").trim();
        enterContactFirstName(clientDetails.get("Contact First Name").trim());
        enterContactLastName(clientDetails.get("Contact Last Name").trim());
        enterContactEmail(clientDetails.get("Contact Email").trim());
        selectClientContactTitle(clientDetails.get("Contact Title").trim());
        enterContactAddress(clientDetails.get("Contact Address"));

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
        WebDriverUtil.waitForAWhile();
        CommonSteps.takeScreenshot();
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
        WebDriverUtil.waitForAWhile();
        CommonSteps.takeScreenshot();
        WebElement successMessage = driverUtil.getWebElementAndScroll("//div[contains(text(),'Client successfully deactivated')]");
        if (successMessage==null) {
            throw new AutomationException("Client deactivated Failed!");
        }
        waitForInvisibleElement(By.xpath("//div[contains(text(),'Client successfully deactivated')]"));
    }

    public void enterContactFirstName(String firstName) throws AutomationException {
        if (firstName != null && !firstName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_FIRST_NAME);
            name.clear();
            name.sendKeys(firstName);
        }
    }
    public void enterContactLastName(String lastName) throws AutomationException {
        if (lastName != null && !lastName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_LAST_NAME);
            name.clear();
            name.sendKeys(lastName);
        }
    }

    public void enterContactEmail(String email) throws AutomationException {
        if (email != null && !email.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_EMAIL);
            name.clear();
            name.sendKeys(email);
        }
    }
    public void enterContactAddress(String address) throws AutomationException {
        if (address != null && !address.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_ADDRESS);
            name.clear();
            name.sendKeys(address);
        }
    }
    public void selectClientContactTitle(String businessSector) throws AutomationException {
        if (businessSector != null && !businessSector.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(CONTACT_SELECT_TITLE, 4);
            driverUtil.moveToElementAndClick(business);
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, businessSector));
        }
    }

    public void clickOnCreateContactButtonToSaveRecord() throws AutomationException {
        //driverUtil.getWebElementAndScroll(USER_MENU, 2);
        driverUtil.getWebElementAndScroll(CONTACT_CREATE_BUTTON, 2).click();
        String successMSG1 = String.format(SUCCESS_MSG, cName);
        System.out.println("successMSG1:"+successMSG1);

        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();

        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
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

    public void clickOnClientButton() throws AutomationException {
        clickOnSideBarMenuItem("Clients");
    }

    public void selectTheClient(String clientName) throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),10);
        driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).sendKeys(clientName);
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(String.format(CLINT, clientName)).click();

    }

    public void clickOnCampaignButton() throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CONTACT_CAMPAIGN_BUTTON).click();

    }


    public void selectCampaign(String campaignName) throws AutomationException {

        try {
            waitForInvisibleElement(By.xpath(SPINNER),3);
            System.out.println("clientName:"+String.format(CLINT, campaignName));
            waitForInvisibleElement(By.xpath(SPINNER),3);
            driverUtil.getWebElementAndScroll(String.format(CLINT, campaignName)).click();

        } catch (Exception ae) {
            waitForInvisibleElement(By.xpath(SPINNER),3);
            System.out.println("clientName:"+String.format(CLINT, campaignName));
            driverUtil.getWebElement(CONTACT_CAMPAIGN_NEXT_PAGE).click();
            driverUtil.getWebElementAndScroll(String.format(CLINT, campaignName)).click();
        }

    }

    public void clickOnReportButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CONTACT_REPORT_BUTTON).click();
    }

    public void clickOnReportAndVerifyReportPage(String reportName) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),5);
        driverUtil.getWebElementAndScroll(String.format(CLINT, reportName)).click();
    }

    public void userDeactivatingClientRecord(String firstName, String lastName) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(DELETE_CONTACT_BUTTON, firstName+" "+lastName)).click();
        driverUtil.getWebElementAndScroll("(//button[text()='Delete Contact'])[2]").click();
        CommonSteps.takeScreenshot();
        WebElement successMessage = driverUtil.getWebElementAndScroll("//div[contains(text(),'You successfully deleted the contact.')]");
        if (successMessage==null) {
            throw new AutomationException("Contact deactivated Failed!");
        }
        waitForInvisibleElement(By.xpath("//div[contains(text(),'You successfully deleted the contact.')]"));

    }

    public void verifyCampaignPage() throws AutomationException {
        Assert.assertEquals("Campaigns", driverUtil.getWebElement("//li[contains(@class,'chakra-breadcrumb__list-item')]/a").getText());
    }

    public void verifyReportPage() throws AutomationException {
        Assert.assertEquals("Reporting Clients", driverUtil.getWebElement("(//li[contains(@class,'chakra-breadcrumb__list-item')]/a)[2]").getText());
    }
}

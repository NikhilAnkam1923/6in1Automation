package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import com.centrifi.automation.util.CommonUtil;
import com.centrifi.automation.util.WebDriverUtil;
import gherkin.lexer.Fi;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;
import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.centrifi.automation.util.WebDriverUtil.waitForVisibleElement;

public class AddClientPage extends BasePage {
    private static final ThreadLocal<Map<String, String>> clientDetails = new ThreadLocal<>();
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
    private static final String BACK = "//button[contains(text(),'Back')]";
    private static final String UPDATE_SAVE = "//div[@class='css-spstuo']//button[contains(text(),'Save')]";
    private static final String NEW_CLIENT_BTN = "//*[text()='New Client']//parent::button[@type='button']";
    private static final String ADD_CLIENT_LABEL = "//section[contains(@class,'PageContainer')]/h2[contains(@class,'chakra-heading')]";
    private static final String ORGANIZATION_INPUT = "//input[@id='organizationId']";
    private static final String SELECT_ORGANIZATION = "//div[@role='listbox']//div[text()='%s']";
    private static final String TAG_INPUT = "//input[@id='tags']";
    private static final String SUCCESS_MSG_1 = "//div[contains(text(),'You successfully added the %s Client.')]";
    private static final String SUCCESS_MSG = "//div[contains(text(),'You successfully updated the %s contact')]";
    private static final String CONTACT_UPDATE_MSG = "//div[contains(text(),'You successfully updated the %s contact')]";
    private static final String SUCCESS_MSG_2 = "//div[contains(text(),'You successfully added the %s contact')]";
    private static final String UPDATE_SUCCESS_MSG = "//div[contains(text(),'You successfully updated the %s Client.')]";
    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLIENT_NAME = "//tbody//td//div[text()='%s']//preceding::td//div//a";
    private static final String EDIT_CLIENT_BTN = "//*[text()='Edit Client']//parent::button[@type='button']";
    private static final String DELETE_BUTTON = "//tbody//td//div[text()='%s']//following::td//div//button";
    private static final String DOT_CONTACT_BUTTON = "//tbody//td//div/p[text()='%s']//following::td//div//button";
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
    private static final String CONTACT_SELECT_TITLE="//div[contains(@class,'css-1709og7')]";
    private static final String SELECT_STATUS="(//div[contains(@class,' css-j93siq')])[2]";
    private static final String CONTACT_CREATE_BUTTON="//button[text()='Create Contact']";
    private static final String CONTACT_CAMPAIGN_BUTTON="//button[text()='Campaigns']";
    private static final String CONTACT_REPORT_BUTTON="//button[text()='Reporting']";
    private static final String CONTACT_PROPOSAL_BUTTON="//button[text()='Proposals']";
    private static final String CONTACT_CAMPAIGN_NEXT_PAGE="//button[@aria-label='Next page']";
    private static final String CLIENT_ACTIVE_BUTTON = "(//tbody//td//div[text()='%s']//following::button)[1]";
    public static final String IMAGE_FILE_PATH = File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"test-data"+File.separator;
    private static final String CLINT="//*[text()='%s']";
    private static final String CLOSE_BTN="//button[contains(@class,'chakra-modal__close-btn')]";

    public static ThreadLocal<String> cName = new ThreadLocal<>();
    public static String contact;

    public void clickOnNewClientButton() throws AutomationException {
        clickOnSideBarMenuItem("Clients");
        driverUtil.getWebElement(NEW_CLIENT_BTN).click();
    }

    public void verifyAddClientPage() throws AutomationException {
        Assert.assertEquals("Add Client", driverUtil.getWebElementAndScroll(ADD_CLIENT_LABEL).getText().trim());
    }

    public void enterDetails(DataTable clientData) throws AutomationException, AWTException {
        clientDetails.set(CommonUtil.readData(clientData));
        try {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString());
            cName.set(clientDetails.get().get("Client Name").trim());
            enterClientName(cName.get());
            contact = clientDetails.get().get("Primary Contact Name").trim();
            enterPrimaryContactNumber(contact);
            enterPrimaryContactEmail(clientDetails.get().get("Primary Contact Email").trim());
            enterPrimaryContactPhone(clientDetails.get().get("Primary Contact Phone").trim());
            selectPrimaryContactTitle(clientDetails.get().get("Primary Contact Title").trim());
            selectBusinessSector(clientDetails.get().get("Business Sector").trim());
            selectOrganization(clientDetails.get().get("Organization").trim());
            enterWebsite(clientDetails.get().get("Website").trim());
            enterTag(clientDetails.get().get("Tags").trim());
            uploadImage(clientDetails.get().get("profileName"));
        } catch(Throwable th) {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString() + "\n" + th.getMessage());
            throw new AutomationException(th.getMessage());
        }
    }

    public void uploadImage(String fileName) throws AutomationException, AWTException {
        String path = System.getProperty("user.dir")+IMAGE_FILE_PATH+fileName;
        driverUtil.getWebElement("//input[@type='file' and @id='fileUpload']", 0)
                .sendKeys((new File(path)).getAbsolutePath());
    }

    public void updateClientsDetails(DataTable clientData) throws AutomationException {
        clientDetails.set(CommonUtil.readData(clientData));
        try {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString());
            waitForInvisibleElement(By.xpath(SPINNER),3);
            String primaryContactName = clientDetails.get().get("Primary Contact Name").trim();
            driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).sendKeys(primaryContactName);
            driverUtil.getWebElementAndScroll(String.format(CLIENT_NAME, primaryContactName)).click();
            waitForInvisibleElement(By.xpath(SPINNER));
            driverUtil.getWebElementAndScroll(EDIT_CLIENT_BTN).click();
            cName.set(clientDetails.get().get("Client Name").trim());
            enterClientName(cName.get());
            selectBusinessSector(clientDetails.get().get("Business Sector").trim());
            selectOrganization(clientDetails.get().get("Organization").trim());
            enterWebsite(clientDetails.get().get("Website").trim());
            enterTag(clientDetails.get().get("Tags").trim());
        } catch(Throwable th) {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString() + "\n" + th.getMessage());
            throw new AutomationException(th.getMessage());
        }
    }

    public void clickOnSaveButtonToUpdateRecord() throws AutomationException {
        WebElement saveUpdate = driverUtil.getWebElementAndScroll(UPDATE_SAVE, 2);
        driverUtil.moveToElementAndClick(saveUpdate);
        String updateMSG = String.format(UPDATE_SUCCESS_MSG, cName.get());
        WebElement w = driverUtil.getWebElementAndScroll(updateMSG);
        boolean successMSG1 = w.isDisplayed();

        if (!successMSG1) {
            throw new AutomationException("Client updated message is not displayed");
        }
        waitForInvisibleElement(By.xpath(updateMSG));
    }

    public void createClientContactDetails(DataTable clientData) throws AutomationException {
        clientDetails.set(CommonUtil.readData(clientData));
        try {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString());
            waitForInvisibleElement(By.xpath(SPINNER),3);
            driverUtil.getWebElement(ADD_CONTACT_BUTTON).click();
            cName.set(clientDetails.get().get("Contact First Name").trim()+" "+clientDetails.get().get("Contact Last Name").trim());
            enterContactFirstName(clientDetails.get().get("Contact First Name").trim());
            enterContactLastName(clientDetails.get().get("Contact Last Name").trim());
            enterContactEmail(clientDetails.get().get("Contact Email").trim());
            selectClientContactTitle(clientDetails.get().get("Contact Title").trim());
            enterContactAddress(clientDetails.get().get("Contact Address"));
            enterPhone(clientDetails.get().get("Contact Phone"));
        } catch(Throwable th) {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString() + "\n" + th.getMessage());
            throw new AutomationException(th.getMessage());
        }
    }

    public void enterPhone(String clientPhone) throws AutomationException {
        if (clientPhone != null && !clientPhone.isEmpty()) {
            WebElement phone = driverUtil.getWebElementAndScroll(CONTACT_PHONE);
            phone.clear();
            phone.sendKeys(Keys.CONTROL+"A");
            phone.sendKeys(Keys.CLEAR);
            phone.sendKeys(clientPhone);
        }
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
        String successMSG1 = String.format(SUCCESS_MSG_1, cName.get());
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
        driverUtil.getWebElementAndScroll(SEARCH_INPUT).clear();
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
            name.click();
            name.clear();
            name.sendKeys(firstName);
        }else {
            driverUtil.getWebElementAndScroll(CONTACT_FIRST_NAME).clear();
        }
    }
    public void enterContactLastName(String lastName) throws AutomationException {
        if (lastName != null && !lastName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_LAST_NAME);
            name.click();
            name.clear();
            name.sendKeys(lastName);
        }else {
            driverUtil.getWebElementAndScroll(CONTACT_LAST_NAME).clear();
        }
    }

    public void enterContactEmail(String email) throws AutomationException {
        if (email != null && !email.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_EMAIL);
            name.click();
            name.clear();
            name.sendKeys(email);
        }
    }
    public void enterContactAddress(String address) throws AutomationException {
        if (address != null && !address.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(CONTACT_ADDRESS);
            name.click();
            name.clear();
            name.sendKeys(Keys.CONTROL+"A");
            name.sendKeys(Keys.CLEAR);
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
        driverUtil.getWebElementAndScroll(CONTACT_CREATE_BUTTON, 2).click();
        String successMSG1 = String.format(SUCCESS_MSG, cName.get());
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();

        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));

    }

    public void clickOnSaveUpdateRecord() throws AutomationException {
        driverUtil.getWebElementAndScroll(SAVE, 2).click();
        String successMSG1 = String.format(CONTACT_UPDATE_MSG, cName.get());
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();

        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Contact Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
    }

    @Override
    String getName() {
        return "AddClient";
    }

    public void clickOnClientButton() throws AutomationException {
        clickOnSideBarMenuItem("Clients");
    }

    public void selectTheClient(String clientName) throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),10);
        driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).clear();
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
            CommonSteps.takeScreenshot();
            driverUtil.getWebElementAndScroll(String.format(CLINT, campaignName)).click();

        } catch (Exception ae) {
            waitForInvisibleElement(By.xpath(SPINNER),3);
            CommonSteps.takeScreenshot();
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
        waitForInvisibleElement(By.xpath(SPINNER),30);
        driverUtil.getWebElement(String.format(DOT_CONTACT_BUTTON, firstName+" "+lastName)).click();
        WebDriverUtil.waitForAWhile(3);
        driverUtil.getWebElementAndScroll("//div[contains(@class,'chakra-popover__popper') and contains(@style,'visibility: visible;')]//button[text()='Delete Contact']").click();
        WebElement successMessage = driverUtil.getWebElementAndScroll("//div[contains(text(),'You successfully deleted the contact.')]");
        if (successMessage==null) {
            throw new AutomationException("Contact deactivated Failed!");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath("//div[contains(text(),'You successfully deleted the contact.')]"));

    }

    public void verifyCampaignPage(String campaignName) throws AutomationException {
        Assert.assertEquals("Campaigns", driverUtil.getWebElement("//li[contains(@class,'chakra-breadcrumb__list-item')]/a").getText());
        Assert.assertEquals(campaignName, driverUtil.getWebElement("//a[text()='Campaigns']//following::h2[contains(@class,'chakra-heading')]").getText());
    }

    public void verifyReportPage() throws AutomationException {
        Assert.assertEquals("Reporting Clients", driverUtil.getWebElement("(//li[contains(@class,'chakra-breadcrumb__list-item')]/a)[2]").getText());
    }

    public void clickOnCreateContactButtonToInvalidRecord() throws AutomationException {
        driverUtil.getWebElementAndScroll(CONTACT_CREATE_BUTTON, 2).click();
        if(clientDetails.get().get("Contact First Name").isEmpty())
            Assert.assertEquals("First Name is Required.",driverUtil.getWebElement("//input[@name='firstName']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Last Name").isEmpty())
            Assert.assertEquals("Last Name is Required.",driverUtil.getWebElement("//input[@name='lastName']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Email").isEmpty())
            Assert.assertEquals("Email address is required",driverUtil.getWebElement("//input[@name='email']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        else
            Assert.assertEquals("Invalid email address",driverUtil.getWebElement("//input[@name='email']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if (!clientDetails.get().get("Contact Phone").isEmpty())
            Assert.assertEquals("Please enter a valid 10 digit phone number.",driverUtil.getWebElement("//input[@name='phone']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Title").isEmpty())
            Assert.assertEquals("Title is required",driverUtil.getWebElement("//input[@name='businessType']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        CommonSteps.takeScreenshot();
        WebElement element = driverUtil.getWebElementAndScroll(CLOSE_BTN, 2);
        driverUtil.moveToElementAndClick(element);
        Assert.assertEquals("Are you sure you want to discard your changes? Any unsaved changes will be lost.",driverUtil.getWebElement("(//div[contains(@class,'chakra-modal__body')])[2]").getText().trim());
        driverUtil.getWebElement("//button[text()='Ok']").click();
    }

    public void clickOnSaveButtonToInvalidRecord() throws AutomationException {
        driverUtil.getWebElement(SAVE, 2).click();
        if(clientDetails.get().get("Contact First Name").isEmpty())
            Assert.assertEquals("First Name is Required.",driverUtil.getWebElement("//input[@name='firstName']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Last Name").isEmpty())
            Assert.assertEquals("Last Name is Required.",driverUtil.getWebElement("//input[@name='lastName']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Email").isEmpty())
            Assert.assertEquals("Email address is required",driverUtil.getWebElement("//input[@name='email']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        else
            Assert.assertEquals("Invalid email address",driverUtil.getWebElement("//input[@name='email']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if (!clientDetails.get().get("Contact Phone").isEmpty())
            Assert.assertEquals("Please enter a valid 10 digit phone number.",driverUtil.getWebElement("//input[@name='phone']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        if(clientDetails.get().get("Contact Title").isEmpty())
            Assert.assertEquals("Title is required",driverUtil.getWebElement("//input[@name='businessType']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(SPINNER),3);
        WebElement element = driverUtil.getWebElementAndScroll(CLOSE_BTN, 2);
        driverUtil.moveToElementAndClick(element);
        //driverUtil.findElementAndScroll(CLOSE_BTN).click();
        Assert.assertEquals("Are you sure you want to discard your changes? Any unsaved changes will be lost.",driverUtil.getWebElement("(//div[contains(@class,'chakra-modal__body')])[2]").getText().trim());
        driverUtil.getWebElement("//button[text()='Ok']").click();

    }

    public void clickOnContactButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CLINT_CONTACT_BUTTON).click();
    }

    public void createContactUpdateDetails(DataTable contactDetails) throws AutomationException {
        clientDetails.set(CommonUtil.readData(contactDetails));
        try {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString());
            driverUtil.getWebElement(String.format(DOT_CONTACT_BUTTON, clientDetails.get().get("Contact Name"))).click();
            driverUtil.getWebElementAndScroll("//div[contains(@class,'chakra-popover__popper') and contains(@style,'visibility: visible;')]//button[text()='Edit Contact']").click();
            cName.set(clientDetails.get().get("Contact First Name").trim()+" "+clientDetails.get().get("Contact Last Name").trim());
            enterContactFirstName(clientDetails.get().get("Contact First Name").trim());
            enterContactLastName(clientDetails.get().get("Contact Last Name").trim());
            enterContactEmail(clientDetails.get().get("Contact Email").trim());
            selectClientContactTitle(clientDetails.get().get("Contact Title").trim());
            enterContactAddress(clientDetails.get().get("Contact Address"));
            enterPhone(clientDetails.get().get("Contact Phone"));
        } catch(Throwable th) {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString() + "\n" + th.getMessage());
            throw new AutomationException(th.getMessage());
        }
    }

    public void clickOnProposalButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CONTACT_PROPOSAL_BUTTON).click();
    }

    public void verifyProposalPage(String proposalName) throws AutomationException {
        Assert.assertEquals("Proposals", driverUtil.getWebElement("//li[contains(@class,'chakra-breadcrumb__list-item')]/a").getText());
        Assert.assertEquals(proposalName, driverUtil.getWebElement("//h2[text()='Proposal Generator']//following-sibling::p").getText());
        CommonSteps.takeScreenshot();
    }

    public void userVerifyClientDetails(DataTable clientDetail) throws AutomationException {
        clientDetails.set(CommonUtil.readData(clientDetail));
        try {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString());
            Assert.assertTrue(driverUtil.getWebElement("//tbody//tr//td//div[text()='" + clientDetails.get().get("Primary Contact Name") + "']").isDisplayed());
            Assert.assertTrue(driverUtil.getWebElement("//tbody//tr//td//div//a//p[text()='" + clientDetails.get().get("Client Name") + "']").isDisplayed());
            Assert.assertTrue(driverUtil.getWebElement("//tbody//tr//td//div[text()='" + clientDetails.get().get("Business Sector") + "']").isDisplayed());
            Assert.assertTrue(driverUtil.getWebElement("//tbody//tr//td//div[text()='" + clientDetails.get().get("Organization") + "']").isDisplayed());
            Assert.assertTrue(driverUtil.getWebElement("//tbody//tr//td//div//span[text()='" + clientDetails.get().get("Status") + "']").isDisplayed());
        } catch(Throwable th) {
            CommonSteps.CURRENT_STEP_MESSAGE.set(clientDetails.get().toString() + "\n" + th.getMessage());
            throw new AutomationException(th.getMessage());
        }
    }

    public void searchTheClient(String clientName) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(SEARCH_INPUT).clear();
        driverUtil.getWebElementAndScroll(SEARCH_INPUT).sendKeys(clientName);
        waitForInvisibleElement(By.xpath(SPINNER),3);
        CommonSteps.takeScreenshot();
    }

    public void SelectTheInactiveClient(String status) throws AutomationException {
        selectInactiveClient(status);
    }
    public void selectInactiveClient(String businessSector) throws AutomationException {
        if (businessSector != null && !businessSector.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(SELECT_STATUS, 4);
            driverUtil.moveToElementAndClick(business);
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, businessSector));
        }
    }

    public void userClickOnActiveButton(String name) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(String.format(CLIENT_ACTIVE_BUTTON,name)).click();
    }

    public void userClickOnEditClientButton(String name) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(EDIT_CLIENT_BTN).click();
        driverUtil.getWebElement(CLIENT_NAME_INPUT).clear();
        driverUtil.getWebElement(CLIENT_NAME_INPUT).sendKeys(name);
    }

    public void clickOnSaveButtonInvalidRecord() throws AutomationException {
        WebElement saveUpdate = driverUtil.getWebElementAndScroll(UPDATE_SAVE, 2);
        driverUtil.moveToElementAndClick(saveUpdate);
        CommonSteps.takeScreenshot();
        Assert.assertEquals("Client Name is Required.",driverUtil.getWebElement(CLIENT_NAME_INPUT+"//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        saveUpdate=driverUtil.getWebElementAndScroll(CLOSE_BTN,2);
        driverUtil.moveToElementAndClick(saveUpdate);
        Assert.assertEquals("Are you sure you want to discard your changes? Any unsaved changes will be lost.",driverUtil.getWebElement("(//div[contains(@class,'chakra-modal__body')])[2]").getText().trim());
        driverUtil.getWebElement("//button[text()='Ok']").click();
    }

    public void clickOnSaveButtonVerifyErrorMessage() throws AutomationException {
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
        Assert.assertEquals("Client Name is Required.",driverUtil.getWebElement(CLIENT_NAME_INPUT+"//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Client Contact Name is required.",driverUtil.getWebElement(PRIMARY_CONTACT_NAME+"//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Invalid email address",driverUtil.getWebElement("//input[@placeholder='Email']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Please enter a valid phone number",driverUtil.getWebElement("//input[@name='phone']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Title is required.",driverUtil.getWebElement("//input[@name='businessType']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Business Sector is required.",driverUtil.getWebElement("//input[@name='businessSector']//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        Assert.assertEquals("Must be a valid web domain.",driverUtil.getWebElement(WEBSITE+"//following::div[contains(@class,'chakra-form__error-message')][1]").getText());
        driverUtil.getWebElementAndScroll(USER_MENU, 2);
        try {
            driverUtil.getWebElementAndScroll(BACK, 2).click();
        } catch (Exception ae) {
            WebElement saveBTN = driverUtil.getWebElementAndScroll(BACK, 2);
            Actions actions = new Actions(DriverFactory.drivers.get());
            actions.scrollToElement(saveBTN).perform();
            saveBTN.click();
        }

    }
}

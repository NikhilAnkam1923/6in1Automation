package com.centrifi.automation.pages;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;

public class AddClientIntegrationPage extends BasePage {
    private static Map<String, String> integrationDetails = new LinkedHashMap<>();
    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLINT = "//*[text()='%s']";
    private static final String CLINT_INTEGRATION_BUTTON = "//button[text()='Integrations']";
    private static final String INTEGRATION_PLATFORM_BTN = "//button[@id='%s']";
    private static final String INTEGRATION_PLATFORM_DISCONNECT_BTN = "//h2[text()='%s']//following::button[text()='Disconnect']";
    private static final String INTEGRATION_REPORTING_CHECKBOX = "//p[text()='Reporting']";
    private static final String INTEGRATION_ADVERTISING_CHECKBOX = "//p[text()='Advertising']";
    private static final String EMAIL_CLIENT_NAME = "//input[@name='client_name']";
    private static final String SIMPLI_ORG_ID = "//input[@name='organization_id']";
    private static final String INTEGRATION_CONNECT_BUTTON = "//footer[contains(@class,'chakra-modal__footer')]//button[text()='Connect']";
    private static final String INTEGRATION_DISCONNECT = "//footer[contains(@class,'chakra-modal__footer')]//button[text()='Disconnect']";
    private static final String INTEGRATION_CANCEL_BUTTON = "//footer[contains(@class,'chakra-modal__footer')]//button[text()='Cancel']";
    private static final String SELECT_DROPDOWN = "(//*[@class=' css-18euh9p'])[1]";
    private static final String SELECT_BUSINESS_SECTOR = "//div[@role='listbox']//div[text()='%s']";
    private static final String SELECT_FB_ACCOUNT_ID = "//*[@name='ad_account_id']";
    private static final String SELECT_FB_PAGE_ID = "//*[@name='page_id']";
    private static final String DISCONNECT_CONFIRM_MSG = "//*[@role='alertdialog']/div";
    private static final String ACCOUNT_ID = "//input[@name='account_id']";
    private static final String OWNER_ACCOUNT_ID = "//input[@name='owner_account_id']";
    private static final String LOCATION_NAME = "//input[@name='location_name']";
    private static final String ACCOUNT_NAME = "//input[@name='account_name']";
    private static final String PROPERTY_ID = "//input[@name='property_id'] ";
    private static final String DISSCONNECT_MSG = "//div[contains(text(),'%s platform de-authorized successfully')]";
    private static final String CONNECT_MSG = "//div[contains(text(),'%s integration authorized successfully')]";
    public String platFormName="";


    public void clickOnIntegrationButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER), 3);
        driverUtil.getWebElement(CLINT_INTEGRATION_BUTTON).click();

    }

    public void selectClientAuthorization(String businessSector) throws AutomationException {
        if (businessSector != null && !businessSector.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(SELECT_DROPDOWN, 4);
            driverUtil.moveToElementAndClick(business);
            //driverUtil.getWebElementAndScroll(SELECT_DROPDOWN, 4).click();
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, businessSector));
        }
    }

    @Override
    String getName() {
        return "Client Integration page";
    }

    public void enterTheEmailDetails(DataTable dataTable) throws AutomationException {
        integrationDetails = readData(dataTable);
        CommonSteps.CURRENT_STEP_MESSAGE.set(integrationDetails.toString());
        waitForInvisibleElement(By.xpath(SPINNER), 3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Email-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        selectClientAuthorization(integrationDetails.get("authName"));
        if (integrationDetails.get("ReportFlag").equals("Yes")) {

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
            selectClientAuthorization(integrationDetails.get("ReportName"));

        }
        waitForInvisibleElement(By.xpath(SPINNER), 3);
        enterClientName(integrationDetails.get("ClientName"));

    }
        public void clickOnEmailContinueButton () throws AutomationException {
            driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
            String successMSG1 = String.format(CONNECT_MSG, "Email");
            System.out.println("successMSG1:" + successMSG1);
            boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
            if (!isSuccessMSG1) {
                throw new AutomationException("Email Integration message is not displayed");
            }
            CommonSteps.takeScreenshot();
            waitForInvisibleElement(By.xpath(successMSG1));
        }


        public void enterClientName (String firstName) throws AutomationException {
            if (firstName != null && !firstName.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(EMAIL_CLIENT_NAME);
                name.clear();
                name.sendKeys(firstName);
            }
        }

        public void userClickOnDisconnectButton (String platFromName) throws AutomationException {
            driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_DISCONNECT_BTN, platFromName), 2).click();
            String text = "Are you sure? You can't undo this action afterwards.";
            Assert.assertEquals(text, driverUtil.getWebElement(DISCONNECT_CONFIRM_MSG).getText());
            driverUtil.getWebElement(INTEGRATION_DISCONNECT, 3).click();
            String successMSG1 = String.format(DISSCONNECT_MSG, platFromName);
            System.out.println("successMSG1:" + successMSG1);
            boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
            if (!isSuccessMSG1) {
                throw new AutomationException(platFromName+" disconnect disconnected successfully message is not displayed");
            }
            CommonSteps.takeScreenshot();
            waitForInvisibleElement(By.xpath(successMSG1));
        }

        public void enterFaceBookDetails (DataTable faceBookDetails) throws AutomationException {
            integrationDetails = readData(faceBookDetails);
            CommonSteps.CURRENT_STEP_MESSAGE.set(integrationDetails.toString());
            waitForInvisibleElement(By.xpath(SPINNER), 3);
            driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Facebook Ads-id")).click();
            driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
            selectClientAuthorization(integrationDetails.get("AuthorizationId"));
            if (integrationDetails.get("ReportFlag").equals("Yes")) {

                driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
                selectClientAuthorization(integrationDetails.get("ReportName"));
            }
            waitForInvisibleElement(By.xpath(SPINNER), 3);
            Select select = new Select(driverUtil.getWebElement(SELECT_FB_ACCOUNT_ID));
            select.selectByValue(integrationDetails.get("AccountId"));
            select = new Select(driverUtil.getWebElement(SELECT_FB_PAGE_ID));
            select.selectByValue(integrationDetails.get("PageId"));

        }

        public void enterTheSimpliplatformDetails (DataTable simpliDetails) throws AutomationException {
            integrationDetails = readData(simpliDetails);
            CommonSteps.CURRENT_STEP_MESSAGE.set(integrationDetails.toString());
            waitForInvisibleElement(By.xpath(SPINNER), 3);
            driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Simpli.Fi-id")).click();
            driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
            selectClientAuthorization(integrationDetails.get("authName"));
            if (integrationDetails.get("ReportFlag").equals("Yes")) {

                driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
                selectClientAuthorization(integrationDetails.get("ReportName"));
            }
            enterOrgId(integrationDetails.get("OrgId"));

        }
        public void enterOrgId (String OrgId) throws AutomationException {
            if (OrgId != null && !OrgId.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(SIMPLI_ORG_ID);
                name.clear();
                name.sendKeys(OrgId);
            }
        }

        public void clickOnGoogleContinueButton (DataTable googleDetails) throws AutomationException        {
            integrationDetails = readData(googleDetails);
            CommonSteps.CURRENT_STEP_MESSAGE.set(integrationDetails.toString());
            waitForInvisibleElement(By.xpath(SPINNER), 3);
            driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, integrationDetails.get("IntegrationType"))).click();
            driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
            selectClientAuthorization(integrationDetails.get("authName"));
            if (integrationDetails.get("ReportFlag").equals("Yes")) {
                driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
                selectClientAuthorization(integrationDetails.get("ReportName"));
            }
            waitForInvisibleElement(By.xpath(SPINNER), 3);

            if (integrationDetails.get("IntegrationType").equalsIgnoreCase("Google Analytics V4-id")) {
                enterPropertyId(integrationDetails.get("OwnerAccId_PropertyId"));
                platFormName = "Google Analytics V4";
            } else if (integrationDetails.get("IntegrationType").equalsIgnoreCase("Google My Business-id")) {
                enterAccountName(integrationDetails.get("AccountId_Name"));
                enterLocationName(integrationDetails.get("LocationName"));
                platFormName = "Google My Business";

            } else if (integrationDetails.get("IntegrationType").equalsIgnoreCase("Google Ads-id")) {
                enterAccountId(integrationDetails.get("OwnerAccId_PropertyId"));
                enterOwnerAccountId(integrationDetails.get("AccountId_Name"));
                platFormName = "Google Ads";
            }
        }


        private void enterAccountId (String ownerAccIdPropertyId) throws AutomationException {
            if (ownerAccIdPropertyId != null && !ownerAccIdPropertyId.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(ACCOUNT_ID);
                name.clear();
                name.sendKeys(ownerAccIdPropertyId);
            }
        }
        private void enterOwnerAccountId (String accountIdName) throws AutomationException {
            if (accountIdName != null && !accountIdName.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(OWNER_ACCOUNT_ID);
                name.clear();
                name.sendKeys(accountIdName);
            }
        }
        private void enterAccountName (String accountIdName) throws AutomationException {
            if (accountIdName != null && !accountIdName.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(ACCOUNT_NAME);
                name.clear();
                name.sendKeys(accountIdName);
            }
        }
        private void enterLocationName (String locationName) throws AutomationException {
            if (locationName != null && !locationName.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(LOCATION_NAME);
                name.clear();
                name.sendKeys(locationName);
            }
        }

        private void enterPropertyId (String ownerAccIdPropertyId) throws AutomationException {
            if (ownerAccIdPropertyId != null && !ownerAccIdPropertyId.isEmpty()) {
                WebElement name = driverUtil.getWebElementAndScroll(PROPERTY_ID);
                name.clear();
                name.sendKeys(ownerAccIdPropertyId);
            }
        }
        public static Map<String, String> readData (DataTable parameters){
            Map<String, String> parametersMap = new LinkedHashMap<>();
            List<Map<String, String>> rows = parameters.asMaps();
            for (Map<String, String> row : rows) {
                parametersMap.put(row.get("FieldName"), row.get("Value"));
            }
            return parametersMap;
        }


    public void clickOnFaceBookContinueButton() throws AutomationException {
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        String successMSG1 = String.format(CONNECT_MSG, "Facebook Ads");
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();

        if (!isSuccessMSG1) {
            throw new AutomationException("Facebook Integration message is not displayed not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
    }

    public void clickOnSimpliPlatform() throws AutomationException {
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        String successMSG1 = String.format(CONNECT_MSG, "Simpli.Fi");
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        if (!isSuccessMSG1) {
            throw new AutomationException("Simpli Integration message message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
    }

    public void clickOnGooglePlatform() throws AutomationException {
        String successMSG1 = String.format(CONNECT_MSG, platFormName);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();

        if (!isSuccessMSG1) {
            throw new AutomationException("Google Integration message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));

        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();

    }
}

package com.centrifi.automation.pages;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;

public class AddClientIntegrationPage extends BasePage{

    private static final String SEARCH_INPUT = "//input[@type='text' and contains(@placeholder, 'Search')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CLINT="//*[text()='%s']";
    private static final String CLINT_INTEGRATION_BUTTON="//button[text()='Integrations']";
    private static final String INTEGRATION_PLATFORM_BTN="//button[@id='%s']";
    private static final String INTEGRATION_PLATFORM_DISCONNECT_BTN="//h2[text()='%s']//following::button[text()='Disconnect']";
    private static final String INTEGRATION_REPORTING_CHECKBOX="//p[text()='Reporting']";
    private static final String INTEGRATION_ADVERTISING_CHECKBOX="//p[text()='Advertising']";
    private static final String EMAIL_CLIENT_NAME="//input[@name='client_name']";
    private static final String SIMPLI_ORG_ID="//input[@name='organization_id']";
    private static final String INTEGRATION_CONNECT_BUTTON="//footer[contains(@class,'chakra-modal__footer')]//button[text()='Connect']";
    private static final String INTEGRATION_DISCONNECT="//footer[contains(@class,'chakra-modal__footer')]//button[text()='Disconnect']";
    private static final String INTEGRATION_CANCEL_BUTTON="//footer[contains(@class,'chakra-modal__footer')]//button[text()='Cancel']";
    //private static final String SELECT_DROPDOWN="//*[@class=' css-18euh9p']";
    private static final String SELECT_DROPDOWN="(//*[@class=' css-18euh9p'])[1]";
    //private static final String SELECT_DROPDOWN="//*[contains(text(),'Select Authorization')]";
    private static final String SELECT_BUSINESS_SECTOR = "//div[@role='listbox']//div[text()='%s']";
    private static final String SELECT_FB_ACCOUNT_ID = "//*[@name='ad_account_id']";
    private static final String SELECT_FB_PAGE_ID = "//*[@name='page_id']";
    private static final String DISCONNECT_CONFIRM_MSG = "//*[@role='alertdialog']/div";
    private static final String ACCOUNT_ID="//input[@name='account_id']";
    private static final String OWNER_ACCOUNT_ID="//input[@name='owner_account_id']";
    private static final String LOCATION_NAME="//input[@name='location_name']";
    private static final String ACCOUNT_NAME="//input[@name='account_name']";
    private static final String PROPERTY_ID = "//input[@name='property_id'] ";
    private static final String DISSCONNECT_MSG="//div[contains(text(),'%s platform de-authorized successfully')]";
    private static final String CONNECT_MSG="//div[contains(text(),'%s integration authorized successfully')]";


    public void clickOnIntegrationButton(String clientName) throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(SEARCH_INPUT,2).sendKeys(clientName);
        // String clientName="//*[text()='$s']";
        System.out.println("clientName:"+String.format(CLINT, clientName));
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElementAndScroll(String.format(CLINT, clientName)).click();

        waitForInvisibleElement(By.xpath(SPINNER),3);
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

    public void clickOnEmailContinueButton(String clientName, String authName,String reportFlag,String reportName) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Email-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        selectClientAuthorization(authName);
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
            selectClientAuthorization(reportName);

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);

        enterClientName(clientName);
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        String successMSG1 = String.format(CONNECT_MSG, "Email");
        System.out.println("successMSG1:"+successMSG1);
        //String successMSG2 = String.format(SUCCESS_MSG_2, contact);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        //boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));

        try{

            System.out.println("error message:"+driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText());
            if ("Selected Authorization is not configured for Reporting".contains(driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText())) {
                // throw new AutomationException("Simpli.fi Validation failed message is not displayed");
            }else if("Validation failed.".contains(driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText())){

            }
            CommonSteps.takeScreenshot();
            //waitForInvisibleElement(By.xpath(SPINNER),10);
            driverUtil.getWebElement(("//button[@aria-label='Close']")).click();
            driverUtil.getWebElement((INTEGRATION_CANCEL_BUTTON)).click();

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public void enterClientName(String firstName) throws AutomationException {
        if (firstName != null && !firstName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(EMAIL_CLIENT_NAME);
            name.clear();
            name.sendKeys(firstName);
        }
    }

    public void userClickOnDisconnectButton(String platFromName) throws AutomationException {
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_DISCONNECT_BTN,platFromName),2).click();
        String text="Are you sure? You can't undo this action afterwards.";

        Assert.assertEquals(text, driverUtil.getWebElement(DISCONNECT_CONFIRM_MSG).getText());
        CommonSteps.takeScreenshot();
        driverUtil.getWebElement(INTEGRATION_DISCONNECT,3).click();

        String successMSG1 = String.format(DISSCONNECT_MSG, platFromName);
        System.out.println("successMSG1:"+successMSG1);
        //String successMSG2 = String.format(SUCCESS_MSG_2, contact);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        //boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
    }

    public void clickOnFaceBookContinueButton( String acctId, String pageId, String authId,String reportFlag,String reportName) throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Facebook Ads-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        selectClientAuthorization(authId);
      //Facebook
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
            selectClientAuthorization(reportName);

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);
       // driverUtil.getWebElement(SELECT_DROPDOWN).click();
        //driverUtil.getWebElement("//input[@role='combobox']").sendKeys(authId);

        Select select=new Select(driverUtil.getWebElement(SELECT_FB_ACCOUNT_ID));
        select.selectByValue(acctId);
        select=new Select(driverUtil.getWebElement(SELECT_FB_PAGE_ID));
        select.selectByValue(pageId);

        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        String successMSG1 = String.format(CONNECT_MSG, "Facebook Ads");
        System.out.println("successMSG1:"+successMSG1);
        //String successMSG2 = String.format(SUCCESS_MSG_2, contact);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        //boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
        try{

            System.out.println("error message:"+driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText());
            if ("Selected Authorization is not configured for Reporting".contains(driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText())) {
                // throw new AutomationException("Simpli.fi Validation failed message is not displayed");
            }else if("Organization ID does not exist".contains(driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText())){

            }
            CommonSteps.takeScreenshot();
            //waitForInvisibleElement(By.xpath(SPINNER),10);
            driverUtil.getWebElement(("//button[@aria-label='Close']")).click();
            driverUtil.getWebElement((INTEGRATION_CANCEL_BUTTON)).click();
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    public void clickOnSimpliContinueButton(String OrgId, String authName, String reportFlag,String reportName) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Simpli.Fi-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        selectClientAuthorization(authName);
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
            selectClientAuthorization(reportName);

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);

        enterOrgId(OrgId);
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();

        String successMSG1 = String.format(CONNECT_MSG, "Simpli.Fi");
        System.out.println("successMSG1:"+successMSG1);
        //String successMSG2 = String.format(SUCCESS_MSG_2, contact);
        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
        //boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
        if (!isSuccessMSG1 ) {
            throw new AutomationException("Client Update save message is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(successMSG1));
        try{

            System.out.println("error message:"+driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText());
            if(driverUtil.getWebElement("(//div[@data-status='error'])[1]").isDisplayed()) {
                driverUtil.getWebElement(("//button[@aria-label='Close']")).click();
                if ("Selected Authorization is not configured for Reporting".contains(driverUtil.getWebElement("(//div[@data-status='error'])[2]").getText())) {
                    // throw new AutomationException("Simpli.fi Validation failed message is not displayed");

                } else if ("Organization ID does not exist".contains(driverUtil.getWebElement("(//div[@data-status='error'])[2]").getText())) {

                }
            }
            CommonSteps.takeScreenshot();
            //waitForInvisibleElement(By.xpath(SPINNER),10);

            driverUtil.getWebElement((INTEGRATION_CANCEL_BUTTON)).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void enterOrgId(String OrgId) throws AutomationException {
        if (OrgId != null && !OrgId.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(SIMPLI_ORG_ID);
            name.clear();
            name.sendKeys(OrgId);
        }
    }

    public void clickOnGoogleContinueButton(String integrationType, String accountIdName, String ownerAccIdPropertyId, String authName, String reportFlag, String reportName, String locationName) throws AutomationException {

        {
            waitForInvisibleElement(By.xpath(SPINNER),3);
            driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, integrationType)).click();
            driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
            selectClientAuthorization(authName);
            if(reportFlag.equals("Yes")){

                driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();
                selectClientAuthorization(reportName);

            }
            waitForInvisibleElement(By.xpath(SPINNER),3);
            String platformName="";
            if(integrationType.equalsIgnoreCase("Google Analytics V4-id")){
                enterPropertyId(ownerAccIdPropertyId);
                platformName="Google Analytics V4";
            }else if(integrationType.equalsIgnoreCase("Google My Business-id")){
                enterAccountName(accountIdName);
                enterLocationName(locationName);
                platformName="Google My Business";

            }else if(integrationType.equalsIgnoreCase("Google Ads-id")){
                enterAccountId(ownerAccIdPropertyId);
                enterOwnerAccountId(accountIdName);
                platformName="Google Ads";
            }

            String successMSG1 = String.format(CONNECT_MSG, platformName);
            System.out.println("successMSG1:"+successMSG1);
            //String successMSG2 = String.format(SUCCESS_MSG_2, contact);
            boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(successMSG1).isDisplayed();
            //boolean isSuccessMSG2 = driverUtil.getWebElementAndScroll(successMSG2).isDisplayed();
            if (!isSuccessMSG1 ) {
                throw new AutomationException("Client Update save message is not displayed");
            }
            CommonSteps.takeScreenshot();
            waitForInvisibleElement(By.xpath(successMSG1));

            driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
            try{

                System.out.println("error message:"+driverUtil.getWebElement("(//div[@data-status='error'])[1]").getText());
                if(driverUtil.getWebElement("(//div[@data-status='error'])[1]").isDisplayed()) {
                    driverUtil.getWebElement(("//button[@aria-label='Close']")).click();
                    if ("Selected Authorization is not configured for Reporting".contains(driverUtil.getWebElement("(//div[@data-status='error'])[2]").getText())) {
                        // throw new AutomationException("Simpli.fi Validation failed message is not displayed");

                    } else if ("Invalid Account ID ".contains(driverUtil.getWebElement("(//div[@data-status='error'])[2]").getText())) {

                    }else if("Validation failed.".contains(driverUtil.getWebElement("(//div[@data-status='error'])[2]").getText())){

                    }
                }
                CommonSteps.takeScreenshot();
                //waitForInvisibleElement(By.xpath(SPINNER),10);

                driverUtil.getWebElement((INTEGRATION_CANCEL_BUTTON)).click();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void enterAccountId(String ownerAccIdPropertyId) throws AutomationException {
        if (ownerAccIdPropertyId != null && !ownerAccIdPropertyId.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(ACCOUNT_ID);
            name.clear();
            name.sendKeys(ownerAccIdPropertyId);
        }
    }
    private void enterOwnerAccountId(String accountIdName) throws AutomationException {
        if (accountIdName != null && !accountIdName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(OWNER_ACCOUNT_ID);
            name.clear();
            name.sendKeys(accountIdName);
        }
    }
    private void enterAccountName(String accountIdName) throws AutomationException {
        if (accountIdName != null && !accountIdName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(ACCOUNT_NAME);
            name.clear();
            name.sendKeys(accountIdName);
        }
    }
    private void enterLocationName(String locationName) throws AutomationException {
        if (locationName != null && !locationName.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(LOCATION_NAME);
            name.clear();
            name.sendKeys(locationName);
        }
    }

    private void enterPropertyId(String ownerAccIdPropertyId) throws AutomationException {
        if (ownerAccIdPropertyId != null && !ownerAccIdPropertyId.isEmpty()) {
            WebElement name = driverUtil.getWebElementAndScroll(PROPERTY_ID);
            name.clear();
            name.sendKeys(ownerAccIdPropertyId);
        }
    }
}

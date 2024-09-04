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
    private static final String SELECT_DROPDOWN="//*[@class=' css-18euh9p']";
    //private static final String SELECT_DROPDOWN="//*[contains(text(),'Select Authorization')]";
    private static final String SELECT_BUSINESS_SECTOR = "//div[@role='listbox']//div[text()='%s']";
    private static final String SELECT_FB_ACCOUNT_ID = "//*[@name='ad_account_id']";
    private static final String SELECT_FB_PAGE_ID = "//*[@name='page_id']";
    private static final String DISCONNECT_CONFIRM_MSG = "//*[@role='alertdialog']/div";
    private static final String ERR_MSG_SMPLI="(//div[contains(text(),'Organization ID does not exist.')])[1]";



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

    public void clickOnEmailContinueButton(String clientName, String reportName,String reportFlag) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Email-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);
        selectClientAuthorization(reportName);
        enterClientName(clientName);
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        try{
            /*WebElement w = driverUtil.getWebElementAndScroll("//div[contains(text(),'Email - Integration Authorization')])");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("Email integration message is not displayed");
            }
            CommonSteps.takeScreenshot();*/
            WebElement w = driverUtil.getWebElementAndScroll("//div[text()='Validation failed.']");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("Email Validation failed message is not displayed");
            }
            CommonSteps.takeScreenshot();
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

    }

    public void clickOnFaceBookContinueButton( String acctId, String pageId, String authId,String reportFlag) throws AutomationException {

        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Facebook Ads-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);
       // driverUtil.getWebElement(SELECT_DROPDOWN).click();
        //driverUtil.getWebElement("//input[@role='combobox']").sendKeys(authId);
        selectClientAuthorization(authId);
        Select select=new Select(driverUtil.getWebElement(SELECT_FB_ACCOUNT_ID));
        select.selectByValue(acctId);
        select=new Select(driverUtil.getWebElement(SELECT_FB_PAGE_ID));
        select.selectByValue(pageId);

        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        try{
            /*WebElement w = driverUtil.getWebElementAndScroll("//div[contains(text(),'Integration Authorization')])");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("FaceBook integration message is not displayed");
            }*/
            WebElement w = driverUtil.getWebElementAndScroll("//div[text()='Validation failed.']");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("FaceBook Validation failed message is not displayed");
            }
            CommonSteps.takeScreenshot();
            driverUtil.getWebElement((INTEGRATION_CANCEL_BUTTON)).click();
            CommonSteps.takeScreenshot();
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    public void clickOnSimpliContinueButton(String OrgId, String reportName, String reportFlag) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(String.format(INTEGRATION_PLATFORM_BTN, "Simpli.Fi-id")).click();
        driverUtil.getWebElement(INTEGRATION_ADVERTISING_CHECKBOX).click();
        if(reportFlag.equals("Yes")){

            driverUtil.getWebElement(INTEGRATION_REPORTING_CHECKBOX).click();

        }
        waitForInvisibleElement(By.xpath(SPINNER),3);
        selectClientAuthorization(reportName);
        enterOrgId(OrgId);
        driverUtil.getWebElement((INTEGRATION_CONNECT_BUTTON)).click();
        try{
            /*WebElement w = driverUtil.getWebElementAndScroll("//div[contains(text(),'Email - Integration Authorization')])");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("Email integration message is not displayed");
            }
            CommonSteps.takeScreenshot();*/
            WebElement w = driverUtil.getWebElementAndScroll("(//div[contains(text(),'Organization ID does not exist.')])[1]");
            boolean invalidInputMsg = w.isDisplayed();
            if (!invalidInputMsg) {
                throw new AutomationException("Simpli.fi Validation failed message is not displayed");
            }
            CommonSteps.takeScreenshot();
            //waitForInvisibleElement(By.xpath(SPINNER),10);
            driverUtil.getWebElement(("//button[@aria-label='Close']")).click();
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
}

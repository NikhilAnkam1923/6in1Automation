package com.centrifi.automation.pages;

import com.centrifi.automation.exception.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;

public class AuthorizationsPage extends BasePage{
    private static Map<String, String> clientDetails = new LinkedHashMap<>();
    private static final String AUTH_GOOGLE_BUTTON="//button[@id='authByOauth-google']";
    private static final String AUTH_EMAIL_BUTTON="//button[@id='authByKeys-email']";
    private static final String AUTH_FACEBOOK_BUTTON="//button[@id='authByOauth-facebook']";
    private static final String AUTH_SIMPLI_BUTTON="//button[@id='authByKeys-simplifi']";
    private static final String DELETE_YES_BUTTON="//button[text()='Yes']";
    private static final String DELETE_NO_BUTTON="//button[text()='No']";
    private static final String AUTH_ADVERTISING_CHECKBOX="//span[text()='Authorize Advertising']";
    private static final String AUTH_REPORTING_CHECKBOX="//span[text()='Authorize Reporting']";
    private static final String CONTINUE_WITH_BUTTON="//button[contains(text(),'Continue with ')]";
    private static final String FACEBOOK_RECONNECT_BUTTON="//div[text()='Reconnect']";
    private static final String FACEBOOK_USERNAME="//input[@name='email']";
    private static final String FACEBOOK_PASSWORD="//input[@name='pass']";
    private static final String GOOGLE_USERNAME="//input[@name='identifier']";
    private static final String GOOGLE_USERNAME_NEXT="//span[text()='Next']";
    private static final String GOOGLE_PASSWORD="//input[@name='Passwd']";
    private static final String GOOGLE_CHECKBOX="//input[@id='i1']";
    private static final String GOOGLE_CONTINUE="//span[text()='Continue']";
    private static final String PLATFORM_NAME="//input[@name='name']";

    private static final String FACEBOOK_LOGIN="//button[@name='login']";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String ROW_NO="//p[text()='Rows per page:']";
    private static final String DROP_DOWN="//select[contains(@class,'chakra-select TablePagination_Pager')]";
    private static final String AUTH_NEXT_PAGE="//*[text()='Rows per page:']/following::button[@aria-label='Next page']";
    private static final String AUTH_PREV_PAGE="//*[text()='Rows per page:']/following::button[@aria-label='Previous page']";

    public void clickOnAuthorizationButton() throws AutomationException {
        clickOnSideBarMenuItem("Authorizations");
    }

    @Override
    String getName() {
        return  "Authorizations";
    }

    public void clickOnFaceBookButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(AUTH_FACEBOOK_BUTTON).click();
    }

    public void clickOnContinueWithFacebook() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(AUTH_REPORTING_CHECKBOX).click();
        driverUtil.getWebElement(AUTH_ADVERTISING_CHECKBOX).click();
        driverUtil.getWebElement(CONTINUE_WITH_BUTTON).click();
    }

    public void clickOnLoginWithFacebook(String userName, String passWord) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        WebElement userNameInput = driverUtil.getWebElement(FACEBOOK_USERNAME);
        if (userNameInput != null) {
            WebElement passwordInput = driverUtil.getWebElement(FACEBOOK_PASSWORD);
            WebElement login = driverUtil.getWebElement(FACEBOOK_LOGIN);
            userNameInput.clear();
            userNameInput.sendKeys(userName);
            passwordInput.clear();
            passwordInput.sendKeys(passWord);
            login.click();
            driverUtil.waitForLoadingPage();
        }
    }

    public void clickOnConnectOrReconnectButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(FACEBOOK_RECONNECT_BUTTON).click();
        waitForInvisibleElement(By.xpath(SPINNER),3);
    }


    public void clickOnDeleteAuthorizationRecord(String platformName,String name) throws AutomationException {
       // driverUtil.getWebElementAndScroll(ROW_NO);
       // Select select=new Select(driverUtil.getWebElement(DROP_DOWN));
        //select.selectByValue("50");
        //driverUtil.getWebElementAndScrollUp();
        waitForInvisibleElement(By.xpath(SPINNER),3);
        List<WebElement> noOfRows =driverUtil.getWebElements("//tbody/tr");
        System.out.println("size:"+noOfRows.size());
        int rowCount=1;
        for(int i=0;i<noOfRows.size();i++){
            List < WebElement > Columns_row = noOfRows.get(i).findElements(By.tagName("td"));
            for(int j=0;j<Columns_row.size();j++){

              String platfrmName=driverUtil.getWebElement("//tbody/tr["+rowCount+"]/td[1]/div/div/div[2]").getText ();
              String nme=driverUtil.getWebElement("//tbody/tr["+rowCount+"]/td[2]/div/p").getText ();

                if(platformName.equals(platfrmName) && name.equals(nme)){

                    try{
                        driverUtil.getWebElementAndScroll("//tbody//tr["+rowCount+"]//td[6]//button[2]").click();
                        driverUtil.getWebElement(DELETE_NO_BUTTON).click();
                        break;
                    }catch (Exception e){
                        //driverUtil.getWebElementAndScroll("//tbody/tr["+rowCount+"]/td[2]/div/p");
                        // driverUtil.getWebElementAndScroll("//tbody//tr["+rowCount+"]//td[6]//button[2]");
                        //driverUtil.scrollTo("//tbody//tr["+rowCount+"]//td[6]//button[2]");
                        // driverUtil.getWebElementAndScroll("//tbody//tr["+rowCount+"]//td[6]//button[2]").click();
                       // driverUtil.getWebElement("//tbody//tr["+rowCount+"]//td[6]//button[2]").click();
                        driverUtil.getWebElementAndScroll(ROW_NO);
                        driverUtil.getWebElement(AUTH_NEXT_PAGE).click();
                        clickOnDeleteAuthorizationRecord(platformName, name);

                    }
                    break;
               }
            }
            rowCount++;
        }

    }

    public void clickOnConnectGoogleButton(String userName, String password) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        WebElement userNameInput = driverUtil.getWebElement(GOOGLE_USERNAME);
        if (userNameInput != null) {

            userNameInput.clear();
            userNameInput.sendKeys(userName);
            WebElement login = driverUtil.getWebElement(GOOGLE_USERNAME_NEXT);
            login.click();
            WebElement passwordInput = driverUtil.getWebElement(GOOGLE_PASSWORD);
            passwordInput.clear();
            passwordInput.sendKeys(password);
             login = driverUtil.getWebElement(GOOGLE_USERNAME_NEXT);
            login.click();

            driverUtil.waitForLoadingPage();
        }
    }

    public void clickOnllOptionOnConnect() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        try{
            driverUtil.getWebElement(GOOGLE_CHECKBOX).click();
            driverUtil.getWebElement(GOOGLE_CONTINUE).click();
        }catch (Exception e){
            driverUtil.getWebElement(GOOGLE_CONTINUE).click();
        }

        waitForInvisibleElement(By.xpath(SPINNER),3);
    }

    public void clickOnContinueWithGoogle(String name) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(PLATFORM_NAME).clear();
        driverUtil.getWebElement(PLATFORM_NAME).sendKeys(name);
        driverUtil.getWebElement(AUTH_GOOGLE_BUTTON).click();
    }
}

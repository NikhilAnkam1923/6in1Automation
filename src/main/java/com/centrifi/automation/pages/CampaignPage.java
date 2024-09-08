package com.centrifi.automation.pages;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;

public class CampaignPage extends BasePage{

    private static final String ADD_CAMPAIGN_BTN="//button[text()='New Campaign']";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";
    private static final String CAMPAIGN_NAME = "//input[@name='name']";
    private static final String CAMPAIGN_BUDGET = "//input[@name='budget']";
    private static final String NEXT_BUTTON = "//button[@name='Next']";
    private static final String CLOSE_BUTTON = "//button[text()='Close']";
    private static final String SELECT_CLIENT="(//*[@class=' css-18euh9p'])[1]";
    private static final String SELECT_GEOGRAPHY="//select[@name='formArr.0.key']";
    private static final String SEARCH_INPUT="(//input[@type='text'])[1]";
    private static final String SELECT_ALL="//button[text()='Select All']";
    private static final String SAVE_BUTTON="//button[text()='Save']";
    private static final String SELECT_CAMPAIGN_GOAL="(//*[@class=' css-39njxa'])[1]";
    //private static final String SELECT_DROPDOWN="//*[contains(text(),'Select Authorization')]";
    private static final String SELECT_BUSINESS_SECTOR = "//div[@role='listbox']//div[text()='%s']";
    //private static final String SUCCESS_MSG_2 = "//div[contains(text(),'Your campaign's goal has been saved')]";
    private static final String SUCCESS_MSG = "//div[contains(text(),'goal has been saved')]";
    @Override
    String getName() {
        return "Campaign page";
    }

    public void clickOnCampaignsButton() throws AutomationException {
        clickOnSideBarMenuItem("Campaigns");
    }

    public void clickOnCAddNewCampaignButton() throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(ADD_CAMPAIGN_BTN).click();
    }

    public void enterCampaignInputDetails(String campaignName,String Budget,String ClientName,String CampaignGoal) throws AutomationException {
        waitForInvisibleElement(By.xpath(SPINNER),3);
        driverUtil.getWebElement(CAMPAIGN_NAME).clear();
        driverUtil.getWebElement(CAMPAIGN_NAME).sendKeys(campaignName);
        selectCampaignGoal(CampaignGoal);
        selectClientName(ClientName);
        driverUtil.getWebElement(CAMPAIGN_BUDGET).clear();
        driverUtil.getWebElement(CAMPAIGN_BUDGET).sendKeys(Budget);
        driverUtil.getWebElement(NEXT_BUTTON).click();

        boolean isSuccessMSG1 = driverUtil.getWebElementAndScroll(SUCCESS_MSG).isDisplayed();

        if (!isSuccessMSG1 ) {
            throw new AutomationException("Your campaign's goal has been saved. is not displayed");
        }
        CommonSteps.takeScreenshot();
        waitForInvisibleElement(By.xpath(SUCCESS_MSG));
    }
    public void selectCampaignGoal(String CampaignGoal) throws AutomationException {
        if (CampaignGoal != null && !CampaignGoal.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(SELECT_CAMPAIGN_GOAL, 4);
            driverUtil.moveToElementAndClick(business);
            //driverUtil.getWebElementAndScroll(SELECT_DROPDOWN, 4).click();
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, CampaignGoal));
        }
    }
    public void selectClientName(String ClientName) throws AutomationException {
        if (ClientName != null && !ClientName.isEmpty()) {
            WebElement business = driverUtil.getWebElementAndScroll(SELECT_CLIENT, 4);
            driverUtil.moveToElementAndClick(business);
            //driverUtil.getWebElementAndScroll(SELECT_DROPDOWN, 4).click();
            driverUtil.clickUsingJavaScript(String.format(SELECT_BUSINESS_SECTOR, ClientName));
        }
    }
}

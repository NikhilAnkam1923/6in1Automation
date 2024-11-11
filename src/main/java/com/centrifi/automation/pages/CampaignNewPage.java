package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import com.centrifi.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.centrifi.automation.pages.BasePage.driverUtil;

public class CampaignNewPage extends BasePage {
    String getName() {
        return "CampaignNew page";
    }

    private static final String ALL_STATUS_BTN = "//div[text()='All Statuses']";
    private static final String ALL_DATERANGES_BTN = "//div[text()='All Date Ranges']";
    private static final String ORGANIZATION_DROPDOWN = "//div[text()='Organization']/parent::div[@class=' css-j93siq']";
    private static final String SEARCH_BTN = "//input[@placeholder='Search...']";
    private static final String NEWCAMPAIGN_BTN = "//button[text()='New Campaign']";
    private static final String CAMPAIGN_BUILDER = "//h2[text()='Campaign Builder']";
    private static final String CAMPAIGN_NAME = "//input[@placeholder='Enter campaign name']";
    private static final String CAMPAIGN_GOAL = "//div[text()='Select campaign goal']";
    private static final String CLIENT = "//div[@class=' css-18euh9p']";
    private static final String BUDGET = "//input[@placeholder='Enter budget']";
    private static final String POP_UP_CLOSE_BTN = "//button[text()='Close']";
    private static final String NEXT_BTN = "//button[text()='Next']";
    private static final String SELECT_GEOGRAPHY = "//p[text()='Select Geography']";


    public void clickOnCampaignsButton() throws AutomationException {
        clickOnSideBarMenuItem("Campaign");
    }

    public void verifyThePage() throws AutomationException {
        WebElement ADD_NEW_CAMPAIGN_BUTTON = driverUtil.getWebElement("//button[@class='chakra-button css-9364e4']");
        if (!ADD_NEW_CAMPAIGN_BUTTON.isDisplayed()) {
            throw new AutomationException("New button is not displayed on the Campaign page.");
        }
        CommonSteps.takeScreenshot();
    }

    public void selectAllStatusDropdown(String allStatusesList) throws AutomationException {
        driverUtil.getWebElement(ALL_STATUS_BTN).click();
        if (allStatusesList != null && !allStatusesList.isEmpty()) {
            for (String statusToSelect : allStatusesList.split(",")) {
                String trimmedStatus = statusToSelect.trim();
                List<WebElement> matchingOptions = DriverFactory.drivers.get().findElements(By.xpath("//div[@role='listbox']//div[text()='" + trimmedStatus + "']"));
                if (matchingOptions.isEmpty()) {
                    throw new AutomationException("Status '" + trimmedStatus + "' not found in the dropdown.");
                }
                driverUtil.moveToElementAndClick(matchingOptions.get(0));
            }
        }
    }

    public void selectAllDateRangeDropdown(String allDateRanges) throws AutomationException {
        driverUtil.getWebElement(ALL_DATERANGES_BTN).click();
        List<WebElement> matchingDateRangeOptions = DriverFactory.drivers.get().findElements(By.xpath("//div[@role='listbox']//div[text()='" + allDateRanges.trim() + "']"));
        if (matchingDateRangeOptions.isEmpty()) {
            throw new AutomationException("Date Range '" + allDateRanges + "' not found in the dropdown.");
        }
        driverUtil.moveToElementAndClick(matchingDateRangeOptions.get(0));
        Assert.assertEquals(allDateRanges, driverUtil.getWebElement("//div[@class=' css-1xa1gs2']").getText(), "The Selected All date range value is mismatch");
        CommonSteps.logInfo("selectAllDateRangeDropdown Test Pass Successfully ");
    }

    public void selectOrganizationDropdown(String organization) throws AutomationException {
        driverUtil.getWebElement(ORGANIZATION_DROPDOWN).click();
        List<WebElement> matchingOrganizationOptions = DriverFactory.drivers.get().findElements(By.xpath("//div[@role='listbox']//div[text()='" + organization.trim() + "']"));
        if (matchingOrganizationOptions.isEmpty()) {
            throw new AutomationException("Organization '" + organization + "' not found in the dropdown.");
        }
        driverUtil.moveToElementAndClick(matchingOrganizationOptions.get(0));
        // Assert.assertEquals(organization, driverUtil.getWebElement("//div[@class=' css-1xa1gs2']").getText(), "The Selected Organization value is mismatch");
        //CommonSteps.logInfo("selectOrganizationDropdown Test Pass Successfully ");
    }

    public void entersValuesInSearchField(String... search) throws AutomationException {
        if (!SEARCH_BTN.isEmpty()) {
            driverUtil.getWebElement(SEARCH_BTN).clear();
        }
        driverUtil.getWebElement(SEARCH_BTN).sendKeys(search);
        WebDriverUtil.waitForAWhile(3);
    }

    public void clickOnNewCampaignButton() throws AutomationException {
        driverUtil.getWebElement(NEWCAMPAIGN_BTN).click();
        WebDriverUtil.waitForAWhile(3);
        driverUtil.getWebElement(CAMPAIGN_BUILDER).isDisplayed();
    }

    public void enterCampaignOverviewInputDetailsAndClickOnNext(String campaignName, String campaignGoal, String client, String budget) throws AutomationException {
        driverUtil.getWebElement(CAMPAIGN_NAME).sendKeys(campaignName);
        selectCampaignGoal(campaignGoal);
        driverUtil.scrollTo(CLIENT);
        selectClient(client);
        driverUtil.scrollTo(BUDGET);
        driverUtil.getWebElement(BUDGET).sendKeys(budget);
        WebDriverUtil.waitForAWhile(3);
        driverUtil.scrollTo(NEXT_BTN);
        driverUtil.getWebElement(NEXT_BTN).click();
        WebDriverUtil.waitForAWhile(3);
        driverUtil.getWebElement(SELECT_GEOGRAPHY).isDisplayed();
        }

    public void selectCampaignGoal(String campaignGoal) throws AutomationException {
        driverUtil.getWebElement(CAMPAIGN_GOAL).click();

        List<WebElement> matchingDateRangeOptions = DriverFactory.drivers.get().findElements(By.xpath("//div[@role='listbox']//div[text()='" + campaignGoal.trim() + "']"));
        if (matchingDateRangeOptions.isEmpty()) {
            throw new AutomationException("Campaign Goal '" + campaignGoal + "' not found in the dropdown.");
        }
        driverUtil.moveToElementAndClick(matchingDateRangeOptions.get(0));
    }

    public void selectClient(String client) throws AutomationException {
        driverUtil.getWebElement(CLIENT).click();
        List<WebElement> matchingDateRangeOptions = DriverFactory.drivers.get().findElements(By.xpath("//div[@role='listbox']//div[text()='" + client.trim() + "']"));
        if (matchingDateRangeOptions.isEmpty()) {
            throw new AutomationException("Client name'" + client + "' not found in the dropdown.");
        }
        driverUtil.moveToElementAndClick(matchingDateRangeOptions.get(0));
        //WebDriverUtil.waitForVisibleElement(By.xpath("//button[text()='Close']"));
        //driverUtil.getWebElement(POP_UP_CLOSE_BTN).click();
    }
}
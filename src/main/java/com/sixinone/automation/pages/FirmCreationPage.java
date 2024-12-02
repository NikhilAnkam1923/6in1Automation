package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class FirmCreationPage {

    public static WebDriverUtil driverUtil = new WebDriverUtil();

    public boolean isFirmBreadcrumbVisible() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, LoginPage.SPINNER);
        String breadcrumbLocator = "//nav[@aria-label='breadcrumb']//ol//li[@class='breadcrumb-item']//a[text()='Firm']";

        try {
            WebElement breadcrumbElement = driverUtil.getWebElement(breadcrumbLocator, 5);
            return breadcrumbElement != null && breadcrumbElement.isDisplayed();
        } catch (Exception e) {
            CommonSteps.logError("Error locating breadcrumb: " + e.getMessage());
            return false;
        }
    }

    public boolean isFirmBreadcrumbVisibleForCreate() throws AutomationException {
        String breadcrumbLocator = "//nav[@aria-label='breadcrumb']//ol//li[@class='breadcrumb-item']//a[text()='Firm']/following-sibling::a[text()='New']";

        try {
            WebElement breadcrumbElement = driverUtil.getWebElement(breadcrumbLocator, 5);
            return breadcrumbElement != null && breadcrumbElement.isDisplayed();
        } catch (Exception e) {
            CommonSteps.logError("Error locating breadcrumb: " + e.getMessage());
            return false;
        }
    }
}

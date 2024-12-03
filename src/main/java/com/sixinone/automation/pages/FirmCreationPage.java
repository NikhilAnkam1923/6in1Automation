package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.logging.Logger;

public class FirmCreationPage {

    public static WebDriverUtil driverUtil = new WebDriverUtil();
    public static final String SAVEBUTTON = "//button[@type='submit']";
    private static final Map<String, By> fieldLocators = Map.of(
            "Firm Name", By.xpath("//input[@name='lawFirm.name']"),
            "Firm Email Address", By.xpath("//input[@name='lawFirm.email']"),
            "Phone #", By.xpath("//input[@name='lawFirm.phone']"),
            "First Name", By.xpath("//input[@name='accountOwner.firstName']"),
            "Mobile #", By.xpath("//input[@name='accountOwner.phone']"),
            "Email Address", By.xpath("//input[@name='accountOwner.email']"));

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

    public void verifyUserEntersTheInput(String textBox, String input) throws AutomationException {
        enterValueInField(textBox, input);
    }

    public void enterValueInField(String textBox, String input) throws AutomationException {
        By fieldLocator = fieldLocators.get(textBox);
        if (fieldLocator != null) {
            WebElement element = driverUtil.getWebElementAndScroll(fieldLocator, 10);
            element.clear();
            element.sendKeys(input);
        } else {
            throw new IllegalArgumentException("Field locator not found for: " + textBox);
        }
    }

    public void clickOnSaveButton(String button) throws AutomationException {
        WebElement saveBtn = driverUtil.getWebElementAndScroll(SAVEBUTTON, 5);
        saveBtn.click();
    }

    public void verifyDisplayedErrorMessage(String expectedErrorMsg,String field) throws AutomationException {
        By errorMessageLocator = By.xpath("//input[@name='"+field+"']");;

        WebElement errorMessageElement = driverUtil.getWebElementAndScroll(errorMessageLocator, 5);
        String actualMessage = errorMessageElement.getText().trim();
        if (!actualMessage.equals(expectedErrorMsg)) {
            throw new AutomationException("Expected error message: '" + expectedErrorMsg + "', but found: '" + actualMessage + "'");
        }
    }


}

package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.WebElement;


public class FirmCreationPage {

    public static WebDriverUtil driverUtil = new WebDriverUtil();
    public static final String FIELD_XPATH = "//label[text()='%s']/following-sibling::input";

    public boolean isFirmBreadcrumbVisible() {
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

    public boolean enterDataInFieldByLabel(String fieldData, String fieldName){
        String inputFieldXPath = String.format(FIELD_XPATH, fieldName);

        try {
            WebElement inputField = driverUtil.getWebElement(inputFieldXPath, 5);
            if (inputField == null) {
                throw new AutomationException("Input field with label '" + fieldName + "' not found.");
            }

            inputField.clear();
            inputField.sendKeys(fieldData);
            return true;
        } catch (Exception e) {
            CommonSteps.logError("Error entering data in field with label '" + fieldName + "': " + e.getMessage());
            return false;
        }
    }

    public boolean enterFirmPhone(String data){
        try {
            String phoneFieldXPath = "//input[@name='lawFirm.phone']";
            WebElement PHONE_FIRM = driverUtil.getWebElement(phoneFieldXPath, 5);

            if (PHONE_FIRM == null) {
                throw new AutomationException("Firm phone input field not found.");
            }

            PHONE_FIRM.clear();

            PHONE_FIRM.sendKeys(data);

            return true;
        } catch (Exception e) {
            CommonSteps.logError("Error entering firm phone: " + e.getMessage());
            return false;
        }
    }

    public boolean enterUserPhone(String data){
        try {
            String phoneFieldXPath = "//input[@name='accountOwner.phone']";
            WebElement PHONE_USER = driverUtil.getWebElement(phoneFieldXPath, 5);

            if (PHONE_USER == null) {
                throw new AutomationException("Primary User phone input field not found.");
            }

            PHONE_USER.clear();

            PHONE_USER.sendKeys(data);

            return true;
        } catch (Exception e) {
            CommonSteps.logError("Error entering firm phone: " + e.getMessage());
            return false;
        }
    }



    public boolean selectOption(String labelName, String option){
        try {
            WebElement comboBox = driverUtil.getWebElement(String.format("//label[text()='%s']/following-sibling::div/span/following-sibling::div//div[contains(@class,'select__indicators')]", labelName), 5);

            comboBox.click();

            WebElement selectOption = driverUtil.getWebElement(String.format("//div[contains(@class,'select__menu-list')]//div[text()='%s']", option), 5);

            selectOption.click();

            return true;
        } catch (Exception e) {
            CommonSteps.logError("Failed to select license type: " + option);
            return false;
        }
    }

    public boolean verifySuccessMessage(String expectedMessage){
        try {
            WebElement toastMessageElement = driverUtil.getWebElement("//div[@class='Toastify__toast-body']//div/following-sibling::div[contains(text(),'" + expectedMessage + "')]", 10);

            if (toastMessageElement != null && toastMessageElement.isDisplayed()) {
                String actualMessage = toastMessageElement.getText();
                return actualMessage.equals(expectedMessage);
            } else {
                return false;
            }
        } catch (Exception e) {
            CommonSteps.logError("Failed to verify the success message: " + expectedMessage);
            return false;
        }
    }


}

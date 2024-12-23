package com.sixinone.automation.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.CommonUtil;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.sixinone.automation.util.WebDriverUtil;

import java.io.IOException;

public class EstateContactsPage extends BasePage {

    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";

    String getName() {
        return "";
    }

    public void navigateToEstateContactsTab() throws AutomationException {
        WebElement tempUser = driverUtil.getWebElement("//a[text()='Jonny Vasco']");
        tempUser.click();
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
    }
}

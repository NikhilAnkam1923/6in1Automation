package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class EstateCreationPage extends BasePage{

    private static final String FIRST_NAME_FIELD = "//input[@name='firstName']";
    private static final String LAST_NAME_FIELD = "//input[@name='lastName']";
    private static final String SSN_FIELD = "//input[@name='ssn']";
    private static final String PROCEED_BUTTON = "//button[@type='submit']";

    @Override
    String getName() {
        return "";
    }

    public void enterFirstAndLastNameAndSSN() {

    }
}

package com.centrifi.automation.pages;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class LoginPage  extends BasePage {
    public static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());
    public static final String USER_NAME_INPUT = "//input[@name='email']";
    public static final String PASSWORD_INPUT = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//button[@type='submit']";

    /**
     * This function is used to login.
     * @param userName
     * @param password
     * @throws AutomationException
     */
    public void doLogin(String userName, String password) throws AutomationException {
        WebElement userNameInput = driverUtil.getWebElement(USER_NAME_INPUT);
        if(userNameInput!=null) {
            WebElement passwordInput = driverUtil.getWebElement(PASSWORD_INPUT);
            WebElement login = driverUtil.getWebElement(LOGIN_BUTTON);
            userNameInput.clear();
            userNameInput.sendKeys(userName);
            passwordInput.clear();
            passwordInput.sendKeys(password);
            login.click();
            driverUtil.waitForLoadingPage();
        }
    }

    @Override
    public String getName() {
        return"Login";
    }
}

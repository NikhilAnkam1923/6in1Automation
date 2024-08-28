package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import com.centrifi.automation.util.PropertyReader;
import com.centrifi.automation.util.WebDriverUtil;
import org.bouncycastle.cms.PasswordRecipientId;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.logging.Logger;

public class LoginPage extends BasePage {
    public static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());
    public static final String USER_NAME_INPUT = "//input[@name='email']";
    public static final String PASSWORD_INPUT = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//button[@type='submit']";
    private static final String USER_EMAIL_INPUT = "//input[@id='username']";
    private static final String USER_PASSWORD = "//input[@id='password']";
    private static final String LOGIN_BTN = "//input[@id='kc-login']";
    private static final String LOGIN_TEXT = "//h1[@id='kc-page-title']";
    private static final String HOME_PAGE = "//a[@class='chakra-link active css-spn4bz']";
    private static final String USER_MENU = "//header/button[contains(@class,'chakra-menu__menu-button')]";
    private static final String LOGOUT_BUTTON = "//div[@class='UserDropdown css-1kfu8nn']//button[text()='Logout']";
    private static final String FORGET_PASSWORD_LINK = "//a[contains(@class,'forget-password-link')]";
    private static final String FORGOT_PASS_TXT = "//*[contains(text(),'Forget Password? ')]";
    private static final String ERROR_MSG = "//span[@id='input-error']";

    /**
     * This function is used to login.
     *
     * @param userName
     * @param password
     * @throws AutomationException
     */
    public void doLogin(String userName, String password) throws AutomationException {
        WebElement userNameInput = driverUtil.getWebElement(USER_NAME_INPUT);
        if (userNameInput != null) {
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


    public void clickOnLoginButton() throws AutomationException {
        driverUtil.getWebElement("//li[@class='menBut']//a[contains(text(),'Log In')]").click();
    }

    public void loginToCentriFi(String userEmail, String password) throws AutomationException {
        doLogoutFromCentrifiIfAlreadyLoggedIn();
        enterUserEmail(userEmail);
        enterPassword(password);
        clickLoginButton();
    }

    public void enterUserEmail(String userEmail) throws AutomationException {
        driverUtil.getWebElement(USER_EMAIL_INPUT).sendKeys(userEmail);
    }

    public void enterPassword(String password) throws AutomationException {
        driverUtil.getWebElement(USER_PASSWORD).sendKeys(password);
    }

    public void clickLoginButton() throws AutomationException {
        driverUtil.getWebElement(LOGIN_BTN).click();
    }

    public void verifyLoginPageUIAttributes() throws AutomationException {
        Assert.assertEquals("MEMBER LOGIN", getLogInText().trim());
        Assert.assertEquals("a", verifyForgotPasswordLink());
    }

    public String getLogInText() throws AutomationException {
        return driverUtil.getWebElementAndScroll(LOGIN_TEXT).getText();
    }

    public String verifyForgotPasswordLink() throws AutomationException {
        return driverUtil.getWebElement(FORGET_PASSWORD_LINK).getTagName();
    }

    public void verifyHomePage() throws AutomationException {
        driverUtil.getWebElement(USER_MENU);
    }

    public void doLogoutFromCentrifi() throws AutomationException {
        WebElement userMenu = driverUtil.getWebElementAndScroll(USER_MENU);
        ((JavascriptExecutor) DriverFactory.drivers.get()).executeScript("arguments[0].scrollIntoView(true);", userMenu);
        userMenu.click();
        driverUtil.getWebElementAndScroll(LOGOUT_BUTTON).click();
        driverUtil.getWebElementAndScroll(LOGIN_TEXT);
    }

    public void doLogoutFromCentrifiIfAlreadyLoggedIn() throws AutomationException {
        if ((driverUtil.getWebElement(USER_MENU, 2) != null)) {
            driverUtil.getWebElement(USER_MENU).click();
            driverUtil.getWebElement(LOGOUT_BUTTON).click();
        }
    }

    public void verifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        Assert.assertEquals(errorMessage, driverUtil.getWebElement(ERROR_MSG, 0, "Unable to locate error message field").getText().trim());
    }

    @Override
    public String getName() {
        return "Login";
    }
}

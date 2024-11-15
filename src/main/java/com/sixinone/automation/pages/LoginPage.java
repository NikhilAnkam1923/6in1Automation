package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.stringtemplate.v4.ST;
import org.testng.Assert;
import java.util.logging.Logger;

public class LoginPage extends BasePage {
    public static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());
    public static final String USER_NAME_INPUT = "//input[@name='email']";
    public static final String PASSWORD_INPUT = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//button[@type='submit']";
    public static final String USER_EMAIL_INPUT = "//input[@id='username']";
    public static final String USER_PASSWORD = "//input[@id='password']";
    private static final String LOGIN_BTN = "//input[@id='kc-login']";
    private static final String LOGIN_TEXT = "//h2[contains(text(),'Login')]";
    private static final String HOME_PAGE = "//a[@class='chakra-link active css-spn4bz']";
    private static final String SPINNER = "//div[contains(class,'spinner')]";
    private static final String LOGOUT_BTN = "//a[@aria-label='Logout']";
    private static final String USER_DASHBOARD = "//span[contains(text(),'Dashboard')]";
    private static final String LOGOUT_BUTTON = "//div[@class='UserDropdown css-1kfu8nn']//button[text()='Logout']";
    private static final String FORGET_PASSWORD_LINK = "//a[contains(@class,'forget-password-link')]";
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
        driverUtil.getWebElement(LOGIN_BTN).click();
    }

    public void loginTo6in1(String userEmail, String password) throws AutomationException {
        enterUserEmail(userEmail);
        enterPassword(password);
        clickLoginButton();
        WebDriverUtil.waitForAWhile(5);

    }

    public void enterUserEmail(String userEmail) throws AutomationException {
        driverUtil.getWebElementAndScroll(USER_EMAIL_INPUT).sendKeys(userEmail);
    }

    public void enterPassword(String password) throws AutomationException {
        driverUtil.getWebElementAndScroll(USER_PASSWORD).sendKeys(password);
    }

    public void clickLoginButton() throws AutomationException {
        driverUtil.getWebElementAndScroll(LOGIN_BTN).click();
    }

    public void verifyLoginPageUIAttributes() throws AutomationException {
        Assert.assertEquals("MEMBER LOGIN", getLogInText().trim());
        Assert.assertEquals("a", verifyForgotPasswordLink());
    }

    public String getLogInText() throws AutomationException {
        return driverUtil.getWebElement(LOGIN_TEXT).getText();
    }

    public String verifyForgotPasswordLink() throws AutomationException {
        return driverUtil.getWebElement(FORGET_PASSWORD_LINK).getTagName();
    }

    public void verifyHomePage() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60,SPINNER);
        WebDriverUtil.waitForElementClickable(By.xpath("//span[contains(text(),'Dashboard')]"));
    }

    public void doLogoutFrom6in1() throws AutomationException {
        driverUtil.getWebElementAndScroll(LOGOUT_BTN).click();
        WebDriverUtil.waitForElementNotVisible(60,SPINNER);
        driverUtil.getWebElementAndScroll(LOGIN_TEXT);
    }

    public void doLogoutFrom6in1IfAlreadyLoggedIn() throws AutomationException {
        if ((driverUtil.getWebElement(LOGOUT_BTN, 5) != null)) {
            driverUtil.getWebElement(LOGIN_BTN).click();
            WebDriverUtil.waitForAWhile();
        }
    }

    public void verifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        String actualMessage = driverUtil.getWebElement(ERROR_MSG, 0, "Unable to locate error message field").getText().trim();
        if (actualMessage.equals(errorMessage)) {
            System.out.println("Error message matches expected value.");
        } else {
            throw new AutomationException("Expected error message: '" + errorMessage + "' but found: '" + actualMessage + "'");
        }
        driverUtil.getWebElementAndScroll(LoginPage.USER_EMAIL_INPUT).clear();
    }

    @Override
    public String getName() {
        return "Login";
    }
}

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
    public static final String USERNAME_INPUT = "//input[@id='username']";
    public static final String PASSWORD_INPUT = "//input[@name='password']";
    private static final String LOGIN_BTN = "//input[@name='login']";
    private static final String LOGIN_TEXT = "//h2[contains(text(),'Login')]";
    private static final String SPINNER = "//div[contains(class,'spinner')]";
    private static final String LOGOUT_BTN = "//a[@aria-label='Logout']";
    private static final String ERROR_MSG = "//span[@id='input-error']";
    private static final String EYE_ICON = "//button[@aria-label=\"Show password\"]";
    private static final String REMEBER_ME = "//input[@id='rememberMe']";
    private static final String FORGOT_PASSWORD = "//a[text()='Forgot Password?']";
    private static final String FORGOT_PASSWORD_LOGO = "//h2[text()='Forgot Password']";

    /**
     * This function is used to login.
     *
     * @param userName
     * @param password
     * @throws AutomationException
     */
    public void doLogin(String userName, String password) throws AutomationException {
        WebElement userNameInput = driverUtil.getWebElement(USERNAME_INPUT);
        if (userNameInput != null) {
            WebElement passwordInput = driverUtil.getWebElement(PASSWORD_INPUT);
            WebElement login = driverUtil.getWebElement(LOGIN_BTN);
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
        enterUsername(userEmail);
        enterPassword(password);
        clickOnLoginButton();
        WebDriverUtil.waitForAWhile(5);
    }

    public void enterUsername(String userEmail) throws AutomationException {
        driverUtil.getWebElementAndScroll(USERNAME_INPUT).sendKeys(userEmail);
    }

    public void enterPassword(String password) throws AutomationException {
        driverUtil.getWebElementAndScroll(PASSWORD_INPUT).sendKeys(password);
    }

    public void verifyLoginPageUIAttributes() throws AutomationException {
        Assert.assertEquals("MEMBER LOGIN", getLogInText().trim());
        Assert.assertEquals("a", verifyForgotPasswordLink());
    }

    public String getLogInText() throws AutomationException {
        return driverUtil.getWebElement(LOGIN_TEXT).getText();
    }

    public String verifyForgotPasswordLink() throws AutomationException {
        return driverUtil.getWebElement(FORGOT_PASSWORD).getTagName();
    }

    public void verifyHomePage() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForElementClickable(By.xpath("//span[contains(text(),'Dashboard')]"));
    }

    public void doLogoutFrom6in1() throws AutomationException {
        driverUtil.getWebElementAndScroll(LOGOUT_BTN).click();
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        driverUtil.getWebElementAndScroll(LOGIN_TEXT);
    }

    public void doLogoutFrom6in1IfAlreadyLoggedIn() throws AutomationException {
        if ((driverUtil.getWebElement(LOGOUT_BTN, 5) != null)) {
            driverUtil.getWebElement(LOGIN_BTN).click();
            WebDriverUtil.waitForAWhile();
//            driverUtil.getWebElement(LOGOUT_BUTTON).click();
        }
    }

    public void clickOnEyeIconVisibility() throws AutomationException {
        driverUtil.getWebElement(EYE_ICON).click();
    }

    public void verifyEyeIconVisibility() throws AutomationException {
        WebElement passwordField = driverUtil.getWebElement(PASSWORD_INPUT);
        String passwordFieldType = passwordField.getAttribute("value");
        System.out.println("the given string - " + passwordFieldType);
        if (passwordFieldType == null || passwordFieldType.isEmpty()) {
            throw new AutomationException("Password field is empty or null");
        }
    }

    public void verifyRememberMeCheckboxAvailability() throws AutomationException {
        WebElement rememberMe = driverUtil.getWebElementAndScroll(REMEBER_ME);
            if(!rememberMe.isDisplayed())
            {
            throw new AutomationException("Remember me checkbox is not displayed.");
        }
    }

//    public void verifyRememberMeCheckboxAvailability() throws AutomationException {
//        if (driverUtil.getWebElementAndScroll(REMEBER_ME).isDisplayed()) {
//            System.out.println("Remember Me Checkbox is available, availability check passed.");
//        } else {
//            throw new AutomationException("Remember me checkbox is not displayed.");
//        }
//    }

    public void verifyForgotPasswordButtonClickability() throws AutomationException {
        driverUtil.getWebElement(FORGOT_PASSWORD).click();
    }

    public void verifyForgotPasswordPageOpen() throws AutomationException {
        driverUtil.getWebElement(FORGOT_PASSWORD_LOGO, 10, "Forgot Password link open successfully");
        //throw new AutomationException("Forgot Password page did not open.");
    }


    public void verifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
//        Assert.assertEquals(errorMessage, driverUtil.getWebElement(ERROR_MSG, 0, "Unable to locate error message field").getText().trim());
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

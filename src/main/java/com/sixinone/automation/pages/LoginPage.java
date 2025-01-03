package com.sixinone.automation.pages;

import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.glue.CommonSteps;
import com.sixinone.automation.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stringtemplate.v4.ST;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginPage extends BasePage {
    public static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());
    public static final String USERNAME_INPUT = "//input[@id='username']";
    public static final String PASSWORD_INPUT = "//input[@name='password']";
    private static final String LOGIN_BTN = "//input[@name='login']";
    private static final String LOGIN_LOGO = "//h2[text()='Login']";
    public static final String SPINNER = "//div[contains(class,'spinner')]";
    private static final String LOGOUT_BTN = "//a[@aria-label='Logout']";
    private static final String ERROR_MSG = "//span[@id='input-error']";
    private static final String EYE_ICON = "//button[@aria-label=\"Show password\"]";
    private static final String REMEBER_ME = "//input[@id='rememberMe']";
    private static final String FORGOT_PASSWORD = "//a[text()='Forgot Password?']";
    private static final String FORGOT_PASSWORD_LOGO = "//h2[text()='Forgot Password']";
    private static final String BACK_TO_LOGIN = "//a[text()='« Back to Login']";
    public static WebDriverUtil driverUtil = new WebDriverUtil();
    private static final String CREATE_LACKNER_STAFF_BTN = "//button[@aria-label='%s']";

    public void clickOnLoginButton() throws AutomationException {
        driverUtil.getWebElement(LOGIN_BTN).click();
    }


    public void loginTo6in1(String userEmail, String password) throws AutomationException {
        enterUsername(userEmail);
        enterPassword(password);
        clickOnLoginButton();
        //driverUtil.waitForLoaderToDisappear();
    }

    public void enterUsername(String userEmail) throws AutomationException {
        WebElement usernameInput = driverUtil.getWebElementAndScroll(USERNAME_INPUT);
        usernameInput.clear();
        usernameInput.sendKeys(userEmail);
    }

    public void enterPassword(String password) throws AutomationException {
        WebElement passwordInput = driverUtil.getWebElementAndScroll(PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public String getLogInText() throws AutomationException {
        return driverUtil.getWebElement(LOGIN_LOGO).getText();
    }

    public void doLogoutFrom6in1IfAlreadyLoggedIn() throws AutomationException {
        if ((driverUtil.getWebElement(LOGOUT_BTN, 5) != null)) {
            driverUtil.getWebElement(LOGIN_BTN).click();
        }
    }


    public void verifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        String actualMessage = driverUtil.getWebElement(ERROR_MSG, 0, "Unable to locate error message field").getText().trim();
        if (actualMessage.equals(errorMessage)) {
            System.out.println("Error message matches expected value.");
        } else {
            throw new AutomationException("Expected error message: '" + errorMessage + "' but found: '" + actualMessage + "'");
        }
    }

    public String verifyForgotPasswordLink() throws AutomationException {
        return driverUtil.getWebElement(FORGOT_PASSWORD).getTagName();
    }

    public void verifyLoginPageUIAttributes() throws AutomationException {
        String loginText = getLogInText().trim();
        if (!"Login".equals(loginText)) {
            throw new AutomationException("Login text does not match. Expected: 'Login', but found: '" + loginText + "'.");
        }
        String forgotPasswordLink = verifyForgotPasswordLink();
        if (!"a".equals(forgotPasswordLink)) {
            throw new AutomationException("Forgot Password link attribute does not match. Expected: 'a', but found: '" + forgotPasswordLink + "'.");
        }
    }

    public void verifyHomePage() throws AutomationException {
        WebDriverUtil.waitForElementNotVisible(60, SPINNER);
        WebDriverUtil.waitForVisibleElement(By.xpath("//span[contains(text(),'Dashboard')]"), 60);
    }

    public void doLogoutFrom6in1() throws AutomationException {
        WebDriverUtil.waitForVisibleElement(By.xpath(LOGOUT_BTN));
        driverUtil.getWebElementAndScroll(LOGOUT_BTN).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebElement loginLogo = driverUtil.getWebElementAndScroll(LOGIN_LOGO);
        if(loginLogo.isDisplayed())
        {
            CommonSteps.logInfo("Successfully logout done");
        }
        else {
            throw new AutomationException("Logout failed");
        }

    }

    public void clickOnEyeIconVisibility() throws AutomationException {
        WebElement eyeIcon = driverUtil.getWebElement(EYE_ICON);
        if (eyeIcon == null) {
            throw new AutomationException("Expected Eye Icon to be visible on the UI, but it is either not present or not locatable.");
        }
        eyeIcon.click();
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
        if (!rememberMe.isDisplayed()) {
            throw new AutomationException("Remember me checkbox is not displayed.");
        }
    }

    public void userVerifyRememberMeCheckboxClickability() throws AutomationException {
        WebElement rememberMe = driverUtil.getWebElementAndScroll(REMEBER_ME);
        rememberMe.click();
        if (!rememberMe.isEnabled()) {
            throw new AutomationException("Remember me checkbox is not clickable");
        }
    }

    public void verifyForgotPasswordButtonClickability() throws AutomationException {
        driverUtil.getWebElement(FORGOT_PASSWORD).click();
    }

    public void verifyForgotPasswordPageOpen() throws AutomationException {
        WebElement forgotPasswordLogo = driverUtil.getWebElementAndScroll(FORGOT_PASSWORD_LOGO);
        if (!forgotPasswordLogo.isDisplayed()) {
            throw new AutomationException("Forgot Password page did not open.");
        }
    }

    public void verifyBackToLoginButtonClickability() throws AutomationException {
        {
            driverUtil.getWebElementAndScroll(LOGIN_LOGO).click();
        }
    }

    public void verifyUserLandedOnLoginPage() throws AutomationException {
        WebElement loginLogo = driverUtil.getWebElementAndScroll(LOGIN_LOGO, 5, "User Landed on the login page after click on the back to login");
        if (!loginLogo.isDisplayed()) {
            throw new AutomationException("After click on back to login button its not landed on login page");
        }
    }

    public boolean isLacknerStaffButtonVisible(String createLacknerStaffButtonLabel) throws AutomationException {
        String locator = String.format(CREATE_LACKNER_STAFF_BTN, createLacknerStaffButtonLabel);
        WebElement buttonElement = driverUtil.getWebElement(locator, 5);
        return buttonElement != null && buttonElement.isDisplayed();
    }

    public void backToLoginPage() throws AutomationException {
        driverUtil.getWebElement(BACK_TO_LOGIN).click();
    }

    @Override
    public String getName() {
        return "Login";
    }

}

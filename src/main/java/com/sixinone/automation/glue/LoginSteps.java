package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.LoginPage;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.CommonUtil;
import com.sixinone.automation.util.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.util.logging.Commons;

import static com.sixinone.automation.pages.BasePage.driverUtil;

public class LoginSteps {
    @Then("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String userName, String password) throws Exception {
        if (userName.startsWith("$"))
            userName = System.getProperty(userName.substring(1));
        if (password.startsWith("$"))
            password = System.getProperty(password.substring(1));
        CommonSteps.logInfo("User login with user: " + userName + " and password: *********");
        PageFactory.loginPage().doLogin(userName, password);
    }

    @When("user click on Login button")
    public void userClickOnLoginButton() throws AutomationException {
        CommonSteps.logInfo("user click on Login button");
        PageFactory.loginPage().clickOnLoginButton();
    }

    @When("^user login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public static void userLoginTo6in1(String userEmail, String password) throws AutomationException {
        PageFactory.loginPage().doLogoutFrom6in1IfAlreadyLoggedIn();
        userEmail = CommonUtil.processString(userEmail);
        password = CommonUtil.processString(password);
        CommonSteps.logInfo("User login with user: " + userEmail + " and password: *********");
        PageFactory.loginPage().loginTo6in1(userEmail, password);

    }

    @Given("user verify login page ui attributes")
    public void userVerifyLoginPageUIAttributes() throws AutomationException {
        CommonSteps.logInfo("User verify login page ui attributes ");
        PageFactory.loginPage().verifyLoginPageUIAttributes();
        CommonSteps.takeScreenshot();
    }

    @Given("user verify home page")
    public void userVerifyHomePage() throws AutomationException {
        CommonSteps.logInfo("User verify home page ");
        PageFactory.loginPage().verifyHomePage();
        CommonSteps.takeScreenshot();
    }

    @Given("user logged out from the application")
    public void userLogoutFromCentrifi() throws AutomationException {
        CommonSteps.logInfo("User logged out from the application");
        PageFactory.loginPage().doLogoutFrom6in1();
    }

    @When("^user verify \"([^\"]*)\"$")
    public void userVerifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        CommonSteps.logInfo("User verify error message");
        PageFactory.loginPage().verifyInvalidCredErrorMessage(errorMessage);
        CommonSteps.takeScreenshot();
        driverUtil.getWebElementAndScroll(LoginPage.USERNAME_INPUT).clear();
    }

    @When("^user enter username as \"([^\"]*)\"$")
    public void userEnterUsername(String email) throws AutomationException {
        PageFactory.loginPage().enterUsername(email);
        PageFactory.loginPage().clickOnLoginButton();
    }

    @When("^user enter password as \"([^\"]*)\"$")
    public void userEnterPassword(String password) throws AutomationException {
        CommonSteps.logInfo("user enter password: *********");
        PageFactory.loginPage().enterPassword(password);
    }

    @Then("user clicks the eye icon to toggle visibility")
    public void userClicksTheEyeIconToToggleVisibility() throws AutomationException {
        CommonSteps.logInfo("user click the eye icon");
        PageFactory.loginPage().clickOnEyeIconVisibility();
    }

    @And("user verify password is visible after click on the eye icon")
    public void userVerifyPasswordIsVisibleAfterClickOnTheEyeIcon() throws AutomationException {
        CommonSteps.logInfo("user verifies password is visible");
        PageFactory.loginPage().verifyEyeIconVisibility();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify Remember me checkbox should be available")
    public void rememberMeCheckboxShouldBeAvailable() throws AutomationException {
        CommonSteps.logInfo("user verify Remember me checkbox should be available");
        PageFactory.loginPage().verifyRememberMeCheckboxAvailability();
        CommonSteps.takeScreenshot();
    }

    @When("user click on forgot password link")
    public void userClickOnForgotPasswordLink() throws AutomationException {
        CommonSteps.logInfo("user click on forgot password link");
        PageFactory.loginPage().verifyForgotPasswordButtonClickability();
    }

    @Then("verify forgot password page is open")
    public void verifyForgotPasswordPageIsOpen() throws AutomationException {
        CommonSteps.logInfo("verify forgot password page is open");
        PageFactory.loginPage().verifyForgotPasswordPageOpen();
        CommonSteps.takeScreenshot();
    }
}

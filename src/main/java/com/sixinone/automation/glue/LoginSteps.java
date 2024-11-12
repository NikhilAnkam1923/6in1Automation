package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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

    @When("^user verify \"([^\"]*)\" for invalid credentials$")
    public void userVerifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        CommonSteps.logInfo("User verify error message for invalid credentials");
        PageFactory.loginPage().verifyInvalidCredErrorMessage(errorMessage);
        CommonSteps.takeScreenshot();
    }
}

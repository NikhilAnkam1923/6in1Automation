package com.sixinone.automation.glue;

import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {

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

}

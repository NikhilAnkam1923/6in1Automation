package com.centrifi.automation.glue;

import com.aspose.pdf.Page;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.LoginPage;
import com.centrifi.automation.pages.PageFactory;
import com.centrifi.automation.util.PropertyReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.centrifi.automation.glue.CommonSteps.driverUtil;
import static com.centrifi.automation.glue.CommonSteps.takeScreenshot;

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
    public static void userLoginToCentrifi(String userEmail, String password) throws AutomationException {
        PageFactory.loginPage().doLogoutFromCentrifiIfAlreadyLoggedIn();
        String env = PropertyReader.getEnv();
        userEmail = System.getProperty(env + "." + userEmail.substring(1, userEmail.length()));
        password = System.getProperty(env + "." + password.substring(1, password.length()));
        CommonSteps.logInfo("User login with user: " + userEmail + " and password: *********");
        PageFactory.loginPage().loginToCentriFi(userEmail, password);
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
        PageFactory.loginPage().doLogoutFromCentrifi();
    }

    @When("^user verify \"([^\"]*)\" for invalid credentials$")
    public void userVerifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        CommonSteps.logInfo("User verify error message for invalid credentials");
        PageFactory.loginPage().verifyInvalidCredErrorMessage(errorMessage);
        CommonSteps.takeScreenshot();
    }
}

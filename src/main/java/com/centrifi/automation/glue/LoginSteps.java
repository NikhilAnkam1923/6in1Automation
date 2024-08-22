package com.centrifi.automation.glue;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.LoginPage;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.centrifi.automation.glue.CommonSteps.driverUtil;
import static com.centrifi.automation.glue.CommonSteps.takeScreenshot;

public class LoginSteps {
    @Then("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String userName, String password) throws Exception {
        if(userName.startsWith("$"))
            userName=System.getProperty(userName.substring(1));
        if(password.startsWith("$"))
            password=System.getProperty(password.substring(1));
        CommonSteps.logInfo("User login with user: "+userName+" and password: *********");
        PageFactory.loginPage().doLogin(userName, password);
    }
}

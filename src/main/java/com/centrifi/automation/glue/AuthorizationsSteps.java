package com.centrifi.automation.glue;


import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.PageFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class AuthorizationsSteps {

    @Then("user click on authorization button")
    public void userClickOnAuthorizationButton() throws AutomationException {
        CommonSteps.logInfo("user click on client button from navigation list");
        PageFactory.authorizationsPage().clickOnAuthorizationButton();
        CommonSteps.takeScreenshot();
    }

    @Then("user click on facebook button")
    public void userClickOnFacebookButton() throws AutomationException {
        CommonSteps.logInfo("user click on facebook button");
        PageFactory.authorizationsPage().clickOnFaceBookButton();
        CommonSteps.takeScreenshot();
    }

    @Then("user select the permissions and click continue with facebook")
    public void userSelectThePermissionsAndClickContinueWithFacebook() throws AutomationException {
        CommonSteps.logInfo("user select the permissions and click continue with facebook");
        PageFactory.authorizationsPage().clickOnContinueWithFacebook();
        CommonSteps.takeScreenshot();
    }

    @Then("user login using {string} and {string} with facebook")
    public void userLoginUsingAndWithFacebook(String userName, String passWord) throws AutomationException {
        CommonSteps.logInfo("user login using {string} and {string} with facebook");
        PageFactory.authorizationsPage().clickOnLoginWithFacebook(userName,passWord);
        CommonSteps.takeScreenshot();
    }

    @Then("user click on Connect or Reconnect button verify the page")
    public void userClickOnConnectOrReconnectButtonVerifyThePage() throws AutomationException {
        CommonSteps.logInfo("user click on Connect or Reconnect button verify the page");
        PageFactory.authorizationsPage().clickOnConnectOrReconnectButton();
        CommonSteps.takeScreenshot();
    }

    @Then("user click on {string} platform ande delete the record {string}")
    public void userClickOnPlatformAndeDeleteTheRecord(String platFormName, String name) throws AutomationException {
        CommonSteps.logInfo("user click on "+platFormName+" platform ande delete the record"+name);
        PageFactory.authorizationsPage().clickOnDeleteAuthorizationRecord(platFormName,name);
        CommonSteps.takeScreenshot();
    }

    @Then("user login using {string} and {string} with google")
    public void userLoginUsingAndWithGoogle(String userName, String password) throws AutomationException {
        CommonSteps.logInfo("user click on Connect or Reconnect button verify the page");
        PageFactory.authorizationsPage().clickOnConnectGoogleButton(userName,password);
        CommonSteps.takeScreenshot();
    }

    @Then("user click all option on Connect or Reconnect button verify the page")
    public void userClickAllOptionOnConnectOrReconnectButtonVerifyThePage() throws AutomationException {
        CommonSteps.logInfo("user click all option on Connect or Reconnect button verify the page");
        PageFactory.authorizationsPage().clickOnllOptionOnConnect();
        CommonSteps.takeScreenshot();
    }

    @Then("user click on google button {string}")
    public void userSelectThePermissionsAndClickContinueWithGoogle(String name) throws AutomationException {
        CommonSteps.logInfo("user click on google button:"+name);
        PageFactory.authorizationsPage().clickOnContinueWithGoogle(name);
        CommonSteps.takeScreenshot();
    }


}

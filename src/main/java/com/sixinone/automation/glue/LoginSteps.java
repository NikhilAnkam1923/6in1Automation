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
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static com.sixinone.automation.pages.BasePage.driverUtil;

public class LoginSteps {

    @When("user click on Login button")
    public void userClickOnLoginButton() throws AutomationException {
        CommonSteps.logInfo("user click on Login button");
        PageFactory.loginPage().clickOnLoginButton();
    }

    @When("user login using credentials")
    public void userLoginUsingCredentials(DataTable dataTable) throws AutomationException {
      //  PageFactory.loginPage().doLogoutFrom6in1IfAlreadyLoggedIn();
        List<Map<Object, Object>> data = dataTable.asMaps(String.class, String.class);
        Map<Object, Object> row = data.get(0);

        String userEmail = row.get("userEmail").toString();
        String password = row.get("password").toString();
        String userType = row.get("userType").toString();
        CommonSteps.logInfo("User login with user: " + userEmail + " and password: ********* as" + userType + " ");
        PageFactory.loginPage().loginTo6in1(userEmail, password);
        CommonSteps.takeScreenshot();
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
        driverUtil.waitForLoaderToDisappear();
        PageFactory.loginPage().verifyHomePage();
        CommonSteps.takeScreenshot();
    }

    @Given("user logged out from the application")
    public void userLogoutFrom6in1() throws AutomationException {
        CommonSteps.logInfo("User logged out from the application");
        PageFactory.loginPage().doLogoutFrom6in1();
        CommonSteps.takeScreenshot();
    }

    @When("^user verify \"([^\"]*)\"$")
    public void userVerifyInvalidCredErrorMessage(String errorMessage) throws AutomationException {
        CommonSteps.logInfo("User verify error message: '" + errorMessage + "'");
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

    @Then("user verify Remember me checkbox available")
    public void rememberMeCheckboxAvailability() throws AutomationException {
        CommonSteps.logInfo("user verify Remember me checkbox available");
        PageFactory.loginPage().verifyRememberMeCheckboxAvailability();
        CommonSteps.takeScreenshot();
    }

    @Then("user verify Remember me checkbox clickable")
    public void rememberMeCheckboxClickability() throws AutomationException {
        CommonSteps.logInfo("user verify Remember me checkbox clickable");
        PageFactory.loginPage().userVerifyRememberMeCheckboxClickability();
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
        PageFactory.loginPage().backToLoginPage();
    }

    @Then("^user verify visibility of the \"([^\"]*)\" button$")
    public void userVerifyVisibilityOfTheCreateLacknerStaffButton(String createLacknerStaffButtonLabel) throws AutomationException {
        CommonSteps.logInfo("user verify visibility of '" + createLacknerStaffButtonLabel + "' button ");
        boolean isButtonVisible = PageFactory.loginPage().isLacknerStaffButtonVisible(createLacknerStaffButtonLabel);
        CommonSteps.takeScreenshot();
        CommonUtil.setTestData("isAdmin", isButtonVisible);
    }

    @And("^verify the user type as \"([^\"]*)\"$")
    public void verifyUserType(String expectedUserType) throws AutomationException {

        boolean isAdmin = (boolean) CommonUtil.getTestData("isAdmin");
        String actualUserType = isAdmin ? "Admin" : "Support Staff";

        if (!actualUserType.equals(expectedUserType)) {
            throw new AutomationException("Expected user type: " + expectedUserType + ", but found: " + actualUserType);
        }

        CommonSteps.logInfo("Verified user type is: '" + actualUserType + "'");
        CommonSteps.takeScreenshot();
    }


    @When("user click on Back to Login link")
    public void userClickOnBackToLoginLink() throws AutomationException {
        CommonSteps.logInfo("user click on Back to Login link");
        PageFactory.loginPage().verifyBackToLoginButtonClickability();
    }

    @Then("verify user landed on login page")
    public void verifyUserLandedOnLoginPage() throws AutomationException {
        CommonSteps.logInfo("verify user landed on login page after click on back to login");
        PageFactory.loginPage().verifyUserLandedOnLoginPage();
        CommonSteps.takeScreenshot();
    }



}

package com.sixinone.automation.glue;

import com.aspose.pdf.Page;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.pages.PageFactory;

import com.sixinone.automation.util.CommonUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EstateCreationSteps {

    @Then("user fills the first name,last name and SSN details")
    public void userFillsTheFirstNameLastNameAndSSNDetails() throws AutomationException {
        PageFactory.estateCreationPage().enterFirstAndLastNameAndSSN();

    }


}

package com.sixinone.automation.pages;

import com.sixinone.automation.exception.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class ProbateFormsRW03Page extends BasePage{

    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";

    public void navigateToEstateContactsTab() throws AutomationException {
        WebElement tempUser = driverUtil.getWebElement("//a[text()='Baby John']");
        tempUser.click();
        waitForInvisibleElement(By.xpath(SPINNER));
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
    }





    @Override
    String getName() {
        return "";
    }
}

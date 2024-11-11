package com.centrifi.automation.pages;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import com.centrifi.automation.util.ReportLogger;
import com.centrifi.automation.util.WebDriverUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import static com.centrifi.automation.util.WebDriverUtil.waitForInvisibleElement;

public abstract class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());
    protected String logPropertyFileName = null;
    public static ReportLogger REPORT_LOGGER = null;
    private static Boolean SETUP_COMPLETED = false;
    public static WebDriverUtil driverUtil = new WebDriverUtil();
    public static final int WAIT_1_SECOND = 1;
    public static final int WAIT_3_SECOND = 3;
    public static final int WAIT_5_SECOND = 5;
    public static final int WAIT_10_SECOND = 10;
    public static final int WAIT_30_SECOND = 30;
    public static final int WAIT_60_SECOND = 60;
    public static final int WAIT_120_SECOND = 120;
    public static final String METHOD_NAME = "NAME";
    public static final String EMPTY_STRING = "";
    public static final String SEARCH_POPUP_CLOSE = "//*[contains(@class,'SearchPatient-__close')]";
    public static final String ERROR_POPUP = "//*[contains(@class,'popup') and .//*[contains(text(),'error')]]";
    public static final String ERROR_POPUP_BUTTON_OK = "//button[text()='OK']";
    public static final String CONFIRMATION_DIALOG = "//*[@role='dialog']";
    public static final String CONFIRMATION_DIALOG_DELETE_BUTTON = "//*[@role='dialog']//button[text()='Delete']";
    public static final String ANY_OTHER_POPUP = "//*[contains(@class,'popup')]";
    public static final String CLOSE_POPUP_BUTTON = "//*[contains(@class,'close') and not(contains(@class,'closeButton'))]";
    public static final String LOADING = "//*[contains(@class,'src-components-Loading-__txt') and text()='LOADING']";
    public static final String LOADING_DATA = "//*[contains(@class,'src-components-Loading-__txt')]";
    public static final String LOADING_REPORT = "//*[contains(@class,'src-components-Loading-__txt') and text()='Loading...']";
    public static final String INFO_POPUP_BUTTON_OK = "//button[text()='OK']";
    public static final String INFO_POPUP_BUTTON_YES = "//button[text()='Yes']";
    public static final String CLOSE_PDF_VIEWER_BUTTON  ="//*[contains(@class,'ReportPopUp-__button')]";
    public static final String DELETE_CONFIRMATION_POPUP_DELETE_BUTTON = "//button[text()='%s']";
    public static final String VERIFY_POPUP = "//*[(contains(@class,'__popUp') or contains(@class,'__popup') or contains(@class,'__popupLoaded')) and .//*[contains(text(),'%s')]]";
    public static final String VERIFY_SEARCH_PRACTITIONER_POPUP = "//*[contains(@class,'SelectPractitioner') and .//*[text()='%s']]";
    public static final String ERROR_POPUP_OK_BUTTON = "//button[text()='OK' and contains(@style,'inline-block;')]";
    public static final String PATIENT_RELOAD_POPUP = "//*[@role='dialog' and .//*[contains(text(),'This patient has been updated')]]//button[text()='OK']";
    public static final String ANY_POPUP_WITH_OK_BUTTON = "//button[@style='display: inline-block;' and text()='OK']";
    private static final String MENU_ITEM = "//*[contains(@class,'Sidebar')]//*[contains(text(),'%s')]";
    private static final String SPINNER = "//div[contains(@class,'chakra-spinner')]";


    public boolean checkErrorPopup() throws AutomationException {
        WebElement element = driverUtil.getWebElement(ERROR_POPUP);
        if(element!=null) {
            return true;
        }
        return false;
    }

    public boolean verifyAnyPopup() {
        boolean status = false;
        try {
            WebElement element = driverUtil.getWebElement(ERROR_POPUP, WebDriverUtil.NO_WAIT);
            if(element!=null) {
                status = true;
            }
            element = driverUtil.getWebElement(SEARCH_POPUP_CLOSE, WebDriverUtil.NO_WAIT);
            if(element!=null) {
                status = true;
            }
            element = driverUtil.getWebElement(ANY_OTHER_POPUP, WebDriverUtil.NO_WAIT);
            if(element!=null) {
                status = true;
            }
        } catch(Exception ex) {
            LOGGER.info(ex.getMessage());
        }
        return status;
    }

    public void checkInfoPopupAndClose() {
        try {
            WebElement element = driverUtil.getWebElement(PATIENT_RELOAD_POPUP, WebDriverUtil.NO_WAIT);
            if (element!=null)
                element.click();
        } catch(Exception ex) {
            //Do Nothing..
        }
    }

    public void checkAdminPortalErrorPopup() {
        try {
            WebElement btn = driverUtil.getWebElement("//span["+ driverUtil.ignoreCase("Cancel")+"]/parent::button[not(contains(@class,'Mui-disabled'))]");
            if (btn!= null)
                btn.click();
        } catch(Exception ex) {
            //Do Nothing..
        }
    }


    public boolean checkAndCloseErrorPopup() throws AutomationException {
        try {
            WebElement element = driverUtil.getWebElement(ERROR_POPUP);
            if(element!=null) {
                element = driverUtil.getWebElement(ERROR_POPUP_BUTTON_OK);
                if(element!=null)
                    element.click();
                return true;
            }
        } catch(Exception ex) {
            //Do Nothing..
        }

        return false;
    }

    public void checkAndCloseSearchPopup() throws AutomationException {
        WebElement popup = driverUtil.getWebElement(SEARCH_POPUP_CLOSE);
        if(popup!=null) {
            popup.click();
        }
    }

    public boolean verifyConfirmationDialog() {
        WebElement dialog = driverUtil.findElement(CONFIRMATION_DIALOG);
        if(dialog==null)
            return false;
        return true;
    }

    public void clickOnConfirmationDialogDeleteButton() throws AutomationException {
        if(verifyConfirmationDialog()) {
            WebElement dialogDelete = driverUtil.getWebElement(CONFIRMATION_DIALOG_DELETE_BUTTON);
            if (dialogDelete == null)
                throw new AutomationException("Unable to locate delete button on confirmation dialog popup!");
            dialogDelete.click();
        } else {
            throw new AutomationException("Unable to locate delete confirmation dialog popup!");
        }
    }



    public static void waitForLoadingPage() {
        waitForInvisibleElement(By.xpath(LOADING), WebDriverUtil.MAX_PAGE_LOADING_WAIT);
        waitForInvisibleElement(By.xpath(LOADING_DATA), WebDriverUtil.MAX_PAGE_LOADING_WAIT);
        waitForInvisibleElement(By.xpath(LOADING_REPORT), WebDriverUtil.MAX_PAGE_LOADING_WAIT);
    }

   public void waitForLoadingReport() {
        waitForInvisibleElement(By.xpath(LOADING), WebDriverUtil.MAX_PAGE_LOADING_WAIT);
        waitForInvisibleElement(By.xpath(LOADING_REPORT), WebDriverUtil.MAX_PAGE_LOADING_WAIT);
    }

    public static Map<String, String> convertDataTableIntoMap(DataTable dataTable) {
        Map<String, String> dataMap = new HashMap<>();
        List<List<String>> rows = dataTable.asLists(String.class);
        for(int i=1; i<rows.size(); i++) {
            List<String> row = rows.get(i);
            for(int j=0; j<row.size();j++) {
                dataMap.put(rows.get(0).get(j), row.get(j));
            }
        }
        return dataMap;
    }

    public void clickOnSideBarMenuItem(String item)throws AutomationException{
        driverUtil.getWebElementAndScroll(String.format(MENU_ITEM,item), WAIT_30_SECOND, "Unable to locate Menu!").click();
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    abstract String getName();


}

package com.sixinone.automation.glue;

import com.sixinone.automation.MainApplication;
import com.sixinone.automation.constants.Constants;
import com.sixinone.automation.drivers.DriverFactory;
import com.sixinone.automation.exception.AutomationException;
import com.sixinone.automation.models.TestScenario;
import com.sixinone.automation.pages.PageFactory;
import com.sixinone.automation.reports.ExtentManager;
import com.sixinone.automation.reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.sixinone.automation.util.*;
import cucumber.api.*;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.ScenarioImpl;
import gherkin.events.PickleEvent;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.testng.SkipException;
import runner.BaseRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

import static com.sixinone.automation.util.WebDriverUtil.waitForInvisibleElement;
import static com.sixinone.automation.util.WebDriverUtil.waitForVisibleElement;

public class CommonSteps {

    public static ReportLogger reportLogger = new ReportLogger();
    private static final Logger LOGGER = Logger.getLogger("Centrifi Test Automation Logger-");
    protected String logPropertyFileName = null;
    public static ReportLogger REPORT_LOGGER = new ReportLogger();
    private static Boolean SETUP_COMPLETED = false;
    public static WebDriverUtil driverUtil;
    public static final int WAIT_10_SECOND = 10;
    public static final int WAIT_30_SECOND = 30;
    public static final int WAIT_60_SECOND = 60;
    public static final int WAIT_120_SECOND = 120;
    public static final String METHOD_NAME = "NAME";
    public static final String TAB_XPATH = "//div[@class='nav-item']//a//span[contains(text(),'%s')]";
    public static final String BTN_XPATH = "//button[contains(text(),'%s')]";
    public static final String NAME_FILTER_INPUT = "//th[@aria-colindex='1'] //input[@aria-label='Filter']";
    public static final String SPINNER = "//div[contains(@class,'spinner')]";
    private static final String TEMP_ESTATE = "//a[text()='%s']";
    public static final String PROBATE_FORMS_TAB = "//span[text()='Probate Forms']";
    private static final String RW_FORM_XPATH = "//a//p[text()='%s']";
    private static final String SHOW_AKA_CHECkBOX = "//label[text()='Show aka']/preceding-sibling::input";
    public static final String ESTATE_CONTACTS_TAB = "//span[text()='Estate Contacts']";
    private static final String CAPACITY_REPRESENTATIVE = "//input[@name='capacity' and @value='1']";
    private static final String CAPACITY_COUNSEL = "//input[@name='capacity' and @value='2']";
    private static final String PERSON_NAME_FIELD = "//p[text()='Name of Person']/preceding-sibling::div//p//input";
    private static final String PERSON_CLEAR_SELECTION_BTN = "//div[@id='nameOfPersonCheckboxSection']/following-sibling::div//button[text()='Clear Selection']";
    private static final String CORPORATE_FIDUCIARY_CLEAR_SELECTION_BTN = "//p[contains(text(),'Corporate Fiduciary (if applicable)')]//button[text()='Clear Selection']";
    private static final String PAGE_NUMBER_DYNAMIC_XPATH = "//a[@role='tab' and text()='%s']";
    private static final String NAME_OF_COUNSEL_FIELD = "//p//span[text()='Name of Counsel:']/following-sibling::span//input";
    private static final String PETITIONER_NAME_FIELD = "//td[@class='tr5 td9']//input";
    private static final String DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllBenyOnAttachment']";
    private static final String DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN = "//input[@name='isDisplayAllIncomeOnAttachment']";
    public static ThreadLocal<Scenario> CURRENT_SCENARIO = new ThreadLocal<>();
    public static ThreadLocal<String> CURRENT_SCENARIO_MESSAGE = new ThreadLocal<>();
    public static ThreadLocal<String> CURRENT_STEP_MESSAGE = new ThreadLocal<>();
    public static ThreadLocal<Integer> currentStepIndex = new ThreadLocal<>();
    public static ThreadLocal<String> currentStep = new ThreadLocal<>();
    public static ThreadLocal<String> stepScreenshot = new ThreadLocal<>();
    public static ThreadLocal<List<String>> stepScreenshots = new ThreadLocal<>();
    public static ThreadLocal<List<String>> currentScenarioSteps = new ThreadLocal<>();
    public static ThreadLocal<Boolean> SETUP_FAILED = new ThreadLocal<>();
    /**
     * Below field related to TesTrail functionality.
     */
    public static TestRailUtility testRailUtility;
    public static Map<String, Integer> testCaseMapping = new HashMap<>();
    public static Map<String, Integer> sectionMapping = new HashMap<>();
    public static Map<Integer, TestRailTestStatus> testResultMapping = new HashMap<>();
    public static Integer projectID;
    public static Integer suiteID;
    public static Integer testRunID;

    public static void logError(String error) {
        reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + error);
        CURRENT_SCENARIO_MESSAGE.set(error);
    }

    public static void logError(String error, List<String> screenshot) {
        stepScreenshots.set(screenshot);
        reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + error);
        CURRENT_SCENARIO_MESSAGE.set(error);
    }

//    public static void logInfo(String... data) {
//        currentStep.set(data[0]);
//    }

    public static void logInfo(String... data) {
        if (data.length > 0) {
            currentStep.set(data[0]);
            System.out.println("[INFO] " + data[0]); // Print the log to the console
        }
    }

    public static void cleanUP() {
        File logs = new File("./logs");
        if (logs.exists())
            PackageUtil.recursiveDelete(logs);
        File extentReports = new File("./extent-reports");
        if (extentReports.exists())
            PackageUtil.recursiveDelete(extentReports);
    }

    public static void setup() {
        try {
            MainApplication.init();
            String logPropertyFileName = Constants.PROPERTY_FILE_PATH + "config/log4j.properties";
            PropertyConfigurator.configure(logPropertyFileName);
            PropertyReader.loadProperties(PropertyReader.CONFIG_PROPERTIES_FILE);
            PropertyReader.loadProperties(PropertyReader.ENV_PROPERTIES_FILE);
            /**
             * If TestRail property marked as true.
             * we need to sync our test scenario with TestRail Project.
             */
            if ("true".equalsIgnoreCase(System.getProperty("TestRail"))) {
                projectID = Integer.parseInt(System.getProperty("TestRail_projectid"));
                suiteID = Integer.parseInt(System.getProperty("TestRail_suitid"));
                testRunID = Integer.parseInt(System.getProperty("TestRail_runid"));
                testRailUtility = TestRailUtility.getInstance(System.getProperty("TestRail_url"), System.getProperty("TestRail_username"), System.getProperty("TestRail_password"));
                testCaseMapping.putAll(testRailUtility.getTestCaseIDsUnderSuite(projectID, suiteID));
                sectionMapping.putAll(testRailUtility.getSectionIDsUnderSuite(projectID, suiteID));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Then("^user go to application \"([^\"]*)\"$")
    public void launch(String url) throws InterruptedException {
        if (url.startsWith("$")) {
            String env = PropertyReader.getEnv();
            url = System.getProperty(env + "." + url.substring(1, url.length()));
        }
        logInfo("User go to application " + url);
        launchApplication(url);
    }


    @Given("^User launched \"([^\"]*)\"$")
    public void launchBrowser(String browser) throws AutomationException, IOException, ParseException {
        logInfo("User launched " + browser);
        if (DriverFactory.drivers.get() == null) {
            WebDriver driver = DriverFactory.getInstance().initDriver(browser);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.manage().window().maximize();
            PageFactory.init();
            driverUtil = new WebDriverUtil();
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) throws AutomationException {
        REPORT_LOGGER.log(ExtentTestManager.featureFileName.get() + " => " + scenario.getName());
        REPORT_LOGGER.log("----------------------- TEST STARTED -----------------------");
        currentStepIndex.set(0);
        CURRENT_SCENARIO.set(scenario);
        CURRENT_SCENARIO_MESSAGE.set(null);
        CURRENT_STEP_MESSAGE.set(null);
        currentScenarioSteps.set(new ArrayList<String>());
        String featureName = ExtentTestManager.featureFileName.get();
        String scenarioName = scenario.getName();
        Collection<String> tags = scenario.getSourceTagNames();
        Map<String, TestScenario> mappings = BaseRunner.MAPPING_SHEET_NAME;
        if (!tags.contains("@Setup") && PropertyReader.groupingBy().equalsIgnoreCase(Constants.GROUPING_BY_EXCEL_MAPPING_DATA)) {
            String key = ExtentTestManager.featureFileName.get() + "_" + scenarioName;
            TestScenario test = mappings.get(ExtentTestManager.featureFileName.get() + "_" + scenarioName);
            if (test == null || test.isDisable(PropertyReader.suiteType())) {
                throw new PendingException("As per the mapping in TestData.xlsx, we are skipping this scenario!");
            }
        } else if (tags.contains("@Setup") && PropertyReader.groupingBy().equalsIgnoreCase(Constants.GROUPING_BY_EXCEL_MAPPING_DATA)) {
            if (!TestDataExcelUtil.isFeatureIncluded(mappings, PropertyReader.suiteType(), featureName)) {
                throw new PendingException("As per the mapping in TestData.xlsx, we are skipping this scenario!");
            }
        }
        ExtentTest test = ExtentTestManager.startTest(scenario.getName(), scenario.getName());
        ExtentTestManager.assignCategory(test);
        if (tags.contains("@Setup") && scenarioName.toLowerCase().contains("launch browser")) {
            SETUP_FAILED.set(Boolean.FALSE);
        } else if (!tags.contains("@Setup") && SETUP_FAILED.get() != null && SETUP_FAILED.get()) {
            throw new SkipException("Skipping this scenario as setup scenario got failed!");
        } else if (tags.contains("@Setup") && SETUP_FAILED.get() != null && SETUP_FAILED.get() && scenarioName.toLowerCase().contains("close browser")) {
            throw new SkipException("Skipping this scenario as setup scenario got failed!");
        }
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        int index = currentStepIndex.get();
        PickleEvent pickleEvent = BaseRunner.CURRENT_SCENARIO_EXECUTION.get();
        if (pickleEvent != null) {
            String stepName = pickleEvent.pickle.getSteps().get(index).getText();
            currentScenarioSteps.get().add(stepName);
            currentStep.set(stepName);
        }
        stepScreenshot.set(null);
        CURRENT_STEP_MESSAGE.set(null);
        currentStepIndex.set(index + 1);


    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        try {
            String stepName = currentStep.get();
            if (stepName == null || stepName.toLowerCase().contains("skipped"))
                return;
            reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + currentStep.get());
            ReportUtil.writeReportLog(true, stepName, CURRENT_STEP_MESSAGE.get() != null ? CURRENT_STEP_MESSAGE.get() : stepName, stepScreenshot.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        String screenshotFilePath = null;
        try {
            String scenarioName = scenario.getName();
            Collection<String> tags = scenario.getSourceTagNames();
            if (tags.contains("@Setup") && SETUP_FAILED.get() != null && SETUP_FAILED.get() && scenarioName.toLowerCase().contains("close browser")) {
                if (DriverFactory.drivers.get() != null)
                    screenshotFilePath = takeScreenshot(true);
                currentStepIndex.set(0);
                reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + scenario.getName());
                reportLogger.log("---------------------- TEST COMPLETED ----------------------");
                if (DriverFactory.drivers.get() != null)
                    DriverFactory.drivers.get().quit();
                DriverFactory.clearDriverSession();
            } else if (tags.contains("@Setup") && scenario.isFailed() && !scenarioName.toLowerCase().contains("close browser")) {
                SETUP_FAILED.set(Boolean.TRUE);
                if (DriverFactory.drivers.get() != null)
                    takeScreenshot(true);
                currentStepIndex.set(0);
                reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + scenario.getName());
                reportLogger.log("---------------------- TEST COMPLETED ----------------------");
            } else if (!tags.contains("@Setup") && SETUP_FAILED.get() != null && SETUP_FAILED.get()) {
                reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + scenario.getName() + " Execution has been skipped!");
                ReportUtil.writeReportSkipLog(CURRENT_SCENARIO.get().getName(), scenario.getName() + " Execution has been skipped as login failed!");
                reportLogger.log("---------------------- TEST COMPLETED ----------------------");
            } else {
                if (scenario.isFailed())
                    takeScreenshot(true);
                currentStepIndex.set(0);
                if (!scenario.getStatus().equals(cucumber.api.Result.Type.PENDING)) {
                    reportLogger.log(ExtentTestManager.featureFileName.get() + " => " + scenario.getName());
                    reportLogger.log("---------------------- TEST COMPLETED ----------------------");
                }
            }

            /**
             * If TestRail property marked as true.
             * Check the test scenario mapping for the scenario in TestRail
             * if scenario is not present in TestRail then we need to create test case for this scenario.
             * update test scenario result.
             */
            if (!tags.contains("@Setup") && "true".equalsIgnoreCase(System.getProperty("TestRail"))) {
                if (testCaseMapping.get(scenario.getName()) == null) {
                    //Check test scenario in test trail if not present create one.
                    String uri = scenario.getUri();
                    String directoryPath = uri.substring(0, uri.lastIndexOf("/"));
                    String directoryName = directoryPath.substring(directoryPath.lastIndexOf("/") + 1);
                    Integer testCaseID = testRailUtility.createNewTestCase(testRailUtility.getSectionIDWithSectionName(sectionMapping, projectID, suiteID, directoryName), scenario.getName(), currentScenarioSteps.get());
                    testCaseMapping.put(scenario.getName(), testCaseID);
                }
                //update test result.
                Result.Type executionStatus = scenario.getStatus();
                Integer testcaseStatus = executionStatus.equals(Result.Type.PASSED) ? 1 : 5;
                TestRailTestStatus status = new TestRailTestStatus();
                status.setTestCaseStatus(testcaseStatus);
                if (screenshotFilePath != null)
                    status.setFilePath(screenshotFilePath);
                status.setDescription(getScenarioMessage(scenario));
                testResultMapping.put(testCaseMapping.get(scenario.getName()), status);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CURRENT_SCENARIO_MESSAGE.set(null);
            CURRENT_STEP_MESSAGE.set(null);
        }
    }

    private static void logErrorInReport(Scenario scenario) {
        Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            for (Result result : results) {
                if (result.getError() != null)
                    ReportUtil.writeReportLog(false, currentStep.get(), CURRENT_SCENARIO_MESSAGE.get() != null ? (CURRENT_SCENARIO_MESSAGE.get() + ", ") : "" + result.getError(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ReportUtil.writeReportLog(false, currentStep.get(), e.getMessage() + ", Error while logging error: " + Arrays.toString(e.getStackTrace()), true);
        }
    }

    private static String getScenarioMessage(Scenario scenario) {
        Field field = FieldUtils.getField((scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            for (Result result : results) {
                if (result.getError() != null)
                    return result.getError().getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage() + ", Error while logging error: " + Arrays.toString(e.getStackTrace());
        }
        return "Passed";
    }

    private static void logErrorInReport(Scenario scenario, List<String> screenshots) {
        String errorMessage = CURRENT_STEP_MESSAGE.get();
        if (errorMessage == null)
            errorMessage = CURRENT_SCENARIO_MESSAGE.get();
        Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            for (Result result : results) {
                if (result.getError() != null)
                    ReportUtil.writeReportLog(false, currentStep.get(), errorMessage != null ? errorMessage + ", " : "" + result.getError(), screenshots);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ReportUtil.writeReportLog(false, currentStep.get(), e.getMessage() + ", Error while logging error: " + Arrays.toString(e.getStackTrace()), screenshots);
        }
    }

    public static void takeScreenshot() {
        takeScreenshot(false);
    }

    public static String takeScreenshot(boolean isFailedScenario) {
        String stepMessage = CURRENT_STEP_MESSAGE.get();
        String screenshotFileName = ReportUtil.takeScreenshot();
        stepScreenshot.set(screenshotFileName);
        if (isFailedScenario) {
            if (stepMessage != null)
                ReportUtil.writeReportLog(false, currentStep.get(), stepMessage, stepScreenshot.get());
            else if (stepScreenshots.get() != null && !stepScreenshots.get().isEmpty())
                logErrorInReport(CURRENT_SCENARIO.get(), stepScreenshots.get());
            else
                logErrorInReport(CURRENT_SCENARIO.get());
            stepScreenshot.set(null);
            stepScreenshots.set(null);
        }
        return screenshotFileName;
    }

    @Then("^User close browser$")
    public void closeBrowser() throws AutomationException {
        logInfo("User close browser");
        try {
            if (DriverFactory.drivers.get() != null)
                DriverFactory.drivers.get().quit();
            DriverFactory.clearDriverSession();
            String featureName = ExtentTestManager.featureFileName.get();
            if (featureName == null)
                ExtentManager.getExtentReports().flush();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void launchApplication(String url) {
        DriverFactory.drivers.get().get(url);
    }

    public static WebDriver getDriver() {
        return DriverFactory.drivers.get();
    }

    public static void updateResultsToTheTestRail() {
        if ("true".equalsIgnoreCase(System.getProperty("TestRail")))
            testRailUtility.updateTestResultsToTestRun(projectID, testRunID, suiteID, "Automated Test Run", testResultMapping);
    }

    private void scrollToElementAndClick(String elementLocator) throws AutomationException {
        WebElement element = driverUtil.getWebElement(elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();

        element.click();
    }

    @When("^user click on tab: \"([^\"]*)\"$")
    public void userClickOnTab(String tabText) throws AutomationException {
        WebElement tab = driverUtil.getWebElementAndScroll(String.format(TAB_XPATH, tabText));
        if (tab == null)
            throw new AutomationException(String.format("Unable to find %s text on page or it might taking too long time to load!", tabText));
        driverUtil.waitForElementClickable(By.xpath(String.format(TAB_XPATH, tabText)));
        try {
            driverUtil.clickUsingJavaScript(String.format(TAB_XPATH, tabText));
        } catch (Exception e) {
            driverUtil.waitForAWhile(5);
            tab.click();
        }
    }

    @Then("^user click on \"([^\"]*)\" Button$")
    public void userClickOnButton(String btnext) throws AutomationException {

        WebElement btn = driverUtil.getWebElementAndScroll(String.format(BTN_XPATH, btnext));
        if (btn == null)
            throw new AutomationException(String.format("Unable to find %s text on page or it might taking too long time to load!", btnext));
        driverUtil.waitForElementClickable(By.xpath(String.format(BTN_XPATH, btnext)));
        try {
            driverUtil.clickUsingJavaScript(String.format(BTN_XPATH, btnext));
        } catch (Exception e) {
            driverUtil.waitForAWhile(5);
            btn.click();
        }
    }

    @When("^user opens \"([^\"]*)\" Estate$")
    public void userOpensEstate(String estateName) throws AutomationException {
        CommonSteps.logInfo("user opens " + estateName + " Estate");
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        driverUtil.getWebElement(NAME_FILTER_INPUT).click();
        WebElement fieldElement = driverUtil.getWebElement(NAME_FILTER_INPUT);
        fieldElement.sendKeys(Keys.CONTROL + "a");
        fieldElement.sendKeys(Keys.BACK_SPACE);
        driverUtil.getWebElement(NAME_FILTER_INPUT).sendKeys(estateName);
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(TEMP_ESTATE, estateName)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    @And("^user saves entered Estate information for \"([^\"]*)\" form$")
    public void userSavesEstateInformation(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user saves Estate Information for " + formName + " form");
        switch (formName) {
            case "REV346":
                PageFactory.probateFormsRW01Page().userSavesEstateInfo();
                break;
            case "RW02":
                PageFactory.probateFormsRW02Page().userSavesEstateInfo();
                break;
            case "RW03":
                PageFactory.probateFormsRW03Page().userSavesEstateInfo();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().userSavesEstateInfo();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().userSavesEstateInfo();
                break;
            case "RW06":
                PageFactory.probateFormsRW06Page().userSavesEstateInfo();
                break;
            case "RWxx":
                PageFactory.probateFormsRWxxPage().userSavesEstateInfo();
                break;
            case "RW07":
                PageFactory.probateFormsRW07Page().userSavesEstateInfo();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().userSavesEstateInfo();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().userSavesEstateInfo();
                break;
            case "OC01":
                PageFactory.probateFormsOC01Page().userSavesEstateInfo();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userSavesEstateInfo();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("user navigates to the probate forms tab")
    public void userNavigatesToTheProbateFormsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to the probate forms tab");
        waitForVisibleElement(By.xpath(PROBATE_FORMS_TAB));
        driverUtil.getWebElement(PROBATE_FORMS_TAB).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    @And("^user click on the \"([^\"]*)\" form$")
    public void userClickOnTheRW(String formToSelect) throws AutomationException {
        CommonSteps.logInfo("user click on the " + formToSelect + " form");
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
        driverUtil.getWebElement(String.format(RW_FORM_XPATH, formToSelect)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    @Then("user selects the aka checkbox")
    public void userSelectsTheAkaCheckbox() throws AutomationException {
        CommonSteps.logInfo("user selects the aka checkbox");
        DriverFactory.drivers.get().findElement(By.xpath(SHOW_AKA_CHECkBOX)).click();
    }

    @When("^user resets the \"([^\"]*)\" form$")
    public void userResetsTheRWForm(String formName) throws AutomationException {
        CommonSteps.logInfo("user resets the " + formName + " form");
        switch (formName) {
            case "REV346":
                PageFactory.probateFormsRW01Page().userResetsTheRWForm();
                break;
            case "RW02":
                PageFactory.probateFormsRW02Page().userResetsTheRWForm();
                break;
            case "RW03":
                PageFactory.probateFormsRW03Page().userResetsTheRWForm();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().userResetTheRWForm();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().userResetsTheRWForm();
                break;
            case "RW06":
                PageFactory.probateFormsRW06Page().userResetsTheRWForm();
                break;
            case "RWxx":
                PageFactory.probateFormsRWxxPage().userResetsTheRWForm();
                break;
            case "RW07":
                PageFactory.probateFormsRW07Page().userResetsTheRWForm();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().userResetsTheRWForm();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().userResetsTheRWForm();
                break;
            case "OC01":
                PageFactory.probateFormsOC01Page().userResetsTheRWForm();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userResetsTheRWForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies the auto-populated fields of \"([^\"]*)\" form are not editable$")
    public void userVerifiesTheAutoPopulatedFieldsAreNotEditable(String formName) throws Exception {
        CommonSteps.logInfo("Verified that the auto-populated fields of " + formName + " are not editable");
        switch (formName) {
            case "RW02":
                PageFactory.probateFormsRW02Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            case "RW06":
                PageFactory.probateFormsRW06Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            case "RW07":
                PageFactory.probateFormsRW07Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifyAutoPopulatedFieldsAreNotEditable();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies witnesses name, address and signature fields of \"([^\"]*)\" form are editable and in yellow background$")
    public void userVerifiesWitnessesNameAddressAndSignatureFieldsAreEditableAndInYellowBackground(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that witnesses name, address and signature fields of " + formName + " form are editable and in yellow background");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyFieldsAreEditableAndYellowBackground();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().verifyFieldsAreEditableAndYellowInBackground();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyFieldsAreEditableAndYellowBackground();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies names updated in signature fields of \"([^\"]*)\" form are reflected in the witness fields$")
    public void userVerifiesNamesUpdatedInSignatureFieldsAreReflectedInTheWitnessFields(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that names updated in signature fields of " + formName + " form are reflected in the witness fields");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyNamesUpdatedInSignatureFieldsAreReflectedInTheWitnessFields();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().verifyNamesUpdatedInSignatureFieldsAreReflectedInWitnessFields();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyNamesUpdatedInSignatureFieldsAreReflectedInTheWitnessFields();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies both the address, city, zip fields of \"([^\"]*)\" form accept correct text$")
    public void userVerifiesBothTheAddressCityZipFieldsAcceptCorrectText(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that both the address, city, zip fields of " + formName + " form accept correct text");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyTheAddressCityZipFieldsAcceptCorrectText();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().verifyTheAddressCityZipFieldsAcceptCorrectText();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyTheAddressCityZipFieldsAcceptCorrectText();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the input fields in the \"([^\"]*)\" form are auto saved$")
    public void userVerifiesAllTheInputFieldsInTheFormAreAutoSaved(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that all the input fields in the " + formName + " form are auto saved");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyAllTheInputFieldsInTheFormAreAutoSaved();
                break;
            case "RW04":
                PageFactory.probateFormsRW04Page().verifyAllTheInputFieldsInTheFormAreAutoSaved();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyAllTheInputFieldsInTheFormAreAutoSaved();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @And("^user verifies the county, estate and aka names are auto-populated on the \"([^\"]*)\" form$")
    public void userVerifiesTheCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm(String formName) throws AutomationException {
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
                break;
            case "RW06":
                PageFactory.probateFormsRW06Page().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
                break;
            case "RWxx":
                PageFactory.probateFormsRWxxPage().verifyCountyEstateAndAkaNamesAreAutoPopulatedOnTheForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.logInfo("Verified that the county, estate and aka names are auto-populated on the " + formName + " form");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies witness's name is not auto populated and the fields of \"([^\"]*)\" form are empty$")
    public void userVerifiesWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty(String formName) throws Exception {
        CommonSteps.logInfo("Verified that witness's name is not auto populated and the fields are empty");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyWitnessesSNameIsNotAutoPopulatedAndTheFieldsAreEmpty();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies witness fields of \"([^\"]*)\" form accept names and same names are reflected in signature fields$")
    public void userVerifiesWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that witness fields accept names and same names are reflected in signature fields");
        switch (formName) {
            case "RW03":
                PageFactory.probateFormsRW03Page().verifyWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields();
                break;
            case "RW05":
                PageFactory.probateFormsRW05Page().verifyWitnessFieldsAcceptNamesAndSameNamesAreReflectedInSignatureFields();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies the county, estate, file number and aka names are auto-populated on the \"([^\"]*)\" form$")
    public void userVerifiesTheCountyEstateFileNumberAndAkaNamesAreAutoPopulatedOnTheForm(String formName) throws AutomationException {
        switch (formName) {
            case "RW07":
                PageFactory.probateFormsRW07Page().verifyCountyEstateFileNumberAkaNamesAreAutoPopulatedOnTheForm();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifyCountyEstateFileNumberAkaNamesAreAutoPopulatedOnTheForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.logInfo("Verified that the county, estate, file number and aka names are auto-populated on the " + formName + " form");
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies any one of the corporate fiduciary contacts can be selected for \"([^\"]*)\" form$")
    public void userVerifiesAnyOneOfTheFiduciaryContactsCanBeSelected(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that any one of the corporate fiduciary contacts can be selected for " + formName + " form");
        switch (formName) {
            case "RW07":
                PageFactory.probateFormsRW07Page().verifyAnyOneOfTheFiduciaryContactsCanBeSelected();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifyAnyOneOfTheFiduciaryContactsCanBeSelected();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().verifyAnyOneOfTheFiduciaryContactsCanBeSelected();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("^user selects capacity as \"([^\"]*)\"$")
    public void userSelectsCapacityAs(String capacity) throws AutomationException {
        CommonSteps.logInfo("user selects capacity as " + capacity);
        switch (capacity) {
            case "Personal Representative":
                DriverFactory.drivers.get().findElement(By.xpath(CAPACITY_REPRESENTATIVE)).click();
                break;
            case "Counsel":
                DriverFactory.drivers.get().findElement(By.xpath(CAPACITY_COUNSEL)).click();
                break;
            default:
                throw new AutomationException("Unsupported capacity : " + capacity);
        }
    }

    @And("user clicks on name of person field")
    public void userClicksOnNameOfPersonField() throws AutomationException {
        CommonSteps.logInfo("user clicks on name of person field");
        WebDriverUtil.waitForAWhile();
        scrollToElementAndClick(PERSON_NAME_FIELD);
    }

    @Then("^user verifies for \"([^\"]*)\" form fiduciary type of contacts are displayed in the list and can be selected$")
    public void userVerifiesFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for " + formName + " form fiduciary type of contacts are displayed in the list and can be selected");
        switch (formName) {
            case "RW07":
                PageFactory.probateFormsRW07Page().verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().verifyFiduciaryTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies for \"([^\"]*)\" form attorney type of contacts are displayed in the list and can be selected$")
    public void userVerifiesAttorneyTypeOfContactsAreDisplayedInTheListAndCanBeSelected(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for " + formName + " form attorney type of contacts are displayed in the list and can be selected");
        switch (formName) {
            case "RW07":
                PageFactory.probateFormsRW07Page().verifyAttorneyTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifyAttorneyTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().verifyAttorneyTypeOfContactAreDisplayedInTheListAndCanBeSelected();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("user clicks on Clear Selection buttons")
    public void userClicksOnClearSelectionButton() throws AutomationException {
        CommonSteps.logInfo("user clicks on Clear Selection buttons");
        scrollToElementAndClick(CORPORATE_FIDUCIARY_CLEAR_SELECTION_BTN);
        WebDriverUtil.waitForAWhile(2);
        scrollToElementAndClick(PERSON_CLEAR_SELECTION_BTN);
        WebDriverUtil.waitForAWhile(2);
    }

    @Then("^user verifies selected contacts on \"([^\"]*)\" form are cleared$")
    public void userVerifiesSelectedContactsAreCleared(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that selected contacts on " + formName + " form are cleared");
        switch (formName) {
            case "RW07":
                PageFactory.probateFormsRW07Page().verifySelectedContactsAreCleared();
                break;
            case "RW08":
                PageFactory.probateFormsRW08Page().verifySelectedContactsAreCleared();
                break;
            case "RW10":
                PageFactory.probateFormsRW10Page().verifySelectedContactsAreCleared();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("verify form can be printed in pdf with name as {string}")
    public void verifyFormCanBePrintedInPdfWithNameAsRW(String formName) throws AutomationException {
        CommonSteps.logInfo("Verify form can be printed in PDF for form: " + formName);

        switch (formName) {
            case "Rw01":
                PageFactory.probateFormsRW01Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw02":
                PageFactory.probateFormsRW02Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw03":
                PageFactory.probateFormsRW03Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw04":
                PageFactory.probateFormsRW04Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw05":
                PageFactory.probateFormsRW05Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw06":
                PageFactory.probateFormsRW06Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw08":
                PageFactory.probateFormsRW08Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw07":
                PageFactory.probateFormsRW07Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rwxx":
                PageFactory.probateFormsRWxxPage().verifyFormPrintedInPDFForm(formName);
                break;
            case "Rw10":
                PageFactory.probateFormsRW10Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyFormPrintedInPDFForm(formName);
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyFormPrintedInPDFForm(formName);
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }


    @And("verify all the fields entered are correctly reflected in the {string} pdf")
    public void verifyAllTheFieldsEnteredAreCorrectlyReflectedInTheRwPdf(String formName) throws Exception {
        CommonSteps.logInfo("Verify form can be printed in PDF");
        switch (formName) {
            case "Rw01":
                PageFactory.probateFormsRW01Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw02":
                PageFactory.probateFormsRW02Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw03":
                PageFactory.probateFormsRW03Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw04":
                PageFactory.probateFormsRW04Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw05":
                PageFactory.probateFormsRW05Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw06":
                PageFactory.probateFormsRW06Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw07":
                PageFactory.probateFormsRW07Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw08":
                PageFactory.probateFormsRW08Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rwxx":
                PageFactory.probateFormsRWxxPage().verifyAllFieldsInDownloadedPDF();
                break;
            case "Rw10":
                PageFactory.probateFormsRW10Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyAllFieldsInDownloadedPDF();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyAllFieldsInDownloadedPDF();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("^user navigates to page number: \"([^\"]*)\"$")
    public void userNavigatesToPage(String pageNumber) throws AutomationException {
        CommonSteps.logInfo("user navigates to page number: " + pageNumber);
        driverUtil.getWebElement(String.format(PAGE_NUMBER_DYNAMIC_XPATH, pageNumber)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
    }

    @Then("^user verifies for \"([^\"]*)\" form file number field is editable$")
    public void userVerifiesFileNumberFieldIsEditable(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for "+formName+" form file number field is editable");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyFileNumberFieldIsEditable();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyFileNumberFieldIsEditable();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    private void scrollToElement(String elementLocator) {
        WebElement element = DriverFactory.drivers.get().findElement(By.xpath(elementLocator));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverUtil.waitForAWhile();
    }

    @When("user clicks on Name of Counsel field")
    public void userClicksOnNameOfCounselField() throws AutomationException {
        CommonSteps.logInfo("user clicks on Name of Counsel field");
        scrollToElement(NAME_OF_COUNSEL_FIELD);
        driverUtil.getWebElement(NAME_OF_COUNSEL_FIELD).click();
    }

    @Then("^user verifies for \"([^\"]*)\" form a sidebar appears and attorney can be selected$")
    public void userVerifiesASidebarAppearsAndAttorneyCanBeSelected(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for "+formName+" form a sidebar appears and attorney can be selected");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifySidebarAppearsAndAttorneyCanBeSelected();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifySidebarAppearsAndAttorneyCanBeSelected();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @And("^user verifies for \"([^\"]*)\" form selected attorney’s details are populated in the 'Name of Counsel' field$")
    public void userVerifiesSelectedAttorneySDetailsArePopulatedInTheField(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that for "+formName+" form selected attorney’s details are populated in the 'Name of Counsel' field");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifySelectedAttorneySDetailsArePopulatedInTheField();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifySelectedAttorneySDetailsArePopulatedInTheField();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies for \"([^\"]*)\" form attorney details are auto fetched and correctly displayed$")
    public void userVerifiesAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed(String formName) throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verified that "+formName+" form attorney details are auto fetched and correctly displayed");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyAttorneyDetailsAreAutoFetchedAndCorrectlyDisplayed();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @When("user click on Petitioner name field")
    public void userClickOnPetitionerNameField() throws AutomationException {
        CommonSteps.logInfo("user click on Petitioner name field");
        scrollToElement(PETITIONER_NAME_FIELD);
        driverUtil.getWebElement(PETITIONER_NAME_FIELD).click();
    }

    @Then("^user verifies for \"([^\"]*)\" form swapped petitioner names are reflected on UI accordingly$")
    public void userVerifiesSwappedPetitionerNamesAreReflectedOnUIAccordingly(String formName) throws AutomationException, IOException, ParseException {
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifySwappedPetitionerNamesAreReflectedOnUIAccordingly();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifySwappedPetitionerNamesAreReflectedOnUIAccordingly();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.logInfo("Verified that for "+formName+" form swapped petitioner names are reflected on UI accordingly");
        CommonSteps.takeScreenshot();
    }

    @Then("^user saves selected beneficiaries details for \"([^\"]*)\" form$")
    public void userSavesSelectedBeneficiariesDetails(String formName) throws AutomationException {
        CommonSteps.logInfo("user saves selected beneficiaries details for "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().userSavesSelectedBeneficiariesDetails();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userSavesSelectedBeneficiariesDetails();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies for \"([^\"]*)\" form rest of the selected beneficiaries are displayed on the attachment$")
    public void userVerifiesRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that for "+formName+" form rest of the selected beneficiaries are displayed on the attachment");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyRestOfTheSelectedBeneficiariesAreDisplayedOnTheAttachment();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @And("user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox")
    public void userClicksOnDisplayALLBeneficiariesOnAttachmentScheduleCheckbox() throws AutomationException {
        CommonSteps.logInfo("user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox");
        scrollToElement(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_BENE_ON_ATTACHMENT_BTN)).click();
        WebDriverUtil.waitForInvisibleElement(By.xpath(SPINNER));
        WebDriverUtil.waitForAWhile();
    }

    @Then("^user verifies for \"([^\"]*)\" form all the beny users are displayed as a part of attachment$")
    public void userVerifiesAllTheBenyUsersAreDisplayedAsAPartOfAttachment(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that for "+formName+" form all the beny users are displayed as a part of attachment");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyAllTheBenyUsersAreDisplayedAsAPartOfAttachment();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyAllTheBenyUsersAreDisplayedAsAPartOfAttachment();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies for \"([^\"]*)\" form the Main's count is turn to zero and only Attachment count is displayed correctly$")
    public void userVerifiesTheMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that for "+formName+" form the Main's count is turn to zero and only Attachment count is displayed correctly");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyMainSCountIsTurnToZeroAndOnlyAttachmentCountIsDisplayedCorrectly();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("^user adds initials for \"([^\"]*)\"$")
    public void userAddsInitials(String formName) throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("User adds initials for " + formName);
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().userAddsInitials();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userAddsInitials();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies name and address gets disappear for \"([^\"]*)\"$")
    public void userVerifiesNameAndAddressGetsDisappear(String formName) throws AutomationException {
        CommonSteps.logInfo("Verifying name and address disappear for " + formName);
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyNameAndAddressGetsDisappear();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyNameAndAddressGetsDisappear();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @When("^user removes initials for \"([^\"]*)\"$")
    public void userRemovesInitials(String formName) throws AutomationException {
        CommonSteps.logInfo("User removes initials for " + formName);
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().userRemovesInitials();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userRemovesInitials();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies name and address of the beneficiaries is displayed for \"([^\"]*)\"$")
    public void userVerifiesNameAndAddressOfTheBeneficiariesIsDisplayed(String formName) throws AutomationException {
        CommonSteps.logInfo("Verifying name and address of beneficiaries is displayed for " + formName);
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyNameAndAddressOfTheBeneficiariesIsDisplayed();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyNameAndAddressOfTheBeneficiariesIsDisplayed();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies correct relationship is auto fetched and displayed under relationship section for \"([^\"]*)\" form$")
    public void userVerifiesCorrectRelationshipIsAutoFetchedAndDisplayedUnderRelationshipSection(String formName) throws IOException, ParseException, AutomationException {
        CommonSteps.logInfo("Verifying that correct relationship is auto fetched and displayed under relationship section for " + formName + " form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyCorrectRelationshipIsAutoFetchedAndDisplayedUnderRelationshipSection();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyCorrectRelationshipIsAutoFetchedAndDisplayedUnderRelationshipSection();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies interest is auto fetched from beny worksheet for \"([^\"]*)\" form$")
    public void userVerifiesInterestIsAutoFetchedFromBenyWorksheet(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verifying that interest is auto fetched from beny worksheet for " + formName + " form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyInterestIsAutoFetchedFromBenyWorksheet();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyInterestIsAutoFetchedFromBenyWorksheet();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @When("^user checks the Display checkbox for beneficiaries of \"([^\"]*)\" form$")
    public void userChecksTheDisplayCheckboxForBeneficiaries(String formName) throws AutomationException {
        CommonSteps.logInfo("user checks the Display checkbox for beneficiaries of "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().userChecksTheDisplayCheckboxForBeneficiaries();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userChecksTheDisplayCheckboxForBeneficiaries();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies the contacts for which the checkbox is checked are displayed on the \"([^\"]*)\" form$")
    public void userVerifiesTheContactsForWhichTheCheckboxIsCheckedAreDisplayedOnTheForm(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that the contacts for which the checkbox is checked are displayed on the "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().userVerifiesDisplayedContactsOnForm();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().userVerifiesDisplayedContactsOnForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @When("user checks 'Display ALL INCOME Distributees on attachment' checkbox")
    public void userChecksDisplayALLINCOMEDistributeesOnAttachmentCheckbox() {
        CommonSteps.logInfo("user checks 'Display ALL INCOME Distributees on attachment' checkbox");
        scrollToElement(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN);
        DriverFactory.drivers.get().findElement(By.xpath(DISPLAY_ALL_INCOME_ON_ATTACHMENT_BTN)).click();
        WebDriverUtil.waitForAWhile();
    }

    @Then("^user verifies all the beneficiary contacts are moved to the attachment for \"([^\"]*)\" form$")
    public void userVerifiesAllTheBeneficiaryContactsAreMovedToTheAttachment(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that all the beneficiary contacts are moved to the attachment for "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyAllTheBeneficiaryContactsAreMovedToTheAttachment();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyAllTheBeneficiaryContactsAreMovedToTheAttachment();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies correct trust name is displayed on the \"([^\"]*)\" form$")
    public void userVerifiesCorrectTrustNameIsDisplayedOnTheForm(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that correct trust name is displayed on the "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyCorrectTrustNameIsDisplayedOnTheForm();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyCorrectTrustNameIsDisplayedOnTheForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies 1st individual petitioner selected on page 2 is displayed under individual petitioner on \"([^\"]*)\" form$")
    public void userVerifies1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that 1st individual petitioner selected on page 2 is displayed under individual petitioner on "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verify1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verify1StIndividualPetitionerSelectedOnPage2IsDisplayedUnderIndividualPetitioner();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies all the remaining petitioners are displayed as a part of attachment for \"([^\"]*)\" form$")
    public void userVerifiesAllTheRemainingPetitionersAreDisplayedAsAPartOfAttachment(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that all the remaining petitioners are displayed as a part of attachment for "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyAllTheRemainingPetitionersAreDisplayedAsAPartOfAttachment();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyAllTheRemainingPetitionersAreDisplayedAsAPartOfAttachment();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @When("user navigates to Estate Contacts tab")
    public void userNavigatesToEstateContactsTab() throws AutomationException {
        CommonSteps.logInfo("user navigates to Estate Contacts tab");
        waitForVisibleElement(By.xpath(ESTATE_CONTACTS_TAB));
        driverUtil.getWebElement(ESTATE_CONTACTS_TAB).click();
        waitForInvisibleElement(By.xpath(SPINNER));
    }

    @And("^user verifies for \"([^\"]*)\" form notification is displayed when the contact selected as the petitioner is removed from the estate contacts$")
    public void userVerifiesNotificationIsDisplayedWhenTheContactSelectedAsThePetitionerIsRemovedFromTheEstateContacts(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for "+formName+" form notification is displayed when the contact selected as the petitioner is removed from the estate contacts");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyNotificationIsDisplayedWhenTheContactSelectedAsThePetitionerIsRemovedFromTheEstateContacts();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyNotificationIsDisplayedWhenTheContactSelectedAsThePetitionerIsRemovedFromTheEstateContacts();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies for \"([^\"]*)\" form notification is displayed when the beneficiary contact is removed from the estate contacts$")
    public void userVerifiesNotificationIsDisplayedWhenTheBeneficiaryContactIsRemovedFromTheEstateContacts(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("Verified that for "+formName+" form notification is displayed when the beneficiary contact is removed from the estate contacts");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyNotificationIsDisplayedWhenTheBeneficiaryContactIsRemovedFromTheEstateContacts();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyNotificationIsDisplayedWhenTheBeneficiaryContactIsRemovedFromTheEstateContacts();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }

    @Then("^user verifies removed petitioner contacts from the estate contacts is also gets removed from the \"([^\"]*)\" form$")
    public void userVerifiesRemovedPetitionerContactsFromTheEstateContactsIsAlsoRemovedFromTheForm(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that removed petitioner contacts from the estate contacts is also gets removed from the "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyRemovedPetitionerContactsFromTheEstateContactsIsAlsoRemovedFromTheForm();;
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyRemovedPetitionerContactsFromTheEstateContactsIsAlsoRemovedFromTheForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @Then("^user verifies removed beneficiary contact from the estate contacts is also gets removed from the \"([^\"]*)\" form$")
    public void userVerifiesRemovedBeneficiaryContactFromTheEstateContactsIsAlsoGetsRemovedFromTheForm(String formName) throws AutomationException {
        CommonSteps.logInfo("Verified that removed beneficiary contact from the estate contacts is also gets removed from the "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().verifyRemovedBeneficiaryContactFromTheEstateContactsIsAlsoGetsRemovedFromTheForm();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().verifyRemovedBeneficiaryContactFromTheEstateContactsIsAlsoGetsRemovedFromTheForm();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
        CommonSteps.takeScreenshot();
    }

    @When("^user resets roles of removed contacts from the Estate Contacts of \"([^\"]*)\" form$")
    public void userResetsRolesOfRemovedContactsFromTheEstateContacts(String formName) throws AutomationException, IOException, ParseException {
        CommonSteps.logInfo("user resets roles of removed contacts from the Estate Contacts of "+formName+" form");
        switch (formName) {
            case "OC01":
                PageFactory.probateFormsOC01Page().resetRolesOfContacts();
                break;
            case "OC02":
                PageFactory.probateFormsOC02Page().resetRolesOfContacts();
                break;
            default:
                throw new AutomationException("Unsupported form name: " + formName);
        }
    }
}

package com.centrifi.automation.glue;

import com.centrifi.automation.MainApplication;
import com.centrifi.automation.constants.Constants;
import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.models.TestScenario;
import com.centrifi.automation.pages.PageFactory;
import com.centrifi.automation.reports.ExtentManager;
import com.centrifi.automation.reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.centrifi.automation.util.*;
import cucumber.api.*;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import cucumber.runtime.ScenarioImpl;
import gherkin.events.PickleEvent;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import runner.BaseRunner;
import java.io.File;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

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

    public static void logInfo(String... data) {
        currentStep.set(data[0]);
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
        //takeScreenshot();
    }


    @Given("^User launched \"([^\"]*)\"$")
    public void launchBrowser(String browser) throws AutomationException {
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
            if (!tags.contains("@Setup") && "true".equalsIgnoreCase(System.getProperty("TestRail"))){
                if(testCaseMapping.get(scenario.getName()) == null) {
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
}

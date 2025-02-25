package com.sixinone.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("6in1 Test Automation Report");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("6in1 Test Automation Report");
        reporter.config().setEncoding("utf-8");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "6in1");
        return extentReports;
    }
}

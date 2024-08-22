package com.centrifi.automation.util;

import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.reports.ExtentTestManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class ReportUtil {

    public static String takeScreenshot() {
        String path = null;
        String fileName = getScreenshotName();
        String directory = Paths.get("").toAbsolutePath().toString() +"/extent-reports/screenshots/";
        new File(directory).mkdir();
        File file = new File(directory + fileName);
        try {
            File screenshot = ((TakesScreenshot)DriverFactory.drivers.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, file);
            //path = file.getAbsolutePath();
            path = "screenshots/"+fileName;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }

    public static String getScreenshotName() {
        String featureName = ExtentTestManager.featureFileName.get();
        Long timestamp = System.currentTimeMillis();
        return timestamp+"_"+featureName.substring(0, featureName.indexOf("."))+".png";
    }

    public static void writeReportLog(boolean success, String step, String log, boolean attachScreenshot) {
        if(!success) {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=red>"
            +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");
            if(attachScreenshot){
                String path = takeScreenshot();
                //markup.append("<b><font color=red>"+"Screenshot"+"</font></");
                ExtentTestManager.getTest().fail(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            }
        } else {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=black>"
                    +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");
            if(attachScreenshot){
                String path = takeScreenshot();
                //markup.append("<b><font color=green>"+"Screenshot"+"</font></b>");
                ExtentTestManager.getTest().pass(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            }
        }
    }

    public static void writeReportLog(boolean success, String step, String log, String attachScreenshot) {
        if(!success) {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=red>"
                    +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");

            if(attachScreenshot!=null){
                //markup.append("<b><font color=red>"+"Screenshot"+"</font>");
                ExtentTestManager.getTest().fail(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(attachScreenshot).build());
            } else {
                ExtentTestManager.getTest().fail(markup.toString());
            }
        } else {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=black>"
                    +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");
            if(attachScreenshot!=null){
                //markup.append("<b><font color=green>"+"Screenshot"+"</font>");
                ExtentTestManager.getTest().pass(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(attachScreenshot).build());
            } else {
                ExtentTestManager.getTest().pass(markup.toString());
            }
        }
    }

    public static void writeReportLog(boolean success, String step, String log, List<String> attachScreenshot) {
        if(!success) {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=red>"
                    +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");

            if(attachScreenshot!=null){
                ExtentTestManager.getTest().fail(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(attachScreenshot.get(0)).build());
            } else {
                ExtentTestManager.getTest().fail(markup.toString());
            }
        } else {
            StringBuilder markup = new StringBuilder("<details><summary><b><font color=black>"
                    +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");
            if(attachScreenshot!=null){
                ExtentTestManager.getTest().pass(markup.toString(),MediaEntityBuilder.createScreenCaptureFromPath(attachScreenshot.get(0)).build());
            } else {
                ExtentTestManager.getTest().pass(markup.toString());
            }
        }
    }

    public static void writeReportSkipLog(String step, String log) {
        StringBuilder markup = new StringBuilder("<details><summary><b><font color=gray>"
                +step+"</font></b></summary>"+log.replaceAll(",","<br>")+"</details>");
        ExtentTestManager.getTest().skip(markup.toString());
    }
}

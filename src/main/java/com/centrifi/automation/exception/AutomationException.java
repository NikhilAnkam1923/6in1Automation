package com.centrifi.automation.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.centrifi.automation.glue.CommonSteps;

public class AutomationException extends Exception {
    private static final Logger LOGGER = Logger.getLogger(AutomationException.class.getName());
    String message;
    List<String> screenshots;
    String error;
    String stacktrace;

    public AutomationException(String message) {
        this.message = message;
        LOGGER.info("Exception Occurred: "+message);
        CommonSteps.logError(message);
    }

    public AutomationException(String message,List<String> screenshotList) {
        this.message = message;
        screenshots = screenshotList;
        LOGGER.info("Exception Occurred: "+message);
        CommonSteps.logError(message, screenshotList);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScreenshot(String screenshot) {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        if(screenshots==null)
            screenshots = new ArrayList<>();
        this.screenshots.add(screenshot);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "AutomationException{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}

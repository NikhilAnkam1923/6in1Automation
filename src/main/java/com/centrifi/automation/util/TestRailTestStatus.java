package com.centrifi.automation.util;


public class TestRailTestStatus {

    private Integer testCaseID;
    private String testCaseName;
    private String filePath;
    private String description;
    /**
     * 1 - Pass
     * 5 - Fail
     */
    private Integer testCaseStatus;

    public Integer getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(Integer testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTestCaseStatus() {
        return testCaseStatus;
    }

    public void setTestCaseStatus(Integer testCaseStatus) {
        this.testCaseStatus = testCaseStatus;
    }


}
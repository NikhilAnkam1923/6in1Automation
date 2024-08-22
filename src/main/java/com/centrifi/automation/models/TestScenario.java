package com.centrifi.automation.models;

import com.centrifi.automation.constants.Constants;

public class TestScenario {
    private String id;
    private String featureFileName;
    private String scenarioName;
    private boolean smokeTest;
    private boolean regressionTest;
    private boolean end2End;
    private boolean noType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatureFileName() {
        return featureFileName;
    }

    public void setFeatureFileName(String featureFileName) {
        this.featureFileName = featureFileName;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public boolean getSmokeTest() {
        return smokeTest;
    }

    public void setSmokeTest(boolean smokeTest) {
        this.smokeTest = smokeTest;
    }

    public boolean getRegressionTest() {
        return regressionTest;
    }

    public void setRegressionTest(boolean regressionTest) {
        this.regressionTest = regressionTest;
    }

    public boolean getEnd2End() {
        return end2End;
    }

    public void setEnd2End(boolean end2End) {
        this.end2End = end2End;
    }

    public boolean getNoType() {
        return noType;
    }

    public void setNoType(boolean noType) {
        this.noType = noType;
    }

    @Override
    public String toString() {
        return "TestScenario{" +
                "id='" + id + '\'' +
                ", featureFileName='" + featureFileName + '\'' +
                ", scenarioName='" + scenarioName + '\'' +
                ", smokeTest=" + smokeTest +
                ", regressionTest=" + regressionTest +
                ", end2End=" + end2End +
                ", noType=" + noType +
                '}';
    }

    public boolean isEnable(String suiteType) {
        if(suiteType.equalsIgnoreCase(Constants.SUITE_TYPE_SMOKE))
            return this.getSmokeTest();
        else
            return this.getRegressionTest();
    }

    public boolean isDisable(String suiteType) {
        return !isEnable(suiteType);
    }
}

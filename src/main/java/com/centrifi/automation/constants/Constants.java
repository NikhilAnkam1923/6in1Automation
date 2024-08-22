package com.centrifi.automation.constants;

public interface Constants {
    public static final String PROJECT_NAME = "CENTRIFI TEST AUTOMATION";
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String PROPERTY_FILE_PATH = "src/test/resources/";
    public static final String TEST_DATA_FILE_PATH = "src/test/resources/test-data/";

    public static final String DOT =".";

    public static final String ENVIRONMENT ="env";
    public static final String BROWSER = "browser";

    public static final String URL = "url";
    public static final String USER_TYPE = "user.type";
    public static final String USER_NAME = "user.name";
    public static final String PASSWORD = "user.pass";

    public static final String PAGE_LOAD_TIMEOUT = "pageload.max.timeout";

    public static final String GROUPING_BY = "groupingBy";
    public static final String GROUPING_BY_TAGS = "Tags";
    public static final String GROUPING_BY_EXCEL_MAPPING_DATA = "TestData";
    public static final String SUITE_TYPE = "suiteType";
    public static final String SUITE_TYPE_SMOKE = "SmokeTest";
    public static final String SUITE_TYPE_REGRESSION = "RegressionTest";


}

package com.centrifi.automation.util;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRailUtility {

    public static final Logger reportLogger = Logger.getLogger(TestRailUtility.class.getName());
    private static final String EMPTY_STRING = "";
    private final String baseURI;
    private final String authToken;
    private static TestRailUtility instance = null;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION = "Authorization";

    private TestRailUtility(String baseURI, String userName, String password) {
        this.baseURI = baseURI;
        this.authToken = getAuthorization(userName, password);
    }

    public static TestRailUtility getInstance(String baseURI, String userName, String password) {
        if (instance == null) instance = new TestRailUtility(baseURI, userName, password);
        return instance;
    }

    private static String getAuthorization(String user, String password) {
        try {
            String auth = user + ":" + password;
            byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
            return "Basic " + new String(encodedAuth);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return EMPTY_STRING;
    }

    /**
     * Method to get all the test case title and its id mapped under a suite
     *
     * @param projectID - ID of project in testrail should be passed as integer
     * @param suiteID   - ID of test suite in testrail should be passed as integer
     * @return Map<String, Integer> - Key=TestCaseTitle, Value=TestCaseID
     */
    public Map<String, Integer> getTestCaseIDsUnderSuite(Integer projectID, Integer suiteID) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/get_cases/" + projectID, EMPTY_STRING);
            queryParam.put("suite_id", suiteID);
            String response = getRequest(EMPTY_STRING, headers, queryParam, 200);
            JsonPath jsonPath = new JsonPath(response);
            int testCaseCount = jsonPath.get("cases.size()");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < testCaseCount; i++) {
                map.put(jsonPath.get("cases[" + i + "].title"), jsonPath.get("cases[" + i + "].id"));
            }
            return map;
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING, "Get test case IDs under test suite failed: " + e.getMessage());
            return Collections.emptyMap();
        }
    }

    /**
     * Method to get all the test run ids under a project
     *
     * @param projectID - ID of project in testrail should be passed as integer
     * @return List<Integer> - List of test run id as Integer
     */
    public List<Integer> getTestRunsUnderProject(Integer projectID) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/get_runs/" + projectID, EMPTY_STRING);
            String response = getRequest(EMPTY_STRING, headers, queryParam, 200);
            JsonPath jsonPath = new JsonPath(response);
            int testCaseCount = jsonPath.get("runs.size()");
            List<Integer> testRunList = new ArrayList<>();
            for (int i = 0; i < testCaseCount; i++) {
                testRunList.add(jsonPath.get("runs[" + i + "].id"));
            }
            return testRunList;
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Get test case IDs under test suite failed: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Method to get api request
     *
     * @param resourcePath       Enter empty if not required
     * @param headersMap         Map all the header parameters in <String, String> format (Required Param)
     * @param queryParam         Add only LinkedHashMap to maintain order
     * @param expectedStatusCode 0 if not required to assert the status code
     * @return returns response as string
     */
    public String getRequest(String resourcePath, Map<String, String> headersMap, Map<String, Object> queryParam, int expectedStatusCode) {
        try {
            RestAssured.baseURI = baseURI;
            Headers headers = addHeaders(headersMap);
            if (expectedStatusCode > 0)
                return RestAssured.given().headers(headers).queryParams(queryParam).expect().statusCode(expectedStatusCode).when().get(resourcePath).then().extract().body().asString();
            else
                return RestAssured.given().headers(headers).queryParams(queryParam).when().get(resourcePath).then().extract().body().asString();
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Get request failed due with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to create new test case under a given section
     *
     * @param sectionID     Section ID under which the new testcase to be added
     * @param testcaseTitle Title of the testcase
     * @return returns the created testcase id as an integer
     */
    public Integer createNewTestCase(int sectionID, String testcaseTitle, List<String> testSteps) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_case/" + sectionID, "");
            /*StringBuilder steps = new StringBuilder("{\"content\":\"");
            for (int i = 0; i < testSteps.size(); i++) {
                String step = testSteps.get(i).replaceAll("\"","'");
                if (i == testSteps.size() - 1) steps.append(step).append("\"}");
                else steps.append(step).append("\"},{\"content\":\"");
            }*/
            StringBuilder steps = new StringBuilder(0);
            for (int i = 0; i < testSteps.size(); i++) {
                String step = testSteps.get(i).replaceAll("\"","'");
                steps.append((i+1)+". "+step+"\\n");
            }
            reportLogger.log(Level.INFO,"Creating new test case -  " + testcaseTitle);
            reportLogger.log(Level.INFO,"####################################################################");
            //String requestBody = "{\"title\":\"" + testcaseTitle + "\",\"type_id\":1,\"priority_id\":3,\"custom_test_status\":3,\"custom_automation_status\":3,\"custom_steps_separated\":[" + steps + "]}";
            String requestBody = "{\"title\":\"" + testcaseTitle + "\",\"type_id\":1,\"priority_id\":3,\"custom_test_status\":3,\"custom_automation_status\":3,\"custom_steps\":\"" + steps + "\"}";
            reportLogger.log(Level.INFO,"Request Body -  " + requestBody);
            String response = postRequest("", headersMap, queryParam, requestBody, 200);
            reportLogger.log(Level.INFO,"Response -  " + response);
            reportLogger.log(Level.INFO,"####################################################################");
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.get("id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"New test case creation failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to create new test run under a project
     *
     * @param projectID    ID of the project as int
     * @param suiteID      ID of the suite as int
     * @param testRunTitle Name of the test run as String
     * @param testCaseIDs  List of test case IDs to be added to the test run
     * @return returns the id of created test run
     */
    public int createNewTestRun(int projectID, int suiteID, String testRunTitle, List<Integer> testCaseIDs) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_run/" + projectID, "");
            String requestBody = "{\"suite_id\":" + suiteID + ",\"name\":\"" + testRunTitle + "\",\"include_all\":false,\"case_ids\":" + testCaseIDs + ",\"description\":\"Automated test run\"}";
            String response = postRequest(EMPTY_STRING, headersMap, queryParam, requestBody, 200);
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.get("id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"New test run creation failed with the error: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
            return 0;
        }
    }

    /**
     * Method to update existing test run with its id
     *
     * @param testRunID   ID of the test run as int
     * @param testCaseIDs List of testcases to be added
     */
    public void updateTestRun(int testRunID, List<Integer> testCaseIDs) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/update_run/" + testRunID, EMPTY_STRING);
            String requestBody = "{\"case_ids\":" + testCaseIDs + "}";
            String response = postRequest(EMPTY_STRING, headersMap, queryParam, requestBody, 200);
            JsonPath jsonPath = new JsonPath(response);
            jsonPath.get("id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Update test run failed with the error: " + e.getMessage());
        }
    }

    /**
     * Method to post api request
     *
     * @param resourcePath       Enter empty if not required
     * @param headersMap         Map all the header parameters in <String, String> format (Required Param)
     * @param queryParam         Add only LinkedHashMap to maintain order
     * @param requestBody        Request payload as string
     * @param expectedStatusCode 0 if not required to assert the status code
     * @return returns response as string
     */
    private String postRequest(String resourcePath, Map<String, String> headersMap, Map<String, Object> queryParam, String requestBody, int expectedStatusCode) {
        try {
            RestAssured.baseURI = baseURI;
            Headers headers = addHeaders(headersMap);
            if (expectedStatusCode > 0)
                return RestAssured.given().headers(headers).queryParams(queryParam).body(requestBody).expect().statusCode(expectedStatusCode).when().post(resourcePath).then().extract().body().asString();
            else
                return RestAssured.given().headers(headers).queryParams(queryParam).body(requestBody).when().post(resourcePath).then().extract().body().asString();
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Post request failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to add results to the given test run
     *
     * @param testRunID  ID of the test run
     * @param testCaseID ID of the testcase
     * @param resultCode Test result code (1-Pass, 5-Failed)
     * @return returns result id
     */
    public Integer addResultToTestRun(int testRunID, int testCaseID, int resultCode, String comments) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_result_for_case/" + testRunID + "/" + testCaseID, EMPTY_STRING);
            comments = comments != null ? comments.replaceAll("[^a-zA-Z0-9!@#$%^&*()+={}|:;',./?`~_\\[\\]\\- ]", " ") : "No Message";
            String requestBody = "{\"status_id\":" + resultCode + ",\"comment\":\"" + comments.replaceAll("\"","'") + "\"}";
            String response = postRequest(EMPTY_STRING, headersMap, queryParam, requestBody, 200);
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.get("id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Adding results to the test run failed with the error:" + e.getMessage());
            return 0;
        }
    }

    /**
     * Method to add results to multiple testcases in a bulk manner to a given test run
     * @param testRunID The ID of test run to which the results need to be updated
     * @param testResultMapping Map of test case id and it's results and description should be passed
     */
    public void addResultsToTestRun(int testRunID, Map<Integer, TestRailTestStatus> testResultMapping) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_results_for_cases/" + testRunID, EMPTY_STRING);
            String comments = "";
            StringBuilder requestBody = new StringBuilder("{\"results\":[");
            int i = 0;
            for (Map.Entry<Integer, TestRailTestStatus> entry : testResultMapping.entrySet()) {
                comments = entry.getValue().getDescription();
                comments = comments != null ? comments.replaceAll("[^a-zA-Z0-9!@#$%^&*()+={}|:;',./?`~_\\[\\]\\- ]", " ") : "No message";
                requestBody.append("{\"case_id\":").append(entry.getKey()).append(",\"status_id\":").append(entry.getValue().getTestCaseStatus()).append(",\"comment\":\"").append(comments.replaceAll("\"","'")).append("\"}");
                String appendingString = i == testResultMapping.size() - 1 ? "]}" : ",";
                requestBody.append(appendingString);
                i = i + 1;
            }
            postRequest(EMPTY_STRING, headersMap, queryParam, String.valueOf(requestBody), 200);
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Adding results to the test run failed with the error:" + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Method to add the attachment to the given test result
     *
     * @param testResultID TestResult ID to which the attachment added
     * @param filePath     Path of the attachment including its name and extension
     * @return returns attachment id
     */
    public String addAttachmentToTestResult(int testResultID, String filePath) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, "multipart/form-data");
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_attachment_to_result/" + testResultID, "");
            String response = postRequestWithAttachment(headersMap, EMPTY_STRING, queryParam, "attachment", filePath, 200);
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.get("attachment_id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Adding attachment to the result is failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to post the attachment
     *
     * @param headersMap         Map all the header parameters in <String, String> format (Required Param)
     * @param resourcePath       Enter empty if not required
     * @param queryParam         Add only LinkedHashMap to maintain order
     * @param controlName        Control name of the file
     * @param filePath           path of the file to be attached
     * @param expectedStatusCode 0 if not required to assert the status code
     * @return returns response as string
     */
    private String postRequestWithAttachment(Map<String, String> headersMap, String resourcePath, Map<String, Object> queryParam, String controlName, String filePath, int expectedStatusCode) {
        try {
            RestAssured.baseURI = baseURI;
            Headers headers = addHeaders(headersMap);
            if (expectedStatusCode > 0)
                return RestAssured.given().multiPart(controlName, new File(filePath)).headers(headers).queryParams(queryParam).expect().statusCode(expectedStatusCode).when().post(resourcePath).then().extract().body().asString();
            else
                return RestAssured.given().multiPart(controlName, new File(filePath)).headers(headers).queryParams(queryParam).expect().when().post(resourcePath).then().extract().body().asString();
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"The method to post the request with attachment is failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to add headers to attach in the api requests
     *
     * @param headersMap Provide the headers in <String,String> format
     * @return returns Headers to be used for requests
     */
    private Headers addHeaders(Map<String, String> headersMap) {
        try {
            List<Header> headerList = new ArrayList<>();
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headerList.add(new Header(entry.getKey(), entry.getValue()));
            }
            return new Headers(headerList);
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Add headers is failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to delete test case with its id
     *
     * @param testCaseID ID to delete testcase
     */
    public void deleteTestCaseWithID(int testCaseID) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/delete_case/" + testCaseID, "");
            postRequest(EMPTY_STRING, headersMap, queryParam, EMPTY_STRING, 200);
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Deleting test case with id is failed with the error: " + e.getMessage());
        }
    }

    /**
     * Method to get all the section ids under a test suite
     *
     * @param projectID ID of the project as Integer
     * @param suiteID   ID of the suite as Integer
     * @return returns section IDs under the suite
     */
    public Map<String, Integer> getSectionIDsUnderSuite(Integer projectID, Integer suiteID) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/get_sections/" + projectID, "");
            queryParam.put("suite_id", suiteID);
            String response = getRequest(EMPTY_STRING, headers, queryParam, 200);
            JsonPath jsonPath = new JsonPath(response);
            int testCaseCount = jsonPath.get("sections.size()");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < testCaseCount; i++) {
                map.put(jsonPath.get("sections[" + i + "].name"), jsonPath.get("sections[" + i + "].id"));
            }
            return map;
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Getting section IDs under suite is failed with the error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to create new test section under the given project and test suite
     *
     * @param projectID   ID of the project as Integer
     * @param suiteID     ID of the suite as Integer
     * @param sectionName Name of the section
     * @return returns section IDs under the suite
     */
    public Integer createNewSection(int projectID, int suiteID, String sectionName) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/add_section/" + projectID, "");
            String requestBody = "{\"suite_id\":" + suiteID + ",\"name\":\"" + sectionName + "\"}";
            String response = postRequest(EMPTY_STRING, headersMap, queryParam, requestBody, 200);
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.get("id");
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Creating new section is failed with the error: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Method to get section id with the given section name
     *
     * @param sectionMapping Section name id map
     * @param projectID      project id under which section to be created if not present
     * @param suiteID        suite id under which section to be created if not present
     * @param sectionName    name of the section
     * @return returns the id of the section
     */
    public synchronized Integer getSectionIDWithSectionName(Map<String, Integer> sectionMapping, Integer projectID, Integer suiteID, String sectionName) {
        sectionMapping.computeIfAbsent(sectionName, sectionID -> createNewSection(projectID, suiteID, sectionName));
        return sectionMapping.get(sectionName);
    }

    /**
     * Method to update test results all at once by adding the test run if it doesn't exist
     *
     * @param projectID          Project id as an Integer
     * @param testRunID          Run id as an Integer
     * @param suiteID            Suite id as an Integer
     * @param testRunTitle       Test run name as String
     * @param mapTestIdAndStatus Map that contains the test id  and respective result
     */
    public void updateTestResultsToTestRun(Integer projectID, Integer testRunID, Integer suiteID, String testRunTitle, Map<Integer, TestRailTestStatus> mapTestIdAndStatus) {
        try {
            List<Integer> testCaseIDs = new ArrayList<>(mapTestIdAndStatus.keySet());
            if (!getTestRunsUnderProject(projectID).contains(testRunID))
                testRunID = createNewTestRun(projectID, suiteID, testRunTitle, testCaseIDs);
            else updateTestRun(testRunID, testCaseIDs);
            Integer testResultID;
            Map<Integer,TestRailTestStatus> testResultsWithoutAttachment=new HashMap<>();
            for (Map.Entry<Integer, TestRailTestStatus> entry : mapTestIdAndStatus.entrySet()) {
                if (entry.getValue().getFilePath() != null) {
                    testResultID = addResultToTestRun(testRunID, entry.getKey(), entry.getValue().getTestCaseStatus(), entry.getValue().getDescription());
                    addAttachmentToTestResult(testResultID, entry.getValue().getFilePath());
                }else{
                    testResultsWithoutAttachment.put(entry.getKey(),entry.getValue());
                }
            }
            if(testResultsWithoutAttachment.size()>0)
                addResultsToTestRun(testRunID,testResultsWithoutAttachment);
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Updating the test results to the test run failed with the error :" + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
    
    /**
     * Method to update existing test case with a given test case id
     *
     * @param testCaseID    ID of test case which needs to be updated
     * @param testcaseTitle Title of the testcase
     * @param testSteps List of test steps
    */
    public void updateTestCase(int testCaseID, String testcaseTitle, List<String> testSteps) {
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(CONTENT_TYPE, APPLICATION_JSON);
            headersMap.put(AUTHORIZATION, authToken);
            LinkedHashMap<String, Object> queryParam = new LinkedHashMap<>();
            queryParam.put("/api/v2/update_case/" + testCaseID, "");
            StringBuilder steps = new StringBuilder("{\"content\":\"");
            for (int i = 0; i < testSteps.size(); i++) {
                String step = testSteps.get(i).replaceAll("\"","'");
                if (i == testSteps.size() - 1) steps.append(step).append("\"}");
                else steps.append(step).append("\"},{\"content\":\"");
            }
            String requestBody = "{\"title\":\"" + testcaseTitle + "\",\"custom_steps_separated\":[" + steps + "]}";
            postRequest("", headersMap, queryParam, requestBody, 200);
        } catch (Throwable e) {
            reportLogger.log(Level.WARNING,"Update test case failed with the error: " + e.getMessage());
        }
}
    
}

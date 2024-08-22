package com.centrifi.automation.util;

import com.centrifi.automation.constants.Constants;
import com.centrifi.automation.models.TestScenario;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;


public class TestDataExcelUtil {
    private static final Logger LOGGER = Logger.getLogger(TestDataExcelUtil.class.getName());
    private static final String XLSX_FILE_PATH = Constants.TEST_DATA_FILE_PATH + "TestData.xlsx";
    private static final String XLSX_FILE_ALTERNATE_PATH = Constants.TEST_DATA_FILE_PATH + "TestData1.xlsx";
    private static final String TEST_SCENARIO_MAPPING_SHEET = "ScenarioMapping";
    public static Map<String, TestScenario> TEST_SCENARIO_MAPPING = new HashMap<>();
    private static DataFormatter dataFormatter = new DataFormatter();
    private static Workbook workbook = null;

    static {
        TEST_SCENARIO_MAPPING = getScenarioMappings(TEST_SCENARIO_MAPPING_SHEET);
    }
    /**
     * This method creates and return test data excel workbook..
     *
     * @return Workbook
     */
    public static Workbook getWorkBook() {
        try {
            if (workbook == null) {
                workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
            }
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            LOGGER.info(e.getMessage());
            try {
                copyFileUsingStream(XLSX_FILE_PATH, XLSX_FILE_ALTERNATE_PATH);
                workbook = WorkbookFactory.create(new File(XLSX_FILE_ALTERNATE_PATH));
            } catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
                LOGGER.info(e1.getMessage());
            } finally {
                deleteFile(XLSX_FILE_ALTERNATE_PATH);
            }
        } finally {
            deleteFile(XLSX_FILE_ALTERNATE_PATH);
        }
        return workbook;
    }

    /**
     * This method return a sheet from workbook if workbook contains sheet with
     * the given sheet name.
     *
     * @param sheetName
     * @return Sheet
     */
    public static Sheet getDataSheet(String sheetName) {
        Workbook workbook = getWorkBook();
        if(workbook!=null) {
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {
                    return sheet;
                }
            }
        }
        return null;
    }

    /**
     * Method returns Map (column index with header text mapping).
     * @return Map<Integer, String>
     */
    private static HashMap<Integer, String> getHeaderMap(Row headerRow) {
        if (headerRow == null)
            return null;
        HashMap<Integer, String> headerMap = new HashMap<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        int columnNumber = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String headerValue = dataFormatter.formatCellValue(cell);
            headerMap.put(columnNumber, headerValue);
            columnNumber++;
        }
        return headerMap;
    }

    /**
     * Method returns all test method list from input data excel file.
     * FeatureFileName	ScenarioName	SmokeTest	RegressionTest	End2End	NoType
     */
    public static Map<String, TestScenario> getScenarioMappings(String testMasterSheet) {
        Map<String, TestScenario> testFunctions = new HashMap<>();
        Sheet sheet = getDataSheet(testMasterSheet);
        if(sheet==null)
            return testFunctions;
        Iterator<Row> rowIterator = sheet.rowIterator();
        int header = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (header == 0) {
                header++;
            } else {
                TestScenario tesFunction = new TestScenario();
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    String cellData = dataFormatter.formatCellValue(row.getCell(i));
                    switch (i) {
                        case 0:
                            tesFunction.setId(cellData.trim());
                            break;
                        case 1:
                            tesFunction.setFeatureFileName(cellData.trim());
                            break;
                        case 2:
                            tesFunction.setScenarioName(cellData.trim());
                            break;
                        case 3:
                            if (cellData != null && cellData.equalsIgnoreCase("yes"))
                                tesFunction.setSmokeTest(true);
                            break;
                        case 4:
                            if (cellData != null && cellData.equalsIgnoreCase("yes"))
                                tesFunction.setRegressionTest(true);
                            break;
                        case 5:
                            if (cellData != null && cellData.equalsIgnoreCase("yes"))
                                tesFunction.setEnd2End(true);
                            break;
                        case 6:
                            if (cellData != null && cellData.equalsIgnoreCase("yes"))
                                tesFunction.setNoType(true);
                            break;
                        default:
                            break;
                    }
                }
                testFunctions.put(tesFunction.getFeatureFileName()+"_"+tesFunction.getScenarioName(), tesFunction);
            }
        }
        LOGGER.info("Function List: " + testFunctions);

        return testFunctions;
    }

    /**
     * Method is created to copy of a file.
     *
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void copyFileUsingStream(String source, String destination) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null)
                is.close();
            if (os != null)
                os.close();
        }
    }

    /**
     * Method is created to delete file.
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File alternateFile = new File(filePath);
        if (alternateFile.exists()) {
            alternateFile.delete();
        }
    }

    /**
     * Method is created to clean a directory.
     */
    public static void cleanDirectory(String directoryName) {
        File dir = new File(Constants.USER_DIR + "/" + directoryName);
        deleteRecursive(dir);
        dir.delete();
    }

    /**
     * Method is created to delete a directory recursively .
     */
    private static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    private static Set<String> getIncludedFeatureFiles(Map<String, TestScenario> sheetName,String suiteType) {
        Set<String> includedFeatures = new HashSet<>();
        for(String featureFileName: sheetName.keySet()) {
            TestScenario scenario = sheetName.get(featureFileName);
            if(scenario.isEnable(suiteType))
                includedFeatures.add(scenario.getFeatureFileName());
        }
        return includedFeatures;
    }

    public static Set<String> includedFeatures = null;
    public static boolean isFeatureIncluded(Map<String, TestScenario> sheetName, String suiteType, String featureFileName) {
        if(includedFeatures==null)
            includedFeatures = getIncludedFeatureFiles(sheetName,suiteType);
        return includedFeatures.contains(featureFileName);
    }

}

package com.centrifi.automation.util;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class ExcelDataProviderUtil {
    private static final Logger LOGGER = Logger.getLogger(ExcelDataProviderUtil.class.getName());
    private static DataFormatter dataFormatter = new DataFormatter();
    private static Workbook workbook = null;

    /**
     * This method creates and return test data excel workbook..
     *
     * @return Workbook
     */
    private static Workbook getWorkBook(String filePath, String alternateFilePath) {
        try {
            if (workbook == null) {
                workbook = WorkbookFactory.create(new File(filePath));
            }
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            LOGGER.info(e.getMessage());
            try {
                copyFileUsingStream(filePath, alternateFilePath);
                workbook = WorkbookFactory.create(new File(alternateFilePath));
            } catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
                LOGGER.info(e1.getMessage());
            } finally {
                deleteFile(alternateFilePath);
            }
        } finally {
            deleteFile(alternateFilePath);
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
    private static Sheet getDataSheet(String filePath, String alternateFilePath, String sheetName) {
        if(filePath==null)
            return null;
        if(filePath.contains("${env}"))
            filePath = filePath.replace("${env}", System.getProperty("env"));
        Workbook workbook = getWorkBook(filePath, alternateFilePath);
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
     * Method to get enabled column index from the Excel sheet if exist else it will return -1
     * @param headerRow
     * @return
     */
    private static int getEnabledColumnIndex(Row headerRow) {
        if (headerRow == null)
            return -1;
        HashMap<Integer, String> headerMap = new HashMap<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        int enabledColumnIndex = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String headerValue = dataFormatter.formatCellValue(cell);
            if(headerValue.equalsIgnoreCase("Enabled"))
                return enabledColumnIndex;
            enabledColumnIndex++;
        }
        return -1;
    }

    /**
     * This method is written to get all excel records.
     * @param filePath
     * @param testMasterSheet
     * @return
     */
    public static List<List<String>> getExcelDataSetWithHeader(String filePath, String testMasterSheet) {
        boolean hasDescription = false;
        String dataProviderHasDescription = System.getProperty("DataProviderHasDescription");
        if(dataProviderHasDescription!=null && dataProviderHasDescription.equalsIgnoreCase("true"))
            hasDescription = true;
        List<List<String>> dataList = new ArrayList<>();
        Sheet sheet = getDataSheet(filePath, filePath.replace(".","1."),testMasterSheet);
        if(sheet==null)
            return dataList;
        Iterator<Row> rowIterator = sheet.rowIterator();
        int enabledColumnIndex = -1;
        int header = 0;
        boolean isFirstRow = true;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(hasDescription && isFirstRow) {
                isFirstRow = false;
                continue;
            }
            if (header == 0) {
                enabledColumnIndex = getEnabledColumnIndex(row);
                header++;
            }
            List<String> recordData = new ArrayList<>();
            for (int i = 0; i < row.getLastCellNum(); i++) {
                String cellData = dataFormatter.formatCellValue(row.getCell(i));
                if(i==enabledColumnIndex && !(cellData.equalsIgnoreCase("yes") || cellData.equalsIgnoreCase("enabled"))) {
                    recordData = null;
                    break;
                }
                if(i!=enabledColumnIndex)
                    recordData.add(cellData);
            }

            if(recordData!=null)
                dataList.add(recordData);

        }
        return dataList;
    }

    /**
     * Method is created to copy of a file.
     *
     * @param source
     * @param destination
     * @throws IOException
     */
    private static void copyFileUsingStream(String source, String destination) throws IOException {
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
    private static void deleteFile(String filePath) {
        File alternateFile = new File(filePath);
        if (alternateFile.exists()) {
            alternateFile.delete();
        }
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

}

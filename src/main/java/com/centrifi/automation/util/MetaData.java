package com.centrifi.automation.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MetaData {
    private String dataFile;
    private String sheetName;

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public static MetaData readMetaData(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, MetaData.class);
    }
}

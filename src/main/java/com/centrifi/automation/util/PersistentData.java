package com.centrifi.automation.util;

import com.centrifi.automation.constants.Constants;
import com.centrifi.automation.exception.AutomationException;

import java.io.*;
import java.util.Properties;

public class PersistentData {
    public static final String PERSISTENT_DATA_PROPERTIES_FILE_PATH = Constants.TEST_DATA_FILE_PATH + "persistent-data.properties";

    public static Properties load(String filePath) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            file.createNewFile();
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
        } catch(Exception ex) {
            //DO Nothing..
        } finally {
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static String getProperty(String key) throws AutomationException {
        Object value = load(PERSISTENT_DATA_PROPERTIES_FILE_PATH).get(key);
        return value!=null?value.toString():null;
    }

    public static void updatePersistentProperty(String key, String value) throws AutomationException {
        Properties prop = load(PERSISTENT_DATA_PROPERTIES_FILE_PATH);
        try (OutputStream output = new FileOutputStream(PERSISTENT_DATA_PROPERTIES_FILE_PATH)) {
            // set the properties value
            prop.setProperty(key, value);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) throws AutomationException {
        //PersistentData.updatePersistentProperty("SIG_TEXT_EXECUTED_COUNTER_INDEX","11");
        //System.out.println("SIG_TEXT_EXECUTED_COUNTER: "+getProperty("SIG_TEXT_EXECUTED_COUNTER_INDEX"));
    }

}

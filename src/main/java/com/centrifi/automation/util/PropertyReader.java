package com.centrifi.automation.util;

import com.centrifi.automation.constants.Constants;
import com.centrifi.automation.exception.AutomationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PropertyReader {
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    public static final String CONFIG_PROPERTIES_FILE = "src/test/resources/config/config.properties";
    public static final String ENV_PROPERTIES_FILE = "src/test/resources/config/env.properties";
    public static final String USERS = "src/test/resources/test-data/${env}/users.json";

    public static void loadProperties(String fileName) throws AutomationException {
        InputStream inputStream = null;
        try {
            File file = new File(fileName);
            inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            if(!properties.isEmpty())
                for(Object key: properties.keySet())
                    System.setProperty(key.toString(), properties.getProperty(key.toString()));
        } catch(Exception ex) {
            throw new AutomationException(String.format("Unable to read property file: {s}", fileName));
        } finally {
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static String groupingBy() {
        String groupingBy = System.getProperty(Constants.GROUPING_BY);
        if(groupingBy==null)
            System.setProperty(Constants.GROUPING_BY, Constants.GROUPING_BY_TAGS);
        return System.getProperty(Constants.GROUPING_BY);
    }

    public static String suiteType() {
        String suiteType = System.getProperty(Constants.SUITE_TYPE);
        if(suiteType==null)
            System.setProperty(Constants.SUITE_TYPE, Constants.SUITE_TYPE_REGRESSION);
        return System.getProperty(Constants.SUITE_TYPE);
    }

    public static String getEnv(){
        String env=System.getProperty("env");
        if(env==null)
            System.setProperty("env","dev");
        return System.getProperty("env");
    }

}

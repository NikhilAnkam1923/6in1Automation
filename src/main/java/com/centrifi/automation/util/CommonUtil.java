package com.centrifi.automation.util;

import io.cucumber.datatable.DataTable;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {
    public CommonUtil() {
    }

    public static Map<String, String> readData(DataTable parameters) {
        Map<String, String> parametersMap = new LinkedHashMap<>();
        List<Map<String, String>> rows = parameters.asMaps();
        for (Map<String, String> row : rows) {
            parametersMap.put(row.get("FieldName"), CommonUtil.processString(row.get("Value")));
        }
        return parametersMap;
    }

    public static int getIntFromObject(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            int val = 0;

            try {
                val = Integer.parseInt(obj.toString());
            } catch (Exception var3) {
            }

            return val;
        }
    }

    public static String getUTF8String(String value) {
        try {
            byte[] byteArray = value.getBytes();
            byte[] utf8ByteArray = (new String(byteArray, "ISO-8859-1")).getBytes("UTF-8");
            value = new String(utf8ByteArray);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return value;
    }

    public static String getIOSString(String value) {
        try {
            byte[] byteArray = value.getBytes();
            byte[] utf8ByteArray = (new String(byteArray, "UTF-8")).getBytes("ISO-8859-1");
            value = new String(utf8ByteArray);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return value;
    }

    public static String processString(String key) {
        if (key != null && !key.isEmpty()) {
            String value = null;
            if (!key.contains("Today's Date") && !key.contains("Current Date")) {
                if (key.contains("$timestamp")) {
                    value = key.replace("$timestamp", Long.toString(System.currentTimeMillis()));
                } else if (key.contains("$uuid")) {
                    value = key.replace("$uuid", UUID.randomUUID().toString());
                } else if (key.startsWith("@")) {
                    value = DataCache.readVariable(key) != null ? DataCache.readVariable(key) : key;
                } else if (key.startsWith("$")) {
                    value = System.getProperty(key.substring(1));
                } else {
                    value = System.getProperty(key) != null ? System.getProperty(key) : key;
                }
            } else {
                value = currentDate("MM/dd/yyyy");
            }

            return value == null ? key : value;
        } else {
            return key;
        }
    }

    public static String currentDate(String formatStr) {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat(formatStr);
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));
        return formatter.format(date);
    }

    public static String[] getStringToStringArray(String values) {
        if (values.startsWith("[") && values.endsWith("]")) {
            values = values.replaceAll(", ", ",");
            values = values.substring(1, values.length() - 1);
        }

        return values.split(",");
    }
}

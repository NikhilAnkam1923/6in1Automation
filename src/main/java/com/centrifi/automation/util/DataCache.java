package com.centrifi.automation.util;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class DataCache {
    public static ThreadLocal<Map<String, Response>> serviceResponseCache = new ThreadLocal();
    public static ThreadLocal<Map<String, String>> variableCache = new ThreadLocal();
    public static ThreadLocal<Map<Object, Object>> objectCache = new ThreadLocal();

    public DataCache() {
    }

    public static void saveVariable(String key, String value) {
        Map<String, String> dataMap = (Map)variableCache.get();
        if (dataMap == null) {
            dataMap = new HashMap();
        }

        ((Map)dataMap).put(key, value);
        variableCache.set(dataMap);
    }

    public static String readVariable(String key) {
        Map<String, String> dataMap = (Map)variableCache.get();
        return dataMap == null ? key : (String)((Map)variableCache.get()).get(key);
    }

    public static void saveObject(Object key, Object value) {
        Map<Object, Object> dataMap = (Map)objectCache.get();
        if (dataMap == null) {
            dataMap = new HashMap();
        }

        ((Map)dataMap).put(key, value);
        objectCache.set(dataMap);
    }

    public static Object readObject(Object key) {
        Map<Object, Object> dataMap = (Map)objectCache.get();
        return dataMap != null ? ((Map)objectCache.get()).get(key) : null;
    }

    public static void saveResponse(String key, Response response) {
        Map<String, Response> data = (Map)serviceResponseCache.get();
        if (data == null) {
            data = new HashMap();
        }

        ((Map)data).put(key, response);
        serviceResponseCache.set(data);
    }

    public static Response readResponse(String key) {
        return (Response)((Map)serviceResponseCache.get()).get(key);
    }
}
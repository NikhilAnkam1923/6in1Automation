package com.centrifi.automation.util;

import java.io.UnsupportedEncodingException;

public class CommonUtil {

    public static int getIntFromObject(Object obj) {
        if(obj==null)
            return 0;
        int val = 0;
        try {
            val = Integer.parseInt(obj.toString());
        } catch(Exception ex) {
            //DO Nothing..
        }
        return val;
    }

    public static String getUTF8String(String value) {
        /*try {
            byte[] byteArray = value.getBytes();
            byte[] utf8ByteArray = new String(byteArray, "ISO-8859-1").getBytes("UTF-8");
            value = new String(utf8ByteArray);
        } catch(Exception ex) {
            ex.printStackTrace();
        }*/
        return value;
    }

    public static String getIOSString(String value) {
        try {
            byte[] byteArray = value.getBytes();
            byte[] utf8ByteArray = new String(byteArray, "UTF-8").getBytes("ISO-8859-1");
            value = new String(utf8ByteArray);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public static void main(String[] arg) {
        //System.out.println(getUTF8String("Use 2 atomizaciones (1 mg) en cada fosa nasal a la hora de dormir"));
        //System.out.println(getIOSString("Aplique vÃ\u00ADa tÃ³pica to the affected area segÃºn sea necesario"));

    }

}

package com.centrifi.automation.util;

import java.io.File;

public class FileUtil {

    public static File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        return chosenFile;
    }

    public static File[] getAllFiles(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        return files;
    }

    public static void deleteFile(String directoryFilePath,String fileName){
        File[] files = getAllFiles(directoryFilePath);
        try{
            for (File file:files) {
                if (file.exists() && !file.isDirectory()) {
                    System.out.println(file.getName());
                    if (file.getName().toLowerCase().contains(fileName.toLowerCase())) {
                        file.delete();
                    }
                }
            }
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}

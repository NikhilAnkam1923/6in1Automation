package com.centrifi.automation.util;

import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.glue.CommonSteps;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.ScreenImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class PDFBoxReadText {
    public static String FILEPATH = "./extent-reports/screenshots/generated-patient-report.pdf";
    public static String IMAGE_LOCATION = "./extent-reports/screenshots/";

    public static List<String> readTextFromPdfFile(byte[] data) throws AutomationException {
        List<String> pages = new ArrayList<>();
        String encodedString = new String(data);
        encodedString = encodedString.substring(encodedString.indexOf(",")+1, encodedString.length());

        if(encodedString.startsWith("https")){
            try {
                URL url = new URL(encodedString);
                PDDocument document = PDDocument.load(url.openStream());
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                pages = Collections.singletonList(pdfTextStripper.getText(document));
                document.close();
            }catch(Exception e) {
                throw new AutomationException("Unable to read URL report pdf!\n Exception:"+e.getMessage());
            }
        }
        else {
            File reportFile = createFileFromByteArray(encodedString);
            try (PDDocument doc = PDDocument.load(reportFile)) {
                PDFTextStripper stripper = new PDFTextStripper();
                for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                    stripper.setStartPage(i);
                    stripper.setEndPage(i);
                    String pageText = stripper.getText(doc);
                    pages.add(pageText);
                }
            } catch (Exception e) {
                throw new AutomationException("Unable to read report pdf!\n Exception:" + e.getMessage());
            }
        }
        return pages;
    }

    public static List<File> getAllImagesFromPdf(File reportFile) {
        List<File> images = new ArrayList<>();
        try {
            PDDocument document = PDDocument.load(reportFile);
            PDPage pdfPage = document.getPage(0);
            int i = 1;
            PDResources pdResources = pdfPage.getResources();
            for (COSName c : pdResources.getXObjectNames()) {
                PDXObject o = pdResources.getXObject(c);
                if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                    File file = new File(IMAGE_LOCATION + System.currentTimeMillis() + "-image-in-report.png");
                    i++;
                    ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), "png", file);
                    images.add(file);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return images;
    }

    public static List<File> getALlImagesFromPdf(byte[] data) {
        List<File> images = new ArrayList<>();
        try {
            String encodedString = new String(data);
            encodedString = encodedString.substring(encodedString.indexOf(",")+1, encodedString.length());
            File reportFile = createFileFromByteArray(encodedString);
            PDDocument document = PDDocument.load(reportFile);
            //int totalPage = document.getNumberOfPages();
            PDPage pdfPage = document.getPage(0);
            int i = 1;
            PDResources pdResources = pdfPage.getResources();
            for (COSName c : pdResources.getXObjectNames()) {
                PDXObject o = pdResources.getXObject(c);
                if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                    File file = new File(i + ".png");
                    i++;
                    ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), "png", file);
                    //ITesseract instance = new Tesseract();
                    //String result = instance.doOCR(file);
                    //System.out.println("Image text: "+result);
                    images.add(file);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return images;
    }

    public static File createFileFromByteArray(String encodedString) {
        File file = new File(FILEPATH);
        try {
            file.createNewFile();
            byte[] pdfBytes =null;
            if(encodedString.startsWith("https")) {
                URL pdfUrl = new URL(encodedString);
                InputStream inputStream = pdfUrl.openStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                pdfBytes = outputStream.toByteArray();
            }
            else {
                pdfBytes = Base64.getDecoder().decode(encodedString.getBytes());
            }
            FileOutputStream fos = new FileOutputStream(file.getPath());
            fos.write(pdfBytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return file;
    }



    public static BufferedImage toBufferedImage(File imgFile) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
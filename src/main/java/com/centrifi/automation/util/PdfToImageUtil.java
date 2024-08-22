package com.centrifi.automation.util;



import com.aspose.pdf.Document;
import com.aspose.pdf.devices.ImageDevice;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfToImageUtil {
    private static Path _dataDir = Paths.get("temp/Sample-PDF-verification/VNS-Org");
    public static void main(String[] args) throws IOException {
        Resolution resolution = new Resolution(300);
        JpegDevice jpegDevice = new JpegDevice(resolution);
        Document document = new Document(_dataDir + "/VNS-CL-Report-for-01-Beneficiary.pdf");
        ConvertPDFtoImage(jpegDevice, "jpeg", document);

    }
    public static void ConvertPDFtoImage(ImageDevice imageDevice, String ext, Document pdfDocument) throws IOException {
        for (int pageCount = 1; pageCount <= pdfDocument.getPages().size(); pageCount++) {
            java.io.OutputStream imageStream = new java.io.FileOutputStream(
                    _dataDir + "image" + pageCount + "_out." + ext);
            imageDevice.process(pdfDocument.getPages().get_Item(pageCount), imageStream);

            // Close stream
            imageStream.close();
        }
    }
}

package com.javasans.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class KankotriApplication {
    public void imagesToPdf(String destination, String pdfName, String imagFileSource) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4, 20.0f, 20.0f, 20.0f, 150.0f);
        String desPath = destination;

        File destinationDirectory = new File(desPath);
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
            System.out.println("DESTINATION FOLDER CREATED -> " + destinationDirectory.getAbsolutePath());
        } else if (destinationDirectory.exists()) {
            System.out.println("DESTINATION FOLDER ALREADY CREATED!!!");
        } else {
            System.out.println("DESTINATION FOLDER NOT CREATED!!!");
        }

        File file = new File(destinationDirectory, pdfName + ".pdf");

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        document.open();

        System.out.println("CONVERTER START.....");

        String[] splitImagFiles = imagFileSource.split("@@");

        for (String singleImage : splitImagFiles) {
            Image image = Image.getInstance(singleImage);
            document.setPageSize(image);
            document.newPage();
            image.setAbsolutePosition(0, 0);
            document.add(image);
        }

        document.close();
        System.out.println("CONVERTER STOPTED.....");
    }


    public static void main(String[] args) {

        String page3 = "C:\\kankori\\page3.jpeg";
        String page4 = "C:\\kankori\\page4.jpeg";

        try {
            KankotriApplication converter = new KankotriApplication();
            File file = new File("C:\\kankori\\prepared");
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    System.out.println("file1 = " + file1.getName());
                    File[] files1 = file1.listFiles();
                    String page1 = null;
                    String page2 = null;
                    for (File file2 : files1) {
                        if (file2.getName().startsWith("page1")) {
                            page1 = file2.toPath().toString();
                        } else {
                            page2 = file2.toPath().toString();
                        }
                    }
                    /*output file name*/
                    String name = file1.getName();
                    String destination = "C:\\desti";
                    String sourcePath = page1 + "@@" + page2 + "@@" + page3 + "@@" + page4;
                    converter.imagesToPdf(destination, name, sourcePath);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

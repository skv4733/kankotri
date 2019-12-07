package com.javasans.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagesToPDF {
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


    public static void main(String[] args) throws IOException, DocumentException {


        /*output file name*/
        String name = "riddhi_birth_certificate";
        String destination = "C:\\desti";
        String path1 = "C:\\kankori\\prepared\\2.jpg";
        String path2 = "C:\\kankori\\prepared\\1.jpg";
//        String path3 = "C:\\kankori\\prepared\\3.jpg";
        String sourcePath4 = path1 + "@@" + path2 ;
        new ImagesToPDF().imagesToPdf(destination, name, sourcePath4);

    }

}
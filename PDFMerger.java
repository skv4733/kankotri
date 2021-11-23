package com.javasans.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to merge two or more
 * existing pdf file using iText jar.
 */
public class PDFMerger {

    static void mergePdfFiles(List<InputStream> inputPdfList,
                              OutputStream outputStream) throws Exception {
        //Create document and pdfReader objects.
        Document document = new Document();
        List<PdfReader> readers =new ArrayList<>();
        int totalPages = 0;

        //Create pdf Iterator object using inputPdfList.
        Iterator<InputStream> pdfIterator = inputPdfList.iterator();

        // Create reader list for the input pdf files.
        while (pdfIterator.hasNext()) {
            InputStream pdf = pdfIterator.next();
            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
        }

        // Create writer for the outputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        //Open document.
        document.open();

        //Contain the pdf data.
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();

        // Iterate and process the reader list.
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = iteratorPDFReader.next();
            //Create page and add content.
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
                document.newPage();
                pdfImportedPage = writer.getImportedPage(
                        pdfReader, currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            currentPdfReaderPage = 1;
        }

        //Close document and outputStream.
        outputStream.flush();
        document.close();
        outputStream.close();

        System.out.println("Pdf files merged successfully.");
    }

    public static void main(String args[]) {
        try {
            //Prepare input pdf file list as list of input stream.
            List<InputStream> inputPdfList = new ArrayList<InputStream>();
            inputPdfList.add(new FileInputStream("2/1.pdf"));
            inputPdfList.add(new FileInputStream("2/2.pdf"));
            inputPdfList.add(new FileInputStream("2/3.pdf"));
            inputPdfList.add(new FileInputStream("2/4.pdf"));


            //Prepare output stream for merged pdf file.
            OutputStream outputStream =
                    new FileOutputStream("2_1234.pdf");

            //call method to merge pdf files.
            mergePdfFiles(inputPdfList, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

     <?xml version="1.0" encoding="UTF-8"?>
     <project xmlns="http://maven.apache.org/POM/4.0.0"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
     <modelVersion>4.0.0</modelVersion>
     <groupId>com.javasans</groupId>
     <artifactId>kankotri</artifactId>
     <version>1.0-SNAPSHOT</version>
     <properties>
     <maven.compiler.target>1.8</maven.compiler.target>
     <maven.compiler.source>1.8</maven.compiler.source>
     </properties>
     <dependencies>
     <dependency>
     <groupId>com.itextpdf</groupId>
     <artifactId>itextpdf</artifactId>
     <version>5.5.10</version>
     </dependency>
     </dependencies>
     </project>

     */
}

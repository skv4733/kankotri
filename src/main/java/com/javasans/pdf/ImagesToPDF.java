package com.javasans.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ImagesToPDF {
    public void imagesToPdf(String destination, String pdfName, File[] splitImagFiles) throws IOException, DocumentException {

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

        for (File singleImage : splitImagFiles) {
            Image image = Image.getInstance(singleImage.getAbsolutePath());
            document.setPageSize(image);
            document.newPage();
            image.setAbsolutePosition(0, 0);

            document.add(image);
        }

        document.close();
        System.out.println("CONVERTER STOPTED.....");
    }


    public static void main(String[] args) {
        ImagesToPDF imagesToPDF = new ImagesToPDF();
        imagesToPDF.run();
    }

    private void run() {
        try {

            Path inputFolder = Paths.get("fileTobeMergedWIthSubfolder").toAbsolutePath();
            Path merged = Paths.get("merged").toAbsolutePath();
            FileUtils.validateIfFolderExist(merged);
            File file = inputFolder.toFile();
            if (file.isDirectory()) {
                for (File subDir : file.listFiles()) {
                    if (subDir.isDirectory()) {
                        String collect = Arrays.stream(subDir.list()).collect(Collectors.joining("@@"));
                        imagesToPdf(merged+File.separator, subDir.getName(), subDir.listFiles());
                    }
                }
            }
//
//            /*output file name*/
//            String name = "3e3e33";
//            String destination = "C:\\desti";
//            String path1 = "C:\\kankori\\prepared\\2.jpg";
//            String path2 = "C:\\kankori\\prepared\\1.jpg";
////        String path3 = "C:\\kankori\\prepared\\3.jpg";
//            String sourcePath4 = path1 + "@@" + path2 ;
//            imagesToPdf(destination, name, sourcePath4);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}

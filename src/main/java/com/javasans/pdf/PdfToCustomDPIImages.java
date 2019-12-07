package com.javasans.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToCustomDPIImages {

    public static void main(String[] args) throws Exception {
        PdfToCustomDPIImages main = new PdfToCustomDPIImages();

        File file = new File("C:\\Users\\Super User\\IdeaProjects\\apachePDFbox\\inputfiles");

        if (file.isDirectory()) {

            File[] files = file.listFiles();
            for (File file1 : files) {
                String name = file1.getName();
                String replace1 = file1.getPath().replace(".pdf", "compressed.pdf");
                String replace = name.replace(".pdf", "compressed.pdf");
                System.out.println("name = " + replace);
                main.convertPdftocustomDPIImages(file1.toPath().toString(),replace);
            }
        }

    }

    public void convertPdftocustomDPIImages(String inputfilename, String outputimagefilename) throws IOException {
        String filename = inputfilename;
        String extension = "jpeg";
        PDDocument document = PDDocument.load(new File(filename));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 150, ImageType.RGB);
            ImageIOUtil.writeImage(
                    bim, String.format(outputimagefilename+"pdf-%d.%s", page + 1, extension), 300);
        }
        document.close();
    }


}

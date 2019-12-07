package com.javasans.pdf;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class CompressImages {

    public static void main(String[] args) throws Exception{
           compressImage("2.jpg");
           compressImage("3.jpg");
//           compressImage("riddhi_birth_certificate_gujarati.jpg");
//           compressImage("jite4.jpeg");
//           compressImage("jite5.jpeg");
//           compressImage("jite6.jpeg");
//

//           compressImage("jite6.jpeg");
//           compressImage("jite7.jpeg");
//           compressImage("jite8.jpeg");


// File imageFile = new File("jite6.jpeg");
//        File compressedImageFile = new File("myimage_compressejited.jpeg");
//
//        InputStream is = new FileInputStream(imageFile);
//        OutputStream os = new FileOutputStream(compressedImageFile);
//
//        float quality = 0.5f;
//
//        // create a BufferedImage as the result of decoding the supplied InputStream
//        BufferedImage image = ImageIO.read(is);
//
//        // get all image writers for jpg format
//        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
//
//        if (!writers.hasNext())
//            throw new IllegalStateException("No writers found");
//
//        ImageWriter writer = (ImageWriter) writers.next();
//        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//        writer.setOutput(ios);
//
//        ImageWriteParam param = writer.getDefaultWriteParam();
//
//        // compress to a given quality
//        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        param.setCompressionQuality(quality);
//
//        // appends a complete image stream containing a single image and
//        //associated stream and image metadata and thumbnails to the output
//        writer.write(null, new IIOImage(image, null, null), param);
//
//        // close all streams
//        is.close();
//        os.close();
//        ios.close();
//        writer.dispose();
    }

       public static void compressImage(String filename) throws Exception {
              File imageFile = new File(filename);
              File compressedImageFile = new File(filename+"compressed");

              InputStream is = new FileInputStream(imageFile);
              OutputStream os = new FileOutputStream(compressedImageFile);

              float quality = 0.5f;

              // create a BufferedImage as the result of decoding the supplied InputStream
              BufferedImage image = ImageIO.read(is);

              // get all image writers for jpg format
              Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

              if (!writers.hasNext())
                     throw new IllegalStateException("No writers found");

              ImageWriter writer = (ImageWriter) writers.next();
              ImageOutputStream ios = ImageIO.createImageOutputStream(os);
              writer.setOutput(ios);

              ImageWriteParam param = writer.getDefaultWriteParam();

              // compress to a given quality
              param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
              param.setCompressionQuality(quality);

              // appends a complete image stream containing a single image and
              //associated stream and image metadata and thumbnails to the output
              writer.write(null, new IIOImage(image, null, null), param);

              // close all streams
              is.close();
              os.close();
              ios.close();
              writer.dispose();
       }
}


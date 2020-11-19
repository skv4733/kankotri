package com.javasans.pdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ImageCompresser {
    private static final Logger logger = LoggerFactory.getLogger(ImageCompresser.class);


    public static void main(String[] args) {
        ImageCompresser imageCompresser = new ImageCompresser();
        imageCompresser.startCompressing();

    }

    private void startCompressing() {
        //please provide folder in which you want to compress images from
        Path photos = Paths.get("photos").toAbsolutePath();
        Path compressed = Paths.get("compressed").toAbsolutePath();
        FileUtils.validateIfFolderExist(compressed);


        File inputFoldertoCompressPhotos = photos.toFile();


        //please provide destination folder name in which you want to put your compressed images.
        if (inputFoldertoCompressPhotos.isDirectory()) {
            for (File listFile : inputFoldertoCompressPhotos.listFiles()) {
                try {
                    compressImage(listFile, compressed.toAbsolutePath().toString(), 0.5f);
                } catch (Exception e) {
                    logger.error("can not compress image", e);
                }
            }
        } else {
            logger.error("can not find folder for given path {}", inputFoldertoCompressPhotos.toURI());
        }

    }


//    public static void de(String[] args) throws Exception{
//
//           compressImage("2.jpg");
//           compressImage("3.jpg");
////           compressImage("riddhi_birth_certificate_gujarati.jpg");
////           compressImage("jite4.jpeg");
////           compressImage("jite5.jpeg");
////           compressImage("jite6.jpeg");
////
//
////           compressImage("jite6.jpeg");
////           compressImage("jite7.jpeg");
////           compressImage("jite8.jpeg");
//
//
//// File imageFile = new File("jite6.jpeg");
////        File compressedImageFile = new File("myimage_compressejited.jpeg");
////
////        InputStream is = new FileInputStream(imageFile);
////        OutputStream os = new FileOutputStream(compressedImageFile);
////
////        float quality = 0.5f;
////
////        // create a BufferedImage as the result of decoding the supplied InputStream
////        BufferedImage image = ImageIO.read(is);
////
////        // get all image writers for jpg format
////        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
////
////        if (!writers.hasNext())
////            throw new IllegalStateException("No writers found");
////
////        ImageWriter writer = (ImageWriter) writers.next();
////        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
////        writer.setOutput(ios);
////
////        ImageWriteParam param = writer.getDefaultWriteParam();
////
////        // compress to a given quality
////        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
////        param.setCompressionQuality(quality);
////
////        // appends a complete image stream containing a single image and
////        //associated stream and image metadata and thumbnails to the output
////        writer.write(null, new IIOImage(image, null, null), param);
////
////        // close all streams
////        is.close();
////        os.close();
////        ios.close();
////        writer.dispose();
//    }

    public String compressImage(File sourceFile, String destinationFolder, float quality) throws Exception {
//              File sourceFile = new File(sourceFilePath);
           String fileName = getFileName(sourceFile);
              File compressedImageFile = new File(destinationFolder+File.separator+fileName+"compressed.jpg");

              InputStream is = new FileInputStream(sourceFile);
              OutputStream os = new FileOutputStream(compressedImageFile);

           if (quality == 0.0) {
               quality = 0.5f;
           }

              // create a BufferedImage as the result of decoding the supplied InputStream
              BufferedImage image = ImageIO.read(is);

              // get all image writers for jpg format
              Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

              if (!writers.hasNext())
                     throw new IllegalStateException("No writers found");

              ImageWriter writer = writers.next();
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
              return compressedImageFile.getName();
       }

    /**
     * Pull file name itsefl from whole path
     * @param sourceFile
     * @return
     */
    private String getFileName(final File sourceFile) {
        return sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf('.'));
    }

    public static void cosmpressImage(String filename) throws Exception {
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


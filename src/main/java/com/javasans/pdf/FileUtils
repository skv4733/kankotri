package com.javasans.pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static void validateIfFolderExist(Path compressed) {
        try {
            File file = compressed.toFile();
            if (file.exists() && !file.isDirectory()) {
                file.delete();
                Files.createDirectories(compressed);
            }else if (!file.exists()){
                Files.createDirectories(compressed);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

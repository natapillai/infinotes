package com.csye6220.infinotes.utils;

import java.io.*;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public static void saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        String directoryPath = "C:\\InfinotesImages";
        Path uploadPath = Paths.get(directoryPath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}

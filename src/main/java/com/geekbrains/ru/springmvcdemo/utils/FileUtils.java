package com.geekbrains.ru.springmvcdemo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static Path saveProductImage(MultipartFile file) {
        Path filepath = Path.of(Paths.get(System.getProperty("user.dir"), "data").toFile().getAbsolutePath());
        try {
            file.transferTo(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }

}

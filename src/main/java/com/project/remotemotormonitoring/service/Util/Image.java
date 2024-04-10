package com.project.remotemotormonitoring.service.Util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Image {

    public static byte[] readImage(String path) throws IOException {
        ClassPathResource imgFile = new ClassPathResource(path);
        return Files.readAllBytes(Path.of(path));
    }
}

package com.company.aemss.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    public List<String> getImagesFromBaseDirectory(String baseDirectory) {
        List<String> imageFiles = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(baseDirectory))) {
            imageFiles = walk
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(f -> f.toLowerCase().endsWith(".jpg") ||
                            f.toLowerCase().endsWith(".png") ||
                            f.toLowerCase().endsWith(".jpeg"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFiles;
    }

    // Add logging to help debug
    public void logImagePaths(List<String> paths) {
        System.out.println("Found images:");
        paths.forEach(System.out::println);
    }
}

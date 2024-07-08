package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface MediaFileService {

//    String upload(MultipartFile multipartFile);
    String upload(File file, ImageType type) throws IOException;
    String upload(File file, ImageType type, String productName);
    void downloadFile(String fileName, ImageType type);
}

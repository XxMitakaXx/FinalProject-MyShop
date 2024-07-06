package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;

import java.io.File;

public interface MediaFileService {

//    String upload(MultipartFile multipartFile);
    String upload(File file, ImageType type);
    void downloadFile(String fileName, ImageType type);
}

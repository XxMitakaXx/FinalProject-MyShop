package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface MediaFileService {
    String upload(MultipartFile multipartFile, ImageType type);
    String upload(MultipartFile multipartFile, ImageType type, String productName);
    String downloadFile(String fileName, ImageType type);
    String downloadFile(String fileName, ImageType type, String productName);
    void save(MediaFile mediaFile);
}

package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface MediaFileService {

//    String upload(MultipartFile multipartFile);
    List<String> upload(File file, ImageType type) throws IOException;
    void downloadFile(String fileName, ImageType type);
}

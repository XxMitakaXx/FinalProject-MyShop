package org.example.finalprojectmyshop.mediaFile.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface MediaFileService {

//    String upload(MultipartFile multipartFile);
    String upload(File file);

}

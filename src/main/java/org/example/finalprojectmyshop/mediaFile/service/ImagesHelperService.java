package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagesHelperService {
    MediaFileEntity saveImage(MultipartFile multipartFile) throws IOException;
    void deleteImage(MediaFileEntity mediaFileEntity);
}

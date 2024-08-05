package org.example.finalprojectmyshop.mediaFile.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.CloudinaryService;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImagesHelperServiceImpl implements ImagesHelperService {

    private final MediaFileService mediaFileService;
    private final CloudinaryService cloudinaryService;

    public ImagesHelperServiceImpl(MediaFileService mediaFileService, CloudinaryService cloudinaryService) {
        this.mediaFileService = mediaFileService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public MediaFileEntity saveImage(MultipartFile multipartFile) throws IOException {
        Map savedImageData = this.cloudinaryService.upload(multipartFile);

        MediaFileEntity mediaFile = new MediaFileEntity();
        mediaFile
                .setImageUrl((String) savedImageData.get("url"))
                .setImageId((String) savedImageData.get("public_id"));

        this.mediaFileService.save(mediaFile);

        return mediaFile;
    }

    @Override
    public void deleteImage(MediaFileEntity mediaFileEntity) {
        try {
            this.cloudinaryService.delete(mediaFileEntity.getImageId());
            this.mediaFileService.deleteById(mediaFileEntity.getId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

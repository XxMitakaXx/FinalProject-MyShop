package org.example.finalprojectmyshop.mediaFile.service;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;

import java.util.Optional;

public interface MediaFileService {
    Optional<MediaFileEntity> getMediaFileById(long id);

    void save(MediaFileEntity mediaFile);

    void deleteById(long id);

    boolean existsById(long id);
}

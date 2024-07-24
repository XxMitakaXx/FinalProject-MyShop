package org.example.finalprojectmyshop.mediaFile.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.repository.MediaFileRepository;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private final MediaFileRepository mediaFileRepository;

    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public Optional<MediaFileEntity> getMediaFileById(long id) {
        return this.mediaFileRepository.findById(id);
    }

    @Override
    public void save(MediaFileEntity mediaFile) {
        this.mediaFileRepository.save(mediaFile);
    }

    @Override
    public void deleteById(long id) {
        this.mediaFileRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return this.mediaFileRepository.existsById(id);
    }
}

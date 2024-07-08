package org.example.finalprojectmyshop.mediaFile.repository;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
}

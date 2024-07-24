package org.example.finalprojectmyshop.mediaFile.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "media_files")
public class MediaFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imageUrl;

    private String imageId;

    public MediaFileEntity() {}

    public long getId() {
        return this.id;
    }

    public MediaFileEntity setId(long id) {
        this.id = id;

        return this;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public MediaFileEntity setImageUrl(String url) {
        this.imageUrl = url;

        return this;
    }

    public String getImageId() {
        return this.imageId;
    }

    public MediaFileEntity setImageId(String imageId) {
        this.imageId = imageId;

        return this;
    }
}

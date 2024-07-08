package org.example.finalprojectmyshop.mediaFile.models.entities;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "media_files")
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    public MediaFile() {}

    public long getId() {
        return this.id;
    }

    public MediaFile setId(long id) {
        this.id = id;

        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public MediaFile setUrl(String url) {
        this.url = url;

        return this;
    }
}

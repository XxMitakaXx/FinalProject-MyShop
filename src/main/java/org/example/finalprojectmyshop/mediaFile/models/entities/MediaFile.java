package org.example.finalprojectmyshop.mediaFile.models.entities;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "media_files")
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private File file;

    public MediaFile() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

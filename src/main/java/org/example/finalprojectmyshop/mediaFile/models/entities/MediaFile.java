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
}

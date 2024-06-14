package org.example.finalprojectmyshop.mediaFile.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private final String BUCKER_NAME = "onlineshopfinalprojectapp.appspot.com";
    private final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/Images/o/%s?alt=media";

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKER_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = MediaFileService.class.getClassLoader().getResourceAsStream("onlineshopfinalprojectapp-firebase-adminsdk-2tda6-578fc3bc5d.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public String upload(File file) {
        try {
//            String fileName = multipartFile.getOriginalFilename();
//            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

//            File file = this.convertToFile(multipartFile, fileName);
            String url = this.uploadFile(file, file.getName());
            file.delete();
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }

//    public File fetchFile() {
//
//    }
}

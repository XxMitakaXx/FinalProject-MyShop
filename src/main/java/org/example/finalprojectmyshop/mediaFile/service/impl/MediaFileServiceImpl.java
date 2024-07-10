package org.example.finalprojectmyshop.mediaFile.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.repository.MediaFileRepository;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private final String BUCKER_NAME = "onlineshopfinalprojectapp.appspot.com";
    private final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/Images/o/%s?alt=media";
    private final Storage storage = this.initStorage();

    private final MediaFileRepository mediaFileRepository;

    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository) throws IOException {
        this.mediaFileRepository = mediaFileRepository;
    }


    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKER_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        this.storage.create(blobInfo, Files.readAllBytes(file.toPath()));

//        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        fileName = fileName.split("/")[1];
        return fileName;
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
    public String upload(MultipartFile multipartFile, ImageType type) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);

            String url = this.uploadFile(file, ImageType.USER.getCloudFolderPath() + file.getName());
            file.delete();

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }

    @Override
    public String upload(MultipartFile multipartFile, ImageType type, String productName) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);

            String url = this.uploadFile(file, ImageType.PRODUCT.getCloudFolderPath() + productName + file.getName());
            file.delete();

            return url;
        } catch (IOException e) {
            return "Images couldn't upload, Something went wrong";
        }

    }

    @Override
    public String downloadFile(String fileName, ImageType type) {
        String fileCloudPath = type.getCloudFolderPath().concat(fileName);
        String localFolderPath = type.getLocalFolderPath().concat(fileName);
        Blob blob = this.storage.get(BUCKER_NAME, fileCloudPath);
        blob.downloadTo(Paths.get("C:\\Users\\mitak\\Desktop\\FinalProject-MyShop\\src\\main\\resources\\static\\img\\" + localFolderPath));

        return fileName;
    }

    @Override
    public String downloadFile(String fileName, ImageType type, String productName) {
        String cloudFilePath = type.getCloudFolderPath().concat(fileName);
        String productLocalFolderPath = type.getLocalFolderPath() + fileName.split("/")[0] + "/";
//        String localFolderPath = type.getLocalFolderPath() + productLocalFolderPath + "/";

        Blob blob = this.storage.get(BUCKER_NAME, cloudFilePath);
        blob.downloadTo(Paths.get("C:\\Users\\mitak\\Desktop\\FinalProject-MyShop\\src\\main\\resources\\static\\img\\" + productLocalFolderPath + fileName));

//        String filePath =
        String filePathAndName = String.format("%s/%s", productName, fileName);
        return fileName;
    }

    @Override
    public void save(MediaFile mediaFile) {
        this.mediaFileRepository.save(mediaFile);
    }

    private Storage initStorage() throws IOException {
        InputStream inputStream = MediaFileService.class.getClassLoader().getResourceAsStream("onlineshopfinalprojectapp-firebase-adminsdk-2tda6-578fc3bc5d.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        return storage;
    }
}

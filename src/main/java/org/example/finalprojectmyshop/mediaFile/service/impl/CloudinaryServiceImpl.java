package org.example.finalprojectmyshop.mediaFile.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.finalprojectmyshop.mediaFile.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = this.convert(multipartFile);
        Map result = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

//        if (Files.deleteIfExists(file.toPath())) {
//            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
//        }

        return result;
    }

    @Override
    public Map delete(String id) throws IOException {
        return this.cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return file;
    }
}

package org.example.finalprojectmyshop.mediaFile.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.example.finalprojectmyshop.mediaFile.service.DBInitializeService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class DBInitializeServiceServiceImpl implements DBInitializeService {

    @PostConstruct
    @Override
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Mitaka\\IdeaProjects\\FinalProject-MyShop\\src\\main\\resources\\onlineshopfinalprojectapp-firebase-adminsdk-2tda6-578fc3bc5d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

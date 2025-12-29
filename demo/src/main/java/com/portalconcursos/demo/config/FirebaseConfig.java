package com.portalconcursos.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                        GoogleCredentials.fromStream(
                            new ByteArrayInputStream(firebaseConfig.getBytes())
                        )
                    )
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }
}


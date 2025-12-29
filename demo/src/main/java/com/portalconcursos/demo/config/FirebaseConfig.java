package com.portalconcursos.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {

            InputStream serviceAccount = null;

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");
            if (firebaseConfig != null && !firebaseConfig.isBlank()) {
                serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));
            } else {
                String path = System.getenv("FIREBASE_CONFIG_PATH");
                if (path != null && !path.isBlank()) {
                    serviceAccount = new FileInputStream(path);
                } else {
                    ClassPathResource resource = new ClassPathResource("firebase-key.json");
                    if (resource.exists()) {
                        serviceAccount = resource.getInputStream();
                    }
                }
            }

            if (serviceAccount == null) {
                throw new IllegalStateException("Missing Firebase credentials: set FIREBASE_CONFIG (JSON), FIREBASE_CONFIG_PATH, or add firebase-key.json to resources.");
            }

            try (InputStream is = serviceAccount) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(is))
                        .build();

                FirebaseApp.initializeApp(options);
            }
        }
    }
}
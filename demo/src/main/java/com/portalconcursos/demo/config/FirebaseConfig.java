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

        if (!FirebaseApp.getApps().isEmpty()) {
            return;
        }

        String firebaseConfig = System.getenv("FIREBASE_CONFIG");

        if (firebaseConfig == null || firebaseConfig.isBlank()) {
            throw new RuntimeException("Variável FIREBASE_CONFIG não encontrada");
        }

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

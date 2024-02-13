package com.ktds.FitnessPartner.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FirebaseApp app = null;
        if(FirebaseApp.getApps().isEmpty()) {
            try{
                FileInputStream serviceAccount =
                        new FileInputStream("src/main/resources/serviceAccountKey.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setStorageBucket("fitnesspartner-f70cd.appspot.com")
                        .build();
                app = FirebaseApp.initializeApp(options, "FitnessPartner");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            app = FirebaseApp.getInstance("FitnessPartner");
        }

//        FileInputStream serviceAccount =
//                new FileInputStream("src/main/resources/serviceAccountKey.json");
//
//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setStorageBucket("fitnesspartner-f70cd.appspot.com")
//                .build();
        return app;
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(firebaseApp());
//        return FirebaseAuth.getInstance(app);
    }

    @Bean
    public Bucket bucket() throws IOException {
        if(FirebaseApp.getApps().isEmpty()) {
            return StorageClient.getInstance(firebaseApp()).bucket();
        }
        else {
            return StorageClient.getInstance(FirebaseApp.getInstance("FitnessPartner")).bucket();
        }
//        return StorageClient.getInstance(firebaseApp()).bucket();
//        return StorageClient.getInstance(app).bucket();
    }
}

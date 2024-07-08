package com.example.Fullstack_Backend.service;

import com.example.Fullstack_Backend.config.CloudinaryClient;
import com.example.Fullstack_Backend.controller.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {
    @Autowired
    private CloudinaryClient cloudinaryClient;

    public ResponseEntity<String> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            String url = cloudinaryClient.uploadFile(file.getBytes(),"lokesh1107","lokesh1107-applicationId");
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + url);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}

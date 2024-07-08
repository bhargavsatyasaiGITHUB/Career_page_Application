package com.example.Fullstack_Backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryClient {

    private final Cloudinary cloudinary;
    private final HandlerMapping resourceHandlerMapping;

    @Autowired
    public CloudinaryClient(Cloudinary cloudinary, @Qualifier("resourceHandlerMapping") HandlerMapping resourceHandlerMapping) {
        this.cloudinary = cloudinary;
        this.resourceHandlerMapping = resourceHandlerMapping;
    }

    public String uploadFile(String filePath,String userId,String publicId) throws IOException {
        File file = new File(filePath);
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "resource_type", "raw",
                "folder", String.format("user_pdfs/%s", userId),
                "public_id", publicId // Set the custom public_id
        ));
        return uploadResult.get("url").toString();
    }

    public String uploadFile(byte[] fileBytes, String userId, String publicId) throws IOException {
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "resource_type", "raw",
                "folder", String.format("user_pdfs/%s", userId), // Specify the folder path
                "public_id", publicId // Set the custom public_id
        );

        Map<String, Object> uploadResult = cloudinary.uploader().upload(fileBytes, uploadParams);
        return (String) uploadResult.get("url");
    }

}
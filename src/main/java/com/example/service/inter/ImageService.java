package com.example.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public String uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadImage(String fileName);
}

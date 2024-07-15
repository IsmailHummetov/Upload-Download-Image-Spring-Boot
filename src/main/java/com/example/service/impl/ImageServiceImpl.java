package com.example.service.impl;

import com.example.model.Image;
import com.example.repository.ImageRepository;
import com.example.service.inter.ImageService;
import com.example.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Image image = imageRepository.save(
                Image
                        .builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .build());
        if (image!=null)
            return "File uploaded  successfully" + file.getOriginalFilename();
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImage = imageRepository.findByName(fileName);
        byte[] image = ImageUtils.decompressImage(dbImage.get().getImageData());
        return image;
    }
}

package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String newFileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath = path+ File.separator + newFileName;

        File folder = new File(path+File.separator);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filePath;
    }
}

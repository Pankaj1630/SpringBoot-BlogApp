package com.example.BlogApplication.ServiceImpl;

import com.example.BlogApplication.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileIndex = randomId.concat(filename.substring(filename.lastIndexOf(".")));
        String filePath = path + File.separator + fileIndex;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileIndex;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream fileInputStream = new FileInputStream(fullPath);
        return fileInputStream;
    }
}

package com.example.cinemamanagement.utils;

import com.example.cinemamanagement.exceptions.UploadFileException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${upload-path}")
    private String uploadDirectory;

    public String generateUniqueFileName(MultipartFile file){
        // Lấy ra file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Tạo ra tên file duy nhất
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    public String createFile(MultipartFile file) {
        if(file.getSize() > 10 *1024 *1024) // Kích thước lớn hơn 10mb
            throw new UploadFileException("File is too large! Maximum size is 10MB");
        String contenType = file.getContentType();
        if(contenType == null || !contenType.startsWith("image/")) // Không phải file ảnh
            throw new UploadFileException("File must be an image");
        String uniqueFileName = generateUniqueFileName(file);
        try{
            Path uploadDir = Paths.get(uploadDirectory);
            // Kiểm tra và tạo thư mục nếu nó không tồn tại
            if(!Files.exists(uploadDir)){
                Files.createDirectories(uploadDir);
            }
            // Đường dẫn đầy đủ đến file
            Path destination = Paths.get(uploadDir.toString(),uniqueFileName);
            // Sao chép file vào thư mục đích
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        }
        catch(Exception e){
            throw new UploadFileException("Upload file failed");
        }
    }

    public String updateFile(MultipartFile fileNew, String fileOld){
        if(fileNew.getSize() > 10 *1024 *1024) // Kích thước lớn hơn 10mb
            throw new UploadFileException("File is too large! Maximum size is 10MB");
        String contenType = fileNew.getContentType();
        if(contenType == null || !contenType.startsWith("image/")) // Không phải file ảnh
            throw new UploadFileException("File must be an image");
        String uniqueFileName = generateUniqueFileName(fileNew);
        try{
            Path uploadDir = Paths.get(uploadDirectory);

            // Copy file mới vào đường dẫn file cũ
            Path pathOld = Paths.get(uploadDir.toString(),fileOld);
            Files.copy(fileNew.getInputStream(), pathOld, StandardCopyOption.REPLACE_EXISTING);

            // Di chuyển đường dẫn file cũ vào đường dẫn mới
            Path pathNew = Paths.get(uploadDir.toString(), uniqueFileName);
            Files.move(pathOld, pathNew, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        }
        catch(Exception e){
            throw new UploadFileException("Update file failed");
        }
    }
}

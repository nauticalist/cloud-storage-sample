package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.FileDto;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<FileDto> getAllFiles(String username) throws Exception;

    File getFileByFileId(Integer fileId) throws Exception;

    void createFile(MultipartFile fileUpload, String username) throws Exception;

    boolean deleteFile(Integer fileId) throws Exception;

    boolean isFileExists(String fileName);
}

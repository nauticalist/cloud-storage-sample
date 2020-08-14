package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.FileDto;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;
    private final UserService userService;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    @Override
    public List<FileDto> getAllFiles(String username) throws Exception{
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);

        List<File> files = fileMapper.findAllByUserId(user.getUserId());
        if (files == null) {
            throw new Exception("Unable to find any files");
        }
        return files.stream().map(this::mapFileToFileDto).collect(Collectors.toList());
    }

    @Override
    public File getFileByFileId(Integer fileId) throws Exception {
        File file =  fileMapper.findByFileId(fileId);

        if (file == null) {
            throw new Exception("Unable to find file with id:  " + fileId);
        }

        if (!this.verifyOwner(file.getUserId())) {
            throw new Exception("Unauthorized access to file detected: " + fileId);
        }

        return file;

    }

    @Override
    public void createFile(MultipartFile fileUpload, String username) throws Exception {
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);

        File file = new File();
        file.setFileName(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        file.setFileSize(Long.toString(fileUpload.getSize()));
        file.setUserId(user.getUserId());
        file.setFileData(fileUpload.getBytes());

        fileMapper.create(file);
    }

    @Override
    public boolean deleteFile(Integer fileId) throws Exception{
        File file = fileMapper.findByFileId(fileId);

        if ( file == null) {
            throw new Exception("Unable to find file with id:  " + fileId);
        }

        if (!this.verifyOwner(file.getUserId())) {
            throw new Exception("Unauthorized access to file detected: " + fileId);
        }

        return fileMapper.delete(fileId);
    }

    @Override
    public boolean isFileExists(String fileName) {
        return fileMapper.findByFileName(fileName) == null;
    }



    private FileDto mapFileToFileDto(File file){
        String base64 = Base64.getEncoder().encodeToString(file.getFileData());
        String dataURL = "data:" + file.getContentType() + ";base64," + base64;

        return new FileDto(file.getFileId(), file.getFileName(), dataURL);
    }

    private boolean verifyOwner(int userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        User user = userService.getUser(username);

        return (user.getUserId().equals(userId));
    }

}

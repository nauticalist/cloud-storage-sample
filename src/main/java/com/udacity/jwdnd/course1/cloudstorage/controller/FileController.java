package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;

@Controller
@RequestMapping(value = "/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/download/{fileId}")
    public ResponseEntity<InputStreamResource> download(@PathVariable int fileId) {
        File file;

        try {
            file = fileService.getFileByFileId(fileId);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get file: " + e.getMessage());
        }

        InputStream fileStream = new ByteArrayInputStream(file.getFileData());
        InputStreamResource fileResource = new InputStreamResource(fileStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(fileResource);
    }


    @PostMapping(value = "/upload")
    public String createNewFile(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal) {
        String errorMessage = null;

        String username = principal.getName();

        if (file.isEmpty())
            errorMessage = "Error: File is not valid.";

        if (!fileService.isFileExists(file.getOriginalFilename()))
            errorMessage = "Error: File already exists!";

        if (errorMessage == null) {
            try {
                fileService.createFile(file, username);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }

        if (errorMessage != null)
            model.addAttribute("errorMessage", errorMessage);
        else
            model.addAttribute("successMessage", "Your file were successfully save. Click <a id=\"success-link\" href=\"/#nav-files\">here</a> to continue.");
        return "result";
    }

    @GetMapping(value = "/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId, Model model) {
        String errorMessage = null;

        try {
            fileService.deleteFile(fileId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        if(errorMessage == null)
            model.addAttribute("successMessage", "Your file were successfully deleted. Click <a id=\"success-link\" href=\"/#nav-files\">here</a> to continue.");
        else
            model.addAttribute("errorMessage",errorMessage);

        return "result";
    }
}

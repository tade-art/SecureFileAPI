package com.tade.secure_file_api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.service.FileService;
import com.tade.secure_file_api.model.File;

import lombok.RequiredArgsConstructor;

/*
* Controller for handling file-related endpoints
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    // Endpoint for file upload
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        // Get authenticated user from security context
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        fileService.saveFile(file, user);

        return ResponseEntity.ok("File uploaded successfully");
    }

    // Endpoint for file deletion
    @DeleteMapping("/delete/{id}")
    public boolean deleteFile(@PathVariable Long id) { return false;}

    // Endpoint for file download by id - change return type
    @GetMapping("/files/{id}")
    public void downloadFileById(@PathVariable Long id) {}

    // Endpoint for downloading all user files - change return type
    @GetMapping
    public ResponseEntity<List<File>> downloadFiles() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println("User ID: " + user.getId());
        List<File> files = fileService.getAllFilesForUser(user.getId());
        //System.out.println(files);
        return ResponseEntity.ok(files);
    }

}

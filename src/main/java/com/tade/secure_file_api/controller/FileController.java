package com.tade.secure_file_api.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
    public ResponseEntity<?> deleteFile(@PathVariable Long id) throws IOException { 
        boolean deleted = fileService.deleteFileById(id);
        
        if (deleted)
            return ResponseEntity.ok(deleted);
        else            
            return ResponseEntity.status(500).body("Failed to delete file");
    }


    // Endpoint for file download by id - change return type
    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable Long id) throws MalformedURLException {
        File file = fileService.getFileById(id);
        Path path = Paths.get(file.getFilepath());
        Resource resource = new UrlResource(path.toUri());
   
        return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\"")
        .header("Content-Type", "application/octet-stream")
        .body(resource);
    }

    // Endpoint for downloading all user files - change return type
    @GetMapping
    public ResponseEntity<List<File>> downloadFiles() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<File> files = fileService.getAllFilesForUser(user.getId());

        return ResponseEntity.ok(files);
    }

}

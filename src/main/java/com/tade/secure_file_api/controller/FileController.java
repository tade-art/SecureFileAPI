package com.tade.secure_file_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/*
* Controller for handling file-related endpoints
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    // Endpoint for file upload
    @PostMapping("/upload")
    public void uploadFile() {}

    // Endpoint for file deletion
    @DeleteMapping("/delete/{id}")
    public boolean deleteFile(@PathVariable Long id) { return false;}

    // Endpoint for file download by id - change return type
    @GetMapping("/download/{id}")
    public void downloadFileById(@PathVariable Long id) {}

    // Endpoint for downloading all user files - change return type
    @GetMapping("/download")
    public void downloadFiles() {}

}

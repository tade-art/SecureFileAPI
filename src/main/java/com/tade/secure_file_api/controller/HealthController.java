package com.tade.secure_file_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tade.secure_file_api.model.File;
import com.tade.secure_file_api.repository.FileRepository;

import lombok.RequiredArgsConstructor;

/*
* Basic health check endpoint to verify API is running - not to be used in prod
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthController {

    private final FileRepository fileRepository;

    @GetMapping("/health")
    public String healthCheck() {
        return "API is running";
    }

        // Test to verify JWT auth is working
    @GetMapping("/test")
    public String test() {
        return "Secure endpoint works";
    }

    @GetMapping("/debug/files")
    public List<File> debugFiles() {
        return fileRepository.findAll();
}
}
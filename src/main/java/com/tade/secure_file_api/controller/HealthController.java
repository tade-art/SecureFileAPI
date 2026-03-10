package com.tade.secure_file_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Basic health check endpoint to verify API is running - not to be used in prod
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "API is running";
    }

        // Test to verify JWT auth is working
    @GetMapping("/test")
    public String test() {
        return "Secure endpoint works";
    }
}
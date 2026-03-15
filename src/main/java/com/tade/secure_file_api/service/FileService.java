package com.tade.secure_file_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tade.secure_file_api.model.File;
import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.repository.FileRepository;

import lombok.RequiredArgsConstructor;

/*
* Service class for handling file operations
*/

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final String fileStorageLocation = "uploads";

    //Method to save an uploaded file to the database + disk
    public File saveFile(MultipartFile file, User user) throws IOException {
        //Create file name
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        //Find or create upload directory
        Path uploadDir = Paths.get(fileStorageLocation);

        //Create directory if it doesn't exist
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        //Save file to disk
        Path filePath = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        //Create and save file metadata to DB
        File fileToReturn = new File();
        fileToReturn.setFilename(filename);
        fileToReturn.setOwner(user);
        fileToReturn.setFilepath(filePath.toString());
        fileToReturn.setUploadDate(java.time.LocalDate.now());

        return fileRepository.save(fileToReturn);
    }  

    // Method to get a file by its ID
    public File getFileById(Long id){
        File file = fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
        return file;
    }

    //Method to grab all files related to a user
    public List<File> getAllFilesForUser(Long id){
        return fileRepository.findByOwnerId(id);
    }

    // Method to delete a file by its ID
    public void deleteFileById(Long id, User user) throws IOException{
        File file = fileRepository.findById(id).orElseThrow(null);

        if(!file.getOwner().getId().equals(user.getId()))
            throw new RuntimeException("Illegal Attempt");

        Path path = Paths.get(file.getFilepath());
        Files.deleteIfExists(path);
        fileRepository.deleteById(id);        
    }
}

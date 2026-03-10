package com.tade.secure_file_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
* Data model for file metadata
*/

@Entity
@Data
@Table(name = "files")
public class FileObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false)
    private String filename;
    
    @Column(nullable = false)
    private String filepath;
    
    @Column(nullable = false)
    private User owner;
    
    private LocalDate uploadDate;
}

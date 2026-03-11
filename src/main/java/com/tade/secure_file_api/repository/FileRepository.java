package com.tade.secure_file_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tade.secure_file_api.model.File;
import com.tade.secure_file_api.model.User;

/*
* Repo interface for file ops
*/
public interface FileRepository extends JpaRepository<File, Long> {

    public List<File> findByOwner(User user);
    public Optional<File> findById(Long id);
    
}

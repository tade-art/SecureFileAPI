package com.tade.secure_file_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tade.secure_file_api.model.File;

/*
* Repo interface for file ops
*/
public interface FileRepository extends JpaRepository<File, Long> {

    public List<File> findByOwnerId(Long id);
}

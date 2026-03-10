package com.tade.secure_file_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tade.secure_file_api.model.FileObj;
import com.tade.secure_file_api.model.User;

/*
* Repo interface for file ops
*/
public interface FileRepository extends JpaRepository<FileObj, Long> {

    public void delete();
    public FileObj findByUser(User user);
    public FileObj findById();
    public void save();
    
}

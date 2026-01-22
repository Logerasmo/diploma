package com.example.diploma.repository;

import com.example.diploma.domenClasses.FileClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RepositoryFiles extends JpaRepository<FileClass, String> {


//    public boolean editFile(String filename, String filenameToChange){
//        if (files.containsKey(filename) && !files.containsKey(filenameToChange)){
//            File temp_file = files.get(filename);
//            files.remove(filename);
//            temp_file.setFilename(filenameToChange);
//            files.put(filenameToChange, temp_file);
//
//            return true;
//        }
//        return false;
//    }
//
//    public Map<String, File> getFilesList(){
//        return files;
//    }
}

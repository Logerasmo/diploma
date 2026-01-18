package com.example.diploma.repository;

import com.example.diploma.service.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class RepositoryFiles {
    Map<String, File> files = new HashMap<>();

    public RepositoryFiles(){
        files.put("example.txt", new File("examplee", "example.txt"));
    }
    public boolean addFile(File file){
        if (!files.containsKey(file.getFilename())){
            files.put(file.getFilename(), file);
            return true;
        }
        return false;
    }

    public boolean deleteFile(String filename){
        if (files.containsKey(filename)){
            files.remove(filename);
            return true;
        }
        return false;
    }

    public boolean editFile(String filename, String filenameToChange){
        if (files.containsKey(filename) && !files.containsKey(filenameToChange)){
            File temp_file = files.get(filename);
            files.remove(filename);
            temp_file.setFilename(filenameToChange);
            files.put(filenameToChange, temp_file);

            return true;
        }
        return false;
    }

    public Optional<File> getFile(String filename){
        if (files.containsKey(filename)){
            return Optional.of(files.get(filename));
        }
        return Optional.empty();
    }
    public Map<String, File> getFilesList(){
        return files;
    }
}

package com.example.diploma.service;

import com.example.diploma.domenClasses.*;
import com.example.diploma.dto.FileNameAndSize;
import com.example.diploma.repository.RepositoryFiles;

import java.util.List;


public class FileService {
    RepositoryFiles repositoryFiles;
    AuthorityService authorityService;

    public FileService(RepositoryFiles repositoryFiles, AuthorityService authorityService) {
        this.repositoryFiles = repositoryFiles;
        this.authorityService = authorityService;
    }

    public boolean uploadFile(FileClass fileClass) {
        if (!repositoryFiles.existsById(fileClass.getFilename())){
            repositoryFiles.save(fileClass);
            return true;
        }
        return false;
    }

    public boolean editFile(String filename, String filenameToChange) {
        if (repositoryFiles.existsById(filename)){
            var file_opt = repositoryFiles.findById(filename);
            if (file_opt.isPresent()){
                FileClass fileClass = file_opt.get();
                fileClass.setFilename(filenameToChange);
                repositoryFiles.deleteById(filename);
                repositoryFiles.save(fileClass);
            }

            return true;
        }
        return false;
    }

    public boolean deleteFile(String filename) {
        if (repositoryFiles.existsById(filename)){
            repositoryFiles.deleteById(filename);
            return true;
        }
        return false;
    }

    public FileClass getFile(String filename) {
        return repositoryFiles.findById(filename).orElse(null);
    }

    public List<FileNameAndSize> getFilesList(Integer limit) {
        return repositoryFiles.
                findAll().
                stream().
                limit(limit).
                map(FileNameAndSize::new).
                toList();
    }
}

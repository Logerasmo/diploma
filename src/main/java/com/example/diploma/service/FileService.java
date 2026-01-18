package com.example.diploma.service;

import com.example.diploma.repository.FileNameAndSize;
import com.example.diploma.repository.RepositoryFiles;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;


public class FileService {
    RepositoryFiles repositoryFiles;
    AuthorityService authorityService;

    public FileService(RepositoryFiles repositoryFiles, AuthorityService authorityService) {
        this.repositoryFiles = repositoryFiles;
        this.authorityService = authorityService;
    }

    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<String> uploadFile(Login login, File file) {
        if (authorityService.checkToken(login)) {
            if (repositoryFiles.addFile(file)) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Error input data", 400)),
                    HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }

    public ResponseEntity<String> editFile(Login login, String filename, String filenameToChange) {
        if (authorityService.checkToken(login)) {
            if (repositoryFiles.editFile(filename, filenameToChange)) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Error upload file", 500)),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }

    public ResponseEntity<String> deleteFile(Login login, String filename) {
        if (authorityService.checkToken(login)) {
            if (repositoryFiles.deleteFile(filename)) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Error delete file", 500)),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }

    public ResponseEntity<String> getFile(Login login, String filename) {
        if (authorityService.checkToken(login)) {
            if (repositoryFiles.getFile(filename).isPresent()) {
                return new ResponseEntity<>(
                        mapper.writeValueAsString(repositoryFiles.getFile(filename).get()),
                        HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Error upload file", 500)),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }

    public ResponseEntity<String> getFilesList(Login login, Integer limit) {
        if (authorityService.checkToken(login)) {
            return new ResponseEntity<>(
                    mapper.writeValueAsString(repositoryFiles.getFilesList().values().
                            stream().limit(limit).map(FileNameAndSize::new).toList()),
                    HttpStatusCode.valueOf(200));

        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }
}

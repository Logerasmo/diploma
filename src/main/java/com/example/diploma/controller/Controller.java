package com.example.diploma.controller;

import com.example.diploma.domenClasses.Error;
import com.example.diploma.dto.FilenameDTO;
import com.example.diploma.dto.LoginDTO;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.domenClasses.FileClass;
import com.example.diploma.service.FileService;
import com.example.diploma.domenClasses.Login;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class Controller {
    AuthorityService authorityService;
    FileService fileService;


    public Controller(AuthorityService authorityService, FileService fileService) {
        this.authorityService = authorityService;
        this.fileService = fileService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Login login = authorityService.login(loginDTO.getLogin(), loginDTO.getPassword());
        if (login != null){
            return new ResponseEntity<>(login, HttpStatusCode.valueOf(200));

        }
        return new ResponseEntity<>(new Error("Bad credentials", 400),
                HttpStatusCode.valueOf(400));
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token){
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("file")
    public ResponseEntity<?> fileUpload(@RequestHeader("auth-token") String token,
                                        @RequestParam("filename") String filename,
                                        @RequestPart("file") String file) {

        if (authorityService.checkToken(tokenToLogin(token))) {
            if (fileService.uploadFile(new FileClass(file, filename))) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }

            return new ResponseEntity<>(new Error("Error input data", 400),
                    HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(new Error("Unauthorized error", 401),
                HttpStatusCode.valueOf(401));

    }
    @DeleteMapping("file")
    public ResponseEntity<?> fileDelete(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename){
        if (authorityService.checkToken(tokenToLogin(token))) {
            if (fileService.deleteFile(filename)) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }

            return new ResponseEntity<>(new Error("Error delete file", 500),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(new Error("Unauthorized error", 401),
                HttpStatusCode.valueOf(401));
    }
    @GetMapping("file")
    public ResponseEntity<?> fileDownload(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename){
        if (authorityService.checkToken(tokenToLogin(token))) {
            var file = fileService.getFile(filename);
            if (file != null) {
                return new ResponseEntity<>(file.getFile_content(), HttpStatusCode.valueOf(200));
            }

            return new ResponseEntity<>(new Error("Error upload file", 500),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(new Error("Unauthorized error", 401),
                HttpStatusCode.valueOf(401));
    }



    @PutMapping("file")
    public ResponseEntity<?> fileEdit(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename,
                                             @RequestBody FilenameDTO filenameDTO){
        if (authorityService.checkToken(tokenToLogin(token))) {
            if (fileService.editFile(filename, filenameDTO.getFilename())) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(new Error("Error input data", 500),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(new Error("Unauthorized error", 401),
                HttpStatusCode.valueOf(401));
    }

    @GetMapping("list")
    public ResponseEntity<?> list(@RequestHeader("auth-token") String token,
                                       @RequestParam("limit") Integer limit){
        if (authorityService.checkToken(tokenToLogin(token))) {
            var list = fileService.getFilesList(limit);
            if (!list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(new Error("Error input data", 500),
                    HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(new Error("Unauthorized error", 401),
                HttpStatusCode.valueOf(401));

    }

    private Login tokenToLogin(String token){
        return new Login(token.split(" ")[1]);
    }
}

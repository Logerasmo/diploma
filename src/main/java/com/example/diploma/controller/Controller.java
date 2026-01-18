package com.example.diploma.controller;

import com.example.diploma.RequestBodyGetters.RequestBodyGetterFilename;
import com.example.diploma.RequestBodyGetters.RequestBodyGetterLogin;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.service.File;
import com.example.diploma.service.FileService;
import com.example.diploma.service.Login;
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
    public ResponseEntity<String> login(@RequestBody RequestBodyGetterLogin req){
        return authorityService.login(req.getLogin(), req.getPassword());
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") String token){
        return authorityService.logout(tokenToLogin(token));
    }

    @PostMapping("file")
    public ResponseEntity<String> fileUpload(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename,
                                             @RequestPart("file") String file){
        return fileService.uploadFile(tokenToLogin(token), new File(file, filename));
    }
    @DeleteMapping("file")
    public ResponseEntity<String> fileDelete(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename){
        return fileService.deleteFile(tokenToLogin(token), filename);
    }
    @GetMapping("file")
    public ResponseEntity<String> fileDownload(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename){
        return fileService.getFile(tokenToLogin(token), filename);
    }



    @PutMapping("file")
    public ResponseEntity<String> fileEdit(@RequestHeader("auth-token") String token,
                                             @RequestParam("filename") String filename,
                                             @RequestBody RequestBodyGetterFilename filenameToChange){
        return fileService.editFile(tokenToLogin(token), filename, filenameToChange.getFilename());
    }

    @GetMapping("list")
    public ResponseEntity<String> list(@RequestHeader("auth-token") String token,
                                       @RequestParam("limit") Integer limit){
        return fileService.getFilesList(tokenToLogin(token), limit);

    }

    private Login tokenToLogin(String token){
        return new Login(token.split(" ")[1]);
    }
}

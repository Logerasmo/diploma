package com.example.diploma.config;

import com.example.diploma.controller.Controller;
import com.example.diploma.repository.RepositoryFiles;
import com.example.diploma.repository.RepositoryUsers;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public AuthorityService authorityService(RepositoryUsers repositoryUsers){
        return new AuthorityService(repositoryUsers);
    }

    @Bean
    public FileService fileService(RepositoryFiles repositoryFiles, AuthorityService authorityService){
        return new FileService(repositoryFiles, authorityService);
    }

    @Bean
    public Controller controller(AuthorityService authorityService, FileService fileService){
        return new Controller(authorityService, fileService);
    }
}

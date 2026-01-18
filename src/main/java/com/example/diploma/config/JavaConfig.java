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
    public RepositoryUsers repositoryUsers(){
        return new RepositoryUsers();
    }

    @Bean
    public AuthorityService authorityService(){
        return new AuthorityService(repositoryUsers());
    }

    @Bean
    public RepositoryFiles repositoryFiles(){
        return new RepositoryFiles();
    }
    @Bean
    public FileService fileService(){
        return new FileService(repositoryFiles(), authorityService());
    }

    @Bean
    public Controller controller(){
        return new Controller(authorityService(), fileService());
    }
}

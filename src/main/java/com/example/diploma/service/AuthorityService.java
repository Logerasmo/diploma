package com.example.diploma.service;

import com.example.diploma.repository.RepositoryUsers;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;

public class AuthorityService {
    RepositoryUsers repositoryUsers;
    tools.jackson.databind.ObjectMapper mapper = new ObjectMapper();

    public AuthorityService(RepositoryUsers repositoryUsers) {
        this.repositoryUsers = repositoryUsers;
    }

    public ResponseEntity<String> login(String login, String password) {
        if (repositoryUsers.getUser(login).isEmpty()) {
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Bad credentials", 400)),
                    HttpStatusCode.valueOf(400));
        }
        User user = repositoryUsers.getUser(login).get();
        var login_opt = user.login(login, password);
        if (login_opt.isEmpty()) {
            return new ResponseEntity<>(mapper.writeValueAsString(
                    new Error("Bad credentials", 400)),
                    HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(mapper.writeValueAsString(
                login_opt.get()),
                HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<String> logout(Login login) {
        var user_opt = repositoryUsers.getUser(login);
        if (user_opt.isPresent()) {
            user_opt.get().logout();
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>(mapper.writeValueAsString(
                new Error("Unauthorized error", 401)),
                HttpStatusCode.valueOf(401));
    }
    public boolean checkToken(Login login){
        return repositoryUsers.getUser(login).isPresent();
    }
}


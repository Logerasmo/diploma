package com.example.diploma.service;

import java.util.Optional;
import java.util.UUID;

public class User {
    private final Login loginToken;
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;

        loginToken = new Login(UUID.nameUUIDFromBytes((login + password).getBytes()).toString());
    }
    public Optional<Login> login(String login, String password){
       if (this.login.equals(login) && this.password.equals(password)){
           return Optional.of(loginToken);
       }
       return Optional.empty();
    }
    public boolean checkToken(Login loginToken){
        return this.loginToken.equals(loginToken);
    }
    public void logout(){
        loginToken.setAuthToken(UUID.nameUUIDFromBytes((login + password).getBytes()).toString());
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Login getLoginToken() {
        return loginToken;
    }
}

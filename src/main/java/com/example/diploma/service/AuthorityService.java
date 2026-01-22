package com.example.diploma.service;

import com.example.diploma.domenClasses.Login;
import com.example.diploma.repository.RepositoryUsers;
import com.example.diploma.repository.User;

public class AuthorityService {
    RepositoryUsers repositoryUsers;

    public AuthorityService(RepositoryUsers repositoryUsers) {
        this.repositoryUsers = repositoryUsers;
    }

    public Login login(String login, String password) {
        if (repositoryUsers.findById(login).isEmpty()) {
            return null;
        }
        User user = repositoryUsers.findById(login).get();
        var login_opt = user.login(login, password);
        return login_opt.orElse(null);
    }

    public void logout(Login login) {
        var user_opt = repositoryUsers.findByLogin(login);
        user_opt.ifPresent(User::logout);
    }
    public boolean addUser(User user){
        if (!repositoryUsers.existsById(user.getLogin())){
            repositoryUsers.save(user);
            return true;
        }
        return false;
    }
    public boolean deleteUser(User user){
        if (repositoryUsers.existsById(user.getLogin())){
            repositoryUsers.deleteById(user.getLogin());
            return true;
        }
        return false;
    }
    public boolean checkToken(Login login){
        return repositoryUsers.findByLogin(login).isPresent();
    }
}


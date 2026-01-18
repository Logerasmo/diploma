package com.example.diploma.repository;

import com.example.diploma.service.Login;
import com.example.diploma.service.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class RepositoryUsers {
    Map<String, User> users = new HashMap<>();
    public RepositoryUsers(){
        users.put("admin", new User("admin", "admin"));
    }
    public boolean addUser(User user) {
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        return users.remove(user.getLogin()) != null;
    }

    public Optional<User> getUser(String login) {
        if (users.containsKey(login)) {
            return Optional.of(users.get(login));
        }
        return Optional.empty();
    }
    public Optional<User> getUser(Login login) {
        for (String key : users.keySet()) {
            if (users.get(key).checkToken(login)){
                return Optional.of(users.get(key));
            }
        }
        return Optional.empty();
    }
}

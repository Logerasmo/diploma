package com.example.diploma.repository;

import com.example.diploma.domenClasses.Login;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RepositoryUsers extends JpaRepository<User, String> {
    public Optional<User> findByLogin(Login login);
}

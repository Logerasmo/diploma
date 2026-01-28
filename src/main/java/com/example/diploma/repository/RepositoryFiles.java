package com.example.diploma.repository;

import com.example.diploma.domenClasses.FileClass;
import org.springframework.data.jpa.repository.JpaRepository;


@org.springframework.stereotype.Repository
public interface RepositoryFiles extends JpaRepository<FileClass, String> {
}

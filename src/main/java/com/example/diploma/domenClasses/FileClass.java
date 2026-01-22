package com.example.diploma.domenClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class FileClass {

    @JsonIgnore
    @Column(nullable = false)
    private String file;

    @Id
    @JsonIgnore
    @Column(nullable = false)
    private String filename;

    @JsonProperty("file")
    @Column(nullable = false)
    private byte[] file_content;

    @Column(nullable = false)
    private String hash;

    public FileClass() {
    }

    public FileClass(String file, String filename) {
        this.hash = String.valueOf(file.hashCode());
        this.file = file;
        this.filename = filename;
        this.file_content = file.getBytes();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFile_content() {
        return file_content;
    }

    public void setFile_content(byte[] file_content) {
        this.file_content = file_content;
    }
}

package com.example.diploma.dto;

public class FilenameDTO {
    private String filename;

    public FilenameDTO(String filename) {
        this.filename = filename;
    }
    public FilenameDTO(){}

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

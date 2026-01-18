package com.example.diploma.repository;

import com.example.diploma.service.File;

public class FileNameAndSize {
    private String filename;
    private Integer size;

    public FileNameAndSize(File file){
        this.filename = file.getFilename();
        this.size = file.getFile().getBytes().length;
    }

    public FileNameAndSize(String filename, Integer size) {
        this.filename = filename;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

package com.example.diploma.dto;

import com.example.diploma.domenClasses.FileClass;

public class FileNameAndSize {
    private String filename;
    private Integer size;

    public FileNameAndSize(FileClass fileclass){
        this.filename = fileclass.getFilename();
        this.size = fileclass.getFile().getBytes().length;
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

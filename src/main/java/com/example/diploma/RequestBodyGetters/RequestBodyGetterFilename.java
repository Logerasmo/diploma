package com.example.diploma.RequestBodyGetters;

public class RequestBodyGetterFilename {
    private String filename;

    public RequestBodyGetterFilename(String filename) {
        this.filename = filename;
    }
    public RequestBodyGetterFilename(){}

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

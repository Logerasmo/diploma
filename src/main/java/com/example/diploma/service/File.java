package com.example.diploma.service;
public class File {
    private String hash;
    private String file;
    private String filename;
    public File(){};

    public File(String file, String filename) {
        this.hash = String.valueOf(file.hashCode());
        this.file = file;
        this.filename = filename;
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
}

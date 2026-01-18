package com.example.diploma.service;

public class Error {
    private String message;
    private Integer id;

    public Error(String message, Integer id) {
        this.message = message;
        this.id = id;
    }

    public Error() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

package com.example.diploma.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Login {
    @JsonProperty("auth-token")
    private String authToken;

    public Login(){}

    public Login(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(authToken, login.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authToken);
    }
}

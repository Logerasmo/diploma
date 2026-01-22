package com.example.diploma.domenClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
@Embeddable
public class Login {
    @JsonProperty("auth-token")
    private String authToken;

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

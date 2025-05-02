package com.joao.pivatto.apirest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateUserDTO {

    private String login;

    private String password;

    private String name;

    private List<String> roles; // Ex: ["ROLE_USER", "ROLE_ADMIN"]

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

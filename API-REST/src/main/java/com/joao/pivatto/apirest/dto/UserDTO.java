package com.joao.pivatto.apirest.dto;

import com.joao.pivatto.apirest.model.User;

import java.util.stream.Collectors;

public class UserDTO {

    private String name;

    private String login;

    private String roles;

    public UserDTO(User user) {
        this.name = user.getName();
        this.login = user.getLogin();
        this.roles = user.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(", "));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

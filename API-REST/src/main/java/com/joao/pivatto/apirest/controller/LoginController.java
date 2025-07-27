package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.infrastructure.Token;
import com.joao.pivatto.apirest.infrastructure.TokenService;
import com.joao.pivatto.apirest.model.Login;
import com.joao.pivatto.apirest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody Login login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var user = (User) auth.getPrincipal();
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(Map.of(
                    "token", Map.of("value", token),
                    "userId", user.getId(),
                    "userLogin", user.getLogin(),
                    "userRole", user.getRoles()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Bad credentials"));
        }
    }
}
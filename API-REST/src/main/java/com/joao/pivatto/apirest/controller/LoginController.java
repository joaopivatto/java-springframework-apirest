package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.infrastructure.Token;
import com.joao.pivatto.apirest.infrastructure.TokenService;
import com.joao.pivatto.apirest.model.Login;
import com.joao.pivatto.apirest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("")
    public ResponseEntity<Token> login(@RequestBody Login login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new Token(token));
    }
}
package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.dto.CreateUserDTO;
import com.joao.pivatto.apirest.dto.UpdateUserDTO;
import com.joao.pivatto.apirest.dto.UserDTO;
import com.joao.pivatto.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        try{
            return ResponseEntity.ok(userService.find(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> search(){
        return ResponseEntity.ok(userService.search());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.createUser(createUserDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, updateUserDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

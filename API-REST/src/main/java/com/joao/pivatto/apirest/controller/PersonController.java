package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.dto.PersonDTO;
import com.joao.pivatto.apirest.model.Person;
import com.joao.pivatto.apirest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        try{
            return ResponseEntity.ok(personService.find(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PersonDTO>> search(){
        return ResponseEntity.ok(personService.search());
    }

    @GetMapping("/name")
    public ResponseEntity<List<PersonDTO>> searchOrderByName(){
        return ResponseEntity.ok(personService.searchOrderByName());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Person person){
        try{
            return ResponseEntity.ok(personService.save(person));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return ResponseEntity.ok(personService.delete(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

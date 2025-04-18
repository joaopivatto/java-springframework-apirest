package com.joao.pivatto.apirest.service;

import com.joao.pivatto.apirest.dto.PersonDTO;
import com.joao.pivatto.apirest.model.Person;
import com.joao.pivatto.apirest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO find(int id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find person with id: " + id + "."));

        return new PersonDTO(person);

    }

    public List<PersonDTO> search(){
        return personRepository.findAll().stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    public List<PersonDTO> searchOrderByName(){
        return personRepository.findAllByOrderByNameAsc().stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    public String save(Person person){
        personRepository.save(person);
        return "Person successfully saved!";
    }

    public String delete(int id){
        personRepository.deleteById(id);
        return "Person successfully deleted!";
    }
}

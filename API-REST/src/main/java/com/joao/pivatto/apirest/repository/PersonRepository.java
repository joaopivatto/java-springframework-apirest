package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByOrderByNameAsc();
}

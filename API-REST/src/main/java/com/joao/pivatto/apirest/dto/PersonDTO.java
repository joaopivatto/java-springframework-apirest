package com.joao.pivatto.apirest.dto;

import com.joao.pivatto.apirest.model.Person;

import java.time.format.DateTimeFormatter;

public class PersonDTO {

    private String name;

    private String gender;

    private String birthDate;

    private String age;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.name = person.getName();
        this.gender = person.getGender();
        this.birthDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(person.getBirthDate());
        this.age = String.valueOf(person.getAge());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

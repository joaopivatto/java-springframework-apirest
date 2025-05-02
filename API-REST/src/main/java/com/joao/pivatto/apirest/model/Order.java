package com.joao.pivatto.apirest.model;

import com.joao.pivatto.apirest.dto.MakeOrderDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_id")
    private int personId;

    public Order() {}

    public Order(MakeOrderDTO makeOrderDTO) {
        this.personId = makeOrderDTO.getPersonId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}


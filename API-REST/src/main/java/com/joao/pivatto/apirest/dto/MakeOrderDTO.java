package com.joao.pivatto.apirest.dto;


import java.util.List;

public class MakeOrderDTO {

    private int id;

    private int personId;

    private List<SaveOrderItemDTO> orderItems;

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

    public List<SaveOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<SaveOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}

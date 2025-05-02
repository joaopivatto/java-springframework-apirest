package com.joao.pivatto.apirest.dto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class OrderDTO {

    private PersonDTO person;

    private List<OrderItemDTO> orderItems;

    private String price;

    public OrderDTO() {}

    public OrderDTO(PersonDTO person, List<OrderItemDTO> orderItems) {
        this.person = person;
        this.orderItems = orderItems;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPrice() throws ParseException {
        double totalPrice = 0;
        for (OrderItemDTO orderItem : orderItems) {
            double orderItemPrice = NumberFormat.getCurrencyInstance(new Locale("en", "US")).parse(orderItem.getProduct().getPrice()).doubleValue();
            totalPrice += orderItemPrice * orderItem.getQuantity();
        }
        if (totalPrice == 0) {
            return "$0.00";
        }else {
            return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(totalPrice);
        }
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

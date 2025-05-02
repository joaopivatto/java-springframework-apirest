package com.joao.pivatto.apirest.service;

import com.joao.pivatto.apirest.dto.*;
import com.joao.pivatto.apirest.handler.InvalidFieldException;
import com.joao.pivatto.apirest.handler.ResourceNotFoundException;
import com.joao.pivatto.apirest.model.Order;
import com.joao.pivatto.apirest.model.OrderItem;
import com.joao.pivatto.apirest.model.Person;
import com.joao.pivatto.apirest.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PersonService personService;

    public OrderDTO find(int id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find order with id: " + id + "."));
        PersonDTO person = personService.find(order.getPersonId());
        List<OrderItemDTO> orderItems = orderItemService.searchByOrderId(id);


        return new OrderDTO(person, orderItems);
    }

    public List<OrderDTO> search(){
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(personService.find(order.getPersonId()), orderItemService.searchByOrderId(order.getId())))
                .toList();
    }

    @Transactional
    public String save(MakeOrderDTO makeOrderDTO){
        if (makeOrderDTO.getOrderItems().isEmpty() || makeOrderDTO.getOrderItems() == null) {
            throw new InvalidFieldException("Order items cannot be empty!");
        }

        Order order = orderRepository.save(new Order(makeOrderDTO));
        for (SaveOrderItemDTO orderItem : makeOrderDTO.getOrderItems()) {
            orderItemService.save(new OrderItem(order.getId(), orderItem));
        }
        return "Order successfully saved!";
    }
}

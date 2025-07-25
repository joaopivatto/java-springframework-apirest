package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.dto.MakeOrderDTO;
import com.joao.pivatto.apirest.dto.OrderDTO;
import com.joao.pivatto.apirest.handler.ResourceNotFoundException;
import com.joao.pivatto.apirest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        try{
            return ResponseEntity.ok(orderService.find(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> search(){
        return ResponseEntity.ok(orderService.search());
    }

    @PostMapping("/make-order")
    public ResponseEntity<?> makeOrder(@RequestBody MakeOrderDTO makeOrderDTO){
        try{
            return ResponseEntity.ok(orderService.save(makeOrderDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Could not make order"));
        }
    }

}

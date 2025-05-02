package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderId(int id);
}

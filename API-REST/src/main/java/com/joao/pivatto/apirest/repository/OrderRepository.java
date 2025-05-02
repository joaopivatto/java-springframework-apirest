package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

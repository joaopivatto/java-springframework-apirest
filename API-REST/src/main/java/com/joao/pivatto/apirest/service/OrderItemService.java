package com.joao.pivatto.apirest.service;

import com.joao.pivatto.apirest.dto.OrderItemDTO;
import com.joao.pivatto.apirest.dto.ProductDTO;
import com.joao.pivatto.apirest.model.OrderItem;
import com.joao.pivatto.apirest.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    public List<OrderItemDTO> searchByOrderId(int id){
        Map<Integer, ProductDTO> productsMap = productService.search().stream()
                .collect(Collectors.toMap(ProductDTO::getId, productDTO -> productDTO));

        return orderItemRepository.findAllByOrderId(id).stream().map(orderItem
                -> new OrderItemDTO(productsMap.get(orderItem.getProductId()), orderItem.getQuantity())).collect(Collectors.toList());
    }

    public String save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
        return "OrderItem successfully saved!";
    }
}

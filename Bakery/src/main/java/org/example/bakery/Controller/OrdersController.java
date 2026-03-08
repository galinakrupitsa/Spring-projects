package org.example.bakery.Controller;

import org.example.bakery.DTO.OrderDTO;
import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.Service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {
    public final OrderService orderService;
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/orders")
    public OrderResponseDTO createOrderFromMenu(@RequestBody OrderDTO dto) {
        return orderService.createOrder(dto);
    }
}

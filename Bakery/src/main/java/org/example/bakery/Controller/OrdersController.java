package org.example.bakery.Controller;

import org.example.bakery.DTO.OrderDTO;
import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @GetMapping("/sum")
    public double totalBakerySum(){
        return orderService.getSumAll();
    }
    @GetMapping("/top")
    public Map<String, Integer> getTop(){
        return orderService.getTopItems();
    }
}

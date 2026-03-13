package org.example.bakery.Controller;

import org.example.bakery.DTO.OrderDTO;
import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.OrderResponseTodayDTO;
import org.example.bakery.Model.Orders;
import org.example.bakery.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    @GetMapping("/orders/count")
    public long getOrdersCount(){
        return orderService.getCount();
    }
    @GetMapping("/today")
    public List<OrderResponseTodayDTO> getTodayOrders(){
        return orderService.getTodayOrders();
    }
    @GetMapping("/date/{date}")
    public List<OrderResponseTodayDTO> getTodayOrders(@PathVariable LocalDate date){
        return orderService.getDayOrders(date);
    }
}

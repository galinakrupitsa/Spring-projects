package org.example.bakery.Controller;

import org.example.bakery.DTO.*;
import org.example.bakery.Model.Orders;
import org.example.bakery.Service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<OrderResponseTodayDTO> getTodayOrders(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date){
        return orderService.getDayOrders(date);
    }
    @GetMapping("/orders/customer/{id}")
    public List<OrderResponseDTO> getOrdersById(@PathVariable Long id){
        return orderService.getOrdersByCustomerId(id);
    }
    @GetMapping("/orders/customer/{id}/total")
    public int getTotalById(@PathVariable Long id){
        return orderService.getTotalById(id);
    }
    @GetMapping ("/orders/itemsToday/{date}")
    public List<ItemsDTO> getItemsToday(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return orderService.getItemsToday(date);
    }
    @GetMapping("/orders/bestDay")
    public BestDayDTO getBestDay(){
        return orderService.getBestDay();
    }
    @GetMapping("/orders/customer-spendings")
    public List<CustomerSpendingDTO> getCustomerSpendings(){
        return orderService.getCustomerSpending();
    }
}

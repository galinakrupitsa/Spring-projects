package org.example.bakery.Service;

import jakarta.persistence.criteria.Order;
import org.example.bakery.DTO.OrderDTO;

import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.OrderResponseTodayDTO;
import org.example.bakery.DTO.OrdersItemDTO;
import org.example.bakery.Exception.ItemNotFoundException;
import org.example.bakery.Model.Customer;
import org.example.bakery.Model.MenuItem;
import org.example.bakery.Model.Orders;
import org.example.bakery.Model.OrdersItem;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.CustomerRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    public final OrderRepository orderRepository;
    public final BakeryRepository bakeryRepository;
    public final StoreRepository storeRepository;
    public final StoreService storeService;
    public final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, BakeryRepository bakeryRepository, StoreRepository storeRepository, StoreService storeService, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bakeryRepository = bakeryRepository;
        this.storeRepository = storeRepository;
        this.storeService = storeService;
        this.customerRepository = customerRepository;
    }

    public OrderResponseDTO createOrder(OrderDTO dto) {
        // ищем клиента
        List<Customer> customers = customerRepository.findAll();
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(dto.getName()) && c.getGender().equalsIgnoreCase(dto.getGender())) {
                customer = c;
                break;
            }
        }
// Если клиент не найден, создаем нового
            if (customer == null) {
                Customer newCustomer = new Customer();
                newCustomer.setName(dto.getName());
                newCustomer.setGender(dto.getGender());
                customer = customerRepository.save(newCustomer);
            }
        // создаем заказ
            Orders order = new Orders();
            order.setCustomer(customer);
            List<OrdersItem> orderItems = new ArrayList<>();
            double total = 0;

            for (OrdersItemDTO itemDTO : dto.getOrdersItems()) {
                int quantity = itemDTO.getQuantity();
                MenuItem menuItem = bakeryRepository
                        .findById(itemDTO.getId())
                        .orElseThrow(() -> new ItemNotFoundException(
                        "Menu item with id " + itemDTO.getId() + " not found"
                ));

                OrdersItem item = new OrdersItem();
                item.setOrder(order);          // связь с заказом
                item.setItem(menuItem);        // товар
                item.setQuantity(quantity);   // количество
                double price = menuItem.getPrice();
                    if  (customer.getGender().equalsIgnoreCase("female")&&
                        (menuItem.getName().equalsIgnoreCase("Cruassant"))||
                        (menuItem.getName().equalsIgnoreCase("americano"))){
                        price = 0.5*price;
                }

                total += price * quantity;

                orderItems.add(item);
                storeService.decreaseQuantity(menuItem.getId(), quantity);
            }

            order.setItems(orderItems);
            order.setTotal(total);
            order.setCreatedAt(LocalDate.now());

            Orders savedOrder = orderRepository.save(order);

            OrderResponseDTO response = new OrderResponseDTO();
            response.setOrderId(savedOrder.getId());
            response.setTotal(savedOrder.getTotal());
            response.setOrderDate(savedOrder.getCreatedAt());
            return response;
        }


    public Double getSumAll() {
            List<Orders> orders = orderRepository.findAll();
            double total = 0;
            for (Orders order : orders) {
                total = total + order.getTotal();
            }
            return total;
        }
    public Map<String,Integer> getTopItems(){
        List<Orders> orders = orderRepository.findAll();
        Map<String,Integer> result = new HashMap<>();
        for (Orders order : orders) {
            List<OrdersItem> items = order.getItems();
            for (OrdersItem item : items) {
                String itemName = item.getItem().getName();
                int quantity = item.getQuantity();
                if (result.containsKey(itemName)) {
                    int current = result.get(itemName);
                    result.put(itemName, current + quantity);
                } else {
                    result.put(itemName, quantity);
                }
            }
        }
        return result;
    }
    public long getCount() {
        return  orderRepository.count();
    }
    public List<OrderResponseTodayDTO> getTodayOrders() {
        LocalDate today = LocalDate.now();
        List<Orders> orders = orderRepository.findAll();
        List<OrderResponseTodayDTO> responses = new ArrayList<>();

        for (Orders order : orders) {
            if (order.getCreatedAt().equals(today)){
                OrderResponseTodayDTO response = new OrderResponseTodayDTO();
                response.setId(order.getId());
                response.setTotal(order.getTotal());
                response.setDate(order.getCreatedAt());
                responses.add(response);
            }
        }
            return responses;
    }
    public List<OrderResponseTodayDTO> getDayOrders(LocalDate date) {
        List<Orders> orders = orderRepository.findByCreatedAt(date);
        List<OrderResponseTodayDTO> responses = new ArrayList<>();
        for (Orders order : orders) {
            OrderResponseTodayDTO response = new OrderResponseTodayDTO();
            response.setId(order.getId());
            response.setTotal(order.getTotal());
            response.setDate(order.getCreatedAt());
            responses.add(response);
        }
        return responses;
    }

}
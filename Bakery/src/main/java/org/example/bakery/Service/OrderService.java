package org.example.bakery.Service;

import org.example.bakery.DTO.OrderDTO;

import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.OrdersItemDTO;
import org.example.bakery.Model.MenuItem;
import org.example.bakery.Model.Orders;
import org.example.bakery.Model.OrdersItem;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public final OrderRepository orderRepository;
    public final BakeryRepository bakeryRepository;
    public final StoreRepository storeRepository;
    public OrderService(OrderRepository orderRepository, BakeryRepository bakeryRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.bakeryRepository = bakeryRepository;
        this.storeRepository = storeRepository;
    }

    public OrderResponseDTO createOrder(OrderDTO dto) {

        Orders order = new Orders();
        List<OrdersItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrdersItemDTO itemDTO : dto.getOrdersItems()) {
            int quantity = itemDTO.getQuantity();
            MenuItem menuItem = bakeryRepository
                    .findById(itemDTO.getId())
                    .orElseThrow();

            OrdersItem item = new OrdersItem();
            item.setOrder(order);          // связь с заказом
            item.setItem(menuItem);        // товар
            item.setQuantity(quantity);    // количество
            total += menuItem.getPrice() * quantity;

            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotal(total);

        Orders savedOrder = orderRepository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getId());
        response.setTotal(savedOrder.getTotal());

        return response;
    }
}

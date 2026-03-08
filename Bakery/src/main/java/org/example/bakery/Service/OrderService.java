package org.example.bakery.Service;

import org.example.bakery.DTO.OrderDTO;

import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.OrdersItemDTO;
import org.example.bakery.Model.MenuItem;
import org.example.bakery.Model.Orders;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public final OrderRepository orderRepository;
    public final BakeryRepository bakeryRepository;
    public OrderService(OrderRepository orderRepository, BakeryRepository bakeryRepository) {
        this.orderRepository = orderRepository;
        this.bakeryRepository = bakeryRepository;
    }

    public OrderResponseDTO createOrder(OrderDTO dto) {
        Orders or = new Orders();
        double total = 0;
        for (OrdersItemDTO item : dto.getOrdersItems() ) {
            int quantity = item.getQuantity();
            MenuItem menuItem = bakeryRepository
                    .findById(item.getId())
                    .orElseThrow();
            total += menuItem.getPrice() * quantity;
        }
        or.setTotal(total);

        Orders savedOrder = orderRepository.save(or);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getId());
        response.setTotal(savedOrder.getTotal());

        return response;
    }
}

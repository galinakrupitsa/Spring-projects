package org.example.bakery.Service;

import jakarta.transaction.Transactional;
import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.PaymentDTO;
import org.example.bakery.Model.Enums.PaymentMethod;
import org.example.bakery.Model.Enums.PaymentStatus;
import org.example.bakery.Model.Orders;
import org.example.bakery.Model.Payment;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    public String pay(PaymentDTO dto){
        Orders order = orderRepository.findById(dto.getOrderId()).
                orElseThrow(()-> new RuntimeException("Заказ не найден"));
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotal());
        payment.setMethod(PaymentMethod.valueOf(dto.getMethod().toUpperCase()));
        payment.setStatus(PaymentStatus.valueOf(dto.getStatus().toUpperCase()));
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
        return "Заказ " + dto.getOrderId() + " " + dto.getStatus();
    }
    @Transactional
    public List<OrderResponseDTO> getPaidOrders(){
        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.PAID);
        List<OrderResponseDTO> result = new ArrayList<>();

        for (Payment payment : payments){
            Orders order = payment.getOrder();
            OrderResponseDTO dto = new OrderResponseDTO();
            dto.setOrderId(order.getId());
            dto.setTotal(order.getTotal());
            dto.setOrderDate(order.getCreatedAt());
            result.add(dto);
        }
        return result;
    }
}

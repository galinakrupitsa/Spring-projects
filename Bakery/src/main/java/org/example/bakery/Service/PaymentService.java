package org.example.bakery.Service;

import org.example.bakery.DTO.PaymentDTO;
import org.example.bakery.Model.Enums.PaymentMethod;
import org.example.bakery.Model.Enums.PaymentStatus;
import org.example.bakery.Model.Orders;
import org.example.bakery.Model.Payment;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}

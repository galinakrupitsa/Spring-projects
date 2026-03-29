package org.example.bakery.Service;

import org.example.bakery.Model.Orders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderConsumer {
    private final PaymentService paymentService;
    private final OrderService orderService;
    public OrderConsumer(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }
    @KafkaListener(topics = "order-created", groupId = "bakery-group")
    public void handleOrderCreated(String orderIdStr) {

        System.out.println("📥 Получен заказ из Kafka: " + orderIdStr);

        Long orderId = Long.parseLong(orderIdStr);

        // 📦 Получаем заказ
        Optional<Orders> order = orderService.getById(orderId);

//        // 💳 1. создаём оплату
//        paymentService.createPayment(orderId, order.getTotal());
//
//        // 🔄 2. обновляем статус
//        orderService.updateStatus(orderId, OrderStatus.IN_PROGRESS);

        System.out.println("✅ Заказ обработан: " + orderId);
    }
}

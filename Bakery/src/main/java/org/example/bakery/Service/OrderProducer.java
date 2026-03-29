package org.example.bakery.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private KafkaTemplate<String, String> kafkaTemplate;
    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendOrderCreated(Long orderId) {
        kafkaTemplate.send("order-created", orderId.toString());
        System.out.println("📤 Отправлен заказ в Kafka: " + orderId);
    }
}

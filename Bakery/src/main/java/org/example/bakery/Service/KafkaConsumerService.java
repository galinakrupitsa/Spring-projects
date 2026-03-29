package org.example.bakery.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test-topic", groupId = "bakery-group")
    public void listen(String message) {
        System.out.println("🔥 Получено сообщение: " + message);
    }
}

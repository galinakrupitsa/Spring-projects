package org.example.bakery.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StoreConsumer {

    @KafkaListener(topics = "store-updated", groupId = "bakery-group")
    public void listen(String message) {
        System.out.println("📥 Обновление склада: " + message);
    }
}

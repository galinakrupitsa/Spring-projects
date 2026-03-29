package org.example.bakery.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public StoreProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStoreUpdate(String message) {
        kafkaTemplate.send("store-updated", message);
        System.out.println("📤 Kafka (store): " + message);
    }
}

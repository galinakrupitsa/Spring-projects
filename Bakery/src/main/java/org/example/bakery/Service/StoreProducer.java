package org.example.bakery.Service;

import org.example.bakery.DTO.StoreEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreProducer {

    private final KafkaTemplate<String, StoreEventDTO> kafkaTemplate;

    public StoreProducer(KafkaTemplate<String, StoreEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStoreUpdate(StoreEventDTO event) {
        kafkaTemplate.send("store-updated", event);
        System.out.println("📤 Kafka DTO: " + event);
    }
}

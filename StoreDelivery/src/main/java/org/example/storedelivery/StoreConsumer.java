package org.example.storedelivery;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class StoreConsumer {
    private final ObjectMapper objectMapper;

    public StoreConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "store-updated", groupId = "store-group")
    public void listen(String message) {

        System.out.println("📥 RAW JSON: " + message);

        try {
            StoreEventDTO event = objectMapper.readValue(message, StoreEventDTO.class);
            System.out.println("✅ Преобразовано в DTO: " + event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

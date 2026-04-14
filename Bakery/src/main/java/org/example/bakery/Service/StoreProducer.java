package org.example.bakery.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bakery.DTO.StoreEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public StoreProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendStoreUpdate(StoreEventDTO event) {
        try {
            String json = objectMapper.writeValueAsString(event); // используем event
            System.out.println("JSON: " + json);
            kafkaTemplate.send("store-updated", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

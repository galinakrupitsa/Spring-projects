package org.example.bakery.Service;

import org.example.bakery.DTO.StoreEventDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StoreConsumer {

//    @KafkaListener(topics = "store-updated", groupId = "bakery-group")
//    public void listen(StoreEventDTO event) {
//
//        System.out.println("📥 Получен DTO:");
//        System.out.println("Товар: " + event.getItemName());
//        System.out.println("Количество: " + event.getQuantity());
//        System.out.println("Действие: " + event.getAction());
//    }
}

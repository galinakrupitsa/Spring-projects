package org.example.bakery.Controller;

import org.example.bakery.Service.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducerService producer;
    public KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }
    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Сообщение получено : " + message;
    }
}

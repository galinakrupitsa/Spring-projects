package org.example.storedelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class StoreDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreDeliveryApplication.class, args);
    }

}

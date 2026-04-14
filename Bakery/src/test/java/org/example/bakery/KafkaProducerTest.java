package org.example.bakery;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.bakery.Model.Orders;
import org.example.bakery.Service.OrderProducer;
import org.example.bakery.Service.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @InjectMocks
    private OrderProducer orderProducer;

    public void KafkaProducerSendTest(){
        Long orderId = 123L;
        orderProducer.sendOrderCreated(orderId);
        verify(kafkaTemplate).send("order-created", orderId.toString());

    }

}

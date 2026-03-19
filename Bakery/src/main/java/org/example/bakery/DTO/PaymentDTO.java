package org.example.bakery.DTO;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long orderId;
    private String method;
    private String status;
}

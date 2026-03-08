package org.example.bakery.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Double total;
}

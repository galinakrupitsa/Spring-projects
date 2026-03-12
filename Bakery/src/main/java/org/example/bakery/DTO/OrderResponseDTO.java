package org.example.bakery.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Double total;
    private LocalDate orderDate;
}

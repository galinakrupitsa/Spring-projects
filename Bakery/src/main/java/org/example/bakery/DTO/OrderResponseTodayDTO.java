package org.example.bakery.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderResponseTodayDTO {
    private Long id;
    private Double total;
    private LocalDate date;
}

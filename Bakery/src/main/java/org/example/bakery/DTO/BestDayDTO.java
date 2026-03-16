package org.example.bakery.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BestDayDTO {
    public LocalDate date;
    public Double revenue;

    public BestDayDTO(LocalDate bestDate, double maxRevenue) {
        this.date = bestDate;
        this.revenue = maxRevenue;
    }
}

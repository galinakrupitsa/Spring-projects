package org.example.bakery.DTO;

import lombok.Data;

@Data
public class CustomerSpendingDTO {
    private String customerName;
    private Double totalSpent;
    public CustomerSpendingDTO(String customerName, Double totalSpent) {
        this.customerName = customerName;
        this.totalSpent = totalSpent;
    }
}

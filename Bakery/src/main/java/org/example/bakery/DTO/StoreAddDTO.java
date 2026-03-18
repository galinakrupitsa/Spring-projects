package org.example.bakery.DTO;

import lombok.Data;

@Data
public class StoreAddDTO {
    private Long id;
    private int quantity;
    private String itemName;
}

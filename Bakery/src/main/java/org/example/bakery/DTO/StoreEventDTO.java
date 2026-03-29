package org.example.bakery.DTO;

import lombok.Data;

@Data
public class StoreEventDTO {
    private Long itemId;
    private String itemName;
    private int quantity;
    private String action;
}

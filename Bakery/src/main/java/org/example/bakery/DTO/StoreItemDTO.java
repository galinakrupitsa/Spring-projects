package org.example.bakery.DTO;

import lombok.Data;

@Data
public class StoreItemDTO {
    private String storeItemName;
    private int availableQuantity;

    public StoreItemDTO(String storeItemName, int availableQuantity) {
        this.storeItemName = storeItemName;
        this.availableQuantity = availableQuantity;
    }
}

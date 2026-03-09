package org.example.bakery.DTO;

import lombok.Data;
import org.example.bakery.Model.Orders;

@Data
public class OrdersItemDTO {
    private Long id;
    private int quantity;
    private Orders order;
}

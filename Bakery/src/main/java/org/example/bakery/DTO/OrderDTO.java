package org.example.bakery.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private List<OrdersItemDTO> ordersItems = new ArrayList<>();
    private String name;
    private String gender;
}

package org.example.bakery.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrdersItem {
    @Id
    private Long id;
    private Long itemId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
}

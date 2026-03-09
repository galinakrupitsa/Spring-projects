package org.example.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "orders_item")
public class OrdersItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem item;

    private int quantity;
}

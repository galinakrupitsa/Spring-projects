package org.example.bakery.Model;

import jakarta.persistence.*;

@Entity
//@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuItem itemId;
    private int  quantity;
    private double total;
}

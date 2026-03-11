package org.example.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
//@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrdersItem> items;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

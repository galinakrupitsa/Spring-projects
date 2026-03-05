package org.example.bakery.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
}

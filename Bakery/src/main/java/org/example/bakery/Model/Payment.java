package org.example.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bakery.Model.Enums.PaymentMethod;
import org.example.bakery.Model.Enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private LocalDateTime createdAt;
}

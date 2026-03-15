package org.example.bakery.Repository;
import org.example.bakery.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCreatedAt(LocalDate date);
    List<Orders> findByCustomerId(Long customerId);

}

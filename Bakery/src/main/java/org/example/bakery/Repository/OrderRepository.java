package org.example.bakery.Repository;
import org.example.bakery.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCreatedAt(LocalDate date);
    List<Orders> findByCustomerId(Long customerId);
    Optional<Orders> findById(Long id);

}

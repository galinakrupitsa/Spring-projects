package org.example.bakery.Repository;

import org.example.bakery.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakeryRepository extends JpaRepository<MenuItem, Long> {

}

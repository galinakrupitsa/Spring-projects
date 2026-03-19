package org.example.bakery.Repository;
import org.example.bakery.Model.MenuItem;
import org.example.bakery.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s JOIN FETCH s.item")
    List<Store> findAllWithItems();
    Store findByItemId(Long itemId);

    Optional<Store> findByItem(MenuItem item);
}


package org.example.bakery.Service;

import org.example.bakery.Model.Store;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository, OrderRepository orderRepository, BakeryRepository bakeryRepository) {
        this.storeRepository = storeRepository;

    }
   public List<Store> showAll(){
        return storeRepository.findAllWithItems();
   }
}
